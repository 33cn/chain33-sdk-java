package cn.chain33.javasdk.model;

import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] execer;
	private byte[] payload;
	private long fee;
	private long expire;
	private long nonce;
	private String to;
	private int groupCount;
	private byte[] header;
	private byte[] next;
	private int chainID;

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public byte[] getHeader() {
		return header;
	}

	public void setHeader(byte[] header) {
		this.header = header;
	}

	public byte[] getNext() {
		return next;
	}

	public void setNext(byte[] next) {
		this.next = next;
	}

	public int getChainID() {
		return chainID;
	}

	public void setChainID(int chainID) {
		this.chainID = chainID;
	}

	private Signature signature;

	public byte[] getExecer() {
		return execer;
	}

	public void setExecer(byte[] execer) {
		this.execer = execer;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public long getNonce() {
		return nonce;
	}

	public void setNonce(long nonce) {
		this.nonce = nonce;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

}
