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

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.PrepareRequest,
      rt4.distributed.TaskProto.PromiseResponse> getPrepareMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Prepare",
      requestType = rt4.distributed.TaskProto.PrepareRequest.class,
      responseType = rt4.distributed.TaskProto.PromiseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.PrepareRequest,
      rt4.distributed.TaskProto.PromiseResponse> getPrepareMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.PrepareRequest, rt4.distributed.TaskProto.PromiseResponse> getPrepareMethod;
    if ((getPrepareMethod = NodeServiceGrpc.getPrepareMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getPrepareMethod = NodeServiceGrpc.getPrepareMethod) == null) {
          NodeServiceGrpc.getPrepareMethod = getPrepareMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.PrepareRequest, rt4.distributed.TaskProto.PromiseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Prepare"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.PrepareRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.PromiseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("Prepare"))
              .build();
        }
      }
    }
    return getPrepareMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.AcceptRequest,
      rt4.distributed.TaskProto.AcceptedResponse> getAcceptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Accept",
      requestType = rt4.distributed.TaskProto.AcceptRequest.class,
      responseType = rt4.distributed.TaskProto.AcceptedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.AcceptRequest,
      rt4.distributed.TaskProto.AcceptedResponse> getAcceptMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.AcceptRequest, rt4.distributed.TaskProto.AcceptedResponse> getAcceptMethod;
    if ((getAcceptMethod = NodeServiceGrpc.getAcceptMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getAcceptMethod = NodeServiceGrpc.getAcceptMethod) == null) {
          NodeServiceGrpc.getAcceptMethod = getAcceptMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.AcceptRequest, rt4.distributed.TaskProto.AcceptedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Accept"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.AcceptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.AcceptedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("Accept"))
              .build();
        }
      }
    }
    return getAcceptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.HealthRequest,
      rt4.distributed.TaskProto.HealthResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = rt4.distributed.TaskProto.HealthRequest.class,
      responseType = rt4.distributed.TaskProto.HealthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.HealthRequest,
      rt4.distributed.TaskProto.HealthResponse> getPingMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.HealthRequest, rt4.distributed.TaskProto.HealthResponse> getPingMethod;
    if ((getPingMethod = NodeServiceGrpc.getPingMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getPingMethod = NodeServiceGrpc.getPingMethod) == null) {
          NodeServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.HealthRequest, rt4.distributed.TaskProto.HealthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.HealthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.HealthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.MutexRequest,
      rt4.distributed.TaskProto.MutexResponse> getRequestMutexMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestMutex",
      requestType = rt4.distributed.TaskProto.MutexRequest.class,
      responseType = rt4.distributed.TaskProto.MutexResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.MutexRequest,
      rt4.distributed.TaskProto.MutexResponse> getRequestMutexMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.MutexRequest, rt4.distributed.TaskProto.MutexResponse> getRequestMutexMethod;
    if ((getRequestMutexMethod = NodeServiceGrpc.getRequestMutexMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getRequestMutexMethod = NodeServiceGrpc.getRequestMutexMethod) == null) {
          NodeServiceGrpc.getRequestMutexMethod = getRequestMutexMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.MutexRequest, rt4.distributed.TaskProto.MutexResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestMutex"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.MutexRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.MutexResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("RequestMutex"))
              .build();
        }
      }
    }
    return getRequestMutexMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rt4.distributed.TaskProto.MutexRequest,
      rt4.distributed.TaskProto.MutexResponse> getReleaseMutexMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReleaseMutex",
      requestType = rt4.distributed.TaskProto.MutexRequest.class,
      responseType = rt4.distributed.TaskProto.MutexResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rt4.distributed.TaskProto.MutexRequest,
      rt4.distributed.TaskProto.MutexResponse> getReleaseMutexMethod() {
    io.grpc.MethodDescriptor<rt4.distributed.TaskProto.MutexRequest, rt4.distributed.TaskProto.MutexResponse> getReleaseMutexMethod;
    if ((getReleaseMutexMethod = NodeServiceGrpc.getReleaseMutexMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getReleaseMutexMethod = NodeServiceGrpc.getReleaseMutexMethod) == null) {
          NodeServiceGrpc.getReleaseMutexMethod = getReleaseMutexMethod =
              io.grpc.MethodDescriptor.<rt4.distributed.TaskProto.MutexRequest, rt4.distributed.TaskProto.MutexResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReleaseMutex"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.MutexRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rt4.distributed.TaskProto.MutexResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("ReleaseMutex"))
              .build();
        }
      }
    }
    return getReleaseMutexMethod;
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
     * ── Client-facing ──────────────────────────────────────────────────────────
     * </pre>
     */
    default void submitTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitTaskMethod(), responseObserver);
    }

    /**
     * <pre>
     * ── Inter-node: task routing ────────────────────────────────────────────────
     * </pre>
     */
    default void forwardTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getForwardTaskMethod(), responseObserver);
    }

    /**
     * <pre>
     * ── Paxos Phase 1: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends a proposal number; acceptor promises not to accept lower ones
     * </pre>
     */
    default void prepare(rt4.distributed.TaskProto.PrepareRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.PromiseResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrepareMethod(), responseObserver);
    }

    /**
     * <pre>
     * ── Paxos Phase 2: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends (proposalId, value); acceptor accepts if proposalId &gt;= promised
     * </pre>
     */
    default void accept(rt4.distributed.TaskProto.AcceptRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.AcceptedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcceptMethod(), responseObserver);
    }

    /**
     * <pre>
     * ── Health check: heartbeat between nodes ──────────────────────────────────
     * </pre>
     */
    default void ping(rt4.distributed.TaskProto.HealthRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.HealthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     * <pre>
     *  Mutex messages
     * </pre>
     */
    default void requestMutex(rt4.distributed.TaskProto.MutexRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.MutexResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestMutexMethod(), responseObserver);
    }

    /**
     */
    default void releaseMutex(rt4.distributed.TaskProto.MutexRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.MutexResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReleaseMutexMethod(), responseObserver);
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
     * ── Client-facing ──────────────────────────────────────────────────────────
     * </pre>
     */
    public void submitTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ── Inter-node: task routing ────────────────────────────────────────────────
     * </pre>
     */
    public void forwardTask(rt4.distributed.TaskProto.TaskRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.TaskResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getForwardTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ── Paxos Phase 1: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends a proposal number; acceptor promises not to accept lower ones
     * </pre>
     */
    public void prepare(rt4.distributed.TaskProto.PrepareRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.PromiseResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ── Paxos Phase 2: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends (proposalId, value); acceptor accepts if proposalId &gt;= promised
     * </pre>
     */
    public void accept(rt4.distributed.TaskProto.AcceptRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.AcceptedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ── Health check: heartbeat between nodes ──────────────────────────────────
     * </pre>
     */
    public void ping(rt4.distributed.TaskProto.HealthRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.HealthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *  Mutex messages
     * </pre>
     */
    public void requestMutex(rt4.distributed.TaskProto.MutexRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.MutexResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestMutexMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void releaseMutex(rt4.distributed.TaskProto.MutexRequest request,
        io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.MutexResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReleaseMutexMethod(), getCallOptions()), request, responseObserver);
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
     * ── Client-facing ──────────────────────────────────────────────────────────
     * </pre>
     */
    public rt4.distributed.TaskProto.TaskResponse submitTask(rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitTaskMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ── Inter-node: task routing ────────────────────────────────────────────────
     * </pre>
     */
    public rt4.distributed.TaskProto.TaskResponse forwardTask(rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getForwardTaskMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ── Paxos Phase 1: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends a proposal number; acceptor promises not to accept lower ones
     * </pre>
     */
    public rt4.distributed.TaskProto.PromiseResponse prepare(rt4.distributed.TaskProto.PrepareRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrepareMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ── Paxos Phase 2: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends (proposalId, value); acceptor accepts if proposalId &gt;= promised
     * </pre>
     */
    public rt4.distributed.TaskProto.AcceptedResponse accept(rt4.distributed.TaskProto.AcceptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcceptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ── Health check: heartbeat between nodes ──────────────────────────────────
     * </pre>
     */
    public rt4.distributed.TaskProto.HealthResponse ping(rt4.distributed.TaskProto.HealthRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *  Mutex messages
     * </pre>
     */
    public rt4.distributed.TaskProto.MutexResponse requestMutex(rt4.distributed.TaskProto.MutexRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestMutexMethod(), getCallOptions(), request);
    }

    /**
     */
    public rt4.distributed.TaskProto.MutexResponse releaseMutex(rt4.distributed.TaskProto.MutexRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReleaseMutexMethod(), getCallOptions(), request);
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
     * ── Client-facing ──────────────────────────────────────────────────────────
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.TaskResponse> submitTask(
        rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitTaskMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ── Inter-node: task routing ────────────────────────────────────────────────
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.TaskResponse> forwardTask(
        rt4.distributed.TaskProto.TaskRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getForwardTaskMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ── Paxos Phase 1: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends a proposal number; acceptor promises not to accept lower ones
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.PromiseResponse> prepare(
        rt4.distributed.TaskProto.PrepareRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrepareMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ── Paxos Phase 2: Proposer → Acceptors ────────────────────────────────────
     * Proposer sends (proposalId, value); acceptor accepts if proposalId &gt;= promised
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.AcceptedResponse> accept(
        rt4.distributed.TaskProto.AcceptRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ── Health check: heartbeat between nodes ──────────────────────────────────
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.HealthResponse> ping(
        rt4.distributed.TaskProto.HealthRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *  Mutex messages
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.MutexResponse> requestMutex(
        rt4.distributed.TaskProto.MutexRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestMutexMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rt4.distributed.TaskProto.MutexResponse> releaseMutex(
        rt4.distributed.TaskProto.MutexRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReleaseMutexMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBMIT_TASK = 0;
  private static final int METHODID_FORWARD_TASK = 1;
  private static final int METHODID_PREPARE = 2;
  private static final int METHODID_ACCEPT = 3;
  private static final int METHODID_PING = 4;
  private static final int METHODID_REQUEST_MUTEX = 5;
  private static final int METHODID_RELEASE_MUTEX = 6;

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
        case METHODID_PREPARE:
          serviceImpl.prepare((rt4.distributed.TaskProto.PrepareRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.PromiseResponse>) responseObserver);
          break;
        case METHODID_ACCEPT:
          serviceImpl.accept((rt4.distributed.TaskProto.AcceptRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.AcceptedResponse>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((rt4.distributed.TaskProto.HealthRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.HealthResponse>) responseObserver);
          break;
        case METHODID_REQUEST_MUTEX:
          serviceImpl.requestMutex((rt4.distributed.TaskProto.MutexRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.MutexResponse>) responseObserver);
          break;
        case METHODID_RELEASE_MUTEX:
          serviceImpl.releaseMutex((rt4.distributed.TaskProto.MutexRequest) request,
              (io.grpc.stub.StreamObserver<rt4.distributed.TaskProto.MutexResponse>) responseObserver);
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
          getPrepareMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.PrepareRequest,
              rt4.distributed.TaskProto.PromiseResponse>(
                service, METHODID_PREPARE)))
        .addMethod(
          getAcceptMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.AcceptRequest,
              rt4.distributed.TaskProto.AcceptedResponse>(
                service, METHODID_ACCEPT)))
        .addMethod(
          getPingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.HealthRequest,
              rt4.distributed.TaskProto.HealthResponse>(
                service, METHODID_PING)))
        .addMethod(
          getRequestMutexMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.MutexRequest,
              rt4.distributed.TaskProto.MutexResponse>(
                service, METHODID_REQUEST_MUTEX)))
        .addMethod(
          getReleaseMutexMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              rt4.distributed.TaskProto.MutexRequest,
              rt4.distributed.TaskProto.MutexResponse>(
                service, METHODID_RELEASE_MUTEX)))
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
              .addMethod(getPrepareMethod())
              .addMethod(getAcceptMethod())
              .addMethod(getPingMethod())
              .addMethod(getRequestMutexMethod())
              .addMethod(getReleaseMutexMethod())
              .build();
        }
      }
    }
    return result;
  }
}
