package cn.chain33.javasdk.server.http.handler;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.enums.EncodeType;
import cn.chain33.javasdk.server.http.callback.Outflow;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/16 下午5:14
 * 抽象handler类
 */
public abstract class Handler implements HttpHandler {
    private EncodeType encodeType;

    private List<Event> eventList;

    private Outflow outflow;

    public EncodeType getEncodeType() {
        return encodeType;
    }

    public void setEncodeType(EncodeType encodeType) {
        this.encodeType = encodeType;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Outflow getOutflow() {
        return outflow;
    }

    public void setOutflow(Outflow outflow) {
        this.outflow = outflow;
    }

    public String getURI() {
        return encodeType.getType();
    }

    public abstract void handle(HttpExchange httpExchange) throws IOException;
}
