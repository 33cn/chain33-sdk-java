package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.pre.EncryptKey;
import cn.chain33.javasdk.model.pre.KeyFrag;
import cn.chain33.javasdk.model.pre.ReKeyFrag;
import cn.chain33.javasdk.utils.AesUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.PreUtils;
import cn.chain33.javasdk.utils.TransactionUtil;
import org.bitcoinj.core.ECKey;
import org.junit.Assert;

public class PreUtilsTest {
    static String content = "chain33 pre test";

    public static void bnEccUtils(int numSplit, int threshold, String serverPub) {
        RpcClient[] client = new RpcClient[]{
                new RpcClient("http://192.168.0.155:11801"),
                new RpcClient("http://192.168.0.155:11802"),
                new RpcClient("http://192.168.0.155:11803"),
        };

        ECKey alice = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());
        EncryptKey encryptKey = PreUtils.GenerateEncryptKey(alice.getPubKey());
        String cipher = AesUtil.encrypt(content, encryptKey.getShareKey(),  AesUtil.generateIv());

        ECKey bob = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());
        ECKey server = ECKey.fromPublicOnly(HexUtil.fromHexString(serverPub));

        // generate kfrag
        KeyFrag[] kFrags = new KeyFrag[numSplit];
        try {
            kFrags = PreUtils.GenerateKeyFragments(alice.getPrivKeyBytes(), bob.getPubKey(), numSplit, threshold);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String dhProof =  HexUtil.toHexString(server.getPubKeyPoint().multiply(alice.getPrivKey()).getEncoded());
        for(int i = 0; i < client.length; i++) {
            boolean result = client[i].sendKeyFragment(alice.getPublicKeyAsHex(), bob.getPublicKeyAsHex(),
                    encryptKey.getPubProofR(), encryptKey.getPubProofU(), 100, dhProof, kFrags[i]);
            if (!result) {
                System.out.println("sendKeyFragment failed");
                return;
            }
        }

        // reencrypt
        ReKeyFrag[] reKeyFrags = new ReKeyFrag[threshold];
        for(int i = 0; i < threshold; i++) {
            reKeyFrags[i] = client[i].reencrypt(alice.getPublicKeyAsHex(), bob.getPublicKeyAsHex());
        }
//        for (int i = 0; i < threshold; i++) {
//            ReKeyFrag reKeyFrag = new ReKeyFrag();
//            reKeyFrag.setPrecurPub(kFrags[i].getPrecurPub());
//            reKeyFrag.setRandom(kFrags[i].getRandom());
//
//            ECPoint rekeyU = ECKey.fromPublicOnly(HexUtil.fromHexString(encryptKey.getPubProofU())).getPubKeyPoint().multiply(new BigInteger(kFrags[i].getValue()));
//            reKeyFrag.setReKeyU(HexUtil.toHexString(rekeyU.getEncoded()));
//
//            ECPoint rekeyR = ECKey.fromPublicOnly(HexUtil.fromHexString(encryptKey.getPubProofR())).getPubKeyPoint().multiply(new BigInteger(kFrags[i].getValue()));
//            reKeyFrag.setReKeyR(HexUtil.toHexString(rekeyR.getEncoded()));
//        }

        // decrypt
        byte[] shareKeyBob;
        try {
            shareKeyBob = PreUtils.AssembleReencryptFragment(bob.getPrivKeyBytes(), reKeyFrags);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (!HexUtil.toHexString(encryptKey.getShareKey()).equals(HexUtil.toHexString(shareKeyBob))) {
            Assert.fail();
        }
        String text = AesUtil.decrypt(cipher, HexUtil.toHexString(shareKeyBob));
        if (!text.equals(content)) {
            Assert.fail();
        }
    }

    public static void main(String args[]) {
        bnEccUtils(3, 2, "0x02005d3a38feaff00f1b83014b2602d7b5b39506ddee7919dd66539b5428358f08");
    }
}
