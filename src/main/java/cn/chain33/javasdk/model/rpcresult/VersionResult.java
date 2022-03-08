package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class VersionResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区块链名，该节点chain33.toml中配置的title值
     */
    private String title;

    /**
     * 应用app的版本
     */
    private String app;

    /**
     * 版本信息，版本号-GitCommit(前八个字符)
     */
    private String chain33;

    /**
     * localdb版本号
     */
    private String localDb;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the app
     */
    public String getApp() {
        return app;
    }

    /**
     * @param app
     *            the app to set
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * @return the chain33
     */
    public String getChain33() {
        return chain33;
    }

    /**
     * @param chain33
     *            the chain33 to set
     */
    public void setChain33(String chain33) {
        this.chain33 = chain33;
    }

    /**
     * @return the localDb
     */
    public String getLocalDb() {
        return localDb;
    }

    /**
     * @param localDb
     *            the localDb to set
     */
    public void setLocalDb(String localDb) {
        this.localDb = localDb;
    }

    @Override
    public String toString() {
        return "VersionResult [title=" + title + ", app=" + app + ", chain33=" + chain33 + ", localDb=" + localDb + "]";
    }

}
