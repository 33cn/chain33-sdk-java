package cn.chain33.javasdk.model;

import cn.chain33.javasdk.model.rpcresult.Int64Result;
import cn.chain33.javasdk.model.rpcresult.ListPushesResult;
import org.junit.Test;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PushTest {
    // 联盟链节点IP
    String mainIp = "主链IP";
    // 平行链服务端口
    int mainPort = 8801;
    RpcClient clientMain = new RpcClient(mainIp, mainPort);

    // 平行链节点IP
    String paraIp = "平行链节点IP";
    // 平行链服务端口
    int paraPort = 8901;
    RpcClient clientPara = new RpcClient(paraIp, paraPort);

    /**
     *  添加推送url
     *  1、分别注册区块（区块头）推送服务或者合约回执推送服务，注册成功之后就开始推送;
     *  注册时使用rpc接口Chain33.AddPushSubscribe进行注册，一旦通过name完成注册，其他订阅用户就不能使用相同的名字进行订阅;
     *  注册用户数最大上限为100个，超过100个，不能继续注册;
     *  必须保持一致，不然会出错；
     *  lastSequence=0，lastHeight=0，lastBlockHash=“”，从零开始推送。
     *  2、重新激活
     *  当连续推送3次失败之后，就会停止向该用户进行推送；
     *  如果接收应用程序重启后，需要继续接收数据，则直接通过原有注册信息激活即可，推送服务就会从上次推送成功处，继续推送;
     *  当注册的名字name相同，不管url是否相同，会有以下几种情况，并做不同的处理:
     *  - URL不同
     *  提示该name已经被注册,注册失败；
     *  - URL相同
     *  如果推送已经停止，则重新开始推送；
     *  如果推送正常，则继续推送；
     * @throws IOException 
     */
    @Test
    public void push() throws IOException{
        Map<String,Boolean> m = new HashMap<String, Boolean>();
        m.put("coin",true);
        BooleanResult result = clientMain.addPushSubscribe("test1","http://127.0.0.1:8080","json",0,0,"",0,m);
        System.out.println(result.toString());
    }

    /**
     *  获取推送列表
     * @throws IOException 
     */
    @Test
    public void listPushes() throws IOException{
        ListPushesResult result = clientMain.listPushes();
        System.out.println(result.toString());
    }

    /**
     *  获取name对应推送最新seq值
     * @throws IOException 
     */
    @Test
    public void getPushSeqLastNum() throws IOException{
        String name = "test";
        Int64Result result = clientMain.getPushSeqLastNum(name);
        System.out.println(result.toString());
    }
}
