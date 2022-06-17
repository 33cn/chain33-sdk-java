package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/17 下午4:38
 */
public class EvmLogParseInBlocks implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<EvmLogParseInBlock> evmLogParseInBlockList;

    public List<EvmLogParseInBlock> getEvmLogParseInBlockList() {
        return evmLogParseInBlockList;
    }

    public void setEvmLogParseInBlockList(List<EvmLogParseInBlock> evmLogParseInBlockList) {
        this.evmLogParseInBlockList = evmLogParseInBlockList;
    }
}
