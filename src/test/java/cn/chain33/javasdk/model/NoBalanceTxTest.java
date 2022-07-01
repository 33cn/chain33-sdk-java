package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.enums.AddressType;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.Transaction;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.protobuf.Transactions;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.CoinsUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/30 上午9:24
 */
public class NoBalanceTxTest {

    String ip = "localhost";
    RpcClient client = new RpcClient(ip, 8901);
    String PrivateKey = "cc38546e9e659d15e6b4893f0ab32a06d103931a8230b0bde71459d2b27d6944";

    String PrivatekeyB = "0x28df032183cdb07bebd7cc62fbc86857d482079f66c877a5d77a6efacd211686";

    String to = "193TZpLVKoJE9nqggiGtDFKcv34faKRBF5";


    /**
     * 测试coins普通地址转账
     */
    @Test
    public void testNoBalanceTx() throws Exception {
        String AddrA = "14KEKbYtKKQm4wMthSK9J4La4nAiidGozt";
        String AddrB = "193TZpLVKoJE9nqggiGtDFKcv34faKRBF5";
        //step1 构造代扣交易
        String tx1 = CoinsUtil.createTransferTx("", 200000000, AddrB, "", "", SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 0);
        String tx = TransactionUtil.createNoBalanceTx(TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(tx1)), PrivatekeyB,PrivateKey, SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, 100000, "");
        System.out.println(tx);
        String txhash = client.submitTransaction(tx);
        System.out.println("转移到AddrB的主币交易哈希 = " + txhash);

    }

    @Test
    public void testTxGroup() throws Exception {
        String AddrA = "14KEKbYtKKQm4wMthSK9J4La4nAiidGozt";
        String AddrB = "193TZpLVKoJE9nqggiGtDFKcv34faKRBF5";
        //step1 构造代扣交易
        String tx1 = CoinsUtil.createTransferTx("", 200000000, AddrB, "", "", SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);
        String tx2 = CoinsUtil.createTransferTx("", 200000000, AddrB, "", "", SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);
        ArrayList<TransactionAllProtobuf.Transaction> txsList = new ArrayList<TransactionAllProtobuf.Transaction>();
        txsList.add(TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(tx1)));
        txsList.add(TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(tx2)));
        TransactionAllProtobuf.Transaction tx = TransactionUtil.getTxFromTxGroup(TransactionUtil.createTxGroup(txsList, 100000));
        String txHex = TransactionUtil.signProtoBufTxs(tx, PrivateKey, SignType.SECP256K1, 0, 0);
        String txhash = client.submitTransaction(txHex);
        System.out.println("转账交易组交易哈希： = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        List<String> list = new ArrayList<String>();
        list.add(AddrA);
        list.add(AddrB);
        List<AccountAccResult> results = client.queryBalance(list, "coins");
        results.forEach(a -> {
            System.out.println("addr: " + a.getAddr() + " balance:" + a.getBalance());
        });
    }

    @Test
    public void testTransactions() throws Exception {
        String AddrA = "14KEKbYtKKQm4wMthSK9J4La4nAiidGozt";
        String AddrB = "193TZpLVKoJE9nqggiGtDFKcv34faKRBF5";

        //step1 构造交易组
        String tx1 = CoinsUtil.createTransferTx("", 200000000, AddrB, "", "", SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);
        String tx2 = CoinsUtil.createTransferTx("", 200000000, AddrB, "", "", SignType.SECP256K1, AddressType.BTC_ADDRESS, 0, "", 1000000);


        Transactions txs = new Transactions(tx1,tx2);
        txs.reBuildGroup(100000);
        System.out.println(txs.getTxs());
        txs.signN(-1,SignType.SECP256K1, PrivateKey);
        System.out.println(txs.toTransaction().hexString());
        String txhash = client.submitTransaction(txs.toTransaction().hexString());
        System.out.println("转账交易组交易哈希： = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        List<String> list = new ArrayList<String>();
        list.add(AddrA);
        list.add(AddrB);
        List<AccountAccResult> results = client.queryBalance(list, "coins");
        results.forEach(a -> {
            System.out.println("addr: " + a.getAddr() + " balance:" + a.getBalance());
        });
    }

}
