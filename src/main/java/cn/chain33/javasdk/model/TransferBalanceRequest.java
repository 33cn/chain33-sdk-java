package cn.chain33.javasdk.model;

import cn.chain33.javasdk.model.enums.SignType;

public class TransferBalanceRequest {

    /**
     * 转账说明
     */
    private String note;

    /**
     * 积分名称
     */
    private String coinToken;

    /**
     * 转账金额
     */
    private Long amount;

    /**
     * 转向的地址
     */
    private String to;

    /**
     * 转账地址私钥
     */
    private String fromPrivateKey;

    /**
     * 签名类型
     */
    private SignType signType;

    /**
     * 手续费
     */
    private long fee;

    /**
     * 执行器名称
     */
    private String execer;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCoinToken() {
        return coinToken;
    }

    public void setCoinToken(String coinToken) {
        this.coinToken = coinToken;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromPrivateKey() {
        return fromPrivateKey;
    }

    public void setFromPrivateKey(String fromPrivateKey) {
        this.fromPrivateKey = fromPrivateKey;
    }

    public String getExecer() {
        return execer;
    }

    public void setExecer(String execer) {
        this.execer = execer;
    }

    public SignType getSignType() {
        return signType;
    }

    public void setSignType(SignType signType) {
        this.signType = signType;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

}
