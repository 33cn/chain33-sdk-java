package cn.chain33.javasdk.model.paillier;

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger lambda;

    private final BigInteger n;

    private final BigInteger nSquared;

    private final BigInteger mu;

    PrivateKey(BigInteger lambda, BigInteger n, BigInteger nSquared, BigInteger mu) {
        this.lambda = lambda;
        this.n = n;
        this.nSquared = nSquared;
        this.mu = mu;
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getnSquared() {
        return nSquared;
    }

    public BigInteger getMu() {
        return mu;
    }
}
