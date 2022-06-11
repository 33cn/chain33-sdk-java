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

/**
 * Dynamically allocated sequence of bytes.
 */
public class DynamicBytes extends BytesType {

    public static final String TYPE_NAME = "bytes";
    public static final DynamicBytes DEFAULT = new DynamicBytes(new byte[]{});

    public DynamicBytes(byte[] value) {
        super(value, TYPE_NAME);
    }
}
