package rt4.distributed;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: task.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NodeServiceGrpc {

  private NodeServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "NodeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.TaskRequest,
      rt4.distributed.TaskProto.TaskResponse> getSubmitTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitTask",
      requestType = rt4.distributed.TaskProto.TaskRequest.class,
      responseType = rt4.distributed.TaskProto.TaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.TaskRequest,
      rt4.distributed.TaskProto.TaskResponse> getSubmitTaskMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.TaskRequest, rt4.distributed.TaskProto.TaskResponse> getSubmitTaskMethod;
    if ((getSubmitTaskMethod = NodeServiceGrpc.getSubmitTaskMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getSubmitTaskMethod = NodeServiceGrpc.getSubmitTaskMethod) == null) {
          NodeServiceGrpc.getSubmitTaskMethod = getSubmitTaskMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.TaskRequest, rt4.distributed.TaskProto.TaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.TaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.TaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("SubmitTask"))
              .build();
        }
      }
    }
    return getSubmitTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.TaskRequest,
      rt4.distributed.TaskProto.TaskResponse> getForwardTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ForwardTask",
      requestType = rt4.distributed.TaskProto.TaskRequest.class,
      responseType = rt4.distributed.TaskProto.TaskResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.TaskRequest,
      rt4.distributed.TaskProto.TaskResponse> getForwardTaskMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.TaskRequest, rt4.distributed.TaskProto.TaskResponse> getForwardTaskMethod;
    if ((getForwardTaskMethod = NodeServiceGrpc.getForwardTaskMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getForwardTaskMethod = NodeServiceGrpc.getForwardTaskMethod) == null) {
          NodeServiceGrpc.getForwardTaskMethod = getForwardTaskMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.TaskRequest, rt4.distributed.TaskProto.TaskResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ForwardTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.TaskRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.TaskResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("ForwardTask"))
              .build();
        }
      }
    }
    return getForwardTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.LockRequest,
      rt4.distributed.TaskProto.LockResponse> getAcquireLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AcquireLock",
      requestType = rt4.distributed.TaskProto.LockRequest.class,
      responseType = rt4.distributed.TaskProto.LockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.LockRequest,
      rt4.distributed.TaskProto.LockResponse> getAcquireLockMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.LockRequest, rt4.distributed.TaskProto.LockResponse> getAcquireLockMethod;
    if ((getAcquireLockMethod = NodeServiceGrpc.getAcquireLockMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getAcquireLockMethod = NodeServiceGrpc.getAcquireLockMethod) == null) {
          NodeServiceGrpc.getAcquireLockMethod = getAcquireLockMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.LockRequest, rt4.distributed.TaskProto.LockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AcquireLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.LockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.LockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("AcquireLock"))
              .build();
        }
      }
    }
    return getAcquireLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.LockRequest,
      rt4.distributed.TaskProto.LockResponse> getReleaseLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReleaseLock",
      requestType = rt4.distributed.TaskProto.LockRequest.class,
      responseType = rt4.distributed.TaskProto.LockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.LockRequest,
      rt4.distributed.TaskProto.LockResponse> getReleaseLockMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.LockRequest, rt4.distributed.TaskProto.LockResponse> getReleaseLockMethod;
    if ((getReleaseLockMethod = NodeServiceGrpc.getReleaseLockMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getReleaseLockMethod = NodeServiceGrpc.getReleaseLockMethod) == null) {
          NodeServiceGrpc.getReleaseLockMethod = getReleaseLockMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.LockRequest, rt4.distributed.TaskProto.LockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReleaseLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.LockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.LockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("ReleaseLock"))
              .build();
        }
      }
    }
    return getReleaseLockMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NodeServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceStub>() {
        @java.lang.Override
        public NodeServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceStub(channel, callOptions);
        }
      };
    return NodeServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceBlockingStub>() {
        @java.lang.Override
        public NodeServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceBlockingStub(channel, callOptions);
        }
      };
    return NodeServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeServiceFutureStub>() {
        @java.lang.Override
        public NodeServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeServiceFutureStub(channel, callOptions);
        }
      };
    return NodeServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Client sends a task to any node
     * </pre>
     */
    default void submitTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitTaskMethod(), responseObserver);
    }

    /**
     * <pre>
     * Nodes use this to forward tasks to the leader
     * </pre>
     */
    default void forwardTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getForwardTaskMethod(), responseObserver);
    }

    /**
     * <pre>
     * Leader uses this to acquire the distributed mutex on all nodes
     * </pre>
     */
    default void acquireLock(rt4.distributed.TaskProto.LockRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.LockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcquireLockMethod(), responseObserver);
    }

    /**
     * <pre>
     * Leader releases the lock after execution
     * </pre>
     */
    default void releaseLock(rt4.distributed.TaskProto.LockRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.LockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReleaseLockMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service NodeService.
   */
  public static abstract class NodeServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return NodeServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service NodeService.
   */
  public static final class NodeServiceStub
      extends io.grpc.stub.AbstractAsyncStub<NodeServiceStub> {
    private NodeServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client sends a task to any node
     * </pre>
     */
    public void submitTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Nodes use this to forward tasks to the leader
     * </pre>
     */
    public void forwardTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getForwardTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Leader uses this to acquire the distributed mutex on all nodes
     * </pre>
     */
    public void acquireLock(rt4.distributed.TaskProto.LockRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.LockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcquireLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Leader releases the lock after execution
     * </pre>
     */
    public void releaseLock(rt4.distributed.TaskProto.LockRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.LockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReleaseLockMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service NodeService.
   */
  public static final class NodeServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<NodeServiceBlockingStub> {
    private NodeServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client sends a task to any node
     * </pre>
     */
    public rt4.distributed.TaskProto.TaskResponse submitTask(rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitTaskMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Nodes use this to forward tasks to the leader
     * </pre>
     */
    public rt4.distributed.TaskProto.TaskResponse forwardTask(rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getForwardTaskMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Leader uses this to acquire the distributed mutex on all nodes
     * </pre>
     */
    public rt4.distributed.TaskProto.LockResponse acquireLock(rt4.distributed.TaskProto.LockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcquireLockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Leader releases the lock after execution
     * </pre>
     */
    public rt4.distributed.TaskProto.LockResponse releaseLock(rt4.distributed.TaskProto.LockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReleaseLockMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service NodeService.
   */
  public static final class NodeServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<NodeServiceFutureStub> {
    private NodeServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client sends a task to any node
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.TaskResponse> submitTask(
        rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitTaskMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Nodes use this to forward tasks to the leader
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.TaskResponse> forwardTask(
        rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getForwardTaskMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Leader uses this to acquire the distributed mutex on all nodes
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.LockResponse> acquireLock(
        rt4.distributed.TaskProto.LockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcquireLockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Leader releases the lock after execution
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.LockResponse> releaseLock(
        rt4.distributed.TaskProto.LockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReleaseLockMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_TASK = 0;
  private static final int METHODID_FORWARD_TASK = 1;
  private static final int METHODID_ACQUIRE_LOCK = 2;
  private static final int METHODID_RELEASE_LOCK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBMIT_TASK:
          serviceImpl.submitTask((rt4.distributed.TaskProto.TaskRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse>) responseObserver);
          break;
        case METHODID_FORWARD_TASK:
          serviceImpl.forwardTask((rt4.distributed.TaskProto.TaskRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse>) responseObserver);
          break;
        case METHODID_ACQUIRE_LOCK:
          serviceImpl.acquireLock((rt4.distributed.TaskProto.LockRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.LockResponse>) responseObserver);
          break;
        case METHODID_RELEASE_LOCK:
          serviceImpl.releaseLock((rt4.distributed.TaskProto.LockRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.LockResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubmitTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.TaskRequest,
              rt4.distributed.TaskProto.TaskResponse>(
                service, METHODID_SUBMIT_TASK)))
        .addMethod(
          getForwardTaskMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.TaskRequest,
              rt4.distributed.TaskProto.TaskResponse>(
                service, METHODID_FORWARD_TASK)))
        .addMethod(
          getAcquireLockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.LockRequest,
              rt4.distributed.TaskProto.LockResponse>(
                service, METHODID_ACQUIRE_LOCK)))
        .addMethod(
          getReleaseLockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.LockRequest,
              rt4.distributed.TaskProto.LockResponse>(
                service, METHODID_RELEASE_LOCK)))
        .build();
  }

  private static abstract class NodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NodeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return rt4.distributed.TaskProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NodeService");
    }
  }

  private static final class NodeServiceFileDescriptorSupplier
      extends NodeServiceBaseDescriptorSupplier {
    NodeServiceFileDescriptorSupplier() {}
  }

  private static final class NodeServiceMethodDescriptorSupplier
      extends NodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    NodeServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (NodeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NodeServiceFileDescriptorSupplier())
              .addMethod(getSubmitTaskMethod())
              .addMethod(getForwardTaskMethod())
              .addMethod(getAcquireLockMethod())
              .addMethod(getReleaseLockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
