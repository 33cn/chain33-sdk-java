package cn.chain33.javasdk.model.protobuf;

import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.utils.HexUtil;
import com.google.protobuf.ByteString;

import java.util.ArrayList;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/7/1 上午8:30
 */
public class Transactions {
    private List<Transaction> list;

    //提供一些常用的构造函数
    public Transactions(TransactionAllProtobuf.Transactions txs) throws Exception {
        if (txs.getTxsCount() > 20 || txs.getTxsCount() <= 1) {
            throw new Exception("ErrTxGroupCount");
        }
        ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
        boolean isPara = false;
        boolean isMain = false;
        for (int i = 0; i < txs.getTxsCount(); i++) {
            if (txs.getTxs(i).getExecer().startsWith(ByteString.copyFrom("user.p.".getBytes()))) {
                isPara = true;
            } else {
                isMain = true;
            }
            arrayList.add(new Transaction(txs.getTxs(i)));
        }
        if (isMain && isPara) {
            throw new Exception("ErrTxGroupParaMainMixed");
        }
        this.list = arrayList;
    }

    //使用时需要重构交易组
    public Transactions(TransactionAllProtobuf.Transaction... txs) throws Exception {
        if (txs.length > 20 || txs.length <= 1) {
            throw new Exception("ErrTxGroupCount");

        }
        boolean isPara = false;
        boolean isMain = false;
        ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
        for (int i = 0; i < txs.length; i++) {
            if (txs[i].getExecer().startsWith(ByteString.copyFrom("user.p.".getBytes()))) {
                isPara = true;
            } else {
                isMain = true;
            }
            arrayList.add(new Transaction(txs[i]));
        }
        if (isMain && isPara) {
            throw new Exception("ErrTxGroupParaMainMixed");
        }
        this.list = arrayList;
    }


    //使用时需要重构交易组
    public Transactions(String... txs) throws Exception {
        if (txs.length > 20 || txs.length <= 1) {
            throw new Exception("ErrTxGroupCount");
        }
        boolean isPara = false;
        boolean isMain = false;
        ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
        for (int i = 0; i < txs.length; i++) {
            TransactionAllProtobuf.Transaction tx = TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(txs[i]));
            if (tx.getExecer().startsWith(ByteString.copyFrom("user.p.".getBytes()))) {
                isPara = true;
            } else {
                isMain = true;
            }
            arrayList.add(new Transaction(tx));
        }
        if (isMain && isPara) {
            throw new Exception("ErrTxGroupParaMainMixed");
        }
        this.list = arrayList;
    }

    public List<Transaction> getTxList() {
        return this.list;
    }

    //重新构建交易组
    public void reBuild(long feeRate) throws Exception {
        long totalFee = 0;
        long minFee = 0;
        byte[] header = list.get(0).hash();
        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).setGroupCount(list.size());
            totalFee += list.get(i).getFee();
            list.get(i).setHeader(header);
            if (i == 0) {
                list.get(i).setFee(1 << 62);
            } else {
                list.get(i).setFee(0);
            }
            long realFee = list.get(i).getRealFee(feeRate);
            minFee += realFee;
            if (i == 0) {
                if (totalFee < minFee) {
                    totalFee = minFee;
                }
                list.get(i).setFee(totalFee);
                header = list.get(i).hash();
            } else {
                list.get(i).setFee(0);
                list.get(i - 1).setNext(getTxList().get(i).hash());
            }

        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setHeader(header);
        }
    }


    /**
     * 对交易组中交易签名
     *
     * @param n          当n小于0时表示对所有交易签名
     * @param signType
     * @param privateKey
     * @throws Exception
     */
    public void signN(int n, SignType signType, String privateKey) throws Exception {
        if (n >= list.size()) {
            throw new Exception("ErrIndex");
        }
        if (n < 0) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).sign(signType, privateKey);
            }
            return;
        }
        list.get(n).sign(signType, privateKey);
    }

    public Transaction toTransaction() throws Exception {
        if (getTxList().size() < 2) {
            throw new Exception("ErrInvalidParam");
        }
        //利用序列化进行深拷贝
        TransactionAllProtobuf.Transaction copytx = TransactionAllProtobuf.Transaction.parseFrom(getTxList().get(0).getTx().toByteArray());
        //放到header中不影响交易的Hash
        return new Transaction(copytx.toBuilder().setHeader(ByteString.copyFrom(getTxGroup().toByteArray())).build());
    }

    public TransactionAllProtobuf.Transactions getTxGroup() {
        TransactionAllProtobuf.Transactions.Builder builder = TransactionAllProtobuf.Transactions.newBuilder();
        getTxList().forEach(
                tx -> {
                    builder.addTxs(tx.getTx());
                }
        );
        return builder.build();
    }

}
