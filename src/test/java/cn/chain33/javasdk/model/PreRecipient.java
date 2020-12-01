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
            new RpcClient("http://ip1:11801"),
            new RpcClient("http://ip2:11801"),
            new RpcClient("http://ip3:11801"),
            new RpcClient("http://ip4:11801"),
    };

    // 区块链节点
    static RpcClient chain33Client = new RpcClient("http://ip:8901");

    // 数据所有者公钥
    static String OwnerPubKey = "02e8fd8762cf391266b7acb5d50e5f426e3740c8794dea50a69a3b0d5aae5e216c";

    // 被授权人私钥,Bob的私钥
    static String RecipientBobPrivateKey = "2af2bb8745103ad7b29d6b9c8c9dd5707f910f002475cba9993785573ab4fadc";
    
    // 被授权人私钥,Tom的私钥
    static String RecipientTomPrivateKey = "51638366c18b560359a4b6233cffee1d9cf9d6b146beb30a6a75fa4d754bed06";
    
    // 被授权人私钥,James的私钥
    static String RecipientJamesPrivateKey = "5f88f4a90bbcd83a299d42b3b49923d63137f142e37119c7730626cde50c6bf9";
    
    // 门限是3， 也就是通过3个分片就可以恢复私钥
    static int threshold = 2;

    /**
     * Bob解密
     */
    @Test
    public void preDecryptBob() throws Exception {
        AccountInfo bob = new AccountInfo();
        bob.setPrivateKey(RecipientBobPrivateKey);
        bob.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientBobPrivateKey));

        byte[] shareKeyBob = downloadKey(bob);

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-alice2");
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");
        byte[] fromHexString = HexUtil.fromHexString(content);
        
        System.out.println("加密后的密文: " + fromHexString.toString());
        
        // 解密
        String text = AesUtil.decrypt(fromHexString, HexUtil.toHexString(shareKeyBob));
        System.out.println("秘钥: " + HexUtil.toHexString(shareKeyBob));
        
        System.out.println(text);
    }
    
    /**
     * Tom解密
     */
    @Test
    public void preDecryptTom() throws Exception {
        AccountInfo tom = new AccountInfo();
        tom.setPrivateKey(RecipientTomPrivateKey);
        tom.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientTomPrivateKey));

        byte[] shareKeyTom = downloadKey(tom);

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-alice2");
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
    public void preDecryptJames() throws Exception {
        AccountInfo james = new AccountInfo();
        james.setPrivateKey(RecipientBobPrivateKey);
        james.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientJamesPrivateKey));

        byte[] shareKeyJames = downloadKey(james);

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-alice2");
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");
        byte[] fromHexString = HexUtil.fromHexString(content);

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
    public  byte[] downloadKey(AccountInfo accountInfo) throws Exception {
        // 申请重加密，需要两边的公钥
        ReKeyFrag[] reKeyFrags = new ReKeyFrag[threshold];
        for(int i = 0; i < threshold; i++) {
            reKeyFrags[i] = preClient[i].reencrypt(OwnerPubKey, accountInfo.getPublicKey());
        }

        // 解密对称密钥，需要被授权人私钥
        byte[] shareKey;
        try {
            shareKey = PreUtils.AssembleReencryptFragment(HexUtil.fromHexString(accountInfo.getPrivateKey()), reKeyFrags);
        } catch (Exception e) {
            System.out.print("出错：没有权限进行重加密解密！！");
            return null;
        }
        System.out.println(DatatypeConverter.printHexBinary(shareKey));
        return shareKey;
    }
}
