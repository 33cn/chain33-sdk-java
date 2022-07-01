package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.enums.AddressType;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.CoinsProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import com.google.protobuf.ByteString;

/**
 * @authoer lhl
 * @date 2022/6/24 上午9:57
 */
public class CoinsUtil {
    /**
     * 普通coins主币交易转账
     *
     * @param coinsToken
     * @param amount
     * @param to
     * @param note
     * @param privateKey
     * @param signType
     * @param addressType
     * @param chainID
     * @param paraName
     * @param fee
     * @return
     * @throws Exception
     */
    public static String createTransferTx(String coinsToken, long amount, String to, String note, String privateKey, SignType signType, AddressType addressType, int chainID, String paraName, long fee) throws Exception {
        TransactionAllProtobuf.AssetsTransfer.Builder builder = TransactionAllProtobuf.AssetsTransfer.newBuilder();
        builder.setAmount(amount);
        builder.setTo(to);
        builder.setCointoken(coinsToken);
        builder.setNote(ByteString.copyFrom(note.getBytes()));
        TransactionAllProtobuf.AssetsTransfer assetsTransfer = builder.build();
        CoinsProtobuf.CoinsAction.Builder coinsActionBuilder = CoinsProtobuf.CoinsAction.newBuilder();
        coinsActionBuilder.setTransfer(assetsTransfer);
        coinsActionBuilder.setTy(1);
        CoinsProtobuf.CoinsAction coinsAction = coinsActionBuilder.build();
        TransactionAllProtobuf.Transaction.Builder txBuilder = TransactionAllProtobuf.Transaction.newBuilder();

        txBuilder.setExecer(ByteString.copyFrom((paraName + "coins").getBytes()));
        txBuilder.setFee(fee);
        txBuilder.setNonce(TransactionUtil.getRandomNonce());
        txBuilder.setPayload(ByteString.copyFrom(coinsAction.toByteArray()));
        if (paraName.isEmpty()) {
            txBuilder.setTo(to);
        } else {
            txBuilder.setTo(AddressUtil.getToAddress((paraName + "coins").getBytes(), addressType));
        }
        txBuilder.setChainID(chainID);
        TransactionAllProtobuf.Transaction tx = txBuilder.build();
        if (privateKey.isEmpty()){
            return HexUtil.toHexString(tx.toByteArray());
        }
        TransactionAllProtobuf.Transaction signedProtobufTx = TransactionUtil.signedProtobufTx(tx, privateKey, signType);
        String hexString = HexUtil.toHexString(signedProtobufTx.toByteArray());
        return hexString;
    }

    /**
     * 将coins下主币转移到其他执行器平台上
     *
     * @param coinsToken
     * @param amount
     * @param execName
     * @param to
     * @param note
     * @param privateKey
     * @param signType
     * @param addressType
     * @param chainID
     * @param paraName
     * @param fee
     * @return
     * @throws Exception
     */
    public static String createTransferToExecTx(String coinsToken, long amount, String execName, String to, String note, String privateKey, SignType signType, AddressType addressType, int chainID, String paraName, long fee) {
        TransactionAllProtobuf.AssetsTransferToExec.Builder builder = TransactionAllProtobuf.AssetsTransferToExec.newBuilder();
        builder.setAmount(amount);
        builder.setTo(to);
        builder.setCointoken(coinsToken);
        builder.setExecName(execName);
        builder.setNote(ByteString.copyFrom(note.getBytes()));
        TransactionAllProtobuf.AssetsTransferToExec assetsTransfer = builder.build();
        CoinsProtobuf.CoinsAction.Builder coinsActionBuilder = CoinsProtobuf.CoinsAction.newBuilder();
        coinsActionBuilder.setTransferToExec(assetsTransfer);
        coinsActionBuilder.setTy(10);
        CoinsProtobuf.CoinsAction coinsAction = coinsActionBuilder.build();

        TransactionAllProtobuf.Transaction.Builder txBuilder = TransactionAllProtobuf.Transaction.newBuilder();

        txBuilder.setExecer(ByteString.copyFrom((paraName + "coins").getBytes()));
        txBuilder.setFee(fee);
        txBuilder.setNonce(TransactionUtil.getRandomNonce());
        txBuilder.setPayload(ByteString.copyFrom(coinsAction.toByteArray()));
        if (paraName.isEmpty()) {
            txBuilder.setTo(AddressUtil.getToAddress(execName.getBytes(), addressType));
        } else {
            txBuilder.setTo(AddressUtil.getToAddress((paraName + "coins").getBytes(), addressType));
        }

        txBuilder.setChainID(chainID);
        TransactionAllProtobuf.Transaction tx = txBuilder.build();
        if (privateKey.isEmpty()){
            return HexUtil.toHexString(tx.toByteArray());
        }
        TransactionAllProtobuf.Transaction signedProtobufTx = TransactionUtil.signedProtobufTx(tx, privateKey, signType);
        String hexString = HexUtil.toHexString(signedProtobufTx.toByteArray());
        return hexString;
    }

    /**
     * 从其他执行器平台回提主币到coins下
     *
     * @param coinsToken
     * @param amount
     * @param execName
     * @param to
     * @param note
     * @param privateKey
     * @param signType
     * @param addressType
     * @param chainID
     * @param paraName
     * @param fee
     * @return
     * @throws Exception
     */
    public static String createWithdrawTx(String coinsToken, long amount, String execName, String to, String note, String privateKey, SignType signType, AddressType addressType, int chainID, String paraName, long fee) {
        TransactionAllProtobuf.AssetsWithdraw.Builder builder = TransactionAllProtobuf.AssetsWithdraw.newBuilder();
        builder.setAmount(amount);
        builder.setTo(to);
        builder.setCointoken(coinsToken);
        builder.setExecName(execName);
        builder.setNote(ByteString.copyFrom(note.getBytes()));
        TransactionAllProtobuf.AssetsWithdraw assetsWithdraw = builder.build();
        CoinsProtobuf.CoinsAction.Builder coinsActionBuilder = CoinsProtobuf.CoinsAction.newBuilder();
        coinsActionBuilder.setWithdraw(assetsWithdraw);
        coinsActionBuilder.setTy(3);
        CoinsProtobuf.CoinsAction coinsAction = coinsActionBuilder.build();
        TransactionAllProtobuf.Transaction.Builder txBuilder = TransactionAllProtobuf.Transaction.newBuilder();

        txBuilder.setExecer(ByteString.copyFrom((paraName + "coins").getBytes()));
        txBuilder.setFee(fee);
        txBuilder.setNonce(TransactionUtil.getRandomNonce());
        txBuilder.setPayload(ByteString.copyFrom(coinsAction.toByteArray()));
        if (paraName.isEmpty()) {
            txBuilder.setTo(AddressUtil.getToAddress(execName.getBytes(), addressType));
        } else {
            txBuilder.setTo(AddressUtil.getToAddress((paraName + "coins").getBytes(), addressType));
        }
        txBuilder.setChainID(chainID);
        TransactionAllProtobuf.Transaction tx = txBuilder.build();
        if (privateKey.isEmpty()){
            return HexUtil.toHexString(tx.toByteArray());
        }
        TransactionAllProtobuf.Transaction signedProtobufTx = TransactionUtil.signedProtobufTx(tx, privateKey, signType);
        String hexString = HexUtil.toHexString(signedProtobufTx.toByteArray());
        return hexString;
    }
}
