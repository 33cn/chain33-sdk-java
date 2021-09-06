package cn.chain33.javasdk.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.chain33.javasdk.model.cert.CertObject;
import cn.chain33.javasdk.model.protobuf.CertService;
import cn.chain33.javasdk.model.gm.SM2KeyPair;
import cn.chain33.javasdk.model.gm.SM2Util;
import cn.chain33.javasdk.model.pre.KeyFrag;
import cn.chain33.javasdk.model.pre.ReKeyFrag;
import cn.chain33.javasdk.model.rpcresult.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.chain33.javasdk.model.RpcRequest;
import cn.chain33.javasdk.model.RpcResponse;
import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.enums.RpcMethod;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.HttpUtil;
import cn.chain33.javasdk.utils.StringUtil;

/**
 * ����Զ�̽ӿ�
 * 
 * @author logan 2018��5��16��
 */
public class RpcClient {

    private static Logger logger = LoggerFactory.getLogger(RpcClient.class);

    // ͨ�������ļ�����������ʽ����URL
    private String BASE_URL;
    
    public static final Integer TX_EXEC_RESULT_OK = 0;

    public static final Integer TX_EXEC_RESULT_FAIL = 1;

    public static final Integer TX_EXEC_RESULT_WAIT = 2;

    private final String TX_NOT_EXIST = "tx not exist";

    public RpcClient() {
    }

    public RpcClient(String url) {
        this.BASE_URL = url;
    }

    public RpcClient(String host, Integer port) {
        this.BASE_URL = "http://" + host + ":" + port;
    }

    public void setBASE_URL(String bASE_URL) {
        BASE_URL = bASE_URL;
    }

    public void setUrl(String host, Integer port) {
        this.BASE_URL = "http://" + host + ":" + port;
    }

    public void setUrl(String url) {
        this.BASE_URL = url;
    }

    /**
     * @description ���ͽ���
     * 
     * @param transactionJsonResult
     * @return
     * @throws IOException 
     */
    public String submitTransaction(RpcRequest transactionJsonResult) throws IOException {
        transactionJsonResult.setMethod(RpcMethod.SEND_TRANSACTION);
        String jsonString = JSONObject.toJSONString(transactionJsonResult);
        String httpPostResult = HttpUtil.httpPost(getUrl(), jsonString);
        if (StringUtil.isNotEmpty(httpPostResult)) {
            JSONObject parseObject = JSONObject.parseObject(httpPostResult);
            if (messageValidate(parseObject))
                return null;
            return parseObject.getString("result");
        }
        return null;
    }

    /**
     * @description ���ͽ���
     * 
     * @param data ǩ����Ľ���
     * @return ����hash
     * @throws IOException 
     */
    public String submitTransaction(String data) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", data);

        RpcRequest postData = getPostData(RpcMethod.SEND_TRANSACTION);
        postData.addJsonParams(jsonObject);
        String httpPostResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(httpPostResult)) {
            JSONObject parseObject = JSONObject.parseObject(httpPostResult);
            if (messageValidate(parseObject))
                return null;
            return parseObject.getString("result");
        }
        return null;
    }

    /**
     * 
     * @description ��ѯ�ڵ��Ƿ�ͬ��
     * @return ͬ�����
     * @throws IOException 
     *
     */
    public Boolean isSync() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.BLOCKCHAIN_IS_SYNC);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            try {
                JSONObject jsonResult = JSONObject.parseObject(result);
                Boolean isSync = jsonResult.getBoolean("result");
                return isSync;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * @description ���ݽ��׹�ϣ��ѯ������Ϣ
     * @param hash ����hash
     * @return ������Ϣ
     * @throws IOException 
     */
    public QueryTransactionResult queryTransaction(String hash) throws IOException {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.QUERY_TRANSACTION);

        postData.addJsonParams(jsonObject);

        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            QueryTransactionResult transactionResult = resultJson.toJavaObject(QueryTransactionResult.class);
            transactionResult.setBlocktime(new Date(transactionResult.getBlocktime().getTime() * 1000));
            return transactionResult;
        }
        return null;
    }
    
    /**
     * @description ���ݽ��׹�ϣ��ѯ������Ϣ
     * @param hash ����hash
     * @return ������Ϣ
     * @throws IOException 
     */
    public String queryTx(String hash) throws IOException {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.QUERY_TRANSACTION);

        postData.addJsonParams(jsonObject);

        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            return "OK";
        }
        return null;
    }
    
    /**
     * @description ���ݽ��׹�ϣ��ѯ������Ϣ
     * @param hash ����hash
     * @return ������Ϣ
     */
    public Integer queryTransactionStat(String hash) throws IOException {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.QUERY_TRANSACTION);

        postData.addJsonParams(jsonObject);

        String httpPostResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());

        JSONObject jsonObjectResult = JSONObject.parseObject(httpPostResult);
        String error = jsonObjectResult.getString("error");
        if (StringUtil.isNotEmpty(error) && TX_NOT_EXIST.equals(error)) {
            return TX_EXEC_RESULT_WAIT;
        }

        JSONObject resultJson = jsonObjectResult.getJSONObject("result");
        QueryTransactionResult transactionResult = resultJson.toJavaObject(QueryTransactionResult.class);
        if("ExecOk".equals(transactionResult.getReceipt().getTyname())) {
            return TX_EXEC_RESULT_OK;
        }

        return TX_EXEC_RESULT_FAIL;
    }
    
    
    /**
     * @description ��ѯevm��Լͳ����Ϣ
     *
     * @param address:  ��ѯ�ĵ�ַ
     * @return
     */
    public JSONObject queryEVMStatResult(String address) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "evm");
        requestParam.put("funcName", "QueryStatistic");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("addr", address);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());

        return JSONObject.parseObject(requestResult);
    }
    
    /**
     * @description ��ѯ��ԼABI���
     *
     * @param address:  ��ѯ�ĵ�ַ
     * @param abiPack:  ��ѯ������abi��ʽ
     * @return
     */
    public JSONObject callEVMAbi(String address, String abiPack) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "evm");
        requestParam.put("funcName", "Query");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        payloadJson.put("input", abiPack);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());

        return JSONObject.parseObject(requestResult);
    }

    /**
     * @description ���ݹ�ϣ����������ȡ������Ϣ GetTxByHashes
     * 
     * @param hashIdList ����ID�б�
     * @return ���׽�������б�
     * @throws IOException 
     */
    public List<QueryTransactionResult> GetTxByHashes(List<String> hashIdList) throws IOException {
        if (hashIdList != null && !hashIdList.isEmpty()) {
            for (int i = 0; i < hashIdList.size(); i++) {
                String hash = hashIdList.get(i);
                hash = HexUtil.removeHexHeader(hash);
                hashIdList.set(i, hash);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hashes", hashIdList);
        RpcRequest postData = getPostData(RpcMethod.GET_TX_BY_HASHES);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultJson.getJSONArray("txs");
            List<QueryTransactionResult> resultList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject txJson = jsonArray.getJSONObject(i);
                QueryTransactionResult transactionResult = txJson.toJavaObject(QueryTransactionResult.class);
                transactionResult.setBlocktime(new Date(transactionResult.getBlocktime().getTime() * 1000));
                resultList.add(transactionResult);
            }
            return resultList;
        }
        return null;
    }

    /**
     * @description ���ݹ�ϣ��ȡ���׵��ַ��� GetHexTxByHash
     * 
     * @param hash ����hash
     * @return �����ַ���
     * @throws IOException 
     */
    public String getHexTxByHash(String hash) throws IOException {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.GET_HEX_TX_BY_HASH);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            String txString = parseObject.getString("result");
            return txString;
        }
        return null;
    }

    /**
     * @description ��ȡ�������� GetBlocks
     * 
     * @param start    ���鿪ʼ�߶�
     * @param end      ��������߶�
     * @param isDetail �Ƿ��ȡ����
     * @throws IOException 
     * 
     */
    public List<BlocksResult> getBlocks(Long start, Long end, boolean isDetail) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start);
        jsonObject.put("end", end);
        jsonObject.put("isDetail", isDetail);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCKS);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONArray jsonArray = parseObject.getJSONObject("result").getJSONArray("items");
            List<BlocksResult> blocksList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject blocksJson = jsonArray.getJSONObject(i);
                BlocksResult javaObject = JSONObject.toJavaObject(blocksJson, BlocksResult.class);
                javaObject.getBlock().setBlockTime(new Date(javaObject.getBlock().getBlockTime().getTime() * 1000));
                blocksList.add(javaObject);
            }
            return blocksList;
        }
        return null;
    }

    /**
     * @description ��ȡ���µ�����ͷ GetLastHeader
     * @return ����������Ϣ
     * @throws IOException 
     */
    public BlockResult getLastHeader() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_LAST_HEADER);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject jsonResult = parseObject.getJSONObject("result");
            BlockResult block = JSONObject.toJavaObject(jsonResult, BlockResult.class);
            Long blockTimeLong = jsonResult.getLong("blockTime");
            block.setBlockTime(new Date(blockTimeLong * 1000));
            return block;
        }
        return null;
    }

    /**
     * @description ��ȡ��������ͷ GetHeaders �ýӿ����ڻ�ȡָ���߶����������ͷ����Ϣ
     * 
     * @param start    ��ʼ����߶�
     * @param end      ��������߶�
     * @param isDetail �Ƿ��ӡ������ϸ��Ϣ
     * @throws IOException 
     */
    public List<BlockResult> getHeaders(Long start, Long end, boolean isDetail) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start);
        jsonObject.put("end", end);
        jsonObject.put("isDetail", isDetail);
        RpcRequest postData = getPostData(RpcMethod.GET_HEADERS);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject jsonResult = parseObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("items");
            List<BlockResult> blockResultList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject blockJson = jsonArray.getJSONObject(i);
                BlockResult blockResult = JSONObject.toJavaObject(blockJson, BlockResult.class);
                blockResult.setBlockTime(new Date(blockResult.getBlockTime().getTime() * 1000));
                blockResultList.add(blockResult);
            }
            return blockResultList;
        }
        return null;
    }
    
    /**
     * ȡƽ������ʱ��
     * @return
     * @throws IOException 
     */
    public int getBlockAverageTime() throws IOException {
    	
    	// �������ʱ��������ļ��ж�ȡ�� ���Թ��˵�
    	List<BlockResult> blockResultList = getHeaders(1l, 1l, false);
    	BlockResult resultFirst = blockResultList.get(0);
    	
    	BlockResult resultLast = getLastHeader();
    	
    	long averageSecond = (resultLast.getBlockTime().getTime() - resultFirst.getBlockTime().getTime())/((resultLast.getHeight() -1) * 1000);
    	
    	return (int)averageSecond ;
    }

    /**
     * @description ��ȡĳ�߶������ hash ֵ GetBlockHash �ýӿ����ڻ�ȡָ���߶����������ͷ����Ϣ
     * @param height ����߶�
     * @return ����hash
     * @throws IOException 
     */
    public String getBlockHash(Long height) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("height", height);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_HASH);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            String hexString = parseObject.getJSONObject("result").getString("hash");
            ;
            return HexUtil.removeHexHeader(hexString);
        }
        return null;
    }

    /**
     * @description ��ȡ�������ϸ��Ϣ
     * 
     * @param hash ����hash
     * @return ������Ϣ
     * @throws IOException 
     */
    public BlockOverViewResult getBlockOverview(String hash) throws IOException {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_DETAIL);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            BlockOverViewResult blockOverViewResult = JSONObject.toJavaObject(resultJson, BlockOverViewResult.class);
            return blockOverViewResult;
        }
        return null;
    }
    
    /**
     * @description ���ݹ�ϣ�б��ȡ�������ϸ��Ϣ
     * 
     * @param hash ����hash
     * @return ������Ϣ
     * @throws IOException 
     */
    public List<BlockResult> getBlockByHashes(String[] hashes, boolean disableDetail) throws IOException {
        if (hashes == null || hashes.length == 0) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hashes", hashes);
        jsonObject.put("disableDetail", disableDetail);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_BY_HASHS);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject jsonResult = parseObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("items");
            List<BlockResult> blockResultList = new ArrayList<BlockResult>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject blockJson = jsonArray.getJSONObject(i);
                BlockResult blockResult = JSONObject.toJavaObject(blockJson, BlockResult.class);
                blockResult.setBlockTime(new Date(blockResult.getBlockTime().getTime() * 1000));
                blockResultList.add(blockResult);
            }
            return blockResultList;
        }
        return null;
        
   }

    /**
     * @description ��ȡԶ�̽ڵ��б�
     * @return �ڵ���Ϣ
     * @throws IOException 
     */
    public List<PeerResult> getPeerInfo() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_PEER_INFO);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultJson.getJSONArray("peers");
            List<PeerResult> peerList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject peerJson = jsonArray.getJSONObject(i);
                PeerResult peer = JSONObject.toJavaObject(peerJson, PeerResult.class);
                peerList.add(peer);
            }
            return peerList;
        }
        return null;
    }
    
    
    /**
     * @description ��ѯ�ڵ�״̬
     * @return �ڵ�״̬
     * @throws IOException 
     */
    public NetResult getNetInfo() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_NET_INFO);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            NetResult netResult = JSONObject.toJavaObject(resultJson, NetResult.class);
            return netResult;
        }
        return null;
    }
    
    /**
     * @description ��ȡϵͳ֧��ǩ������
     * @return ǩ�������б�
     * @throws IOException 
     */
    public List<CryptoResult> getCryptoResult() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_CRYPTO_INFO);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultJson.getJSONArray("cryptos");
            List<CryptoResult> cryptoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject cryptoJson = jsonArray.getJSONObject(i);
                CryptoResult crypto = JSONObject.toJavaObject(cryptoJson, CryptoResult.class);
                cryptoList.add(crypto);
            }
            return cryptoList;
        }
        return null;
    }
    
    private RpcRequest getPostData(RpcMethod method) {
        RpcRequest postJsonData = new RpcRequest();
        postJsonData.setMethod(method);
        return postJsonData;
    }

    private Boolean messageValidate(JSONObject parseObject) {
        if (parseObject != null && parseObject.containsKey("error")) {
            String error = parseObject.getString("error");
            if (StringUtil.isNotEmpty(error)) {
                System.err.println("rpc error:" + parseObject);
                logger.error("rpc error:" + parseObject);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    /**
     * 
     * @description ���ݹ�ϣ����������ȡ������Ϣ
     * @param hashIdList    ����ID�б��ö��š�,���ָ�
     * @return ������Ϣ�б�
     * @throws IOException 
     */
    public List<QueryTransactionResult> getTxByHashes(String hashIdList) throws IOException {
        if (StringUtil.isEmpty(hashIdList)) {
            return null;
        }
        String[] hashArr = hashIdList.split(",");
        for (int i = 0; i < hashArr.length; i++) {
            String hash = hashArr[i];
            if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
                hashArr[i] = HexUtil.removeHexHeader(hash);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hashes", hashArr);
        RpcRequest postData = getPostData(RpcMethod.GET_TX_BY_HASHES);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultJson.getJSONArray("txs");
            if (jsonArray != null && jsonArray.size() != 0) {
                List<QueryTransactionResult> resultList = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject txJson = jsonArray.getJSONObject(i);
                    if (txJson == null) {
                        continue;
                    }
                    QueryTransactionResult transactionResult = txJson.toJavaObject(QueryTransactionResult.class);
                    transactionResult.setBlocktime(new Date(transactionResult.getBlocktime().getTime() * 1000));
                    resultList.add(transactionResult);
                }
                return resultList;
            }
        }
        return null;
    }

    /**
     * 
     * @description ��ѯǮ��״̬
     * @return Ǯ��״̬
     * @throws IOException 
     *
     */
    public WalletStatusResult getWalletStatus() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_WALLET_STUATUS);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            WalletStatusResult WalletStatus = resultJson.toJavaObject(WalletStatusResult.class);
            return WalletStatus;
        }
        return null;
    }

    /**
     * @description ���� Lock
     * 
     * @return ���
     * @throws IOException 
     */
    public BooleanResult lock() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.LOCK_WALLET);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            BooleanResult lockResult = resultJson.toJavaObject(BooleanResult.class);
            return lockResult;
        }
        return null;
    }

    /**
     * @description ���� Unlock
     * 
     * @param passwd    ��������
     * @param walletorticket true��ֻ����ticket��Ʊ���ܣ�false����������Ǯ����
     * @param timeout        ����ʱ�䣬Ĭ�� 0����ʾ��Զ�������� 0 ֵ����ʾ��ʱ֮�������סǮ������λ���롣
     * @return ���
     * @throws IOException 
     */
    public BooleanResult unlock(String passwd, boolean walletorticket, int timeout) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.UNLOCK_WALLET);
        JSONObject requestParam = new JSONObject();
        requestParam.put("passwd", passwd);
        requestParam.put("walletorticket", walletorticket);
        requestParam.put("timeout", timeout);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            BooleanResult lockResult = resultJson.toJavaObject(BooleanResult.class);
            return lockResult;
        }
        return null;
    }

    /**
     * 
     * @description �ڽڵ�Ǯ���д����˻���ַ
     * @param label ��ǩ
     * @return �˻���Ϣ
     * @throws IOException 
     *
     */
    public AccountResult newAccount(String label) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.NEW_ACCOUNT);
        JSONObject requestParam = new JSONObject();
        requestParam.put("label", label);
        postData.addJsonParams(requestParam);

        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            AccountResult newAccountResult = resultJson.toJavaObject(AccountResult.class);
            return newAccountResult;
        }
        return null;
    }
    

    /**
     * @description ���������seed
     * 
     * @param lang lang=0:Ӣ�lang=1:���庺��
     * @return seed
     * @throws IOException 
     */
    public String seedGen(Integer lang) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GEN_SEED);
        JSONObject requestParam = new JSONObject();
        requestParam.put("lang", lang);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            String seed = resultJson.getString("seed");
            return seed;
        }
        return null;
    }

    /**
     * @description ����seed�����������
     * 
     * @param seed ����Ҫ��16�����ʻ��ߺ��֣��ο�genseed�����ʽ����Ҫ�ո����
     * @param passwd �������룬������ڻ����8���ַ�����ĸ���������
     * @return
     * @throws IOException 
     */
    public BooleanResult seedSave(String seed, String passwd) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.SAVE_SEED);
        JSONObject requestParam = new JSONObject();
        requestParam.put("seed", seed);
        requestParam.put("passwd", passwd);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            BooleanResult booleanResult = resultJson.toJavaObject(BooleanResult.class);
            return booleanResult;
        }
        return null;
    }

    /**
     * @description ͨ�������ȡseed
     * 
     * @param passwd ����
     * @return  seed
     * @throws IOException 
     */
    public String seedGet(String passwd) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_SEED);
        JSONObject requestParam = new JSONObject();
        requestParam.put("passwd", passwd);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            String seed = resultJson.getString("seed");
            return seed;
        }
        return null;
    }

    /**
     * @description ����Լת��Ϊ��ַ
     * 
     * @param execername ����user.p.xxchain.xxx
     * @return ��Լ��ַ
     * @throws IOException 
     */
    public String convertExectoAddr(String execername) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CONVERT_EXECER_TO_ADDRESS);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execname", execername);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            String address = parseObject.getString("result");
            return address;
        }
        return null;
    }

    /**
     * @description ���õ�ַ��ǩ
     * 
     * @param addr  ���� 13TbfAPJRmekQxYVEyyGWgfvLwTa8DJW6U
     * @param label ���� macAddrlabel
     * @return ���
     * @throws IOException 
     */
    public AccountResult setlabel(String addr, String label) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.SET_LABEL);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addr", addr);
        requestParam.put("label", label);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            AccountResult accountResult = resultJson.toJavaObject(AccountResult.class);
            return accountResult;
        }
        return null;
    }

    /**
     * @description ��ȡ�˻��б� GetAccounts
     * 
     * @return �˺��б�
     * @throws IOException 
     */
    public List<AccountResult> getAccountList() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_ACCOUNT_LIST);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultJson.getJSONArray("wallets");
            List<AccountResult> accountList = jsonArray.toJavaList(AccountResult.class);
            return accountList;
        }
        return null;
    }

    /**
     * ����EVM��Լ���� CreateTransaction * 11.9 ����Ԥ����token �Ľ���
     * 
     * @param execer     ִ�������ƣ�����̶�Ϊevm
     * @param actionName �������ƣ�����̶�ΪCreateCall
     * @param payload    https://chain.33.cn/document/108#1.1%20%E5%88%9B%E5%BB%BAEVM%E5%90%88%E7%BA%A6%E4%BA%A4%E6%98%93%20CreateTransaction
     * @return
     * @throws Exception
     */
    public String createTransaction(String execer, String actionName, JSONObject payload) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CREATE_TRASACTION);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("actionName", actionName);
        requestParam.put("payload", payload);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }
    
    /**
     * ���ù����Լ�������������� ����token-finisher��
     * 
     * @param execer
     * @param actionName
     * @param superManager
     * @return
     * @throws Exception 
     */
    public String createManageTransaction(String execer, String actionName, String key, String value, String op, String superManager) throws Exception {
    	JSONObject blackListPayload = new JSONObject();
    	blackListPayload.put("key", key);
    	blackListPayload.put("value", value);
    	blackListPayload.put("op", op);
    	String managerResult = createTransaction(execer, actionName, blackListPayload);
    	return managerResult;
    }
    

    /**
     * �����˺�
     * @param execer
     * @param actionName
     * @param accountId
     * @return
     * @throws Exception
     */
    public String registeAccount(String execer, String actionName, String accountId) throws Exception {
    	JSONObject accountPayload = new JSONObject();
    	accountPayload.put("accountID", accountId);
    	String accountResult = createTransaction(execer, actionName, accountPayload);
    	return accountResult;
    }
    
    /**
     * ���˺Ž�����Ȩ
     * @param execer
     * @param actionName
     * @param accountIds
     * @return
     * @throws Exception
     */
    public String authAccount(String execer, String actionName, String[] accountIds, String op, String level) throws Exception {
    	JSONObject accountPayload = new JSONObject();
    	accountPayload.put("accountIDs", accountIds);
    	accountPayload.put("op", op);
    	if (StringUtil.isNotEmpty(level)) {
        	accountPayload.put("level", level);
    	}
    	String accountResult = createTransaction(execer, actionName, accountPayload);
    	return accountResult;
    }
    
    /**
     * ���ӹ�ʶ�ڵ�
     * 
     * @param execer
     * @param actionName
     * @return
     * @throws Exception
     */
    public String addConsensusNode(String execer, String actionName, String pubKey, int power) throws Exception {
    	JSONObject nodePayload = new JSONObject();
    	nodePayload.put("pubKey", pubKey);
    	nodePayload.put("power", power);
    	String nodeResult = createTransaction(execer, actionName, nodePayload);
    	return nodeResult;
    }
    

    /**
     * @description ����Ԥ����token�Ľ���
     * 
     * @param name         token��ȫ������󳤶���128���ַ���
     * @param symbol       token��Ƿ�����󳤶���16���ַ����ұ���Ϊ��д�ַ���
     * @param introduction token���ܣ���󳤶�Ϊ1024���ֽڡ�
     * @param ownerAddr    tokenӵ���ߵ�ַ
     * @param total        ��������,��Ҫ����10��8�η�������Ҫ����100���ң���Ҫ100*1e8
     * @param price        ���и�tokenԸ��е��ķ���
     * @return ����ʮ�����Ʊ������ַ���
     * @throws IOException 
     */
    public String createRawTokenPreCreateTx(String name,String symbol,String introduction,String ownerAddr,long total,long price,Integer category) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_PRE_CREATE_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("name", name);
        requestParam.put("symbol", symbol);
        requestParam.put("introduction", introduction);
        requestParam.put("owner", ownerAddr);
        requestParam.put("total", total);
        requestParam.put("price", price);
        requestParam.put("category", category);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }

    /**
     * @description ������ɴ���token�Ľ��ף�δǩ����
     * 
     * @param symbol:    token��Ƿ�����󳤶���16���ַ����ұ���Ϊ��д�ַ���
     * @param ownerAddr: tokenӵ���ߵ�ַ
     * @param fee:       ���׵�������
     * @return ����ʮ�����Ʊ������ַ���
     * @throws IOException 
     */
    public String createRawTokenFinishTx(long fee,String symbol,String ownerAddr) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_FINISH_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("fee", fee);
        requestParam.put("symbol", symbol);
        requestParam.put("owner", ownerAddr);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }

    /**
     * @description ���콻��
     * 
     * @param to:Ŀ���ַ��
     * @param amount:���ͽ�ע��������ҵ�λΪ10^8
     * @param fee:�����ѣ�ע��������ҵ�λΪ10^8
     * @param note:��ע���Ǳ���
     * @param isToken:�Ƿ���token���͵�ת�� ����tokenת����������
     * @param isWithdraw:�Ƿ�Ϊȡ���
     * @param tokenSymbol:token �� symbol ����tokenת����������
     * @param execName:TransferToExec��ת����Լ�� �� Withdraw���Ӻ�Լ���������Ҫ����ƽ�����ϵ�ת�ˣ��˲����ÿ�
     * @return ��ע�����result ��Ϊnil,��Ϊ�����Ľ���16�����ַ������롣����ͨ��hex decode��
     * @throws IOException 
     */
    public String createRawTransaction(String to, long amount, long fee, String note, boolean isToken,
            boolean isWithdraw, String tokenSymbol, String execName) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_RAW_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("to", to);
        requestParam.put("amount", amount);
        requestParam.put("fee", fee);
        requestParam.put("note", note);
        requestParam.put("isToken", isToken);
        requestParam.put("isWithdraw", isWithdraw);
        requestParam.put("tokenSymbol", tokenSymbol);
        requestParam.put("execName", execName);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }

    /**
     * @description ���콻��(ƽ�����ϻ��õ�)
     * 
     * @param txHex:   ����һ����createRawTx���ɵĽ����ٴ��루���磬CreateRawTokenPreCreateTx��tokenԤ������CreateRawTokenFinishTx��token��ɣ�CreateRawTransaction��ת��token��
     * @param payAddr: ���ڸ��ѵĵ�ַ�������ַҪ�������ϴ��ڣ����������б���Ԫ����֧�������ѡ�
     * @param Privkey�� ��Ӧ��payAddr��˽Կ�����payAddr�Ѿ����뵽ƽ��������ô���˽Կ���Բ�����������������ƽ�����ϵ����ַ����֤˽Կ��ȫ��
     * @param expire:  ��ʱʱ��
     * @return hash
     * @throws IOException 
     */
    public String createRawTransaction(String txHex, String payAddr, String Privkey, String expire) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_RAW_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("txHex", txHex);
        requestParam.put("addr", payAddr);
        requestParam.put("Privkey", Privkey);
        requestParam.put("expire", expire);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }
    
    /**
     * @description ����ǩ��
     * 
     * @param addr ��key����ֻ������һ
     * @param key ˽Կ
     * @param expire ����ʱ���������"300ms"��"-1.5h"����"2h45m"���ַ�������Чʱ�䵥λΪ"ns", "us" (or "��s"), "ms","s", "m","h"
     * @param index ����ǩ�������飬��ΪҪǩ���Ľ�����ţ���1��ʼ��С�ڵ���0��Ϊǩ������ȫ������
     * @param index �̶���д2(������һ�������飬��1��none�Ľ����Ѿ���pay addressǩ�����ˣ��˴�ǩindex=2�Ľ���)
     * @return txhex
     * @throws IOException 
     */
    public String signRawTx(String addr, String key, String txhex, String expire, int index) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.SIGN_RAW_TRANSACTION);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addr", addr);
        requestParam.put("key", key);
        requestParam.put("txhex", txhex);
        requestParam.put("expire", expire);
        requestParam.put("index", index);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }

    /**
     * @description ��ѯ��ַtoken���
     * 
     * @param addresses ��ַ�б�
     * @param execer    token ��ѯ���õ���� ��trade ��ѯ���ڽ��׺�Լ���token,����ǲ�ѯƽ������������Ҫָ������ƽ������ִ����execer,���磺user.p.xxx.token .
     * @param tokenSymbol   token��������
     * @return  �˺�����б�
     * @throws IOException 
     */
    public List<AccountAccResult> getTokenBalance(List<String> addresses, String execer, String tokenSymbol) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_TOKEN_BALANCE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addresses", addresses);
        requestParam.put("execer", execer);
        requestParam.put("tokenSymbol", tokenSymbol);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONArray resultArray = parseObject.getJSONArray("result");
            List<AccountAccResult> javaList = resultArray.toJavaList(AccountAccResult.class);
            return javaList;
        }
        return null;
    }

    /**
     * @description ��ѯ���������
     * 
     * @param addresses  ��ַ�б�
     * @param execer    coins
     * @return  ����б�
     * @throws IOException 
     */
    public List<AccountAccResult> getCoinsBalance(List<String> addresses, String execer) throws IOException {
        RpcRequest postJsonData = new RpcRequest();
        postJsonData.setMethod(RpcMethod.GET_BALANCE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addresses", addresses);
        requestParam.put("execer", execer);
        postJsonData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postJsonData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONArray resultArray = parseObject.getJSONArray("result");
            List<AccountAccResult> javaList = resultArray.toJavaList(AccountAccResult.class);
            return javaList;
        }
        return null;
    }

    /**
     * @description ����˽Կ
     * 
     * @param addr ����˽Կ�ĵ�ַ
     * @return ˽Կ
     * @throws IOException 
     */
    public String dumpPrivkey(String addr) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.DUMP_PRIVKEY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("data", addr);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultObj = parseObject.getJSONObject("result");
            String resultStr = resultObj.getString("data");
            return resultStr;
        }
        return null;
    }

    /**
     * @description ����˽Կ
     * 
     * @param privateKey ˽Կ
     * @param label   ��ַlabel
     * @return ������
     * @throws IOException 
     */
    public String importPrivatekey(String privateKey, String label) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.IMPORT_PRIVKEY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("privkey", privateKey);
        requestParam.put("label", label);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultObj = parseObject.getJSONObject("result");
            String resultStr = resultObj.getString("acc");
            return resultStr;
        }
        return null;
    }

    /**
     * @description ���ݵ�ַ��ȡ������Ϣhash
     * 
     * @param flag:   0��addr �����н��ף�1���� addr Ϊ���ͷ�ʱ�Ľ��ף�2���� addr Ϊ���շ�ʱ�Ľ��ס�
     * @param height: �������ڵ�block�߶ȣ�-1����ʾ�����µĿ�ʼ���ȡ�����ڵ���0��ֵ���Ӿ���ĸ߶�+����index��ʼȡ��
     * @param index:  ��������block�е�������ȡֵ0--100000��
     * @return �����б�
     * @throws IOException 
     */
    public List<TxResult> getTxByAddr(String addr, Integer flag, Integer count, Integer direction, Long height,
            Integer index) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_TX_BY_ADDR);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addr", addr);
        requestParam.put("flag", flag);
        requestParam.put("count", count);
        requestParam.put("direction", direction);
        requestParam.put("height", height);
        requestParam.put("index", index);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultObj = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultObj.getJSONArray("txInfos");
            List<TxResult> javaList = jsonArray.toJavaList(TxResult.class);
            return javaList;
        }
        return null;
    }

    /**
     * @description ��ѯ����Ԥ������token�򴴽��ɹ���token
     * 
     * @param status 0:Ԥ���� 1:�����ɹ� ��token
     * @return  token��Ϣ�б�
     * @throws IOException 
     */
    public List<TokenResult> queryCreateTokens(Integer status,String execer) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "GetTokens");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("status", status);
        payloadJson.put("queryAll", true);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray resultArray = resultJson.getJSONArray("tokens");
            List<TokenResult> javaList = resultArray.toJavaList(TokenResult.class);
            return javaList;
        }
        return null;
    }

    /**
     * @description ��ѯ��ַ�µ�token/trace��Լ�µ�token�ʲ�
     * 
     * @param address:  ��ѯ�ĵ�ַ
     * @param payloadExecer:   token �� trade
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public List<TokenBalanceResult> queryAccountBalance(String address, String payloadExecer) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "token");
        requestParam.put("funcName", "GetAccountTokenAssets");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        payloadJson.put("execer", payloadExecer);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray resultArray = resultJson.getJSONArray("tokenAssets");
            List<TokenBalanceResult> javaList = resultArray.toJavaList(TokenBalanceResult.class);
            return javaList;
        }
        return null;
    }
    
    /**
     * @description ��ѯ��Լ���ĵ�GAS
     * @param execer:   ִ��������
     * @param tx δǩ���Ľ���hex
     * @param address:  ��ѯ�ĵ�ַ
     * @param funcName: ������
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public long queryEVMGas(String execer, String tx, String address) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "EstimateGas");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("tx", tx);
        payloadJson.put("from", address);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return 0;
            String gas = parseObject.getJSONObject("result").getString("gas");
            return Long.parseLong(gas);
        }
        return 0;
    }
    
    /**
     * @description ��ѯ��Լ�󶨵�ABI��Ϣ
     * 
     * @param address:  ��ѯ�ĵ�ַ
     * @param execer:   ִ��������
     * @param funcName: ������
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public JSONArray queryEVMABIInfo(String address, String execer) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "QueryABI");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray resultArray = resultJson.getJSONArray("abi");
            return resultArray;
        }
        return null;
    }
    
    /**
     * @description ��ѯ��ԼABI���
     * 
     * @param address:  ��ѯ�ĵ�ַ
     * @param execer:   ִ��������
     * @param funcName: ������
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public JSONArray queryEVMABIResult(String address, String execer, String abiFunc) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "Query");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        payloadJson.put("input", abiFunc);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            JSONArray resultArray = resultJson.getJSONArray("jsonData");
            return resultArray;
        }
        return null;
    }

    /**
     * @description ��ѯ��ַ���
     * 
     * @param addressList ��ַ
     * @param execer  coins
     * @return
     * @throws IOException 
     */
    public List<AccountAccResult> queryBalance(List<String> addressList, String execer) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_BALANCE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("addresses", addressList);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONArray jsonArray = parseObject.getJSONArray("result");
            List<AccountAccResult> javaList = jsonArray.toJavaList(AccountAccResult.class);
            return javaList;
        }
        return null;
    }

    public String getUrl() {
        return BASE_URL;
    }

    /**
     * @description ����ǩ����Ľ���
     * @param unsignTx δǩ����tx
     * @param sign     sign:��˽Կ��unsigntxǩ���õ�����
     * @param pubkey   ˽Կ��Ӧ�Ĺ�Կ
     * @param signType ǩ������
     * @return hash
     * @throws IOException 
     */
    public String submitRawTransaction(String unsignTx, String sign, String pubkey, SignType signType) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.SEND_RAW_TRANSACTION);
        JSONObject requestParam = new JSONObject();
        requestParam.put("unsignTx", unsignTx);
        requestParam.put("sign", sign);
        requestParam.put("pubkey", pubkey);
        requestParam.put("ty", signType.getType());
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }

    /**
     * @description tokenת��
     * 
     * @param from:        ��Դ��ַ��
     * @param to:          ���͵���ַ��
     * @param amount:      ���ͽ�
     * @param note:        ��ע��
     * @param isToken:     ���͵��Ƿ���token��false ������·��͵�bt
     * 
     * @param tokenSymbol: token��Ƿ�����󳤶���16���ַ����ұ���Ϊ��д�ַ���
     * @return
     * @throws IOException 
     */
    public String sendToAddress(String from, String to, Long amount, String note, boolean isToken, String tokenSymbol) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.SEND_TO_ADDRESS);
        JSONObject requestParam = new JSONObject();
        requestParam.put("from", from);
        requestParam.put("to", to);
        requestParam.put("amount", amount);
        requestParam.put("note", note);
        requestParam.put("isToken", isToken);
        requestParam.put("tokenSymbol", tokenSymbol);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            String hash = parseObject.getJSONObject("result").getString("hash");
            return hash;
        }
        return null;
    }

    /**
     * 
     * @param userAddr �û���ַ
     * @param reqStr   �������
     * @param signAddr sys_sign_addr ϵͳǩ����ַ��
     * @return
     * @throws IOException 
     */
    public String processTxGroup(String userAddr, String reqStr, String signAddr) throws IOException {
        String response = HttpUtil.httpPost(getUrl(), reqStr);
        RpcResponse rep = parseResponse(response, reqStr);
        if (rep == null) {
            logger.error("����������ʧ��");
            return "";
        }
        String rawTxHex = String.valueOf(rep.getResult());

        // �������������ѽ���
        String withholdTxHex = createNoBalanceTx(rawTxHex, signAddr);

        // �Դ��۽���ǩ��
        String signedTxHex = signRawTx(userAddr, null, withholdTxHex, "1h", 2);
        if ("".equals(signedTxHex)) {
            logger.error("����ǩ��ʧ��");
            return "";
        }

        // ���ͽ�����
        String txHash = submitTransaction(signedTxHex);
        if ("".equals(txHash)) {
            logger.error("�����鷢�ͽ���ʧ��");
            return "";
        }
        return txHash;
    }
    

    public List<DecodeRawTransaction> decodeRawTransaction(String rawTx) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.DECODE_RAW_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("txHex", rawTx);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultObj = parseObject.getJSONObject("result");
            JSONArray jsonArray = resultObj.getJSONArray("txs");
            List<DecodeRawTransaction> javaList = jsonArray.toJavaList(DecodeRawTransaction.class);
            return javaList;
        }
        return null;
    }


    /**
     * @descprition ��ԭ�еĽ��׻����Ϲ���һ�������Ѵ��۽��ף���Ԥ�Ƚ�payAddr��Ӧ��˽Կ���뵽ƽ����
     * 
     * @param txHex   ��ת���׵�16�����ַ���
     * @param payAddr �����˻��ĵ�ַ
     * @return ����ԭ�л�ת��������۽��׵Ľ�����16�����ַ���
     * @throws IOException 
     */
    public final String createNoBalanceTx(String txHex, String payAddr) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CREATE_NO_BALANCE_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("txHex", txHex);
        requestParam.put("payAddr", payAddr);
        requestParam.put("expire", "1h");
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (requestResult == null || "".equals(requestResult) || "null".equals(requestResult)) {
            logger.error("create no balance tx error");
        }
        RpcResponse parseResponse = parseResponse(requestResult, requestParam.toJSONString());
        if (parseResponse == null) {
            return null;
        }
        return String.valueOf(parseResponse.getResult());
    }

    /**
     * @descprition ����RPC���ؽ���ַ���
     * @author lyz
     * @create 2018/11/19 18:20
     * @param response RPC���󷵻ؽ���ַ���
     * @param reqParam �����������Ҫ���ڳ����ʱ����ʾ����־
     * @return RPC�������
     */
    public static RpcResponse parseResponse(String response, String reqParam) {
        RpcResponse rep = null;
        if (response != null && !"".equals(response) && !"null".equals(response)) {
            logger.info("RESPONSE:" + response);
            rep = JSONObject.parseObject(response, RpcResponse.class);
            if (rep.isValid()) {
                return rep;
            }
        }
        logger.error("RPC����ʧ�ܣ�������Ϣ��" + rep == null ? "" : rep.getError() + " , ���������" + reqParam);
        return null;
    }
    
    
    /**
     * 
     * @description    ��������Ԥ����token����
     * @param symbol   ����symbol
     * @param owner    ����ӵ���ߵ�ַ
     * @return
     * @throws IOException 
     *
     */
    public String CreateRawTokenRevokeTx(String symbol,String owner) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_RAW_TOKEN_REVOKE_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("symbol", symbol);
        requestParam.put("owner", owner);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) return null;
            String result = parseObject.getString("result");
            return result;
        }
        return null;
    }
    
    
    /**
     * @description ��ѯ��֤��Ϣ
     * 
     * @param hash:   hash
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public JSONObject queryStorage(String hash) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "storage");
        requestParam.put("funcName", "QueryStorage");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("txHash", hash);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            return resultJson;
        }
        return null;
    }
    
    /**
     * @description ����AccountId���˻���Ϣ
     * 
     * @param accountId:   accountId
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public JSONObject queryAccountById(String accountId) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "accountmanager");
        requestParam.put("funcName", "QueryAccountByID");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("accountID", accountId);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            return resultJson;
        }
        return null;
    }
    
    /**
     * @description �����˻�״̬���˻���Ϣ
     * 
     * @param status:   status
     * @return TokenBalanceResult
     * @throws IOException 
     */
    public JSONObject queryAccountByStatus(String status) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "accountmanager");
        requestParam.put("funcName", "QueryAccountsByStatus");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("status", status);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            return resultJson;
        }
        return null;
    }

    /**
     *
     * @description �����ؼ���Կ��Ƭ���ؼ��ܽڵ�
     *
     * @param pubOwner     ���ݹ����߹�Կ
     * @param pubRecipient ���ݽ����߹�Կ
     * @param pubProofR    �ؼ��������ԿR
     * @param pubProofU    �ؼ��������ԿU
     * @param expire       ��ʱʱ��
     * @param dhProof      ���֤��
     * @param frag         �ؼ�����Կ��Ƭ
     * @return true/false
     * @throws IOException 
     */
    public boolean sendKeyFragment(String pubOwner, String pubRecipient, String pubProofR, String pubProofU, int expire,
                                   String dhProof, KeyFrag frag) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.PRE_SEND_KEY_FRAGMENT);

        JSONObject requestParam = new JSONObject();
        requestParam.put("pubOwner", pubOwner);
        requestParam.put("pubRecipient", pubRecipient);
        requestParam.put("pubProofR", pubProofR);
        requestParam.put("pubProofU", pubProofU);
        requestParam.put("random", frag.getRandom());
        requestParam.put("value", frag.getValue());
        requestParam.put("expire", expire);
        requestParam.put("dhProof", dhProof);
        requestParam.put("precurPub", frag.getPrecurPub());
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return false;
            }

            return parseObject.getJSONObject("result").getBoolean("result");
        }
        return false;
    }

    /**
     *
     * @description �����ؼ���
     *
     * @param pubOwner      ���ݹ����߹�Կ
     * @param pubRecipient  ���ݽ����߹�Կ
     * @return �ؼ���Ƭ��
     * @throws IOException 
     */
    public ReKeyFrag reencrypt(String pubOwner, String pubRecipient) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.PRE_RE_ENCRYPT);

        JSONObject requestParam = new JSONObject();
        requestParam.put("pubOwner", pubOwner);
        requestParam.put("pubRecipient", pubRecipient);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return null;
            }

            String rekeyObject = parseObject.getString("result");
            if (messageValidate(parseObject)) {
                return null;
            }
            return JSONObject.parseObject(rekeyObject, ReKeyFrag.class);
        }
        return null;
    }

    /**
     *
     * @description ֤���û�ע��
     *
     * @param userName   �û���
     * @param identity   �û�id
     * @param userPub    �û���Կ
     * @param adminKey   ����Ա˽Կ
     * @return ע����
     * @throws IOException 
     */
    public boolean certUserRegister(String userName, String identity, String userPub, String adminKey) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_USER_REGISTER);

        JSONObject requestParam = new JSONObject();
        requestParam.put("name", userName);
        requestParam.put("identity", identity);
        requestParam.put("pubKey", userPub);

        CertService.ReqRegisterUser.Builder reqBuilder = CertService.ReqRegisterUser.newBuilder();
        reqBuilder.setName(userName);
        reqBuilder.setIdentity(identity);
        reqBuilder.setPubKey(userPub);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(adminKey));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return false;
            }

            return parseObject.getJSONObject("result").getBoolean("result");
        }
        return false;
    }

    /**
     *
     * @description ֤���û�ע��
     *
     * @param identity   �û�id
     * @param adminKey   ����Ա˽Կ
     * @return ע�����
     * @throws IOException 
     */
    public boolean certUserRevoke(String identity, String adminKey) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_USER_REVOKE);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqRevokeUser.Builder reqBuilder = CertService.ReqRevokeUser.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(adminKey));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return false;
            }

            return parseObject.getJSONObject("result").getBoolean("result");
        }
        return false;
    }

    /**
     *
     * @description �û�֤������
     *
     * @param identity   �û�id
     * @param key        �û�˽Կ
     * @return ע�����
     * @throws IOException 
     */
    public CertObject.CertEnroll certEnroll(String identity, String key) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_ENROLL);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqEnroll.Builder reqBuilder = CertService.ReqEnroll.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return null;
            }

            String certObject = parseObject.getString("result");
            if (messageValidate(parseObject)) {
                return null;
            }

            return JSONObject.parseObject(certObject, CertObject.CertEnroll.class);
        }
        return null;
    }

    /**
     *
     * @description �û�֤���������룬�����û�֤�鱻ע����
     *
     * @param identity   �û�id
     * @param adminKey   ����Ա˽Կ
     * @return ע�����
     * @throws IOException 
     */
    public CertObject.CertEnroll certReEnroll(String identity, String adminKey) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_REENROLL);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqEnroll.Builder reqBuilder = CertService.ReqEnroll.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(adminKey));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return null;
            }

            String certObject = parseObject.getString("result");
            if (messageValidate(parseObject)) {
                return null;
            }

            return JSONObject.parseObject(certObject, CertObject.CertEnroll.class);
        }
        return null;
    }

    /**
     *
     * @description �û�֤��ע��
     *
     * @param serial     ֤�������к�
     * @param identity   �û�id
     * @return ע�����
     * @throws IOException 
     */
    public boolean certRevoke(String serial, String identity, String key) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_REVOKE);

        JSONObject requestParam = new JSONObject();
        requestParam.put("serial", serial);
        requestParam.put("identity", identity);

        CertService.ReqRevokeCert.Builder reqBuilder = CertService.ReqRevokeCert.newBuilder();
        reqBuilder.setIdentity(identity);
        reqBuilder.setSerial(serial);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return false;
            }

            return parseObject.getJSONObject("result").getBoolean("result");
        }
        return false;
    }

    /**
     *
     * @description ��ȡcrl
     *
     * @param identity   �û�id
     * @return crl
     * @throws IOException 
     */
    public byte[] certGetCRL(String identity, String key) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_GET_CRL);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqGetCRL.Builder reqBuilder = CertService.ReqGetCRL.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return null;
            }

            return parseObject.getJSONObject("result").getBytes("crl");
        }
        return null;
    }

    /**
     *
     * @description ��ȡ�û���Ϣ
     *
     * @param identity   �û�id
     * @return �û���Ϣ
     * @throws IOException 
     */
    public CertObject.UserInfo certGetUserInfo(String identity, String key) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_GET_USERINFO);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqGetUserInfo.Builder reqBuilder = CertService.ReqGetUserInfo.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return null;
            }

            String rekeyObject = parseObject.getString("result");
            if (messageValidate(parseObject)) {
                return null;
            }
            return JSONObject.parseObject(rekeyObject, CertObject.UserInfo.class);
        }
        return null;
    }

    /**
     *
     * @description ��ȡ֤����Ϣ
     *
     * @param serial   ֤�����к�
     * @return ֤����Ϣ
     * @throws IOException 
     */
    public CertObject.CertInfo certGetCertInfo(String serial, String key) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.CERT_GET_CERTINFO);

        JSONObject requestParam = new JSONObject();
        requestParam.put("sn", serial);

        CertService.ReqGetCertInfo.Builder reqBuilder = CertService.ReqGetCertInfo.newBuilder();
        reqBuilder.setSn(serial);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        try {
            byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
            requestParam.put("sign", sig);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(requestResult)) {
            JSONObject parseObject = JSONObject.parseObject(requestResult);
            if (messageValidate(parseObject)) {
                return null;
            }

            String rekeyObject = parseObject.getString("result");
            if (messageValidate(parseObject)) {
                return null;
            }
            return JSONObject.parseObject(rekeyObject, CertObject.CertInfo.class);
        }
        return null;
    }

    /**
     * @description ���ͽ���
     *
     * @param name string ע������ ���Ȳ��ܳ��� 128
     * @param url string �������͵� URL�����Ȳ��ܳ��� 1024��
     * @param encode string ���ݱ��뷽ʽ��json ���� proto
     * @param lastSequence int ���Ϳ�ʼ���к�
     * @param lastHeight int ���Ϳ�ʼ�߶�
     * @param lastBlockHash String ���Ϳ�ʼ���ϣ
     * @param type int ���͵��������ͣ�0:�������飻1:��������ͷ��Ϣ��2�������׻�ִ
     * @param contract map[string]bool ���ĵĺ�Լ���ƣ���type=2��ʱ����Ч�����硰coins=true��
     * @return
     * @throws IOException 
     */
    public BooleanResult addPushSubscribe(String name, String url, String encode, int lastSequence, int lastHeight, String lastBlockHash, int type, Map<String, Boolean> contract) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.ADD_PUSH_SUBSCRIBE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("name", name);
        requestParam.put("URL", url);
        requestParam.put("encode", encode);
        requestParam.put("lastSequence", lastSequence);
        requestParam.put("lastHeight", lastHeight);
        requestParam.put("lastBlockHash", lastBlockHash);
        requestParam.put("type", type);
        requestParam.put("contract", contract);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject resultJson = parseObject.getJSONObject("result");
            BooleanResult booleanResult = resultJson.toJavaObject(BooleanResult.class);
            return booleanResult;
        }
        return null;
    }

    /**
     * @description ��ȡ�����б� listPushes
     * @return �����б�
     * @throws IOException 
     */
    public ListPushesResult listPushes() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.LIST_PUSHES);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject jsonResult = parseObject.getJSONObject("result");
            ListPushesResult list = JSONObject.toJavaObject(jsonResult, ListPushesResult.class);
            return list;
        }
        return null;
    }

    /**
     * @description ��ȡĳ���ͷ����������кŵ�ֵ getPushSeqLastNum
     * @return ��ȡĳ���ͷ����������кŵ�ֵ
     * @throws IOException 
     */
    public Int64Result getPushSeqLastNum(String name) throws IOException {
        RpcRequest postData = getPostData(RpcMethod.GET_PUSH_SEQ_LAST_NUM);
        JSONObject requestParam = new JSONObject();
        requestParam.put("data", name);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            if (messageValidate(parseObject))
                return null;
            JSONObject jsonResult = parseObject.getJSONObject("result");
            Int64Result data = JSONObject.toJavaObject(jsonResult, Int64Result.class);
            return data;
        }
        return null;
    }
    
    /**
     * 
     * @description ��ȡ�汾��
     * @return �汾��
     * @throws IOException 
     *
     */
    public VersionResult getVersion() throws IOException {
        RpcRequest postData = getPostData(RpcMethod.VERSION);
        String result = HttpUtil.httpPost(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            try {
                JSONObject parseObject = JSONObject.parseObject(result);
                if (messageValidate(parseObject))
                    return null;
                JSONObject resultJson = parseObject.getJSONObject("result");
                VersionResult versionResult = resultJson.toJavaObject(VersionResult.class);
                return versionResult;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    
}
