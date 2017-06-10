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
	 * @Description: 获取正式合同信息  
	 * @author cuichx --- 2016-6-14上午11:08:14     
	 * @param map
	 * @return
	 */
	public List<ContractInfo> queryContractByMap(Map<String, Object> map);

	/**
	 * @Description: 获取有效合同以及合同当前的服务周期
	 * @author cuichx --- 2016-11-30下午2:52:48     
	 * @param paramMap
	 * @return
	 */
	public List<ContractInfoRequest> queryListByMap(Map<String, Object> paramMap);
	/**
	 * 
	 * @描述: 站点  查询父级住址单位的  合同
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年2月15日下午1:49:16 
	 * @param conRequest2
	 * @return
	 */
	public List<ContractInfo> queryByParentCon(ContractInfoRequest conRequest2);
	/**
	 * @Description:通过10位网站标识码  查询所有为其买单的组织单位的合同信息
	 * @param map
	 * @return
	 */
	public List<ContractInfo> queryOrgContractBySite(Map<String, Object> map);

}
