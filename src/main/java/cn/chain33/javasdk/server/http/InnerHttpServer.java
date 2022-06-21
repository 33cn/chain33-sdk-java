package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.server.http.handler.Handler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

/**
 * @authoer lhl
 * @date 2022/6/8 下午2:43
 */
public class InnerHttpServer implements InnerServer{

    HttpServer   httpServer;

    InnerHttpServer(int port) throws IOException {
        this.httpServer= HttpServer.create(new InetSocketAddress(port), 0);
    }

    InnerHttpServer() throws IOException {
        this.httpServer= HttpServer.create(new InetSocketAddress(80), 0);
    }


    public  void registerHandlers(Handler ...handlers){
        for(int i=0;i<handlers.length;i++){
            System.out.println("URI: "+handlers[i].getURI());
           this.httpServer.createContext(handlers[i].getURI(),handlers[i]);
        }
    }

    public void start(){
        this.httpServer.start();
    }

    public void stop(){
        this.httpServer.stop(1000);
    }
}
