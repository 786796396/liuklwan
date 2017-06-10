package com.ucap.cloud_web.servlet.message;

/**
 * 音乐model
 * 
 * @author lixuxiang
 */
public class LinkMessage extends BaseMessage{
	// 链接名称
	private String Title;
	// 链接描述
	private String Description;
	// 链接
	private String Url;
	
	
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	



}