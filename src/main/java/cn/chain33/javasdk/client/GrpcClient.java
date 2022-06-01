package cn.chain33.javasdk.client;

import cn.chain33.javasdk.model.protobuf.*;
import cn.chain33.javasdk.utils.HexUtil;
import com.google.protobuf.ByteString;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GrpcClient {
    private static final Logger logger = LoggerFactory.getLogger(GrpcClient.class);
    private final ManagedChannel channel;
    private final chain33Grpc.chain33BlockingStub blockingStub;

    private GrpcClient(ManagedChannel channel,List<EquivalentAddressGroup> addresses) {
        this.channel = channel;
        NameResolverRegistry nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
        NameResolverProvider nameResolverFactory = new MultipleResolverProvider(addresses);
        nameResolverRegistry.register(nameResolverFactory);
        blockingStub = chain33Grpc.newBlockingStub(channel);
    }

    public GrpcClient(String targetURI,List<EquivalentAddressGroup> addresses) {
        this(ManagedChannelBuilder.forTarget(targetURI)
                .defaultLoadBalancingPolicy("round_robin") //pick_first,grpclb,round_robin,HealthCheckingRoundRobin
                .usePlaintext()
                .build(),addresses);
    }

    private GrpcClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = chain33Grpc.newBlockingStub(channel);
    }

    public GrpcClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    public GrpcClient(String host) {
        this(ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .build());
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * @description 获取最新的区块头 getLastHeader
     * @return 最新区块信息
     */
    public BlockchainProtobuf.Header getLastHeader() {
        CommonProtobuf.ReqNil request = CommonProtobuf.ReqNil.newBuilder().build();
        BlockchainProtobuf.Header response;
        try {
            //使用阻塞 stub调用
            response = blockingStub.getLastHeader(request);
            logger.info(response.toString());
            return response;
        } catch (StatusRuntimeException e) {
            logger.error(String.format("rpc failed:%s", e.getStatus()));
            return null;
        }
    }

    /**
     * @description 查询交易 queryTransaction
     * @return 交易信息
     */
    public TransactionAllProtobuf.TransactionDetail queryTransaction(String hash) {
        byte[] hashBytes = HexUtil.fromHexString(hash);
        CommonProtobuf.ReqHash request = CommonProtobuf.ReqHash.newBuilder().setHash(ByteString.copyFrom(hashBytes)).build();
        TransactionAllProtobuf.TransactionDetail response;
        try {
            //使用阻塞 stub调用
            response = blockingStub.queryTransaction(request);
            logger.info(response.toString());
            return response;
        } catch (StatusRuntimeException e) {
            logger.error(String.format("rpc failed:%s", e.getStatus()));
            return null;
        }
    }

    public <Result> Result run(Chain33Functional<chain33Grpc.chain33BlockingStub,Result> functional)
    {
        chain33Grpc.chain33BlockingStub blockingStub =
                chain33Grpc.newBlockingStub(channel);
        return functional.run(blockingStub);
    }

}