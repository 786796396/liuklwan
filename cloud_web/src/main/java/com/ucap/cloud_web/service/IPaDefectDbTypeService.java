package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.PaDefectDbTypeRequest;import com.ucap.cloud_web.entity.PaDefectDbType;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-08-23 10:59:23 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IPaDefectDbTypeService {


	/**	* 添加数据	* @param paDefectDbType			对象			(必填)	*/	public void add(PaDefectDbType paDefectDbType);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return paDefectDbType	*/	public PaDefectDbType get(Integer id);

	/**	* 修改数据	* @param PaDefectDbType			对象			(必填)	*/	public void update(PaDefectDbType paDefectDbType);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<PaDefectDbType>	*/	public PageVo<PaDefectDbType> query(PaDefectDbTypeRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(PaDefectDbTypeRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<PaDefectDbType>	*/	public List<PaDefectDbType> queryList(PaDefectDbTypeRequest request);

}

