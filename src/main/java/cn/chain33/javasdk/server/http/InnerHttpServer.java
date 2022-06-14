package cn.chain33.javasdk.server.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.zip.GZIPInputStream;

/**
 * @authoer lhl
 * @date 2022/6/8 下午2:43
 */
public class InnerHttpServer {

        //TODO http订阅接收服务需要封装
        public static void main(String[] args) throws IOException {
            // 启动一个http服务
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8383), 0);
            httpServer.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange httpExchange) throws IOException {
                    System.out.println("url: " + httpExchange.getRequestURI().getQuery());
                    httpExchange.sendResponseHeaders(200, "hello".length());
                    httpExchange.getResponseBody().write("hello".getBytes());
                }
            });
            httpServer.createContext("/topic", new HttpHandler() {
                @Override
                public void handle(HttpExchange httpExchange) throws IOException {
                    System.out.println("---url: " + httpExchange.getRequestURI().getQuery());
                    httpExchange.sendResponseHeaders(200, "hello".length());
                    httpExchange.getResponseBody().write("hello".getBytes());

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    GZIPInputStream ungzip = new GZIPInputStream(httpExchange.getRequestBody());
                    byte[] buffer = new byte[256];
                    int n;
                    while ((n = ungzip.read(buffer)) >= 0) {
                        out.write(buffer, 0, n);
                    }
                }
            });
            httpServer.start();
        }
}
