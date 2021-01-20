package cn.chain33.javasdk.model;

import org.junit.Test;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;

import java.util.HashMap;
import java.util.Map;

public class PushTest {
    // 联盟链节点IP
    String mainIp = "47.92.105.160";
    // 平行链服务端口
    int mainPort = 8901;
    RpcClient clientMain = new RpcClient(mainIp, mainPort);

    // 平行链节点IP
    String paraIp = "161.117.186.252";
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
        BooleanResult result = clientMain.addPushSubscribe("test","192.168.0.1","json",120,120,"ssss",0,m);
        System.out.println(result.toString());
    }
}
