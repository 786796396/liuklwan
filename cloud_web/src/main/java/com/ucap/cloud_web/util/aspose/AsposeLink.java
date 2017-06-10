package com.ucap.cloud_web.util.aspose;

/**
 * <p>Description: </p>
 * <p>@Package：com.demo.util </p>
 * <p>Title: AsposeHyperlink </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：masl@ucap.com.cn </p>
 * <p>@date：2016-7-15下午2:55:08 </p>
 */
public class AsposeLink {
	private String text;
	private String href;

	public AsposeLink(String text, String href) {
		this.text = text;
		this.href = href;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
}
