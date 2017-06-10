package com.ucap.cloud_web.constant;
/**
 * 描述： 全站扫描任务表  status
 * 包：com.ucap.cloud_web.constant
 * 文件名称：TaskAllStatusType
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-8-17下午4:57:52 
 * @version V1.0
 */
public enum TaskAllStatusType {
	
	NOT_START(0,"未启动"),
	
	START_SUCCESS(1,"启动成功"),
	
	STARTUP_FAILED(2,"启动失败"),
	
	GET_SUCCESS(4,"获取成功"),
	
	GET_FAILED(5,"获取失败")
	
	;
	
	private Integer code;
	private String name;
	
	private TaskAllStatusType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public static String getNameByCode(Integer code) {
		for (TaskAllStatusType c : TaskAllStatusType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
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
