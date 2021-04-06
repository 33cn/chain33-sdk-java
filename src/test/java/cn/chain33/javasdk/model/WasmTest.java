package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.GrpcClient;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.gm.SM2Util;
import cn.chain33.javasdk.model.protobuf.CommonProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.protobuf.WasmProtobuf;
import cn.chain33.javasdk.utils.*;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *	wasm交易构造列子
 * @author szh
 */
public class WasmTest {

    String grpcHost = "127.0.0.1:8802";
    String jsonRpcHost = "http://127.0.0.1:8801";
    GrpcClient javaGrpcClient;
    RpcClient chain33client = new RpcClient(jsonRpcHost);
    String execer = "wasm";
    Boolean isPara = false;
    String title = "user.p.test.";


    /*
     * 创建wasm合约  调用wasm合约  更新wasm合约 调用旧wasm合约 调用新wasm合约
     *
     */
    @Test
    public void testWasmAll() throws Exception {
        String contractName = "test9";
        if (isPara == true) {
            execer = title+execer;
        }

        System.out.println("===========>生成wasm合约<===========");
        byte[] codes = getWasmContent("test/wasm/dice.wasm");
        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./authdir/crypto/org1/user1/keystore/8e6675d9ab566931ca967bb0b520e431d89bde2d9567f50d8ec6a4cad46a8955_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./authdir/crypto/org1/user1/cacerts/org1-cert.pem");
        WasmProtobuf.wasmAction create = WasmUtil.createWasmContract(contractName,codes);
        String createTx = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, create.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String createHash = chain33client.submitTransaction(createTx);
        System.out.println("hash:"+createHash);
        System.out.println("===========>生成wasm合约<===========");

        System.out.println("===========>调用wasm合约<===========");
        String method = "play";
        int[] parameters = new int[]{1,2};
        String[] envs = new String[]{"test1","test2"};
        WasmProtobuf.wasmAction call = WasmUtil.createWasmCallContract(contractName,method,parameters,null);
        String callTx = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, call.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String callHash = chain33client.submitTransaction(callTx);
        System.out.println("hash:"+callHash);
        System.out.println("===========>调用wasm合约<===========");

        System.out.println("===========>更新wasm合约<===========");
        byte[] newCodes  = getWasmContent("test/wasm/evidence.wasm");
        WasmProtobuf.wasmAction update = WasmUtil.updateWasmContract(contractName,newCodes);
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, update.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String hash = chain33client.submitTransaction(transactionHash);
        System.out.println("hash:"+hash);
        System.out.println("===========>更新wasm合约<===========");

        System.out.println("===========>调用旧wasm合约<===========");
        String oldMethod = "play";
        WasmProtobuf.wasmAction oldCall = WasmUtil.createWasmCallContract(contractName,oldMethod,parameters,null);
        String oldCallTx = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, oldCall.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String oldCallHash = chain33client.submitTransaction(oldCallTx);
        System.out.println("hash:"+oldCallHash);
        System.out.println("===========>调用旧wasm合约<===========");

        System.out.println("===========>调用新wasm合约<===========");
        String newMethod = "AddStateTx";
        WasmProtobuf.wasmAction newCall = WasmUtil.createWasmCallContract(contractName,newMethod,null,envs);
        String newCallTx = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, newCall.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String newCallHash = chain33client.submitTransaction(newCallTx);
        System.out.println("hash:"+newCallHash);
        System.out.println("===========>调用新wasm合约<===========");

    }

    /**
     * 构造chain33 wasm结构jsonRpc
     */
    @Test
    public void createWasmContract() throws Exception {
        String contractName = "test1";
        if (isPara == true) {
            execer = title+execer;
        }
        byte[] codes = getWasmContent("test/wasm/dice.wasm");
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
        System.out.println(accountInfo);
        WasmProtobuf.wasmAction create = WasmUtil.createWasmContract(contractName,codes);
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, create.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
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
        String contractName = "test";
        if (isPara == true) {
            execer = title+execer;
        }
        byte[] codes = getWasmContent("test/wasm/dice.wasm");

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
        WasmProtobuf.wasmAction create = WasmUtil.createWasmContract(contractName,codes);
        TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTxWithCertProto(accountInfo.getPrivateKey(), execer, create.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
        System.out.println("txhash:"+"0x"+ HexUtil.toHexString(result.getMsg().toByteArray()));
    }

    /**
     * 构造chain33 wasm结构jsonRpc
     */
    @Test
    public void updateWasmContract() throws Exception {
        String contractName = "test1";
        if (isPara == true) {
            execer = title+execer;
        }
        byte[] codes  = getWasmContent("test/wasm/evidence.wasm");
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
        WasmProtobuf.wasmAction update = WasmUtil.updateWasmContract(contractName,codes);
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, update.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String hash = chain33client.submitTransaction(transactionHash);
        System.out.println("hash:"+hash);
    }

    /**
     * 构造chain33 wasm结构 GRPC
     */
    @Test
    public void updateWasmContractGrpc() throws Exception {
        javaGrpcClient = new GrpcClient(grpcHost);
        String contractName = "test2";
        if (isPara == true) {
            execer = title+execer;
        }
        byte[] codes = getWasmContent("test/wasm/dice.wasm");

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
        WasmProtobuf.wasmAction update = WasmUtil.updateWasmContract(contractName,codes);
        TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTxWithCertProto(accountInfo.getPrivateKey(), execer, update.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
        System.out.println("txhash:"+"0x"+ HexUtil.toHexString(result.getMsg().toByteArray()));
    }


    /**
     * 构造chain33 wasm结构jsonRpc
     */
    @Test
    public void callWasmContract() throws Exception {
        String contractName = "test5";
        if (isPara == true) {
            execer = title+execer;
        }
        String method = "AddStateTx";
        int[] parameters = new int[]{};
        String[] envs = new String[]{"test1","test2"};
        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./authdir/crypto/org1/user1/keystore/8e6675d9ab566931ca967bb0b520e431d89bde2d9567f50d8ec6a4cad46a8955_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./test/signcerts/user1@org1-cert.pem");
        // 构造交易
//        WasmProtobuf.wasmCall.Builder builder = WasmProtobuf.wasmCall.newBuilder();
//        builder.setContract(contractName);
//        builder.setMethod(method);
//        byte[] reqBytes = builder.build().toByteArray();
        WasmProtobuf.wasmAction call = WasmUtil.createWasmCallContract(contractName,method,parameters,envs);
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), execer, call.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        String hash = chain33client.submitTransaction(transactionHash);
        System.out.println("hash:"+hash);
    }

    /**
     * 构造chain33 wasm结构 GRPC
     */
    @Test
    public void callWasmContractGrpc() throws Exception {
        javaGrpcClient = new GrpcClient(grpcHost);
        String contractName = "test";
        String method = "play";
        if (isPara == true) {
            execer = title+execer;
        }
        int[] parameters = new int[]{1,2};
        String[] envs = new String[]{"1","2","3"};
        // 从文件加载账户
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./authdir/crypto/org1/user1/keystore/8e6675d9ab566931ca967bb0b520e431d89bde2d9567f50d8ec6a4cad46a8955_sk");
        // 加载用户证书
        byte[] certBytes = CertUtils.getCertFromFile("./test/signcerts/user1@org1-cert.pem");
        // 构造交易
//        WasmProtobuf.wasmCall.Builder builder = WasmProtobuf.wasmCall.newBuilder();
//        builder.setContract(contractName);
//        builder.setMethod(method);
//        builder.setParameters(0,0);
//        byte[] reqBytes = builder.build().toByteArray();
        WasmProtobuf.wasmAction call = WasmUtil.createWasmCallContract(contractName,method,parameters,envs);

        TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTxWithCertProto(accountInfo.getPrivateKey(), execer, call.toByteArray(), SignType.SM2, certBytes, SM2Util.Default_Uid);
        // 发送交易
        CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
        System.out.println("txhash:"+"0x"+ HexUtil.toHexString(result.getMsg().toByteArray()));
    }

    public static byte[] getWasmContent(String file) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();
        return out.toByteArray();
    }
}
