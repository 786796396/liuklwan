package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.dto.MTaskdetailRequest;
import com.ucap.cloud_web.entity.MTaskdetail;
import com.ucap.cloud_web.entity.Result;
import com.publics.util.dao.GenericDao;
/**
* <br>
* <b>作者：</b>liujc<br>
* <b>日期：</b> 2016-10-17 15:03:01 <br>
* <b>版权所有：<b>版权所有(C) 2016<br>
*/

public interface MTaskdetailDao extends GenericDao<MTaskdetail>{
	 public List<Result> queryResultList(Map arg0);

	 /**
	  * @Description:  查询 昨天、全面监测中 、前7天首页不连通占比超过3%
	  * @author cuichx --- 2016-12-20下午1:25:04     
	  * @param paramMap
	  * @return
	  */
	public List<MTaskdetailRequest> queryByMap(Map<String, Object> paramMap);

	/**
	 * @Description: 前7天首页不连通占比超过3%
	 * @author cuichx --- 2017-3-13下午3:43:09     
	 * @param paramMap
	 * @return
	 */
	public int queryConnPer(Map<String, Object> paramMap);
	/**
	 * @Description: 查询某个组织单位下   前7天首页不连通率超过3% 
	 * @author cuichx --- 2017-3-29上午10:46:36     
	 * @param paraMap
	 * @return
	 */
	public List<MTaskdetailRequest> queryPerLin7ByMap(
			Map<String, Object> paraMap);
	/**
	 * @Description: 查询某个组织单位下   前7天首页不连通率超过3% ---记录数
	 * @author cuichx --- 2017-3-29上午10:46:36     
	 * @param paraMap
	 * @return
	 */
	public int queryPerLin7ByMapCount(Map<String, Object> paraMap);

}

