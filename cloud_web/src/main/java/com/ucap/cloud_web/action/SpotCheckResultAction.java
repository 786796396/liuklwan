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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.bizService.DatabaseBizService;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.CheckReportResultType;
import com.ucap.cloud_web.constant.CheckResultType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.SpotResultStateType;
import com.ucap.cloud_web.constant.SpotScheduleStateType;
import com.ucap.cloud_web.constant.TreeVo;
import com.ucap.cloud_web.dto.ComboRequest;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DetectionPeroidCountRequest;
import com.ucap.cloud_web.dto.ReportWordResultRequest;
import com.ucap.cloud_web.dto.ServicePeriodRequest;
import com.ucap.cloud_web.dto.SpotCheckInfoRequest;
import com.ucap.cloud_web.dto.SpotCheckNoticeRequest;
import com.ucap.cloud_web.dto.SpotCheckResultRequest;
import com.ucap.cloud_web.dto.SpotCheckScheduleRequest;
import com.ucap.cloud_web.dto.SpotSecurityReportRequest;
import com.ucap.cloud_web.entity.Combo;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DetectionPeroidCount;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.ReportWordResult;
import com.ucap.cloud_web.entity.ServicePeriod;
import com.ucap.cloud_web.entity.SpotCheckInfo;
import com.ucap.cloud_web.entity.SpotCheckNotice;
import com.ucap.cloud_web.entity.SpotCheckResult;
import com.ucap.cloud_web.entity.SpotCheckSchedule;
import com.ucap.cloud_web.entity.SpotSecurityReport;
import com.ucap.cloud_web.service.IComboService;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDetectionPeroidCountService;
import com.ucap.cloud_web.service.IReportCollectResultService;
import com.ucap.cloud_web.service.IReportWordResultService;
import com.ucap.cloud_web.service.IServicePeriodService;
import com.ucap.cloud_web.service.ISpotCheckInfoService;
import com.ucap.cloud_web.service.ISpotCheckNoticeService;
import com.ucap.cloud_web.service.ISpotCheckResultService;
import com.ucap.cloud_web.service.ISpotCheckScheduleService;
import com.ucap.cloud_web.service.ISpotSecurityReportService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.DownFiles;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.FileUtils;

/**
 * 描述：抽查结果表 包：com.ucap.cloud_web.action 文件名称：SpotCheckResultAction 公司名称：开普互联
 * 作者：lixuxiang 时间：2015-11-12上午11:22:23
 * 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SpotCheckResultAction extends BaseAction {

	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private ISpotCheckResultService spotCheckResultServiceImpl;
	@Autowired
	private ISpotCheckInfoService spotCheckInfoServiceImpl;
	@Autowired
	private ISpotCheckScheduleService spotCheckScheduleServiceImpl;
	@Autowired
	private IReportWordResultService reportWordResultServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IComboService comboServiceImpl;
	@Autowired
	private IServicePeriodService servicePeriodServiceImpl;
	@Autowired
	private ISpotCheckNoticeService spotCheckNoticeServiceImpl;
	@Autowired
	private IDetectionPeroidCountService detectionPeroidCountServiceImpl;	
	@Autowired
	private DatabaseBizService databaseBizServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private ISpotSecurityReportService spotSecurityReportServiceImpl;
	@Autowired
	private IReportCollectResultService reportCollectResultServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	// @Autowired
	// private ICrmProductsService crmProductsServiceImpl;
	private static Log logger = LogFactory.getLog(SpotCheckResultAction.class);
	
	//标识跳转页面来源
	private String flag="";
	//抽查进度表id
	private String scheduleId="";
	//批次
	private String  batchNum="";
	//组次
	private String groupNum="";
	private Integer checkReport;//报告状态是否选中 0全部 1已完成   2未完成
	private Integer checkNotice;//通知整改是否选中 0全部 1已通知 2未通知
	private Integer checkRead;//是否已读
	private Integer checkSiteType;//网站类型 0全部  1本级门户  2本级部门 3下属单位
	private Integer checkResult;// 监测结果0全部  1合格  2不合格
	
	//例外省份（广东），针对抽查更改为考评
	private String site_code_exp = "440000,";
	//session中获取当前登录的site_code
	private String site_code_session = "";
	//抽查汇报上传文件
	private String siteReportup;
	private String siteReportupNo;
	// 类型为抽查产品
	// private Integer[] productTypeArr = { CrmProductsType.CHECK.getCode() };
	
	/** 新产品信息 **/
	// /**
	// * @Description: 根据抽查比例，获取对应的抽查站点,并将其保存
	// * @author cuichx --- 2016-5-11下午2:59:07
	// */
	// public void randomSite(){
	// site_code_session = getCurrentSiteCode();
	// /**
	// * 根据条件随机抽查站点的集合
	// * 下面的处理思路和人工的处理思路是一样的
	// */
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	// try {
	// JSONObject json=getJSONObject();
	// if(json!=null){
	// String dateStart = json.getString("dateStart");//开始日期
	// String dateEnd = json.getString("dateEnd");//结束日期
	// Integer batchNum = Integer.parseInt(json.getString("batchNum"));// 批次
	// Integer groupNum = Integer.parseInt(json.getString("groupNum"));// 组次
	// Integer isorganizational =
	// Integer.parseInt(json.getString("isorganizational"));// 是非门户
	// String taskName = json.getString("taskName");//任务名称
	//
	// String scheduleId = json.getString("scheduleId");
	// int levels = getCurrentUserInfo().getUserType();
	//
	// String levelStr=json.getString("level");//站点级别
	// String isorg=json.getString("isorg");//门户类型
	// String spotPer=json.getString("spotPer");//抽查比例
	// String orgSiteCodes=json.getString("checkCodeStr");//省级组织机构编码串
	// //封装参数
	// Map<String, Object> map=new HashMap<String, Object>();
	// //省编码串
	// if(StringUtils.isNotEmpty(orgSiteCodes) && !",".equals(orgSiteCodes)){
	// if(levels == 3){//市
	// map.put("orgSiteCodes", getCurrentSiteCode().substring(0,4).split(","));
	// }else if(levels == 4){//县
	// map.put("orgSiteCodes", getCurrentSiteCode().split(","));
	// }else{
	// map.put("orgSiteCodes", orgSiteCodes.split(","));
	// }
	// }
	// //是否门户串
	// String[] isorgArray=null;
	// if(StringUtils.isNotEmpty(isorg) && !",".equals(isorg)){
	// isorgArray=isorg.split(",");
	// map.put("isorganizationals", isorgArray);
	// }
	// //抽查比例
	// double spotPercent=0.00;
	// if(StringUtils.isNotEmpty(spotPer)){
	// spotPercent=Double.valueOf(spotPer)/100;
	// }
	// //站点类型 1正常，2例外，3关停
	// map.put("isexp", DatabaseInfoType.NORMAL.getCode());
	//
	// List<DatabaseInfo> siteList=new ArrayList<DatabaseInfo>();//站点信息数据集合
	// if(StringUtils.isNotEmpty(levelStr) && !",".equals(levelStr)){
	// map.put("level", levelStr.split(","));
	//
	// //获取每个省的组织单位编码和各省的站点个数
	// List<DatabaseInfoRequest> list=
	// databaseInfoServiceImpl.queryLevelCountByMap(map);
	// if(list!=null && list.size()>0){
	// for(int j=0;j<list.size();j++){
	// DatabaseInfoRequest dataRequest=list.get(j);
	// String orgCode = dataRequest.getOrgCode();//省组织单位编码
	// int spotCounts=(int) (spotPercent*dataRequest.getSpotCount());//随机获取站点个数
	// if(spotCounts==0){
	// spotCounts=1;
	// }else{
	// if(!"100".equals(spotPer)){
	// spotCounts = spotCounts + 1;
	// }
	// }
	// Map<String, Object> paramMap=new HashMap<String, Object>();
	// if(levels == 3){//市
	// paramMap.put("orgCode", getCurrentSiteCode().substring(0,4));
	// }else if(levels == 4){//县
	// paramMap.put("orgCode", getCurrentSiteCode());
	// }else{
	// paramMap.put("orgCode", orgCode);
	// }
	// paramMap.put("spotCount", spotCounts);
	// paramMap.put("level", levelStr.split(","));
	// paramMap.put("isorganizationals", isorg.split(","));//是否门户
	// paramMap.put("isexp", DatabaseInfoType.NORMAL.getCode());//正常网站
	//
	// List<DatabaseInfo> shengLevelList =
	// databaseInfoServiceImpl.queryLevelRandomSite(paramMap);
	// if(shengLevelList !=null && shengLevelList.size()>0){
	// siteList.addAll(shengLevelList);
	// }
	// }
	// }
	// }
	//
	// /**
	// * 随机抽查站点保存与人工选择站点保存逻辑一样
	// * 如果抽查进度表i参数为空，说明是新增一个批组次
	// * 如果抽查进度表id参数不为空，说明是为原有的批组次增加站点
	// */
	//
	// if (StringUtils.isNotEmpty(dateStart)) {
	// dateStart=DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateStart));
	// }
	// if (StringUtils.isNotEmpty(dateEnd)) {
	// dateEnd=DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateEnd));
	// }
	//
	// String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
	//
	// // 当前抽查产品信息
	// Integer[] executeStatus = { 1 };
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), executeStatus, null);
	//
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// if(StringUtils.isEmpty(scheduleId)){//第一次添加站点
	// //抽查进度表判断重复--处理多并发问题 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
	// SpotCheckScheduleRequest seRequest=new SpotCheckScheduleRequest();
	// seRequest.setSiteCode(siteCode);
	// seRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// seRequest.setBatchNum(batchNum);
	// seRequest.setGroupNum(groupNum);
	// List<SpotCheckSchedule>
	// seList=spotCheckScheduleServiceImpl.queryList(seRequest);
	// if(seList==null || (seList!=null && seList.size()==0)){
	// // 向抽查进度表添加新批次数据
	// SpotCheckSchedule spotCheckSchedule = new SpotCheckSchedule();
	// spotCheckSchedule.setContractInfoId(crmlist.get(0).getId() + "");
	// spotCheckSchedule.setSiteCode(siteCode);
	// spotCheckSchedule.setTaskName(taskName);
	// spotCheckSchedule.setStartDate(dateStart);
	// spotCheckSchedule.setEndDate(dateEnd);
	// //该批次抽查次数在提交时设置
	// //spotCheckSchedule.setSpotWebsiteNum(webSum);
	// spotCheckSchedule.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckSchedule.setStatus(0);
	// spotCheckSchedule.setBatchNum(batchNum);
	// spotCheckSchedule.setGroupNum(groupNum);
	// spotCheckSchedule.setIsorganizational(isorganizational);
	// spotCheckScheduleServiceImpl.add(spotCheckSchedule);
	//
	// //通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
	// SpotCheckScheduleRequest scheduleRequest=new SpotCheckScheduleRequest();
	// scheduleRequest.setSiteCode(siteCode);
	// scheduleRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// scheduleRequest.setBatchNum(batchNum);
	// scheduleRequest.setGroupNum(groupNum);
	// List<SpotCheckSchedule>
	// scheduleList=spotCheckScheduleServiceImpl.queryList(scheduleRequest);
	// if(scheduleList!=null && scheduleList.size()>0){
	//
	// SpotCheckSchedule schedule=scheduleList.get(0);
	// // 向抽查任务结果表中添加抽查的所有siteCode(10位)的信息
	// for (int i = 0; i < siteList.size(); i++) {
	// DatabaseInfo databaseInfo =
	// databaseInfoServiceImpl.findByDatabaseInfoCode(siteList.get(i).getSiteCode());
	// if(databaseInfo!=null){
	// SpotCheckResult spotCheckResult = new SpotCheckResult();
	// spotCheckResult.setSiteCode(siteList.get(i).getSiteCode());
	// spotCheckResult.setCheckResult(2);//检查结果（1：合格，0：单项否决,2:未检查）
	// spotCheckResult.setCity(databaseInfo.getCity());
	// spotCheckResult.setCounty(databaseInfo.getCounty());
	// spotCheckResult.setProvince(databaseInfo.getProvince());
	// spotCheckResult.setDirector(databaseInfo.getDirector());
	// spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
	// spotCheckResult.setOrgSiteCode(siteCode);
	// if(databaseInfo.getJumpUrl() != null &&
	// !"".equals(databaseInfo.getJumpUrl())){
	// spotCheckResult.setUrl(databaseInfo.getJumpUrl());
	// }else{
	// spotCheckResult.setUrl(databaseInfo.getUrl());
	// }
	// spotCheckResult.setSpotCheckSchedule(schedule.getId());//抽查进度表id
	// spotCheckResult.setState(-1);//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
	// spotCheckResult.setSiteName(databaseInfo.getName());
	// spotCheckResult.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResult.setModifyTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResultServiceImpl.add(spotCheckResult);
	// }
	// }
	//
	// /**
	// * 创建服务周期
	// */
	// ServicePeriod sPeriod = new ServicePeriod();
	// sPeriod.setComboId(4);// 高级版
	// sPeriod.setContractInfoId(crmlist.get(0).getId());// 合同id
	// sPeriod.setStartDate(DateUtils.parseStandardDate(dateStart));// 服务开始时间
	// sPeriod.setEndDate(DateUtils.parseStandardDate(dateEnd));// 服务结束时间
	// sPeriod.setSpotCheckScheduleId(schedule.getId());// 抽查进度表id
	// sPeriod.setStatus(0);// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
	// sPeriod.setServicePeriodNum(crmlist.get(0).getProductCode() + groupNum +
	// batchNum);// 周期任务号
	// sPeriod.setSiteCode(siteCode);// 当前登录人的组织单位编码
	// servicePeriodServiceImpl.add(sPeriod);
	// }
	// resultMap.put("success", "success");
	// }else{
	// resultMap.put("errorMsg", "已经存在该批次数据");
	// }
	//
	// }else{//点击其他页面中的新增站点按钮--进行站点添加
	// int webSum = 0;// 网站数量
	// /**
	// * 遍历新增站点数组，查询抽查结果表，判断新增的站点是否已经存在，
	// * 如果存在，执行下一次循环；
	// * 如果不存在，统计个数+1， 抽查服务表中已抽查数-1（更新缓存数据），抽查进度表中站点数+1，抽查结果表中添加一条记录
	// */
	// for(int i=0;i<siteList.size();i++){
	// //根据组织单位编码、网站标识码、抽查进度id,查询抽查结果表
	// SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
	// resultRequest.setOrgSiteCode(siteCode);
	// resultRequest.setSiteCode(siteList.get(i).getSiteCode());
	// resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
	// resultRequest.setPageSize(Integer.MAX_VALUE);
	//
	// List<SpotCheckResult>
	// spotResultList=spotCheckResultServiceImpl.queryList(resultRequest);
	// if(spotResultList==null ||(spotResultList!=null &&
	// spotResultList.size()==0) ){
	// webSum+=1;
	// DatabaseInfo databaseInfo =
	// databaseInfoServiceImpl.findByDatabaseInfoCode(siteList.get(i).getSiteCode());
	// if(databaseInfo!=null){
	// SpotCheckResult spotCheckResult = new SpotCheckResult();
	// spotCheckResult.setSiteCode(siteList.get(i).getSiteCode());
	// spotCheckResult.setCheckResult(2);//检查结果（1：合格，0：单项否决,2:未检查）
	// spotCheckResult.setCity(databaseInfo.getCity());
	// spotCheckResult.setCounty(databaseInfo.getCounty());
	// spotCheckResult.setProvince(databaseInfo.getProvince());
	// spotCheckResult.setDirector(databaseInfo.getDirector());
	// spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
	// spotCheckResult.setOrgSiteCode(siteCode);
	// if(databaseInfo.getJumpUrl() != null &&
	// !"".equals(databaseInfo.getJumpUrl())){
	// spotCheckResult.setUrl(databaseInfo.getJumpUrl());
	// }else{
	// spotCheckResult.setUrl(databaseInfo.getUrl());
	// }
	// spotCheckResult.setSpotCheckSchedule(Integer.valueOf(scheduleId));
	// spotCheckResult.setState(-1);//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
	// spotCheckResult.setSiteName(databaseInfo.getName());
	// spotCheckResult.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResult.setModifyTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResultServiceImpl.add(spotCheckResult);
	// }
	// }
	// }
	// if(webSum>0){
	// // 修改抽查服务统计表中的已抽查数和抽查总数
	// SpotCheckInfoRequest infoRequest=new SpotCheckInfoRequest();
	// infoRequest.setSiteCode(siteCode);
	// infoRequest.setContractInfoId(crmlist.get(0).getId());
	// List<SpotCheckInfo>
	// spotInfoList=spotCheckInfoServiceImpl.queryList(infoRequest);
	// if (spotInfoList!= null && spotInfoList.size()>0) {
	// SpotCheckInfo spotCheckInfo=spotInfoList.get(0);
	// spotCheckInfo.setSpotNum(spotCheckInfo.getSpotNum() + webSum);
	// spotCheckInfo.setModifyTime(new Date());
	// spotCheckInfoServiceImpl.update(spotCheckInfo);
	// // 修改session
	// ShiroUser shiroUser = (ShiroUser)
	// getSession().getAttribute(Constants.SHIRO_USER);
	// shiroUser.setSportCount(spotCheckInfo.getSpotNum() + "/" +
	// spotCheckInfo.getSpotSum());
	// removeSession(Constants.SHIRO_USER);
	// add2Session(Constants.SHIRO_USER, shiroUser);
	// }
	//
	// }
	// resultMap.put("success", "success");
	// }
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "免费版不存在考评");
	// }else{
	// resultMap.put("errorMsg", "免费版不存在抽查");
	// }
	// }
	// }else{
	// resultMap.put("errorMsg", "获取页面参数为空");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "保存随机考评站点数据异常");
	// }else{
	// resultMap.put("errorMsg", "保存随机抽查站点数据异常");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	// /**
	// * @Description: 获取抽查进度表数据
	// * @author cuichx --- 2016-5-5下午3:44:36
	// */
	// public void getSpotSchedule(){
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	// String batchNum=request.getParameter("batchNum");//批次
	// String groupNum=request.getParameter("groupNum");//组次
	// String flagAll = request.getParameter("flagAll");//抽查进度id
	// //获取当前登录人的组织机构编码
	// site_code_session = getCurrentSiteCode();
	// String siteCode=getCurrentSiteCode();
	// try {
	//
	// String contractId = "";
	// if(flagAll != null && !"".equals(flagAll)){
	// if("".equals(contractId)){
	// SpotCheckSchedule spotCheckSchedule =
	// spotCheckScheduleServiceImpl.get(Integer.parseInt(flagAll));
	// contractId = spotCheckSchedule.getContractInfoId();
	// }
	// }else{
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// contractId = crmlist.get(0).getId() + "";
	// }
	// }
	//
	// //if(conList!=null && conList.size()>0){
	// if(!"".equals(contractId)){//
	// //根据组织机构编码、合同id、批次、组次,查询抽查进度表
	// SpotCheckScheduleRequest spotRequest=new SpotCheckScheduleRequest();
	// spotRequest.setSiteCode(siteCode);
	// //spotRequest.setContractInfoId(conList.get(0).getId()+"");
	// spotRequest.setContractInfoId(contractId);
	// spotRequest.setBatchNum(Integer.valueOf(batchNum));
	// spotRequest.setGroupNum(Integer.valueOf(groupNum));
	//
	// List<SpotCheckSchedule>
	// spotScheduleList=spotCheckScheduleServiceImpl.queryList(spotRequest);
	// if(spotScheduleList!=null && spotScheduleList.size()>0){
	// SpotCheckSchedule schedule=spotScheduleList.get(0);
	// resultMap.put("scheduleId", schedule.getId());//抽查进度表id
	// resultMap.put("batchNum", batchNum);
	// resultMap.put("groupNum", groupNum);
	// resultMap.put("spotWebsiteNum", schedule.getSpotWebsiteNum());//站点总数
	// resultMap.put("status", schedule.getStatus());//状态（0：未启动，1：检查中，2：检查完成）
	// resultMap.put("taskName", schedule.getTaskName());//任务名称
	// resultMap.put("startDate", schedule.getStartDate());//监测开始时间（yyyy-mm-dd）
	// resultMap.put("endDate", schedule.getEndDate());//监测结束时间(yyyy-mm-dd)
	//
	//
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "不存在要查询的考评进度数据");
	// }else{
	// resultMap.put("errorMsg", "不存在要查询的抽查进度数据");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }else{
	// resultMap.put("errorMsg", "不存在该组织单位的合同信息或合同已经过期");
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// } catch (Exception e) {
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "不存在要查询的考评进度数据");
	// }else{
	// resultMap.put("errorMsg", "不存在要查询的抽查进度数据");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	// /**
	// * @Description: 全部启动抽查任务
	// * @author cuichx --- 2016-5-5下午9:24:16
	// */
	// public void openAllCheckResult(){
	// site_code_session = getCurrentSiteCode();
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	//
	// String scheduleId=request.getParameter("scheduleId");//抽查进度表id
	// String batchNum=request.getParameter("batchNum");//批次
	// String groupNum=request.getParameter("groupNum");//组次
	// String orgSiteCode=getCurrentSiteCode();//组织单位编码
	//
	// try {
	// SpotCheckSchedule scSchedule=null;
	// if(StringUtils.isNotEmpty(scheduleId)){
	// scSchedule=spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
	// }else if(StringUtils.isNotEmpty(batchNum) &&
	// StringUtils.isNotEmpty(groupNum)){
	// List<CrmProductsResponse> crmlist = getCrmProductsList(orgSiteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// //通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
	// SpotCheckScheduleRequest scheduleRequest=new SpotCheckScheduleRequest();
	// scheduleRequest.setSiteCode(orgSiteCode);
	// scheduleRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// scheduleRequest.setBatchNum(Integer.valueOf(batchNum));
	// scheduleRequest.setGroupNum(Integer.valueOf(groupNum));
	// List<SpotCheckSchedule>
	// scheduleList=spotCheckScheduleServiceImpl.queryList(scheduleRequest);
	// if(scheduleList!=null && scheduleList.size()>0){
	// scSchedule= scheduleList.get(0);
	// }
	// }
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评进度id为空");
	// }else{
	// resultMap.put("errorMsg", "抽查进度id为空");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// return;
	// }
	//
	// //将抽查进度表中的状态修改为1-检查中
	// if(scSchedule!=null){
	// scSchedule.setStatus(SpotScheduleStateType.IN_CHECK.getCode());//状态（0：未启动，1：检查中，2：检查完成）
	// spotCheckScheduleServiceImpl.update(scSchedule);
	//
	// //修改抽查次数
	// updateSpotCheckInfo(scSchedule.getSiteCode(),
	// scSchedule.getContractInfoId(), scSchedule.getSpotWebsiteNum());
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评结束，不能启动考评");
	// }else{
	// resultMap.put("errorMsg", "抽查结束，不能启动抽查");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// return;
	// }
	// //根据组织机构编码和抽查进度表id,查询抽查结果表
	// SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
	// resultRequest.setOrgSiteCode(orgSiteCode);
	// resultRequest.setSpotCheckSchedule(scSchedule.getId());
	// resultRequest.setPageSize(Integer.MAX_VALUE);
	//
	// List<SpotCheckResult>
	// resultList=spotCheckResultServiceImpl.queryList(resultRequest);
	// if(resultList!=null && resultList.size()>0){
	// for (SpotCheckResult spotCheckResult : resultList) {
	// spotCheckResult.setState(SpotResultStateType.IN_CHECK.getCode());//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
	// ，3：报告完成）
	// //更新抽查任务表数据
	// spotCheckResultServiceImpl.update(spotCheckResult);
	// }
	// /**
	// * 根据抽查合同号，查询合同信息表，获取合同信息,再根据合同信息表id,查询服务周期表，
	// * 将查询出来的服务周期表数据的状态更新为1-服务中（服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务）
	// */
	// //通过抽查进度表id,查询服务周期表，将获取的周期状态修改1-服务中（服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务）
	// ServicePeriodRequest spRequest=new ServicePeriodRequest();
	// spRequest.setSpotCheckScheduleId(scSchedule.getId());
	// spRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
	// List<ServicePeriod> spList=servicePeriodServiceImpl.queryList(spRequest);
	// if(spList!=null && spList.size()>0){
	// ServicePeriod sp=spList.get(0);
	// //获取抽查合同id
	// int ctInfoId=sp.getContractInfoId();
	// //根据抽查合同id查询合同信息表
	// CrmProducts crm = crmProductsServiceImpl.get(ctInfoId);
	// if (crm != null) {
	// //将抽查合同执行状态修改为0-初始化（作废：-1，初始化：0，服务中：1，服务结束：2）
	// crm.setExecuteStatus(1);
	// crmProductsServiceImpl.update(crm);
	// }
	//
	// }
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("success", "启动考评任务成功");
	// }else{
	// resultMap.put("success", "启动抽查任务成功");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "不存在考评站点");
	// }else{
	// resultMap.put("errorMsg", "不存在抽查站点");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评任务添加异常");
	// }else{
	// resultMap.put("errorMsg", "抽查任务添加异常");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	// /**
	// * @Description: 按批次删除抽查任务
	// * @author Na.Y --- 2016-9-14下午4:24:16
	// */
	// public void deleteSpotCheckSchedule(){
	// site_code_session = getCurrentSiteCode();
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	// String scheduleIdStr=request.getParameter("scheduleId");//抽查进度表id
	//
	// if(StringUtils.isEmpty(scheduleIdStr)){
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评进度id为空");
	// }else{
	// resultMap.put("errorMsg", "抽查进度id为空");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// return;
	// }
	//
	// try {
	//
	// //抽查进度表Id
	// int scheduleId = Integer.valueOf(scheduleIdStr);
	//
	// //1.删除抽查进度表:物理删除
	// spotCheckScheduleServiceImpl.delete(scheduleId);
	//
	// //3.删除抽查服务周期表：标记删除
	// ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
	// servicePeriodRequest.setSpotCheckScheduleId(scheduleId);
	// List<ServicePeriod> listServicePeroid =
	// servicePeriodServiceImpl.queryList(servicePeriodRequest);
	// if(!CollectionUtils.isEmpty(listServicePeroid)){
	// ServicePeriod servicePeriod = listServicePeroid.get(0);
	// servicePeriod.setStatus(-1);
	// servicePeriodServiceImpl.update(servicePeriod);
	// }
	//
	// //4.删除抽查结果表:物理删除
	// spotCheckResultServiceImpl.deleteBatchBySchedule(scheduleId);
	//
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("success", "删除考评任务成功");
	// }else{
	// resultMap.put("success", "删除抽查任务成功");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }catch (Exception e) {
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评任务删除异常");
	// }else{
	// resultMap.put("errorMsg", "抽查任务删除异常");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	//
	//
	// }
	// /**
	// * @Description: 数据提交页面--点击删除 删除单条记录
	// * @author cuichx --- 2016-5-4下午9:55:09
	// */
	// public void deleteSpotResult(){
	// site_code_session = getCurrentSiteCode();
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	//
	// String siteCode=request.getParameter("siteCode");//网站标识码
	// String scheduleId=request.getParameter("scheduleId");//抽查进度表id
	// String orgSiteCode=getCurrentUserInfo().getSiteCode();//当前登录人的编码
	//
	// try {
	// //根据组织单位编码、省份、抽查进度id,查询抽查结果表
	// SpotCheckResultRequest spotRequest=new SpotCheckResultRequest();
	// if(StringUtils.isNotEmpty(siteCode)){
	// spotRequest.setSiteCode(siteCode);
	// }
	// if(StringUtils.isNotEmpty(scheduleId)){
	// spotRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
	// }
	// spotRequest.setOrgSiteCode(orgSiteCode);
	// spotRequest.setPageSize(Integer.MAX_VALUE);
	// /**
	// * 如果抽查结果表中存在该记录，删除该记录，同时，更新抽查进度表中站点个数、更新抽查服务统计表中已抽查数
	// * 如果抽查结果表中不存在该记录，给出提示信息
	// */
	// List<SpotCheckResult>
	// spotList=spotCheckResultServiceImpl.queryList(spotRequest);
	// if(spotList!=null && spotList.size()>0){
	// SpotCheckResult spotResult=spotList.get(0);
	// int spotResultState=spotResult.getState();
	// spotCheckResultServiceImpl.delete(spotResult.getId());
	//
	// SpotCheckSchedule
	// schedule=spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
	// if(schedule!=null){
	// //未提交删除，不需要扣除批次抽查数量
	// if(spotResultState!=-1){
	// schedule.setSpotWebsiteNum(schedule.getSpotWebsiteNum()-1);//站点数减1
	// }
	//
	// schedule.setModifyTime(DateUtils.parseStandardDateTime(DateUtils.formatStandardDateTime(new
	// Date())));//修改时间
	// spotCheckScheduleServiceImpl.update(schedule);
	// }
	// List<CrmProductsResponse> crmlist = getCrmProductsList(orgSiteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// int spotWebsiteNum=0;
	//
	// Map<String, Object> params=new HashMap<String, Object>();
	// params.put("orgSiteCode", orgSiteCode);
	// params.put("spotCheckSchedule",scheduleId);
	// params.put("state", -1);
	//
	// //封装页面左侧菜单统计数据
	// List<Map<String, Object>> siteNumList=new
	// ArrayList<Map<String,Object>>();
	// List<SpotCheckResultRequest> stList=
	// spotCheckResultServiceImpl.queryByMap(params);
	// if(stList!=null && stList.size()>0){
	// for(int i=0;i<stList.size();i++){
	// Map<String, Object> map=new HashMap<String, Object>();
	// SpotCheckResultRequest spotResultRequest=stList.get(i);
	// map.put("province", spotResultRequest.getProvince());//省名称
	// map.put("siteNum", spotResultRequest.getSiteNum());//站点个数
	//
	// siteNumList.add(map);
	// spotWebsiteNum+=spotResultRequest.getSiteNum();
	// }
	// resultMap.put("siteNumList", siteNumList);
	// }
	//
	// resultMap.put("spotWebsiteNum", spotWebsiteNum);//用于抽查提交页面中的抽查个数赋值
	// }
	//
	//
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("success", "考评结果删除成功");
	// }else{
	// resultMap.put("success", "抽查结果删除成功");
	// }
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评结果表中不存在该记录");
	// }else{
	// resultMap.put("errorMsg", "抽查结果表中不存在该记录");
	// }
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "删除考评结果异常");
	// }else{
	// resultMap.put("errorMsg", "删除抽查结果异常");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	// /**
	// * @Description: 增加站点时，页面数据初始化
	// * @author cuichx --- 2016-5-4下午2:27:13
	// */
	// public void getSpotCheckSchedule(){
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	// String scheduleId=request.getParameter("scheduleId");//抽查进度表id
	// String groupNum=request.getParameter("groupNum");//抽查组次
	// String batchNum=request.getParameter("batchNum");//抽查批次
	// site_code_session = getCurrentSiteCode();
	// try {
	// if(StringUtils.isNotEmpty(scheduleId)){//抽查提交页面--点击增加站点 或者
	// 站点详情页面--点击增加站点-初始化抽查页面新建任务modal
	// SpotCheckSchedule
	// spotSchedule=spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
	// if(spotSchedule!=null){
	// //批次
	// resultMap.put("batchNum", spotSchedule.getBatchNum());
	// //组次
	// resultMap.put("groupNum", spotSchedule.getGroupNum());
	// //任务名称
	// resultMap.put("taskName",spotSchedule.getTaskName());
	// //开始日期
	// resultMap.put("startDate", spotSchedule.getStartDate());
	// //结束日期
	// resultMap.put("endDate", spotSchedule.getEndDate());
	// //抽查进度表id
	// resultMap.put("scheduleId", spotSchedule.getId());
	//
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }else if(StringUtils.isNotEmpty(batchNum) &&
	// StringUtils.isNotEmpty(groupNum)){//首页面点击添加站点
	// String siteCode=getCurrentUserInfo().getSiteCode();//组织单位编码（当前登录人编码）
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	//
	// SpotCheckScheduleRequest spotScheduleRequest=new
	// SpotCheckScheduleRequest();
	// spotScheduleRequest.setSiteCode(siteCode);
	// spotScheduleRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// spotScheduleRequest.setBatchNum(Integer.valueOf(batchNum));
	// spotScheduleRequest.setGroupNum(Integer.valueOf(groupNum));
	//
	// List<SpotCheckSchedule>
	// spotScheduleList=spotCheckScheduleServiceImpl.queryList(spotScheduleRequest);
	// if(spotScheduleList!=null && spotScheduleList.size()>0){
	// SpotCheckSchedule spotSchedule=spotScheduleList.get(0);
	// //批次
	// resultMap.put("batchNum", batchNum);
	// //组次
	// resultMap.put("groupNum", groupNum);
	// //任务名称
	// resultMap.put("taskName",spotSchedule.getTaskName());
	// //开始日期
	// resultMap.put("startDate", spotSchedule.getStartDate());
	// //结束日期
	// resultMap.put("endDate",spotSchedule.getEndDate());
	//
	// //抽查进度表id
	// resultMap.put("scheduleId", spotSchedule.getId());
	//
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评结束，不能再次新增站点");
	// }else{
	// resultMap.put("errorMsg", "抽查结束，不能再次新增站点");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	//
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "不存在考评进度表数据");
	// }else{
	// resultMap.put("errorMsg", "不存在抽查进度表数据");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "获取考评进度表数据异常");
	// }else{
	// resultMap.put("errorMsg", "获取抽查进度表数据异常");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	// /**
	// * @Description: 抽查提交页面数据初始化
	// * @author cuichx --- 2016-5-3下午3:42:51
	// */
	// public void spotSubInit(){
	// /**
	// * 1 如果页面传递过来的抽查进度表id不为空，直接通过抽查进度表，查询抽查进度表
	// * 2 如果页面传递抽查进度表id为空，
	// * 根据当前登录人,当前时间查询合同信息表，获取合同信息表id
	// * 根据当前登录人组织机构编码、合同信息表id、批次、组次查询抽查进度表
	// * 3 将查询结果返回页面
	// */
	// site_code_session = getCurrentSiteCode();
	// Map<String, Object> resultMap=new HashMap<String, Object>();
	// try {
	// JSONObject jsonObj=getJSONObject();
	// if(jsonObj!=null){
	// String batchNum=jsonObj.getString("batchNum");//抽查批次
	// String groupNum=jsonObj.getString("groupNum");//抽查组次
	// String scheduleId=jsonObj.getString("scheduleId");//抽查进度表id
	// String orgSiteCode=getCurrentUserInfo().getSiteCode();//获取当前登录人的组织单位编码
	//
	// SpotCheckSchedule spotCheckSchedule=null;
	// if(StringUtils.isNotEmpty(scheduleId)){
	// spotCheckSchedule=spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
	// }else{
	// List<CrmProductsResponse> crmlist = getCrmProductsList(orgSiteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// SpotCheckScheduleRequest spotRequest=new SpotCheckScheduleRequest();
	// spotRequest.setSiteCode(orgSiteCode);
	// spotRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// spotRequest.setBatchNum(Integer.valueOf(batchNum));
	// spotRequest.setGroupNum(Integer.valueOf(groupNum));
	// List<SpotCheckSchedule>
	// spotList=spotCheckScheduleServiceImpl.queryList(spotRequest);
	// if(spotList!=null && spotList.size()>0){
	// spotCheckSchedule=spotList.get(0);
	// }
	// }
	// }
	//
	// if(spotCheckSchedule!=null){
	// resultMap.put("batchNum", spotCheckSchedule.getBatchNum());//批次
	// resultMap.put("groupNum", spotCheckSchedule.getGroupNum());//组次
	// resultMap.put("taskName", spotCheckSchedule.getTaskName());//任务名称
	//
	// resultMap.put("startDate",
	// DateUtils.formatStandardDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));//监测开始日期
	// resultMap.put("endDate",
	// DateUtils.formatStandardDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));//监测结束日期
	// resultMap.put("spotWebsiteNum",
	// spotCheckSchedule.getSpotWebsiteNum());//站点个数
	//
	// resultMap.put("scheduleId", spotCheckSchedule.getId());
	//
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评进度表中不存在该记录");
	// }else{
	// resultMap.put("errorMsg", "抽查进度表中不存在该记录");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// return;
	// }
	//
	// /**
	// * 列表数据初始化
	// * 1 首先根据省分组查询（组织单位编码 、抽查进度表id、状态（-1:未提交）），获取每个省的站点个数--用于页面左侧菜单初始化
	// * 2 根据 组织单位编码 、抽查进度表id、状态（-1:未提交）查询抽查任务结果表，获取所有数据展示在页面
	// */
	// Map<String, Object> params=new HashMap<String, Object>();
	// params.put("spotCheckSchedule", spotCheckSchedule.getId());
	// params.put("state", -1);
	//
	// //封装页面左侧菜单统计数据
	// List<Map<String, Object>> siteNumList=new
	// ArrayList<Map<String,Object>>();
	// List<SpotCheckResultRequest> spotList=
	// spotCheckResultServiceImpl.queryByMap(params);
	// if(spotList!=null && spotList.size()>0){
	// for(int i=0;i<spotList.size();i++){
	// Map<String, Object> map=new HashMap<String, Object>();
	// SpotCheckResultRequest spotResultRequest=spotList.get(i);
	// map.put("province", spotResultRequest.getProvince());//省名称
	// map.put("siteNum", spotResultRequest.getSiteNum());//站点个数
	//
	// siteNumList.add(map);
	// }
	// resultMap.put("siteNumList", siteNumList);
	// }
	//
	// SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
	// resultRequest.setSpotCheckSchedule(spotCheckSchedule.getId());//抽查进度表id
	// resultRequest.setState(-1);//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
	// resultRequest.setPageSize(Integer.MAX_VALUE);
	// //封装页面列表数据
	// List<Map<String, Object>> returnList=new ArrayList<Map<String,
	// Object>>();
	// List<SpotCheckResult>
	// resultList=spotCheckResultServiceImpl.queryList(resultRequest);
	//
	// resultMap.put("spotWebsiteNum", resultList.size());//此处抽查站点个数
	// if(resultList!=null && resultList.size()>0){
	// for(int i=0;i<resultList.size();i++){
	// Map<String, Object> map=new HashMap<String, Object>();
	// SpotCheckResult result=resultList.get(i);
	// String city="";//市
	// if(StringUtils.isNotEmpty(result.getCity())){
	// city=result.getCity();
	// }
	// map.put("city", city);
	//
	// String county="";//县
	// if(StringUtils.isNotEmpty(result.getCounty())){
	// county=result.getCounty();
	// }
	// map.put("county", county);
	//
	// String siteCode="";//网站标识码
	// if(StringUtils.isNotEmpty(result.getSiteCode())){
	// siteCode=result.getSiteCode();
	// }
	// map.put("siteCode", siteCode);
	//
	// String siteName="";//网站名称
	// if(StringUtils.isNotEmpty(result.getSiteName())){
	// siteName=result.getSiteName();
	// }
	// map.put("siteName", siteName);
	//
	// String url="";//url
	// if(StringUtils.isNotEmpty(result.getUrl())){
	// url=result.getUrl();
	// }
	// map.put("url", url);
	//
	// //通过网站标识码查询站点信息表，获取主办单位名称
	// DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
	// dataRequest.setSiteCode(siteCode);
	// String director="";//主办单位
	// List<DatabaseInfo>
	// dataList=databaseInfoServiceImpl.queryList(dataRequest);
	// if(dataList!=null && dataList.size()>0){
	// director=dataList.get(0).getDirector();
	// }
	// map.put("director", director);
	//
	// returnList.add(map);
	// }
	// resultMap.put("returnList", returnList);
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "不存在考评结果数据");
	// }else{
	// resultMap.put("errorMsg", "不存在抽查结果数据");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }else{
	// resultMap.put("errorMsg", "页面传递参数不能为空");
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "考评结果提交页面初始化异常");
	// }else{
	// resultMap.put("errorMsg", "抽查结果提交页面初始化异常");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }
	// /**
	// * @Description: 获取抽查批次
	// * @author cuichx --- 2016-4-28下午7:55:28
	// */
	// public void getSchedule() {
	// site_code_session = getCurrentSiteCode();
	// //封装返回数据
	// HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// try {
	// String key="";
	// String datePd="";
	// JSONObject json=getJSONObject();
	// if(json!=null ){
	// key=json.getString("key");//关键字
	// datePd=json.getString("datePd");//创建时间
	// }
	//
	// logger.info("getSchedule key:"+key+" datePd:"+datePd);
	// String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
	// int spotSum = 0;//抽查总数
	// int spotNum = 0;//已抽查次数
	// int remainNum = 0;//剩余可抽查次数
	// List<Map<String, Object>> returnList = new ArrayList<Map<String,
	// Object>>();
	// //封装批次集合 主要用于前台区分批次和组的关系
	// List<Integer> batchNumList=new ArrayList<Integer>();
	// List<CrmProductsResponse> currentCrmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr, null, null, null);
	//
	// logger.info("conList size:" + currentCrmlist.size() + " siteCode:" +
	// siteCode + "new Date()"
	// + DateUtils.formatStandardDateTime(new Date()));
	// if (crmlist != null && crmlist.size() > 0) {
	// if (CollectionUtils.isNotEmpty(currentCrmlist)) {
	// CrmProductsResponse crm = currentCrmlist.get(0);
	// if (StringUtils.isNotEmpty(crm.getProductCode())) {
	// //当前服务中的合同号
	// resultMap.put("curredContractCode", crm.getProductCode());
	// }
	//
	// SpotCheckInfoRequest spotCheckInfoRequest = new SpotCheckInfoRequest();
	// spotCheckInfoRequest.setSiteCode(siteCode);
	// spotCheckInfoRequest.setContractInfoId(currentCrmlist.get(0).getId());
	// logger.info("conList id:" + currentCrmlist.get(0).getId());
	// // 抽查服务统计表
	// List<SpotCheckInfo> queryInfo =
	// spotCheckInfoServiceImpl.queryList(spotCheckInfoRequest);
	// for (SpotCheckInfo spotCheckInfo : queryInfo) {
	// spotSum = spotCheckInfo.getSpotSum();
	// spotNum= spotCheckInfo.getSpotNum();
	// }
	// }else{
	// spotSum = 0;
	// spotNum= 0;
	// }
	// //查询抽查进度表
	// SpotCheckScheduleRequest spotCheckScheduleRequest = new
	// SpotCheckScheduleRequest();
	// spotCheckScheduleRequest.setPageSize(Integer.MAX_VALUE);
	// spotCheckScheduleRequest.setSiteCode(siteCode);
	// if(StringUtils.isNotEmpty(key)){
	// spotCheckScheduleRequest.setTaskName(key);
	// }
	// if(StringUtils.isNotEmpty(datePd)){
	// spotCheckScheduleRequest.setEndTime(DateUtils.formatStandardDateTime(new
	// Date()));
	// spotCheckScheduleRequest.setBeginTime(DateUtils.getNextDay(new Date(),
	// -Integer.valueOf(datePd)));
	// }
	// //设置排序 先按批次排序 再按组次排序
	// List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
	// QueryOrder siteQueryOrder1=new
	// QueryOrder("batch_num",QueryOrderType.DESC);
	// QueryOrder siteQueryOrder2=new
	// QueryOrder("group_num",QueryOrderType.DESC);
	// queryOrderList.add(siteQueryOrder1);
	// queryOrderList.add(siteQueryOrder2);
	// spotCheckScheduleRequest.setQueryOrderList(queryOrderList);
	//
	// List<SpotCheckSchedule> spotCheckList =
	// spotCheckScheduleServiceImpl.queryList(spotCheckScheduleRequest);
	// if(spotCheckList!=null && spotCheckList.size()>0){
	//
	// for (int i = 0; i < spotCheckList.size(); i++) {
	// SpotCheckSchedule spotCheckSchedule = spotCheckList.get(i);
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// map.put("scheduleId", spotCheckSchedule.getId());//抽查进度表id
	// map.put("siteCode", spotCheckSchedule.getSiteCode());//网站标识码
	// //任务名称
	// if(StringUtils.isNotBlank(spotCheckSchedule.getTaskName())){
	// map.put("taskName", spotCheckSchedule.getTaskName());
	// }else{
	// map.put("taskName", "");
	// }
	//
	// if(StringUtils.isNotEmpty(spotCheckSchedule.getContractInfoId())){
	// CrmProducts crm = crmProductsServiceImpl
	// .get(Integer.valueOf(spotCheckSchedule.getContractInfoId()));
	// if (crm != null) {
	// if (StringUtils.isNotEmpty(crm.getProductCode())) {
	// // 正式合同号
	// map.put("contractCode", crm.getProductCode());
	// }
	// }
	// }
	//
	//
	// //监测开始日期
	// map.put("dateStart",
	// DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));
	// //监测结束日期
	// map.put("endStart",
	// DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));
	// //监测状态
	// map.put("state", spotCheckSchedule.getStatus());
	// map.put("batchNum", spotCheckSchedule.getBatchNum());
	// map.put("groupNum", spotCheckSchedule.getGroupNum());
	// map.put("webSum", spotCheckSchedule.getSpotWebsiteNum());
	// //查询服务周期id
	// ServicePeriodRequest servicePeriodRequest=new ServicePeriodRequest();
	// servicePeriodRequest.setSpotCheckScheduleId( spotCheckSchedule.getId());
	// servicePeriodRequest.setSiteCode(siteCode);
	// List<ServicePeriod>
	// servicePeriodList=servicePeriodServiceImpl.queryList(servicePeriodRequest);
	// if(servicePeriodList.size()>0){
	// Integer servicePeriodId=servicePeriodList.get(0).getId();
	//
	// //完成报告数量
	// ReportWordResultRequest reportWordResultRequest=new
	// ReportWordResultRequest();
	// reportWordResultRequest.setServicePeriodId(servicePeriodId);
	// reportWordResultRequest.setPageNo(0);
	// reportWordResultRequest.setPageSize(Integer.MAX_VALUE);
	// reportWordResultRequest.setGroupBy("site_code");
	//
	//
	// List<ReportWordResult>
	// wordList=reportWordResultServiceImpl.queryList(reportWordResultRequest);
	// Integer successReportWordNum=wordList.size();
	// map.put("successReportWordNum", successReportWordNum);
	//
	// //反馈整改数量
	// SpotCheckNoticeRequest spotCheckNoticeRequest=new
	// SpotCheckNoticeRequest();
	// spotCheckNoticeRequest.setServicePeriodId(servicePeriodId);
	// spotCheckNoticeRequest.setCheckReportResults(0);
	// spotCheckNoticeRequest.setPageNo(0);
	// spotCheckNoticeRequest.setPageSize(Integer.MAX_VALUE);
	// spotCheckNoticeRequest.setGroupBy("site_code");
	// List<SpotCheckNotice>
	// SpotCheckNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
	// map.put("checkReportResultNum", SpotCheckNoticeList.size());
	// spotCheckNoticeRequest.setCheckReportResults(null);
	// spotCheckNoticeRequest.setIsRead(1);
	// List<SpotCheckNotice>
	// IsReadNoticeList=spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
	// map.put("IsReadNoticeNum", IsReadNoticeList.size());
	// }else{
	// map.put("successReportWordNum", 0);
	// map.put("checkReportResultNum", 0);
	// map.put("IsReadNoticeNum", 0);
	// }
	//
	// //循环遍历批次集合，如果批次集合中存在该批次，不添加；否则将批次添加到集合里面
	// if(batchNumList!=null && batchNumList.size()>0){
	// //获取批次
	// int batchNum=spotCheckSchedule.getBatchNum();
	// boolean existFlag=false;
	// for(int j=0;j<batchNumList.size();j++){
	// if(batchNum==batchNumList.get(j)){
	// existFlag=true;
	// }
	// }
	// //批次集合中不存在该数据，将该批次添加到批次集合中
	// if(!existFlag){
	// batchNumList.add(spotCheckSchedule.getBatchNum());
	// }
	// }else{
	// batchNumList.add(spotCheckSchedule.getBatchNum());
	// }
	// returnList.add(map);
	// }
	// }
	// }
	//
	// //add by Na.Y 20160914,剩余可抽查次数
	// remainNum = spotSum-spotNum;
	// if(remainNum<0){
	// remainNum=0;
	// }
	// resultMap.put("spotSum", spotSum);
	// resultMap.put("spotNum", spotNum);
	// resultMap.put("remainNum", remainNum);
	// resultMap.put("returnList", returnList);
	// resultMap.put("batchNumList", batchNumList);
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "获取考评数据异常");
	// }else{
	// resultMap.put("errorMsg", "获取抽查数据异常");
	// }
	// }
	// }
	// /**
	// * @Description: 复制任务站点，历史抽查任务为完成的数据
	// * @author: yangshuai --- 2016-5-18下午1:51:53
	// * @return
	// * @throws Exception
	// */
	// public void getCloseSchedule() {
	// site_code_session = getCurrentSiteCode();
	// //封装返回数据
	// HashMap<String, Object> resultMap = new HashMap<String, Object>();
	// try {
	// String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
	// List<Map<String, Object>> returnList = new ArrayList<Map<String,
	// Object>>();
	// //封装批次集合 主要用于前台区分批次和组的关系
	// List<Integer> batchNumList=new ArrayList<Integer>();
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr, null, null, null);
	//
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// //查询抽查进度表
	// SpotCheckScheduleRequest spotCheckScheduleRequest = new
	// SpotCheckScheduleRequest();
	// spotCheckScheduleRequest.setPageSize(Integer.MAX_VALUE);
	// spotCheckScheduleRequest.setSiteCode(siteCode);
	// //设置排序 先按批次排序 再按组次排序
	// List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
	// QueryOrder siteQueryOrder1=new
	// QueryOrder("batch_num",QueryOrderType.DESC);
	// QueryOrder siteQueryOrder2=new
	// QueryOrder("group_num",QueryOrderType.DESC);
	// queryOrderList.add(siteQueryOrder1);
	// queryOrderList.add(siteQueryOrder2);
	// spotCheckScheduleRequest.setQueryOrderList(queryOrderList);
	// spotCheckScheduleRequest.setStatus(2);
	//
	// List<SpotCheckSchedule> spotCheckList =
	// spotCheckScheduleServiceImpl.queryList(spotCheckScheduleRequest);
	// if(spotCheckList!=null && spotCheckList.size()>0){
	//
	// for (int i = 0; i < spotCheckList.size(); i++) {
	// SpotCheckSchedule spotCheckSchedule = spotCheckList.get(i);
	// SpotCheckResultRequest spotCheckResultRequest = new
	// SpotCheckResultRequest();
	// spotCheckResultRequest.setSpotCheckSchedule(spotCheckSchedule.getId());
	// spotCheckResultRequest.setPageSize(Integer.MAX_VALUE);
	// List<SpotCheckResult> spotCheckResulrList =
	// spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
	// String checkResultAllSiteCode = "";
	// String checkResultNotAllSiteCode = "";
	// for (SpotCheckResult spotCheckResult : spotCheckResulrList) {
	// if(spotCheckResult.getCheckResult()==0){
	// checkResultAllSiteCode+=spotCheckResult.getSiteCode()+"_";
	// checkResultNotAllSiteCode+=spotCheckResult.getSiteCode()+"_";
	// }else{
	// checkResultAllSiteCode+=spotCheckResult.getSiteCode()+"_";
	// }
	// }
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// map.put("scheduleId", spotCheckSchedule.getId());//抽查进度表id
	// map.put("siteCode", spotCheckSchedule.getSiteCode());//网站标识码
	// //任务名称
	// if(StringUtils.isNotBlank(spotCheckSchedule.getTaskName())){
	// map.put("taskName", spotCheckSchedule.getTaskName());
	// }else{
	// map.put("taskName", "");
	// }
	//
	// //监测开始日期
	// map.put("dateStart",
	// DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));
	// //监测结束日期
	// map.put("endStart",
	// DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));
	// //监测状态
	// map.put("state", spotCheckSchedule.getStatus());
	// map.put("batchNum", spotCheckSchedule.getBatchNum());
	// map.put("groupNum", spotCheckSchedule.getGroupNum());
	// map.put("webSum", spotCheckSchedule.getSpotWebsiteNum());
	// map.put("allSiteCode", checkResultAllSiteCode);
	// map.put("notAllSiteCode", checkResultNotAllSiteCode);
	// map.put("contractInfoId", crmlist.get(0).getId());
	// //循环遍历批次集合，如果批次集合中存在该批次，不添加；否则将批次添加到集合里面
	// if(batchNumList!=null && batchNumList.size()>0){
	// //获取批次
	// int batchNum=spotCheckSchedule.getBatchNum();
	// boolean existFlag=false;
	// for(int j=0;j<batchNumList.size();j++){
	// if(batchNum==batchNumList.get(j)){
	// existFlag=true;
	// }
	// }
	// //批次集合中不存在该数据，将该批次添加到批次集合中
	// if(!existFlag){
	// batchNumList.add(spotCheckSchedule.getBatchNum());
	// }
	// }else{
	// batchNumList.add(spotCheckSchedule.getBatchNum());
	// }
	// returnList.add(map);
	// }
	// }
	// }
	// resultMap.put("returnList", returnList);
	// resultMap.put("batchNumList", batchNumList);
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "获取考评数据异常");
	// }else{
	// resultMap.put("errorMsg", "获取抽查数据异常");
	// }
	// }
	// }
	// /**
	// * @Description: 新建任务的抽查结果/增加站点保存
	// * @author cuichx --- 2016-5-14下午2:14:11
	// */
	// public void newCheckResults() {
	// site_code_session = getCurrentSiteCode();
	// //封装返回数据
	// HashMap<String, Object> result = new HashMap<String, Object>();
	// // 获取当前登陆用户,依据前台页面选择的数据完成如下操作
	// // 1更新spot_check_info抽查服务统计表中的已抽查数量和抽查总数
	// // 2在spot_check_schedule抽查进度表新建一个批次关联到当前的组织单位下
	// // 3在spot_check_result抽查任务结果表新建对应的填报单位抽查结果记录
	// // 4在spot_task抽查任务表中新建一条任务，并标记为启动状态--展示不处理
	// String dateStart = request.getParameter("dateStart");//开始日期
	// String dateEnd = request.getParameter("dateEnd");//结束日期
	// String checkWeb = request.getParameter("checkWeb");// 选择的网站siteCode集合
	// Integer batchNum = Integer.parseInt(request.getParameter("batchNum"));//
	// 批次
	// Integer groupNum = Integer.parseInt(request.getParameter("groupNum"));//
	// 组次
	// Integer isorganizational =
	// Integer.parseInt(request.getParameter("isorganizational"));// 是非门户
	// String taskName = request.getParameter("taskName");//任务名称
	//
	// String scheduleId = request.getParameter("scheduleId");
	//
	// if (StringUtils.isNotEmpty(dateStart)) {
	// dateStart=DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateStart));
	// }
	// if (StringUtils.isNotEmpty(dateEnd)) {
	// dateEnd=DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateEnd));
	// }
	// try {
	// String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	//
	// // 当前抽查产品信息
	// Integer[] executeStatus = { 1 };
	// List<CrmProductsResponse> crmChlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), executeStatus, null);
	//
	// if (checkWeb != null) {
	// String[] siteCodes = checkWeb.split(",");
	//
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// if(StringUtils.isEmpty(scheduleId)){//第一次添加站点
	// //抽查进度表判断重复--处理多并发问题 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
	// SpotCheckScheduleRequest seRequest=new SpotCheckScheduleRequest();
	// seRequest.setSiteCode(siteCode);
	// seRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// seRequest.setBatchNum(batchNum);
	// seRequest.setGroupNum(groupNum);
	// List<SpotCheckSchedule>
	// seList=spotCheckScheduleServiceImpl.queryList(seRequest);
	// if(seList==null || (seList!=null && seList.size()==0)){
	// // 向抽查进度表添加新批次数据
	// SpotCheckSchedule spotCheckSchedule = new SpotCheckSchedule();
	// spotCheckSchedule.setContractInfoId(crmlist.get(0).getId() + "");
	// spotCheckSchedule.setSiteCode(siteCode);
	// spotCheckSchedule.setTaskName(taskName);
	// spotCheckSchedule.setStartDate(dateStart);
	// spotCheckSchedule.setEndDate(dateEnd);
	// //该批次抽查次数在提交时设置
	// spotCheckSchedule.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckSchedule.setStatus(0);
	// spotCheckSchedule.setBatchNum(batchNum);
	// spotCheckSchedule.setGroupNum(groupNum);
	// spotCheckSchedule.setIsorganizational(isorganizational);
	// spotCheckScheduleServiceImpl.add(spotCheckSchedule);
	//
	// //通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
	// SpotCheckScheduleRequest scheduleRequest=new SpotCheckScheduleRequest();
	// scheduleRequest.setSiteCode(siteCode);
	// scheduleRequest.setContractInfoId(crmlist.get(0).getId() + "");
	// scheduleRequest.setBatchNum(batchNum);
	// scheduleRequest.setGroupNum(groupNum);
	// List<SpotCheckSchedule>
	// scheduleList=spotCheckScheduleServiceImpl.queryList(scheduleRequest);
	// if(scheduleList!=null && scheduleList.size()>0){
	// SpotCheckSchedule schedule=scheduleList.get(0);
	// // 向抽查任务结果表中添加抽查的所有siteCode(10位)的信息
	// for (int i = 0; i < siteCodes.length; i++) {
	// DatabaseInfo databaseInfo =
	// databaseInfoServiceImpl.findByDatabaseInfoCode(siteCodes[i]);
	// if(databaseInfo!=null){
	// SpotCheckResult spotCheckResult = new SpotCheckResult();
	// spotCheckResult.setSiteCode(siteCodes[i]);
	// spotCheckResult.setCheckResult(2);//检查结果（1：合格，0：单项否决,2:未检查）
	// spotCheckResult.setCity(databaseInfo.getCity());
	// spotCheckResult.setCounty(databaseInfo.getCounty());
	// spotCheckResult.setProvince(databaseInfo.getProvince());
	// spotCheckResult.setDirector(databaseInfo.getDirector());
	// spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
	// spotCheckResult.setOrgSiteCode(siteCode);
	// if(databaseInfo.getJumpUrl() != null &&
	// !"".equals(databaseInfo.getJumpUrl())){
	// spotCheckResult.setUrl(databaseInfo.getJumpUrl());
	// }else{
	// spotCheckResult.setUrl(databaseInfo.getUrl());
	// }
	// spotCheckResult.setSpotCheckSchedule(schedule.getId());//抽查进度表id
	// spotCheckResult.setState(-1);//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
	// spotCheckResult.setSiteName(databaseInfo.getName());
	// spotCheckResult.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResult.setModifyTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResultServiceImpl.add(spotCheckResult);
	// }
	// }
	//
	// /**
	// * 创建服务周期
	// */
	// ServicePeriod sPeriod = new ServicePeriod();
	// sPeriod.setComboId(4);// 高级版
	// sPeriod.setContractInfoId(crmChlist.get(0).getId());// 合同id
	// sPeriod.setStartDate(DateUtils.parseStandardDate(dateStart));// 服务开始时间
	// sPeriod.setEndDate(DateUtils.parseStandardDate(dateEnd));// 服务结束时间
	// sPeriod.setSpotCheckScheduleId(schedule.getId());// 抽查进度表id
	// sPeriod.setStatus(0);// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
	// sPeriod.setServicePeriodNum(crmChlist.get(0).getProductCode() + groupNum
	// + batchNum);// 周期任务号
	// sPeriod.setSiteCode(siteCode);// 当前登录人的组织单位编码
	// servicePeriodServiceImpl.add(sPeriod);
	// }
	// result.put("body", "success");
	// writerPrint(JSONObject.fromObject(result).toString());
	// }else{
	// result.put("errorMsg", "已经存在该批次数据");
	// writerPrint(JSONObject.fromObject(result).toString());
	// return;
	// }
	// }else{//点击其他页面中的新增站点按钮--进行站点添加
	// /**
	// * 遍历新增站点数组，查询抽查结果表，判断新增的站点是否已经存在，
	// * 如果存在，执行下一次循环；
	// * 如果不存在，统计个数+1， 抽查服务表中已抽查数-1（更新缓存数据），抽查进度表中站点数+1，抽查结果表中添加一条记录
	// */
	// for(int i=0;i<siteCodes.length;i++){
	// //根据组织单位编码、网站标识码、抽查进度id,查询抽查结果表
	// SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
	// resultRequest.setOrgSiteCode(siteCode);
	// resultRequest.setSiteCode(siteCodes[i]);
	// resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
	//
	// List<SpotCheckResult>
	// spotResultList=spotCheckResultServiceImpl.queryList(resultRequest);
	// if(spotResultList==null ||(spotResultList!=null &&
	// spotResultList.size()==0) ){
	// DatabaseInfo databaseInfo =
	// databaseInfoServiceImpl.findByDatabaseInfoCode(siteCodes[i]);
	// if(databaseInfo!=null){
	// SpotCheckResult spotCheckResult = new SpotCheckResult();
	// spotCheckResult.setSiteCode(siteCodes[i]);
	// spotCheckResult.setCheckResult(2);//检查结果（1：合格，0：单项否决,2:未检查）
	// spotCheckResult.setCity(databaseInfo.getCity());
	// spotCheckResult.setCounty(databaseInfo.getCounty());
	// spotCheckResult.setProvince(databaseInfo.getProvince());
	// spotCheckResult.setDirector(databaseInfo.getDirector());
	// spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
	// spotCheckResult.setOrgSiteCode(siteCode);
	// if(databaseInfo.getJumpUrl() != null &&
	// !"".equals(databaseInfo.getJumpUrl())){
	// spotCheckResult.setUrl(databaseInfo.getJumpUrl());
	// }else{
	// spotCheckResult.setUrl(databaseInfo.getUrl());
	// }
	// spotCheckResult.setSpotCheckSchedule(Integer.valueOf(scheduleId));
	// spotCheckResult.setState(-1);//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
	// spotCheckResult.setSiteName(databaseInfo.getName());
	// spotCheckResult.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResult.setModifyTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
	// spotCheckResultServiceImpl.add(spotCheckResult);
	// }
	// }
	// }
	// result.put("body", "success");
	// writerPrint(JSONObject.fromObject(result).toString());
	// }
	// }
	// } else {
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// result.put("errorMsg", "考评站点不能为空");
	// }else{
	// result.put("errorMsg", "抽查站点不能为空");
	// }
	// writerPrint(JSONObject.fromObject(result).toString());
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// result.put("errorMsg", "保存考评数据异常");
	// }else{
	// result.put("errorMsg", "保存抽查数据异常");
	// }
	// writerPrint(JSONObject.fromObject(result).toString());
	// }
	// }
	// /**
	// * @Description: 启动抽查任务
	// * @author Nora 2016-02-16 下午4:41:45
	// *
	// * @Description: 启动抽查任务，修改合同状态
	// * @author: yangshuai --- 2016-6-3上午10:29:20
	// */
	// public void startSpotTask() {
	//
	// String scheduleIdStr = request.getParameter("scheduleId");
	//
	// HashMap<String, Object> result = new HashMap<String, Object>();
	// try {
	//
	// if (StringUtils.isEmpty(scheduleIdStr)) {
	// result.put("body", "fail");
	// writerPrint(JSONObject.fromObject(result).toString());
	// return;
	// }
	//
	// SpotCheckSchedule spotCheckSchedule =
	// spotCheckScheduleServiceImpl.get(Integer.parseInt(scheduleIdStr));
	//
	// if (null == spotCheckSchedule) {
	// result.put("body", "fail");
	// writerPrint(JSONObject.fromObject(result).toString());
	// return;
	// }
	// spotCheckSchedule.setStatus(1);
	// spotCheckScheduleServiceImpl.update(spotCheckSchedule);
	//
	//
	// // 修改spotCheckResult
	// SpotCheckResultRequest spotCheckResultRequest = new
	// SpotCheckResultRequest();
	// spotCheckResultRequest.setSpotCheckSchedule(spotCheckSchedule.getId());
	// List<SpotCheckResult> listSpotCheckResult =
	// spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
	// for (SpotCheckResult spotCheckResult : listSpotCheckResult) {
	// spotCheckResult.setState(1);
	// spotCheckResultServiceImpl.update(spotCheckResult);
	// }
	//
	// //修改合同状态
	// ServicePeriodRequest spRequest=new ServicePeriodRequest();
	// spRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleIdStr));
	// spRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
	// List<ServicePeriod> spList=servicePeriodServiceImpl.queryList(spRequest);
	// if(spList!=null && spList.size()>0){
	// ServicePeriod sp=spList.get(0);
	// //获取抽查合同id
	// int ctInfoId=sp.getContractInfoId();
	// //根据抽查合同id查询合同信息表
	// CrmProducts crm = crmProductsServiceImpl.get(ctInfoId);
	// if (crm != null) {
	// //将抽查合同执行状态修改为0-初始化（作废：-1，初始化：0，服务中：1，服务结束：2）
	// crm.setExecuteStatus(1);
	// crmProductsServiceImpl.update(crm);
	// }
	//
	// }
	//
	// result.put("body", "success");
	// writerPrint(JSONObject.fromObject(result).toString());
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// /**
	// * @Description: 获取抽查批次
	// * @author yuangw@com.cn 2016-04-26
	// *
	// * yangshuai --- 2016-6-13下午4:20:57
	// */
	// public void getBatchTask() {
	//
	// String siteCode = getCurrentSiteCode();
	// site_code_session = getCurrentSiteCode();
	// Map<String, Object> resultMap = new HashMap<String, Object>();
	// try {
	//
	// if (StringUtils.isEmpty(siteCode)) {
	// resultMap.put("list", "");
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// return;
	// }
	// List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
	// List<CrmProductsResponse> crmlist = getCrmProductsList(siteCode,
	// productTypeArr,
	// DateUtils.formatStandardDate(new Date()), null, null);
	//
	// if (CollectionUtils.isNotEmpty(crmlist)) {
	// SpotCheckScheduleRequest spotCheckScheduleRequest = new
	// SpotCheckScheduleRequest();
	// spotCheckScheduleRequest.setSiteCode(siteCode);
	// List<SpotCheckSchedule> listSpotCheckSchedule =
	// spotCheckScheduleServiceImpl.queryBatch(spotCheckScheduleRequest);
	// Map<String, Object> localMap1 = new HashMap<String, Object>();
	// if(listSpotCheckSchedule.size()==0){
	// localMap1.put("batchNum", 1);
	// localMap1.put("groupNum", 1);
	// items.add(localMap1);
	// logger.info("listSpotCheckSchedule.size()=0");
	// }else{
	// logger.info("listSpotCheckSchedule.size()>0");
	// localMap1.put("batchNum", listSpotCheckSchedule.get(0).getBatchNum()+1);
	// localMap1.put("groupNum", 1);
	// items.add(localMap1);
	// for (SpotCheckSchedule spotCheckSchedule : listSpotCheckSchedule) {
	// Map<String, Object> localMap = new HashMap<String, Object>();
	// localMap.put("batchNum", spotCheckSchedule.getBatchNum());
	// localMap.put("groupNum", spotCheckSchedule.getGroupNum());
	// items.add(localMap);
	// }
	// }
	// resultMap.put("list", items);
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }else{
	// if(site_code_exp.indexOf(site_code_session)>=0){
	// resultMap.put("errorMsg", "免费用户不存在考评");
	// }else{
	// resultMap.put("errorMsg", "免费用户不存在抽查");
	// }
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// resultMap.put("errorMsg", "获取批次组次数据异常");
	// writerPrint(JSONObject.fromObject(resultMap).toString());
	// }
	// }

	/**
	 * @Description: 根据抽查比例，获取对应的抽查站点,并将其保存
	 * @author cuichx --- 2016-5-11下午2:59:07
	 */
	public void randomSite() {
		site_code_session = getCurrentSiteCode();
		/**
		 * 根据条件随机抽查站点的集合 下面的处理思路和人工的处理思路是一样的
		 */
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject json = getJSONObject();
			if (json != null) {
				String dateStart = json.getString("dateStart");// 开始日期
				String dateEnd = json.getString("dateEnd");// 结束日期
				Integer batchNum = Integer.parseInt(json.getString("batchNum"));// 批次
				Integer groupNum = Integer.parseInt(json.getString("groupNum"));// 组次
				Integer isorganizational = Integer.parseInt(json.getString("isorganizational"));// 是非门户
				String taskName = json.getString("taskName");// 任务名称

				String scheduleId = json.getString("scheduleId");
				int levels = getCurrentUserInfo().getUserType();

				String levelStr = json.getString("level");// 站点级别
				String isorg = json.getString("isorg");// 门户类型
				String spotPer = json.getString("spotPer");// 抽查比例
				String orgSiteCodes = json.getString("checkCodeStr");// 省级组织机构编码串
				// 封装参数
				Map<String, Object> map = new HashMap<String, Object>();
				// 省编码串
				if (StringUtils.isNotEmpty(orgSiteCodes) && !",".equals(orgSiteCodes)) {
					if (levels == 3) {// 市
						map.put("orgSiteCodes", getCurrentSiteCode().substring(0, 4).split(","));
					} else if (levels == 4) {// 县
						map.put("orgSiteCodes", getCurrentSiteCode().split(","));
					} else {
						map.put("orgSiteCodes", orgSiteCodes.split(","));
					}
				}
				// 是否门户串
				String[] isorgArray = null;
				if (StringUtils.isNotEmpty(isorg) && !",".equals(isorg)) {
					isorgArray = isorg.split(",");
					map.put("isorganizationals", isorgArray);
				}
				// 抽查比例
				double spotPercent = 0.00;
				if (StringUtils.isNotEmpty(spotPer)) {
					spotPercent = Double.valueOf(spotPer) / 100;
				}
				// 站点类型 1正常，2例外，3关停
				map.put("isexp", DatabaseInfoType.NORMAL.getCode());

				List<DatabaseInfo> siteList = new ArrayList<DatabaseInfo>();// 站点信息数据集合
				if (StringUtils.isNotEmpty(levelStr) && !",".equals(levelStr)) {
					map.put("level", levelStr.split(","));

					// 获取每个省的组织单位编码和各省的站点个数
					List<DatabaseInfoRequest> list = databaseInfoServiceImpl.queryLevelCountByMap(map);
					if (list != null && list.size() > 0) {
						for (int j = 0; j < list.size(); j++) {
							DatabaseInfoRequest dataRequest = list.get(j);
							String orgCode = dataRequest.getOrgCode();// 省组织单位编码
							int spotCounts = (int) (spotPercent * dataRequest.getSpotCount());// 随机获取站点个数
							if (spotCounts == 0) {
								spotCounts = 1;
							} else {
								if (!"100".equals(spotPer)) {
									spotCounts = spotCounts + 1;
								}
							}
							Map<String, Object> paramMap = new HashMap<String, Object>();
							if (levels == 3) {// 市
								paramMap.put("orgCode", getCurrentSiteCode().substring(0, 4));
							} else if (levels == 4) {// 县
								paramMap.put("orgCode", getCurrentSiteCode());
							} else {
								paramMap.put("orgCode", orgCode);
							}
							paramMap.put("spotCount", spotCounts);
							paramMap.put("level", levelStr.split(","));
							paramMap.put("isorganizationals", isorg.split(","));// 是否门户
							paramMap.put("isexp", DatabaseInfoType.NORMAL.getCode());// 正常网站

							List<DatabaseInfo> shengLevelList = databaseInfoServiceImpl.queryLevelRandomSite(paramMap);
							if (shengLevelList != null && shengLevelList.size() > 0) {
								siteList.addAll(shengLevelList);
							}
						}
					}
				}

				/**
				 * 随机抽查站点保存与人工选择站点保存逻辑一样 如果抽查进度表i参数为空，说明是新增一个批组次
				 * 如果抽查进度表id参数不为空，说明是为原有的批组次增加站点
				 */

				if (StringUtils.isNotEmpty(dateStart)) {
					dateStart = DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateStart));
				}
				if (StringUtils.isNotEmpty(dateEnd)) {
					dateEnd = DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateEnd));
				}

				String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
				List<ContractInfo> conList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
				if (conList != null && conList.size() > 0) {
					if (StringUtils.isEmpty(scheduleId)) {// 第一次添加站点
						// 抽查进度表判断重复--处理多并发问题
						// 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
						SpotCheckScheduleRequest seRequest = new SpotCheckScheduleRequest();
						seRequest.setSiteCode(siteCode);
						seRequest.setContractInfoId(conList.get(0).getId() + "");
						seRequest.setBatchNum(batchNum);
						seRequest.setGroupNum(groupNum);
						List<SpotCheckSchedule> seList = spotCheckScheduleServiceImpl.queryList(seRequest);
						if (seList == null || (seList != null && seList.size() == 0)) {
							// 向抽查进度表添加新批次数据
							SpotCheckSchedule spotCheckSchedule = new SpotCheckSchedule();
							spotCheckSchedule.setContractInfoId(conList.get(0).getId() + "");
							spotCheckSchedule.setSiteCode(siteCode);
							spotCheckSchedule.setTaskName(taskName);
							spotCheckSchedule.setStartDate(dateStart);
							spotCheckSchedule.setEndDate(dateEnd);
							// 该批次抽查次数在提交时设置
							// spotCheckSchedule.setSpotWebsiteNum(webSum);
							spotCheckSchedule
									.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
							spotCheckSchedule.setStatus(0);
							spotCheckSchedule.setBatchNum(batchNum);
							spotCheckSchedule.setGroupNum(groupNum);
							spotCheckSchedule.setIsorganizational(isorganizational);
							spotCheckScheduleServiceImpl.add(spotCheckSchedule);

							// 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
							SpotCheckScheduleRequest scheduleRequest = new SpotCheckScheduleRequest();
							scheduleRequest.setSiteCode(siteCode);
							scheduleRequest.setContractInfoId(conList.get(0).getId() + "");
							scheduleRequest.setBatchNum(batchNum);
							scheduleRequest.setGroupNum(groupNum);
							List<SpotCheckSchedule> scheduleList = spotCheckScheduleServiceImpl
									.queryList(scheduleRequest);
							if (scheduleList != null && scheduleList.size() > 0) {

								SpotCheckSchedule schedule = scheduleList.get(0);
								// 向抽查任务结果表中添加抽查的所有siteCode(10位)的信息
								for (int i = 0; i < siteList.size(); i++) {
									DatabaseInfo databaseInfo = databaseInfoServiceImpl
											.findByDatabaseInfoCode(siteList.get(i).getSiteCode());
									if (databaseInfo != null) {
										SpotCheckResult spotCheckResult = new SpotCheckResult();
										spotCheckResult.setSiteCode(siteList.get(i).getSiteCode());
										spotCheckResult.setCheckResult(2);// 检查结果（1：合格，0：单项否决,2:未检查）
										spotCheckResult.setCity(databaseInfo.getCity());
										spotCheckResult.setCounty(databaseInfo.getCounty());
										spotCheckResult.setProvince(databaseInfo.getProvince());
										spotCheckResult.setDirector(databaseInfo.getDirector());
										spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
										spotCheckResult.setOrgSiteCode(siteCode);
										if (databaseInfo.getJumpUrl() != null
												&& !"".equals(databaseInfo.getJumpUrl())) {
											spotCheckResult.setUrl(databaseInfo.getJumpUrl());
										} else {
											spotCheckResult.setUrl(databaseInfo.getUrl());
										}
										spotCheckResult.setSpotCheckSchedule(schedule.getId());// 抽查进度表id
										spotCheckResult.setState(-1);// 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
																		// ，3：报告完成）
										spotCheckResult.setSiteName(databaseInfo.getName());
										spotCheckResult.setCreateTime(
												DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
										spotCheckResult.setModifyTime(
												DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
										spotCheckResultServiceImpl.add(spotCheckResult);
									}
								}

								/**
								 * 创建抽查合同
								 */
								ContractInfo contractInfo = new ContractInfo();
								contractInfo.setSiteCode(siteCode);// 组织单位编号
								String nowDateStr = DateUtils.formatShortDate(new Date());// 当前日期
								// 抽查合同号(C+yyyyMMdd_抽查进度表id_批次_组次)
								String contractCodeSpot = "C" + nowDateStr + "_" + schedule.getId() + "_" + batchNum
										+ "_" + groupNum;
								contractInfo.setContractCodeSpot(contractCodeSpot);
								contractInfo.setContractName(taskName);// 合同名称（取抽查进度表的任务名称）

								contractInfo.setContractBeginTime(DateUtils.parseStandardDate(dateStart));// 合同生效时间
								contractInfo.setContractEndTime(DateUtils.parseStandardDate(dateEnd));// 合同结束时间
								// 作废：-1，初始化：0，服务中：1，服务结束：2
								contractInfo.setExecuteStatus(0);
								contractInfo.setIsSearchTb(2);// 默认2，填报单位不可查看
								contractInfoServiceImpl.add(contractInfo);
								// 查询合同信息表，获取添加的抽查合同的id
								ContractInfoRequest contractRequest = new ContractInfoRequest();
								contractRequest.setContractCodeSpot(contractCodeSpot);// 抽查合同号
								contractRequest.setSiteCode(siteCode);// 组织单位编码
								contractRequest.setExecuteStatus(0);// 执行状态
								contractRequest.setNowTime(DateUtils.formatStandardDate(new Date()));

								List<ContractInfo> conInfoList = contractInfoServiceImpl.queryList(contractRequest);
								if (conInfoList != null && conInfoList.size() > 0) {

									ContractInfo ctInfo = conInfoList.get(0);
									/**
									 * 创建服务周期
									 */
									ServicePeriod sPeriod = new ServicePeriod();
									sPeriod.setComboId(4);// 高级版
									sPeriod.setContractInfoId(ctInfo.getId());// 合同id
									sPeriod.setStartDate(DateUtils.parseStandardDate(dateStart));// 服务开始时间
									sPeriod.setEndDate(DateUtils.parseStandardDate(dateEnd));// 服务结束时间
									sPeriod.setSpotCheckScheduleId(schedule.getId());// 抽查进度表id
									sPeriod.setStatus(0);// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
									sPeriod.setServicePeriodNum(ctInfo.getContractCodeSpot() + "_01");// 周期任务号
									sPeriod.setSiteCode(siteCode);// 当前登录人的组织单位编码
									servicePeriodServiceImpl.add(sPeriod);
								}
							}
							resultMap.put("success", "success");
						} else {
							resultMap.put("errorMsg", "已经存在该批次数据");
						}

					} else {// 点击其他页面中的新增站点按钮--进行站点添加
						int webSum = 0;// 网站数量
						/**
						 * 遍历新增站点数组，查询抽查结果表，判断新增的站点是否已经存在， 如果存在，执行下一次循环；
						 * 如果不存在，统计个数+1，
						 * 抽查服务表中已抽查数-1（更新缓存数据），抽查进度表中站点数+1，抽查结果表中添加一条记录
						 */
						for (int i = 0; i < siteList.size(); i++) {
							// 根据组织单位编码、网站标识码、抽查进度id,查询抽查结果表
							SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
							resultRequest.setOrgSiteCode(siteCode);
							resultRequest.setSiteCode(siteList.get(i).getSiteCode());
							resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
							resultRequest.setPageSize(Integer.MAX_VALUE);

							List<SpotCheckResult> spotResultList = spotCheckResultServiceImpl.queryList(resultRequest);
							if (spotResultList == null || (spotResultList != null && spotResultList.size() == 0)) {
								webSum += 1;
								DatabaseInfo databaseInfo = databaseInfoServiceImpl
										.findByDatabaseInfoCode(siteList.get(i).getSiteCode());
								if (databaseInfo != null) {
									SpotCheckResult spotCheckResult = new SpotCheckResult();
									spotCheckResult.setSiteCode(siteList.get(i).getSiteCode());
									spotCheckResult.setCheckResult(2);// 检查结果（1：合格，0：单项否决,2:未检查）
									spotCheckResult.setCity(databaseInfo.getCity());
									spotCheckResult.setCounty(databaseInfo.getCounty());
									spotCheckResult.setProvince(databaseInfo.getProvince());
									spotCheckResult.setDirector(databaseInfo.getDirector());
									spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
									spotCheckResult.setOrgSiteCode(siteCode);
									if (databaseInfo.getJumpUrl() != null && !"".equals(databaseInfo.getJumpUrl())) {
										spotCheckResult.setUrl(databaseInfo.getJumpUrl());
									} else {
										spotCheckResult.setUrl(databaseInfo.getUrl());
									}
									spotCheckResult.setSpotCheckSchedule(Integer.valueOf(scheduleId));
									spotCheckResult.setState(-1);// 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
																	// ，3：报告完成）
									spotCheckResult.setSiteName(databaseInfo.getName());
									spotCheckResult.setCreateTime(
											DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
									spotCheckResult.setModifyTime(
											DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
									spotCheckResultServiceImpl.add(spotCheckResult);
								}
							}
						}
						if (webSum > 0) {
							// 修改抽查服务统计表中的已抽查数和抽查总数
							SpotCheckInfoRequest infoRequest = new SpotCheckInfoRequest();
							infoRequest.setSiteCode(siteCode);
							infoRequest.setContractInfoId(conList.get(0).getId());
							List<SpotCheckInfo> spotInfoList = spotCheckInfoServiceImpl.queryList(infoRequest);
							if (spotInfoList != null && spotInfoList.size() > 0) {
								SpotCheckInfo spotCheckInfo = spotInfoList.get(0);
								spotCheckInfo.setSpotNum(spotCheckInfo.getSpotNum() + webSum);
								spotCheckInfo.setModifyTime(new Date());
								spotCheckInfoServiceImpl.update(spotCheckInfo);
								// 修改session
								ShiroUser shiroUser = (ShiroUser) getSession().getAttribute(Constants.SHIRO_USER);
								shiroUser.setSportCount(spotCheckInfo.getSpotNum() + "/" + spotCheckInfo.getSpotSum());
								removeSession(Constants.SHIRO_USER);
								add2Session(Constants.SHIRO_USER, shiroUser);
							}

						}
						resultMap.put("success", "success");
					}
				} else {
					if (site_code_exp.indexOf(site_code_session) >= 0) {
						resultMap.put("errorMsg", "免费版不存在考评");
					} else {
						resultMap.put("errorMsg", "免费版不存在抽查");
					}
				}
			} else {
				resultMap.put("errorMsg", "获取页面参数为空");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());

		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "保存随机考评站点数据异常");
			} else {
				resultMap.put("errorMsg", "保存随机抽查站点数据异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 根据省数组，获取该省下面所有的市或者县的站点总数及组织单位编码
	 * @author cuichx --- 2016-5-11下午4:34:14     
	 * @param shengCodes
	 * @return
	 */
	public List<DatabaseInfoRequest> getShiAndXianBySheng(String[] shengCodes,int level,String[] isorganizational){
		
		List<DatabaseInfoRequest> siteList=new ArrayList<DatabaseInfoRequest>();
		
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("orgSiteCodes", shengCodes);//省组织单位编码数组
		params.put("isorganizationals", isorganizational);//是否门户
		params.put("isexp", DatabaseInfoType.NORMAL.getCode());//正常网站
		if(level==1){//省
			params.put("level", 1);//市的组织单位编号
			siteList=databaseInfoServiceImpl.queryShiAndXian(params);
			if(siteList!=null && siteList.size()>0){
				return siteList;
			}
		}else if(level==2){//市
			params.put("level", 2);//市的组织单位编号
			siteList=databaseInfoServiceImpl.queryShiAndXian(params);
			if(siteList!=null && siteList.size()>0){
				return siteList;
			}
		}else{//县
			params.put("level", 3);//县的组织单位编号
			siteList=databaseInfoServiceImpl.queryShiAndXian(params);
			if(siteList!=null && siteList.size()>0){
				return siteList;
			}
		}
		return siteList;
	}
	
	/**
	 * @Description: 根据抽查比例，获取对应的抽查站点个数
	 * @author cuichx --- 2016-5-10下午3:27:25
	 */
	public void randomCount(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		site_code_session = getCurrentSiteCode();
		int levels = getCurrentUserInfo().getUserType();
		try {
			JSONObject json=getJSONObject();
			if(json!=null){
				String levelStr=json.getString("level");//站点级别
				String isorg=json.getString("isorg");//门户类型
				String spotPer=json.getString("spotPer");//抽查比例
				String orgSiteCodes=json.getString("checkCodeStr");//省级组织机构编码串
				
				Map<String, Object> map=new HashMap<String, Object>();
				
				if(StringUtils.isNotEmpty(orgSiteCodes) && !",".equals(orgSiteCodes)){
					if(levels == 3){//市
						map.put("orgSiteCodes", getCurrentSiteCode().substring(0,4).split(","));
					}else if(levels == 4){//县
						map.put("orgSiteCodes", getCurrentSiteCode().split(","));
					}else{
						map.put("orgSiteCodes", orgSiteCodes.split(","));
					}
					//orgNum = orgNums.split(",");
				}else {
					resultMap.put("errorMsg", "省级组织单位编码不能为空");
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}
				
				if(StringUtils.isNotEmpty(isorg) && !",".equals(isorg)){
					map.put("isorganizationals", isorg.split(","));
				}else{
					resultMap.put("errorMsg", "门户类型不能为空");
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}
				
				
				double spotPercent=0.00;
				if(StringUtils.isEmpty(spotPer)){
					if(site_code_exp.indexOf(site_code_session)>=0){
						resultMap.put("errorMsg", "考评比例不能为空");
					}else{
						resultMap.put("errorMsg", "抽查比例不能为空");
					}
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}else{
					spotPercent=Double.valueOf(spotPer)/100;
				}
				
				map.put("isexp", DatabaseInfoType.NORMAL.getCode());//1正常，2例外，3关停
				int spotCount=0;//统计站点个数
				if(StringUtils.isNotEmpty(levelStr) && !",".equals(levelStr)){
					map.put("level", levelStr.split(","));
					
					//获取每个省的组织单位编码和各省的站点个数
					List<DatabaseInfoRequest> list= databaseInfoServiceImpl.queryLevelCountByMap(map);
					if(list!=null && list.size()>0){
						for(int j=0;j<list.size();j++){
							DatabaseInfoRequest dataRequest=list.get(j);
							String orgCode = dataRequest.getOrgCode();//省组织单位编码
							int spotCounts=(int) (spotPercent*dataRequest.getSpotCount());//随机获取站点个数
							if(spotCounts==0){
								spotCounts=1;
							}else{
								if(!"100".equals(spotPer)){
									spotCounts = spotCounts + 1;
								}
							}
							Map<String, Object> paramMap=new HashMap<String, Object>();
							if(levels == 3){//市
								paramMap.put("orgCode", getCurrentSiteCode().substring(0,4));
							}else if(levels == 4){//县
								paramMap.put("orgCode", getCurrentSiteCode());
							}else{
								paramMap.put("orgCode", orgCode);
							}
							paramMap.put("spotCount", spotCounts);
							paramMap.put("level", levelStr.split(","));
							paramMap.put("isorganizationals", isorg.split(","));//是否门户
							paramMap.put("isexp", DatabaseInfoType.NORMAL.getCode());//正常网站
							
							List<DatabaseInfo> shengLevelList = databaseInfoServiceImpl.queryLevelRandomSite(paramMap);
							if(shengLevelList !=null && shengLevelList.size()>0){
								spotCount += shengLevelList.size();
							}
						}
					}
					
					resultMap.put("spotCount", spotCount);
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}else{
					resultMap.put("errorMsg", "站点级别不能为空");
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "统计站点个数异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 获取每个省的站点个数--只统计正常的例外和关停的不统计
	 * @author cuichx --- 2016-5-11上午11:25:04
	 */
	public void getSiteCount(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String shengCode=request.getParameter("shengCode");//省级组织单位编码
		try {
			Map<String, Object>  params=new HashMap<String, Object>();
			
			if(StringUtils.isNotEmpty(shengCode)){
				String[] codeArray={shengCode.substring(0, 2)};
				params.put("orgSiteCodes", codeArray);//组织单位前两位
				params.put("isexp", DatabaseInfoType.NORMAL.getCode());//正常网站
				
				int siteCount=databaseInfoServiceImpl.queryCountByMap(params);
				resultMap.put("siteCount",siteCount);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}else{
				resultMap.put("errorMsg", "省级组织单位编码不能为空");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "统计省站点个数异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 获取所有每个省的站点个数--只统计正常的例外和关停的不统计
	 * @author: yangshuai --- 2016-5-17上午9:14:30
	 * @return
	 * @throws Exception
	 */
	public void getAllSiteCount(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String shengCode = request.getParameter("shengCodes");//省级组织单位编码
		try {
			Map<String, Object>  params=new HashMap<String, Object>();
			
			if(StringUtils.isNotEmpty(shengCode)){
				String[] codeArray = shengCode.split(",");
				params.put("orgSiteCodes", codeArray);//组织单位前两位
				params.put("isexp", DatabaseInfoType.NORMAL.getCode());//正常网站
				
				List<DatabaseInfoRequest> databaseInfo = databaseInfoServiceImpl.queryLevelCountByMap(params);
				resultMap.put("databaseInfo",databaseInfo);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}else{
				resultMap.put("errorMsg", "省级组织单位编码不能为空");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "统计省站点个数异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 站点明细页面
	 * @author cuichx --- 2016-5-5上午11:36:09     
	 * @return
	 */
	public String websiteList(){
		 batchNum=request.getParameter("batchNum");//批次
		 groupNum=request.getParameter("groupNum");//组次
		 request.setAttribute("flagAll", request.getParameter("flagAll"));//抽查进度id
		 return SUCCESS;
	}
	
	/**
	 * @Description: 获取抽查进度表数据
	 * @author cuichx --- 2016-5-5下午3:44:36
	 */
	public void getSpotSchedule() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String batchNum = request.getParameter("batchNum");// 批次
		String groupNum = request.getParameter("groupNum");// 组次
		String flagAll = request.getParameter("flagAll");// 抽查进度id
		// 获取当前登录人的组织机构编码
		site_code_session = getCurrentSiteCode();
		String siteCode = getCurrentSiteCode();
		try {

			String contractId = "";
			if (flagAll != null && !"".equals(flagAll)) {
				if ("".equals(contractId)) {
					SpotCheckSchedule spotCheckSchedule = spotCheckScheduleServiceImpl.get(Integer.parseInt(flagAll));
					contractId = spotCheckSchedule.getContractInfoId();
				}
			} else {
				List<ContractInfo> conList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
				if (conList != null && conList.size() > 0) {
					contractId = conList.get(0).getId() + "";
				}
			}

			// if(conList!=null && conList.size()>0){
			if (!"".equals(contractId)) {//
				// 根据组织机构编码、合同id、批次、组次,查询抽查进度表
				SpotCheckScheduleRequest spotRequest = new SpotCheckScheduleRequest();
				spotRequest.setSiteCode(siteCode);
				// spotRequest.setContractInfoId(conList.get(0).getId()+"");
				spotRequest.setContractInfoId(contractId);
				spotRequest.setBatchNum(Integer.valueOf(batchNum));
				spotRequest.setGroupNum(Integer.valueOf(groupNum));

				List<SpotCheckSchedule> spotScheduleList = spotCheckScheduleServiceImpl.queryList(spotRequest);
				if (spotScheduleList != null && spotScheduleList.size() > 0) {
					SpotCheckSchedule schedule = spotScheduleList.get(0);
					resultMap.put("scheduleId", schedule.getId());// 抽查进度表id
					resultMap.put("batchNum", batchNum);
					resultMap.put("groupNum", groupNum);
					resultMap.put("spotWebsiteNum", schedule.getSpotWebsiteNum());// 站点总数
					resultMap.put("status", schedule.getStatus());// 状态（0：未启动，1：检查中，2：检查完成）
					resultMap.put("taskName", schedule.getTaskName());// 任务名称
					resultMap.put("startDate", schedule.getStartDate());// 监测开始时间（yyyy-mm-dd）
					resultMap.put("endDate", schedule.getEndDate());// 监测结束时间(yyyy-mm-dd)

					writerPrint(JSONObject.fromObject(resultMap).toString());
				} else {
					if (site_code_exp.indexOf(site_code_session) >= 0) {
						resultMap.put("errorMsg", "不存在要查询的考评进度数据");
					} else {
						resultMap.put("errorMsg", "不存在要查询的抽查进度数据");
					}
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			} else {
				resultMap.put("errorMsg", "不存在该组织单位的合同信息或合同已经过期");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "不存在要查询的考评进度数据");
			} else {
				resultMap.put("errorMsg", "不存在要查询的抽查进度数据");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	

	/**
	 * @Description: 抽查站点详情表--获取抽查结果数据
	 * @author cuichx --- 2016-5-5下午4:33:26
	 */
	public void getSpotResult(){
		site_code_session = getCurrentSiteCode();
		//封装返回结果
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String servicePeriodId = request.getParameter("servicePeriodId");
		String orgSiteCode=getCurrentSiteCode();//当前登录组织机构编码
		try {
			int index=0;
			if(StringUtils.isNotEmpty(scheduleId)){
				//根据组织机构编码和抽查进度表id,查询抽查结果表
				SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
				resultRequest.setOrgSiteCode(orgSiteCode);
				resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
				resultRequest.setPageSize(Integer.MAX_VALUE);
				//默认 siteCode 正序
				List<QueryOrder> queryList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("site_code",QueryOrderType.ASC);
				queryList.add(queryOrder);
				resultRequest.setQueryOrderList(queryList);
				
				ServicePeriodRequest serviceRequest = new ServicePeriodRequest();
				serviceRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleId));
				serviceRequest.setSiteCode(orgSiteCode);
				List<ServicePeriod> sPeriodList = servicePeriodServiceImpl.queryList(serviceRequest);
				
				List<SpotCheckResult> resultList=spotCheckResultServiceImpl.queryList(resultRequest);
				
				if(resultList!=null && resultList.size()>0){
					List<Map<String, Object>> returnList=new ArrayList<Map<String,Object>>();
					for (int i = 0; i < resultList.size(); i++) {
						Map<String, Object> map =new HashMap<String, Object>();
						SpotCheckResult result=resultList.get(i);
						
						String siteCode="";//网站标识码
						if(StringUtils.isNotEmpty(result.getSiteCode())){
							siteCode=result.getSiteCode();
						}
						map.put("siteCode",siteCode);
						
						String siteName="";//网站名称
						if(StringUtils.isNotEmpty(result.getSiteName())){
							siteName=result.getSiteName();
						}
						map.put("siteName", siteName);
						
						//是否门户（0：否，1：是）
						int isorganizational=result.getIsorganizational();
						map.put("isorganizational", isorganizational);
						
						String url="";//首页url
						if(StringUtils.isNotEmpty(result.getUrl())){
							url=result.getUrl();
						}
						map.put("url", url);
						
						String province="";//省/部
						if(StringUtils.isNotEmpty(result.getProvince())){
							province=result.getProvince();
						}
						map.put("province", province);
						
						String city="";//市
						if(StringUtils.isNotEmpty(result.getCity())){
							city=result.getCity();
						}
						map.put("city", city);
						
						String county="";//县
						if(StringUtils.isNotEmpty(result.getCounty())){
							county=result.getCounty();
						}
						map.put("county", county);
						
						//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
						map.put("resultState", result.getState());
						
						//检查结果（1：合格，0：单项否决,2:未检查）
						map.put("checkResult", result.getCheckResult());
						
						//检查结果（0：待审核，1：通过,2:不通过）
						map.put("checkReportResult", result.getCheckReportResult());
						
						DetectionPeroidCountRequest peroidCountRequest=new DetectionPeroidCountRequest();
						peroidCountRequest.setSiteCode(result.getSiteCode());
						peroidCountRequest.setServicePeroidId(Integer.parseInt(servicePeriodId));
						List<DetectionPeroidCount> peroidCountlist=detectionPeroidCountServiceImpl.queryList(peroidCountRequest);
						
						if(peroidCountlist != null && peroidCountlist.size()>0){
							Integer siteCheckResult=peroidCountlist.get(0).getSiteCheckResult();
							String staDate = peroidCountlist.get(0).getStartDate();
							String date = dicUtils.getValue("spot_check_time");  
							Date d = DateUtils.parseStandardDate(date);       //控制合格时间
							if(siteCheckResult !=null){
								if(StringUtils.isNotEmpty(staDate)){
									Date da = DateUtils.parseStandardDate(staDate);     // 实际开始时间
									if(d.getTime() > da.getTime()){
											map.put("siteCheckResultName", "--");
									}else{
										if(siteCheckResult==1){
											//合格
											map.put("siteCheckResultName", "合格");
										}else if(siteCheckResult==2){
											//不合格
											map.put("siteCheckResultName", "不合格");
										}
									}
								}
							}else{
								//空数据的时候
//								ReportCollectResultRequest reportCollectrequest=new ReportCollectResultRequest();
//								reportCollectrequest.setSiteCode(site_code_session);
//								reportCollectrequest.setServicePeriodId(servicePeriodId);
//								List<ReportCollectResult> reportCollectResultList=reportCollectResultServiceImpl.queryList(reportCollectrequest);
//								if(reportCollectResultList !=null && reportCollectResultList.size()>0){
//									map.put("siteCheckResultName", "合格");
//								}else{
									map.put("siteCheckResultName", "--");
//								}
							}
						}
						
						//判断是否生成报告
						if(sPeriodList.size() >0){
							ReportWordResultRequest req = new ReportWordResultRequest();
							req.setSiteCode(siteCode);
							req.setServicePeriodId(sPeriodList.get(0).getId());
							List<ReportWordResult> count = reportWordResultServiceImpl.queryList(req);
							if(count.size() >0){
								map.put("isDown", 1);
							}else{
								map.put("isDown", 0);
							}
						}else{
							map.put("isDown", 0);
						}
						if(null == checkSiteType){
							checkSiteType=0;
						}
						if(null == checkResult){
							checkResult=0;
						}
						if(null ==checkReport){
							checkReport=0;
						}
						if(null == checkNotice){
							checkNotice=0;
						}
						if(null == checkRead){
							checkRead=0;
						}
						//获取整改通知状态
						SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
						spotCheckNoticeRequest.setSiteCode(siteCode);
						spotCheckNoticeRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
						List<SpotCheckNotice> spotCheckNoticeList = spotCheckNoticeServiceImpl.queryList(spotCheckNoticeRequest);
						Integer isRead=0;
						if(spotCheckNoticeList != null && spotCheckNoticeList.size()>0){
							SpotCheckNotice spotCheckNotice=spotCheckNoticeList.get(0);
							isRead=spotCheckNotice.getIsRead();
							if(null == isRead){
								isRead=1;
							}
						}else{
							isRead=0;
						}
						if(checkReport==1 && checkNotice==1 && checkRead==1){
							//两个都选中
							
							if(sPeriodList.size() >0){
								ReportWordResultRequest req = new ReportWordResultRequest();
								req.setSiteCode(siteCode);
								req.setServicePeriodId(sPeriodList.get(0).getId());
								List<ReportWordResult> count = reportWordResultServiceImpl.queryList(req);
								if(count.size() >0 && result.getCheckReportResult()!=0 && isRead==1){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}else{
								map.put("isHide", 1);
							}
							
							
							
						}else if(checkReport==0 && checkNotice==0 && checkRead==0 && checkSiteType==0 && checkResult==0 ){
								//如果页面没有选中复选框  直接显示全部
								index++;
								map.put("isHide", 0);
						}else{
							if(checkSiteType==1){
								DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
								treeInfoRequest.setSiteCode(siteCode);
								treeInfoRequest.setOrgSiteCode(orgSiteCode);
								treeInfoRequest.setLayerType(1);//本级门户
								treeInfoRequest.setIsLink(1);//是否link使用
								List<DatabaseTreeInfo> treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
								if(treeList.size()>0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}else if(checkSiteType==2){
								DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
								treeInfoRequest.setSiteCode(siteCode);
								treeInfoRequest.setOrgSiteCode(orgSiteCode);
								treeInfoRequest.setLayerType(2);//本级部门
								treeInfoRequest.setIsLink(1);//是否link使用
								List<DatabaseTreeInfo> treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
								if(treeList.size()>0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}else if(checkSiteType ==3 ){
								DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
								treeInfoRequest.setSiteCode(siteCode);
								treeInfoRequest.setOrgSiteCode(orgSiteCode);
								treeInfoRequest.setLayerType(3);//下属单位
								treeInfoRequest.setIsLink(1);//是否link使用
								List<DatabaseTreeInfo> treeList=databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
								if(treeList.size()>0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}
							if(checkResult==1){
								DetectionPeroidCountRequest request=new DetectionPeroidCountRequest();
								request.setSiteCode(siteCode);
								request.setSiteCheckResult(1);//合格
								List<DetectionPeroidCount> list=detectionPeroidCountServiceImpl.queryList(request);
								if(list.size()>0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}else if(checkResult==2){
								DetectionPeroidCountRequest request=new DetectionPeroidCountRequest();
								request.setSiteCode(siteCode);
								request.setSiteCheckResult(2);//不合格
								List<DetectionPeroidCount> list=detectionPeroidCountServiceImpl.queryList(request);
								if(list.size()>0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}
						 if(checkReport==1 ){
								//已完成选中  已通知整改未选中
								if(sPeriodList.size() >0){
									ReportWordResultRequest req = new ReportWordResultRequest();
									req.setSiteCode(siteCode);
									req.setServicePeriodId(sPeriodList.get(0).getId());
									List<ReportWordResult> count = reportWordResultServiceImpl.queryList(req);
									if(count.size() >0 ){
										index++;
										map.put("isHide", 0);
									}else{
										map.put("isHide", 1);
									}
								}else{
									map.put("isHide", 1);
								}
							}
						 
						 if(checkNotice==1){
								//已完成没选中  已通知整改选中
								if(result.getCheckReportResult()!=0){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
								
							}else if(checkNotice ==2){
								if(result.getCheckReportResult()!=0){
									index++;
									map.put("isHide", 1);
								}else{
									map.put("isHide", 0);
								}
							}else if(checkNotice ==3){
								if(result.getCheckReportResult() == 2){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}else if(checkNotice ==4){
								if(result.getCheckReportResult() == 1){
									index++;
									map.put("isHide", 0);
								}else{
									map.put("isHide", 1);
								}
							}
						 if(checkRead==1){
							 if(isRead==1){
									//已读显示
								 index++;
									map.put("isHide", 0);
							}else{
									map.put("isHide", 1);
						   }
						 }
						}
						
						/*2016/06/23 网站考评->站点数明细 网站名称添加鼠标悬浮显示站点基本信息；展现形式参照本级部门--START-- sunjy*/
						DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
						databaseInfoRequest.setSiteCode(result.getSiteCode());
						List<DatabaseInfo> datalist = databaseInfoServiceImpl.queryList(databaseInfoRequest);
						if (!CollectionUtils.isEmpty(datalist)) {
							DatabaseInfo databaseInfo = datalist.get(0);
							String director = databaseInfo.getDirector();
							map.put("siteManageUnit", director);
							String address = databaseInfo.getAddress();
							map.put("officeAddress", address);
							String principalName = databaseInfo.getPrincipalName();
							map.put("relationName", principalName);
							String telephone = databaseInfo.getTelephone();
							map.put("relationCellphone", telephone);
							String mobile = databaseInfo.getMobile();
							map.put("relationPhone", mobile);
							String email = databaseInfo.getEmail();
							map.put("relationEmail", email);

							String linkmanName = databaseInfo.getLinkmanName();
							map.put("linkman", linkmanName);
							String telephone2 = databaseInfo.getTelephone2();
							map.put("linkmanCellphone", telephone2);

							String mobile2 = databaseInfo.getMobile2();
							map.put("linkmanPhone", mobile2);
							String email2 = databaseInfo.getEmail2();
							map.put("linkmanEmail", email2);
						}
						//查询详情页各项指标数据
						DetectionPeroidCountRequest detectionPeroidCountRequest=new DetectionPeroidCountRequest();
						if(StringUtils.isNotEmpty(servicePeriodId) ){
							detectionPeroidCountRequest.setServicePeroidId(Integer.valueOf(servicePeriodId));
						}else{
							detectionPeroidCountRequest.setServicePeroidId(-1);
						}
						detectionPeroidCountRequest.setSiteCode(siteCode);
						List<DetectionPeroidCount> detectionPeroidList=detectionPeroidCountServiceImpl.queryList(detectionPeroidCountRequest);
						if(detectionPeroidList.size()>0){
							for(DetectionPeroidCount detectionPeroidCount :detectionPeroidList){
								detectionPeroidCount.setIsRead(isRead);
								map.put("detectionPeroidCount", detectionPeroidCount);
								}
							returnList.add(map);
						}
						
					}
					resultMap.put("returnList", returnList);
					if(returnList.size()==0){
						resultMap.put("listSize", 0);
					}else{
						resultMap.put("listSize", index);
					}
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}else{
					resultMap.put("listSize", 0);
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}else{
				resultMap.put("listSize", 0);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("listSize", 0);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 全部启动抽查任务
	 * @author cuichx --- 2016-5-5下午9:24:16
	 */
	public void openAllCheckResult() {
		site_code_session = getCurrentSiteCode();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String scheduleId = request.getParameter("scheduleId");// 抽查进度表id
		String batchNum = request.getParameter("batchNum");// 批次
		String groupNum = request.getParameter("groupNum");// 组次
		String orgSiteCode = getCurrentSiteCode();// 组织单位编码

		try {
			SpotCheckSchedule scSchedule = null;
			if (StringUtils.isNotEmpty(scheduleId)) {
				scSchedule = spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
			} else if (StringUtils.isNotEmpty(batchNum) && StringUtils.isNotEmpty(groupNum)) {
				List<ContractInfo> conList = getContractInfoList(orgSiteCode, DateUtils.formatStandardDate(new Date()));
				if (conList != null && conList.size() > 0) {
					// 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
					SpotCheckScheduleRequest scheduleRequest = new SpotCheckScheduleRequest();
					scheduleRequest.setSiteCode(orgSiteCode);
					scheduleRequest.setContractInfoId(conList.get(0).getId() + "");
					scheduleRequest.setBatchNum(Integer.valueOf(batchNum));
					scheduleRequest.setGroupNum(Integer.valueOf(groupNum));
					List<SpotCheckSchedule> scheduleList = spotCheckScheduleServiceImpl.queryList(scheduleRequest);
					if (scheduleList != null && scheduleList.size() > 0) {
						scSchedule = scheduleList.get(0);
					}
				}
			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("errorMsg", "考评进度id为空");
				} else {
					resultMap.put("errorMsg", "抽查进度id为空");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}

			// 将抽查进度表中的状态修改为1-检查中
			if (scSchedule != null) {
				scSchedule.setStatus(SpotScheduleStateType.IN_CHECK.getCode());// 状态（0：未启动，1：检查中，2：检查完成）
				spotCheckScheduleServiceImpl.update(scSchedule);

				// 修改抽查次数
				updateSpotCheckInfo(scSchedule.getSiteCode(), scSchedule.getContractInfoId(),
						scSchedule.getSpotWebsiteNum());
			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("errorMsg", "考评结束，不能启动考评");
				} else {
					resultMap.put("errorMsg", "抽查结束，不能启动抽查");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}
			// 根据组织机构编码和抽查进度表id,查询抽查结果表
			SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
			resultRequest.setOrgSiteCode(orgSiteCode);
			resultRequest.setSpotCheckSchedule(scSchedule.getId());
			resultRequest.setPageSize(Integer.MAX_VALUE);

			List<SpotCheckResult> resultList = spotCheckResultServiceImpl.queryList(resultRequest);
			if (resultList != null && resultList.size() > 0) {
				for (SpotCheckResult spotCheckResult : resultList) {
					spotCheckResult.setState(SpotResultStateType.IN_CHECK.getCode());// 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
																						// ，3：报告完成）
					// 更新抽查任务表数据
					spotCheckResultServiceImpl.update(spotCheckResult);
				}
				/**
				 * 根据抽查合同号，查询合同信息表，获取合同信息,再根据合同信息表id,查询服务周期表，
				 * 将查询出来的服务周期表数据的状态更新为1-服务中（服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务）
				 */
				// 通过抽查进度表id,查询服务周期表，将获取的周期状态修改1-服务中（服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务）
				ServicePeriodRequest spRequest = new ServicePeriodRequest();
				spRequest.setSpotCheckScheduleId(scSchedule.getId());
				spRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
				List<ServicePeriod> spList = servicePeriodServiceImpl.queryList(spRequest);
				if (spList != null && spList.size() > 0) {
					ServicePeriod sp = spList.get(0);
					// 获取抽查合同id
					int ctInfoId = sp.getContractInfoId();
					// 根据抽查合同id查询合同信息表
					ContractInfo ctInfo = contractInfoServiceImpl.get(ctInfoId);
					if (ctInfo != null) {
						// 将抽查合同执行状态修改为0-初始化（作废：-1，初始化：0，服务中：1，服务结束：2）
						ctInfo.setExecuteStatus(1);
						contractInfoServiceImpl.update(ctInfo);
					}

				}
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("success", "启动考评任务成功");
				} else {
					resultMap.put("success", "启动抽查任务成功");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("errorMsg", "不存在考评站点");
				} else {
					resultMap.put("errorMsg", "不存在抽查站点");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "考评任务添加异常");
			} else {
				resultMap.put("errorMsg", "抽查任务添加异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 按批次删除抽查任务
	 * @author Na.Y --- 2016-9-14下午4:24:16
	 */
	public void deleteSpotCheckSchedule() {
		site_code_session = getCurrentSiteCode();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String scheduleIdStr = request.getParameter("scheduleId");// 抽查进度表id
		String batchNum = request.getParameter("batchNum");// 批次
		String groupNum = request.getParameter("groupNum");// 组次
		// String orgSiteCode=getCurrentSiteCode();//组织单位编码

		if (StringUtils.isEmpty(scheduleIdStr)) {
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "考评进度id为空");
			} else {
				resultMap.put("errorMsg", "抽查进度id为空");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
			return;
		}

		try {

			// 抽查进度表Id
			int scheduleId = Integer.valueOf(scheduleIdStr);
			// 抽查合同号(C+yyyyMMdd_抽查进度表id_批次_组次)
			// 抽查合同号后面部分：抽查进度表id_批次_组次
			String contractCodeSpotPart = "_" + scheduleId + "_" + batchNum + "_" + groupNum;

			// 1.删除抽查进度表:物理删除
			spotCheckScheduleServiceImpl.delete(scheduleId);

			// 2.删除抽查合同表：笔记删除
			ContractInfoRequest contractInfoRequest = new ContractInfoRequest();
			contractInfoRequest.setSiteCode(site_code_session);
			contractInfoRequest.setContractCodeSpotLike(contractCodeSpotPart);
			List<ContractInfo> listSpotContractInfo = contractInfoServiceImpl.queryList(contractInfoRequest);
			if (!CollectionUtils.isEmpty(listSpotContractInfo)) {
				// 抽查合同：与spotCheckSchedule一一对应
				ContractInfo contractInfoSpot = listSpotContractInfo.get(0);
				contractInfoSpot.setExecuteStatus(-1);
				contractInfoServiceImpl.update(contractInfoSpot);
			}

			// 3.删除抽查服务周期表：标记删除
			ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
			servicePeriodRequest.setSpotCheckScheduleId(scheduleId);
			List<ServicePeriod> listServicePeroid = servicePeriodServiceImpl.queryList(servicePeriodRequest);
			if (!CollectionUtils.isEmpty(listServicePeroid)) {
				ServicePeriod servicePeriod = listServicePeroid.get(0);
				servicePeriod.setStatus(-1);
				servicePeriodServiceImpl.update(servicePeriod);
			}

			// 4.删除抽查结果表:物理删除
			spotCheckResultServiceImpl.deleteBatchBySchedule(scheduleId);

			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("success", "删除考评任务成功");
			} else {
				resultMap.put("success", "删除抽查任务成功");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "考评任务删除异常");
			} else {
				resultMap.put("errorMsg", "抽查任务删除异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}

	}

	/**
	 * @Description: 抽查站点详情表---导出excel数据
	 * @author cuichx --- 2016-5-5下午8:02:05
	 */
	public void excelSpotResult(){
		site_code_session = getCurrentSiteCode();
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String keyWord=request.getParameter("keyWord");//查询关键字
		String orgSiteCode=getCurrentSiteCode();//当前组织机构编码
		try {
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[] {"序号","网站标识码", "网站名称", "省/部", "市","县","检查状态","报告状态","检查结果","人工核查"};
			list.add(obj1);
			String fileName = "网站抽查-站点明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			String title = "抽查站点明细";
			if(site_code_exp.indexOf(site_code_session)>=0){
				fileName = "网站考评-站点明细(" + DateUtils.formatStandardDate(new Date()) + ").xls";
				title = "考评站点明细";
			}
			
			if(StringUtils.isNotEmpty(scheduleId)){
				//根据组织机构编码和抽查进度表id,查询抽查结果表
				SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
				resultRequest.setOrgSiteCode(orgSiteCode);
				resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
				resultRequest.setPageSize(Integer.MAX_VALUE);
				if(StringUtils.isNotEmpty(keyWord)){
					resultRequest.setKeyWord(keyWord);
				}
				
				List<SpotCheckResult> spotList=spotCheckResultServiceImpl.queryList(resultRequest);
				if(spotList!=null && spotList.size()>0){

					for (int i = 0; i < spotList.size(); i++) {
						SpotCheckResult result = spotList.get(i);
						Object[] obj = new Object[10];
						obj[0] = i + 1;
						String siteCode="";//网站标识码
						if(StringUtils.isNotEmpty(result.getSiteCode())){
							siteCode=result.getSiteCode();
						}
						obj[1]=siteCode;
						
						String siteName="";//网站名称
						if(StringUtils.isNotEmpty(result.getSiteName())){
							siteName=result.getSiteName();
						}
						obj[2]=siteName;
						
						String province="";//省/部
						if(StringUtils.isNotEmpty(result.getProvince())){
							province=result.getProvince();
						}
						obj[3]=province;
						
						String city="";//市
						if(StringUtils.isNotEmpty(result.getCity())){
							city=result.getCity();
						}
						obj[4]=city;
						
						String county="";//县
						if(StringUtils.isNotEmpty(result.getCounty())){
							county=result.getCounty();
						}
						obj[5]=county;
						
						//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
						obj[6]=SpotResultStateType.getNameByCode(result.getState());
						
						//报告状态（0:待审核，1：通过，2:不通过）
						obj[7]=CheckReportResultType.getNameByCode(result.getCheckReportResult());
						
						//检查结果（1：合格，0：单项否决,2:未检查）
						obj[8]=CheckResultType.getNameByCode(result.getCheckResult());
						
						obj[9]="合格";
						
						list.add(obj);
					}
				}
			}
			ExportExcel.spotResultExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * @Description: 获取抽查列表
	 * @author cuichx --- 2016-5-5上午11:35:27     
	 * @return
	 */
	public String list() {
		List<DicItem> dicList = dicUtils.getDictList("siteType");
		request.setAttribute("dicList", dicList); // 网站类别
		scheduleId=request.getParameter("scheduleId");//抽查进度表
		flag=request.getParameter("flag");
		 String loginOrgCode = getCurrentUserInfo().getSiteCode();// 登录组织单位编码
		 String siteCodeUp = databaseBizServiceImpl.superiorUnit(loginOrgCode); // 上级组织单位
		 request.setAttribute("siteCodeUp", siteCodeUp);
		 request.setAttribute("startDate", DateUtils.formatStandardDate(new Date()));//抽查进度id
		 request.setAttribute("endDate", DateUtils.formatStandardDate(new Date()));//抽查进度id
		return SUCCESS;
	}
	
	/**
	 * @Description: 获取某个省份的抽查结果
	 * @author cuichx --- 2016-5-4下午8:40:27
	 */
	public void getSpotCheckResult(){
		site_code_session = getCurrentSiteCode();
		//封装放回页面的数据
		Map<String,Object> resultMap=new HashMap<String, Object>();
		
		String sheng=request.getParameter("sheng");//省份
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String orgSiteCode=getCurrentUserInfo().getSiteCode();//获取当前登录人的编码
		try {
			//根据组织单位编码、省份、抽查进度id,查询抽查结果表
			SpotCheckResultRequest spotRequest=new SpotCheckResultRequest();
			if(StringUtils.isNotEmpty(sheng)){
				spotRequest.setProvince(sheng);
			}
			if(StringUtils.isNotEmpty(scheduleId)){
				spotRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
			}
			spotRequest.setOrgSiteCode(orgSiteCode);
			spotRequest.setPageSize(Integer.MAX_VALUE);
			
			List<Map<String, Object>> resultList=new ArrayList<Map<String,Object>>();
			List<SpotCheckResult> spotList=spotCheckResultServiceImpl.queryList(spotRequest);
			if(spotList!=null && spotList.size()>0){

				for(int i=0;i<spotList.size();i++){
					Map<String, Object> map=new HashMap<String, Object>();
					SpotCheckResult result=spotList.get(i);
					String city="";//市
					if(StringUtils.isNotEmpty(result.getCity())){
						city=result.getCity();
					}
					map.put("city", city);
					
					String county="";//县
					if(StringUtils.isNotEmpty(result.getCounty())){
						county=result.getCounty();
					}
					map.put("county", county);
					
					String siteCode="";//网站标识码
					if(StringUtils.isNotEmpty(result.getSiteCode())){
						siteCode=result.getSiteCode();
					}
					map.put("siteCode", siteCode);
					
					String siteName="";//网站名称
					if(StringUtils.isNotEmpty(result.getSiteName())){
						siteName=result.getSiteName();
					}
					map.put("siteName", siteName);
					
					String url="";//url
					if(StringUtils.isNotEmpty(result.getUrl())){
						url=result.getUrl();
					}
					map.put("url", url);
					
					//通过网站标识码查询站点信息表，获取主办单位名称
					DatabaseInfoRequest dataRequest=new DatabaseInfoRequest();
					dataRequest.setSiteCode(siteCode);
					String director="";//主办单位
					List<DatabaseInfo> dataList=databaseInfoServiceImpl.queryList(dataRequest);
					if(dataList!=null && dataList.size()>0){
						director=dataList.get(0).getDirector();
					}
					map.put("director", director);
					
					resultList.add(map);
				}
				resultMap.put("resultList", resultList);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}else{
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("errorMsg", "该省份不存在考评结果数据");
				}else{
					resultMap.put("errorMsg", "该省份不存在抽查结果数据");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(site_code_exp.indexOf(site_code_session)>=0){
				resultMap.put("errorMsg", "获取考评结果数据异常");
			}else{
				resultMap.put("errorMsg", "获取抽查结果数据异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 数据提交页面--点击删除 删除单条记录
	 * @author cuichx --- 2016-5-4下午9:55:09
	 */
	public void deleteSpotResult() {
		site_code_session = getCurrentSiteCode();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String siteCode = request.getParameter("siteCode");// 网站标识码
		String scheduleId = request.getParameter("scheduleId");// 抽查进度表id
		String orgSiteCode = getCurrentUserInfo().getSiteCode();// 当前登录人的编码

		try {
			// 根据组织单位编码、省份、抽查进度id,查询抽查结果表
			SpotCheckResultRequest spotRequest = new SpotCheckResultRequest();
			if (StringUtils.isNotEmpty(siteCode)) {
				spotRequest.setSiteCode(siteCode);
			}
			if (StringUtils.isNotEmpty(scheduleId)) {
				spotRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
			}
			spotRequest.setOrgSiteCode(orgSiteCode);
			spotRequest.setPageSize(Integer.MAX_VALUE);
			/**
			 * 如果抽查结果表中存在该记录，删除该记录，同时，更新抽查进度表中站点个数、更新抽查服务统计表中已抽查数
			 * 如果抽查结果表中不存在该记录，给出提示信息
			 */
			List<SpotCheckResult> spotList = spotCheckResultServiceImpl.queryList(spotRequest);
			if (spotList != null && spotList.size() > 0) {
				SpotCheckResult spotResult = spotList.get(0);
				int spotResultState = spotResult.getState();
				spotCheckResultServiceImpl.delete(spotResult.getId());

				SpotCheckSchedule schedule = spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
				if (schedule != null) {
					// 未提交删除，不需要扣除批次抽查数量
					if (spotResultState != -1) {
						schedule.setSpotWebsiteNum(schedule.getSpotWebsiteNum() - 1);// 站点数减1
					}

					schedule.setModifyTime(
							DateUtils.parseStandardDateTime(DateUtils.formatStandardDateTime(new Date())));// 修改时间
					spotCheckScheduleServiceImpl.update(schedule);
				}
				List<ContractInfo> conList = getContractInfoList(orgSiteCode, DateUtils.formatStandardDate(new Date()));
				if (conList != null && conList.size() > 0) {
					int spotWebsiteNum = 0;

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("orgSiteCode", orgSiteCode);
					params.put("spotCheckSchedule", scheduleId);
					params.put("state", -1);

					// 封装页面左侧菜单统计数据
					List<Map<String, Object>> siteNumList = new ArrayList<Map<String, Object>>();
					List<SpotCheckResultRequest> stList = spotCheckResultServiceImpl.queryByMap(params);
					if (stList != null && stList.size() > 0) {
						for (int i = 0; i < stList.size(); i++) {
							Map<String, Object> map = new HashMap<String, Object>();
							SpotCheckResultRequest spotResultRequest = stList.get(i);
							map.put("province", spotResultRequest.getProvince());// 省名称
							map.put("siteNum", spotResultRequest.getSiteNum());// 站点个数

							siteNumList.add(map);
							spotWebsiteNum += spotResultRequest.getSiteNum();
						}
						resultMap.put("siteNumList", siteNumList);
					}

					resultMap.put("spotWebsiteNum", spotWebsiteNum);// 用于抽查提交页面中的抽查个数赋值
				}

				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("success", "考评结果删除成功");
				} else {
					resultMap.put("success", "抽查结果删除成功");
				}
			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("errorMsg", "考评结果表中不存在该记录");
				} else {
					resultMap.put("errorMsg", "抽查结果表中不存在该记录");
				}
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "删除考评结果异常");
			} else {
				resultMap.put("errorMsg", "删除抽查结果异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 数据提交页面---删除所有未提交的
	 * @author Na.Y --- 2016-9-18上午9:55:09
	 */
	public void deleteSpotResultBatch(){
		site_code_session = getCurrentSiteCode();
		Map<String, Object> resultMap=new HashMap<String, Object>();
		
		//String siteCode=request.getParameter("siteCode");//网站标识码
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String orgSiteCode=getCurrentUserInfo().getSiteCode();//当前登录人的编码
		
		try {
			//根据组织单位编码、省份、抽查进度id,查询抽查结果表
			SpotCheckResultRequest spotRequest=new SpotCheckResultRequest();
			if(StringUtils.isNotEmpty(scheduleId)){
				spotRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
			}
			spotRequest.setOrgSiteCode(orgSiteCode);
			spotRequest.setState(-1);
			spotRequest.setPageSize(Integer.MAX_VALUE);
			/**
			 * 如果抽查结果表中存在该记录，删除该记录，同时，更新抽查进度表中站点个数、更新抽查服务统计表中已抽查数
			 * 如果抽查结果表中不存在该记录，给出提示信息
			 */
			List<SpotCheckResult> spotList=spotCheckResultServiceImpl.queryList(spotRequest);
			if(spotList!=null && spotList.size()>0){
				for (SpotCheckResult spotCheckResult : spotList) {
					spotCheckResultServiceImpl.delete(spotCheckResult.getId());
				}
			}
			if(site_code_exp.indexOf(site_code_session)>=0){
				resultMap.put("success", "考评结果删除成功");
			}else{
				resultMap.put("success", "抽查结果删除成功");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			if(site_code_exp.indexOf(site_code_session)>=0){
				resultMap.put("errorMsg", "删除考评结果异常");
			}else{
				resultMap.put("errorMsg", "删除抽查结果异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 抽查提交页面--点击提交按钮 提交抽查结果数据
	 * @author cuichx --- 2016-5-4下午4:29:47
	 */
	public void subSpotResult(){
		site_code_session = getCurrentSiteCode();
		/**
		 * 通过组织单位编码和抽查进度表id，查询抽查结果表，将查询出来的结果中的state（抽查状态修改为0-未启动）
		 */
		Map<String, Object> resultMap=new HashMap<String, Object>();
		String scheduleId=request.getParameter("scheduleId");
		try {
			String orgSiteCode=getCurrentUserInfo().getSiteCode();//获取当前登录人的网站标识码（组织单位编码）
			if(StringUtils.isNotEmpty(scheduleId)){
				//批次抽查数据：本批次提交数据
				int spotWebsiteNum = 0;
				SpotCheckResultRequest spotRequest=new SpotCheckResultRequest();
				spotRequest.setOrgSiteCode(orgSiteCode);
				spotRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
				spotRequest.setPageSize(Integer.MAX_VALUE);
				
				List<SpotCheckResult> spotList=spotCheckResultServiceImpl.queryList(spotRequest);
				if(spotList!=null && spotList.size()>0){
					for (int i = 0; i < spotList.size(); i++) {
						SpotCheckResult spotResult=spotList.get(i);
						spotResult.setState(0);//抽查状态修改为0-未启动
						spotCheckResultServiceImpl.update(spotResult);
						spotWebsiteNum+=1;
					}
				}
				
				//提交时设置批次抽查数据
				SpotCheckSchedule spotCheckSchedule = spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
				spotCheckSchedule.setSpotWebsiteNum(spotWebsiteNum);
				spotCheckScheduleServiceImpl.update(spotCheckSchedule);
				
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("success", "提交考评结果数据成功");
				}else{
					resultMap.put("success", "提交抽查结果数据成功");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}else{
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("errorMsg", "不存在要提交的考评结果数据");
				}else{
					resultMap.put("errorMsg", "不存在要提交的抽查结果数据");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(site_code_exp.indexOf(site_code_session)>=0){
				resultMap.put("errorMsg", "提交考评结果数据异常");
			}else{
				resultMap.put("errorMsg", "提交抽查结果数据异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 增加站点时，页面数据初始化
	 * @author cuichx --- 2016-5-4下午2:27:13
	 */
	public void getSpotCheckSchedule() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String scheduleId = request.getParameter("scheduleId");// 抽查进度表id
		String groupNum = request.getParameter("groupNum");// 抽查组次
		String batchNum = request.getParameter("batchNum");// 抽查批次
		site_code_session = getCurrentSiteCode();
		try {
			if (StringUtils.isNotEmpty(scheduleId)) {// 抽查提交页面--点击增加站点 或者
														// 站点详情页面--点击增加站点-初始化抽查页面新建任务modal
				SpotCheckSchedule spotSchedule = spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
				if (spotSchedule != null) {
					// 批次
					resultMap.put("batchNum", spotSchedule.getBatchNum());
					// 组次
					resultMap.put("groupNum", spotSchedule.getGroupNum());
					// 任务名称
					resultMap.put("taskName", spotSchedule.getTaskName());
					// 开始日期
					resultMap.put("startDate", spotSchedule.getStartDate());
					// 结束日期
					resultMap.put("endDate", spotSchedule.getEndDate());
					// 抽查进度表id
					resultMap.put("scheduleId", spotSchedule.getId());

					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			} else if (StringUtils.isNotEmpty(batchNum) && StringUtils.isNotEmpty(groupNum)) {// 首页面点击添加站点
				String siteCode = getCurrentUserInfo().getSiteCode();// 组织单位编码（当前登录人编码）
				List<ContractInfo> conList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
				if (conList != null && conList.size() > 0) {

					SpotCheckScheduleRequest spotScheduleRequest = new SpotCheckScheduleRequest();
					spotScheduleRequest.setSiteCode(siteCode);
					spotScheduleRequest.setContractInfoId(conList.get(0).getId() + "");
					spotScheduleRequest.setBatchNum(Integer.valueOf(batchNum));
					spotScheduleRequest.setGroupNum(Integer.valueOf(groupNum));

					List<SpotCheckSchedule> spotScheduleList = spotCheckScheduleServiceImpl
							.queryList(spotScheduleRequest);
					if (spotScheduleList != null && spotScheduleList.size() > 0) {
						SpotCheckSchedule spotSchedule = spotScheduleList.get(0);
						// 批次
						resultMap.put("batchNum", batchNum);
						// 组次
						resultMap.put("groupNum", groupNum);
						// 任务名称
						resultMap.put("taskName", spotSchedule.getTaskName());
						// 开始日期
						resultMap.put("startDate", spotSchedule.getStartDate());
						// 结束日期
						resultMap.put("endDate", spotSchedule.getEndDate());

						// 抽查进度表id
						resultMap.put("scheduleId", spotSchedule.getId());

						writerPrint(JSONObject.fromObject(resultMap).toString());
					} else {
						if (site_code_exp.indexOf(site_code_session) >= 0) {
							resultMap.put("errorMsg", "考评结束，不能再次新增站点");
						} else {
							resultMap.put("errorMsg", "抽查结束，不能再次新增站点");
						}
						writerPrint(JSONObject.fromObject(resultMap).toString());
					}
				}

			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("errorMsg", "不存在考评进度表数据");
				} else {
					resultMap.put("errorMsg", "不存在抽查进度表数据");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "获取考评进度表数据异常");
			} else {
				resultMap.put("errorMsg", "获取抽查进度表数据异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 抽查提交页面数据初始化
	 * @author cuichx --- 2016-5-3下午3:42:51
	 */
	public void spotSubInit() {
		/**
		 * 1 如果页面传递过来的抽查进度表id不为空，直接通过抽查进度表，查询抽查进度表 2 如果页面传递抽查进度表id为空，
		 * 根据当前登录人,当前时间查询合同信息表，获取合同信息表id 根据当前登录人组织机构编码、合同信息表id、批次、组次查询抽查进度表 3
		 * 将查询结果返回页面
		 */
		site_code_session = getCurrentSiteCode();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject jsonObj = getJSONObject();
			if (jsonObj != null) {
				String batchNum = jsonObj.getString("batchNum");// 抽查批次
				String groupNum = jsonObj.getString("groupNum");// 抽查组次
				String scheduleId = jsonObj.getString("scheduleId");// 抽查进度表id
				String orgSiteCode = getCurrentUserInfo().getSiteCode();// 获取当前登录人的组织单位编码

				SpotCheckSchedule spotCheckSchedule = null;
				if (StringUtils.isNotEmpty(scheduleId)) {
					spotCheckSchedule = spotCheckScheduleServiceImpl.get(Integer.valueOf(scheduleId));
				} else {
					List<ContractInfo> conList = getContractInfoList(orgSiteCode,
							DateUtils.formatStandardDate(new Date()));
					if (conList != null && conList.size() > 0) {
						SpotCheckScheduleRequest spotRequest = new SpotCheckScheduleRequest();
						spotRequest.setSiteCode(orgSiteCode);
						spotRequest.setContractInfoId(conList.get(0).getId() + "");
						spotRequest.setBatchNum(Integer.valueOf(batchNum));
						spotRequest.setGroupNum(Integer.valueOf(groupNum));
						List<SpotCheckSchedule> spotList = spotCheckScheduleServiceImpl.queryList(spotRequest);
						if (spotList != null && spotList.size() > 0) {
							spotCheckSchedule = spotList.get(0);
						}
					}
				}

				if (spotCheckSchedule != null) {
					resultMap.put("batchNum", spotCheckSchedule.getBatchNum());// 批次
					resultMap.put("groupNum", spotCheckSchedule.getGroupNum());// 组次
					resultMap.put("taskName", spotCheckSchedule.getTaskName());// 任务名称

					resultMap.put("startDate", DateUtils
							.formatStandardDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));// 监测开始日期
					resultMap.put("endDate",
							DateUtils.formatStandardDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));// 监测结束日期
					resultMap.put("spotWebsiteNum", spotCheckSchedule.getSpotWebsiteNum());// 站点个数

					resultMap.put("scheduleId", spotCheckSchedule.getId());

				} else {
					if (site_code_exp.indexOf(site_code_session) >= 0) {
						resultMap.put("errorMsg", "考评进度表中不存在该记录");
					} else {
						resultMap.put("errorMsg", "抽查进度表中不存在该记录");
					}
					writerPrint(JSONObject.fromObject(resultMap).toString());
					return;
				}

				/**
				 * 列表数据初始化 1 首先根据省分组查询（组织单位编码
				 * 、抽查进度表id、状态（-1:未提交）），获取每个省的站点个数--用于页面左侧菜单初始化 2 根据 组织单位编码
				 * 、抽查进度表id、状态（-1:未提交）查询抽查任务结果表，获取所有数据展示在页面
				 */
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("spotCheckSchedule", spotCheckSchedule.getId());
				params.put("state", -1);

				// 封装页面左侧菜单统计数据
				List<Map<String, Object>> siteNumList = new ArrayList<Map<String, Object>>();
				List<SpotCheckResultRequest> spotList = spotCheckResultServiceImpl.queryByMap(params);
				if (spotList != null && spotList.size() > 0) {
					for (int i = 0; i < spotList.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						SpotCheckResultRequest spotResultRequest = spotList.get(i);
						map.put("province", spotResultRequest.getProvince());// 省名称
						map.put("siteNum", spotResultRequest.getSiteNum());// 站点个数

						siteNumList.add(map);
					}
					resultMap.put("siteNumList", siteNumList);
				}

				SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
				resultRequest.setSpotCheckSchedule(spotCheckSchedule.getId());// 抽查进度表id
				resultRequest.setState(-1);// 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
											// ，3：报告完成）
				resultRequest.setPageSize(Integer.MAX_VALUE);
				// 封装页面列表数据
				List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
				List<SpotCheckResult> resultList = spotCheckResultServiceImpl.queryList(resultRequest);

				resultMap.put("spotWebsiteNum", resultList.size());// 此处抽查站点个数
				if (resultList != null && resultList.size() > 0) {
					for (int i = 0; i < resultList.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						SpotCheckResult result = resultList.get(i);
						String city = "";// 市
						if (StringUtils.isNotEmpty(result.getCity())) {
							city = result.getCity();
						}
						map.put("city", city);

						String county = "";// 县
						if (StringUtils.isNotEmpty(result.getCounty())) {
							county = result.getCounty();
						}
						map.put("county", county);

						String siteCode = "";// 网站标识码
						if (StringUtils.isNotEmpty(result.getSiteCode())) {
							siteCode = result.getSiteCode();
						}
						map.put("siteCode", siteCode);

						String siteName = "";// 网站名称
						if (StringUtils.isNotEmpty(result.getSiteName())) {
							siteName = result.getSiteName();
						}
						map.put("siteName", siteName);

						String url = "";// url
						if (StringUtils.isNotEmpty(result.getUrl())) {
							url = result.getUrl();
						}
						map.put("url", url);

						// 通过网站标识码查询站点信息表，获取主办单位名称
						DatabaseInfoRequest dataRequest = new DatabaseInfoRequest();
						dataRequest.setSiteCode(siteCode);
						String director = "";// 主办单位
						List<DatabaseInfo> dataList = databaseInfoServiceImpl.queryList(dataRequest);
						if (dataList != null && dataList.size() > 0) {
							director = dataList.get(0).getDirector();
						}
						map.put("director", director);

						returnList.add(map);
					}
					resultMap.put("returnList", returnList);
					writerPrint(JSONObject.fromObject(resultMap).toString());
				} else {
					if (site_code_exp.indexOf(site_code_session) >= 0) {
						resultMap.put("errorMsg", "不存在考评结果数据");
					} else {
						resultMap.put("errorMsg", "不存在抽查结果数据");
					}
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			} else {
				resultMap.put("errorMsg", "页面传递参数不能为空");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "考评结果提交页面初始化异常");
			} else {
				resultMap.put("errorMsg", "抽查结果提交页面初始化异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取抽查批次
	 * @author cuichx --- 2016-4-28下午7:55:28
	 */
	public void getSchedule() {
		site_code_session = getCurrentSiteCode();
		// 封装返回数据
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String key = "";
			String datePd = "";
			JSONObject json = getJSONObject();
			if (json != null) {
				key = json.getString("key");// 关键字
				datePd = json.getString("datePd");// 创建时间
			}

			logger.info("getSchedule key:" + key + "  datePd:" + datePd);
			String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
			int spotSum = 0;// 抽查总数
			int spotNum = 0;// 已抽查次数
			int remainNum = 0;// 剩余可抽查次数
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			// 封装批次集合 主要用于前台区分批次和组的关系
			List<Integer> batchNumList = new ArrayList<Integer>();
			List<ContractInfo> conList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
			List<ContractInfo> conLists = getContractInfoList(siteCode, null);
			logger.info("conList size:" + conList.size() + "  siteCode:" + siteCode + "new Date()"
					+ DateUtils.formatStandardDateTime(new Date()));
			if (CollectionUtils.isEmpty(conList)) {
				resultMap.put("NoContract", "no");
			} else {
				resultMap.put("NoContract", "yes");
				if (conLists != null && conLists.size() > 0) {
					if (conList != null && conList.size() > 0) {

						ContractInfo contractInfo = conList.get(0);
						if (StringUtils.isNotEmpty(contractInfo.getContractCode())) {
							// 当前服务中的合同号
							resultMap.put("curredContractCode", contractInfo.getContractCode());
						} else {
							// 当前服务中的临时合同号
							resultMap.put("curredContractCode", contractInfo.getContractCodeTemp());
						}

						SpotCheckInfoRequest spotCheckInfoRequest = new SpotCheckInfoRequest();
						spotCheckInfoRequest.setSiteCode(siteCode);
						spotCheckInfoRequest.setContractInfoId(conList.get(0).getId());
						logger.info("conList id:" + conList.get(0).getId());
						// 抽查服务统计表
						
						List<SpotCheckInfo> queryInfo = spotCheckInfoServiceImpl.queryList(spotCheckInfoRequest);
						for (SpotCheckInfo spotCheckInfo : queryInfo) {
							spotSum = spotCheckInfo.getSpotSum();
							spotNum = spotCheckInfo.getSpotNum();
						}
					} else {
						spotSum = 0;
						spotNum = 0;
					}
					// 查询抽查进度表
					SpotCheckScheduleRequest spotCheckScheduleRequest = new SpotCheckScheduleRequest();
					spotCheckScheduleRequest.setPageSize(Integer.MAX_VALUE);
					spotCheckScheduleRequest.setSiteCode(siteCode);
					if (StringUtils.isNotEmpty(key)) {
						spotCheckScheduleRequest.setTaskName(key);
					}
					if (StringUtils.isNotEmpty(datePd)) {
						spotCheckScheduleRequest.setEndTime(DateUtils.formatStandardDateTime(new Date()));
						spotCheckScheduleRequest
								.setBeginTime(DateUtils.getNextDay(new Date(), -Integer.valueOf(datePd)));
					}
					// 设置排序 先按批次排序 再按组次排序
					List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
					QueryOrder siteQueryOrder1 = new QueryOrder("batch_num", QueryOrderType.DESC);
					QueryOrder siteQueryOrder2 = new QueryOrder("group_num", QueryOrderType.DESC);
					queryOrderList.add(siteQueryOrder1);
					queryOrderList.add(siteQueryOrder2);
					spotCheckScheduleRequest.setQueryOrderList(queryOrderList);

					List<SpotCheckSchedule> spotCheckList = spotCheckScheduleServiceImpl
							.queryList(spotCheckScheduleRequest);
					if (spotCheckList != null && spotCheckList.size() > 0) {

						for (int i = 0; i < spotCheckList.size(); i++) {
							SpotCheckSchedule spotCheckSchedule = spotCheckList.get(i);
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("scheduleId", spotCheckSchedule.getId());// 抽查进度表id
							map.put("siteCode", spotCheckSchedule.getSiteCode());// 网站标识码
							// 任务名称
							if (StringUtils.isNotBlank(spotCheckSchedule.getTaskName())) {
								map.put("taskName", spotCheckSchedule.getTaskName());
							} else {
								map.put("taskName", "");
							}

							if (StringUtils.isNotEmpty(spotCheckSchedule.getContractInfoId())) {
								ContractInfo contractInfo = contractInfoServiceImpl
										.get(Integer.valueOf(spotCheckSchedule.getContractInfoId()));
								if (contractInfo != null) {
									if (StringUtils.isNotEmpty(contractInfo.getContractCode())) {
										// 正式合同号
										map.put("contractCode", contractInfo.getContractCode());
									} else {
										// 临时合同号
										map.put("contractCode", contractInfo.getContractCodeTemp());
									}
								}

							}

							// 监测开始日期
							map.put("dateStart", DateUtils
									.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));
							// 监测结束日期
							map.put("endStart",
									DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));
							// 监测状态
							map.put("state", spotCheckSchedule.getStatus());
							map.put("batchNum", spotCheckSchedule.getBatchNum());
							map.put("groupNum", spotCheckSchedule.getGroupNum());
							map.put("webSum", spotCheckSchedule.getSpotWebsiteNum());
							// 查询服务周期id
							ServicePeriodRequest servicePeriodRequest = new ServicePeriodRequest();
							servicePeriodRequest.setSpotCheckScheduleId(spotCheckSchedule.getId());
							servicePeriodRequest.setSiteCode(siteCode);
							List<ServicePeriod> servicePeriodList = servicePeriodServiceImpl
									.queryList(servicePeriodRequest);
							if (servicePeriodList.size() > 0) {
								Integer servicePeriodId = servicePeriodList.get(0).getId();
								// 完成报告数量
								ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
								reportWordResultRequest.setServicePeriodId(servicePeriodId);
								reportWordResultRequest.setPageNo(0);
								reportWordResultRequest.setPageSize(Integer.MAX_VALUE);
								reportWordResultRequest.setGroupBy("site_code");

								List<ReportWordResult> wordList = reportWordResultServiceImpl
										.queryList(reportWordResultRequest);
								Integer successReportWordNum = wordList.size();
								map.put("successReportWordNum", successReportWordNum);

								// 反馈整改数量
								SpotCheckNoticeRequest spotCheckNoticeRequest = new SpotCheckNoticeRequest();
								spotCheckNoticeRequest.setServicePeriodId(servicePeriodId);
								spotCheckNoticeRequest.setCheckReportResults(0);
								spotCheckNoticeRequest.setPageNo(0);
								spotCheckNoticeRequest.setPageSize(Integer.MAX_VALUE);
								spotCheckNoticeRequest.setGroupBy("site_code");
								List<SpotCheckNotice> SpotCheckNoticeList = spotCheckNoticeServiceImpl
										.queryList(spotCheckNoticeRequest);
								map.put("checkReportResultNum", SpotCheckNoticeList.size());
								spotCheckNoticeRequest.setCheckReportResults(null);
								spotCheckNoticeRequest.setIsRead(1);
								List<SpotCheckNotice> IsReadNoticeList = spotCheckNoticeServiceImpl
										.queryList(spotCheckNoticeRequest);
								map.put("IsReadNoticeNum", IsReadNoticeList.size());
							} else {
								map.put("successReportWordNum", 0);
								map.put("checkReportResultNum", 0);
								map.put("IsReadNoticeNum", 0);
								map.put("servicePeriodId", 0);
							}

							// 循环遍历批次集合，如果批次集合中存在该批次，不添加；否则将批次添加到集合里面
							if (batchNumList != null && batchNumList.size() > 0) {
								// 获取批次
								int batchNum = spotCheckSchedule.getBatchNum();
								boolean existFlag = false;
								for (int j = 0; j < batchNumList.size(); j++) {
									if (batchNum == batchNumList.get(j)) {
										existFlag = true;
									}
								}
								// 批次集合中不存在该数据，将该批次添加到批次集合中
								if (!existFlag) {
									batchNumList.add(spotCheckSchedule.getBatchNum());
								}
							} else {
								batchNumList.add(spotCheckSchedule.getBatchNum());
							}
							returnList.add(map);
						}
					}
				}
			}
			// add by Na.Y 20160914,剩余可抽查次数
			remainNum = spotSum - spotNum;
			if (remainNum < 0) {
				remainNum = 0;
			}
			resultMap.put("spotSum", spotSum);
			resultMap.put("spotNum", spotNum);
			resultMap.put("remainNum", remainNum);
			resultMap.put("returnList", returnList);
			resultMap.put("batchNumList", batchNumList);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "获取考评数据异常");
			} else {
				resultMap.put("errorMsg", "获取抽查数据异常");
			}
		}
	}

	/**
	 * @Description: 复制任务站点，历史抽查任务为完成的数据
	 * @author: yangshuai --- 2016-5-18下午1:51:53
	 * @return
	 * @throws Exception
	 */
	public void getCloseSchedule() {
		site_code_session = getCurrentSiteCode();
		// 封装返回数据
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			// 封装批次集合 主要用于前台区分批次和组的关系
			List<Integer> batchNumList = new ArrayList<Integer>();
			List<ContractInfo> conList = getContractInfoList(siteCode, null);
			if (conList != null && conList.size() > 0) {
				// 查询抽查进度表
				SpotCheckScheduleRequest spotCheckScheduleRequest = new SpotCheckScheduleRequest();
				spotCheckScheduleRequest.setPageSize(Integer.MAX_VALUE);
				spotCheckScheduleRequest.setSiteCode(siteCode);
				// 设置排序 先按批次排序 再按组次排序
				List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder1 = new QueryOrder("batch_num", QueryOrderType.DESC);
				QueryOrder siteQueryOrder2 = new QueryOrder("group_num", QueryOrderType.DESC);
				queryOrderList.add(siteQueryOrder1);
				queryOrderList.add(siteQueryOrder2);
				spotCheckScheduleRequest.setQueryOrderList(queryOrderList);
				spotCheckScheduleRequest.setStatus(2);

				List<SpotCheckSchedule> spotCheckList = spotCheckScheduleServiceImpl
						.queryList(spotCheckScheduleRequest);
				if (spotCheckList != null && spotCheckList.size() > 0) {

					for (int i = 0; i < spotCheckList.size(); i++) {
						SpotCheckSchedule spotCheckSchedule = spotCheckList.get(i);
						SpotCheckResultRequest spotCheckResultRequest = new SpotCheckResultRequest();
						spotCheckResultRequest.setSpotCheckSchedule(spotCheckSchedule.getId());
						spotCheckResultRequest.setPageSize(Integer.MAX_VALUE);
						List<SpotCheckResult> spotCheckResulrList = spotCheckResultServiceImpl
								.queryList(spotCheckResultRequest);
						String checkResultAllSiteCode = "";
						String checkResultNotAllSiteCode = "";
						for (SpotCheckResult spotCheckResult : spotCheckResulrList) {
							if (spotCheckResult.getCheckResult() == 0) {
								checkResultAllSiteCode += spotCheckResult.getSiteCode() + "_";
								checkResultNotAllSiteCode += spotCheckResult.getSiteCode() + "_";
							} else {
								checkResultAllSiteCode += spotCheckResult.getSiteCode() + "_";
							}
						}
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("scheduleId", spotCheckSchedule.getId());// 抽查进度表id
						map.put("siteCode", spotCheckSchedule.getSiteCode());// 网站标识码
						// 任务名称
						if (StringUtils.isNotBlank(spotCheckSchedule.getTaskName())) {
							map.put("taskName", spotCheckSchedule.getTaskName());
						} else {
							map.put("taskName", "");
						}

						// 监测开始日期
						map.put("dateStart",
								DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getStartDate())));
						// 监测结束日期
						map.put("endStart",
								DateUtils.formatDate(DateUtils.parseStandardDate(spotCheckSchedule.getEndDate())));
						// 监测状态
						map.put("state", spotCheckSchedule.getStatus());
						map.put("batchNum", spotCheckSchedule.getBatchNum());
						map.put("groupNum", spotCheckSchedule.getGroupNum());
						map.put("webSum", spotCheckSchedule.getSpotWebsiteNum());
						map.put("allSiteCode", checkResultAllSiteCode);
						map.put("notAllSiteCode", checkResultNotAllSiteCode);
						map.put("contractInfoId", conList.get(0).getId());
						// 循环遍历批次集合，如果批次集合中存在该批次，不添加；否则将批次添加到集合里面
						if (batchNumList != null && batchNumList.size() > 0) {
							// 获取批次
							int batchNum = spotCheckSchedule.getBatchNum();
							boolean existFlag = false;
							for (int j = 0; j < batchNumList.size(); j++) {
								if (batchNum == batchNumList.get(j)) {
									existFlag = true;
								}
							}
							// 批次集合中不存在该数据，将该批次添加到批次集合中
							if (!existFlag) {
								batchNumList.add(spotCheckSchedule.getBatchNum());
							}
						} else {
							batchNumList.add(spotCheckSchedule.getBatchNum());
						}
						returnList.add(map);
					}
				}
			}
			resultMap.put("returnList", returnList);
			resultMap.put("batchNumList", batchNumList);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				resultMap.put("errorMsg", "获取考评数据异常");
			} else {
				resultMap.put("errorMsg", "获取抽查数据异常");
			}
		}
	}

	/**
	 * @Description: 获取抽查结果
	 * @author sunjiaqi --- 2016-1-4下午07:14:16
	 */
	public void getCheckResult() {
		// 依据当前登陆的组织单位在spot_check_schedule抽查进度表里面获取批次
		try {
			String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
			Integer scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
			Integer pos = Integer.parseInt(request.getParameter("pos"));
			Integer size = Integer.parseInt(request.getParameter("size"));
			String iSortCol = request.getParameter("iSortCol_0");
			String mDataProp = request.getParameter("mDataProp_" + iSortCol);// 获取排序字段
			if (null == mDataProp) {
				mDataProp = "site_code";
			}
			String orderType = request.getParameter("sSortDir_0");// 获取排序类型，正序或者倒序
			if (null == orderType) {
				orderType = "asc";
			}

			SpotCheckResultRequest spotCheckResultRequest = new SpotCheckResultRequest();
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			QueryOrder codeOrder = null;
			if (orderType.equals("asc")) {
				codeOrder = new QueryOrder(mDataProp, QueryOrderType.ASC);
			} else {
				codeOrder = new QueryOrder(mDataProp, QueryOrderType.DESC);
			}
			List<QueryOrder> querySiteList1 = new ArrayList<QueryOrder>();
			querySiteList1.add(codeOrder);
			spotCheckResultRequest.setQueryOrderList(querySiteList1);
			spotCheckResultRequest.setPageNo(pos);
			spotCheckResultRequest.setPageSize(size);
			spotCheckResultRequest.setOrgSiteCode(siteCode);
			spotCheckResultRequest.setSpotCheckSchedule(scheduleId);
			PageVo<SpotCheckResult> query = spotCheckResultServiceImpl.query(spotCheckResultRequest);
			List<SpotCheckResult> spotList = query.getData();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < spotList.size(); j++) {
				HashMap<String, Object> mapSpot = new HashMap<String, Object>();
				SpotCheckResult spotCheckResult = spotList.get(j);
				mapSpot.put("siteCode", spotCheckResult.getSiteCode());
				mapSpot.put("siteName", spotCheckResult.getSiteName());
				mapSpot.put("state", spotCheckResult.getState());
				list.add(mapSpot);
			}
			resultMap.put("body", list);
			resultMap.put("items", list);
			resultMap.put("totalRecords", list.size());
			resultMap.put("iTotalDisplayRecords", query.getRecordSize());
			resultMap.put("hasMoreItems", false);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description: 查询套餐码表获取可选套餐类型
	 * @author sunjiaqi --- 2016-1-4下午03:54:04
	 */
	public void getComboInfos() {
		// 在combo套餐表中获取is_valid为有效的套餐类型返回到前端页面
		try {
			ComboRequest comboRequest = new ComboRequest();
			comboRequest.setIsValid(1);
			List<Combo> cblist = comboServiceImpl.queryList(comboRequest);
			if (null != cblist && cblist.size() > 0) {
				writerPrint(JSONArray.fromObject(cblist).toString());
			} else {
				writerPrint("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 判断是否有要下载的文件
	 * @author sunjiaqi --- 2016-1-5下午05:02:53
	 */
	public void hasReport() {
		// 依据批次和选择的填报单位siteCodes集合判断是否有能够下载的文件
		// 执行一次预判断能够避免页面跳转到空页面
		String checkNumber = request.getParameter("checkNumber");// 批次
		String spotsiteCodes = request.getParameter("spotsiteCodes");// 网站抽查的填报单位siteCode
		String orgSiteCode = getCurrentSiteCode();// 获取当前登陆组织单位
		List<String> fileNames = new ArrayList<String>();
		int spotNum = 0;
		try {
			if (StringUtils.isNotEmpty(checkNumber)) {
				spotNum = Integer.parseInt(checkNumber);
				SpotCheckResultRequest spotCheckResultRequest = new SpotCheckResultRequest();
				spotCheckResultRequest.setSpotCheckSchedule(spotNum);
				spotCheckResultRequest.setOrgSiteCode(orgSiteCode);
				if (StringUtils.isNotEmpty(spotsiteCodes)) {
					String[] siteCodes = spotsiteCodes.split(",");// 按照,拆分分别记录
					spotCheckResultRequest.setSiteCodes(siteCodes);
				}
				spotCheckResultRequest.setPageSize(Integer.MAX_VALUE);
				List<SpotCheckResult> queryList = spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
				for (int i = 0; i < queryList.size(); i++) {
					SpotCheckResult spotCheckResult = queryList.get(i);
					ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
					reportWordResultRequest.setSiteCode(spotCheckResult.getSiteCode());
					reportWordResultRequest.setType(0);
					reportWordResultRequest.setServicePeriodId(spotNum);
					List<ReportWordResult> rwrList = reportWordResultServiceImpl.queryList(reportWordResultRequest);
					if (rwrList != null && rwrList.size() > 0) {
						ReportWordResult rwr = rwrList.get(0);
						String path = ServletActionContext.getServletContext().getRealPath("/");
						path = path + "upload";
						String wordUrl = path + rwr.getWordUrl();
						String pdfUrl = path + rwr.getPdfUrl();
						String excelUrl = path + rwr.getExcelUrl();

						if (StringUtils.isNotEmpty(wordUrl)) {
							fileNames.add(wordUrl);
						}
						if (StringUtils.isNotEmpty(pdfUrl)) {
							fileNames.add(pdfUrl);
						}
						if (StringUtils.isNotEmpty(excelUrl)) {
							fileNames.add(excelUrl);
						}
					}
				}
				if (fileNames.size() > 0) {
					writerPrint("true");
				} else {
					writerPrint("false");
				}
			} else {
				writerPrint("false");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("导出报告：" + orgSiteCode + "  导出 异常！！！错误信息=" + e.getMessage());
		}
	}

	/**
	 * @Description: 网站抽查结果批量导出报告
	 * @author zhurk --- 2015-12-11下午1:30:47
	 */
	public void checkExportReport() {
		String checkNumber = request.getParameter("checkNumber");// 批次
		String spotsiteCodes = request.getParameter("spotsiteCodes");// 网站抽查的填报单位siteCode
		String orgSiteCode = getCurrentSiteCode();// 获取当前登陆组织单位
		List<String> fileNames = new ArrayList<String>();
		int spotNum = Integer.parseInt(checkNumber);
		try {
			SpotCheckResultRequest spotCheckResultRequest = new SpotCheckResultRequest();
			spotCheckResultRequest.setSpotCheckSchedule(spotNum);
			spotCheckResultRequest.setOrgSiteCode(orgSiteCode);
			if (StringUtils.isNotEmpty(spotsiteCodes)) {
				String[] siteCodes = spotsiteCodes.split(",");// 按照,拆分分别记录
				spotCheckResultRequest.setSiteCodes(siteCodes);
			}
			spotCheckResultRequest.setPageSize(Integer.MAX_VALUE);
			List<SpotCheckResult> queryList = spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
			for (int i = 0; i < queryList.size(); i++) {
				SpotCheckResult spotCheckResult = queryList.get(i);
				ReportWordResultRequest reportWordResultRequest = new ReportWordResultRequest();
				reportWordResultRequest.setSiteCode(spotCheckResult.getSiteCode());
				reportWordResultRequest.setType(0);
				reportWordResultRequest.setServicePeriodId(spotNum);
				List<ReportWordResult> rwrList = reportWordResultServiceImpl.queryList(reportWordResultRequest);
				if (rwrList != null && rwrList.size() > 0) {
					ReportWordResult rwr = rwrList.get(0);
					String path = ServletActionContext.getServletContext().getRealPath("/");
					path = path + "upload";
					String wordUrl = path + rwr.getWordUrl();
					String pdfUrl = path + rwr.getPdfUrl();
					String excelUrl = path + rwr.getExcelUrl();

					if (StringUtils.isNotEmpty(wordUrl)) {
						fileNames.add(wordUrl);
					}
					if (StringUtils.isNotEmpty(pdfUrl)) {
						fileNames.add(pdfUrl);
					}
					if (StringUtils.isNotEmpty(excelUrl)) {
						fileNames.add(excelUrl);
					}
				}
			}
			if (fileNames.size() > 0) {
				DownFiles df = new DownFiles();
				Map<String, String> fileMap = df.getNamemap(fileNames);
				outPutFile(fileMap.get("filePath"), "checkSpot.zip");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("导出报告：" + orgSiteCode + "  导出 异常！！！错误信息=" + e.getMessage());
		}
	}

	/**
	 * @Description:网站抽查结果批量导出成绩单
	 * @author zhurk --- 2015-12-10下午2:00:39
	 */
	public void checkExportExcel() {
		site_code_session = getCurrentSiteCode();
		// 依据现则的批次和勾选的site_code填报单位集合生成成绩单
		String checkNumber = request.getParameter("checkNumber");// 批次
		String spotsiteCodes = request.getParameter("spotsiteCodes");// 网站抽查的填报单位siteCode
		String orgSiteCode = getCurrentSiteCode();// 获取当前登陆组织单位
		try {
			if (StringUtils.isEmpty(orgSiteCode)) {
				logger.info("抽查结果为空，导出异常!!!");
				return;
			}
			String fileName = "网站抽查-成绩单(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			if(site_code_exp.indexOf(site_code_session)>=0){
				fileName = "网站考评-成绩单(" + DateUtils.formatStandardDate(new Date()) + ").xls";
			}
			String title = "成绩单";
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			Object[] obj1 = new Object[] { "网站标识码", "网站名称", "不连通(次)", "不可用链接(个)", "内容保障问题(个)", "字词问题(次)", "安全问题(次)",
					"新稿件(条)", "健康指数" };
			list.add(obj1);
			SpotCheckResultRequest spotCheckResultRequest = new SpotCheckResultRequest();
			if (StringUtils.isNotEmpty(checkNumber)) {
				int spotNum = Integer.parseInt(checkNumber);
				spotCheckResultRequest.setSpotCheckSchedule(spotNum);
			}
			spotCheckResultRequest.setOrgSiteCode(orgSiteCode);
			if (StringUtils.isNotEmpty(spotsiteCodes)) {
				String[] siteCodes = spotsiteCodes.split(",");// 按照,拆分分别记录
				spotCheckResultRequest.setSiteCodes(siteCodes);
			}
			spotCheckResultRequest.setPageSize(Integer.MAX_VALUE);
			List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("site_code", QueryOrderType.ASC);
			querySiteList.add(siteQueryOrder);
			spotCheckResultRequest.setQueryOrderList(querySiteList);
			List<SpotCheckResult> queryList = spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
			for (int i = 0; i < queryList.size(); i++) {
				SpotCheckResult spotCheckResult = queryList.get(i);
				Object[] obj = new Object[9];
				obj[0] = spotCheckResult.getSiteCode();
				obj[1] = spotCheckResult.getSiteName();
				list.add(obj);
			}
			ExportExcel.spotCheckExcel(fileName, title, list);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.info("成绩单：" + orgSiteCode + "  导出 异常！！！错误信息=" + e.getMessage());
		}
	}

	/**
	 * @Description: 添加地方或部委数据
	 * @author zhurk --- 2015-12-8上午10:14:03
	 */
	public void getLocalAndMinistries() {
		//省级--获取前两位  市级--获取前四位  县级--获取前六位
		String siteCodeLike = getSiteCodeLike();
		//获取当前登录人的名称
		String currentCity = getCurrentUserInfo().getUserName();
		try {
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			//封装所有的省份
			List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
			//当前省份
			Map<String, Object> localMap = new HashMap<String, Object>();
			localMap.put("local", currentCity);
			localMap.put("siteCode", getCurrentSiteCode());
			items.add(localMap);
			
			//其他省份(出去当前省份)
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("notLikeSiteCode", siteCodeLike);
			hashMap.put("province", "province");
			List<DatabaseInfo> queryList = databaseInfoServiceImpl.getLocalData(hashMap);
			for (int i = 0; i < queryList.size(); i++) {
				DatabaseInfo database = queryList.get(i);
				Map<String, Object> item = new HashMap<String, Object>();
				if (database.getSiteCode().startsWith("bm")) {
					item.put("ministries", database.getProvince());//部委
				} else {
					item.put("local", database.getProvince());//省份
				}
				item.put("siteCode", database.getSiteCode());
				items.add(item);
			}
			resultMap.put("list", items);
			resultMap.put("CurrentSiteCode", getCurrentSiteCode());
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 获取地方树的数据
	 * @author zhurk --- 2015-12-08上午11:26:59
	 */
	public void getCheckResults() {
		try {
			String orgSiteCode = request.getParameter("cityCode");
			if (StringUtils.isEmpty(orgSiteCode)) {
				orgSiteCode = getCurrentSiteCode();
			}else{
				orgSiteCode=orgSiteCode.substring(0, 6);
			}
			DatabaseTreeInfoRequest treeInfo = new DatabaseTreeInfoRequest();
			treeInfo.setSiteCode(orgSiteCode);
			treeInfo.setIsBigdata(1);
			List<DatabaseTreeInfo> treeInfoList = databaseTreeInfoServiceImpl.queryList(treeInfo);
			String code = "";
			if(treeInfoList.size()>0 && treeInfoList != null){
				code = treeInfoList.get(0).getCode();
			}
			if(!code.equals("")){
				treeInfo.setSiteCode(null);
				treeInfo.setCode(code);
			}
			List<TreeVo> treelist = null;
			List<TreeVo> list = new ArrayList<TreeVo>();
			if(treeInfoList.size()>0 && treeInfoList !=null){
			if(orgSiteCode.equals("bm0100")){//bm0100处理
				treeInfo.setParentId(treeInfoList.get(0).getId());
				treelist = databaseTreeInfoServiceImpl.queryTreeBM(treeInfo);
				String pId = "";
				if(treelist.size()>0){
					for (TreeVo treeVo : treelist) {
						if(treeVo.getpId().equals("0")){
							pId = treeVo.getId();
						}else {
							treeVo.setpId(pId);
						}
					}
				}
			}else if(orgSiteCode.equals("110000") || orgSiteCode.equals("120000") || orgSiteCode.equals("310000") || orgSiteCode.equals("500000") && treeInfoList.get(0).getIsBm() == 0){
				//北京，天津，上海，重庆 直辖市处理
				if(treeInfoList.size()>0){
					treeInfo.setId(treeInfoList.get(0).getId());
					treelist = databaseTreeInfoServiceImpl.queryTree(treeInfo);
					if(treelist.size()>0){
						if(treelist.get(0).getIsBm() == 0 && treelist.get(0).getLevel() == 2){
							TreeVo treeMunicipality = new TreeVo();
							treeMunicipality.setId("Municipality"+orgSiteCode.substring(0, 6)); 
							treeMunicipality.setName("市级网站");
							treeMunicipality.setpId(String.valueOf(treeInfoList.get(0).getId()));
							treeMunicipality.setIsParent("true");
							list.add(treeMunicipality);
						}
						for (TreeVo treeVo : treelist) {
							if(treeVo.getLevel() == 3 && treeVo.getIsParent().equals("false") && treeVo.getSiteCode().length()==10 && treeVo.getIsBm() == 0){
								treeVo.setpId("Municipality"+orgSiteCode.substring(0, 6));
							}
						}
						list.addAll(treelist);
					}
					}
			}else if(treeInfoList.get(0).getIsBm() == 0){//省级网站正常登陆
					treeInfo.setId(treeInfoList.get(0).getId());
					treelist = databaseTreeInfoServiceImpl.queryTree(treeInfo);
					if(treelist.size()>0){
					if(treelist.get(0).getIsBm() == 0 && treelist.get(0).getLevel() == 2){
						//拼接省级网站文件夹
						TreeVo treeProvince = new TreeVo();
						//自定义文件夹id
						treeProvince.setId("province"+orgSiteCode.substring(0, 6));
						treeProvince.setName("省级网站");
						//文件夹父id为省id
							treeProvince.setpId(String.valueOf(treeInfoList.get(0).getId()));
						treeProvince.setIsParent("true");
						list.add(treeProvince);
					}
					//市级网站父id
					String cpId = "";
					for (TreeVo treeVo : treelist) {
						if(treeVo.getLevel() == 3 && treeVo.getIsParent().equals("false") && treeVo.getSiteCode().length()==10 && treeVo.getIsBm() == 0){
							//将父id设为自定义的文件夹id
							treeVo.setpId("province"+orgSiteCode.substring(0, 6));
						}
						if(treeVo.getLevel() == 3 && treeVo.getIsParent().equals("true") && treeVo.getSiteCode().length()==6 && treeVo.getIsBm() == 0){
							//拼接市级网站文件夹
							TreeVo treeCity = new TreeVo();
							//自定义文件夹id
							treeCity.setId("city"+treeVo.getSiteCode());
							treeCity.setName("市级网站");
							//文件夹父id为循环的市级id
							treeCity.setpId(treeVo.getId());
							treeCity.setIsParent("true");
							cpId = "city"+treeVo.getSiteCode();
							list.add(treeCity);
						}
						if(treeVo.getLevel() == 4 && treeVo.getIsParent().equals("false") && treeVo.getSiteCode().length()==10 && treeVo.getIsBm() == 0){
							//将父id设为自定义的文件夹id
							treeVo.setpId(cpId);
						}
					}
					}
					list.addAll(treelist);
				}else if(treeInfoList.get(0).getIsBm() == 1){//部委逻辑
					treeInfo.setId(treeInfoList.get(0).getId());
					treelist = databaseTreeInfoServiceImpl.queryTree(treeInfo);
					//部委文件夹
					TreeVo treeMinistries = new TreeVo();
					//自定义文件夹id
					treeMinistries.setId("Ministries"+orgSiteCode.substring(0, 6));
					if(treelist.size()>0 && treelist != null){
						treeMinistries.setName(treelist.get(0).getName());
					}
					treeMinistries.setpId(String.valueOf(treeInfoList.get(0).getId()));
					treeMinistries.setName(treelist.get(0).getName());
					treeMinistries.setpId(String.valueOf(treeInfoList.get(0).getId()));
					treeMinistries.setIsParent("true");
					list.add(treeMinistries);
					if(treelist.size()>0){
						for (TreeVo treeVo : treelist) {
							if(treeVo.getLevel() == 3 && treeVo.getIsParent().equals("false") && treeVo.getSiteCode().length()==10){
								//将父id设为自定义的文件夹id
								treeVo.setpId("Ministries"+orgSiteCode.substring(0, 6));
							}
						}
					}
					list.addAll(treelist);
				}
			}
			if(orgSiteCode.equals("bm2900")){
				if(list.size()>0){
					for(TreeVo treeVo:list){
						if(StringUtils.isNotEmpty(treeVo.getSiteCode()) ){
							if(treeVo.getSiteCode().length()==6 && !treeVo.getSiteCode().equals("bm2900")){
								String siteName=treeVo.getName();
								treeVo.setName(siteName.substring(0, siteName.length()-2));
							}
						}
					}
				}
			}
			if(orgSiteCode.equals("bm0100")){
				writerPrint(JSONArray.fromObject(treelist).toString());
			}else{
				writerPrint(JSONArray.fromObject(list).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @Description: 新建任务的抽查结果/增加站点保存
	 * @author cuichx --- 2016-5-14下午2:14:11
	 */
	public void newCheckResults() {
		site_code_session = getCurrentSiteCode();
		// 封装返回数据
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 获取当前登陆用户,依据前台页面选择的数据完成如下操作
		// 1更新spot_check_info抽查服务统计表中的已抽查数量和抽查总数
		// 2在spot_check_schedule抽查进度表新建一个批次关联到当前的组织单位下
		// 3在spot_check_result抽查任务结果表新建对应的填报单位抽查结果记录
		// 4在spot_task抽查任务表中新建一条任务，并标记为启动状态--展示不处理
		String dateStart = request.getParameter("dateStart");// 开始日期
		String dateEnd = request.getParameter("dateEnd");// 结束日期
		String checkWeb = request.getParameter("checkWeb");// 选择的网站siteCode集合
		Integer batchNum = Integer.parseInt(request.getParameter("batchNum"));// 批次
		Integer groupNum = Integer.parseInt(request.getParameter("groupNum"));// 组次
		Integer isorganizational = Integer.parseInt(request.getParameter("isorganizational"));// 是非门户
		String taskName = request.getParameter("taskName");// 任务名称

		String scheduleId = request.getParameter("scheduleId");

		if (StringUtils.isNotEmpty(dateStart)) {
			dateStart = DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateStart));
		}
		if (StringUtils.isNotEmpty(dateEnd)) {
			dateEnd = DateUtils.formatStandardDate(DateUtils.parseStandardDate(dateEnd));
		}
		try {
			if (checkWeb != null) {
				String[] siteCodes = checkWeb.split(",");
				String siteCode = getCurrentSiteCode();// 获取当前登陆组织单位
				List<ContractInfo> conList = getContractInfoList(siteCode, DateUtils.formatStandardDate(new Date()));
				if (conList != null && conList.size() > 0) {
					if (StringUtils.isEmpty(scheduleId)) {// 第一次添加站点
						// 抽查进度表判断重复--处理多并发问题
						// 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
						SpotCheckScheduleRequest seRequest = new SpotCheckScheduleRequest();
						seRequest.setSiteCode(siteCode);
						seRequest.setContractInfoId(conList.get(0).getId() + "");
						seRequest.setBatchNum(batchNum);
						seRequest.setGroupNum(groupNum);
						List<SpotCheckSchedule> seList = spotCheckScheduleServiceImpl.queryList(seRequest);
						if (seList == null || (seList != null && seList.size() == 0)) {
							// 向抽查进度表添加新批次数据
							SpotCheckSchedule spotCheckSchedule = new SpotCheckSchedule();
							spotCheckSchedule.setContractInfoId(conList.get(0).getId() + "");
							spotCheckSchedule.setSiteCode(siteCode);
							spotCheckSchedule.setTaskName(taskName);
							spotCheckSchedule.setStartDate(dateStart);
							spotCheckSchedule.setEndDate(dateEnd);
							// 该批次抽查次数在提交时设置
							spotCheckSchedule
									.setCreateTime(DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
							spotCheckSchedule.setStatus(0);
							spotCheckSchedule.setBatchNum(batchNum);
							spotCheckSchedule.setGroupNum(groupNum);
							spotCheckSchedule.setIsorganizational(isorganizational);
							spotCheckScheduleServiceImpl.add(spotCheckSchedule);

							// 通过组织机构编码、合同信息表id、批次、组次，查询抽查进度表，获取抽查进度表id
							SpotCheckScheduleRequest scheduleRequest = new SpotCheckScheduleRequest();
							scheduleRequest.setSiteCode(siteCode);
							scheduleRequest.setContractInfoId(conList.get(0).getId() + "");
							scheduleRequest.setBatchNum(batchNum);
							scheduleRequest.setGroupNum(groupNum);
							List<SpotCheckSchedule> scheduleList = spotCheckScheduleServiceImpl
									.queryList(scheduleRequest);
							if (scheduleList != null && scheduleList.size() > 0) {
								SpotCheckSchedule schedule = scheduleList.get(0);
								// 向抽查任务结果表中添加抽查的所有siteCode(10位)的信息
								for (int i = 0; i < siteCodes.length; i++) {
									DatabaseInfo databaseInfo = databaseInfoServiceImpl
											.findByDatabaseInfoCode(siteCodes[i]);
									if (databaseInfo != null) {
										SpotCheckResult spotCheckResult = new SpotCheckResult();
										spotCheckResult.setSiteCode(siteCodes[i]);
										spotCheckResult.setCheckResult(2);// 检查结果（1：合格，0：单项否决,2:未检查）
										spotCheckResult.setCity(databaseInfo.getCity());
										spotCheckResult.setCounty(databaseInfo.getCounty());
										spotCheckResult.setProvince(databaseInfo.getProvince());
										spotCheckResult.setDirector(databaseInfo.getDirector());
										spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
										spotCheckResult.setOrgSiteCode(siteCode);
										if (databaseInfo.getJumpUrl() != null
												&& !"".equals(databaseInfo.getJumpUrl())) {
											spotCheckResult.setUrl(databaseInfo.getJumpUrl());
										} else {
											spotCheckResult.setUrl(databaseInfo.getUrl());
										}
										spotCheckResult.setSpotCheckSchedule(schedule.getId());// 抽查进度表id
										spotCheckResult.setState(-1);// 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
																		// ，3：报告完成）
										spotCheckResult.setSiteName(databaseInfo.getName());
										spotCheckResult.setCreateTime(
												DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
										spotCheckResult.setModifyTime(
												DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
										spotCheckResultServiceImpl.add(spotCheckResult);
									}
								}

								/**
								 * 创建抽查合同
								 */
								ContractInfo contractInfo = new ContractInfo();
								contractInfo.setSiteCode(siteCode);// 组织单位编号
								String nowDateStr = DateUtils.formatShortDate(new Date());// 当前日期
								// 抽查合同号(C+yyyyMMdd_抽查进度表id_批次_组次)
								String contractCodeSpot = "C" + nowDateStr + "_" + schedule.getId() + "_" + batchNum
										+ "_" + groupNum;
								contractInfo.setContractCodeSpot(contractCodeSpot);
								contractInfo.setContractName(taskName);// 合同名称（取抽查进度表的任务名称）
								contractInfo.setContractBeginTime(DateUtils.parseStandardDate(dateStart));// 合同生效时间
								contractInfo.setContractEndTime(DateUtils.parseStandardDate(dateEnd));// 合同结束时间
								// 作废：-1，初始化：0，服务中：1，服务结束：2
								contractInfo.setExecuteStatus(0);
								contractInfoServiceImpl.add(contractInfo);
								// 查询合同信息表，获取添加的抽查合同的id
								ContractInfoRequest contractRequest = new ContractInfoRequest();
								contractRequest.setContractCodeSpot(contractCodeSpot);// 抽查合同号
								contractRequest.setSiteCode(siteCode);// 组织单位编码
								contractRequest.setExecuteStatus(0);// 执行状态
								contractRequest.setNowTime(DateUtils.formatStandardDate(new Date()));

								List<ContractInfo> conInfoList = contractInfoServiceImpl.queryList(contractRequest);
								if (conInfoList != null && conInfoList.size() > 0) {

									ContractInfo ctInfo = conInfoList.get(0);
									/**
									 * 创建服务周期
									 */
									ServicePeriod sPeriod = new ServicePeriod();
									sPeriod.setComboId(4);// 高级版
									sPeriod.setContractInfoId(ctInfo.getId());// 合同id
									sPeriod.setStartDate(DateUtils.parseStandardDate(dateStart));// 服务开始时间
									sPeriod.setEndDate(DateUtils.parseStandardDate(dateEnd));// 服务结束时间
									sPeriod.setSpotCheckScheduleId(schedule.getId());// 抽查进度表id
									sPeriod.setStatus(0);// 服务报告周期状态（-1：删除，0：未开始服务，1：服务中，2已完成服务
									sPeriod.setServicePeriodNum(ctInfo.getContractCodeSpot() + "_01");// 周期任务号
									sPeriod.setSiteCode(siteCode);// 当前登录人的组织单位编码
									servicePeriodServiceImpl.add(sPeriod);
								}
							}
							result.put("body", "success");
							writerPrint(JSONObject.fromObject(result).toString());
						} else {
							result.put("errorMsg", "已经存在该批次数据");
							writerPrint(JSONObject.fromObject(result).toString());
							return;
						}
					} else {// 点击其他页面中的新增站点按钮--进行站点添加
						/**
						 * 遍历新增站点数组，查询抽查结果表，判断新增的站点是否已经存在， 如果存在，执行下一次循环；
						 * 如果不存在，统计个数+1，
						 * 抽查服务表中已抽查数-1（更新缓存数据），抽查进度表中站点数+1，抽查结果表中添加一条记录
						 */
						for (int i = 0; i < siteCodes.length; i++) {
							// 根据组织单位编码、网站标识码、抽查进度id,查询抽查结果表
							SpotCheckResultRequest resultRequest = new SpotCheckResultRequest();
							resultRequest.setOrgSiteCode(siteCode);
							resultRequest.setSiteCode(siteCodes[i]);
							resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));

							List<SpotCheckResult> spotResultList = spotCheckResultServiceImpl.queryList(resultRequest);
							if (spotResultList == null || (spotResultList != null && spotResultList.size() == 0)) {
								DatabaseInfo databaseInfo = databaseInfoServiceImpl
										.findByDatabaseInfoCode(siteCodes[i]);
								if (databaseInfo != null) {
									SpotCheckResult spotCheckResult = new SpotCheckResult();
									spotCheckResult.setSiteCode(siteCodes[i]);
									spotCheckResult.setCheckResult(2);// 检查结果（1：合格，0：单项否决,2:未检查）
									spotCheckResult.setCity(databaseInfo.getCity());
									spotCheckResult.setCounty(databaseInfo.getCounty());
									spotCheckResult.setProvince(databaseInfo.getProvince());
									spotCheckResult.setDirector(databaseInfo.getDirector());
									spotCheckResult.setIsorganizational(databaseInfo.getIsorganizational());
									spotCheckResult.setOrgSiteCode(siteCode);
									if (databaseInfo.getJumpUrl() != null && !"".equals(databaseInfo.getJumpUrl())) {
										spotCheckResult.setUrl(databaseInfo.getJumpUrl());
									} else {
										spotCheckResult.setUrl(databaseInfo.getUrl());
									}
									spotCheckResult.setSpotCheckSchedule(Integer.valueOf(scheduleId));
									spotCheckResult.setState(-1);// 抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成
																	// ，3：报告完成）
									spotCheckResult.setSiteName(databaseInfo.getName());
									spotCheckResult.setCreateTime(
											DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
									spotCheckResult.setModifyTime(
											DateUtils.parseStandardDateTime(DateUtils.getNowStandardStr()));
									spotCheckResultServiceImpl.add(spotCheckResult);
								}
							}
						}
						result.put("body", "success");
						writerPrint(JSONObject.fromObject(result).toString());
					}
				}
			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					result.put("errorMsg", "考评站点不能为空");
				} else {
					result.put("errorMsg", "抽查站点不能为空");
				}
				writerPrint(JSONObject.fromObject(result).toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (site_code_exp.indexOf(site_code_session) >= 0) {
				result.put("errorMsg", "保存考评数据异常");
			} else {
				result.put("errorMsg", "保存抽查数据异常");
			}
			writerPrint(JSONObject.fromObject(result).toString());
		}
	}

	/**
	 * 抽查统计修改：修改已抽查次数
	 * 
	 * @param siteCode
	 * @param contractInfoId
	 * @param webSum
	 */
	private void updateSpotCheckInfo(String siteCode,String contractInfoId,int webSum){
		
		try {
			
			// 修改抽查服务统计表中的已抽查数和抽查总数
			SpotCheckInfoRequest infoRequest=new SpotCheckInfoRequest();
			infoRequest.setSiteCode(siteCode);
			
			if(!StringUtils.isEmpty(contractInfoId)){
				infoRequest.setContractInfoId(Integer.parseInt(contractInfoId));
			}
			
			
			List<SpotCheckInfo>  spotInfoList=spotCheckInfoServiceImpl.queryList(infoRequest);
			if (spotInfoList!= null && spotInfoList.size()>0) {
				SpotCheckInfo spotCheckInfo=spotInfoList.get(0);
				spotCheckInfo.setSpotNum(spotCheckInfo.getSpotNum() + webSum);
				spotCheckInfo.setModifyTime(new Date());
				spotCheckInfoServiceImpl.update(spotCheckInfo);
				// 修改session
				ShiroUser shiroUser = (ShiroUser) getSession().getAttribute(Constants.SHIRO_USER);
				shiroUser.setSportCount(spotCheckInfo.getSpotNum() + "/" + spotCheckInfo.getSpotSum());
				removeSession(Constants.SHIRO_USER);
				add2Session(Constants.SHIRO_USER, shiroUser);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @Description: 批量导出报告的流
	 * @author zhurk --- 2015-12-11下午1:35:24
	 * @param filePath
	 * @param fileName
	 */
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
		response.setHeader("Content-disposition", "attachment;filename=" + fileName); // 设置头部信息
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
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
		} finally {
			// 删除包
			try {
				File f = new File(filePath);
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description: 传递一个值返回一个非null
	 * @author sunjiaqi --- 2016-1-20上午10:09:31
	 * @return
	 */
	public String getNotNull(String s) {
		if (null == s) {
			return "";
		} else {
			return s;
		}
	}

	/**
	 * @Description: 启动抽查任务
	 * @author Nora 2016-02-16 下午4:41:45
	 *
	 * @Description: 启动抽查任务，修改合同状态
	 * @author: yangshuai --- 2016-6-3上午10:29:20
	 */
	public void startSpotTask() {

		String scheduleIdStr = request.getParameter("scheduleId");

		HashMap<String, Object> result = new HashMap<String, Object>();
		try {

			if (StringUtils.isEmpty(scheduleIdStr)) {
				result.put("body", "fail");
				writerPrint(JSONObject.fromObject(result).toString());
				return;
			}

			SpotCheckSchedule spotCheckSchedule = spotCheckScheduleServiceImpl.get(Integer.parseInt(scheduleIdStr));

			if (null == spotCheckSchedule) {
				result.put("body", "fail");
				writerPrint(JSONObject.fromObject(result).toString());
				return;
			}
			spotCheckSchedule.setStatus(1);
			spotCheckScheduleServiceImpl.update(spotCheckSchedule);

			// 修改spotCheckResult
			SpotCheckResultRequest spotCheckResultRequest = new SpotCheckResultRequest();
			spotCheckResultRequest.setSpotCheckSchedule(spotCheckSchedule.getId());
			List<SpotCheckResult> listSpotCheckResult = spotCheckResultServiceImpl.queryList(spotCheckResultRequest);
			for (SpotCheckResult spotCheckResult : listSpotCheckResult) {
				spotCheckResult.setState(1);
				spotCheckResultServiceImpl.update(spotCheckResult);
			}

			// 修改合同状态
			ServicePeriodRequest spRequest = new ServicePeriodRequest();
			spRequest.setSpotCheckScheduleId(Integer.parseInt(scheduleIdStr));
			spRequest.setNowTime(DateUtils.formatStandardDate(new Date()));
			List<ServicePeriod> spList = servicePeriodServiceImpl.queryList(spRequest);
			if (spList != null && spList.size() > 0) {
				ServicePeriod sp = spList.get(0);
				// 获取抽查合同id
				int ctInfoId = sp.getContractInfoId();
				// 根据抽查合同id查询合同信息表
				ContractInfo ctInfo = contractInfoServiceImpl.get(ctInfoId);
				if (ctInfo != null) {
					// 将抽查合同执行状态修改为0-初始化（作废：-1，初始化：0，服务中：1，服务结束：2）
					ctInfo.setExecuteStatus(1);
					contractInfoServiceImpl.update(ctInfo);
				}

			}

			result.put("body", "success");
			writerPrint(JSONObject.fromObject(result).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 获取抽查批次
	 * @author yuangw@com.cn 2016-04-26
	 * 
	 *         yangshuai --- 2016-6-13下午4:20:57
	 */
	public void getBatchTask() {

		String siteCode = getCurrentSiteCode();
		site_code_session = getCurrentSiteCode();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			if (StringUtils.isEmpty(siteCode)) {
				resultMap.put("list", "");
				writerPrint(JSONObject.fromObject(resultMap).toString());
				return;
			}
			List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
			List<ContractInfo> listcontractInfo = getContractInfoList(siteCode,
					DateUtils.formatStandardDate(new Date()));
			if (listcontractInfo != null && listcontractInfo.size() > 0) {
				SpotCheckScheduleRequest spotCheckScheduleRequest = new SpotCheckScheduleRequest();
				spotCheckScheduleRequest.setSiteCode(siteCode);
				List<SpotCheckSchedule> listSpotCheckSchedule = spotCheckScheduleServiceImpl
						.queryBatch(spotCheckScheduleRequest);
				Map<String, Object> localMap1 = new HashMap<String, Object>();
				if (listSpotCheckSchedule.size() == 0) {
					localMap1.put("batchNum", 1);
					localMap1.put("groupNum", 1);
					items.add(localMap1);
					logger.info("listSpotCheckSchedule.size()=0");
				} else {
					logger.info("listSpotCheckSchedule.size()>0");
					localMap1.put("batchNum", listSpotCheckSchedule.get(0).getBatchNum() + 1);
					localMap1.put("groupNum", 1);
					items.add(localMap1);
					for (SpotCheckSchedule spotCheckSchedule : listSpotCheckSchedule) {
						Map<String, Object> localMap = new HashMap<String, Object>();
						localMap.put("batchNum", spotCheckSchedule.getBatchNum());
						localMap.put("groupNum", spotCheckSchedule.getGroupNum());
						items.add(localMap);
					}
				}
				resultMap.put("list", items);
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				if (site_code_exp.indexOf(site_code_session) >= 0) {
					resultMap.put("errorMsg", "免费用户不存在考评");
				} else {
					resultMap.put("errorMsg", "免费用户不存在抽查");
				}
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取批次组次数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取报告下载的路径和文件名称
	 * @author cuichx --- 2016-5-11下午5:23:16
	 */
	public void getUploadPath(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		site_code_session = getCurrentSiteCode();
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String siteCode=request.getParameter("siteCode");//网站标识码
		
		try {
			if(StringUtils.isNotEmpty(scheduleId)){
				ServicePeriodRequest serviceRequest=new ServicePeriodRequest();
				serviceRequest.setSpotCheckScheduleId(Integer.valueOf(scheduleId));//抽查进度表id
				List<ServicePeriod> serviceList=servicePeriodServiceImpl.queryList(serviceRequest);
				if(serviceList!=null && serviceList.size()>0){
					ServicePeriod servicePeriod=serviceList.get(0);
					
					ReportWordResultRequest reportRequest=new ReportWordResultRequest();
					reportRequest.setSiteCode(siteCode);
					reportRequest.setServicePeriodId(servicePeriod.getId());
					
					List<ReportWordResult>  reportList=reportWordResultServiceImpl.queryList(reportRequest);
					if(reportList!=null && reportList.size()>0){
						ReportWordResult wordResult=reportList.get(0);
						resultMap.put("filePath", wordResult.getWordUrl());
						resultMap.put("fileName", wordResult.getAliasName());
					}else{
						resultMap.put("errorMsg", "不存在下载报告");
					}
					
				}else{
					resultMap.put("errorMsg", "不存在服务周期对象");
				}
			}else{
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("errorMsg", "考评进度表id参数不能为空");
				}else{
					resultMap.put("errorMsg", "抽查进度表id参数不能为空");
				}
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取下载报告路径异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 根据servicePeriodId和siteCode判断报告是否存在
	 * @author: yangshuai --- 2016-5-27下午5:10:55
	 * @return
	 */
	public void getUploadPathByServiceRepriod(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String servicePeriodId = request.getParameter("servicePeriodId");//周期服务id
		String siteCode = request.getParameter("siteCode");//网站标识码
		try {	
			ReportWordResultRequest reportRequest=new ReportWordResultRequest();
			reportRequest.setSiteCode(siteCode);
			reportRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
			
			List<ReportWordResult>  reportList=reportWordResultServiceImpl.queryList(reportRequest);
			if(reportList!=null && reportList.size()>0){
				ReportWordResult wordResult=reportList.get(0);
				String wordUrl=wordResult.getWordUrl();
				if(StringUtils.isNotEmpty(wordUrl)){
					wordUrl=urlAdapterVar.getJiXiaoWord()+wordUrl;
				}
				resultMap.put("filePath", wordUrl);
				resultMap.put("fileName", wordResult.getAliasName());
				File file=new File(wordUrl);
				if(!file.exists()){
					resultMap.put("errorMsg", "未找到下载文件");
				}
			}else{
				resultMap.put("errorMsg", "未找到下载文件");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取下载报告路径异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 批量下载报告确定有没有文件
	 * @author: liukl --- 2017年4月11日11:34:55
	 * @return
	 */
	public void batchReport(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String servicePeriodId = request.getParameter("servicePeriodId");//周期服务id
			String  siteCodes= request.getParameter("siteCodes");//所有已完成报告的网站标识码
			List<String> fileNames = new ArrayList<String>();// 文件路径  及名称
			if(StringUtils.isNotEmpty(siteCodes) && StringUtils.isNotEmpty(servicePeriodId)){
				ReportWordResultRequest reportRequest=new ReportWordResultRequest();
				reportRequest.setServicePeriodId(Integer.parseInt(servicePeriodId));
				String[] arr = siteCodes.split(",");
				for (String siteCode : arr) {
					reportRequest.setSiteCode(siteCode);
					List<ReportWordResult>  reportList=reportWordResultServiceImpl.queryList(reportRequest);
					if(reportList!=null && reportList.size()>0){
						ReportWordResult wordResult=reportList.get(0);
						String wordUrl=wordResult.getWordUrl();
						if(StringUtils.isNotEmpty(wordUrl)){
							wordUrl=urlAdapterVar.getJiXiaoWord()+wordUrl;
						}
						File file=new File(wordUrl);
						if(!file.exists()){
							resultMap.put("errorMsg", "未找到下载文件");
						}else{
							fileNames.add(wordUrl);
						}
					}
				}
				ServicePeriod servicePeriod=servicePeriodServiceImpl.get(Integer.valueOf(servicePeriodId));
				if(fileNames.size()>0){
					resultMap.put("isDown", "1");
					DownFiles df = new DownFiles();
					Map<String, String> fileMap = df.getNamemap(fileNames);
					resultMap.put("filePath", fileMap.get("filePath"));
					String siteName = Constants.getCurrendUser().getUserName().trim();//获得当前登录站点名称
					resultMap.put("fileName", siteName+"-深度检测-全面检测"+"("+DateUtils.formatStandardDate(servicePeriod.getStartDate())+"至"+DateUtils.formatStandardDate(servicePeriod.getEndDate())+").zip");
				}else{
					resultMap.put("isDown", "0");
					resultMap.put("errorMsg", "未找到下载文件");
				}
			}else{
				resultMap.put("isDown", "0");
				resultMap.put("errorMsg", "未找到下载文件");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("errorMsg", "获取下载报告路径异常");
		}
	}
	/**
	 * @Description: 批量下载报告
	 * @author: liukl --- 2017年4月11日11:34:55
	 * @return
	 */
	public void batchReportDowLoad(){
		try {
			String fileName = request.getParameter("fileNameReport");
			String filePath = request.getParameter("filePathReport");
			outPutFile(filePath, fileName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 处理文件下载
		
	}
	
	
	/**
	 * @Description: 根据抽查进度表id，查询服务周期对象
	 * @author cuichx --- 2016-5-10下午3:59:12
	 */
	public void getServicePeriod(){
		Map<String, Object> resultMap=new HashMap<String, Object>();
		site_code_session = getCurrentSiteCode();
		String scheduleId=request.getParameter("scheduleId");
		try {
			if(StringUtils.isNotEmpty(scheduleId)){
				ServicePeriodRequest serviceRequest=new ServicePeriodRequest();
				serviceRequest.setSpotCheckScheduleId(Integer.valueOf(scheduleId));//抽查进度表id
				List<ServicePeriod> serviceList=servicePeriodServiceImpl.queryList(serviceRequest);
				if(serviceList!=null && serviceList.size()>0){
					ServicePeriod servicePeriod=serviceList.get(0);
					resultMap.put("servicePeriodId", servicePeriod.getId());//服务周期id
					resultMap.put("startDate", DateUtils.formatStandardDate(servicePeriod.getStartDate()));//开始日期
					resultMap.put("endDate", DateUtils.formatStandardDate(servicePeriod.getEndDate()));//结束日期
				}else{
					resultMap.put("errorMsg", "不存在服务周期对象");
				}
			}else{
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("errorMsg", "考评进度表id参数不能为空");
				}else{
					resultMap.put("errorMsg", "抽查进度表id参数不能为空");
				}
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取周期对象异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * @Description: 更新抽查结果表--1-通过  0-不通过
	 * @author cuichx --- 2016-5-31下午1:39:15
	 */
	public void updateSpotResult(){
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		site_code_session = getCurrentSiteCode();
		String scheduleId=request.getParameter("scheduleId");//抽查进度表id
		String siteCode=request.getParameter("siteCode");//网站标识码
		String type=request.getParameter("type");//1-通过  0-不通过
		try {
			SpotCheckResultRequest resultRequest=new SpotCheckResultRequest();
			if(StringUtils.isNotEmpty(siteCode)){
				resultRequest.setSiteCode(siteCode);
			}
			if(StringUtils.isNotEmpty(scheduleId)){
				resultRequest.setSpotCheckSchedule(Integer.valueOf(scheduleId));
			}
			List<SpotCheckResult> spotList=spotCheckResultServiceImpl.queryList(resultRequest);
			if(spotList!=null && spotList.size()>0){
				SpotCheckResult result=spotList.get(0);
				if("1".equals(type)){//通过
					result.setCheckReportResult(1);
				}else{//不通过
					result.setCheckReportResult(2);
				}
				spotCheckResultServiceImpl.update(result);
				
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("success", "考评结果更新成功");
				}else{
					resultMap.put("success", "抽查结果更新成功");
				}
			}else{
				if(site_code_exp.indexOf(site_code_session)>=0){
					resultMap.put("errorMsg", "考评结果不存在");
				}else{
					resultMap.put("errorMsg", "抽查结果不存在");
				}
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			if(site_code_exp.indexOf(site_code_session)>=0){
				resultMap.put("errorMsg", "考评结果更新异常");
			}else{
				resultMap.put("errorMsg", "抽查结果更新异常");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	
	/**
	 * @Description: 保存上级loginOrgCode登录用户 修改批次表 一键汇报
	 * @author: Liukl --- 2016年12月6日20:42:53
	 * @return
	 */
	public void getSpotCheckSchedulBatchNum() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			String loginOrgCode = getCurrentUserInfo().getSiteCode();// 登录组织单位编码
			String siteCode = databaseBizServiceImpl.superiorUnit(loginOrgCode); // 上级组织单位
			String scheduleIds = request.getParameter("scheduleIds");//抽查进度表的ids
			String affixName = request.getParameter("affixName");// 附件名称
			
			String affixNameNo = request.getParameter("affixNameNo");// 附件名称无任务时
			String taskName = request.getParameter("taskName");// 无任务时
			String websiteNum = request.getParameter("websiteNum");// 无任务时
			String startDate = request.getParameter("startDate");// 无任务时
			String endDate = request.getParameter("endDate");// 无任务时
			String status = request.getParameter("status");// 无任务时
			
			if (StringUtils.isNotEmpty(siteCode)) {
			String fileName = "";
			if(StringUtils.isNotEmpty(siteReportup) && StringUtils.isNotEmpty(affixName)){
				String	folder = dicUtils.getValue("spotReport_Folder");
				if(StringUtils.isNotEmpty(folder)){
					fileName = urlAdapterVar.getWordfilepath()+ folder
							+ DateUtils.formatShortFullDateTime(new Date()) + "_"
							+ affixName;
				}
				String dicFileSize = dicUtils.getValue("spotReportFolder_size");
				if(StringUtils.isNotEmpty(dicFileSize)){
					long maxSize = Long.valueOf(dicFileSize).longValue();
					File fi = new File(siteReportup);
					if (fi.length() > maxSize) {
						resultMap.put("report", "汇报失败，上传文件不能大于20M");
						writerPrint(JSONObject.fromObject(resultMap).toString());
				        return;
					}else{
						FileInputStream fis = new FileInputStream(fi);
						FileUtils.writeFile(fis, urlAdapterVar.getWebPaths() + fileName);
					}
				}else{
					resultMap.put("report", "汇报失败");
				}
				
			}else if(StringUtils.isNotEmpty(siteReportupNo) && StringUtils.isNotEmpty(affixNameNo)){
				String	folder = dicUtils.getValue("spotReport_Folder");
				if(StringUtils.isNotEmpty(folder)){
					fileName = urlAdapterVar.getWordfilepath()+ folder
							+ DateUtils.formatShortFullDateTime(new Date()) + "_"
							+ affixNameNo;
				}
				String dicFileSize = dicUtils.getValue("spotReportFolder_size");
				if(StringUtils.isNotEmpty(dicFileSize)){
					long maxSize = Long.valueOf(dicFileSize).longValue();
					File fi = new File(siteReportupNo);
					if (fi.length() > maxSize) {
						resultMap.put("report", "汇报失败，上传文件不能大于20M");
						writerPrint(JSONObject.fromObject(resultMap).toString());
				        return;
					}else{
						FileInputStream fis = new FileInputStream(fi);
						FileUtils.writeFile(fis, urlAdapterVar.getWebPaths() + fileName);
					}
				}else{
					resultMap.put("report", "汇报失败");
				}
			}
				if(StringUtils.isNotEmpty(scheduleIds)){
					String[] strArray = scheduleIds.split(",");
					for (String str : strArray) {
						//向附件表里存放url等信息 有任务时
						SpotSecurityReport reportUp = new SpotSecurityReport();
						reportUp.setSpotCheckScheduleId(Integer.valueOf(str));
						reportUp.setAffixName(affixName);// 原文件名
						reportUp.setAffixUrl(fileName);
						reportUp.setReportOrgCode(siteCode);
						SpotSecurityReportRequest reportRequest = new SpotSecurityReportRequest();
						reportRequest.setScheduleId(Integer.valueOf(str));
						List<SpotSecurityReport> spotList = spotSecurityReportServiceImpl.queryList(reportRequest);
						if(StringUtils.isNotEmpty(loginOrgCode)){
							reportUp.setSiteCode(loginOrgCode);
						}
						if(spotList != null &&  spotList.size()>0){
//							SpotSecurityReport spotListlist = spotSecurityReportServiceImpl.get(spotList.get(0).getId());
							reportUp.setId(spotList.get(0).getId());
							reportUp.setModifyTime(new Date());
							reportUp.setType(1);
							spotSecurityReportServiceImpl.update(reportUp);
						}else{
							reportUp.setType(1);
							spotSecurityReportServiceImpl.add(reportUp);
						}
						reportUp.setSpotCheckScheduleId(Integer.valueOf(str));
						SpotCheckSchedule spotRequest = spotCheckScheduleServiceImpl.get(Integer.valueOf(str));
						spotRequest.setReportOrgCode(siteCode);
						spotRequest.setModifyTime(new Date());
						spotCheckScheduleServiceImpl.update(spotRequest);
					}
				}else{
					SpotSecurityReport reportUp = new SpotSecurityReport();
					if (StringUtils.isNotEmpty(affixNameNo)) {
						reportUp.setAffixName(affixNameNo);// 原文件名
					}
					if(StringUtils.isNotEmpty(fileName)){
						reportUp.setAffixUrl(fileName);
					}
					
					reportUp.setTaskName(taskName);
					if(StringUtils.isNotEmpty(websiteNum)){
						reportUp.setSpotWebsiteNum(Integer.valueOf(websiteNum));
					}
					reportUp.setStartDate(startDate);
					reportUp.setEndDate(endDate);
					if(StringUtils.isNotEmpty(loginOrgCode)){
						reportUp.setSiteCode(loginOrgCode);
					}
					if(StringUtils.isNotEmpty(status)){
						reportUp.setStatus(Integer.valueOf(status));
					}
					reportUp.setReportOrgCode(siteCode);
					reportUp.setType(2);
					spotSecurityReportServiceImpl.add(reportUp);
				}
				
				resultMap.put("report", "汇报成功");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				resultMap.put("report", "未获取到上级信息");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			resultMap.put("report", "汇报失败");
			e.printStackTrace();
		}
	}

	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public String getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Integer getCheckReport() {
		return checkReport;
	}

	public void setCheckReport(Integer checkReport) {
		this.checkReport = checkReport;
	}

	public Integer getCheckNotice() {
		return checkNotice;
	}

	public void setCheckNotice(Integer checkNotice) {
		this.checkNotice = checkNotice;
	}

	public Integer getCheckRead() {
		return checkRead;
	}

	public void setCheckRead(Integer checkRead) {
		this.checkRead = checkRead;
	}

	public String getSiteReportup() {
		return siteReportup;
	}

	public void setSiteReportup(String siteReportup) {
		this.siteReportup = siteReportup;
	}

	public String getSiteReportupNo() {
		return siteReportupNo;
	}

	public void setSiteReportupNo(String siteReportupNo) {
		this.siteReportupNo = siteReportupNo;
	}

	public Integer getCheckSiteType() {
		return checkSiteType;
	}

	public void setCheckSiteType(Integer checkSiteType) {
		this.checkSiteType = checkSiteType;
	}

	public void setCheckResult(Integer checkResult) {
		this.checkResult = checkResult;
	}
	
	
}



