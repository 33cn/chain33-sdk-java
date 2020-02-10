package cn.chain33.javasdk.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

import cn.chain33.javasdk.model.Address;
import cn.chain33.javasdk.model.Signature;
import cn.chain33.javasdk.model.Transaction;
import cn.chain33.javasdk.model.TransferBalanceRequest;
import cn.chain33.javasdk.model.decode.DecodeRawTransaction;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.ManageProtobuf;
import cn.chain33.javasdk.model.protobuf.ManageProtobuf.ManageAction;
import cn.chain33.javasdk.model.protobuf.ManageProtobuf.ModifyConfig.Builder;
import cn.chain33.javasdk.model.protobuf.RawTransactionProtobuf;
import cn.chain33.javasdk.model.protobuf.TransactionProtoBuf;
import cn.chain33.javasdk.model.protobuf.TransferProtoBuf;
import cn.chain33.javasdk.model.protobuf.TransferProtoBuf.AssetsTransfer;
import cn.chain33.javasdk.model.protobuf.TransferProtoBuf.CoinsAction;
import cn.chain33.javasdk.model.sm2.SM2;
import cn.chain33.javasdk.model.sm2.SM2.SM2Signature;
import cn.chain33.javasdk.model.sm2.SM2KeyPair;
import net.vrallev.java.ecc.Ecc25519Helper;

/**
 * 
 * @author logan 2018年5月14日
 */
public class TransactionUtil {

	private static final SignType DEFAULT_SIGNTYPE = SignType.SECP256K1;

	public static final long DEFAULT_FEE = 1000000;

	private final static Long TX_HEIGHT_OFFSET = 1L << 62;

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
		Random random = new Random(System.currentTimeMillis());
		return Math.abs(random.nextLong());
	}

	/**
	 * 
	 * @description 本地创建转账payload
	 * @param to
	 *            目标地址
	 * @param amount
	 *            数量
	 * @param coinToken
	 *            主代币则为"",其他为token symbol
	 * @param note
	 *            备注，没有为""
	 * @return payload
	 */
	public static byte[] createTransferPayLoad(String to, Long amount, String coinToken, String note) {
		TransferProtoBuf.AssetsTransfer.Builder assetsTransferBuilder = TransferProtoBuf.AssetsTransfer.newBuilder();
		assetsTransferBuilder.setCointoken(coinToken);
		assetsTransferBuilder.setAmount(amount);
		try {
			assetsTransferBuilder.setNote(ByteString.copyFrom(note, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		assetsTransferBuilder.setTo(to);
		AssetsTransfer assetsTransfer = assetsTransferBuilder.build();
		TransferProtoBuf.CoinsAction.Builder coinsActionBuilder = TransferProtoBuf.CoinsAction.newBuilder();
		coinsActionBuilder.setTy(1);
		coinsActionBuilder.setTransfer(assetsTransfer);
		CoinsAction coinsAction = coinsActionBuilder.build();
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
		Transaction transation = new Transaction();
		transation.setExecer(execer);
		transation.setPayload(payLoad);
		transation.setFee(fee);
		transation.setNonce(TransactionUtil.getRandomNonce());
		// 计算To
		transation.setTo(toAddress);
		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, privateKey, transation);
		// 序列化
		byte[] encodeProtobufWithSign = encodeProtobufWithSign(transation);
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
		Transaction transation = new Transaction();
		transation.setExecer(execer);
		transation.setPayload(payLoad);
		transation.setFee(fee);
		transation.setNonce(TransactionUtil.getRandomNonce());
		if (txHeight != null) {
			transation.setExpire(txHeight + TX_HEIGHT_OFFSET);
		}
		// 计算To
		transation.setTo(toAddress);
		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, privateKey, transation);
		// 序列化
		byte[] encodeProtobufWithSign = encodeProtobufWithSign(transation);
		String transationHash = HexUtil.toHexString(encodeProtobufWithSign);
		return transationHash;
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
		TransactionProtoBuf.Transaction.Builder builder = TransactionProtoBuf.Transaction.newBuilder();

		builder.setExecer(ByteString.copyFrom(transaction.getExecer()));
		builder.setExpire(transaction.getExpire());
		builder.setFee(transaction.getFee());
		builder.setNonce(transaction.getNonce());
		builder.setPayload(ByteString.copyFrom(transaction.getPayload()));
		builder.setTo(transaction.getTo());
		TransactionProtoBuf.Transaction build = builder.build();
		byte[] byteArray = build.toByteArray();
		return byteArray;
	}

	/**
	 * 构造带签名的交易
	 * 
	 * @param transaction
	 * @return
	 */
	public static byte[] encodeProtobufWithSign(Transaction transaction) {
		TransactionProtoBuf.Transaction.Builder builder = TransactionProtoBuf.Transaction.newBuilder();

		builder.setExecer(ByteString.copyFrom(transaction.getExecer()));
		builder.setExpire(transaction.getExpire());
		builder.setFee(transaction.getFee());
		builder.setNonce(transaction.getNonce());
		builder.setPayload(ByteString.copyFrom(transaction.getPayload()));
		builder.setTo(transaction.getTo());

		TransactionProtoBuf.Signature.Builder signatureBuilder = builder.getSignatureBuilder();
		signatureBuilder.setPubkey(ByteString.copyFrom(transaction.getSignature().getPubkey()));
		signatureBuilder.setTy(transaction.getSignature().getTy());
		signatureBuilder.setSignature(ByteString.copyFrom(transaction.getSignature().getSignature()));
		TransactionProtoBuf.Signature signatureBuild = signatureBuilder.build();
		builder.setSignature(signatureBuild);
		TransactionProtoBuf.Transaction build = builder.build();
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
	private static void sign(SignType signType, byte[] data, byte[] privateKey, Transaction transaction) {
		switch (signType) {
		case SECP256K1: {
			Signature btcCoinSign = btcCoinSign(data, privateKey);
			transaction.setSignature(btcCoinSign);
		}
			break;
		case SM2: {
			SM2KeyPair keyPair = SM2.fromPrivateKey(privateKey);
			SM2Signature sign = SM2.sign(data, null, keyPair);
			try {
				byte[] derSignBytes = SM2.derByteStream(sign.getR(), sign.getS()).toByteArray();
				Signature signature = new Signature();
				signature.setPubkey(keyPair.getPublicKey().getEncoded(false));
				signature.setSignature(derSignBytes);
				signature.setTy(signType.getType());
				transaction.setSignature(signature);
			} catch (IOException e) {
				e.printStackTrace();
			}

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

	public static TransactionProtoBuf.Transaction decodeTxToProtobuf(DecodeRawTransaction unSignedTransaction,
			String execerAddress) {
		TransactionProtoBuf.Transaction.Builder newBuilder = TransactionProtoBuf.Transaction.newBuilder();
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
		TransactionProtoBuf.Signature.Builder signatureBuilder = TransactionProtoBuf.Signature.newBuilder();
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

		TransactionProtoBuf.Transaction noneTx = decodeTxToProtobuf(unSignedTransaction, null);
		TransactionProtoBuf.Transaction unNoneTx = TransactionUtil.decodeTxToProtobuf(signedSeconedTx, execerAddress);
		TransactionProtoBuf.Transaction.Builder unNoneTxBuilder = unNoneTx.toBuilder();

		String unNoneHash = TransactionUtil.getHash(unNoneTxBuilder.build(), execerAddress);

		TransactionProtoBuf.Transaction.Builder noneBuilder = TransactionProtoBuf.Transaction.newBuilder(noneTx);
		noneBuilder.setNext(ByteString.copyFrom(HexUtil.fromHexString(unNoneHash)));
		// noneBuilder.setGroupCount(2);
		String noneHash = TransactionUtil.getHash(noneBuilder.build());
		noneBuilder.setHeader(ByteString.copyFrom(HexUtil.fromHexString(noneHash)));

		unNoneTxBuilder.setHeader(ByteString.copyFrom(HexUtil.fromHexString(noneHash)));
		TransactionProtoBuf.Transaction firstTxNew = unNoneTxBuilder.build();

		TransactionProtoBuf.Transaction noneTxNew = noneBuilder.build();
		noneTxNew = TransactionUtil.signProbuf(noneTxNew, withHoldPrivateKey);
		firstTxNew = TransactionUtil.signProbuf(firstTxNew, fromAddressPriveteKey);

		// 创建交易组
		TransactionProtoBuf.Transactions.Builder txsBuilder = TransactionProtoBuf.Transactions.newBuilder();
		txsBuilder.addTxs(noneTxNew);
		txsBuilder.addTxs(firstTxNew);
		TransactionProtoBuf.Transactions txs = txsBuilder.build();
		TransactionProtoBuf.Transaction.Builder thirdBuilder = TransactionProtoBuf.Transaction.newBuilder(noneTxNew);
		thirdBuilder.setHeader(ByteString.copyFrom(txs.toByteArray()));
		TransactionProtoBuf.Transaction submitTx = thirdBuilder.build();
		String groupTx = HexUtil.toHexString(submitTx.toByteArray());
		return groupTx;
	}
	
	public static String getHash(TransactionProtoBuf.Transaction transaction) {
		TransactionProtoBuf.Transaction.Builder builder = TransactionProtoBuf.Transaction.newBuilder();
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
		TransactionProtoBuf.Transaction build = builder.build();
		byte[] byteArray = build.toByteArray();
		return HexUtil.toHexString(Sha256(byteArray));
	}

	public static String getHash(TransactionProtoBuf.Transaction transaction, String to) {
		TransactionProtoBuf.Transaction.Builder builder = TransactionProtoBuf.Transaction.newBuilder();
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
		TransactionProtoBuf.Transaction build = builder.build();
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
	public static TransactionProtoBuf.Transaction signProbuf(TransactionProtoBuf.Transaction tx, String privateKey) {
		TransactionProtoBuf.Transaction encodeTx = getSignProbuf(tx);
		byte[] protobufData = encodeTx.toByteArray();
		byte[] privateKeyBytes = HexUtil.fromHexString(privateKey);
		Signature btcCoinSign = btcCoinSign(protobufData, privateKeyBytes);
		TransactionProtoBuf.Transaction.Builder builder = tx.toBuilder();
		TransactionProtoBuf.Signature.Builder signatureBuilder = TransactionProtoBuf.Signature.newBuilder();
		signatureBuilder.setPubkey(ByteString.copyFrom(btcCoinSign.getPubkey()));
		signatureBuilder.setTy(btcCoinSign.getTy());
		signatureBuilder.setSignature(ByteString.copyFrom(btcCoinSign.getSignature())); // 序列化
		TransactionProtoBuf.Transaction.Builder setSignature = builder.setSignature(signatureBuilder.build());
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
	public static TransactionProtoBuf.Transaction getSignProbuf(TransactionProtoBuf.Transaction tx) {
		TransactionProtoBuf.Transaction.Builder builder = TransactionProtoBuf.Transaction.newBuilder();
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
		TransactionProtoBuf.Transaction build = builder.build();
		return build;
	}
	
	/**
	 * 创建并签名管理合约交易
	 * @param key
	 * @param value 
	 * @param op 操作符，add或delete
	 * @return
	 */
    public static String createAndSignManage(String key, String value, String op, String privateKey, SignType signType, String execer, String toAddress) {
        Builder managerBuilder = ManageProtobuf.ModifyConfig.newBuilder();
        managerBuilder.setKey(value);
        managerBuilder.setValue(value);
        managerBuilder.setOp(value);
        cn.chain33.javasdk.model.protobuf.ManageProtobuf.ManageAction.Builder actionBuilder = ManageProtobuf.ManageAction.newBuilder();
        actionBuilder.setModify(managerBuilder.build());
        actionBuilder.setTy(1);
        ManageAction managerAction = actionBuilder.build();

        byte[] payload = managerAction.toByteArray();
        
        if (signType == null)
			signType = DEFAULT_SIGNTYPE;

		Transaction transation = new Transaction();
		transation.setExecer(execer.getBytes());
		transation.setPayload(payload);
		transation.setNonce(TransactionUtil.getRandomNonce());
		transation.setExpire(100 + TX_HEIGHT_OFFSET);
  	
		// 计算To
    	transation.setTo(toAddress);
		// 签名
		byte[] protobufData = encodeProtobuf(transation);

		sign(signType, protobufData, privateKey.getBytes(), transation);
		// 序列化
		byte[] encodeProtobufWithSign = encodeProtobufWithSign(transation);
		String transationHash = HexUtil.toHexString(encodeProtobufWithSign);
		return transationHash;
    }

}
