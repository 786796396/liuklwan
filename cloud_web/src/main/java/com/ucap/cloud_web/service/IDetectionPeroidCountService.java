package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;import com.ucap.cloud_web.entity.DetectionPeroidCount;


/*** <br>* <b>作者：</b>Na.Y<br>* <b>日期：</b> 2016-09-13 14:51:28 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IDetectionPeroidCountService {


	/**	* 添加数据	* @param detectionPeroidCount			对象			(必填)	*/	public int add(DetectionPeroidCount detectionPeroidCount);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return detectionPeroidCount	*/	public DetectionPeroidCount get(Integer id);

	/**	* 修改数据	* @param DetectionPeroidCount			对象			(必填)	*/	public void update(DetectionPeroidCount detectionPeroidCount);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<DetectionPeroidCount>	*/	public PageVo<DetectionPeroidCount> query(DetectionPeroidCountRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(DetectionPeroidCountRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DetectionPeroidCount>	*/	public List<DetectionPeroidCount> queryList(DetectionPeroidCountRequest request);

}

