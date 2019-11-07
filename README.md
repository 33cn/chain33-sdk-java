# chain33-sdk-java
调用chain33 rpc接口的java客户端。

# 使用
1.添加依赖
``` java
<dependency>
	<groupId>cn.chain33</groupId>
	<artifactId>sdk-java</artifactId>
	<version>1.0.7</version>
</dependency>
```
如果直接使用jar包,需要自己导入依赖的包

``` xml
        <dependency>
			<groupId>com.google.protobuf</groupId>
			<artifactId>protobuf-java</artifactId>
			<version>3.5.1</version>
		</dependency>
		
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.47</version>
		</dependency>

		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpclient</artifactId>
			<version>4.5.3</version>
		</dependency>
```
2.使用RpcClient调用接口

``` java
初始化,设置Ip端口
RpcClient client = new RpcClient("x.x.x.x",8801);
调用接口
client.接口();
```
# 接口列表
下面罗列的是java sdk提供的接口，具体调用可以参考测试代码里的例子

 - client.isSync() 	查询节点同步状态
 - client.submitTransaction(txhash)	提交交易
 - client.queryTransaction(txhash)   根据哈希查询交易信息
 - client.getTxByHashes(hashlist)     根据哈希数组批量获取交易信息
 - client.getHexTxByHash(hash)     根据哈希获取交易的hex字符串
 - client.getBlocks(start,end,isdetail)    获取区间区块
 - client.getLastHeader()    获取最新的区块头
 - client.getHeaders(start,end,isdetail)    获取区间区块头
 - client.getBlockHash(height)     获取区块的 hash 值
 - client.getBlockOverview(hash)    获取区块的详细信息
 - client.getPeerInfo()    获取远程节点列表 
 - client.getTxByHashes()    根据哈希数组批量获取交易信息
 - client.getWalletStatus()    获取钱包状态
 - client.lock()    钱包上锁
 - client.unlock(passwd,walletorticket,timeout)   钱包解锁
 - client.newAccount(label)    创建账户
 - client.seedGen()    生成随机的seed
 - client.seedSave(seed,password)    保存seed并用密码加密
 - client.seedGet(passwd)    通过钱包密码获取钱包的seed原文
 - client.setlabel(addr,label)    设置标签
 - client.getAccountList()     获取账户列表
 - client.createRawTokenPreCreateTx(参数)    生成预创建token的交易
 - client.createRawTokenFinishTx(参数)   生成完成创建token 的交易（未签名）
 - client.createRawTransaction(参数)    构造交易
 - client.signRawTx(参数)    交易签名
 - client.getTokenBalance(参数)    查询地址token余额
 - client.getTxByAddr(参数)    根据地址获取交易信息
 - client.queryCreateTokens(参数)    查询token列表
 - client.queryAccountBalance(参数)    查询地址下的token/trace合约下的token资产
 - client.queryBtyBalance(参数)    查询bty余额
 - client.submitRawTransaction(参数)    发送签名后的交易
 - client.sendToAddress(参数)    交易
 - client.createTransaction(参数)    创建合约交易
 - TransactionUtil.createTransferPayLoad(参数)	本地构造转账交易payload
 - TransactionUtil.createTransferTx(参数,payload)	本地构造转账交易体
 - 创建好的交易体使用submitTransaction提交交易
 - TransactionUtil.generatorPrivateKeyString()	创建私钥
 - TransactionUtil.getHexPubKeyFromPrivKey(私钥)	根据私钥获得公钥
 - TransactionUtil.genAddress(公钥)	根据公钥获取地址
 - TransactionUtil.validAddress(地址)	校验地址是否正确

