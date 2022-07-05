# chain33-sdk-java
Chain33的Java SDK提供交易构造、交易签名、数据加密、发送交易、查询交易、区块链查询等能力，以便于应用层更容易的接入区块链。

支持离线生成助记词，私钥，公钥，地址。  
支持查询区块链信息，如区块高度、交易、节点信息、同步状态。  
支持部署EVM和WASM合智能合约，并调用。  
支持JSON-RPC和gRPC调用。  
兼容国密标准SM2、SM3和SM4。  
支持原生存证、积分发行和转账等能力。  
支持EVM合约部署，调用能力
支持web3j abi库与chain33的适配工作

# 使用
1.下载最新的JAVA-SDK版本  
下载地址：https://github.com/33cn/chain33-sdk-java/releases/download/1.0.18/chain33-sdk-java-1.0.18.zip  

2.将SDK压缩包中的JAR包安装到本地仓库。 
在JAR包所在目录，执行以下命令：  
``` java 
mvn install:install-file -Dfile=chain33-sdk-java.jar -DgroupId=cn.chain33 -DartifactId=chain33-sdk-java -Dversion=1.0.18 -Dpackaging=jar
```
执行结果中打印BUILD SUCCESS，表明添加成功。
如果time out导致构建失败，可以再次执行以上命令，直至构建成功。

3.通过pom.xml导入依赖的jar包
如果导入依赖缓慢，或出现Connection timed out的报错信息，则可能是因为默认中央仓库下载超时，可以切换成阿里云镜像重试。

``` xml
<!—阿里云镜像 -->
  <mirror> 
    <id>alimaven</id> 
	<name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url> 
    <mirrorOf>central</mirrorOf>         
</mirror>
```

4.准备好环境，使用RpcClient调用接口

``` java
// JSONRPC调用方式：初始化,设置Ip端口
RpcClient client = new RpcClient("x.x.x.x",8801);
调用接口
client.接口();
```

``` java
// GRPC调用方式
GrpcClient client = new GrpcClient(host);
调用接口
client.run(o->o.method(builder));
```
# 功能介绍

   包名|功能|测试用例
   ----|---|---
   [CoinsUtil](./src/main/java/cn/chain33/javasdk/utils/CoinsUtil.java)|coins主币积分交易构造|[CoinsTest](./src/test/java/cn/chain33/javasdk/model/CoinsTest.java)
   [EvmUtil](./src/main/java/cn/chain33/javasdk/utils/EvmUtil.java)|evm相关交易构造|[EvmTest](./src/test/java/cn/chain33/javasdk/model/EvmTest.java)
   [AddressUtil](./src/main/java/cn/chain33/javasdk/utils/AddressUtil.java)|地址相关操作工具|
   [TransactionUtil](./src/main/java/cn/chain33/javasdk/utils/TransactionUtil.java)|交易相关操作工具|
   [http](./src/main/java/cn/chain33/javasdk/server/http)|http日志订阅服务|[httpTest](./src/test/java/cn/chain33/javasdk/server/http)
   [Transaction](./src/main/java/cn/chain33/javasdk/model/protobuf/Transaction.java)[交易包装类,交易签名重构等一系列方法]|
   [Transactions](./src/main/java/cn/chain33/javasdk/model/protobuf/Transactions.java)[交易组的包装类,交易组签名重构等一系列方法]|
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
 - SeedUtil.generateMnemonic  离线生成助记词
 - SeedUtil.createAccountBy33PATH  根据助记词离线生成私钥和地址
 
 # 通过SDK部署调用EVM合约样例
 https://github.com/andyYuanFZM/NFTDemo/tree/main/src/test/java/com/chain33/cn
 


