package cn.chain33.javasdk.model.enums;

/**
 * @authoer lhl
 * @date 2022/6/2 上午11:20
 */
public enum AddressType {
    BTC_ADDRESS(0),ETH_ADDRESS(2);

    private Integer type;

    private AddressType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
