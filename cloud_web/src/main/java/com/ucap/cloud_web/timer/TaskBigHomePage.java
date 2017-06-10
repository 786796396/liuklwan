package com.ucap.cloud_web.timer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.bizService.BigDataHomeBizService;
import com.ucap.cloud_web.constant.TimedTaskLogType;
import com.ucap.cloud_web.timer.monitorService.common.ITimerLogService;

/**
 * 描述： 大数据首页数据生成任务 包：com.ucap.cloud_web.timer 文件名称：TaskBigHomePage 公司名称：开普互联
 * 作者：yinna@ucap.com.cn 时间：2017-3-3下午1:34:50
 * 
 * @version V1.0
 */
@Service("taskBigHomePage")
public class TaskBigHomePage {

	@Autowired
	private BigDataHomeBizService bigDataHomeBizServiceImpl;
	
	@Autowired
	ITimerLogService timerLogServiceImpl;

	private Logger logger = Logger.getLogger(this.getClass());

	public void start() {
		logger.info("======taskBigHomePage========start");
		
		// 添加任务开始日志
		timerLogServiceImpl.createTaskLog(DateUtils.getTodayStandardStr(),
				TimedTaskLogType.BIG_HOME_TASK);
		
		
		bigDataHomeBizServiceImpl.createBigDataHomeJson();
		
		// 添加任务结束日志
		timerLogServiceImpl.modifyEndTimeTaskLog(DateUtils.getTodayStandardStr(), TimedTaskLogType.BIG_HOME_TASK);

		logger.info("======taskBigHomePage========end");
	}

}
