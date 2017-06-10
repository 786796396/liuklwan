package com.ucap.cloud_web.service;


import java.util.List;import com.ucap.cloud_web.dto.DicIndicatorRequest;import com.ucap.cloud_web.entity.DicIndicator;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:17:59 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IDicIndicatorService {


	/**	* 添加数据	* @param dicIndicator			对象			(必填)	*/	public void add(DicIndicator dicIndicator);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return dicIndicator	*/	public DicIndicator get(Integer id);

	/**	* 修改数据	* @param DicIndicator			对象			(必填)	*/	public void update(DicIndicator dicIndicator);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<DicIndicator>	*/	public List<DicIndicator> queryList(DicIndicatorRequest request);

}

