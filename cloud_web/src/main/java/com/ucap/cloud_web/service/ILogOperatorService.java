package com.ucap.cloud_web.service;

import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.LogOperatorRequest;
import com.ucap.cloud_web.entity.LogOperator;


/*** <br>* <b>作者：</b>Nora<br>* <b>日期：</b> 2016-09-29 15:30:25 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface ILogOperatorService {


	/**	* 添加数据	* @param logOperator			对象			(必填)	*/	public int add(LogOperator logOperator);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return logOperator	*/	public LogOperator get(Integer id);

	/**	* 修改数据	* @param LogOperator			对象			(必填)	*/	public void update(LogOperator logOperator);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<LogOperator>	*/	public PageVo<LogOperator> query(LogOperatorRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(LogOperatorRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<LogOperator>	*/	public List<LogOperator> queryList(LogOperatorRequest request);

}

