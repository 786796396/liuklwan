package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-10-09 09:53:32 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class JcStatistics {


	//	private int id;
	//site_code当日count统计	private int siteCode;
	//url当日count统计	private int url;
	//ip当日count统计	private int ip;
	//访问日期	private String visitDate;
	//站点个数统计（从有数据开始排重统计站点个数）	private int siteCodeCnt;
	//创建时间	private Date createTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set site_code当日count统计 */	public void setSiteCode(int siteCode){
		this.siteCode=siteCode;
	}
	/** get site_code当日count统计 */	public int getSiteCode(){
		return siteCode;
	}
	/** set url当日count统计 */	public void setUrl(int url){
		this.url=url;
	}
	/** get url当日count统计 */	public int getUrl(){
		return url;
	}
	/** set ip当日count统计 */	public void setIp(int ip){
		this.ip=ip;
	}
	/** get ip当日count统计 */	public int getIp(){
		return ip;
	}
	/** set 访问日期 */	public void setVisitDate(String visitDate){
		this.visitDate=visitDate;
	}
	/** get 访问日期 */	public String getVisitDate(){
		return visitDate;
	}
	/** set 站点个数统计（从有数据开始排重统计站点个数） */	public void setSiteCodeCnt(int siteCodeCnt){
		this.siteCodeCnt=siteCodeCnt;
	}
	/** get 站点个数统计（从有数据开始排重统计站点个数） */	public int getSiteCodeCnt(){
		return siteCodeCnt;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

