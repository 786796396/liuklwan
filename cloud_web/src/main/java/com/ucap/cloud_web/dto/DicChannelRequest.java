package com.ucap.cloud_web.dto;


import java.util.List;

import com.publics.util.page.Query;
import com.ucap.cloud_web.entity.UpdateChannelInfo;


/*** 前台页面传递基础数据<br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:17:59 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@SuppressWarnings("serial")public class DicChannelRequest extends Query {
		//栏目名称(手动输入)
		private String channelName;
		//父类id
		private Integer parentId;
		/**
		 * 栏目id数组
		 */
		private List<UpdateChannelInfo> ids;
		
		public List<UpdateChannelInfo> getIds() {
			return ids;
		}

		public void setIds(List<UpdateChannelInfo> ids) {
			this.ids = ids;
		}

		public void setParentId(Integer parentId) {
			this.parentId = parentId;
		}

		public int getParentId() {
			return parentId;
		}

		public void setParentId(int parentId) {
			this.parentId = parentId;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		
		
		
}

