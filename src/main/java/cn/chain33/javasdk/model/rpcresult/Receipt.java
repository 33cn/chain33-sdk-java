package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class Receipt implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer ty;
	
	private String tyname;

	private ReceiptLogResult[] logs;

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

	public ReceiptLogResult[] getLogs() {
		return logs;
	}

	public void setLogs(ReceiptLogResult[] logs) {
		this.logs = logs;
	}
}
