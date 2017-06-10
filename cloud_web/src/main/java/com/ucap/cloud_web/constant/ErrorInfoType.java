/**
 * 
 */
package com.ucap.cloud_web.constant;

/**描述： 
 * 包：com.ucap.cloud_web.constant
 * 文件名称：ErrorInfoType
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年5月3日下午5:24:40 
 * @version V1.0
 */
public enum ErrorInfoType {
	All(-1,"全部"),
	CONTENT(0,"内容无法访问"),
	UPDATE(1,"信息不更新"),
	ACCURATE(2,"内容不准确"),
	REPLY(3,"咨询留言不回复"),
	CHARACTER(4,"错别字"),
	FALSECONTENT(5,"虚假伪造内容"),
	OTHER(6,"其他"); 
	
	private Integer code;
	private String name;
	
	public static String getNameByCode(Integer code) {
		for (ErrorInfoType c : ErrorInfoType.values()) {
			if (c.getCode()==code) {
				return c.name;
			}
		}
		return null;
	}
	
	private ErrorInfoType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
}
