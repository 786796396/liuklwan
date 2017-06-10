package com.ucap.cloud_web.constant;

/**
 * <p>Description: 连通性的连通状态</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: ConnectionType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月6日上午10:18:03 </p>
 */
public enum ConnectionState {
	
	TIMEOUT(1,"超时"),
	CONNSUCCESS(0,"连通");
	
	private Integer code;
	private String name;
	
	
	public static String getNameByCode(Integer code){
		
		for (ConnectionState conn : ConnectionState.values()) {
			if(conn.getCode()==code){
				return conn.getName();
			}
		}
		return null;
		
	}
	private ConnectionState(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
