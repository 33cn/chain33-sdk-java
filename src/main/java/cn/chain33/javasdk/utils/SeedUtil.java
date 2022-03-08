package cn.chain33.javasdk.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.UnreadableWalletException;

import cn.chain33.javasdk.model.AccountInfo;

/**
 * 生成助记词，生成私钥，生成地址
 * 
 * @author andyYuan
 *
 */
public class SeedUtil {

    static NetworkParameters params;

    static {
        params = MainNetParams.get();
    }

    /**
     * 生成助记词
     * 
     * @return String 助记词
     */
    public static String generateMnemonic() {
        StringBuilder words = new StringBuilder();
        DeterministicSeed deterministicSeed = new DeterministicSeed(new SecureRandom(), 160, "",
                Utils.currentTimeSeconds());
        for (String str : deterministicSeed.getMnemonicCode()) {
            words.append(str).append(" ");
        }
        return words.toString().trim();

    }

    /**
     * 根据助记词生成私钥，公钥，地址
     * 
     * @param word
     *            助记词
     * @param passphrase
     * @param childIndex
     *            子账户索引
     * 
     * @return
     * 
     * @throws UnreadableWalletException
     */
    public static AccountInfo createAccountBy33PATH(String word, int childIndex) throws UnreadableWalletException {

        String parsePath = "44H / 13107H / 0H / 0 / " + String.valueOf(childIndex);

        DeterministicSeed deterministicSeed = new DeterministicSeed(word, null, "", 0L);
        DeterministicKeyChain deterministicKeyChain = DeterministicKeyChain.builder().seed(deterministicSeed).build();
        BigInteger privKey = deterministicKeyChain.getKeyByPath(HDUtils.parsePath(parsePath), true).getPrivKey();
        ECKey ecKey = ECKey.fromPrivate(privKey);
        Address address = ecKey.toAddress(params);
        AccountInfo acInfo = new AccountInfo();

        acInfo.setPrivateKey(ecKey.getPrivateKeyAsHex());
        acInfo.setPublicKey(ecKey.getPublicKeyAsHex());
        acInfo.setAddress(address.toBase58());

        return acInfo;
    }

}
