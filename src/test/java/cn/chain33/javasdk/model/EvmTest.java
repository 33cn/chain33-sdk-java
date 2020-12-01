package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.utils.EvmUtil;
import cn.chain33.javasdk.utils.HexUtil;
import org.junit.Test;

public class EvmTest {

    RpcClient client = new RpcClient("127.0.0.1", 8801);

    @Test
    public void testEvmContract() throws Exception {
        String privateKey = "0x85bf7aa29436bb186cac45ecd8ea9e63e56c5817e127ebb5e99cd5a9cbfe0f23";

        String code = "0x608060405234801561001057600080fd5b506298967f60005560ac806100266000396000f3fe6080604052348015600f57600080fd5b506004361060325760003560e01c806360fe47b11460375780636d4ce63c146053575b600080fd5b605160048036036020811015604b57600080fd5b5035606b565b005b60596070565b60408051918252519081900360200190f35b600055565b6000549056fea26469706673582212206850c96ceb3091ba2b0454750fbb02238fe0e3765327ec62c47ef4acf0b3ff2b64736f6c63430006000033";

        String abi = "[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"x\",\"type\":\"uint256\"}],\"name\":\"set\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]";

        // 部署合约
        String txEncode = EvmUtil.createEvmContract(HexUtil.fromHexString(code), "", "evm-sdk-test", abi, privateKey);
        String submitTransaction = client.submitTransaction(txEncode);
        String contractName = submitTransaction;
        System.out.println(submitTransaction);
        Thread.sleep(1000);

        // 调用合约
        txEncode = EvmUtil.callEvmContract("".getBytes(),"", 0, "get()", contractName, privateKey);
        submitTransaction = client.submitTransaction(txEncode);
        System.out.println(submitTransaction);
        Thread.sleep(1000);

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
