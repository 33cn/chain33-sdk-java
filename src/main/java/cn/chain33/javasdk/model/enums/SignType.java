package cn.chain33.javasdk.model.enums;

public enum SignType {

    SECP256K1(1), ED25519(2), SM2(258);

    private Integer type;

    private SignType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
