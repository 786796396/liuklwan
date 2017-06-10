package com.ucap.cloud_web.dto;


import java.util.Date;

import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-05-24 15:52:08 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class DicConfigRequest extends Query {

	//
	private int id;

	//名称
	private String name;

	//值
	private String value;

	//描述
	private String description;

	//1:正常；2：删除
	private int status;

	//创建时间
	private Date createTime;

	//修改时间
	private Date modifyTime;

	/** set  */
	public void setId(int id){
		this.id=id;
	}

	/** get  */
	public int getId(){
		return id;
	}

	/** set 名称 */
	public void setName(String name){
		this.name=name;
	}

	/** get 名称 */
	public String getName(){
		return name;
	}

	/** set 值 */
	public void setValue(String value){
		this.value=value;
	}

	/** get 值 */
	public String getValue(){
		return value;
	}

	/** set 描述 */
	public void setDescription(String description){
		this.description=description;
	}

	/** get 描述 */
	public String getDescription(){
		return description;
	}

	/** set 1:正常；2：删除 */
	public void setStatus(int status){
		this.status=status;
	}

	/** get 1:正常；2：删除 */
	public int getStatus(){
		return status;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime(){
		return createTime;
	}

	/** set 修改时间 */
	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}

	/** get 修改时间 */
	public Date getModifyTime(){
		return modifyTime;
	}
}

