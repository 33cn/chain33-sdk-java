package cn.chain33.javasdk.utils;

import static cn.chain33.javasdk.model.evm.compiler.SolidityCompiler.Options.ABI;
import static cn.chain33.javasdk.model.evm.compiler.SolidityCompiler.Options.BIN;
import static cn.chain33.javasdk.model.evm.compiler.SolidityCompiler.Options.INTERFACE;
import static cn.chain33.javasdk.model.evm.compiler.SolidityCompiler.Options.METADATA;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import cn.chain33.javasdk.model.evm.Abi;
import cn.chain33.javasdk.model.evm.compiler.CompilationResult;
import cn.chain33.javasdk.model.evm.compiler.SolidityCompiler;
import cn.chain33.javasdk.model.protobuf.EvmService;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf;

public class EvmUtil {

    public static byte[] execer = "evm".getBytes();

    public static final long PARA_CREATE_EVM_FEE = 3000000;

    public static final long PARA_CALL_EVM_FEE = 10000000;

    /**
     *
     * @description 部署合约（联盟主链的情况下调用）
     * @param code  合约代码内容
     * @param note  注释
     * @param alias 合约别名
     * @param privateKey 签名私钥
     * @return  hash，即合约名
     *
     */
    public static String createEvmContract(byte[] code, String note, String alias, String privateKey) {
        EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
        evmActionBuilder.setCode(ByteString.copyFrom(code));
        evmActionBuilder.setNote(note);
        evmActionBuilder.setAlias(alias);
        evmActionBuilder.setContractAddr(TransactionUtil.getToAddress(execer));

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
    * @param privateKey 签名私钥
    * @return  hash，即合约名
    *
    */
   public static String createEvmContract(byte[] code, String note, String alias, String privateKey, String paraName) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setCode(ByteString.copyFrom(code));
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAlias(alias);
       evmActionBuilder.setContractAddr(TransactionUtil.getToAddress((paraName + "evm").getBytes()));

       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

       String createTxWithoutSign = TransactionUtil.createTxWithoutSign((paraName + "evm").getBytes(), evmContractAction.toByteArray(),
               PARA_CREATE_EVM_FEE, 0);
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
    * @param privateKey 签名私钥
    * @return  hash，即合约名
    *
    */
   public static String createEvmContractWithhold(byte[] code, String note, String alias, String privateKey, String execer, String contranctAddress) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setCode(ByteString.copyFrom(code));
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAlias(alias);

       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();
       
       String createTransferTx = TransactionUtil.createTransferTx(privateKey, contranctAddress, execer, evmContractAction.toByteArray(), PARA_CREATE_EVM_FEE);

       return createTransferTx;
   }
    

    /**
    * 
    * @description  调用合约（平行链的情况下调用，要传paraName（平行链名称））
    * @param parameter   合约代码内容
    * @param note   注释
    * @param amount 转账金额
    * @param privateKey 签名私钥
    * @return  hash
    *
    */
   public static String callEvmContract(byte[] parameter, String note, long amount, String contractAddr, String privateKey, String paraName) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setPara(ByteString.copyFrom(parameter));
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAmount(amount);
       evmActionBuilder.setContractAddr(contractAddr);
       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

       String createTxWithoutSign = TransactionUtil.createTxWithoutSign((paraName + "evm").getBytes(), evmContractAction.toByteArray(),
               PARA_CALL_EVM_FEE, 0);
       byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
       TransactionAllProtobuf.Transaction parseFrom = null;
       try {
           parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
       } catch (InvalidProtocolBufferException e) {
           e.printStackTrace();
       }
       TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, privateKey);
       return HexUtil.toHexString(signProbuf.toByteArray());
   }
    

   /**
    * @description  调用合约（平行链采用代扣的情况下调用）
    * @param parameter
    * @param note
    * @param amount
    * @param privateKey
    * @param contractAddress
    * @return
    */
   public static String callEvmContractWithhold(byte[] parameter, String note, long amount, String exec, String privateKey, String contractAddress) {
       EvmService.EVMContractAction.Builder evmActionBuilder = EvmService.EVMContractAction.newBuilder();
       evmActionBuilder.setPara(ByteString.copyFrom(parameter));
       evmActionBuilder.setNote(note);
       evmActionBuilder.setAmount(amount);
       evmActionBuilder.setContractAddr(contractAddress);
       EvmService.EVMContractAction evmContractAction = evmActionBuilder.build();

       String createTransferTx = TransactionUtil.createTransferTx(privateKey, TransactionUtil.getToAddress(exec.getBytes()), exec, evmContractAction.toByteArray(), PARA_CALL_EVM_FEE);

       return createTransferTx;
   }


    public static SolidityCompiler.Result compileContract(byte[] code, String version) throws IOException {
        return SolidityCompiler.compile(code, version, true, ABI, BIN, INTERFACE, METADATA);
    }

    public static CompilationResult.ContractMetadata paserCompileResult(SolidityCompiler.Result result, String contractName) throws IOException {
        CompilationResult compilationResult = CompilationResult.parse(result.getOutput());
        return compilationResult.getContract(contractName);
    }

    public static byte[] encodeParameter(String abiStr, String funcName, Object... params) throws Exception {
        try {
            Abi abiObj = Abi.fromJson(abiStr);
            Abi.Function func = abiObj.findFunction(s->s.name.equals(funcName));
            return func.encode(params);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static byte[] encodeContructor(String abiStr, Object... params) throws Exception {
        try {
            Abi abiObj = Abi.fromJson(abiStr);
            Abi.Constructor constructor = abiObj.findConstructor();
            return constructor.encode(params);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static List<?> decodeOutput(String abiStr, String funcName, JSONObject output) {
        String resultArray = output.getString("rawData");

        Abi abiObj = Abi.fromJson(abiStr);
        Abi.Function func = abiObj.findFunction(s->s.name.equals(funcName));

        return func.decodeResult(HexUtil.fromHexString(resultArray));
    }

    public static String getContractAddr(JSONObject result) {
        String resultStr = result.toJSONString();

        String pattern = "\"contractAddr\":\"(\\w+)\"";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(resultStr);

        if(m.find()) {
            String[] splits = StringUtils.split(m.group(0), ":");
            if(splits.length == 2) {
                return StringUtils.strip(splits[1], "\"");
            }
        }

        return "";
    }

}
