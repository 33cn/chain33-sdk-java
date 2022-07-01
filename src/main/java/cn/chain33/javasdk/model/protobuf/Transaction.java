package cn.chain33.javasdk.model.protobuf;

import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.utils.HashUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @authoer lhl
 * @date 2022/6/30 下午8:20
 */
public class Transaction {
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

    public TransactionAllProtobuf.Transaction sign(SignType signType, String privateKey) {
        this.tx = TransactionUtil.signedProtobufTx(this.tx, privateKey, signType);
        return getTx();
    }

    public TransactionAllProtobuf.Transaction getTx() {
        return this.tx;
    }

    public String hexString() {
        return HexUtil.toHexString(this.tx.toByteArray());
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
        this.tx = getTx().toBuilder().setExpire(TransactionUtil.getExpire(expire)).build();
    }

    public Transactions toTransactions() throws Exception {
        if (getTx().getGroupCount() < 0 || getTx().getGroupCount() == 1 || getTx().getGroupCount() > 20) {
            throw new Exception("ErrTxGroupCount");
        }
        if (getTx().getGroupCount() > 0) {
            return new Transactions(TransactionAllProtobuf.Transactions.parseFrom(getTx().getHeader()));
        }
        if (!getTx().getNext().isEmpty() || !getTx().getHeader().isEmpty()) {
            throw new Exception("ErrNomalTx");
        }
        return null;
    }
}
