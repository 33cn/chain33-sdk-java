package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.Address;
import cn.chain33.javasdk.model.Signature;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.RawTransactionProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionProtoBuf;
import com.google.protobuf.ByteString;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;

public class TransactionGrpcUtil {


    /**
     *
     * @description 签名
     * @param tx
     * @param privateKey
     * @return
     *
     * @create 2020年1月9日 下午6:35:16
     */
    public static TransactionAllProtobuf.Transaction signProbuf(TransactionAllProtobuf.Transaction tx, String privateKey) {
        TransactionAllProtobuf.Transaction encodeTx = getSignProbuf(tx);
        byte[] protobufData = encodeTx.toByteArray();
        byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
        Signature btcCoinSign = btcCoinSign(protobufData, privateKeyBytes);
        TransactionAllProtobuf.Transaction.Builder builder = tx.toBuilder();
        TransactionAllProtobuf.Signature.Builder signatureBuilder = TransactionAllProtobuf.Signature.newBuilder();
        signatureBuilder.setPubkey(ByteString.copyFrom(btcCoinSign.getPubkey()));
        signatureBuilder.setTy(btcCoinSign.getTy());
        signatureBuilder.setSignature(ByteString.copyFrom(btcCoinSign.getSignature())); // 序列化
        TransactionAllProtobuf.Transaction.Builder setSignature = builder.setSignature(signatureBuilder.build());
        return setSignature.build();
    }

    /**
     *
     * @description 获取签名需要的protobuf
     * @param tx
     * @return
     *
     * @author lgang
     * @create 2020年1月9日 下午6:35:30
     */
    public static TransactionAllProtobuf.Transaction getSignProbuf(TransactionAllProtobuf.Transaction tx) {
        TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();
        builder.setExecer(tx.getExecer());
        builder.setExpire(tx.getExpire());
        builder.setFee(tx.getFee());
        builder.setNonce(tx.getNonce());
        builder.setPayload(tx.getPayload());
        builder.setTo(tx.getTo());
        if (tx.getNext() != null) {
            builder.setNext(tx.getNext());
        }
        if (tx.getHeader() != null) {
            builder.setHeader(tx.getHeader());
        }
        if (tx.getGroupCount() != 0) {
            builder.setGroupCount(tx.getGroupCount());
        }
        TransactionAllProtobuf.Transaction build = builder.build();
        return build;
    }


    private static RawTransactionProtobuf.Signature signRawTx(byte[] data, byte[] privateKey,
                                                              RawTransactionProtobuf.Transaction.Builder txBuilder) {
        Signature btcCoinSign = btcCoinSign(data, privateKey);
        RawTransactionProtobuf.Signature.Builder signatureBuilder = RawTransactionProtobuf.Signature.newBuilder();
        signatureBuilder.setPubkey(ByteString.copyFrom(btcCoinSign.getPubkey()));
        signatureBuilder.setTy(btcCoinSign.getTy());
        signatureBuilder.setSignature(ByteString.copyFrom(btcCoinSign.getSignature()));
        RawTransactionProtobuf.Signature signatureProtuBuff = signatureBuilder.build();
        return signatureProtuBuff;
    }

    public static String addressToString(Address address) {
        if (StringUtil.isEmpty(address.getEnc58Str())) {
            byte[] ad = new byte[25];
            ad[0] = address.getVersion();
            for (int i = 1; i < 21; i++) {
                ad[i] = address.getHash160()[i - 1];
            }
            byte[] checkSum = getAddressSh(ad);
            address.setCheckSum(checkSum);
            for (int i = 21, j = 0; i < 25; i++, j++) {
                ad[i] = checkSum[j];
            }
            String Enc58Str = Base58Util.encode(ad);
            address.setEnc58Str(Enc58Str);
        }
        return address.getEnc58Str();
    }

    /**
     * @description 数据处理,sha256 2次
     *
     * @param sourceByte
     */
    private static byte[] getAddressSh(byte[] sourceByte) {
        byte[] subByteArr = TransactionUtil.subByteArr(sourceByte, 0, 21);
        byte[] sha256_1 = TransactionUtil.Sha256(subByteArr);
        byte[] sha256_2 = TransactionUtil.Sha256(sha256_1);
        return sha256_2;
    }

    public static byte[] ripemd160(byte[] sourceByte) {
        byte[] hash = Ripemd160Util.getHash(sourceByte);
        return hash;
    }

    private static Signature btcCoinSign(byte[] data, byte[] privateKey) {
        byte[] sha256 = TransactionUtil.Sha256(data);
        Sha256Hash sha256Hash = Sha256Hash.wrap(sha256);
        ECKey ecKey = ECKey.fromPrivate(privateKey);
        ECKey.ECDSASignature ecdsas = ecKey.sign(sha256Hash);
        byte[] signByte = ecdsas.encodeToDER();
        Signature signature = new Signature();
        signature.setPubkey(ecKey.getPubKey());
        signature.setSignature(signByte);
        signature.setTy(SignType.SECP256K1.getType());
        return signature;
    }

}
