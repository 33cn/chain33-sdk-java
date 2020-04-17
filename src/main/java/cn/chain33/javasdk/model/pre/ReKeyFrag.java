package cn.chain33.javasdk.model.pre;

public class ReKeyFrag {
    private String ReKeyR;

    private String ReKeyU;

    private String Random;

    private String PrecurPub;

    public void setRandom(String random) {
        Random = random;
    }

    public void setPrecurPub(String precurPub) {
        PrecurPub = precurPub;
    }

    public void setReKeyR(String reKeyR) {
        ReKeyR = reKeyR;
    }

    public void setReKeyU(String reKeyU) {
        ReKeyU = reKeyU;
    }

    public String getPrecurPub() {
        return PrecurPub;
    }

    public String getRandom() {
        return Random;
    }

    public String getReKeyR() {
        return ReKeyR;
    }

    public String getReKeyU() {
        return ReKeyU;
    }
}
