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

import cn.chain33.javasdk.model.abi.datatypes.Address;
import cn.chain33.javasdk.model.abi.datatypes.DynamicBytes;
import cn.chain33.javasdk.model.abi.datatypes.Type;
import cn.chain33.javasdk.model.abi.spi.FunctionReturnDecoderProvider;

import java.util.*;

/**
 * Decodes values returned by function or event calls.
 *
 * <p>Delegates to {@link DefaultFunctionReturnDecoder} unless a {@link
 * FunctionReturnDecoderProvider} SPI is found, in which case the first implementation found will be
 * used.
 *
 * @see DefaultFunctionReturnDecoder
 * @see FunctionReturnDecoderProvider
 */
public abstract class FunctionReturnDecoder {

    private static final FunctionReturnDecoder decoder;

    static {
        ServiceLoader<FunctionReturnDecoderProvider> loader =
                ServiceLoader.load(FunctionReturnDecoderProvider.class);
        final Iterator<FunctionReturnDecoderProvider> iterator = loader.iterator();

        decoder = iterator.hasNext() ? iterator.next().get() : new DefaultFunctionReturnDecoder();
    }

    /**
     * Decode ABI encoded return values from smart contract function call.
     *
     * @param rawInput         ABI encoded input
     * @param outputParameters list of return types as {@link TypeReference}
     * @return {@link List} of values returned by function, {@link Collections#emptyList()} if
     * invalid response
     */
    public static List<Type> decode(String rawInput, List<TypeReference<Type>> outputParameters) {
        return decoder.decodeFunctionResult(rawInput, outputParameters);
    }

    /**
     * Decode ABI encoded return value DynamicBytes from smart contract function call.
     *
     * @param rawInput ABI encoded input
     * @return {@link DynamicBytes} of values returned by function, null if invalid response
     */
    public static byte[] decodeDynamicBytes(String rawInput) {
        List outputParameters = new ArrayList<TypeReference<Type>>();
        outputParameters.add(new TypeReference<DynamicBytes>() {
        });

        List<Type> typeList = decoder.decodeFunctionResult(rawInput, outputParameters);

        return typeList.isEmpty() ? null : ((DynamicBytes) typeList.get(0)).getValue();
    }

    public static String decodeAddress(String rawInput) {
        List outputParameters = new ArrayList<TypeReference<Type>>();
        outputParameters.add(new TypeReference<Address>() {
        });

        List<Type> typeList = decoder.decodeFunctionResult(rawInput, outputParameters);

        return typeList.isEmpty() ? null : ((Address) typeList.get(0)).getValue();
    }

    /**
     * Decodes an indexed parameter associated with an event. Indexed parameters are individually
     * encoded, unlike non-indexed parameters which are encoded as per ABI-encoded function
     * parameters and return values.
     *
     * <p>If any of the following types are indexed, the Keccak-256 hashes of the values are
     * returned instead. These are returned as a bytes32 value.
     *
     * <ul>
     *   <li>Arrays
     *   <li>Strings
     *   <li>Bytes
     * </ul>
     *
     * <p>See the <a href="http://solidity.readthedocs.io/en/latest/contracts.html#events">Solidity
     * documentation</a> for further information.
     *
     * @param rawInput      ABI encoded input
     * @param typeReference of expected result type
     * @param <T>           type of TypeReference
     * @return the decode value
     */
    public static <T extends Type> Type decodeIndexedValue(
            String rawInput, TypeReference<T> typeReference) {
        return decoder.decodeEventParameter(rawInput, typeReference);
    }

    protected abstract List<Type> decodeFunctionResult(
            String rawInput, List<TypeReference<Type>> outputParameters);

    protected abstract <T extends Type> Type decodeEventParameter(
            String rawInput, TypeReference<T> typeReference);

    protected abstract List<Type> decodeEventParameters(
            String rawInput, List<TypeReference<Type>> inputParameters);
}
