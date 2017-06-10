package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.JcVisitOrgRequest;import com.ucap.cloud_web.entity.JcVisitOrg;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 08:58:19 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IJcVisitOrgService {


	/**	* 添加数据	* @param jcVisitOrg			对象			(必填)	*/	public int add(JcVisitOrg jcVisitOrg);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return jcVisitOrg	*/	public JcVisitOrg get(Integer id);

	/**	* 修改数据	* @param JcVisitOrg			对象			(必填)	*/	public void update(JcVisitOrg jcVisitOrg);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<JcVisitOrg>	*/	public PageVo<JcVisitOrg> query(JcVisitOrgRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(JcVisitOrgRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<JcVisitOrg>	*/	public List<JcVisitOrg> queryList(JcVisitOrgRequest request);
	/**
	 * 
	 * @param rRequest 
	 * @描述:获取下级战群的 访问量数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public List<JcVisitOrg> getOrgData(JcVisitOrgRequest rRequest);

}

