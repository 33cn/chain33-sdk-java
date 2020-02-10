package cn.chain33.javasdk.client;

import org.junit.Test;

import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.utils.TransactionUtil;

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

    	// 管理合约名称
    	String execerName = "manage";
    	// 管理合约对应的区块链地址
    	String toAddress = client.convertExectoAddr(execername);
    	TransactionUtil.createAndSignManage("token-blacklist", "BTC", "add", "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4", SignType.SECP256K1, execerName, toAddress);

    }
}
