package com.ucap.cloud_web.service.impl;

import java.util.List;
import java.util.Map;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;
import com.ucap.cloud_web.dao.UpdateHomeDetailDao;
import org.springframework.stereotype.Service;
import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <br>
 * <b>作者：</b>SunJiang<br>
 * <b>日期：</b> 2015-10-30 09:51:22 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@Service
public class UpdateHomeDetailServiceImpl implements IUpdateHomeDetailService {

	@Autowired
	private UpdateHomeDetailDao updateHomeDetailDao;

	@Override
	public void add(UpdateHomeDetail updateHomeDetail) {
		updateHomeDetailDao.add(updateHomeDetail);
	}

	@Override
	public UpdateHomeDetail get(Integer id) {
		return updateHomeDetailDao.get(id);
	}

	@Override
	public void update(UpdateHomeDetail updateHomeDetail) {
		updateHomeDetailDao.update(updateHomeDetail);
	}

	@Override
	public void delete(Integer id) {
		updateHomeDetailDao.delete(id);
	}

	@Override
	public PageVo<UpdateHomeDetail> query(UpdateHomeDetailRequest request) {
		List<UpdateHomeDetail> updateHomeDetail = queryList(request);

		PageVo<UpdateHomeDetail> pv = new PageVo<UpdateHomeDetail>();
		int count = queryCount(request);

		pv.setData(updateHomeDetail);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(UpdateHomeDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		return updateHomeDetailDao.queryCount(param);
	}

	@Override
	public List<UpdateHomeDetail> queryList(UpdateHomeDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		List<UpdateHomeDetail> list = updateHomeDetailDao.query(param);
		return list;
	}

	@Override
	public int queryBetweenDateCount(UpdateHomeDetailRequest request) {

		try {
			Map<String, Object> param = QueryUtils.getQueryMap(request);
			Integer number = updateHomeDetailDao.queryBetweenDateCount(param);
			if (null == number) {
				return 0;
			}

			return number;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	@Override
	public UpdateHomeDetail getNearest(UpdateHomeDetailRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		UpdateHomeDetail uhd = updateHomeDetailDao.getNearest(param);
		return uhd;
	}


}
