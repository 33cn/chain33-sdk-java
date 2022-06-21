package cn.chain33.javasdk.server.http.handler;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.enums.EncodeType;
import cn.chain33.javasdk.model.protobuf.BlockchainProtobuf;
import cn.chain33.javasdk.server.http.callback.Outflow;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.protobuf.format.JsonFormat;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @authoer lhl
 * @date 2022/6/15 上午9:32
 */
public class BlockHeaderHandler extends Handler implements HttpHandler {
    public BlockHeaderHandler(EncodeType encodeType, Outflow outflow){
        this.setEncodeType(encodeType);
        this.setOutflow(outflow);
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream ungzip = new GZIPInputStream(httpExchange.getRequestBody());
        byte[] buffer = new byte[256];
        int n;
        while ((n = ungzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        //判断编码类型
        if (this.getEncodeType().equals(EncodeType.JSON)) {
            BlockchainProtobuf.HeaderSeqs.Builder builder = BlockchainProtobuf.HeaderSeqs.newBuilder();
            JsonFormat jsonFormat = new JsonFormat();
            //json编码转protobuf编码
            ByteArrayInputStream inputStream = new ByteArrayInputStream(JSONObject.parseObject(new String(out.toByteArray())).toJSONString().getBytes());
            jsonFormat.merge(inputStream, builder);
            BlockchainProtobuf.HeaderSeqs headerSeqs = builder.build();
            this.getOutflow().callback(headerSeqs);

        } else if (this.getEncodeType().equals(EncodeType.PROTOBUFF)) {
            //protobuf解析
            BlockchainProtobuf.HeaderSeqs headerSeqs = BlockchainProtobuf.HeaderSeqs.parseFrom(out.toByteArray());
            //向外输出解析结果
            this.getOutflow().callback(headerSeqs);
        }
        httpExchange.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
        httpExchange.sendResponseHeaders(200, "ok".getBytes().length);
        httpExchange.getResponseBody().write("ok".getBytes());
        httpExchange.close();

    }

    @Override
    public String getURI() {
        return "/blockheader/"+super.getURI();
    }
}
