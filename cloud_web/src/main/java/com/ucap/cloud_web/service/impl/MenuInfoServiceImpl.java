package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.MenuInfo;import com.ucap.cloud_web.service.IMenuInfoService;import com.ucap.cloud_web.dao.MenuInfoDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.MenuInfoRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>qingjy<br>* <b>日期：</b> 2017-03-28 13:59:38 <br>* <b>版权所有：<b>版权所有(C) 2017<br>*/@Servicepublic class MenuInfoServiceImpl implements IMenuInfoService {


	@Autowired	private MenuInfoDao menuInfoDao;	@Override	public int add(MenuInfo menuInfo){		return menuInfoDao.add(menuInfo);	}
	@Override	public MenuInfo get(Integer id){		return menuInfoDao.get(id);	}
	@Override	public void update(MenuInfo menuInfo){		menuInfoDao.update(menuInfo);	}
	@Override	public void delete(Integer id){		menuInfoDao.delete(id);	}
	@Override	public PageVo<MenuInfo> query(MenuInfoRequest request) {		List<MenuInfo> menuInfo = queryList(request);		PageVo<MenuInfo> pv = new PageVo<MenuInfo>();		int count = queryCount(request);		pv.setData(menuInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(MenuInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return menuInfoDao.queryCount(param);	}
	@Override	public List<MenuInfo> queryList(MenuInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<MenuInfo> list = menuInfoDao.query(param);		return list;	}
}

