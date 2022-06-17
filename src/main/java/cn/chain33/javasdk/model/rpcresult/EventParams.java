package cn.chain33.javasdk.model.rpcresult;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import java.io.Serializable;
import java.util.List;
import cn.chain33.javasdk.model.abi.datatypes.Type;


/**
 * @authoer lhl
 * @date 2022/6/17 上午9:09
 */
public class EventParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private Event event;

    private List<Type> params;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Type> getParams() {
        return params;
    }

    public void setParams(List<Type> params) {
        this.params = params;
    }
}
