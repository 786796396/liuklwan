/**
 * 
 */
package com.ucap.cloud_web.constant;

/**描述： 
 * 包：com.ucap.cloud_web.constant
 * 文件名称：DatabaseTreeInfoType
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年2月24日下午4:14:34 
 * @version V1.0
 */
public enum DatabaseTreeInfoType {

	ISLINK(1, "是link使用"),
	ISBIGDATA(1, "是大数据使用"); 

	private Integer code;
	private String name;

	private DatabaseTreeInfoType(Integer code, String name) {
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
