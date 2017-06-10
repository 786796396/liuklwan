package com.ucap.cloud_web.service.impl;


import java.util.List;import java.util.Map;import com.ucap.cloud_web.entity.SpArticle;import com.ucap.cloud_web.service.ISpArticleService;import com.ucap.cloud_web.dao.SpArticleDao;import org.springframework.stereotype.Service;import com.publics.util.page.PageVo;import com.publics.util.utils.QueryUtils;import com.ucap.cloud_web.dto.SpArticleRequest;import org.springframework.beans.factory.annotation.Autowired;


/*** <br>* <b>作者：</b>liujc<br>* <b>日期：</b> 2016-11-22 10:33:04 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class SpArticleServiceImpl implements ISpArticleService {


	@Autowired	private SpArticleDao spArticleDao;	@Override	public int add(SpArticle spArticle){		return spArticleDao.add(spArticle);	}
	@Override	public SpArticle get(Integer id){		return spArticleDao.get(id);	}
	@Override	public void update(SpArticle spArticle){		spArticleDao.update(spArticle);	}
	@Override	public void delete(Integer id){		spArticleDao.delete(id);	}
	@Override	public PageVo<SpArticle> query(SpArticleRequest request) {		List<SpArticle> spArticle = queryList(request);		PageVo<SpArticle> pv = new PageVo<SpArticle>();		int count = queryCount(request);		pv.setData(spArticle);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(SpArticleRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return spArticleDao.queryCount(param);	}
	@Override	public List<SpArticle> queryList(SpArticleRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		List<SpArticle> list = spArticleDao.query(param);		return list;	}

	@Override
	public List<SpArticle> getSpArtList(SpArticleRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<SpArticle> list = spArticleDao.getSpArtList(param);
		return list;
	}
}

