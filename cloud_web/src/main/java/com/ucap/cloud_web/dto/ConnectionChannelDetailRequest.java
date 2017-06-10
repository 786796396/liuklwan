package com.ucap.cloud_web.dto;

import com.publics.util.page.Query;

/**
 * 前台页面传递基础数据<br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */
@SuppressWarnings("serial")
public class ConnectionChannelDetailRequest extends Query {

	// 网站标识码
		private String siteCode;

		// 扫描日期（yyyy-dd-mm）
		private String scanDate;
		private String startDate;
		private String endDate;

		// 连通状态
		private int state;
		//问题描述用来查关键字
		private String questionCode;
		
		// 关键栏目连通性url
		private String url;
		
		private Integer start;
		
		private String channelName;//栏目名称
		
	//	private Integer scanTime2;//
		
		private String scanTime2;
		private String groupBy;//分组
		


		
		
/*		public Integer getScanTime2() {
			return scanTime2;
		}

		public void setScanTime2(Integer scanTime2) {
			this.scanTime2 = scanTime2;
		}*/

		public String getScanTime2() {
			return scanTime2;
		}

		public void setScanTime2(String scanTime2) {
			this.scanTime2 = scanTime2;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public String getGroupBy() {
			return groupBy;
		}

		public void setGroupBy(String groupBy) {
			this.groupBy = groupBy;
		}

		public Integer getStart() {
			return start;
		}

		public void setStart(Integer start) {
			this.start = start;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
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

		public String getQuestionCode() {
			return questionCode;
		}

		public void setQuestionCode(String questionCode) {
			this.questionCode = questionCode;
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
