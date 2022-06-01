package cn.chain33.javasdk.client;

import cn.chain33.javasdk.model.protobuf.*;
import cn.chain33.javasdk.utils.HexUtil;
import com.google.protobuf.ByteString;
import io.grpc.*;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.SSLException;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GrpcClientTLS {
    private static final Logger logger = LoggerFactory.getLogger(GrpcClient.class);
    private final ManagedChannel channel;
    private final chain33Grpc.chain33BlockingStub blockingStub;

    private static SslContext buildSslContext(String trustCertCollectionFilePath,
                                              String clientCertChainFilePath,
                                              String clientPrivateKeyFilePath) throws SSLException {
        SslContextBuilder builder = GrpcSslContexts.forClient();
        if (trustCertCollectionFilePath != null) {
            builder.trustManager(new File(trustCertCollectionFilePath));
        }
        if (clientCertChainFilePath != null && clientPrivateKeyFilePath != null) {
            builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
        }
        return builder.build();
    }

    private GrpcClientTLS(ManagedChannel channel,List<EquivalentAddressGroup> addresses) {
        NameResolverRegistry nameResolverRegistry = NameResolverRegistry.getDefaultRegistry();
        NameResolverProvider nameResolverFactory = new MultipleResolverProvider(addresses);
        nameResolverRegistry.register(nameResolverFactory);
        this.channel = channel;
        blockingStub = chain33Grpc.newBlockingStub(channel);
    }

    public GrpcClientTLS(String targetURI,SslContext sslContext,List<EquivalentAddressGroup> addresses) {
        this(NettyChannelBuilder.forTarget(targetURI)
                        .negotiationType(NegotiationType.TLS)
                        .defaultLoadBalancingPolicy("round_robin") //pick_first,grpclb,round_robin,HealthCheckingRoundRobin
                        .sslContext(sslContext)
                        .build(),addresses);
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
            //ʹ������ stub����
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
            //ʹ������ stub����
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

