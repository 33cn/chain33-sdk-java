package cn.chain33.javasdk.model.paillier;

import java.math.BigInteger;

public class BytesUtils {
    public BytesUtils() {}

    public static byte[] unsignedShortToByte2(int s) {
        byte[] targets = new byte[2];
        targets[0] = (byte) (s >> 8 & 0xFF);
        targets[1] = (byte) (s & 0xFF);
        return targets;
    }

    public static int byte2ToUnsignedShort(byte[] bytes) {
        int high = bytes[0];
        int low = bytes[1];
        return (high << 8 & 0xFF00) | (low & 0xFF);
    }

    public static byte[] asUnsignedByteArray(BigInteger paramBigInteger) {
        byte[] arrayOfByte1 = paramBigInteger.toByteArray();
        if (arrayOfByte1[0] == 0) {
            byte[] arrayOfByte2 = new byte[arrayOfByte1.length - 1];
            System.arraycopy(arrayOfByte1, 1, arrayOfByte2, 0, arrayOfByte2.length);
            return arrayOfByte2;
        }
        return arrayOfByte1;
    }

    public static byte[] asUnsignedByteArray(BigInteger paramBigInteger, int byteLength) {
        byte[] arrayOfByte1 = asUnsignedByteArray(paramBigInteger);
        if (arrayOfByte1.length < byteLength) {
            byte[] arrayOfByte2 = new byte[byteLength];
            int offset = byteLength - arrayOfByte1.length;
            for (int i = 0; i < offset; i++) {
                arrayOfByte2[i] = 0;
            }
            System.arraycopy(arrayOfByte1, 0, arrayOfByte2, offset, arrayOfByte1.length);
            return arrayOfByte2;
        }
        return arrayOfByte1;
    }

    public static BigInteger fromUnsignedByteArray(byte[] paramArrayOfByte) {
        return new BigInteger(1, paramArrayOfByte);
    }

}
