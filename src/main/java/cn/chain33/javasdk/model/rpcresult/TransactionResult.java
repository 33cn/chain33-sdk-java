package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

import cn.chain33.javasdk.model.Signature;


public class TransactionResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private PayLoadResult payload;
	
	private String execer;
	
	private String rawpayload;
	
	private long fee;
	
	private long expire;
	
	private long nonce;
	
	private String to;

	private Signature signature;

	private String next;

	public String getExecer() {
		return execer;
	}

	public void setExecer(String execer) {
		this.execer = execer;
	}

	public String getRawpayload() {
		return rawpayload;
	}

	public void setRawpayload(String rawpayload) {
		this.rawpayload = rawpayload;
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

	public PayLoadResult getPayload() {
		return payload;
	}

	public void setPayload(PayLoadResult payload) {
		this.payload = payload;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "TransactionResult [payload=" + payload + ", execer=" + execer + ", rawpayload=" + rawpayload + ", fee="
				+ fee + ", expire=" + expire + ", nonce=" + nonce + ", to=" + to + ", signature=" + signature + "]";
	}

}
