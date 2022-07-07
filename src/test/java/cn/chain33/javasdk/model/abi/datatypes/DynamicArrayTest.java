/*
 * Copyright 2021 Web3 Labs Ltd.
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

import cn.chain33.javasdk.model.abi.datatypes.generated.Uint8;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicArrayTest {

    @Test
    public void testEmptyDynamicArray() {
        final DynamicArray<Address> array =
                new DynamicArray<>(Address.class, Collections.emptyList());

        assertEquals(Address.TYPE_NAME + "[]", array.getTypeAsString());
    }

    @Test
    public void testDynamicArrayWithDynamicStruct() {
        final List<DynamicStruct> list = Collections.singletonList(new DynamicStruct());
        final DynamicArray<DynamicStruct> array = new DynamicArray<>(DynamicStruct.class, list);

        assertEquals("()[]", array.getTypeAsString());
    }

    @Test
    public void testDynamicArrayWithAbiType() {
        final DynamicArray<Uint> array = new DynamicArray<>(Uint.class, arrayOfUints(1));

        assertEquals(Uint.TYPE_NAME + "[]", array.getTypeAsString());
    }

    private Uint[] arrayOfUints(int length) {
        return IntStream.rangeClosed(1, length).mapToObj(Uint8::new).toArray(Uint[]::new);
    }
}
