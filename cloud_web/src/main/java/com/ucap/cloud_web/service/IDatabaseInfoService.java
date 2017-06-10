package com.ucap.cloud_web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.MenuModel;
import com.ucap.cloud_web.service.exception.EmailException;

/**
 * <p>
 * 
 * @Description:网站信息： </p>
 *                    <p>
 * @Package com.ucap.cloud_web.service
 *          </p>
 *          <p>
 * @Title: IDatabaseInfoService
 *         </p>
 *         <p>
 * @Company:开普互联 </p>
 *               <p>
 * @author： kefan
 *          </p>
 *          <p>
 * @date： 2015-11-16下午4:48:03
 *        </p>
 */
public interface IDatabaseInfoService {

	/**
	 * 添加数据
	 * 
	 * @param databaseInfo
	 *            对象 (必填)
	 */
	public void add(DatabaseInfo databaseInfo);

	/**
	 * 通过主键获取对象数据
	 * 
	 * @param id
	 *            主键 (必填)
	 * @return databaseInfo
	 */
	public DatabaseInfo get(Integer id);

	/**
	 * 修改数据
	 * 
	 * @param DatabaseInfo
	 *            对象 (必填)
	 */
	public void update(DatabaseInfo databaseInfo);

	/**
	 * 通过id删除数据
	 * 
	 * @param id
	 *            对象 (必填)
	 */
	public void delete(Integer id);

	/**
	 * 通过对象获取分页数据
	 * 
	 * @param request
	 *            dto对象 (必填)
	 * @return PageVo<DatabaseInfo>
	 */
	public PageVo<DatabaseInfo> query(DatabaseInfoRequest request);

	/**
	 * 查询总条数
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return int
	 */
	public int queryCount(DatabaseInfoRequest request);

	/**
	 * 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<DatabaseInfo>
	 */
	public List<DatabaseInfo> queryList(DatabaseInfoRequest request);
	
	/**
	 * 查询对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<DatabaseInfo>
	 */
	public List<DatabaseInfo> queryListDatabaseInfo(Map<String, Object> map);
	
	/**
	 * 查询id对象集合
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<DatabaseInfo>
	 */
	public List<String> queryId(DatabaseInfoRequest request);

	/**
	 * @Description:根据siteCode和vCode查询用户信息
	 * @author kefan-- 2015-11-16 下午4:49:44
	 * @param params
	 *            ：存放的是siteCode,和vCode的值
	 * @return 网站基本信息
	 */

	public DatabaseInfo getUser(Map<String, Object> params);

	/**
	 * @Description:根据siteCode查询用户信息
	 * @author kefan-- 2015-11-16 下午4:57:18
	 * @param siteCode
	 * @return
	 */

	public DatabaseInfo getUsers(String siteCode);

//	/**
//	 * @Description:获得该网站用户的权限（国家办公室的权限）
//	 * @author kefan-- 2015-11-16 下午4:51:11
//	 * @return
//	 */
//	public List<Permission> getAdmin();

//	/**
//	 * @Description:获取普通机构的权限
//	 * @author kefan-- 2015-11-16 下午4:51:48
//	 * @param userName
//	 * @return
//	 */
//	public List<Permission> getGuest(String userName);

	public List<MenuModel> getAdminMenuModel();

	public List<MenuModel> getGuestMenuModel(Integer userId);

	/**
	 * @Description:发送邮件
	 * @author kefan-- 2015-11-14 下午4:36:55
	 * @param email
	 *            ：收邮件的邮箱
	 * @return：如果邮件发送成功，返回邮件验证码，如果邮件发送失败，返回null
	 * @throws EmailException
	 *             :邮件发送失败，则抛出此异常
	 */
	public String sendEmail(String email) throws EmailException;

	/**
	 * @Description:更新密码:
	 * @author kefan-- 2015-11-16 上午8:45:23
	 * @param user
	 */
	/**
	 * @Description:更新密码，实体对象user中至少应该有的属性：siteCode，vCode
	 * @author kefan-- 2015-11-16 下午4:52:15
	 * @param databaseInfo
	 */
	public void updatePassword(DatabaseInfo databaseInfo);

	/**
	 * @Description:根据siteCode和邮箱查询用户
	 * @author kefan-- 2015-11-16 下午4:53:43
	 * @param users
	 * @return
	 */
	public DatabaseInfo getUsersByEmailAndSiteCode(DatabaseInfo databaseInfo);

	/**
	 * @Description:根据parent_id查询机构用户
	 * @author kefan-- 2015-11-17 下午2:37:36
	 * @param parentSiteCode
	 *            ：对应数据库中的site_code
	 * @return
	 */
	public List<DatabaseInfo> getChildList(String parentSiteCode);

	/**
	 * @Description:模糊查询用户()
	 * @author kefan-- 2015-11-17 下午2:33:44
	 * @param params
	 *            :params中存放两个属性，prifix，suffix prefix：区分省的字段名称（两位）
	 *            suffix：区分市的字段名称（两位）
	 * @return
	 */
	public List<DatabaseInfo> getChildByLike(Map<String, String> params);
	/** @Description: 获取网站抽查中的本地数据
	 * @author zhurk --- 2015-12-7下午7:26:40     
	 * @param map
	 * @return           
	*/
	public List<DatabaseInfo> getLocalData(Map<String, Object> map);
	/** @Description:获取直辖市的tree数据  抽查 地方树结构  旧方法 注释掉
	 * @author zhurk --- 2015-12-9下午1:51:16     
	 * @param map
	 * @return           
	*/
//	public List<TreeVo> queryLocalTree(Map<String, Object> map);

	/**
	 * @Description:模糊查询用户()
	 * @author kefan-- 2015-11-17 下午2:33:44
	 * @param params
	 *            :params中存放两个属性，prifix，suffix prefix：区分省的字段名称（两位）
	 *            suffix：区分市的字段名称（两位）
	 * @return
	 */
	public int countChildByLike(Map<String, String> params);

	/**
	 * @Description:统计数量
	 * @author kefan-- 2015-11-17 下午2:38:53
	 * @param parentSiteCode
	 * @return
	 */
	public int getChildCount(String parentSiteCode);

	
	
	
	/**
	 * @Description:根据siteCode模糊查询databaseInfo(匹配后半部分)
	 * @author kefan-- 2015-11-17 下午7:46:05
	 * @param siteCode
	 * @return
	 */
	
	public List<DatabaseInfo> getDatabaseinfoByLike(String siteCode);
	
	
	
	/**
	 * @Description:根据siteCode模糊统计数量
	 * @author kefan-- 2015-11-17 下午7:46:05
	 * @param siteCode
	 * @return
	 */
	
	public int countDatabaseinfoByLike(String siteCode);
	
	
	public DatabaseInfo findByDatabaseInfoCode(String siteCode);
	
	/**
	 * @Description: 查询同一订单下的siteCode
	 * @author sunjiang --- 2016-3-1上午12:32:28     
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfo> querySiteCodeList(Map<String, Object> paramMap);

	
	
	public List<String> querySortCodeList();

	/**
	 * @Description: 根据抽查比例统计网站 个数
	 * @author cuichx --- 2016-5-10下午6:28:50     
	 * @param map
	 * @return
	 */
	public int queryCountByMap(Map<String, Object> map);

	/**
	 * @Description: 获取各省多级别网站个数
	 * @author: yangshuai --- 2016-5-14下午2:25:13
	 * @param map
	 * @return
	 */
	public List<DatabaseInfoRequest> queryLevelCountByMap(Map<String, Object> params);

	/**
	 * @Description: 统计省、市、县组织单位的编码和站点个数
	 * @author cuichx --- 2016-5-11下午4:49:43     
	 * @param params
	 * @return
	 */
	public List<DatabaseInfoRequest> queryShiAndXian(Map<String, Object> params);

	/**
	 * @Description: 随机获取，某个组织单位的N个站点信息
	 * @author cuichx --- 2016-5-12上午3:40:06     
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryRandomSite(Map<String, Object> paramMap);

	/**
	 * @Description: 随机获取，某个组织单位(全部级别)的N个站点信息
	 * @author: yangshuai --- 2016-5-14下午3:08:55
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryLevelRandomSite(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  省级数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:03:29
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryProvince(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析 市或者县数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:04:15
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> querySublevel(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  部委数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:10:44
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryBm(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  部委下级数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:14:31
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryBmSublevel(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  门户数据 
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:15:06
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryGateway(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  部委门户数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:15:39
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryBmGateway(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:大数据分析  本级部门数据
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-26下午8:15:52
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryLevelDepartment(Map<String, Object> paramMap);
	/**
	 * 
	 * 描述:查询所有站点
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-28下午7:04:08
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfo> queryAllSite(Map<String, Object> paramMap);
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
	 * @author: yangshuai --- 2016-7-1下午1:08:52
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfoRequest> queryProvinceCountByMap(Map<String, Object> paramMap);
	/**
	 * @Title: 站点数据预览-获取各省市县统计数据  全面检测数量
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-10下午6:14:52
	 * @param paramMap
	 * @return
	 */
	List<DatabaseInfoRequest> queryProvinceScan(Map<String, Object> paramMap);
	/**
	 * @Description: 站点数据预览-获取部委统计数据
	 * @author: yangshuai --- 2016-7-1下午1:15:52
	 * @param paramMap
	 * @return
	 */
	public List<DatabaseInfoRequest> queryCountBm(Map<String, Object> paramMap);
	/**
	 * 
	 * 关联 dic_scan 查询不检测原因
	 * @author:linhb ---2016-08-19
	 * @param pMap
	 * @return
	 */
	public List<DatabaseInfo> queryIsScan(Map<String, Object> pMap);
	/**
	 * 通过对象获取分页数据临时
	 * 
	 * @param request
	 *            dto对象 (必填)
	 * @return PageVo<DatabaseInfo>
	 */
	public PageVo<DatabaseInfo> queryTemp(DatabaseInfoRequest request);
	/**
	 * 查询总条数临时
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return int
	 */
	public int queryCountTemp(DatabaseInfoRequest request);
	/**
	 * 查询对象集合临时
	 * 
	 * @param request
	 *            前台参数 (必填)
	 * @return List<DatabaseInfo>
	 */
	public List<DatabaseInfo> queryListTemp(DatabaseInfoRequest request);
	/**
	 * 
	 * @描述:一键检测地址
	 * @作者:shaoxl@ucap.com.cn
	 * @时间:2016年12月13日上午10:36:17 
	 * @param paramMap
	 */
	public void getSelfAddress(Map<String, Object> paramMap);

	/**
	 * 连接database_link进行查询
	 * @param databaseInfoRequest
	 * @return
	 */
	public List<DatabaseInfo> queryListJoinDatabaseTree(
			HashMap<String, Object> hashMap);
	
	/**
	 * 
	 * @描述:用户体验云分析组织单位
	 * @作者:liukl@ucap.com.cn
	 * @时间:2017年2月28日14:38:21
	 * @param paramMap
	 */
	public void getExperienceAddress(Map<String, Object> paramMap);
	/**
	 * @描述:查询下级单位 分页
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月2日下午5:58:34
	 * @param req
	 * @return
	 */

	public PageVo<DatabaseInfo> getDatabaseInfoBycode(DatabaseInfoRequest req);
	
	/**
	 * 
	 * @描述:查询下级单位 分页 总条数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月3日下午8:12:51
	 * @param req
	 * @return
	 */
	public int getDatabaseInfoCount(DatabaseInfoRequest req);
	/**
	 * 
	 * @描述:关系tree表查询站点基本信息
	 * @作者:cuichx
	 * @时间:2017年5月3日下午8:12:51
	 * @param req
	 * @return
	 */
	public List<DatabaseInfo> getSiteInfoByTree(Map<String, Object> paramMap);

}
