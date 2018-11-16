package cn.chain33.javasdk.model.rpcresult;

public class TxResult {
	
	private String hash;
	
	private Long height;
	
	private Integer index;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "TxResult [hash=" + hash + ", height=" + height + ", index=" + index + "]";
	}
	
	
	
}
