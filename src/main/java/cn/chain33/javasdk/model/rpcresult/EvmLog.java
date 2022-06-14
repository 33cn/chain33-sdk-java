package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

/**
 * @authoer lhl
 * @date 2022/6/10 下午6:48
 */
public class EvmLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private String[] topic;

    private String  data;

    public String[] getTopic() {
        return topic;
    }

    public void setTopic(String[] topic) {
        this.topic = topic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
