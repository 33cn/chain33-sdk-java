package cn.chain33.javasdk.model;

public class Transactions {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private Transaction[] transaction;
	
	public Transaction[] getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction[] transaction) {
		this.transaction = transaction;
	}

}
