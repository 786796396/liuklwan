package com.ucap.cloud_web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;

/**
 * <br>
 * <b>作者：</b>kefan<br>
 * <b>日期：</b> 2015-11-16 10:54:39 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

public interface DatabaseInfoDao extends GenericDao<DatabaseInfo> {

	DatabaseInfo getUsers(String siteCode);

	DatabaseInfo getUser(Map<String, Object> params);

//	List<Permission> getAdmin();
//
//	List<Permission> getGuest(String userName);

	void modifyPassword(DatabaseInfo databaseInfo);

	DatabaseInfo getUsersByEmailAndSiteCode(DatabaseInfo databaseInfo);

	/**
	 * @Description:获得二级节点
	 * @author kefan-- 2015-11-17 上午8:50:30
	 * @param parentSiteCode
	 * @return
	 */
	List<DatabaseInfo> getChildList(String parentSiteCode);

	/**
	 * @Description:获得二级节点的数量
	 * @author kefan-- 2015-11-17 上午8:50:30
	 * @param parentSiteCode
	 * @return
	 */
	int getChildCount(String parentSiteCode);
	
	
	
	
	
	
	/**
	 * @Description:
	 * @author kefan-- 2015-11-17 下午2:32:13
	 * @param 
	 * @param 
	 * @return
	 */
	/**
	 * @Description:模糊查询用户
	 * @author kefan-- 2015-11-17 下午2:33:44
	 * @param params:params中存放两个属性，prifix，suffix
	 * 			prefix：区分省的字段名称（两位）
	 * 			suffix：区分市的字段名称（两位）
	 * @return
	 */
	List<DatabaseInfo> getChildByLike(Map<String,String> params);
	/** @Description: 获取网站抽查中的本地数据
	 * @author zhurk --- 2015-12-7下午7:25:13     
	 * @param map
	 * @return           
	*/
	List<DatabaseInfo> getLocalData(Map<String,Object> map);
	
	/** @Description: 获取直辖市的tree数据  抽查 地方树结构  旧方法 注释掉
	 * @author zhurk --- 2015-12-9下午1:50:27     
	 * @param map
	 * @return           
	*/
//	List<TreeVo> queryLocalTree(Map<String,Object> map);
	
	
	/**
	 * @Description:模糊查询用户
	 * @author kefan-- 2015-11-17 下午2:33:44
	 * @param params:params中存放两个属性，prifix，suffix
	 * 			prefix：区分省的字段名称（两位）
	 * 			suffix：区分市的字段名称（两位）
	 * @return
	 */
	int countByLike(Map<String,String> params);
	
	
	
	
	
	/**
	 * @Description:根据siteCode模糊查询
	 * @author kefan-- 2015-11-17 下午7:41:30
	 * @param siteCode
	 * @return
	 */
	
	List<DatabaseInfo> getDatabaseInfoByLike(String siteCode);
	
	
	
	
	/**
	 * @Description:根据模糊查询统计数量
	 * @author kefan-- 2015-11-17 下午7:42:16
	 * @param siteCode
	 * @return
	 */
	int countDatabaseInfoByLike(String siteCode);
	
	
	
	
	List<String> queryId(Map<String, Object> param);


	public DatabaseInfo findByDatabaseInfoCode(String siteCode);
	
	/**
	 * @Description: 查询同一订单下的siteCode
	 * @author sunjiang --- 2016-3-1上午12:32:28     
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> querySiteCodeList(Map<String, Object> paramMap);

	
	List<String> querySortCodeList();

	/**
	 * @Description: 根据抽查比例统计网站 个数
	 * @author cuichx --- 2016-5-10下午6:30:02     
	 * @param map
	 * @return
	 */
	int queryCountByMap(Map<String, Object> map);

	/**
	 * @Description: 获取各省多级别网站个数
	 * @author: yangshuai --- 2016-5-14下午2:27:08
	 * @param map
	 * @return
	 */
	List<DatabaseInfoRequest> queryLevelCountByMap(Map<String, Object> map);

	/**
	 * @Description: 统计省、市、县组织单位的编码和站点个数
	 * @author cuichx --- 2016-5-11下午4:51:37     
	 * @param params
	 * @return
	 */
	List<DatabaseInfoRequest> queryShiAndXian(Map<String, Object> params);
	/**
	 * @Description: 随机获取，某个组织单位的N个信息
	 * @author cuichx --- 2016-5-12上午3:44:23     
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryRandomSite(Map<String, Object> paramMap);
	/**
	 * @Description: 随机获取，某个组织单位(全部级别)的N个信息
	 * @author: yangshuai --- 2016-5-14下午3:06:59
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryLevelRandomSite(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  省级数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:03:29
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryProvince(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析 市或者县数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:04:15
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> querySublevel(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  部委数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:10:44
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryBm(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  部委下级数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:14:31
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryBmSublevel(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  门户数据 
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:15:06
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryGateway(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  部委门户数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:15:39
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryBmGateway(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  本级部门数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:15:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryLevelDepartment(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:查询所有站点
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-28下午7:04:08
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryAllSite(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析 查询当前登录组织单位最小level
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-31上午11:32:25
	 * @param paramMap
	 * @return
	 */
	Integer queryMinLevel(Map<String, Object> paramMap);
	/**
	 * @Description: 站点数据预览-获取各省市县统计数据
	 * @author: yangshuai --- 2016-7-1下午1:10:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfoRequest> queryProvinceCountByMap(Map<String, Object> paramMap);
	/**
	 * @Title: 站点数据预览-获取各省市县统计数据  全面检测数量
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-10下午6:14:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfoRequest> queryProvinceScan(Map<String, Object> paramMap);
	
	/**
	 * @Description: 站点数据预览-获取各省市县统计数据
	 * @author: yangshuai --- 2016-7-1下午1:17:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfoRequest> queryCountBm(Map<String, Object> paramMap);
	/**
	 * @Description: 关联 dic_Scan 获取原因
	 * @author: linhb --- 2016-8-19下午1:17:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryIsScan(Map<String, Object> pMap);
	/**
	 * @Description: 临时
	 * @author yangs --- 2016-5-10下午6:30:02     
	 * @param map
	 * @return
	 */
	int queryCountTemp(Map<String, Object> map);
	/**
	 * @Description: 临时
	 * @author: yangs --- 2016-8-19下午1:17:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> queryTemp(Map<String, Object> map);

	/**
	 * 连接databaselink表进行查询
	 * @param param
	 * @return
	 */
	List<DatabaseInfo> queryListJoinDatabaseTree(HashMap<String, Object> hashMap);
	
	void update(DatabaseInfo databaseInfo);

	/**
	 * @描述:下级单位 分页
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月2日下午6:02:05
	 * @param param
	 * @return
	 */

	List<DatabaseInfo> getDatabaseInfoBycode(Map<String, Object> param);

	/**
	 * @描述:分页 总条数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月2日下午6:02:11
	 * @param param
	 * @return
	 */

	int getDatabaseInfoCount(Map<String, Object> param);
	/**
	 * 
	 * @描述:关系tree表查询站点基本信息
	 * @作者:cuichx
	 * @时间:2017年5月3日下午8:12:51
	 * @param req
	 * @return
	 */
	List<DatabaseInfo> getSiteInfoByTree(Map<String, Object> paramMap);
	
	List<DatabaseInfo> queryListJoinSecurityFatal(HashMap<String, Object> hashMap);
}
