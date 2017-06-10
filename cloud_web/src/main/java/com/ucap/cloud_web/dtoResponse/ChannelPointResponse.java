/**
 * 
 */
package com.ucap.cloud_web.dtoResponse;

/**描述： 
 * 包：com.ucap.cloud_web.dtoResponse
 * 文件名称：ChannelPointResponse
 * 公司名称：开普互联
 * 作者：qinjy@ucap.com.cn
 * 时间：2017年4月13日下午3:58:04 
 * @version V1.0
 */
public class ChannelPointResponse {
	private int id; // 主键

	private String channelName; // 栏目名称

	private String channelUrl; // 栏目url

	private String jumpPageUrl; // 跳转url

	private Integer status; // 状态（监测中：1，未监测：0,标记删除：-1），取消监测将状态置为0，删除记录为标记删除状态值为-1

	private Integer linkStatus; // 连通状态连通状态（0：不连通，1：连通）

	private String dicName; // 类别

	private String dichName; // 子类
	
	private Integer dichId; //子类id
	

	public Integer getDichId() {
		return dichId;
	}

	public void setDichId(Integer dichId) {
		this.dichId = dichId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(Integer linkStatus) {
		this.linkStatus = linkStatus;
	}

	public String getDicName() {
		return dicName;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDichName() {
		return dichName;
	}

	public void setDichName(String dichName) {
		this.dichName = dichName;
	}

}
