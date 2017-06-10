package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.JcVisitOrgRequest;
import com.ucap.cloud_web.dto.JcVisitSitecodeRequest;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.JcVisitOrg;
import com.ucap.cloud_web.entity.JcVisitSitecode;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IJcVisitOrgService;
import com.ucap.cloud_web.service.IJcVisitSitecodeService;
import com.ucap.cloud_web.util.ExportExcel;
/**
 * <p>Description: 关键栏目连通性详情</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: ConnectionChannelDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：linhb </p>
 * <p>@date：2016年12月13日上午10:29:44 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class JcVisitOrgAction extends BaseAction{
	
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private IJcVisitOrgService jcVisitOrgServiceImpl;
	@Autowired
	private IJcVisitSitecodeService jcVisitSitecodeServiceImpl;

	private String siteCode = "";
	private String commonBigTab = "";
	private int dateNum;
	
	private Object[] objOrg = new Object[]{"组织单位名称/编码","浏览量平均值","浏览量总数","访客量平均值","访客量总数"};
	private Object[] objTb  = new Object[]{"填报单位名称/编码","网址","浏览量","访客量"};
	/**
	 * 
	 * @描述:跳转浏览量 页面
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日上午9:03:46 
	 * @return
	 */
	public String index(){
		
		//获取当前登录的  siteCode
		String siteCod = getCurrentSiteCode();
		int id = 0;
		int level = 2;// 1 bm0100 ;2有下级战群 3 没有下级战群； 
		id = databaseBizServiceImpl.isHasChridren(siteCod);
		if("bm0100".equals(siteCod)){
			level = 1;
		}else{
			// flag 0 有下级战群 1 没有下级战群
			if(id>0){
				level = 2;
			}else{
				level = 3;
			}
		}
		
		request.setAttribute("siteCode",siteCod);
		request.setAttribute("level",level);
		request.setAttribute("databaseTreeInfoId",id);
		return "success";
	}
	/**
	 * 
	 * @描述:获取下级战群的 访问量数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public void getOrgData(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//String parentId = request.getParameter("parentId");
//			String siteCode = request.getParameter("siteCode");
//			String commonBigTab = request.getParameter("commonBigTab");
			int level = 2;// 1 bm0100 ;2有下级战群 3 没有下级战群； 
			int id = databaseBizServiceImpl.isHasChridren(siteCode);
			// flag 0 有下级战群 1 没有下级战群
			if(id>0){
				JcVisitOrgRequest rRequest = new JcVisitOrgRequest();
				if("bm0100".equals(siteCode)){
					level = 1;
					if("3".equals(commonBigTab)){
						rRequest.setIsBm(1);
					}else{
						rRequest.setIsBm(0);
					}
				}else{
					level = 2;
				}
				rRequest.setParentId(id);
				rRequest.setScanDate(DateUtils.getYesterdayStr());
				List<JcVisitOrg> list = jcVisitOrgServiceImpl.getOrgData(rRequest);
				resultMap.put("list", list);
				resultMap.put("databaseTreeInfoId",id);
			}else{
				level = 3;
				DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
				qRequest.setSiteCode(siteCode);
				List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
				if(dList.size()>0){
					JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
					sRequest.setParentId(dList.get(0).getId());
					sRequest.setScanDate(DateUtils.getYesterdayStr());
					List<JcVisitSitecode> sList = jcVisitSitecodeServiceImpl.queryNatives(sRequest);
					resultMap.put("list", sList);
					resultMap.put("databaseTreeInfoId",dList.get(0).getId());
				}
			}
			
			resultMap.put("level", level);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public void getOrganizations(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
//			String siteCode = request.getParameter("siteCode");
//			String commonBigTab = request.getParameter("commonBigTab");
			int parentId = getIdBySiteCode(siteCode);
			List<JcVisitSitecode> list = null;
			if(parentId>0){
				JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
				if("bm0100".equals(siteCode)){
					if("4".equals(commonBigTab)){
						sRequest.setIsBm(1);
					}else{
						sRequest.setIsBm(0);
					}
				}
				sRequest.setParentId(parentId);
				sRequest.setScanDate(DateUtils.getYesterdayStr());
				list = jcVisitSitecodeServiceImpl.queryOrganizations(sRequest);
			}
			resultMap.put("list", list);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @描述:获取 下级地方门户
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月13日下午4:21:54
	 */
	public void getNatives(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//String siteCode = request.getParameter("siteCode");
			List<JcVisitSitecode> list = null;
			int parentId = getIdBySiteCode(siteCode);
			if(parentId>0){
				JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
				if("bm0100".equals(siteCode)){
					sRequest.setIsBm(0);
				}
				sRequest.setParentId(parentId);
				sRequest.setScanDate(DateUtils.getYesterdayStr());
				list = jcVisitSitecodeServiceImpl.queryNatives(sRequest);
			}
			resultMap.put("list", list);
			resultMap.put("success", "true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
  /**
   * 
   * @描述:通过siteCode 获取id
   * @作者:linhb@ucap.com.cn
   * @时间:2016年12月15日上午8:56:51 
   * @param siteCode
   * @return
   */
	public int getIdBySiteCode(String siteCod){
		int id = 0;
		try {
			DatabaseTreeInfoRequest qRequest = new DatabaseTreeInfoRequest();
			qRequest.setSiteCode(siteCod);
			List<DatabaseTreeInfo> dList = databaseTreeInfoServiceImpl.queryList(qRequest);
			if(dList.size()>0){
				id=dList.get(0).getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * 
	 * @描述:导出数据
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月15日下午1:53:03
	 */
	public void exportExcelData(){
		try {
			//String siteCode = request.getParameter("siteCode");
			List<Map<String, Object>> dtList=new ArrayList<Map<String,Object>>();  
			int id = databaseBizServiceImpl.isHasChridren(siteCode);
			// flag 0 有下级战群 1 没有下级战群
			
			if(id>0){
				if("bm0100".equals(siteCode)){//5个sheet
					Map<String, Object> dataMap1=new HashMap<String, Object>();
					dataMap1.put("title", "下级地方站群");
					ArrayList<Object[]> list=new ArrayList<Object[]>();
					list.add(objOrg);
					list.addAll(commonOrg(id,0));
					dataMap1.put("list", list);
					dtList.add(dataMap1);
					
					Map<String, Object> dataMap2=new HashMap<String, Object>();
					dataMap2.put("title", "下级地方门户");
					ArrayList<Object[]> list2=new ArrayList<Object[]>();
					list2.add(objTb);
					list2.addAll(commonOrgOgan(id,0));
					dataMap2.put("list", list2);
					dtList.add(dataMap2);
					
					Map<String, Object> dataMap3=new HashMap<String, Object>();
					dataMap3.put("title", "本级站点");
					ArrayList<Object[]> list3=new ArrayList<Object[]>();
					list3.add(objTb);
					list3.addAll(commonNative(id));
					dataMap3.put("list", list3);
					dtList.add(dataMap3);
					
					Map<String, Object> dataMap4=new HashMap<String, Object>();
					dataMap4.put("title", "下级部门站群");
					ArrayList<Object[]> list4=new ArrayList<Object[]>();
					list4.add(objOrg);
					list4.addAll(commonOrg(id,1));
					dataMap4.put("list", list4);
					dtList.add(dataMap4);
					
					Map<String, Object> dataMap5=new HashMap<String, Object>();
					dataMap5.put("title", "下级部门门户");
					ArrayList<Object[]> list5=new ArrayList<Object[]>();
					list5.add(objTb);
					list5.addAll(commonOrgOgan(id,1));
					dataMap5.put("list", list5);
					dtList.add(dataMap5);
				}else{//3个sheet
					Map<String, Object> dataMap1=new HashMap<String, Object>();
					dataMap1.put("title", "下级地方站群");
					ArrayList<Object[]> list=new ArrayList<Object[]>();
					list.add(objOrg);
					list.addAll(commonOrg(id,0));
					dataMap1.put("list", list);
					dtList.add(dataMap1);
					
					Map<String, Object> dataMap2=new HashMap<String, Object>();
					dataMap2.put("title", "下级地方门户");
					ArrayList<Object[]> list2=new ArrayList<Object[]>();
					list2.add(objTb);
					list2.addAll(commonOrgOgan(id,0));
					dataMap2.put("list", list2);
					dtList.add(dataMap2);
					
					Map<String, Object> dataMap3=new HashMap<String, Object>();
					dataMap3.put("title", "本级站点");
					ArrayList<Object[]> list3=new ArrayList<Object[]>();
					list3.add(objTb);
					list3.addAll(commonNative(id));
					dataMap3.put("list", list3);
					dtList.add(dataMap3);
				}
			}else{//1个sheet
				Map<String, Object> dataMap1=new HashMap<String, Object>();
				dataMap1.put("title", "本级站点");
				ArrayList<Object[]> list3=new ArrayList<Object[]>();
				list3.add(objTb);
				list3.addAll(commonNative(getIdBySiteCode(siteCode)));
				dataMap1.put("list", list3);
				dtList.add(dataMap1);
			}
			ExportExcel.getVisitDatas("网站访问量.xls",dtList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通过parentId获取下级战群
	public List<Object[]> commonOrg(int id,int isBm) throws Exception{
		List<Object[]> rList = new ArrayList<Object[]>();
		JcVisitOrgRequest rRequest = new JcVisitOrgRequest();
		rRequest.setIsBm(isBm);
		rRequest.setParentId(id);
		rRequest.setScanDate(DateUtils.getYesterdayStr());
		List<JcVisitOrg> list = jcVisitOrgServiceImpl.getOrgData(rRequest);
		for (JcVisitOrg jcVisitOrg : list) {
			Object[] obj = new Object[5];
			obj[0]=jcVisitOrg.getName()+" ("+jcVisitOrg.getSiteCode()+")";
			obj[1]=jcVisitOrg.getPvAvg();
			obj[2]=jcVisitOrg.getPv();
			obj[3]=jcVisitOrg.getUvAvg();
			obj[4]=jcVisitOrg.getUv();
			rList.add(obj);
		}
		return rList;
	}
	//通过parentId获取下级战群门户
	public List<Object[]> commonOrgOgan(int id,int isBm) throws Exception{
		List<Object[]> rList = new ArrayList<Object[]>();
		JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
		sRequest.setIsBm(isBm);
		sRequest.setParentId(id);
		sRequest.setScanDate(DateUtils.getYesterdayStr());
		List<JcVisitSitecode> list = jcVisitSitecodeServiceImpl.queryOrganizations(sRequest);
		for (JcVisitSitecode jcVisitSitecode : list) {
			Object[] obj = new Object[4];
			obj[0]=jcVisitSitecode.getName()+" ("+jcVisitSitecode.getSiteCode()+")";
			if(StringUtils.isNotEmpty(jcVisitSitecode.getJumpUrl())){
				obj[1]=jcVisitSitecode.getJumpUrl();
			}else{
				obj[1]=jcVisitSitecode.getUrl();
			}
			obj[2]=jcVisitSitecode.getUrlCnt();
			obj[3]=jcVisitSitecode.getIpCnt();
			rList.add(obj);
		}
		return rList;
	}
	//通过parentId获取本级部门
	public List<Object[]> commonNative(int id) throws Exception{
		List<Object[]> rList = new ArrayList<Object[]>();
		JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
		sRequest.setParentId(id);
		sRequest.setScanDate(DateUtils.getYesterdayStr());
		List<JcVisitSitecode> list  = jcVisitSitecodeServiceImpl.queryNatives(sRequest);
		for (JcVisitSitecode jcVisitSitecode : list) {
			Object[] obj = new Object[4];
			obj[0]=jcVisitSitecode.getName()+" ("+jcVisitSitecode.getSiteCode()+")";
			if(StringUtils.isNotEmpty(jcVisitSitecode.getJumpUrl())){
				obj[1]=jcVisitSitecode.getJumpUrl();
			}else{
				obj[1]=jcVisitSitecode.getUrl();
			}
			obj[2]=jcVisitSitecode.getUrlCnt();
			obj[3]=jcVisitSitecode.getIpCnt();
			rList.add(obj);
		}
		return rList;
	}
	
	
	public void getTrend(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotEmpty(siteCode)){
				if(dateNum>-1){
					dateNum = -30;
				}
				if(siteCode.length()==6){
					JcVisitOrgRequest rRequest = new JcVisitOrgRequest();
					rRequest.setSiteCode(siteCode);
					rRequest.setPageSize(Integer.MAX_VALUE);
					rRequest.setEndDate(DateUtils.getNextDay(new Date(), dateNum));
					
					List<QueryOrder> qList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
					qList.add(siteQueryOrder);
					rRequest.setQueryOrderList(qList);
					
					List<JcVisitOrg> list = jcVisitOrgServiceImpl.queryList(rRequest);
					resultMap.put("list", list);
				}else{
					JcVisitSitecodeRequest sRequest = new JcVisitSitecodeRequest();
					sRequest.setEndDate(DateUtils.getNextDay(new Date(), dateNum));
					sRequest.setSiteCode(siteCode);
					sRequest.setPageSize(Integer.MAX_VALUE);
					
					List<QueryOrder> qList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder=new QueryOrder("visit_date",QueryOrderType.ASC);
					qList.add(siteQueryOrder);
					sRequest.setQueryOrderList(qList);
					
					List<JcVisitSitecode> list = jcVisitSitecodeServiceImpl.queryList(sRequest);
					resultMap.put("list", list);
				}
				resultMap.put("success", "true");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 获取数据
	 * @author: linhb --- 2016-10-09下午4:09:22
	 * @return
	 * @throws Exception
	 */
	public void getSevenDatas() {
		//封装返回数据
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			JcVisitOrgRequest jRequest = new JcVisitOrgRequest();
			
			List<QueryOrder> querySiteList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.ASC);
			querySiteList.add(siteQueryOrder);
			jRequest.setQueryOrderList(querySiteList);
			jRequest.setSiteCode(getCurrentSiteCode());
			jRequest.setEndDate(DateUtils.getNextDay(new Date(), -7));
			List<JcVisitOrg> list = jcVisitOrgServiceImpl.queryList(jRequest);
			resultMap.put("list", list);
			resultMap.put("success","true");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	
	public int getDateNum() {
		return dateNum;
	}
	public void setDateNum(int dateNum) {
		this.dateNum = dateNum;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getCommonBigTab() {
		return commonBigTab;
	}
	public void setCommonBigTab(String commonBigTab) {
		this.commonBigTab = commonBigTab;
	}
}
