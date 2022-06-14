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
package cn.chain33.javasdk.model.abi.datatypes;

import java.math.BigInteger;

/**
 * Common integer properties.
 */
public abstract class IntType extends NumericType {

    private final int bitSize;

    public IntType(String typePrefix, int bitSize, BigInteger value) {
        super(typePrefix + bitSize, value);
        this.bitSize = bitSize;
        if (!valid()) {
            throw new UnsupportedOperationException(
                    "Bit size must be 8 bit aligned, "
                            + "and in range 0 < bitSize <= "
                            + MAX_BIT_LENGTH);
        }
    }

    public int getBitSize() {
        return bitSize;
    }

    protected boolean valid() {
        return isValidBitSize(bitSize) && isValidBitCount(bitSize, value);
    }

    private static boolean isValidBitSize(int bitSize) {
        return bitSize % 8 == 0 && bitSize > 0 && bitSize <= MAX_BIT_LENGTH;
    }

    private static boolean isValidBitCount(int bitSize, BigInteger value) {
        return value.bitLength() <= bitSize;
    }
}
