package cn.chain33.javasdk.model;

import java.io.Serializable;

/**
 * @author lyz
 * 
 * @mail lyz@disanbo.com
 * 
 * @create 2018/11/14 15:45
 * 
 * @description
 */
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String error;
    private Object result;

    public boolean isValid() {
        if (this.error == null || "".equals(this.error) || "null".equals(this.error)) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
