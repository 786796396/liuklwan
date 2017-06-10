package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class UpdateContentCount {


	//内容更新--内容更新统计表id	private int id;
	//网站标识码	private String siteCode;
	//类型（首页：0，栏目：1）	private int type;
	//更新数量	private int updateNum;
	//扫描时间（yyyy-dd-mm）	private String scanDate;
	//创建时间	private Date createTime;
	
	//最后更新url
	private String lastUpdateUrl;
		/** set 内容更新--内容更新统计表id */	public void setId(int id){
		this.id=id;
	}
	/** get 内容更新--内容更新统计表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 类型（首页：0，栏目：1） */	public void setType(int type){
		this.type=type;
	}
	/** get 类型（首页：0，栏目：1） */	public int getType(){
		return type;
	}
	/** set 更新数量 */	public void setUpdateNum(int updateNum){
		this.updateNum=updateNum;
	}
	/** get 更新数量 */	public int getUpdateNum(){
		return updateNum;
	}
	/** set 扫描时间（yyyy-dd-mm） */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间（yyyy-dd-mm） */	public String getScanDate(){
		return scanDate;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}

	public String getLastUpdateUrl() {
		return lastUpdateUrl;
	}

	public void setLastUpdateUrl(String lastUpdateUrl) {
		this.lastUpdateUrl = lastUpdateUrl;
	}
}

