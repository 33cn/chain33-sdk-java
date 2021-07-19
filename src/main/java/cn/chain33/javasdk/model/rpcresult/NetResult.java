package cn.chain33.javasdk.model.rpcresult;

import java.io.Serializable;

public class NetResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 表示自身的外网地址信息
	 */
	private String externaladdr;
	
	/**
	 * 表示节点监听的本地地址信息，例如：192.168.1.108:13802
	 */
	private String localaddr;
	
	/**
	 * 为true 时，表示别的节点可以连接到自己,false 表示自身节点对其它节点不可见，别的节点无法连接到自己
	 */
	private boolean service;
	
	/**
	 * 扇出数，表示对外连接的节点个数
	 */
	private Integer outbounds;
	
	/**
	 * 扇入数，表示有多少外部节点连接本节点
	 */
	private Integer inbounds;
	
	public String getExternaladdr() {
		return externaladdr;
	}


	public void setExternaladdr(String externaladdr) {
		this.externaladdr = externaladdr;
	}


	public String getLocaladdr() {
		return localaddr;
	}


	public void setLocaladdr(String localaddr) {
		this.localaddr = localaddr;
	}


	public boolean isService() {
		return service;
	}


	public void setService(boolean service) {
		this.service = service;
	}


	public Integer getOutbounds() {
		return outbounds;
	}


	public void setOutbounds(Integer outbounds) {
		this.outbounds = outbounds;
	}


	public Integer getInbounds() {
		return inbounds;
	}


	public void setInbounds(Integer inbounds) {
		this.inbounds = inbounds;
	}
	
	
	@Override
	public String toString() {
		return "NetResult [externaladdr=" + externaladdr + ", localaddr=" + localaddr + ", service=" + service + ", outbounds=" + outbounds
				+ ", inbounds=" + inbounds  + "]";
	}
	
}
