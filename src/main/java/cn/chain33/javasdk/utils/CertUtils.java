package cn.chain33.javasdk.utils;

import cn.chain33.javasdk.model.protobuf.CertService;
import com.google.protobuf.ByteString;

import java.io.*;

public class CertUtils {
    public static final int CertActionNew    = 1;
    public static final int CertActionUpdate = 2;
    public static final int CertActionNormal = 3;

    public static byte[] EncodeCertToSignature(byte[] signBytes, byte[] cert, byte[] uid) {
        CertService.CertSignature.Builder certBuilder = CertService.CertSignature.newBuilder();
        certBuilder.setCert(ByteString.copyFrom(cert));
        certBuilder.setSignature(ByteString.copyFrom(signBytes));
        certBuilder.setUid(ByteString.copyFrom(uid));

        return certBuilder.build().toByteArray();
    }

    public static byte[] getCertFromFile(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        int iAvail = is.available();
        byte[] bytes = new byte[iAvail];
        is.read(bytes);
        is.close();

        return  bytes;
    }
}
