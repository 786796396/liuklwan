package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>wl@ucap.com.cn<br>
 * <b>日期：</b> 2016-12-05 16:39:56 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
@SuppressWarnings("serial")
public class YunSoInfoRequest extends Query {

	private String remark;
	private String name;
	private String companyName;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




}
