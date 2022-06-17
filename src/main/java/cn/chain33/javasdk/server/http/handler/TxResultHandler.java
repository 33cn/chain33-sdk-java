package cn.chain33.javasdk.server.http.handler;

import cn.chain33.javasdk.model.enums.EncodeType;
import cn.chain33.javasdk.model.protobuf.PushTxReceiptProtobuf.TxResultSeqs;
import com.googlecode.protobuf.format.JsonFormat;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @authoer lhl
 * @date 2022/6/17 下午10:25
 */
public class TxResultHandler extends Handler implements HttpHandler {
    TxResultHandler(EncodeType encodeType,Outflow outflow) {
        this.setEncodeType(encodeType);
        this.setOutflow(outflow);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("---url:---- " + httpExchange.getRequestURI().getQuery());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream ungzip = new GZIPInputStream(httpExchange.getRequestBody());
        byte[] buffer = new byte[256];
        int n;
        while ((n = ungzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        //判断编码类型
        if (this.getEncodeType().equals(EncodeType.JSON)) {
            TxResultSeqs.Builder builder = TxResultSeqs.newBuilder();
            JsonFormat jsonFormat = new JsonFormat();
            //json编码转protobuf编码
            ByteArrayInputStream inputStream= new ByteArrayInputStream(out.toByteArray());
            jsonFormat.merge(inputStream, builder);
            TxResultSeqs txResultSeqs = builder.build();
            this.getOutflow().process(txResultSeqs);

        } else if (this.getEncodeType().equals(EncodeType.PROTOBUFF)) {
            //protobuf解析
            TxResultSeqs txResultSeqs  = TxResultSeqs.parseFrom(out.toByteArray());
            //向外输出解析结果
            this.getOutflow().process(txResultSeqs);
        }
        httpExchange.getResponseBody().write("ok".getBytes());

    }

    @Override
    public String getURI() {
        return "/txresult/"+super.getURI();
    }
}
