package cn.chain33.javasdk.model.decode;

public class DecodeSignature {
    
    private Integer ty;
    
    private String signature;
    
    private String pubkey;

    public Integer getTy() {
        return ty;
    }

    public void setTy(Integer ty) {
        this.ty = ty;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    @Override
    public String toString() {
        return "DecodeSignature [ty=" + ty + ", signature=" + signature + ", pubkey=" + pubkey + "]";
    }
    
    
}
