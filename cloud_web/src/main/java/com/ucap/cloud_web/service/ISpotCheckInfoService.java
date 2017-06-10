package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SpotCheckInfoRequest;import com.ucap.cloud_web.entity.SpotCheckInfo;


/*** <br>* <b>作者：</b>yuangw@ucap.com.cn<br>* <b>日期：</b> 2016-04-19 11:16:09 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISpotCheckInfoService {


	/**	* 添加数据	* @param spotCheckInfo			对象			(必填)	*/	public void add(SpotCheckInfo spotCheckInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return spotCheckInfo	*/	public SpotCheckInfo get(Integer id);

	/**	* 修改数据	* @param SpotCheckInfo			对象			(必填)	*/	public void update(SpotCheckInfo spotCheckInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SpotCheckInfo>	*/	public PageVo<SpotCheckInfo> query(SpotCheckInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SpotCheckInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SpotCheckInfo>	*/	public List<SpotCheckInfo> queryList(SpotCheckInfoRequest request);

	public List<SpotCheckInfo> findBySpotCheckInfoCode(SpotCheckInfoRequest request);

}

