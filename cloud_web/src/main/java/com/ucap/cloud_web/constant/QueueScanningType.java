package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>队列扫描类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: QueueType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：qinjy </p>
 * <p></p>
 */
public enum QueueScanningType {

	Queue_Sca_1(1,"288"),
	Queue_Sca_3(3,"96"),
	Queue_Sca_6(6,"1")
	;
	
	private Integer code;
	private String name;
	
	private QueueScanningType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(Integer code) {
		for (QueueScanningType q : QueueScanningType.values()) {
			if (q.getCode()==code) {
				return q.getName();
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
	

}
