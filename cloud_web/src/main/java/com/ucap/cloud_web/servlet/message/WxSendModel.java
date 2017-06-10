package com.ucap.cloud_web.servlet.message;

import java.util.Map;

/**
 * <p>Description: 发送微信模板消息对象</p>
 * <p>@Package：com.ucap.cloud_web.servlet.message </p>
 * <p>Title: WxSendModel </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2015-12-31上午9:54:17 </p>
 */
public class WxSendModel {
	private String template_id;//模板编号
	private String touser;//微信客户唯一标识
	private String url;//详情链接
	private String topcolor;
	private Map<String,WxSendData> data;
	
	
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public Map<String, WxSendData> getData() {
		return data;
	}
	public void setData(Map<String, WxSendData> data) {
		this.data = data;
	}
	
	
	
}
