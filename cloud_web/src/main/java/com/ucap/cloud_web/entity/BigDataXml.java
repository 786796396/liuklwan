package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-07-21 10:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class BigDataXml {


	//大数据xml数据id	private Integer id;
	//网站标识码	private String siteCode;
	//tab标签id -1：当前登录组织单位数据汇总  0:下级地方站群 1：下级地方门户 2：本级站点 
	//3：下级部门站群 4：下级部门门户 5：监测网站数量 6：首页不更新（网站数） 7：监测不连通率占比
	private Integer tabId;	//大数据xml数据串	private String dataXml;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 大数据xml数据id */	public void setId(Integer id){
		this.id=id;
	}
	/** get 大数据xml数据id */	public Integer getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 大数据xml数据串 */	public void setDataXml(String dataXml){
		this.dataXml=dataXml;
	}
	/** get 大数据xml数据串 */	public String getDataXml(){
		return dataXml;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}

	public Integer getTabId() {
		return tabId;
	}

	public void setTabId(Integer tabId) {
		this.tabId = tabId;
	}
}

