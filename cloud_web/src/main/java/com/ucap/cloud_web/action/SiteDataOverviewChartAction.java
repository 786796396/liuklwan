package com.ucap.cloud_web.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.BigDataHomeBizService;
import com.ucap.cloud_web.timer.TaskBigHomePage;

/**
 * 描述： 大数据分析-大数据首页
 * 包：com.ucap.cloud_web.action
 * 文件名称：SiteDataOverviewChartAction
 * 公司名称：开普互联
 * 作者：yinna@ucap.com.cn
 * 时间：2017-3-3下午1:41:29 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SiteDataOverviewChartAction extends BaseAction{

	@Autowired
	private BigDataHomeBizService bigDataHomeBizServiceImpl;
	
	@Autowired
	private TaskBigHomePage taskBigHomePage;

	
	
	/**
	 * 
	 * @描述:健康指数(大数据组织单位健康指数)
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午5:14:57
	 */
	public void indexCountOrg(){
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String loginSiteCode = getCurrentSiteCode();
			
		
			//获取广播内容,已经在业务逻辑层封装好
			JSONObject jsonResult = bigDataHomeBizServiceImpl.packageIndexCount(loginSiteCode);
		
			writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success","false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @描述:健康指数折线图
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午5:14:57
	 */
	public void indexCountLineBM(){
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String loginSiteCode = getCurrentSiteCode();
		
		
			//获取健康指数折线图,已经在业务逻辑层封装好
			JSONObject jsonResult = bigDataHomeBizServiceImpl.packageIndexCountLine(loginSiteCode);
			
		
			writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success","false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @描述:获取播报数据
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-1下午5:14:57
	 */
	public void getBroadcastDatas(){
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String loginSiteCode = getCurrentSiteCode();
			
		
			//获取广播内容,已经在业务逻辑层封装好
			JSONObject jsonResult = bigDataHomeBizServiceImpl.packageBroadcastInfo(loginSiteCode);
		
			//writerPrint(JSONObject.fromObject(resultMap).toString());
			writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success","false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}
	
	 
	/**
	 * 获取对应的组织单位 关停  例外    全面检测 站点
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-2下午8:54:22
	 */
	public void getSiteStateInfo(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			//update by Na.Y 20170302,采用从json中获取数据的方式
			//获取组织单位站点情况,已经在业务逻辑层封装好
			String loginSiteCode = getCurrentSiteCode();
            JSONObject jsonResult = bigDataHomeBizServiceImpl.packageSiteStateInfo(loginSiteCode);
        
            writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取网站监测情况：全国监测页面数量,全国发现问题数量
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-2下午8:54:22
	 */
	public void getScanProblemNum(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			//update by Na.Y 20170302,采用从json中获取数据的方式
			//获取网站监测情况：全国监测页面数量,全国发现问题数量,已经在业务逻辑层封装好
			String loginSiteCode = getCurrentSiteCode();
            JSONObject jsonResult = bigDataHomeBizServiceImpl.packageScanProblemNum(loginSiteCode);
        
            writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 组织单位用户访问量
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-2下午8:54:22
	 */
	public void getJcVisitOrg(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			//update by Na.Y 20170302,采用从json中获取数据的方式
			//获取组织单位用户访问量,已经在业务逻辑层封装好
			String loginSiteCode = getCurrentSiteCode();
            JSONObject jsonResult = bigDataHomeBizServiceImpl.packageJcVisitOrg(loginSiteCode);
        
            writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取大数据地图的连通率，以及当前组织单位下的--首页连通率 ( 一周 )，首页正常更新率 ( 两周 )，首页不可用链接数( 昨天 ) ，内容更新总量 ( 昨天 ) ，首页更新总量 ( 昨天 ) 
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-7下午2:28:33
	 */
	public void getDailyMap(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			//update by Na.Y 20170302,采用从json中获取数据的方式
			//获取组织单位用户访问量,已经在业务逻辑层封装好
			String loginSiteCode = getCurrentSiteCode();
            JSONObject jsonResult = bigDataHomeBizServiceImpl.packageConneDailyScan(loginSiteCode);
        
            writerPrint(jsonResult.toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成大数据首页数据
	 * @描述:
	 * @作者:yinna@ucap.com.cn
	 * @时间:2017-3-9上午9:22:13
	 */
	public void createBigHomeData(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		String startTime = DateUtils.getNowStandardStr();
		map.put("start", startTime);
		try {
			String orgSiteCode = request.getParameter("orgSiteCode");
			if(!StringUtils.isEmpty(orgSiteCode)){
				bigDataHomeBizServiceImpl.createBigHomeJsonData(orgSiteCode);
			}else{
				//bigDataHomeBizServiceImpl.createBigDataHomeJson();
				taskBigHomePage.start();
			}
			
			String endtime = DateUtils.getNowStandardStr();
			map.put("end", endtime);
		} catch (Exception e) {
			// TODO: handle exception
		}
		writerPrint(JSONObject.fromObject(map).toString());
		
	}
	

	

}
