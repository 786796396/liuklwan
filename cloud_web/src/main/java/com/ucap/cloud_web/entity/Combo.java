package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
public class Combo {

	// 套餐码表id
	private int id;

	// 套餐编码（A、B1、B2、C1、C2、D）
	private String comboCode;

	// 套餐说明
	private String remark;

	// 指标详情（指标id组成的字符串，以“,”隔开）
	private String indicatorInfo;

	// 是否有效（0：否，1：是）
	private int isValid;

	// 总分
	private double totalPoints;

	// 创建时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	public double getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}

	/** set 套餐码表id */
	public void setId(int id) {
		this.id = id;
	}

	/** get 套餐码表id */
	public int getId() {
		return id;
	}

	/** set 套餐编码（A、B1、B2、C1、C2、D） */
	public void setComboCode(String comboCode) {
		this.comboCode = comboCode;
	}

	/** get 套餐编码（A、B1、B2、C1、C2、D） */
	public String getComboCode() {
		return comboCode;
	}

	/** set 套餐说明 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** get 套餐说明 */
	public String getRemark() {
		return remark;
	}

	/** set 指标详情（指标id组成的字符串，以“,”隔开） */
	public void setIndicatorInfo(String indicatorInfo) {
		this.indicatorInfo = indicatorInfo;
	}

	/** get 指标详情（指标id组成的字符串，以“,”隔开） */
	public String getIndicatorInfo() {
		return indicatorInfo;
	}

	/** set 是否有效（0：否，1：是） */
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	/** get 是否有效（0：否，1：是） */
	public int getIsValid() {
		return isValid;
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

	@Override
	public String toString() {
		return "Combo [id=" + id + ", comboCode=" + comboCode + ", remark="
				+ remark + ", indicatorInfo=" + indicatorInfo + ", isValid="
				+ isValid + ", createTime=" + createTime + ", modifyTime="
				+ modifyTime + "]";
	}

}
