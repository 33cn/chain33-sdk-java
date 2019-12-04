package cn.chain33.javasdk.model.enums;

public enum StorageEnum {
    
    ContentOnlyNotaryStorage(101),
    HashOnlyNotaryStorage(102),
    LinkNotaryStorage(103),
    EncryptNotaryStorage(104),
    EncryptShareNotaryStorage (105);
    
    private int ty;
    
    private StorageEnum(int ty) {
        this.ty = ty;
    }
    
    public int getTy() {
        return ty;
    }
}
