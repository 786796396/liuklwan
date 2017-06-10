package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:04:13 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class DicServiceUnuserfulRequest extends Query {
	//服务不实用问题类型编码(0、1、2、3、4)
	private int serviceUnuserfulCode;

	//问题类型说明：0办事指南要素缺失、1办事指南要素不准确、2附件未提供下载、3附件无法下载、4其他
	private String remark;

	public int getServiceUnuserfulCode() {
		return serviceUnuserfulCode;
	}

	public void setServiceUnuserfulCode(int serviceUnuserfulCode) {
		this.serviceUnuserfulCode = serviceUnuserfulCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	

}

