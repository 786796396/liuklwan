package com.ucap.cloud_web.dto;

import java.util.Date;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>sjy<br>
 * <b>日期：</b> 2016-06-28 11:44:53 <br>
 * <b>版权所有：<b>版权所有(C) 2016<br>
 */
@SuppressWarnings("serial")
public class BigAuthDetailRequest extends Query {


		//组织机构编码
		private String orgSiteCode;

		//des加密码
		private String desCode;

		//状态（1-有效  2-无效）
		private Integer status;
		//开始时间
		private String startDate;
		//结束时间
		private String endDate;
		//tab标签名称1
		private String tabNameOne;
		//tab标签名称2
		private String tabNameTwo;
		//tab标签名称3
		private String tabNameThree;
		//创建时间
		private Date createTime;

		//修改时间
		private Date modifyTime;
		//当前时间
		private String curDate;

		public String getOrgSiteCode() {
			return orgSiteCode;
		}

		public void setOrgSiteCode(String orgSiteCode) {
			this.orgSiteCode = orgSiteCode;
		}

		public String getDesCode() {
			return desCode;
		}

		public void setDesCode(String desCode) {
			this.desCode = desCode;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getTabNameOne() {
			return tabNameOne;
		}

		public void setTabNameOne(String tabNameOne) {
			this.tabNameOne = tabNameOne;
		}

		public String getTabNameTwo() {
			return tabNameTwo;
		}

		public void setTabNameTwo(String tabNameTwo) {
			this.tabNameTwo = tabNameTwo;
		}

		public String getTabNameThree() {
			return tabNameThree;
		}

		public void setTabNameThree(String tabNameThree) {
			this.tabNameThree = tabNameThree;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getModifyTime() {
			return modifyTime;
		}

		public void setModifyTime(Date modifyTime) {
			this.modifyTime = modifyTime;
		}

		public String getCurDate() {
			return curDate;
		}

		public void setCurDate(String curDate) {
			this.curDate = curDate;
		}

	

}
