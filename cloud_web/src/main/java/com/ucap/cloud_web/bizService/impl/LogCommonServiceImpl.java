package com.ucap.cloud_web.bizService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucap.cloud_web.bizService.ILogCommonService;
import com.ucap.cloud_web.constant.LogType;
import com.ucap.cloud_web.entity.LogOperator;
import com.ucap.cloud_web.service.ILogOperatorService;

@Service
public class LogCommonServiceImpl implements ILogCommonService {

	@Autowired
	private ILogOperatorService logOperatorServiceImpl;

	@Override
	public void createLog(String siteCode, String targetObject,
			String contentsBefore, String contentsAfter, LogType logType,
			String operator, String remark) {

		LogOperator logOperator = new LogOperator();
		logOperator.setSiteCode(siteCode);
		logOperator.setContentsBefore(contentsBefore);
		logOperator.setContentsAfter(contentsAfter);
		logOperator.setOperator(operator);
		logOperator.setRemark(remark);
		logOperator.setTargetObject(targetObject);
		logOperator.setType(logType.getCode());
		logOperatorServiceImpl.add(logOperator);

	}

}
