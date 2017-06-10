package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.CrmOrderRequest;import com.ucap.cloud_web.entity.CrmOrder;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-08 11:52:34 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/public interface ICrmOrderService {


	/**	* 添加数据	* @param crmOrder			对象			(必填)	*/	public int add(CrmOrder crmOrder);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return crmOrder	*/	public CrmOrder get(Integer id);

	/**	* 修改数据	* @param CrmOrder			对象			(必填)	*/	public void update(CrmOrder crmOrder);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<CrmOrder>	*/	public PageVo<CrmOrder> query(CrmOrderRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(CrmOrderRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<CrmOrder>	*/	public List<CrmOrder> queryList(CrmOrderRequest request);

}

