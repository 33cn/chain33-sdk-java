package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.model.abi.TypeReference;
import cn.chain33.javasdk.model.abi.datatypes.AddressBTC;
import cn.chain33.javasdk.model.abi.datatypes.AddressETH;
import cn.chain33.javasdk.model.abi.datatypes.Event;
import cn.chain33.javasdk.model.enums.EncodeType;
import cn.chain33.javasdk.server.http.handler.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @authoer lhl
 * @date 2022/6/20 上午9:34
 */
public class HttpServerTest {
    /**
     * 用户在使用时需要实现 Outflow 回调接口，可以根据实际应用场景选择实现某一个接口或者多个接口
     * 本例中，DefaultOutflow只是简单打印一下解析后的推送数据.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //开启8080端口服务
            InnerHttpServer server = new InnerHttpServer(8080);
            //用户实现的解析后数据处理接口
            DefaultOutflow defaultOutflow = new DefaultOutflow();
            //需要解析的event事件
            Event event = new Event("SetAddress", Arrays.asList(new TypeReference<AddressBTC>() {
            }, new TypeReference<AddressBTC>() {
            }));
            List<Event> eventList = new ArrayList<Event>();
            eventList.add(event);
            //这里定义成JSON编码还是proto编码,用户无需关心,默认已经实现5中handler接口会自动处理,流出的数据对象就是Outflow接口中定义的入参对象。
            //建议编码用protobuf方式
            EvmEventHandler evmEventHandler = new EvmEventHandler(EncodeType.JSON, eventList, defaultOutflow);
            //chain33节点推送服务地址
            System.out.println("URL = http://hosthost:8080" + evmEventHandler.getURI());
            BlockHeaderHandler blockHeaderHandler = new BlockHeaderHandler(EncodeType.JSON, defaultOutflow);
            BlockHandler blockHandler = new BlockHandler(EncodeType.JSON, defaultOutflow);
            TxResultHandler txResultHandler = new TxResultHandler(EncodeType.PROTOBUFF, defaultOutflow);
            System.out.println("URL = http://hosthost:8080" + txResultHandler.getURI());
            TxReceiptHandler txReceiptHandler = new TxReceiptHandler(EncodeType.PROTOBUFF, defaultOutflow);
            //这里注册了5种推送服务处理器,用户可以自己选择注册一种或者多种处理器
            server.registerHandlers(blockHandler, blockHeaderHandler, evmEventHandler, txResultHandler, txReceiptHandler);
            //启动服务
            server.start();
            System.out.println("---started---");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
