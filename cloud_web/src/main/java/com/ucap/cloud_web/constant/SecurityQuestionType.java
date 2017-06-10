package com.ucap.cloud_web.constant;
/**
 * 描述： 安全问题 三级指标
 * 包：com.ucap.cloud_web.constant
 * 文件名称：SecurityQuestionType
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-9-6下午2:28:09 
 * @version V1.0
 */
public enum SecurityQuestionType {
	/**
	 * 安全漏洞
	 */
	CON_HOME(20,"安全漏洞"),
	/**
	 * 网页木马
	 */
	SECURITY_HOME(21,"网页木马"),
	/**
	 * 黑链盗链
	 */
	SECURITY_CHANNEL(22,"黑链盗链"),
	/**
	 * 敏感词
	 */
	BLANK_CHANNEL(23,"敏感词"),
	
	
	;
	private Integer code;
	private String name;
	private SecurityQuestionType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String getNameByCode(Integer code) {
		for (SecurityQuestionType c : SecurityQuestionType.values()) {
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
