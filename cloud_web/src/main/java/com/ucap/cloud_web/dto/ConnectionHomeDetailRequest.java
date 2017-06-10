package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ConnectionHomeDetailRequest extends Query {

	// 网站标识码
		private String siteCode;

		// 扫描日期（yyyy-dd-mm）
		private String scanDate;

		// 连通状态
		private int state;
		
		//网站名称
		private String siteName;
		//监测时间
		private String scanTime;
		//7日首页不连通率
		private String linkerrprop7;
		
		private String startDate;
		private String endDate;

		
		
		public String getSiteName() {
			return siteName;
		}

		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}

		public String getScanTime() {
			return scanTime;
		}

		public void setScanTime(String scanTime) {
			this.scanTime = scanTime;
		}

		public String getLinkerrprop7() {
			return linkerrprop7;
		}

		public void setLinkerrprop7(String linkerrprop7) {
			this.linkerrprop7 = linkerrprop7;
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

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

}
