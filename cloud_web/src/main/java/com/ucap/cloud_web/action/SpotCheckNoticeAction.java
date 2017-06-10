package com.ucap.cloud_web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.RelationsPeriodRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;
import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.RelationsPeriod;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.SpotCheckNotice;
import com.ucap.cloud_web.entity.SpotCheckResult;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IRelationsPeriodService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ISpotCheckNoticeService;
import com.ucap.cloud_web.service.ISpotCheckResultService;
import com.ucap.cloud_web.util.FileUtils;
import com.ucap.cloud_web.util.SendEmail;


/**
 * <p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: SpotCheckNoticeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-6-1下午6:45:52 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpotCheckNoticeAction extends BaseAction {

	/**
	 * log日志加载
	 */
	private static Log logger = LogFactory.getLog(SpotCheckNoticeAction.class);
	
	@Autowired
	private ISpotCheckResultService spotCheckResultServiceImpl;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	
	@Autowired
	private ISpotCheckNoticeService spotCheckNoticeServiceImpl;
	
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	
	@Autowired
	private IRelationsPeriodService relationsPeriodServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;//配置文件
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	
	private String noticeReportFileName;
	private String noticeReport;//上传报告附件
    private String noticeReportFileType;
    private String type;
    private String noticeReportContentType;
    
	/**
	 * @Description: 根据site_code/database_info ID获取网站信息
	 * @author: yangshuai --- 2016-6-2下午4:54:41
	 * @return
	 */
	public String getDataBaseInfo (){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String siteCode = request.getParameter("siteCode");//网站标识码
			String director = getCurrentUserInfo().getSiteCode();//主办单位
			DatabaseOrgInfo users = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(director);
			director=users.getName();
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> queryList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if(!CollectionUtils.isEmpty(queryList)){
				DatabaseInfo databaseInfo = queryList.get(0);
				resultMap.put("databaseInfo", databaseInfo);
			}
			//获取上级信息 add by Na.Y 20161103
			//抽查为非上级需要判断是否通知上级组织单位
			String upperOrgCode = databaseTreeBizServiceImpl.getUpperOrgCode(siteCode);
			//登录组织单位编码 
			String loginOrgCode = getCurrentUserInfo().getSiteCode();
			
			//上级组织单位
			DatabaseOrgInfo upperOrgInfo = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(upperOrgCode);
			resultMap.put("upperOrgInfo", upperOrgInfo);
			resultMap.put("loginOrgCode", loginOrgCode);
			
			resultMap.put("requireTime", DateUtils.formatDate(new Date()));
			resultMap.put("director", director);
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	
	/**
	 * @Description: 保存整改通知  抽查
	 * @author: yangshuai --- 2016-6-2下午7:51:59
	 * @return
	 */
	public String noticeAdd(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
		
			//1.获取参数
			String scheduleId = request.getParameter("scheduleId");//抽查进度表id
			String siteCode = request.getParameter("siteCode");//网站标识码
			String director = getCurrentUserInfo().getSiteCode();//主办单位
			DatabaseOrgInfo users = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(director);
			director=users.getName();
			String datepicker = request.getParameter("datepicker");//整改期限
			String noticeRequirement = request.getParameter("noticeRequirement");//整改要求
			String boxInput1 = request.getParameter("boxInput1");//获取联系人及邮箱
			String boxInput2 = request.getParameter("boxInput2");//获取负责人及邮箱
		
			
			//上级组织单位联系人及邮箱,负责人及邮箱
			String boxInput1_up = request.getParameter("boxInput1_up");//获取联系人及邮箱
			String boxInput2_up = request.getParameter("boxInput2_up");//获取负责人及邮箱
			String isAllowUpper = request.getParameter("isAllowUpper");//是否允许上级查看
			
			if(noticeRequirement != null && !"".equals(noticeRequirement) && noticeRequirement.length()>250){
				resultMap.put("errorMsg", "整改通知反馈失败,通反馈内容过长");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return null;
			}
			//获取网站信息
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> databaseInfoList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
			DatabaseInfo databaseInfo = new DatabaseInfo();
			if(databaseInfoList != null && databaseInfoList.size()>0){
				databaseInfo = databaseInfoList.get(0);
			}
			
			SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
			resultRequest.setSiteCode(siteCode);
			resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
			List<SpotCheckResult> resultList = spotCheckResultServiceImpl.queryList(resultRequest);
			if(resultList!=null && resultList.size()>0){
				logger.info("下发整改通知");
				//修改抽查报告状态,置为未反馈状态
				SpotCheckResult result = resultList.get(0);
				result.setCheckReportResult(1);
				spotCheckResultServiceImpl.update(result);//修改通知状态
				
				//获取周期任务
				ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
				servicePeriodRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleId));
				List<ServicePeriod> serviceList = servicePeriodServiceImpl.queryList(servicePeriodRequest);
				ServicePeriod servicePeriod = new ServicePeriod();
				if(serviceList!=null && serviceList.size()>0){
					servicePeriod = serviceList.get(0);
				}
				
				String email = boxInput1;//联系人邮箱
				String email2 = boxInput2;//负责人邮箱
	
				SpotCheckNoticeRequest spotCheckNoticeRequest=new SpotCheckNoticeRequest();
				spotCheckNoticeRequest.setServicePeriodId(servicePeriod.getId());
				spotCheckNoticeRequest.setSiteCode(siteCode);
				List<SpotCheckNotice> spotCheckNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
				if(spotCheckNoticeList.size()==0){
				//保存整改通知信息
				SpotCheckNotice spotCheckNotice = new SpotCheckNotice();
				spotCheckNotice.setServicePeriodId(servicePeriod.getId());
				spotCheckNotice.setDirector(director);
				spotCheckNotice.setDatabaseInfoId(databaseInfo.getId());
				spotCheckNotice.setSiteCode(siteCode);
				spotCheckNotice.setType(Integer.valueOf(type));//0:抽查  1:全面检测
				spotCheckNotice.setIsRead(0);//0:未读未反馈  1：已读未反馈
				if(StringUtils.isNotEmpty(datepicker)){
					spotCheckNotice.setEndTime(DateUtils.parseDateTime(datepicker));
				}
				spotCheckNotice.setNoticeRequirement(noticeRequirement);
				spotCheckNotice.setCheckReportResult(1);
				spotCheckNotice.setEmail(email);
				spotCheckNotice.setEmail2(email2);
				if(StringUtils.isNotEmpty(isAllowUpper)){
					spotCheckNotice.setIsAllowUpper(Integer.parseInt(isAllowUpper));
				}

				//add by Na.Y 20161104设置上级组织单位相关信息+orgSiteCode
				spotCheckNotice.setEmailUpper(boxInput1_up);
				spotCheckNotice.setEmail2Upper(boxInput2_up);
				//获取上级信息 add by Na.Y 20161103
				   //抽查为非上级需要判断是否通知上级组织单位
				String upperOrgCode = databaseTreeBizServiceImpl.getUpperOrgCode(siteCode);
				   //登录组织单位编码
				String loginOrgCode = getCurrentUserInfo().getSiteCode();
				spotCheckNotice.setOrgSiteCode(loginOrgCode);
				spotCheckNotice.setUpperOrgCode(upperOrgCode);
				
				spotCheckNoticeServiceImpl.add(spotCheckNotice);
				
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("siteName", databaseInfo.getName());
				map.put("director", director);
				map.put("nowTime", DateUtils.formatDate(new Date()));
				if(StringUtils.isNotEmpty(datepicker)){
					map.put("endTime", DateUtils.formatDate(DateUtils.parseDateTime(datepicker)));
				}
				
				
				//发邮件
				//1.邮件发送给填报单位
				if(StringUtils.isNotEmpty(email)){
					SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, email);
				}
				if(StringUtils.isNotEmpty(email2)){
					SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, email2);
				}
				
				
				//2.邮件发给上级组织单位（允许上级接收）
				if(StringUtils.isNotEmpty(isAllowUpper)&&isAllowUpper.equals("1")){
	
					DatabaseOrgInfo upperOrg = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(upperOrgCode);
					
					if(null!=upperOrg){
						map.put("siteName", upperOrg.getName());
						if(StringUtils.isNotEmpty(spotCheckNotice.getEmailUpper())){
							SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmailUpper());
						}
						if(StringUtils.isNotEmpty(spotCheckNotice.getEmail2Upper())){
							SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmail2Upper());
						}
					}
					
				}
				
			}
			
			resultMap.put("success", "整改通知下发成功");
			}else{
				resultMap.put("errorMsg", "整改通知下发失败");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "整改通知下发失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	

	/**
	 * @Description: 保存整改通知  抽查   批量
	 * @author: yangshuai --- 2016-6-2下午7:51:59
	 * @return
	 */
	public String noticeAddSome(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String scheduleId = request.getParameter("scheduleId");//抽查进度表id
			String siteCodeStr = request.getParameter("siteCodeStr");//网站标识码
			String director = getCurrentUserInfo().getSiteCode();//主办单位
			DatabaseOrgInfo users = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(director);
			director=users.getName();
			
			String noticeDate=request.getParameter("noticeDate");//反馈截止日期
			String isAllowUpper=request.getParameter("isAllowUpper");//反馈是否允许上级
			
			List<String> list = new ArrayList<String>();
			//将页面传来的questionNum转化为list

				if(null != siteCodeStr && !siteCodeStr.equals("")){
					list = Arrays.asList(siteCodeStr.split(","));
				}else{
					list = new ArrayList<String>();
				}
				for(int i=0;i<list.size();i++){
					String siteCode=list.get(i);
					//获取网站信息
					DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(siteCode);
					List<DatabaseInfo> databaseInfoList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
					DatabaseInfo databaseInfo = new DatabaseInfo();
					if(databaseInfoList != null && databaseInfoList.size()>0){
						databaseInfo = databaseInfoList.get(0);
					}
					
					//邮箱--负责人
					String email=databaseInfo.getEmail();
					//邮箱--联系人
					String email2=databaseInfo.getEmail2();
//					String director=databaseInfo.getDirector();//网站主管单位
					
					
					SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
					resultRequest.setSiteCode(siteCode);
					resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
					List<SpotCheckResult> resultList = spotCheckResultServiceImpl.queryList(resultRequest);
					if(resultList!=null && resultList.size()>0){
						logger.info("下发整改通知");
						//修改抽查报告状态,置为未反馈状态
						SpotCheckResult result = resultList.get(0);
						result.setCheckReportResult(1);
						spotCheckResultServiceImpl.update(result);//修改通知状态
						
						//获取周期任务
						ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
						servicePeriodRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleId));
						List<ServicePeriod> serviceList = servicePeriodServiceImpl.queryList(servicePeriodRequest);
						ServicePeriod servicePeriod = new ServicePeriod();
						if(serviceList!=null && serviceList.size()>0){
							servicePeriod = serviceList.get(0);
						}

						SpotCheckNoticeRequest spotCheckNoticeRequest=new SpotCheckNoticeRequest();
						spotCheckNoticeRequest.setServicePeriodId(servicePeriod.getId());
						spotCheckNoticeRequest.setSiteCode(siteCode);
						List<SpotCheckNotice> spotCheckNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
						if(spotCheckNoticeList.size()==0){		
							//获取上级信息 add by Na.Y 20161103
						   //抽查为非上级需要判断是否通知上级组织单位
							String loginOrgCode = getCurrentUserInfo().getSiteCode();//登录组织单位编码
							String upperOrgCode = databaseTreeBizServiceImpl.getUpperOrgCode(siteCode);//上级组织单位
							DatabaseOrgInfo upperOrg = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(upperOrgCode);
							
							//保存整改通知信息
							SpotCheckNotice spotCheckNotice = new SpotCheckNotice();
							spotCheckNotice.setServicePeriodId(servicePeriod.getId());
							spotCheckNotice.setDirector(director);
							spotCheckNotice.setDatabaseInfoId(databaseInfo.getId());
							spotCheckNotice.setSiteCode(siteCode);
							spotCheckNotice.setCheckReportResult(1);
							spotCheckNotice.setEmail(email);
							spotCheckNotice.setEmail2(email2);
							if(StringUtils.isNotEmpty(noticeDate)){
								spotCheckNotice.setEndTime(DateUtils.parseDateTime(noticeDate));
							}
							spotCheckNotice.setType(Integer.valueOf(type));//0:抽查  1:全面检测
							spotCheckNotice.setIsRead(0);//0:未读未反馈  1：已读未反馈
							if(StringUtils.isNotEmpty(isAllowUpper)&&isAllowUpper.equals("1")){
								spotCheckNotice.setIsAllowUpper(Integer.parseInt(isAllowUpper));
								spotCheckNotice.setEmailUpper(upperOrg.getPrincipalEmail());
								spotCheckNotice.setEmail2Upper(upperOrg.getLinkmanEmail());
							}
							spotCheckNotice.setOrgSiteCode(loginOrgCode);
							spotCheckNotice.setUpperOrgCode(upperOrgCode);
							spotCheckNoticeServiceImpl.add(spotCheckNotice);
							
							//下发邮件
							Map<Object, Object> map = new HashMap<Object, Object>();
							map.put("siteName", databaseInfo.getName());
							map.put("director", director);
							map.put("nowTime", DateUtils.formatDate(new Date()));
							if(StringUtils.isNotEmpty(noticeDate)){
								map.put("endTime", DateUtils.formatDate(DateUtils.parseDateTime(noticeDate)));
							}
							if(StringUtils.isNotEmpty(email)){
								SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, email);
							}
							if(StringUtils.isNotEmpty(email2)){
								SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, email2);
							}
							
							//2.(抽查单位！=上级组织单位)邮件发给上级组织单位（允许上级接收）
							if(!upperOrgCode.equals(loginOrgCode)&&StringUtils.isNotEmpty(isAllowUpper)&&isAllowUpper.equals("1")){
				
								map.put("siteName", upperOrg.getName());
								if(null!=upperOrg){
									if(StringUtils.isNotEmpty(spotCheckNotice.getEmailUpper())){
										SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmailUpper());
									}
									if(StringUtils.isNotEmpty(spotCheckNotice.getEmail2Upper())){
										SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmail2Upper());
									}
								}
							}
							
						}		
						
						resultMap.put("success", "整改通知下发成功");
					}else{
						resultMap.put("errorMsg", "整改通知下发失败");
					}
				}
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "整改通知下发失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	/**
	 * @Description: 保存整改通知,全面监测
	 * @author: yangshuai --- 2016-6-2下午7:51:59
	 * @return
	 */
	public String noticeAddAll(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String servicePeriodId = request.getParameter("servicePeriodId");//周期任务id
			String siteCode = request.getParameter("siteCode");//网站标识码
			String director = getCurrentUserInfo().getSiteCode();//主办单位
			DatabaseOrgInfo users = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(director);
			director=users.getName();
			String datepicker = request.getParameter("datepicker");//整改期限
			String noticeRequirement = request.getParameter("noticeRequirement");//整改要求
			String boxInput1 = request.getParameter("boxInput1");//获取联系人及邮箱
			String boxInput2 = request.getParameter("boxInput2");//获取负责人及邮箱
			//上级组织单位联系人及邮箱,负责人及邮箱
			String boxInput1_up = request.getParameter("boxInput1_up");//获取联系人及邮箱
			String boxInput2_up = request.getParameter("boxInput2_up");//获取负责人及邮箱
			String isAllowUpper = request.getParameter("isAllowUpper");//是否允许上级查看
			
			if(noticeRequirement != null && !"".equals(noticeRequirement) && noticeRequirement.length()>250){
				resultMap.put("errorMsg", "整改通知反馈失败,通知反馈内容过长");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return null;
			}
			//获取网站信息
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> databaseInfoList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
			DatabaseInfo databaseInfo = new DatabaseInfo();
			if(databaseInfoList != null && databaseInfoList.size()>0){
				databaseInfo = databaseInfoList.get(0);
			}
			
			RelationsPeriodRequest relationsPeriodRequest = new RelationsPeriodRequest();
			relationsPeriodRequest.setSiteCode(siteCode);
			relationsPeriodRequest.setServicePeriodId(Integer.valueOf(servicePeriodId));
			List<RelationsPeriod> resultList = relationsPeriodServiceImpl.queryList(relationsPeriodRequest);
			if(resultList!=null && resultList.size()>0){
				logger.info("全面监测下发整改通知");
				
				String email = boxInput1;//联系人邮箱
				String email2 = boxInput2;//负责人邮箱
				
				SpotCheckNoticeRequest spotCheckNoticeRequest=new SpotCheckNoticeRequest();
				spotCheckNoticeRequest.setServicePeriodId(Integer.valueOf(servicePeriodId));
				spotCheckNoticeRequest.setSiteCode(siteCode);
				List<SpotCheckNotice> spotCheckNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
				if(spotCheckNoticeList.size()==0){
					
					//保存整改通知信息
					SpotCheckNotice spotCheckNotice = new SpotCheckNotice();
					spotCheckNotice.setServicePeriodId(Integer.valueOf(servicePeriodId));
					spotCheckNotice.setDirector(director);
					spotCheckNotice.setDatabaseInfoId(databaseInfo.getId());
					spotCheckNotice.setSiteCode(siteCode);
					spotCheckNotice.setEndTime(DateUtils.parseDateTime(datepicker));
					spotCheckNotice.setNoticeRequirement(noticeRequirement);
					spotCheckNotice.setCheckReportResult(1);
					spotCheckNotice.setEmail(email);
					spotCheckNotice.setEmail2(email2);
					spotCheckNotice.setType(Integer.valueOf(type));//0:抽查  1:全面检测
					spotCheckNotice.setIsRead(0);//0:未读未反馈  1：已读未反馈

					if(StringUtils.isNotEmpty(isAllowUpper)){
						spotCheckNotice.setIsAllowUpper(Integer.parseInt(isAllowUpper));
					}
					//add by Na.Y 20161104设置上级组织单位相关信息+orgSiteCode
					spotCheckNotice.setEmailUpper(boxInput1_up);
					spotCheckNotice.setEmail2Upper(boxInput2_up);
					//获取上级信息 add by Na.Y 20161103
					   //抽查为非上级需要判断是否通知上级组织单位
					String upperOrgCode = databaseTreeBizServiceImpl.getUpperOrgCode(siteCode);
					   //登录组织单位编码
					String loginOrgCode = getCurrentUserInfo().getSiteCode();
					spotCheckNotice.setOrgSiteCode(loginOrgCode);
					spotCheckNotice.setUpperOrgCode(upperOrgCode);
					spotCheckNoticeServiceImpl.add(spotCheckNotice);
					
					//邮件发送
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("siteName", databaseInfo.getName());
					map.put("director", director);
					map.put("nowTime", DateUtils.formatDate(new Date()));
					map.put("endTime", DateUtils.formatDate(DateUtils.parseDateTime(datepicker)));

					//发送邮件给填报单位			
					if(StringUtils.isNotEmpty(spotCheckNotice.getEmail())){
						SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmail());
					}
					if(StringUtils.isNotEmpty(spotCheckNotice.getEmail2())){
						SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmail2());
					}
			
					//2.邮件发给上级组织单位（允许上级接收）
					if(StringUtils.isNotEmpty(isAllowUpper)&&isAllowUpper.equals("1")){
		
						DatabaseOrgInfo upperOrg = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(upperOrgCode);
						if(null!=upperOrg){
							map.put("siteName", upperOrg.getName());
							
							if(StringUtils.isNotEmpty(spotCheckNotice.getEmailUpper())){
								SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmailUpper());
							}
							if(StringUtils.isNotEmpty(spotCheckNotice.getEmail2Upper())){
								SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmail2Upper());
							}
						}
					}
					resultMap.put("success", "整改通知下发成功");
				}
				
			}else{
				resultMap.put("errorMsg", "整改通知下发失败");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "整改通知下发失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	/**
	 * @Description: 保存整改通知,全面监测 批量
	 * @author: yangshuai --- 2016-6-2下午7:51:59
	 * @return
	 */
	public String noticeAddAllSome(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			
			String servicePeriodId = request.getParameter("servicePeriodId");//周期任务id
			String siteCodeStr = request.getParameter("siteCodeStr");//网站标识码
			String director = getCurrentUserInfo().getSiteCode();//主办单位
			DatabaseOrgInfo users = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(director);
			director=users.getName();
			
			String noticeDate=request.getParameter("noticeDate");//反馈截止日期
			String isAllowUpper=request.getParameter("isAllowUpper");//反馈是否允许上级
			List<String> list = new ArrayList<String>();
			//将页面传来的questionNum转化为list

				if(null != siteCodeStr && !siteCodeStr.equals("")){
					list = Arrays.asList(siteCodeStr.split(","));
				}else{
					list = new ArrayList<String>();
				}
			
				for(int i=0;i<list.size();i++){
					String siteCode=list.get(i);
					//获取网站信息
					DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
					databaseInfoRequest.setSiteCode(siteCode);
					List<DatabaseInfo> databaseInfoList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
					DatabaseInfo databaseInfo = new DatabaseInfo();
					if(databaseInfoList != null && databaseInfoList.size()>0){
						databaseInfo = databaseInfoList.get(0);
					}
					
					//邮箱--负责人
					String email=databaseInfo.getEmail();
					//邮箱--联系人
					String email2=databaseInfo.getEmail2();
//					String director=databaseInfo.getDirector();//网站主管单位
					
					
					RelationsPeriodRequest relationsPeriodRequest = new RelationsPeriodRequest();
					relationsPeriodRequest.setSiteCode(siteCode);
					relationsPeriodRequest.setServicePeriodId(Integer.valueOf(servicePeriodId));
					List<RelationsPeriod> resultList = relationsPeriodServiceImpl.queryList(relationsPeriodRequest);
					if(resultList!=null && resultList.size()>0){
						logger.info("全面监测下发整改通知");
						
//						String[] email = boxInput1.split(",");//联系人邮箱
//						String[] email2 = boxInput2.split(",");//负责人邮箱
						SpotCheckNoticeRequest spotCheckNoticeRequest=new SpotCheckNoticeRequest();
						spotCheckNoticeRequest.setServicePeriodId(Integer.valueOf(servicePeriodId));
						spotCheckNoticeRequest.setSiteCode(siteCode);
						List<SpotCheckNotice> spotCheckNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
						if(spotCheckNoticeList.size()==0){
							//获取上级信息 add by Na.Y 20161103
						   //抽查为非上级需要判断是否通知上级组织单位
							String loginOrgCode = getCurrentUserInfo().getSiteCode();//登录组织单位编码
							String upperOrgCode = databaseTreeBizServiceImpl.getUpperOrgCode(siteCode);//上级组织单位
							DatabaseOrgInfo upperOrg = databaseOrgInfoServiceImpl.getDatabaseOrgByCode(upperOrgCode);
							
							//保存整改通知信息
							SpotCheckNotice spotCheckNotice = new SpotCheckNotice();
							spotCheckNotice.setServicePeriodId(Integer.valueOf(servicePeriodId));
							spotCheckNotice.setDirector(director);
							spotCheckNotice.setDatabaseInfoId(databaseInfo.getId());
							spotCheckNotice.setSiteCode(siteCode);
							spotCheckNotice.setCheckReportResult(1);
							spotCheckNotice.setEmail(email);
							spotCheckNotice.setEmail2(email2);
							if(StringUtils.isNotEmpty(noticeDate)){
								spotCheckNotice.setEndTime(DateUtils.parseDateTime(noticeDate));
							}
							spotCheckNotice.setType(Integer.valueOf(type));//0:抽查  1:全面检测
							spotCheckNotice.setIsRead(0);//0:未读未反馈  1：已读未反馈
							if(StringUtils.isNotEmpty(isAllowUpper)&&isAllowUpper.equals("1")){
								spotCheckNotice.setIsAllowUpper(Integer.parseInt(isAllowUpper));
								spotCheckNotice.setEmailUpper(upperOrg.getPrincipalEmail());
								spotCheckNotice.setEmail2Upper(upperOrg.getLinkmanEmail());
							}
							spotCheckNotice.setOrgSiteCode(loginOrgCode);
							spotCheckNotice.setUpperOrgCode(upperOrgCode);
							spotCheckNoticeServiceImpl.add(spotCheckNotice);
							
							//下发邮件
							Map<Object, Object> map = new HashMap<Object, Object>();
							map.put("siteName", databaseInfo.getName());
							map.put("director", director);
							map.put("nowTime", DateUtils.formatDate(new Date()));
							if(StringUtils.isNotEmpty(noticeDate)){
								map.put("endTime", DateUtils.formatDate(DateUtils.parseDateTime(noticeDate)));
							}
							if(StringUtils.isNotEmpty(email)){
								SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, email);
							}
							if(StringUtils.isNotEmpty(email2)){
								SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, email2);
							}
							
							//2.(抽查单位！=上级组织单位)邮件发给上级组织单位（允许上级接收）
							if(!upperOrgCode.equals(loginOrgCode)&&StringUtils.isNotEmpty(isAllowUpper)&&isAllowUpper.equals("1")){
								if(null!=upperOrg){
									map.put("siteName", upperOrg.getName());
									if(StringUtils.isNotEmpty(spotCheckNotice.getEmailUpper())){
										SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmailUpper());
									}
									if(StringUtils.isNotEmpty(spotCheckNotice.getEmail2Upper())){
										SendEmail.sendEmail(databaseInfo.getName()+"网站整改通知", "duban.ftl", map, spotCheckNotice.getEmail2Upper());
									}
								}
							}
						}
					
						resultMap.put("success", "整改通知下发成功");
					}else{
						resultMap.put("errorMsg", "整改通知下发失败");
					}
				}
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "整改通知下发失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	/**
	 * @Description: 整改通知反馈
	 * @author: yangshuai --- 2016-6-2下午7:51:59
	 * @return
	 */
	public String updateNotice(){
		System.out.print(noticeReportFileName);
		System.out.print(noticeReportContentType);
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			String noticeId = request.getParameter("noticeId");//整改通知id
			String questionNum = request.getParameter("questionNum");//整改问题数
			String noticeResponse = request.getParameter("noticeResponse");//整改反馈
			String wordx="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			if(StringUtils.isNotEmpty(noticeReport) && !"application/pdf".equals(noticeReportContentType ) && !"application/msword".equals(noticeReportContentType ) && !wordx.equals(noticeReportContentType ) && !"image/jpeg".equals(noticeReportContentType ) && !"image/png".equals(noticeReportContentType )){
				resultMap.put("errorMsg", "只能上传jpg、png、pdf和word文件");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return null;
			}
			
			if(noticeResponse != null && !"".equals(noticeResponse) && noticeResponse.length()>250){
				resultMap.put("errorMsg", "整改通知反馈失败,通反馈内容过长");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return null;
			}
			//获取通知
			SpotCheckNotice spotCheckNotice = spotCheckNoticeServiceImpl.get(Integer.parseInt(noticeId));
			if(spotCheckNotice != null){
				logger.info("整改通知反馈");
				if(noticeReportFileName != null && !"".equals(noticeReportFileName)){
					String fileName = "";
					if(noticeReportFileName.indexOf(".")>=0){
						if(spotCheckNotice.getNoticeReportUrl()!=null && !"".equals(spotCheckNotice.getNoticeReportUrl()) && spotCheckNotice.getNoticeReportUrl().indexOf(".")>=0){
							String noticeReportFileNameHz = noticeReportFileName.substring(noticeReportFileName.indexOf("."), noticeReportFileName.length());
							String getNoticeReportUrlHz = spotCheckNotice.getNoticeReportUrl().substring(spotCheckNotice.getNoticeReportUrl().indexOf("."),spotCheckNotice.getNoticeReportUrl().length());
							if(noticeReportFileNameHz.equals(getNoticeReportUrlHz)){
								 fileName = spotCheckNotice.getNoticeReportUrl();
							 }else{
//								 FileUtils.delFileOrDerectory(urlAdapterVar.getWebPaths()+spotCheckNotice.getNoticeReportUrl());
								 fileName = spotCheckNotice.getNoticeReportUrl().substring(0, spotCheckNotice.getNoticeReportUrl().indexOf("."))+noticeReportFileName.substring(noticeReportFileName.indexOf("."), noticeReportFileName.length());
							 }
						}else{
							fileName = urlAdapterVar.getWordfilepath()+spotCheckNotice.getSiteCode()+"_"+DateUtils.formatShortFullDateTime(new Date())+noticeReportFileName.substring(noticeReportFileName.indexOf("."),noticeReportFileName.length());
						}
					}else{
						fileName = urlAdapterVar.getWordfilepath()+spotCheckNotice.getSiteCode()+"_"+DateUtils.formatShortFullDateTime(new Date())+noticeReportFileName;
					}
					FileInputStream fis = new FileInputStream(noticeReport);
					FileUtils.writeFile(fis, urlAdapterVar.getWebPaths()+fileName);
					spotCheckNotice.setNoticeReportUrl(fileName);
					spotCheckNotice.setNoticeReportName(noticeReportFileName);
					
					
					
				}
				spotCheckNotice.setQuestionNum(Integer.parseInt(questionNum));
				spotCheckNotice.setNoticeResponse(noticeResponse);
				spotCheckNotice.setCheckReportResult(2);
				spotCheckNoticeServiceImpl.update(spotCheckNotice);
				
				//获取周期任务
				ServicePeriod servicePeriod = servicePeriodServiceImpl.get(spotCheckNotice.getServicePeriodId());
				
				SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
				resultRequest.setSiteCode(spotCheckNotice.getSiteCode());
				resultRequest.setSpotCheckSchedule(servicePeriod.getSpotCheckScheduleId());
				List<SpotCheckResult> resultList = spotCheckResultServiceImpl.queryList(resultRequest);
				if(resultList!=null && resultList.size()>0){
					//修改抽查报告状态,置为已反馈状态
					SpotCheckResult result = resultList.get(0);
					result.setCheckReportResult(2);
					spotCheckResultServiceImpl.update(result);//修改通知状态
				}
				resultMap.put("success", "整改通知反馈成功");
			}else{
				resultMap.put("errorMsg", "整改通知反馈失败");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "整改通知反馈失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
		return null;
	}
	
	/**
	 * @Description: 获取整改通知
	 * @author: yangshuai --- 2016-6-3上午9:53:46
	 * @return
	 */
	public void getNotice(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			logger.info("获取整改通知");
			String siteCode = request.getParameter("siteCode");//网站标识码
			String scheduleId = request.getParameter("scheduleId");//抽查进度id
			String servicePeriodId = request.getParameter("servicePeriodId");//服务周期id
			ServicePeriod servicePeriod = new ServicePeriod();
			if(null == servicePeriodId || "".equals(servicePeriodId)){
				//获取周期任务
				ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
				servicePeriodRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleId));
				List<ServicePeriod> serviceList = servicePeriodServiceImpl.queryList(servicePeriodRequest);
				if(serviceList!=null && serviceList.size()>0){
					servicePeriod = serviceList.get(0);
				}
			}
			//获取网站信息
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCode(siteCode);
			List<DatabaseInfo> databaseInfoList = this.databaseInfoServiceImpl.queryList(databaseInfoRequest);
			DatabaseInfo databaseInfo = new DatabaseInfo();
			if(databaseInfoList != null && databaseInfoList.size()>0){
				databaseInfo = databaseInfoList.get(0);
				resultMap.put("databaseInfo", databaseInfo);
			}
			
			//获取整改通知
			SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
			spotCheckNoticeRequest.setSiteCode(siteCode);
			if(null != servicePeriodId && !"".equals(servicePeriodId)){
				spotCheckNoticeRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
			}else{
				spotCheckNoticeRequest.setServicePeriodId(servicePeriod.getId());
			}
			List<SpotCheckNotice> spotCheckNoticeList = spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
			SpotCheckNotice spotCheckNotice = new SpotCheckNotice();
			if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
				spotCheckNotice = spotCheckNoticeList.get(0);
				resultMap.put("spotCheckNotice", spotCheckNotice);
				//通知时间
				if(null!= spotCheckNotice.getRequireTime()){
					resultMap.put("requireTime", DateUtils.formatDate(spotCheckNotice.getRequireTime()));
				}else{
					resultMap.put("requireTime", "");
				}
				//整改期限时间
				if(null != spotCheckNotice.getEndTime()){
					resultMap.put("endTime", DateUtils.formatDate(spotCheckNotice.getEndTime()));
				}else{
					resultMap.put("endTime", "");
				}
				
				//点击查看按钮  出发修改通知整改已读状态 
				if(getCurrentUserInfo().getSiteCode().length()!=6){
					//此状态针对填报单位
					spotCheckNotice.setIsRead(1);
				}	
				spotCheckNoticeServiceImpl.update(spotCheckNotice);
				
			}
			resultMap.put("success", "获取整改通知成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取整改通知失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * @Description: 下载整改报告
	 * @author: yangshuai --- 2016-6-3下午4:29:54
	 * @return
	 */
	public void getDownNoticeWord(){
		try {
			String fid = request.getParameter("fid");
			SpotCheckNotice spotCheckNotice = spotCheckNoticeServiceImpl.get(Integer.parseInt(fid));
			if(null != spotCheckNotice){
				String wordUrl = urlAdapterVar.getWebPaths()+spotCheckNotice.getNoticeReportUrl();
				File file=new File(wordUrl);
				if(file.exists()){
					String aliasName = spotCheckNotice.getNoticeReportName();
					outPutFile(wordUrl,aliasName);
				}else{
					writerPrint("文件不存在");
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void outPutFile(String filePath, String fileName) {
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
		OutputStream outs = null;
		try {
			outs = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}// 获取文件输出IO流
		BufferedOutputStream bouts = new BufferedOutputStream(outs);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-download");// 设置response内容的类型
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName.replaceAll(" ", "%20")); // 设置头部信息
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		// 开始向网络传输文件流
		try {
			while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
				bouts.write(buffer, 0, bytesRead);
			}
			bouts.flush();
			ins.close();
			bins.close();
			outs.close();
			bouts.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	
	public String getNoticeReport() {
		return noticeReport;
	}
	public void setNoticeReport(String noticeReport) {
		this.noticeReport = noticeReport;
	}
	
	public String getNoticeReportFileName() {
		return noticeReportFileName;
	}
	public void setNoticeReportFileName(String noticeReportFileName) {
		this.noticeReportFileName = noticeReportFileName;
	}
	public String getNoticeReportFileType() {
		return noticeReportFileType;
	}
	public void setNoticeReportFileType(String noticeReportFileType) {
		this.noticeReportFileType = noticeReportFileType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNoticeReportContentType() {
		return noticeReportContentType;
	}

	public void setNoticeReportContentType(String noticeReportContentType) {
		this.noticeReportContentType = noticeReportContentType;
	}
	
}
