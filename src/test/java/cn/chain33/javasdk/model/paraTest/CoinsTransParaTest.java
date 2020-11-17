package cn.chain33.javasdk.model.paraTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 * 平行链上，通过代扣的方式转coins
 * @author fkeit
 *
 */
public class CoinsTransParaTest {
	
	// 平行链IP
	String ip = "平行链IP";
	// 平行链服务端口
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);
    
    // 平行链名称，固定格式user.p.xxxx.  其中xxxx可替换，支持大小写英文字母
	String paraName = "user.p.evm.";
	
    /**
     * 
     * @throws InterruptedException 
     * @description 本地签名代扣交易组，调用createNoBlance之后再将返回的数据解析,签名，发送交易
     * 代扣交易主要用在平行链的场合，主链上的交易不需要关注此实现
     *
     */
    @Test
    public void transferCoins() throws InterruptedException {
    	// 转账说明
        String note = "转账说明";
        // 主代币则为"",其他为token名
        String coinToken = "";
        // 转账数量为1
        Long amount = 1 * 10000000L;
        String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // 本地构造转账交易的payload
        byte[] payload = TransactionUtil.createTransferPayLoad(to, amount, coinToken, note);
        
        // 签名私私钥，主链上不会扣除本地址下的主链币，所以此地址下可以没有主链币
        String fromAddressPriveteKey = "CC38546E9E659D15E6B4893F0AB32A06D103931A8230B0BDE71459D2B27D6944";
        // 执行器名称，平行链主代币为平行链名称+coins(平行链对应配置文件中的title项)
        String execer = paraName + "coins";
        // 平行链转账时，实际to的地址填在payload中，外层的to地址对应的是合约的地址
        String contranctAddress = client.convertExectoAddr(execer);
        String createTransferTx = TransactionUtil.createTransferTx(fromAddressPriveteKey, contranctAddress, execer, payload);
        
        //create no balance 传入地址为空
        String createNoBalanceTx = client.createNoBalanceTx(createTransferTx, "");
        // 解析交易
        List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
        // 代扣交易签名的私钥
        String withHoldPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
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
     * @description 查询coins余额
     *
     */
    @Test
    public void getTokenBalace() {
        // 执行器名称
        String execer = paraName + "coins";
        
        List<String> addressList = new ArrayList<>();
        addressList.add("14KEKbYtKKQm4wMthSK9J4La4nAiidGozt");
        addressList.add("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getCoinsBalance(addressList, execer);
        for (AccountAccResult accountAccResult : queryBtyBalance) {
            System.out.println(accountAccResult);
        }
    }

}
