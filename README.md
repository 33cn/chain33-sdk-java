# chain33-sdk-java
调用chain33 rpc接口的java客户端。

# 使用
1.下载chain33-sdk-java 1.0.12版本  
下载地址：https://github.com/33cn/chain33-sdk-java/releases/download/1.0.12/chain33-sdk-java-1.0.12.zip
解压后，通过mvn下载依赖jar包  
这边也提供jar包下载：https://bty33.oss-cn-shanghai.aliyuncs.com/java-sdk-jar.zip  


2.整备好环境，使用RpcClient调用接口

``` java
初始化,设置Ip端口
RpcClient client = new RpcClient("x.x.x.x",8801);
调用接口
client.接口();
```

3.整备好环境，使用GrpcClient调用接口

``` java
初始化,设置Ip端口
GrpcClient client = new GrpcClient("x.x.x.x",8802);
调用接口
client.run(o->o.method(builder));
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
 - client.importPrivatekey()    导入私钥
 - client.unlock(passwd,walletorticket,timeout)   钱包解锁
 - client.newAccount(label)    创建账户
 - client.seedGen()    生成随机的seed
 - client.seedSave(seed,password)    保存seed并用密码加密
 - client.seedGet(passwd)    通过钱包密码获取钱包的seed原文
 - client.setlabel(addr,label)    设置标签
 - client.getAccountList()     获取账户列表
 - client.createRawTokenPreCreateTx(参数)    创建预创建token的交易（未签名）
 - client.createRawTokenFinishTx(参数)   创建完成创建token的交易（未签名）
 - client.CreateRawTokenRevokeTx(参数)   创建撤销预创建token的交易（未签名）
 - client.createRawTransaction(参数)    构造交易
 - client.signRawTx(参数)    交易签名
 - client.getTokenBalance(参数)    查询地址token余额
 - client.getTxByAddr(参数)    根据地址获取交易信息
 - client.queryCreateTokens(参数)    查询token列表
 - client.queryAccountBalance(参数)    查询地址下的token/trace合约下的token资产
 - client.queryBalance(参数)    查询地址余额
 - client.submitRawTransaction(参数)    发送签名后的交易
 - client.sendToAddress(参数)    交易
 - client.createTransaction(参数)    合约交易创建
 - client.addPushSubscribe(参数)    注册推送回调
 - client.listPushes(参数)    列举推送服务
 - client.getPushSeqLastNum(参数)    获取某推送服务最新序列号的值
 - TransactionUtil.createTransferPayLoad(参数)	本地构造转账交易payload
 - TransactionUtil.createTransferTx(参数,payload)	本地构造转账交易体
 - 创建好的交易体使用client.submitTransaction提交交易
 - TransactionUtil.generatorPrivateKeyString()	创建私钥
 - TransactionUtil.getHexPubKeyFromPrivKey(私钥)	根据私钥获得公钥
 - TransactionUtil.genAddress(公钥)	根据公钥获取地址
 - TransactionUtil.validAddress(地址)	校验地址是否正确
 - TransactionUtil.createPrecreateTokenTx(参数)    本地创建预创建token交易
 - TransactionUtil.createTokenFinishTx(地址)   本地创建完成创建token交易
 - StorageUtil.createOnlyNotaryStorage 创建内容存证模型(payload)
 - StorageUtil.createHashStorage 创建哈希存证模型(payload)
 - StorageUtil.createLinkNotaryStorage 创建链接存证模型(payload)
 - StorageUtil.createEncryptNotaryStorage 隐私存证模型型(payload)
 - StorageUtil.createEncryptShareNotaryStorage 创建分享隐私存证模型(payload)



