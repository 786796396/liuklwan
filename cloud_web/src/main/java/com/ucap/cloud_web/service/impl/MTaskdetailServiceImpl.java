package com.ucap.cloud_web.service.impl;


import java.util.List;
import java.util.Map;
import com.ucap.cloud_web.entity.MTaskdetail;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IMTaskdetailService;
import com.ucap.cloud_web.dao.MTaskdetailDao;
import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dto.MTaskdetailRequest;
import org.springframework.beans.factory.annotation.Autowired;


/**
* <br>
* <b>作者：</b>liujc<br>
* <b>日期：</b> 2016-10-17 15:03:01 <br>
* <b>版权所有：<b>版权所有(C) 2016<br>
*/

@Service
public class MTaskdetailServiceImpl implements IMTaskdetailService {


	@Autowired
	private MTaskdetailDao mTaskdetailDao;


	@Override
	public int add(MTaskdetail mTaskdetail){
		return mTaskdetailDao.add(mTaskdetail);
	}

	@Override
	public MTaskdetail get(Integer id){
		return mTaskdetailDao.get(id);
	}

	@Override
	public void update(MTaskdetail mTaskdetail){
		mTaskdetailDao.update(mTaskdetail);
	}

	@Override
	public void delete(Integer id){
		mTaskdetailDao.delete(id);
	}

	@Override
	public PageVo<MTaskdetail> query(MTaskdetailRequest request) {
		List<MTaskdetail> mTaskdetail = queryList(request);

		PageVo<MTaskdetail> pv = new PageVo<MTaskdetail>();
		int count = queryCount(request);

		pv.setData(mTaskdetail);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(MTaskdetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return mTaskdetailDao.queryCount(param);
	}

	@Override
	public List<MTaskdetail> queryList(MTaskdetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<MTaskdetail> list = mTaskdetailDao.query(param);
		return list;
	}
	@Override
	public List<Result> queryResultList(MTaskdetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<Result> list = mTaskdetailDao.queryResultList(param);
		return list;
	}

	@Override
	public List<MTaskdetailRequest> queryByMap(Map<String, Object> paramMap) {
		return mTaskdetailDao.queryByMap(paramMap);
	}

	@Override
	public int queryConnPer(Map<String, Object> paramMap) {
		return mTaskdetailDao.queryConnPer(paramMap);
	}

	@Override
	public List<MTaskdetailRequest> queryPerLin7ByMap(
			Map<String, Object> paraMap) {
		return mTaskdetailDao.queryPerLin7ByMap(paraMap);
	}

	@Override
	public int queryPerLin7ByMapCount(Map<String, Object> paraMap) {
		return mTaskdetailDao.queryPerLin7ByMapCount(paraMap);
	}
	
}

