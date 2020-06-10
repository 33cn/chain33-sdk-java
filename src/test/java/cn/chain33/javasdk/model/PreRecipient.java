package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.pre.ReKeyFrag;
import cn.chain33.javasdk.utils.*;
import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.DatatypeConverter;

public class PreRecipient {

    // 代理重加密节点
    static RpcClient[]  preClient = new RpcClient[]{
            new RpcClient("http://192.168.0.155:11801"),
            new RpcClient("http://192.168.0.155:11802"),
    };

    // 区块链节点
    static RpcClient chain33Client = new RpcClient("http://192.168.0.155:8801");

    // 数据所有者公钥
    static String OwnerPubKey = "02b009d9388d452677e4c00535ee60a0266f2688bb6c68cdd81543bc4b5d6adb80";

    // 被授权人私钥
    static String RecipientPrivateKey = "6e955c1dc32e76394942233eee23b466313a61231a34fde6bc83815f4a29eb9a";

    public static void preDecrypt(int threshold) {
        AccountInfo bob = new AccountInfo();
        bob.setPrivateKey(RecipientPrivateKey);
        bob.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(RecipientPrivateKey));

        // 申请重加密，需要两边的公钥
        ReKeyFrag[] reKeyFrags = new ReKeyFrag[threshold];
        for(int i = 0; i < threshold; i++) {
            reKeyFrags[i] = preClient[i].reencrypt(OwnerPubKey, bob.getPublicKey());
        }

        // 解密对称密钥，需要被授权人私钥
        byte[] shareKeyBob;
        try {
            shareKeyBob = PreUtils.AssembleReencryptFragment(HexUtil.fromHexString(bob.getPrivateKey()), reKeyFrags);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println(DatatypeConverter.printHexBinary(shareKeyBob));

        // 从链上获取密文
        JSONObject resultJson = chain33Client.queryStorage("chain33-storage-key-pre-"+reKeyFrags[0].getPrecurPub());
        JSONObject resultArray = resultJson.getJSONObject("encryptStorage");
        String content = resultArray.getString("encryptContent");
        byte[] fromHexString = HexUtil.fromHexString(content);
        System.out.println(new String(fromHexString));

        // 解密
        String text = AesUtil.decrypt(new String(fromHexString), HexUtil.toHexString(shareKeyBob));
        System.out.println(text);
    }

    public static void main(String args[]) {
        preDecrypt(2);
    }
}
