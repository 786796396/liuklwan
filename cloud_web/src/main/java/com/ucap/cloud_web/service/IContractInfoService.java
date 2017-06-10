package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.ContractInfoRequest;import com.ucap.cloud_web.entity.ContractInfo;


/*** <br>* <b>作者：</b>ccx<br>* <b>日期：</b> 2016-03-04 20:20:38 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IContractInfoService {


	/**	* 添加数据	* @param contractInfo			对象			(必填)	*/	public void add(ContractInfo contractInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return contractInfo	*/	public ContractInfo get(Integer id);

	/**	* 修改数据	* @param ContractInfo			对象			(必填)	*/	public void update(ContractInfo contractInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ContractInfo>	*/	public PageVo<ContractInfo> query(ContractInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ContractInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ContractInfo>	*/	public List<ContractInfo> queryList(ContractInfoRequest request);

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

