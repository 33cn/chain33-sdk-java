package cn.chain33.javasdk.utils;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bitcoinj.core.listeners.NewBestBlockListener;

public class AesUtil {
    
    /**
     * 
     * @description 按照长度生成key
     * @param length    长度
     * @return  key
     * @throws Exception
     *
     */
    public static byte[] generateDesKey(int length) throws Exception {
        //实例化  
        KeyGenerator kgen = null;
        kgen = KeyGenerator.getInstance("AES");
        //设置密钥长度  
        kgen.init(length);  
        //生成密钥  
        SecretKey skey = kgen.generateKey();  
        //返回密钥的二进制编码  
        return skey.getEncoded();  
    }
    
    public static byte[] generateIv() {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int blockSize = cipher.getBlockSize();
            byte[] ivData = new byte[blockSize];
            SecureRandom rnd = SecureRandom.getInstance("SHA1PRNG");
            rnd.nextBytes(ivData);
            return ivData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] encrypt(String content,byte[] symKeyData,byte[] ivData) {
        final byte[] encodedMessage = content.getBytes(Charset
                .forName("UTF-8"));
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int blockSize = cipher.getBlockSize();

            SecretKeySpec symKey = new SecretKeySpec(symKeyData, "AES");

            IvParameterSpec iv = new IvParameterSpec(ivData);
            cipher.init(Cipher.ENCRYPT_MODE, symKey, iv);

            byte[] encryptedMessage = cipher.doFinal(encodedMessage);

            byte[] ivAndEncryptedMessage = new byte[ivData.length
                    + encryptedMessage.length];
            System.arraycopy(ivData, 0, ivAndEncryptedMessage, 0, blockSize);
            System.arraycopy(encryptedMessage, 0, ivAndEncryptedMessage,
                    blockSize, encryptedMessage.length);

            return ivAndEncryptedMessage;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(
                    "key argument does not contain a valid AES key");
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(
                    "Unexpected exception during encryption", e);
        }
    }

    public static String decrypt(byte[] ivAndEncryptedMessage,
            String symKeyHex) {
        byte[] symKeyData = DatatypeConverter.parseHexBinary(symKeyHex);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int blockSize = cipher.getBlockSize();

            SecretKeySpec symKey = new SecretKeySpec(symKeyData, "AES");

            byte[] ivData = new byte[blockSize];
            System.arraycopy(ivAndEncryptedMessage, 0, ivData, 0, blockSize);
            IvParameterSpec iv = new IvParameterSpec(ivData);

            byte[] encryptedMessage = new byte[ivAndEncryptedMessage.length
                    - blockSize];
            System.arraycopy(ivAndEncryptedMessage, blockSize,
                    encryptedMessage, 0, encryptedMessage.length);

            cipher.init(Cipher.DECRYPT_MODE, symKey, iv);

            byte[] encodedMessage = cipher.doFinal(encryptedMessage);

            String message = new String(encodedMessage,
                    Charset.forName("UTF-8"));
            return message;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(
                    "key argument does not contain a valid AES key");
        } catch (BadPaddingException e) {
            // you'd better know about padding oracle attacks
            return null;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(
                    "Unexpected exception during decryption", e);
        }
    }
    
    public static void main(String[] args) {
        try {
            byte[] key = generateDesKey(128);
            byte[] iv = generateIv();
            //byte[] bytes = "G2F4ED5m123456abx6vDrScs".getBytes();
           // key = bytes;
            //iv = "1KSBd17H7ZK8iT37aJztFB22XGwsPTdwE4".getBytes();
            byte[] encrypt = encrypt("hello world", key, iv);
            String keyStr = HexUtil.toHexString(key);
            String ivStr = HexUtil.toHexString(iv);
            System.out.println("key:" + keyStr);
            System.out.println("iv:" + ivStr);
            System.out.println("encrypt:" + encrypt);
            String decrypt = decrypt(encrypt, keyStr);
            System.out.println("decrypt:" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
