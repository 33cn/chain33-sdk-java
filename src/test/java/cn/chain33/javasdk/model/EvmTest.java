package cn.chain33.javasdk.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.ByteUtil;
import cn.chain33.javasdk.utils.EvmUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

public class EvmTest {

	// 区块链IP
	String ip = "区块链IP地址";
	// 区块链服务端口
	int port = 8801;
	RpcClient client = new RpcClient(ip, port);

	// 合约部署人对应的区块链地址和私钥
	String address = "1M8gvr1DZ1KKVjf6XW6aYR6pHGeDRspdCx";
    String privateKey = "452281167e7fa65cdd5bcb1e40565bf06a1aa3ce4fc8954848d0427a4cc27180";

    @Test
    public void testEvmContract() throws Exception {

        // 部署合约
        String txEncode;
        String txhash = "";
   
        // 存证合约owner的address和private key
        String ownerAddress = "1FpJG4PHJARQNpyXkF6E6f9rvBx97zDaaJ";
        String ownerPrivateKey = "2d5cd98d637033028c7ee7ca78e5dd71d8aaaada0e8b3244f18bba7b6a75ba8c";
        
        // 合约代码：https://bty33.oss-cn-shanghai.aliyuncs.com/chain33Dev/BAAS/%E5%AD%98%E8%AF%81%E5%90%88%E7%BA%A6.zip
        String codes = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610651806100326000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806313af4035146100515780637d24e37e14610079578063a8934d79146101a6578063b2bdfa7b146102c1575b600080fd5b6100776004803603602081101561006757600080fd5b50356001600160a01b03166102e5565b005b6100776004803603604081101561008f57600080fd5b8101906020810181356401000000008111156100aa57600080fd5b8201836020820111156100bc57600080fd5b803590602001918460018302840111640100000000831117156100de57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929594936020810193503591505064010000000081111561013157600080fd5b82018360208201111561014357600080fd5b8035906020019184600183028401116401000000008311171561016557600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610350945050505050565b61024c600480360360208110156101bc57600080fd5b8101906020810181356401000000008111156101d757600080fd5b8201836020820111156101e957600080fd5b8035906020019184600183028401116401000000008311171561020b57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061045f945050505050565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561028657818101518382015260200161026e565b50505050905090810190601f1680156102b35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102c9610553565b604080516001600160a01b039092168252519081900360200190f35b6000546001600160a01b0316331461032e5760405162461bcd60e51b81526004018080602001828103825260268152602001806105f66026913960400191505060405180910390fd5b600080546001600160a01b0319166001600160a01b0392909216919091179055565b6000546001600160a01b031633146103995760405162461bcd60e51b81526004018080602001828103825260268152602001806105f66026913960400191505060405180910390fd5b8160008151116103e5576040805162461bcd60e51b815260206004820152601260248201527166696c65496420697320696e76616c69642160701b604482015290519081900360640190fd5b816001846040518082805190602001908083835b602083106104185780518252601f1990920191602091820191016103f9565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084516104599591949190910192509050610562565b50505050565b60606001826040518082805190602001908083835b602083106104935780518252601f199092019160209182019101610474565b518151600019602094850361010090810a820192831692199390931691909117909252949092019687526040805197889003820188208054601f60026001831615909802909501169590950492830182900482028801820190528187529294509250508301828280156105475780601f1061051c57610100808354040283529160200191610547565b820191906000526020600020905b81548152906001019060200180831161052a57829003601f168201915b50505050509050919050565b6000546001600160a01b031681565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105a357805160ff19168380011785556105d0565b828001600101855582156105d0579182015b828111156105d05782518255916020019190600101906105b5565b506105dc9291506105e0565b5090565b5b808211156105dc57600081556001016105e156fe6f6e6c7920617574686f72697a6564206f776e65722063616e2073746f72652066696c65732ea264697066735822122031f48e60df7c860caf840a264a2bf73faebcde84c840a53808e210c386ebde7a64736f6c634300060c0033";
        String abi = "[{\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"fileId\",\"type\":\"string\"}],\"name\":\"getFileById\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"fileId\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"name\":\"setFileStockById\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

        QueryTransactionResult txResult;
        // 部署合约
        try {
            byte[] code = ByteUtil.merge(HexUtil.fromHexString(codes), abi.getBytes());

            txEncode = EvmUtil.createEvmContract(code, "", "evm-sdk-test", privateKey, "");
            txhash = client.submitTransaction(txEncode);
            System.out.print("部署合约交易hash = " + txhash);
            Thread.sleep(5000);
            txResult = client.queryTransaction(txhash);
            Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
            System.out.println("; 执行结果 = " + txResult.getReceipt().getTyname());
                        
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
        
        // 计算合约地址
        String contractAddress = TransactionUtil.convertExectoAddr(address + txhash.substring(2));
        System.out.println("部署好的合约地址 = " + contractAddress);
        
        // 调用合约，设置权限用户
    	byte[] setOwner = EvmUtil.encodeParameter(abi, "setOwner", ownerAddress);
        txEncode = EvmUtil.callEvmContract(setOwner,"", 0, contractAddress, privateKey, "");
        txhash = client.submitTransaction(txEncode);
        System.out.print("调用授权合约的交易hash = " + txhash);
        Thread.sleep(5000);
        txResult = client.queryTransaction(txhash);
        System.out.println("; 执行结果 = " + txResult.getReceipt().getTyname());

        
        // 使用非授权的地址调用存证合约，此笔交易会执行失败，在交易体中会打印错误信息
        String fileId = "\"fileId000001\"";
        String content = "\"{\"档案编号\":\"ID0000001\",\"企业代码\":\"QY0000001\",\"业务标识\":\"DA000001\",\"来源系统\":\"OA\", \"文档摘要\",\"0x93689a705ac0bb4612824883060d73d02534f8ba758f5ca21a343beab2bf7b47\"}\"";
    	// 調用setFileStockById方法來存證，使用非权限用户签名
    	byte[] setFileStock = EvmUtil.encodeParameter(abi, "setFileStockById", fileId, content);
        txEncode = EvmUtil.callEvmContract(setFileStock,"", 0, contractAddress, privateKey, "");
        txhash = client.submitTransaction(txEncode);
        System.out.print("使用非授权地址调用存证合约的交易hash = " + txhash);
        Thread.sleep(5000);
        txResult = client.queryTransaction(txhash);
        System.out.println("; 执行结果 = " + txResult.getReceipt().getTyname());
        
        // 使用授权的地址调用存证合约，此笔交易会执行成功
        txEncode = EvmUtil.callEvmContract(setFileStock,"", 0, contractAddress, ownerPrivateKey, "");
        txhash = client.submitTransaction(txEncode);
        System.out.print("使用授权地址调用存证合约的交易hash = " + txhash);
        Thread.sleep(5000);
        txResult = client.queryTransaction(txhash);
        System.out.println("; 执行结果 = " + txResult.getReceipt().getTyname());
            
        byte[] packAbiGet = EvmUtil.encodeParameter(abi, "getFileById", fileId);

        JSONObject query = client.callEVMAbi(contractAddress, HexUtil.toHexString(packAbiGet));
        Assert.assertNotNull(query);
        JSONObject output = query.getJSONObject("result");
        List<?> result = EvmUtil.decodeOutput(abi, "getFileById", output);
        System.out.println("根据ID从链上的查询结果：" + result);

    }


}
