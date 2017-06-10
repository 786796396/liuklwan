package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.BigOrgDailyRequest;
import com.ucap.cloud_web.dtoResponse.BigOrgDailyResponse;
import com.ucap.cloud_web.entity.BigOrgDaily;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2017-02-28 18:03:30 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IBigOrgDailyService {


	/**	* 添加数据	* @param bigOrgDaily			对象			(必填)	*/	public int add(BigOrgDaily bigOrgDaily);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return bigOrgDaily	*/	public BigOrgDaily get(Integer id);

	/**	* 修改数据	* @param BigOrgDaily			对象			(必填)	*/	public void update(BigOrgDaily bigOrgDaily);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<BigOrgDaily>	*/	public PageVo<BigOrgDaily> query(BigOrgDailyRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(BigOrgDailyRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<BigOrgDaily>	*/	public List<BigOrgDaily> queryList(BigOrgDailyRequest request);
	/**
	 * 
	 * @描述:获取下级站群数据
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-2-28下午6:27:53 
	 * @param request
	 * @return
	 */
	public List<BigOrgDailyResponse> getOrgData(BigOrgDailyRequest request);
}

