package cn.chain33.javasdk.model.gm;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * SM4对称加密算法
 */
public class SM4Util {
    public static final String ALGORITHM_NAME = "SM4";
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";
    public static final String ALGORITHM_NAME_CBC_PADDING = "SM4/CBC/PKCS5Padding";

    /**
     * SM4算法密钥长度128位（即密钥16字节）
     */
    public static final int DEFAULT_KEY_SIZE = 128;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 生成SM4密钥，默认128位
     * 
     * @return 密钥
     */
    public static byte[] generateKey() {
        return generateKey(DEFAULT_KEY_SIZE);
    }

    public static byte[] generateKey(int keySize) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
            kg.init(keySize, new SecureRandom());
            return kg.generateKey().getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ECB模式加密
     * 
     * @param key
     *            加密秘钥
     * @param data
     *            明文
     * 
     * @return 密文
     * 
     * @throws InvalidKeyException
     */
    public static byte[] encryptECB(byte[] key, byte[] data) throws InvalidKeyException {
        try {
            Cipher cipher = generateECBCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ECB模式解密
     * 
     * @param key
     *            解密密钥
     * @param cipherText
     *            密文
     * 
     * @return 明文
     * 
     * @throws InvalidKeyException
     */
    public static byte[] decryptECB(byte[] key, byte[] cipherText) throws InvalidKeyException {
        try {
            Cipher cipher = generateECBCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(cipherText);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * CBC模式加密
     * 
     * @param key
     *            加密密钥
     * @param iv
     *            初始化向量
     * @param data
     *            明文
     * 
     * @return 密文
     * 
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] encryptCBC(byte[] key, byte[] iv, byte[] data)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Cipher cipher = generateCBCCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * CBC模式解密
     * 
     * @param key
     *            解密密钥
     * @param iv
     *            初始化向量
     * @param cipherText
     *            密文
     * 
     * @return 明文
     * 
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] decryptCBC(byte[] key, byte[] iv, byte[] cipherText)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            Cipher cipher = generateCBCCipher(ALGORITHM_NAME_CBC_PADDING, Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(cipherText);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Cipher generateECBCipher(String algorithmName, int mode, byte[] key)
            throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    private static Cipher generateCBCCipher(String algorithmName, int mode, byte[] key, byte[] iv)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(mode, sm4Key, ivParameterSpec);
        return cipher;
    }
}
