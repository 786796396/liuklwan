package com.ucap.cloud_web.dto.xstream;

import java.util.List;

import com.ucap.cloud_web.entity.Result;

public class Root {

	private String response;

	private String msg;

	// 0表示任务未完成，1表示任务完成 ,-1表示任务异常
	private String status;

	private String url;
	
	private String taskid;//(必填) 任务标识码，多个使用,分割
	private String date;//当前日期
	private String count; // 任务数量
	private String totalfailnum;//所有任务不连通总次数
	//返回结果集
	private List<Result> results;
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Root [response=" + response + ", msg=" + msg + ", status="
				+ status + ", url=" + url + "]";
	}



	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTotalfailnum() {
		return totalfailnum;
	}

	public void setTotalfailnum(String totalfailnum) {
		this.totalfailnum = totalfailnum;
	}
}
