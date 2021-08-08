package cn.chain33.javasdk.model.enums;

public enum EvmEnum {
    // EvmCreateAction 创建或调用合约
    EvmExecAction(1),
    // EvmDestroyAction 销毁合约
    EvmUpdateAction(2),
    // EvmDestroyAction 销毁合约
    EvmDestroyAction(3),
    // EvmFreezeAction 冻结合约
    EvmFreezeAction(4),
    // EvmReleaseAction 解冻合约
    EvmReleaseAction(5);

    private int ty;

    private EvmEnum(int ty) {
        this.ty = ty;
    }

    public int getTy() {
        return ty;
    }
}
