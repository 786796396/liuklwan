package com.ucap.cloud_web.bizService;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ucap.cloud_web.entity.EarlyDetail;

/**
 * 描述： 大数据分析-大数据首页业务层
 * 包：com.ucap.cloud_web.bizService
 * 文件名称：BigDataHomeBizService
 * 公司名称：开普互联
 * 作者：yinna@ucap.com.cn
 * 时间：2017-2-28下午8:45:11 
 * @version V1.0
 */
public interface BigDataHomeBizService {
	
	/**
	 * 
	 * @描述:创建大数据分析-大数据首页 json数据生成【bm0100,bm0100下级省级+部委组织单位】
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-3下午1:08:37
	 */
	public void createBigDataHomeJson();
	
	/**
	 * 
	 * @描述:创建大数据分析-大数据首页 json数据
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-2-28下午8:46:08 
	 * @param orgSiteCode 组织单位编码
	 */
	public void createBigHomeJsonData(String orgSiteCode);
	
	/**
	 * 
	 * 大数据组织单位健康指数
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:24:43 
	 * @param orgSiteCode
	 * @return
	 */
	public Map<String, Object> getIndexCount(String orgSiteCode);
	
	/**
	 * 
	 * 大数据健康指数折线图
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:24:43 
	 * @param orgSiteCode
	 * @return
	 */
	public Map<String, Object> getIndexCountLine(String orgSiteCode);
	
	
	/**
	 * 获取实时播报问题:内容保障问题
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1上午9:51:51 
	 * @param orgSiteCode
	 */
	public List<EarlyDetail> getBroadcastSecurity(String orgSiteCode);
	
	
	/**
	 * 获取实时播报问题:连通性
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1上午9:51:51 
	 * @param orgSiteCode
	 */
	public List<EarlyDetail> getBroadcastConnection(String orgSiteCode);
	
	/**
	 * 
	 * 大数据:获取组织单位下各状态站点分布情况（正常，关停，例外），以及监测站点数
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:24:43 
	 * @param orgSiteCode
	 * @return
	 */
	public Map<String, Object> getSiteStateInfo(String orgSiteCode);
	
	
	/**
	 * 
	 * 全国网站监测情况：全国监测页面数量,全国发现问题数量
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:24:43 
	 * @return
	 */
	public Map<String, Object> getAllScanProblemNum();
	

	/**
	 * 
	 * 组织单位用户访问量-连续7天
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:24:43 
	 * @return
	 */
	public Map<String, Object> getJcVisitOrg_Seven(String orgSiteCode);
	
	
	/**
	 * 获取组织单位下各市连通率和组织单位监测情况
	 * 
	 * 组织单位监测情况包含以下：
	 * 首页连通率 ( 一周 ),首页正常更新率 ( 两周 ),首页不可用链接数( 昨天 ), 内容更新总量 ( 昨天 ) ,首页更新总量 ( 昨天 )
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:46:58 
	 * @param orgSiteCode
	 * @return
	 */
	public Map<String, Object> getConnectionAndDailyScan(String orgSiteCode);
	
	
	
	/**
	 * 
	 * @描述:从json文件中解析出JSONObject
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午6:59:05 
	 * @param orgSiteCode
	 * @param key  key值
	 * @return
	 */
	public JSONObject parseBySiteCodeAndKey(String orgSiteCode,String key);
	
	
	//以下方法封装返回的数据集合，从json文件中取出对应值，并以JSONObject返回
	
	/**
	 * 从json中封装健康指数
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageIndexCount(String orgSiteCode);
	
	/**
	 * 从json中封装健康指数折线图
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageIndexCountLine(String orgSiteCode);
	
	/**
	 * 从json中封装实时播报信息（内容保障【昨日】+连通性【实时，当天】）
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageBroadcastInfo(String orgSiteCode);
	
	/**
	 * 从json中封装组织单位下各状态站点分布情况（正常，关停，例外），以及监测站点数
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageSiteStateInfo(String orgSiteCode);
	
	
	/**
	 * 网站监测情况：全国监测页面数量,全国发现问题数量
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageScanProblemNum(String orgSiteCode);
	
	/**
	 * 组织单位用户访问量
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageJcVisitOrg(String orgSiteCode);
	

	/**
	 * 组织单位下各市连通率和组织单位监测情况
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午3:58:59 
	 * @param orgSiteCode
	 * @return
	 */
	public JSONObject packageConneDailyScan(String orgSiteCode);
	/**
	 * @描述:获取概览页面 配置日期
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-3上午9:36:34 
	 * @return
	 */
	public String queryHomePageDate();
	/**
	 * @描述:大数据获取配置查询日期
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-5-3上午11:19:13 
	 * @return
	 */
	public String queryDate();
}
