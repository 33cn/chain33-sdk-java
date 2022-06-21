package cn.chain33.javasdk.model.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: token.proto")
public final class tokenGrpc {

  private tokenGrpc() {}

  public static final String SERVICE_NAME = "token";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance,
      cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getGetTokenBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTokenBalance",
      requestType = cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance.class,
      responseType = cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance,
      cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getGetTokenBalanceMethod() {
    io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getGetTokenBalanceMethod;
    if ((getGetTokenBalanceMethod = tokenGrpc.getGetTokenBalanceMethod) == null) {
      synchronized (tokenGrpc.class) {
        if ((getGetTokenBalanceMethod = tokenGrpc.getGetTokenBalanceMethod) == null) {
          tokenGrpc.getGetTokenBalanceMethod = getGetTokenBalanceMethod =
              io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance, cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTokenBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts.getDefaultInstance()))
              .setSchemaDescriptor(new tokenMethodDescriptorSupplier("GetTokenBalance"))
              .build();
        }
      }
    }
    return getGetTokenBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static tokenStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<tokenStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<tokenStub>() {
        @java.lang.Override
        public tokenStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new tokenStub(channel, callOptions);
        }
      };
    return tokenStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static tokenBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<tokenBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<tokenBlockingStub>() {
        @java.lang.Override
        public tokenBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new tokenBlockingStub(channel, callOptions);
        }
      };
    return tokenBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static tokenFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<tokenFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<tokenFutureStub>() {
        @java.lang.Override
        public tokenFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new tokenFutureStub(channel, callOptions);
        }
      };
    return tokenFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class tokenImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * token 对外提供服务的接口
     *区块链接口
     * </pre>
     */
    public void getTokenBalance(cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance request,
        io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTokenBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetTokenBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance,
                cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts>(
                  this, METHODID_GET_TOKEN_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class tokenStub extends io.grpc.stub.AbstractAsyncStub<tokenStub> {
    private tokenStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected tokenStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new tokenStub(channel, callOptions);
    }

    /**
     * <pre>
     * token 对外提供服务的接口
     *区块链接口
     * </pre>
     */
    public void getTokenBalance(cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance request,
        io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTokenBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class tokenBlockingStub extends io.grpc.stub.AbstractBlockingStub<tokenBlockingStub> {
    private tokenBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected tokenBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new tokenBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * token 对外提供服务的接口
     *区块链接口
     * </pre>
     */
    public cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts getTokenBalance(cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance request) {
      return blockingUnaryCall(
          getChannel(), getGetTokenBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class tokenFutureStub extends io.grpc.stub.AbstractFutureStub<tokenFutureStub> {
    private tokenFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected tokenFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new tokenFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * token 对外提供服务的接口
     *区块链接口
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts> getTokenBalance(
        cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTokenBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TOKEN_BALANCE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final tokenImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(tokenImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_TOKEN_BALANCE:
          serviceImpl.getTokenBalance((cn.chain33.javasdk.model.protobuf.TokenProtobuf.ReqTokenBalance) request,
              (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.AccountProtobuf.Accounts>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class tokenBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    tokenBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cn.chain33.javasdk.model.protobuf.TokenProtobuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("token");
    }
  }

  private static final class tokenFileDescriptorSupplier
      extends tokenBaseDescriptorSupplier {
    tokenFileDescriptorSupplier() {}
  }

  private static final class tokenMethodDescriptorSupplier
      extends tokenBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    tokenMethodDescriptorSupplier(String methodName) {
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
      synchronized (tokenGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new tokenFileDescriptorSupplier())
              .addMethod(getGetTokenBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
