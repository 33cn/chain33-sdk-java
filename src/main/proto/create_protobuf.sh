#!/bin/sh
#本地生成protobuf
#step1 先生成消息对象
#protoc --java_out=../java  --proto_path=. *.proto
#step2 后生成Grpc接口服务
protoc --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.32.1-linux-x86_64.exe   --grpc-java_out=../java   --java_out=../java  --proto_path=. *.proto
