package cn.chain33.javasdk.model.rpcresult;

public class AccountResult {
	
	private String label;
	
	private AccountAccResult acc;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public AccountAccResult getAcc() {
		return acc;
	}

	public void setAcc(AccountAccResult acc) {
		this.acc = acc;
	}

	@Override
	public String toString() {
		return "AccountResult [label=" + label + ", acc=" + acc + "]";
	}
	
	
	
}
