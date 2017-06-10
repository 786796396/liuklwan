package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**

	/**
	 * @Description: 通过网站标识码+昨天日期，查询健康指数统计表，获取健康总分数，
	 * @author cuichx --- 2016-1-5下午5:12:14     
	 * @param param
	 * @return
	 */
	public IndexCountRequest queryByMap(HashMap<String, Object> param);
	/**
	 * @Description: 查询健康指数统计表
	 * @author cuichx --- 2016-1-5下午8:53:02     
	 * @param param
	 * @return
	 */
	public List<IndexCountRequest> queryListByMap(HashMap<String, Object> param);
	/**
	 * @Description: 健康指数折线图
	 * @author sunjiang --- 2016-1-6下午4:45:54     
	 * @param param
	 * @return
	 */
	List<IndexCount> getLineList(HashMap<String, Object> param);

	/**
	 * @Description: 获取指定套餐类型的数据
	 * @author cuichx --- 2016-1-10下午5:43:20     
	 * @param param
	 * @return
	 */
	public List<IndexCountRequest> getListByMap(HashMap<String, Object> param);

}
