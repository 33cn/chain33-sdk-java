package cn.chain33.javasdk.model;

import java.io.IOException;

import org.junit.Test;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 * 简单存证功能，对上链数据不作任何处理，只做打包和共识， 适用高性能要求场合
 * 
 * @author fkeit
 *
 */
public class SimpleStore {

	// 联盟链节点IP
	String mainIp = "主链IP";
	// 平行链服务端口
	int mainPort = 8801;
	RpcClient clientMain = new RpcClient(mainIp, mainPort);
	
	// 平行链节点IP
	String paraIp = "平行链IP";
	// 平行链服务端口
	int paraPort = 8901;
	RpcClient clientPara = new RpcClient(paraIp, paraPort);

	// 上链存证的内容(电子档案上链)
	String content = "{\"档案编号\":\"ID0000001\",\"企业代码\":\"QY0000001\",\"业务标识\":\"DA000001\",\"来源系统\":\"OA\", \"文档摘要\",\"0x93689a705ac0bb4612824883060d73d02534f8ba758f5ca21a343beab2bf7b47\"}";

	// ========================================== 联盟链的场景 start ==============================================================
	/**
	 * 内容存证上链
	 * @throws IOException 
	 */
	@Test
	public void writeData() throws IOException {
		// 存证智能合约的名称（简单存证，固定就用这个名称）
		String execer = "user.write";
		// 合约地址
		String contractAddress = clientMain.convertExectoAddr(execer);

		// 取当前最大区块高度，用于设置查重范围，如果这个值不设置，默认就会从第0个高度开始查
		long txHeight = clientMain.getLastHeader().getHeight();
		// 获取签名用的私钥
		Account account = new Account();
		String privateKey = account.newAccountLocal().getPrivateKey();

		String txEncode = TransactionUtil.createTransferTx(privateKey, contractAddress, execer, content.getBytes(),
				TransactionUtil.DEFAULT_FEE, txHeight);
		String hash = clientMain.submitTransaction(txEncode);

		System.out.println(hash);

	}

	/**
	 * 查询
	 * @throws IOException 
	 */
	@Test
	public void getData() throws IOException {
		// 交易hash
		// String hash = "上一步上链返回的hash";
		String hash = "0xd283df553f62b65306e927677b91052d0a652342acf262ee82c9f43ba519a032";
		QueryTransactionResult queryTransaction;
		queryTransaction = clientMain.queryTransaction(hash);
		System.out.println("交易所在的区块高度：" + queryTransaction.getHeight());
		System.out.println("区块的打包时间：" + queryTransaction.getBlocktime());
		System.out.println("从哪个用户发出：" + queryTransaction.getFromaddr());
		System.out.print("上链的数据内容" + HexUtil.hexStringToString(queryTransaction.getTx().getRawpayload()));

	}

	// ========================================== 联盟链的场景 end  ==============================================================

	// ========================================== 平行链的场景 start  ==============================================================
	/**
	 * 内容存证上链
	 * @throws IOException 
	 */
	@Test
	public void writeParaData() throws IOException {
		// 存证智能合约的名称（简单存证，固定就用这个名称）
		String execer = "user.p.midea.user.write";
		// 合约地址
		String contractAddress = clientPara.convertExectoAddr(execer);

		// 获取签名用的私钥
		Account account = new Account();
		String privateKey = account.newAccountLocal().getPrivateKey();
		// 取当前最大区块高度，用于设置查重范围，如果这个值不设置，默认就会从第0个高度开始查
		// 注意： 这些查的是主链高度，而不是平行链的
		long txHeight = clientMain.getLastHeader().getHeight();
		String txEncode = TransactionUtil.createTransferTx(privateKey, contractAddress, execer, content.getBytes(),
				TransactionUtil.DEFAULT_FEE, txHeight);
		String hash = clientPara.submitTransaction(txEncode);

		System.out.println(hash);
	}

	/**
	 * 查询
	 * @throws IOException 
	 */
	@Test
	public void getParaData() throws IOException {
		// 交易hash
		// String hash = "上一步上链返回的hash";
		String hash = "0xaa09d0cd3f231e8ae6cb3d9456db1ec035bd08143052441e50850c96de082418";
		QueryTransactionResult queryTransaction;
		queryTransaction = clientPara.queryTransaction(hash);
		System.out.println("交易所在的区块高度：" + queryTransaction.getHeight());
		System.out.println("区块的打包时间：" + queryTransaction.getBlocktime());
		System.out.println("从哪个用户发出：" + queryTransaction.getFromaddr());
		System.out.print("上链的数据内容" + HexUtil.hexStringToString(queryTransaction.getTx().getRawpayload()));

	}

	// ========================================== 平行链的场景 end ==============================================================
}
