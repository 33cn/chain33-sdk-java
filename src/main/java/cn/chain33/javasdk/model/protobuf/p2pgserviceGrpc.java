package cn.chain33.javasdk.model.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.31.1)", comments = "Source: p2p.proto")
public final class p2pgserviceGrpc {

    private p2pgserviceGrpc() {
    }

    public static final String SERVICE_NAME = "p2pgservice";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PTx, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBroadCastTxMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "BroadCastTx", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PTx.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PTx, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBroadCastTxMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PTx, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBroadCastTxMethod;
        if ((getBroadCastTxMethod = p2pgserviceGrpc.getBroadCastTxMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getBroadCastTxMethod = p2pgserviceGrpc.getBroadCastTxMethod) == null) {
                    p2pgserviceGrpc.getBroadCastTxMethod = getBroadCastTxMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PTx, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BroadCastTx"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PTx.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("BroadCastTx")).build();
                }
            }
        }
        return getBroadCastTxMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBroadCastBlockMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "BroadCastBlock", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBroadCastBlockMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBroadCastBlockMethod;
        if ((getBroadCastBlockMethod = p2pgserviceGrpc.getBroadCastBlockMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getBroadCastBlockMethod = p2pgserviceGrpc.getBroadCastBlockMethod) == null) {
                    p2pgserviceGrpc.getBroadCastBlockMethod = getBroadCastBlockMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BroadCastBlock"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("BroadCastBlock")).build();
                }
            }
        }
        return getBroadCastBlockMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> getPingMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "Ping", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPong.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> getPingMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> getPingMethod;
        if ((getPingMethod = p2pgserviceGrpc.getPingMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getPingMethod = p2pgserviceGrpc.getPingMethod) == null) {
                    p2pgserviceGrpc.getPingMethod = getPingMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPong.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("Ping")).build();
                }
            }
        }
        return getPingMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> getGetAddrMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetAddr", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> getGetAddrMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> getGetAddrMethod;
        if ((getGetAddrMethod = p2pgserviceGrpc.getGetAddrMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetAddrMethod = p2pgserviceGrpc.getGetAddrMethod) == null) {
                    p2pgserviceGrpc.getGetAddrMethod = getGetAddrMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAddr"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetAddr")).build();
                }
            }
        }
        return getGetAddrMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> getGetAddrListMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetAddrList", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> getGetAddrListMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> getGetAddrListMethod;
        if ((getGetAddrListMethod = p2pgserviceGrpc.getGetAddrListMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetAddrListMethod = p2pgserviceGrpc.getGetAddrListMethod) == null) {
                    p2pgserviceGrpc.getGetAddrListMethod = getGetAddrListMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAddrList"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetAddrList")).build();
                }
            }
        }
        return getGetAddrListMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> getVersionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "Version", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> getVersionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> getVersionMethod;
        if ((getVersionMethod = p2pgserviceGrpc.getVersionMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getVersionMethod = p2pgserviceGrpc.getVersionMethod) == null) {
                    p2pgserviceGrpc.getVersionMethod = getVersionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Version"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("Version")).build();
                }
            }
        }
        return getVersionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> getVersion2Method;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "Version2", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> getVersion2Method() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> getVersion2Method;
        if ((getVersion2Method = p2pgserviceGrpc.getVersion2Method) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getVersion2Method = p2pgserviceGrpc.getVersion2Method) == null) {
                    p2pgserviceGrpc.getVersion2Method = getVersion2Method = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Version2"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("Version2")).build();
                }
            }
        }
        return getVersion2Method;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSoftVersionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SoftVersion", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSoftVersionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSoftVersionMethod;
        if ((getSoftVersionMethod = p2pgserviceGrpc.getSoftVersionMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getSoftVersionMethod = p2pgserviceGrpc.getSoftVersionMethod) == null) {
                    p2pgserviceGrpc.getSoftVersionMethod = getSoftVersionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SoftVersion"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("SoftVersion")).build();
                }
            }
        }
        return getSoftVersionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getGetBlocksMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBlocks", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PInv.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getGetBlocksMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getGetBlocksMethod;
        if ((getGetBlocksMethod = p2pgserviceGrpc.getGetBlocksMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetBlocksMethod = p2pgserviceGrpc.getGetBlocksMethod) == null) {
                    p2pgserviceGrpc.getGetBlocksMethod = getGetBlocksMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlocks"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PInv.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetBlocks")).build();
                }
            }
        }
        return getGetBlocksMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getGetMemPoolMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetMemPool", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PInv.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getGetMemPoolMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getGetMemPoolMethod;
        if ((getGetMemPoolMethod = p2pgserviceGrpc.getGetMemPoolMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetMemPoolMethod = p2pgserviceGrpc.getGetMemPoolMethod) == null) {
                    p2pgserviceGrpc.getGetMemPoolMethod = getGetMemPoolMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMemPool"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PInv.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetMemPool")).build();
                }
            }
        }
        return getGetMemPoolMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData, cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> getGetDataMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetData", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.InvDatas.class, methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData, cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> getGetDataMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData, cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> getGetDataMethod;
        if ((getGetDataMethod = p2pgserviceGrpc.getGetDataMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetDataMethod = p2pgserviceGrpc.getGetDataMethod) == null) {
                    p2pgserviceGrpc.getGetDataMethod = getGetDataMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData, cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetData"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.InvDatas.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetData")).build();
                }
            }
        }
        return getGetDataMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders, cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> getGetHeadersMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetHeaders", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders, cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> getGetHeadersMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders, cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> getGetHeadersMethod;
        if ((getGetHeadersMethod = p2pgserviceGrpc.getGetHeadersMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetHeadersMethod = p2pgserviceGrpc.getGetHeadersMethod) == null) {
                    p2pgserviceGrpc.getGetHeadersMethod = getGetHeadersMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders, cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetHeaders"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetHeaders")).build();
                }
            }
        }
        return getGetHeadersMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo, cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> getGetPeerInfoMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetPeerInfo", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo, cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> getGetPeerInfoMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo, cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> getGetPeerInfoMethod;
        if ((getGetPeerInfoMethod = p2pgserviceGrpc.getGetPeerInfoMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getGetPeerInfoMethod = p2pgserviceGrpc.getGetPeerInfoMethod) == null) {
                    p2pgserviceGrpc.getGetPeerInfoMethod = getGetPeerInfoMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo, cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPeerInfo"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("GetPeerInfo")).build();
                }
            }
        }
        return getGetPeerInfoMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil> getServerStreamReadMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "ServerStreamRead", requestType = cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil> getServerStreamReadMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil> getServerStreamReadMethod;
        if ((getServerStreamReadMethod = p2pgserviceGrpc.getServerStreamReadMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getServerStreamReadMethod = p2pgserviceGrpc.getServerStreamReadMethod) == null) {
                    p2pgserviceGrpc.getServerStreamReadMethod = getServerStreamReadMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ServerStreamRead"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("ServerStreamRead")).build();
                }
            }
        }
        return getServerStreamReadMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> getServerStreamSendMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "ServerStreamSend", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData.class, methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> getServerStreamSendMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> getServerStreamSendMethod;
        if ((getServerStreamSendMethod = p2pgserviceGrpc.getServerStreamSendMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getServerStreamSendMethod = p2pgserviceGrpc.getServerStreamSendMethod) == null) {
                    p2pgserviceGrpc.getServerStreamSendMethod = getServerStreamSendMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ServerStreamSend"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("ServerStreamSend")).build();
                }
            }
        }
        return getServerStreamSendMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getCollectInPeersMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CollectInPeers", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.PeerList.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getCollectInPeersMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getCollectInPeersMethod;
        if ((getCollectInPeersMethod = p2pgserviceGrpc.getCollectInPeersMethod) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getCollectInPeersMethod = p2pgserviceGrpc.getCollectInPeersMethod) == null) {
                    p2pgserviceGrpc.getCollectInPeersMethod = getCollectInPeersMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CollectInPeers"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.PeerList.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("CollectInPeers")).build();
                }
            }
        }
        return getCollectInPeersMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> getCollectInPeers2Method;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CollectInPeers2", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.PeersReply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> getCollectInPeers2Method() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> getCollectInPeers2Method;
        if ((getCollectInPeers2Method = p2pgserviceGrpc.getCollectInPeers2Method) == null) {
            synchronized (p2pgserviceGrpc.class) {
                if ((getCollectInPeers2Method = p2pgserviceGrpc.getCollectInPeers2Method) == null) {
                    p2pgserviceGrpc.getCollectInPeers2Method = getCollectInPeers2Method = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CollectInPeers2"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PPing.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.PeersReply.getDefaultInstance()))
                            .setSchemaDescriptor(new p2pgserviceMethodDescriptorSupplier("CollectInPeers2")).build();
                }
            }
        }
        return getCollectInPeers2Method;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static p2pgserviceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<p2pgserviceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<p2pgserviceStub>() {
            @java.lang.Override
            public p2pgserviceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new p2pgserviceStub(channel, callOptions);
            }
        };
        return p2pgserviceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static p2pgserviceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<p2pgserviceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<p2pgserviceBlockingStub>() {
            @java.lang.Override
            public p2pgserviceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new p2pgserviceBlockingStub(channel, callOptions);
            }
        };
        return p2pgserviceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static p2pgserviceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<p2pgserviceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<p2pgserviceFutureStub>() {
            @java.lang.Override
            public p2pgserviceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new p2pgserviceFutureStub(channel, callOptions);
            }
        };
        return p2pgserviceFutureStub.newStub(factory, channel);
    }

    /**
     */
    public static abstract class p2pgserviceImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * 广播交易
         * </pre>
         */
        public void broadCastTx(cn.chain33.javasdk.model.protobuf.P2pService.P2PTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getBroadCastTxMethod(), responseObserver);
        }

        /**
         * <pre>
         * 广播区块
         * </pre>
         */
        public void broadCastBlock(cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getBroadCastBlockMethod(), responseObserver);
        }

        /**
         * <pre>
         * PING
         * </pre>
         */
        public void ping(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> responseObserver) {
            asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取地址
         * </pre>
         */
        public void getAddr(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAddrMethod(), responseObserver);
        }

        /**
         */
        public void getAddrList(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAddrListMethod(), responseObserver);
        }

        /**
         * <pre>
         * 版本
         * </pre>
         */
        public void version(cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> responseObserver) {
            asyncUnimplementedUnaryCall(getVersionMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取p2p协议的版本号
         * </pre>
         */
        public void version2(cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> responseObserver) {
            asyncUnimplementedUnaryCall(getVersion2Method(), responseObserver);
        }

        /**
         * <pre>
         * 获取软件的版本号
         * </pre>
         */
        public void softVersion(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getSoftVersionMethod(), responseObserver);
        }

        /**
         * <pre>
         *获取区块，最高200
         * </pre>
         */
        public void getBlocks(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlocksMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取mempool
         * </pre>
         */
        public void getMemPool(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> responseObserver) {
            asyncUnimplementedUnaryCall(getGetMemPoolMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取数据
         * </pre>
         */
        public void getData(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> responseObserver) {
            asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取头部
         * </pre>
         */
        public void getHeaders(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> responseObserver) {
            asyncUnimplementedUnaryCall(getGetHeadersMethod(), responseObserver);
        }

        /**
         * <pre>
         *获取 peerinfo
         * </pre>
         */
        public void getPeerInfo(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> responseObserver) {
            asyncUnimplementedUnaryCall(getGetPeerInfoMethod(), responseObserver);
        }

        /**
         * <pre>
         * grpc server 读客户端发送来的数据
         * </pre>
         */
        public io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> serverStreamRead(
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil> responseObserver) {
            return asyncUnimplementedStreamingCall(getServerStreamReadMethod(), responseObserver);
        }

        /**
         * <pre>
         * grpc server 发送数据给客户端
         * </pre>
         */
        public void serverStreamSend(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> responseObserver) {
            asyncUnimplementedUnaryCall(getServerStreamSendMethod(), responseObserver);
        }

        /**
         * <pre>
         * grpc 收集inpeers
         * </pre>
         */
        public void collectInPeers(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeerList> responseObserver) {
            asyncUnimplementedUnaryCall(getCollectInPeersMethod(), responseObserver);
        }

        /**
         */
        public void collectInPeers2(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> responseObserver) {
            asyncUnimplementedUnaryCall(getCollectInPeers2Method(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(getBroadCastTxMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PTx, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_BROAD_CAST_TX)))
                    .addMethod(getBroadCastBlockMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_BROAD_CAST_BLOCK)))
                    .addMethod(getPingMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.P2PPong>(
                                    this, METHODID_PING)))
                    .addMethod(getGetAddrMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr>(
                                    this, METHODID_GET_ADDR)))
                    .addMethod(getGetAddrListMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr, cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList>(
                                    this, METHODID_GET_ADDR_LIST)))
                    .addMethod(getVersionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck>(
                                    this, METHODID_VERSION)))
                    .addMethod(getVersion2Method(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion, cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion>(
                                    this, METHODID_VERSION2)))
                    .addMethod(getSoftVersionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_SOFT_VERSION)))
                    .addMethod(getGetBlocksMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv>(
                                    this, METHODID_GET_BLOCKS)))
                    .addMethod(getGetMemPoolMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool, cn.chain33.javasdk.model.protobuf.P2pService.P2PInv>(
                                    this, METHODID_GET_MEM_POOL)))
                    .addMethod(getGetDataMethod(), asyncServerStreamingCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData, cn.chain33.javasdk.model.protobuf.P2pService.InvDatas>(
                                    this, METHODID_GET_DATA)))
                    .addMethod(getGetHeadersMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders, cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders>(
                                    this, METHODID_GET_HEADERS)))
                    .addMethod(getGetPeerInfoMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo, cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo>(
                                    this, METHODID_GET_PEER_INFO)))
                    .addMethod(getServerStreamReadMethod(), asyncClientStreamingCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil>(
                                    this, METHODID_SERVER_STREAM_READ)))
                    .addMethod(getServerStreamSendMethod(), asyncServerStreamingCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData>(
                                    this, METHODID_SERVER_STREAM_SEND)))
                    .addMethod(getCollectInPeersMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeerList>(
                                    this, METHODID_COLLECT_IN_PEERS)))
                    .addMethod(getCollectInPeers2Method(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PPing, cn.chain33.javasdk.model.protobuf.P2pService.PeersReply>(
                                    this, METHODID_COLLECT_IN_PEERS2)))
                    .build();
        }
    }

    /**
     */
    public static final class p2pgserviceStub extends io.grpc.stub.AbstractAsyncStub<p2pgserviceStub> {
        private p2pgserviceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected p2pgserviceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new p2pgserviceStub(channel, callOptions);
        }

        /**
         * <pre>
         * 广播交易
         * </pre>
         */
        public void broadCastTx(cn.chain33.javasdk.model.protobuf.P2pService.P2PTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getBroadCastTxMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 广播区块
         * </pre>
         */
        public void broadCastBlock(cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getBroadCastBlockMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * PING
         * </pre>
         */
        public void ping(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取地址
         * </pre>
         */
        public void getAddr(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetAddrMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getAddrList(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetAddrListMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 版本
         * </pre>
         */
        public void version(cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getVersionMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取p2p协议的版本号
         * </pre>
         */
        public void version2(cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getVersion2Method(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取软件的版本号
         * </pre>
         */
        public void softVersion(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSoftVersionMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *获取区块，最高200
         * </pre>
         */
        public void getBlocks(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取mempool
         * </pre>
         */
        public void getMemPool(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetMemPoolMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取数据
         * </pre>
         */
        public void getData(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> responseObserver) {
            asyncServerStreamingCall(getChannel().newCall(getGetDataMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取头部
         * </pre>
         */
        public void getHeaders(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetHeadersMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *获取 peerinfo
         * </pre>
         */
        public void getPeerInfo(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetPeerInfoMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * grpc server 读客户端发送来的数据
         * </pre>
         */
        public io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> serverStreamRead(
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil> responseObserver) {
            return asyncClientStreamingCall(getChannel().newCall(getServerStreamReadMethod(), getCallOptions()),
                    responseObserver);
        }

        /**
         * <pre>
         * grpc server 发送数据给客户端
         * </pre>
         */
        public void serverStreamSend(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> responseObserver) {
            asyncServerStreamingCall(getChannel().newCall(getServerStreamSendMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * grpc 收集inpeers
         * </pre>
         */
        public void collectInPeers(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeerList> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCollectInPeersMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         */
        public void collectInPeers2(cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCollectInPeers2Method(), getCallOptions()), request,
                    responseObserver);
        }
    }

    /**
     */
    public static final class p2pgserviceBlockingStub
            extends io.grpc.stub.AbstractBlockingStub<p2pgserviceBlockingStub> {
        private p2pgserviceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected p2pgserviceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new p2pgserviceBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * 广播交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply broadCastTx(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PTx request) {
            return blockingUnaryCall(getChannel(), getBroadCastTxMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 广播区块
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply broadCastBlock(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock request) {
            return blockingUnaryCall(getChannel(), getBroadCastBlockMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * PING
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PPong ping(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return blockingUnaryCall(getChannel(), getPingMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取地址
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr getAddr(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request) {
            return blockingUnaryCall(getChannel(), getGetAddrMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList getAddrList(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request) {
            return blockingUnaryCall(getChannel(), getGetAddrListMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 版本
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck version(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request) {
            return blockingUnaryCall(getChannel(), getVersionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取p2p协议的版本号
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion version2(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request) {
            return blockingUnaryCall(getChannel(), getVersion2Method(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取软件的版本号
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply softVersion(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return blockingUnaryCall(getChannel(), getSoftVersionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *获取区块，最高200
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PInv getBlocks(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks request) {
            return blockingUnaryCall(getChannel(), getGetBlocksMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取mempool
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PInv getMemPool(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool request) {
            return blockingUnaryCall(getChannel(), getGetMemPoolMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取数据
         * </pre>
         */
        public java.util.Iterator<cn.chain33.javasdk.model.protobuf.P2pService.InvDatas> getData(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData request) {
            return blockingServerStreamingCall(getChannel(), getGetDataMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取头部
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders getHeaders(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders request) {
            return blockingUnaryCall(getChannel(), getGetHeadersMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *获取 peerinfo
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo getPeerInfo(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo request) {
            return blockingUnaryCall(getChannel(), getGetPeerInfoMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * grpc server 发送数据给客户端
         * </pre>
         */
        public java.util.Iterator<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData> serverStreamSend(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return blockingServerStreamingCall(getChannel(), getServerStreamSendMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * grpc 收集inpeers
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.PeerList collectInPeers(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return blockingUnaryCall(getChannel(), getCollectInPeersMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.PeersReply collectInPeers2(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return blockingUnaryCall(getChannel(), getCollectInPeers2Method(), getCallOptions(), request);
        }
    }

    /**
     */
    public static final class p2pgserviceFutureStub extends io.grpc.stub.AbstractFutureStub<p2pgserviceFutureStub> {
        private p2pgserviceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected p2pgserviceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new p2pgserviceFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * 广播交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> broadCastTx(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PTx request) {
            return futureUnaryCall(getChannel().newCall(getBroadCastTxMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 广播区块
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> broadCastBlock(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock request) {
            return futureUnaryCall(getChannel().newCall(getBroadCastBlockMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * PING
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PPong> ping(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return futureUnaryCall(getChannel().newCall(getPingMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取地址
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr> getAddr(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request) {
            return futureUnaryCall(getChannel().newCall(getGetAddrMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList> getAddrList(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr request) {
            return futureUnaryCall(getChannel().newCall(getGetAddrListMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 版本
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck> version(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request) {
            return futureUnaryCall(getChannel().newCall(getVersionMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取p2p协议的版本号
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion> version2(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion request) {
            return futureUnaryCall(getChannel().newCall(getVersion2Method(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取软件的版本号
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> softVersion(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return futureUnaryCall(getChannel().newCall(getSoftVersionMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *获取区块，最高200
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getBlocks(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks request) {
            return futureUnaryCall(getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取mempool
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv> getMemPool(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool request) {
            return futureUnaryCall(getChannel().newCall(getGetMemPoolMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取头部
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders> getHeaders(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders request) {
            return futureUnaryCall(getChannel().newCall(getGetHeadersMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *获取 peerinfo
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo> getPeerInfo(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo request) {
            return futureUnaryCall(getChannel().newCall(getGetPeerInfoMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * grpc 收集inpeers
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.PeerList> collectInPeers(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return futureUnaryCall(getChannel().newCall(getCollectInPeersMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.PeersReply> collectInPeers2(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PPing request) {
            return futureUnaryCall(getChannel().newCall(getCollectInPeers2Method(), getCallOptions()), request);
        }
    }

    private static final int METHODID_BROAD_CAST_TX = 0;
    private static final int METHODID_BROAD_CAST_BLOCK = 1;
    private static final int METHODID_PING = 2;
    private static final int METHODID_GET_ADDR = 3;
    private static final int METHODID_GET_ADDR_LIST = 4;
    private static final int METHODID_VERSION = 5;
    private static final int METHODID_VERSION2 = 6;
    private static final int METHODID_SOFT_VERSION = 7;
    private static final int METHODID_GET_BLOCKS = 8;
    private static final int METHODID_GET_MEM_POOL = 9;
    private static final int METHODID_GET_DATA = 10;
    private static final int METHODID_GET_HEADERS = 11;
    private static final int METHODID_GET_PEER_INFO = 12;
    private static final int METHODID_SERVER_STREAM_SEND = 13;
    private static final int METHODID_COLLECT_IN_PEERS = 14;
    private static final int METHODID_COLLECT_IN_PEERS2 = 15;
    private static final int METHODID_SERVER_STREAM_READ = 16;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final p2pgserviceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(p2pgserviceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
            case METHODID_BROAD_CAST_TX:
                serviceImpl.broadCastTx((cn.chain33.javasdk.model.protobuf.P2pService.P2PTx) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_BROAD_CAST_BLOCK:
                serviceImpl.broadCastBlock((cn.chain33.javasdk.model.protobuf.P2pService.P2PBlock) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_PING:
                serviceImpl.ping((cn.chain33.javasdk.model.protobuf.P2pService.P2PPing) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PPong>) responseObserver);
                break;
            case METHODID_GET_ADDR:
                serviceImpl.getAddr((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddr>) responseObserver);
                break;
            case METHODID_GET_ADDR_LIST:
                serviceImpl.getAddrList((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetAddr) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PAddrList>) responseObserver);
                break;
            case METHODID_VERSION:
                serviceImpl.version((cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PVerAck>) responseObserver);
                break;
            case METHODID_VERSION2:
                serviceImpl.version2((cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PVersion>) responseObserver);
                break;
            case METHODID_SOFT_VERSION:
                serviceImpl.softVersion((cn.chain33.javasdk.model.protobuf.P2pService.P2PPing) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_BLOCKS:
                serviceImpl.getBlocks((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetBlocks) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv>) responseObserver);
                break;
            case METHODID_GET_MEM_POOL:
                serviceImpl.getMemPool((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetMempool) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PInv>) responseObserver);
                break;
            case METHODID_GET_DATA:
                serviceImpl.getData((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetData) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.InvDatas>) responseObserver);
                break;
            case METHODID_GET_HEADERS:
                serviceImpl.getHeaders((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetHeaders) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PHeaders>) responseObserver);
                break;
            case METHODID_GET_PEER_INFO:
                serviceImpl.getPeerInfo((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerInfo) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.P2PPeerInfo>) responseObserver);
                break;
            case METHODID_SERVER_STREAM_SEND:
                serviceImpl.serverStreamSend((cn.chain33.javasdk.model.protobuf.P2pService.P2PPing) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.BroadCastData>) responseObserver);
                break;
            case METHODID_COLLECT_IN_PEERS:
                serviceImpl.collectInPeers((cn.chain33.javasdk.model.protobuf.P2pService.P2PPing) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeerList>) responseObserver);
                break;
            case METHODID_COLLECT_IN_PEERS2:
                serviceImpl.collectInPeers2((cn.chain33.javasdk.model.protobuf.P2pService.P2PPing) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeersReply>) responseObserver);
                break;
            default:
                throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
            case METHODID_SERVER_STREAM_READ:
                return (io.grpc.stub.StreamObserver<Req>) serviceImpl.serverStreamRead(
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil>) responseObserver);
            default:
                throw new AssertionError();
            }
        }
    }

    private static abstract class p2pgserviceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        p2pgserviceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return cn.chain33.javasdk.model.protobuf.P2pService.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("p2pgservice");
        }
    }

    private static final class p2pgserviceFileDescriptorSupplier extends p2pgserviceBaseDescriptorSupplier {
        p2pgserviceFileDescriptorSupplier() {
        }
    }

    private static final class p2pgserviceMethodDescriptorSupplier extends p2pgserviceBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        p2pgserviceMethodDescriptorSupplier(String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (p2pgserviceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new p2pgserviceFileDescriptorSupplier())
                            .addMethod(getBroadCastTxMethod()).addMethod(getBroadCastBlockMethod())
                            .addMethod(getPingMethod()).addMethod(getGetAddrMethod()).addMethod(getGetAddrListMethod())
                            .addMethod(getVersionMethod()).addMethod(getVersion2Method())
                            .addMethod(getSoftVersionMethod()).addMethod(getGetBlocksMethod())
                            .addMethod(getGetMemPoolMethod()).addMethod(getGetDataMethod())
                            .addMethod(getGetHeadersMethod()).addMethod(getGetPeerInfoMethod())
                            .addMethod(getServerStreamReadMethod()).addMethod(getServerStreamSendMethod())
                            .addMethod(getCollectInPeersMethod()).addMethod(getCollectInPeers2Method()).build();
                }
            }
        }
        return result;
    }
}
