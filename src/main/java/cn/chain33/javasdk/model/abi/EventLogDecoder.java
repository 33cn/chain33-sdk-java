package cn.chain33.javasdk.model.abi;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.abi.datatypes.Type;
import cn.chain33.javasdk.model.abi.spi.FunctionReturnDecoderProvider;

import java.util.*;

/**
 * @authoer lhl
 * @date 2022/6/12 上午10:26
 */
public abstract class EventLogDecoder {

    private static final FunctionReturnDecoder decoder;

    static {
        //加载已经实现的默认decoder解析器
        ServiceLoader<FunctionReturnDecoderProvider> loader =
                ServiceLoader.load(FunctionReturnDecoderProvider.class);
        final Iterator<FunctionReturnDecoderProvider> iterator = loader.iterator();

        decoder = iterator.hasNext() ? iterator.next().get() : new DefaultFunctionReturnDecoder();
    }
    /**
     * 反向解析Event日志参数值
     *
     * @param rawInput
     * @param event
     * @return
     */
    public static List<Type> decodeEventParameters(
            String rawInput, Event event){
        return decoder.decodeEventParameters(rawInput,event.getParameters());
    }
}

