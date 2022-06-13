package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.Address;
import cn.chain33.javasdk.model.enums.AddressType;
import org.bitcoinj.core.ECKey;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @authoer lhl
 * @date 2022/6/12 上午10:34
 */
public class AddressUtil {

    private static byte[] addrSeed = "address seed bytes for public key".getBytes();
    /**
     * @param address BTC地址
     * @return 校验结果
     * @description 校验地址是否符合规则
     */
    public static boolean validBTCAddress(String address) {
        try {
            byte[] decodeBytes = Base58Util.decode(address);
            byte[] checkByteByte = ByteUtils.subArray(decodeBytes, decodeBytes.length - 4);
            byte[] noCheckByte = ByteUtils.subArray(decodeBytes, 0, decodeBytes.length - 4);
            byte[] twice = twoSha3(noCheckByte);
            for (int i = 0; i < 4; i++) {
                if (twice[i] != checkByteByte[i]) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param address 地址
     * @return 校验结果
     * @description 校验普通个人地址是否符合以太坊地址规则
     */
    public static boolean validETHAddress(String address) {
        if (!address.startsWith("0x"))
            return false;
        String cleanHexInput = Numeric.cleanHexPrefix(address);
        try {
            Numeric.toBigIntNoPrefix(cleanHexInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return cleanHexInput.length() == 40;
    }

    /**
     * @param address 地址
     * @return 校验结果
     * @description 校验地址是否符合规则
     */
    public static boolean validAddress(String address, AddressType addressType) {
        switch (addressType) {
            case BTC_ADDRESS: {
                return validBTCAddress(address);
            }
            case ETH_ADDRESS: {
                return validETHAddress(address);
            }
            default:
                return false;
        }
    }


    /**
     * 通用根据执行器名称获取相应地址格式执行器地址
     *
     * @param execer
     * @param addressType
     * @return
     */
    public static String getToAddress(byte[] execer, AddressType addressType) {
        byte[] mergeredByte = ByteUtil.merge(addrSeed, execer);
        //两次sha256处理
        byte[] sha256_2 = twoSha3(mergeredByte);

        if (addressType == AddressType.BTC_ADDRESS) {
            byte[] hash160=HashUtil.sha256hash160(sha256_2);
            Address address = new Address();
            address.setHash160(hash160);
            return addressToString(address);
        }
        if (addressType == AddressType.ETH_ADDRESS) {
            //根据公钥生成eth格式地址,因为根据执行器名称生成的公钥不具有压缩属性，所以需要Keccak256生成相应的地址
            byte[] srcBytes = new byte[sha256_2.length - 1];
            System.arraycopy(sha256_2, 1, srcBytes, 0, sha256_2.length - 1);
            byte[] bytes = HashUtil.sha3(srcBytes);
            //取后20位作为地址
            byte[] data = new byte[20];
            System.arraycopy(bytes, 12, data, 0, 20);
            return "0x" + HexUtil.toHexString(data);
        }
        return null;
    }

    /**
     * 本地计算evm合约地址
     *
     * @param txHash
     * @param addr
     * @param addressType
     * @return
     */
    public static String getContractAddress(String txHash,String addr, AddressType addressType) {
        //TODO 要判断地址格式, 这里要重新适配
        byte[] bytesAddr= new byte[0];
        if (validETHAddress(addr)){
            bytesAddr=HexUtil.fromHexString(addr);
        }else if (validBTCAddress(addr)){
            bytesAddr=HexUtil.fromHexString(convertBTCToETH(addr));
        }
        byte[] bytes=ByteUtil.merge(HexUtil.fromHexString(txHash),bytesAddr);
        return getToAddress(HashUtil.sha256(bytes),addressType);
    }
    /**
     * @return 私钥
     * @description 创建私钥和公钥
     */
    public static byte[] generatorPrivateKey() {
        int length = 0;
        byte[] privateKey;
        do {
            ECKeyPairGenerator gen = new ECKeyPairGenerator();
            SecureRandom secureRandom = new SecureRandom();
            X9ECParameters secnamecurves = SECNamedCurves.getByName("secp256k1");
            ECDomainParameters ecParams = new ECDomainParameters(secnamecurves.getCurve(), secnamecurves.getG(),
                    secnamecurves.getN(), secnamecurves.getH());
            ECKeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(ecParams, secureRandom);
            gen.init(keyGenParam);
            AsymmetricCipherKeyPair kp = gen.generateKeyPair();
            ECPrivateKeyParameters privatekey = (ECPrivateKeyParameters) kp.getPrivate();
            privateKey = privatekey.getD().toByteArray();
            length = privatekey.getD().toByteArray().length;
        } while (length != 32);
        return privateKey;
    }

    /**
     * @return 私钥
     * @description 生成私钥
     */
    public static String generatorPrivateKeyString() {
        byte[] generatorPrivateKey = generatorPrivateKey();
        ECKey eckey = ECKey.fromPrivate(generatorPrivateKey);
        return eckey.getPrivateKeyAsHex();
    }

    /**
     * @param privateKey 私钥
     * @return btc公钥
     * @description 通过私钥生成公钥
     */
    public static String getHexPubKeyFromPrivKey(String privateKey) {
        ECKey eckey = ECKey.fromPrivate(HexUtil.fromHexString(privateKey));
        byte[] pubKey = eckey.getPubKey();
        String pubKeyStr = HexUtil.toHexString(pubKey);
        return pubKeyStr;
    }


    /**
     * 通过私钥生成YCC格式公钥
     *
     * @param privateKey
     * @return
     */
    public static BigInteger getHexPubKeyFromPrivKeyForYCC(String privateKey) {
        ECKeyPair keyPair = ECKeyPair.create(HexUtil.fromHexString(privateKey));
        BigInteger pubKeyStr = keyPair.getPublicKey();
        return pubKeyStr;
    }

    /**
     * @param pubKey 公钥
     * @return 地址
     * @description 通过公钥生成BTC格式地址
     */
    public static String genBTCAddress(byte[] pubKey) {
        byte[] hash160 = HashUtil.sha256hash160(pubKey);
        Address address = new Address();
        address.setHash160(hash160);
        return addressToString(address);
    }
    public static String addressToString(Address address) {
        if (StringUtil.isEmpty(address.getEnc58Str())) {
            byte[] ad = new byte[25];
            ad[0] = address.getVersion();
            for (int i = 1; i < 21; i++) {
                ad[i] = address.getHash160()[i - 1];
            }
            byte[] subByteArr = ByteUtil.subByteArr(ad, 0, 21);
            byte[] checkSum = twoSha3(subByteArr);
            address.setCheckSum(checkSum);
            for (int i = 21, j = 0; i < 25; i++, j++) {
                ad[i] = checkSum[j];
            }
            String Enc58Str = Base58Util.encode(ad);
            address.setEnc58Str(Enc58Str);
        }
        return address.getEnc58Str();
    }

    /**
     * @param sourceByte
     * @description 数据处理, sha256 2次
     */
    private static byte[] twoSha3(byte[] sourceByte) {
        byte[] sha256_1 = HashUtil.sha256(sourceByte);
        byte[] sha256_2 = HashUtil.sha256(sha256_1);
        return sha256_2;
    }
    /**
     * @param pubKey 公钥
     * @return 地址
     * @description 通过公钥生成ETH格式地址（以太坊形式，以0x开头的地址）
     */
    public static String genETHAddress(BigInteger pubKey) {
        //通过公钥生成钱包地址
        String address = Keys.getAddress(pubKey);
        return "0x" + address;
    }

    /**
     * @param privateKey  私钥
     * @param addressType 0表示生成btc格式地址,2表示生成eth格式地址
     * @return 地址
     * @description 通过私钥生成地址
     */
    public static String genAddress(String privateKey, AddressType addressType) {
        switch (addressType) {
            case BTC_ADDRESS: {
                return genBTCAddress(HexUtil.fromHexString(getHexPubKeyFromPrivKey(privateKey)));
            }
            case ETH_ADDRESS: {
                return genETHAddress(getHexPubKeyFromPrivKeyForYCC(privateKey));
            }
            default:
                return null;
        }
    }

    /**
     * 将evm地址转成base58编码地址
     *
     * @param addressByte
     * @return
     * @throws Exception
     */
    public static String encodeAddress(byte[] addressByte) {
        Address address = new Address();
        address.setHash160(addressByte);
        return addressToString(address);
    }

    /**
     * 将ETH地址转成BTC地址
     * @param ethAddress chain33中eth格式地址
     * @return
     */
    public static String convertETHToBTC(String ethAddress) {
        return encodeAddress(HexUtil.fromHexString(ethAddress));
    }

    /**
     * 将BTC地址转为ETH地址
     *
     * @param btcAddress chain33中btc格式地址
     * @return
     */
    public static String convertBTCToETH(String btcAddress) {
        try {
            return "0x"+HexUtil.toHexString(decodeAddress(btcAddress));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将base58编码的地址转成evm地址
     *
     * @param address chain33中btc格式地址
     * @return
     * @throws Exception
     */
    public static byte[] decodeAddress(String address) throws Exception {
        byte[] decodeBytes = Base58Util.decode(address);
        if (decodeBytes.length < 25) {
            throw new Exception("Address too short " + HexUtil.toHexString(decodeBytes));
        }

        if (!validBTCAddress(address)) {
            throw new Exception("Address check failed " + HexUtil.toHexString(decodeBytes));
        }

        return ByteUtils.subArray(decodeBytes, 1, decodeBytes.length - 4);
    }
}
