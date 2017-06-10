package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.YunOpenDetailRequest;import com.ucap.cloud_web.entity.YunOpenDetail;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-10 19:03:10 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IYunOpenDetailService {


	/**	* 添加数据	* @param yunOpenDetail			对象			(必填)	*/	public int add(YunOpenDetail yunOpenDetail);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return yunOpenDetail	*/	public YunOpenDetail get(Integer id);

	/**	* 修改数据	* @param YunOpenDetail			对象			(必填)	*/	public void update(YunOpenDetail yunOpenDetail);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<YunOpenDetail>	*/	public PageVo<YunOpenDetail> query(YunOpenDetailRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(YunOpenDetailRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<YunOpenDetail>	*/	public List<YunOpenDetail> queryList(YunOpenDetailRequest request);

}

