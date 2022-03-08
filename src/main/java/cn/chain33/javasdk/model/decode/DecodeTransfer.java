package cn.chain33.javasdk.model.decode;

public class DecodeTransfer {

    private Long amount;

    private String cointoken;

    private String to;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCointoken() {
        return cointoken;
    }

    public void setCointoken(String cointoken) {
        this.cointoken = cointoken;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "DecodeTransfer [amount=" + amount + ", cointoken=" + cointoken + ", to=" + to + "]";
    }

}
