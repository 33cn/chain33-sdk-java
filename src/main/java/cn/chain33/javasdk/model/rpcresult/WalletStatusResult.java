package cn.chain33.javasdk.model.rpcresult;

public class WalletStatusResult {

    private boolean isWalletLock;

    private boolean isAutoMining;

    private boolean isHasSeed;

    private boolean isTicketLock;

    public boolean isWalletLock() {
        return isWalletLock;
    }

    public void setWalletLock(boolean isWalletLock) {
        this.isWalletLock = isWalletLock;
    }

    public boolean isAutoMining() {
        return isAutoMining;
    }

    public void setAutoMining(boolean isAutoMining) {
        this.isAutoMining = isAutoMining;
    }

    public boolean isHasSeed() {
        return isHasSeed;
    }

    public void setHasSeed(boolean isHasSeed) {
        this.isHasSeed = isHasSeed;
    }

    public boolean isTicketLock() {
        return isTicketLock;
    }

    public void setTicketLock(boolean isTicketLock) {
        this.isTicketLock = isTicketLock;
    }

    @Override
    public String toString() {
        return "WalletStatus [isWalletLock=" + isWalletLock + ", isAutoMining=" + isAutoMining + ", isHasSeed="
                + isHasSeed + ", isTicketLock=" + isTicketLock + "]";
    }

}
