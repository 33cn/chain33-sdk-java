package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.pre.EncryptKey;
import cn.chain33.javasdk.model.pre.KeyFrag;
import cn.chain33.javasdk.utils.*;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * 数据拥有者将数据加密上链，并且将
 * @author fkeit
 *
 */
public class PreOwner {

    // 代理重加密节点
    static RpcClient[]  preClient = new RpcClient[]{
            new RpcClient("http://139.196.201.120:11801"),
            new RpcClient("http://139.196.201.141:11801"),
            new RpcClient("http://139.196.201.241:11801"),
            new RpcClient("http://139.196.201.49:11801"),
    };

    // 区块链节点
    static RpcClient chain33Client = new RpcClient("http://139.196.201.120:8901");

    // 数据所有者私钥
    static String OwnerPrivateKey = "30a13eb5f155404e5973203293b4700a5759ec9b74af01c6512ff0450f51ed88";


    // 代理节点公钥，用于身份验证，不参与重加密和加解密算法
    static String ServerPub = "0x02005d3a38feaff00f1b83014b2602d7b5b39506ddee7919dd66539b5428358f08";
    
    // 分4片
    static int numSplit = 4;
    
    // 门限是3， 也就是通过3个分片就可以恢复私钥
    static int threshold = 2;

    // 待加密的数据
    static String content = "Chain33 代理重加密测试";

    /**
     * 代理重加密秘钥分片存储到代理重加密服务器，同时数据加密上链存储
     * @param numSplit
     * @param threshold
     */
    @Test
    public void preEncrypt() {
        AccountInfo alice = new AccountInfo();
        alice.setPrivateKey(OwnerPrivateKey);
        alice.setPublicKey(TransactionUtil.getHexPubKeyFromPrivKey(OwnerPrivateKey));
        
        
        // 生成对称秘钥
        EncryptKey encryptKey = PreUtils.GenerateEncryptKey(HexUtil.fromHexString(alice.getPublicKey()));
        System.out.println("对称加密秘钥：" + DatatypeConverter.printHexBinary(encryptKey.getShareKey()));

        // 数据被授权人公钥：授权Bob可以看
        String RecipientBobPubKey = "02eb159bfaea4a3c0e3dc74c7f5084162a5aea0f0a21d40ab8fa10914ad5e15a74";
        uploadKey(encryptKey, alice, RecipientBobPubKey);

        // 数据被授权人公钥：授权Tom可以看
        String RecipientTomPubKey = "02449aa75bd6f4313847eb9c2ac07f8dd26f0ca5a511e973ab1377ace32998391d";
        uploadKey(encryptKey, alice, RecipientTomPubKey);

        // 数据加密
        byte[] iv = AesUtil.generateIv();
        byte[] cipher = AesUtil.encrypt(content, encryptKey.getShareKey(), iv);
        System.out.println("加密后的密文：" + cipher.toString());

        // 加密数据上链
        byte[] contentHash;
        try {
            contentHash = TransactionUtil.Sha256(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }

        String txEncode = StorageUtil.createEncryptNotaryStorage(cipher, contentHash, iv,
                "chain33-storage-key-pre-alice2", "", "storage", OwnerPrivateKey);
        String submitTransaction = chain33Client.submitTransaction(txEncode);
        System.out.println("交易hash值：" + submitTransaction);
    }


    /**
     * 分片并上传加密私钥
     * 
     * @param encryptKey
     * @param alice
     */
    private void uploadKey(EncryptKey encryptKey, AccountInfo alice, String pubkey) {
        // 生成重加密密钥分片
        KeyFrag[] kFrags = new KeyFrag[numSplit];
        try {
            kFrags = PreUtils.GenerateKeyFragments(HexUtil.fromHexString(alice.getPrivateKey()),
                    HexUtil.fromHexString(pubkey), numSplit, threshold);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 密钥分片发送到代理节点
        String dhProof = PreUtils.ECDH(ServerPub, alice.getPrivateKey());
        for(int i = 0; i < preClient.length; i++) {
            boolean result = preClient[i].sendKeyFragment(alice.getPublicKey(), pubkey, encryptKey.getPubProofR(),
                    encryptKey.getPubProofU(), 100, dhProof, kFrags[i]);
            if (!result) {
                System.out.println("sendKeyFragment failed");
                return;
            }
        }
}
}
