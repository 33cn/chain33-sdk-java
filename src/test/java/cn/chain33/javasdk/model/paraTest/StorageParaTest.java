package cn.chain33.javasdk.model.paraTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.chain33.javasdk.utils.AesUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.StorageUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 *	包含内容存证, 哈希存证,链接存证,隐私存证,分享隐私存证几个接口（公链+平行链场景）
 * @author fkeit
 */
public class StorageParaTest {

	// 平行链IP
	String ip = "平行链IP";
	// 平行链服务端口
	int port = 8801;
	RpcClient client = new RpcClient(ip, port);

	String content = "疫情发生后，NPO法人仁心会联合日本湖北总商会等四家机构第一时间向湖北捐赠3800套杜邦防护服，包装纸箱上用中文写有“岂曰无衣，与子同裳”。这句诗词出自《诗经·秦风·无衣》，翻译成白话的意思是“谁说我们没衣穿？与你同穿那战裙”。不料，这句诗词在社交媒体上引发热议，不少网民赞叹日本人的文学造诣。实际上，NPO法人仁心会是一家在日华人组织，由在日或有留日背景的医药保健从业者以及相关公司组成的新生公益组织。NPO法人仁心会事务局告诉环球时报-环球网记者，由于第一批捐赠物资是防护服，“岂曰无衣，与子同裳”恰好可以表达海外华人华侨与一线医护人员共同战胜病毒的同仇敌忾之情，流露出对同胞的守护之爱。";


	/**
	 * 代扣内容存证，在需要缴纳手续费的情况下，可以采用代扣的方式， 实际的存证交易不需要缴纳手续费，全部通过代扣交易来缴纳手续费
	 *
	 * 代扣交易模型
	 * @throws IOException
	 * @throws Exception
	 */
	@Test
	public void contentStoreNobalance() throws InterruptedException, IOException {
		// 存证智能合约的名称，代扣情况下，要带上平行链前缀
		String execer = "user.p.evm.storage";
		// 实际交易签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String contranctAddress = client.convertExectoAddr(execer);
		String txEncode = StorageUtil.createOnlyNotaryStorage(content.getBytes(), execer, privateKey, contranctAddress);

		String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
		// 解析交易
		List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
		// 代扣交易签名的私钥
		String withHoldPrivateKey = "53a601fb5f6de0f4002397cdb7d1e0e6dc655392cacdbe36ede06353c444cfb2";
		String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, privateKey, withHoldPrivateKey);
		String submitTransaction = client.submitTransaction(hexString);
		System.out.println("submitTransaction:" + submitTransaction);

		Thread.sleep(5000);
		for (int tick = 0; tick < 5; tick++){
			QueryTransactionResult result = client.queryTransaction(submitTransaction);
			if(result == null) {
				Thread.sleep(5000);
				continue;
			}

			System.out.println("next:" + result.getTx().getNext());
			QueryTransactionResult nextResult = client.queryTransaction(result.getTx().getNext());
			System.out.println("ty:" + nextResult.getReceipt().getTyname());
			break;
		}
	}

	/**
	 * 哈希存证模型，推荐使用sha256哈希，限制256位得摘要值
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void hashStoreNobalance() throws InterruptedException, IOException {
		// 存证智能合约的名称
		String execer = "user.p.evm.storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String contranctAddress = client.convertExectoAddr(execer);
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createHashStorage(contentHash, execer, privateKey, contranctAddress);

		String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
		// 解析交易
		List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
		// 代扣交易签名的私钥
		String withHoldPrivateKey = "53a601fb5f6de0f4002397cdb7d1e0e6dc655392cacdbe36ede06353c444cfb2";
		String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, privateKey, withHoldPrivateKey);
		String submitTransaction = client.submitTransaction(hexString);
		System.out.println("submitTransaction:" + submitTransaction);

		Thread.sleep(5000);
		for (int tick = 0; tick < 5; tick++){
			QueryTransactionResult result = client.queryTransaction(submitTransaction);
			if(result == null) {
				Thread.sleep(5000);
				continue;
			}

			System.out.println("next:" + result.getTx().getNext());
			QueryTransactionResult nextResult = client.queryTransaction(result.getTx().getNext());
			System.out.println("ty:" + nextResult.getReceipt().getTyname());
			break;
		}

	}


	/**
	 * 链接存证模型
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void hashAndLinkStoreNobalance() throws InterruptedException, IOException {
		// 存证智能合约的名称
		String execer = "user.p.evm.storage";
		String contranctAddress = client.convertExectoAddr(execer);
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String link = "https://cs.33.cn/product?hash=13mBHrKBxGjoyzdej4bickPPPupejAGvXr";
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createLinkNotaryStorage(link.getBytes(), contentHash, execer, privateKey, contranctAddress);

		String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
		// 解析交易
		List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
		// 代扣交易签名的私钥
		String withHoldPrivateKey = "53a601fb5f6de0f4002397cdb7d1e0e6dc655392cacdbe36ede06353c444cfb2";
		String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, privateKey, withHoldPrivateKey);
		String submitTransaction = client.submitTransaction(hexString);
		System.out.println("submitTransaction:" + submitTransaction);

		Thread.sleep(5000);
		for (int tick = 0; tick < 5; tick++){
			QueryTransactionResult result = client.queryTransaction(submitTransaction);
			if(result == null) {
				Thread.sleep(5000);
				continue;
			}

			System.out.println("next:" + result.getTx().getNext());
			QueryTransactionResult nextResult = client.queryTransaction(result.getTx().getNext());
			System.out.println("ty:" + nextResult.getReceipt().getTyname());
			break;
		}

	}

	/**
	 * 隐私存证模型
	 * @throws Exception
	 */
	@Test
	public void EncryptNotaryStoreNobalance() throws Exception {
		// 存证智能合约的名称
		String execer = "user.p.evm.storage";
		String contranctAddress = client.convertExectoAddr(execer);
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";

		// 生成AES加密KEY
		String aesKeyHex = "ba940eabdf09ee0f37f8766841eee763";
		//可用该方法生成 AesUtil.generateDesKey(128);
		byte[] key = HexUtil.fromHexString(aesKeyHex);
		System.out.println("key:" + HexUtil.toHexString(key));
		// 生成iv
		byte[] iv = AesUtil.generateIv();
		// 对明文进行加密
		byte[] encrypt = AesUtil.encrypt(content, key, iv);
		String decrypt = AesUtil.decrypt(encrypt, HexUtil.toHexString(key));
		System.out.println("decrypt:" + decrypt);
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes("utf-8"));
		String txEncode = StorageUtil.createEncryptNotaryStorage(encrypt,contentHash, iv, "", "", execer, privateKey, contranctAddress);

		String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
		// 解析交易
		List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
		// 代扣交易签名的私钥
		String withHoldPrivateKey = "53a601fb5f6de0f4002397cdb7d1e0e6dc655392cacdbe36ede06353c444cfb2";
		String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, privateKey, withHoldPrivateKey);
		String submitTransaction = client.submitTransaction(hexString);
		System.out.println("submitTransaction:" + submitTransaction);

		Thread.sleep(5000);
		for (int tick = 0; tick < 5; tick++){
			QueryTransactionResult result = client.queryTransaction(submitTransaction);
			if(result == null) {
				Thread.sleep(5000);
				continue;
			}

			System.out.println("next:" + result.getTx().getNext());
			QueryTransactionResult nextResult = client.queryTransaction(result.getTx().getNext());
			System.out.println("ty:" + nextResult.getReceipt().getTyname());
			break;
		}

	}


	/**
	 * 根据hash查询存证结果
	 * @throws IOException
	 */
	@Test
	public void queryStorage() throws IOException {
		// contentStore
		JSONObject resultJson = client.queryStorage("0xcc4b820c86d00019e2f0c490bb6a9bcd46812321fe6d38c0b7214421d12fed29");

		JSONObject resultArray;
		if (resultJson.containsKey("linkStorage")) {
			// hash及link型存证
			resultArray = resultJson.getJSONObject("linkStorage");
			String link = resultArray.getString("link");
			String hash = resultArray.getString("hash");
			byte[] linkByte = HexUtil.fromHexString(link);
			String linkresult = new String(linkByte,"UTF-8");
			System.out.println("存证link是:" + linkresult);
			System.out.println("存证hash是:" + hash);
		} else if (resultJson.containsKey("hashStorage")) {
			// hash型存证解析
			resultArray = resultJson.getJSONObject("hashStorage");
			String hash = resultArray.getString("hash");
			System.out.println("链上读取的hash是:" + hash);
			byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
			String result = HexUtil.toHexString(contentHash);
			System.out.println("存证前的hash是:" + result);
		} else if (resultJson.containsKey("encryptStorage")) {
			//隐私存证
			String desKey = "ba940eabdf09ee0f37f8766841eee763";
			resultArray = resultJson.getJSONObject("encryptStorage");
			String content = resultArray.getString("encryptContent");
			byte[] fromHexString = HexUtil.fromHexString(content);
			String decrypt = AesUtil.decrypt(fromHexString, desKey);
			System.out.println(decrypt);
		} else {
			// 内容型存证解析
			resultArray = resultJson.getJSONObject("contentStorage");
			String content = resultArray.getString("content");
			byte[] contentByte = HexUtil.fromHexString(content);
			String result = new String(contentByte,"UTF-8");
			System.out.println("存证内容是:" + result);
		}
	}


}