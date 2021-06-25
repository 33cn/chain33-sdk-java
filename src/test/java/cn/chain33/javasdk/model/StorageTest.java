package cn.chain33.javasdk.model;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import cn.chain33.javasdk.client.GrpcClient;
import cn.chain33.javasdk.model.enums.StorageEnum;
import cn.chain33.javasdk.model.protobuf.*;
import cn.chain33.javasdk.utils.*;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.StatusRuntimeException;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.chain33.javasdk.client.RpcClient;

/**
 *	包含内容存证, 哈希存证,链接存证,隐私存证,分享隐私存证几个接口（联盟链场景）
 * @author fkeit
 */
public class StorageTest {
	
	// 联盟链节点IP
	String ip = "节点IP";
	// 平行链服务端口
	int port = 8801;
	int gprcPort = 8802;
    RpcClient client = new RpcClient(ip, port);
	GrpcClient javaGrpcClient = new GrpcClient(ip+":"+gprcPort,null);

    String content = "疫情发生后，NPO法人仁心会联合日本湖北总商会等四家机构第一时间向湖北捐赠3800套杜邦防护服，包装纸箱上用中文写有“岂曰无衣，与子同裳”。这句诗词出自《诗经·秦风·无衣》，翻译成白话的意思是“谁说我们没衣穿？与你同穿那战裙”。不料，这句诗词在社交媒体上引发热议，不少网民赞叹日本人的文学造诣。实际上，NPO法人仁心会是一家在日华人组织，由在日或有留日背景的医药保健从业者以及相关公司组成的新生公益组织。NPO法人仁心会事务局告诉环球时报-环球网记者，由于第一批捐赠物资是防护服，“岂曰无衣，与子同裳”恰好可以表达海外华人华侨与一线医护人员共同战胜病毒的同仇敌忾之情，流露出对同胞的守护之爱。";
    
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
	 * 内容存证,KV字符串存储
	 */
	@Test
	public void kvStore() throws InterruptedException {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		// 唯一索引
		String key= "project20210625";
		String value1="01工序====";
		String value2="02工序====";
		String txEncode = StorageUtil.createOnlyNotaryStorage(key,value1,0, execer, privateKey);
		String submitTransaction = client.submitTransaction(txEncode);
		System.out.println(submitTransaction);

        TimeUnit.SECONDS.sleep(2);
		//更新键值,add value2
		txEncode = StorageUtil.createOnlyNotaryStorage(key,value2,1, execer, privateKey);
		submitTransaction = client.submitTransaction(txEncode);
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
		String txEncode = StorageUtil.createEncryptNotaryStorage(encrypt,contentHash, iv, "", "", execer, privateKey);
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
		JSONObject resultJson = client.queryStorage("0x401f043696500030d49a511505b5c703e943382082b1154880e753acacb3d443");
		
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

	//==Grpc
	/**
	 * 内容存证
	 */
	@Test
	public void contentStoreGrpc() {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		TransactionAllProtobuf.Transaction tx = StorageUtil.createOnlyNotaryStorageGrpc(content.getBytes(), execer, privateKey);
		CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(tx));
		System.out.println("txhash:"+"0x"+HexUtil.toHexString(result.getMsg().toByteArray()));

	}

	/**
	 * 哈希存证模型，推荐使用sha256哈希，限制256位得摘要值
	 */
	@Test
	public void hashStoreGrpc() {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		TransactionAllProtobuf.Transaction tx = StorageUtil.createHashStorageGrpc(contentHash, execer, privateKey);
		try {
			CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(tx));
			System.out.println("txhash:"+"0x"+HexUtil.toHexString(result.getMsg().toByteArray()));
		} catch (StatusRuntimeException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 链接存证模型
	 */
	@Test
	public void hashAndLinkStoreGrpc() {
		// 存证智能合约的名称
		String execer = "storage";
		// 签名用的私钥
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String link = "https://cs.33.cn/product?hash=13mBHrKBxGjoyzdej4bickPPPupejAGvXr";
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		TransactionAllProtobuf.Transaction tx = StorageUtil.createLinkNotaryStorageGrpc(link.getBytes(), contentHash, execer, privateKey);
		CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(tx));
		System.out.println("txhash:"+"0x"+HexUtil.toHexString(result.getMsg().toByteArray()));

	}

	/**
	 * 隐私存证模型
	 * @throws Exception
	 */
	@Test
	public void EncryptNotaryStoreGrpc() throws Exception {
		// 存证智能合约的名称
		String execer = "storage";
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
		TransactionAllProtobuf.Transaction tx = StorageUtil.createEncryptNotaryStorageGrpc(encrypt,contentHash, iv, "", "", execer, privateKey);
		CommonProtobuf.Reply result = javaGrpcClient.run(o->o.sendTransaction(tx));
		System.out.println("txhash:"+"0x"+HexUtil.toHexString(result.getMsg().toByteArray()));

	}


	/**
	 * 根据hash查询存证结果
	 * @throws UnsupportedEncodingException
	 */
	@Test
	public void queryStorageGrpc() throws UnsupportedEncodingException {
		// 存证智能合约的名称
		String execer = "storage";
		String funcName = "QueryStorage";
		//String hash = "0x401f043696500030d49a511505b5c703e943382082b1154880e753acacb3d443";
		String hash = "0x777da1c63b7fbd56fb4c877661a93163c766b1456f027b8b06b305363c0e7313";
		// contentStore
		StorageProtobuf.QueryStorage queryStorage = StorageProtobuf.QueryStorage.newBuilder().setTxHash(hash).build();
		BlockchainProtobuf.ChainExecutor query = BlockchainProtobuf.ChainExecutor.newBuilder().setFuncName(funcName).setDriver(execer).setParam(queryStorage.toByteString()).build();
		CommonProtobuf.Reply result = javaGrpcClient.run(o->o.queryChain(query));
		StorageProtobuf.Storage storage = null;
		if (result.getIsOk()) {
			try {
				storage = StorageProtobuf.Storage.parseFrom(result.getMsg().toByteArray());
				System.out.println("storage:"+storage);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		}
		int ty = storage.getTy();
		switch (ty) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			default:
				System.out.println("action not found");
		}
//		JSONObject resultArray;
//		if (resultJson.containsKey("linkStorage")) {
//			// hash及link型存证
//			resultArray = resultJson.getJSONObject("linkStorage");
//			String link = resultArray.getString("link");
//			String hash = resultArray.getString("hash");
//			byte[] linkByte = HexUtil.fromHexString(link);
//			String linkresult = new String(linkByte,"UTF-8");
//			System.out.println("存证link是:" + linkresult);
//			System.out.println("存证hash是:" + hash);
//		} else if (resultJson.containsKey("hashStorage")) {
//			// hash型存证解析
//			resultArray = resultJson.getJSONObject("hashStorage");
//			String hash = resultArray.getString("hash");
//			System.out.println("链上读取的hash是:" + hash);
//			byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
//			String result = HexUtil.toHexString(contentHash);
//			System.out.println("存证前的hash是:" + result);
//		} else if (resultJson.containsKey("encryptStorage")) {
//			//隐私存证
//			String desKey = "ba940eabdf09ee0f37f8766841eee763";
//			resultArray = resultJson.getJSONObject("encryptStorage");
//			String content = resultArray.getString("encryptContent");
//			byte[] fromHexString = HexUtil.fromHexString(content);
//			String decrypt = AesUtil.decrypt(fromHexString, desKey);
//			System.out.println(decrypt);
//		} else {
//			// 内容型存证解析
//			resultArray = resultJson.getJSONObject("contentStorage");
//			String content = resultArray.getString("content");
//			byte[] contentByte = HexUtil.fromHexString(content);
//			String result = new String(contentByte,"UTF-8");
//			System.out.println("存证内容是:" + result);
//		}
	}

}
