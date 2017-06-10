package com.ucap.cloud_web.util.aspose;

/**
 * <p>Description: </p>
 * <p>@Package：com.demo.util </p>
 * <p>Title: AsposeImage </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：masl@ucap.com.cn </p>
 * <p>@date：2016-7-15下午2:50:52 </p>
 */
public class AsposeImage {
	private String path;
	private int width;
	private int height;

	public AsposeImage(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
