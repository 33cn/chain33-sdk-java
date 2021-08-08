package cn.chain33.javasdk.performance;

import java.io.IOException;
import java.util.Random;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.utils.TransactionUtil;

public class Performance {

	RpcClient client = null;
	
	Account account = new Account();
	
	long txHeight = 0;

	
	/**
	 * 
	 */
	public void runTest(String ip, String port, String num) {

		int nThreads = Runtime.getRuntime().availableProcessors();
		System.out.println("线程数" + nThreads);

		String execer = "user.write";

		try {
			client = new RpcClient(ip, Integer.parseInt(port));
			String contractAddress = client.convertExectoAddr(execer);
			String privateKey = null;
			// 获取区块高度
			startthread2(client);
			
			for (int i = 0; i < nThreads; i++) {
				privateKey = account.newAccountLocal().getPrivateKey();
				// 构造交易
				startthread1(privateKey, Integer.parseInt(num), client, execer, contractAddress);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * 用于构造交易
	 * 
	 * @param client
	 * @param privateKey
	 * @param toaddress
	 */
	private void startthread1(String privateKey, int num, RpcClient client, String execer, String toaddress) {
		Thread1 st = new Thread1(privateKey, num, client, execer, toaddress);
		Thread t = new Thread(st);
		t.start();
	}
	
	/**
	 * 获取区块高度
	 * 
	 * @param client
	 */
	private void startthread2(RpcClient client) {
		Thread2 st = new Thread2(client);
		Thread t = new Thread(st);
		t.start();
	}


	/**
	 * 
	 * @author fkeit
	 *
	 */
	class Thread1 implements Runnable {

		String privateKey;
		int num;
		RpcClient client;
		String execer;
		String toaddress;

		public Thread1(String privateKey, int num, RpcClient client, String execer, String toaddress) {
			this.privateKey = privateKey;
			this.num = num;
			this.client = client;
			this.execer = execer;
			this.toaddress = toaddress;
			
		}

		@Override
		public void run() {

			String payLoad = getRamdonString(32);
			String txEncode = null;
			String hash = null;
			int count = 0;
			long start = System.currentTimeMillis();
			for (int i = 0; i < num; i++) {
				try {

					txEncode = TransactionUtil.createTransferTx(privateKey, toaddress, execer, payLoad.getBytes(),
							TransactionUtil.DEFAULT_FEE, txHeight);
					hash = client.submitTransaction(txEncode);
					if (hash != null) {
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			
			long end = System.currentTimeMillis();
			System.out.println("构造及发送交易花费时间" + (end - start) + " 毫秒, 总共发送" + count + "笔交易");
		}
			

		/**
		 * 获取随机值
		 * 
		 * @param length
		 * @return
		 */
		public String getRamdonString(int length) {
			StringBuffer sb = new StringBuffer();
			String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random random = new Random();
			for (int i = 0; i < length; i++) {
				sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
			}

			return sb.toString();
		}

	}
	
	/**
	 * 获取区块高度
	 * @author fkeit
	 *
	 */
	class Thread2 implements Runnable {

		RpcClient client;

		public Thread2(RpcClient client) {
			this.client = client;
		}

		@Override
		public void run() {
			while (true) {
				try {
					txHeight = client.getLastHeader().getHeight();
					Thread.sleep(1000);
				} catch (InterruptedException | IOException  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("请带上[IP][端口号]以及[发送的交易数目]这三个参数");
			return;
		}
		String ip = args[0];
		String port = args[1];
		String num = args[2];
		Performance test = new Performance();
		test.runTest(ip, port, num);
	}

}
