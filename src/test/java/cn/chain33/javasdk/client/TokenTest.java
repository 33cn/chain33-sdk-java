package cn.chain33.javasdk.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class TokenTest {

	
    String ip = "192.168.0.193";
    RpcClient client = new RpcClient(ip, 8801);
    
    /**
     * 
     * @throws Exception 
     * @description 创建自定义积分的黑名单
     *
     */
    @Test
    public void createBlackList() throws Exception {
    	
    	JSONObject blackListPayload = new JSONObject();
    	blackListPayload.put("key", "token-blacklist");
    	blackListPayload.put("value", "BTC");
    	blackListPayload.put("op", "add");
    	client.createTransaction("manage", "Modify", blackListPayload);

    }
}
