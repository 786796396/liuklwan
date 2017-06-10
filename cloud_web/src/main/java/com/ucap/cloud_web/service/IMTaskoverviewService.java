package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.dto.MTaskoverviewRequest;import com.ucap.cloud_web.entity.MTaskoverview;
import com.ucap.cloud_web.entity.Result;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-10-17 15:16:51 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IMTaskoverviewService {


	/**	* 添加数据	* @param mTaskoverview			对象			(必填)	*/	public int add(MTaskoverview mTaskoverview);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return mTaskoverview	*/	public MTaskoverview get(Integer id);

	/**	* 修改数据	* @param MTaskoverview			对象			(必填)	*/	public void update(MTaskoverview mTaskoverview);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<MTaskoverview>	*/	public PageVo<MTaskoverview> query(MTaskoverviewRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(MTaskoverviewRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<MTaskoverview>	*/	public List<MTaskoverview> queryList(MTaskoverviewRequest request);
	
	/**
	* 查询对象集合
	* @param request				前台参数			(必填)
	* @return	List<MTaskdetail>
	*/
	public List<Result> queryResultList(MTaskoverviewRequest request);

	/**
	 * @描述:
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年11月22日下午7:24:43 
	 * @param hashMap 
	 */
	
	public List<MTaskoverviewRequest> getMTaskoverMap(HashMap<String, Object> hashMap);

}

