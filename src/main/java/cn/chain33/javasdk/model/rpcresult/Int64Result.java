package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class Int64Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private long data;

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Int64Result{" + "data=" + data + '}';
    }
}
