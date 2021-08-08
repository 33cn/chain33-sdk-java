package cn.chain33.javasdk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bitcoinj.wallet.UnreadableWalletException;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.SeedUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

public class AccountTest {

    String ip = "172.22.19.101";
    RpcClient client = new RpcClient(ip, 8801);

	Account account = new Account();

	/**
	 * 
	 * @description 直接创建账户 (私钥，公钥，地址)
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
	 * 根据seed创建私钥，公钥，地址
	 * @throws UnreadableWalletException 
	 */
	@Test
	public void createAccountBySeed() throws UnreadableWalletException {
		// 生成助记词
		String mnemonic = SeedUtil.generateMnemonic();
		System.out.println("助记词：" + mnemonic);
		
		// 根据助记词生成私钥，公钥，地址
		AccountInfo info = SeedUtil.createAccountBy33PATH(mnemonic, 0);
		System.out.println("privateKey is:" + info.getPrivateKey());
		System.out.println("publicKey is:" + info.getPublicKey());
		System.out.println("Address is:" + info.getAddress());
		
		
		info = SeedUtil.createAccountBy33PATH(mnemonic, 1);
		System.out.println("privateKey is:" + info.getPrivateKey());
		System.out.println("publicKey is:" + info.getPublicKey());
		System.out.println("Address is:" + info.getAddress());
		
		info = SeedUtil.createAccountBy33PATH(mnemonic, 10000000);
		System.out.println("privateKey is:" + info.getPrivateKey());
		System.out.println("publicKey is:" + info.getPublicKey());
		System.out.println("Address is:" + info.getAddress());
		
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
	 * @throws IOException 
	 * @description 本地构造主链主积分转账交易
	 */
	@Test
	public void createCoinTransferTxMain() throws InterruptedException, IOException {

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

	/**
	 * 注册账户，并查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void registeAndQueryAccount() throws Exception {
		
		String accountId = "testAccount2";

		AccountInfo accountInfo = account.newAccountLocal();
		String createTxWithoutSign = client.registeAccount("accountmanager", "Register", accountId);

		byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
		TransactionAllProtobuf.Transaction parseFrom = null;
		try {
			parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, accountInfo.getPrivateKey());
		String hexString = HexUtil.toHexString(signProbuf.toByteArray());

		String submitTransaction = client.submitTransaction(hexString);
		System.out.println(submitTransaction);

		// 一般1秒一个区块
		QueryTransactionResult queryTransaction1;
		for (int i = 0; i < 5; i++) {
			queryTransaction1 = client.queryTransaction(submitTransaction);
			if (null == queryTransaction1) {
				Thread.sleep(1000);
			} else {
				break;
			}
		}

		// 根据accountId查询账户信息
		JSONObject resultJson = client.queryAccountById(accountId);
		
    	System.out.println("账户ID:" + resultJson.getString("accountID"));
    	System.out.println("过期时间:" + resultJson.getString("expireTime"));
    	System.out.println("创建时间:" + resultJson.getString("createTime"));
    	// 账户状态 0 正常， 1表示冻结, 2表示锁定 3,过期注销
    	System.out.println("账户状态:" + resultJson.getString("status"));
    	//等级权限 0普通,后面根据业务需要可以自定义，有管理员授予不同的权限
    	System.out.println("等级权限:" + resultJson.getString("level"));
    	// 账户地址
    	System.out.println("地址:" + resultJson.getString("addr"));
    	
    	// 根据状态查账户信息
    	String status = "0";
    	resultJson = client.queryAccountByStatus(status);
    	System.out.println(resultJson);
	}
	
    /**
     * 创建管理员，用于系统权限授权操作
     * @throws Exception 
     * @description 创建自定义积分的黑名单
     *
     */
    @Test
    public void createManager() throws Exception {

    	// 管理合约名称
    	String execerName = "manage";
    	// 管理合约:配置管理员key
    	String key = "accountmanager-managerAddr";
    	// 管理合约:配置管理员VALUE, 对应的私钥：3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8 
    	String value = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
    	// 管理合约:配置操作符
    	String op = "add";
    	// 当前链管理员私钥（superManager）
    	String privateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
    	// 构造并签名交易,使用链的管理员（superManager）进行签名， 
    	String txEncode = TransactionUtil.createManage(key, value, op, privateKey, execerName);
    	// 发送交易
    	String hash = client.submitTransaction(txEncode);
    	System.out.print(hash);
    }
	
	/**
	 * 对账户进行授权
	 * @throws Exception 
	 * 
	 */
    @Test
	public void authAndQueryAccount() throws Exception {
		String[] accountIds = new String[]{"testAccount2"};
		// 1为冻结，2为解冻，3增加有效期,4为授权
		String op = "4";
		//0普通,后面根据业务需要可以自定义，有管理员授予不同的权限
		String level = "2";
		manageAccount(accountIds, op, level);
		
	}
    
	/**
	 * 冻结账户
	 * @throws Exception 
	 * 
	 */
    @Test
	public void frozenAndQueryAccount() throws Exception {
		String[] accountIds = new String[]{"testAccount2"};
		// 1为冻结，2为解冻，3增加有效期,4为授权
		String op = "1";
		// level填空
		manageAccount(accountIds, op, "");

	}
    
	/**
	 * 解冻账户
	 * @throws Exception 
	 * 
	 */
    @Test
	public void unfrozenAndQueryAccount() throws Exception {
		String[] accountIds = new String[]{"testAccount2"};
		// 1为冻结，2为解冻，3增加有效期,4为授权
		String op = "2";
		// level填空
		manageAccount(accountIds, op, "");

	}
    
    
    /**
     * 账户操作
     * 
     * @param accountIds
     * @param op
     * @param level
     * @throws Exception
     */
    private void manageAccount(String[] accountIds, String op, String level) throws Exception {
		String createTxWithoutSign = client.authAccount("accountmanager", "Supervise", accountIds, op, level);

		byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
		TransactionAllProtobuf.Transaction parseFrom = null;
		try {
			parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8");
		String hexString = HexUtil.toHexString(signProbuf.toByteArray());

		String submitTransaction = client.submitTransaction(hexString);
		System.out.println(submitTransaction);

		// 一般1秒一个区块
		QueryTransactionResult queryTransaction1;
		for (int i = 0; i < 5; i++) {
			queryTransaction1 = client.queryTransaction(submitTransaction);
			if (null == queryTransaction1) {
				Thread.sleep(1000);
			} else {
				break;
			}
		}

		// 根据accountId查询账户信息
		JSONObject resultJson = client.queryAccountById(accountIds[0]);
		
    	System.out.println("账户ID:" + resultJson.getString("accountID"));
    	System.out.println("过期时间:" + resultJson.getString("expireTime"));
    	System.out.println("创建时间:" + resultJson.getString("createTime"));
    	// 账户状态 0 正常， 1表示冻结, 2表示锁定 3,过期注销
    	System.out.println("账户状态:" + resultJson.getString("status"));
    	//等级权限 0普通,后面根据业务需要可以自定义，有管理员授予不同的权限
    	System.out.println("等级权限:" + resultJson.getString("level"));
    	// 账户地址
    	System.out.println("地址:" + resultJson.getString("addr"));
    }

    @Test
	public void testAccountStore() throws Exception {
    	AccountInfo accountInfo = account.newAccountLocal("testa", "12345678", "testa");
		System.out.println("name is:" + accountInfo.getName());
		System.out.println("privateKey is:" + accountInfo.getPrivateKey());
		System.out.println("publicKey is:" + accountInfo.getPublicKey());
		System.out.println("Address is:" + accountInfo.getAddress());

		AccountInfo accountInfo1 = account.loadAccountLocal("testa", "12345678", "testa");
		Assert.assertEquals(accountInfo.getName(), accountInfo1.getName());
		Assert.assertEquals(accountInfo.getPrivateKey(), accountInfo1.getPrivateKey());
		Assert.assertEquals(accountInfo.getPublicKey(), accountInfo1.getPublicKey());
		Assert.assertEquals(accountInfo.getAddress(), accountInfo1.getAddress());
	}
}
