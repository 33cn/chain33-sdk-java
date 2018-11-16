package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.Date;

public class QueryTransactionResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long amount;
	
	private TransactionResult tx;
	
	private Date blocktime;
	
	private Integer index;
	
	private String actionname;
	
	private String fromaddr;
	
	private Long height;
	
	private Receipt receipt;
	
	public TransactionResult getTx() {
		return tx;
	}

	public void setTx(TransactionResult tx) {
		this.tx = tx;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}


	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getBlocktime() {
		return blocktime;
	}

	public void setBlocktime(Date blocktime) {
		this.blocktime = blocktime;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getFromaddr() {
		return fromaddr;
	}

	public void setFromaddr(String fromaddr) {
		this.fromaddr = fromaddr;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "QueryTransactionResult [amount=" + amount + ", tx=" + tx + ", blocktime=" + blocktime + ", index="
				+ index + ", actionname=" + actionname + ", fromaddr=" + fromaddr + ", height=" + height + ", receipt="
				+ receipt + "]";
	}
	
}
