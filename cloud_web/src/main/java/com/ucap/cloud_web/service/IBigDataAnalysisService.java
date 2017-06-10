package com.ucap.cloud_web.service;

import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.Result;

public interface IBigDataAnalysisService {

	/**
	 * @Description: 拼装 调用大数据分析接口的地址参数---任务标识码
	 * @author cuichx --- 2016-5-27下午3:29:03     
	 * @param list
	 * @return
	 */
	public  Map<String,Object> listToStr(List<DatabaseInfo> list);
	
	/**
	 * 
	 * 描述:解析xml返回List结果
	 * 
	 * 作者：liujc@ucap.com.cn	2016-5-27上午11:11:17
	 * @param siteCodeStr
	 * @param type 0:汇总结果接口   1:站点接口
	 * @param Map<String, Object> siteCodeMap 
	 * @return
	 */
	public   List<Result> bigDataXml(String siteCodeStr,Integer type,Map<String, Object> siteCodeMap);
	/**
	 * @Title: 搜索引擎大数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-11-1下午7:28:36
	 * @param siteCodeStr
	 * @param type
	 * @param siteCodeMap
	 * @return
	 */
	public List<Result> baiduData(String siteCodeStr,Integer type,Map<String, Object> siteCodeMap);
	/**
	 * @Title: 下一级门户---获取组织机构下级地方门户网站的统计数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-13下午1:19:50
	 * @param orgSiteCode
	 * @return
	 */
	public List<DatabaseInfo> getNextSitesMH(String orgSiteCode,int type);
	/**
	 * @Title: 本级站点---获取组织机构本级站点网站的统计数据
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-13下午3:04:38
	 * @return
	 */
	public List<DatabaseInfo> getLocalSites(DatabaseTreeInfoRequest request);
	/**
	 * 
	 * @描述: 是否是末级
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-11-29下午8:07:50 
	 * @param request
	 * @return
	 */
	public boolean isLocalSites(DatabaseTreeInfoRequest request);
	/**
	 * @Title: 查询下级省市县 和下级部委 2级 3级
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-10-13下午3:49:41
	 * @param request
	 * @return
	 */
	public List<DatabaseInfo>  getNextCityAndCountry(DatabaseTreeInfoRequest request);
	/**
	 * @Description:判断用户的类型
	 * @author kefan-- 2015-11-15 下午7:18:20
	 * @return:用户的类型： 1:国办 2：省份 3:市 4:区县
	 */
	public int judgeUserType(String siteCode);
	/**
	 * 
	 * @描述: 获得监测网站数量
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-27下午3:48:29 
	 * @param level
	 * @param siteCode
	 * @return
	 */
	public Map<String, Result> queryLinkErrSiteNum(Integer level,String siteCode);
}
