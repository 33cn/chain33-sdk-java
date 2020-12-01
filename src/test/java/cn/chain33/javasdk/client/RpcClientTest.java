package cn.chain33.javasdk.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import org.junit.Test;

import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.AccountResult;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.model.rpcresult.TokenBalanceResult;
import cn.chain33.javasdk.model.rpcresult.TxResult;
import cn.chain33.javasdk.model.rpcresult.WalletStatusResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

public class RpcClientTest {

	// 区块链节点IP
	String ip = "区块链节点IP";
	// 平行链服务端口
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);


    String withHoldPrivateKey = "代扣地址私钥，需要有主链代币";
    String withHoldAddress = "代扣地址";

    /**
     * 
     * @description 查询节点是否同步
     * 联盟链：适用
     * 主链：适用
     * 平行链：不适用
     */
    @Test
    public void checkStatus() throws Exception {
        System.out.println("is sync:" + client.isSync());
    }

    /**
     * 
     * @description 查询钱包状态
     *
     */
    @Test
    public void getWalletStatus() throws Exception {
        WalletStatusResult walletStatus;
        walletStatus = client.getWalletStatus();
        System.out.println(walletStatus);
    }

    /**
     * 
     * @description 锁定
     *
     */
    @Test
    public void lock() throws Exception {
        BooleanResult lock = client.lock();
        System.out.println(lock);
    }

    @Test
    public void unlock() throws Exception {
        // 钱包密码，长度8位，数字+字母
        String passwd = "fzm12345";
        boolean walletorticket = false;
        int timeout = 0;
        BooleanResult unlock = client.unlock(passwd, walletorticket, timeout);
        System.out.println(unlock);
    }

    /**
     * 
     * @description 生成seed
     *
     */
    @Test
    public void seedGen() throws Exception {
        String seedGen = client.seedGen(1);
        System.out.println(seedGen);
    }

    /**
     * 
     * @description 保存seed
     *
     */
    @Test
    public void seedSave() throws Exception {
        String seedCn = "常 辉 觉 李 固 参 鲜 村 见 控 罩 戈 狠 亿 圣";
        String passwd = "fzm12345";
        BooleanResult booleanResult = client.seedSave(seedCn, passwd);
        System.out.println(booleanResult);
    }

    /**
     * 
     * @description 使用密码查询seed
     *
     */
    @Test
    public void seedGet() throws Exception {
        String passwd = "fzm12345";
        String seedGet = client.seedGet(passwd);
        System.out.println(seedGet);
    }

    /**
     * 
     * @description 调用节点创建账户地址
     *
     */
    @Test
    public void newAccount() throws Exception {
        AccountResult newAccount = client.newAccount("secondAccount");
        System.out.println(newAccount);
    }

    /**
     * 
     * @description 查询节点钱包 地址列表
     *
     */
    @Test
    public void getAccounts() throws Exception {
        List<AccountResult> accountList;
        accountList = client.getAccountList();
        for (AccountResult accountResult : accountList) {
            System.out.println(accountResult);
        }
    }

    /**
     * 
     * @description 查询主代币余额
     *
     */
    @Test
    public void getCoinsBalace() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("15DAJUBA65K6SHWAWXuJRnTXkT8K1DNXNL");
        list.add("1JGEaFMXi6irAgEFXR6Hgaoe5crqBh6QYs");
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getCoinsBalance(list, "coins");
        for (AccountAccResult accountAccResult : queryBtyBalance) {
            System.out.println(accountAccResult);
        }
    }


    /**
     * 
     * @description 设置地址label
     * 
     */
    @Test
    public void setLabel() throws Exception {
        AccountResult accountResult;
        accountResult = client.setlabel("15DAJUBA65K6SHWAWXuJRnTXkT8K1DNXNL", "first");
        System.out.println(accountResult);

    }

    /**
     * 
     * @description 导入私钥
     *
     */
    @Test
    public void importPrivKey() throws Exception {
        String accountResult;
        accountResult = client.importPrivatekey("privatekey", "labelname");
        System.out.println(accountResult);

    }
    
    
    /**
     * 
     * @description 导出私钥
     *
     */
    @Test
    public void dumpPrivKey() throws Exception {
        String accountResult;
        accountResult = client.dumpPrivkey("节点上已经import的区块链地址");
        System.out.println(accountResult);

    }

    /**
     * @description 查询交易hash详情
     *
     */
    @Test
    public void queryTxDetail() throws Exception {
    	// 交易hash
    	String hash = "0x3fb9b1ebf10d7ab4ac36882f5031fdfb89dc503d146a098a3127df608d795034";
        //String hash = "交易hash";
        QueryTransactionResult queryTransaction1;
        queryTransaction1 = client.queryTransaction(hash);
        System.out.println(queryTransaction1);

        // 如果是上链内容,读取上链内容
        String rawpayload = queryTransaction1.getTx().getRawpayload();
        String content = new String(HexUtil.fromHexString(rawpayload));
        System.out.println(content);

    }
    
    /**
     * 查询平均出块时间
     */
    @Test
    public void getBlockAverageTime() throws Exception {
    	int blockTime =  client.getBlockAverageTime();
    	System.out.println("平均出块时间为： " + blockTime + " 秒");
    }


    /**
     * 
     * @description 查询某地址下得token/合约资产列表
     *
     */
    @Test
    public void queryUserTokens() throws Exception {
        List<TokenBalanceResult> queryAccountBalance;
        queryAccountBalance = client.queryAccountBalance("12qyocayNF7Lv6C9qxxxxxx", "user.p.xxchain.coins");
        for (TokenBalanceResult tokenBalanceResult : queryAccountBalance) {
            System.out.println(tokenBalanceResult);
        }
    }

    /**
     * 
     * @description 查询地址列表token余额
     *
     */
    @Test
    public void queryTokenBalace() throws Exception {
        List<String> addrList = new ArrayList<>();
        addrList.add("XXXXXXXXXXXX");
        List<AccountAccResult> tokenBalance;
        tokenBalance = client.getTokenBalance(addrList, "user.p.xxchain.token", "tokenSymbol");
        for (AccountAccResult accountAccResult : tokenBalance) {
            System.out.println(accountAccResult);
        }

    }

    /**
     * 
     * @description 查询某地址的交易hash
     *
     */
    @Test
    public void getTxByAddr() throws Exception {
        List<TxResult> txByAddr;
        txByAddr = client.getTxByAddr("address", 0, 10, 0, -1L, 0);
        for (TxResult txResult : txByAddr) {
            System.out.println(txResult);
        }
        for (TxResult txResult : txByAddr) {
            System.out.println(client.queryTransaction(txResult.getHash()));
        }
    }

    /**
     * 
     * @description 预创建token
     */
    @Test
    public void preCreateToken() throws Exception {
        long total = (long) (1000 * 1e8);
        // 调用节点接口预创建token 返回hash
        String createRawTokenPreCreateTx = client.createRawTokenPreCreateTx("logan coin1", "LGS",
                "logan create the coin", "1Af1JWXYVJwMrSkC7QpG4KVckNKgXmnhm4", total, 0,0);
        // 签名
        String signRawTx = client.signRawTx("1Af1JWXYVJwMrSkC7QpG4KVckNKgXmnhm4", "0x65622cbb675a62ec6de652811dc649286652b75c80850ccd7bb30ffb053c5af9", createRawTokenPreCreateTx, "300", 0);
        String submitTransaction =  client.submitTransaction(signRawTx);
        System.out.println(submitTransaction);
    }

    /**
     * 
     * @description 完成token创建
     */
    @Test
    public void createTokenFinish() throws Exception {
        String createRawTokenFinishTx = client.createRawTokenFinishTx((long) 1e8, "LGS", "address");
        String signRawTx = client.signRawTx("address", "addressPrivateKey", createRawTokenFinishTx, 300 + "", 0);
        String submitTransaction = client.submitTransaction(signRawTx);
        System.out.println(submitTransaction);
    }

    /**
     * @description 合约转为地址
     */
    @Test
    public void convertExecertoAddr() throws Exception {
        String address;
        address = client.convertExectoAddr("manage");
        System.out.println(address);
    }


    /**
     * @description 本地构造上链交易数据。数据大手续费越高,推荐压缩之后再上链。
     */
    @Test
    public void uploadDateToChain() throws Exception {
        String transactionHash = TransactionUtil.createTx(withHoldPrivateKey, "user.p.xxchain.coins", "content",
                TransactionUtil.DEFAULT_FEE);
        // 创建代扣交易 创建交易上链需要消耗手续费
        String noBalanceHash = client.createNoBalanceTx(transactionHash, withHoldAddress);
        String signedTxHash = client.signRawTx("user address", null, noBalanceHash, "1h", 2);
        String withHoldTx = client.submitTransaction(signedTxHash);
        System.out.println(withHoldTx);
    }
    
    
    /**
     * @description 本地构造平行链主代币转账交易
     */
    @Test
    public void createCoinTransferTxPara() throws Exception {
    	// 转账说明
        String note = "转账说明";
        // 主代币则为"",其他为token名
        String coinToken = "";
        Long amount = 1 * 100000000L;// 1 = real amount
        // 转到的地址
        String to = "toAddress";
        //String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // 本地构造转账交易的payload
        byte[] payload = TransactionUtil.createTransferPayLoad(to, amount, coinToken, note);
        // 签名私私钥，里面需要有主链币，用于缴纳手续费
        String fromAddressPriveteKey = "from addrss privateKey";
        //String fromAddressPriveteKey = "0x1ce5a097b01e53d423275091e383a2c3a35d042144bd3bced44194eabxxxxxxx";
        // 执行器名称，平行链主代币为平行链名称+coins(平行链对应配置文件中的title项)
        String execer = "user.p.xxchain.coins";
        // 平行链转账时，实际to的地址填在payload中，外层的to地址对应的是合约的地址
        String contranctAddress = client.convertExectoAddr(execer);
        String createTransferTx = TransactionUtil.createTransferTx(fromAddressPriveteKey, contranctAddress, execer, payload);
        String txHash = client.submitTransaction(createTransferTx);
        System.out.println(txHash);
    }

    /**
     * @description 通过节点构造token/主代币转账
     */
    @Test
    public void transferToken() throws Exception {
        String toAddr = "to address";
        String tokenSymBol = "tokenSymbol";
        String fromAddr = "from address";
        Long amount = 10L;
        Long tranAmount = amount * 100000000L;
        Boolean isToken = false;// false为主代币转账，tokenSymbol不传，为""，true为token转账
        // 通过节点创建转账交易
        String rawTx = client.createRawTransaction(toAddr, tranAmount, TransactionUtil.DEFAULT_FEE, "", isToken, false,
                tokenSymBol, null);
        // 通过节点创建代扣交易
        String createNoBalanceTx = client.createNoBalanceTx(rawTx, withHoldAddress);
        // 通过节点签名
        String signRawTx = client.signRawTx(fromAddr, null, createNoBalanceTx, "1h", 2);
        // 发送交易
        String signedTxHex = client.submitTransaction(signRawTx);
        System.out.println(signedTxHex);
    }
    
    /**
     * 
     * @description 本地签名代扣交易组，调用createNoBlance之后再将返回的数据解析,签名，发送交易
     * 代扣交易主要用在平行链的场合，主链上的交易不需要关注此实现
     *
     */
    @Test
    public void localTransfer() throws Exception {
    	// 转账说明
        String note = "转账说明";
        // 主代币则为"",其他为token名
        String coinToken = "";
        // 转账数量为1
        Long amount = 1 * 10000000L;
        String to = "toAddress";
        //String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // 本地构造转账交易的payload
        byte[] payload = TransactionUtil.createTransferPayLoad(to, amount, coinToken, note);
        // 签名私私钥，主链上不会扣除本地址下的主链币，所以此地址下可以没有主链币
        String fromAddressPriveteKey = "实际交易签名私钥";
        //String fromAddressPriveteKey = "1ce5a097b01e53d423275091e383a2c3a35d042144bd3bced44194eab2ff18c9";
        // 执行器名称，平行链主代币为平行链名称+coins(平行链对应配置文件中的title项)
        String execer = "user.p.sec.coins";
        // 平行链转账时，实际to的地址填在payload中，外层的to地址对应的是合约的地址
        String contranctAddress = client.convertExectoAddr(execer);
        String createTransferTx = TransactionUtil.createTransferTx(fromAddressPriveteKey, contranctAddress, execer, payload);
        
        //create no balance 传入地址为空
        String createNoBalanceTx = client.createNoBalanceTx(createTransferTx, "");
        // 解析交易
        List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
        // 代扣交易签名的私钥
        //String withHoldPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
        String withHoldPrivateKey = "代扣地址私钥";
        String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, fromAddressPriveteKey, withHoldPrivateKey);
        String submitTransaction = client.submitTransaction(hexString);
        System.out.println("submitTransaction:" + submitTransaction);
    }

    /**
     * @description 本地将执行器转为地址
     */
    @Test
    public void convertExeceToAddress() {
        String exece = "user.p.demo.game";
        String addr = TransactionUtil.convertExectoAddr(exece);
        System.out.println(addr);
    }
        
	/**
	* @description 撤销预创建的token
	*/
	@Test
	public void revokePrecreateToken() throws Exception {
	   String symbol = "COINSDEVX";
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
	   String createRawTokenRevokeTx = client.CreateRawTokenRevokeTx(symbol, owner);
	   String signRawTx = client.signRawTx(privateKey, null, createRawTokenRevokeTx, "1h", 0);
	   String submitTransaction = client.submitTransaction(signRawTx);
	   System.out.println(submitTransaction);
	}
	
	/**
	 * 接口QPS测试(单线程)
	 */
	@Test
	public void qpsTest() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    	System.out.println(df.format(System.currentTimeMillis())); 
		
        String hash = "0x441e91ff13f28fe104d66db4308ab12652868eaf8a0011dec2059a4be98bdfb3";
        for (int i = 0; i <= 50000; i++) {

            client.queryTransaction(hash);
           // System.out.println(queryTransaction1);
            if (i%1000 == 0) {
            	System.out.println(i);
            	System.out.println(df.format(System.currentTimeMillis())); 
            }
        }
        
	}
}
