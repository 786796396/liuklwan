package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:24 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SysAttachRequest extends Query {
	private String tbKey;
	private String tbName;
	public String getTbKey() {
		return tbKey;
	}
	public void setTbKey(String tbKey) {
		this.tbKey = tbKey;
	}
	public String getTbName() {
		return tbName;
	}
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

	
	

}

