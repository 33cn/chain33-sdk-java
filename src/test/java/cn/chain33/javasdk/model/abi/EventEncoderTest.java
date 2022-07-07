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
import cn.chain33.javasdk.model.abi.datatypes.AddressBTC;
import cn.chain33.javasdk.model.abi.datatypes.AddressETH;
import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.abi.datatypes.generated.Uint256;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static cn.chain33.javasdk.model.abi.Utils.convert;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventEncoderTest {

    @Test
    public void testBuildEventSignature() {
        assertEquals(
                EventEncoder.buildEventSignature("Deposit(address,hash256,uint256)"),
                ("0x50cb9fe53daa9737b786ab3646f04d0150dc50ef4e75f59509d83667ad5adb20"));


        assertEquals(
                EventEncoder.buildEventSignature("Notify(uint256,uint256)"),
                ("0x71e71a8458267085d5ab16980fd5f114d2d37f232479c245d523ce8d23ca40ed"));
    }

    @Test
    public void testEncode() {
        Event event =
                new Event(
                        "Notify",
                        Arrays.<TypeReference<?>>asList(
                                new TypeReference<Address>() {
                                }, new TypeReference<Uint256>() {
                                }));

        assertEquals(
                EventEncoder.encode(event),
                "0x534b8c68fce1c341e67c2f4a3e2cebe2c8c749e662eb7a303cfb265e63141dec");

        event =
                new Event(
                        "Notify",
                        Arrays.<TypeReference<?>>asList(
                                new TypeReference<AddressETH>() {
                                }, new TypeReference<Uint256>() {
                                }));
        assertEquals(
                EventEncoder.encode(event),
                "0x534b8c68fce1c341e67c2f4a3e2cebe2c8c749e662eb7a303cfb265e63141dec");
        event =
                new Event(
                        "Notify",
                        Arrays.<TypeReference<?>>asList(
                                new TypeReference<AddressBTC>() {
                                }, new TypeReference<Uint256>() {
                                }));
        assertEquals(
                EventEncoder.encode(event),
                "0x534b8c68fce1c341e67c2f4a3e2cebe2c8c749e662eb7a303cfb265e63141dec");


    }

    @Test
    public void testBuildMethodSignature() {
        List<TypeReference<?>> parameters =
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<Address>() {
                        }, new TypeReference<Uint256>() {
                        });

        assertEquals(
                EventEncoder.buildMethodSignature("Notify", convert(parameters)),
                "Notify(address,uint256)");
        parameters =
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<AddressETH>() {
                        }, new TypeReference<Uint256>() {
                        });

        assertEquals(
                EventEncoder.buildMethodSignature("Notify", convert(parameters)),
                "Notify(address,uint256)");
        parameters =
                Arrays.<TypeReference<?>>asList(
                        new TypeReference<AddressBTC>() {
                        }, new TypeReference<Uint256>() {
                        });

        assertEquals(
                EventEncoder.buildMethodSignature("Notify", convert(parameters)),
                "Notify(address,uint256)");
    }
}
