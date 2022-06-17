package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.model.protobuf.EvmEventProtobuf;
import cn.chain33.javasdk.model.protobuf.EvmEventProtobuf.EVMTxLogsInBlks;
import cn.chain33.javasdk.model.protobuf.EvmEventProtobuf.EVMTxLogPerBlk;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.protobuf.format.JsonFormat;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * @authoer lhl
 * @date 2022/6/16 下午3:30
 */
public class HandlerTest {

    @Test
    public void testJsonToProto() throws InvalidProtocolBufferException {
        EVMTxLogPerBlk  evmTxLogPerBlk = EVMTxLogPerBlk.newBuilder().setBlockHash(ByteString.copyFrom("test".getBytes())).build();
        byte[] bytes = evmTxLogPerBlk.toByteArray();
        EVMTxLogPerBlk  log = null;
        try {
            log = EVMTxLogPerBlk.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        System.out.println(log.getBlockHash());
        System.out.println(log);
        JsonFormat jsonFormat = new JsonFormat();
        String json= jsonFormat.printToString(evmTxLogPerBlk);
        System.out.println(json);

        bytes = json.getBytes();
        try {
            ByteArrayInputStream inputStream= new ByteArrayInputStream(bytes);

            EVMTxLogPerBlk.Builder  builder = EVMTxLogPerBlk.newBuilder();
            jsonFormat.merge(inputStream,builder);
            EVMTxLogPerBlk perBlk = builder.build();
            System.out.println(perBlk.getBlockHash());
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
