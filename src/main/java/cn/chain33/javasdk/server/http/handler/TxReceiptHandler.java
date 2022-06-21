package cn.chain33.javasdk.server.http.handler;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.enums.EncodeType;
import cn.chain33.javasdk.model.protobuf.PushTxReceiptProtobuf.TxReceipts4Subscribe;
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
 * @date 2022/6/17 下午10:24
 */
public class TxReceiptHandler extends Handler implements HttpHandler {
    public TxReceiptHandler(EncodeType encodeType, Outflow outflow){
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
            TxReceipts4Subscribe.Builder builder = TxReceipts4Subscribe.newBuilder();
            JsonFormat jsonFormat = new JsonFormat();
            //json编码转protobuf编码
            ByteArrayInputStream inputStream = new ByteArrayInputStream(JSONObject.parseObject(new String(out.toByteArray())).toJSONString().getBytes());
            jsonFormat.merge(inputStream, builder);
            TxReceipts4Subscribe txReceipts4Subscribe = builder.build();
            this.getOutflow().callback(txReceipts4Subscribe);

        } else if (this.getEncodeType().equals(EncodeType.PROTOBUFF)) {
            //protobuf解析
            TxReceipts4Subscribe txReceipts4Subscribe = TxReceipts4Subscribe.parseFrom(out.toByteArray());
            //向外输出解析结果
            this.getOutflow().callback(txReceipts4Subscribe);
        }
        httpExchange.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
        httpExchange.sendResponseHeaders(200, "ok".getBytes().length);
        httpExchange.getResponseBody().write("ok".getBytes());
        httpExchange.close();

    }
    @Override
    public String getURI() {
        return "/txreceipt/"+super.getURI();
    }
}
