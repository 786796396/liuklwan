package com.ucap.cloud_web.dtoResponse;
/**
 * 易分析登录接口封装数据实体类
 * @author luocheng
 *
 */
public class VerJsonResponse {
	private String sign;
	private String timestamp;
	private String errMsg;
	private String infoMsg;
	private boolean status = true;//默认为  true

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the infoMsg
	 */
	public String getInfoMsg() {
		return infoMsg;
	}

	/**
	 * @param infoMsg the infoMsg to set
	 */
	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

	/**
	 * @return the status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
