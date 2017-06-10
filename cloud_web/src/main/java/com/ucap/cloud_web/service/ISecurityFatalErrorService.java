package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityFatalErrorRequest;
import com.ucap.cloud_web.entity.DicFatalError;
import com.ucap.cloud_web.entity.SecurityFatalError;



/*** <br>* <b>作者：</b>sunjq<br>* <b>日期：</b> 2016-05-09 11:53:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISecurityFatalErrorService {


	/**	* 添加数据	* @param securityFatalError			对象			(必填)	*/	public void add(SecurityFatalError securityFatalError);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityFatalError	*/	public SecurityFatalError get(Integer id);

	/**	* 修改数据	* @param SecurityFatalError			对象			(必填)	*/	public void update(SecurityFatalError securityFatalError);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityFatalError>	*/	public PageVo<SecurityFatalError> query(SecurityFatalErrorRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityFatalErrorRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityFatalError>	*/	public List<SecurityFatalError> queryList(SecurityFatalErrorRequest request);
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  各个站点的严重问题统计数据 
	 * @author cuichx --- 2017-4-7下午2:40:49     
	 * @param paraMap
	 * @return
	 */
	public List<SecurityFatalErrorRequest> queryFatalErrorInfoByTree(
			HashMap<String, Object> paraMap);
	/**
	 * @Description:根据tree表查询  某个组织单位下  某个服务周期内  各个站点的严重问题统计数据 ----总记录数
	 * @author cuichx --- 2017-4-7下午2:40:49     
	 * @param paraMap
	 * @return
	 */
	public int queryFatalErrorInfoByTreeCount(HashMap<String, Object> paraMap);
}

