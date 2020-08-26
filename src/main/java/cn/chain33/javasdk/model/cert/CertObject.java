package cn.chain33.javasdk.model.cert;

public class CertObject {
    public static final class CertEnroll {
        public String serial;
        public byte[] cert;

        public String getSerial() {
            return serial;
        }

        public byte[] getCert() {
            return cert;
        }

        public void setCert(byte[] cert) {
            this.cert = cert;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }
    }

    public static final class UserInfo {
        public String name;
        public byte[] pubKey;
        public String identity;
        public String serial;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPubKey(byte[] pubKey) {
            this.pubKey = pubKey;
        }

        public byte[] getPubKey() {
            return pubKey;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getIdentity() {
            return identity;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getSerial() {
            return serial;
        }
    }

    public static final class CertInfo {
        public String serial;
        public int    status;
        public long   exipreTime;
        public long   revokeTime;
        public byte[] cert;
        public String identity;

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getSerial() {
            return serial;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setExipreTime(long exipreTime) {
            this.exipreTime = exipreTime;
        }

        public long getExipreTime() {
            return exipreTime;
        }

        public void setRevokeTime(long revokeTime) {
            this.revokeTime = revokeTime;
        }

        public long getRevokeTime() {
            return revokeTime;
        }

        public void setCert(byte[] cert) {
            this.cert = cert;
        }

        public byte[] getCert() {
            return cert;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getIdentity() {
            return identity;
        }
    }

}
