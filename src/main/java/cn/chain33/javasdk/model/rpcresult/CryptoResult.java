package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class CryptoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 签名类型名称
     */
    private String name;

    /**
     * 签名类型ID
     */
    private Integer typeID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "CryptoResult [name=" + name + ", typeID=" + typeID + "]";
    }

}
