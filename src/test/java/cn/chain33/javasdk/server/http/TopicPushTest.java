package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;
import cn.chain33.javasdk.model.rpcresult.Int64Result;
import cn.chain33.javasdk.model.rpcresult.ListPushesResult;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TopicPushTest {
    // 节点IP
    String Ip = "localhost";
    // 服务端口
    int Port = 8901;
    RpcClient client = new RpcClient(Ip, Port);

    /**
     * 添加推送url
     * 1、分别注册区块（区块头）推送服务或者合约回执推送服务，注册成功之后就开始推送;
     * 注册时使用rpc接口Chain33.AddPushSubscribe进行注册，一旦通过name完成注册，其他订阅用户就不能使用相同的名字进行订阅;
     * 注册用户数最大上限为100个，超过100个，不能继续注册;
     * 必须保持一致，不然会出错；
     * lastSequence=0，lastHeight=0，lastBlockHash=“”，从零开始推送。
     * 2、重新激活
     * 当连续推送3次失败之后，就会停止向该用户进行推送；
     * 如果接收应用程序重启后，需要继续接收数据，则直接通过原有注册信息激活即可，推送服务就会从上次推送成功处，继续推送;
     * 当注册的名字name相同，不管url是否相同，会有以下几种情况，并做不同的处理:
     * - URL不同
     * 提示该name已经被注册,注册失败；
     * - URL相同
     * 如果推送已经停止，则重新开始推送；
     * 如果推送正常，则继续推送；
     *
     * @throws IOException
     */
    @Test
    public void topicBlockPush() throws IOException {
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        BooleanResult result = client.addPushSubscribe("block", "http://127.0.0.1:8080/block/json", "jrpc", 0, 0, "", 0, m);
        System.out.println(result.toString());
    }

    @Test
    public void topicBlockHeaderPush() throws IOException {
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        BooleanResult result = client.addPushSubscribe("blockheader", "http://127.0.0.1:8080/blockheader/json", "jrpc", 0, 0, "", 1, m);
        System.out.println(result.toString());
    }

    @Test
    public void topicTxReceiptPush() throws IOException {
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        BooleanResult result = client.addPushSubscribe("txreceipt1", "http://127.0.0.1:8080/txreceipt/protobuf", "grpc", 0, 0, "", 2, m);
        System.out.println(result.toString());
    }

    @Test
    public void topicTxResultPush() throws IOException {
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        m.put("coins",true);
        BooleanResult result = client.addPushSubscribe("txResult", "http://127.0.0.1:8080/txresult/protobuf", "grpc", 0, 0, "", 3, m);
        System.out.println(result.toString());
    }

    @Test
    public void topicEVMEventPush() throws IOException {
        Map<String, Boolean> m = new HashMap<String, Boolean>();
        //订阅指定某个evm合约地址下的日志推送,然后由EvmEventHandler解析用户想要解析的event中各个参数值
        m.put("14Crdcg6v56Pr8MBd1BYob92ECtcnkFcHn",true);
        BooleanResult result = client.addPushSubscribe("EVMEvent", "http://127.0.0.1:8080/evmevent/json", "jrpc", 0, 0, "", 4, m);
        System.out.println(result.toString());
    }

    /**
     * 获取推送列表
     *
     * @throws IOException
     */
    @Test
    public void listPushes() throws IOException {
        ListPushesResult result = client.listPushes();
        System.out.println(result.toString());
    }

    /**
     * 获取name对应推送最新seq值
     *
     * @throws IOException
     */
    @Test
    public void getPushSeqLastNum() throws IOException {
        String name = "test";
        Int64Result result = client.getPushSeqLastNum(name);
        System.out.println(result.toString());
    }
}
