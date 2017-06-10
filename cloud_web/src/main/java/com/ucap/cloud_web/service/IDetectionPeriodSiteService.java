package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DetectionPeriodSiteRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.DetectionPeriodSite;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-03-24 10:15:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IDetectionPeriodSiteService {


	/**	* 添加数据	* @param detectionPeriodSite			对象			(必填)	*/	public int add(DetectionPeriodSite detectionPeriodSite);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return detectionPeriodSite	*/	public DetectionPeriodSite get(Integer id);

	/**	* 修改数据	* @param DetectionPeriodSite			对象			(必填)	*/	public void update(DetectionPeriodSite detectionPeriodSite);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DetectionPeriodSite>	*/	public PageVo<DetectionPeriodSite> query(DetectionPeriodSiteRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DetectionPeriodSiteRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DetectionPeriodSite>	*/	public List<DetectionPeriodSite> queryList(DetectionPeriodSiteRequest request);


}

