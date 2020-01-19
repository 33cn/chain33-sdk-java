package cn.chain33.javasdk.client;

import cn.chain33.javasdk.model.AccountInfo;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

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

}
