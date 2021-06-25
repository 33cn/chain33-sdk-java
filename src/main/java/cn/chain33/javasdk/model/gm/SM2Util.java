package cn.chain33.javasdk.model.gm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import cn.chain33.javasdk.utils.HexUtil;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.DERSequenceGenerator;
import org.spongycastle.asn1.DLSequence;

/**
 * SM2公钥加密算法实现 包括 -签名,验签 -密钥交换 -公钥加密,私钥解密
 * 
 * @author Potato
 *
 */
public class SM2Util {
	private static BigInteger n = new BigInteger(
			"FFFFFFFE" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "7203DF6B" + "21C6052B" + "53BBF409" + "39D54123", 16);
	private static BigInteger p = new BigInteger(
			"FFFFFFFE" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "00000000" + "FFFFFFFF" + "FFFFFFFF", 16);
	private static BigInteger a = new BigInteger(
			"FFFFFFFE" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "FFFFFFFF" + "00000000" + "FFFFFFFF" + "FFFFFFFC", 16);
	private static BigInteger b = new BigInteger(
			"28E9FA9E" + "9D9F5E34" + "4D5A9E4B" + "CF6509A7" + "F39789F5" + "15AB8F92" + "DDBCBD41" + "4D940E93", 16);
	private static BigInteger gx = new BigInteger(
			"32C4AE2C" + "1F198119" + "5F990446" + "6A39C994" + "8FE30BBF" + "F2660BE1" + "715A4589" + "334C74C7", 16);
	private static BigInteger gy = new BigInteger(
			"BC3736A2" + "F4F6779C" + "59BDCEE3" + "6B692153" + "D0A9877C" + "C62A4740" + "02DF32E5" + "2139F0A0", 16);

	private static int w = (int) Math.ceil(n.bitLength() * 1.0 / 2) - 1;
	private static BigInteger _2w = new BigInteger("2").pow(w);
	private static final int DIGEST_LENGTH = 32;

	private static byte[] default_uid = {0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
	public  static final byte[] Default_Uid = {0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38};
	private static SecureRandom random = new SecureRandom();
	private static ECCurve.Fp curve = new ECCurve.Fp(p, a, b, n, null);
	private static ECPoint G= curve.createPoint(gx, gy);
    private static ECDomainParameters ecc_bc_spec = new ECDomainParameters(curve, G, n);

	/**
	 * 以16进制打印字节数组
	 * 
	 * @param b
	 */
	public static void printHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase());
		}
		System.out.println();
	}

	/**
	 * 随机数生成器
	 * 
	 * @param max
	 * @return
	 */
	private static BigInteger random(BigInteger max) {

		BigInteger r = new BigInteger(256, random);
		while (r.compareTo(max) >= 0 || r.compareTo(BigInteger.ONE) <= 0) {
			r = new BigInteger(128, random);
		}

		return r;
	}

	/**
	 * 判断字节数组是否全0
	 * 
	 * @param buffer
	 * @return
	 */
	private static boolean allZero(byte[] buffer) {
		for (int i = 0; i < buffer.length; i++) {
			if (buffer[i] != 0)
				return false;
		}
		return true;
	}

	/**
	 * 公钥加密
	 * 
	 * @param input
	 *            加密原文
	 * @param publicKey
	 *            公钥
	 * @return
	 */
	public static byte[] encrypt(String input, ECPoint publicKey) {

		byte[] inputBuffer = input.getBytes();
		byte[] C1Buffer;
		ECPoint kpb;
		byte[] t;
		do {
			/* 1 产生随机数k，k属于[1, n-1] */
			BigInteger k = random(n);

			/* 2 计算椭圆曲线点C1 = [k]G = (x1, y1) */
			ECPoint C1 = G.multiply(k);
			C1Buffer = C1.getEncoded(false);

			/*
			 * 3 计算椭圆曲线点 S = [h]Pb
			 */
			BigInteger h = ecc_bc_spec.getH();
			if (h != null) {
				ECPoint S = publicKey.multiply(h);
				if (S.isInfinity())
					throw new IllegalStateException();
			}

			/* 4 计算 [k]PB = (x2, y2) */
			kpb = publicKey.multiply(k).normalize();

			/* 5 计算 t = KDF(x2||y2, klen) */
			byte[] kpbBytes = kpb.getEncoded(false);
			t = KDF(kpbBytes, inputBuffer.length);
		} while (allZero(t));

		/* 6 计算C2=M^t */
		byte[] C2 = new byte[inputBuffer.length];
		for (int i = 0; i < inputBuffer.length; i++) {
			C2[i] = (byte) (inputBuffer[i] ^ t[i]);
		}

		/* 7 计算C3 = Hash(x2 || M || y2) */
		byte[] C3 = sm3hash(kpb.getXCoord().toBigInteger().toByteArray(), inputBuffer,
				kpb.getYCoord().toBigInteger().toByteArray());

		/* 8 输出密文 C=C1 || C2 || C3 */

		byte[] encryptResult = new byte[C1Buffer.length + C2.length + C3.length];

		System.arraycopy(C1Buffer, 0, encryptResult, 0, C1Buffer.length);
		System.arraycopy(C2, 0, encryptResult, C1Buffer.length, C2.length);
		System.arraycopy(C3, 0, encryptResult, C1Buffer.length + C2.length, C3.length);

		return encryptResult;
	}

	/**
	 * 私钥解密
	 * 
	 * @param encryptData
	 *            密文数据字节数组
	 * @param privateKey
	 *            解密私钥
	 * @return
	 */
	public static String decrypt(byte[] encryptData, BigInteger privateKey) {
		byte[] C1Byte = new byte[65];
		System.arraycopy(encryptData, 0, C1Byte, 0, C1Byte.length);

		ECPoint C1 = curve.decodePoint(C1Byte).normalize();

		/*
		 * 计算椭圆曲线点 S = [h]C1 是否为无穷点
		 */
		BigInteger h = ecc_bc_spec.getH();
		if (h != null) {
			ECPoint S = C1.multiply(h);
			if (S.isInfinity())
				throw new IllegalStateException();
		}
		/* 计算[dB]C1 = (x2, y2) */
		ECPoint dBC1 = C1.multiply(privateKey).normalize();

		/* 计算t = KDF(x2 || y2, klen) */
		byte[] dBC1Bytes = dBC1.getEncoded(false);
		int klen = encryptData.length - 65 - DIGEST_LENGTH;
		byte[] t = KDF(dBC1Bytes, klen);

		if (allZero(t)) {
			System.err.println("all zero");
			throw new IllegalStateException();
		}

		/* 5 计算M'=C2^t */
		byte[] M = new byte[klen];
		for (int i = 0; i < M.length; i++) {
			M[i] = (byte) (encryptData[C1Byte.length + i] ^ t[i]);
		}

		/* 6 计算 u = Hash(x2 || M' || y2) 判断 u == C3是否成立 */
		byte[] C3 = new byte[DIGEST_LENGTH];

		System.arraycopy(encryptData, encryptData.length - DIGEST_LENGTH, C3, 0, DIGEST_LENGTH);
		byte[] u = sm3hash(dBC1.getXCoord().toBigInteger().toByteArray(), M,
				dBC1.getYCoord().toBigInteger().toByteArray());
		if (Arrays.equals(u, C3)) {
			try {
				return new String(M, "UTF8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}

	/**
	 * 判断是否在范围内
	 * 
	 * @param param
	 * @param min
	 * @param max
	 * @return
	 */
	private static boolean between(BigInteger param, BigInteger min, BigInteger max) {
		if (param.compareTo(min) >= 0 && param.compareTo(max) < 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断生成的公钥是否合法
	 * 
	 * @param publicKey
	 * @return
	 */
	private static boolean checkPublicKey(ECPoint publicKey) {

		if (!publicKey.isInfinity()) {

			BigInteger x = publicKey.getXCoord().toBigInteger();
			BigInteger y = publicKey.getYCoord().toBigInteger();

			if (between(x, new BigInteger("0"), p) && between(y, new BigInteger("0"), p)) {

				BigInteger xResult = x.pow(3).add(a.multiply(x)).add(b).mod(p);
				BigInteger yResult = y.pow(2).mod(p);

				if (yResult.equals(xResult) && publicKey.multiply(n).isInfinity()) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 通过私钥创建公钥
	 * @param privateKey
	 * @return
	 */
	public ECPoint generatePublicKey(BigInteger privateKey) {
		return G.multiply(privateKey).normalize();
	}
	
	public static SM2KeyPair fromPrivateKey(byte[] privateKey) {
		BigInteger privateKeyInteger = new BigInteger(privateKey);
		ECPoint publicKey = G.multiply(privateKeyInteger).normalize();
		return new SM2KeyPair(publicKey, privateKeyInteger);
	}

	/**
	 * 生成密钥对
	 * 
	 * @return
	 */
	public static SM2KeyPair generateKeyPair() {

		BigInteger d = random(n.subtract(new BigInteger("1")).shiftRight(1));

		SM2KeyPair keyPair = new SM2KeyPair(G.multiply(d).normalize(), d);

		if (checkPublicKey(keyPair.getPublicKey())) {
			return keyPair;
		} else {
			return null;
		}
	}

	/**
	 * 导出公钥到本地
	 * 
	 * @param publicKey
	 * @param path
	 */
	public void exportPublicKey(ECPoint publicKey, String path) {
		File file = new File(path);
		try {
			if (!file.exists())
				file.createNewFile();
			byte buffer[] = publicKey.getEncoded(false);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(buffer);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从本地导入公钥
	 * 
	 * @param path
	 * @return
	 */
	public ECPoint importPublicKey(String path) {
		File file = new File(path);
		try {
			if (!file.exists())
				return null;
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte buffer[] = new byte[16];
			int size;
			while ((size = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, size);
			}
			fis.close();
			return curve.decodePoint(baos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导出私钥到本地
	 * 
	 * @param privateKey
	 * @param path
	 */
	public void exportPrivateKey(BigInteger privateKey, String path) {
		File file = new File(path);
		try {
			if (!file.exists())
				file.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(privateKey);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从本地导入私钥
	 * 
	 * @param path
	 * @return
	 */
	public BigInteger importPrivateKey(String path) {
		File file = new File(path);
		try {
			if (!file.exists())
				return null;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BigInteger res = (BigInteger) (ois.readObject());
			ois.close();
			fis.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字节数组拼接
	 * 
	 * @param params
	 * @return
	 */
	private static byte[] join(byte[]... params) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] res = null;
		try {
			for (int i = 0; i < params.length; i++) {
				baos.write(params[i]);
			}
			res = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * sm3摘要
	 * 
	 * @param params
	 * @return
	 */
	private static byte[] sm3hash(byte[]... params) {
		byte[] res = null;
		try {
			res = SM3Util.hash(join(params));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 取得用户标识字节数组
	 * 
	 * @param idaBytes
	 * @param aPublicKey
	 * @return
	 */
	private static byte[] ZA(byte[] idaBytes, ECPoint aPublicKey) {
		int entlenA = idaBytes.length * 8;
		byte[] ENTLA = new byte[] { (byte) (entlenA & 0xFF00), (byte) (entlenA & 0x00FF) };
		byte[] ZA = sm3hash(ENTLA, idaBytes, HexUtil.byteConvert32Bytes(a), HexUtil.byteConvert32Bytes(b),
				HexUtil.byteConvert32Bytes(gx), HexUtil.byteConvert32Bytes(gy),
				HexUtil.byteConvert32Bytes(aPublicKey.getXCoord().toBigInteger()),
				HexUtil.byteConvert32Bytes(aPublicKey.getYCoord().toBigInteger()));
		return ZA;
	}

    private static ByteArrayOutputStream derByteStream(BigInteger r,BigInteger s) throws IOException {
        // Usually 70-72 bytes.
        ByteArrayOutputStream bos = new ByteArrayOutputStream(72);
        DERSequenceGenerator seq = new DERSequenceGenerator(bos);
        seq.addObject(new ASN1Integer(r));
        seq.addObject(new ASN1Integer(s));
        seq.close();
        return bos;
    }

	/**
	 * 签名
	 * 
	 * @param M
	 *            签名信息
	 * @param IDA
	 *            签名方唯一标识
	 * @param keyPair
	 *            签名方密钥对
	 * @return 签名
	 */
	public static byte[] sign(byte[] M, byte[] IDA, SM2KeyPair keyPair) throws IOException {
		if (IDA == null) {
			IDA = default_uid;
		}
		byte[] ZA = ZA(IDA, keyPair.getPublicKey());
		byte[] M_ = join(ZA, M);
		BigInteger e = new BigInteger(1, sm3hash(M_));
		BigInteger k;
		BigInteger r;
		do {
			k = random(n);
			ECPoint p1 = G.multiply(k).normalize();
			BigInteger x1 = p1.getXCoord().toBigInteger();
			r = e.add(x1);
			r = r.mod(n);
		} while (r.equals(BigInteger.ZERO) || r.add(k).equals(n));

		BigInteger s = ((keyPair.getPrivateKey().add(BigInteger.ONE).modInverse(n))
				.multiply((k.subtract(r.multiply(keyPair.getPrivateKey()))).mod(n))).mod(n);

        byte[] derSignBytes = SM2Util.derByteStream(r, s).toByteArray();
        return derSignBytes;
	}

    private static SM2Signature decodeFromDER(byte[] bytes) {
        ASN1InputStream decoder = null;

        SM2Signature var5;
        try {
            decoder = new ASN1InputStream(bytes);
            DLSequence seq = (DLSequence)decoder.readObject();
            if (seq == null) {
                throw new RuntimeException("Reached past end of ASN.1 stream.");
            }

            ASN1Integer r;
            ASN1Integer s;
            try {
                r = (ASN1Integer)seq.getObjectAt(0);
                s = (ASN1Integer)seq.getObjectAt(1);
            } catch (ClassCastException var15) {
                throw new IllegalArgumentException(var15);
            }

            var5 = new SM2Signature(r.getPositiveValue(), s.getPositiveValue());
        } catch (IOException var16) {
            throw new RuntimeException(var16);
        } finally {
            if (decoder != null) {
                try {
                    decoder.close();
                } catch (IOException var14) {
                    ;
                }
            }
        }

        return var5;
    }

    /**
	 * 验签
	 * 
	 * @param M
	 *            签名信息
	 * @param signatureByte
	 *            签名
	 * @param IDA
	 *            签名方唯一标识
	 * @param aPublicKey
	 *            签名方公钥
	 * @return true or false
	 */
	public static boolean verify(byte[] M, byte[] signatureByte, byte[] IDA, ECPoint aPublicKey) {
        SM2Signature signature = decodeFromDER(signatureByte);
		if (!between(signature.r, BigInteger.ONE, n))
			return false;
		if (!between(signature.s, BigInteger.ONE, n))
			return false;

		byte[] M_ = join(ZA(IDA, aPublicKey), M);

		BigInteger e = new BigInteger(1, sm3hash(M_));
		BigInteger t = signature.r.add(signature.s).mod(n);

		if (t.equals(BigInteger.ZERO))
			return false;

		ECPoint p1 = G.multiply(signature.s).normalize();
		ECPoint p2 = aPublicKey.multiply(t).normalize();
		BigInteger x1 = p1.add(p2).normalize().getXCoord().toBigInteger();
		BigInteger R = e.add(x1).mod(n);
		if (R.equals(signature.r))
			return true;
		return false;
	}

	/**
	 * 密钥派生函数
	 * 
	 * @param Z
	 * @param klen
	 *            生成klen字节数长度的密钥
	 * @return
	 */
	private static byte[] KDF(byte[] Z, int klen) {
		int ct = 1;
		int end = (int) Math.ceil(klen * 1.0 / 32);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			for (int i = 1; i < end; i++) {
				baos.write(sm3hash(Z, SM3Util.toByteArray(ct)));
				ct++;
			}
			byte[] last = sm3hash(Z, SM3Util.toByteArray(ct));
			if (klen % 32 == 0) {
				baos.write(last);
			} else
				baos.write(last, 0, klen % 32);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 传输实体类
	 * 
	 * @author Potato
	 *
	 */
	public static class TransportEntity implements Serializable {
		private static final long serialVersionUID = 1L;
		final byte[] R; //R点
		final byte[] S; //验证S
		final byte[] Z; //用户标识
		final byte[] K; //公钥

		public TransportEntity(byte[] r, byte[] s,byte[] z,ECPoint pKey) {
			R = r;
			S = s;
			Z=z;
			K=pKey.getEncoded(false);
		}
	}

	/**
	 * 密钥协商辅助类
	 * 
	 * @author Potato
	 *
	 */
	public static class KeyExchange {
		BigInteger rA;
		ECPoint RA;
		ECPoint V;
		byte[] Z;
		byte[] key;
		
		byte[] ID;
		SM2KeyPair keyPair;

		public KeyExchange(byte[] ID,SM2KeyPair keyPair) {
			this.ID=ID;
			this.keyPair = keyPair;
			this.Z=ZA(ID, keyPair.getPublicKey());
		}

		/**
		 * 密钥协商发起方发起
		 * 
		 * @return 发送给响应方的传输实体
		 */
		public TransportEntity InitiatorInit() {
			rA = random(n);
			RA = G.multiply(rA).normalize();
			return new TransportEntity(RA.getEncoded(false), null,Z,keyPair.getPublicKey());
		}

		/**
		 * 密钥协商响应方交换
		 * 
		 * @param entity 传输实体
		 * @return 返回给发起方的传输实体
		 */
		public TransportEntity ResponderExchange(TransportEntity entity) {
			BigInteger rB = random(n);
			ECPoint RB = G.multiply(rB).normalize();
			
			this.rA=rB;
			this.RA=RB;

			BigInteger x2 = RB.getXCoord().toBigInteger();
			x2 = _2w.add(x2.and(_2w.subtract(BigInteger.ONE)));

			BigInteger tB = keyPair.getPrivateKey().add(x2.multiply(rB)).mod(n);
			ECPoint RA = curve.decodePoint(entity.R).normalize();
			
			BigInteger x1 = RA.getXCoord().toBigInteger();
			x1 = _2w.add(x1.and(_2w.subtract(BigInteger.ONE)));

			ECPoint aPublicKey=curve.decodePoint(entity.K).normalize();
			ECPoint temp = aPublicKey.add(RA.multiply(x1).normalize()).normalize();
			ECPoint V = temp.multiply(ecc_bc_spec.getH().multiply(tB)).normalize();
			if (V.isInfinity())
				throw new IllegalStateException();
			this.V=V;
			
			byte[] xV = V.getXCoord().toBigInteger().toByteArray();
			byte[] yV = V.getYCoord().toBigInteger().toByteArray();
			byte[] KB = KDF(join(xV, yV, entity.Z, this.Z), 16);
			key = KB;

			byte[] sB = sm3hash(new byte[] { 0x02 }, yV,
					sm3hash(xV, entity.Z, this.Z, RA.getXCoord().toBigInteger().toByteArray(),
							RA.getYCoord().toBigInteger().toByteArray(), RB.getXCoord().toBigInteger().toByteArray(),
							RB.getYCoord().toBigInteger().toByteArray()));
			return new TransportEntity(RB.getEncoded(false), sB,this.Z,keyPair.getPublicKey());
		}

		/**
		 * 密钥协商发起方交换
		 * 
		 * @param entity 传输实体
		 * @return 发起方检验后的传输实体(null:检验失败)
		 */
		public TransportEntity InitiatorExchange(TransportEntity entity) {
			BigInteger x1 = RA.getXCoord().toBigInteger();
			x1 = _2w.add(x1.and(_2w.subtract(BigInteger.ONE)));

			BigInteger tA = keyPair.getPrivateKey().add(x1.multiply(rA)).mod(n);
			ECPoint RB = curve.decodePoint(entity.R).normalize();
			
			BigInteger x2 = RB.getXCoord().toBigInteger();
			x2 = _2w.add(x2.and(_2w.subtract(BigInteger.ONE)));

			ECPoint bPublicKey=curve.decodePoint(entity.K).normalize();
			ECPoint temp = bPublicKey.add(RB.multiply(x2).normalize()).normalize();
			ECPoint U = temp.multiply(ecc_bc_spec.getH().multiply(tA)).normalize();
			if (U.isInfinity())
				throw new IllegalStateException();
			this.V=U;
			
			byte[] xU = U.getXCoord().toBigInteger().toByteArray();
			byte[] yU = U.getYCoord().toBigInteger().toByteArray();
			byte[] KA = KDF(join(xU, yU,
					this.Z, entity.Z), 16);
			key = KA;

			byte[] s1= sm3hash(new byte[] { 0x02 }, yU,
					sm3hash(xU, this.Z, entity.Z, RA.getXCoord().toBigInteger().toByteArray(),
							RA.getYCoord().toBigInteger().toByteArray(), RB.getXCoord().toBigInteger().toByteArray(),
							RB.getYCoord().toBigInteger().toByteArray()));

			if(!Arrays.equals(entity.S, s1)) {
				return null;
			}

			byte[] sA = sm3hash(new byte[]{0x03}, yU,
					sm3hash(xU, this.Z, entity.Z, RA.getXCoord().toBigInteger().toByteArray(),
							RA.getYCoord().toBigInteger().toByteArray(), RB.getXCoord().toBigInteger().toByteArray(),
							RB.getYCoord().toBigInteger().toByteArray()));

			return new TransportEntity(RA.getEncoded(false), sA, this.Z, keyPair.getPublicKey());
		}
		
		/**
		 * 密钥确认响应方校验
		 * 
		 * @param entity 传输实体
		 * @return true 检验成功; false 检验失败
		 */
		public boolean ResponderValidate(TransportEntity entity) {
			byte[] xV = V.getXCoord().toBigInteger().toByteArray();
			byte[] yV = V.getYCoord().toBigInteger().toByteArray();
			ECPoint RA = curve.decodePoint(entity.R).normalize();
			byte[] s2= sm3hash(new byte[] { 0x03 }, yV,
					sm3hash(xV, entity.Z, this.Z, RA.getXCoord().toBigInteger().toByteArray(),
							RA.getYCoord().toBigInteger().toByteArray(), this.RA.getXCoord().toBigInteger().toByteArray(),
							this.RA.getYCoord().toBigInteger().toByteArray()));
			if(!Arrays.equals(entity.S, s2)) {
				return false;
			}
			return true;
		}
	}
	
	public static class SM2Signature {
		BigInteger r;
		BigInteger s;

		public SM2Signature(BigInteger r, BigInteger s) {
			this.r = r;
			this.s = s;
		}

		public BigInteger getR() {
			return r;
		}
		
		public BigInteger getS() {
			return s;
		}
	}

}
