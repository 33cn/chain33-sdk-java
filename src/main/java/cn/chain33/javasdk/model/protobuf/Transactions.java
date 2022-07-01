package cn.chain33.javasdk.model.protobuf;

import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/7/1 上午8:30
 */
public class Transactions {
    private TransactionAllProtobuf.Transactions txs;

    //提供一些常用的构造函数
    public Transactions(TransactionAllProtobuf.Transactions txs) throws Exception {
        if (txs.getTxsCount() > 20 || txs.getTxsCount() <= 1) {
            throw new Exception("ErrTxGroupCount");
        }
        this.txs = txs;
    }
    //使用时需要重构交易组
    public Transactions(TransactionAllProtobuf.Transaction... txs) throws Exception {
        if (txs.length > 20 || txs.length <= 1) {
            throw new Exception("ErrTxGroupCount");

        }
        TransactionAllProtobuf.Transactions.Builder builder = TransactionAllProtobuf.Transactions.newBuilder();
        for (int i = 0; i < txs.length; i++) {
            builder.addTxs(txs[i]);
        }
        this.txs = builder.build();
    }
    //使用时需要重构交易组
    public Transactions(String... txs) throws Exception {
        if (txs.length > 20 || txs.length <= 1) {
            throw new Exception("ErrTxGroupCount");
        }
        TransactionAllProtobuf.Transactions.Builder builder = TransactionAllProtobuf.Transactions.newBuilder();
        for (int i = 0; i < txs.length; i++) {
            builder.addTxs(TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(txs[i])));
        }
        this.txs = builder.build();
    }
   //重新构建交易组
    public void reBuildGroup(long feeRate) throws Exception {
        TransactionAllProtobuf.Transactions.Builder builder = TransactionAllProtobuf.Transactions.newBuilder();
        List<TransactionAllProtobuf.Transaction.Builder> builderList = new ArrayList<TransactionAllProtobuf.Transaction.Builder>();
        for (int i = 0; i < txs.getTxsCount(); i++) {
            builderList.add(txs.getTxs(i).toBuilder());
        }
        long totalFee = 0;
        long minFee = 0;
        byte[] header = new byte[0];
        try {
            header = TransactionUtil.getTxHash(txs.getTxs(0));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        for (int i = txs.getTxsCount() - 1; i >= 0; i--) {
            builderList.get(i).setGroupCount(txs.getTxsCount());
            totalFee += txs.getTxs(i).getFee();
            builderList.get(i).clearSignature().setHeader(ByteString.copyFrom(header));
            if (i == 0) {
                builderList.get(i).setFee(1 << 62);
            } else {
                builderList.get(i).setFee(0);
            }
            long realFee = TransactionUtil.getRealFee(builderList.get(i).build(), feeRate);
            minFee += realFee;
            if (i == 0) {
                if (totalFee < minFee) {
                    totalFee = minFee;
                }
                builderList.get(i).setFee(totalFee);
                header = TransactionUtil.getTxHash(builderList.get(i).build());
            } else {
                builderList.get(i).setFee(0);
                builderList.get(i - 1).setNext(ByteString.copyFrom(TransactionUtil.getTxHash(builderList.get(i).build())));
            }

        }
        for (int i = 0; i < txs.getTxsCount(); i++) {
            builderList.get(i).setHeader(ByteString.copyFrom(header));
        }
        for (int i = 0; i < txs.getTxsCount(); i++) {
            builder.addTxs(builderList.get(i).build());
        }
        this.txs = builder.build();
    }

    public TransactionAllProtobuf.Transactions getTxs() {
        return this.txs;
    }

    /**
     * 对交易组中交易签名
     * @param n  当n小于0时表示对所有交易签名
     * @param signType
     * @param privateKey
     * @throws Exception
     */
    public void signN(int n, SignType signType, String privateKey) throws Exception {
        if (n >= getTxs().getTxsCount()) {
            throw new Exception("ErrIndex");
        }
        if (n<0){
            for (int i=0;i<txs.getTxsCount();i++){
                Transaction tx = new Transaction(txs.getTxs(i));
                tx.sign(signType, privateKey);
                this.txs = txs.toBuilder().setTxs(i, tx.getTx()).build();
            }
            return;
        }
        Transaction tx = new Transaction(txs.getTxs(n));
        tx.sign(signType, privateKey);
        this.txs = txs.toBuilder().setTxs(n, tx.getTx()).build();
    }

    public Transaction toTransaction() throws Exception {
        if (getTxs().getTxsCount() < 2) {
            throw new Exception("ErrInvalidParam");
        }
        //利用序列化进行深拷贝
        TransactionAllProtobuf.Transaction copytx = TransactionAllProtobuf.Transaction.parseFrom(txs.getTxs(0).toByteArray());
        //放到header中不影响交易的Hash
        return new Transaction(copytx.toBuilder().setHeader(ByteString.copyFrom(txs.toByteArray())).build());
    }

}
