package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @Description: 获取组织单位内容保障问题-基本信息问题统计列表
	 * @author cuichx --- 2016-3-30下午6:19:00     
	 * @param hashMap
	 * @return
	 */
	public List<SecurityBasicInfoRequest> getProblemNum(
			HashMap<String, Object> hashMap);

	/**
	 * @Description: 同一监测周期  基本信息不更新>=8个
	 * @author cuichx --- 2016-7-15下午12:09:02     
	 * @param paramMap
	 * @return
	 */
	public List<SecurityBasicInfoRequest> queryBasicNum(Map<String, Object> paramMap);

}
