package cn.chain33.javasdk.model.rpcresult;

/**
 * 
 * @author lgang
 * @date 创建时间：2018年8月16日 下午5:31:18 
 *
 */
public class TokenBalanceResult {
	
	//token symbol
	private String symbol;
	
	private AccountAccResult account;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public AccountAccResult getAccount() {
		return account;
	}

	public void setAccount(AccountAccResult account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "TokenBalanceResult [symbol=" + symbol + ", account=" + account + "]";
	}
	
	
	
}
