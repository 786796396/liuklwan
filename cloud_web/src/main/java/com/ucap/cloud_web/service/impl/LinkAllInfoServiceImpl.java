package com.ucap.cloud_web.service.impl;


import java.util.HashMap;
import java.util.List;import java.util.Map;
import com.ucap.cloud_web.entity.LinkAllInfo;import com.ucap.cloud_web.service.ILinkAllInfoService;import com.ucap.cloud_web.dao.LinkAllInfoDao;
import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.LinkAllInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:08 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class LinkAllInfoServiceImpl implements ILinkAllInfoService {


	@Autowired	private LinkAllInfoDao linkAllInfoDao;	@Override	public void add(LinkAllInfo linkAllInfo){		linkAllInfoDao.add(linkAllInfo);	}
	@Override	public LinkAllInfo get(Integer id){		return linkAllInfoDao.get(id);	}
	@Override	public void update(LinkAllInfo linkAllInfo){		linkAllInfoDao.update(linkAllInfo);	}
	@Override	public void delete(Integer id){		linkAllInfoDao.delete(id);	}
	@Override	public PageVo<LinkAllInfo> query(LinkAllInfoRequest request) {		List<LinkAllInfo> linkAllInfo = queryList(request);		PageVo<LinkAllInfo> pv = new PageVo<LinkAllInfo>();		int count = queryCount(request);		pv.setData(linkAllInfo);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(LinkAllInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return linkAllInfoDao.queryCount(param);	}
	@Override	public List<LinkAllInfo> queryList(LinkAllInfoRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getServiceList() !=null){
			param.put("serviceList", request.getServiceList());
		}		List<LinkAllInfo> list = linkAllInfoDao.query(param);		return list;	}

	@Override
	public List<LinkAllInfoRequest> queryByMap(Map<String, Object> map) {
		List<LinkAllInfoRequest> list = linkAllInfoDao.queryByMap(map);
		return list;
	}

	@Override
	public List<LinkAllInfo> getAllline(Map<String, Object> map) {
		return linkAllInfoDao.getAllline(map);
	}

	@Override
	public List<LinkAllInfo> getAlllineOrg(Map<String, Object> map) {
		return linkAllInfoDao.getAlllineOrg(map);
	}

	@Override
	public List<LinkAllInfoRequest> getAllLinkSum(Map<String, Object> map) {
		return linkAllInfoDao.getAllLinkSum(map);
	}

	@Override
	public List<LinkAllInfoRequest> queryLinkAllInfoByTree(
			HashMap<String, Object> paraMap) {
		return linkAllInfoDao.queryLinkAllInfoByTree(paraMap);
	}

	@Override
	public int queryLinkAllInfoByTreeCount(HashMap<String, Object> paraMap) {
		return linkAllInfoDao.queryLinkAllInfoByTreeCount(paraMap);
	}
}

