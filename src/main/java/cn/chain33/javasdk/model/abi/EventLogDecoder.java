package cn.chain33.javasdk.model.abi;

import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.abi.datatypes.Type;
import cn.chain33.javasdk.model.abi.spi.FunctionReturnDecoderProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

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
            String rawInput, Event event) {
       //解析参数要按顺位返回
        List<Type> values= decoder.decodeEventParameters(rawInput, event.getParsedParameters());
        List<Type> parsedValues =  new ArrayList<Type>();
        List<Integer> indexs = event.getParsedParametersIndex();
        if (event.isRecordered()){
            for(int i=0;i<values.size();i++){
               parsedValues.set(indexs.get(i), values.get(i));
            }
            return parsedValues;
        }

        return values;
    }
    //TODO 后面有时间的话indexed 和noindexed 参数解析接口也要单独实现
}

