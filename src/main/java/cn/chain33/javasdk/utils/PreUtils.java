package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.pre.EncryptKey;
import cn.chain33.javasdk.model.pre.KeyFrag;
import cn.chain33.javasdk.model.pre.ReKeyFrag;
import org.bitcoinj.core.ECKey;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.spongycastle.math.ec.ECPoint;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PreUtils {
    private static final BigInteger baseN = SECNamedCurves.getByName("secp256k1").getN();

    private static final int encKeyLength = 16; // 兼容老版本jdk，使用aes-128

    public static BigInteger hashToModInt(byte[] digest) {
        int orderBits = baseN.bitLength();
        int orderBytes = (orderBits + 7) / 8;

        BigInteger sum;
        if (digest.length > orderBytes) {
            byte[] digest1 = java.util.Arrays.copyOf(digest,orderBytes);
            sum = new BigInteger(digest1);
        } else {
            sum = new BigInteger(digest);
        }
        int excess = digest.length * 8 - orderBits;
        if (excess > 0) {
            sum.shiftRight(excess);
        }

        return sum;
    }

    private static BigInteger[] makeShamirPolyCoeff(int threshold) {
        BigInteger[] coeffs = new BigInteger[threshold - 1];
        for (int i = 0; i < threshold - 1; i++) {
            ECKey key = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());
            coeffs[i] = key.getPrivKey();
        }

        return coeffs;
    }

    private static BigInteger hornerPolyEval(BigInteger[] poly, BigInteger x) {
        BigInteger result = BigInteger.ZERO;
        result = result.add(poly[0]);
        for(int i = 1; i < poly.length; i++) {
            result = result.multiply(x).add(poly[i]);
        }
        result = result.mod(baseN);

        return result;
    }

    private static BigInteger caclPart(BigInteger a, BigInteger b) {
        BigInteger p, res;
        p = a.subtract(b).mod(baseN);
        p = p.modInverse(baseN);

        res = a.multiply(p).mod(baseN);
        return res;
    }

    private static BigInteger calcLambdaCoeff(BigInteger inId, BigInteger[] selectedIds) {
        List<BigInteger> idsList = new ArrayList<>();
        for (int i = 0; i < selectedIds.length; i++) {
            if (inId.compareTo(selectedIds[i]) == 0) {
                continue;
            }
            idsList.add(selectedIds[i]);
        }
        BigInteger[] ids = idsList.toArray(new BigInteger[idsList.size()]);

        if (ids.length == 0) {
            return BigInteger.ONE;
        }

        BigInteger result = caclPart(ids[0], inId);
        if (ids.length > 1) {
            for (int i = 1; i < ids.length; i++) {
                result = result.multiply(caclPart(ids[i], inId)).mod(baseN);
            }
        }

        return result;
    }

    /**
     * 密钥派生函数
     *
     * @param Z
     * @param klen
     *            生成klen字节数长度的密钥
     * @return
     */
    public static byte[] KDF(byte[] Z, int klen) {
        int ct = 1;
        int end = (int) Math.ceil(klen * 1.0 / 32);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            for (int i = 1; i < end; i++) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(Z);
                md.update(HexUtil.intToBytes(ct));
                baos.write(md.digest());
                ct++;
            }
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Z);
            md.update(HexUtil.intToBytes(ct));
            byte[] last = md.digest();
            if (klen % 32 == 0) {
                baos.write(last);
            } else
                baos.write(last, 0, klen % 32);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static EncryptKey GenerateEncryptKey(byte[] pubOwner) {
        ECKey pubOwnerKey = ECKey.fromPublicOnly(pubOwner);

        ECKey priv_r = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());

        ECKey priv_u = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());

        BigInteger sum;
        sum = priv_r.getPrivKey().add(priv_u.getPrivKey()).mod(baseN);

        byte[] shareKey = pubOwnerKey.getPubKeyPoint().multiply(sum).getEncoded();

        byte[] enKey = KDF(shareKey, encKeyLength);
        return new EncryptKey(enKey, priv_r.getPublicKeyAsHex(), priv_u.getPublicKeyAsHex());
    }

    public static KeyFrag[] GenerateKeyFragments(byte[] privOwner, byte[] pubRecipient, int numSplit, int threshold)  throws Exception {
        if (numSplit < 1 || threshold < 1 || numSplit < threshold) {
            throw new Exception("param error");
        }

        ECKey precursor = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());
        ECKey recipient = ECKey.fromPublicOnly(pubRecipient);
        ECKey owner = ECKey.fromPrivate(privOwner);

        byte[] dh_Alice_poit = recipient.getPubKeyPoint().multiply(precursor.getPrivKey()).getEncoded();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(precursor.getPubKeyPoint().getXCoord().getEncoded());
        md.update(recipient.getPubKeyPoint().getXCoord().getEncoded());
        md.update(dh_Alice_poit);

        BigInteger dhAliceBN = hashToModInt(md.digest());

        BigInteger f0;
        f0 = dhAliceBN.modInverse(baseN).multiply(owner.getPrivKey()).mod(baseN);

        KeyFrag[] kFrags = new KeyFrag[numSplit];
        Random random = new Random();
        if (numSplit == 1) {
            BigInteger id = new BigInteger(baseN.bitLength() - 1, random);
            kFrags[0] = new KeyFrag(id.toString(), f0.toString(), precursor.getPublicKeyAsHex());
        } else {
            BigInteger[] coeffs = makeShamirPolyCoeff(threshold);
            List<BigInteger> coeffsList = Arrays.asList(coeffs);
            List<BigInteger> arrList = new ArrayList(coeffsList);
            arrList.add(f0);
            coeffs = arrList.toArray(new BigInteger[arrList.size()]);

            for (int i = 0; i < numSplit; i++) {
                BigInteger id = new BigInteger(baseN.bitLength() - 1, random);
                MessageDigest dShareHash = MessageDigest.getInstance("SHA-256");
                dShareHash.update(precursor.getPubKeyPoint().getXCoord().getEncoded());
                dShareHash.update(recipient.getPubKeyPoint().getXCoord().getEncoded());
                dShareHash.update(dh_Alice_poit);
                dShareHash.update(id.toByteArray());
                BigInteger share = hashToModInt(dShareHash.digest());
                BigInteger rk = hornerPolyEval(coeffs, share);

                kFrags[i] = new KeyFrag(id.toString(), rk.toString(), precursor.getPublicKeyAsHex());
            }
        }

        return kFrags;
    }

    public static byte[] AssembleReencryptFragment(byte[] privRecipient, ReKeyFrag[] reKeyFrags) throws Exception {
        ECKey privRecipientKey = ECKey.fromPrivate(privRecipient);

        if (reKeyFrags.length ==0 || reKeyFrags[0] == null){
            throw new Exception("param error");
        }
        ECKey precursor = ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[0].getPrecurPub()));

        int threshold = reKeyFrags.length;
        byte[] dh_Bob_poit = precursor.getPubKeyPoint().multiply(privRecipientKey.getPrivKey()).getEncoded();
        BigInteger[] ids = new BigInteger[threshold];
        for (int i = 0; i < threshold; i++) {
            MessageDigest xs = MessageDigest.getInstance("SHA-256");
            xs.update(precursor.getPubKeyPoint().getXCoord().getEncoded());
            xs.update(privRecipientKey.getPubKeyPoint().getXCoord().getEncoded());
            xs.update(dh_Bob_poit);
            xs.update(new BigInteger(reKeyFrags[i].getRandom()).toByteArray());
            ids[i] = hashToModInt(xs.digest());
        }

        MessageDigest md1 = MessageDigest.getInstance("SHA-256");
        md1.update(precursor.getPubKeyPoint().getXCoord().getEncoded());
        md1.update(privRecipientKey.getPubKeyPoint().getXCoord().getEncoded());
        md1.update(dh_Bob_poit);
        BigInteger dhBobBN = hashToModInt(md1.digest());

        byte[] shareKeyBob;
        if (reKeyFrags.length == 1) {
            ECPoint efinal = ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[0].getReKeyR())).getPubKeyPoint();
            ECPoint vfinal = ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[0].getReKeyU())).getPubKeyPoint();

            ECPoint re_sum = efinal.add(vfinal);
            shareKeyBob = re_sum.multiply(dhBobBN).getEncoded();
        } else {
            BigInteger lambda = calcLambdaCoeff(ids[0], ids);
            ECPoint efinal = ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[0].getReKeyR())).getPubKeyPoint().multiply(lambda);
            ECPoint vfinal = ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[0].getReKeyU())).getPubKeyPoint().multiply(lambda);
            for (int i = 1; i < threshold; i++) {
                lambda = calcLambdaCoeff(ids[i], ids);

                efinal = efinal.add(ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[i].getReKeyR())).getPubKeyPoint().multiply(lambda));
                vfinal = vfinal.add(ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[i].getReKeyU())).getPubKeyPoint().multiply(lambda));
            }

            shareKeyBob = efinal.add(vfinal).multiply(dhBobBN).getEncoded();
        }

        byte[] enKey = KDF(shareKeyBob, encKeyLength);
        return enKey;
    }

    public static String ECDH(String pub, String priv) {
        ECKey pubKey = ECKey.fromPublicOnly(HexUtil.fromHexString(pub));
        ECKey privKey = ECKey.fromPrivate(HexUtil.fromHexString(priv));

        return HexUtil.toHexString(pubKey.getPubKeyPoint().multiply(privKey.getPrivKey()).getEncoded());
    }
}
