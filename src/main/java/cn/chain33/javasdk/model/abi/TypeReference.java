/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package cn.chain33.javasdk.model.abi;

import cn.chain33.javasdk.model.abi.datatypes.AbiTypes;
import cn.chain33.javasdk.model.abi.datatypes.DynamicArray;
import cn.chain33.javasdk.model.abi.datatypes.StaticArray;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Type wrapper to get around limitations of Java's type erasure. This is so that we can pass around
 * Typed {@link cn.chain33.javasdk.model.abi.datatypes.Array} types.
 *
 * <p>See <a href="http://gafter.blogspot.com.au/2006/12/super-type-tokens.html">this blog post</a>
 * for further details.
 *
 * <p>It may make sense to switch to using Java's reflection <a
 * href="https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Type.html">Type</a> to avoid
 * working around this fundamental generics limitation.
 */
public abstract class TypeReference<T extends cn.chain33.javasdk.model.abi.datatypes.Type>
        implements Comparable<TypeReference<T>> {
    protected static Pattern ARRAY_SUFFIX = Pattern.compile("\\[(\\d*)]");

    private final Type type;
    private final boolean indexed;

    protected TypeReference() {
        this(false);
    }

    protected TypeReference(boolean indexed) {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        this.indexed = indexed;
    }

    /**
     * getSubTypeReference() is used by instantiateType to see what TypeReference is wrapped by this
     * one. eg calling getSubTypeReference() on a TypeReference to
     * DynamicArray[StaticArray3[Uint256]] would return a TypeReference to StaticArray3[Uint256]
     *
     * @return the type wrapped by this Array TypeReference, or null if not Array
     */
    TypeReference getSubTypeReference() {
        return null;
    }

    public int compareTo(TypeReference<T> o) {
        // taken from the blog post comments - this results in an errror if the
        // type parameter is left out.
        return 0;
    }

    public Type getType() {
        return type;
    }

    public boolean isIndexed() {
        return indexed;
    }

    /**
     * Workaround to ensure type does not come back as T due to erasure, this enables you to create
     * a TypeReference via {@link Class Class&lt;T&gt;}.
     *
     * @return the parameterized Class type if applicable, otherwise a regular class
     * @throws ClassNotFoundException if the class type cannot be determined
     */
    @SuppressWarnings("unchecked")
    public Class<T> getClassType() throws ClassNotFoundException {
        Type clsType = getType();

        if (getType() instanceof ParameterizedType) {
            return (Class<T>) ((ParameterizedType) clsType).getRawType();
        } else {
            return (Class<T>) Class.forName(clsType.getTypeName());
        }
    }

    public static <T extends cn.chain33.javasdk.model.abi.datatypes.Type> TypeReference<T> create(Class<T> cls) {
        return create(cls, false);
    }

    public static <T extends cn.chain33.javasdk.model.abi.datatypes.Type> TypeReference<T> create(
            Class<T> cls, boolean indexed) {
        return new TypeReference<T>(indexed) {
            public java.lang.reflect.Type getType() {
                return cls;
            }
        };
    }

    /**
     * This is a helper method that only works for atomic types (uint, bytes, etc). Array types must
     * be wrapped by a {@link java.lang.reflect.ParameterizedType}.
     *
     * @param solidityType the solidity as a string eg Address Int
     * @param primitives   is it a primitive type
     * @return returns
     * @throws ClassNotFoundException when the class cannot be found.
     */
    protected static Class<? extends cn.chain33.javasdk.model.abi.datatypes.Type> getAtomicTypeClass(
            String solidityType, boolean primitives) throws ClassNotFoundException {

        if (ARRAY_SUFFIX.matcher(solidityType).find()) {
            throw new ClassNotFoundException(
                    "getAtomicTypeClass does not work with array types."
                            + " See makeTypeReference()");
        } else {
            return AbiTypes.getType(solidityType, primitives);
        }
    }

    public abstract static class StaticArrayTypeReference<T extends cn.chain33.javasdk.model.abi.datatypes.Type>
            extends TypeReference<T> {

        private final int size;

        protected StaticArrayTypeReference(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    public static TypeReference makeTypeReference(String solidityType)
            throws ClassNotFoundException {
        return makeTypeReference(solidityType, false, false);
    }

    public static TypeReference makeTypeReference(
            String solidityType, final boolean indexed, final boolean primitives)
            throws ClassNotFoundException {

        Matcher nextSquareBrackets = ARRAY_SUFFIX.matcher(solidityType);
        if (!nextSquareBrackets.find()) {
            final Class<? extends cn.chain33.javasdk.model.abi.datatypes.Type> typeClass =
                    getAtomicTypeClass(solidityType, primitives);
            return create(typeClass, indexed);
        }

        int lastReadStringPosition = nextSquareBrackets.start();

        final Class<? extends cn.chain33.javasdk.model.abi.datatypes.Type> baseClass =
                getAtomicTypeClass(solidityType.substring(0, lastReadStringPosition), primitives);

        TypeReference arrayWrappedType = create(baseClass, indexed);
        final int len = solidityType.length();

        // for each [\d*], wrap the previous TypeReference in an array
        while (lastReadStringPosition < len) {
            String arraySize = nextSquareBrackets.group(1);
            final TypeReference baseTr = arrayWrappedType;
            if (arraySize == null || arraySize.equals("")) {
                arrayWrappedType =
                        new TypeReference<DynamicArray>(indexed) {
                            @Override
                            TypeReference getSubTypeReference() {
                                return baseTr;
                            }

                            @Override
                            public java.lang.reflect.Type getType() {
                                return new ParameterizedType() {
                                    @Override
                                    public java.lang.reflect.Type[] getActualTypeArguments() {
                                        return new java.lang.reflect.Type[]{baseTr.getType()};
                                    }

                                    @Override
                                    public java.lang.reflect.Type getRawType() {
                                        return DynamicArray.class;
                                    }

                                    @Override
                                    public java.lang.reflect.Type getOwnerType() {
                                        return Class.class;
                                    }
                                };
                            }
                        };
            } else {
                final Class arrayclass;
                int arraySizeInt = Integer.parseInt(arraySize);
                if (arraySizeInt <= StaticArray.MAX_SIZE_OF_STATIC_ARRAY) {
                    arrayclass =
                            Class.forName(
                                    "cn.chain33.javasdk.model.abi.datatypes.generated.StaticArray" + arraySize);
                } else {
                    arrayclass = StaticArray.class;
                }
                arrayWrappedType =
                        new TypeReference.StaticArrayTypeReference<StaticArray>(arraySizeInt) {

                            @Override
                            TypeReference getSubTypeReference() {
                                return baseTr;
                            }

                            @Override
                            public boolean isIndexed() {
                                return indexed;
                            }

                            @Override
                            public java.lang.reflect.Type getType() {
                                return new ParameterizedType() {
                                    @Override
                                    public java.lang.reflect.Type[] getActualTypeArguments() {
                                        return new java.lang.reflect.Type[]{baseTr.getType()};
                                    }

                                    @Override
                                    public java.lang.reflect.Type getRawType() {
                                        return arrayclass;
                                    }

                                    @Override
                                    public java.lang.reflect.Type getOwnerType() {
                                        return Class.class;
                                    }
                                };
                            }
                        };
            }
            lastReadStringPosition = nextSquareBrackets.end();
            nextSquareBrackets = ARRAY_SUFFIX.matcher(solidityType);
            // cant find any more [] and string isn't fully parsed
            if (!nextSquareBrackets.find(lastReadStringPosition) && lastReadStringPosition != len) {
                throw new ClassNotFoundException(
                        "Unable to make TypeReference from " + solidityType);
            }
        }
        return arrayWrappedType;
    }
}
