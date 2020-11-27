package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.utils.EvmUtil;
import cn.chain33.javasdk.utils.HexUtil;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

public class EvmTest {

	// 区块链IP
	String ip = "119.45.1.41";
	// 区块链服务端口
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);

    @Test
    public void testEvmContract() throws InterruptedException {
        String privateKey = "0x85bf7aa29436bb186cac45ecd8ea9e63e56c5817e127ebb5e99cd5a9cbfe0f23";

        String code = "0x608060405234801561001057600080fd5b5060c78061001f6000396000f3fe6080604052348015600f57600080fd5b506004361060325760003560e01c806360fe47b11460375780636d4ce63c146062575b600080fd5b606060048036036020811015604b57600080fd5b8101908080359060200190929190505050607e565b005b60686088565b6040518082815260200191505060405180910390f35b8060008190555050565b6000805490509056fea2646970667358221220ed06cf1aefb69c19593b31e7039b83768108c2943ca726b68b351622b14a292f64736f6c63430007040033";

        String abi = "[\n" +
                "\t{\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"get\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"internalType\": \"uint256\",\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"internalType\": \"uint256\",\n" +
                "\t\t\t\t\"name\": \"_x\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"set\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t}\n" +
                "]";
        
        // 部署合约
        String txEncode = EvmUtil.createEvmContract(HexUtil.fromHexString(code), "", "evm-sdk-test", abi, privateKey);
        String submitTransaction = client.submitTransaction(txEncode);
        String contractName = submitTransaction;
        System.out.println(submitTransaction);
        Thread.sleep(1000);
        
        // GAS费预估算
//        String feelog = client.queryEVMGas("evm", code, abi, "");

        // 调用合约
        txEncode = EvmUtil.callEvmContract("".getBytes(),"", 0, "get()", contractName, privateKey);
        submitTransaction = client.submitTransaction(txEncode);
        System.out.println(submitTransaction);
        Thread.sleep(1000);
        
        // 计算合约地址
        String contractAddress = client.convertExectoAddr("user.evm." + contractName);
        System.out.println(contractAddress);
        // 查询
        JSONArray result = client.queryEVMABIResult(contractAddress, "user.evm." + contractName, "get()");
        System.out.println("查询结果：　" + result.toJSONString());

        // 销毁合约
        txEncode = EvmUtil.destroyEvmContract(contractName, privateKey);
        submitTransaction = client.submitTransaction(txEncode);
        System.out.println(submitTransaction);
        Thread.sleep(1000);

        // 再次调用合约
        txEncode = EvmUtil.callEvmContract("".getBytes(),"", 0, "get()", contractName, privateKey);
        submitTransaction = client.submitTransaction(txEncode);
        System.out.println(submitTransaction);
        
    }

}
