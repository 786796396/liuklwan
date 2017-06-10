package com.ucap.cloud_web.bizService;

import java.util.List;

import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;

/**
 * 描述： databaseTreeinfo 公用方法接口
 * 包：com.ucap.cloud_web.bizService
 * 文件名称：DatabaseTreeBizService
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2017-1-16上午11:14:51 
 * @version V1.0
 */
public interface DatabaseTreeBizService {
	/**
	 * 
	 * @描述:获取上级组织单位编码
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-16上午11:33:41 
	 * @param siteCode
	 * @return
	 */
	public String getUpperOrgCode(String siteCode);
	/**
	 * 
	 * @描述:查询组织单位下的填报单位（注：不是所有的站点，只是父id挂在该站点的）
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-16上午11:15:37 
	 * @return List<DatabaseTreeInfo>
	 */
	public List<DatabaseInfo> localLevelSite(DatabaseTreeInfoRequest databaseTreeInfoRequest);
	/**
	 * 
	 * @描述:查询组织单位下所有的10位站点
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-1-17上午10:43:06 
	 * @param databaseTreeInfoRequest.siteCode   可以区别是否门户    正常关停例外
	 * @return
	 */
	public List<DatabaseInfo> localLevelSiteAll(DatabaseTreeInfoRequest databaseTreeInfoRequest);

	/** 新表方法 */

	/**
	 * @描述:查询下级单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年2月13日下午7:57:06
	 * @param loginCode
	 * @return
	 */

	public List<DatabaseTreeInfo> getDatabaseTreeInfoList(DatabaseTreeInfoRequest request);
}
