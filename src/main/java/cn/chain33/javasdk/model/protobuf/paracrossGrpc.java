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
    comments = "Source: paracross.proto")
public final class paracrossGrpc {

  private paracrossGrpc() {}

  public static final String SERVICE_NAME = "paracross";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil,
      cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp> getIsSyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IsSync",
      requestType = cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.class,
      responseType = cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil,
      cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp> getIsSyncMethod() {
    io.grpc.MethodDescriptor<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp> getIsSyncMethod;
    if ((getIsSyncMethod = paracrossGrpc.getIsSyncMethod) == null) {
      synchronized (paracrossGrpc.class) {
        if ((getIsSyncMethod = paracrossGrpc.getIsSyncMethod) == null) {
          paracrossGrpc.getIsSyncMethod = getIsSyncMethod =
              io.grpc.MethodDescriptor.<cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil, cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IsSync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp.getDefaultInstance()))
              .setSchemaDescriptor(new paracrossMethodDescriptorSupplier("IsSync"))
              .build();
        }
      }
    }
    return getIsSyncMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static paracrossStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<paracrossStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<paracrossStub>() {
        @java.lang.Override
        public paracrossStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new paracrossStub(channel, callOptions);
        }
      };
    return paracrossStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static paracrossBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<paracrossBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<paracrossBlockingStub>() {
        @java.lang.Override
        public paracrossBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new paracrossBlockingStub(channel, callOptions);
        }
      };
    return paracrossBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static paracrossFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<paracrossFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<paracrossFutureStub>() {
        @java.lang.Override
        public paracrossFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new paracrossFutureStub(channel, callOptions);
        }
      };
    return paracrossFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class paracrossImplBase implements io.grpc.BindableService {

    /**
     */
    public void isSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
        io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp> responseObserver) {
      asyncUnimplementedUnaryCall(getIsSyncMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIsSyncMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil,
                cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp>(
                  this, METHODID_IS_SYNC)))
          .build();
    }
  }

  /**
   */
  public static final class paracrossStub extends io.grpc.stub.AbstractAsyncStub<paracrossStub> {
    private paracrossStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected paracrossStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new paracrossStub(channel, callOptions);
    }

    /**
     */
    public void isSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request,
        io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIsSyncMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class paracrossBlockingStub extends io.grpc.stub.AbstractBlockingStub<paracrossBlockingStub> {
    private paracrossBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected paracrossBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new paracrossBlockingStub(channel, callOptions);
    }

    /**
     */
    public cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp isSync(cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
      return blockingUnaryCall(
          getChannel(), getIsSyncMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class paracrossFutureStub extends io.grpc.stub.AbstractFutureStub<paracrossFutureStub> {
    private paracrossFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected paracrossFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new paracrossFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp> isSync(
        cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil request) {
      return futureUnaryCall(
          getChannel().newCall(getIsSyncMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_IS_SYNC = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final paracrossImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(paracrossImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_IS_SYNC:
          serviceImpl.isSync((cn.chain33.javasdk.model.protobuf.CommonProtobuf.ReqNil) request,
              (io.grpc.stub.StreamObserver<cn.chain33.javasdk.model.protobuf.BlockchainProtobuf.IsCaughtUp>) responseObserver);
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

  private static abstract class paracrossBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    paracrossBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cn.chain33.javasdk.model.protobuf.ParacrossProtobuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("paracross");
    }
  }

  private static final class paracrossFileDescriptorSupplier
      extends paracrossBaseDescriptorSupplier {
    paracrossFileDescriptorSupplier() {}
  }

  private static final class paracrossMethodDescriptorSupplier
      extends paracrossBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    paracrossMethodDescriptorSupplier(String methodName) {
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
      synchronized (paracrossGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new paracrossFileDescriptorSupplier())
              .addMethod(getIsSyncMethod())
              .build();
        }
      }
    }
    return result;
  }
}
