package com.ucap.cloud_web.dto;


import java.util.Date;

import com.publics.util.page.Query;


/*** 前台页面传递基础数据<br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-30 16:35:27 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@SuppressWarnings("serial")public class SecurityHomeSituationRequest extends Query {

		//站点标识码
		private String siteCode;

		//服务周期ID
		private Integer servicePeriodId;

		//发现/扫描日期
		private String scanDate;

		public String getSiteCode() {
			return siteCode;
		}

		public void setSiteCode(String siteCode) {
			this.siteCode = siteCode;
		}

		public Integer getServicePeriodId() {
			return servicePeriodId;
		}

		public void setServicePeriodId(Integer servicePeriodId) {
			this.servicePeriodId = servicePeriodId;
		}

		public String getScanDate() {
			return scanDate;
		}

		public void setScanDate(String scanDate) {
			this.scanDate = scanDate;
		}



	

}

