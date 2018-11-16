package cn.chain33.javasdk.model;

public class Address {

	private byte version;
	
	private byte[] hash160;
	
	private byte[] checkSum;
	
	private byte[] pubkey;
	
	private String Enc58Str;

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public byte[] getHash160() {
		return hash160;
	}

	public void setHash160(byte[] hash160) {
		this.hash160 = hash160;
	}

	public byte[] getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(byte[] checkSum) {
		this.checkSum = checkSum;
	}

	public byte[] getPubkey() {
		return pubkey;
	}

	public void setPubkey(byte[] pubkey) {
		this.pubkey = pubkey;
	}

	public String getEnc58Str() {
		return Enc58Str;
	}

	public void setEnc58Str(String enc58Str) {
		Enc58Str = enc58Str;
	}
	
}
