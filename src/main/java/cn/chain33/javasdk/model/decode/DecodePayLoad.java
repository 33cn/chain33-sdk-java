package cn.chain33.javasdk.model.decode;

public class DecodePayLoad {
    
    private DecodeTransfer transfer;
    
    private Integer ty;

    public DecodeTransfer getTransfer() {
        return transfer;
    }

    public void setTransfer(DecodeTransfer transfer) {
        this.transfer = transfer;
    }

    public Integer getTy() {
        return ty;
    }

    public void setTy(Integer ty) {
        this.ty = ty;
    }

    @Override
    public String toString() {
        return "DecodePayLoad [transfer=" + transfer + ", ty=" + ty + "]";
    }
    
    
}
