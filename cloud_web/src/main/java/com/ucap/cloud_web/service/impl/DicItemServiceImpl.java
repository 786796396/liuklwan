package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.DicItem;import com.ucap.cloud_web.entity.SpSite;
import com.ucap.cloud_web.service.IDicItemService;import com.ucap.cloud_web.dao.DicItemDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.DicItemRequest;import com.ucap.cloud_web.dto.SpSiteRequest;

import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>linhb<br>* <b>日期：</b> 2016-09-13 20:17:57 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class DicItemServiceImpl implements IDicItemService {


	@Autowired	private DicItemDao dicItemDao;	@Override	public int add(DicItem dicItem){		return dicItemDao.add(dicItem);	}
	@Override	public DicItem get(Integer id){		return dicItemDao.get(id);	}
	@Override	public void update(DicItem dicItem){		dicItemDao.update(dicItem);	}
	@Override	public void delete(Integer id){		dicItemDao.delete(id);	}
	@Override	public PageVo<DicItem> query(DicItemRequest request) {		List<DicItem> dicItem = queryList(request);		PageVo<DicItem> pv = new PageVo<DicItem>();		int count = queryCount(request);		pv.setData(dicItem);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(DicItemRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return dicItemDao.queryCount(param);	}
	@Override	public List<DicItem> queryList(DicItemRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<DicItem> list = dicItemDao.query(param);		return list;	}

	@Override
	public List<DicItem> queryImgSize(DicItemRequest dicrequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(dicrequest);
		List<DicItem> list = dicItemDao.queryImgSize(param);
		return list;
	}
	@Override
	public List<DicItem> queryByPEnName(String pEnName){
		if(StringUtils.isEmpty(pEnName)){
			return null;
		}
		return dicItemDao.queryByPEnName(pEnName);
	}

	@Override
	public DicItem getByEnName(String enName) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(enName)){
			return null;
		}
		return dicItemDao.getByEnName(enName);
	}

	@Override
	public List<DicItem> queryByPid(int pId) {
		// TODO Auto-generated method stub
		return dicItemDao.queryByPid(pId);
	}

	@Override
	public int queryCountByEnName(String enName) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(enName)){
			return 0;
		}
		return dicItemDao.queryCountByEnName(enName);
	}}

