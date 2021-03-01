package cn.chain33.javasdk.client;

import cn.chain33.javasdk.model.protobuf.BlockchainProtobuf;
import cn.chain33.javasdk.model.protobuf.CommonProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.utils.HexUtil;
import com.google.protobuf.ByteString;
import org.junit.Test;

public class GrpcClientTest {
    // 区块链节点IP
    String ip = "区块链ip";
    // 平行链服务端口
    int port = 8802;
    // targetURI
    String targetURI = "47.92.105.160:8802";
    GrpcClient javaGrpcClient = new GrpcClient(targetURI,null);

    /**
     * 获取最新高度
     */
    @Test
    public void getLastHeader() {
        CommonProtobuf.ReqNil request = CommonProtobuf.ReqNil.newBuilder().build();
        System.out.println(request);
        BlockchainProtobuf.Header result = javaGrpcClient.run(o -> o.getLastHeader(request));
        System.out.println(result);
        System.out.println("txhash:"+ HexUtil.toHexString(result.getHash().toByteArray()));
        BlockchainProtobuf.Header result2 = javaGrpcClient.run(o -> o.getLastHeader(request));
        System.out.println(result2);
        System.out.println("txhash2:"+ HexUtil.toHexString(result2.getHash().toByteArray()));
    }

    /**
     * 查询交易
     */
    @Test
    public void queryTransaction() {
        String hash = "Oxe11a097fa64607aca16ec02b886e7afcea6d8f0a7d8bbe3bc07710b9e873ec15";
        byte[] hashBytes = HexUtil.fromHexString(hash);
        CommonProtobuf.ReqHash request = CommonProtobuf.ReqHash.newBuilder().setHash(ByteString.copyFrom(hashBytes)).build();
        System.out.println(request);
        TransactionAllProtobuf.TransactionDetail result = javaGrpcClient.run(o -> o.queryTransaction(request));
        System.out.println(result);
    }

    /**
     * 查询交易
     */
    @Test
    public void queryTransaction2() {
        String hash = "Oxe11a097fa64607aca16ec02b886e7afcea6d8f0a7d8bbe3bc07710b9e873ec15";
        TransactionAllProtobuf.TransactionDetail tx = javaGrpcClient.queryTransaction(hash);
        System.out.println("result:"+tx);
    }
}
