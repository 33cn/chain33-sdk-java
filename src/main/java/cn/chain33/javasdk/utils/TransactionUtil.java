package cn.chain33.javasdk.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import cn.chain33.javasdk.model.protobuf.*;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import cn.chain33.javasdk.model.Address;
import cn.chain33.javasdk.model.Signature;
import cn.chain33.javasdk.model.Transaction;
import cn.chain33.javasdk.model.TransferBalanceRequest;
import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.gm.SM2KeyPair;
import cn.chain33.javasdk.model.gm.SM2Util;
import cn.chain33.javasdk.model.protobuf.ManageProtobuf.ManageAction;
import cn.chain33.javasdk.model.protobuf.ManageProtobuf.ModifyConfig.Builder;
import cn.chain33.javasdk.model.protobuf.TokenActionProtoBuf.TokenAction;
import cn.chain33.javasdk.model.protobuf.TokenActionProtoBuf.TokenFinishCreate;
import cn.chain33.javasdk.model.protobuf.TokenActionProtoBuf.TokenPreCreate;
import cn.chain33.javasdk.model.protobuf.TransactionAllProtobuf.AssetsTransfer;
import net.vrallev.java.ecc.Ecc25519Helper;

/**
 * 
 * @author logan 2018年5月14日
 */
public class TransactionUtil {

	private static final SignType DEFAULT_SIGNTYPE = SignType.SECP256K1;

	public static final long DEFAULT_FEE = 1000000;
	
	public static final long PARA_CREATE_EVM_FEE = 3000000;
	
	public static final long PARA_CALL_EVM_FEE = 200000;

	private final static Long TX_HEIGHT_OFFSET = 1L << 62;
	
	private final static Long LowAllowPackHeight = 30L;

	private static byte[] addrSeed = "address seed bytes for public key".getBytes();

	private static final long DURATION = 1;

	private static final long MICROSECOND = DURATION * 1000;

	private static final long MILLISECOND = MICROSECOND * 1000;

	private static final long SECOND = MILLISECOND * 1000;

	// private static final long MINUTE = SECOND * 1000;

	private static final long EXPIREBOUND = 1000000000;

	public static String toHexString(byte[] byteArr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteArr.length; i++) {
			int b = byteArr[i] & 0xff;
			String hexString = Integer.toHexString(b);
			sb.append(hexString);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @description expire转换为纳秒为单位
	 * @param expire
	 *            单位为秒
	 * @return
	 *
	 */
	public static long getExpire(long expire) {
		expire = expire * EXPIREBOUND;
		if (expire > EXPIREBOUND) {
			if (expire < SECOND * 120) {
				expire = SECOND * 120;
			}
			expire = System.currentTimeMillis() / 1000l + expire / SECOND;
			return expire;
		} else {
			return expire;
		}
	}

	/**
	 * byte数组合并
	 * 
	 * @param byte_1
	 * @param byte_2
	 * @return
	 */
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	/**
	 * 
	 * @description 通过公钥生成地址
	 * @param pubKey
	 *            公钥
	 * @return 地址
	 */
	public static String genAddress(byte[] pubKey) {
		byte[] sha256 = TransactionUtil.Sha256(pubKey);
		byte[] ripemd160 = TransactionUtil.ripemd160(sha256);
		Address address = new Address();
		address.setHash160(ripemd160);
		return addressToString(address);
	}

	/**
	 * 
	 * @description 校验地址是否符合规则
	 * @param address
	 *            地址
	 * @return 校验结果
	 */
	public static boolean validAddress(String address) {
		try {
			byte[] decodeBytes = Base58Util.decode(address);
			byte[] checkByteByte = ByteUtils.subArray(decodeBytes, decodeBytes.length - 4);
			byte[] noCheckByte = ByteUtils.subArray(decodeBytes, 0, decodeBytes.length - 4);
			byte[] sha256 = Sha256(noCheckByte);
			byte[] twice = Sha256(sha256);
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
	 * @description byte数组截取
	 * 
	 * @param byteArr
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte[] subByteArr(byte[] byteArr, Integer start, Integer end) {
		Integer diff = end - start;
		byte[] byteTarget = new byte[diff];
		if (diff > byteArr.length) {
			diff = byteArr.length;
		}
		for (int i = 0; i < diff; i++) {
			byteTarget[i] = byteArr[i];
		}
		return byteTarget;
	}

	public static byte[] Sha256(byte[] sourceByte) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(sourceByte);
			byte byteData[] = md.digest();
			return byteData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Long getRandomNonce() {
		Random random = new Random(System.nanoTime());
		return Math.abs(random.nextLong());
	}

	/**
	 * 
	 * @description 本地创建coins转账payload
	 * @param to 目标地址
	 * @param amount 数量
	 * @param coinToken 主代币则为""
	 * @param note 备注，没有为""
	 * @return payload
	 */
	public static byte[] createTransferPayLoad(String to, Long amount, String coinToken, String note) {
		TransactionAllProtobuf.AssetsTransfer.Builder assetsTransferBuilder = TransactionAllProtobuf.AssetsTransfer.newBuilder();
		assetsTransferBuilder.setCointoken(coinToken);
		assetsTransferBuilder.setAmount(amount);
		try {
			assetsTransferBuilder.setNote(ByteString.copyFrom(note, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assetsTransferBuilder.setTo(to);
		AssetsTransfer assetsTransfer = assetsTransferBuilder.build();
		CoinsProtobuf.CoinsAction.Builder coinsActionBuilder = CoinsProtobuf.CoinsAction.newBuilder();
		coinsActionBuilder.setTy(1);
		coinsActionBuilder.setTransfer(assetsTransfer);
		CoinsProtobuf.CoinsAction coinsAction = coinsActionBuilder.build();
		byte[] payload = coinsAction.toByteArray();
		return payload;
	}
	


	/**
	 * 
	 * @description 本地创建转账交易
	 * @param privateKey
	 * @param toAddress
	 * @param execer
	 * @param payLoad
	 * @return
	 *
	 */
	public static String createTransferTx(String privateKey, String toAddress, String execer, byte[] payLoad) {
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTxMain(privateKeyBytes, toAddress, execer.getBytes(), payLoad, DEFAULT_SIGNTYPE, DEFAULT_FEE);
	}
	
	public static String createTransferTx(String privateKey, String toAddress, String execer, byte[] payLoad, long fee) {
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTxMain(privateKeyBytes, toAddress, execer.getBytes(), payLoad, DEFAULT_SIGNTYPE, fee);
	}
	
	public static String createTransferTx(String privateKey, String toAddress, String execer, byte[] payLoad, long fee, long txheight) {
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTxMain(privateKeyBytes, toAddress, execer.getBytes(), payLoad, DEFAULT_SIGNTYPE, fee, txheight);
	}

	public static TransactionAllProtobuf.Transaction createTransferTx2(String privateKey, String toAddress, String execer, byte[] payLoad, long fee, long txheight) {
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTxMain2(privateKeyBytes, toAddress, execer.getBytes(), payLoad, DEFAULT_SIGNTYPE, fee, txheight);
	}

	public static String createTx(String privateKey, String execer, String payLoad) {
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTx(privateKeyBytes, execer.getBytes(), payLoad.getBytes(), DEFAULT_SIGNTYPE, DEFAULT_FEE);
	}

	public static String createTx(String privateKey, String execer, String payLoad, long fee) {
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTx(privateKeyBytes, execer.getBytes(), payLoad.getBytes(), DEFAULT_SIGNTYPE, fee);
	}

	public static String createTx(byte[] privateKey, byte[] execer, byte[] payLoad, SignType signType, long fee) {
		String toAddress = getToAddress(execer);
		return createTxMain(privateKey, toAddress, execer, payLoad, signType, fee);
	}

	private static String createTxMain(byte[] privateKey, String toAddress, byte[] execer, byte[] payLoad,
			SignType signType, long fee) {
		if (signType == null)
			signType = DEFAULT_SIGNTYPE;

		// 如果没有私钥，创建私钥 privateKey =
		if (privateKey == null) {
			TransactionUtil.generatorPrivateKey();
		}

		Transaction transaction = createTxRaw(toAddress, execer, payLoad, fee);

		// 签名
		byte[] protobufData = encodeProtobuf(transaction);

		sign(signType, protobufData, privateKey, null, transaction);
		// 序列化
		byte[] encodeProtobufWithSign = encodeProtobufWithSign(transaction);
		String transationStr = HexUtil.toHexString(encodeProtobufWithSign);
		return transationStr;
	}

	/**
	 * 
	 * @description 本地构造交易
	 * @param privateKey
	 *            私钥
	 * @param toAddress
	 *            目标地址
	 * @param execer
	 *            例如user.p.xxchain.token
	 * @param payLoad
	 *            内容
	 * @param signType
	 *            签名方式，默认SignType.SECP256K1
	 * @param fee
	 *            手续费
	 * @param txHeight
	 *            联盟链需要，其他为null
	 * @return
	 *
	 */
	public static String createTxMain(byte[] privateKey, String toAddress, byte[] execer, byte[] payLoad,
			SignType signType, long fee, Long txHeight) {
		if (signType == null)
			signType = DEFAULT_SIGNTYPE;

		// 如果没有私钥，创建私钥 privateKey =
		if (privateKey == null) {
			TransactionUtil.generatorPrivateKey();
		}

		Transaction transation = createTxRaw(toAddress, execer, payLoad, fee);
		if (txHeight != null) {
			transation.setExpire(txHeight + TX_HEIGHT_OFFSET + LowAllowPackHeight);
		}

		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, privateKey, null, transation);
		// 序列化
		byte[] encodeProtobufWithSign = encodeProtobufWithSign(transation);
		String transationHash = HexUtil.toHexString(encodeProtobufWithSign);
		return transationHash;
	}

	/**
	 *
	 * @description 本地构造交易
	 * @param privateKey
	 *            私钥
	 * @param toAddress
	 *            目标地址
	 * @param execer
	 *            例如user.p.xxchain.token
	 * @param payLoad
	 *            内容
	 * @param signType
	 *            签名方式，默认SignType.SECP256K1
	 * @param fee
	 *            手续费
	 * @param txHeight
	 *            联盟链需要，其他为null
	 * @return
	 *
	 */
	public static TransactionAllProtobuf.Transaction createTxMain2(byte[] privateKey, String toAddress, byte[] execer, byte[] payLoad,
																SignType signType, long fee, Long txHeight) {
		if (signType == null)
			signType = DEFAULT_SIGNTYPE;

		// 如果没有私钥，创建私钥 privateKey =
		if (privateKey == null) {
			TransactionUtil.generatorPrivateKey();
		}

		Transaction transation = createTxRaw(toAddress, execer, payLoad, fee);
		if (txHeight != null) {
			transation.setExpire(txHeight + TX_HEIGHT_OFFSET + LowAllowPackHeight);
		}

		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, privateKey, null, transation);
		TransactionAllProtobuf.Transaction tx = encodeProtobufWithSign2(transation);
		return tx;
	}

	public static String createTxWithCert(String privateKey, String execer, byte[] payLoad, SignType signType, byte[] cert, byte[] uid) {
		if (signType == null)
			signType = DEFAULT_SIGNTYPE;

		// 如果没有私钥，创建私钥 privateKey =
		if (privateKey == null) {
			TransactionUtil.generatorPrivateKey();
		}

		String toAddress = getToAddress(execer.getBytes());
		Transaction transation = createTxRaw(toAddress, execer.getBytes(), payLoad, DEFAULT_FEE);
		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, HexUtil.fromHexString(privateKey), uid, transation);

		byte[] certSign = CertUtils.EncodeCertToSignature(transation.getSignature().getSignature(), cert, uid);
		transation.getSignature().setSignature(certSign);

		// 序列化
		byte[] encodeProtobufWithSign = encodeProtobufWithSign(transation);
		String transationHash = HexUtil.toHexString(encodeProtobufWithSign);
		return transationHash;
	}

	public static TransactionAllProtobuf.Transaction createTxWithCertProto(String privateKey, String execer, byte[] payLoad, SignType signType, byte[] cert, byte[] uid) {
		if (signType == null)
			signType = DEFAULT_SIGNTYPE;

		// 如果没有私钥，创建私钥 privateKey =
		if (privateKey == null) {
			TransactionUtil.generatorPrivateKey();
		}

		String toAddress = getToAddress(execer.getBytes());
		Transaction transation = createTxRaw(toAddress, execer.getBytes(), payLoad, DEFAULT_FEE);
		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, HexUtil.fromHexString(privateKey), uid, transation);

		byte[] certSign = CertUtils.EncodeCertToSignature(transation.getSignature().getSignature(), cert, uid);
		transation.getSignature().setSignature(certSign);

		TransactionAllProtobuf.Transaction tx = encodeProtobufWithSign2(transation);
		return tx;
	}
	public static Transaction createTxRaw(String toAddress, byte[] execer, byte[] payLoad, long fee) {
		Transaction transation = new Transaction();
		transation.setExecer(execer);
		transation.setPayload(payLoad);
		transation.setFee(fee);
		transation.setNonce(TransactionUtil.getRandomNonce());
		// 计算To
		transation.setTo(toAddress);

		return transation;
	}

	/**
	 * 构造转帐交易，并签名
	 * 
	 * @return 交易hash
	 */
	public static String transferBalanceMain(TransferBalanceRequest transferBalanceRequest) {
		String to = transferBalanceRequest.getTo();
		Long amount = transferBalanceRequest.getAmount();
		String coinToken = transferBalanceRequest.getCoinToken();
		String note = transferBalanceRequest.getNote();
		SignType signType = transferBalanceRequest.getSignType();
		String privateKey = transferBalanceRequest.getFromPrivateKey();
		String execer = transferBalanceRequest.getExecer();
		long fee = transferBalanceRequest.getFee();
		byte[] payload = createTransferPayLoad(to, amount, coinToken, note);

		byte[] execerBytes;
		if (StringUtil.isNotEmpty(execer)) {
			execerBytes = execer.getBytes();
		} else {
			execerBytes = "none".getBytes();
		}
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);

		String transferTx = createTxMain(privateKeyBytes, to, execerBytes, payload, signType, fee);
		return transferTx;
	}

	/**
	 * 计算to
	 * 
	 * @param execer
	 * @return
	 */
	private static String getToAddress(byte[] execer) {
		byte[] mergeredByte = TransactionUtil.byteMerger(addrSeed, execer);
		byte[] sha256_1 = TransactionUtil.Sha256(mergeredByte);
		for (int i = 0; i < sha256_1.length; i++) {
			sha256_1[i] = (byte) (sha256_1[i] & 0xff);
		}
		byte[] sha256_2 = TransactionUtil.Sha256(sha256_1);
		byte[] sha256_3 = TransactionUtil.Sha256(sha256_2);
		byte[] ripemd160 = TransactionUtil.ripemd160(sha256_3);
		Address address = new Address();
		address.setHash160(ripemd160);
		return addressToString(address);
	}

	/**
	 * @description 创建私钥和公钥
	 * 
	 * @return 私钥
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
	 * 
	 * @description 生成私钥
	 * @return 私钥
	 *
	 */
	public static String generatorPrivateKeyString() {
		byte[] generatorPrivateKey = generatorPrivateKey();
		ECKey eckey = ECKey.fromPrivate(generatorPrivateKey);
		return eckey.getPrivateKeyAsHex();
	}

	/**
	 * 
	 * @description 通过私钥生成公钥
	 * @param privateKey
	 *            私钥
	 * @return 公钥
	 *
	 */
	public static String getHexPubKeyFromPrivKey(String privateKey) {
		ECKey eckey = ECKey.fromPrivate(HexUtil.fromHexString(privateKey));
		byte[] pubKey = eckey.getPubKey();
		String pubKeyStr = HexUtil.toHexString(pubKey);
		return pubKeyStr;
	}

	/**
	 * 构造交易
	 * 
	 * @param transaction
	 * @return
	 */
	public static byte[] encodeProtobuf(Transaction transaction) {
		TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();

		builder.setExecer(ByteString.copyFrom(transaction.getExecer()));
		builder.setExpire(transaction.getExpire());
		builder.setFee(transaction.getFee());
		builder.setNonce(transaction.getNonce());
		builder.setPayload(ByteString.copyFrom(transaction.getPayload()));
		builder.setTo(transaction.getTo());
		TransactionAllProtobuf.Transaction build = builder.build();
		byte[] byteArray = build.toByteArray();
		return byteArray;
	}

	/**
	 * 构造带签名的交易
	 * 
	 * @param transaction
	 * @return
	 */
	public static TransactionAllProtobuf.Transaction encodeProtobufWithSign2(Transaction transaction) {
		TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();

		builder.setExecer(ByteString.copyFrom(transaction.getExecer()));
		builder.setExpire(transaction.getExpire());
		builder.setFee(transaction.getFee());
		builder.setNonce(transaction.getNonce());
		builder.setPayload(ByteString.copyFrom(transaction.getPayload()));
		builder.setTo(transaction.getTo());

		TransactionAllProtobuf.Signature.Builder signatureBuilder = builder.getSignatureBuilder();
		signatureBuilder.setPubkey(ByteString.copyFrom(transaction.getSignature().getPubkey()));
		signatureBuilder.setTy(transaction.getSignature().getTy());
		signatureBuilder.setSignature(ByteString.copyFrom(transaction.getSignature().getSignature()));
		TransactionAllProtobuf.Signature signatureBuild = signatureBuilder.build();
		builder.setSignature(signatureBuild);
		TransactionAllProtobuf.Transaction build = builder.build();
		return build;
	}

	/**
	 * 构造带签名的交易
	 *
	 * @param transaction
	 * @return
	 */
	public static byte[] encodeProtobufWithSign(Transaction transaction) {
		TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();

		builder.setExecer(ByteString.copyFrom(transaction.getExecer()));
		builder.setExpire(transaction.getExpire());
		builder.setFee(transaction.getFee());
		builder.setNonce(transaction.getNonce());
		builder.setPayload(ByteString.copyFrom(transaction.getPayload()));
		builder.setTo(transaction.getTo());

		TransactionAllProtobuf.Signature.Builder signatureBuilder = builder.getSignatureBuilder();
		signatureBuilder.setPubkey(ByteString.copyFrom(transaction.getSignature().getPubkey()));
		signatureBuilder.setTy(transaction.getSignature().getTy());
		signatureBuilder.setSignature(ByteString.copyFrom(transaction.getSignature().getSignature()));
		TransactionAllProtobuf.Signature signatureBuild = signatureBuilder.build();
		builder.setSignature(signatureBuild);
		TransactionAllProtobuf.Transaction build = builder.build();
		byte[] byteArray = build.toByteArray();
		return byteArray;
	}
	/**
	 * 签名
	 * 
	 * @param signType
	 *            签名类型
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @param transaction
	 *            交易
	 */
	private static void sign(SignType signType, byte[] data, byte[] privateKey, byte[] uid, Transaction transaction) {
		switch (signType) {
		case SECP256K1: {
			Signature btcCoinSign = btcCoinSign(data, privateKey);
			transaction.setSignature(btcCoinSign);
		}
			break;
		case SM2: {
			SM2KeyPair keyPair = SM2Util.fromPrivateKey(privateKey);
			byte[] derSignBytes;
			try {
				derSignBytes = SM2Util.sign(data, uid, keyPair);
			} catch (IOException e) {
				break;
			}
			Signature signature = new Signature();
			signature.setPubkey(keyPair.getPublicKey().getEncoded(true));
			signature.setSignature(derSignBytes);
			signature.setTy(signType.getType());
			transaction.setSignature(signature);
		}
			break;
		case ED25519: {
			Ecc25519Helper helper1 = new Ecc25519Helper(privateKey);
			byte[] publicKey = helper1.getKeyHolder().getPublicKeySignature();
			byte[] sign = helper1.sign(data);
			Signature signature = new Signature();
			signature.setPubkey(publicKey);
			signature.setSignature(sign);
			signature.setTy(signType.getType());
			transaction.setSignature(signature);
		}
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @description 本地签名
	 * @param privateKey
	 *            私钥
	 * @param expire
	 *            秒数
	 * @param txHex
	 *            上一步CreateNoBalanceTransaction生成的交易hash 16进制
	 * @param index
	 *            是签名交易组，则为要签名的交易序号，从1开始，小于等于0则为签名组内全部交易
	 * @return
	 *
	 */
	public static String signRawTx(String privateKey, long expire, String txHex, Integer index) throws Exception {
		// 1.检查私钥是否存在 ->存在：->byte
		if (StringUtil.isEmpty(privateKey)) {
			throw new Exception("privateKey not Exist");
		}
		byte[] privKeyBytes = HexUtil.fromHexString(privateKey);
		RawTransactionProtobuf.Transaction.Builder txBuilder = RawTransactionProtobuf.Transaction.newBuilder();
		RawTransactionProtobuf.Transaction rawtransactionProtobuf = txBuilder.mergeFrom(HexUtil.fromHexString(txHex))
				.build();
		long changedExpire = getExpire(expire);
		txBuilder.setExpire(changedExpire);
		// 如果执行器为privacy 暂时不处理
		/*
		 * if(Arrays.equals(ExecerPrivacy,rawtransactionProtobuf.getExecer().
		 * toByteArray ())) { //signTxWithPrivacy }
		 */
		int groupCount = rawtransactionProtobuf.getGroupCount();
		if (groupCount < 0 || groupCount == 1 || groupCount > 20) {
			throw new Exception("ErrTxGroupCount");
		} else if (groupCount > 0) {
			byte[] txsBytes = rawtransactionProtobuf.getHeader().toByteArray();
			RawTransactionProtobuf.Transactions.Builder txsBuilder = RawTransactionProtobuf.Transactions.newBuilder();
			RawTransactionProtobuf.Transactions txs = txsBuilder.mergeFrom(txsBytes).build();
			List<RawTransactionProtobuf.Transaction> txsList = txs.getTxsList();
			if (index > txsList.size()) {
				throw new Exception("ErrIndex");
			}
			if (index <= 0) {
				for (int i = 0; i < txsList.size(); i++) {
					RawTransactionProtobuf.Transaction signTransactionsN = signTransactionsN(i, txsList, privKeyBytes);
					txsList.set(i, signTransactionsN);
				}
				RawTransactionProtobuf.Transaction transaction = txsList.get(0);
				transaction.toBuilder().setHeader(ByteString.copyFrom(txs.toByteArray()));
				byte[] byteArray = transaction.toByteArray();
				String signHexString = HexUtil.toHexString(byteArray);
				return signHexString;
			}
			index--;
			RawTransactionProtobuf.Transaction signTransactionsN = signTransactionsN(index, txsList, privKeyBytes);
			txsList.set(index, signTransactionsN);
			RawTransactionProtobuf.Transaction transactionFirst = txsList.get(0);
			transactionFirst.toBuilder().setHeader(ByteString.copyFrom(txs.toByteArray()));
			byte[] byteArray = transactionFirst.toByteArray();
			String signHexString = HexUtil.toHexString(byteArray);
			return signHexString;
		} else {
			byte[] rawTxProtobufBytes = txBuilder.build().toByteArray();
			RawTransactionProtobuf.Signature signatureProtobuf = signRawTx(rawTxProtobufBytes, privKeyBytes, txBuilder);
			txBuilder.setSignature(signatureProtobuf);
			String signedTx = HexUtil.toHexString(txBuilder.build().toByteArray());
			return signedTx;
		}
	}

	private static RawTransactionProtobuf.Transaction signTransactionsN(int n,
			List<RawTransactionProtobuf.Transaction> transactionList, byte[] privKeyBytes) {
		RawTransactionProtobuf.Transaction.Builder newTxBuilder = transactionList.get(n).toBuilder();
		RawTransactionProtobuf.Signature txSignature = signRawTx(transactionList.get(n).toByteArray(), privKeyBytes,
				newTxBuilder);
		newTxBuilder.setSignature(txSignature);
		return newTxBuilder.build();
	}

	private static RawTransactionProtobuf.Signature signRawTx(byte[] data, byte[] privateKey,
			RawTransactionProtobuf.Transaction.Builder txBuilder) {
		Signature btcCoinSign = btcCoinSign(data, privateKey);
		RawTransactionProtobuf.Signature.Builder signatureBuilder = RawTransactionProtobuf.Signature.newBuilder();
		signatureBuilder.setPubkey(ByteString.copyFrom(btcCoinSign.getPubkey()));
		signatureBuilder.setTy(btcCoinSign.getTy());
		signatureBuilder.setSignature(ByteString.copyFrom(btcCoinSign.getSignature()));
		RawTransactionProtobuf.Signature signatureProtuBuff = signatureBuilder.build();
		return signatureProtuBuff;
	}

	public static String addressToString(Address address) {
		if (StringUtil.isEmpty(address.getEnc58Str())) {
			byte[] ad = new byte[25];
			ad[0] = address.getVersion();
			for (int i = 1; i < 21; i++) {
				ad[i] = address.getHash160()[i - 1];
			}
			byte[] checkSum = getAddressSh(ad);
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
	 * @description 数据处理,sha256 2次
	 * 
	 * @param sourceByte
	 */
	private static byte[] getAddressSh(byte[] sourceByte) {
		byte[] subByteArr = TransactionUtil.subByteArr(sourceByte, 0, 21);
		byte[] sha256_1 = TransactionUtil.Sha256(subByteArr);
		byte[] sha256_2 = TransactionUtil.Sha256(sha256_1);
		return sha256_2;
	}

	public static byte[] ripemd160(byte[] sourceByte) {
		byte[] hash = Ripemd160Util.getHash(sourceByte);
		return hash;
	}

	private static Signature btcCoinSign(byte[] data, byte[] privateKey) {
		byte[] sha256 = TransactionUtil.Sha256(data);
		Sha256Hash sha256Hash = Sha256Hash.wrap(sha256);
		ECKey ecKey = ECKey.fromPrivate(privateKey);
		ECKey.ECDSASignature ecdsas = ecKey.sign(sha256Hash);
		byte[] signByte = ecdsas.encodeToDER();
		Signature signature = new Signature();
		signature.setPubkey(ecKey.getPubKey());
		signature.setSignature(signByte);
		signature.setTy(SignType.SECP256K1.getType());
		return signature;
	}

	public static TransactionAllProtobuf.Transaction decodeTxToProtobuf(DecodeRawTransaction unSignedTransaction,
			String execerAddress) {
		TransactionAllProtobuf.Transaction.Builder newBuilder = TransactionAllProtobuf.Transaction.newBuilder();
		newBuilder.setExecer(ByteString.copyFrom(unSignedTransaction.getExecer().getBytes()));
		newBuilder.setExpire(unSignedTransaction.getExpire());
		newBuilder.setFee(unSignedTransaction.getFee());
		newBuilder.setNonce(unSignedTransaction.getNonce());
		newBuilder.setPayload(ByteString.copyFrom(HexUtil.fromHexString(unSignedTransaction.getRawPayload())));
		if (StringUtil.isEmpty(execerAddress)) {
			newBuilder.setTo(unSignedTransaction.getTo());
		} else {
			newBuilder.setTo(execerAddress);
		}
		newBuilder.setHeader(ByteString.copyFrom(HexUtil.fromHexString(unSignedTransaction.getHeader())));
		newBuilder.setNonce(unSignedTransaction.getNonce());
		if (StringUtil.isNotEmpty(unSignedTransaction.getNext())) {
			newBuilder.setNext(ByteString.copyFrom(HexUtil.fromHexString(unSignedTransaction.getNext())));
		}
		if (unSignedTransaction.getPayload() == null) {
			newBuilder.setPayload(ByteString.copyFrom(HexUtil.fromHexString(unSignedTransaction.getRawPayload())));
		}
		if (unSignedTransaction.getGroupCount() != null) {
			newBuilder.setGroupCount(unSignedTransaction.getGroupCount());
		}
		TransactionAllProtobuf.Signature.Builder signatureBuilder = TransactionAllProtobuf.Signature.newBuilder();
		signatureBuilder.setTy(unSignedTransaction.getSignature().getTy());
		signatureBuilder
				.setPubkey(ByteString.copyFrom(HexUtil.fromHexString(unSignedTransaction.getSignature().getPubkey())));
		signatureBuilder.setSignature(
				ByteString.copyFrom(HexUtil.fromHexString(unSignedTransaction.getSignature().getSignature())));
		newBuilder.setSignature(signatureBuilder.build());
		return newBuilder.build();
	}

	/**
	 * 重组并签名交易组
	 * 
	 * @param decodeRawTransactions
	 * @param execerAddress
	 * @param fromAddressPriveteKey
	 * @param withHoldPrivateKey
	 * @return
	 */
	public static String signDecodeTx(List<DecodeRawTransaction> decodeRawTransactions, String execerAddress,
			String fromAddressPriveteKey, String withHoldPrivateKey) {
		DecodeRawTransaction unSignedTransaction = null;
		DecodeRawTransaction signedSeconedTx = null;
		for (DecodeRawTransaction decodeRawTransaction2 : decodeRawTransactions) {
			if (StringUtil.isEmpty(decodeRawTransaction2.getSignature().getSignature())) {
				unSignedTransaction = decodeRawTransaction2;
			} else {
				signedSeconedTx = decodeRawTransaction2;
			}
		}
		// 签名none交易 用代扣地址签名

		TransactionAllProtobuf.Transaction noneTx = decodeTxToProtobuf(unSignedTransaction, null);
		TransactionAllProtobuf.Transaction unNoneTx = TransactionUtil.decodeTxToProtobuf(signedSeconedTx, execerAddress);
		TransactionAllProtobuf.Transaction.Builder unNoneTxBuilder = unNoneTx.toBuilder();

		String unNoneHash = TransactionUtil.getHash(unNoneTxBuilder.build(), execerAddress);

		TransactionAllProtobuf.Transaction.Builder noneBuilder = TransactionAllProtobuf.Transaction.newBuilder(noneTx);
		noneBuilder.setNext(ByteString.copyFrom(HexUtil.fromHexString(unNoneHash)));
		// noneBuilder.setGroupCount(2);
		String noneHash = TransactionUtil.getHash(noneBuilder.build());
		noneBuilder.setHeader(ByteString.copyFrom(HexUtil.fromHexString(noneHash)));

		unNoneTxBuilder.setHeader(ByteString.copyFrom(HexUtil.fromHexString(noneHash)));
		TransactionAllProtobuf.Transaction firstTxNew = unNoneTxBuilder.build();

		TransactionAllProtobuf.Transaction noneTxNew = noneBuilder.build();
		noneTxNew = TransactionUtil.signProbuf(noneTxNew, withHoldPrivateKey);
		firstTxNew = TransactionUtil.signProbuf(firstTxNew, fromAddressPriveteKey);

		// 创建交易组
		TransactionAllProtobuf.Transactions.Builder txsBuilder = TransactionAllProtobuf.Transactions.newBuilder();
		txsBuilder.addTxs(noneTxNew);
		txsBuilder.addTxs(firstTxNew);
		TransactionAllProtobuf.Transactions txs = txsBuilder.build();
		TransactionAllProtobuf.Transaction.Builder thirdBuilder = TransactionAllProtobuf.Transaction.newBuilder(noneTxNew);
		thirdBuilder.setHeader(ByteString.copyFrom(txs.toByteArray()));
		TransactionAllProtobuf.Transaction submitTx = thirdBuilder.build();
		String groupTx = HexUtil.toHexString(submitTx.toByteArray());
		return groupTx;
	}
	
	public static String getHash(TransactionAllProtobuf.Transaction transaction) {
		TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();
		if (transaction.getPayload() != ByteString.EMPTY) {
			builder.setPayload(transaction.getPayload());
		}
		builder.setExecer(transaction.getExecer());
		builder.setFee(transaction.getFee());
		builder.setExpire(transaction.getExpire());
		builder.setNonce(transaction.getNonce());
		builder.setTo(transaction.getTo());
		builder.setGroupCount(transaction.getGroupCount());
		if (transaction.getNext() != ByteString.EMPTY) {
			builder.setNext(transaction.getNext());
		}
		TransactionAllProtobuf.Transaction build = builder.build();
		byte[] byteArray = build.toByteArray();
		return HexUtil.toHexString(Sha256(byteArray));
	}

	public static String getHash(TransactionAllProtobuf.Transaction transaction, String to) {
		TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();
		if (transaction.getPayload() != ByteString.EMPTY) {
			builder.setPayload(transaction.getPayload());
		}
		builder.setExecer(transaction.getExecer());
		builder.setFee(transaction.getFee());
		builder.setExpire(0L);
		builder.setNonce(transaction.getNonce());
		if (!StringUtil.isEmpty(to)) {
			builder.setTo(to);
		} else {
			builder.setTo(transaction.getTo());
		}

		builder.setGroupCount(transaction.getGroupCount());
		if (transaction.getNext() != ByteString.EMPTY) {
			builder.setNext(transaction.getNext());
		}
		TransactionAllProtobuf.Transaction build = builder.build();
		byte[] byteArray = build.toByteArray();
		return HexUtil.toHexString(Sha256(byteArray));
	}

	/**
	 * 
	 * @description 签名
	 * @param tx
	 * @param privateKey
	 * @return
	 *
	 * @create 2020年1月9日 下午6:35:16
	 */
	public static TransactionAllProtobuf.Transaction signProbuf(TransactionAllProtobuf.Transaction tx, String privateKey) {
		TransactionAllProtobuf.Transaction encodeTx = getSignProbuf(tx);
		byte[] protobufData = encodeTx.toByteArray();
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		Signature btcCoinSign = btcCoinSign(protobufData, privateKeyBytes);
		TransactionAllProtobuf.Transaction.Builder builder = tx.toBuilder();
		TransactionAllProtobuf.Signature.Builder signatureBuilder = TransactionAllProtobuf.Signature.newBuilder();
		signatureBuilder.setPubkey(ByteString.copyFrom(btcCoinSign.getPubkey()));
		signatureBuilder.setTy(btcCoinSign.getTy());
		signatureBuilder.setSignature(ByteString.copyFrom(btcCoinSign.getSignature())); // 序列化
		TransactionAllProtobuf.Transaction.Builder setSignature = builder.setSignature(signatureBuilder.build());
		return setSignature.build();
	}

	/**
	 * 
	 * @description 获取签名需要的protobuf
	 * @param tx
	 * @return
	 *
	 * @author lgang
	 * @create 2020年1月9日 下午6:35:30
	 */
	public static TransactionAllProtobuf.Transaction getSignProbuf(TransactionAllProtobuf.Transaction tx) {
		TransactionAllProtobuf.Transaction.Builder builder = TransactionAllProtobuf.Transaction.newBuilder();
		builder.setExecer(tx.getExecer());
		builder.setExpire(tx.getExpire());
		builder.setFee(tx.getFee());
		builder.setNonce(tx.getNonce());
		builder.setPayload(tx.getPayload());
		builder.setTo(tx.getTo());
		if (tx.getNext() != null) {
			builder.setNext(tx.getNext());
		}
		if (tx.getHeader() != null) {
			builder.setHeader(tx.getHeader());
		}
		if (tx.getGroupCount() != 0) {
			builder.setGroupCount(tx.getGroupCount());
		}
		TransactionAllProtobuf.Transaction build = builder.build();
		return build;
	}
	
	/**
	 * 创建并签名管理合约交易
	 * @param key
	 * @param value 
	 * @param op 操作符，add或delete
	 * @return
	 */
    public static String createManage(String key, String value, String op, String privateKey, String execer) {
        Builder managerBuilder = ManageProtobuf.ModifyConfig.newBuilder();
        managerBuilder.setKey(key);
        managerBuilder.setValue(value);
        managerBuilder.setOp(op);
        cn.chain33.javasdk.model.protobuf.ManageProtobuf.ManageAction.Builder actionBuilder = ManageProtobuf.ManageAction.newBuilder();
        actionBuilder.setTy(0);
        actionBuilder.setModify(managerBuilder.build());
        ManageAction managerAction = actionBuilder.build();

        String createTxWithoutSign = TransactionUtil.createTxWithoutSign(execer.getBytes(), managerAction.toByteArray(),
                DEFAULT_FEE, 0);
        byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
        TransactionAllProtobuf.Transaction parseFrom = null;
        try {
            parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return null;
        }
        TransactionAllProtobuf.Transaction signProbuf = signProbuf(parseFrom, privateKey);
        return HexUtil.toHexString(signProbuf.toByteArray());
    }
    
    
    /**
     * 
     * @description 将执行器名称转为地址
     * @param exec 执行器名称
     * @return
     *
     */
    public static String convertExectoAddr(String exec) {
        String toAddress = getToAddress(exec.getBytes());
        return toAddress;
    }

    public static String getExecerAddress(String exec) {
        String toAddress = getToAddress(exec.getBytes());
        return toAddress;
    }

    /**
     * 
     * @description 本地构造 预创建积分交易
     * @param execer        user.p.xxx.token
     * @param name         token名称
     * @param symbol       tokenSymbol
     * @param introduction token介绍
     * @param total        发行总量,需要乘以10的8次方，比如要发行100个币，需要100*1e8
     * @param price        发行该token愿意承担的费用
     * @param owner        token地址拥有者
     * @param category     token属性类别， 0 为普通token， 1 可增发和燃烧
     * @param privateKey   超级管理员私钥
     * @return 交易
     *
     */
    public static String createPrecreateTokenTx(String execer, String name, String symbol, String introduction,
            Long total, Long price, String owner, Integer category, String privateKey) {
        TokenActionProtoBuf.TokenPreCreate.Builder precreateBuilder = TokenActionProtoBuf.TokenPreCreate.newBuilder();
        precreateBuilder.setName(name);
        precreateBuilder.setSymbol(symbol);
        precreateBuilder.setIntroduction(introduction);
        precreateBuilder.setTotal(total);
        precreateBuilder.setPrice(price);
        precreateBuilder.setOwner(owner);
        precreateBuilder.setCategory(category);
        TokenPreCreate tokenPreCreate = precreateBuilder.build();
        TokenActionProtoBuf.TokenAction.Builder tokenActionBuilder = TokenActionProtoBuf.TokenAction.newBuilder();
        tokenActionBuilder.setTy(7);
        tokenActionBuilder.setTokenPreCreate(tokenPreCreate);
        TokenAction tokenAction = tokenActionBuilder.build();
        String createTxWithoutSign = TransactionUtil.createTxWithoutSign(execer.getBytes(), tokenAction.toByteArray(),
                DEFAULT_FEE, 0);
        byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
        TransactionAllProtobuf.Transaction parseFrom = null;
        try {
            parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return null;
        }
        TransactionAllProtobuf.Transaction signProbuf = signProbuf(parseFrom, privateKey);
        return HexUtil.toHexString(signProbuf.toByteArray());
    }

    /**
     * 
     * @description 本地创建token完成交易
     * @param symbol            token地址
     * @param execer            user.p.xxx.token
     * @param ownerAddr         token拥有者地址
     * @param managerPrivateKey 超级管理员私钥
     * @return 交易
     *
     */
    public static String createTokenFinishTx(String symbol, String execer, String ownerAddr, String managerPrivateKey) {
        TokenActionProtoBuf.TokenFinishCreate.Builder payloadBuilder = TokenActionProtoBuf.TokenFinishCreate
                .newBuilder();
        payloadBuilder.setSymbol(symbol);
        payloadBuilder.setOwner(ownerAddr);
        TokenFinishCreate tokenFinishCreate = payloadBuilder.build();
        TokenActionProtoBuf.TokenAction.Builder tokenActionBuilder = TokenActionProtoBuf.TokenAction.newBuilder();
        tokenActionBuilder.setTy(8);
        tokenActionBuilder.setTokenFinishCreate(tokenFinishCreate);
        TokenAction tokenAction = tokenActionBuilder.build();
        String createTxWithoutSign = TransactionUtil.createTxWithoutSign(execer.getBytes(), tokenAction.toByteArray(),
                DEFAULT_FEE, 0);
        byte[] fromHexString = HexUtil.fromHexString(createTxWithoutSign);
        TransactionAllProtobuf.Transaction parseFrom = null;
        try {
            parseFrom = TransactionAllProtobuf.Transaction.parseFrom(fromHexString);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        TransactionAllProtobuf.Transaction signProbuf = TransactionUtil.signProbuf(parseFrom, managerPrivateKey);
        String hexString = HexUtil.toHexString(signProbuf.toByteArray());
        return hexString;
    }
    
	/**
	 * 
	 * @description 本地创建并签名token转账交易
	 * @param privateKey
	 * @param toAddress
	 * @param execer
	 * @param amount
	 * @param coinToken
	 * @param note
	 * @return
	 *
	 */
	public static String createTokenTransferTx(String privateKey, String toAddress, String execer, Long amount, String coinToken, String note) {
		
		byte[] payload = createtTokenTransferPayLoad(toAddress, amount, coinToken, note);
		
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		return createTxMain(privateKeyBytes, toAddress, execer.getBytes(), payload, DEFAULT_SIGNTYPE, DEFAULT_FEE);
	}
	
	/**
	 * 
	 * @description 本地创建token转账payload
	 * @param to  目标地址
	 * @param amount 数量
	 * @param coinToken token symbol
	 * @param note 备注，没有为""
	 * @return payload
	 */
	public static byte[] createtTokenTransferPayLoad(String to, Long amount, String coinToken, String note) {
		TokenActionProtoBuf.AssetsTransfer.Builder assetsTransferBuilder = TokenActionProtoBuf.AssetsTransfer.newBuilder();
		assetsTransferBuilder.setCointoken(coinToken);
		assetsTransferBuilder.setAmount(amount);
		try {
			assetsTransferBuilder.setNote(ByteString.copyFrom(note, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assetsTransferBuilder.setTo(to);
		cn.chain33.javasdk.model.protobuf.TokenActionProtoBuf.AssetsTransfer assetsTransfer = assetsTransferBuilder.build();
		TokenActionProtoBuf.TokenAction.Builder tokenActionBuilder = TokenActionProtoBuf.TokenAction.newBuilder();
		tokenActionBuilder.setTy(4);
		tokenActionBuilder.setTransfer(assetsTransfer);
		TokenAction tokenAction = tokenActionBuilder.build();
		byte[] payload = tokenAction.toByteArray();
		return payload;
	}

    /**
     * 创建交易,不签名 默认使用比特币seck256K1
     * 
     * @param execer     执行器名称
     * @param payLoad    内容
     * @return
     */
    public static String createTxWithoutSign(String execer, String payLoad) {
        return createTxWithoutSign(execer.getBytes(), payLoad.getBytes(), DEFAULT_FEE, 0);
    }

    /**
     * 创建没有签名的交易
     * 
     * @param execer
     * @param payLoad
     * @param fee
	 * @param txHeight
     * @return
     */
    public static String createTxWithoutSign(byte[] execer, byte[] payLoad, long fee, long txHeight) {
        Transaction transation = new Transaction();
        transation.setExecer(execer);
        transation.setPayload(payLoad);
        transation.setFee(fee);
        transation.setExpire(TX_HEIGHT_OFFSET + txHeight);
        transation.setNonce(TransactionUtil.getRandomNonce());
        // 计算To
        String toAddress = getToAddress(execer);
        transation.setTo(toAddress);
        // 签名
        byte[] protobufData = encodeProtobuf(transation);

        // 序列化
        String transationStr = HexUtil.toHexString(protobufData);
        return transationStr;
    }

    public static WasmProtobuf.wasmAction createWasmContract(String name,byte[] codes) {
    	WasmProtobuf.wasmAction.Builder action = WasmProtobuf.wasmAction.newBuilder();
		WasmProtobuf.wasmCreate.Builder builder = WasmProtobuf.wasmCreate.newBuilder();
		builder.setName(name);
		builder.setCode(ByteString.copyFrom(codes));
		action.setCreate(builder);
		action.setTy(1);
		return action.build();
	}

	public static WasmProtobuf.wasmAction createWasmCallContract(String name, String method, int[] parameters) {
		WasmProtobuf.wasmAction.Builder action = WasmProtobuf.wasmAction.newBuilder();
		WasmProtobuf.wasmCall.Builder builder = WasmProtobuf.wasmCall.newBuilder();
		builder.setContract(name);
		builder.setMethod(method);
		for(int i = 0; i < parameters.length; i++) {
			builder.addParameters(parameters[i]);
		}
		action.setCall(builder);
		action.setTy(2);
		return action.build();
	}
}