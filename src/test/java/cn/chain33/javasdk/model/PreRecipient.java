package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.pre.ReKeyFrag;
import cn.chain33.javasdk.utils.*;
import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class PreRecipient {

    // 代理重加密节点
    static RpcClient[]  preClient = new RpcClient[]{
            new RpcClient("http://139.196.201.120:11801"),
            new RpcClient("http://139.196.201.141:11801"),
            new RpcClient("http://139.196.201.241:11801"),
            new RpcClient("http://139.196.201.49:11801"),
    };

    // 区块链节点
    static RpcClient chain33Client = new RpcClient("http://139.196.201.120:8901");

    // 数据所有者公钥
    static String OwnerPubKey = "03aeba7f887ac8978eb878d0a45e3938361604c3db0d2442695f3f226e9d5f5567";

    // 被授权人私钥,Bob的私钥
    static String RecipientBobPrivateKey = "407ba5d402e8760dab6f08b1743143eb31edb978d329f241f97ad71028d2cc3f";
    
    // 被授权人私钥,Tom的私钥
    static String RecipientTomPrivateKey = "386fdbddbda034ed719321a723ed057542879199e18309cfc09e4264b3237833";
    
    // 被授权人私钥,James的私钥
    static String RecipientJamesPrivateKey = "41902240b8aed527841dd0cf257b9cab3e26638f3eb5c93c1f202e9af925b743";
    
    // 门限是3， 也就是通过3个分片就可以恢复私钥
    static int threshold = 3;

    /**
     * Bob解密
     */
    @Test
    public void preDecryptBob() {
        AccountInfo bob = new AccountInfo();
        bob.setPrivateKey(RecipientBobPrivateKey);
        bob.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientBobPrivateKey));

        byte[] shareKeyBob = downloadKey(bob);

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-alice1");
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");
        byte[] fromHexString = HexUtil.fromHexString(content);

        // 解密
        String text = AesUtil.decrypt(fromHexString, HexUtil.toHexString(shareKeyBob));
        System.out.println(text);
    }
    
    /**
     * Tom解密
     */
    @Test
    public void preDecryptTom() {
        AccountInfo tom = new AccountInfo();
        tom.setPrivateKey(RecipientTomPrivateKey);
        tom.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientTomPrivateKey));

        byte[] shareKeyTom = downloadKey(tom);

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-alice");
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");
        byte[] fromHexString = HexUtil.fromHexString(content);

        // 解密
        String text = AesUtil.decrypt(fromHexString, HexUtil.toHexString(shareKeyTom));
        System.out.println(text);
    }
    
    /**
     * James解密
     */
    @Test
    public void preDecryptJames() {
        AccountInfo james = new AccountInfo();
        james.setPrivateKey(RecipientBobPrivateKey);
        james.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientJamesPrivateKey));

        byte[] shareKeyJames = downloadKey(james);

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-alice");
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");
        byte[] fromHexString = HexUtil.fromHexString(content);
        System.out.println(new String(fromHexString));

        // 解密
        String text = AesUtil.decrypt(fromHexString, HexUtil.toHexString(shareKeyJames));
        System.out.println(text);
    }

    /**
     * 从代理重加密节点下载私钥匙分片，并组成解密私钥匙
     * 
     * @param accountInfo
     * @return
     */
    public  byte[] downloadKey(AccountInfo accountInfo) {
        // 申请重加密，需要两边的公钥
        ReKeyFrag[] reKeyFrags = new ReKeyFrag[threshold];
        for(int i = 0; i < threshold; i++) {
            reKeyFrags[i] = preClient[i].reencrypt(OwnerPubKey, accountInfo.getPublicKey());
        }

        // 解密对称密钥，需要被授权人私钥
        byte[] shareKeyBob;
        try {
            shareKeyBob = PreUtils.AssembleReencryptFragment(HexUtil.fromHexString(accountInfo.getPrivateKey()), reKeyFrags);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(DatatypeConverter.printHexBinary(shareKeyBob));
        return shareKeyBob;
    }
}
