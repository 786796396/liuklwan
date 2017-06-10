package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 10:46:39 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public class DicServiceUnuserful {


	//服务不实用问题类型表id	private int id;
	//服务不实用问题类型编码(0、1、2、3、4)	private int serviceUnuserfulCode;
	//问题类型说明：0办事指南要素缺失、1办事指南要素不准确、2附件未提供下载、3附件无法下载、4其他	private String remark;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set 服务不实用问题类型表id */	public void setId(int id){
		this.id=id;
	}
	/** get 服务不实用问题类型表id */	public int getId(){
		return id;
	}
	/** set 服务不实用问题类型编码(0、1、2、3、4) */	public void setServiceUnuserfulCode(int serviceUnuserfulCode){
		this.serviceUnuserfulCode=serviceUnuserfulCode;
	}
	/** get 服务不实用问题类型编码(0、1、2、3、4) */	public int getServiceUnuserfulCode(){
		return serviceUnuserfulCode;
	}
	/** set 问题类型说明：0办事指南要素缺失、1办事指南要素不准确、2附件未提供下载、3附件无法下载、4其他 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 问题类型说明：0办事指南要素缺失、1办事指南要素不准确、2附件未提供下载、3附件无法下载、4其他 */	public String getRemark(){
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
}

