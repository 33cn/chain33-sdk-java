package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.Map;

public class PushSubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private String URL;

    private String encode;

    private long lastSequence;

    private long lastHeight;

    private String lastBlockHash;

    private long type;

    private Map<String, Boolean> contract;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public long getLastSequence() {
        return lastSequence;
    }

    public void setLastSequence(long lastSequence) {
        this.lastSequence = lastSequence;
    }

    public long getLastHeight() {
        return lastHeight;
    }

    public void setLastHeight(long lastHeight) {
        this.lastHeight = lastHeight;
    }

    public String getLastBlockHash() {
        return lastBlockHash;
    }

    public void setLastBlockHash(String lastBlockHash) {
        this.lastBlockHash = lastBlockHash;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public Map<String, Boolean> getContract() {
        return contract;
    }

    public void setContract(Map<String, Boolean> contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "PushSubscribeReq{" + "name='" + name + '\'' + ", URL='" + URL + '\'' + ", encode='" + encode + '\''
                + ", lastSequence=" + lastSequence + ", lastHeight=" + lastHeight + ", lastBlockHash='" + lastBlockHash
                + '\'' + ", type=" + type + ", contract=" + contract + '}';
    }
}
