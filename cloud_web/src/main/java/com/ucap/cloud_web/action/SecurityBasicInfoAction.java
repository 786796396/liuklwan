package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.constant.SecurityBasicType;
import com.ucap.cloud_web.dto.SecurityBasicInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.SecurityBasicInfo;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.service.ISecurityBasicInfoService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.util.ExportExcel;

import net.sf.json.JSONObject;

/**
 * <p>Description:内容保障问题--基本信息处理 </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: SecurityBasicInfoAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：cuichx </p>
 * <p>@date：2016-4-12上午10:03:09 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityBasicInfoAction extends BaseAction {
	//log日志信息
	private static Log logger = LogFactory.getLog(SecurityBasicInfoAction.class);
	
	@Autowired
	private ISecurityBasicInfoService securityBasicInfoServiceImpl;
	
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;

	
	/**
	 * @Description: 基本信息页面
	 * @author cuichx --- 2015-11-18下午6:48:13     
	 * @return
	 */
	public String index(){
		//siteCode处理由组织单位跳转到该页面时，session的修改
		String siteCode = request.getParameter("siteCode");
		
		if(StringUtils.isNotEmpty(siteCode)){
			setCurrentShiroUser(siteCode);
		}
		return "success";
	}
	/**
	 * @Description:页面周期时间控件初始化数据 
	 * @author cuichx --- 2015-11-18下午4:06:24
	 */
	public void getTimeTool(){
		try {
			Map<String,Object> resultMap=new HashMap<String, Object>();
			resultMap=timeTool("");
			logger.info(JSONObject.fromObject(resultMap).toString());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 基本信息检测结果--列表查询
	 * @author cuichx --- 2015-11-19下午2:30:39
	 */
	public void basicInfoList(){
		Map<String,Object> map_list=new HashMap<String, Object>();
		List<Object> returnList=new ArrayList<Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
//			String serviceId=request.getParameter("serviceId");//周期id
			//if(StringUtils.isNotEmpty(serviceId)){
				//封装页面参数
				SecurityBasicInfoRequest securityBasicInfoRequest=new SecurityBasicInfoRequest();
				/**
				 * 通过网站表示码、服务周期id 查询内容保障问题基本信息表
				 */
				securityBasicInfoRequest.setSiteCode(siteCode);
				//securityBasicInfoRequest.setServicePeriodId(Integer.valueOf(serviceId));
				securityBasicInfoRequest.setPageSize(Integer.MAX_VALUE);
				
				List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
				//前台去掉周期，发现时间倒排序
				QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
				queryBlankList.add(siteQueryOrder);
				securityBasicInfoRequest.setQueryOrderList(queryBlankList);
				//查询今天之前的数据
				String yesDate = DateUtils.getYesterdayStr();
				securityBasicInfoRequest.setScanDate(yesDate);
				int total=0;
				List<SecurityBasicInfo>  securityBasicList=securityBasicInfoServiceImpl.queryList(securityBasicInfoRequest);
				if(securityBasicList!=null && securityBasicList.size()>0){
					for(int i=0;i<securityBasicList.size();i++){
						SecurityBasicInfo securityBasicInfo=securityBasicList.get(i);
						Map<String,Object> map=new HashMap<String, Object>();
						

						//栏目名称
						if(StringUtils.isNotEmpty(securityBasicInfo.getChannelName())){
							map.put("channelName", securityBasicInfo.getChannelName());//栏目名称
						}else{
							map.put("channelName", "");//栏目名称
						}
						//栏目url
						if(StringUtils.isNotEmpty(securityBasicInfo.getChannelUrl())){
							map.put("channelUrl", securityBasicInfo.getChannelUrl());//栏目url
						}else{
							map.put("channelUrl", "");//栏目url
						}
						
						
						//问题类型
						String problemTypeName=SecurityBasicType.getNameByCode(securityBasicInfo.getProblemTypId());
						if(StringUtils.isNotEmpty(problemTypeName)){
							map.put("problemTypeId",problemTypeName );
						}else{
							map.put("problemTypeId","");
						}
						
						
						if(StringUtils.isNotEmpty(securityBasicInfo.getProblemDesc())){
							map.put("problemDesc", securityBasicInfo.getProblemDesc());//问题描述
						}else{
							map.put("problemDesc", "");//问题描述
						}
						
						//周期时间
						String serviceTime="";
						//根据服务周期id，查询服务周期表
						ServicePeriod servicePeriod=servicePeriodServiceImpl.get(securityBasicInfo.getServicePeriodId());//serviceId
						if(servicePeriod!=null){
							if(StringUtils.isNotEmpty(DateUtils.formatStandardDate(servicePeriod.getStartDate())) &&
									StringUtils.isNotEmpty(DateUtils.formatStandardDate(servicePeriod.getEndDate()))){
								serviceTime=DateUtils.formatStandardDate(servicePeriod.getStartDate())+
										"至"+DateUtils.formatStandardDate(servicePeriod.getEndDate());
							}
						}
						map.put("serviceTime", serviceTime);
						
						//修改时间
						String modifyTime="";
						if(securityBasicInfo.getModifyTime()!=null){
							modifyTime=DateUtils.formatStandardDateTime(securityBasicInfo.getModifyTime());
						}
						map.put("modifyTime", modifyTime);
						
						String imgUrl=securityBasicInfo.getImgUrl();
						if(StringUtils.isNotEmpty(imgUrl)){
							if(imgUrl.startsWith("htm")){
								map.put("imgUrl", urlAdapterVar.getImgUrl()+imgUrl);//快照
							}else{
								map.put("imgUrl", urlAdapterVar.getLinkUrl()+imgUrl);//问题截图
							}
						}else{
							map.put("imgUrl", "");//快照
						}

						returnList.add(map);
					}
				}	
				map_list.put("returnList", returnList);//列表查询数据
				map_list.put("total", total);//空白栏目个数
			/*}else{
				map_list.put("errorMsg", "不存在该周期对象！");
				
			}*/
			logger.info("map_list="+map_list);
			writerPrint(JSONObject.fromObject(map_list).toString());
		} catch (Exception e) {
			e.printStackTrace();
			map_list.put("errorMsg", "获取互动回应差数据异常！");
			writerPrint(JSONObject.fromObject(map_list).toString());
		}
		
	}
	
	/**
	 * @Description: 基本信息  excel导出
	 * @author cuichx --- 2015-11-25上午11:42:02
	 */
	public void basicInfoExcel(){
		try {
			//获取页面参数
//			String serviceId=request.getParameter("serviceId");//服务周期id
			//关键字
			String key =request.getParameter("key");
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			
			
			//封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			//excel标题
			Object[] obj1 = new Object[]{"序号","栏目名称","问题类型","问题描述","周期时间","修改时间","截图"};
			list.add(obj1);
			//内容保障问题-基本信息监测结果(YYYY-MM-DD)
			String fileName = "内容保障问题-基本信息监测结果("+DateUtils.formatStandardDate(new Date())+").xls";
			String title = "基本信息监测结果"; 
			
			
			//if(StringUtils.isNotEmpty(serviceId)){
				//封装页面参数
				SecurityBasicInfoRequest securityBasicInfoRequest=new SecurityBasicInfoRequest();
				/**
				 * 通过网站表示码、服务周期id 查询内容保障问题基本信息表
				 */
				securityBasicInfoRequest.setSiteCode(siteCode);
				//securityBasicInfoRequest.setServicePeriodId(Integer.valueOf(serviceId));
				securityBasicInfoRequest.setPageSize(Integer.MAX_VALUE);

				//查询今天之前的数据
				String yesDate = DateUtils.getYesterdayStr();
				securityBasicInfoRequest.setScanDate(yesDate);
				
				//用于关键字查询
				if(StringUtils.isNotEmpty(key)){
					securityBasicInfoRequest.setChannelName(key);
				}
				List<SecurityBasicInfo>  scuBasicList=securityBasicInfoServiceImpl.queryList(securityBasicInfoRequest);
				if(scuBasicList!=null && scuBasicList.size()>0){
					for(int i=0;i<scuBasicList.size();i++){
						SecurityBasicInfo scuBasicInfo=scuBasicList.get(i);
						Object[] obj = new Object[7];

						String channelName=scuBasicInfo.getChannelName();//栏目名称
						if(StringUtils.isEmpty(channelName)){
							channelName="";
						}
						String problemTypeId=SecurityBasicType.getNameByCode(scuBasicInfo.getProblemTypId());
						if(StringUtils.isEmpty(problemTypeId)){
							problemTypeId="";
						}
						
						//问题描述
						String problemDesc="";
						if(StringUtils.isNotEmpty(scuBasicInfo.getProblemDesc())){
							problemDesc=scuBasicInfo.getProblemDesc();
						}
						
						//周期时间
						String serviceTime="";
						//根据服务周期id，查询服务周期表
						ServicePeriod servicePeriod=servicePeriodServiceImpl.get(scuBasicInfo.getServicePeriodId());//serviceId
						if(servicePeriod!=null){
							if(StringUtils.isNotEmpty(DateUtils.formatStandardDate(servicePeriod.getStartDate())) &&
									StringUtils.isNotEmpty(DateUtils.formatStandardDate(servicePeriod.getEndDate()))){
								serviceTime=DateUtils.formatStandardDate(servicePeriod.getStartDate())+
										"至"+DateUtils.formatStandardDate(servicePeriod.getEndDate());
							}
						}
						
						//修改时间
						String modifyTime="";
						if(scuBasicInfo.getModifyTime()!=null){
							modifyTime=DateUtils.formatStandardDateTime(scuBasicInfo.getModifyTime());
						}
						

						String imgUrl=scuBasicInfo.getImgUrl();//问题截图

						obj[0]=i+1;
						obj[1]=channelName;
						obj[2]=problemTypeId;
						obj[3]=problemDesc;
						obj[4]=serviceTime;
						obj[5]=modifyTime;
						if(StringUtils.isNotEmpty(imgUrl)){
							if(imgUrl.startsWith("htm")){
								obj[6]=urlAdapterVar.getImgUrl()+imgUrl;
							}else{
								obj[6]=urlAdapterVar.getLinkUrl()+imgUrl;
							}
						}else{
							obj[6]="无";
						}

						list.add(obj);
					}
				}
				ExportExcel.basicChannelExcel(fileName, title, list);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 组织单位——13个列表-基本信息列表数据
	 * @author cuichx --- 2016-3-30下午6:13:30
	 */
	public void getSecurityBasicInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String menuType=request.getParameter("menuType");//组织机构级别
			logger.info("getSecurityBasicInfo menuType:"+menuType);
			//获取当前组织机构编码
			String siteCode = getCurrentSiteCode();
			//根据组织机构编码和组织机构的级别，获取对应的网站标识码集合
			List<DatabaseInfo>  siteList=queryDatebaseInfoListByType(menuType, siteCode);
			
			List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
			
			int iscost=getCurrentUserInfo().getIsOrgCost();
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			//当前组织机构下所有站点服务不实用个数统计
			int errorNum = 0;
			if(iscost==1){//付费版
				//获取当前周期
				/**老合同信息**/
				ServicePeriod spid = getCurrentPeriod();
				/**新产品信息**/
				// Integer[] productTypeArr = {
				// CrmProductsType.DETECTION.getCode() };
				// ServicePeriod spid = getCurrentServicePeriod(productTypeArr);
				if(spid!=null){
					hashMap.put("ids", siteList);
					hashMap.put("servicePeriodId", spid.getId());
					List<SecurityBasicInfoRequest> basicList = securityBasicInfoServiceImpl.getProblemNum(hashMap);
					if(basicList!=null && basicList.size()>0){
						for (int i = 0; i < basicList.size(); i++) {
							SecurityBasicInfoRequest basicInfoRequest = basicList.get(i);
							Map<String, Object> item = new HashMap<String, Object>();
							item.put("siteCode", basicInfoRequest.getSiteCode());
							item.put("siteName", basicInfoRequest.getSiteName());
							//先取跳转url,如果跳转url为空，再取首页url
							String jumpPageUrl=basicInfoRequest.getJumpPageUrl();
							if (StringUtils.isNotEmpty(jumpPageUrl)) {
								item.put("url", jumpPageUrl);
							} else {
								item.put("url", basicInfoRequest.getHomePageUrl());
							}
							//每个站点服务不实用个数统计
							item.put("questionCount", basicInfoRequest.getProblemNum());
							//当前组织机构下所有站点服务不实用个数统计
							errorNum += basicInfoRequest.getProblemNum();

							int isorganizational = basicInfoRequest.getIsorganizational();
							item.put("isorganizational", isorganizational);
							String director = basicInfoRequest.getDirector();
							item.put("siteManageUnit", director);
							String address = basicInfoRequest.getAddress();
							item.put("officeAddress", address);
							String principalName = basicInfoRequest.getPrincipalName();
							item.put("relationName", principalName);
							String telephone = basicInfoRequest.getTelephone();
							item.put("relationCellphone", telephone);
							String mobile = basicInfoRequest.getMobile();
							item.put("relationPhone", mobile);
							String email = basicInfoRequest.getEmail();
							item.put("relationEmail", email);
							
							
							String linkmanName = basicInfoRequest.getLinkmanName();
							item.put("linkman", linkmanName);
							String telephone2 = basicInfoRequest.getTelephone2();
							item.put("linkmanCellphone", telephone2);
							
							String mobile2 = basicInfoRequest.getMobile2();
							item.put("linkmanPhone", mobile2);
							String email2 = basicInfoRequest.getEmail2();
							item.put("linkmanEmail", email2);
							
							items.add(item);
						}
					}
				}
			}else{
				String scanDate=DateUtils.formatStandardDate(DateUtils.parseStandardDate(DateUtils.getYesterdayStr()));
				hashMap.put("ids", siteList);
				hashMap.put("scanDate", scanDate);
				List<SecurityBasicInfoRequest> basicList = securityBasicInfoServiceImpl.getProblemNum(hashMap);
				if(basicList!=null && basicList.size()>0){
					for (int i = 0; i < basicList.size(); i++) {
						SecurityBasicInfoRequest basicInfoRequest = basicList.get(i);
						Map<String, Object> item = new HashMap<String, Object>();
						item.put("siteCode", basicInfoRequest.getSiteCode());
						item.put("siteName", basicInfoRequest.getSiteName());
						//先取跳转url,如果跳转url为空，再取首页url
						String jumpPageUrl=basicInfoRequest.getJumpPageUrl();
						if (StringUtils.isNotEmpty(jumpPageUrl)) {
							item.put("url", jumpPageUrl);
						} else {
							item.put("url", basicInfoRequest.getHomePageUrl());
						}
						//每个站点服务不实用个数统计
						item.put("questionCount", basicInfoRequest.getProblemNum());
						//当前组织机构下所有站点服务不实用个数统计
						errorNum += basicInfoRequest.getProblemNum();

						int isorganizational = basicInfoRequest.getIsorganizational();
						item.put("isorganizational", isorganizational);
						String director = basicInfoRequest.getDirector();
						item.put("siteManageUnit", director);
						String address = basicInfoRequest.getAddress();
						item.put("officeAddress", address);
						String principalName = basicInfoRequest.getPrincipalName();
						item.put("relationName", principalName);
						String telephone = basicInfoRequest.getTelephone();
						item.put("relationCellphone", telephone);
						String mobile = basicInfoRequest.getMobile();
						item.put("relationPhone", mobile);
						String email = basicInfoRequest.getEmail();
						item.put("relationEmail", email);
						
						
						String linkmanName = basicInfoRequest.getLinkmanName();
						item.put("linkman", linkmanName);
						String telephone2 = basicInfoRequest.getTelephone2();
						item.put("linkmanCellphone", telephone2);
						
						String mobile2 = basicInfoRequest.getMobile2();
						item.put("linkmanPhone", mobile2);
						String email2 = basicInfoRequest.getEmail2();
						item.put("linkmanEmail", email2);
						
						items.add(item);
					}
				}
			}
			resultMap.put("hide", "#table_data_security_basic_info_hide");
			resultMap.put("errorNum", errorNum);
			resultMap.put("body", items);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
