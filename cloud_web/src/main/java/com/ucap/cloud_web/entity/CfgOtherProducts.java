package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-01-03 13:38:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public class CfgOtherProducts {


	//主键	private int id;
	//图标	private String icon;
	//名称	private String title;
	//描述	private String description;
	//跳转路径	private String linkUrl;
	//是否启用： 1.启用 2.禁用	private Integer inUse;
	//排序	private int sort;
	//是否特殊显示：1.是 2.否(特殊显示只的是在弹框外显示）	private Integer specialShow;
	//是否传参数 1：是 2：否	private int isData;
	//备注	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	//添加人	private int createBy;
	//修改人	private int modifyBy;
	
	//云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
	private String type;
	/** set 主键 */	public void setId(int id){
		this.id=id;
	}
	/** get 主键 */	public int getId(){
		return id;
	}
	/** set 图标 */	public void setIcon(String icon){
		this.icon=icon;
	}
	/** get 图标 */	public String getIcon(){
		return icon;
	}
	/** set 名称 */	public void setTitle(String title){
		this.title=title;
	}
	/** get 名称 */	public String getTitle(){
		return title;
	}
	/** set 描述 */	public void setDescription(String description){
		this.description=description;
	}
	/** get 描述 */	public String getDescription(){
		return description;
	}
	/** set 跳转路径 */	public void setLinkUrl(String linkUrl){
		this.linkUrl=linkUrl;
	}
	/** get 跳转路径 */	public String getLinkUrl(){
		return linkUrl;
	}
	/** set 是否启用： 1.启用 2.禁用 */	public void setInUse(Integer inUse) {
		this.inUse=inUse;
	}
	/** get 是否启用： 1.启用 2.禁用 */	public Integer getInUse() {
		return inUse;
	}
	/** set 排序 */	public void setSort(int sort){
		this.sort=sort;
	}
	/** get 排序 */	public int getSort(){
		return sort;
	}
	/** set 是否特殊显示：1.是 2.否(特殊显示只的是在弹框外显示） */	public void setSpecialShow(Integer specialShow) {
		this.specialShow=specialShow;
	}
	/** get 是否特殊显示：1.是 2.否(特殊显示只的是在弹框外显示） */	public Integer getSpecialShow() {
		return specialShow;
	}
	/** set 是否传参数 1：是 2：否 */	public void setIsData(int isData){
		this.isData=isData;
	}
	/** get 是否传参数 1：是 2：否 */	public int getIsData(){
		return isData;
	}
	/** set 备注 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 备注 */	public String getRemark(){
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
	/** set 添加人 */	public void setCreateBy(int createBy){
		this.createBy=createBy;
	}
	/** get 添加人 */	public int getCreateBy(){
		return createBy;
	}
	/** set 修改人 */	public void setModifyBy(int modifyBy){
		this.modifyBy=modifyBy;
	}
	/** get 修改人 */	public int getModifyBy(){
		return modifyBy;
	}
	//云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
	public String getType() {
		return type;
	}
	//云产品类型(1:一键检测，2云搜索，3云分析，4云网盘)
	public void setType(String type) {
		this.type = type;
	}
}

