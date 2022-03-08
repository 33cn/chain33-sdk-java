package cn.chain33.javasdk.model.gm;

import java.math.BigInteger;

import cn.chain33.javasdk.utils.HexUtil;
import org.bouncycastle.math.ec.ECPoint;

/**
 * SM2密钥对Bean
 * 
 * @author Potato
 *
 */
public class SM2KeyPair {

    private final ECPoint publicKey;
    private final BigInteger privateKey;

    SM2KeyPair(ECPoint publicKey, BigInteger privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public ECPoint getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public String getPublicKeyString() {
        return HexUtil.toHexString(publicKey.getEncoded(true));
    }

    public String getPrivateKeyString() {
        return HexUtil.toHexString(privateKey.toByteArray());
    }
}
