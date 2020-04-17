package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.pre.EncryptKey;
import cn.chain33.javasdk.model.pre.KeyFrag;
import cn.chain33.javasdk.model.pre.ReKeyFrag;
import org.bitcoinj.core.ECKey;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.spongycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PreUtils {
    private static final BigInteger baseN = SECNamedCurves.getByName("secp256k1").getN();

    private static final int encKeyLength = 32;

    private static BigInteger hashToModInt(byte[] digest) {
        BigInteger sum = new BigInteger(digest);
        BigInteger order_minus_1 = new BigInteger(baseN.toByteArray());
        order_minus_1.subtract(BigInteger.ONE);

        sum.mod(order_minus_1).add(BigInteger.ONE);

        return sum;
    }

    private static BigInteger[] makeShamirPolyCoeff(int threshold) {
        BigInteger[] coeffs = new BigInteger[threshold - 1];
        for (int i = 0; i < threshold - 1; i++) {
            ECKey key = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());
            coeffs[0] = key.getPrivKey();
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

    public static EncryptKey GenerateEncryptKey(byte[] pubOwner) {
        ECKey pubOwnerKey = ECKey.fromPublicOnly(pubOwner);

        ECKey priv_r = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());

        ECKey priv_u = ECKey.fromPrivate(TransactionUtil.generatorPrivateKey());

        BigInteger sum;
        sum = priv_r.getPrivKey().add(priv_u.getPrivKey()).mod(baseN);

        byte[] shareKey = pubOwnerKey.getPubKeyPoint().multiply(sum).getEncoded();

        byte[] enKey = new byte[encKeyLength];
        for(int i = 0; i < encKeyLength; i++) {
                enKey[i] = shareKey[i];
        }
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
        MessageDigest md = new Blake2b.Blake2b256();
        md.update(precursor.getPubKey());
        md.update(pubRecipient);
        md.update(dh_Alice_poit);

        BigInteger dhAliceBN = hashToModInt(md.digest());
        //System.out.println("dhAliceBN:"+dhAliceBN);

        BigInteger f0;
        f0 = dhAliceBN.modInverse(baseN).multiply(owner.getPrivKey()).mod(baseN);

        KeyFrag[] kFrags = new KeyFrag[numSplit];
        Random random = new Random();
        if (numSplit == 1) {
            BigInteger id = new BigInteger(baseN.bitLength(), random);
            kFrags[0] = new KeyFrag(id.toString(), f0.toString(), precursor.getPublicKeyAsHex());
        } else {
            BigInteger[] coeffs = makeShamirPolyCoeff(threshold);
            List<BigInteger> coeffsList = Arrays.asList(coeffs);
            List<BigInteger> arrList = new ArrayList(coeffsList);
            arrList.add(f0);
            coeffs = arrList.toArray(new BigInteger[arrList.size()]);

            for (int i = 0; i < numSplit; i++) {
                BigInteger id = new BigInteger(baseN.bitLength(), random);
                MessageDigest dShareHash = new Blake2b.Blake2b256();
                dShareHash.update(precursor.getPubKey());
                dShareHash.update(pubRecipient);
                dShareHash.update(dh_Alice_poit);
                dShareHash.update(id.toByteArray());
                BigInteger share = hashToModInt(dShareHash.digest());
                BigInteger rk = hornerPolyEval(coeffs, share);

                kFrags[i] = new KeyFrag(id.toString(), rk.toString(), precursor.getPublicKeyAsHex());
            }
        }

        return kFrags;
    }

    public static byte[] AssembleReencryptFragment(byte[] privRecipient, ReKeyFrag[] reKeyFrags) {
        ECKey privRecipientKey = ECKey.fromPrivate(privRecipient);
        ECKey precursor = ECKey.fromPublicOnly(HexUtil.fromHexString(reKeyFrags[0].getPrecurPub()));

        int threshold = reKeyFrags.length;
        byte[] dh_Bob_poit = precursor.getPubKeyPoint().multiply(privRecipientKey.getPrivKey()).getEncoded();
        BigInteger[] ids = new BigInteger[threshold];
        for (int i = 0; i < threshold; i++) {
            MessageDigest xs = new Blake2b.Blake2b256();
            xs.update(precursor.getPubKey());
            xs.update(privRecipientKey.getPubKey());
            xs.update(dh_Bob_poit);
            xs.update(new BigInteger(reKeyFrags[i].getRandom()).toByteArray());
            ids[i] = hashToModInt(xs.digest());
        }

        MessageDigest md1 = new Blake2b.Blake2b256();
        md1.update(precursor.getPubKey());
        md1.update(privRecipientKey.getPubKey());
        md1.update(dh_Bob_poit);
        BigInteger dhBobBN = hashToModInt(md1.digest());
        //System.out.println("dhBobBN:  " + dhBobBN);

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

        byte[] enKey = new byte[encKeyLength];
        for(int i = 0; i < encKeyLength; i++) {
            enKey[i] = shareKeyBob[i];
        }

        return enKey;
    }
}
