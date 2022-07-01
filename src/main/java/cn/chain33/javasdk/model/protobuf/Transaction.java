package cn.chain33.javasdk.model.protobuf;

import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.utils.HashUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @authoer lhl
 * @date 2022/6/30 下午8:20
 */
public class Transaction {

    private static final long MAXTXSIZE = 100000;
    private TransactionAllProtobuf.Transaction tx;

    public Transaction(TransactionAllProtobuf.Transaction tx) {
        this.tx = tx;
    }

    public Transaction(String hexTx) throws Exception {
        try {
            this.tx = TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(hexTx));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sign(SignType signType, String privateKey) {
        this.tx = TransactionUtil.signedProtobufTx(tx, privateKey, signType);
    }

    public TransactionAllProtobuf.Transaction getTx() {
        return this.tx;
    }

    public String hexString() {
        return HexUtil.toHexString(tx.toByteArray());
    }

    public byte[] hash() throws Exception {
        return HashUtil.sha256(clone().getTx().toBuilder().clearSignature().clearHeader().build().toByteArray());
    }

    @Override
    public Transaction clone() throws CloneNotSupportedException {
        try {
            return new Transaction(TransactionAllProtobuf.Transaction.parseFrom(this.tx.toByteArray()));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

    //暂时只支持设置时间
    public void setExpire(long expire) {
        this.tx=tx.toBuilder().setExpire(TransactionUtil.getExpire(expire)).build();
    }

    public void setGroupCount(int groupCount){
        this.tx=tx.toBuilder().setGroupCount(groupCount).build();
    }

    public void setHeader(byte[] header){
        this.tx=tx.toBuilder().setHeader(ByteString.copyFrom(header)).build();
    }

    public void setNext(byte[] next){
        this.tx=tx.toBuilder().setNext(ByteString.copyFrom(next)).build();
    }

    public byte[]getExecer(){
        return tx.getExecer().toByteArray();
    }
    public Transactions toTransactions() throws Exception {
        if (tx.getGroupCount() < 0 || tx.getGroupCount() == 1 || tx.getGroupCount() > 20) {
            throw new Exception("ErrTxGroupCount");
        }
        if (tx.getGroupCount() > 0) {
            return new Transactions(TransactionAllProtobuf.Transactions.parseFrom(tx.getHeader()));
        }
        if (!tx.getNext().isEmpty() || !tx.getHeader().isEmpty()) {
            throw new Exception("ErrNomalTx");
        }
        return null;
    }

    public int size(){
       return tx.toByteArray().length;
    }
    public long getFee(){
        return tx.getFee();
    }
    //获取真实fee
    public long getRealFee(long feeRate) throws Exception{
        int txSize = tx.toByteArray().length;
        //如果签名为空，那么加上签名的空间
        if (tx.getSignature() == null) {
            txSize += 300;
        }
        if (size() > MAXTXSIZE) {
            throw new Exception("ErrTxMsgSizeTooBig");
        }
        // 检查交易费是否小于最低值
        long realFee = (txSize / 1000 + 1) * feeRate;
        return realFee;
    }

    public void setFee(long fee){
        this.tx=tx.toBuilder().setFee(fee).build();
    }
}
