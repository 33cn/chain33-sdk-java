package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class PeerResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String addr;

    private Integer port;

    private String name;

    private Integer mempoolSize;

    private Boolean self;

    private BlockResult header;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMempoolSize() {
        return mempoolSize;
    }

    public void setMempoolSize(Integer mempoolSize) {
        this.mempoolSize = mempoolSize;
    }

    public Boolean getSelf() {
        return self;
    }

    public void setSelf(Boolean self) {
        this.self = self;
    }

    public BlockResult getHeader() {
        return header;
    }

    public void setHeader(BlockResult header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "PeerResult [addr=" + addr + ", port=" + port + ", name=" + name + ", mempoolSize=" + mempoolSize
                + ", self=" + self + ", header=" + header + "]";
    }

}
