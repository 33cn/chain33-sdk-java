package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.List;

public class BlockOverViewResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private BlockResult head;
	
	private Integer txCount;
	
	private List<String> txHashes;

	public BlockResult getHead() {
		return head;
	}

	public void setHead(BlockResult head) {
		this.head = head;
	}

	public Integer getTxCount() {
		return txCount;
	}

	public void setTxCount(Integer txCount) {
		this.txCount = txCount;
	}

	public List<String> getTxHashes() {
		return txHashes;
	}

	public void setTxHashes(List<String> txHashes) {
		this.txHashes = txHashes;
	}

	@Override
	public String toString() {
		return "BlockOverViewResult [head=" + head + ", txCount=" + txCount + ", txHashes=" + txHashes + "]";
	}
	
	
	
}
