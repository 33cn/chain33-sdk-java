package cn.chain33.javasdk.gmtest;

import cn.chain33.javasdk.model.gm.*;
import cn.chain33.javasdk.utils.TransactionUtil;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class GMTest {
    public static final byte[] SRC_DATA = new byte[]{0x1, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef, (byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98, 0x76, 0x54, 0x32, 0x10};
    public static final String SRC_DATA_24B = "123456781234567812345678";
    public static final String WITH_ID = "1234";

    @Test
    public void testSM2SignAndVerify() {
        try {
            SM2KeyPair keyPair = SM2Util.generateKeyPair();

            byte[] sign = SM2Util.sign(SRC_DATA, WITH_ID, keyPair);
            System.out.println("SM2 sign with withId result:\n" + ByteUtils.toHexString(sign));
            boolean flag = SM2Util.verify(SRC_DATA, sign, WITH_ID, keyPair.getPublicKey());
            if (!flag) {
                Assert.fail("verify failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testSM2EncryptAndDecrypt() {
        try {
            SM2KeyPair keyPair = SM2Util.generateKeyPair();

            byte[] encryptedData = SM2Util.encrypt(SRC_DATA_24B, keyPair.getPublicKey());
            System.out.println("SM2 encrypt result:\n" + ByteUtils.toHexString(encryptedData));
            String decryptedData = SM2Util.decrypt(encryptedData, keyPair.getPrivateKey());
            System.out.println("SM2 decrypt result:\n" + decryptedData);
            if (!decryptedData.equals(SRC_DATA_24B)) {
                Assert.fail();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testSM2KeyExchange() {
        System.out.println("-----------------密钥协商-----------------");
        String aID = "AAAAAAAAAAAAA";
        SM2KeyPair aKeyPair = SM2Util.generateKeyPair();
        SM2Util.KeyExchange initiator = new SM2Util.KeyExchange(aID, aKeyPair);

        String bID = "BBBBBBBBBBBBB";
        SM2KeyPair bKeyPair = SM2Util.generateKeyPair();
        SM2Util.KeyExchange responder = new SM2Util.KeyExchange(bID, bKeyPair);

        SM2Util.TransportEntity entity1 = initiator.InitiatorInit();

        SM2Util.TransportEntity entity2 = responder.ResponderExchange(entity1);

        SM2Util.TransportEntity entity3 = initiator.InitiatorExchange(entity2);

        boolean result = responder.ResponderValidate(entity3);
        if (!result) {
            Assert.fail();
        }

    }

    @Test
    public void testSM3() {
        try {
            byte[] hash = SM3Util.hash(SRC_DATA);
            System.out.println("SM3 hash result:\n" + ByteUtils.toHexString(hash));
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testSM4() {
        try {
            byte[] key;
            byte[] iv = SM4Util.generateKey();
            byte[] cipherText;
            byte[] decryptedData;

            key = new byte[]{0x1, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef, (byte)0xfe, (byte)0xdc, (byte)0xba, (byte)0x98, 0x76, 0x54, 0x32, 0x10};
            cipherText = SM4Util.encryptECB(key, SRC_DATA);
            System.out.println("SM4 ECB Padding encrypt result:\n" + Arrays.toString(cipherText));
            decryptedData = SM4Util.decryptECB(key, cipherText);
            System.out.println("SM4 ECB Padding decrypt result:\n" + Arrays.toString(decryptedData));
            if (!Arrays.equals(decryptedData, SRC_DATA)) {
                Assert.fail();
            }

            cipherText = SM4Util.encryptCBC(key, iv, SRC_DATA);
            System.out.println("SM4 CBC Padding encrypt result:\n" + Arrays.toString(cipherText));
            decryptedData = SM4Util.decryptCBC(key, iv, cipherText);
            System.out.println("SM4 CBC Padding decrypt result:\n" + Arrays.toString(decryptedData));
            if (!Arrays.equals(decryptedData, SRC_DATA)) {
                Assert.fail();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
