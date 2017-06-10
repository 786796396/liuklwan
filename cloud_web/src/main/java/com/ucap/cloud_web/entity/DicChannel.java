package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>cuichx<br>
 * <b>日期：</b> 2015-10-29 10:44:38 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class DicChannel {

	// 栏目类别码表id
	private int id;

	// 栏目名称(手动输入)
	private String channelName;

	// 父类id
	private int parentId;

	// 类型（2：业务系统，3：栏目） add by Nora 20151113
	private int type;

	// 是否更新 add by Nora 20160106
	private int isUpdate;

	// 应更新天数add by Nora 20160106
	private int updateDay;

	// 说明
	private String remark;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	/** set 栏目类别码表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 栏目类别码表id */
	public int getId() {
		return id;
	}

	/** set 栏目名称(手动输入) */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/** get 栏目名称(手动输入) */
	public String getChannelName() {
		return channelName;
	}

	/** set 父类id */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/** get 父类id */
	public int getParentId() {
		return parentId;
	}

	/** set 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** get 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** set 修改时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** get 修改时间 */
	public Date getModifyTime() {
		return modifyTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public int getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(int updateDay) {
		this.updateDay = updateDay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
