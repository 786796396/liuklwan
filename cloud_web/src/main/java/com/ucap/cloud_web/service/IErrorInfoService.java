package com.ucap.cloud_web.service;


import java.util.List;

import com.publics.util.page.PageVo;
import com.ucap.cloud_web.dto.ErrorInfoRequest;
import com.ucap.cloud_web.entity.ErrorInfo;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-04-28 16:08:42 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface IErrorInfoService {


	/**	* 添加数据	* @param errorInfo			对象			(必填)	*/	public int add(ErrorInfo errorInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return errorInfo	*/	public ErrorInfo get(Integer id);

	/**	* 修改数据	* @param ErrorInfo			对象			(必填)	*/	public void update(ErrorInfo errorInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<ErrorInfo>	*/	public PageVo<ErrorInfo> query(ErrorInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(ErrorInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<ErrorInfo>	*/	public List<ErrorInfo> queryList(ErrorInfoRequest request);

	/**
	 * @描述: 分页（关联tree表）
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月5日下午5:32:45
	 * @param req
	 * @return
	 */

	public PageVo<ErrorInfo> getErrorInfoList(ErrorInfoRequest req);

	/**
	 * @描述:分页（关联tree表）条数
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年5月5日下午5:35:13
	 * @param request
	 * @return
	 */

	int getErrorInfoCount(ErrorInfoRequest request);

}

