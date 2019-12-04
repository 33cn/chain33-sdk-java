package cn.chain33.javasdk.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtil {
    
    /**
     * 
     * @description encrypt
     * @param content
     * @param password
     * @return
     */
    public static byte[] encrypt(byte[] content, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return cipher.doFinal(content);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @description decrypt
     * @param src
     * @param password
     * @return
     */
    public static byte[] decrypt(byte[] contentEncrypt, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        return cipher.doFinal(contentEncrypt);
    }

    // 测试
    public static void main(String args[]) {
        // 待加密内容
        String str = "测试内容";
        // 密码，长度要是8的倍数
        String password = "11111111";
        
        System.out.println("content:" + str);
        System.out.println("password:" + password);
        byte[] result = encrypt(str.getBytes(), password);
        System.out.println("des encrypt hex string：" + HexUtil.toHexString(result));
        // 直接将如上内容解密
        try {
            byte[] decryResult = decrypt(result, password);
            System.out.println("des decrypt：" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
