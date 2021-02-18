package cn.chain33.javasdk.model;

import cn.chain33.javasdk.client.Account;
import cn.chain33.javasdk.client.RpcClient;
import cn.chain33.javasdk.model.cert.CertObject;
import cn.chain33.javasdk.model.enums.SignType;
import cn.chain33.javasdk.model.protobuf.CertService;
import cn.chain33.javasdk.utils.CertUtils;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;
import com.google.protobuf.ByteString;
import org.junit.Assert;
import org.junit.Test;

public class CertUtilTest {
    static RpcClient certclient = new RpcClient("http://127.0.0.1:11901");
    static RpcClient chain33client = new RpcClient("http://127.0.0.1:8801");

    public static final String AdminKey = "619044d303498246f8b39d8e38fe639be08ce5e213d490593ffcde8ee63beb79";
    public static final String UserName = "ycy";
    public static final String Identity = "101";
    public static final String UserPub = "03afccf0095ce8ac2021f77d032cdac1a670e77c4e7dca568f9bab935b84ab711e";
    public static final String UserKey = "03ffd7371d86246f9bf5682d4319c5767b051e2acf98f806fe724579d9124b53";

    @Test
    public void testCertUtilUser() {
        boolean result = certclient.certUserRegister(UserName, Identity, UserPub, AdminKey);
        Assert.assertEquals(result, true);

        CertObject.UserInfo user = certclient.certGetUserInfo(Identity, UserKey);
        Assert.assertEquals(UserName, user.name);

        result = certclient.certRevoke("", Identity, AdminKey);
        Assert.assertEquals(result, true);

        result = certclient.certUserRevoke(Identity, AdminKey);
        Assert.assertEquals(result, true);

        user = certclient.certGetUserInfo(Identity, UserKey);
        Assert.assertNull(user);
    }

    @Test
    public void testCertUtilEnroll() {
        boolean result = certclient.certUserRegister(UserName, Identity, UserPub, AdminKey);
        Assert.assertEquals(result, true);

        CertObject.CertEnroll cert = certclient.certEnroll(Identity, UserKey);
        Assert.assertNotNull(cert);
        Assert.assertNotNull(cert.serial);
        Assert.assertNotNull(cert.cert);
        System.out.println(HexUtil.toHexString(cert.cert));

        CertObject.CertInfo certInfo = certclient.certGetCertInfo(cert.serial, UserKey);
        Assert.assertNotNull(certInfo);
        Assert.assertEquals(Identity, certInfo.identity);

        CertService.CertNormal.Builder normal = CertService.CertNormal.newBuilder();
        normal.setValue(ByteString.copyFrom("value123".getBytes()));
        normal.setKey("key123");
        CertService.CertNormal normalAction = normal.build();

        CertService.CertAction.Builder builder = CertService.CertAction.newBuilder();
        builder.setTy(CertUtils.CertActionNormal);
        builder.setNormal(normalAction);

        byte[] reqBytes = builder.build().toByteArray();
        String transactionHash = TransactionUtil.createTxWithCert(UserKey, "cert", reqBytes, SignType.SM2, cert.getCert(), "ca test".getBytes());
        String hash = chain33client.submitTransaction(transactionHash);
        System.out.println(hash);
        Assert.assertNotNull(hash);

        result = certclient.certRevoke(cert.serial, "", AdminKey);
        Assert.assertEquals(result, true);

        certInfo = certclient.certGetCertInfo(cert.serial, UserKey);
        Assert.assertNotNull(certInfo);
        Assert.assertEquals(1, certInfo.status);

        byte[] crl = certclient.certGetCRL(Identity, UserKey);
        Assert.assertNotNull(crl);

        CertObject.CertEnroll certReEnroll = certclient.certReEnroll(Identity, AdminKey);
        Assert.assertNotNull(certReEnroll);
        Assert.assertNotNull(certReEnroll.serial);
        Assert.assertNotNull(certReEnroll.cert);

        certInfo = certclient.certGetCertInfo(cert.serial, UserKey);
        Assert.assertNotNull(certInfo);
        Assert.assertEquals(Identity, certInfo.identity);
    }

    @Test
    public void testLoadFromFile() throws Exception {
        Account account = new Account();
        AccountInfo accountInfo = account.loadGMAccountLocal("test", "", "./test/keystore/5c3682a5719cf5bc1bd6280938670c3acfcb67cc15744a7b9b348066795a4e62_sk");

        byte[] certBytes = CertUtils.getCertFromFile("./test/signcerts/user1@org1-cert.pem");
        CertService.CertNormal.Builder normal = CertService.CertNormal.newBuilder();
        normal.setValue(ByteString.copyFrom("value123".getBytes()));
        normal.setKey("key123");
        CertService.CertNormal normalAction = normal.build();

        CertService.CertAction.Builder builder = CertService.CertAction.newBuilder();
        builder.setTy(CertUtils.CertActionNormal);
        builder.setNormal(normalAction);

        byte[] reqBytes = builder.build().toByteArray();
        String transactionHash = TransactionUtil.createTxWithCert(accountInfo.getPrivateKey(), "cert", reqBytes, SignType.SM2, certBytes, "ca test".getBytes());
        String hash = chain33client.submitTransaction(transactionHash);
        System.out.println(hash);
        Assert.assertNotNull(hash);
    }
}
