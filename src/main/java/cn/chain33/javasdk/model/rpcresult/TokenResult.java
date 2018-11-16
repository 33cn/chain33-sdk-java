package cn.chain33.javasdk.model.rpcresult;

public class TokenResult {
	
	private String name;
	
	private String symbol;
	
	private String introduction;
	
	private String ownerAddr;
	
	private Long total;
	
	private Long price;
	
	private String owner;
	
	private String creator;
	
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getOwnerAddr() {
		return ownerAddr;
	}

	public void setOwnerAddr(String ownerAddr) {
		this.ownerAddr = ownerAddr;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TokenResult [name=" + name + ", symbol=" + symbol + ", introduction=" + introduction + ", ownerAddr="
				+ ownerAddr + ", total=" + total + ", price=" + price + ", owner=" + owner + ", creator=" + creator
				+ ", status=" + status + "]";
	}
	
	
}
