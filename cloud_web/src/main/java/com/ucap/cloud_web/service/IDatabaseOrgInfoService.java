package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;import com.ucap.cloud_web.entity.DatabaseOrgInfo;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-03-17 18:11:30 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IDatabaseOrgInfoService {


	/**	* 添加数据	* @param databaseOrgInfo			对象			(必填)	*/	public void add(DatabaseOrgInfo databaseOrgInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return databaseOrgInfo	*/	public DatabaseOrgInfo get(Integer id);

	/**	* 修改数据	* @param DatabaseOrgInfo			对象			(必填)	*/	public void update(DatabaseOrgInfo databaseOrgInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DatabaseOrgInfo>	*/	public PageVo<DatabaseOrgInfo> query(DatabaseOrgInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DatabaseOrgInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DatabaseOrgInfo>	*/	public List<DatabaseOrgInfo> queryList(DatabaseOrgInfoRequest request);
	
	/**
	 * 通过组织单位编码获取DatabaseOrgInfo
	 * @param siteCode
	 * @return
	 */
	public DatabaseOrgInfo getDatabaseOrgByCode(String siteCode);

}

