package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DetectionPeriodOrgRequest;import com.ucap.cloud_web.entity.DetectionPeriodOrg;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:17:35 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IDetectionPeriodOrgService {


	/**	* 添加数据	* @param detectionPeriodOrg			对象			(必填)	*/	public int add(DetectionPeriodOrg detectionPeriodOrg);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return detectionPeriodOrg	*/	public DetectionPeriodOrg get(Integer id);

	/**	* 修改数据	* @param DetectionPeriodOrg			对象			(必填)	*/	public void update(DetectionPeriodOrg detectionPeriodOrg);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DetectionPeriodOrg>	*/	public PageVo<DetectionPeriodOrg> query(DetectionPeriodOrgRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DetectionPeriodOrgRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DetectionPeriodOrg>	*/	public List<DetectionPeriodOrg> queryList(DetectionPeriodOrgRequest request);

}

