package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.CorrectContentDetailRequest;
import com.ucap.cloud_web.entity.CorrectContentDetail;


/**


	/**

	/**

	/**

	/**

	/**

	/**

	/**
	/**
	 * @Description: 内容正确性每日个数统计查询表
	 * @author cuichx --- 2016-3-17下午10:11:35     
	 * @param hashMap
	 * @return
	 */
	public List<CorrectContentDetailRequest> queryCorrectLine(
			HashMap<String, Object> hashMap);
	
	/**
	 * @Description: 查询内容正确性详情表和databaseInfo表
	 * @author cuichx --- 2016-3-17下午10:58:20     
	 * @param hashMap
	 * @return
	 */
	public List<CorrectContentDetailRequest> queryWrongNum(
			HashMap<String, Object> hashMap);
	
	/**
	 * @Description: 组织单位首页面折线图
	 * @author sunjiang --- 2016-3-31上午11:15:39     
	 * @param hashMap
	 * @return
	 */
	List<CorrectContentDetailRequest> getCorrectLine(Map<Object, Object> parapMap);

	/**
	 * @描述:日常监测-疑似错别字数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午3:10:33
	 * @param hashMap
	 * @return
	 */

	public List<CorrectContentDetailRequest> getCorrectContentList(HashMap<String, Object> hashMap);
	/**
	 * @描述:日常监测-疑似错别字数据---总记录数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月23日下午3:10:33
	 * @param hashMap
	 * @return
	 */
	public int getCorrectContentListCount(HashMap<String, Object> paraMap);

}
