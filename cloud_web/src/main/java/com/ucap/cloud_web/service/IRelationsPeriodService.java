package com.ucap.cloud_web.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dtoResponse.SecurityGuaranteeResponse;
import com.ucap.cloud_web.entity.RelationsPeriod;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2016-03-09 10:08:29 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IRelationsPeriodService {


	/**	* 添加数据	* @param relationsPeriod			对象			(必填)	*/	public void add(RelationsPeriod relationsPeriod);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return relationsPeriod	*/	public RelationsPeriod get(Integer id);

	/**	* 修改数据	* @param RelationsPeriod			对象			(必填)	*/	public void update(RelationsPeriod relationsPeriod);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<RelationsPeriod>	*/	public PageVo<RelationsPeriod> query(RelationsPeriodRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(RelationsPeriodRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<RelationsPeriod>	*/	public List<RelationsPeriod> queryList(RelationsPeriodRequest request);

	/**
	 * @Description: 通过网站标识码获取客户编号
	 * @author cuichx --- 2016-3-9上午10:11:19     
	 * @param pMap
	 * @return
	 */
	public List<RelationsPeriodRequest> queryByMap(Map<String, Object> pMap);

	/**
	 * 
	 * @描述:获取内容保障周期下sitecode
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年3月7日下午2:45:03
	 * @param map
	 * @return
	 */
	public List<SecurityGuaranteeResponse> contentSecurityTree(HashMap<String, Object> map);

}

