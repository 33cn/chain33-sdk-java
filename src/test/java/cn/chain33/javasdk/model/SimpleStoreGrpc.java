package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.GrpcClient;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.protobuf.BlockchainProtobuf;
import cn.chain33.javasdk.model.protobuf.CommonProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import com.google.protobuf.ByteString;
import org.junit.Test;

public class SimpleStoreGrpc {

	// 区块链节点IP
	String mainIp = "区块链ip";
	// 平行链服务端口
	int mainPort = 8801;
	int grpcMainPort = 8802;
	RpcClient clientMain = new RpcClient(mainIp, mainPort);

	// 平行链节点IP
	String paraIp = "区块链ip";
	// 平行链服务端口
	int paraPort = 8901;
	int grpcParaPort = 8902;
	RpcClient clientPara = new RpcClient(paraIp, paraPort);

	// 上链存证的内容(电子档案上链)
	String content = "{\"档案编号\":\"ID0000001\",\"企业代码\":\"QY0000001\",\"业务标识\":\"DA000001\",\"来源系统\":\"OA\", \"文档摘要\",\"0x93689a705ac0bb4612824883060d73d02534f8ba758f5ca21a343beab2bf7b47\"}";

	GrpcClient javaGrpcClient = new GrpcClient(mainIp+":"+grpcMainPort,null);
	GrpcClient javaGrpcClientPara = new GrpcClient(paraIp+":"+grpcParaPort,null);

	/**
	 * 内容存证上链
	 */
	@Test
	public void writeData() {
		// 存证智能合约的名称（简单存证，固定就用这个名称）
		String execer = "user.write";
		//jsonrpc
		String contractAddress = clientMain.convertExectoAddr(execer);
		// 获取签名用的私钥
		Account account = new Account();
		String privateKey = account.newAccountLocal().getPrivateKey();

	    long txHeight = javaGrpcClient.run(o->o.getLastHeader(CommonProtobuf.ReqNil.newBuilder().build())).getHeight();
		TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTransferTx2(privateKey, contractAddress, execer, content.getBytes(),
				TransactionUtil.DEFAULT_FEE, txHeight);

		CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
		System.out.println("txhash:"+"0x"+HexUtil.toHexString(result.getMsg().toByteArray()));
	}

	/**
	 * 查询
	 */
	@Test
	public void getData() {
		// 交易hash
		// String hash = "上一步上链返回的hash";
		String hash = "188bf9a95029557f6157ea9d54282a9b87f88af4082c056cfd86752aed0248c8";
		byte[] hashBytes = HexUtil.fromHexString(hash);
		CommonProtobuf.ReqHash request = CommonProtobuf.ReqHash.newBuilder().setHash(ByteString.copyFrom(hashBytes)).build();
		TransactionAllProtobuf.TransactionDetail queryTransaction = javaGrpcClient.run(o -> o.queryTransaction(request));
		System.out.println("交易所在的区块高度：" + queryTransaction.getHeight());
		System.out.println("区块的打包时间：" + queryTransaction.getBlocktime());
		System.out.println("从哪个用户发出：" + queryTransaction.getFromaddr());
		System.out.print("上链的数据内容" + queryTransaction.getTx().getPayload().toString());

	}

	// ========================================== 平行链的场景 start  ==============================================================
	/**
	 * 内容存证上链
	 */
	@Test
	public void writeParaData() {
		// 存证智能合约的名称（简单存证，固定就用这个名称）
		String execer = "user.p.midea.user.write";
		//jsonrpc
		String contractAddress = clientPara.convertExectoAddr(execer);
		// 获取签名用的私钥
		Account account = new Account();
		String privateKey = account.newAccountLocal().getPrivateKey();

		long txHeight = javaGrpcClient.run(o->o.getLastHeader(CommonProtobuf.ReqNil.newBuilder().build())).getHeight();
		TransactionAllProtobuf.Transaction transaction = TransactionUtil.createTransferTx2(privateKey, contractAddress, execer, content.getBytes(),
				TransactionUtil.DEFAULT_FEE, txHeight);

		CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(transaction));
		System.out.println("txhash:"+"0x"+HexUtil.toHexString(result.getMsg().toByteArray()));
	}

	/**
	 * 查询
	 */
	@Test
	public void getParaData() {
		// 交易hash
		// String hash = "上一步上链返回的hash";
		String hash = "0xd283df553f62b65306e927677b91052d0a652342acf262ee82c9f43ba519a032";
		byte[] hashBytes = HexUtil.fromHexString(hash);
		CommonProtobuf.ReqHash request = CommonProtobuf.ReqHash.newBuilder().setHash(ByteString.copyFrom(hashBytes)).build();
		System.out.println(request);
		TransactionAllProtobuf.TransactionDetail queryTransaction = javaGrpcClientPara.run(o -> o.queryTransaction(request));
		System.out.println("交易所在的区块高度：" + queryTransaction.getHeight());
		System.out.println("区块的打包时间：" + queryTransaction.getBlocktime());
		System.out.println("从哪个用户发出：" + queryTransaction.getFromaddr());
		System.out.print("上链的数据内容" + queryTransaction.getTx().getPayload().toString());

	}
}
