package com.ucap.cloud_web.service;


import java.util.List;import com.publics.util.page.PageVo;import com.ucap.cloud_web.dto.YunSoInfoRequest;
import com.ucap.cloud_web.entity.YunSoInfo;



/*** <br>* <b>作者：</b>wl@ucap.com.cn<br>* <b>日期：</b> 2016-12-05 16:39:56 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/public interface IYunSoInfoService {


	/**	* 添加数据	* @param yunSoInfo			对象			(必填)	*/	public int add(YunSoInfo yunSoInfo);

	/**	* 通过主键获取对象数据	* @param id						主键			(必填)	* @return yunSoInfo	*/	public YunSoInfo get(Integer id);

	/**	* 修改数据	* @param YunSoInfo			对象			(必填)	*/	public void update(YunSoInfo yunSoInfo);

	/**	* 通过id删除数据	* @param id						对象			(必填)	*/	public void delete(Integer id);

	/**	* 通过对象获取分页数据	* @param request				dto对象			(必填)	* @return	PageVo<YunSoInfo>	*/	public PageVo<YunSoInfo> query(YunSoInfoRequest request);

	/**	* 查询总条数	* @param request				前台参数			(必填)	* @return	int	*/	public int queryCount(YunSoInfoRequest request);

	/**	* 查询对象集合	* @param request				前台参数			(必填)	* @return	List<YunSoInfo>	*/	public List<YunSoInfo> queryList(YunSoInfoRequest request);

}

