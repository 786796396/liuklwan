package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.LinkAllDetail;import com.ucap.cloud_web.service.ILinkAllDetailService;import com.ucap.cloud_web.dao.LinkAllDetailDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.LinkAllDetailRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>cuichx<br>* <b>日期：</b> 2015-10-29 11:05:51 <br>* <b>版权所有：<b>版权所有(C) 2015<br>*/@Servicepublic class LinkAllDetailServiceImpl implements ILinkAllDetailService {


	@Autowired	private LinkAllDetailDao linkAllDetailDao;	@Override	public void add(LinkAllDetail linkAllDetail){		linkAllDetailDao.add(linkAllDetail);	}
	@Override	public LinkAllDetail get(Integer id){		return linkAllDetailDao.get(id);	}
	@Override	public void update(LinkAllDetail linkAllDetail){		linkAllDetailDao.update(linkAllDetail);	}
	@Override	public void delete(Integer id){		linkAllDetailDao.delete(id);	}
	@Override	public PageVo<LinkAllDetail> query(LinkAllDetailRequest request) {		List<LinkAllDetail> linkAllDetail = queryList(request);		PageVo<LinkAllDetail> pv = new PageVo<LinkAllDetail>();		int count = queryCount(request);		pv.setData(linkAllDetail);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(LinkAllDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getErrorCodeArr()!=null && request.getErrorCodeArr().length>0){
			param.put("errorCodeArr", request.getErrorCodeArr());
		}		return linkAllDetailDao.queryCount(param);	}
	@Override	public List<LinkAllDetail> queryList(LinkAllDetailRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getErrorCodeArr()!=null && request.getErrorCodeArr().length>0){
			param.put("errorCodeArr", request.getErrorCodeArr());
		}		List<LinkAllDetail> list = linkAllDetailDao.query(param);
		return list;	}
	public int queryNoExceptCount(LinkAllDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getErrorCodeArr()!=null && request.getErrorCodeArr().length>0){
			param.put("errorCodeArr", request.getErrorCodeArr());
		}
		return linkAllDetailDao.queryNoExceptCount(param);
	}
	@Override
	public List<LinkAllDetail> queryNoExceptList(LinkAllDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<LinkAllDetail> list = linkAllDetailDao.queryNoExceptList(param);
		return list;
	}

	@Override
	public PageVo<LinkAllDetail> queryNoExcept(LinkAllDetailRequest request) {
		List<LinkAllDetail> linkAllDetail = queryNoExceptList(request);

		PageVo<LinkAllDetail> pv = new PageVo<LinkAllDetail>();
		int count = queryNoExceptCount(request);

		pv.setData(linkAllDetail);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public List<LinkAllDetailRequest> querySumGroupBy(
			Map<String, Object> paramMap) {
		return linkAllDetailDao.querySumGroupBy(paramMap);
	}

	@Override
	public List<LinkAllDetailRequest> queryByCode(Map<Object, Object> paramMap) {
		return linkAllDetailDao.queryByCode(paramMap);
	}}

