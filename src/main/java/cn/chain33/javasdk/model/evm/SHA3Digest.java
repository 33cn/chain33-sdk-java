package cn.chain33.javasdk.model.evm;

import org.bouncycastle.jcajce.provider.digest.Keccak;

public class SHA3Digest {
    public static byte[] hash(byte[] input, int offset, int length) {
        Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(input, offset, length);
        return kecc.digest();
    }

    public static byte[] hash(byte[] input) {
        return hash(input, 0, input.length);
    }
}
