package cn.chain33.javasdk.model;

import java.io.Serializable;

public class Signature implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int Ty;
	
	private byte[] Pubkey;
	
	private byte[] Signature;

	public int getTy() {
		return Ty;
	}

	public void setTy(int ty) {
		Ty = ty;
	}

	public byte[] getPubkey() {
		return Pubkey;
	}

	public void setPubkey(byte[] pubkey) {
		Pubkey = pubkey;
	}

	public byte[] getSignature() {
		return Signature;
	}

	public void setSignature(byte[] signature) {
		Signature = signature;
	}
	
	
}
