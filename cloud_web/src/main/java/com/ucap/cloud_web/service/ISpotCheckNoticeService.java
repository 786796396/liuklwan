package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;import com.ucap.cloud_web.entity.SpotCheckNotice;


/*** <br>* <b>作者：</b>yangshuai<br>* <b>日期：</b> 2016-06-01 18:43:28 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpotCheckNoticeService {


	/**	* 添加数据	* @param spotCheckNotice			对象			(必填)	*/	public void add(SpotCheckNotice spotCheckNotice);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spotCheckNotice	*/	public SpotCheckNotice get(Integer id);

	/**	* 修改数据	* @param SpotCheckNotice			对象			(必填)	*/	public void update(SpotCheckNotice spotCheckNotice);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpotCheckNotice>	*/	public PageVo<SpotCheckNotice> query(SpotCheckNoticeRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpotCheckNoticeRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpotCheckNotice>	*/	public List<SpotCheckNotice> queryList(SpotCheckNoticeRequest request);

}

