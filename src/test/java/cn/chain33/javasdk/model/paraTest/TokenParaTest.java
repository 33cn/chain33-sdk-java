package cn.chain33.javasdk.model.paraTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.chain33.javasdk.client.RpcClient;
import org.junit.Test;

import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
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
public class TokenParaTest {

	
	// 平行链IP
	String ip = "平行链IP";
	// 平行链服务端口
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);
    
    // 平行链名称，固定格式user.p.xxxx.  其中xxxx可替换，支持大小写英文字母
	String paraName = "user.p.evm.";
	
	// 当前链超级管理员的私钥（superManager），  对应地址： 1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs
	// 在BTY公链平行链的场合下，地址下需要带有相应的coins，用于支付交易手续费
	// 联盟链默认不收取手续费，所以联盟链+平行链的场合下，地址下可以没有coins
	String superManagerPk = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
    
    /**
     * 
     * @throws Exception 
     * @description 创建自定义积分的黑名单
     *
     */
    @Test
    public void createBlackList() throws Exception {

    	// 管理合约名称
    	String execerName = paraName + "manage";
    	// 管理合约:配置黑名单KEY
    	String key = "token-blacklist";
    	// 管理合约:配置黑名单VALUE
    	String value = "BTC";
    	// 管理合约:配置操作符
    	String op = "add";
    	// 构造并签名交易,使用链的管理员（superManager）进行签名， 
    	// 55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4 对应的测试地址是：1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7
    	String txEncode = TransactionUtil.createManage(key, value, op, superManagerPk, execerName);
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
    	String execerName = paraName + "manage";;
    	// 管理合约:配置KEY
    	String key = "token-finisher";
    	// 管理合约:配置VALUE，用于审核token的创建，可以授权超级管理员地址，也可以授权其它地址
    	String value = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
    	// 管理合约:配置操作符
    	String op = "add";
    	String txEncode = TransactionUtil.createManage(key, value, op, superManagerPk, execerName);
    	// 发送交易
    	String hash = client.submitTransaction(txEncode);
    	System.out.print(hash);
    	
    }
    
	/**
	 * 本地预创建token并提交
	 * @throws IOException 
	 */
	@Test
	public void preCreateTokenLocal() throws IOException {
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
	   String execer = paraName + "token";
	   //token的拥有者地址
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   //token-finisher地址对应的私钥（createTokenFinisher函数中配置的：value）
	   String managerPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
	   String precreateTx = TransactionUtil.createPrecreateTokenTx(execer, name, symbol, introduction, total, price,
	           owner, category, managerPrivateKey);
	   String submitTransaction = client.submitTransaction(precreateTx);
	   System.out.println(submitTransaction);
	}
	
	/**
	 * 本地创建token完成交易并提交
	 * @throws IOException 
	 */
	@Test
	public void createTokenFinishLocal() throws IOException {
	   String symbol = "COINSDEVX";
	   String execer = paraName + "token";
	   //token-finisher地址对应的私钥（createTokenFinisher函数中配置的：value）
	   String managerPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
	   // token的拥有者地址
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   String hexData = TransactionUtil.createTokenFinishTx(symbol, execer, owner, managerPrivateKey);
	   String submitTransaction = client.submitTransaction(hexData);
	   System.out.println(submitTransaction);
	}
	
    /**
     * 平行链上的转账交易一般通过代扣的方式进行
     * @throws InterruptedException 
     * @throws IOException 
     * @description 通过代扣的方式构造token的转账交易
     */
    @Test
    public void createTokenTransfer() throws InterruptedException, IOException {
    	// 转账说明
        String note = "转账说明";
        // token名
        String coinToken = "COINSDEVX";
        Long amount = 10000 * 100000000L;// 1 = real amount
        // 转到的地址
        String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // 签名私钥，里面需要有主链币，用于缴纳手续费
        String fromAddressPriveteKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
        // 执行器名称
        String execer = paraName + "token";
        String txEncode = TransactionUtil.createTokenTransferTx(fromAddressPriveteKey, to, execer, amount, coinToken, note);
        
        String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
        
	    // 解析交易
	    List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
	    // 代扣交易签名的私钥 （所有交易的手续费都从这个地址扣除）
	    String withHoldPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
	    
	    String contranctAddress = client.convertExectoAddr(execer);
	    String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, fromAddressPriveteKey, withHoldPrivateKey);
	    String submitTransaction = client.submitTransaction(hexString);
	    System.out.println("submitTransaction:" + submitTransaction);

		Thread.sleep(5000);
		for (int tick = 0; tick < 5; tick++){
			QueryTransactionResult result = client.queryTransaction(submitTransaction);
			if(result == null) {
				Thread.sleep(5000);
				continue;
			}

			System.out.println("next:" + result.getTx().getNext());
			QueryTransactionResult nextResult = client.queryTransaction(result.getTx().getNext());
			System.out.println("ty:" + nextResult.getReceipt().getTyname());
			break;
		}
    }
    
    /**
     * 
     * @throws IOException 
     * @description 查询已经创建的token
     *
     */
    @Test
    public void queryCreateTokens() throws IOException {
        String execer = paraName + "token";
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
     * @throws IOException 
     * @description 查询token余额
     *
     */
    @Test
    public void getTokenBalace() throws IOException {
        // 执行器名称
        String execer = paraName + "token";
        
        List<String> addressList = new ArrayList<>();
        addressList.add("1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7");
        addressList.add("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getTokenBalance(addressList, execer, "COINSDEVX");
        for (AccountAccResult accountAccResult : queryBtyBalance) {
            System.out.println(accountAccResult);
        }
    }

}
