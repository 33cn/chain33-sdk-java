/*
 * Copyright 2022 Web3 Labs Ltd.
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
package cn.chain33.javasdk.model.abi.datatypes.ens;

import cn.chain33.javasdk.model.abi.datatypes.DynamicBytes;
import cn.chain33.javasdk.model.abi.datatypes.Type;

import java.util.Arrays;
import java.util.List;

public class EnsCallBackFunctionParams {

    private DynamicBytes gatewayResponse;

    private DynamicBytes extraData;

    public EnsCallBackFunctionParams(DynamicBytes gatewayResponse, DynamicBytes extraData) {
        this.gatewayResponse = gatewayResponse;
        this.extraData = extraData;
    }

    public EnsCallBackFunctionParams(byte[] gatewayResponse, byte[] extraData) {
        this.gatewayResponse = new DynamicBytes(gatewayResponse);
        this.extraData = new DynamicBytes(extraData);
    }

    public List<Type> getParams() {
        return Arrays.asList(gatewayResponse, extraData);
    }

    public DynamicBytes getGatewayResponse() {
        return gatewayResponse;
    }

    public void setGatewayResponse(DynamicBytes gatewayResponse) {
        this.gatewayResponse = gatewayResponse;
    }

    public DynamicBytes getExtraData() {
        return extraData;
    }

    public void setExtraData(DynamicBytes extraData) {
        this.extraData = extraData;
    }
}
