package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.UpdateChannelDetailRequest;import com.ucap.cloud_web.entity.UpdateChannelDetail;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IUpdateChannelDetailService {


	/**	* 添加数据	* @param updateChannelDetail			对象			(必填)	*/	public void add(UpdateChannelDetail updateChannelDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return updateChannelDetail	*/	public UpdateChannelDetail get(Integer id);

	/**	* 修改数据	* @param UpdateChannelDetail			对象			(必填)	*/	public void update(UpdateChannelDetail updateChannelDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<UpdateChannelDetail>	*/	public PageVo<UpdateChannelDetail> query(UpdateChannelDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(UpdateChannelDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<UpdateChannelDetail>	*/	public List<UpdateChannelDetail> queryList(UpdateChannelDetailRequest request);

}

