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
 * UTF-8 encoded string type.
 */
public class Utf8String implements Type<String> {

    public static final String TYPE_NAME = "string";
    public static final Utf8String DEFAULT = new Utf8String("");

    private String value;

    public Utf8String(String value) {
        this.value = value;
    }

    /**
     * Returns the Bytes32 Padded length. If the string is empty, we only encode its length. Else,
     * we concatenate its length along of its value
     */
    @Override
    public int bytes32PaddedLength() {
        if (value.isEmpty()) {
            return MAX_BYTE_LENGTH;
        } else {
            return 2 * MAX_BYTE_LENGTH;
        }
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getTypeAsString() {
        return TYPE_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Utf8String that = (Utf8String) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
