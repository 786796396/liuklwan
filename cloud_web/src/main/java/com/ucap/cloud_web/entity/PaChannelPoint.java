package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-23 15:04:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaChannelPoint {


	//重点栏目检测表id	private int id;
	//网站标识码	private String siteCode;
	//站点信息id	private int websiteInfoId;
	//类别id	private int dicChannelId;
	//子类id	private int dicChannelSonId;
	//栏目名称(手动输入)	private String channelName;
	//栏目url	private String channelUrl;
	//跳转url	private String jumpPageUrl;
	//md5加密后url(32位加密)	private String encodeUrl;
	//是否门户（1门户，0非门户）	private int isorganizational;
	//状态（监测中：1，未监测：0,标记删除：-1），取消监测将状态置为0，删除记录为标记删除状态值为-1	private int status;
	//连通状态连通状态（0：不连通，1：连通）	private int linkStatus;
	//0未处理，1已处理，2没有http	private int isHandle;
	//获取内容正确性日期（yyyy-mm-dd）	private String correctDate;
	//备注（用于填写不检查原因）	private String remark;
	//初始化时间	private Date initTime;
	//修改时间	private Date modifyTime;
	//创建时间	private Date createTime;
	/** set 重点栏目检测表id */	public void setId(int id){
		this.id=id;
	}
	/** get 重点栏目检测表id */	public int getId(){
		return id;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 站点信息id */	public void setWebsiteInfoId(int websiteInfoId){
		this.websiteInfoId=websiteInfoId;
	}
	/** get 站点信息id */	public int getWebsiteInfoId(){
		return websiteInfoId;
	}
	/** set 类别id */	public void setDicChannelId(int dicChannelId){
		this.dicChannelId=dicChannelId;
	}
	/** get 类别id */	public int getDicChannelId(){
		return dicChannelId;
	}
	/** set 子类id */	public void setDicChannelSonId(int dicChannelSonId){
		this.dicChannelSonId=dicChannelSonId;
	}
	/** get 子类id */	public int getDicChannelSonId(){
		return dicChannelSonId;
	}
	/** set 栏目名称(手动输入) */	public void setChannelName(String channelName){
		this.channelName=channelName;
	}
	/** get 栏目名称(手动输入) */	public String getChannelName(){
		return channelName;
	}
	/** set 栏目url */	public void setChannelUrl(String channelUrl){
		this.channelUrl=channelUrl;
	}
	/** get 栏目url */	public String getChannelUrl(){
		return channelUrl;
	}
	/** set 跳转url */	public void setJumpPageUrl(String jumpPageUrl){
		this.jumpPageUrl=jumpPageUrl;
	}
	/** get 跳转url */	public String getJumpPageUrl(){
		return jumpPageUrl;
	}
	/** set md5加密后url(32位加密) */	public void setEncodeUrl(String encodeUrl){
		this.encodeUrl=encodeUrl;
	}
	/** get md5加密后url(32位加密) */	public String getEncodeUrl(){
		return encodeUrl;
	}
	/** set 是否门户（1门户，0非门户） */	public void setIsorganizational(int isorganizational){
		this.isorganizational=isorganizational;
	}
	/** get 是否门户（1门户，0非门户） */	public int getIsorganizational(){
		return isorganizational;
	}
	/** set 状态（监测中：1，未监测：0,标记删除：-1），取消监测将状态置为0，删除记录为标记删除状态值为-1 */	public void setStatus(int status){
		this.status=status;
	}
	/** get 状态（监测中：1，未监测：0,标记删除：-1），取消监测将状态置为0，删除记录为标记删除状态值为-1 */	public int getStatus(){
		return status;
	}
	/** set 连通状态连通状态（0：不连通，1：连通） */	public void setLinkStatus(int linkStatus){
		this.linkStatus=linkStatus;
	}
	/** get 连通状态连通状态（0：不连通，1：连通） */	public int getLinkStatus(){
		return linkStatus;
	}
	/** set 0未处理，1已处理，2没有http */	public void setIsHandle(int isHandle){
		this.isHandle=isHandle;
	}
	/** get 0未处理，1已处理，2没有http */	public int getIsHandle(){
		return isHandle;
	}
	/** set 获取内容正确性日期（yyyy-mm-dd） */	public void setCorrectDate(String correctDate){
		this.correctDate=correctDate;
	}
	/** get 获取内容正确性日期（yyyy-mm-dd） */	public String getCorrectDate(){
		return correctDate;
	}
	/** set 备注（用于填写不检查原因） */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 备注（用于填写不检查原因） */	public String getRemark(){
		return remark;
	}
	/** set 初始化时间 */	public void setInitTime(Date initTime){
		this.initTime=initTime;
	}
	/** get 初始化时间 */	public Date getInitTime(){
		return initTime;
	}
	/** set 修改时间 */	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	/** get 修改时间 */	public Date getModifyTime(){
		return modifyTime;
	}
	/** set 创建时间 */	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	/** get 创建时间 */	public Date getCreateTime(){
		return createTime;
	}
}

