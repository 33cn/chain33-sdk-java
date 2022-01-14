package cn.chain33.javasdk.model.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.31.1)", comments = "Source: grpc.proto")
public final class chain33Grpc {

    private chain33Grpc() {
    }

    public static final String SERVICE_NAME = "types.chain33";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getGetBlocksMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBlocks", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getGetBlocksMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getGetBlocksMethod;
        if ((getGetBlocksMethod = chain33Grpc.getGetBlocksMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetBlocksMethod = chain33Grpc.getGetBlocksMethod) == null) {
                    chain33Grpc.getGetBlocksMethod = getGetBlocksMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlocks"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetBlocks")).build();
                }
            }
        }
        return getGetBlocksMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> getGetLastHeaderMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetLastHeader", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> getGetLastHeaderMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> getGetLastHeaderMethod;
        if ((getGetLastHeaderMethod = chain33Grpc.getGetLastHeaderMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetLastHeaderMethod = chain33Grpc.getGetLastHeaderMethod) == null) {
                    chain33Grpc.getGetLastHeaderMethod = getGetLastHeaderMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLastHeader"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetLastHeader")).build();
                }
            }
        }
        return getGetLastHeaderMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateRawTransactionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CreateRawTransaction", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateRawTransactionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateRawTransactionMethod;
        if ((getCreateRawTransactionMethod = chain33Grpc.getCreateRawTransactionMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getCreateRawTransactionMethod = chain33Grpc.getCreateRawTransactionMethod) == null) {
                    chain33Grpc.getCreateRawTransactionMethod = getCreateRawTransactionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRawTransaction"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("CreateRawTransaction")).build();
                }
            }
        }
        return getCreateRawTransactionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateRawTxGroupMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CreateRawTxGroup", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateRawTxGroupMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateRawTxGroupMethod;
        if ((getCreateRawTxGroupMethod = chain33Grpc.getCreateRawTxGroupMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getCreateRawTxGroupMethod = chain33Grpc.getCreateRawTxGroupMethod) == null) {
                    chain33Grpc.getCreateRawTxGroupMethod = getCreateRawTxGroupMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRawTxGroup"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("CreateRawTxGroup")).build();
                }
            }
        }
        return getCreateRawTxGroupMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> getQueryTransactionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "QueryTransaction", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> getQueryTransactionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> getQueryTransactionMethod;
        if ((getQueryTransactionMethod = chain33Grpc.getQueryTransactionMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getQueryTransactionMethod = chain33Grpc.getQueryTransactionMethod) == null) {
                    chain33Grpc.getQueryTransactionMethod = getQueryTransactionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryTransaction"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("QueryTransaction")).build();
                }
            }
        }
        return getQueryTransactionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSendTransactionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SendTransaction", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSendTransactionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSendTransactionMethod;
        if ((getSendTransactionMethod = chain33Grpc.getSendTransactionMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSendTransactionMethod = chain33Grpc.getSendTransactionMethod) == null) {
                    chain33Grpc.getSendTransactionMethod = getSendTransactionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendTransaction"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SendTransaction")).build();
                }
            }
        }
        return getSendTransactionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> getGetTransactionByAddrMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetTransactionByAddr", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> getGetTransactionByAddrMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> getGetTransactionByAddrMethod;
        if ((getGetTransactionByAddrMethod = chain33Grpc.getGetTransactionByAddrMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetTransactionByAddrMethod = chain33Grpc.getGetTransactionByAddrMethod) == null) {
                    chain33Grpc.getGetTransactionByAddrMethod = getGetTransactionByAddrMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTransactionByAddr"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetTransactionByAddr")).build();
                }
            }
        }
        return getGetTransactionByAddrMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> getGetTransactionByHashesMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetTransactionByHashes", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> getGetTransactionByHashesMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> getGetTransactionByHashesMethod;
        if ((getGetTransactionByHashesMethod = chain33Grpc.getGetTransactionByHashesMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetTransactionByHashesMethod = chain33Grpc.getGetTransactionByHashesMethod) == null) {
                    chain33Grpc.getGetTransactionByHashesMethod = getGetTransactionByHashesMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTransactionByHashes"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetTransactionByHashes")).build();
                }
            }
        }
        return getGetTransactionByHashesMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getGetMemPoolMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetMemPool", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getGetMemPoolMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getGetMemPoolMethod;
        if ((getGetMemPoolMethod = chain33Grpc.getGetMemPoolMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetMemPoolMethod = chain33Grpc.getGetMemPoolMethod) == null) {
                    chain33Grpc.getGetMemPoolMethod = getGetMemPoolMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMemPool"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetMemPool")).build();
                }
            }
        }
        return getGetMemPoolMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> getGetAccountsMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetAccounts", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> getGetAccountsMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> getGetAccountsMethod;
        if ((getGetAccountsMethod = chain33Grpc.getGetAccountsMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetAccountsMethod = chain33Grpc.getGetAccountsMethod) == null) {
                    chain33Grpc.getGetAccountsMethod = getGetAccountsMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccounts"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetAccounts")).build();
                }
            }
        }
        return getGetAccountsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getGetAccountMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetAccount", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getGetAccountMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getGetAccountMethod;
        if ((getGetAccountMethod = chain33Grpc.getGetAccountMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetAccountMethod = chain33Grpc.getGetAccountMethod) == null) {
                    chain33Grpc.getGetAccountMethod = getGetAccountMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccount"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetAccount")).build();
                }
            }
        }
        return getGetAccountMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getNewAccountMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "NewAccount", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getNewAccountMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getNewAccountMethod;
        if ((getNewAccountMethod = chain33Grpc.getNewAccountMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getNewAccountMethod = chain33Grpc.getNewAccountMethod) == null) {
                    chain33Grpc.getNewAccountMethod = getNewAccountMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NewAccount"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("NewAccount")).build();
                }
            }
        }
        return getNewAccountMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> getWalletTransactionListMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "WalletTransactionList", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> getWalletTransactionListMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> getWalletTransactionListMethod;
        if ((getWalletTransactionListMethod = chain33Grpc.getWalletTransactionListMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getWalletTransactionListMethod = chain33Grpc.getWalletTransactionListMethod) == null) {
                    chain33Grpc.getWalletTransactionListMethod = getWalletTransactionListMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "WalletTransactionList"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("WalletTransactionList")).build();
                }
            }
        }
        return getWalletTransactionListMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getImportPrivkeyMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "ImportPrivkey", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getImportPrivkeyMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getImportPrivkeyMethod;
        if ((getImportPrivkeyMethod = chain33Grpc.getImportPrivkeyMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getImportPrivkeyMethod = chain33Grpc.getImportPrivkeyMethod) == null) {
                    chain33Grpc.getImportPrivkeyMethod = getImportPrivkeyMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportPrivkey"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("ImportPrivkey")).build();
                }
            }
        }
        return getImportPrivkeyMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getSendToAddressMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SendToAddress", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getSendToAddressMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getSendToAddressMethod;
        if ((getSendToAddressMethod = chain33Grpc.getSendToAddressMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSendToAddressMethod = chain33Grpc.getSendToAddressMethod) == null) {
                    chain33Grpc.getSendToAddressMethod = getSendToAddressMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendToAddress"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SendToAddress")).build();
                }
            }
        }
        return getSendToAddressMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSetTxFeeMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SetTxFee", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSetTxFeeMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSetTxFeeMethod;
        if ((getSetTxFeeMethod = chain33Grpc.getSetTxFeeMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSetTxFeeMethod = chain33Grpc.getSetTxFeeMethod) == null) {
                    chain33Grpc.getSetTxFeeMethod = getSetTxFeeMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetTxFee"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SetTxFee")).build();
                }
            }
        }
        return getSetTxFeeMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getSetLablMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SetLabl", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getSetLablMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getSetLablMethod;
        if ((getSetLablMethod = chain33Grpc.getSetLablMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSetLablMethod = chain33Grpc.getSetLablMethod) == null) {
                    chain33Grpc.getSetLablMethod = getSetLablMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetLabl"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SetLabl")).build();
                }
            }
        }
        return getSetLablMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> getMergeBalanceMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "MergeBalance", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> getMergeBalanceMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> getMergeBalanceMethod;
        if ((getMergeBalanceMethod = chain33Grpc.getMergeBalanceMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getMergeBalanceMethod = chain33Grpc.getMergeBalanceMethod) == null) {
                    chain33Grpc.getMergeBalanceMethod = getMergeBalanceMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "MergeBalance"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("MergeBalance")).build();
                }
            }
        }
        return getMergeBalanceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSetPasswdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SetPasswd", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSetPasswdMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSetPasswdMethod;
        if ((getSetPasswdMethod = chain33Grpc.getSetPasswdMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSetPasswdMethod = chain33Grpc.getSetPasswdMethod) == null) {
                    chain33Grpc.getSetPasswdMethod = getSetPasswdMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPasswd"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SetPasswd")).build();
                }
            }
        }
        return getSetPasswdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getLockMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "Lock", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getLockMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getLockMethod;
        if ((getLockMethod = chain33Grpc.getLockMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getLockMethod = chain33Grpc.getLockMethod) == null) {
                    chain33Grpc.getLockMethod = getLockMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Lock"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("Lock")).build();
                }
            }
        }
        return getLockMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getUnLockMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "UnLock", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getUnLockMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getUnLockMethod;
        if ((getUnLockMethod = chain33Grpc.getUnLockMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getUnLockMethod = chain33Grpc.getUnLockMethod) == null) {
                    chain33Grpc.getUnLockMethod = getUnLockMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnLock"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("UnLock")).build();
                }
            }
        }
        return getUnLockMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getGetLastMemPoolMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetLastMemPool", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getGetLastMemPoolMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getGetLastMemPoolMethod;
        if ((getGetLastMemPoolMethod = chain33Grpc.getGetLastMemPoolMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetLastMemPoolMethod = chain33Grpc.getGetLastMemPoolMethod) == null) {
                    chain33Grpc.getGetLastMemPoolMethod = getGetLastMemPoolMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLastMemPool"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetLastMemPool")).build();
                }
            }
        }
        return getGetLastMemPoolMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> getGetProperFeeMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetProperFee", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> getGetProperFeeMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> getGetProperFeeMethod;
        if ((getGetProperFeeMethod = chain33Grpc.getGetProperFeeMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetProperFeeMethod = chain33Grpc.getGetProperFeeMethod) == null) {
                    chain33Grpc.getGetProperFeeMethod = getGetProperFeeMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProperFee"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetProperFee")).build();
                }
            }
        }
        return getGetProperFeeMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> getGetWalletStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetWalletStatus", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> getGetWalletStatusMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> getGetWalletStatusMethod;
        if ((getGetWalletStatusMethod = chain33Grpc.getGetWalletStatusMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetWalletStatusMethod = chain33Grpc.getGetWalletStatusMethod) == null) {
                    chain33Grpc.getGetWalletStatusMethod = getGetWalletStatusMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetWalletStatus"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetWalletStatus")).build();
                }
            }
        }
        return getGetWalletStatusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> getGetBlockOverviewMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBlockOverview", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> getGetBlockOverviewMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> getGetBlockOverviewMethod;
        if ((getGetBlockOverviewMethod = chain33Grpc.getGetBlockOverviewMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetBlockOverviewMethod = chain33Grpc.getGetBlockOverviewMethod) == null) {
                    chain33Grpc.getGetBlockOverviewMethod = getGetBlockOverviewMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockOverview"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetBlockOverview")).build();
                }
            }
        }
        return getGetBlockOverviewMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> getGetAddrOverviewMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetAddrOverview", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> getGetAddrOverviewMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> getGetAddrOverviewMethod;
        if ((getGetAddrOverviewMethod = chain33Grpc.getGetAddrOverviewMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetAddrOverviewMethod = chain33Grpc.getGetAddrOverviewMethod) == null) {
                    chain33Grpc.getGetAddrOverviewMethod = getGetAddrOverviewMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAddrOverview"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetAddrOverview")).build();
                }
            }
        }
        return getGetAddrOverviewMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getGetBlockHashMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBlockHash", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getGetBlockHashMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getGetBlockHashMethod;
        if ((getGetBlockHashMethod = chain33Grpc.getGetBlockHashMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetBlockHashMethod = chain33Grpc.getGetBlockHashMethod) == null) {
                    chain33Grpc.getGetBlockHashMethod = getGetBlockHashMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockHash"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetBlockHash")).build();
                }
            }
        }
        return getGetBlockHashMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getGenSeedMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GenSeed", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getGenSeedMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getGenSeedMethod;
        if ((getGenSeedMethod = chain33Grpc.getGenSeedMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGenSeedMethod = chain33Grpc.getGenSeedMethod) == null) {
                    chain33Grpc.getGenSeedMethod = getGenSeedMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GenSeed"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GenSeed")).build();
                }
            }
        }
        return getGenSeedMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getGetSeedMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetSeed", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getGetSeedMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getGetSeedMethod;
        if ((getGetSeedMethod = chain33Grpc.getGetSeedMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetSeedMethod = chain33Grpc.getGetSeedMethod) == null) {
                    chain33Grpc.getGetSeedMethod = getGetSeedMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSeed"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetSeed")).build();
                }
            }
        }
        return getGetSeedMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSaveSeedMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SaveSeed", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSaveSeedMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getSaveSeedMethod;
        if ((getSaveSeedMethod = chain33Grpc.getSaveSeedMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSaveSeedMethod = chain33Grpc.getSaveSeedMethod) == null) {
                    chain33Grpc.getSaveSeedMethod = getSaveSeedMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveSeed"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SaveSeed")).build();
                }
            }
        }
        return getSaveSeedMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getGetBalanceMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBalance", requestType = cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance.class, responseType = cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getGetBalanceMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getGetBalanceMethod;
        if ((getGetBalanceMethod = chain33Grpc.getGetBalanceMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetBalanceMethod = chain33Grpc.getGetBalanceMethod) == null) {
                    chain33Grpc.getGetBalanceMethod = getGetBalanceMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetBalance")).build();
                }
            }
        }
        return getGetBalanceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getQueryChainMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "QueryChain", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getQueryChainMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getQueryChainMethod;
        if ((getQueryChainMethod = chain33Grpc.getQueryChainMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getQueryChainMethod = chain33Grpc.getQueryChainMethod) == null) {
                    chain33Grpc.getQueryChainMethod = getQueryChainMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryChain"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("QueryChain")).build();
                }
            }
        }
        return getQueryChainMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getExecWalletMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "ExecWallet", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getExecWalletMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getExecWalletMethod;
        if ((getExecWalletMethod = chain33Grpc.getExecWalletMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getExecWalletMethod = chain33Grpc.getExecWalletMethod) == null) {
                    chain33Grpc.getExecWalletMethod = getExecWalletMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecWallet"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("ExecWallet")).build();
                }
            }
        }
        return getExecWalletMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getQueryConsensusMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "QueryConsensus", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getQueryConsensusMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getQueryConsensusMethod;
        if ((getQueryConsensusMethod = chain33Grpc.getQueryConsensusMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getQueryConsensusMethod = chain33Grpc.getQueryConsensusMethod) == null) {
                    chain33Grpc.getQueryConsensusMethod = getQueryConsensusMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryConsensus"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("QueryConsensus")).build();
                }
            }
        }
        return getQueryConsensusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateTransactionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CreateTransaction", requestType = cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateTransactionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> getCreateTransactionMethod;
        if ((getCreateTransactionMethod = chain33Grpc.getCreateTransactionMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getCreateTransactionMethod = chain33Grpc.getCreateTransactionMethod) == null) {
                    chain33Grpc.getCreateTransactionMethod = getCreateTransactionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTransaction"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("CreateTransaction")).build();
                }
            }
        }
        return getCreateTransactionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> getGetHexTxByHashMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetHexTxByHash", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.class, responseType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> getGetHexTxByHashMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> getGetHexTxByHashMethod;
        if ((getGetHexTxByHashMethod = chain33Grpc.getGetHexTxByHashMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetHexTxByHashMethod = chain33Grpc.getGetHexTxByHashMethod) == null) {
                    chain33Grpc.getGetHexTxByHashMethod = getGetHexTxByHashMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetHexTxByHash"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetHexTxByHash")).build();
                }
            }
        }
        return getGetHexTxByHashMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> getDumpPrivkeyMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "DumpPrivkey", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> getDumpPrivkeyMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> getDumpPrivkeyMethod;
        if ((getDumpPrivkeyMethod = chain33Grpc.getDumpPrivkeyMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getDumpPrivkeyMethod = chain33Grpc.getDumpPrivkeyMethod) == null) {
                    chain33Grpc.getDumpPrivkeyMethod = getDumpPrivkeyMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DumpPrivkey"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("DumpPrivkey")).build();
                }
            }
        }
        return getDumpPrivkeyMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getDumpPrivkeysFileMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "DumpPrivkeysFile", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getDumpPrivkeysFileMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getDumpPrivkeysFileMethod;
        if ((getDumpPrivkeysFileMethod = chain33Grpc.getDumpPrivkeysFileMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getDumpPrivkeysFileMethod = chain33Grpc.getDumpPrivkeysFileMethod) == null) {
                    chain33Grpc.getDumpPrivkeysFileMethod = getDumpPrivkeysFileMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DumpPrivkeysFile"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("DumpPrivkeysFile")).build();
                }
            }
        }
        return getDumpPrivkeysFileMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getImportPrivkeysFileMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "ImportPrivkeysFile", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getImportPrivkeysFileMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getImportPrivkeysFileMethod;
        if ((getImportPrivkeysFileMethod = chain33Grpc.getImportPrivkeysFileMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getImportPrivkeysFileMethod = chain33Grpc.getImportPrivkeysFileMethod) == null) {
                    chain33Grpc.getImportPrivkeysFileMethod = getImportPrivkeysFileMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ImportPrivkeysFile"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("ImportPrivkeysFile")).build();
                }
            }
        }
        return getImportPrivkeysFileMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> getVersionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "Version", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> getVersionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> getVersionMethod;
        if ((getVersionMethod = chain33Grpc.getVersionMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getVersionMethod = chain33Grpc.getVersionMethod) == null) {
                    chain33Grpc.getVersionMethod = getVersionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Version"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("Version")).build();
                }
            }
        }
        return getVersionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getIsSyncMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "IsSync", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getIsSyncMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getIsSyncMethod;
        if ((getIsSyncMethod = chain33Grpc.getIsSyncMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getIsSyncMethod = chain33Grpc.getIsSyncMethod) == null) {
                    chain33Grpc.getIsSyncMethod = getIsSyncMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IsSync"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("IsSync")).build();
                }
            }
        }
        return getIsSyncMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getGetPeerInfoMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetPeerInfo", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.PeerList.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getGetPeerInfoMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getGetPeerInfoMethod;
        if ((getGetPeerInfoMethod = chain33Grpc.getGetPeerInfoMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetPeerInfoMethod = chain33Grpc.getGetPeerInfoMethod) == null) {
                    chain33Grpc.getGetPeerInfoMethod = getGetPeerInfoMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq, cn.chain33.javasdk.model.protobuf.P2pService.PeerList> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPeerInfo"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.PeerList.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetPeerInfo")).build();
                }
            }
        }
        return getGetPeerInfoMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq, cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> getNetInfoMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "NetInfo", requestType = cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq.class, responseType = cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq, cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> getNetInfoMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq, cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> getNetInfoMethod;
        if ((getNetInfoMethod = chain33Grpc.getNetInfoMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getNetInfoMethod = chain33Grpc.getNetInfoMethod) == null) {
                    chain33Grpc.getNetInfoMethod = getNetInfoMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq, cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NetInfo"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("NetInfo")).build();
                }
            }
        }
        return getNetInfoMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getIsNtpClockSyncMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "IsNtpClockSync", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getIsNtpClockSyncMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getIsNtpClockSyncMethod;
        if ((getIsNtpClockSyncMethod = chain33Grpc.getIsNtpClockSyncMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getIsNtpClockSyncMethod = chain33Grpc.getIsNtpClockSyncMethod) == null) {
                    chain33Grpc.getIsNtpClockSyncMethod = getIsNtpClockSyncMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IsNtpClockSync"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("IsNtpClockSync")).build();
                }
            }
        }
        return getIsNtpClockSyncMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> getGetFatalFailureMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetFatalFailure", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> getGetFatalFailureMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> getGetFatalFailureMethod;
        if ((getGetFatalFailureMethod = chain33Grpc.getGetFatalFailureMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetFatalFailureMethod = chain33Grpc.getGetFatalFailureMethod) == null) {
                    chain33Grpc.getGetFatalFailureMethod = getGetFatalFailureMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFatalFailure"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetFatalFailure")).build();
                }
            }
        }
        return getGetFatalFailureMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetLastBlockSequenceMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetLastBlockSequence", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetLastBlockSequenceMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetLastBlockSequenceMethod;
        if ((getGetLastBlockSequenceMethod = chain33Grpc.getGetLastBlockSequenceMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetLastBlockSequenceMethod = chain33Grpc.getGetLastBlockSequenceMethod) == null) {
                    chain33Grpc.getGetLastBlockSequenceMethod = getGetLastBlockSequenceMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLastBlockSequence"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetLastBlockSequence")).build();
                }
            }
        }
        return getGetLastBlockSequenceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetSequenceByHashMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetSequenceByHash", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetSequenceByHashMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetSequenceByHashMethod;
        if ((getGetSequenceByHashMethod = chain33Grpc.getGetSequenceByHashMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetSequenceByHashMethod = chain33Grpc.getGetSequenceByHashMethod) == null) {
                    chain33Grpc.getGetSequenceByHashMethod = getGetSequenceByHashMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSequenceByHash"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetSequenceByHash")).build();
                }
            }
        }
        return getGetSequenceByHashMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> getGetBlockByHashesMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBlockByHashes", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> getGetBlockByHashesMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> getGetBlockByHashesMethod;
        if ((getGetBlockByHashesMethod = chain33Grpc.getGetBlockByHashesMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetBlockByHashesMethod = chain33Grpc.getGetBlockByHashesMethod) == null) {
                    chain33Grpc.getGetBlockByHashesMethod = getGetBlockByHashesMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockByHashes"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetBlockByHashes")).build();
                }
            }
        }
        return getGetBlockByHashesMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> getGetBlockBySeqMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetBlockBySeq", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> getGetBlockBySeqMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> getGetBlockBySeqMethod;
        if ((getGetBlockBySeqMethod = chain33Grpc.getGetBlockBySeqMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetBlockBySeqMethod = chain33Grpc.getGetBlockBySeqMethod) == null) {
                    chain33Grpc.getGetBlockBySeqMethod = getGetBlockBySeqMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockBySeq"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetBlockBySeq")).build();
                }
            }
        }
        return getGetBlockBySeqMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getCloseQueueMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CloseQueue", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getCloseQueueMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getCloseQueueMethod;
        if ((getCloseQueueMethod = chain33Grpc.getCloseQueueMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getCloseQueueMethod = chain33Grpc.getCloseQueueMethod) == null) {
                    chain33Grpc.getCloseQueueMethod = getCloseQueueMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CloseQueue"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("CloseQueue")).build();
                }
            }
        }
        return getCloseQueueMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> getGetAllExecBalanceMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetAllExecBalance", requestType = cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance.class, responseType = cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> getGetAllExecBalanceMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> getGetAllExecBalanceMethod;
        if ((getGetAllExecBalanceMethod = chain33Grpc.getGetAllExecBalanceMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetAllExecBalanceMethod = chain33Grpc.getGetAllExecBalanceMethod) == null) {
                    chain33Grpc.getGetAllExecBalanceMethod = getGetAllExecBalanceMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllExecBalance"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetAllExecBalance")).build();
                }
            }
        }
        return getGetAllExecBalanceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getSignRawTxMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "SignRawTx", requestType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getSignRawTxMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getSignRawTxMethod;
        if ((getSignRawTxMethod = chain33Grpc.getSignRawTxMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getSignRawTxMethod = chain33Grpc.getSignRawTxMethod) == null) {
                    chain33Grpc.getSignRawTxMethod = getSignRawTxMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SignRawTx"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("SignRawTx")).build();
                }
            }
        }
        return getSignRawTxMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getCreateNoBalanceTransactionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CreateNoBalanceTransaction", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getCreateNoBalanceTransactionMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getCreateNoBalanceTransactionMethod;
        if ((getCreateNoBalanceTransactionMethod = chain33Grpc.getCreateNoBalanceTransactionMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getCreateNoBalanceTransactionMethod = chain33Grpc.getCreateNoBalanceTransactionMethod) == null) {
                    chain33Grpc.getCreateNoBalanceTransactionMethod = getCreateNoBalanceTransactionMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateNoBalanceTransaction"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("CreateNoBalanceTransaction"))
                            .build();
                }
            }
        }
        return getCreateNoBalanceTransactionMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getQueryRandNumMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "QueryRandNum", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getQueryRandNumMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getQueryRandNumMethod;
        if ((getQueryRandNumMethod = chain33Grpc.getQueryRandNumMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getQueryRandNumMethod = chain33Grpc.getQueryRandNumMethod) == null) {
                    chain33Grpc.getQueryRandNumMethod = getQueryRandNumMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryRandNum"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("QueryRandNum")).build();
                }
            }
        }
        return getQueryRandNumMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetForkMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetFork", requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey.class, responseType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetForkMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getGetForkMethod;
        if ((getGetForkMethod = chain33Grpc.getGetForkMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetForkMethod = chain33Grpc.getGetForkMethod) == null) {
                    chain33Grpc.getGetForkMethod = getGetForkMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFork"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetFork")).build();
                }
            }
        }
        return getGetForkMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getCreateNoBalanceTxsMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "CreateNoBalanceTxs", requestType = cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs.class, responseType = cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getCreateNoBalanceTxsMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> getCreateNoBalanceTxsMethod;
        if ((getCreateNoBalanceTxsMethod = chain33Grpc.getCreateNoBalanceTxsMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getCreateNoBalanceTxsMethod = chain33Grpc.getCreateNoBalanceTxsMethod) == null) {
                    chain33Grpc.getCreateNoBalanceTxsMethod = getCreateNoBalanceTxsMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateNoBalanceTxs"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("CreateNoBalanceTxs")).build();
                }
            }
        }
        return getCreateNoBalanceTxsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getGetParaTxByTitleMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetParaTxByTitle", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getGetParaTxByTitleMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getGetParaTxByTitleMethod;
        if ((getGetParaTxByTitleMethod = chain33Grpc.getGetParaTxByTitleMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetParaTxByTitleMethod = chain33Grpc.getGetParaTxByTitleMethod) == null) {
                    chain33Grpc.getGetParaTxByTitleMethod = getGetParaTxByTitleMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetParaTxByTitle"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetParaTxByTitle")).build();
                }
            }
        }
        return getGetParaTxByTitleMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> getLoadParaTxByTitleMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "LoadParaTxByTitle", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> getLoadParaTxByTitleMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> getLoadParaTxByTitleMethod;
        if ((getLoadParaTxByTitleMethod = chain33Grpc.getLoadParaTxByTitleMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getLoadParaTxByTitleMethod = chain33Grpc.getLoadParaTxByTitleMethod) == null) {
                    chain33Grpc.getLoadParaTxByTitleMethod = getLoadParaTxByTitleMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LoadParaTxByTitle"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("LoadParaTxByTitle")).build();
                }
            }
        }
        return getLoadParaTxByTitleMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getGetParaTxByHeightMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetParaTxByHeight", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getGetParaTxByHeightMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getGetParaTxByHeightMethod;
        if ((getGetParaTxByHeightMethod = chain33Grpc.getGetParaTxByHeightMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetParaTxByHeightMethod = chain33Grpc.getGetParaTxByHeightMethod) == null) {
                    chain33Grpc.getGetParaTxByHeightMethod = getGetParaTxByHeightMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetParaTxByHeight"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails
                                            .getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetParaTxByHeight")).build();
                }
            }
        }
        return getGetParaTxByHeightMethod;
    }

    private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> getGetHeadersMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/'
            + "GetHeaders", requestType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks.class, responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> getGetHeadersMethod() {
        io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> getGetHeadersMethod;
        if ((getGetHeadersMethod = chain33Grpc.getGetHeadersMethod) == null) {
            synchronized (chain33Grpc.class) {
                if ((getGetHeadersMethod = chain33Grpc.getGetHeadersMethod) == null) {
                    chain33Grpc.getGetHeadersMethod = getGetHeadersMethod = io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetHeaders"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils
                                    .marshaller(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks
                                            .getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                    cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers.getDefaultInstance()))
                            .setSchemaDescriptor(new chain33MethodDescriptorSupplier("GetHeaders")).build();
                }
            }
        }
        return getGetHeadersMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static chain33Stub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<chain33Stub> factory = new io.grpc.stub.AbstractStub.StubFactory<chain33Stub>() {
            @java.lang.Override
            public chain33Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new chain33Stub(channel, callOptions);
            }
        };
        return chain33Stub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static chain33BlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<chain33BlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<chain33BlockingStub>() {
            @java.lang.Override
            public chain33BlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new chain33BlockingStub(channel, callOptions);
            }
        };
        return chain33BlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static chain33FutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<chain33FutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<chain33FutureStub>() {
            @java.lang.Override
            public chain33FutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new chain33FutureStub(channel, callOptions);
            }
        };
        return chain33FutureStub.newStub(factory, channel);
    }

    /**
     */
    public static abstract class chain33ImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * chain33 对外提供服务的接口
         *区块链接口
         * </pre>
         */
        public void getBlocks(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlocksMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取最新的区块头
         * </pre>
         */
        public void getLastHeader(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> responseObserver) {
            asyncUnimplementedUnaryCall(getGetLastHeaderMethod(), responseObserver);
        }

        /**
         * <pre>
         * 交易接口
         * </pre>
         */
        public void createRawTransaction(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> responseObserver) {
            asyncUnimplementedUnaryCall(getCreateRawTransactionMethod(), responseObserver);
        }

        /**
         */
        public void createRawTxGroup(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> responseObserver) {
            asyncUnimplementedUnaryCall(getCreateRawTxGroupMethod(), responseObserver);
        }

        /**
         * <pre>
         * 根据哈希查询交易
         * </pre>
         */
        public void queryTransaction(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryTransactionMethod(), responseObserver);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public void sendTransaction(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getSendTransactionMethod(), responseObserver);
        }

        /**
         * <pre>
         * 通过地址获取交易信息
         * </pre>
         */
        public void getTransactionByAddr(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> responseObserver) {
            asyncUnimplementedUnaryCall(getGetTransactionByAddrMethod(), responseObserver);
        }

        /**
         * <pre>
         * 通过哈希数组获取对应的交易
         * </pre>
         */
        public void getTransactionByHashes(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> responseObserver) {
            asyncUnimplementedUnaryCall(getGetTransactionByHashesMethod(), responseObserver);
        }

        /**
         * <pre>
         * 缓存接口
         * </pre>
         */
        public void getMemPool(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> responseObserver) {
            asyncUnimplementedUnaryCall(getGetMemPoolMethod(), responseObserver);
        }

        /**
         * <pre>
         *钱包接口
         *获取钱包账户信息
         * </pre>
         */
        public void getAccounts(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAccountsMethod(), responseObserver);
        }

        /**
         * <pre>
         * 根据账户lable信息获取账户地址
         * </pre>
         */
        public void getAccount(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAccountMethod(), responseObserver);
        }

        /**
         * <pre>
         * 创建钱包账户
         * </pre>
         */
        public void newAccount(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnimplementedUnaryCall(getNewAccountMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取钱包的交易列表
         * </pre>
         */
        public void walletTransactionList(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> responseObserver) {
            asyncUnimplementedUnaryCall(getWalletTransactionListMethod(), responseObserver);
        }

        /**
         * <pre>
         * 导入钱包私钥
         * </pre>
         */
        public void importPrivkey(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnimplementedUnaryCall(getImportPrivkeyMethod(), responseObserver);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public void sendToAddress(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> responseObserver) {
            asyncUnimplementedUnaryCall(getSendToAddressMethod(), responseObserver);
        }

        /**
         * <pre>
         * 设置交易手续费
         * </pre>
         */
        public void setTxFee(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getSetTxFeeMethod(), responseObserver);
        }

        /**
         * <pre>
         * 设置标签
         * </pre>
         */
        public void setLabl(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnimplementedUnaryCall(getSetLablMethod(), responseObserver);
        }

        /**
         * <pre>
         * 合并钱包余额
         * </pre>
         */
        public void mergeBalance(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> responseObserver) {
            asyncUnimplementedUnaryCall(getMergeBalanceMethod(), responseObserver);
        }

        /**
         * <pre>
         * 设置钱包密码
         * </pre>
         */
        public void setPasswd(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getSetPasswdMethod(), responseObserver);
        }

        /**
         * <pre>
         * 给钱包上锁
         * </pre>
         */
        public void lock(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getLockMethod(), responseObserver);
        }

        /**
         * <pre>
         * 给钱包解锁
         * </pre>
         */
        public void unLock(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getUnLockMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取最新的Mempool
         * </pre>
         */
        public void getLastMemPool(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> responseObserver) {
            asyncUnimplementedUnaryCall(getGetLastMemPoolMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取最新的ProperFee
         * </pre>
         */
        public void getProperFee(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> responseObserver) {
            asyncUnimplementedUnaryCall(getGetProperFeeMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取钱包状态
         * </pre>
         */
        public void getWalletStatus(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetWalletStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         *区块浏览器接口
         * /
         * </pre>
         */
        public void getBlockOverview(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockOverviewMethod(), responseObserver);
        }

        /**
         */
        public void getAddrOverview(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAddrOverviewMethod(), responseObserver);
        }

        /**
         */
        public void getBlockHash(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockHashMethod(), responseObserver);
        }

        /**
         * <pre>
         * seed
         * 创建seed
         * </pre>
         */
        public void genSeed(cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> responseObserver) {
            asyncUnimplementedUnaryCall(getGenSeedMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取seed
         * </pre>
         */
        public void getSeed(cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> responseObserver) {
            asyncUnimplementedUnaryCall(getGetSeedMethod(), responseObserver);
        }

        /**
         * <pre>
         * 保存seed
         * </pre>
         */
        public void saveSeed(cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getSaveSeedMethod(), responseObserver);
        }

        /**
         * <pre>
         * Balance Query
         *获取余额
         * </pre>
         */
        public void getBalance(cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
        }

        /**
         */
        public void queryChain(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryChainMethod(), responseObserver);
        }

        /**
         */
        public void execWallet(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getExecWalletMethod(), responseObserver);
        }

        /**
         */
        public void queryConsensus(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryConsensusMethod(), responseObserver);
        }

        /**
         */
        public void createTransaction(cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> responseObserver) {
            asyncUnimplementedUnaryCall(getCreateTransactionMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取交易的十六进制编码
         * </pre>
         */
        public void getHexTxByHash(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> responseObserver) {
            asyncUnimplementedUnaryCall(getGetHexTxByHashMethod(), responseObserver);
        }

        /**
         * <pre>
         * 导出私钥
         * </pre>
         */
        public void dumpPrivkey(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> responseObserver) {
            asyncUnimplementedUnaryCall(getDumpPrivkeyMethod(), responseObserver);
        }

        /**
         * <pre>
         * 导出全部私钥到文件
         * </pre>
         */
        public void dumpPrivkeysFile(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getDumpPrivkeysFileMethod(), responseObserver);
        }

        /**
         * <pre>
         * 从文件中批量导入私钥
         * </pre>
         */
        public void importPrivkeysFile(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getImportPrivkeysFileMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取程序版本
         * </pre>
         */
        public void version(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> responseObserver) {
            asyncUnimplementedUnaryCall(getVersionMethod(), responseObserver);
        }

        /**
         * <pre>
         * 是否同步
         * </pre>
         */
        public void isSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getIsSyncMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取当前节点连接的其他节点信息
         * </pre>
         */
        public void getPeerInfo(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeerList> responseObserver) {
            asyncUnimplementedUnaryCall(getGetPeerInfoMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取当前节点的网络信息
         * </pre>
         */
        public void netInfo(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> responseObserver) {
            asyncUnimplementedUnaryCall(getNetInfoMethod(), responseObserver);
        }

        /**
         * <pre>
         * ntpclock是否同步
         * </pre>
         */
        public void isNtpClockSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getIsNtpClockSyncMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取系统致命故障信息
         * </pre>
         */
        public void getFatalFailure(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> responseObserver) {
            asyncUnimplementedUnaryCall(getGetFatalFailureMethod(), responseObserver);
        }

        /**
         */
        public void getLastBlockSequence(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> responseObserver) {
            asyncUnimplementedUnaryCall(getGetLastBlockSequenceMethod(), responseObserver);
        }

        /**
         * <pre>
         * get add block's sequence by hash
         * </pre>
         */
        public void getSequenceByHash(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> responseObserver) {
            asyncUnimplementedUnaryCall(getGetSequenceByHashMethod(), responseObserver);
        }

        /**
         * <pre>
         *通过block hash 获取对应的blocks信息
         * </pre>
         */
        public void getBlockByHashes(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockByHashesMethod(), responseObserver);
        }

        /**
         * <pre>
         *通过block seq 获取对应的blocks hash 信息
         * </pre>
         */
        public void getBlockBySeq(cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockBySeqMethod(), responseObserver);
        }

        /**
         * <pre>
         * 关闭chain33
         * </pre>
         */
        public void closeQueue(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnimplementedUnaryCall(getCloseQueueMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取地址所以合约下的余额
         * </pre>
         */
        public void getAllExecBalance(cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAllExecBalanceMethod(), responseObserver);
        }

        /**
         * <pre>
         * 签名交易
         * </pre>
         */
        public void signRawTx(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> responseObserver) {
            asyncUnimplementedUnaryCall(getSignRawTxMethod(), responseObserver);
        }

        /**
         */
        public void createNoBalanceTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> responseObserver) {
            asyncUnimplementedUnaryCall(getCreateNoBalanceTransactionMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取随机HASH
         * </pre>
         */
        public void queryRandNum(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryRandNumMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取是否达到fork高度
         * </pre>
         */
        public void getFork(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> responseObserver) {
            asyncUnimplementedUnaryCall(getGetForkMethod(), responseObserver);
        }

        /**
         */
        public void createNoBalanceTxs(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> responseObserver) {
            asyncUnimplementedUnaryCall(getCreateNoBalanceTxsMethod(), responseObserver);
        }

        /**
         * <pre>
         * 通过seq以及title获取对应平行连的交易
         * </pre>
         */
        public void getParaTxByTitle(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> responseObserver) {
            asyncUnimplementedUnaryCall(getGetParaTxByTitleMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取拥有此title交易的区块高度
         * </pre>
         */
        public void loadParaTxByTitle(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> responseObserver) {
            asyncUnimplementedUnaryCall(getLoadParaTxByTitleMethod(), responseObserver);
        }

        /**
         * <pre>
         * 通过区块高度列表 + title获取平行链交易
         * </pre>
         */
        public void getParaTxByHeight(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> responseObserver) {
            asyncUnimplementedUnaryCall(getGetParaTxByHeightMethod(), responseObserver);
        }

        /**
         * <pre>
         * 获取区块头信息
         * </pre>
         */
        public void getHeaders(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> responseObserver) {
            asyncUnimplementedUnaryCall(getGetHeadersMethod(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(getGetBlocksMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_GET_BLOCKS)))
                    .addMethod(getGetLastHeaderMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header>(
                                    this, METHODID_GET_LAST_HEADER)))
                    .addMethod(getCreateRawTransactionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx>(
                                    this, METHODID_CREATE_RAW_TRANSACTION)))
                    .addMethod(getCreateRawTxGroupMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx>(
                                    this, METHODID_CREATE_RAW_TX_GROUP)))
                    .addMethod(getQueryTransactionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail>(
                                    this, METHODID_QUERY_TRANSACTION)))
                    .addMethod(getSendTransactionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_SEND_TRANSACTION)))
                    .addMethod(getGetTransactionByAddrMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos>(
                                    this, METHODID_GET_TRANSACTION_BY_ADDR)))
                    .addMethod(getGetTransactionByHashesMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails>(
                                    this, METHODID_GET_TRANSACTION_BY_HASHES)))
                    .addMethod(getGetMemPoolMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList>(
                                    this, METHODID_GET_MEM_POOL)))
                    .addMethod(getGetAccountsMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts>(
                                    this, METHODID_GET_ACCOUNTS)))
                    .addMethod(getGetAccountMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>(
                                    this, METHODID_GET_ACCOUNT)))
                    .addMethod(getNewAccountMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>(
                                    this, METHODID_NEW_ACCOUNT)))
                    .addMethod(getWalletTransactionListMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails>(
                                    this, METHODID_WALLET_TRANSACTION_LIST)))
                    .addMethod(getImportPrivkeyMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>(
                                    this, METHODID_IMPORT_PRIVKEY)))
                    .addMethod(getSendToAddressMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash>(
                                    this, METHODID_SEND_TO_ADDRESS)))
                    .addMethod(getSetTxFeeMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_SET_TX_FEE)))
                    .addMethod(getSetLablMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>(
                                    this, METHODID_SET_LABL)))
                    .addMethod(getMergeBalanceMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes>(
                                    this, METHODID_MERGE_BALANCE)))
                    .addMethod(getSetPasswdMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_SET_PASSWD)))
                    .addMethod(getLockMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_LOCK)))
                    .addMethod(getUnLockMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_UN_LOCK)))
                    .addMethod(getGetLastMemPoolMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList>(
                                    this, METHODID_GET_LAST_MEM_POOL)))
                    .addMethod(getGetProperFeeMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee>(
                                    this, METHODID_GET_PROPER_FEE)))
                    .addMethod(getGetWalletStatusMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus>(
                                    this, METHODID_GET_WALLET_STATUS)))
                    .addMethod(getGetBlockOverviewMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview>(
                                    this, METHODID_GET_BLOCK_OVERVIEW)))
                    .addMethod(getGetAddrOverviewMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview>(
                                    this, METHODID_GET_ADDR_OVERVIEW)))
                    .addMethod(getGetBlockHashMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash>(
                                    this, METHODID_GET_BLOCK_HASH)))
                    .addMethod(getGenSeedMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed>(
                                    this, METHODID_GEN_SEED)))
                    .addMethod(getGetSeedMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed>(
                                    this, METHODID_GET_SEED)))
                    .addMethod(getSaveSeedMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_SAVE_SEED)))
                    .addMethod(getGetBalanceMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts>(
                                    this, METHODID_GET_BALANCE)))
                    .addMethod(getQueryChainMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_QUERY_CHAIN)))
                    .addMethod(getExecWalletMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_EXEC_WALLET)))
                    .addMethod(getQueryConsensusMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_QUERY_CONSENSUS)))
                    .addMethod(getCreateTransactionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx>(
                                    this, METHODID_CREATE_TRANSACTION)))
                    .addMethod(getGetHexTxByHashMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx>(
                                    this, METHODID_GET_HEX_TX_BY_HASH)))
                    .addMethod(getDumpPrivkeyMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString>(
                                    this, METHODID_DUMP_PRIVKEY)))
                    .addMethod(getDumpPrivkeysFileMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_DUMP_PRIVKEYS_FILE)))
                    .addMethod(getImportPrivkeysFileMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_IMPORT_PRIVKEYS_FILE)))
                    .addMethod(getVersionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo>(
                                    this, METHODID_VERSION)))
                    .addMethod(getIsSyncMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_IS_SYNC)))
                    .addMethod(getGetPeerInfoMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq, cn.chain33.javasdk.model.protobuf.P2pService.PeerList>(
                                    this, METHODID_GET_PEER_INFO)))
                    .addMethod(getNetInfoMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq, cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo>(
                                    this, METHODID_NET_INFO)))
                    .addMethod(getIsNtpClockSyncMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_IS_NTP_CLOCK_SYNC)))
                    .addMethod(getGetFatalFailureMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32>(
                                    this, METHODID_GET_FATAL_FAILURE)))
                    .addMethod(getGetLastBlockSequenceMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64>(
                                    this, METHODID_GET_LAST_BLOCK_SEQUENCE)))
                    .addMethod(getGetSequenceByHashMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64>(
                                    this, METHODID_GET_SEQUENCE_BY_HASH)))
                    .addMethod(getGetBlockByHashesMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails>(
                                    this, METHODID_GET_BLOCK_BY_HASHES)))
                    .addMethod(getGetBlockBySeqMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq>(
                                    this, METHODID_GET_BLOCK_BY_SEQ)))
                    .addMethod(getCloseQueueMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>(
                                    this, METHODID_CLOSE_QUEUE)))
                    .addMethod(getGetAllExecBalanceMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance>(
                                    this, METHODID_GET_ALL_EXEC_BALANCE)))
                    .addMethod(getSignRawTxMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx>(
                                    this, METHODID_SIGN_RAW_TX)))
                    .addMethod(getCreateNoBalanceTransactionMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx>(
                                    this, METHODID_CREATE_NO_BALANCE_TRANSACTION)))
                    .addMethod(getQueryRandNumMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash, cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash>(
                                    this, METHODID_QUERY_RAND_NUM)))
                    .addMethod(getGetForkMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey, cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64>(
                                    this, METHODID_GET_FORK)))
                    .addMethod(getCreateNoBalanceTxsMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs, cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx>(
                                    this, METHODID_CREATE_NO_BALANCE_TXS)))
                    .addMethod(getGetParaTxByTitleMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails>(
                                    this, METHODID_GET_PARA_TX_BY_TITLE)))
                    .addMethod(getLoadParaTxByTitleMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle>(
                                    this, METHODID_LOAD_PARA_TX_BY_TITLE)))
                    .addMethod(getGetParaTxByHeightMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails>(
                                    this, METHODID_GET_PARA_TX_BY_HEIGHT)))
                    .addMethod(getGetHeadersMethod(), asyncUnaryCall(
                            new MethodHandlers<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers>(
                                    this, METHODID_GET_HEADERS)))
                    .build();
        }
    }

    /**
     */
    public static final class chain33Stub extends io.grpc.stub.AbstractAsyncStub<chain33Stub> {
        private chain33Stub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected chain33Stub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new chain33Stub(channel, callOptions);
        }

        /**
         * <pre>
         * chain33 对外提供服务的接口
         *区块链接口
         * </pre>
         */
        public void getBlocks(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取最新的区块头
         * </pre>
         */
        public void getLastHeader(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetLastHeaderMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 交易接口
         * </pre>
         */
        public void createRawTransaction(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCreateRawTransactionMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         */
        public void createRawTxGroup(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCreateRawTxGroupMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 根据哈希查询交易
         * </pre>
         */
        public void queryTransaction(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getQueryTransactionMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public void sendTransaction(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 通过地址获取交易信息
         * </pre>
         */
        public void getTransactionByAddr(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetTransactionByAddrMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 通过哈希数组获取对应的交易
         * </pre>
         */
        public void getTransactionByHashes(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetTransactionByHashesMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 缓存接口
         * </pre>
         */
        public void getMemPool(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetMemPoolMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *钱包接口
         *获取钱包账户信息
         * </pre>
         */
        public void getAccounts(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetAccountsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 根据账户lable信息获取账户地址
         * </pre>
         */
        public void getAccount(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetAccountMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 创建钱包账户
         * </pre>
         */
        public void newAccount(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getNewAccountMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取钱包的交易列表
         * </pre>
         */
        public void walletTransactionList(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getWalletTransactionListMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 导入钱包私钥
         * </pre>
         */
        public void importPrivkey(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getImportPrivkeyMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public void sendToAddress(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSendToAddressMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 设置交易手续费
         * </pre>
         */
        public void setTxFee(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSetTxFeeMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 设置标签
         * </pre>
         */
        public void setLabl(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSetLablMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 合并钱包余额
         * </pre>
         */
        public void mergeBalance(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getMergeBalanceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 设置钱包密码
         * </pre>
         */
        public void setPasswd(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSetPasswdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 给钱包上锁
         * </pre>
         */
        public void lock(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getLockMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 给钱包解锁
         * </pre>
         */
        public void unLock(cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getUnLockMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取最新的Mempool
         * </pre>
         */
        public void getLastMemPool(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetLastMemPoolMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取最新的ProperFee
         * </pre>
         */
        public void getProperFee(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetProperFeeMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取钱包状态
         * </pre>
         */
        public void getWalletStatus(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetWalletStatusMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         *区块浏览器接口
         * /
         * </pre>
         */
        public void getBlockOverview(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBlockOverviewMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         */
        public void getAddrOverview(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetAddrOverviewMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         */
        public void getBlockHash(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBlockHashMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * seed
         * 创建seed
         * </pre>
         */
        public void genSeed(cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGenSeedMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取seed
         * </pre>
         */
        public void getSeed(cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetSeedMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 保存seed
         * </pre>
         */
        public void saveSeed(cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSaveSeedMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Balance Query
         *获取余额
         * </pre>
         */
        public void getBalance(cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void queryChain(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getQueryChainMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void execWallet(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getExecWalletMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void queryConsensus(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getQueryConsensusMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         */
        public void createTransaction(cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCreateTransactionMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取交易的十六进制编码
         * </pre>
         */
        public void getHexTxByHash(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetHexTxByHashMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 导出私钥
         * </pre>
         */
        public void dumpPrivkey(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getDumpPrivkeyMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 导出全部私钥到文件
         * </pre>
         */
        public void dumpPrivkeysFile(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getDumpPrivkeysFileMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 从文件中批量导入私钥
         * </pre>
         */
        public void importPrivkeysFile(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getImportPrivkeysFileMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取程序版本
         * </pre>
         */
        public void version(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getVersionMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 是否同步
         * </pre>
         */
        public void isSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getIsSyncMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取当前节点连接的其他节点信息
         * </pre>
         */
        public void getPeerInfo(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeerList> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetPeerInfoMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取当前节点的网络信息
         * </pre>
         */
        public void netInfo(cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getNetInfoMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * ntpclock是否同步
         * </pre>
         */
        public void isNtpClockSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getIsNtpClockSyncMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取系统致命故障信息
         * </pre>
         */
        public void getFatalFailure(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetFatalFailureMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         */
        public void getLastBlockSequence(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetLastBlockSequenceMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * get add block's sequence by hash
         * </pre>
         */
        public void getSequenceByHash(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetSequenceByHashMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         *通过block hash 获取对应的blocks信息
         * </pre>
         */
        public void getBlockByHashes(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBlockByHashesMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         *通过block seq 获取对应的blocks hash 信息
         * </pre>
         */
        public void getBlockBySeq(cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetBlockBySeqMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 关闭chain33
         * </pre>
         */
        public void closeQueue(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCloseQueueMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取地址所以合约下的余额
         * </pre>
         */
        public void getAllExecBalance(cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetAllExecBalanceMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 签名交易
         * </pre>
         */
        public void signRawTx(cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getSignRawTxMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void createNoBalanceTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCreateNoBalanceTransactionMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取随机HASH
         * </pre>
         */
        public void queryRandNum(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getQueryRandNumMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 获取是否达到fork高度
         * </pre>
         */
        public void getFork(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetForkMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void createNoBalanceTxs(cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getCreateNoBalanceTxsMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 通过seq以及title获取对应平行连的交易
         * </pre>
         */
        public void getParaTxByTitle(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetParaTxByTitleMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取拥有此title交易的区块高度
         * </pre>
         */
        public void loadParaTxByTitle(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getLoadParaTxByTitleMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 通过区块高度列表 + title获取平行链交易
         * </pre>
         */
        public void getParaTxByHeight(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetParaTxByHeightMethod(), getCallOptions()), request,
                    responseObserver);
        }

        /**
         * <pre>
         * 获取区块头信息
         * </pre>
         */
        public void getHeaders(cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request,
                io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> responseObserver) {
            asyncUnaryCall(getChannel().newCall(getGetHeadersMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     */
    public static final class chain33BlockingStub extends io.grpc.stub.AbstractBlockingStub<chain33BlockingStub> {
        private chain33BlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected chain33BlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new chain33BlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * chain33 对外提供服务的接口
         *区块链接口
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply getBlocks(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request) {
            return blockingUnaryCall(getChannel(), getGetBlocksMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取最新的区块头
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header getLastHeader(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getGetLastHeaderMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 交易接口
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx createRawTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx request) {
            return blockingUnaryCall(getChannel(), getCreateRawTransactionMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx createRawTxGroup(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup request) {
            return blockingUnaryCall(getChannel(), getCreateRawTxGroupMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 根据哈希查询交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail queryTransaction(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return blockingUnaryCall(getChannel(), getQueryTransactionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply sendTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction request) {
            return blockingUnaryCall(getChannel(), getSendTransactionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 通过地址获取交易信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos getTransactionByAddr(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request) {
            return blockingUnaryCall(getChannel(), getGetTransactionByAddrMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 通过哈希数组获取对应的交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails getTransactionByHashes(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request) {
            return blockingUnaryCall(getChannel(), getGetTransactionByHashesMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 缓存接口
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList getMemPool(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool request) {
            return blockingUnaryCall(getChannel(), getGetMemPoolMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *钱包接口
         *获取钱包账户信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts getAccounts(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getGetAccountsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 根据账户lable信息获取账户地址
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount getAccount(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount request) {
            return blockingUnaryCall(getChannel(), getGetAccountMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 创建钱包账户
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount newAccount(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount request) {
            return blockingUnaryCall(getChannel(), getNewAccountMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取钱包的交易列表
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails walletTransactionList(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList request) {
            return blockingUnaryCall(getChannel(), getWalletTransactionListMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 导入钱包私钥
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount importPrivkey(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey request) {
            return blockingUnaryCall(getChannel(), getImportPrivkeyMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash sendToAddress(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress request) {
            return blockingUnaryCall(getChannel(), getSendToAddressMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 设置交易手续费
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply setTxFee(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee request) {
            return blockingUnaryCall(getChannel(), getSetTxFeeMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 设置标签
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount setLabl(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel request) {
            return blockingUnaryCall(getChannel(), getSetLablMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 合并钱包余额
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes mergeBalance(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance request) {
            return blockingUnaryCall(getChannel(), getMergeBalanceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 设置钱包密码
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply setPasswd(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd request) {
            return blockingUnaryCall(getChannel(), getSetPasswdMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 给钱包上锁
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply lock(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getLockMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 给钱包解锁
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply unLock(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock request) {
            return blockingUnaryCall(getChannel(), getUnLockMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取最新的Mempool
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList getLastMemPool(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getGetLastMemPoolMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取最新的ProperFee
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee getProperFee(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee request) {
            return blockingUnaryCall(getChannel(), getGetProperFeeMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取钱包状态
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus getWalletStatus(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getGetWalletStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *区块浏览器接口
         * /
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview getBlockOverview(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return blockingUnaryCall(getChannel(), getGetBlockOverviewMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview getAddrOverview(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request) {
            return blockingUnaryCall(getChannel(), getGetAddrOverviewMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash getBlockHash(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt request) {
            return blockingUnaryCall(getChannel(), getGetBlockHashMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * seed
         * 创建seed
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed genSeed(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang request) {
            return blockingUnaryCall(getChannel(), getGenSeedMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取seed
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed getSeed(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw request) {
            return blockingUnaryCall(getChannel(), getGetSeedMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 保存seed
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply saveSeed(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw request) {
            return blockingUnaryCall(getChannel(), getSaveSeedMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Balance Query
         *获取余额
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts getBalance(
                cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance request) {
            return blockingUnaryCall(getChannel(), getGetBalanceMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply queryChain(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request) {
            return blockingUnaryCall(getChannel(), getQueryChainMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply execWallet(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request) {
            return blockingUnaryCall(getChannel(), getExecWalletMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply queryConsensus(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request) {
            return blockingUnaryCall(getChannel(), getQueryConsensusMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx createTransaction(
                cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn request) {
            return blockingUnaryCall(getChannel(), getCreateTransactionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取交易的十六进制编码
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx getHexTxByHash(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return blockingUnaryCall(getChannel(), getGetHexTxByHashMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 导出私钥
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString dumpPrivkey(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString request) {
            return blockingUnaryCall(getChannel(), getDumpPrivkeyMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 导出全部私钥到文件
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply dumpPrivkeysFile(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request) {
            return blockingUnaryCall(getChannel(), getDumpPrivkeysFileMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 从文件中批量导入私钥
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply importPrivkeysFile(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request) {
            return blockingUnaryCall(getChannel(), getImportPrivkeysFileMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取程序版本
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo version(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getVersionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 是否同步
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply isSync(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getIsSyncMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取当前节点连接的其他节点信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.PeerList getPeerInfo(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq request) {
            return blockingUnaryCall(getChannel(), getGetPeerInfoMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取当前节点的网络信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo netInfo(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq request) {
            return blockingUnaryCall(getChannel(), getNetInfoMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * ntpclock是否同步
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply isNtpClockSync(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getIsNtpClockSyncMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取系统致命故障信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32 getFatalFailure(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getGetFatalFailureMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 getLastBlockSequence(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getGetLastBlockSequenceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * get add block's sequence by hash
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 getSequenceByHash(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return blockingUnaryCall(getChannel(), getGetSequenceByHashMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *通过block hash 获取对应的blocks信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails getBlockByHashes(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request) {
            return blockingUnaryCall(getChannel(), getGetBlockByHashesMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *通过block seq 获取对应的blocks hash 信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq getBlockBySeq(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 request) {
            return blockingUnaryCall(getChannel(), getGetBlockBySeqMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 关闭chain33
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply closeQueue(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return blockingUnaryCall(getChannel(), getCloseQueueMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取地址所以合约下的余额
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance getAllExecBalance(
                cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance request) {
            return blockingUnaryCall(getChannel(), getGetAllExecBalanceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 签名交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx signRawTx(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx request) {
            return blockingUnaryCall(getChannel(), getSignRawTxMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx createNoBalanceTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx request) {
            return blockingUnaryCall(getChannel(), getCreateNoBalanceTransactionMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取随机HASH
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash queryRandNum(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash request) {
            return blockingUnaryCall(getChannel(), getQueryRandNumMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取是否达到fork高度
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 getFork(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey request) {
            return blockingUnaryCall(getChannel(), getGetForkMethod(), getCallOptions(), request);
        }

        /**
         */
        public cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx createNoBalanceTxs(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs request) {
            return blockingUnaryCall(getChannel(), getCreateNoBalanceTxsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 通过seq以及title获取对应平行连的交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails getParaTxByTitle(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle request) {
            return blockingUnaryCall(getChannel(), getGetParaTxByTitleMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取拥有此title交易的区块高度
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle loadParaTxByTitle(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle request) {
            return blockingUnaryCall(getChannel(), getLoadParaTxByTitleMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 通过区块高度列表 + title获取平行链交易
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails getParaTxByHeight(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight request) {
            return blockingUnaryCall(getChannel(), getGetParaTxByHeightMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 获取区块头信息
         * </pre>
         */
        public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers getHeaders(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request) {
            return blockingUnaryCall(getChannel(), getGetHeadersMethod(), getCallOptions(), request);
        }
    }

    /**
     */
    public static final class chain33FutureStub extends io.grpc.stub.AbstractFutureStub<chain33FutureStub> {
        private chain33FutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected chain33FutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new chain33FutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * chain33 对外提供服务的接口
         *区块链接口
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> getBlocks(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request) {
            return futureUnaryCall(getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取最新的区块头
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header> getLastHeader(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getGetLastHeaderMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 交易接口
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> createRawTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx request) {
            return futureUnaryCall(getChannel().newCall(getCreateRawTransactionMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> createRawTxGroup(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup request) {
            return futureUnaryCall(getChannel().newCall(getCreateRawTxGroupMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 根据哈希查询交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail> queryTransaction(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return futureUnaryCall(getChannel().newCall(getQueryTransactionMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> sendTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction request) {
            return futureUnaryCall(getChannel().newCall(getSendTransactionMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 通过地址获取交易信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos> getTransactionByAddr(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request) {
            return futureUnaryCall(getChannel().newCall(getGetTransactionByAddrMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 通过哈希数组获取对应的交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails> getTransactionByHashes(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request) {
            return futureUnaryCall(getChannel().newCall(getGetTransactionByHashesMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 缓存接口
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getMemPool(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool request) {
            return futureUnaryCall(getChannel().newCall(getGetMemPoolMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *钱包接口
         *获取钱包账户信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts> getAccounts(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getGetAccountsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 根据账户lable信息获取账户地址
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> getAccount(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount request) {
            return futureUnaryCall(getChannel().newCall(getGetAccountMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 创建钱包账户
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> newAccount(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount request) {
            return futureUnaryCall(getChannel().newCall(getNewAccountMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取钱包的交易列表
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails> walletTransactionList(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList request) {
            return futureUnaryCall(getChannel().newCall(getWalletTransactionListMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 导入钱包私钥
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> importPrivkey(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey request) {
            return futureUnaryCall(getChannel().newCall(getImportPrivkeyMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 发送交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> sendToAddress(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress request) {
            return futureUnaryCall(getChannel().newCall(getSendToAddressMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 设置交易手续费
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> setTxFee(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee request) {
            return futureUnaryCall(getChannel().newCall(getSetTxFeeMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 设置标签
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount> setLabl(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel request) {
            return futureUnaryCall(getChannel().newCall(getSetLablMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 合并钱包余额
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes> mergeBalance(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance request) {
            return futureUnaryCall(getChannel().newCall(getMergeBalanceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 设置钱包密码
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> setPasswd(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd request) {
            return futureUnaryCall(getChannel().newCall(getSetPasswdMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 给钱包上锁
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> lock(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getLockMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 给钱包解锁
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> unLock(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock request) {
            return futureUnaryCall(getChannel().newCall(getUnLockMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取最新的Mempool
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList> getLastMemPool(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getGetLastMemPoolMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取最新的ProperFee
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee> getProperFee(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee request) {
            return futureUnaryCall(getChannel().newCall(getGetProperFeeMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取钱包状态
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus> getWalletStatus(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getGetWalletStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *区块浏览器接口
         * /
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview> getBlockOverview(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return futureUnaryCall(getChannel().newCall(getGetBlockOverviewMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview> getAddrOverview(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr request) {
            return futureUnaryCall(getChannel().newCall(getGetAddrOverviewMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> getBlockHash(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt request) {
            return futureUnaryCall(getChannel().newCall(getGetBlockHashMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * seed
         * 创建seed
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> genSeed(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang request) {
            return futureUnaryCall(getChannel().newCall(getGenSeedMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取seed
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed> getSeed(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw request) {
            return futureUnaryCall(getChannel().newCall(getGetSeedMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 保存seed
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> saveSeed(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw request) {
            return futureUnaryCall(getChannel().newCall(getSaveSeedMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Balance Query
         *获取余额
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getBalance(
                cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance request) {
            return futureUnaryCall(getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> queryChain(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request) {
            return futureUnaryCall(getChannel().newCall(getQueryChainMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> execWallet(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request) {
            return futureUnaryCall(getChannel().newCall(getExecWalletMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> queryConsensus(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor request) {
            return futureUnaryCall(getChannel().newCall(getQueryConsensusMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx> createTransaction(
                cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn request) {
            return futureUnaryCall(getChannel().newCall(getCreateTransactionMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取交易的十六进制编码
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx> getHexTxByHash(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return futureUnaryCall(getChannel().newCall(getGetHexTxByHashMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 导出私钥
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString> dumpPrivkey(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString request) {
            return futureUnaryCall(getChannel().newCall(getDumpPrivkeyMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 导出全部私钥到文件
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> dumpPrivkeysFile(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request) {
            return futureUnaryCall(getChannel().newCall(getDumpPrivkeysFileMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 从文件中批量导入私钥
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> importPrivkeysFile(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile request) {
            return futureUnaryCall(getChannel().newCall(getImportPrivkeysFileMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取程序版本
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo> version(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getVersionMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 是否同步
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> isSync(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getIsSyncMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取当前节点连接的其他节点信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.PeerList> getPeerInfo(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq request) {
            return futureUnaryCall(getChannel().newCall(getGetPeerInfoMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取当前节点的网络信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo> netInfo(
                cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq request) {
            return futureUnaryCall(getChannel().newCall(getNetInfoMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * ntpclock是否同步
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> isNtpClockSync(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getIsNtpClockSyncMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取系统致命故障信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32> getFatalFailure(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getGetFatalFailureMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getLastBlockSequence(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getGetLastBlockSequenceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * get add block's sequence by hash
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getSequenceByHash(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash request) {
            return futureUnaryCall(getChannel().newCall(getGetSequenceByHashMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *通过block hash 获取对应的blocks信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails> getBlockByHashes(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes request) {
            return futureUnaryCall(getChannel().newCall(getGetBlockByHashesMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *通过block seq 获取对应的blocks hash 信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq> getBlockBySeq(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64 request) {
            return futureUnaryCall(getChannel().newCall(getGetBlockBySeqMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 关闭chain33
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply> closeQueue(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
            return futureUnaryCall(getChannel().newCall(getCloseQueueMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取地址所以合约下的余额
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance> getAllExecBalance(
                cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance request) {
            return futureUnaryCall(getChannel().newCall(getGetAllExecBalanceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 签名交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> signRawTx(
                cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx request) {
            return futureUnaryCall(getChannel().newCall(getSignRawTxMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> createNoBalanceTransaction(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx request) {
            return futureUnaryCall(getChannel().newCall(getCreateNoBalanceTransactionMethod(), getCallOptions()),
                    request);
        }

        /**
         * <pre>
         * 获取随机HASH
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash> queryRandNum(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash request) {
            return futureUnaryCall(getChannel().newCall(getQueryRandNumMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取是否达到fork高度
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64> getFork(
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey request) {
            return futureUnaryCall(getChannel().newCall(getGetForkMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx> createNoBalanceTxs(
                cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs request) {
            return futureUnaryCall(getChannel().newCall(getCreateNoBalanceTxsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 通过seq以及title获取对应平行连的交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getParaTxByTitle(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle request) {
            return futureUnaryCall(getChannel().newCall(getGetParaTxByTitleMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取拥有此title交易的区块高度
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle> loadParaTxByTitle(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle request) {
            return futureUnaryCall(getChannel().newCall(getLoadParaTxByTitleMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 通过区块高度列表 + title获取平行链交易
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails> getParaTxByHeight(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight request) {
            return futureUnaryCall(getChannel().newCall(getGetParaTxByHeightMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 获取区块头信息
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers> getHeaders(
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks request) {
            return futureUnaryCall(getChannel().newCall(getGetHeadersMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_BLOCKS = 0;
    private static final int METHODID_GET_LAST_HEADER = 1;
    private static final int METHODID_CREATE_RAW_TRANSACTION = 2;
    private static final int METHODID_CREATE_RAW_TX_GROUP = 3;
    private static final int METHODID_QUERY_TRANSACTION = 4;
    private static final int METHODID_SEND_TRANSACTION = 5;
    private static final int METHODID_GET_TRANSACTION_BY_ADDR = 6;
    private static final int METHODID_GET_TRANSACTION_BY_HASHES = 7;
    private static final int METHODID_GET_MEM_POOL = 8;
    private static final int METHODID_GET_ACCOUNTS = 9;
    private static final int METHODID_GET_ACCOUNT = 10;
    private static final int METHODID_NEW_ACCOUNT = 11;
    private static final int METHODID_WALLET_TRANSACTION_LIST = 12;
    private static final int METHODID_IMPORT_PRIVKEY = 13;
    private static final int METHODID_SEND_TO_ADDRESS = 14;
    private static final int METHODID_SET_TX_FEE = 15;
    private static final int METHODID_SET_LABL = 16;
    private static final int METHODID_MERGE_BALANCE = 17;
    private static final int METHODID_SET_PASSWD = 18;
    private static final int METHODID_LOCK = 19;
    private static final int METHODID_UN_LOCK = 20;
    private static final int METHODID_GET_LAST_MEM_POOL = 21;
    private static final int METHODID_GET_PROPER_FEE = 22;
    private static final int METHODID_GET_WALLET_STATUS = 23;
    private static final int METHODID_GET_BLOCK_OVERVIEW = 24;
    private static final int METHODID_GET_ADDR_OVERVIEW = 25;
    private static final int METHODID_GET_BLOCK_HASH = 26;
    private static final int METHODID_GEN_SEED = 27;
    private static final int METHODID_GET_SEED = 28;
    private static final int METHODID_SAVE_SEED = 29;
    private static final int METHODID_GET_BALANCE = 30;
    private static final int METHODID_QUERY_CHAIN = 31;
    private static final int METHODID_EXEC_WALLET = 32;
    private static final int METHODID_QUERY_CONSENSUS = 33;
    private static final int METHODID_CREATE_TRANSACTION = 34;
    private static final int METHODID_GET_HEX_TX_BY_HASH = 35;
    private static final int METHODID_DUMP_PRIVKEY = 36;
    private static final int METHODID_DUMP_PRIVKEYS_FILE = 37;
    private static final int METHODID_IMPORT_PRIVKEYS_FILE = 38;
    private static final int METHODID_VERSION = 39;
    private static final int METHODID_IS_SYNC = 40;
    private static final int METHODID_GET_PEER_INFO = 41;
    private static final int METHODID_NET_INFO = 42;
    private static final int METHODID_IS_NTP_CLOCK_SYNC = 43;
    private static final int METHODID_GET_FATAL_FAILURE = 44;
    private static final int METHODID_GET_LAST_BLOCK_SEQUENCE = 45;
    private static final int METHODID_GET_SEQUENCE_BY_HASH = 46;
    private static final int METHODID_GET_BLOCK_BY_HASHES = 47;
    private static final int METHODID_GET_BLOCK_BY_SEQ = 48;
    private static final int METHODID_CLOSE_QUEUE = 49;
    private static final int METHODID_GET_ALL_EXEC_BALANCE = 50;
    private static final int METHODID_SIGN_RAW_TX = 51;
    private static final int METHODID_CREATE_NO_BALANCE_TRANSACTION = 52;
    private static final int METHODID_QUERY_RAND_NUM = 53;
    private static final int METHODID_GET_FORK = 54;
    private static final int METHODID_CREATE_NO_BALANCE_TXS = 55;
    private static final int METHODID_GET_PARA_TX_BY_TITLE = 56;
    private static final int METHODID_LOAD_PARA_TX_BY_TITLE = 57;
    private static final int METHODID_GET_PARA_TX_BY_HEIGHT = 58;
    private static final int METHODID_GET_HEADERS = 59;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final chain33ImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(chain33ImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
            case METHODID_GET_BLOCKS:
                serviceImpl.getBlocks((cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_LAST_HEADER:
                serviceImpl.getLastHeader((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Header>) responseObserver);
                break;
            case METHODID_CREATE_RAW_TRANSACTION:
                serviceImpl.createRawTransaction(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTx) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx>) responseObserver);
                break;
            case METHODID_CREATE_RAW_TX_GROUP:
                serviceImpl.createRawTxGroup(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.CreateTransactionGroup) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx>) responseObserver);
                break;
            case METHODID_QUERY_TRANSACTION:
                serviceImpl.queryTransaction((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetail>) responseObserver);
                break;
            case METHODID_SEND_TRANSACTION:
                serviceImpl.sendTransaction(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.Transaction) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_TRANSACTION_BY_ADDR:
                serviceImpl.getTransactionByAddr(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxInfos>) responseObserver);
                break;
            case METHODID_GET_TRANSACTION_BY_HASHES:
                serviceImpl.getTransactionByHashes((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.TransactionDetails>) responseObserver);
                break;
            case METHODID_GET_MEM_POOL:
                serviceImpl.getMemPool((cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqGetMempool) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList>) responseObserver);
                break;
            case METHODID_GET_ACCOUNTS:
                serviceImpl.getAccounts((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccounts>) responseObserver);
                break;
            case METHODID_GET_ACCOUNT:
                serviceImpl.getAccount((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqGetAccount) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>) responseObserver);
                break;
            case METHODID_NEW_ACCOUNT:
                serviceImpl.newAccount((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqNewAccount) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>) responseObserver);
                break;
            case METHODID_WALLET_TRANSACTION_LIST:
                serviceImpl.walletTransactionList(
                        (cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletTransactionList) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletTxDetails>) responseObserver);
                break;
            case METHODID_IMPORT_PRIVKEY:
                serviceImpl.importPrivkey(
                        (cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletImportPrivkey) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>) responseObserver);
                break;
            case METHODID_SEND_TO_ADDRESS:
                serviceImpl.sendToAddress(
                        (cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSendToAddress) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash>) responseObserver);
                break;
            case METHODID_SET_TX_FEE:
                serviceImpl.setTxFee((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetFee) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_SET_LABL:
                serviceImpl.setLabl((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetLabel) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletAccount>) responseObserver);
                break;
            case METHODID_MERGE_BALANCE:
                serviceImpl.mergeBalance(
                        (cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletMergeBalance) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHashes>) responseObserver);
                break;
            case METHODID_SET_PASSWD:
                serviceImpl.setPasswd((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqWalletSetPasswd) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_LOCK:
                serviceImpl.lock((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_UN_LOCK:
                serviceImpl.unLock((cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletUnLock) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_LAST_MEM_POOL:
                serviceImpl.getLastMemPool((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyTxList>) responseObserver);
                break;
            case METHODID_GET_PROPER_FEE:
                serviceImpl.getProperFee(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqProperFee) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReplyProperFee>) responseObserver);
                break;
            case METHODID_GET_WALLET_STATUS:
                serviceImpl.getWalletStatus((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.WalletStatus>) responseObserver);
                break;
            case METHODID_GET_BLOCK_OVERVIEW:
                serviceImpl.getBlockOverview((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockOverview>) responseObserver);
                break;
            case METHODID_GET_ADDR_OVERVIEW:
                serviceImpl.getAddrOverview((cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.ReqAddr) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AddrOverview>) responseObserver);
                break;
            case METHODID_GET_BLOCK_HASH:
                serviceImpl.getBlockHash((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqInt) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash>) responseObserver);
                break;
            case METHODID_GEN_SEED:
                serviceImpl.genSeed((cn.chain33.javasdk.model.protobuf.WalletProtobuf.GenSeedLang) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed>) responseObserver);
                break;
            case METHODID_GET_SEED:
                serviceImpl.getSeed((cn.chain33.javasdk.model.protobuf.WalletProtobuf.GetSeedByPw) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySeed>) responseObserver);
                break;
            case METHODID_SAVE_SEED:
                serviceImpl.saveSeed((cn.chain33.javasdk.model.protobuf.WalletProtobuf.SaveSeedByPw) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_BALANCE:
                serviceImpl.getBalance((cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqBalance) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts>) responseObserver);
                break;
            case METHODID_QUERY_CHAIN:
                serviceImpl.queryChain((cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_EXEC_WALLET:
                serviceImpl.execWallet((cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_QUERY_CONSENSUS:
                serviceImpl.queryConsensus((cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ChainExecutor) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_CREATE_TRANSACTION:
                serviceImpl.createTransaction((cn.chain33.javasdk.model.protobuf.ExecuterProtobuf.CreateTxIn) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.UnsignTx>) responseObserver);
                break;
            case METHODID_GET_HEX_TX_BY_HASH:
                serviceImpl.getHexTxByHash((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.HexTx>) responseObserver);
                break;
            case METHODID_DUMP_PRIVKEY:
                serviceImpl.dumpPrivkey((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqString) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyString>) responseObserver);
                break;
            case METHODID_DUMP_PRIVKEYS_FILE:
                serviceImpl.dumpPrivkeysFile((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_IMPORT_PRIVKEYS_FILE:
                serviceImpl.importPrivkeysFile(
                        (cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqPrivkeysFile) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_VERSION:
                serviceImpl.version((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.VersionInfo>) responseObserver);
                break;
            case METHODID_IS_SYNC:
                serviceImpl.isSync((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_PEER_INFO:
                serviceImpl.getPeerInfo((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetPeerReq) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.PeerList>) responseObserver);
                break;
            case METHODID_NET_INFO:
                serviceImpl.netInfo((cn.chain33.javasdk.model.protobuf.P2pService.P2PGetNetInfoReq) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.P2pService.NodeNetInfo>) responseObserver);
                break;
            case METHODID_IS_NTP_CLOCK_SYNC:
                serviceImpl.isNtpClockSync((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_FATAL_FAILURE:
                serviceImpl.getFatalFailure((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.Int32>) responseObserver);
                break;
            case METHODID_GET_LAST_BLOCK_SEQUENCE:
                serviceImpl.getLastBlockSequence((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64>) responseObserver);
                break;
            case METHODID_GET_SEQUENCE_BY_HASH:
                serviceImpl.getSequenceByHash((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHash) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64>) responseObserver);
                break;
            case METHODID_GET_BLOCK_BY_HASHES:
                serviceImpl.getBlockByHashes((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqHashes) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockDetails>) responseObserver);
                break;
            case METHODID_GET_BLOCK_BY_SEQ:
                serviceImpl.getBlockBySeq((cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.BlockSeq>) responseObserver);
                break;
            case METHODID_CLOSE_QUEUE:
                serviceImpl.closeQueue((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Reply>) responseObserver);
                break;
            case METHODID_GET_ALL_EXEC_BALANCE:
                serviceImpl.getAllExecBalance(
                        (cn.chain33.javasdk.model.protobuf.AccountProtobuf.ReqAllExecBalance) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.AllExecBalance>) responseObserver);
                break;
            case METHODID_SIGN_RAW_TX:
                serviceImpl.signRawTx((cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReqSignRawTx) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx>) responseObserver);
                break;
            case METHODID_CREATE_NO_BALANCE_TRANSACTION:
                serviceImpl.createNoBalanceTransaction(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTx) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx>) responseObserver);
                break;
            case METHODID_QUERY_RAND_NUM:
                serviceImpl.queryRandNum((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqRandHash) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReplyHash>) responseObserver);
                break;
            case METHODID_GET_FORK:
                serviceImpl.getFork((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqKey) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.CommonProtobuf.Int64>) responseObserver);
                break;
            case METHODID_CREATE_NO_BALANCE_TXS:
                serviceImpl.createNoBalanceTxs(
                        (cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.NoBalanceTxs) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.WalletProtobuf.ReplySignRawTx>) responseObserver);
                break;
            case METHODID_GET_PARA_TX_BY_TITLE:
                serviceImpl.getParaTxByTitle(
                        (cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByTitle) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails>) responseObserver);
                break;
            case METHODID_LOAD_PARA_TX_BY_TITLE:
                serviceImpl.loadParaTxByTitle(
                        (cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqHeightByTitle) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReplyHeightByTitle>) responseObserver);
                break;
            case METHODID_GET_PARA_TX_BY_HEIGHT:
                serviceImpl.getParaTxByHeight(
                        (cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqParaTxByHeight) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ParaTxDetails>) responseObserver);
                break;
            case METHODID_GET_HEADERS:
                serviceImpl.getHeaders((cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.ReqBlocks) request,
                        (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.Headers>) responseObserver);
                break;
            default:
                throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
            default:
                throw new AssertionError();
            }
        }
    }

    private static abstract class chain33BaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        chain33BaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return cn.chain33.javasdk.model.protobuf.GrpcService.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("chain33");
        }
    }

    private static final class chain33FileDescriptorSupplier extends chain33BaseDescriptorSupplier {
        chain33FileDescriptorSupplier() {
        }
    }

    private static final class chain33MethodDescriptorSupplier extends chain33BaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        chain33MethodDescriptorSupplier(String methodName) {
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
            synchronized (chain33Grpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new chain33FileDescriptorSupplier()).addMethod(getGetBlocksMethod())
                            .addMethod(getGetLastHeaderMethod()).addMethod(getCreateRawTransactionMethod())
                            .addMethod(getCreateRawTxGroupMethod()).addMethod(getQueryTransactionMethod())
                            .addMethod(getSendTransactionMethod()).addMethod(getGetTransactionByAddrMethod())
                            .addMethod(getGetTransactionByHashesMethod()).addMethod(getGetMemPoolMethod())
                            .addMethod(getGetAccountsMethod()).addMethod(getGetAccountMethod())
                            .addMethod(getNewAccountMethod()).addMethod(getWalletTransactionListMethod())
                            .addMethod(getImportPrivkeyMethod()).addMethod(getSendToAddressMethod())
                            .addMethod(getSetTxFeeMethod()).addMethod(getSetLablMethod())
                            .addMethod(getMergeBalanceMethod()).addMethod(getSetPasswdMethod())
                            .addMethod(getLockMethod()).addMethod(getUnLockMethod())
                            .addMethod(getGetLastMemPoolMethod()).addMethod(getGetProperFeeMethod())
                            .addMethod(getGetWalletStatusMethod()).addMethod(getGetBlockOverviewMethod())
                            .addMethod(getGetAddrOverviewMethod()).addMethod(getGetBlockHashMethod())
                            .addMethod(getGenSeedMethod()).addMethod(getGetSeedMethod()).addMethod(getSaveSeedMethod())
                            .addMethod(getGetBalanceMethod()).addMethod(getQueryChainMethod())
                            .addMethod(getExecWalletMethod()).addMethod(getQueryConsensusMethod())
                            .addMethod(getCreateTransactionMethod()).addMethod(getGetHexTxByHashMethod())
                            .addMethod(getDumpPrivkeyMethod()).addMethod(getDumpPrivkeysFileMethod())
                            .addMethod(getImportPrivkeysFileMethod()).addMethod(getVersionMethod())
                            .addMethod(getIsSyncMethod()).addMethod(getGetPeerInfoMethod())
                            .addMethod(getNetInfoMethod()).addMethod(getIsNtpClockSyncMethod())
                            .addMethod(getGetFatalFailureMethod()).addMethod(getGetLastBlockSequenceMethod())
                            .addMethod(getGetSequenceByHashMethod()).addMethod(getGetBlockByHashesMethod())
                            .addMethod(getGetBlockBySeqMethod()).addMethod(getCloseQueueMethod())
                            .addMethod(getGetAllExecBalanceMethod()).addMethod(getSignRawTxMethod())
                            .addMethod(getCreateNoBalanceTransactionMethod()).addMethod(getQueryRandNumMethod())
                            .addMethod(getGetForkMethod()).addMethod(getCreateNoBalanceTxsMethod())
                            .addMethod(getGetParaTxByTitleMethod()).addMethod(getLoadParaTxByTitleMethod())
                            .addMethod(getGetParaTxByHeightMethod()).addMethod(getGetHeadersMethod()).build();
                }
            }
        }
        return result;
    }
}
