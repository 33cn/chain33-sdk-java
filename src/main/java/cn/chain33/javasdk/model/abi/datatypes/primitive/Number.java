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
package cn.chain33.javasdk.model.abi.datatypes.primitive;

import cn.chain33.javasdk.model.abi.datatypes.NumericType;

public abstract class Number<T extends java.lang.Number & Comparable<T>> extends PrimitiveType<T> {
    Number(T value) {
        super(value);
    }

    @Override
    public abstract NumericType toSolidityType();
}
