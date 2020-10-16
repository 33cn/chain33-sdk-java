package cn.chain33.javasdk.model.paillier;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class PaillierKeyPair {
    public PublicKey pubKey;

    public PrivateKey privKey;

    PaillierKeyPair(PublicKey pubKey, PrivateKey privKey) {
        this.pubKey = pubKey;
        this.privKey = privKey;
    }

    public static PaillierKeyPair generateGoodKeyPair() {
        return generateKeyPair(2048);
    }

    public static PaillierKeyPair generateStrongKeyPair() {
        return generateKeyPair(4096);
    }

    public static PaillierKeyPair generateKeyPair(int len) {
        Random rng = new SecureRandom();

        BigInteger p, q;
        int length = len / 2;
        p = BigInteger.probablePrime(length, rng);
        q = BigInteger.probablePrime(length, rng);

        BigInteger n = p.multiply(q);
        BigInteger nSquared = n.multiply(n);

        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger qMinusOne = q.subtract(BigInteger.ONE);

        BigInteger lambda = pMinusOne.multiply(qMinusOne);

        BigInteger g = n.add(BigInteger.ONE);;

        BigInteger mu = lambda.modInverse(n);
        PublicKey publicKey = new PublicKey(n, nSquared, g);
        PrivateKey privateKey = new PrivateKey(lambda, n, nSquared, mu);

        return new PaillierKeyPair(publicKey, privateKey);
    }

}
