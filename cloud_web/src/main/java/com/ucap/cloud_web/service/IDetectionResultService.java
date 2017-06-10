package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.DetectionResultRequest;
import com.ucap.cloud_web.entity.DetectionResult;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-11-20 16:56:11 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDetectionResultService {


	/**	* 添加数据	* @param detectionResult			对象			(必填)	*/	public void add(DetectionResult detectionResult);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return detectionResult	*/	public DetectionResult get(Integer id);

	/**	* 修改数据	* @param DetectionResult			对象			(必填)	*/	public void update(DetectionResult detectionResult);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DetectionResult>	*/	public PageVo<DetectionResult> query(DetectionResultRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DetectionResultRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DetectionResult>	*/	public List<DetectionResult> queryList(DetectionResultRequest request);
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
	public void batchUpdate(HashMap<String, Object> param);
	/**
	 * @Description: 个数
	 * @author sunjiang --- 2016-1-27下午4:08:25     
	 * @param param
	 * @return
	 */
	public int getListCount(HashMap<String, Object> param);

	/**
	 * @描述:统计数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年1月14日上午11:48:29
	 * @param paramMap
	 * @return
	 */

	public List<DetectionResult> getList(HashMap<String, Object> paramMap);
}

