package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.GrpcClient;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.CommonProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.protobuf.WasmProtobuf;
import cn.chain33.javasdk.utils.CertUtils;
import cn.chain33.javasdk.utils.ConfigUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import io.grpc.EquivalentAddressGroup;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 *	wasm交易构造列子
 * @author szh
 */
public class WasmTest {
    String grpcHost = "127.0.0.1:8802";
    String jsonRpcHost = "http://47.92.105.160:8801";
    GrpcClient javaGrpcClient;
    RpcClient chain33client = new RpcClient(grpcHost);
    String execer = "wasm";
    Boolean isPara = false;
    String title = "user.p.test.";
    /**
     * 构造chain33 wasm结构jsonRpc
     */
    @Test
    public void createWasmContract() throws Exception {
        String contractName = "test";
        if (isPara == true) {
            execer = title+execer;
        }
        ByteArrayOutputStream content = getWasmContent("test/wasm/dice.wasm");
        byte[] codes = content.toByteArray();
        System.out.println("codes length"+codes.length);
        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./authdir/crypto/org1/user1/keystore/8e6675d9ab566931ca967bb0b520e431d89bde2d9567f50d8ec6a4cad46a8955_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./authdir/crypto/org1/user1/cacerts/org1-cert.pem");
        // 构造交易
//        WasmProtobuf.wasmCreate.Builder builder = WasmProtobuf.wasmCreate.newBuilder();
//        builder.setName(contractName);
//        builder.setCode(ByteString.copyFrom(codes));
//        byte[] reqBytes = builder.build().toByteArray();
        WasmProtobuf.wasmAction create = TransactionUtil.createWasmContract(contractName,codes);
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, create.toByteArray(), SignType.SM2, certBytes, "ca test".getBytes());
        // 发送交易
        String hash = chain33client.submitTransaction(transactionHash);
        System.out.println("hash:"+hash);
    }

    /**
     * 构造chain33 wasm结构 GRPC
     */
    @Test
    public void createWasmContractGrpc() throws Exception {
        javaGrpcClient = new GrpcClient(grpcHost);
        String contractName = "test2";
        if (isPara == true) {
            execer = title+execer;
        }
        ByteArrayOutputStream content = getWasmContent("test/wasm/dice.wasm");
        byte[] codes = content.toByteArray();

        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./authdir/crypto/org1/user1/keystore/8e6675d9ab566931ca967bb0b520e431d89bde2d9567f50d8ec6a4cad46a8955_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./authdir/crypto/org1/user1/cacerts/org1-cert.pem");
        // 构造交易
//        WasmProtobuf.wasmCreate.Builder builder = WasmProtobuf.wasmCreate.newBuilder();
//        builder.setName(contractName);
//        builder.setCode(ByteString.copyFrom(codes));
//        byte[] reqBytes = builder.build().toByteArray();
        WasmProtobuf.wasmAction create = TransactionUtil.createWasmContract(contractName,codes);
        TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTxWithCertProto(accountInfo.getPrivateKey(), execer, create.toByteArray(), SignType.SM2, certBytes, "ca test".getBytes());
        // 发送交易
        CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
        System.out.println("txhash:"+"0x"+ HexUtil.toHexString(result.getMsg().toByteArray()));
    }

    /**
     * 构造chain33 wasm结构jsonRpc
     */
    @Test
    public void callWasmContract() throws Exception {
        String contractName = "test";
        if (isPara == true) {
            execer = title+execer;
        }
        String method = "call";
        int[] parameters = new int[]{1,2,3};
        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./test/keystore/b01568b358e336f28d86baaa27df2a07dd01b6991f82f9bc680896ce4ceeaf77_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./test/signcerts/user1@org1-cert.pem");
        // 构造交易
//        WasmProtobuf.wasmCall.Builder builder = WasmProtobuf.wasmCall.newBuilder();
//        builder.setContract(contractName);
//        builder.setMethod(method);
//        byte[] reqBytes = builder.build().toByteArray();
        WasmProtobuf.wasmAction call = TransactionUtil.createWasmCallContract(contractName,method,parameters);
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, call.toByteArray(), SignType.SM2, certBytes, "ca test".getBytes());
        // 发送交易
        String hash = chain33client.submitTransaction(transactionHash);
    }

    /**
     * 构造chain33 wasm结构 GRPC
     */
    @Test
    public void callWasmContractGrpc() throws Exception {
        javaGrpcClient = new GrpcClient(grpcHost);
        String contractName = "test";
        String method = "call";
        if (isPara == true) {
            execer = title+execer;
        }
        int[] parameters = new int[]{1,2,3};
        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./test/keystore/b01568b358e336f28d86baaa27df2a07dd01b6991f82f9bc680896ce4ceeaf77_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./test/signcerts/user1@org1-cert.pem");
        // 构造交易
//        WasmProtobuf.wasmCall.Builder builder = WasmProtobuf.wasmCall.newBuilder();
//        builder.setContract(contractName);
//        builder.setMethod(method);
//        builder.setParameters(0,0);
//        byte[] reqBytes = builder.build().toByteArray();
        WasmProtobuf.wasmAction call = TransactionUtil.createWasmCallContract(contractName,method,parameters);

        TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTxWithCertProto(accountInfo.getPrivateKey(), execer, call.toByteArray(), SignType.SM2, certBytes, "ca test".getBytes());
        // 发送交易
        CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
        System.out.println("txhash:"+"0x"+ HexUtil.toHexString(result.getMsg().toByteArray()));
    }

    public ByteArrayOutputStream getWasmContent(String file) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("test/wasm/dice.wasm"));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        System.out.println("Available bytes:" + in.available());

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        return out;
    }
}
