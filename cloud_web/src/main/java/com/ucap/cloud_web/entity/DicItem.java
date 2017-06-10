package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-13 20:17:57 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class DicItem {


	//	private int id;
	//父id	private int pId;
	//字典名称	private String name;
	//英文名称	private String enName;
	//字典值	private String value;
	//描述	private String remark;
	//编号（格式为0.1.21.33.）	private String code;
	//是否禁用删除（0是，1否）	private int status;
	//是否父节点（0：否，1，是）	private int isParent;
	//是否删除（0：否，1：是）	private int delStatus;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 父id */	public void setPId(int pId){
		this.pId=pId;
	}
	/** get 父id */	public int getPId(){
		return pId;
	}
	/** set 字典名称 */	public void setName(String name){
		this.name=name;
	}
	/** get 字典名称 */	public String getName(){
		return name;
	}
	/** set 英文名称 */	public void setEnName(String enName){
		this.enName=enName;
	}
	/** get 英文名称 */	public String getEnName(){
		return enName;
	}
	/** set 字典值 */	public void setValue(String value){
		this.value=value;
	}
	/** get 字典值 */	public String getValue(){
		return value;
	}
	/** set 描述 */	public void setRemark(String remark){
		this.remark=remark;
	}
	/** get 描述 */	public String getRemark(){
		return remark;
	}
	/** set 编号（格式为0.1.21.33.） */	public void setCode(String code){
		this.code=code;
	}
	/** get 编号（格式为0.1.21.33.） */	public String getCode(){
		return code;
	}
	/** set 是否禁用删除（0是，1否） */	public void setStatus(int status){
		this.status=status;
	}
	/** get 是否禁用删除（0是，1否） */	public int getStatus(){
		return status;
	}
	/** set 是否父节点（0：否，1，是） */	public void setIsParent(int isParent){
		this.isParent=isParent;
	}
	/** get 是否父节点（0：否，1，是） */	public int getIsParent(){
		return isParent;
	}
	/** set 是否删除（0：否，1：是） */	public void setDelStatus(int delStatus){
		this.delStatus=delStatus;
	}
	/** get 是否删除（0：否，1：是） */	public int getDelStatus(){
		return delStatus;
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

