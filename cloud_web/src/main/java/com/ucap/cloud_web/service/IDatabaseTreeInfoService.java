package com.ucap.cloud_web.service;


import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.constant.TreeVo;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-12-06 14:26:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IDatabaseTreeInfoService {


	/**	* 添加数据	* @param databaseTreeInfo			对象			(必填)	*/	public int add(DatabaseTreeInfo databaseTreeInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return databaseTreeInfo	*/	public DatabaseTreeInfo get(Integer id);

	/**	* 修改数据	* @param DatabaseTreeInfo			对象			(必填)	*/	public void update(DatabaseTreeInfo databaseTreeInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DatabaseTreeInfo>	*/	public PageVo<DatabaseTreeInfo> query(DatabaseTreeInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DatabaseTreeInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DatabaseTreeInfo>	*/	public List<DatabaseTreeInfo> queryList(DatabaseTreeInfoRequest request);
	/**
	 * 
	 * @描述: 通过该站点id 作为下级的父id 获取该组织单位下  关停 例外  正常 上报 统计好的数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日上午11:04:28 
	 * @param rRequest
	 * @return
	 */
	public List<DatabaseTreeInfo> getDatas(DatabaseTreeInfoRequest rRequest);
	/**
	 * 
	 * @描述:通过该站点id 作为下级的父id 获取该组织单位下  关停 例外  正常  站点数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日上午11:04:33 
	 * @param sRequest
	 * @return
	 */
	public PageVo<DatabaseInfo> getNativeDatas(DatabaseTreeInfoRequest sRequest);
	/**
	 * 
	 * @描述:通过 父id 获取  站点
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日下午4:11:22 
	 * @param sRequest
	 * @return
	 */
	public List<DatabaseInfo> getNatives(DatabaseTreeInfoRequest sRequest);
	/**
	 * 
	 * @描述:查询bm0100本级站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-28下午3:32:48 
	 * @param request
	 * @return
	 */
	public List<DatabaseInfo> queryBmLocalSites(DatabaseTreeInfoRequest request);
	
	/**
	 * @Title: 大数据查询组织单位集合
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-11上午9:47:04
	 * @param param
	 * @return
	 */
	public List<DatabaseInfo> queryDatabaseInfoList(DatabaseTreeInfoRequest request);
	/**
	 * @Title: 大数据查询填报单位集合
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-11上午9:47:04
	 * @param param
	 * @return
	 */
	public List<DatabaseInfo> querySiteDatabaseInfoList(DatabaseTreeInfoRequest request);

	/**
	 * @Title: 查询下级所有站点
	 * @Description:
	 * @author liukl@ucap.com.cn	2017年1月6日12:01:17
	 * @param param
	 * @return
	 */
	public List<TreeVo> queryTree(DatabaseTreeInfoRequest treeInfo);

	/**
	 * @Title: 查询bm0100本级站点 ztree
	 * @Description:
	 * @author liukl@ucap.com.cn	2017年1月6日12:01:17
	 * @param param
	 * @return
	 */
	public List<TreeVo> queryTreeBM(DatabaseTreeInfoRequest treeInfo);

	/**
	 * 
	 * @描述:通过父 code 获取 该组织单位下的 关停  例外  正常站点 统计
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年1月9日下午4:09:39 
	 * @param rRequest
	 * @return
	 */
	public DatabaseTreeInfo getInfoStates(DatabaseTreeInfoRequest rRequest);
	/**
	 * @描述:关联  dataBaseInfo (使用到了  isexp)
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年2月16日下午2:03:00 
	 * @param databaseTreeInfoRequest
	 * @return
	 */
	public List<DatabaseTreeInfo> queryListJoinInfo(DatabaseTreeInfoRequest databaseTreeInfoRequest);

	/** 新表方法 */

	/**
	 * @描述:查询下级单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月14日下午2:24:36
	 * @param map
	 * @return
	 */
	
	public List<DatabaseTreeInfo> getDatabaseTreeInfoList(DatabaseTreeInfoRequest request);
	/**
	 * 
	 * @描述: 查询组织单位下的  关停例外  本机门户 下属单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年2月17日下午6:22:33 
	 * @param databaseTreeInfoRequest
	 * @return
	 */
	public List<DatabaseTreeInfoRequest> queryListByTypeOrIsexp(DatabaseTreeInfoRequest databaseTreeInfoRequest);

	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-13下午1:54:28     
	 * @param map
	 * @return
	 */
	public List<String> queryDownSite(Map<String, Object> map);
	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-14下午2:02:22     
	 * @param map
	 * @return
	 */
	public List<DatabaseTreeInfo> queryDownSiteInfo(Map<String, Object> map);
	
	/**
	 * 
	 * @描述:填报单位 手动预警  ，查询 收费的组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午4:34:43 
	 * @param DatabaseTreeInfoRequest
	 * @return
	 */
	public List<DatabaseTreeInfo> queryJoinContract(DatabaseTreeInfoRequest databaseTreeInfoRequest);
	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-28上午10:46:03     
	 * @param paraMap
	 * @return
	 */
	public List<String> queryDownSite2(Map<String, Object> paraMap);

	/**
	 * @描述:code级别
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月4日下午9:22:02
	 * @param req
	 * @return
	 */

	public Integer getSiteCodeLevel(DatabaseTreeInfoRequest req);
	/**
	 * 
	 * @描述:获取组织单位的  所有上级组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年5月9日下午4:47:57 
	 * @param map
	 * @return 
	 */
	public DatabaseTreeInfo queryUpOrgSiteCode(Map<String, Object> map);
	
	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（非关停例外）
	 * @author cuichx --- 2017-3-28上午10:46:03     
	 * @param paraMap
	 * @return
	 */
	public List<DatabaseTreeInfoRequest> querySiteinfoByTree(
			Map<String, Object> paraMap);
}

