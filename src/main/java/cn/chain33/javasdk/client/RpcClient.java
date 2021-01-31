package cn.chain33.javasdk.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.chain33.javasdk.model.cert.CertObject;
import cn.chain33.javasdk.model.exception.Chain33Exception;
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
 * 调用远程接口
 * 
 * @author logan 2018年5月16日
 */
public class RpcClient {

    private static Logger logger = LoggerFactory.getLogger(RpcClient.class);

    // 通过配置文件或者其他方式设置URL
    private String BASE_URL;

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
     * @description 发送交易
     * 
     * @param transactionJsonResult
     * @return
     */
    public String submitTransaction(RpcRequest transactionJsonResult) throws Exception {
        transactionJsonResult.setMethod(RpcMethod.SEND_TRANSACTION);
        String jsonString = JSONObject.toJSONString(transactionJsonResult);
        String httpPostResult = HttpUtil.httpPostBody(getUrl(), jsonString);

        JSONObject parseObject = JSONObject.parseObject(httpPostResult);
        messageValidate(parseObject);
        return parseObject.getString("result");
    }

    /**
     * @description 发送交易
     * 
     * @param data 签名后的交易
     * @return 交易hash
     */
    public String submitTransaction(String data) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", data);

        RpcRequest postData = getPostData(RpcMethod.SEND_TRANSACTION);
        postData.addJsonParams(jsonObject);
        String httpPostResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(httpPostResult);
        messageValidate(parseObject);
        return parseObject.getString("result");
    }

    /**
     * 
     * @description 查询节点是否同步
     * @return 同步结果
     *
     */
    public Boolean isSync() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.BLOCKCHAIN_IS_SYNC);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject jsonResult = JSONObject.parseObject(result);
        messageValidate(jsonResult);
        Boolean isSync = jsonResult.getBoolean("result");
        return isSync;
    }

    /**
     * @description 根据交易哈希查询交易信息
     * @param hash 交易hash
     * @return 交易信息
     */
    public QueryTransactionResult queryTransaction(String hash) throws Exception  {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.QUERY_TRANSACTION);

        postData.addJsonParams(jsonObject);

        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        QueryTransactionResult transactionResult = resultJson.toJavaObject(QueryTransactionResult.class);
        transactionResult.setBlocktime(new Date(transactionResult.getBlocktime().getTime() * 1000));
        return transactionResult;
    }
    
    /**
     * @description 根据哈希数组批量获取交易信息 GetTxByHashes
     * 
     * @param hashIdList 交易ID列表
     * @return 交易结果对象列表
     */
    public List<QueryTransactionResult> GetTxByHashes(List<String> hashIdList) throws Exception {
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
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
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

    /**
     * @description 根据哈希获取交易的字符串 GetHexTxByHash
     * 
     * @param hash 交易hash
     * @return 交易字符串
     */
    public String getHexTxByHash(String hash) throws Exception {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.GET_HEX_TX_BY_HASH);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        String txString = parseObject.getString("result");
        return txString;
    }

    /**
     * @description 获取区间区块 GetBlocks
     * 
     * @param start    区块开始高度
     * @param end      区块结束高度
     * @param isDetail 是否获取详情
     * 
     */
    public List<BlocksResult> getBlocks(Long start, Long end, boolean isDetail) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start);
        jsonObject.put("end", end);
        jsonObject.put("isDetail", isDetail);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCKS);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
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

    /**
     * @description 获取最新的区块头 GetLastHeader
     * @return 最新区块信息
     */
    public BlockResult getLastHeader() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_LAST_HEADER);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject jsonResult = parseObject.getJSONObject("result");
        BlockResult block = JSONObject.toJavaObject(jsonResult, BlockResult.class);
        Long blockTimeLong = jsonResult.getLong("blockTime");
        block.setBlockTime(new Date(blockTimeLong * 1000));
        return block;
    }

    /**
     * @description 获取区间区块头 GetHeaders 该接口用于获取指定高度区间的区块头部信息
     * 
     * @param start    开始区块高度
     * @param end      结束区块高度
     * @param isDetail 是否打印区块详细信息
     */
    public List<BlockResult> getHeaders(Long start, Long end, boolean isDetail) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start);
        jsonObject.put("end", end);
        jsonObject.put("isDetail", isDetail);
        RpcRequest postData = getPostData(RpcMethod.GET_HEADERS);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
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
    
    /**
     * 取平均出块时间
     * @return
     */
    public int getBlockAverageTime() throws Exception {
    	
    	// 创世块的时间从配置文件中读取， 所以过滤掉
    	List<BlockResult> blockResultList = getHeaders(1l, 1l, false);
    	BlockResult resultFirst = blockResultList.get(0);
    	
    	BlockResult resultLast = getLastHeader();
    	
    	long averageSecond = (resultLast.getBlockTime().getTime() - resultFirst.getBlockTime().getTime())/((resultLast.getHeight() -1) * 1000);
    	
    	return (int)averageSecond ;
    }

    /**
     * @description 获取某高度区块的 hash 值 GetBlockHash 该接口用于获取指定高度区间的区块头部信息
     * @param height 区块高度
     * @return 区块hash
     */
    public String getBlockHash(Long height) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("height", height);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_HASH);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        String hexString = parseObject.getJSONObject("result").getString("hash");
        return HexUtil.removeHexHeader(hexString);
    }

    /**
     * @description 获取区块的详细信息
     * 
     * @param hash 区块hash
     * @return 区块信息
     */
    public BlockOverViewResult getBlockOverview(String hash) throws Exception {
        if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
            hash = HexUtil.removeHexHeader(hash);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hash", hash);
        RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_DETAIL);
        postData.addJsonParams(jsonObject);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        BlockOverViewResult blockOverViewResult = JSONObject.toJavaObject(resultJson, BlockOverViewResult.class);
        return blockOverViewResult;
    }

    /**
     * @description 获取远程节点列表
     * @return 节点信息
     */
    public List<PeerResult> getPeerInfo() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_PEER_INFO);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
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

    private RpcRequest getPostData(RpcMethod method) {
        RpcRequest postJsonData = new RpcRequest();
        postJsonData.setMethod(method);
        return postJsonData;
    }

    private void messageValidate(JSONObject parseObject) throws Chain33Exception {
        if (parseObject != null && parseObject.containsKey("error")) {
            String error = parseObject.getString("error");
            if (StringUtil.isNotEmpty(error)) {
                logger.error("rpc error:" + error);
                throw new Chain33Exception(error);
            }
        }
    }
    
    /**
     * 
     * @description 根据哈希数组批量获取交易信息
     * @param hashIdList    交易ID列表，用逗号“,”分割
     * @return 交易信息列表
     */
    public List<QueryTransactionResult> getTxByHashes(String hashIdList) throws Exception {
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
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
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

        return new ArrayList<>();
    }

    /**
     * 
     * @description 查询钱包状态
     * @return 钱包状态
     *
     */
    public WalletStatusResult getWalletStatus() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_WALLET_STUATUS);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        WalletStatusResult WalletStatus = resultJson.toJavaObject(WalletStatusResult.class);
        return WalletStatus;
    }

    /**
     * @description 上锁 Lock
     * 
     * @return 结果
     */
    public BooleanResult lock() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.LOCK_WALLET);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        BooleanResult lockResult = resultJson.toJavaObject(BooleanResult.class);
        return lockResult;
    }

    /**
     * @description 解锁 Unlock
     * 
     * @param passwd    解锁密码
     * @param walletorticket true，只解锁ticket买票功能，false：解锁整个钱包。
     * @param timeout        解锁时间，默认 0，表示永远解锁；非 0 值，表示超时之后继续锁住钱包，单位：秒。
     * @return 结果
     */
    public BooleanResult unlock(String passwd, boolean walletorticket, int timeout) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.UNLOCK_WALLET);
        JSONObject requestParam = new JSONObject();
        requestParam.put("passwd", passwd);
        requestParam.put("walletorticket", walletorticket);
        requestParam.put("timeout", timeout);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        BooleanResult lockResult = resultJson.toJavaObject(BooleanResult.class);
        return lockResult;
    }

    /**
     * 
     * @description 在节点钱包中创建账户地址
     * @param label 标签
     * @return 账户信息
     *
     */
    public AccountResult newAccount(String label) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.NEW_ACCOUNT);
        JSONObject requestParam = new JSONObject();
        requestParam.put("label", label);
        postData.addJsonParams(requestParam);

        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        AccountResult newAccountResult = resultJson.toJavaObject(AccountResult.class);
        return newAccountResult;
    }
    

    /**
     * @description 生成随机的seed
     * 
     * @param lang lang=0:英语，lang=1:简体汉字
     * @return seed
     */
    public String seedGen(Integer lang) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GEN_SEED);
        JSONObject requestParam = new JSONObject();
        requestParam.put("lang", lang);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        String seed = resultJson.getString("seed");
        return seed;
    }

    /**
     * @description 保存seed并用密码加密
     * 
     * @param seed 种子要求16个单词或者汉字，参考genseed输出格式，需要空格隔开
     * @param passwd 加密密码，必须大于或等于8个字符的字母和数字组合
     * @return
     */
    public BooleanResult seedSave(String seed, String passwd) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.SAVE_SEED);
        JSONObject requestParam = new JSONObject();
        requestParam.put("seed", seed);
        requestParam.put("passwd", passwd);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        BooleanResult booleanResult = resultJson.toJavaObject(BooleanResult.class);
        return booleanResult;
    }

    /**
     * @description 通过密码获取seed
     * 
     * @param passwd 密码
     * @return  seed
     */
    public String seedGet(String passwd) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_SEED);
        JSONObject requestParam = new JSONObject();
        requestParam.put("passwd", passwd);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        String seed = resultJson.getString("seed");
        return seed;
    }

    /**
     * @description 将合约转换为地址
     * 
     * @param execername 例如user.p.xxchain.xxx
     * @return 合约地址
     */
    public String convertExectoAddr(String execername) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CONVERT_EXECER_TO_ADDRESS);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execname", execername);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        String address = parseObject.getString("result");
        return address;
    }

    /**
     * @description 设置地址标签
     * 
     * @param addr  例如 13TbfAPJRmekQxYVEyyGWgfvLwTa8DJW6U
     * @param label 例如 macAddrlabel
     * @return 结果
     */
    public AccountResult setlabel(String addr, String label) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.SET_LABEL);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addr", addr);
        requestParam.put("label", label);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        AccountResult accountResult = resultJson.toJavaObject(AccountResult.class);
        return accountResult;
    }

    /**
     * @description 获取账户列表 GetAccounts
     * 
     * @return 账号列表
     */
    public List<AccountResult> getAccountList() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_ACCOUNT_LIST);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(result);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        JSONArray jsonArray = resultJson.getJSONArray("wallets");
        List<AccountResult> accountList = jsonArray.toJavaList(AccountResult.class);
        return accountList;
    }

    /**
     * 创建EVM合约交易 CreateTransaction * 11.9 生成预创建token 的交易
     * 
     * @param execer     执行器名称，这里固定为evm
     * @param actionName 操作名称，这里固定为CreateCall
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
        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }
    
    /**
     * 调用管理合约（创建黑名单， 创建token-finisher）
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
     * 创建账号
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
     * 对账号进行授权
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
     * 增加共识节点
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
     * @description 生成预创建token的交易
     * 
     * @param name         token的全名，最大长度是128个字符。
     * @param symbol       token标记符，最大长度是16个字符，且必须为大写字符。
     * @param introduction token介绍，最大长度为1024个字节。
     * @param ownerAddr    token拥有者地址
     * @param total        发行总量,需要乘以10的8次方，比如要发行100个币，需要100*1e8
     * @param price        发行该token愿意承担的费用
     * @return 交易十六进制编码后的字符串
     */
    public String createRawTokenPreCreateTx(String name,String symbol,String introduction,String ownerAddr,long total,long price,Integer category) throws Exception {
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
        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }

    /**
     * @description 生成完成创建token的交易（未签名）
     * 
     * @param symbol:    token标记符，最大长度是16个字符，且必须为大写字符。
     * @param ownerAddr: token拥有者地址
     * @param fee:       交易的手续费
     * @return 交易十六进制编码后的字符串
     */
    public String createRawTokenFinishTx(long fee,String symbol,String ownerAddr) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_FINISH_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("fee", fee);
        requestParam.put("symbol", symbol);
        requestParam.put("owner", ownerAddr);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }

    /**
     * @description 构造交易
     * 
     * @param to:目标地址。
     * @param amount:发送金额，注意基础货币单位为10^8
     * @param fee:手续费，注意基础货币单位为10^8
     * @param note:备注。非必须
     * @param isToken:是否是token类型的转账 （非token转账这个不用填）
     * @param isWithdraw:是否为取款交易
     * @param tokenSymbol:token 的 symbol （非token转账这个不用填）
     * @param execName:TransferToExec（转到合约） 或 Withdraw（从合约中提款），如果要构造平行链上的转账，此参数置空
     * @return 备注：如果result 不为nil,则为构造后的交易16进制字符串编码。解码通过hex decode。
     */
    public String createRawTransaction(String to, long amount, long fee, String note, boolean isToken,
            boolean isWithdraw, String tokenSymbol, String execName) throws Exception {
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

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }

    /**
     * @description 构造交易(平行链上会用到)
     * 
     * @param txHex:   由上一步的createRawTx生成的交易再传入（比如，CreateRawTokenPreCreateTx：token预创建；CreateRawTokenFinishTx：token完成；CreateRawTransaction：转移token）
     * @param payAddr: 用于付费的地址，这个地址要在主链上存在，并且里面有比特元用于支付手续费。
     * @param Privkey： 对应于payAddr的私钥。如果payAddr已经导入到平行链，那么这个私钥可以不传（建议做法是在平行链上导入地址，保证私钥安全）
     * @param expire:  超时时间
     * @return hash
     */
    public String createRawTransaction(String txHex, String payAddr, String Privkey, String expire) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_RAW_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("txHex", txHex);
        requestParam.put("addr", payAddr);
        requestParam.put("Privkey", Privkey);
        requestParam.put("expire", expire);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
       messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }
    
    /**
     * @description 交易签名
     * 
     * @param addr 与key可以只输入其一
     * @param key 私钥
     * @param expire 过期时间可输入如"300ms"，"-1.5h"或者"2h45m"的字符串，有效时间单位为"ns", "us" (or "µs"), "ms","s", "m","h"
     * @param index 若是签名交易组，则为要签名的交易序号，从1开始，小于等于0则为签名组内全部交易
     * @param index 固定填写2(这里是一个交易组，第1笔none的交易已经用pay address签过名了，此处签index=2的交易)
     * @return txhex
     */
    public String signRawTx(String addr, String key, String txhex, String expire, int index) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.SIGN_RAW_TRANSACTION);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addr", addr);
        requestParam.put("key", key);
        requestParam.put("txhex", txhex);
        requestParam.put("expire", expire);
        requestParam.put("index", index);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }

    /**
     * @description 查询地址token余额
     * 
     * @param addresses 地址列表
     * @param execer    token 查询可用的余额 ，trade 查询正在交易合约里的token,如果是查询平行链上余额，则需要指定具体平行链的执行器execer,例如：user.p.xxx.token .
     * @param tokenSymbol   token符号名称
     * @return  账号余额列表
     */
    public List<AccountAccResult> getTokenBalance(List<String> addresses, String execer, String tokenSymbol) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_TOKEN_BALANCE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addresses", addresses);
        requestParam.put("execer", execer);
        requestParam.put("tokenSymbol", tokenSymbol);
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONArray resultArray = parseObject.getJSONArray("result");
        List<AccountAccResult> javaList = resultArray.toJavaList(AccountAccResult.class);
        return javaList;
    }

    /**
     * @description 查询主代币余额
     * 
     * @param addresses  地址列表
     * @param execer    coins
     * @return  余额列表
     */
    public List<AccountAccResult> getCoinsBalance(List<String> addresses, String execer) throws Exception {
        RpcRequest postJsonData = new RpcRequest();
        postJsonData.setMethod(RpcMethod.GET_BALANCE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addresses", addresses);
        requestParam.put("execer", execer);
        postJsonData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPostBody(getUrl(), postJsonData.toJsonString());

        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONArray resultArray = parseObject.getJSONArray("result");
        List<AccountAccResult> javaList = resultArray.toJavaList(AccountAccResult.class);
        return javaList;
    }

    /**
     * @description 导出私钥
     * 
     * @param addr 导出私钥的地址
     * @return 私钥
     */
    public String dumpPrivkey(String addr) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.DUMP_PRIVKEY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("data", addr);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultObj = parseObject.getJSONObject("result");
        String resultStr = resultObj.getString("data");
        return resultStr;
    }

    /**
     * @description 导入私钥
     * 
     * @param privateKey 私钥
     * @param label   地址label
     * @return 导入结果
     */
    public String importPrivatekey(String privateKey, String label) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.IMPORT_PRIVKEY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("privkey", privateKey);
        requestParam.put("label", label);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultObj = parseObject.getJSONObject("result");
        String resultStr = resultObj.getString("acc");
        return resultStr;
    }

    /**
     * @description 根据地址获取交易信息hash
     * 
     * @param flag:   0：addr 的所有交易；1：当 addr 为发送方时的交易；2：当 addr 为接收方时的交易。
     * @param height: 交易所在的block高度，-1：表示从最新的开始向后取；大于等于0的值，从具体的高度+具体index开始取。
     * @param index:  交易所在block中的索引，取值0--100000。
     * @return 交易列表
     */
    public List<TxResult> getTxByAddr(String addr, Integer flag, Integer count, Integer direction, Long height,
            Integer index) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_TX_BY_ADDR);
        JSONObject requestParam = new JSONObject();
        requestParam.put("addr", addr);
        requestParam.put("flag", flag);
        requestParam.put("count", count);
        requestParam.put("direction", direction);
        requestParam.put("height", height);
        requestParam.put("index", index);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultObj = parseObject.getJSONObject("result");
        JSONArray jsonArray = resultObj.getJSONArray("txInfos");
        List<TxResult> javaList = jsonArray.toJavaList(TxResult.class);
        return javaList;
    }

    /**
     * @description 查询所有预创建的token或创建成功的token
     * 
     * @param status 0:预创建 1:创建成功 的token
     * @return  token信息列表
     */
    public List<TokenResult> queryCreateTokens(Integer status,String execer) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "GetTokens");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("status", status);
        payloadJson.put("queryAll", true);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        JSONArray resultArray = resultJson.getJSONArray("tokens");
        List<TokenResult> javaList = resultArray.toJavaList(TokenResult.class);
        return javaList;
    }

    /**
     * @description 查询地址下的token/trace合约下的token资产
     * 
     * @param address:  查询的地址
     * @param payloadExecer:   token 或 trade
     * @return TokenBalanceResult
     */
    public List<TokenBalanceResult> queryAccountBalance(String address, String payloadExecer) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "token");
        requestParam.put("funcName", "GetAccountTokenAssets");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        payloadJson.put("execer", payloadExecer);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        JSONArray resultArray = resultJson.getJSONArray("tokenAssets");
        List<TokenBalanceResult> javaList = resultArray.toJavaList(TokenBalanceResult.class);
        return javaList;
    }
    
    /**
     * @description 查询合约消耗的GAS
     * 
     * @param address:  查询的地址
     * @param execer:   执行器名称
     * @return TokenBalanceResult
     */
    public String queryEVMGas(String execer, String code, String abi, String address) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "EstimateGas");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("to", address);
        payloadJson.put("code", code);
        payloadJson.put("abi", abi);
        payloadJson.put("caller", "");
        payloadJson.put("amount", 0);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String gas = parseObject.getJSONObject("result").getString("gas");
        return gas;
    }
    
    /**
     * @description 查询合约绑定的ABI信息
     * 
     * @param address:  查询的地址
     * @param execer:   执行器名称
     * @return TokenBalanceResult
     */
    public JSONArray queryEVMABIInfo(String address, String execer) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "QueryABI");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        JSONArray resultArray = resultJson.getJSONArray("abi");
        return resultArray;
    }
    
    /**
     * @description 查询合约ABI结果
     * 
     * @param address:  查询的地址
     * @param execer:   执行器名称
     * @return TokenBalanceResult
     */
    public JSONArray queryEVMABIResult(String address, String execer, String abiFunc) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("funcName", "Query");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("address", address);
        payloadJson.put("input", abiFunc);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        JSONArray resultArray = resultJson.getJSONArray("jsonData");
        return resultArray;
    }

    /**
     * @description 查询地址余额
     * 
     * @param addressList 地址
     * @param execer  coins
     * @return
     */
    public List<AccountAccResult> queryBalance(List<String> addressList, String execer) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_BALANCE);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", execer);
        requestParam.put("addresses", addressList);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONArray jsonArray = parseObject.getJSONArray("result");
        List<AccountAccResult> javaList = jsonArray.toJavaList(AccountAccResult.class);
        return javaList;
    }

    public String getUrl() {
        return BASE_URL;
    }

    /**
     * @description 发送签名后的交易
     * @param unsignTx 未签名的tx
     * @param sign     sign:用私钥对unsigntx签名好的数据
     * @param pubkey   私钥对应的公钥
     * @param signType 签名类型
     * @return hash
     */
    public String submitRawTransaction(String unsignTx, String sign, String pubkey, SignType signType) throws Exception{
        RpcRequest postData = getPostData(RpcMethod.SEND_RAW_TRANSACTION);
        JSONObject requestParam = new JSONObject();
        requestParam.put("unsignTx", unsignTx);
        requestParam.put("sign", sign);
        requestParam.put("pubkey", pubkey);
        requestParam.put("ty", signType.getType());
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }

    /**
     * @description token转账
     * 
     * @param from:        来源地址。
     * @param to:          发送到地址。
     * @param amount:      发送金额。
     * @param note:        备注。
     * @param isToken:     发送的是否是token。false 的情况下发送的bt
     * 
     * @param tokenSymbol: token标记符，最大长度是16个字符，且必须为大写字符。
     * @return
     */
    public String sendToAddress(String from, String to, Long amount, String note, boolean isToken, String tokenSymbol) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.SEND_TO_ADDRESS);
        JSONObject requestParam = new JSONObject();
        requestParam.put("from", from);
        requestParam.put("to", to);
        requestParam.put("amount", amount);
        requestParam.put("note", note);
        requestParam.put("isToken", isToken);
        requestParam.put("tokenSymbol", tokenSymbol);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String hash = parseObject.getJSONObject("result").getString("hash");
        return hash;
    }

    /**
     * 
     * @param userAddr 用户地址
     * @param reqStr   请求参数
     * @param signAddr sys_sign_addr 系统签名地址？
     * @return
     */
    public String processTxGroup(String userAddr, String reqStr, String signAddr) throws Exception {
        String response = HttpUtil.httpPostBody(getUrl(), reqStr);
        RpcResponse rep = parseResponse(response, reqStr);
        if (rep == null) {
            logger.error("构建交易组失败");
            return "";
        }
        String rawTxHex = String.valueOf(rep.getResult());

        // 构建代扣手续费交易
        String withholdTxHex = createNoBalanceTx(rawTxHex, signAddr);

        // 对代扣交易签名
        String signedTxHex = signRawTx(userAddr, null, withholdTxHex, "1h", 2);
        if ("".equals(signedTxHex)) {
            logger.error("交易签名失败");
            return "";
        }

        // 发送交易组
        String txHash = submitTransaction(signedTxHex);
        if ("".equals(txHash)) {
            logger.error("交易组发送交易失败");
            return "";
        }
        return txHash;
    }
    

    public List<DecodeRawTransaction> decodeRawTransaction(String rawTx) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.DECODE_RAW_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("txHex", rawTx);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultObj = parseObject.getJSONObject("result");
        JSONArray jsonArray = resultObj.getJSONArray("txs");
        List<DecodeRawTransaction> javaList = jsonArray.toJavaList(DecodeRawTransaction.class);
        return javaList;
    }


    /**
     * @descprition 在原有的交易基础上构建一个手续费代扣交易，需预先将payAddr对应的私钥导入到平行链
     * 
     * @param txHex   划转交易的16进制字符串
     * @param payAddr 代扣账户的地址
     * @return 包含原有划转交易与代扣交易的交易组16进制字符串
     */
    public final String createNoBalanceTx(String txHex, String payAddr) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CREATE_NO_BALANCE_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("txHex", txHex);
        requestParam.put("payAddr", payAddr);
        requestParam.put("expire", "1h");
        postData.addJsonParams(requestParam);
        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
     * @descprition 处理RPC返回结果字符串
     * @author lyz
     * @create 2018/11/19 18:20
     * @param response RPC请求返回结果字符串
     * @param reqParam 请求参数，主要是在出错的时候，显示到日志
     * @return RPC结果对象
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
        logger.error("RPC请求失败，错误信息：" + rep == null ? "" : rep.getError() + " , 请求参数：" + reqParam);
        return null;
    }
    
    
    /**
     * 
     * @description    创建撤销预创建token交易
     * @param symbol   积分symbol
     * @param owner    积分拥有者地址
     * @return
     *
     */
    public String CreateRawTokenRevokeTx(String symbol,String owner) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_RAW_TOKEN_REVOKE_TX);
        JSONObject requestParam = new JSONObject();
        requestParam.put("symbol", symbol);
        requestParam.put("owner", owner);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String result = parseObject.getString("result");
        return result;
    }
    
    
    /**
     * @description 查询存证信息
     * 
     * @param hash:   hash
     * @return TokenBalanceResult
     */
    public JSONObject queryStorage(String hash) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "storage");
        requestParam.put("funcName", "QueryStorage");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("txHash", hash);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        return resultJson;
    }
    
    /**
     * @description 根据AccountId查账户信息
     * 
     * @param accountId:   accountId
     * @return TokenBalanceResult
     */
    public JSONObject queryAccountById(String accountId) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "accountmanager");
        requestParam.put("funcName", "QueryAccountByID");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("accountID", accountId);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        return resultJson;
    }
    
    /**
     * @description 根据账户状态查账户信息
     * 
     * @param status:   status
     * @return TokenBalanceResult
     */
    public JSONObject queryAccountByStatus(String status) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.QUERY);
        JSONObject requestParam = new JSONObject();
        requestParam.put("execer", "accountmanager");
        requestParam.put("funcName", "QueryAccountsByStatus");
        JSONObject payloadJson = new JSONObject();
        payloadJson.put("status", status);
        requestParam.put("payload", payloadJson);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        JSONObject resultJson = parseObject.getJSONObject("result");
        return resultJson;
    }

    /**
     *
     * @description 发送重加秘钥分片给重加密节点
     *
     * @param pubOwner     数据共享者公钥
     * @param pubRecipient 数据接收者公钥
     * @param pubProofR    重加密随机公钥R
     * @param pubProofU    重加密随机公钥U
     * @param expire       超时时间
     * @param dhProof      身份证明
     * @param frag         重加密秘钥分片
     * @return true/false
     */
    public boolean sendKeyFragment(String pubOwner, String pubRecipient, String pubProofR, String pubProofU, int expire,
                                   String dhProof, KeyFrag frag) throws Exception {
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

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        return parseObject.getJSONObject("result").getBoolean("result");
    }

    /**
     *
     * @description 申请重加密
     *
     * @param pubOwner      数据共享者公钥
     * @param pubRecipient  数据接收者公钥
     * @return 重加密片段
     */
    public ReKeyFrag reencrypt(String pubOwner, String pubRecipient) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.PRE_RE_ENCRYPT);

        JSONObject requestParam = new JSONObject();
        requestParam.put("pubOwner", pubOwner);
        requestParam.put("pubRecipient", pubRecipient);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String rekeyObject = parseObject.getString("result");
        return JSONObject.parseObject(rekeyObject, ReKeyFrag.class);
    }

    /**
     *
     * @description 证书用户注册
     *
     * @param userName   用户名
     * @param identity   用户id
     * @param userPub    用户公钥
     * @param adminKey   管理员私钥
     * @return 注册结果
     */
    public boolean certUserRegister(String userName, String identity, String userPub, String adminKey) throws Exception {
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

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);

        return parseObject.getJSONObject("result").getBoolean("result");
    }

    /**
     *
     * @description 证书用户注销
     *
     * @param identity   用户id
     * @param adminKey   管理员私钥
     * @return 注销结果
     */
    public boolean certUserRevoke(String identity, String adminKey) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_USER_REVOKE);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqRevokeUser.Builder reqBuilder = CertService.ReqRevokeUser.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(adminKey));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);

        return parseObject.getJSONObject("result").getBoolean("result");
    }

    /**
     *
     * @description 用户证书申请
     *
     * @param identity   用户id
     * @param key        用户私钥
     * @return 注销结果
     */
    public CertObject.CertEnroll certEnroll(String identity, String key) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_ENROLL);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqEnroll.Builder reqBuilder = CertService.ReqEnroll.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String certObject = parseObject.getString("result");
        return JSONObject.parseObject(certObject, CertObject.CertEnroll.class);
    }

    /**
     *
     * @description 用户证书重新申请，用于用户证书被注销后
     *
     * @param identity   用户id
     * @param adminKey   管理员私钥
     * @return 注销结果
     */
    public CertObject.CertEnroll certReEnroll(String identity, String adminKey) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_REENROLL);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqEnroll.Builder reqBuilder = CertService.ReqEnroll.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(adminKey));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);
        String certObject = parseObject.getString("result");
        return JSONObject.parseObject(certObject, CertObject.CertEnroll.class);
    }

    /**
     *
     * @description 用户证书注销
     *
     * @param serial     证书序序列号
     * @param identity   用户id
     * @return 注销结果
     */
    public boolean certRevoke(String serial, String identity, String key) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_REVOKE);

        JSONObject requestParam = new JSONObject();
        requestParam.put("serial", serial);
        requestParam.put("identity", identity);

        CertService.ReqRevokeCert.Builder reqBuilder = CertService.ReqRevokeCert.newBuilder();
        reqBuilder.setIdentity(identity);
        reqBuilder.setSerial(serial);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);

        return parseObject.getJSONObject("result").getBoolean("result");
    }

    /**
     *
     * @description 获取crl
     *
     * @param identity   用户id
     * @return crl
     */
    public byte[] certGetCRL(String identity, String key) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_GET_CRL);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqGetCRL.Builder reqBuilder = CertService.ReqGetCRL.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);

        return parseObject.getJSONObject("result").getBytes("crl");
    }

    /**
     *
     * @description 获取用户信息
     *
     * @param identity   用户id
     * @return 用户信息
     */
    public CertObject.UserInfo certGetUserInfo(String identity, String key) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_GET_USERINFO);

        JSONObject requestParam = new JSONObject();
        requestParam.put("identity", identity);

        CertService.ReqGetUserInfo.Builder reqBuilder = CertService.ReqGetUserInfo.newBuilder();
        reqBuilder.setIdentity(identity);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);

        String rekeyObject = parseObject.getString("result");
        return JSONObject.parseObject(rekeyObject, CertObject.UserInfo.class);
    }

    /**
     *
     * @description 获取证书信息
     *
     * @param serial   证书序列号
     * @return 证书信息
     */
    public CertObject.CertInfo certGetCertInfo(String serial, String key) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.CERT_GET_CERTINFO);

        JSONObject requestParam = new JSONObject();
        requestParam.put("sn", serial);

        CertService.ReqGetCertInfo.Builder reqBuilder = CertService.ReqGetCertInfo.newBuilder();
        reqBuilder.setSn(serial);
        byte[] reqBytes = reqBuilder.build().toByteArray();
        SM2KeyPair sm2Key = SM2Util.fromPrivateKey(HexUtil.fromHexString(key));
        byte[] sig = SM2Util.sign(reqBytes, null, sm2Key);
        requestParam.put("sign", sig);
        postData.addJsonParams(requestParam);

        String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        JSONObject parseObject = JSONObject.parseObject(requestResult);
        messageValidate(parseObject);

        String rekeyObject = parseObject.getString("result");
        return JSONObject.parseObject(rekeyObject, CertObject.CertInfo.class);
    }

    /**
     * @description 发送交易
     *
     * @param name string 注册名称 长度不能超过 128
     * @param url string 接受推送的 URL，长度不能超过 1024；
     * @param encode string 数据编码方式；json 或者 proto
     * @param lastSequence int 推送开始序列号
     * @param lastHeight int 推送开始高度
     * @param lastBlockHash String 推送开始块哈希
     * @param type int 推送的数据类型；0:代表区块；1:代表区块头信息；2：代表交易回执
     * @param contract map[string]bool 订阅的合约名称，当type=2的时候起效，比如“coins=true”
     * @return
     */
    public BooleanResult addPushSubscribe(String name, String url, String encode, int lastSequence, int lastHeight, String lastBlockHash, int type, Map<String, Boolean> contract) throws Exception {
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
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            messageValidate(parseObject);
            JSONObject resultJson = parseObject.getJSONObject("result");
            BooleanResult booleanResult = resultJson.toJavaObject(BooleanResult.class);
            return booleanResult;
        }
        return null;
    }

    /**
     * @description 获取推送列表 listPushes
     * @return 推送列表
     */
    public ListPushesResult listPushes() throws Exception {
        RpcRequest postData = getPostData(RpcMethod.LIST_PUSHES);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            messageValidate(parseObject);
            JSONObject jsonResult = parseObject.getJSONObject("result");
            ListPushesResult list = JSONObject.toJavaObject(jsonResult, ListPushesResult.class);
            return list;
        }
        return null;
    }

    /**
     * @description 获取某推送服务最新序列号的值 getPushSeqLastNum
     * @return 获取某推送服务最新序列号的值
     */
    public Int64Result getPushSeqLastNum(String name) throws Exception {
        RpcRequest postData = getPostData(RpcMethod.GET_PUSH_SEQ_LAST_NUM);
        JSONObject requestParam = new JSONObject();
        requestParam.put("data", name);
        postData.addJsonParams(requestParam);
        String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
        if (StringUtil.isNotEmpty(result)) {
            JSONObject parseObject = JSONObject.parseObject(result);
            messageValidate(parseObject);
            JSONObject jsonResult = parseObject.getJSONObject("result");
            Int64Result data = JSONObject.toJavaObject(jsonResult, Int64Result.class);
            return data;
        }
        return null;
    }
}
