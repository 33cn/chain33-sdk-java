package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

/**
 * @authoer lhl
 * @date 2022/6/9 上午9:46
 */
public class ReceiptLogResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ty;

    private String tyname;

    private Object log;

    private String  rawlog;

    public Integer getTy() {
        return ty;
    }

    public void setTy(Integer ty) {
        this.ty = ty;
    }

    public String getTyname() {
        return tyname;
    }

    public void setTyname(String tyname) {
        this.tyname = tyname;
    }

    public Object getLog() {
        return log;
    }

    public void setLog(Object log) {
        this.log = log;
    }

    public String getRawlog() {
        return rawlog;
    }

    public void setRawlog(String rawlog) {
        this.rawlog = rawlog;
    }
}
