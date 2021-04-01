package cn.chain33.javasdk.model.enums;

public enum WasmEnum {

    WasmCreate(1),WasmUpdate(2),WasmCall(3);

    private int ty;

    private WasmEnum(int ty) {
        this.ty = ty;
    }

    public int getTy() {
        return ty;
    }
}
