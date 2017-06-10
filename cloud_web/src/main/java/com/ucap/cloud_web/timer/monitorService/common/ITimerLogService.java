package com.ucap.cloud_web.timer.monitorService.common;

import java.util.Date;

import com.ucap.cloud_web.constant.TimedTaskLogType;

public interface ITimerLogService {

	// 添加错误日志
	public void addTimeTaskLog(String siteCode, String scanDate, String errorMsg, TimedTaskLogType taskLogType);

	// 创建或更新任务执行成功日志
	public void createOrModifyTaskLog(String scanDate, Date startTime, Date endTime, TimedTaskLogType taskLogType);

	// 创建成功日志:任务开始时间,先查询，有就不创建
	public void createStartTaskLog(String scanDate, TimedTaskLogType taskLogType);
	
	// 创建成功日志:任务开始时间,只插入，不判重
	public void createTaskLog(String scanDate, TimedTaskLogType taskLogType);

	// 修改成功日志:任务结束时间
	public void modifyEndTimeTaskLog(String scanDate, TimedTaskLogType taskLogType);

}
