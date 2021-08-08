package cn.chain33.javasdk.model.paraTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.chain33.javasdk.client.RpcClient;
import org.junit.Test;

import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.model.rpcresult.TokenResult;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 *	�����������ƺ����������ֵ�Ԥ���У����ַ��У��������������ֲ�ѯ�Ƚӿ�
 *	���ַ��в��裺
 *	1. ͨ����ǰ���ĳ�������Ա�������Զ�����ֵĺ�����(ȫ�����ã�ͨ�������ֻ��Ҫִ��һ��)��
 *	2. ͨ����ǰ���ĳ�������Ա�������Զ�����ֵ������(ȫ�����ã�ͨ�������ֻ��Ҫִ��һ��)��
 *	3. ͨ�������������Ԥ�����Զ�����֡�
 *	4. ͨ���������������ʽ�����Զ�����֡�
 * @author fkeit
 */
public class TokenParaTest {

	
	// ƽ����IP
	String ip = "ƽ����IP";
	// ƽ��������˿�
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);
    
    // ƽ�������ƣ��̶���ʽuser.p.xxxx.  ����xxxx���滻��֧�ִ�СдӢ����ĸ
	String paraName = "user.p.evm.";
	
	// ��ǰ����������Ա��˽Կ��superManager����  ��Ӧ��ַ�� 1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs
	// ��BTY����ƽ�����ĳ����£���ַ����Ҫ������Ӧ��coins������֧������������
	// ������Ĭ�ϲ���ȡ�����ѣ�����������+ƽ�����ĳ����£���ַ�¿���û��coins
	String superManagerPk = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
    
    /**
     * 
     * @throws Exception 
     * @description �����Զ�����ֵĺ�����
     *
     */
    @Test
    public void createBlackList() throws Exception {

    	// �����Լ����
    	String execerName = paraName + "manage";
    	// �����Լ:���ú�����KEY
    	String key = "token-blacklist";
    	// �����Լ:���ú�����VALUE
    	String value = "BTC";
    	// �����Լ:���ò�����
    	String op = "add";
    	// ���첢ǩ������,ʹ�����Ĺ���Ա��superManager������ǩ���� 
    	// 55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4 ��Ӧ�Ĳ��Ե�ַ�ǣ�1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7
    	String txEncode = TransactionUtil.createManage(key, value, op, superManagerPk, execerName);
    	// ���ͽ���
    	String hash = client.submitTransaction(txEncode);
    	System.out.print(hash);
    }
    
    /**
     * 
     * @throws Exception 
     * @description �����Զ�����ֵ�token-finisher
     *
     */
    @Test
    public void createTokenFinisher() throws Exception {

    	// �����Լ����
    	String execerName = paraName + "manage";;
    	// �����Լ:����KEY
    	String key = "token-finisher";
    	// �����Լ:����VALUE���������token�Ĵ�����������Ȩ��������Ա��ַ��Ҳ������Ȩ������ַ
    	String value = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
    	// �����Լ:���ò�����
    	String op = "add";
    	String txEncode = TransactionUtil.createManage(key, value, op, superManagerPk, execerName);
    	// ���ͽ���
    	String hash = client.submitTransaction(txEncode);
    	System.out.print(hash);
    	
    }
    
	/**
	 * ����Ԥ����token���ύ
	 * @throws IOException 
	 */
	@Test
	public void preCreateTokenLocal() throws IOException {
	   //token�ܶ�
	   long total = 19900000000000000L;
	   //token��ע������
	   String name = "DEVELOP COINS";
	   //token�����ƣ�ֻ֧�ִ�д��ĸ��ͬһ������������ͬsymbol����
	   String symbol = "COINSDEVX";
	   //token����
	   String introduction = "�����߱�";
	   //����tokenԸ��е��ķ��ã���0����
	   Long price = 0L;
	   //0 Ϊ��ͨtoken�� 1 ��������ȼ��
	   Integer category = 0;
	   //��Լ����
	   String execer = paraName + "token";
	   //token��ӵ���ߵ�ַ
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   //token-finisher��ַ��Ӧ��˽Կ��createTokenFinisher���������õģ�value��
	   String managerPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
	   String precreateTx = TransactionUtil.createPrecreateTokenTx(execer, name, symbol, introduction, total, price,
	           owner, category, managerPrivateKey);
	   String submitTransaction = client.submitTransaction(precreateTx);
	   System.out.println(submitTransaction);
	}
	
	/**
	 * ���ش���token��ɽ��ײ��ύ
	 * @throws IOException 
	 */
	@Test
	public void createTokenFinishLocal() throws IOException {
	   String symbol = "COINSDEVX";
	   String execer = paraName + "token";
	   //token-finisher��ַ��Ӧ��˽Կ��createTokenFinisher���������õģ�value��
	   String managerPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
	   // token��ӵ���ߵ�ַ
	   String owner = "1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7";
	   String hexData = TransactionUtil.createTokenFinishTx(symbol, execer, owner, managerPrivateKey);
	   String submitTransaction = client.submitTransaction(hexData);
	   System.out.println(submitTransaction);
	}
	
    /**
     * ƽ�����ϵ�ת�˽���һ��ͨ�����۵ķ�ʽ����
     * @throws InterruptedException 
     * @throws IOException 
     * @description ͨ�����۵ķ�ʽ����token��ת�˽���
     */
    @Test
    public void createTokenTransfer() throws InterruptedException, IOException {
    	// ת��˵��
        String note = "ת��˵��";
        // token��
        String coinToken = "COINSDEVX";
        Long amount = 10000 * 100000000L;// 1 = real amount
        // ת���ĵ�ַ
        String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // ǩ��˽Կ��������Ҫ�������ң����ڽ���������
        String fromAddressPriveteKey = "55637b77b193f2c60c6c3f95d8a5d3a98d15e2d42bf0aeae8e975fc54035e2f4";
        // ִ��������
        String execer = paraName + "token";
        String txEncode = TransactionUtil.createTokenTransferTx(fromAddressPriveteKey, to, execer, amount, coinToken, note);
        
        String createNoBalanceTx = client.createNoBalanceTx(txEncode, "");
        
	    // ��������
	    List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
	    // ���۽���ǩ����˽Կ �����н��׵������Ѷ��������ַ�۳���
	    String withHoldPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
	    
	    String contranctAddress = client.convertExectoAddr(execer);
	    String hexString = TransactionUtil.signDecodeTx(decodeRawTransactions, contranctAddress, fromAddressPriveteKey, withHoldPrivateKey);
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
     * 
     * @throws IOException 
     * @description ��ѯ�Ѿ�������token
     *
     */
    @Test
    public void queryCreateTokens() throws IOException {
        String execer = paraName + "token";
        //״̬ 0Ԥ������ 1�����ɹ���
        Integer status = 1;
        List<TokenResult> queryCreateTokens;
        queryCreateTokens = client.queryCreateTokens(status,execer);
        for (TokenResult tokenResult : queryCreateTokens) {
            System.out.println(tokenResult);
        }
    }
    
	
    /**
     * 
     * @throws IOException 
     * @description ��ѯtoken���
     *
     */
    @Test
    public void getTokenBalace() throws IOException {
        // ִ��������
        String execer = paraName + "token";
        
        List<String> addressList = new ArrayList<>();
        addressList.add("1EHWKLEixvfanTHWmnF7mYMuDDXTCorZd7");
        addressList.add("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getTokenBalance(addressList, execer, "COINSDEVX");
        for (AccountAccResult accountAccResult : queryBtyBalance) {
            System.out.println(accountAccResult);
        }
    }

}
