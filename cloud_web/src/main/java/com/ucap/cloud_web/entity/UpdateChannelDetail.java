package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/**
* <br>
* <b>作者：</b>SunJiang<br>
* <b>日期：</b> 2015-10-30 09:51:22 <br>
* <b>版权所有：<b>版权所有(C) 2015<br>
*/
public class UpdateChannelDetail {


	//栏目更新详情表id
	private int id;

	//栏目更新趋势表id
	private int updateChannelInfo;

	//内容更新统计表Id（按日统计）
	private int updateContentCount;

	//网站标识码
	private String siteCode;
	//标题
   private String title;

	//栏目名称
	private String channelName;

	//栏目url
	private String url;

	//栏目分类
	private int dicChannelId;

	//最后更新时间(0000-00-00 00:00:00)
	private String lastTime;

	//快照
	private String imgUrl;
	
	//更新链接url
	private String linkUrl;

	//扫描时间（yyyy-mm-dd）
	private String scanDate;

	//创建时间
	private Date createTime;
	
	//修改时间
	private Date modifyTime;
	

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** set 栏目更新详情表id */
	public void setId(int id){
		this.id=id;
	}

	/** get 栏目更新详情表id */
	public int getId(){
		return id;
	}

	/** set 栏目更新趋势表id */
	public void setUpdateChannelInfo(int updateChannelInfo){
		this.updateChannelInfo=updateChannelInfo;
	}

	/** get 栏目更新趋势表id */
	public int getUpdateChannelInfo(){
		return updateChannelInfo;
	}

	/** set 内容更新统计表Id（按日统计） */
	public void setUpdateContentCount(int updateContentCount){
		this.updateContentCount=updateContentCount;
	}

	/** get 内容更新统计表Id（按日统计） */
	public int getUpdateContentCount(){
		return updateContentCount;
	}

	/** set 网站标识码 */
	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}

	/** get 网站标识码 */
	public String getSiteCode(){
		return siteCode;
	}

	/** set 栏目名称 */
	public void setChannelName(String channelName){
		this.channelName=channelName;
	}

	/** get 栏目名称 */
	public String getChannelName(){
		return channelName;
	}

	/** set 栏目url */
	public void setUrl(String url){
		this.url=url;
	}
	/** get 栏目url */
	public String getUrl(){
		return url;
	}
	/** get 栏目标题 */
	public String getTitle() {
		return title;
	}
	/** set 栏目标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** set 栏目分类 */
	public void setDicChannelId(int dicChannelId){
		this.dicChannelId=dicChannelId;
	}

	/** get 栏目分类 */
	public int getDicChannelId(){
		return dicChannelId;
	}

	/** set 最后更新时间(0000-00-00 00:00:00) */
	public void setLastTime(String lastTime){
		this.lastTime=lastTime;
	}

	/** get 最后更新时间(0000-00-00 00:00:00) */
	public String getLastTime(){
		return lastTime;
	}

	/** set 快照 */
	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}

	/** get 快照 */
	public String getImgUrl(){
		return imgUrl;
	}

	/** set 扫描时间（yyyy-mm-dd） */
	public void setScanDate(String scanDate){
		this.scanDate=scanDate;
	}

	/** get 扫描时间（yyyy-mm-dd） */
	public String getScanDate(){
		return scanDate;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime(){
		return createTime;
	}

}

