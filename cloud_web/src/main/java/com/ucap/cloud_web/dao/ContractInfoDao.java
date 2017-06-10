package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.entity.ContractInfo;import com.publics.util.dao.GenericDao;/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-04 20:20:38 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ContractInfoDao extends GenericDao<ContractInfo>{

	/**
	 * @Description: 获取正式合同信息  
	 * @author cuichx --- 2016-6-14上午11:09:17     
	 * @param map
	 * @return
	 */
	List<ContractInfo> queryContractByMap(Map<String, Object> map);

	/**
	 * @Description: 获取有效合同以及合同当前的服务周期
	 * @author cuichx --- 2016-11-30下午2:54:21     
	 * @param paramMap
	 * @return
	 */
	List<ContractInfoRequest> queryListByMap(Map<String, Object> paramMap);
	/**
	 * 
	 * @描述: 站点  查询父级住址单位的  合同
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年2月15日下午1:49:16 
	 * @param conRequest2
	 * @return
	 */
	List<ContractInfo> queryByParentCon(Map<String, Object> param);
	/**
	 * @Description:通过10位网站标识码  查询所有为其买单的组织单位的合同信息
	 * @param map
	 * @return
	 */
	List<ContractInfo> queryOrgContractBySite(Map<String, Object> map);}

