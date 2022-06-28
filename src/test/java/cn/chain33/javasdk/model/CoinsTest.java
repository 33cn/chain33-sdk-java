package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.enums.AddressType;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.AddressUtil;
import cn.chain33.javasdk.utils.CoinsUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/28 下午2:12
 */
public class CoinsTest {

    String ip = "localhost";
    RpcClient client = new RpcClient(ip, 8901);
    String PrivateKey = "cc38546e9e659d15e6b4893f0ab32a06d103931a8230b0bde71459d2b27d6944";

    /**
     * 测试coins主币转移到其他执行器中
     *
     * @throws Exception
     */
    @Test
    public void testTransferToExec() throws Exception {
        String Addr = "14KEKbYtKKQm4wMthSK9J4La4nAiidGozt";
        //step1 先将主币coins资产转移到evm执行器平台名下
        String tx = CoinsUtil.createTransferToExecTx("", 200000000, "evm", AddressUtil.getToAddress("evm".getBytes(), AddressType.BTC_ADDRESS), "", PrivateKey, SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);
        String txhash = client.submitTransaction(tx);
        System.out.println("转移到evm平台下的主币交易哈希 = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        List<String> list = new ArrayList<String>();
        list.add(Addr);
        List<AccountAccResult> results = client.getCoinsBalance(list, "evm");
        results.forEach(a->{
            System.out.println("addr: "+a.getAddr()+ " balance:"+a.getBalance());
        });
    }

    /**
     * 测试从其他执行器中回提coins代币
     * @throws Exception
     */
    @Test
    public void testWithdraw() throws Exception {
        String Addr = "14KEKbYtKKQm4wMthSK9J4La4nAiidGozt";
        //step1 先将主币coins资产转移到evm执行器平台名下
        String tx = CoinsUtil.createWithdrawTx("", 200000000, "evm", AddressUtil.getToAddress("evm".getBytes(), AddressType.BTC_ADDRESS), "", PrivateKey, SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);
        String txhash = client.submitTransaction(tx);
        System.out.println("从evm平台下的提现交易哈希 = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        List<String> list = new ArrayList<String>();
        list.add(Addr);
        List<AccountAccResult> results = client.getCoinsBalance(list, "evm");
        results.forEach(a->{
            System.out.println("addr: "+a.getAddr()+ " balance:"+a.getBalance());
        });
    }

    /**
     * 测试coins普通地址转账
     */
    @Test
    public void testTransfer() throws Exception {
        String AddrA = "14KEKbYtKKQm4wMthSK9J4La4nAiidGozt";
        String AddrB = "193TZpLVKoJE9nqggiGtDFKcv34faKRBF5";
        //step1 先将主币coins资产转移到evm执行器平台名下
        String tx = CoinsUtil.createTransferTx("", 200000000, AddrB, "", PrivateKey, SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);
        String txhash = client.submitTransaction(tx);
        System.out.println("转移到AddrB的主币交易哈希 = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        List<String> list = new ArrayList<String>();
        list.add(AddrA);
        list.add(AddrB);
        List<AccountAccResult> results = client.queryBalance(list, "coins");
        results.forEach(a->{
            System.out.println("addr: "+a.getAddr()+ " balance:"+a.getBalance());
        });
    }
}
