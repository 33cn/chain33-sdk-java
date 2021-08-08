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
 *	�������ݴ�֤, ��ϣ��֤,���Ӵ�֤,��˽��֤,������˽��֤�����ӿڣ�����+ƽ����������
 * @author fkeit
 */
public class StorageParaTest {
	
	// ƽ����IP
	String ip = "ƽ����IP";
	// ƽ��������˿�
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);
	    
    String content = "���鷢����NPO�������Ļ������ձ��������̻���ļһ�����һʱ�����������3800�׶Ű����������װֽ����������д�С���Ի���£�����ͬ�ѡ������ʫ�ʳ��ԡ�ʫ�����ط硤���¡�������ɰ׻�����˼�ǡ�˭˵����û�´�������ͬ����սȹ�������ϣ����ʫ�����罻ý�����������飬����������̾�ձ��˵���ѧ���衣ʵ���ϣ�NPO�������Ļ���һ�����ջ�����֯�������ջ������ձ�����ҽҩ������ҵ���Լ���ع�˾��ɵ�����������֯��NPO�������Ļ�����ָ��߻���ʱ��-���������ߣ����ڵ�һ�����������Ƿ�����������Ի���£�����ͬ�ѡ�ǡ�ÿ��Ա�ﺣ�⻪�˻�����һ��ҽ����Ա��ͬսʤ������ͬ�����֮�飬��¶����ͬ�����ػ�֮����";
    
		
    /**
     * �������ݴ�֤������Ҫ���������ѵ�����£����Բ��ô��۵ķ�ʽ�� ʵ�ʵĴ�֤���ײ���Ҫ���������ѣ�ȫ��ͨ�����۽���������������
     * 
     * ���۽���ģ��
     * @throws IOException 
     * @throws Exception 
     */
	@Test
	public void contentStoreNobalance() throws InterruptedException, IOException {
	    // ��֤���ܺ�Լ�����ƣ���������£�Ҫ����ƽ����ǰ׺
	    String execer = "user.p.evm.storage";
	    // ʵ�ʽ���ǩ���õ�˽Կ
	    String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
	    String contranctAddress = client.convertExectoAddr(execer);
	    String txEncode = StorageUtil.createOnlyNotaryStorage(content.getBytes(), execer, privateKey, contranctAddress);

	    String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
	    // ��������
	    List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
	    // ���۽���ǩ����˽Կ
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
	 * ��ϣ��֤ģ�ͣ��Ƽ�ʹ��sha256��ϣ������256λ��ժҪֵ
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test
	public void hashStoreNobalance() throws InterruptedException, IOException {
		// ��֤���ܺ�Լ������
	    String execer = "user.p.evm.storage";
		// ǩ���õ�˽Կ
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
	    String contranctAddress = client.convertExectoAddr(execer);
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createHashStorage(contentHash, execer, privateKey, contranctAddress);
		
	    String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
	    // ��������
	    List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
	    // ���۽���ǩ����˽Կ
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
     * ���Ӵ�֤ģ��
	 * @throws InterruptedException 
	 * @throws IOException 
     */
	@Test
	public void hashAndLinkStoreNobalance() throws InterruptedException, IOException {
		// ��֤���ܺ�Լ������
		String execer = "user.p.evm.storage";
		String contranctAddress = client.convertExectoAddr(execer);
		// ǩ���õ�˽Կ
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		String link = "https://cs.33.cn/product?hash=13mBHrKBxGjoyzdej4bickPPPupejAGvXr";
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
		String txEncode = StorageUtil.createLinkNotaryStorage(link.getBytes(), contentHash, execer, privateKey, contranctAddress);

		String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
	    // ��������
	    List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
	    // ���۽���ǩ����˽Կ
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
     * ��˽��֤ģ��
     * @throws Exception 
     */
	@Test
	public void EncryptNotaryStoreNobalance() throws Exception {
		// ��֤���ܺ�Լ������
		String execer = "user.p.evm.storage";
		String contranctAddress = client.convertExectoAddr(execer);
		// ǩ���õ�˽Կ
		String privateKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
		
		// ����AES����KEY
		String aesKeyHex = "ba940eabdf09ee0f37f8766841eee763";
		//���ø÷������� AesUtil.generateDesKey(128);
		byte[] key = HexUtil.fromHexString(aesKeyHex);
		System.out.println("key:" + HexUtil.toHexString(key));
		// ����iv
		byte[] iv = AesUtil.generateIv();
		// �����Ľ��м���
		byte[] encrypt = AesUtil.encrypt(content, key, iv);
		String decrypt = AesUtil.decrypt(encrypt, HexUtil.toHexString(key));
		System.out.println("decrypt:" + decrypt);
		byte[] contentHash = TransactionUtil.Sha256(content.getBytes("utf-8"));
		String txEncode = StorageUtil.createEncryptNotaryStorage(encrypt,contentHash, iv, "", "", execer, privateKey, contranctAddress);

		String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
	    // ��������
	    List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
	    // ���۽���ǩ����˽Կ
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
	 * ����hash��ѯ��֤���
	 * @throws IOException 
	 */
	@Test
	public void queryStorage() throws IOException {
		// contentStore
		JSONObject resultJson = client.queryStorage("0xcc4b820c86d00019e2f0c490bb6a9bcd46812321fe6d38c0b7214421d12fed29");
		
		JSONObject resultArray;
        if (resultJson.containsKey("linkStorage")) {
        	// hash��link�ʹ�֤
        	resultArray = resultJson.getJSONObject("linkStorage");
        	String link = resultArray.getString("link");
        	String hash = resultArray.getString("hash");
        	byte[] linkByte = HexUtil.fromHexString(link);
        	String linkresult = new String(linkByte,"UTF-8");
        	System.out.println("��֤link��:" + linkresult);
        	System.out.println("��֤hash��:" + hash);
        } else if (resultJson.containsKey("hashStorage")) {
        	// hash�ʹ�֤����
        	resultArray = resultJson.getJSONObject("hashStorage");
        	String hash = resultArray.getString("hash");
        	System.out.println("���϶�ȡ��hash��:" + hash);
        	byte[] contentHash = TransactionUtil.Sha256(content.getBytes());
        	String result = HexUtil.toHexString(contentHash);
    		System.out.println("��֤ǰ��hash��:" + result);
        } else if (resultJson.containsKey("encryptStorage")) {
            //��˽��֤
            String desKey = "ba940eabdf09ee0f37f8766841eee763";
            resultArray = resultJson.getJSONObject("encryptStorage");
            String content = resultArray.getString("encryptContent");
            byte[] fromHexString = HexUtil.fromHexString(content);
            String decrypt = AesUtil.decrypt(fromHexString, desKey);
            System.out.println(decrypt);
        } else {
        	// �����ʹ�֤����
        	resultArray = resultJson.getJSONObject("contentStorage");
        	String content = resultArray.getString("content");
        	byte[] contentByte = HexUtil.fromHexString(content);
        	String result = new String(contentByte,"UTF-8");
        	System.out.println("��֤������:" + result);
        }
	}
	
	
}
