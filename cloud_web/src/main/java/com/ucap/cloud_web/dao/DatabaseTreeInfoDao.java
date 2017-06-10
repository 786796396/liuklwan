package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.constant.TreeVo;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-12-06 14:26:52 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface DatabaseTreeInfoDao extends GenericDao<DatabaseTreeInfo>{
	/**
	 * 
	 * @描述: 通过该站点id 作为下级的父id 获取该组织单位下  关停 例外  正常 上报 统计好的数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日上午11:04:28 
	 * @param rRequest
	 * @return
	 */
	List<DatabaseTreeInfo> getDatas(Map<String, Object> param);
	/**
	 * 
	 * @描述:通过该站点id 作为下级的父id 获取该组织单位下  关停 例外  正常  站点数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日上午11:04:33 
	 * @param sRequest
	 * @return
	 */
	List<DatabaseInfo> getNativeDatas(Map<String, Object> param);
	/**
	 * 通过父 id 获取下级 站点
	 * @描述:
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日下午4:13:23 
	 * @param param
	 * @return
	 */
	List<DatabaseInfo> getNatives(Map<String, Object> param);
	/**
	 * 通过父 id 获取下级 站点 查询总数
	 * @描述:
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日下午4:13:23 
	 * @param param
	 * @return
	 */
	int getNativeDatasCount(Map<String, Object> param);	/**
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
	public List<DatabaseInfo> queryDatabaseInfoList(Map<String, Object> param);
	/**
	 * @Title: 大数据查询填报单位集合
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-11上午9:47:04
	 * @param param
	 * @return
	 */
	public List<DatabaseInfo> querySiteDatabaseInfoList(Map<String, Object> param);
	/**
	 * @Title: 查询所有下级站点
	 * @Description:
	 * @author liukl@ucap.com.cn	2017年1月6日12:03:30
	 * @param param
	 * @return
	 */
	List<TreeVo> queryTree(DatabaseTreeInfoRequest treeInfo);
	
	/**
	 * @Title: 查询bm0100本级站点  ztree
	 * @Description:
	 * @author liukl@ucap.com.cn	2017年1月6日12:03:30
	 * @param param
	 * @return
	 */
	List<TreeVo> queryTreeBM(DatabaseTreeInfoRequest treeInfo);
	/**
	 * 
	 * @描述:通过父 code 获取 该组织单位下的 关停  例外  正常站点 统计
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年1月9日下午4:09:39 
	 * @param rRequest
	 * @return
	 */
	public DatabaseTreeInfo getInfoStates(Map<String, Object> param);
	/**
	 * @描述:关联  dataBaseInfo (使用到了  isexp)
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年2月16日下午2:03:00 
	 * @param databaseTreeInfoRequest
	 * @return
	 */
	List<DatabaseTreeInfo> queryListJoinInfo(Map<String, Object> param);

	/** 新表方法 */

	/**
	 * @描述:查询下级单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月14日下午2:26:50
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
	List<DatabaseTreeInfoRequest> queryListByTypeOrIsexp(Map<String, Object> param);

	/**
	 * @Description:  获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-13下午1:56:21     
	 * @param map
	 * @return
	 */
	List<String> queryDownSite(Map<String, Object> map);
	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-14下午2:03:35     
	 * @param map
	 * @return
	 */
	List<DatabaseTreeInfo> queryDownSiteInfo(Map<String, Object> map);
	
	
	/**
	 * 
	 * @描述:填报单位 手动预警  ，查询 收费的组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年11月22日下午4:37:25 
	 * @param param
	 * @return
	 */
	public List<DatabaseTreeInfo> queryJoinContract(Map<String, Object> param);
	/**
	 * @Description:  获取当前组织机构下面的有效监测站点（收费、监测中、非关停例外）
	 * @author cuichx --- 2017-3-28上午10:47:00     
	 * @param paraMap
	 * @return
	 */
	List<String> queryDownSite2(Map<String, Object> paraMap);

	/**
	 * @描述:code级别
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月4日下午9:22:52
	 * @param param
	 * @return
	 */

	Integer getSiteCodeLevel(Map<String, Object> param);
	/**
	 * 
	 * @描述:获取组织单位的  所有上级组织单位
	 * @作者:linhb@ucap.com.cn
	 * @时间:2017年5月9日下午4:47:57 
	 * @param map
	 * @return 
	 */
	DatabaseTreeInfo queryUpOrgSiteCode(Map<String, Object> map);

	/**
	 * @Description: 获取当前组织机构下面的有效监测站点（非关停例外）
	 * @author cuichx --- 2017-3-28上午10:46:03     
	 * @param paraMap
	 * @return
	 */
	List<DatabaseTreeInfoRequest> querySiteinfoByTree(
			Map<String, Object> paraMap);
}

