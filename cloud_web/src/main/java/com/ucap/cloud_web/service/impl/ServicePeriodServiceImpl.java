package com.ucap.cloud_web.service.impl;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.dao.ServicePeriodDao;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.IServicePeriodService;


/*** <br>* <b>作者：</b>Sunjiang<br>* <b>日期：</b> 2016-03-05 16:17:04 <br>* <b>版权所有：<b>版权所有(C) 2016<br>*/@Servicepublic class ServicePeriodServiceImpl implements IServicePeriodService {


	@Autowired	private ServicePeriodDao servicePeriodDao;	@Override	public void add(ServicePeriod servicePeriod){		servicePeriodDao.add(servicePeriod);	}
	@Override	public ServicePeriod get(Integer id){		return servicePeriodDao.get(id);	}
	@Override	public void update(ServicePeriod servicePeriod){		servicePeriodDao.update(servicePeriod);	}
	@Override	public void delete(Integer id){		servicePeriodDao.delete(id);	}
	@Override	public PageVo<ServicePeriod> query(ServicePeriodRequest request) {		List<ServicePeriod> servicePeriod = queryList(request);		PageVo<ServicePeriod> pv = new PageVo<ServicePeriod>();		int count = queryCount(request);		pv.setData(servicePeriod);		pv.setPageNo(request.getPageNo());		pv.setPageSize(request.getPageSize());		pv.setRecordSize(count);		return pv;	}
	@Override	public int queryCount(ServicePeriodRequest request) {		Map<String, Object> param = QueryUtils.getQueryMap(request);		return servicePeriodDao.queryCount(param);	}
	@Override	public List<ServicePeriod> queryList(ServicePeriodRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if( request.getComboIdArr()!=null){
			param.put("comboIdArr", request.getComboIdArr());
		}
		if(request.getStatasArray()!=null){
			param.put("statasArray", request.getStatasArray());
		}
		if(request.getList()!=null){
			param.put("list", request.getList());
		}
		if(request.getContractList()!=null){
			param.put("contractList", request.getContractList());
		}
/*		if (request.getCrmProductsList() != null) {
			param.put("crmProductsList", request.getCrmProductsList());
		}*/
		List<ServicePeriod> list = servicePeriodDao.query(param);		return list;	}
	
	/**
	 * 根据siteCode查询服务周期
	 */
	@Override
	public List<ServicePeriodRequest> queryByRelationPeriod(ServicePeriodRequest request) {
			request.setSpotCheckScheduleId(0); //抽查进度表Id
		    Calendar cal = Calendar.getInstance();
			cal.add(1, -1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String startDateTime = sdf.format(cal.getTime()); 
		    request.setStartDateTime(startDateTime); //查询默认一年的周期
		    request.setPageSize(Integer.MAX_VALUE); 
		    request.setComboI(4);
		    
		    List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder2=new QueryOrder("s.start_date",QueryOrderType.DESC); //根据周期的开始时间进行倒序排序
			queryOrderList.add(siteQueryOrder2);
			request.setQueryOrderList(queryOrderList);
			
			Map<String, Object> param = QueryUtils.getQueryMap(request);
			param.put("statasArray", new int[]{1,2}); //查询服务状态 在服务中已经完成服务的
			List<ServicePeriodRequest> list = servicePeriodDao.queryByRelationPeriod(param);
			return list;
	}
	
	@Override
	public List<ServicePeriodRequest> queryByGroup(
			Map<String, Object> paramMap) {
		List<ServicePeriodRequest> list=servicePeriodDao.queryByGroup(paramMap);
		return list;
	}

	@Override
	public List<ServicePeriodRequest> queryByRelationPeriodBasic(
			Map<String, Object> param) {
		List<ServicePeriodRequest> list = servicePeriodDao.queryByRelationPeriod(param);
		return list;
	}

	@Override
	public List<ServicePeriod> queryAdvanceService(Map<String, Object> map) {
		return servicePeriodDao.queryAdvanceService(map);
	}
}

