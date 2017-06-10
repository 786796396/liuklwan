package com.ucap.cloud_web.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dtoResponse.ChannelPointResponse;
import com.ucap.cloud_web.dtoResponse.DatabaseInfoResponse;
import com.ucap.cloud_web.entity.ChannelPoint;
/**
	public int queryCountByType(Map<String, Object> param);

	/**
	 * @描述:栏目数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月13日下午4:03:01
	 * @param hashMap
	 * @return
	 */

	public List<ChannelPointResponse> getChannelPointInfo(HashMap<String, Object> hashMap);

	/**
	 * @描述:下级站点信息
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月14日上午10:56:09
	 * @param hashMap
	 * @return
	 */

	public List<DatabaseInfoResponse> getlowerLevelList(HashMap<String, Object> hashMap);
}
