package cn.chain33.javasdk.model.cert;

public class CertObject {
    public  static final class CertEnroll {
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
}
