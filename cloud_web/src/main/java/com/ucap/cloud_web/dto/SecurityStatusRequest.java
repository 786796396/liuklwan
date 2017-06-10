package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-09-02 16:14:53 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SecurityStatusRequest extends Query {
		//网站标示码
		private String siteCode;

		//扫描类别（1：安全扫描   2：全站死链扫描）
		private Integer type;

		//状态:(1:未监测  2:监测中  3:已完成)
		private Integer status;

		//是否已读（1:未读  2：已读，  默认1）
		private Integer isReaded;

		//是否删除（0：正常，1：删除；默认0）
		private Integer delFlag;

		public String getSiteCode() {
			return siteCode;
		}

		public void setSiteCode(String siteCode) {
			this.siteCode = siteCode;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getIsReaded() {
			return isReaded;
		}

		public void setIsReaded(Integer isReaded) {
			this.isReaded = isReaded;
		}

		public Integer getDelFlag() {
			return delFlag;
		}

		public void setDelFlag(Integer delFlag) {
			this.delFlag = delFlag;
		}



}

