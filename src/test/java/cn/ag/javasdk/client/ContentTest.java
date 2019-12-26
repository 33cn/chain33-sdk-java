package cn.ag.javasdk.client;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.TransactionUtil;
import org.bitcoinj.wallet.DeterministicSeed;

public class ContentTest {

	    // 连接区块链的jsonRPC接口
		RpcClient client = new RpcClient("172.16.103.14",8801);
		
		// 用户私钥
		private static String producerSecret = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
		

		public static void main(String []args) throws Exception {
			
			
			
//			ContentTest test = new ContentTest();
//			// 药品生产商数据上链内容
//			String content = "{\"药品编号\":\"MEDICAL000001\",\"药品图片HASH\":\"933a925767fe0ae387947f41690fc054\",\"药品产地\":\"上海\",\"保质期\":\"2019-12-12\",\"生产商\":\"上海xxxxxx有限公司\"}";
//			String txHash = test.sendtx(content, producerSecret);
//			
//			System.out.println("交易hash：" + txHash);
//			
//			// 根据交易hash查询交易
//			test.querytx(txHash);
						
		}
		
		// 发送交易
		public String sendtx(String content, String Secret) throws Exception {
			// user.write代表合约名称
			String contractName = "user.write";
			String creareTx = TransactionUtil.createTx(Secret, contractName, content, 10000000);
			String txHash = client.submitTransaction(creareTx);
			
			return txHash;
		}
		
		
		
		public void querytx(String txHash) throws InterruptedException {
			
			QueryTransactionResult queryTransaction1 = new QueryTransactionResult();
			// 打包区块可能会有一定延时，这边等待一会
			for (int i = 0; i < 20; i++) {
				queryTransaction1 = client.queryTransaction(txHash);
				if (null == queryTransaction1) {
					Thread.sleep(2000);
				} else {
					break;
				}
			}
			
			// 获得区块高度
			// TODO:这边需要处理下异常，以防queryTransaction1是空导致异常
//			System.out.println("区块高度：" + queryTransaction1.getHeight());
//			// 获得区块时间
//			System.out.println("区块时间：" + queryTransaction1.getBlocktime());
//			// 获得from地址
//			System.out.println("from地址：" + queryTransaction1.getFromaddr());
//			// 获得to地址
//			System.out.println("to地址：" + queryTransaction1.getTx().getTo());
//			// 获得区块hash
//			System.out.println("区块hash:" + client.getBlockHash(queryTransaction1.getHeight()));

			// 获得交易体	
			String payload = queryTransaction1.getTx().getRawpayload();
			String s = payload.substring(2, payload.length());
			byte[] b = new byte[s.length() / 2];
			for (int i = 0; i < b .length; i++) {
				try {
					b [i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				s = new String(b, "GB2312");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("交易信息：" + s);
			
		}
}
