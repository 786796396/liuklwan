package com.ucap.cloud_web.servlet.message;

/**
 * 文本消息
 * 
 * @author lixuxiang
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}