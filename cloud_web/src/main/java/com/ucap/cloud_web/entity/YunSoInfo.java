package com.ucap.cloud_web.entity;

import java.util.Date;


/*** <br>* <b>作者：</b>wl@ucap.com.cn<br>* <b>日期：</b> 2016-12-05 16:39:56 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class YunSoInfo {


	//	private int id;
	//站点信息id	private int databaseInfoId;
	//网站标识码	private String siteCode;
	//单位名称	private String director;
	//联系人	private String linkmanName;
	//手机	private String telephone;
	//email	private String email;
	//服务项（错别字、栏目扫描、安全性检测、全站死链扫描、互动回应服务质量检测），可多选	private String serverPro;
	//服务状态（0：未启动，1：服务中，2，服务关闭，-1废弃服务）	private int status;
	//开通状态（0：未开通，1：申请中，2：开通通过，3：开通未通过）	private int type;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	
	//来源类型 1:一键检测 2:服务中心
	private Integer sourceType;

	/** get 来源类型 1:一键检测 2:服务中心 */	public Integer getSourceType() {
		return sourceType;
	}
	
	/** set 来源类型 1:一键检测 2:服务中心 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 站点信息id */	public void setDatabaseInfoId(int databaseInfoId){
		this.databaseInfoId=databaseInfoId;
	}
	/** get 站点信息id */	public int getDatabaseInfoId(){
		return databaseInfoId;
	}
	/** set 网站标识码 */	public void setSiteCode(String siteCode){
		this.siteCode=siteCode;
	}
	/** get 网站标识码 */	public String getSiteCode(){
		return siteCode;
	}
	/** set 单位名称 */	public void setDirector(String director){
		this.director=director;
	}
	/** get 单位名称 */	public String getDirector(){
		return director;
	}
	/** set 联系人 */	public void setLinkmanName(String linkmanName){
		this.linkmanName=linkmanName;
	}
	/** get 联系人 */	public String getLinkmanName(){
		return linkmanName;
	}
	/** set 手机 */	public void setTelephone(String telephone){
		this.telephone=telephone;
	}
	/** get 手机 */	public String getTelephone(){
		return telephone;
	}
	/** set email */	public void setEmail(String email){
		this.email=email;
	}
	/** get email */	public String getEmail(){
		return email;
	}
	/** set 服务项（错别字、栏目扫描、安全性检测、全站死链扫描、互动回应服务质量检测），可多选 */	public void setServerPro(String serverPro){
		this.serverPro=serverPro;
	}
	/** get 服务项（错别字、栏目扫描、安全性检测、全站死链扫描、互动回应服务质量检测），可多选 */	public String getServerPro(){
		return serverPro;
	}
	/** set 服务状态（0：未启动，1：服务中，2，服务关闭，-1废弃服务） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 服务状态（0：未启动，1：服务中，2，服务关闭，-1废弃服务） */	public int getStatus(){
		return status;
	}
	/** set 开通状态（0：未开通，1：申请中，2：开通通过，3：开通未通过） */	public void setType(int type){
		this.type=type;
	}
	/** get 开通状态（0：未开通，1：申请中，2：开通通过，3：开通未通过） */	public int getType(){
		return type;
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
}

