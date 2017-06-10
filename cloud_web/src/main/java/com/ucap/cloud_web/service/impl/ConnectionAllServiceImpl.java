package com.ucap.cloud_web.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.page.PageVo;
import com.publics.util.utils.QueryUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.ConnectionState;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.dao.ConnectionAllDao;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.ConnectionAllRequest;
import com.ucap.cloud_web.dto.xstream.Root;
import com.ucap.cloud_web.dto.xstream.XstreamXmlParseBeanUtil;
import com.ucap.cloud_web.entity.ChannelPoint;
import com.ucap.cloud_web.entity.ConnectionAll;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.DicConfig;
import com.ucap.cloud_web.entity.Result;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IConnectionAllService;
import com.ucap.cloud_web.service.IDicConfigService;
import com.ucap.cloud_web.util.CommonUtils;

/**
 * <br>
 * <b>作者：</b>Nora<br>
 * <b>日期：</b> 2015-10-29 11:19:33 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@Service
public class ConnectionAllServiceImpl implements IConnectionAllService {

	@Autowired
	private ConnectionAllDao connectionAllDao;
	@Autowired
	private IChannelPointService channelPointServiceImpl;
	@Autowired
	private  UrlAdapterVar urlAdapterVar;	
	@Autowired
	private IDicConfigService dicConfigServiceImpl;
	@Autowired
	private DicUtils dicUtils;
	//获取接口连通性root bean对象  
	public Root connectivityByRoot(String siteCode, String dateStr, String type) {
		Root root = new Root();
		try {
			DicConfig dicConfig=dicConfigServiceImpl.get(2);
			String code=dicConfig.getValue();//异常码404
			String co = urlAdapterVar.getConnectivityUrl() + "?sitecode=" + siteCode + "&date=" + dateStr + "&type="
					+ type + "&detail=1&code="+code;
			
			root = XstreamXmlParseBeanUtil.getRootByUrl(co);
			System.out.println("$$$$$$$$="+co);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return root;
	}
	//获取关键栏目接口连通性详情root bean对象    单个栏目的详情
	public Root channelStatisticsInfo(String encodeurl, String dateStr,String code, String queue) {
			Root root = new Root();
			try {
				String url=dicUtils.getValue("connKey_info_url");
				String co = url + "?encodeurl=" + encodeurl + "&date=" + dateStr + "&queue="
						+ queue + "&code="+code;
				root = XstreamXmlParseBeanUtil.getRootByUrl(co);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return root;
		}
	//获取接口连通性list数据
	public ArrayList<Object[]> connectivityList(String siteCode, String dateStr, String type, ArrayList<Object[]> list) {
		try {
			ChannelPointRequest res = new ChannelPointRequest();
			res.setPageSize(Integer.MAX_VALUE);
			List<ChannelPoint> channelList = new ArrayList<ChannelPoint>();
			Root root =connectivityByRoot(siteCode, dateStr, type);
			String scanTime = "";
			String stateName = "";
			String url = "";
			String encodeurl = "";
			String systemName = "";
			if(root.getResponse().equals("success")){
				List<Result> rootlist = root.getResults();
				if(rootlist.size() > 0 && rootlist != null){
					int num = 0;
					for (Result re : rootlist) {
						url = re.getUrl();
						encodeurl = re.getEncodeurl();  //加密后的url
						res.setEncodeUrl(encodeurl);
						res.setStatus(1);// 1监测中
						res.setChannelType(1);// 0 业务连通性
						channelList = channelPointServiceImpl.queryList(res);
						if(channelList.size() > 0 && channelList != null){
								ChannelPoint ca = channelList.get(0);
								systemName = ca.getChannelName();  //栏目名称
								if(re.getDetails().size() > 0 && re.getDetails() != null){
									for (int i = 0; i < re.getDetails().size(); i++) {
										num++;

										Detail de = re.getDetails().get(i);
										scanTime = de.getStime();
										stateName = ConnectionState.TIMEOUT.getName();
										
										Object[] obj = new Object[6];
										obj[0] = num;
										obj[1] = systemName;
										obj[2] = CommonUtils.setHttpUrl(url); //  判断是否有http头
										obj[3] = scanTime;
										obj[4] = stateName;
										String questionCode = de.getCode();
										if(StringUtils.isNotEmpty(questionCode)){
											obj[5] = questionCode+"　　"+QuestionType.getNameByCode(questionCode);
										}else{
											obj[5] = "";
										}
										list.add(obj);
									}
								}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void add(ConnectionAll connectionAll) {
		connectionAllDao.add(connectionAll);
	}

	@Override
	public ConnectionAll get(Integer id) {
		return connectionAllDao.get(id);
	}

	@Override
	public void update(ConnectionAll connectionAll) {
		connectionAllDao.update(connectionAll);
	}

	@Override
	public void delete(Integer id) {
		connectionAllDao.delete(id);
	}
	@Override
	public PageVo<ConnectionAll> query(ConnectionAllRequest request) {
		List<ConnectionAll> conncectAllList = queryList(request);

		PageVo<ConnectionAll> pv = new PageVo<ConnectionAll>();
		int count = queryCount(request);

		pv.setData(conncectAllList);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(ConnectionAllRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		return connectionAllDao.queryCount(param);
	}
	
	@Override
	public List<ConnectionAll> queryList(ConnectionAllRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		List<ConnectionAll> list = connectionAllDao.query(param);
		return list;
	}
	@Override
	public Object queryConnectionSum(ConnectionAllRequest request){
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		Object sum = connectionAllDao.queryConnectionSum(param);
		return sum;
	}

	@Override
	public Object queryErrorNumSum(ConnectionAllRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDataLinkList()!=null){
			param.put("dataLinkList", request.getDataLinkList());
		}
		Object sum = connectionAllDao.queryErrorNumSum(param);
		return sum;
	}
	

	@Override
	public List<ConnectionAll> queryByMap(Map<String, Object> map) {
		List<ConnectionAll> list = connectionAllDao.queryByMap(map);
		return list;
	}

	@Override
	public List<ConnectionAllRequest> getHomeBar(Map<String, Object> map) {
		return connectionAllDao.getHomeBar(map);
	}

	@Override
	public List<ConnectionAllRequest> getHomeSum(Map<String, Object> map) {
		return connectionAllDao.getHomeSum(map);
	}

	@Override
	public List<ConnectionAllRequest> getOtherSum(Map<String, Object> map) {
		return connectionAllDao.getOtherSum(map);
	}

	@Override
	public List<ConnectionAll> getConnectionAllInfo(
			ConnectionAllRequest connectionAllRequest) {
		return connectionAllDao.getConnectionAllInfo(connectionAllRequest);
	}
	
	@Override
	public ConnectionAllRequest queryBetweenDate(
			ConnectionAllRequest connectionAllRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(connectionAllRequest);
		return connectionAllDao.queryBetweenDate(param);
	}

	@Override
	public List<ConnectionAllRequest> getSumInfoGroupByName(
			ConnectionAllRequest connectionAllRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(connectionAllRequest);
		return connectionAllDao.getSumInfoGroupByName(param);
	}

	@Override
	public List<ConnectionAllRequest> queryNotConnByMap(
			Map<String, Object> paramMap) {
		return connectionAllDao.queryNotConnByMap(paramMap);
	}

	@Override
	public List<ConnectionAll> queryHomePer(Map<String, Object> paramMap) {
		return connectionAllDao.queryHomePer(paramMap);
	}

	@Override
	public List<ConnectionAll> queryPerSeven(ConnectionAllRequest conRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(conRequest);
		return connectionAllDao.queryPerSeven(param);
	}
	@Override
	public double queryConnAvgByMap(Map<String, Object> paramMap) {
		return connectionAllDao.queryConnAvgByMap(paramMap);
	}

	@Override
	public int queryConnCountByMap(Map<String, Object> paramMap) {
		return connectionAllDao.queryConnCountByMap(paramMap);
	}

	@Override
	public List<ConnectionAllRequest> getwebConnectedList(HashMap<String, Object> hashMap) {
		return connectionAllDao.getwebConnectedList(hashMap);
	}

	@Override
	public int queryConnCountByMap2(Map<String, Object> paramMap) {
		return connectionAllDao.queryConnCountByMap2(paramMap);
	}

	@Override
	public int getwebConnectedList2Count(HashMap<String, Object> paraMap) {
		return connectionAllDao.getwebConnectedList2Count(paraMap);
	}

	@Override
	public List<ConnectionAllRequest> getwebConnectedList2(
			HashMap<String, Object> hashMap) {
		return connectionAllDao.getwebConnectedList2(hashMap);
	}

	
}
