package cn.chain33.javasdk.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.chain33.javasdk.model.RpcRequest;
import cn.chain33.javasdk.model.enums.RpcMethod;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.AccountResult;
import cn.chain33.javasdk.model.rpcresult.BlockOverViewResult;
import cn.chain33.javasdk.model.rpcresult.BlockResult;
import cn.chain33.javasdk.model.rpcresult.BlocksResult;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;
import cn.chain33.javasdk.model.rpcresult.PeerResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.model.rpcresult.TokenBalanceResult;
import cn.chain33.javasdk.model.rpcresult.TokenResult;
import cn.chain33.javasdk.model.rpcresult.TxResult;
import cn.chain33.javasdk.model.rpcresult.WalletStatusResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.HttpUtil;
import cn.chain33.javasdk.utils.StringUtil;

public class RpcClient {
	
	private String chainApiUrl;
	
	private String DEFAULT_SCHEMA = "http";
	
	public RpcClient() {
	}
	
	public RpcClient(String url) {
		this.chainApiUrl = url; 
	}
	
	public RpcClient(String host,Integer port) {
		this.chainApiUrl = DEFAULT_SCHEMA + "://" + host + ":" + port;
	}
	
	public RpcClient(String schema,String host,Integer port) {
		this.chainApiUrl = schema + "://" + host + ":" + port;
	}
	
	public void setBASE_URL(String bASE_URL) {
		chainApiUrl = bASE_URL;
	}
	
	public void setUrl(String host,Integer port) {
		this.chainApiUrl = DEFAULT_SCHEMA +"://" + host + ":" + port;
	}
	
	public void setUrl(String url) {
		this.chainApiUrl = url;
	}
	
	/**
	 *  SendTransaction
	 * 
	 * @param transactionJsonResult
	 * @return
	 * @throws Exception 
	 */
	public String submitTransaction(RpcRequest transactionJsonResult) throws Exception {
		transactionJsonResult.setMethod(RpcMethod.SEND_TRANSACTION);
		String jsonString = JSONObject.toJSONString(transactionJsonResult);
		String httpPostResult = HttpUtil.httpPostBody(getUrl(), jsonString);
		if (StringUtil.isNotEmpty(httpPostResult)) {
			JSONObject parseObject = JSONObject.parseObject(httpPostResult);
			if (messageValidate(parseObject))
				return null;
			return parseObject.getString("result");
		}
		return null;
	}
	
	/**
	 *  SendTransaction
	 * 
	 * @param data hex transation
	 * @return
	 * @throws Exception 
	 */
	public String submitTransaction(String data) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", data);
		
		RpcRequest postData = getPostData(RpcMethod.SEND_TRANSACTION);
		postData.addJsonParams(jsonObject);
		String httpPostResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(httpPostResult)) {
			JSONObject parseObject = JSONObject.parseObject(httpPostResult);
			if (messageValidate(parseObject))
				return null;
			return parseObject.getString("result");
		}
		return null;
	}
	
	public Boolean isSync() {
		RpcRequest postData = getPostData(RpcMethod.BLOCKCHAIN_IS_SYNC);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 * query transaction detail
	 * @param hash
	 * @return
	 * @throws Exception 
	 */
	public QueryTransactionResult queryTransaction(String hash) throws Exception{
		if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
			hash = HexUtil.removeHexHeader(hash);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hash", hash);
		RpcRequest postData = getPostData(RpcMethod.QUERY_TRANSACTION);

		postData.addJsonParams(jsonObject);

		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		System.out.println(result);
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
	 * GetTxByHashes
	 * @param hashIdList
	 * 
	 * @return transaction list
	 */
	public List<QueryTransactionResult> getTxByHashes(List<String> hashIdList)throws Exception {
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
	 * GetHexTxByHash
	 * @param hash hash
	 *            
	 * @return 
	 */
	public String getHexTxByHash(String hash) throws Exception{
		if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
			hash = HexUtil.removeHexHeader(hash);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hash", hash);
		RpcRequest postData = getPostData(RpcMethod.GET_HEX_TX_BY_HASH);
		postData.addJsonParams(jsonObject);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 *  GetBlocks
	 * 
	 * @param start
	 * @param end 
	 * @param isDetail
	 * 
	 */
	public List<BlocksResult> getBlocks(Long start, Long end, boolean isDetail) throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("start", start);
		jsonObject.put("end", end);
		jsonObject.put("isDetail", isDetail);
		RpcRequest postData = getPostData(RpcMethod.GET_BLOCKS);
		postData.addJsonParams(jsonObject);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 * GetLastHeader
	 * 
	 * @return blockresult
	 */
	public BlockResult getLastHeader() throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_LAST_HEADER);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 *  GetHeaders
	 * 
	 * @param start
	 * @param end
	 * @param isDetail
	 * @throws Exception 
	 */
	public List<BlockResult> getHeaders(Long start, Long end, boolean isDetail) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("start", start);
		jsonObject.put("end", end);
		jsonObject.put("isDetail", isDetail);
		RpcRequest postData = getPostData(RpcMethod.GET_HEADERS);
		postData.addJsonParams(jsonObject);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 *  GetBlockHash 
	 * 
	 * @param height
	 * @return block hash
	 */
	public String getBlockHash(Long height) throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("height", height);
		RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_HASH);
		postData.addJsonParams(jsonObject);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 * getBlockOverview
	 * 
	 * @param hash
	 * @return block detail
	 */
	public BlockOverViewResult getBlockOverview(String hash) throws Exception{
		if (StringUtil.isNotEmpty(hash) && hash.startsWith("0x")) {
			hash = HexUtil.removeHexHeader(hash);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hash", hash);
		RpcRequest postData = getPostData(RpcMethod.GET_BLOCK_DETAIL);
		postData.addJsonParams(jsonObject);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	 * get block peer info
	 * @return
	 */
	public List<PeerResult> getPeerInfo() throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_PEER_INFO);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
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
	

	private RpcRequest getPostData(RpcMethod method) {
		RpcRequest postJsonData = new RpcRequest();
		postJsonData.setMethod(method);
		return postJsonData;
	}

	private Boolean messageValidate(JSONObject parseObject)throws Exception {
		if (parseObject != null && parseObject.containsKey("error")) {
			String error = parseObject.getString("error");
			if (StringUtil.isNotEmpty(error)) {
				throw new Exception("request error:"+error);
			} else {
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<QueryTransactionResult> getTxByHashes(String hashIdList) throws Exception{
		if (StringUtil.isEmpty(hashIdList)) {
			return Collections.EMPTY_LIST;
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
					if(txJson == null) {
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
	 * get wallet status
	 * 
	 */
	public WalletStatusResult getWalletStatus() throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_WALLET_STUATUS);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			WalletStatusResult WalletStatus = resultJson.toJavaObject(WalletStatusResult.class);
			return WalletStatus;
		}
		return null;
	}
	
	/**
	 * lock wallet
	 * @return
	 */
	public BooleanResult lock() throws Exception{
		RpcRequest postData = getPostData(RpcMethod.LOCK_WALLET);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			BooleanResult lockResult = resultJson.toJavaObject(BooleanResult.class);
			return lockResult;
		}
		return null;
	}
	
	/**
	 * Unlock wallet
	 * @param passwd
	 * @param walletorticket  true，只解锁ticket买票功能，false：解锁整个钱包。
	 * @param timeout 解锁时间，默认 0，表示永远解锁；非 0 值，表示超时之后继续锁住钱包，单位：秒。
	 */
	public BooleanResult unlock(String passwd,boolean walletorticket,int timeout) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.UNLOCK_WALLET);
		JSONObject requestParam = new JSONObject();
		requestParam.put("passwd", passwd);
		requestParam.put("walletorticket", walletorticket);
		requestParam.put("timeout", timeout);
		postData.addJsonParams(requestParam);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			BooleanResult lockResult = resultJson.toJavaObject(BooleanResult.class);
			return lockResult;
		}
		return null;
	}
	
	/**
	 * create account
	 * @param label
	 */
	public AccountResult newAccount(String label) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.NEW_ACCOUNT);
		JSONObject requestParam = new JSONObject();
		requestParam.put("label", label);
		postData.addJsonParams(requestParam);
		
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			AccountResult newAccountResult = resultJson.toJavaObject(AccountResult.class);
			return newAccountResult;
		}
		return null;
	}
	
	/**
	 * generate seed
	 * 
	 * @param lang lang=0:英语，lang=1:简体汉字
	 * @return seedStr
	 */
	public String seedGen(Integer lang) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GEN_SEED);
		JSONObject requestParam = new JSONObject();
		requestParam.put("lang", lang);
		postData.addJsonParams(requestParam);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			String seed = resultJson.getString("seed");
			return seed;
		}
		return null;
	}
	
	/**
	 * save seed
	 * @param seed
	 * @param passwd
	 * @return
	 */
	public BooleanResult seedSave(String seed,String passwd) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.SAVE_SEED);
		JSONObject requestParam = new JSONObject();
		requestParam.put("seed", seed);
		requestParam.put("passwd", passwd);
		postData.addJsonParams(requestParam);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			BooleanResult booleanResult = resultJson.toJavaObject(BooleanResult.class);
			return booleanResult;
		}
		return null;
	}
	
	/**
	 * get seed
	 * @param passwd
	 * @return
	 */
	public String seedGet(String passwd) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_SEED);
		JSONObject requestParam = new JSONObject();
		requestParam.put("passwd", passwd);
		postData.addJsonParams(requestParam);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			String seed = resultJson.getString("seed");
			return seed;
		}
		return null;
	}
	
	/**
	 *  set Labl
	 * @param addr example:13TbfAPJRmekQxYVEyyGWgfvLwTa8DJW6U
	 * @param label example:macAddrlabel
	 * @return
	 */
	public AccountResult setlabel(String addr,String label) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.SET_LABEL);
		JSONObject requestParam = new JSONObject();
		requestParam.put("addr", addr);
		requestParam.put("label", label);
		postData.addJsonParams(requestParam);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			AccountResult accountResult = resultJson.toJavaObject(AccountResult.class);
			return accountResult;
		}
		return null;
	}
	
	/**
	 * get wallet account
	 * @param testAddr example:13TbfAPJRmekQxYVEyyGWgfvLwTa8DJW6U
	 * @param label example: macAddrlabel
	 * @return
	 */
	public List<AccountResult> getAccountList() throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_ACCOUNT_LIST);
		String result = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(result)) {
			JSONObject parseObject = JSONObject.parseObject(result);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			JSONArray jsonArray = resultJson.getJSONArray("wallets");
			List<AccountResult> accountList = jsonArray.toJavaList(AccountResult.class);
			return accountList;
		}
		return null;
	}
	
	/**
	 * 创建EVM合约交易 CreateTransaction
	 * 
	 * @param execer	执行器名称，这里固定为evm
	 * @param actionName	操作名称，这里固定为CreateCall
	 * @param payload	https://chain.33.cn/document/108#1.1%20%E5%88%9B%E5%BB%BAEVM%E5%90%88%E7%BA%A6%E4%BA%A4%E6%98%93%20CreateTransaction
	 * @return
	 * @throws Exception 
	 */
	public String createTransaction(String execer,String actionName,JSONObject payload) throws Exception {
		RpcRequest postData = getPostData(RpcMethod.CREATE_TRASACTION);
		JSONObject requestParam = new JSONObject();
		requestParam.put("execer", execer);
		requestParam.put("actionName", actionName);
		requestParam.put("payload", payload);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 *  生成预创建token 的交易
	 * 
	 * @param name 			token的全名，最大长度是128个字符。
	 * @param symbol		token标记符，最大长度是16个字符，且必须为大写字符。
	 * @param introduction	token介绍，最大长度为1024个字节。
	 * @param ownerAddr		token拥有者地址
	 * @param total			发行总量,需要乘以10的8次方，比如要发行100个币，需要100*1e8
	 * @param price			发行该token愿意承担的费用	
	 * @param fee			交易的手续费
	 * @return 交易十六进制编码后的字符串
	 */
	public String createRawTokenPreCreateTx(String name,String symbol,String introduction,String ownerAddr,long total,long price,long fee) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_PRE_CREATE_TX);
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", name);
		requestParam.put("symbol", symbol);
		requestParam.put("introduction", introduction);
		requestParam.put("ownerAddr", ownerAddr);
		requestParam.put("total", total);
		requestParam.put("price", price);
		requestParam.put("fee", fee);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 *  生成完成创建token 的交易（未签名）
	 * 
	 * @param symbol: token标记符，最大长度是16个字符，且必须为大写字符。
	 * @param ownerAddr: token拥有者地址
	 * @param fee: 交易的手续费
	 * @return 交易十六进制编码后的字符串
	 */
	public String createRawTokenFinishTx(long fee,String symbol,String ownerAddr) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_FINISH_TX);
		JSONObject requestParam = new JSONObject();
		requestParam.put("fee", fee);
		requestParam.put("symbol", symbol);
		requestParam.put("ownerAddr", ownerAddr);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 * 生成完成创建token 的交易（未签名）
	 * 
	 * @param to: 发送到地址。
	 * @param amount: 发送金额。
	 * @param fee 手续费
	 * @param note: 备注。
	 * @param isToken: 是否是token类型的转账 （非token转账这个不用填）
	 * @param isWithdraw：是否为取款交易
	 * @param tokenSymbol: token 的 symbol （非token转账这个不用填）
	 * @param execName：暂时不传，传coins会走到合约那边	合约名称（"none", "coins", "hashlock", "retrieve", "ticket", "token", "trade"等等） 
	 * @return 备注：如果result 不为nil,则为构造后的交易16进制字符串编码。解码通过hex decode。 
	 */
	public String createRawTransaction(String to,long amount,long fee,String note,boolean isToken,boolean isWithdraw,String tokenSymbol,String execName) throws Exception{
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
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 * 构造交易
	 * @param txHex
	 * @param txHex: 由上一步的createRawTx生成的交易再传入（比如，CreateRawTokenPreCreateTx：token预创建；CreateRawTokenFinishTx：token完成；CreateRawTransaction：转移token）
	 * @param payAddr:  用于付费的地址，这个地址要在主链上存在，并且里面有比特元用于支付手续费。
	 * @param Privkey： 对应于payAddr的私钥。如果payAddr已经导入到平行链，那么这个私钥可以不传（建议做法是在平行链上导入地址，保证私钥安全）
	 * @param Expire: 超时时间
	 * @return
	 */
	public String createRawTransaction(String txHex,String payAddr,String Privkey,String expire) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.TOKEN_CREATE_RAW_TX);
		JSONObject requestParam = new JSONObject();
		requestParam.put("txHex", txHex);
		requestParam.put("addr", payAddr);
		requestParam.put("Privkey", Privkey);
		requestParam.put("expire", expire);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 * 交易签名
	 * 
	 * @param addr与key可以只输入其一
	 * @param expire：过期时间可输入如"300ms"，"-1.5h"或者"2h45m"的字符串，有效时间单位为"ns", "us" (or "µs"), "ms", "s", "m", "h"。
	 * @param index: 若是签名交易组，则为要签名的交易序号，从1开始，小于等于0则为签名组内全部交易
	 * @param txHex： 上一步CreateNoBalanceTransaction生成的tx
	 * @param index： 固定填写2(这里是一个交易组，第1笔none的交易已经用pay address签过名了，此处签index=2的交易)
	 * @return txhex
	 */
	public String signRawTx(String addr,String key,String txhex,String expire,int index) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.SIGN_RAW_TRANSACTION);
		JSONObject requestParam = new JSONObject();
		requestParam.put("addr", addr);
		requestParam.put("key", key);  
		requestParam.put("txhex", txhex);
		requestParam.put("expire", expire);
		requestParam.put("index", index);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 * 查询地址token余额
	 * 
	 * @param execer: token 查询可用的余额 ，trade 查询正在交易合约里的token
	 */
	public List<AccountAccResult> getTokenBalance(List<String> addresses,String execer,String tokenSymbol) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_TOKEN_BALANCE);
		JSONObject requestParam = new JSONObject();
		requestParam.put("addresses", addresses);
		requestParam.put("execer", execer);
		requestParam.put("tokenSymbol", tokenSymbol);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			JSONArray resultArray = parseObject.getJSONArray("result");
			List<AccountAccResult> javaList = resultArray.toJavaList(AccountAccResult.class);
			return javaList;
		}
		return null;
	}
	
	
	/**
	 * 根据地址获取交易信息 GetTxByAddr
	 * 
	 * @param flag: 0：addr 的所有交易；1：当 addr 为发送方时的交易；2：当 addr 为接收方时的交易。
	 * @param height: 交易所在的block高度，-1：表示从最新的开始向后取；大于等于0的值，从具体的高度+具体index开始取。
	 * @param index: 交易所在block中的索引，取值0--100000。
	 * @return 
	 */
	public List<TxResult> getTxByAddr(String addr,Integer flag,Integer count,Integer direction,Long height,Integer index) throws Exception{
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
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			JSONObject resultObj = parseObject.getJSONObject("result");
			JSONArray jsonArray = resultObj.getJSONArray("txInfos");
			List<TxResult> javaList = jsonArray.toJavaList(TxResult.class);
			return javaList;
		}
		return null;
	}
	
	/**
	 * 查询token列表
	 * 
	 * @param status 0:预创建  1:创建成功 的token
	 * @return
	 */
	public List<TokenResult> queryCreateTokens(Integer status) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.QUERY);
		JSONObject requestParam = new JSONObject();
		requestParam.put("execer", "token");
		requestParam.put("funcName", "GetTokens");
		JSONObject payloadJson = new JSONObject();
		payloadJson.put("status", status);
		payloadJson.put("queryAll", true);
		requestParam.put("payload", payloadJson);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			JSONArray resultArray = resultJson.getJSONArray("tokens");
			List<TokenResult> javaList = resultArray.toJavaList(TokenResult.class);
			return javaList;
		}
		return null;
	}
	
	/**
	 * queryAccountBalance
	 * 
	 * @param execer: token
	 * @param funcName: GetAccountTokenAssets
	 * @param address: 查询的地址
	 * @param execer: token 或 trade
	 * @return
	 */
	public List<TokenBalanceResult> queryAccountBalance(String address,String payloadExecer) throws Exception{
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
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			JSONObject resultJson = parseObject.getJSONObject("result");
			JSONArray resultArray = resultJson.getJSONArray("tokenAssets");
			List<TokenBalanceResult> javaList = resultArray.toJavaList(TokenBalanceResult.class);
			return javaList;
		}
		return null;
	}
	
	
	/**
	 * 
	 * 
	 * @param address 地址
	 * @param execer coins
	 * @return
	 */
	public List<AccountAccResult> queryBtyBalance(List<String> addressList,String execer) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.GET_ACCOUNT_BALANCE);
		JSONObject requestParam = new JSONObject();
		requestParam.put("execer", execer);
		requestParam.put("addresses", addressList);
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			JSONArray jsonArray = parseObject.getJSONArray("result");
			List<AccountAccResult> javaList = jsonArray.toJavaList(AccountAccResult.class);
			return javaList;
		}
		return null;
	}
	
	public String getUrl() {
		return chainApiUrl;
	}
	
	/**
	 * submitRawTransaction 发送签名后的交易
	 * 
	 * @param unsignTx	未签名的tx
	 * @param sign		sign:用私钥对unsigntx签名好的数据
	 * @param pubkey	私钥对应的公钥
	 * @param signType	default SECP256K1
	 * @return
	 */
	public String submitRawTransaction(String unsignTx, String sign, String pubkey,SignType signType) throws Exception{
		RpcRequest postData = getPostData(RpcMethod.SEND_RAW_TRANSACTION);
		JSONObject requestParam = new JSONObject();
		requestParam.put("unsignTx", unsignTx);
		requestParam.put("sign", sign);
		requestParam.put("pubkey", pubkey);
		requestParam.put("ty", signType.getType());
		postData.addJsonParams(requestParam);
		String requestResult = HttpUtil.httpPostBody(getUrl(), postData.toJsonString());
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String result = parseObject.getString("result");
			return result;
		}
		return null;
	}
	
	/**
	 * sendToAddress 交易
	 * 
	 * @param from: 来源地址。
	 * @param to: 发送到地址。
	 * @param amount: 发送金额。
	 * @param note: 备注。
	 * @param isToken: 发送的是否是token。false 的情况下发送的bty
	 * @param tokenSymbol: token标记符，最大长度是16个字符，且必须为大写字符。
	 * @return
	 */
	public String sendToAddress(String from,String to,Long amount,String note,boolean isToken,String tokenSymbol) throws Exception{
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
		if (StringUtil.isNotEmpty(requestResult)) {
			JSONObject parseObject = JSONObject.parseObject(requestResult);
			if (messageValidate(parseObject)) return null;
			String hash = parseObject.getJSONObject("result").getString("hash");
			return hash;
		}
		return null;
	}
	
}
