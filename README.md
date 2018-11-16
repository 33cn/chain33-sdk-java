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

 - isSync() 查询节点同步状态
 - submitTransaction(txhash)    提交交易
 - queryTransaction(txhash)     根据哈希查询交易信息
 - getTxByHashes(hashlist)     根据哈希数组批量获取交易信息
 - getHexTxByHash(hash)     根据哈希获取交易的hex字符串
 - getBlocks(start,end,isdetail)    获取区间区块
 - getLastHeader()    获取最新的区块头
 - getHeaders(start,end,isdetail)    获取区间区块头
 - getBlockHash(height)     获取区块的 hash 值
 - getBlockOverview(hash)    获取区块的详细信息
 - getPeerInfo()    获取远程节点列表 
 - getTxByHashes()    根据哈希数组批量获取交易信息
 - getWalletStatus()    获取钱包状态
 - lock()    钱包上锁
 - unlock(passwd,walletorticket,timeout)   钱包解锁
 - newAccount(label)    创建账户
 - seedGen()    生成随机的seed
 - seedSave(seed,password)    保存seed并用密码加密
 - seedGet(passwd)    通过钱包密码获取钱包的seed原文
 - setlabel(addr,label)    设置标签
 - getAccountList()     获取账户列表
 - createRawTokenPreCreateTx(参数)    生成预创建token的交易
 - createRawTokenFinishTx(参数)   生成完成创建token 的交易（未签名）
 - createRawTransaction(参数)    构造交易
 - signRawTx(参数)    交易签名
 - getTokenBalance(参数)    查询地址token余额
 - getTxByAddr(参数)    根据地址获取交易信息
 - queryCreateTokens(参数)    查询token列表
 - queryAccountBalance(参数)    查询地址下的token/trace合约下的token资产
 - queryBtyBalance(参数)    查询bty余额
 - submitRawTransaction(参数)    发送签名后的交易
 - sendToAddress(参数)    交易

