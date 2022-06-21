package cn.chain33.javasdk.model.enums;

/**
 * @authoer lhl
 * @date 2022/6/17 下午5:10
 */
public enum EncodeType {
    JSON("json"),
    PROTOBUFF("protobuf");

    private String type;

    private EncodeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
