package cn.chain33.javasdk.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.AccountResult;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.model.rpcresult.TokenBalanceResult;
import cn.chain33.javasdk.model.rpcresult.TokenResult;
import cn.chain33.javasdk.model.rpcresult.TxResult;
import cn.chain33.javasdk.model.rpcresult.WalletStatusResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

public class RpcClientTest {

	String ip = "";
	RpcClient client = new RpcClient(ip, 8801);

	String testAddr = "address";
	String testKey = "privateKey";

	@Test
	public void checkStatus() {
		RpcClient newClient = new RpcClient();
		newClient.setUrl(ip, 8801);

		System.out.println("chain status:" + newClient.isSync());
	}

	@Test
	public void getWalletStatus() {
		WalletStatusResult walletStatus;
		try {
			walletStatus = client.getWalletStatus();
			System.out.println(walletStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void lock() {
		BooleanResult lock;
		try {
			lock = client.lock();
			System.out.println(lock);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void unlock() {
		String passwd = "123456";
		passwd = "fzm123";
		boolean walletorticket = false;
		int timeout = 0;
		BooleanResult unlock;
		try {
			unlock = client.unlock(passwd, walletorticket, timeout);
			System.out.println(unlock);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void seedGen() {
		String seedGen;
		try {
			seedGen = client.seedGen(1);
			System.out.println(seedGen);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void seedSave() {
		String seedCn = "常 辉 觉 李 固 参 鲜 村 见 控 罩 戈 狠 亿 圣";
		String passwd = "123456";
		BooleanResult booleanResult;
		try {
			booleanResult = client.seedSave(seedCn, passwd);
			System.out.println(booleanResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void seedGet() {
		String passwd = "123456";
		String seedGet;
		try {
			seedGet = client.seedGet(passwd);
			System.out.println(seedGet);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void newAccount() {
		AccountResult newAccount;
		try {
			newAccount = client.newAccount("secondAccount");
			System.out.println(newAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getAccounts() {
		List<AccountResult> accountList;
		try {
			accountList = client.getAccountList();
			for (AccountResult accountResult : accountList) {
				System.out.println(accountResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getBtyBalace() {
		List<String> list = new ArrayList<>();
		list.add("XXXXXX");
		List<AccountAccResult> queryBtyBalance;
		try {
			queryBtyBalance = client.queryBtyBalance(list, "coins");
			for (AccountAccResult accountAccResult : queryBtyBalance) {
				System.out.println(accountAccResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void setLabel() {
		AccountResult accountResult;
		try {
			accountResult = client.setlabel("19Sq2BSqX4CKvPMjsDsdwdfFzjUmfbSB7w", "first");
			System.out.println(accountResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void importPrivKey() {
		String accountResult;
		try {
			accountResult = client.importPrivkey("3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8",
					"genisis2");
			System.out.println(accountResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void queryTxDetail() {
		String hash = "0xe5ae58fab899781c72beaa92beb2661b4e70f8c8cbb8bbad61b0a191bc5ef6b7";
		QueryTransactionResult queryTransaction1;
		try {
			queryTransaction1 = client.queryTransaction(hash);
			System.out.println(queryTransaction1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void queryCreateTokens() {
		List<TokenResult> queryCreateTokens;
		try {
			queryCreateTokens = client.queryCreateTokens(1);
			for (TokenResult tokenResult : queryCreateTokens) {
				System.out.println(tokenResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void queryUserTokens() {
		List<TokenBalanceResult> queryAccountBalance;
		try {
			queryAccountBalance = client.queryAccountBalance("12qyocayNF7Lv6C9qxxxxxx", "user.xxx");
			for (TokenBalanceResult tokenBalanceResult : queryAccountBalance) {
				System.out.println(tokenBalanceResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void queryTokenBalace() {
		List<String> addrList = new ArrayList<>();
		addrList.add("XXXXXXXXXXXX");
		List<AccountAccResult> tokenBalance;
		try {
			tokenBalance = client.getTokenBalance(addrList, "XXXXXXXXXXXX", "tokenname");
			for (AccountAccResult accountAccResult : tokenBalance) {
				System.out.println(accountAccResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getTxByAddr() {
		List<TxResult> txByAddr;
		try {
			txByAddr = client.getTxByAddr(testAddr, 0, 10, 0, -1L, 0);
			for (TxResult txResult : txByAddr) {
				System.out.println(txResult);

			}
			for (TxResult txResult : txByAddr) {
				System.out.println(client.queryTransaction(txResult.getHash()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void sendTrsansation() {
		String unsignTx = "input unsigned tx";
		String sign = "sign";
		String pubkey = "public key";
		SignType ty = SignType.SECP256K1;
		String txHash;
		try {
			txHash = client.submitRawTransaction(unsignTx, sign, pubkey, ty);
			System.out.println(txHash);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void transferBtyFirstFunc() throws Exception {

		String to = "XXXXXXXXXXXXXXXXXXX";
		long amount = 1002;
		long fee = 1000000;
		String note = "test transfer";
		String tokenSymbol = "LGS";
		String unsignTx = client.createRawTransaction(to, amount, fee, note, true, false, tokenSymbol, "");

		String txhex = unsignTx;
		Integer index = 1;
		String expire = "300s";
		String signedTX = client.signRawTx(testAddr, testKey, txhex, expire, index);

		client.submitTransaction(signedTX);
	}

	@Test
	public void transferBtySecondFunc() throws Exception {

		String from = testAddr;
		String to = "xxxx";
		Long amount = 1002L;
		String note = "test";
		boolean isToken = true;
		String tokenSymbol = "LGS";
		String txHash = client.sendToAddress(from, to, amount, note, isToken, tokenSymbol);
		System.out.println(txHash);
		getAccounts();
	}

	@Test
	public void preCreateToken() throws Exception {
		long total = (long) (1000 * 1e8);
		String createRawTokenPreCreateTx = client.createRawTokenPreCreateTx("logan coin1", "LGS",
				"logan create the coin", testAddr, total, 0, (long) 1e8);
		String signRawTx = client.signRawTx(testAddr, testKey, createRawTokenPreCreateTx, "300", 0);
		client.submitTransaction(signRawTx);
	}

	@Test
	public void createTokenFinish() throws Exception {
		String createRawTokenFinishTx = client.createRawTokenFinishTx((long) 1e8, "LGS", testAddr);
		String signRawTx = client.signRawTx(testAddr, testKey, createRawTokenFinishTx, 300 + "", 0);
		String submitTransaction = client.submitTransaction(signRawTx);
		System.out.println(submitTransaction);
	}

	@Test
	public void convertExecertoAddr() {
		String address;
		try {
			address = client.convertExectoAddr("user.p.demo.game");
			System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void createPriviteKey() {
	    //create privateKey
	    String hexPrivateKey = TransactionUtil.generatorPrivateKeyString();
	    //get publicKey
	    String hexPublicKey = TransactionUtil.getHexPubKeyFromPrivKey(hexPrivateKey);
	    byte[] publicKeyByte = HexUtil.fromHexString(hexPublicKey);
	    //get address
        String genAddress = TransactionUtil.genAddress(publicKeyByte);
        //validate address
        boolean validAddressResult = TransactionUtil.validAddress(genAddress);
        System.out.printf("privateKey:%s\n",hexPrivateKey);
        System.out.printf("publicKey:%s\n",hexPublicKey);
        System.out.printf("address:%s\n",genAddress);
        System.out.printf("validate address:%s",validAddressResult);
    }
	
	@Test
    public void createCoinTransferTx() {
	    String note = "";
        String coinToken = "";
        Long amount = 1*100000000L;//1 = real amount
        String to = "toAddress";
        byte[] payload = TransactionUtil.createTransferPayLoad(to, amount, coinToken, note);
        
	    String fromAddressPriveteKey = testKey;
	    String execer = "coins";
	    String createTransferTx = TransactionUtil.createTransferTx(fromAddressPriveteKey,to, execer, payload);
	    String txHash = client.submitTransaction(createTransferTx);
	    System.out.println(txHash);
	    //queryTxDetail(txHash)
    }

}
