package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.SecurityServcieRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.SecurityServcie;
/**
	 * @Description:组织单位-日常监测  内容保障问题--服务不实用
	 * @author sunjiang --- 2015年11月19日下午2:38:38     
	 * @param map
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryBarNum(Map<String, Object> map);
	 * 将参数封装到map集合中查询对象集合
	 * @param paramMap
	 * @return List<SecurityServcieRequest> 
	 */
	List<SecurityServcieRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * 将参数封装到map集合中查询对象集合
	 * @param paramMap
	 * @return List<SecurityServcieRequest> 
	 */
	List<SecurityServcieRequest> queryByGroup(Map<String, Object> paramMap);
	 * @author zhurk --- 2015-12-2下午4:58:35     
	 * @param paramMap
	 * @return           
	*/
	List<SecurityServcieRequest> getProblemNum(Map<String, Object> paramMap);
	
	
	/**
	 * @Description: 根据siteCode和ServicePeriodId 查询当前站点服务不实用栏目个数
	 * @author luocheng --- 2017-01-22    
	 * @param paramMap
	 * @return
	 */
	public List<SecurityGuaranteeResponse> getServiceNumber(HashMap<String, Object> hashMap);
	/**
	 * @Description: 依据tree表  查询某个组织单位下某个服务周期内   服务不实用的统计个数
	 * @author cuichx --- 2017-4-7上午9:57:35     
	 * @param paraMap
	 * @return
	 */
	public List<SecurityServcieRequest> queryServiceInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description: 依据tree表  查询某个组织单位下某个服务周期内   服务不实用的统计个数----总记录数
	 * @author cuichx --- 2017-4-7上午9:56:04     
	 * @param paraMap
	 * @return
	 */
	public int queryServiceInfoByTreeCount(HashMap<String, Object> paraMap);
	
}
