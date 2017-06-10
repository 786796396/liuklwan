package com.ucap.cloud_web.dao;


import java.util.List;
import java.util.Map;

import com.ucap.cloud_web.entity.DicItem;

	List<DicItem> queryImgSize(Map<String, Object> param);
	/**
	 * @描述:根据英文名称查询
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:20:15 
	 * @param enName
	 * @return
	 */
	public DicItem getByEnName(String enName);
	/**
	 * @描述:根据 pid 查询
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:20:22 
	 * @param pId
	 * @return
	 */
	public List<DicItem> queryByPid(int pId);
	/**
	 * @描述:根据英文名称查询数量
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:21:45 
	 * @param enName
	 * @return
	 */
	public int  queryCountByEnName(String enName); 
	
	/**
	 * @描述:根据 父节点  enName 查询
	 * @作者:liujc@ucap.com.cn
	 * @时间:2016-12-7下午12:22:00 
	 * @param pEnName
	 * @return
	 */
	public List<DicItem> queryByPEnName(String pEnName); 






