package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaChannelPointRequest;import com.ucap.cloud_web.entity.PaChannelPoint;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-23 15:04:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaChannelPointService {


	/**	* 添加数据	* @param paChannelPoint			对象			(必填)	*/	public int add(PaChannelPoint paChannelPoint);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paChannelPoint	*/	public PaChannelPoint get(Integer id);

	/**	* 修改数据	* @param PaChannelPoint			对象			(必填)	*/	public void update(PaChannelPoint paChannelPoint);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaChannelPoint>	*/	public PageVo<PaChannelPoint> query(PaChannelPointRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaChannelPointRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaChannelPoint>	*/	public List<PaChannelPoint> queryList(PaChannelPointRequest request);

}

