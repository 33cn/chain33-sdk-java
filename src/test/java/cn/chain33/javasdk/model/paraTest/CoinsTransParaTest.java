package cn.chain33.javasdk.model.paraTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.utils.TransactionUtil;

/**
 * ƽ�����ϣ�ͨ�����۵ķ�ʽתcoins
 * @author fkeit
 *
 */
public class CoinsTransParaTest {
	
	// ƽ����IP
	String ip = "ƽ����IP";
	// ƽ��������˿�
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);
    
    // ƽ�������ƣ��̶���ʽuser.p.xxxx.  ����xxxx���滻��֧�ִ�СдӢ����ĸ
	String paraName = "user.p.evm.";
	
    /**
     * 
     * @throws InterruptedException 
     * @throws IOException 
     * @description ����ǩ�����۽����飬����createNoBlance֮���ٽ����ص����ݽ���,ǩ�������ͽ���
     * ���۽�����Ҫ����ƽ�����ĳ��ϣ������ϵĽ��ײ���Ҫ��ע��ʵ��
     *
     */
    @Test
    public void transferCoins() throws InterruptedException, IOException {
    	// ת��˵��
        String note = "ת��˵��";
        // ��������Ϊ"",����Ϊtoken��
        String coinToken = "";
        // ת������Ϊ1
        Long amount = 1 * 10000000L;
        String to = "1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs";
        // ���ع���ת�˽��׵�payload
        byte[] payload = TransactionUtil.createTransferPayLoad(to, amount, coinToken, note);
        
        // ǩ��˽˽Կ�������ϲ���۳�����ַ�µ������ң����Դ˵�ַ�¿���û��������
        String fromAddressPriveteKey = "CC38546E9E659D15E6B4893F0AB32A06D103931A8230B0BDE71459D2B27D6944";
        // ִ�������ƣ�ƽ����������Ϊƽ��������+coins(ƽ������Ӧ�����ļ��е�title��)
        String execer = paraName + "coins";
        // ƽ����ת��ʱ��ʵ��to�ĵ�ַ����payload�У�����to��ַ��Ӧ���Ǻ�Լ�ĵ�ַ
        String contranctAddress = client.convertExectoAddr(execer);
        String createTransferTx = TransactionUtil.createTransferTx(fromAddressPriveteKey, contranctAddress, execer, payload);
        
        //create no balance �����ַΪ��
        String createNoBalanceTx = client.createNoBalanceTx(createTransferTx, "");
        // ��������
        List<DecodeRawTransaction> decodeRawTransactions = client.decodeRawTransaction(createNoBalanceTx);
        // ���۽���ǩ����˽Կ
        String withHoldPrivateKey = "3990969DF92A5914F7B71EEB9A4E58D6E255F32BF042FEA5318FC8B3D50EE6E8";
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
     * @description ��ѯcoins���
     *
     */
    @Test
    public void getTokenBalace() throws IOException {
        // ִ��������
        String execer = paraName + "coins";
        
        List<String> addressList = new ArrayList<>();
        addressList.add("14KEKbYtKKQm4wMthSK9J4La4nAiidGozt");
        addressList.add("1CbEVT9RnM5oZhWMj4fxUrJX94VtRotzvs");
        List<AccountAccResult> queryBtyBalance;
        queryBtyBalance = client.getCoinsBalance(addressList, execer);
        for (AccountAccResult accountAccResult : queryBtyBalance) {
            System.out.println(accountAccResult);
        }
    }

}
