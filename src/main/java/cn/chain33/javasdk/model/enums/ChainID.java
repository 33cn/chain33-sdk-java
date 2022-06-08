package cn.chain33.javasdk.model.enums;

/**
 * @authoer lhl
 * @date 2022/6/6 上午10:46
 */
public enum ChainID {
    BTY(0),YCC(999) ;

    private int ID;

    private ChainID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
