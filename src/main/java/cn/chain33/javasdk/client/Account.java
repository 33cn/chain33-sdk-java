package cn.chain33.javasdk.client;

import cn.chain33.javasdk.model.AccountInfo;
import cn.chain33.javasdk.utils.DesUtil;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.StringUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

import java.io.*;

public class Account {

    /**
     * 
     * @description 在本地创建账户信息
     * @return 账户信息(私钥，公钥，地址)
     *
     */
    public AccountInfo newAccountLocal() {
    	AccountInfo accountInfo = new AccountInfo();
    	
    	// 生成私钥匙
    	String privateKey = TransactionUtil.generatorPrivateKeyString();
    	accountInfo.setPrivateKey(privateKey);
    	// 生成公钥匙
    	String publicKey = TransactionUtil.getHexPubKeyFromPrivKey(privateKey);
    	accountInfo.setPublicKey(publicKey);
    	byte[] publicKeyByte = HexUtil.fromHexString(publicKey);
    	// 生成地址
    	accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
        return accountInfo;
    }

	/**
	 * 本地创建账户信息，加密输出到指定路径
	 */
	public AccountInfo newAccountLocal(String name, String passwd, String path) throws IOException {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setName(name);

		// 生成私钥匙
		String privateKey = TransactionUtil.generatorPrivateKeyString();
		accountInfo.setPrivateKey(privateKey);

		// 导出到文件
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

		// 生成公钥匙
		String publicKey = TransactionUtil.getHexPubKeyFromPrivKey(privateKey);
		accountInfo.setPublicKey(publicKey);
		byte[] publicKeyByte = HexUtil.fromHexString(publicKey);
		// 生成地址
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
		return accountInfo;
	}

	/**
	 * 导出本地账户信息
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
		// 生成公钥匙
		String publicKey = TransactionUtil.getHexPubKeyFromPrivKey(privateKey);
		accountInfo.setPublicKey(publicKey);
		byte[] publicKeyByte = HexUtil.fromHexString(publicKey);
		// 生成地址
		accountInfo.setAddress(TransactionUtil.genAddress(publicKeyByte));
		return accountInfo;
	}
}
