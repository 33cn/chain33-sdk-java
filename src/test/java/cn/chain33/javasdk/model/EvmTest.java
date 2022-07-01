package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.abi.EventEncoder;
import cn.chain33.javasdk.model.abi.EventLogDecoder;
import cn.chain33.javasdk.model.abi.FunctionEncoder;
import cn.chain33.javasdk.model.abi.TypeReference;
import cn.chain33.javasdk.model.abi.datatypes.*;
import cn.chain33.javasdk.model.abi.datatypes.generated.Uint256;
import cn.chain33.javasdk.model.enums.AddressType;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.rpcresult.EvmLog;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.*;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @authoer lhl
 * @date 2022/6/7 下午2:48
 */
public class EvmTest {
    String ip = "172.22.16.179";
    RpcClient client = new RpcClient(ip, 8921);
    String TestA_BIN = "608060405234801561001057600080fd5b5060405161043d38038061043d8339818101604052810190610032919061008e565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610100565b600081519050610088816100e9565b92915050565b6000602082840312156100a057600080fd5b60006100ae84828501610079565b91505092915050565b60006100c2826100c9565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6100f2816100b7565b81146100fd57600080fd5b50565b61032e8061010f6000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80632096525514610051578063552410771461006f578063b895c74a1461008b578063f2c57866146100a9575b600080fd5b6100596100c7565b6040516100669190610250565b60405180910390f35b610089600480360381019061008491906101ab565b6100d0565b005b61009361016a565b6040516100a09190610250565b60405180910390f35b6100b1610170565b6040516100be9190610215565b60405180910390f35b60008054905090565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610160576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161015790610230565b60405180910390fd5b8060008190555050565b60005481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000813590506101a5816102e1565b92915050565b6000602082840312156101bd57600080fd5b60006101cb84828501610196565b91505092915050565b6101dd8161027c565b82525050565b60006101f060168361026b565b91506101fb826102b8565b602082019050919050565b61020f816102ae565b82525050565b600060208201905061022a60008301846101d4565b92915050565b60006020820190508181036000830152610249816101e3565b9050919050565b60006020820190506102656000830184610206565b92915050565b600082825260208201905092915050565b60006102878261028e565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b7f596f7520646f6e27742068617665206163636573732100000000000000000000600082015250565b6102ea816102ae565b81146102f557600080fd5b5056fea26469706673582212208c63ddb6947935687ba10a707db39100bb896d68b2232eeb538385ee0824ff0064736f6c63430008040033";
    String TestA_ABI = "[{\"inputs\":[{\"internalType\":\"address\",\"name\":\"b\",\"type\":\"address\"}],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[],\"name\":\"_B\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"_value\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getValue\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"setValue\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";
    String TestB_BIN = "608060405234801561001057600080fd5b50610a5d806100206000396000f3fe6080604052600436106100a05760003560e01c80637c5ee4c5116100645780637c5ee4c5146101e35780638f4af7e7146101ed578063d0e30db014610218578063d2d2634614610222578063db13b24f1461024b578063f8b2cb4f14610267576100e2565b8063209652551461011d57806322968885146101485780632abb281114610173578063552410771461018f57806376e97a98146101b8576100e2565b366100e2577f8cabf31d2b1b11ba52dbb302817a3c9c83e4b2a5194d35121ab1354d69f6a4cb3330346040516100d8939291906108ba565b60405180910390a1005b7ffbf15a1bae5e021d024841007b692b167afd2a281a4ff0b44f47387eb388205c33346040516101139291906108f1565b60405180910390a1005b34801561012957600080fd5b506101326102a4565b60405161013f9190610935565b60405180910390f35b34801561015457600080fd5b5061015d61034e565b60405161016a9190610935565b60405180910390f35b61018d6004803603810190610188919061079c565b610356565b005b34801561019b57600080fd5b506101b660048036038101906101b191906107d8565b61040d565b005b3480156101c457600080fd5b506101cd6104ea565b6040516101da919061089f565b60405180910390f35b6101eb610513565b005b3480156101f957600080fd5b50610202610605565b60405161020f919061091a565b60405180910390f35b610220610629565b005b34801561022e57600080fd5b5061024960048036038101906102449190610773565b61062b565b005b6102656004803603810190610260919061079c565b6106c8565b005b34801561027357600080fd5b5061028e60048036038101906102899190610773565b610713565b60405161029b9190610935565b60405180910390f35b60008060008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663209652556040518163ffffffff1660e01b815260040160206040518083038186803b15801561030d57600080fd5b505afa158015610321573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103459190610801565b90508091505090565b600047905090565b8173ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f193505050506103cd577ffbf15a1bae5e021d024841007b692b167afd2a281a4ff0b44f47387eb388205c30826040516103c09291906108f1565b60405180910390a1610409565b7f8cabf31d2b1b11ba52dbb302817a3c9c83e4b2a5194d35121ab1354d69f6a4cb308383604051610400939291906108ba565b60405180910390a15b5050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166355241077826040518263ffffffff1660e01b81526004016104669190610935565b600060405180830381600087803b15801561048057600080fd5b505af1158015610494573d6000803e3d6000fd5b50505050803373ffffffffffffffffffffffffffffffffffffffff167fa9d8a4f3fc191fb61df2687323053abf87eb27f0fab7b5d3f4d97fd50cc7a7c7836040516104df9190610935565b60405180910390a350565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60003073ffffffffffffffffffffffffffffffffffffffff166040516024016040516020818303038152906040527f12cccb34000000000000000000000000000000000000000000000000000000007bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19166020820180517bffffffffffffffffffffffffffffffffffffffffffffffffffffffff83818316178352505050506040516105bc9190610888565b6000604051808303816000865af19150503d80600081146105f9576040519150601f19603f3d011682016040523d82523d6000602084013e6105fe565b606091505b5050905050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167ff8ccd674427631ea146851d7f64c7106849c71907e782a5316b70ff16b7dfa8760405160405180910390a350565b8173ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f1935050505015801561070e573d6000803e3d6000fd5b505050565b60008173ffffffffffffffffffffffffffffffffffffffff16319050919050565b600081359050610743816109f9565b92915050565b60008135905061075881610a10565b92915050565b60008151905061076d81610a10565b92915050565b60006020828403121561078557600080fd5b600061079384828501610734565b91505092915050565b600080604083850312156107af57600080fd5b60006107bd85828601610734565b92505060206107ce85828601610749565b9150509250929050565b6000602082840312156107ea57600080fd5b60006107f884828501610749565b91505092915050565b60006020828403121561081357600080fd5b60006108218482850161075e565b91505092915050565b61083381610966565b82525050565b600061084482610950565b61084e818561095b565b935061085e8185602086016109c6565b80840191505092915050565b610873816109a2565b82525050565b61088281610998565b82525050565b60006108948284610839565b915081905092915050565b60006020820190506108b4600083018461082a565b92915050565b60006060820190506108cf600083018661082a565b6108dc602083018561082a565b6108e96040830184610879565b949350505050565b6000604082019050610906600083018561082a565b6109136020830184610879565b9392505050565b600060208201905061092f600083018461086a565b92915050565b600060208201905061094a6000830184610879565b92915050565b600081519050919050565b600081905092915050565b600061097182610978565b9050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60006109ad826109b4565b9050919050565b60006109bf82610978565b9050919050565b60005b838110156109e45780820151818401526020810190506109c9565b838111156109f3576000848401525b50505050565b610a0281610966565b8114610a0d57600080fd5b50565b610a1981610998565b8114610a2457600080fd5b5056fea26469706673582212201a5cc4cd63aa59f453c12522d83ecb3de0685b72f119b40b28928d6d50b6f47164736f6c63430008040033";
    String TestB_ABI = "[{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"Fallback\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"Received\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"testA\",\"type\":\"address\"}],\"name\":\"SetAddress\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"value1\",\"type\":\"uint256\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value2\",\"type\":\"uint256\"}],\"name\":\"SetValue\",\"type\":\"event\"},{\"stateMutability\":\"payable\",\"type\":\"fallback\"},{\"inputs\":[],\"name\":\"CallTransTest\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"_A\",\"outputs\":[{\"internalType\":\"contract TestA\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"deposit\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"addr\",\"type\":\"address\"}],\"name\":\"getBalance\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getBalanceOfContract\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getTestA\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"name\":\"getValue\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"a\",\"type\":\"address\"}],\"name\":\"setTestA\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"_value\",\"type\":\"uint256\"}],\"name\":\"setValue\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"withdraw1\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"withdraw2\",\"outputs\":[],\"stateMutability\":\"payable\",\"type\":\"function\"},{\"stateMutability\":\"payable\",\"type\":\"receive\"}]";
    String PrivateKey = "cc38546e9e659d15e6b4893f0ab32a06d103931a8230b0bde71459d2b27d6944";
    String WithHoldPrivateKey="0x42c87f281b2cd101a5dc54b85fa6b753579be3f5770f1cf9b166188bf0c41b72";

    //先部署B合约（不带构造参数）
    @Test
    public void testEvmContractForYCC() throws Exception {
        //部署合约B（无参构造函数合约部署）
        String tx = EvmUtil.createEvmContractForYCC(TestB_BIN, FunctionEncoder.encodeConstructor(Collections.emptyList()), "this is test B deploy", "", PrivateKey, "user.p.parademo.", 1000000);
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
        String calcAddr=AddressUtil.getContractAddress(txhash,AddressUtil.genAddress(PrivateKey, AddressType.ETH_ADDRESS),AddressType.ETH_ADDRESS);
        System.out.println("本地计算出来的合约地址= " + calcAddr);
        Assert.assertEquals(TestB_addr,calcAddr);

        //部署A合约（有参构造函数合约部署）
        String code = FunctionEncoder.encodeConstructor(Arrays.asList(new AddressETH(TestB_addr)));
        tx = EvmUtil.createEvmContractForYCC(TestA_BIN, code, "this is test B deploy", "", PrivateKey, "user.p.parademo.", 1000000);
        txhash = client.submitTransaction(tx);
        System.out.println("Test A 合约交易hash = " + txhash);
        Thread.sleep(20000);
        txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        System.out.println("执行结果 = " + txResult.getReceipt().getTyname());
        String TestA_addr = EvmUtil.getContractAddr((JSONObject) JSONObject.toJSON(Arrays.stream(txResult.getReceipt().getLogs()).filter(log -> log.getTy() == 603).findFirst().get().getLog()));
        System.out.println("TestA合约地址= " + TestA_addr);

        //调用B合约(把合约A的地址加入到B合约中）
        String encodeParams = FunctionEncoder.encode(new Function("setTestA", Arrays.asList(new AddressETH(TestA_addr)), Collections.emptyList()));
        tx = EvmUtil.callEvmContractForYCC(encodeParams, TestB_addr, "this is test B Set A address", 0, PrivateKey, "user.p.parademo.", 1000000);
        txhash = client.submitTransaction(tx);
        System.out.print("添加地址交易哈希 = " + txhash);
        Thread.sleep(20000);
        txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");

        //解析Event事件
        txResult = client.queryTransaction(txhash);
        //构造event
        Event event = new Event("SetAddress", Arrays.asList(new TypeReference<AddressETH>() {
        }, new TypeReference<AddressETH>() {
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
        tx = EvmUtil.callEvmContractForYCC(encodeParams, TestB_addr, "this is test B Set A value", 0, PrivateKey, "user.p.parademo.", 1000000);
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
        String TestB_Address = "0xaca14135581f442369a08ba2798fc9cadebcea41";
        String TestA_Address = "0x98831dbc5e39f1863cf06d807966254e99e75506";

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
    public void testNobalanceTx()throws Exception{
        String tx = EvmUtil.createEvmContractForYCC(TestB_BIN, FunctionEncoder.encodeConstructor(Collections.emptyList()), "this is test B deploy", "", PrivateKey, "user.p.parademo.", 1000000);

        String noBalanceTx = TransactionUtil.createNoBalanceTx(TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(tx)), WithHoldPrivateKey,PrivateKey, SignType.ETH_SECP256K1, AddressType.ETH_ADDRESS, 999, 100000, "user.p.parademo.");
        System.out.println(noBalanceTx);
        client.submitTransaction(noBalanceTx);
        String txhash = HexUtil.toHexString(TransactionUtil.getTxHash(TransactionAllProtobuf.Transactions.parseFrom(TransactionAllProtobuf.Transaction.parseFrom(HexUtil.fromHexString(noBalanceTx)).getHeader().toByteArray()).getTxs(1)));
        System.out.print("部署合约交易hash = " + txhash);
        Thread.sleep(20000);
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
        System.out.println("执行结果 = " + txResult.getReceipt().getTyname());
    }

    /**
     * 平行链模式下，需要先将主链代币跨链到平行链上面，然后再将跨链的主币转移到evm中使用
     * evm执行器中使用案例，后面跨链内容补充完再完善用例
     *
     * @throws Exception
     */
//    @Test
//    public void testEvmTransfer() throws Exception {
//        String Addr="0xf39e69a8f2c1041edd7616cf079c7084bb7a5242";
//        String TestB_Address = "0xcf1f462df899011ec94bbc9e8cb313765958dcef";
//        String TestA_Address = "0x80d73cdf74fb90993003fec1ffac62a4cc2d371b";
//        //step1 先将主币coins资产转移到evm执行器平台名下
//        String tx = CoinsUtil.createTransferToExecTx("para", 200000000, "user.p.parademo.evm", AddressUtil.getToAddress("user.p.parademo.evm".getBytes(), AddressType.ETH_ADDRESS), "", PrivateKey, SignType.ETH_SECP256K1, AddressType.ETH_ADDRESS, 999, "user.p.parademo.", 1000000);
//        String txhash = client.submitTransaction(tx);
//        System.out.println("转移到evm平台下的主币交易哈希 = " + txhash);
//        Thread.sleep(20000);
//        QueryTransactionResult txResult = client.queryTransaction(txhash);
//        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
//        //step2 再将个人地址在evm执行器下面的资产转移到evm合约里
//        String encodeParams = FunctionEncoder.encode(new Function("deposit", Collections.emptyList(), Collections.emptyList()));
//        tx = EvmUtil.callEvmContract(encodeParams, TestB_Address, "deposit", 200000000L, PrivateKey, SignType.ETH_SECP256K1, AddressType.ETH_ADDRESS, 999, "user.p.parademo.", 1000000);
//        txhash = client.submitTransaction(tx);
//        System.out.println("往evm合约地址转账交易哈希 = " + txhash);
//        Thread.sleep(20000);
//        txResult = client.queryTransaction(txhash);
//        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
//        //step3 查询evm合约账户余额
//        List<Type> result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getBalanceOfContract", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
//        })));
//        System.out.println("合约地址："+TestB_Address +" 余额："+result.get(0).getValue());
//
//        //查询个人地址余额
//        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getBalance", Arrays.asList(new AddressETH(Addr)), Collections.singletonList(new TypeReference<Uint256>() {
//        })));
//        System.out.println("个人地址："+Addr +" 余额："+result.get(0).getValue());
//
//
//        //step4 从合约里提款
//        encodeParams = FunctionEncoder.encode(new Function("withdraw1", Arrays.asList(new AddressETH(Addr),new Uint256(100000000L)), Collections.emptyList()));
//        tx = EvmUtil.callEvmContract(encodeParams, TestB_Address, "withdraw1", 0, PrivateKey, SignType.ETH_SECP256K1, AddressType.ETH_ADDRESS, 999, "user.p.parademo.", 1000000);
//        txhash = client.submitTransaction(tx);
//        System.out.println("从evm合约地址往个人地址转账交易哈希 = " + txhash);
//        Thread.sleep(20000);
//        txResult = client.queryTransaction(txhash);
//        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
//        //step5 查询evm合约账户余额
//        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getBalanceOfContract", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
//        })));
//        System.out.println("合约地址："+TestB_Address +" 余额："+result.get(0).getValue());
//
//        //查询个人地址余额
//        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getBalance", Arrays.asList(new AddressETH(Addr)), Collections.singletonList(new TypeReference<Uint256>() {
//        })));
//        System.out.println("个人地址："+Addr +" 余额："+result.get(0).getValue());
//
//        //step4 从合约里提款
//        encodeParams = FunctionEncoder.encode(new Function("withdraw2", Arrays.asList(new AddressETH(Addr),new Uint256(100000000L)), Collections.emptyList()));
//        tx = EvmUtil.callEvmContract(encodeParams, TestB_Address, "withdraw2", 0, PrivateKey, SignType.ETH_SECP256K1, AddressType.ETH_ADDRESS, 999, "user.p.parademo.", 1000000);
//        txhash = client.submitTransaction(tx);
//        System.out.println("从evm合约地址往个人地址转账交易哈希 = " + txhash);
//        Thread.sleep(20000);
//        txResult = client.queryTransaction(txhash);
//        Assert.assertEquals(txResult.getReceipt().getTyname(), "ExecOk");
//        //step5 查询evm合约账户余额
//        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getBalanceOfContract", Collections.emptyList(), Collections.singletonList(new TypeReference<Uint256>() {
//        })));
//        System.out.println("合约地址："+TestB_Address +" 余额："+result.get(0).getValue());
//
//        //查询个人地址余额
//        result = client.callEVMContractReadOnlyFunc(TestB_Address, new Function("getBalance", Arrays.asList(new AddressETH(Addr)), Collections.singletonList(new TypeReference<Uint256>() {
//        })));
//        System.out.println("个人地址："+Addr +" 余额："+result.get(0).getValue());
//
//    }

    @Test
    public void testEvmEventParse() throws IOException {
        String txhash = "0x1e4ba8ad1d8fc332dd366f5fb48861b433405557d8da8fdb5e2272290faf88f2";
        QueryTransactionResult txResult = client.queryTransaction(txhash);
        //解析evmLog
        Event event = new Event("SetValue", Arrays.asList(new TypeReference<AddressETH>(true) {
        }, new TypeReference<Uint256>(true) {
        }, new TypeReference<Uint256>() {
        }));
        List<String> list = Arrays.stream(txResult.getReceipt().getLogs()).filter(log -> {
            EvmLog evmLog = JSONObject.parseObject(JSONObject.toJSONString(log.getLog())).toJavaObject(EvmLog.class);
            return log.getTy() == 605 && evmLog.getTopic()[0].equals(EventEncoder.encode(event));
        }).map(item -> {
            EvmLog evmLog = JSONObject.parseObject(JSONObject.toJSONString(item.getLog())).toJavaObject(EvmLog.class);
            byte[] bytes = new byte[0];
            for (int i = 0; i < evmLog.getTopic().length; i++) {
                if (i == 0) {
                    continue;
                }
                bytes = ByteUtil.merge(bytes, HexUtil.fromHexString(evmLog.getTopic()[i]));
            }
            if (evmLog.getData() != null) {
                bytes = ByteUtil.merge(bytes, HexUtil.fromHexString(evmLog.getData()));
            }
            return HexUtil.toHexString(bytes);
        }).collect(Collectors.toList());
        //处理evmLog
        List<List<Type>> resultList = Optional.ofNullable(list).orElse(new ArrayList<String>()).stream().map(item -> {
            List<Type> result = EventLogDecoder.decodeEventParameters(item, event);
            return result;
        }).collect(Collectors.toList());

        List<Type> result = resultList.get(0);
        for (int i = 0; i < result.size(); i++) {
            System.out.println("type: " + result.get(i).getTypeAsString() + " ,  value: " + result.get(i).getValue());
        }
    }


    @Test
    public void testEvmContractAddress() throws Exception {
        String txhash = "0x8335ab9ea0a2e56c23c56a259b55362def9583a55ecae22a463444165ec99de2";

        //0xf39e69a8f2c1041edd7616cf079c7084bb7a5242
        String addr = AddressUtil.genAddress(PrivateKey, AddressType.ETH_ADDRESS);
        Assert.assertEquals("0xf39e69a8f2c1041edd7616cf079c7084bb7a5242",addr);
        //本地计算合约地址
        //0x0d7670e39a629f2591f1bec0c0bf4446fdc5f0dd
        String contractAddressETH = AddressUtil.getContractAddress(txhash, addr, AddressType.ETH_ADDRESS);
        System.out.println("以太坊地址格式合约地址 = " + contractAddressETH);
        Assert.assertEquals("0x0d7670e39a629f2591f1bec0c0bf4446fdc5f0dd",contractAddressETH);
        //1DqjD3Fk8URVda7nPuGNZLtN1ZwTvuK68K
        String contractAddressBTC = AddressUtil.getContractAddress(txhash, addr, AddressType.BTC_ADDRESS);
        System.out.println("比特币地址格式合约地址 = " + contractAddressBTC);
        Assert.assertEquals("1DqjD3Fk8URVda7nPuGNZLtN1ZwTvuK68K",contractAddressBTC);
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
