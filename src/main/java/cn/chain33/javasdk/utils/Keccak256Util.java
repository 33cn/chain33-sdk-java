package cn.chain33.javasdk.utils;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.digests.KeccakDigest;

/**
 * @authoer lhl
 * @date 2022/6/7 上午10:17
 */
public class Keccak256Util {

    private static final int keccak256_DIGEST_LENGTH = 32;

    public static byte[] keccak256(byte[] bytes) {
        return keccak256(bytes, 0, bytes.length);
    }

    public static byte[] keccak256(byte[] bytes, int offset, int size) {
        KeccakDigest keccakDigest = new KeccakDigest(256);
        keccakDigest.update(bytes, offset, size);
        byte[] keccak256 = new byte[keccak256_DIGEST_LENGTH];
        keccakDigest.doFinal(keccak256, offset);
        return keccak256;
    }

    public static byte[] nameHash(String name) {
        String[] labels = StringUtils.split(name, '.');

        byte[] pre = new byte[32];
        if (labels.length > 0) {
            for (int i = labels.length - 1; i >= 0; i--) {
                byte[] label = keccak256(labels[i].getBytes());

                KeccakDigest keccakDigest = new KeccakDigest(256);
                keccakDigest.update(pre, 0, pre.length);
                keccakDigest.update(label, 0, label.length);
                byte[] keccak256 = new byte[keccak256_DIGEST_LENGTH];
                keccakDigest.doFinal(keccak256, 0);

                pre = keccak256;
            }
            return pre;
        }

        return null;
    }

}
