package com.ucap.cloud_web.entity;


import java.util.Date;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:22 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public class PaDefectDbType {


	//	private int id;
	//缺陷库表id（pa_defect_db）	private int defectId;
	//父id	private int parentId;
	//缺陷库名称	private String typeName;
	//基础缺陷描述	private String baseDesc;
	//特色缺陷描述	private String speciDesc;
	//级别（1：一级，2：二级）	private short layer;
	//删除标识（1：删除，2：正常）	private short isDelete;
	//创建时间	private Date createTime;
	//修改时间	private Date modifyTime;
	/** set  */	public void setId(int id){
		this.id=id;
	}
	/** get  */	public int getId(){
		return id;
	}
	/** set 缺陷库表id（pa_defect_db） */	public void setDefectId(int defectId){
		this.defectId=defectId;
	}
	/** get 缺陷库表id（pa_defect_db） */	public int getDefectId(){
		return defectId;
	}
	/** set 父id */	public void setParentId(int parentId){
		this.parentId=parentId;
	}
	/** get 父id */	public int getParentId(){
		return parentId;
	}
	/** set 缺陷库名称 */	public void setTypeName(String typeName){
		this.typeName=typeName;
	}
	/** get 缺陷库名称 */	public String getTypeName(){
		return typeName;
	}
	/** set 基础缺陷描述 */	public void setBaseDesc(String baseDesc){
		this.baseDesc=baseDesc;
	}
	/** get 基础缺陷描述 */	public String getBaseDesc(){
		return baseDesc;
	}
	/** set 特色缺陷描述 */	public void setSpeciDesc(String speciDesc){
		this.speciDesc=speciDesc;
	}
	/** get 特色缺陷描述 */	public String getSpeciDesc(){
		return speciDesc;
	}
	/** set 级别（1：一级，2：二级） */	public void setLayer(short layer){
		this.layer=layer;
	}
	/** get 级别（1：一级，2：二级） */	public short getLayer(){
		return layer;
	}
	/** set 删除标识（1：删除，2：正常） */	public void setIsDelete(short isDelete){
		this.isDelete=isDelete;
	}
	/** get 删除标识（1：删除，2：正常） */	public short getIsDelete(){
		return isDelete;
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

