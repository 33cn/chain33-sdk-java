package cn.chain33.javasdk.model;

import java.math.BigInteger;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.paillier.PaillierCipher;
import cn.chain33.javasdk.model.paillier.PaillierKeyPair;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.StorageUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class PaillierTest {
    @Test
    public void homAddTest() {
        PaillierKeyPair keypair = PaillierKeyPair.generateGoodKeyPair();

        BigInteger i1 = BigInteger.valueOf(100000);
        String c1 = PaillierCipher.encrypt(i1, keypair.pubKey);

        BigInteger o1 = PaillierCipher.decrypt(c1, keypair.privKey);
        Assert.assertEquals(i1, o1);

        BigInteger i2 = BigInteger.valueOf(-20000);
        String c2 = PaillierCipher.encrypt(i2, keypair.pubKey);

        String c3 = PaillierCipher.ciphertextAdd(c1, c2);

        BigInteger o3 = PaillierCipher.decrypt(c3, keypair.privKey);
        Assert.assertEquals(BigInteger.valueOf(80000), o3);
    }

    @Test
    public void paillierStoryTest() throws Exception {
        RpcClient client = new RpcClient("127.0.0.1", 8801);

        // 上次同态加密公私钥对
        PaillierKeyPair keypair = PaillierKeyPair.generateGoodKeyPair();

        // 初始数据加密
        BigInteger i1 = BigInteger.valueOf(100000);
        byte[] c1 = PaillierCipher.encryptAsBytes(i1, keypair.pubKey);

        // 签名用的私钥
        String privateKey = "0x85bf7aa29436bb186cac45ecd8ea9e63e56c5817e127ebb5e99cd5a9cbfe0f23";

        // 初始数据上链
        String txEncode = StorageUtil.createEncryptNotaryStorage(c1, TransactionUtil.Sha256(i1.toByteArray()),  i1.toByteArray(), "", "",  "storage", privateKey);
        String submitTransaction = client.submitTransaction(txEncode);
        Thread.sleep(1000);

        // 待计算的数据加密
        BigInteger i2 = BigInteger.valueOf(-30000);
        byte[] c2 = PaillierCipher.encryptAsBytes(i2, keypair.pubKey);

        // 链上执行数据运算
        txEncode = StorageUtil.createEncryptNotaryAdd(c2, submitTransaction, privateKey);
        String submitTransaction2 = client.submitTransaction(txEncode);
        Thread.sleep(1000);

        // 查询数据
        JSONObject resultJson = client.queryStorage(submitTransaction);
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");

        // 数据解密
        byte[] fromHexString = HexUtil.fromHexString(content);
        BigInteger res = PaillierCipher.decrypt(fromHexString, keypair.privKey);
        System.out.println(res.intValue());
        Assert.assertEquals(res.intValue(), i1.add(i2).intValue());
    }
}
