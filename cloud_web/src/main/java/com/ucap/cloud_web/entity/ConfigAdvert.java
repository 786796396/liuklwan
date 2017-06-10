package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-09-06 19:26:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class ConfigAdvert {


	//	private int id;
	//广告名称	private String name;
	//类别	private int type;
	//类型（图片、链接等）	private int adType;
	//开始时间	private String startTime;
	//结束时间	private String endTime;
	//图片宽度	private String width;
	//图片高度	private String height;
	//图片路径、地址	private String urlAddr;
	//链接地址	private String linkAddr;
	//启用状态（0启用，1未启用）	private int status;
	//描述	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	//广告背景颜色
	private String backColor;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 广告名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 广告名称 */	public String getName(){
		return name;
	}
	/** set 类别 */	public void setType(int type){
		this.type=type;
	}
	/** get 类别 */	public int getType(){
		return type;
	}
	/** set 类型（图片、链接等） */	public void setAdType(int adType){
		this.adType=adType;
	}
	/** get 类型（图片、链接等） */	public int getAdType(){
		return adType;
	}
	/** set 开始时间 */	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	/** get 开始时间 */	public String getStartTime(){
		return startTime;
	}
	/** set 结束时间 */	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	/** get 结束时间 */	public String getEndTime(){
		return endTime;
	}
	/** set 图片宽度 */	public void setWidth(String width){
		this.width=width;
	}
	/** get 图片宽度 */	public String getWidth(){
		return width;
	}
	/** set 图片高度 */	public void setHeight(String height){
		this.height=height;
	}
	/** get 图片高度 */	public String getHeight(){
		return height;
	}
	/** set 图片路径、地址 */	public void setUrlAddr(String urlAddr){
		this.urlAddr=urlAddr;
	}
	/** get 图片路径、地址 */	public String getUrlAddr(){
		return urlAddr;
	}
	/** set 链接地址 */	public void setLinkAddr(String linkAddr){
		this.linkAddr=linkAddr;
	}
	/** get 链接地址 */	public String getLinkAddr(){
		return linkAddr;
	}
	/** set 启用状态（0启用，1未启用） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 启用状态（0启用，1未启用） */	public int getStatus(){
		return status;
	}
	/** set 描述 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 描述 */	public String getRemark(){
		return remark;
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

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	
}

