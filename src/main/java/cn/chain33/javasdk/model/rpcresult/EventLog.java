package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

/**
 * @authoer lhl
 * @date 2022/6/17 下午1:56
 */
public class EventLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String eventId;

    private String encodeCode;


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEncodeCode() {
        return encodeCode;
    }

    public void setEncodeCode(String encodeCode) {
        this.encodeCode = encodeCode;
    }
}
