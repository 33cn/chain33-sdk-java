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

import cn.chain33.javasdk.model.abi.datatypes.*;
import cn.chain33.javasdk.model.abi.datatypes.generated.Bytes32;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.chain33.javasdk.model.abi.TypeDecoder.MAX_BYTE_LENGTH_FOR_HEX_STRING;
import static cn.chain33.javasdk.model.abi.TypeDecoder.isDynamic;
import static cn.chain33.javasdk.model.abi.Utils.getParameterizedTypeFromArray;
import static cn.chain33.javasdk.model.abi.Utils.staticStructNestedPublicFieldsFlatList;

/**
 * Ethereum Contract Application Binary Interface (ABI) encoding for functions. Further details are
 * available <a href="https://github.com/ethereum/wiki/wiki/Ethereum-Contract-ABI">here</a>.
 */
public class DefaultFunctionReturnDecoder extends FunctionReturnDecoder {

    public List<Type> decodeFunctionResult(
            String rawInput, List<TypeReference<Type>> outputParameters) {

        String input = Numeric.cleanHexPrefix(rawInput);

        if (Strings.isEmpty(input)) {
            return Collections.emptyList();
        } else {
            return build(input, outputParameters);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Type> Type decodeEventParameter(
            String rawInput, TypeReference<T> typeReference) {

        String input = Numeric.cleanHexPrefix(rawInput);

        try {
            Class<T> type = typeReference.getClassType();

            if (Bytes.class.isAssignableFrom(type)) {
                Class<Bytes> bytesClass = (Class<Bytes>) Class.forName(type.getName());
                return TypeDecoder.decodeBytes(input, bytesClass);
            } else if (Array.class.isAssignableFrom(type)
                    || BytesType.class.isAssignableFrom(type)
                    || Utf8String.class.isAssignableFrom(type)) {
                return TypeDecoder.decodeBytes(input, Bytes32.class);
            } else {
                return TypeDecoder.decode(input, type);
            }
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Invalid class reference provided", e);
        }
    }

    public List<Type> decodeEventParameters(
            String rawInput, List<TypeReference<Type>> inputParameters) {
        return decodeFunctionResult(rawInput,inputParameters);
    }

    private static List<Type> build(String input, List<TypeReference<Type>> outputParameters) {
        List<Type> results = new ArrayList<>(outputParameters.size());

        int offset = 0;
        for (TypeReference<?> typeReference : outputParameters) {
            try {
                int hexStringDataOffset = getDataOffset(input, offset, typeReference);

                @SuppressWarnings("unchecked")
                Class<Type> classType = (Class<Type>) typeReference.getClassType();

                Type result;
                if (DynamicStruct.class.isAssignableFrom(classType)) {
                    result =
                            TypeDecoder.decodeDynamicStruct(
                                    input, hexStringDataOffset, typeReference);
                    offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;

                } else if (DynamicArray.class.isAssignableFrom(classType)) {
                    result =
                            TypeDecoder.decodeDynamicArray(
                                    input, hexStringDataOffset, typeReference);
                    offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;

                } else if (typeReference instanceof TypeReference.StaticArrayTypeReference) {
                    int length = ((TypeReference.StaticArrayTypeReference) typeReference).getSize();
                    result =
                            TypeDecoder.decodeStaticArray(
                                    input, hexStringDataOffset, typeReference, length);
                    offset += length * MAX_BYTE_LENGTH_FOR_HEX_STRING;

                } else if (StaticStruct.class.isAssignableFrom(classType)) {
                    result =
                            TypeDecoder.decodeStaticStruct(
                                    input, hexStringDataOffset, typeReference);
                    offset +=
                            staticStructNestedPublicFieldsFlatList(classType).size()
                                    * MAX_BYTE_LENGTH_FOR_HEX_STRING;
                } else if (StaticArray.class.isAssignableFrom(classType)) {
                    int length =
                            Integer.parseInt(
                                    classType
                                            .getSimpleName()
                                            .substring(StaticArray.class.getSimpleName().length()));
                    result =
                            TypeDecoder.decodeStaticArray(
                                    input, hexStringDataOffset, typeReference, length);
                    if (DynamicStruct.class.isAssignableFrom(
                            getParameterizedTypeFromArray(typeReference))) {
                        offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    } else if (StaticStruct.class.isAssignableFrom(
                            getParameterizedTypeFromArray(typeReference))) {
                        offset +=
                                staticStructNestedPublicFieldsFlatList(
                                        getParameterizedTypeFromArray(
                                                typeReference))
                                        .size()
                                        * length
                                        * MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    } else if (Utf8String.class.isAssignableFrom(
                            getParameterizedTypeFromArray(typeReference))) {
                        offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    } else {
                        offset += length * MAX_BYTE_LENGTH_FOR_HEX_STRING;
                    }
                } else {
                    result = TypeDecoder.decode(input, hexStringDataOffset, classType);
                    offset += MAX_BYTE_LENGTH_FOR_HEX_STRING;
                }
                results.add(result);

            } catch (ClassNotFoundException e) {
                throw new UnsupportedOperationException("Invalid class reference provided", e);
            }
        }
        return results;
    }

    public static <T extends Type> int getDataOffset(
            String input, int offset, TypeReference<?> typeReference)
            throws ClassNotFoundException {
        @SuppressWarnings("unchecked")
        Class<Type> type = (Class<Type>) typeReference.getClassType();
        if (DynamicBytes.class.isAssignableFrom(type)
                || Utf8String.class.isAssignableFrom(type)
                || DynamicArray.class.isAssignableFrom(type)
                || hasDynamicOffsetInStaticArray(typeReference, offset)) {
            return TypeDecoder.decodeUintAsInt(input, offset) << 1;
        } else {
            return offset;
        }
    }

    /**
     * Checks if the parametrized type is offsetted in case of static array containing structs.
     *
     * @param typeReference of static array
     * @return true, if static array elements have dynamic offsets
     * @throws ClassNotFoundException if class type cannot be determined
     */
    private static boolean hasDynamicOffsetInStaticArray(TypeReference<?> typeReference, int offset)
            throws ClassNotFoundException {
        @SuppressWarnings("unchecked")
        Class<Type> type = (Class<Type>) typeReference.getClassType();
        try {
            return StaticArray.class.isAssignableFrom(type)
                    && (DynamicStruct.class.isAssignableFrom(
                    getParameterizedTypeFromArray(typeReference))
                    || isDynamic(getParameterizedTypeFromArray(typeReference)));
        } catch (ClassCastException e) {
            return false;
        }
    }
}
