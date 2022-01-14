package cn.chain33.javasdk.model.protobuf;

public interface Chain33Functional<Arg, Result> {
    Result run(Arg arg);
}
