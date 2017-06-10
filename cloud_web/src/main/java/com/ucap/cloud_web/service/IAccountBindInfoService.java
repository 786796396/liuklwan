package com.ucap.cloud_web.service;


import java.util.List;import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.AccountBindInfoRequest;import com.ucap.cloud_web.entity.AccountBindInfo;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-01-04 14:48:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IAccountBindInfoService {


	/**	* 添加数据	* @param accountBindInfo			对象			(必填)	*/	public void add(AccountBindInfo accountBindInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return accountBindInfo	*/	public AccountBindInfo get(Integer id);

	/**	* 修改数据	* @param AccountBindInfo			对象			(必填)	*/	public void update(AccountBindInfo accountBindInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<AccountBindInfo>	*/	public PageVo<AccountBindInfo> query(AccountBindInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(AccountBindInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<AccountBindInfo>	*/	public List<AccountBindInfo> queryList(AccountBindInfoRequest request);
	/**
	 * @Description:查询客户信息表和微信绑定账户信息表
	 * @author cuichx --- 2015-12-25上午11:28:09     
	 * @return
	 */
	public AccountBindInfoRequest queryByMap(Map<String, Object> paramMap);

	
	/**
	 * @Description: 通过网站标识码查询客户信息
	 * @author cuichx --- 2015-12-30下午8:53:34     
	 * @param map
	 * @return
	 */
	public List<AccountBindInfoRequest> queryBySiteCode(Map<String, Object> map);

	/**
	 * @Description: 
	 * @author cuichx --- 2016-1-6下午8:42:38     
	 * @param earlyMap
	 * @return
	 */
	public List<AccountBindInfoRequest> queryEarlyInfo(
			Map<String, Object> earlyMap);


	/**
	 * @Description:联表查询微信绑定账户信息表和站点信息表 
	 * @author cuichx --- 2016-1-13下午9:46:41     
	 * @param paramMap
	 * @return
	 */
	public List<AccountBindInfoRequest> queryTBSite(Map<String, Object> paramMap);
	
	/**
	 * @Description: 绑定账户信息、合同信息表、服务周期表多表查询
	 * @author cuichx --- 2016-3-16下午2:53:35     
	 * @param paramMap
	 * @return
	 */
	public List<AccountBindInfoRequest> queryTBByMap(
			Map<String, Object> paramMap);
	/**
	 * @Description: 获取微信绑定账户中所有的免费的标识码（组织单位编号或者填报单位编码）
	 * @author cuichx --- 2016-5-25下午3:20:54     
	 * @param paramMap
	 * @return
	 */
	public List<AccountBindInfo> queryListByMap(Map<String, Object> paramMap);


	
}

