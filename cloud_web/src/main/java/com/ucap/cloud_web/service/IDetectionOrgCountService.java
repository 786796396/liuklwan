package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DetectionOrgCountRequest;import com.ucap.cloud_web.entity.DetectionOrgCount;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-05-20 11:36:17 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IDetectionOrgCountService {


	/**	* 添加数据	* @param detectionOrgCount			对象			(必填)	*/	public void add(DetectionOrgCount detectionOrgCount);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return detectionOrgCount	*/	public DetectionOrgCount get(Integer id);

	/**	* 修改数据	* @param DetectionOrgCount			对象			(必填)	*/	public void update(DetectionOrgCount detectionOrgCount);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DetectionOrgCount>	*/	public PageVo<DetectionOrgCount> query(DetectionOrgCountRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DetectionOrgCountRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DetectionOrgCount>	*/	public List<DetectionOrgCount> queryList(DetectionOrgCountRequest request);

}

