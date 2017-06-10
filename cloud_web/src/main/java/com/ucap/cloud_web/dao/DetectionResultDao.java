package com.ucap.cloud_web.dao;


import java.util.HashMap;
import java.util.List;
import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.entity.DetectionResult;
/**
	 * @Description:获取最新的时间 
	 * @author sunjiang --- 2015年11月23日上午11:44:04     
	 * @return
	 */
	public String maxScanDate();
	/**
	 * @Description:查询总数 
	 * @author sunjiang --- 2015年11月23日上午11:45:44     
	 * @param param
	 * @return
	 */
	public DetectionResult querySum(HashMap<String, Object> param);
	/**
	 * @Description:批量更新 
	 * @author sunjiang --- 2015年11月24日下午2:13:15     
	 * @param param
	 */
	/**
	 * @Description:列表 
	 * @author sunjiang --- 2015年11月26日下午5:27:53     
	 * @param param
	 * @return
	 */
	public List<DetectionResult> getList(HashMap<String, Object> param);
	/**
	 * @Description: 个数
	 * @author sunjiang --- 2016-1-27下午4:08:25     
	 * @param param
	 * @return
	 */
	public int getListCount(HashMap<String, Object> param);
