package cn.chain33.javasdk.model.decode;

public class DecodeRawTransaction {

    private String rawPayload;

    private Long fee;

    private DecodeSignature signature;

    private String feefmt;

    private Long nonce;

    private Integer groupCount;

    private DecodePayLoad payload;

    private Long expire;

    private String header;

    private String from;

    private String to;

    private String execer;

    private String hash;

    private String next;

    public String getRawPayload() {
        return rawPayload;
    }

    public void setRawPayload(String rawPayload) {
        this.rawPayload = rawPayload;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public DecodeSignature getSignature() {
        return signature;
    }

    public void setSignature(DecodeSignature signature) {
        this.signature = signature;
    }

    public String getFeefmt() {
        return feefmt;
    }

    public void setFeefmt(String feefmt) {
        this.feefmt = feefmt;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public DecodePayLoad getPayload() {
        return payload;
    }

    public void setPayload(DecodePayLoad payload) {
        this.payload = payload;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getExecer() {
        return execer;
    }

    public void setExecer(String execer) {
        this.execer = execer;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
