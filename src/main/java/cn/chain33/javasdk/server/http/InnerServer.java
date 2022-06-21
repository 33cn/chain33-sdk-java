package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.server.http.handler.Handler;
import com.sun.net.httpserver.HttpHandler;

import java.util.Map;

/**
 * @authoer lhl
 * @date 2022/6/14 下午2:55
 */
public interface InnerServer {

    void registerHandlers(Handler...handlers);

    void start();

    void stop();

}
