package cn.chain33.javasdk.utils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 
 * @author logan 2018年5月14日
 */
public class TransactionUtil {
	
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
	 * byte数组截取
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

}
