package cn.chain33.javasdk.model.rpcresult;

public class BooleanResult {
	
	private boolean isOK;
	
	private String msg;

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "LockResult [isOK=" + isOK + ", msg=" + msg + "]";
	}
	
}
