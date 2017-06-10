package com.ucap.cloud_web.constant;

/**
 * <p>Description: </p>队列类型
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: QueueType </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：qinjy </p>
 * <p></p>
 */
public enum QueueType {

	Queue_5(1,"5分钟"),
	Queue_10(2,"10分钟"),
	Queue_15(3,"15分钟"),
	Queue_30(4,"30分钟"),
	Queue_60(5,"60分钟"),
	Queue_day(6,"1天")
	;
	
	private Integer code;
	private String name;
	
	private QueueType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(Integer code) {
		for (QueueType q : QueueType.values()) {
			if (q.getCode() == code) {
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
