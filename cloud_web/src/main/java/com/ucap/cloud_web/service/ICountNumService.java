package com.ucap.cloud_web.service;

import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DetectionPeroidCount;

/**
 * 数量统计接口
 * 
 * @author Na.Y
 * 
 */
public interface ICountNumService {

	/**
	 * 首页连通性：不连通比例
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getConnectionHomeProportion(DatabaseInfo databaseInfo, String startDate, String endDate);

	/**
	 * 查询一段时间内连通性数据：返回数据包含：siteCode,总次数，成功次数，超时次数，成功占比，超时占比
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ConnectionAll getConnectionHomeData(String siteCode, String startDate, String endDate);

	/**
	 * 首页死链数量
	 * 
	 * 去掉删除的（del_flag=0），进行了url排除，排除了ConfigLinkExcept中数据
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getLinkHomeNum(String siteCode, String startDate, String endDate);

	/**
	 * 全站死链数量
	 * 
	 * 去掉删除的（del_flag=0），进行了url排除，排除了ConfigLinkExcept中数据
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getLinkAllNum(String siteCode, String startDate, String endDate, int servicePeriodId);

	/**
	 * 首页信息未更新天数是否超过两周（【是】或【否】）
	 * 
	 * 去掉删除的
	 * 1：是 0：否 -1：未知
	 * 
	 * 
	 * @param siteCode
	 * @param scanDate
	 * @return
	 */
	public int isSecurityHomeOverTwoWeek(String siteCode, String homeUrl, String scanDate);

	/**
	 * 获取栏目不更新数量
	 * 
	 *  以人工为主，如果机器扫描的不在人工数据之列，往里加 
	 *  
	 *  注意trim url判重
	 *  去掉删除的
	 *  
	 *  人工加的栏目不更新，需要添加服务周期过滤
	 * 
	 * @param siteCode
	 * @param scanDate
	 * @return
	 */
	public int getSecurityChannelNum(String siteCode, String startDate, String endDate,int servicePeroidId);

	/**
	 * 获取空白栏目数量
	 * 
	 * 去掉删除的
	 * @param siteCode
	 * @param servicePeroidId
	 *            服务周期id
	 * @return
	 */
	public int getSecurityBlankNum(String siteCode, String startDate, String endDate, int servicePeriodId);

	/**
	 * 获取互动回应差数量
	 * 
	 * 去掉删除的
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeriodId
	 * @return
	 */
	public int getSecurityResponseNum(String siteCode, String startDate, String endDate, int servicePeriodId);

	/**
	 * 获取服务不实用数量
	 * 
	 * 去掉删除的
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeriodId
	 * @param type
	 *            1：办事指南 3：附件下载
	 * @return
	 */
	public int getSecurityServiceNum(String siteCode, String startDate, String endDate, int servicePeriodId, int type);

	/**
	 * 获取服务不实用:在线系统连通性数量
	 * 
	 * 去掉删除的
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getConnectionBusinessNum(String siteCode, String startDate, String endDate);

	/**
	 * 获取严重错误:严重错别字数量
	 * 
	 * exposure=1
	 * correctType=3
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getCorrectContentNum(String siteCode, String startDate, String endDate);

	/**
	 * 获取严重错误:类型：1 虚假或伪造内容 。2 反动、暴力或色情内容。 3 其它
	 * 
	 * 去掉删除的
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeriodId
	 * @param type
	 *            类型：1 虚假或伪造内容 。2 反动、暴力或色情内容。 3 其它
	 * @return
	 */
	public int getSecurityFatalErrorNum(String siteCode, String startDate, String endDate, int servicePeriodId, int type);

	/**
	 * 获取其他问题数量
	 * 
	 * 去掉删除的
	 * 
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeriodId
	 * @return
	 */
	public int getSecurityOthersNum(String siteCode, String startDate, String endDate, int servicePeriodId);
	
	/**
	 * 创建或修改DetectionPeroidCount(高级版按服务周期统计表)
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeroidId
	 * @return
	 */
	public void createOrUpdateDetectionPeroidCount(String siteCode, String startDate, String endDate, int servicePeroidId);
	
	/**
	 * 获取DetectionPeroidCount(高级版按服务周期统计表)
	 * 没有就创建，有直接返回
	 * @param siteCode
	 * @param startDate
	 * @param endDate
	 * @param servicePeroidId
	 * @return
	 */
	public DetectionPeroidCount getDetectionPeroidCount(String siteCode, String startDate, String endDate, int servicePeroidId);
}
