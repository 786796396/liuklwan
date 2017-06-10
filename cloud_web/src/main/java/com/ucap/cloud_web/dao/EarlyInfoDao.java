package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.EarlyInfoRequest;
import com.ucap.cloud_web.entity.EarlyInfo;
/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:30 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface EarlyInfoDao extends GenericDao<EarlyInfo>{
	/**
	 * @Description: 组织机构查询预警信息
	 * @author cuichx --- 2015-11-2下午3:18:43     
	 * @param params
	 * @return
	 */
	List<EarlyInfo> queryByMap(Map<String, Object> params);
	/**
	 * @Description:获取组织机构下的所有的新预警数 
	 * @author cuichx --- 2015-12-18下午4:36:19     
	 * @param param
	 * @return
	 */
	EarlyInfo querySum(Map<String, Object> param);
	
	/**
	 * @Description: 分页   新预警的和
	 * @author sunjiang --- 2016-1-22上午11:31:56     
	 * @param param
	 * @return
	 */
	public List<EarlyInfo> queryEarlyInfo(Map<String, Object> param);
	
	/**
	 * @Description: 更新新预警数
	 * @author sunjiang --- 2016-1-25下午5:42:26     
	 * @param earlyInfo
	 */
	public void updateNewEarlyNum(EarlyInfo earlyInfo);
	/**
	 * @Description: 查询新预警数
	 * @author sunjiang --- 2016-1-25下午5:52:03     
	 * @param param
	 * @return
	 */
	public EarlyInfo queryNewEarlyNum(Map<String, Object> param);
	/**
	 * @Description: 查询总数
	 * @author sunjiang --- 2016-1-26上午10:40:13     
	 * @param request
	 * @return
	 */
	public int queryEarlyInfoCount(EarlyInfoRequest request);
	
	/**
	 * @Description: 每个站站的预警总数
	 * @author sunjiang --- 2016-3-29下午4:26:55     
	 * @param request
	 * @return
	 */
	public List<EarlyInfo> queryEarlyInfoList(EarlyInfoRequest request);}

