package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class PayLoadResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String rawlog;

	public String getRawlog() {
		return rawlog;
	}

	public void setRawlog(String rawlog) {
		this.rawlog = rawlog;
	}

	@Override
	public String toString() {
		return "PayLoadResult [rawlog=" + rawlog + "]";
	}
	
	
	
	
}
