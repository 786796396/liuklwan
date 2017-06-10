package com.ucap.cloud_web.timer.monitorService.impl.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.constant.TimedTaskLogType;
import com.ucap.cloud_web.dto.TimedTaskLogRequest;
import com.ucap.cloud_web.entity.TimedTaskLog;
import com.ucap.cloud_web.service.ITimedTaskLogService;
import com.ucap.cloud_web.timer.monitorService.common.ITimerLogService;

@Service
public class TimerLogServiceImpl implements ITimerLogService {

	@Autowired
	private ITimedTaskLogService timedTaskLogServiceImpl;

	@Override
	public void addTimeTaskLog(String siteCode, String scanDate, String errorMsg, TimedTaskLogType taskLogType) {
		// 保存操作日志
		TimedTaskLog timedTaskLog = new TimedTaskLog();
		timedTaskLog.setSiteCode(siteCode);
		timedTaskLog.setScanDate(scanDate);
		timedTaskLog.setErrMesg(errorMsg);
		timedTaskLog.setType(taskLogType.getCode());
		timedTaskLog.setStatus(0);
		timedTaskLog.setRemark(taskLogType.getName());
		timedTaskLogServiceImpl.add(timedTaskLog);

	}

	@Override
	public void createOrModifyTaskLog(String scanDate, Date beginTime, Date endTime, TimedTaskLogType taskLogType) {

		TimedTaskLogRequest request = new TimedTaskLogRequest();
		request.setScanDate(scanDate);
		request.setType(taskLogType.getCode());
		List<TimedTaskLog> listLog = timedTaskLogServiceImpl.queryList(request);

		if (CollectionUtils.isEmpty(listLog)) {
			// 保存操作日志
			TimedTaskLog timedTaskLog = new TimedTaskLog();
			timedTaskLog.setScanDate(scanDate);
			timedTaskLog.setType(taskLogType.getCode());
			timedTaskLog.setRemark(taskLogType.getName());
			timedTaskLog.setStatus(1);
			timedTaskLog.setBeginTime(beginTime);
			timedTaskLog.setEndTime(endTime);
			timedTaskLogServiceImpl.add(timedTaskLog);
		} else {
			TimedTaskLog timedTaskLog = listLog.get(0);
			if (beginTime != null) {
				timedTaskLog.setBeginTime(beginTime);
			}

			if (endTime != null) {
				timedTaskLog.setEndTime(endTime);
			}

			timedTaskLogServiceImpl.update(timedTaskLog);
		}
	}

	@Override
	public void createStartTaskLog(String scanDate, TimedTaskLogType taskLogType) {

		TimedTaskLogRequest request = new TimedTaskLogRequest();
		request.setScanDate(scanDate);
		request.setType(taskLogType.getCode());
		List<TimedTaskLog> listLog = timedTaskLogServiceImpl.queryList(request);

		if (CollectionUtils.isEmpty(listLog)) {
			// 保存操作日志
			TimedTaskLog timedTaskLog = new TimedTaskLog();
			timedTaskLog.setScanDate(scanDate);
			timedTaskLog.setType(taskLogType.getCode());
			timedTaskLog.setStatus(1);
			timedTaskLog.setBeginTime(DateUtils.getNow());
			timedTaskLog.setEndTime(DateUtils.getNow());
			timedTaskLog.setRemark(taskLogType.getName());
			timedTaskLogServiceImpl.add(timedTaskLog);
		}

	}

	@Override
	public void createTaskLog(String scanDate, TimedTaskLogType taskLogType) {

		// 保存操作日志
		TimedTaskLog timedTaskLog = new TimedTaskLog();
		timedTaskLog.setScanDate(scanDate);
		timedTaskLog.setType(taskLogType.getCode());
		timedTaskLog.setStatus(1);
		timedTaskLog.setBeginTime(DateUtils.getNow());
		timedTaskLog.setEndTime(DateUtils.getNow());
		timedTaskLog.setRemark(taskLogType.getName());
		timedTaskLogServiceImpl.add(timedTaskLog);

	}

	@Override
	public void modifyEndTimeTaskLog(String scanDate, TimedTaskLogType taskLogType) {
		TimedTaskLogRequest request = new TimedTaskLogRequest();
		request.setScanDate(scanDate);
		request.setType(taskLogType.getCode());

		QueryOrder queryOrder = new QueryOrder("create_time", QueryOrderType.DESC);
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
		queryOrderList.add(queryOrder);
		request.setQueryOrderList(queryOrderList);
		
		List<TimedTaskLog> listLog = timedTaskLogServiceImpl.queryList(request);

		if (!CollectionUtils.isEmpty(listLog)) {
			TimedTaskLog timedTaskLog = listLog.get(0);
			timedTaskLog.setEndTime(DateUtils.getNow());
			timedTaskLogServiceImpl.update(timedTaskLog);
		}

	}

}
