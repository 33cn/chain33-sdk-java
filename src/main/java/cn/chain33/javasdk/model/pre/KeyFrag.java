package cn.chain33.javasdk.model.pre;

public class KeyFrag {
    private String random;

    private String value;

    private String precurPub;

    public KeyFrag(String random, String value, String precurPub) {
        this.random = random;
        this.value = value;
        this.precurPub = precurPub;
    }

    public String getPrecurPub() {
        return precurPub;
    }

    public void setPrecurPub(String precurPub) {
        this.precurPub = precurPub;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
