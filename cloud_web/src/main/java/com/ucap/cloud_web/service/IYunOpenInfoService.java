package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.YunOpenInfoRequest;import com.ucap.cloud_web.entity.YunOpenInfo;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2017-05-10 19:06:11 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IYunOpenInfoService {


	/**	* 添加数据	* @param yunOpenInfo			对象			(必填)	*/	public int add(YunOpenInfo yunOpenInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return yunOpenInfo	*/	public YunOpenInfo get(Integer id);

	/**	* 修改数据	* @param YunOpenInfo			对象			(必填)	*/	public void update(YunOpenInfo yunOpenInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<YunOpenInfo>	*/	public PageVo<YunOpenInfo> query(YunOpenInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(YunOpenInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<YunOpenInfo>	*/	public List<YunOpenInfo> queryList(YunOpenInfoRequest request);

}

