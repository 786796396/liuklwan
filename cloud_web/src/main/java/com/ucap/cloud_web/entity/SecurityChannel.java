package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:53:39 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class SecurityChannel {


	//内容保障问题-栏目信息不更新检测结果详情表id	private int id;
	//栏目类型id	private int dicChannelId;
	//网站标识码	private String siteCode;
	//扫描时间（yyyy-dd-mm）	private String scanDate;
	//栏目名称	private String channelName;
	//栏目url	private String url;
	//最后更新时间	private String lastModifyTime;
	//快照	private String imgUrl;
	//创建时间	private Date createTime;
	/** set 内容保障问题-栏目信息不更新检测结果详情表id */	public void setId(int id){
		this.id=id;
	}
	/** get 内容保障问题-栏目信息不更新检测结果详情表id */	public int getId(){
		return id;
	}
	/** set 栏目类型id */	public void setDicChannelId(int dicChannelId){
		this.dicChannelId=dicChannelId;
	}
	/** get 栏目类型id */	public int getDicChannelId(){
		return dicChannelId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 扫描时间（yyyy-dd-mm） */	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}
	/** get 扫描时间（yyyy-dd-mm） */	public String getScanDate(){
		return scanDate;
	}
	/** set 栏目名称 */	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
	/** get 栏目名称 */	public String getChannelName(){
		return channelName;
	}
	/** set 栏目url */	public void setUrl(String url){
		this.url=url;
	}
	/** get 栏目url */	public String getUrl(){
		return url;
	}
	/** set 最后更新时间 */	public void setLastModifyTime(String lastModifyTime){
		this.lastModifyTime=lastModifyTime;
	}
	/** get 最后更新时间 */	public String getLastModifyTime(){
		return lastModifyTime;
	}
	/** set 快照 */	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	/** get 快照 */	public String getImgUrl(){
		return imgUrl;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

