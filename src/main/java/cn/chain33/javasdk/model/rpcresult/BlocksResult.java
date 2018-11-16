package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.List;

public class BlocksResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BlockResult block;
	
	private List<Receipt> recipts;

	public BlockResult getBlock() {
		return block;
	}

	public void setBlock(BlockResult block) {
		this.block = block;
	}

	
	public List<Receipt> getRecipts() {
		return recipts;
	}

	public void setRecipts(List<Receipt> recipts) {
		this.recipts = recipts;
	}

	@Override
	public String toString() {
		return "BlocksResult [block=" + block + ", recipts=" + recipts + "]";
	}
	
}
