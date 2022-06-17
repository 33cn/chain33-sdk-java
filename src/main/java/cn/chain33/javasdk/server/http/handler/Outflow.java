package cn.chain33.javasdk.server.http.handler;

import cn.chain33.javasdk.model.protobuf.BlockchainProtobuf;
import cn.chain33.javasdk.model.rpcresult.EvmLogParseInBlocks;
import cn.chain33.javasdk.model.protobuf.PushTxReceiptProtobuf;


/**
 * @authoer lhl
 * @date 2022/6/17 下午7:30
 *
 * 数据流出处理接口,用户可根据自己业务情况实现如下自己所需接口
 */
public interface Outflow {
    //evm订阅处理（解析后的event数据处理）
    void process(EvmLogParseInBlocks evmLogParseInBlocks);
    //区块订阅接收处理
    void process(BlockchainProtobuf.BlockSeqs blockSeqs);
    //区块头订阅处理
    void process(BlockchainProtobuf.HeaderSeqs headerSeqs);
    //交易回执订阅
    void process(PushTxReceiptProtobuf.TxReceipts4Subscribe txReceipts4Subscribe);
    //交易执行结果订阅处理,可用于检查交易是否执行成功
    void process(PushTxReceiptProtobuf.TxResultSeqs txResultSeqs);
}
