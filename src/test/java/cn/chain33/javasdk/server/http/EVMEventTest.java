package cn.chain33.javasdk.server.http;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.abi.EventEncoder;
import cn.chain33.javasdk.model.abi.EventLogDecoder;
import cn.chain33.javasdk.model.abi.FunctionEncoder;
import cn.chain33.javasdk.model.abi.TypeReference;
import cn.chain33.javasdk.model.abi.datatypes.*;
import cn.chain33.javasdk.model.abi.datatypes.generated.Uint256;
import cn.chain33.javasdk.model.enums.AddressType;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.rpcresult.EvmLog;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.AddressUtil;
import cn.chain33.javasdk.utils.ByteUtil;
import cn.chain33.javasdk.utils.EvmUtil;
import cn.chain33.javasdk.utils.HexUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @authoer lhl
 * @date 2022/6/20 下午3:48
 */
public class EVMEventTest {
    String ip = "localhost";
    RpcClient client = new RpcClient(ip, 8901);
    String TestA_BIN = "608060405234801561001057600080fd5b5060405161043d38038061043d8339818101604052810190610032919061008e565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610100565b600081519050610088816100e9565b92915050565b6000602082840312156100a057600080fd5b60006100ae84828501610079565b91505092915050565b60006100c2826100c9565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6100f2816100b7565b81146100fd57600080fd5b50565b61032e8061010f6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80632096525514610051578063552410771461006f578063b895c74a1461008b578063f2c57866146100a9575b600080fd5b6100596100c7565b6040516100669190610250565b60405180910390f35b610089600480360381019061008491906101ab565b6100d0565b005b61009361016a565b6040516100a09190610250565b60405180910390f35b6100b1610170565b6040516100be9190610215565b60405180910390f35b60008054905090565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610160576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161015790610230565b60405180910390fd5b8060008190555050565b60005481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000813590506101a5816102e1565b92915050565b6000602082840312156101bd57600080fd5b60006101cb84828501610196565b91505092915050565b6101dd8161027c565b82525050565b60006101f060168361026b565b91506101fb826102b8565b602082019050919050565b61020f816102ae565b82525050565b600060208201905061022a60008301846101d4565b92915050565b60006020820190508181036000830152610249816101e3565b9050919050565b60006020820190506102656000830184610206565b92915050565b600082825260208201905092915050565b60006102878261028e565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b7f596f7520646f6e27742068617665206163636573732100000000000000000000600082015250565b6102ea816102ae565b81146102f557600080fd5b5056fea2646970667358221220bf17415e6ec2228dc7725894e29a4759d25b5661f1cbc8ecda3f01e9f18940f064736f6c63430008040033";
    String TestA_ABI = "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"b\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[],\"name\":\"_B\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"_value\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getValue\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"setValue\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";
    String TestB_BIN = "608060405234801561001057600080fd5b5061055b806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063209652551461005c578063552410771461007a57806376e97a98146100965780638f4af7e7146100b4578063d2d26346146100d2575b600080fd5b6100646100ee565b604051610071919061047c565b60405180910390f35b610094600480360381019061008f91906103c7565b610198565b005b61009e610275565b6040516100ab9190610446565b60405180910390f35b6100bc61029e565b6040516100c99190610461565b60405180910390f35b6100ec60048036038101906100e7919061039e565b6102c2565b005b60008060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663209652556040518163ffffffff1660e01b815260040160206040518083038186803b15801561015757600080fd5b505afa15801561016b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061018f91906103f0565b90508091505090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166355241077826040518263ffffffff1660e01b81526004016101f1919061047c565b600060405180830381600087803b15801561020b57600080fd5b505af115801561021f573d6000803e3d6000fd5b50505050803373ffffffffffffffffffffffffffffffffffffffff167fa9d8a4f3fc191fb61df2687323053abf87eb27f0fab7b5d3f4d97fd50cc7a7c78360405161026a919061047c565b60405180910390a350565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167ff8ccd674427631ea146851d7f64c7106849c71907e782a5316b70ff16b7dfa8760405160405180910390a350565b60008135905061036e816104f7565b92915050565b6000813590506103838161050e565b92915050565b6000815190506103988161050e565b92915050565b6000602082840312156103b057600080fd5b60006103be8482850161035f565b91505092915050565b6000602082840312156103d957600080fd5b60006103e784828501610374565b91505092915050565b60006020828403121561040257600080fd5b600061041084828501610389565b91505092915050565b61042281610497565b82525050565b610431816104d3565b82525050565b610440816104c9565b82525050565b600060208201905061045b6000830184610419565b92915050565b60006020820190506104766000830184610428565b92915050565b60006020820190506104916000830184610437565b92915050565b60006104a2826104a9565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60006104de826104e5565b9050919050565b60006104f0826104a9565b9050919050565b61050081610497565b811461050b57600080fd5b50565b610517816104c9565b811461052257600080fd5b5056fea2646970667358221220eccfe077f8046e8003919cd9e5376287269ab254f3aeefe7e4be3389a581e35b64736f6c63430008040033";
    String TestB_ABI = "[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"testA\",\"type\":\"address\"}],\"name\":\"SetAddress\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"value1\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value2\",\"type\":\"uint256\"}],\"name\":\"SetValue\",\"type\":\"event\"},{\"inputs\":[],\"name\":\"_A\",\"outputs\":[{\"internalType\":\"contract TestA\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getTestA\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getValue\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"a\",\"type\":\"address\"}],\"name\":\"setTestA\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"setValue\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";
    String PrivateKey = "cc38546e9e659d15e6b4893f0ab32a06d103931a8230b0bde71459d2b27d6944";

    //先部署B合约（不带构造参数）
    @Test
    public void testEvmContract() throws Exception {
        //部署合约B（无参构造函数合约部署）
        String tx = EvmUtil.createEvmContract(TestB_BIN, FunctionEncoder.encodeConstructor(Collections.emptyList()), "this is test B deploy", "", PrivateKey, SignType.SECP256K1,AddressType.BTC_ADDRESS,0, "", 1000000);
        String txhash = client.submitTransaction(tx);
        System.out.print("部署合约交易hash = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        System.out.println("执行结果 = " + txResult.getReceipt().getTyname());

        //获取B合约地址
        String TestB_addr = EvmUtil.getContractAddr((JSONObject) JSONObject.toJSON(Arrays.stream(txResult.getReceipt().getLogs()).filter(log -> log.getTy() == 603).findFirst().get().getLog()));
        System.out.println("TestB合约地址= " + TestB_addr);

        //判断于本地计算的合约地址是否相同
        String calcAddr=AddressUtil.getContractAddress(txhash,AddressUtil.genAddress(PrivateKey, AddressType.BTC_ADDRESS),AddressType.BTC_ADDRESS);
        System.out.println("本地计算出来的合约地址= " + calcAddr);
        Assert.assertEquals(TestB_addr,calcAddr);

        //部署A合约（有参构造函数合约部署）
        String code = FunctionEncoder.encodeConstructor(Arrays.asList(new AddressBTC(TestB_addr)));
        tx = EvmUtil.createEvmContract(TestA_BIN, code, "this is test B deploy", "", PrivateKey,SignType.SECP256K1,AddressType.BTC_ADDRESS,0, "", 1000000);
        txhash = client.submitTransaction(tx);
        System.out.println("Test A 合约交易hash = " + txhash);
        Thread.sleep(20000);
        txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        System.out.println("执行结果 = " + txResult.getReceipt().getTyname());
        String TestA_addr = EvmUtil.getContractAddr((JSONObject) JSONObject.toJSON(Arrays.stream(txResult.getReceipt().getLogs()).filter(log -> log.getTy() == 603).findFirst().get().getLog()));
        System.out.println("TestA合约地址= " + TestA_addr);

        //调用B合约(把合约A的地址加入到B合约中）
        String encodeParams = FunctionEncoder.encode(new Function("setTestA", Arrays.asList(new AddressBTC(TestA_addr)), Collections.emptyList()));
        tx = EvmUtil.callEvmContract(encodeParams, TestB_addr, "this is test B Set A address", 0, PrivateKey,SignType.SECP256K1,AddressType.BTC_ADDRESS,0, "", 1000000);
        txhash = client.submitTransaction(tx);
        System.out.print("添加地址交易哈希 = " + txhash);
        Thread.sleep(20000);
        txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");

        //解析Event事件
        txResult = client.queryTransaction(txhash);
        //构造event
        Event event = new Event("SetAddress", Arrays.asList(new TypeReference<AddressBTC>() {
        }, new TypeReference<AddressBTC>() {
        }));
        //获取eventLog日志
        List<String> logList=EvmUtil.getEvmLogList(txResult,event);
        System.out.println("event log list: " + logList);
        //解析匹配到的日志
        //处理evmLog
        List<List<Type>> resultList = Optional.ofNullable(logList).orElse(new ArrayList<String>()).stream().map(item -> {
            List<Type> result = EventLogDecoder.decodeEventParameters(item, event);
            return result;
        }).collect(Collectors.toList());

        List<Type> result = resultList.get(0);
        for (int i = 0; i < result.size(); i++) {
            //打印参数类型及值
            System.out.println("type: " + result.get(i).getTypeAsString() + " ,  value: " + result.get(i).getValue());
        }

        //通过B合约接口给A合约中Value设置
        encodeParams = FunctionEncoder.encode(new Function("setValue", Arrays.asList(new Uint256(BigInteger.valueOf(123456789L))), Collections.emptyList()));
        tx = EvmUtil.callEvmContract(encodeParams, TestB_addr, "this is test B Set A value", 0, PrivateKey, SignType.SECP256K1,AddressType.BTC_ADDRESS,0, "",1000000);
        txhash = client.submitTransaction(tx);
        System.out.print("设置Value交易哈希 = " + txhash);
        Thread.sleep(20000);
        txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");

        //通过只读函数查询value值
        result = client.callEVMContractReadOnlyFunc(TestA_addr, new Function("getValue", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        })));
        System.out.println(result.get(0).getValue());
        Assert.assertEquals(new Uint256(BigInteger.valueOf(123456789L)).getValue(), result.get(0).getValue());
        result = client.callEVMContractReadOnlyFunc(TestB_addr, new Function("getValue", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        })));
        System.out.println(result.get(0).getValue());
        Assert.assertEquals(new Uint256(BigInteger.valueOf(123456789L)).getValue(), result.get(0).getValue());

    }

    @Test
    public void testcallEVMContractReadOnlyFunc() throws Exception {
        String TestB_Address = "14Crdcg6v56Pr8MBd1BYob92ECtcnkFcHn";
        String TestA_Address = "19DNqjfQ4vi4p8Wkx7iGxioC6VapNKMakA";

        //查询value值
        List<Type> result = client.callEVMContractReadOnlyFunc(TestA_Address, new Function("getValue", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
        })));
        System.out.println(result.get(0).getValue());
        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getTestA", Collections.emptyList(), Collections.singletonList(new TypeReference<AddressBTC>() {
        })));
        System.out.println(result.get(0).getValue());
        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getTestA", Collections.emptyList(), Collections.singletonList(new TypeReference<AddressETH>() {
        })));
        System.out.println(result.get(0).getValue());

    }

    @Test
    public void testEvmEventParse() throws Exception {
        String TestB_Address = "14Crdcg6v56Pr8MBd1BYob92ECtcnkFcHn";
        String TestA_Address = "19DNqjfQ4vi4p8Wkx7iGxioC6VapNKMakA";
        String encodeParams = FunctionEncoder.encode(new Function("setTestA", Arrays.asList(new AddressBTC(TestA_Address)), Collections.emptyList()));
        String tx = EvmUtil.callEvmContract(encodeParams, TestB_Address, "this is test B Set A address", 0, PrivateKey,SignType.SECP256K1,AddressType.BTC_ADDRESS,0, "", 1000000);
        String  txhash = client.submitTransaction(tx);
        System.out.print("添加地址交易哈希 = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
    }




    @Test
    public void testEncodeFunction() {

        String code1 = FunctionEncoder.encodeConstructor(Arrays.asList(new AddressETH("0xf39e69a8f2c1041edd7616cf079c7084bb7a5242")));
        System.out.println(code1);
        String code2 = FunctionEncoder.encodeConstructor(Arrays.asList(new AddressBTC("1PD8yKphczWERv8KnjQrdHjLxDWqXkBLgN")));
        System.out.println(code2);
        Assert.assertEquals(code1, code2);

        String funcEncode1 = FunctionEncoder.encode(new Function("setTestA", Arrays.asList(new AddressETH("0xf39e69a8f2c1041edd7616cf079c7084bb7a5242")), Collections.emptyList()));
        System.out.println(funcEncode1);
        String funcEncode2 = FunctionEncoder.encode(new Function("setTestA", Arrays.asList(new AddressBTC("1PD8yKphczWERv8KnjQrdHjLxDWqXkBLgN")), Collections.emptyList()));
        System.out.println(funcEncode2);
        Assert.assertEquals(funcEncode1, funcEncode2);
        System.out.println(new AddressETH("0xf39e69a8f2c1041edd7616cf079c7084bb7a5242").toBTCAddress());
        System.out.println(new AddressBTC("1PD8yKphczWERv8KnjQrdHjLxDWqXkBLgN").toETHAddress());
    }
}
