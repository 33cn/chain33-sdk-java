package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/17 上午9:22
 */
public class EvmLogParseInTx implements Serializable {

    private static final long serialVersionUID = 1L;

    private String txHash;

    private List<EventParams> eventParamsList;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public List<EventParams> getEventParamsList() {
        return eventParamsList;
    }

    public void setEventParamsList(List<EventParams> eventParamsList) {
        this.eventParamsList = eventParamsList;
    }
}
