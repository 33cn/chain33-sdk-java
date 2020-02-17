package cn.chain33.javasdk.client;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.chain33.javasdk.utils.AesUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.StorageUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 *	包含内容存证, 哈希存证,链接存证,隐私存证,分享隐私存证几个接口
 * @author fkeit
 */
public class StorageTest {
	
    String ip = "172.16.103.14";
    RpcClient client = new RpcClient(ip, 8801);
    
    String content = "疫情发生后，NPO法人仁心会联合日本湖北总商会等四家机构第一时间向湖北捐赠3800套杜邦防护服，包装纸箱上用中文写有“岂曰无衣，与子同裳”。这句诗词出自《诗经·秦风·无衣》，翻译成白话的意思是“谁说我们没衣穿？与你同穿那战裙”。不料，这句诗词在社交媒体上引发热议，不少网民赞叹日本人的文学造诣。实际上，NPO法人仁心会是一家在日华人组织，由在日或有留日背景的医药保健从业者以及相关公司组成的新生公益组织。NPO法人仁心会事务局告诉环球时报-环球网记者，由于第一批捐赠物资是防护服，“岂曰无衣，与子同裳”恰好可以表达海外华人华侨与一线医护人员共同战胜病毒的同仇敌忾之情，流露出对同胞的守护之爱。";

//	// AES加密KEY
//	byte[] key = {-117, 119, -114, 66, -112, 72, -52, -54, 7, -101, -5, -20, 64, 89, 4, 57};
//	// AES 加密向量 iv
//	byte[] iv = {84, 101, -87, -68, 67, -69, 80, -102, -116, -30, 124, -18, 84, 4, -34, -83};
    
	/**
	 * 内容存证
	 */
	@Test
	public void contentStore() {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String txEncode = StorageUtil.createOnlyNotaryStorage(content.getBytes(), execer, privateKey);
		String submitTransaction = client.submitTransaction(txEncode);
		System.out.println(submitTransaction);
		
	}
	
	/**
	 * 哈希存证模型，推荐使用sha256哈希，限制256位得摘要值
	 */
	@Test
	public void hashStore() {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createHashStorage(contentHash, execer, privateKey);
		String submitTransaction = client.submitTransaction(txEncode);
		System.out.println(submitTransaction);
		
	}
	
    /**
     * 链接存证模型
     */
	@Test
	public void hashAndLinkStore() {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String link = "https://cs.33.cn/product?hash=13mBHrKBxGjoyzdej4bickPPPupejAGvXr";
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createLinkNotaryStorage(link.getBytes(), contentHash, execer, privateKey);
		String submitTransaction = client.submitTransaction(txEncode);
		System.out.println(submitTransaction);
		
	}
	
    /**
     * 隐私存证模型
     * @throws Exception 
     */
	@Test
	public void EncryptNotaryStore() throws Exception {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		
		// 生成AES加密KEY
		byte[] key = AesUtil.generateDesKey(128);
		// 生成iv
		byte[] iv = AesUtil.generateIv();
		// 对明文进行加密
		String encrypt = AesUtil.encrypt(content, key, iv);
		
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createEncryptNotaryStorage(contentHash, encrypt.getBytes(), iv, execer, privateKey);
		String submitTransaction = client.submitTransaction(txEncode);
		System.out.println(submitTransaction);
		
	}
	
    /**
     * 分享型存证模型
     * @throws Exception 
     */
	@Test
	public void EncryptShareNotaryStore() throws Exception {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		
		// 生成AES加密KEY
		byte[] key = AesUtil.generateDesKey(128);
		// 生成iv
		byte[] iv = AesUtil.generateIv();
		// 对明文进行加密
		String encrypt = AesUtil.encrypt(content, key, iv);
		
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createEncryptNotaryStorage(contentHash, encrypt.getBytes(), iv, execer, privateKey);
		String submitTransaction = client.submitTransaction(txEncode);
		System.out.println(submitTransaction);
		
	}
	
	/**
	 * 根据hash查询存证结果
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void queryStorage() throws UnsupportedEncodingException {
		// contentStore
//		JSONObject resultJson = client.queryStorage("0x7f675d6f16c13e4ac157ac8b5ffe4fd5b34fa8973dd400474cabb07ed6d2d1d8");
//		JSONObject resultJson = client.queryStorage("0x8c97bb1659c54c2c95d93d4b0c7423cf604c485b0e534b7bc1aada2c45be5c39");
//		JSONObject resultJson = client.queryStorage("0xa69815e1fe52ba8787cb710a236127299f7d15798c59ec9ed350d1342d7d256e");
//		JSONObject resultJson = client.queryStorage("0x95c8ba7a818e94349049e4de0f9259bc6cfd9429ed4af9ede38262ff7f974a7c");
		JSONObject resultJson = client.queryStorage("存证hash");
		
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
