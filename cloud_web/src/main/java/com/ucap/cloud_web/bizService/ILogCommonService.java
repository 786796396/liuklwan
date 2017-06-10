package com.ucap.cloud_web.bizService;

import com.ucap.cloud_web.constant.LogType;

public interface ILogCommonService {

	public void createLog(String siteCode, String targetObject,String contentsBefore,
			String contentsAfter,LogType logType,String operator,String remark);

}
