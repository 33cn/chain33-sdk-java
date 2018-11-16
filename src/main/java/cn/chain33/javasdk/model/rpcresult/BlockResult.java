package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BlockResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer version;
	
	private String parentHash;
	
	private String txHash;
	
	private String stateHash;
	
	private Long height;
	
	private Date blockTime;
	
	private Integer txcount;
	
	private String hash;
	
	private List<TransactionResult> txs;
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getParentHash() {
		return parentHash;
	}

	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}

	public String getStateHash() {
		return stateHash;
	}

	public void setStateHash(String stateHash) {
		this.stateHash = stateHash;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Date getBlockTime() {
		return blockTime;
	}

	public void setBlockTime(Date blockTime) {
		this.blockTime = blockTime;
	}

	public Integer getTxcount() {
		return txcount;
	}

	public void setTxcount(Integer txcount) {
		this.txcount = txcount;
	}

	
	
	public List<TransactionResult> getTxs() {
		return txs;
	}

	public void setTxs(List<TransactionResult> txs) {
		this.txs = txs;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public String toString() {
		return "BlockResult [version=" + version + ", parentHash=" + parentHash + ", txHash=" + txHash + ", stateHash="
				+ stateHash + ", height=" + height + ", blockTime=" + blockTime + ", txcount=" + txcount + ", hash="
				+ hash + ", txs=" + txs + "]";
	}

}
