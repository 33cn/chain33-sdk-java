package cn.chain33.javasdk.model.enums;

public enum StorageEnum {
    
    ContentOnlyNotaryStorage(1),
    HashOnlyNotaryStorage(2),
    LinkNotaryStorage(3),
    EncryptNotaryStorage(4),
    EncryptShareNotaryStorage (5),
    EncryptNotaryAdd(6);
    
    private int ty;
    
    private StorageEnum(int ty) {
        this.ty = ty;
    }
    
    public int getTy() {
        return ty;
    }
}
