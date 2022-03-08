package cn.chain33.javasdk.model;

import java.io.IOException;

import org.junit.Test;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.TransactionUtil;

public class ExceptionTest {

    String ip = "fd.33.cn";
    RpcClient client = new RpcClient(ip, 1263);

    /**
     * 双花测试
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void doubleSpent() throws InterruptedException, IOException {

        TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest();

        // 转账说明
        transferBalanceRequest.setNote("转账说明");
        // 转主积分的情况下，默认填""
        transferBalanceRequest.setCoinToken("");
        // 转账数量 ， 以下代表转1个积分
        transferBalanceRequest.setAmount(1 * 100000000L);
        // 转到的地址
        transferBalanceRequest.setTo("1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7");
        // 签名私私钥,对应的测试地址是：1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs
        transferBalanceRequest.setFromPrivateKey("3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8");
        // 执行器名称，主链主积分固定为coins
        transferBalanceRequest.setExecer("coins");
        // 签名类型 (支持SM2, SECP256K1, ED25519)
        transferBalanceRequest.setSignType(SignType.SECP256K1);
        // 构造好，并本地签好名的交易
        String createTransferTx = TransactionUtil.transferBalanceMain(transferBalanceRequest);
        // 交易发往区块链
        String txHash1 = client.submitTransaction(createTransferTx);
        // 相同交易重复发送，验证是否双花
        String txHash2 = client.submitTransaction(createTransferTx);
        System.out.println(txHash1);
        // 第一笔交易有hash，重复发送的这笔会收到提示：ErrTxExist,没有交易hash返回
        System.out.println(txHash2);

    }

    /**
     * 超出额度花费
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void overflowSpent() throws InterruptedException, IOException {

        TransferBalanceRequest transferBalanceRequest = new TransferBalanceRequest();

        // 转账说明
        transferBalanceRequest.setNote("转账说明");
        // 转主积分的情况下，默认填""
        transferBalanceRequest.setCoinToken("");
        // 转账数量 ， 以下代表转1个积分
        transferBalanceRequest.setAmount(100 * 100000000L);
        // 转到的地址
        transferBalanceRequest.setTo("1ChXKZMNwVjY3bGEqpwHTL5NAPKiJhGzzy");
        // 签名私私钥,对应的测试地址是：1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs
        transferBalanceRequest.setFromPrivateKey("3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8");
        // 执行器名称，主链主积分固定为coins
        transferBalanceRequest.setExecer("coins");
        // 签名类型 (支持SM2, SECP256K1, ED25519)
        transferBalanceRequest.setSignType(SignType.SECP256K1);
        // 构造好，并本地签好名的交易
        String createTransferTx = TransactionUtil.transferBalanceMain(transferBalanceRequest);
        // 交易发往区块链
        String txHash = client.submitTransaction(createTransferTx);
        // 第一笔交易有hash，重复发送的这笔会收到提示：ErrTxExist,没有交易hash返回
        System.out.println(txHash);

        // 一般1秒一个区块
        QueryTransactionResult queryTransaction;
        for (int i = 0; i < 10; i++) {
            queryTransaction = client.queryTransaction(txHash);
            if (null == queryTransaction) {
                Thread.sleep(1000);
            } else {
                break;
            }
        }

        // 从1ChXKZMNwVjY3bGEqpwHTL5NAPKiJhGzzy地址中打出两笔交易，一笔100,一笔0.1

        // 转账说明
        transferBalanceRequest.setNote("转账说明");
        // 转主积分的情况下，默认填""
        transferBalanceRequest.setCoinToken("");
        // 转账数量 ， 以下代表转100个积分
        transferBalanceRequest.setAmount(100 * 100000000L);
        // 转到的地址
        transferBalanceRequest.setTo("19jsPusNK6KdLkwNzZUVQpB4QdZi6EL2HH");
        // 签名私私钥,对应的测试地址是：1ChXKZMNwVjY3bGEqpwHTL5NAPKiJhGzzy
        transferBalanceRequest.setFromPrivateKey("ffba3db75fe6c1c2166009bc4d7bfbe4f02d9a24c0c7bceb9187625ed465d654");
        // 执行器名称，主链主积分固定为coins
        transferBalanceRequest.setExecer("coins");
        // 签名类型 (支持SM2, SECP256K1, ED25519)
        transferBalanceRequest.setSignType(SignType.SECP256K1);
        // 构造好，并本地签好名的交易
        String createTransferTx1 = TransactionUtil.transferBalanceMain(transferBalanceRequest);

        // 转账说明
        transferBalanceRequest.setNote("转账说明");
        // 转主积分的情况下，默认填""
        transferBalanceRequest.setCoinToken("");
        // 转账数量 ， 以下代表转0.1个积分
        transferBalanceRequest.setAmount(1 * 10000000L);
        // 转到的地址
        transferBalanceRequest.setTo("19jsPusNK6KdLkwNzZUVQpB4QdZi6EL2HH");
        // 签名私私钥,对应的测试地址是：1ChXKZMNwVjY3bGEqpwHTL5NAPKiJhGzzy
        transferBalanceRequest.setFromPrivateKey("ffba3db75fe6c1c2166009bc4d7bfbe4f02d9a24c0c7bceb9187625ed465d654");
        // 执行器名称，主链主积分固定为coins
        transferBalanceRequest.setExecer("coins");
        // 签名类型 (支持SM2, SECP256K1, ED25519)
        transferBalanceRequest.setSignType(SignType.SECP256K1);
        // 构造好，并本地签好名的交易
        String createTransferTx2 = TransactionUtil.transferBalanceMain(transferBalanceRequest);

        // 交易发往区块链
        String txHash1 = client.submitTransaction(createTransferTx1);
        String txHash2 = client.submitTransaction(createTransferTx2);
        // 第一笔交易有hash，重复发送的这笔会收到提示：ErrTxExist,没有交易hash返回
        System.out.println(txHash1);
        System.out.println(txHash2);

        // 一般1秒一个区块
        QueryTransactionResult queryTransaction1;
        QueryTransactionResult queryTransaction2;
        for (int i = 0; i < 10; i++) {
            queryTransaction1 = client.queryTransaction(txHash1);
            queryTransaction2 = client.queryTransaction(txHash2);
            if (null == queryTransaction1 || null == queryTransaction1) {
                Thread.sleep(1000);
            } else {
                System.out.println(queryTransaction1.getReceipt().getTyname());
                System.out.println(queryTransaction2.getReceipt().getTyname());
                break;
            }
        }

    }

}
