package com.ucap.cloud_web.entity;

/**
 * 描述： 大数据分析 解析xml返回集合对象
 * 包：com.ucap.cloud_web.entity
 * 文件名称：BigDateResults
 * 公司名称：开普互联
 * 作者：qinjy
 * @version V1.0
 */
public class Detail {
	private String stime;//时间
	private String code;// 问题代码
	
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
