package cn.chain33.javasdk.model;

import cn.chain33.javasdk.model.rpcresult.Int64Result;
import cn.chain33.javasdk.model.rpcresult.ListPushesResult;
import org.junit.Test;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;

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
     */
    @Test
    public void push(){
        Map<String,Boolean> m = new HashMap();
        m.put("coin",true);
        BooleanResult result = clientMain.addPushSubscribe("test1","http://127.0.0.1:8080","json",0,0,"",0,m);
        System.out.println(result.toString());
    }

    /**
     *  获取推送列表
     */
    @Test
    public void listPushes(){
        ListPushesResult result = clientMain.listPushes();
        System.out.println(result.toString());
    }

    /**
     *  获取name对应推送最新seq值
     */
    @Test
    public void getPushSeqLastNum(){
        String name = "test";
        Int64Result result = clientMain.getPushSeqLastNum(name);
        System.out.println(result.toString());
    }
}
