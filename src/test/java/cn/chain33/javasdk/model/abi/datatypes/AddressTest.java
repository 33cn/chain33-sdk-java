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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    public void testToString() {
        assertEquals(
                new Address("52b08330e05d731e38c856c1043288f7d9744").toString(),
                ("0x00052b08330e05d731e38c856c1043288f7d9744"));
        assertEquals(
                new Address("0x00052b08330e05d731e38c856c1043288f7d9744").toString(),
                ("0x00052b08330e05d731e38c856c1043288f7d9744"));
    }
}
