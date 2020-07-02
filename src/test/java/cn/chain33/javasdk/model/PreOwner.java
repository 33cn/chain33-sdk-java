package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.pre.EncryptKey;
import cn.chain33.javasdk.model.pre.KeyFrag;
import cn.chain33.javasdk.utils.*;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class PreOwner {

    // 代理重加密节点
//    static RpcClient[]  preClient = new RpcClient[]{
//            new RpcClient("http://192.168.0.155:11801"),
//            new RpcClient("http://192.168.0.155:11802"),
//            new RpcClient("http://192.168.0.155:11803"),
//    };
//
//    // 区块链节点
//    static RpcClient chain33Client = new RpcClient("http://192.168.0.155:8801");

    static RpcClient[]  preClient = new RpcClient[]{
            new RpcClient("http://139.196.201.120:11801"),
            new RpcClient("http://139.196.201.141:11801"),
            new RpcClient("http://139.196.201.241:11801"),
            new RpcClient("http://139.196.201.49:11801"),
    };

    // 区块链节点
    static RpcClient chain33Client = new RpcClient("http://139.196.201.120:8901");

    // 数据所有者私钥
    static String OwnerPrivateKey = "4247776e69fec9c72b03812302b98de6663de43ad9fe3674d953079fceea850c";

    // 数据授权人公钥
    static String RecipientPubKey = "028ad2f80aa90f7182a0519b09f5efbbfc5eb3fae42f99986cd5a9c64ea4c4ced2";

    // 代理节点公钥，用于身份验证，不参与重加密和加解密算法
    static String ServerPub = "0x02005d3a38feaff00f1b83014b2602d7b5b39506ddee7919dd66539b5428358f08";

    // 待加密的数据
    static String content = "Chain33 代理重加密测试";

    public static void preEncrypt(int numSplit, int threshold) {
        AccountInfo alice = new AccountInfo();
        alice.setPrivateKey(OwnerPrivateKey);
        alice.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(OwnerPrivateKey));

        // 生成对称秘钥
        EncryptKey encryptKey = PreUtils.GenerateEncryptKey(HexUtil.fromHexString(alice.getPublicKey()));
        System.out.println(DatatypeConverter.printHexBinary(encryptKey.getShareKey()));

        // 生成重加密密钥分片
        KeyFrag[] kFrags = new KeyFrag[numSplit];
        try {
            kFrags = PreUtils.GenerateKeyFragments(HexUtil.fromHexString(alice.getPrivateKey()),
                    HexUtil.fromHexString(RecipientPubKey), numSplit, threshold);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 密钥分片发送到代理节点
        String dhProof = PreUtils.ECDH(ServerPub, alice.getPrivateKey());
        for(int i = 0; i < preClient.length; i++) {
            boolean result = preClient[i].sendKeyFragment(alice.getPublicKey(), RecipientPubKey, encryptKey.getPubProofR(),
                    encryptKey.getPubProofU(), 100, dhProof, kFrags[i]);
            if (!result) {
                System.out.println("sendKeyFragment failed");
                return;
            }
        }

        // 数据加密
        byte[] iv = AesUtil.generateIv();
        byte[] cipher = AesUtil.encrypt(content, encryptKey.getShareKey(), iv);
        System.out.println(cipher);

        // 加密数据上链
        byte[] contentHash;
        try {
            contentHash = TransactionUtil.Sha256(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        String txEncode = StorageUtil.createEncryptNotaryStorage(cipher, contentHash, iv,
                "chain33-storage-key-pre-"+kFrags[0].getPrecurPub(), "", "storage", OwnerPrivateKey);
        String submitTransaction = chain33Client.submitTransaction(txEncode);
        System.out.println(submitTransaction);
    }

    public static void main(String args[]) {
        preEncrypt(4, 3);
    }
}
