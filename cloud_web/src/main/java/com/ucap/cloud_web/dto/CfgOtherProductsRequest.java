package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-01-03 13:38:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@SuppressWarnings("serial")public class CfgOtherProductsRequest extends Query {

	private Integer inUse; // 是否启用： 1.启用 2.禁用
	private Integer type;//云产品类型
	public Integer getInUse() {
		return inUse;
	}

	public void setInUse(Integer inUse) {
		this.inUse = inUse;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}

