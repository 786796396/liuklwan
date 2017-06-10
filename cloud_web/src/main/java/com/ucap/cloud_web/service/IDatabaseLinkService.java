package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.DatabaseLinkRequest;
import com.ucap.cloud_web.entity.DatabaseLink;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2016-03-21 16:16:55 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IDatabaseLinkService {


	/**	* 添加数据	* @param databaseLink			对象			(必填)	*/	public void add(DatabaseLink databaseLink);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return databaseLink	*/	public DatabaseLink get(Integer id);

	/**	* 修改数据	* @param DatabaseLink			对象			(必填)	*/	public void update(DatabaseLink databaseLink);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DatabaseLink>	*/	public PageVo<DatabaseLink> query(DatabaseLinkRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DatabaseLinkRequest request);
	
	/**
	 * @Description: 分组查询
	 * @author sunjiang --- 2016-3-21下午4:34:11     
	 * @param request
	 * @return
	 */
	public List<DatabaseLinkRequest> queryCountBySiteCode(DatabaseLinkRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DatabaseLink>	*/	public List<DatabaseLink> queryList(DatabaseLinkRequest request);
	/**
	 * @Title: 获取组织单位 单站预警 10位sitecode
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-2上午10:51:01
	 * @param request
	 * @return
	 */
	public List<DatabaseLink> queryEarlySingleSiteCode(DatabaseLinkRequest request);
	/**
	 * 
	 * @描述:填报单位 手动预警  ，查询 收费的组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午4:34:43 
	 * @param dataBaseLinkRequest
	 * @return
	 */
	public List<DatabaseLink> queryJoinContract(DatabaseLinkRequest dataBaseLinkRequest);

}

