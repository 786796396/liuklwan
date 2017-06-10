package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.publics.util.dao.GenericDao;
import com.ucap.cloud_web.dto.SecurityBlankInfoRequest;
import com.ucap.cloud_web.dto.UpdateChannelInfoRequest;
import com.ucap.cloud_web.entity.UpdateChannelInfo;

/*** <br>* <b>作者：</b>SunJiang<br>* <b>日期：</b> 2015-10-30 09:51:22 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/public interface UpdateChannelInfoDao extends GenericDao<UpdateChannelInfo>{

	/**
	 * @Description: 重点监测栏目的总数
	 * @author sunjiang --- 2015年11月19日下午5:38:38     
	 * @return
	 */
	public List<SecurityBlankInfoRequest> queryPonitNum(Map<String,Object> map);
	/**
	 * @Description: 栏目更新的数量
	 * @author sunjiang --- 2015年11月19日下午5:38:43     
	 * @return
	 */	public List<SecurityBlankInfoRequest> queryChannelNum(Map<String,Object> map);	/**
	* 查询对象集合
	* @param paramMap				前台参数			(必填)
	* @return	List<UpdateChannelInfoRequest>
	*/
	List<UpdateChannelInfoRequest> queryByGroup(Map<String, Object> paramMap);
	/** @Description: 组织单位--栏目更新监测结果
	 * @author zhurk --- 2015-12-1下午7:59:35     
	 * @param map
	 * @return           
	*/
	List<UpdateChannelInfoRequest> getChannelNum(Map<String, Object> map);
	/**
	 * @Description:组织单位 获取栏目总数 
	 * @author sunjiang --- 2015-12-4下午1:57:41     
	 * @param paramMap
	 * @return
	 */
	public List<UpdateChannelInfo> querySumUpdateNum(Map<String, Object> paramMap);
	/**
	 * @Description: 统计一段时间内所有的栏目更新数
	 * @author cuichx --- 2016-3-17上午1:44:38     
	 * @param infoRequest
	 * @return
	 */
	public List<UpdateChannelInfoRequest> queryListSum(
			Map<String, Object> paramMap);
}

