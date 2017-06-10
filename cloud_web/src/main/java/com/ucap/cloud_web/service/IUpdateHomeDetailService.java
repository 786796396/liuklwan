package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;import com.ucap.cloud_web.entity.UpdateHomeDetail;


/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface IUpdateHomeDetailService {


	/**	* 添加数据	* @param updateHomeDetail			对象			(必填)	*/	public void add(UpdateHomeDetail updateHomeDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return updateHomeDetail	*/	public UpdateHomeDetail get(Integer id);

	/**	* 修改数据	* @param UpdateHomeDetail			对象			(必填)	*/	public void update(UpdateHomeDetail updateHomeDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<UpdateHomeDetail>	*/	public PageVo<UpdateHomeDetail> query(UpdateHomeDetailRequest request);
	
	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(UpdateHomeDetailRequest request);
	/** @Description: 计算两个时间内的更新数量
	 * @author sunjiaqi --- 2015-11-19下午03:02:34     
	 * @param beginDate 开始时间
	 * @param endDate  结束时间
	 * @return           
	*/
	public int queryBetweenDateCount(UpdateHomeDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<UpdateHomeDetail>	*/	public List<UpdateHomeDetail> queryList(UpdateHomeDetailRequest request);
	/** @Description:  获取指定日期内
	 * @author sunjiaqi --- 2015-11-20下午05:48:07     
	 * @param request
	 * @return           
	*/
	public UpdateHomeDetail getNearest (UpdateHomeDetailRequest request);

}

