package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/17 上午9:23
 */
public class EvmLogParseInBlock implements Serializable {

    private static final long serialVersionUID = 1L;

    private long blockHeight;

    private List<EvmLogParseInTx>  evmLogParseInTxeList;

    public long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public List<EvmLogParseInTx> getEvmLogParseInTxeList() {
        return evmLogParseInTxeList;
    }

    public void setEvmLogParseInTxeList(List<EvmLogParseInTx> evmLogParseInTxeList) {
        this.evmLogParseInTxeList = evmLogParseInTxeList;
    }
}
