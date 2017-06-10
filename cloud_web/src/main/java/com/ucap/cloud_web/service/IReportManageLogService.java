package com.ucap.cloud_web.service;


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
	 * @Description:将参数封装到map中，查询报告管理 
	 * @author cuichx --- 2015-12-10下午1:01:08     
	 * @param paramMap
	 * @return
	 */
	public List<ReportManageLogRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 报告管理发送成功失败统计
	 * @author cuichx --- 2015-12-18下午6:47:22     
	 * @param param
	 * @return
	 */
	public ReportManageLogRequest querySum(Map<String, Object> param);

	/**
	 * @Description: 将参数封装到map中，查询报告管理 
	 * @author cuichx --- 2016-1-21下午5:14:52     
	 * @param map
	 * @return
	 */
	public List<ReportManageLogRequest> queryListByMap(Map<String, Object> map);


}
