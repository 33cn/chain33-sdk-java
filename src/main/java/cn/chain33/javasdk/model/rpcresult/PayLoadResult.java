package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class PayLoadResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String rawlog;
	
	private String topic;
	
	private String content;

	public String getRawlog() {
		return rawlog;
	}

	public void setRawlog(String rawlog) {
		this.rawlog = rawlog;
	}

	@Override
	public String toString() {
		return "PayLoadResult [rawlog=" + rawlog + "]";
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
