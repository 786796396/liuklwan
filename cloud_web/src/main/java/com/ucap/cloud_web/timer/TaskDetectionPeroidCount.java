package com.ucap.cloud_web.timer;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.TimedTaskLogType;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.ICountNumService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDetectionPeroidCountService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.timer.monitorService.common.ITimerLogService;

/**
 * 按服务周期统计站点数据 任务
 * 
 * @author Na.Y
 * 
 */
@Service("taskDetectionPeroidCount")
public class TaskDetectionPeroidCount {

	@Autowired
	IServicePeriodService servicePeriodServiceImpl;

	@Autowired
	IRelationsPeriodService relationsPeriodServiceImpl;

	@Autowired
	private ICountNumService countNumServiceImpl;

	@Autowired
	ITimerLogService timerLogServiceImpl;

	private static Logger logger = Logger
			.getLogger(TaskDetectionPeroidCount.class);// 日志信息

	public void start() {

		logger.info("按服务周期统计站点数据任务 start............");

		// 添加任务开始日志
		timerLogServiceImpl.createTaskLog(DateUtils.getYesterdayStr(),
				TimedTaskLogType.PEROID_COUNT_TASK);
		// 1.查询待处理的服务周期关联表数据:套餐为高级版，服务周期开始时间<=昨天，结束时间>=昨天,
		String yesterDay = DateUtils.getYesterdayStr();
		RelationsPeriodRequest request = new RelationsPeriodRequest();
		request.setComboId(4);
		request.setYesterDay(yesterDay);
		request.setPageSize(Integer.MAX_VALUE);
		List<RelationsPeriod> listRelationsPeriod = relationsPeriodServiceImpl
				.queryList(request);

		if (CollectionUtils.isEmpty(listRelationsPeriod)) {
			return;
		}

		for (RelationsPeriod relationsPeriod : listRelationsPeriod) {

			try {
				ServicePeriod servicePeriod = servicePeriodServiceImpl
						.get(relationsPeriod.getServicePeriodId());

				if (null == servicePeriod) {
					continue;
				}

				String siteCode = relationsPeriod.getSiteCode();
				String startDate = DateUtils.formatStandardDate(servicePeriod
						.getStartDate());
				String endDate = DateUtils.formatStandardDate(servicePeriod
						.getEndDate());
				if (DateUtils.parseDateTime(endDate).getTime() > DateUtils
						.parseDateTime(yesterDay).getTime()) {
					endDate = yesterDay;
				}

				int servicePeroidId = relationsPeriod.getServicePeriodId();

				if (StringUtils.isEmpty(siteCode) || 0 == servicePeroidId
						|| StringUtils.isEmpty(startDate)
						|| StringUtils.isEmpty(endDate)) {
					continue;
				}

				countNumServiceImpl.createOrUpdateDetectionPeroidCount(
						siteCode, startDate, endDate, servicePeroidId);
			} catch (Exception e) {
				logger.info("TaskDetectionPeroidCount  errorMsg=="
						+ e.getMessage());
				continue;
			}

		}

		// 添加任务结束日志
		timerLogServiceImpl.modifyEndTimeTaskLog(DateUtils.getYesterdayStr(),
				TimedTaskLogType.PEROID_COUNT_TASK);

		logger.info("按服务周期统计站点数据任务 end............");

	}

	/*
	 * 处理所有高级版统计站点数据（按服务周期）
	 */
	public void startAll() {

		// 添加任务开始日志
		timerLogServiceImpl.createTaskLog(DateUtils.getYesterdayStr(),
				TimedTaskLogType.PEROID_COUNT_TASK);
		// 1.查询待处理的服务周期关联表数据:套餐为高级版
		String yesterDay = DateUtils.getYesterdayStr();
		RelationsPeriodRequest request = new RelationsPeriodRequest();
		request.setComboId(4);
		request.setPageSize(Integer.MAX_VALUE);
		List<RelationsPeriod> listRelationsPeriod = relationsPeriodServiceImpl
				.queryList(request);

		if (CollectionUtils.isEmpty(listRelationsPeriod)) {
			return;
		}

		for (RelationsPeriod relationsPeriod : listRelationsPeriod) {

			try {
				ServicePeriod servicePeriod = servicePeriodServiceImpl
						.get(relationsPeriod.getServicePeriodId());

				if (null == servicePeriod) {
					continue;
				}

				String siteCode = relationsPeriod.getSiteCode();
				String startDate = DateUtils.formatStandardDate(servicePeriod
						.getStartDate());
				String endDate = DateUtils.formatStandardDate(servicePeriod
						.getEndDate());
				if (DateUtils.parseDateTime(endDate).getTime() > DateUtils
						.parseDateTime(yesterDay).getTime()) {
					endDate = yesterDay;
				}
				int servicePeroidId = relationsPeriod.getServicePeriodId();

				countNumServiceImpl.createOrUpdateDetectionPeroidCount(
						siteCode, startDate, endDate, servicePeroidId);
			} catch (Exception e) {
				logger.info("TaskDetectionPeroidCount  errorMsg=="
						+ e.getMessage());
				continue;
			}

		}

		// 添加任务结束日志
		timerLogServiceImpl.modifyEndTimeTaskLog(DateUtils.getYesterdayStr(),
				TimedTaskLogType.PEROID_COUNT_TASK);

	}

}
