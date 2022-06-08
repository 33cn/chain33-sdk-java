package cn.chain33.javasdk.model.enums;

public enum SignType {
	
	SECP256K1(1),ED25519(2),SM2(258),ETH_SECP256K1(8193),ETH_ED25519(8194),ETH_SM2(8450);
	
	private Integer type;
	
	private SignType(Integer type) {
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
}
