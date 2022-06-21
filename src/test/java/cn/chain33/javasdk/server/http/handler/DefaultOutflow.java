package cn.chain33.javasdk.server.http.handler;

import cn.chain33.javasdk.model.protobuf.BlockchainProtobuf;
import cn.chain33.javasdk.model.protobuf.PushTxReceiptProtobuf;
import cn.chain33.javasdk.model.rpcresult.EvmLogParseInBlocks;
import cn.chain33.javasdk.server.http.callback.Outflow;

/**
 * @authoer lhl
 * @date 2022/6/20 上午10:55
 */
public class DefaultOutflow implements Outflow {
    public DefaultOutflow() {
        super();
    }

    @Override
    public void callback(EvmLogParseInBlocks evmLogParseInBlocks) {

        evmLogParseInBlocks.getEvmLogParseInBlockList().forEach(evmLogParseInBlock -> {
            //block 级别
            System.out.println("block height: " + evmLogParseInBlock.getBlockHeight());
            evmLogParseInBlock.getEvmLogParseInTxeList().forEach(evmLogParseInTx -> {
                //tx 级别,每个交易有可能会触发多个事件
                System.out.println("tx hash: " + evmLogParseInTx.getTxHash());
                evmLogParseInTx.getEventParamsList().forEach(eventParams -> {
                    System.out.println("event: " + eventParams.getEvent());
                    eventParams.getParams().forEach(type -> {
                        System.out.println("type name: " + type.getTypeAsString() + "value: " + type.getValue());
                    });
                });
            });

        });

    }

    @Override
    public void callback(BlockchainProtobuf.BlockSeqs blockSeqs) {
        System.out.println("BlockSeqs: " + blockSeqs);
    }

    @Override
    public void callback(BlockchainProtobuf.HeaderSeqs headerSeqs) {
        System.out.println("HeaderSeqs: " + headerSeqs);
    }

    @Override
    public void callback(PushTxReceiptProtobuf.TxReceipts4Subscribe txReceipts4Subscribe) {
        System.out.println("TxReceipts4Subscribe: " + txReceipts4Subscribe);
    }

    @Override
    public void callback(PushTxReceiptProtobuf.TxResultSeqs txResultSeqs) {
        System.out.println("TxResultSeqs: " + txResultSeqs);
    }
}
