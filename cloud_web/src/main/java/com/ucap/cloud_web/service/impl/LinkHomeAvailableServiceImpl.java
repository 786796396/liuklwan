package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.LinkHomeAvailable;import com.ucap.cloud_web.service.ILinkHomeAvailableService;import com.ucap.cloud_web.dao.LinkHomeAvailableDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.LinkHomeAvailableRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:06:19 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class LinkHomeAvailableServiceImpl implements ILinkHomeAvailableService {


	@Autowired	private LinkHomeAvailableDao linkHomeAvailableDao;	@Override	public void add(LinkHomeAvailable linkHomeAvailable){		linkHomeAvailableDao.add(linkHomeAvailable);	}
	@Override	public LinkHomeAvailable get(Integer id){		return linkHomeAvailableDao.get(id);	}
	@Override	public void update(LinkHomeAvailable linkHomeAvailable){		linkHomeAvailableDao.update(linkHomeAvailable);	}
	@Override	public void delete(Integer id){		linkHomeAvailableDao.delete(id);	}
	@Override	public PageVo<LinkHomeAvailable> query(LinkHomeAvailableRequest request) {		List<LinkHomeAvailable> linkHomeAvailable = queryList(request);		PageVo<LinkHomeAvailable> pv = new PageVo<LinkHomeAvailable>();		int count = queryCount(request);		pv.setData(linkHomeAvailable);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(LinkHomeAvailableRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getQuestionCodeArr()!=null && request.getQuestionCodeArr().length>0){
			param.put("questionCodeArr", request.getQuestionCodeArr());
		}
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}		return linkHomeAvailableDao.queryCount(param);	}
	@Override	public List<LinkHomeAvailable> queryList(LinkHomeAvailableRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getQuestionCodeArr()!=null && request.getQuestionCodeArr().length>0){
			param.put("questionCodeArr", request.getQuestionCodeArr());
		}		List<LinkHomeAvailable> list = linkHomeAvailableDao.query(param);		return list;	}

	@Override
	public List<LinkHomeAvailableRequest> queryGroupBy(
			Map<String, Object> paramMap) {
		return linkHomeAvailableDao.queryGroupBy(paramMap);
	}
}

