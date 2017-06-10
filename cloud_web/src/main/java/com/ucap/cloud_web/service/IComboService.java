package com.ucap.cloud_web.service;


import java.util.List;

import com.ucap.cloud_web.dto.ComboRequest;
import com.ucap.cloud_web.entity.Combo;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2015-10-29 11:19:33 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IComboService {


	/**	* 添加数据	* @param combo			对象			(必填)	*/	public void add(Combo combo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return combo	*/	public Combo get(Integer id);

	/**	* 修改数据	* @param Combo			对象			(必填)	*/	public void update(Combo combo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);
	/**
	* 查询总条数
	* @param request				前台参数			(必填)
	* @return	int
	*/
	public int queryCount(ComboRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<Combo>	*/	public List<Combo> queryList(ComboRequest request);

}

