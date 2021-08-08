package cn.chain33.javasdk.client;

import cn.chain33.javasdk.model.AccountInfo;
import cn.chain33.javasdk.model.gm.SM2KeyPair;
import cn.chain33.javasdk.model.gm.SM2Util;
import cn.chain33.javasdk.utils.DesUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.StringUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

import java.io.*;

public class Account {

    /**
     * 
     * @description �ڱ��ش����˻���Ϣ
     * @return �˻���Ϣ(˽Կ����Կ����ַ)
     *
     */
    public AccountInfo newAccountLocal() {
    	AccountInfo accountInfo = new AccountInfo();
    	
    	// ����˽Կ��
    	String privateKey = TransactionUtil.generatorPrivateKeyString();
    	accountInfo.setPrivateKey(privateKey);
    	// ���ɹ�Կ��
    	String publicKey = TransactionUtil.getHexPubKeyFromPrivKey(privateKey);
    	accountInfo.setPublicKey(publicKey);
    	byte[] publicKeyByte = HexUtil.fromHexString(publicKey);
    	// ���ɵ�ַ
    	accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
        return accountInfo;
    }

	/**
	 * ���ش����˻���Ϣ�����������ָ��·��
	 */
	public AccountInfo newAccountLocal(String name, String passwd, String path) throws IOException {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setName(name);

		// ����˽Կ��
		String privateKey = TransactionUtil.generatorPrivateKeyString();
		accountInfo.setPrivateKey(privateKey);

		// �������ļ�
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		if(StringUtil.isEmpty(passwd)){
			fos.write(privateKey.getBytes());
		} else {
			fos.write(DesUtil.encrypt(privateKey.getBytes(), DesUtil.padding(passwd)));
		}
		fos.close();

		// ���ɹ�Կ��
		String publicKey = TransactionUtil.getHexPubKeyFromPrivKey(privateKey);
		accountInfo.setPublicKey(publicKey);
		byte[] publicKeyByte = HexUtil.fromHexString(publicKey);
		// ���ɵ�ַ
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
		return accountInfo;
	}

	/**
	 * ���������˻���Ϣ
	 */
	public AccountInfo loadAccountLocal(String name, String passwd, String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}

		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte buffer[] = new byte[64];
		int size;
		while ((size = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, size);
		}
		fis.close();

		String privateKey;
		if(StringUtil.isEmpty(passwd)) {
			privateKey = baos.toString();
		} else{
			privateKey = new String(DesUtil.decrypt(baos.toByteArray(), DesUtil.padding(passwd)));
		}
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setName(name);
		accountInfo.setPrivateKey(privateKey);
		// ���ɹ�Կ��
		String publicKey = TransactionUtil.getHexPubKeyFromPrivKey(privateKey);
		accountInfo.setPublicKey(publicKey);
		byte[] publicKeyByte = HexUtil.fromHexString(publicKey);
		// ���ɵ�ַ
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
		return accountInfo;
	}

	/**
	 *
	 * @description �ڱ��ش����˻���Ϣ
	 * @return �˻���Ϣ(˽Կ����Կ����ַ)
	 *
	 */
	public AccountInfo newGMAccountLocal() {
		AccountInfo accountInfo = new AccountInfo();

		SM2KeyPair keyPair = SM2Util.generateKeyPair();
		accountInfo.setPrivateKey(keyPair.getPrivateKeyString());
		accountInfo.setPublicKey(keyPair.getPublicKeyString());

		byte[] publicKeyByte = HexUtil.fromHexString(accountInfo.getPublicKey());
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));

		return accountInfo;
	}

	/**
	 * ���ش����˻���Ϣ�����������ָ��·��
	 */
	public AccountInfo newGMAccountLocal(String name, String passwd, String path) throws IOException {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setName(name);

		SM2KeyPair keyPair = SM2Util.generateKeyPair();
		accountInfo.setPrivateKey(keyPair.getPrivateKeyString());
		accountInfo.setPublicKey(keyPair.getPublicKeyString());

		// �������ļ�
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		if(StringUtil.isEmpty(passwd)){
			fos.write(accountInfo.getPrivateKey().getBytes());
		} else {
			fos.write(DesUtil.encrypt(accountInfo.getPrivateKey().getBytes(), DesUtil.padding(passwd)));
		}
		fos.close();

		byte[] publicKeyByte = HexUtil.fromHexString(accountInfo.getPublicKey());
		// ���ɵ�ַ
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
		return accountInfo;
	}

	/**
	 * ���������˻���Ϣ
	 */
	public AccountInfo loadGMAccountLocal(String name, String passwd, String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}

		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte buffer[] = new byte[64];
		int size;
		while ((size = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, size);
		}
		fis.close();

		byte[] privateKey;
		if(StringUtil.isEmpty(passwd)) {
			privateKey = baos.toByteArray();
		} else{
			privateKey = DesUtil.decrypt(baos.toByteArray(), DesUtil.padding(passwd));
		}

		SM2KeyPair keyPair = SM2Util.fromPrivateKey(HexUtil.fromHexString(new String(privateKey)));
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setPrivateKey(keyPair.getPrivateKeyString());
		accountInfo.setPublicKey(keyPair.getPublicKeyString());

		byte[] publicKeyByte = HexUtil.fromHexString(accountInfo.getPublicKey());
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
		return accountInfo;
	}
}
