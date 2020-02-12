package cn.chain33.javasdk.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.model.AccountInfo;
import cn.chain33.javasdk.model.TransferBalanceRequest;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.TransactionUtil;

public class AccountTest {
	
    String ip = "172.16.103.14";
    RpcClient client = new RpcClient(ip, 8801);
    
    Account account = new Account();

    /**
     * 
     * @description 创建账户 (私钥，公钥，地址)
     *
     */
    @Test
    public void createAccountLocal() {
    	AccountInfo accountInfo = account.newAccountLocal();
        System.out.println("privateKey is:" + accountInfo.getPrivateKey());
        System.out.println("publicKey is:" + accountInfo.getPublicKey());
        System.out.println("Address is:" + accountInfo.getAddress());
    }
    
    
    /**
     * @description 验证地址的合法性
     */
    @Test
    public void validateAddress() {
        String address = "1G1L2M1w1c1gpV6SP8tk8gBPGsJe2RfTks";
        boolean validAddressResult = TransactionUtil.validAddress(address);
        System.out.printf("validate result is:%s", validAddressResult);
    }
    
    
    /**
     * @throws InterruptedException 
     * @description 本地构造主链主积分转账交易
     */
    @Test
    public void createCoinTransferTxMain() throws InterruptedException {
    	
    	TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest();
    	
    	// 转账说明
    	transferBalanceRequest.setNote("转账说明");
        // 转主积分的情况下，默认填""
    	transferBalanceRequest.setCoinToken("");
        // 转账数量 ， 以下代表转1个积分
    	transferBalanceRequest.setAmount(1 * 100000000L);
        // 转到的地址
    	transferBalanceRequest.setTo("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        // 签名私私钥,对应的测试地址是：1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7
    	transferBalanceRequest.setFromPrivateKey("55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4");
        // 执行器名称，主链主积分固定为coins
        transferBalanceRequest.setExecer("coins");
        // 签名类型 (支持SM2, SECP256K1, ED25519)
        transferBalanceRequest.setSignType(SignType.SECP256K1);
        // 构造好，并本地签好名的交易
        String createTransferTx = TransactionUtil.transferBalanceMain(transferBalanceRequest);
        // 交易发往区块链
        String txHash = client.submitTransaction(createTransferTx);
        System.out.println(txHash);
        
        List<String> list = new ArrayList<>();
        list.add("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        list.add("1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7");
        
        // 一般1秒一个区块
        QueryTransactionResult queryTransaction1;
        for (int i = 0; i < 10; i++) {
			queryTransaction1 = client.queryTransaction(txHash);
			if (null == queryTransaction1) {
				Thread.sleep(1000);
			} else {
				break;
			}
		}
        
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getCoinsBalance(list, "coins");
        if (queryBtyBalance != null) {
            for (AccountAccResult accountAccResult : queryBtyBalance) {
                System.out.println(accountAccResult);
            }
    }
    }
}
