package cn.chain33.javasdk.model.paillier;

import cn.chain33.javasdk.utils.HexUtil;

import java.math.BigInteger;
import java.util.Random;

public class PaillierCipher {

    public static String encrypt(BigInteger m, PublicKey publicKey) {
        return HexUtil.toHexString(encryptAsBytes(m, publicKey));
    }

    public static byte[] encryptAsBytes(BigInteger m, PublicKey publicKey) {
        BigInteger n = publicKey.getN();
        BigInteger random;
        do {
            random = new BigInteger(n.bitLength(), new Random());
        } while (random.signum() != 1);

        if (m.signum() == -1) {
            m = m.mod(n);
        }

        BigInteger nsquare = publicKey.getnSquared();
        BigInteger ciphertext =
                publicKey.getG().modPow(m, nsquare).multiply(random.modPow(n, nsquare)).mod(nsquare);

        byte[] nBytes = BytesUtils.asUnsignedByteArray(n);
        byte[] nLenBytes = BytesUtils.unsignedShortToByte2(nBytes.length);
        byte[] cipherBytes = BytesUtils.asUnsignedByteArray(ciphertext, n.bitLength() / 4);
        byte[] data = new byte[nLenBytes.length + nBytes.length + cipherBytes.length];
        System.arraycopy(nLenBytes, 0, data, 0, nLenBytes.length);
        System.arraycopy(nBytes, 0, data, nLenBytes.length, nBytes.length);
        System.arraycopy(
                cipherBytes, 0, data, nLenBytes.length + nBytes.length, cipherBytes.length);
        return data;
    }

    public static BigInteger decrypt(String ciphertext, PrivateKey privateKey) {
        return decrypt(HexUtil.fromHexString(ciphertext), privateKey);
    }

    public static BigInteger decrypt(byte[] ciphertext, PrivateKey privateKey) {
        BigInteger n = privateKey.getN();
        BigInteger lambda = privateKey.getLambda();

        int nLen = BytesUtils.byte2ToUnsignedShort(ciphertext);
        byte[] nBytes = new byte[nLen];
        System.arraycopy(ciphertext, 2, nBytes, 0, nLen);
        BigInteger n1 = BytesUtils.fromUnsignedByteArray(nBytes);
        if (n1.compareTo(n) != 0) {
            System.err.println("Invalid ciphertext, cannot match n parameter");
            return null;
        }

        byte[] data = new byte[ciphertext.length - nLen - 2];
        System.arraycopy(ciphertext, 2 + nLen, data, 0, ciphertext.length - nLen - 2);
        BigInteger intCiphertext = BytesUtils.fromUnsignedByteArray(data);

        BigInteger message =
                intCiphertext
                        .modPow(lambda, privateKey.getnSquared())
                        .subtract(BigInteger.ONE)
                        .divide(n)
                        .multiply(privateKey.getMu())
                        .mod(n);
        BigInteger maxValue = BigInteger.ONE.shiftLeft(n.bitLength() / 2);
        if (message.compareTo(maxValue) > 0) {
            return message.subtract(n);
        } else {
            return message;
        }
    }

    public static String ciphertextAdd(String ciphertext1, String ciphertext2) {
        byte[] data = ciphertextAdd(HexUtil.fromHexString(ciphertext1), HexUtil.fromHexString(ciphertext2));
        if (data == null) {
           System.err.println("Ciphertext add error");
           return null;
        }
        return HexUtil.toHexString(data);
    }

    public static byte[] ciphertextAdd(byte[] ciphertext1, byte[] ciphertext2) {
        int nLen1 = BytesUtils.byte2ToUnsignedShort(ciphertext1);
        byte[] nBytes1 = new byte[nLen1];
        System.arraycopy(ciphertext1, 2, nBytes1, 0, nLen1);
        BigInteger n1 = BytesUtils.fromUnsignedByteArray(nBytes1);
        byte[] data1 = new byte[ciphertext1.length - nLen1 - 2];
        System.arraycopy(ciphertext1, 2 + nLen1, data1, 0, ciphertext1.length - nLen1 - 2);

        int nLen2 = BytesUtils.byte2ToUnsignedShort(ciphertext2);
        byte[] nBytes2 = new byte[nLen2];
        System.arraycopy(ciphertext2, 2, nBytes2, 0, nLen2);
        BigInteger n2 = BytesUtils.fromUnsignedByteArray(nBytes2);
        if (n2.compareTo(n1) != 0) {
            System.err.println("ciphertext1 cannot match ciphertext2");
            return null;
        }

        byte[] data2 = new byte[ciphertext2.length - nLen2 - 2];
        System.arraycopy(ciphertext2, 2 + nLen2, data2, 0, ciphertext2.length - nLen2 - 2);

        BigInteger ct1 = BytesUtils.fromUnsignedByteArray(data1);
        BigInteger ct2 = BytesUtils.fromUnsignedByteArray(data2);
        BigInteger nsquare = n1.multiply(n1);
        BigInteger ct = ct1.multiply(ct2).mod(nsquare);

        byte[] nLenBytes = BytesUtils.unsignedShortToByte2(nBytes1.length);
        byte[] cipherBytes = BytesUtils.asUnsignedByteArray(ct, n1.bitLength() / 4);
        byte[] data = new byte[nLenBytes.length + nBytes1.length + cipherBytes.length];
        System.arraycopy(nLenBytes, 0, data, 0, nLenBytes.length);
        System.arraycopy(nBytes1, 0, data, nLenBytes.length, nBytes1.length);
        System.arraycopy(
                cipherBytes, 0, data, nLenBytes.length + nBytes1.length, cipherBytes.length);
        return data;
    }
}
