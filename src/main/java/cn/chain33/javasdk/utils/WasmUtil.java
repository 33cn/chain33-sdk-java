package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.enums.WasmEnum;
import cn.chain33.javasdk.model.protobuf.WasmProtobuf;
import com.google.protobuf.ByteString;

public class WasmUtil {
    public static WasmProtobuf.wasmAction createWasmContract(String name, byte[] codes) {
        WasmProtobuf.wasmAction.Builder action = WasmProtobuf.wasmAction.newBuilder();
        WasmProtobuf.wasmCreate.Builder builder = WasmProtobuf.wasmCreate.newBuilder();
        builder.setName(name);
        builder.setCode(ByteString.copyFrom(codes));
        action.setCreate(builder);
        action.setTy(WasmEnum.WasmCreate.getTy());
        return action.build();
    }

    public static WasmProtobuf.wasmAction updateWasmContract(String name,byte[] codes) {
        WasmProtobuf.wasmAction.Builder action = WasmProtobuf.wasmAction.newBuilder();
        WasmProtobuf.wasmUpdate.Builder builder = WasmProtobuf.wasmUpdate.newBuilder();
        builder.setName(name);
        builder.setCode(ByteString.copyFrom(codes));
        action.setUpdate(builder);
        action.setTy(WasmEnum.WasmUpdate.getTy());
        return action.build();
    }

    public static WasmProtobuf.wasmAction createWasmCallContract(String name, String method, int[] parameters,String[] envs) {
        WasmProtobuf.wasmAction.Builder action = WasmProtobuf.wasmAction.newBuilder();
        WasmProtobuf.wasmCall.Builder builder = WasmProtobuf.wasmCall.newBuilder();
        builder.setContract(name);
        builder.setMethod(method);
        if (parameters!= null && parameters.length > 0) {
            for(int i = 0; i < parameters.length; i++) {
                builder.addParameters(parameters[i]);
            }
        }
        if (envs != null && envs.length > 0) {
            for(int i = 0; i < envs.length; i++) {
                builder.addEnv(envs[i]);
            }
        }

        action.setCall(builder);
        action.setTy(WasmEnum.WasmCall.getTy());
        return action.build();
    }
}
