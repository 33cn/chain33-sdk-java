package cn.chain33.javasdk.model.paillier;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger n;

    private final BigInteger nSquared;

    private final BigInteger g;

    PublicKey(BigInteger n, BigInteger nSquared, BigInteger g) {
        this.n = n;
        this.nSquared = nSquared;
        this.g = g;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getnSquared() {
        return nSquared;
    }

    public BigInteger getG() {
        return g;
    }
}
