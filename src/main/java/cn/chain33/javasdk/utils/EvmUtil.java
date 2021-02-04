package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.protobuf.EvmService;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class EvmUtil {

    public static byte[] execer = "evm".getBytes();
    
    public static String prefix = "user.evm.";
    /**
     *
     * @description 部署合约（联盟主链的情况下调用）
     * @param code  合约代码内容
     * @param note  注释
     * @param alias 合约别名
     * @param abi   合约abi内容
     * @param privateKey 签名私钥
     * @return  hash，即合约名
     *
     */
    public static String createEvmContract(byte[] code, String note, String alias, String abi, String privateKey) {
        EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
        evmActionBuilder.setCode(ByteString.copyFrom(code));
        evmActionBuilder.setAbi(abi);
        evmActionBuilder.setNote(note);
        evmActionBuilder.setAlias(alias);
        EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

        String createTxWithoutSign = TransactionUtil.createTxWithoutSign(execer, evmContractAction.toByteArray(),
                TransactionUtil.DEFAULT_FEE, 0);
        byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
        TransactionAllProtobuf.Transaction parseFrom = null;
        try {
            parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, privateKey);
        String hexString = HexUtil.toHexString(signProbuf.toByteArray());
        return hexString;
    }
    
    /**
    *
    * @description 部署合约（平行链的情况下调用，要传paraName（平行链名称））
    * @param code  合约代码内容
    * @param note  注释
    * @param alias 合约别名
    * @param abi   合约abi内容
    * @param privateKey 签名私钥
    * @return  hash，即合约名
    *
    */
   public static String createEvmContract(byte[] code, String note, String alias, String abi, String privateKey, String paraName) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setCode(ByteString.copyFrom(code));
       evmActionBuilder.setAbi(abi);
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAlias(alias);
       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

       String createTxWithoutSign = TransactionUtil.createTxWithoutSign((paraName + "evm").getBytes(), evmContractAction.toByteArray(),
               TransactionUtil.PARA_CREATE_EVM_FEE, 0);
       byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
       TransactionAllProtobuf.Transaction parseFrom = null;
       try {
           parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
       } catch (InvalidProtocolBufferException e) {
           e.printStackTrace();
       }
       TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, privateKey);
       String hexString = HexUtil.toHexString(signProbuf.toByteArray());
       return hexString;
   }
    
    /**
    *
    * @description 部署合约（平行链采用代扣的情况下调用）
    * @param code  合约代码内容
    * @param note  注释
    * @param alias 合约别名
    * @param abi   合约abi内容
    * @param privateKey 签名私钥
    * @return  hash，即合约名
    *
    */
   public static String createEvmContractWithhold(byte[] code, String note, String alias, String abi, String privateKey, String execer, String contranctAddress) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setCode(ByteString.copyFrom(code));
       evmActionBuilder.setAbi(abi);
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAlias(alias);
       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();
       
       String createTransferTx = TransactionUtil.createTransferTx(privateKey, contranctAddress, execer, evmContractAction.toByteArray(), TransactionUtil.PARA_CREATE_EVM_FEE);

       return createTransferTx;
   }
    

    /**
     * 
     * @description  调用合约（联盟主链的情况下调用）
     * @param code   合约代码内容
     * @param note   注释
     * @param amount 转账金额
     * @param abi    合约abi内容
     * @param contractName 合约名
     * @param privateKey 签名私钥
     * @return  hash
     *
     */
    public static String callEvmContract(byte[] code, String note, long amount, String abi, String contractName, String privateKey) {
        EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
        evmActionBuilder.setCode(ByteString.copyFrom(code));
        evmActionBuilder.setAbi(abi);
        evmActionBuilder.setNote(note);
        evmActionBuilder.setAmount(amount);
        EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

        String createTxWithoutSign = TransactionUtil.createTxWithoutSign((prefix + contractName).getBytes(), evmContractAction.toByteArray(),
                TransactionUtil.DEFAULT_FEE, 0);
        byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
        TransactionAllProtobuf.Transaction parseFrom = null;
        try {
            parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, privateKey);
        String hexString = HexUtil.toHexString(signProbuf.toByteArray());
        return hexString;
    }
    
    /**
    * 
    * @description  调用合约（平行链的情况下调用，要传paraName（平行链名称））
    * @param code   合约代码内容
    * @param note   注释
    * @param amount 转账金额
    * @param abi    合约abi内容
    * @param contractName 合约名
    * @param privateKey 签名私钥
    * @return  hash
    *
    */
   public static String callEvmContract(byte[] code, String note, long amount, String abi, String contractName, String privateKey, String paraName) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setCode(ByteString.copyFrom(code));
       evmActionBuilder.setAbi(abi);
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAmount(amount);
       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

       String createTxWithoutSign = TransactionUtil.createTxWithoutSign((paraName + prefix + contractName).getBytes(), evmContractAction.toByteArray(),
               TransactionUtil.PARA_CALL_EVM_FEE, 0);
       byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
       TransactionAllProtobuf.Transaction parseFrom = null;
       try {
           parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
       } catch (InvalidProtocolBufferException e) {
           e.printStackTrace();
       }
       TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, privateKey);
       String hexString = HexUtil.toHexString(signProbuf.toByteArray());
       return hexString;
   }
    

   /**
    * @description  调用合约（平行链采用代扣的情况下调用）
    * @param code
    * @param note
    * @param amount
    * @param abi
    * @param contractName
    * @param privateKey
    * @param contranctAddress
    * @return
    */
   public static String callEvmContractWithhold(byte[] code, String note, long amount, String abi, String contractName, String privateKey, String contranctAddress) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setCode(ByteString.copyFrom(code));
       evmActionBuilder.setAbi(abi);
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAmount(amount);
       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

       String createTransferTx = TransactionUtil.createTransferTx(privateKey, contranctAddress, contractName, evmContractAction.toByteArray(), TransactionUtil.PARA_CALL_EVM_FEE);

       return createTransferTx;
   }



    /**
     *
     * @description 销毁合约
     * @param contractName 合约名
     * @param privateKey 签名私钥
     * @return  hash
     *
     */
    public static String destroyEvmContract(String contractName, String privateKey) {
        EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
        EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

        String createTxWithoutSign = TransactionUtil.createTxWithoutSign((prefix + contractName).getBytes(), evmContractAction.toByteArray(),
                TransactionUtil.DEFAULT_FEE, 0);
        byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
        TransactionAllProtobuf.Transaction parseFrom = null;
        try {
            parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, privateKey);
        String hexString = HexUtil.toHexString(signProbuf.toByteArray());
        return hexString;
    }
}
