package com.ucap.cloud_web.dto;


import com.publics.util.page.Query;


/**

		//网站标识码
		private String siteCode;
		//发现时间
		private String scanDate;
		
		//是否删除（0：正常，1：删除；默认0）
		private int delFlag;
		
		public String getSiteCode() {
			return siteCode;
		}
		public void setSiteCode(String siteCode) {
			this.siteCode = siteCode;
		}
		public String getScanDate() {
			return scanDate;
		}
		public void setScanDate(String scanDate) {
			this.scanDate = scanDate;
		}
		public int getDelFlag() {
			return delFlag;
		}
		public void setDelFlag(int delFlag) {
			this.delFlag = delFlag;
		}
}
