package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.JcVisitSitecodeRequest;
import com.ucap.cloud_web.dtoResponse.JcVisitSitecodeResponse;
import com.ucap.cloud_web.entity.JcVisitSitecode;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-12-13 20:29:02 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IJcVisitSitecodeService {


	/**	* 添加数据	* @param jcVisitSitecode			对象			(必填)	*/	public int add(JcVisitSitecode jcVisitSitecode);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return jcVisitSitecode	*/	public JcVisitSitecode get(Integer id);

	/**	* 修改数据	* @param JcVisitSitecode			对象			(必填)	*/	public void update(JcVisitSitecode jcVisitSitecode);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<JcVisitSitecode>	*/	public PageVo<JcVisitSitecode> query(JcVisitSitecodeRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(JcVisitSitecodeRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<JcVisitSitecode>	*/	public List<JcVisitSitecode> queryList(JcVisitSitecodeRequest request);
	/**
	 * 
	 * @描述:关联 tree表  获取 站点数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月14日上午9:38:08 
	 * @param sRequest
	 * @return
	 */
	public List<JcVisitSitecode> querySiteCodes(JcVisitSitecodeRequest sRequest);
	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public List<JcVisitSitecode> queryOrganizations(JcVisitSitecodeRequest sRequest);
	/**
	 * 
	 * @描述:获取 本级站点 
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public List<JcVisitSitecode> queryNatives(JcVisitSitecodeRequest sRequest);

	/**
	 * @描述:获取日常监测-网站访问量数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月22日下午5:48:34
	 * @param hashMap
	 * @return
	 */

	public List<JcVisitSitecodeResponse> getSiteVisitsList(HashMap<String, Object> hashMap);

}

