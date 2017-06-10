package com.ucap.cloud_web.dto;

import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.DatabaseInfo;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class TaskDailyRequest extends Query {
	// 网站标识码
	private String siteCode;

	// 订单详情表id
	private Integer websiteInfoId;
	/**
	 * sitecode 数组
	 */
	private List<DatabaseInfo> ids;
	
	public List<DatabaseInfo> getIds() {
		return ids;
	}

	public void setIds(List<DatabaseInfo> ids) {
		this.ids = ids;
	}

	public Integer getWebsiteInfoId() {
		return websiteInfoId;
	}

	public void setWebsiteInfoId(Integer websiteInfoId) {
		this.websiteInfoId = websiteInfoId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

}
