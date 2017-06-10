package com.ucap.cloud_web.entity; 
/** 
* @描述:
* @作者:houzs@ucap.com.cn
* @时间:2017年5月17日
* @return:
*/
public class DicFatalError {
	//严重错误ID
	private int id;
	//严重错误类型说明
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
 