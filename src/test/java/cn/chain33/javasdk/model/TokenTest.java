package cn.chain33.javasdk.model;

import java.util.ArrayList;
import java.util.List;

import cn.chain33.javasdk.client.RpcClient;
import org.junit.Test;

import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.TokenResult;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 *	包含积分名称黑名单，积分的预发行，积分发行，积分增发，积分查询等接口
 *	积分发行步骤：
 *	1. 通过当前链的超级管理员来配置自定义积分的黑名单(全局配置：通常情况下只需要执行一次)。
 *	2. 通过当前链的超级管理员来配置自定义积分的审核者(全局配置：通常情况下只需要执行一次)。
 *	3. 通过积分审核者来预发行自定义积分。
 *	4. 通过积分审核者来正式发行自定义积分。
 * @author fkeit
 */
public class TokenTest {

	
    String ip = "172.16.103.14";
    RpcClient client = new RpcClient(ip, 8801);
    
    /**
     * 
     * @throws Exception 
     * @description 创建自定义积分的黑名单
     *
     */
    @Test
    public void createBlackList() throws Exception {

    	// 管理合约名称
    	String execerName = "manage";
    	// 管理合约:配置黑名单KEY
    	String key = "token-blacklist";
    	// 管理合约:配置黑名单VALUE
    	String value = "BTC";
    	// 管理合约:配置操作符
    	String op = "add";
    	// 当前链管理员私钥（superManager）
    	String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
    	// 构造并签名交易,使用链的管理员（superManager）进行签名， 
    	// 55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4 对应的测试地址是：1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7
    	String txEncode = TransactionUtil.createManage(key, value, op, privateKey, execerName);
    	// 发送交易
    	String hash = client.submitTransaction(txEncode);
    	System.out.print(hash);
    }
    
    /**
     * 
     * @throws Exception 
     * @description 创建自定义积分的token-finisher
     *
     */
    @Test
    public void createTokenFinisher() throws Exception {

    	// 管理合约名称
    	String execerName = "manage";
    	// 管理合约:配置KEY
    	String key = "token-finisher";
    	// 管理合约:配置VALUE，用于审核token的创建
    	String value = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
    	// 管理合约:配置操作符
    	String op = "add";
    	// 当前链管理员私钥（superManager）
    	String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
    	// 构造并签名交易,使用链的管理员（superManager）进行签名， 
    	// 55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4 对应的测试地址是：1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7
    	String txEncode = TransactionUtil.createManage(key, value, op, privateKey, execerName);
    	// 发送交易
    	String hash = client.submitTransaction(txEncode);
    	System.out.print(hash);
    	
    }
    
	/**
	 * 本地预创建token并提交
	 */
	@Test
	public void preCreateTokenLocal() {
	   //token总额
	   long total = 19900000000000000L;
	   //token的注释名称
	   String name = "DEVELOP COINS";
	   //token的名称，只支持大写字母，同一条链不允许相同symbol存在
	   String symbol = "COINSDEVX";
	   //token介绍
	   String introduction = "开发者币";
	   //发行token愿意承担的费用，填0就行
	   Long price = 0L;
	   //0 为普通token， 1 可增发和燃烧
	   Integer category = 0;
	   //合约名称
	   String execer = "token";
	   //token的拥有者地址
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   //链超级管理员私钥
	   String managerPrivateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
	   String precreateTx = TransactionUtil.createPrecreateTokenTx(execer, name, symbol, introduction, total, price,
	           owner, category, managerPrivateKey);
	   String submitTransaction = client.submitTransaction(precreateTx);
	   System.out.println(submitTransaction);
	}
	
	/**
	 * 本地创建token完成交易并提交
	 */
	@Test
	public void createTokenFinishLocal() {
	   String symbol = "COINSDEVX";
	   String execer = "token";
	   String managerPrivateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   String hexData = TransactionUtil.createTokenFinishTx(symbol, execer, owner, managerPrivateKey);
	   String submitTransaction = client.submitTransaction(hexData);
	   System.out.println(submitTransaction);
	}
	
    /**
     * @description 本地构造平行链主代币转账交易
     */
    @Test
    public void createTokenTransfer() {
    	// 转账说明
        String note = "转账说明";
        // token名
        String coinToken = "COINSDEVX";
        Long amount = 10000 * 100000000L;// 1 = real amount
        // 转到的地址
        String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // 签名私私钥，里面需要有主链币，用于缴纳手续费
        String fromAddressPriveteKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
        // 执行器名称
        String execer = "token";
        String createTransferTx = TransactionUtil.createTokenTransferTx(fromAddressPriveteKey, to, execer, amount, coinToken, note);
        String txHash = client.submitTransaction(createTransferTx);
        System.out.println(txHash);
    }
    
    /**
     * 
     * @description 查询已经创建的token
     *
     */
    @Test
    public void queryCreateTokens() {
        String execer = "token";
        //状态 0预创建的 1创建成功的
        Integer status = 1;
        List<TokenResult> queryCreateTokens;
        queryCreateTokens = client.queryCreateTokens(status,execer);
        for (TokenResult tokenResult : queryCreateTokens) {
            System.out.println(tokenResult);
        }
    }
    
	
    /**
     * 
     * @description 查询token余额
     *
     */
    @Test
    public void getTokenBalace() {
        List<String> addressList = new ArrayList<>();
        addressList.add("1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7");
        addressList.add("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getTokenBalance(addressList, "token", "COINSDEVX");
        for (AccountAccResult accountAccResult : queryBtyBalance) {
            System.out.println(accountAccResult);
        }
    }

}
