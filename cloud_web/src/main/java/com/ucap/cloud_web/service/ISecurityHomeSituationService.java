package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.SecurityHomeSituationRequest;import com.ucap.cloud_web.entity.SecurityHomeSituation;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-30 16:35:27 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ISecurityHomeSituationService {


	/**	* 添加数据	* @param securityHomeSituation			对象			(必填)	*/	public int add(SecurityHomeSituation securityHomeSituation);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return securityHomeSituation	*/	public SecurityHomeSituation get(Integer id);

	/**	* 修改数据	* @param SecurityHomeSituation			对象			(必填)	*/	public void update(SecurityHomeSituation securityHomeSituation);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<SecurityHomeSituation>	*/	public PageVo<SecurityHomeSituation> query(SecurityHomeSituationRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(SecurityHomeSituationRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<SecurityHomeSituation>	*/	public List<SecurityHomeSituation> queryList(SecurityHomeSituationRequest request);

}

