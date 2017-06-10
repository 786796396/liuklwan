package com.ucap.cloud_web.bizService;

import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dtoResponse.VerJsonResponse;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;

public interface DatabaseBizService {

	/**
	 * 
	 * @描述:通过siteCode 判断是否拥有上级单位
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月19日下午1:26:17
	 * @param siteCode
	 * @return
	 */
	public String superiorUnit(String siteCode);
	/**通过siteCode 判断是否拥有下级单位
	 * 
	 * @param siteCode
	 * @return
	 */
	public Integer isHasChridren(String siteCode); 
	/**
	 * 
	 * @描述:是否存在下级组织单位
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-1上午10:59:05 
	 * @param siteCode
	 * @return DatabaseTreeInfo
	 */
	public DatabaseTreeInfo isNextOrg(String siteCode);

	/**
	 * 
	 * @描述:根据sitecode获取 本级部门/下属单位 数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月21日下午1:51:05
	 * @param type
	 * @param siteCode
	 * @return
	 */
	public List<DatabaseInfo> getDatebaseInfoByType(String type, String siteCode);
	/**服务周期公用
	 * 
	 * @param siteCode
	 * @return
	 */
	public Map<String,Object> characterCycle(String siteCode,String siteCodeTb);
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
	 * @描述:通过父 id 获取 该组织单位下的 站点
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月28日下午4:09:39 
	 * @param sRequest
	 * @return
	 */
	public List<DatabaseInfo> getNatives(DatabaseTreeInfoRequest sRequest);
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
	 * 
	 * @描述:查询站点内容保障基本信息  的周期
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017年2月8日下午4:09:39 
	 * @param rRequest
	 * @return
	 */
	public List<ServicePeriodRequest> queryByRelationPeriodBasic(
			ServicePeriodRequest request);
	
	/**
	 * @描述:验证非空参数 
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日下午3:24:20 
	 * @param noNullParams:非空参数列表（格式：name|age）
	 * @return 
	 * @return boolean
	 */
	public VerJsonResponse verParams(Map<String, String> params, String noNullParams);
	
	/**
	 * @描述:获取签名
	 * @作者:masl@ucap.com.cn
	 * @时间:2016年11月24日下午1:09:05 
	 * @return 
	 * @return String
	 */
	public String getSign(Map<String, String> params);
	/**
	 * @描述:通过urlType获得资源类型
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-3-29上午10:08:12 
	 * @param urlType
	 * @return
	 */
	public int getResourceType(int urlType);

	/**
	 * 
	 * @描述:根据sitecode获取url
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月21日下午6:28:59
	 * @param request
	 * @return
	 */
	public String getDatabaseUrl(String siteCode);
	
	/**
	 * 查询内容保障模块的当前最近开始的合同
	 * @param siteCode
	 * @param nowDate
	 * @return
	 */
	public List<ContractInfo> getContractInfoServurity(String siteCode, String nowDate);
}
