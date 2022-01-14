package cn.chain33.javasdk.model.pre;

public class EncryptKey {
    private byte[] shareKey;

    private String PubProofR;

    private String PubProofU;

    public EncryptKey(byte[] shareKey, String pubProofR, String pubProofU) {
        this.PubProofR = pubProofR;
        this.PubProofU = pubProofU;
        this.shareKey = shareKey;
    }

    public byte[] getShareKey() {
        return shareKey;
    }

    public void setShareKey(byte[] shareKey) {
        this.shareKey = shareKey;
    }

    public String getPubProofR() {
        return PubProofR;
    }

    public void setPubProofR(String pubProofR) {
        PubProofR = pubProofR;
    }

    public String getPubProofU() {
        return PubProofU;
    }

    public void setPubProofU(String pubProofU) {
        PubProofU = pubProofU;
    }
}
