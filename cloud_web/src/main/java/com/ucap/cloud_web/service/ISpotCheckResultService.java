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
	 * @Description: 获取每个省的抽查站点个数
	 * @author cuichx --- 2016-5-3下午7:16:11     
	 * @param params
	 * @return
	 */
	public List<SpotCheckResultRequest> queryByMap(Map<String, Object> params);
	
	/**
	 * 按抽查进度批量删除
	 * @param spotCheckSchedule
	 */
	void deleteBatchBySchedule(int spotCheckSchedule);

}
