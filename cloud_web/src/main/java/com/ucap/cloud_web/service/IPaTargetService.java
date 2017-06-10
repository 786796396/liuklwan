package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaTargetRequest;import com.ucap.cloud_web.entity.PaTarget;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-24 13:38:42 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaTargetService {


	/**	* 添加数据	* @param paTarget			对象			(必填)	*/	public void add(PaTarget paTarget);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paTarget	*/	public PaTarget get(Integer id);

	/**	* 修改数据	* @param PaTarget			对象			(必填)	*/	public void update(PaTarget paTarget);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaTarget>	*/	public PageVo<PaTarget> query(PaTargetRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaTargetRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaTarget>	*/	public List<PaTarget> queryList(PaTargetRequest request);
	/**
	 * 通过项目id 任务 id 获取 对象
	 * linhb 2016-8-24
	 * @param paTargetRequest
	 * @return
	 */
	public List<PaTarget> queryByIds(PaTargetRequest paTargetRequest);

}

