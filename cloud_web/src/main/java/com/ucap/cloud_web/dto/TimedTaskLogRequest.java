package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class TimedTaskLogRequest extends Query {
	// 网站标识码
	private String siteCode;
	// 类型(1:首页更新，2：栏目更新，3.首页死链，4.内容正确性 ，5,获取更新，首页死链，错别字数据任务，6.连通性任务，7.汇总数据任务，8.健康指数任务)
	private Integer type;

	// 扫描日期
	private String scanDate;

	// 完成状态
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

}
