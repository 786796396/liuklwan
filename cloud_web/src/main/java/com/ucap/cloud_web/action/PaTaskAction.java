package com.ucap.cloud_web.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aspose.words.SaveFormat;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.PaRatingChannelRequest;
import com.ucap.cloud_web.dto.PaRatingDetailRequest;
import com.ucap.cloud_web.dto.PaTaskRequest;
import com.ucap.cloud_web.dto.SysAttachRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.PaRating;
import com.ucap.cloud_web.entity.PaRatingChannel;
import com.ucap.cloud_web.entity.PaRatingDetail;
import com.ucap.cloud_web.entity.PaTargetTask;
import com.ucap.cloud_web.entity.PaTask;
import com.ucap.cloud_web.entity.SysAttach;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IPaQuotaService;
import com.ucap.cloud_web.service.IPaRatingChannelService;
import com.ucap.cloud_web.service.IPaRatingDetailService;
import com.ucap.cloud_web.service.IPaRatingService;
import com.ucap.cloud_web.service.IPaTargetTaskService;
import com.ucap.cloud_web.service.IPaTaskService;
import com.ucap.cloud_web.service.ISysAttachService;
import com.ucap.cloud_web.util.FileUtils;
import com.ucap.cloud_web.util.PicturetoBase64;
import com.ucap.cloud_web.util.aspose.AsposeHelper;
import com.ucap.cloud_web.util.aspose.AsposeType;
import com.ucap.cloud_web.util.aspose.JMap;
/**
 * <p>Description: 绩效任务</p>
 * <p>Title: PaTaskAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：linhb </p>
 * <p>@date：2016年8月23日上午11:01:07 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PaTaskAction extends BaseAction{
	
	@Autowired
	private IPaTaskService paTaskServiceImpl;
	@Autowired
	private IPaRatingChannelService paRatingChannelServiceImpl;
	@Autowired
	private IPaQuotaService paQuotaServiceImpl;
	@Autowired
	private IPaRatingService paRatingServiceImpl;
	@Autowired
	private IPaRatingDetailService paRatingDetailServiceImpl;
	@Autowired
	private IPaTargetTaskService paTargetTaskServiceImpl;
	@Autowired
	private ISysAttachService sysAttachServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	// 空两格
	private final String INDEXEM_TWO = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	private final String REPORT_PATH = "/tpl/appraisals/site_report.doc";
	private AsposeHelper aspose = new AsposeHelper();
	@Autowired
	private UrlAdapterVar urlAdapterVar;//配置文件
	
	
	//String localWebPath = "D:/softwaire/tomcat/apache-tomcat-7.0.42/webapps/cloud_web";
//	String localfilepath = "/wordfile/";
	String taskName = "";
	String webName = "";
	
	String ratingName = "";
	String ratingPhone = "";
	String ratingDate = "";
	String ratingCon = "";
	
	
	String  overallSituation= "";
	String  problemsSuggestions = "";
	String  otherinstructions = "";
	String  plan = "";
	
	
	/**
	 * 跳转页面
	 * linhb - 2016-8-29
	 */
	public String paTaskInfo(){
		return "success";
	}
	
	/**
	 * 任务对象关联表  与 任务表  关联  获取数据
	 * linhb - 2016-8-29
	 */
	public void getTaskInfo(){
		try {
			String siteCode = request.getParameter("siteCode");
			
			PaTaskRequest pRequest = new PaTaskRequest();
			if(StringUtils.isNotEmpty(siteCode)  && !"null".equals(siteCode) && !"undefined".equals(siteCode)){
				pRequest.setSiteCode(siteCode);
			}else{
				return;
			}
			List<PaTask> list = paTaskServiceImpl.queryJoinTarget(pRequest);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("list", list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 生成自评报告
	 * 
	 * linhb - 2016-10-21
	 */
	public void createWord(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {

			String ratingId = request.getParameter("ratingId");
			
			if(StringUtils.isNotEmpty(ratingId)){
				PaRating pRating = paRatingServiceImpl.get(Integer.parseInt(ratingId));
				
				ratingName = pRating.getRatingName();
				ratingPhone = pRating.getRatingPhone();
				ratingDate = DateUtils.formatStandardDate(pRating.getCreateTime());
				ratingCon = pRating.getCompanyName();
			
				overallSituation= StringUtils.isEmpty(pRating.getOverallSituation())?"暂无":pRating.getOverallSituation();
				problemsSuggestions= StringUtils.isEmpty(pRating.getProblemsSuggestions())?"暂无":pRating.getProblemsSuggestions();
				otherinstructions= StringUtils.isEmpty(pRating.getOther())?"暂无":pRating.getOther();
				plan= StringUtils.isEmpty(pRating.getPlan())?"暂无":pRating.getPlan();
				
				
				//获取任务名称
				
				PaTask paTask = paTaskServiceImpl.get(pRating.getTaskId());
				
				taskName = paTask.getTaskName();
				DatabaseInfoRequest dRequest = new DatabaseInfoRequest();
				dRequest.setSiteCode(pRating.getSiteCode());
				List<DatabaseInfo> dInfoList = databaseInfoServiceImpl.queryList(dRequest);
				webName = dInfoList.get(0).getName();
				createReportFile("",Integer.parseInt(ratingId));
				map.put("success", "true");
				writerPrint(JSONObject.fromObject(map).toString());
			}
			
		} catch (Exception e) {
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
			e.printStackTrace();
		}

	}
	
	

	
	/**
	 * @Title: getFullDetailList 
	 * @Description: 获取分层级的列表
	 * @author: masl@ucap.com.cn 2016年9月9日下午4:33:19
	 * @param @param list
	 * @param @return 
	 * @return List<PaRatingDetail>
	 * @throws
	*/
	private List<PaRatingDetail> getFullDetailList(List<PaRatingDetail> list) {
		String quota1 = "", quota2 = "", quota3 = "";
		int k = 1;

		List<String> layer1Quota = new ArrayList<String>();
		List<String> layer2Quota = new ArrayList<String>();
		List<String> layer3Quota = new ArrayList<String>();
		Hashtable<String, Integer> ht = new Hashtable<String, Integer>();

		List<PaRatingDetail> newList = new ArrayList<PaRatingDetail>();
		PaRatingDetail paRatingDetail = null;
		try {

			for (PaRatingDetail mod : list) {
				quota1 = mod.getQuota1();
				quota2 = mod.getQuota2();
				quota3 = mod.getQuota3();
				mod.setQuotaIdId(mod.getQuotaId());

				// 第一级
				if (!layer1Quota.contains(quota1)) {
					paRatingDetail = new PaRatingDetail();
					paRatingDetail.setQuotaId(k);
					paRatingDetail.setLayer(1);
					paRatingDetail.setQuotaParentId(0);
					paRatingDetail.setQuotaName(quota1);
					paRatingDetail.setIsEnd(false);

					newList.add(paRatingDetail);
					ht.put(quota1, k);

					layer1Quota.add(quota1);
					k++;
				}
				// 第二级
				if (StringUtils.isNotEmpty(quota2) && !layer2Quota.contains(quota1 + quota2)) {
					paRatingDetail = new PaRatingDetail();
					if (StringUtils.isEmpty(quota3)) {
						paRatingDetail = mod;
						paRatingDetail.setIsEnd(true);
					}else {
						paRatingDetail.setIsEnd(false);
					}
					paRatingDetail.setQuotaId(k);
					paRatingDetail.setLayer(2);
					paRatingDetail.setQuotaName(quota2);
					paRatingDetail.setQuotaParentId(ht.get(quota1));

					newList.add(paRatingDetail);
					ht.put(quota1 + quota2, k);

					layer2Quota.add(quota1 + quota2);
					k++;
				}

				// 第三级
				if (StringUtils.isNotEmpty(quota3) && !layer3Quota.contains(quota1 + quota2 + quota3)) {
					paRatingDetail = new PaRatingDetail();
					paRatingDetail = mod;
					
					paRatingDetail.setQuotaId(k);
					paRatingDetail.setLayer(3);
					paRatingDetail.setIsEnd(true);
					paRatingDetail.setQuotaName(quota3);
					paRatingDetail.setQuotaParentId(ht.get(quota1 + quota2));

					newList.add(paRatingDetail);
					//ht.put(quota1 + quota2 + quota3, k);

					layer3Quota.add(quota1 + quota2 + quota3);
					k++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}

	/**
	 * @Title: createReportFile 
	 * @Description: 生成报告文件
	 * @author: masl@ucap.com.cn 2016年9月9日上午11:16:07
	 * @param @param ratingId
	 * @param @return 
	 * @return boolean
	 * @throws
	 */
	public boolean createReportFile(String basePath,Integer ratingId) {
		if (ratingId == null) {
			return false;
		}
		//String wordReportTemplate = urlAdapterVar.getWebPaths() + REPORT_PATH;//数据集合
		String wordReportTemplate = urlAdapterVar.getCloubWebPaths() + REPORT_PATH;//数据集合
		//正文内容
		StringBuilder sbContent = new StringBuilder();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> dataHtmlMap = new HashMap<String, Object>();
		PaRatingDetailRequest request = new PaRatingDetailRequest();
		List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();

		PaRating paRating = paRatingServiceImpl.get(ratingId);
		if (paRating == null) {
			return false;
		}
		try {
			if (!FileUtils.fileExists(wordReportTemplate)) {
				throw new Exception("模板文件不存在");
				//return false;
			}
			request.setRatingId(ratingId);

			//queryOrderList.add(new QueryOrder("quota_parent_id", QueryOrderType.ASC));
			queryOrderList.add(new QueryOrder("quota_id", QueryOrderType.ASC));
			request.setQueryOrderList(queryOrderList);

			dataMap.put("siteName", webName);//网站名称
			dataMap.put("rName", ratingName);
			dataMap.put("rPhone", ratingPhone);
			dataMap.put("ratingCon", ratingCon);
			dataMap.put("ratingDate", ratingDate);
			
			dataMap.put("overSituation", overallSituation);
			dataMap.put("pSuggestions", problemsSuggestions);
			dataMap.put("other", otherinstructions);
			dataMap.put("plan", plan);
			request.setPageSize(Integer.MAX_VALUE);
			List<PaRatingDetail> paRatingDetailList = paRatingDetailServiceImpl.queryList(request);
			List<PaRatingDetail> list = getFullDetailList(paRatingDetailList);
			
			Map<Integer, Integer> rowMap1 = new HashMap<Integer, Integer>();
			Map<Integer, Integer> rowMap2 = new HashMap<Integer, Integer>();
			Map<Integer, Float> weightMap = new HashMap<Integer, Float>();
			String row1 ="";
			String row2 ="";
			Integer pDetailId1 = 0;
			Integer pDetailId2 = 0;
			
			
			for (int i=0;i<paRatingDetailList.size();i++) {
				PaRatingDetail pDetail = paRatingDetailList.get(i);
				//添加权重
				Float weight = (float) 0.00;
				if(pDetail.getQuotaIdId()>0){
					weight = paQuotaServiceImpl.get(pDetail.getQuotaIdId()).getWeight();
					weightMap.put(pDetail.getId(), weight);
				}
				if(i==0){
					rowMap1.put(pDetail.getId(), 1);
					rowMap2.put(pDetail.getId(), 1);
					
					row1 = pDetail.getQuota1();
					row2 = pDetail.getQuota2();
					
					pDetailId1 = pDetail.getId();
					pDetailId2 = pDetail.getId();
				}else{
					//一级指标
					if(pDetail.getQuota1().equals(row1) ){
						rowMap1.put(pDetailId1, rowMap1.get(pDetailId1)+1);
					}else{
						rowMap1.put(pDetail.getId(), 1);
						row1 = pDetail.getQuota1();
						pDetailId1 = pDetail.getId();
					}
					//二级指标
					if(pDetail.getQuota2().equals(row2)){
						rowMap2.put(pDetailId2, rowMap2.get(pDetailId2)+1);
					}else{
						rowMap2.put(pDetail.getId(), 1);
						row2 = pDetail.getQuota2();
						pDetailId2 = pDetail.getId();
					}
					
				}
				
			}
			String tdStyle="border-left:solid 1px #333;border-top:solid 1px #333;padding:2px;";
			sbContent.append("<h1 style='text-align: center;font-family: 黑体;font-size: 21pt;'>总览</h1>");
			
			sbContent.append("	<table style='table-layout:fixed; word-wrap:break-all;font-size:12px;margin-left:-25px;border-collapse:collapse;width:600px;border-right:solid 1px #333;border-bottom:solid 1px #333'>");
			sbContent.append("		<tr>");
			sbContent.append("			<td colspan='3' style='" + tdStyle +"width:67%;"+ "text-align: center;background:#c7d9f1;font-weight: bold;margin-left:0px;'>考评项</td>");
			sbContent.append("			<td style='" + tdStyle +"width:16%;"+ "text-align: center;background:#c7d9f1;font-weight: bold;'>考评权重</td>");
			sbContent.append("			<td style='" + tdStyle +"width:17%;"+ "text-align: center;background:#c7d9f1;font-weight: bold;'>得分</td>");
			sbContent.append("		</tr>");
			//float zWeight = 0;
			float rScore= 0;
			for (int i=0;i<paRatingDetailList.size();i++) {
				PaRatingDetail pDetail = paRatingDetailList.get(i);
				int pId = pDetail.getId();
				if(rowMap1.get(pId)!=null){
					//一级
					sbContent.append("<tr><td rowspan='"+rowMap1.get(pId)+"' style='" + tdStyle +"width:24%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + pDetail.getQuota1() + "</td>");
				}
				if(StringUtils.isNotEmpty(pDetail.getQuota3())){
					if(rowMap2.get(pId)!=null){
						//二级
						sbContent.append("<td rowspan='"+rowMap2.get(pId)+"' style='" + tdStyle +"width:22%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + pDetail.getQuota2() + "</td>");
					}
					//三级
					sbContent.append("<td style='" + tdStyle +"width:21%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + pDetail.getQuota3() + "</td>");
				}else{
					//二级
					sbContent.append("<td colspan='2' style='" + tdStyle +"width:43%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + pDetail.getQuota2() + "</td>");
				}
				
				if(weightMap.get(pDetail.getId()) != null){
					//zWeight +=weightMap.get(pDetail.getId());
					sbContent.append("<td style='" + tdStyle +"width:16%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + weightMap.get(pDetail.getId())+ "</td>");
				}else{
					sbContent.append("<td style='" + tdStyle +"width:16%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + weightMap.get(pDetail.getId())+ "</td>");
				}
				
				if(pDetail.getRatingScore()>=0){
					if("扣分指标".equals(pDetail.getQuota2())){
						rScore-=pDetail.getRatingScore();
					}else{
						rScore+=pDetail.getRatingScore();
					}
					sbContent.append("<td style='" + tdStyle +"width:17%;font-size: 12pt;line-height:1.5;"+ "text-align: center;'>"+pDetail.getRatingScore() + "</td>");
				}else{
					sbContent.append("<td style='" + tdStyle +"width:17%;font-size: 12pt;line-height:1.5;"+ "text-align: center;'></td>");
				}
				sbContent.append("</tr>");
			}
			sbContent.append("		<tr>");
			sbContent.append("			<td colspan='4' style='" + tdStyle +"width:67%;"+ "text-align: center;font-weight: bold;margin-left:0px;'>总分</td>");
//			sbContent.append("			<td style='" + tdStyle +"width:16%;"+ "text-align: center;font-weight: bold;'>"+zWeight + "</td>");
			DecimalFormat df = new DecimalFormat("0.00");
			sbContent.append("			<td style='" + tdStyle +"width:17%;"+ "text-align: center;font-weight: bold;'>"+df.format(rScore) + "</td>");
			sbContent.append("		</tr>");
			sbContent.append("	</table>");
//=============================================================================上面是  总览====================			
			int no1 = 1, no2 = 1, no3 = 1;
			if (list != null && list.size() > 0) {
				for (PaRatingDetail entity1 : list) {
					
					if (entity1.getLayer() != 1) {
						continue;
					}
					//第一级 ======
					sbContent.append("<h1 style='text-align: center;font-family: 黑体;font-size: 21pt;'>" + entity1.getQuotaName() + "</h1>");
					no2 = 1;
					//第二级 ======
					for (PaRatingDetail entity2 : list) {
						if (entity2.getLayer() == 2 && entity2.getQuotaParentId().equals(entity1.getQuotaId())) {
							sbContent.append("<h2 style='font-family: 黑体;font-size: 15.75pt;'>" + entity2.getQuotaName() + "</h2>");
							if (entity2.getIsEnd()) {
								sbContent.append(getChannelDetail(basePath,entity2,weightMap));
								//sbContent.append(getChannelDetail(basePath,entity2));
							} else {
								no3 = 1;
								//第三级 ======
								for (PaRatingDetail entity3 : list) {
									if (entity3.getLayer() != 3 || !entity3.getQuotaParentId().equals(entity2.getQuotaId())) {
										continue;
									}
									sbContent.append("<h3 style='font-family:黑体;font-size: 15.75pt;'>" + entity3.getQuotaName() + "</h3>");
									sbContent.append(getChannelDetail(basePath,entity3,weightMap));
									//sbContent.append(getChannelDetail(basePath,entity3));
									no3++;
								}
							}
							no2++;
						}
					}
					no1++;
				}
				dataHtmlMap.put("TAG_QUOTA", sbContent.toString());

				List<JMap> maps = new ArrayList<JMap>();
				maps.add(new JMap(AsposeType.Txt, dataMap));
				maps.add(new JMap(AsposeType.Html, dataHtmlMap));

				//任务名-xxx网站考评记录.doc
				String aliasName = taskName + "-" + webName + "网站自评工作报告.doc";
				//String path = urlAdapterVar.getWordfilepath() + DateUtils.formatStandardDate(new Date()).replace("-", "/") + "/" + aliasName;
				//String path = localfilepath + DateUtils.formatStandardDate(new Date()).replace("-", "/") + "/" + aliasName;
				
				//String path = "D:/softwaire/tomcat/apache-tomcat-7.0.42/webapps/cloud_web" + DateUtils.formatStandardDate(new Date()).replace("-", "/") + "/" + aliasName;

				//FileUtils.createDir(urlAdapterVar.getWebPath() + path);
				FileUtils.createDir(urlAdapterVar.getCloubWebPaths() + urlAdapterVar.getWordfilepath());
				
				aspose.Open(wordReportTemplate);
				aspose.replace(maps);
				//aspose.saveAs(urlAdapterVar.getWebPath() + path, SaveFormat.DOC, 1);
				aspose.saveAs(urlAdapterVar.getCloubWebPaths()+urlAdapterVar.getWordfilepath()+aliasName, SaveFormat.DOC, 1);

				//保存到数据表中
				PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(paRating.getTargetTaskId());
				paTargetTask.setRatingReportPath(urlAdapterVar.getWordfilepath()+aliasName);
				paTargetTaskServiceImpl.update(paTargetTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private StringBuilder getChannelDetail(String basePath,PaRatingDetail paRatingDetail,Map<Integer, Float> weightMap) {
		//private StringBuilder getChannelDetail(String basePath,PaRatingDetail paRatingDetail) {
		StringBuilder result = new StringBuilder();
		if(paRatingDetail == null || paRatingDetail.getId() == 0){
			return result;
		}
		//添加权重
		Float weight = (float) 0.00;
		if(paRatingDetail.getQuotaIdId()>0){
			//weight = paQuotaServiceImpl.get(paRatingDetail.getQuotaIdId()).getWeight();
			weight = weightMap.get(paRatingDetail.getId());
		}
		String tdStyle="border-left:solid 1px #333;border-top:solid 1px #333;padding:2px;";
		
		result.append("<span style='font-family: 仿宋;font-size: 15pt;'>【指标与评分】</span>");
		result.append("	<table style='table-layout:fixed; word-wrap:break-all;font-size:12px;margin-left:-25px;border-collapse:collapse;width:600px;border-right:solid 1px #333;border-bottom:solid 1px #333'>");
		result.append("		<tr>");
		result.append("			<td style='" + tdStyle +"width:130px;"+ "text-align: left;background:#c7d9f1;font-weight: bold;margin-left:0px;'>评估内容</td>");
		result.append("			<td style='" + tdStyle +"width:100px;"+ "text-align: center;background:#c7d9f1;font-weight: bold;'>权重</td>");
		result.append("			<td style='" + tdStyle +"width:100px;"+ "text-align: center;background:#c7d9f1;font-weight: bold;'>得分</td>");
		result.append("		<tr>");
//		result.append("			<td style='" + tdStyle +"width:70%;"+ "'>" + CommonUtils.Wrap(paRatingDetail.getAppraisalContent(), 28, "<br/>") + "</td>");
		result.append("			<td style='" + tdStyle +"width:74%;font-family: 仿宋;font-size: 12pt;line-height:1.5;"+ "'>" + paRatingDetail.getAppraisalContent() + "</td>");
//		result.append("			<td style='" + tdStyle +"width:300px;"+ "'>" + CommonUtils.Wrap(paRatingDetail.getRatingExplain(), 66, "<br/>") + "</td>");
		result.append("			<td style='" + tdStyle +"width:13%;font-size: 12pt;line-height:1.5;"+ "text-align: center;'>"+weight + "</td>");
		if(paRatingDetail.getRatingScore()<0){
			result.append("			<td style='" + tdStyle +"width:13%;font-size: 12pt;line-height:1.5;"+ "text-align: center;'></td>");
		}else{
			result.append("			<td style='" + tdStyle +"width:13%;font-size: 12pt;line-height:1.5;"+ "text-align: center;'>"+paRatingDetail.getRatingScore() + "</td>");
		}
		result.append("		</tr>");
		result.append("	</table>");
//		result.append("<br/>");
		// 栏目列表
		PaRatingChannelRequest ratingChannelRequery = new PaRatingChannelRequest();
		// 附件列表
		SysAttachRequest sysAttachRequest = new SysAttachRequest();
		
		ratingChannelRequery.setRatingDetailId(paRatingDetail.getId());
		//栏目列表
		List<PaRatingChannel> channelList = paRatingChannelServiceImpl.queryJoinList(ratingChannelRequery);
		if(paRatingDetail.getRatingExplain().length()>65){
			result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【自评说明】：</span><br/>" + paRatingDetail.getRatingExplain() + "</span><br/>");
		}else{
			if(StringUtils.isNotEmpty(paRatingDetail.getRatingExplain())){
				result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【自评说明】：" + paRatingDetail.getRatingExplain() + "</span><br/>");
			}
		}
		
		if (channelList!=null && channelList.size() > 0) {
			int k = 0;
			for (PaRatingChannel channel : channelList) {
				//前台添加
				if(k>0){
					result.append("<br/>");
				}
				result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【栏目名称】：" + channel.getChannelName() + "</span><br/>"); 
				if(channel.getChannelUrl().length()>65){
					result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【栏目地址】：</span><br/><a href='" + channel.getChannelUrl() + "' title='" + channel.getChannelUrl() + "' style='color: #0606e2;text-decoration: underline;'>" + channel.getChannelUrl() + "</a><br/>");
				}else{
					result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【栏目地址】：</span><a href='" + channel.getChannelUrl() + "' title='" + channel.getChannelUrl() + "' style='color: #0606e2;text-decoration: underline;'>" + channel.getChannelUrl() + "</a><br/>");
				}

				sysAttachRequest.setTbName("pa_rating_channel");
				sysAttachRequest.setTbKey(String.valueOf(channel.getId()));
				List<SysAttach> attachList = sysAttachServiceImpl.queryList(sysAttachRequest);
				for (SysAttach sysAttach : attachList) {
					//附件列表
					if((urlAdapterVar.getCloudWebLinkUrl()+ sysAttach.getPath()).length()>65){
						result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【附件地址】：</span><br/><a href='" + urlAdapterVar.getCloudWebLinkUrl()+sysAttach.getPath() + "' style='color: #0606e2;text-decoration: underline;'>" + urlAdapterVar.getCloudWebLinkUrl()+ sysAttach.getPath() + "</a><br/>");
					}else{
						result.append("<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【附件地址】：</span><br/><a href='" + urlAdapterVar.getCloudWebLinkUrl()+sysAttach.getPath() + "' style='color: #0606e2;text-decoration: underline;'>" + urlAdapterVar.getCloudWebLinkUrl()+ sysAttach.getPath() + "</a><br/>");
					}
//					String url = basePath+ "/jsp/paAppraisal/channelAttachList.jsp?flag=1&ratingChannelId=" + channel.getId();
//					result.append(INDEXEM_TWO + "附件地址：<a href='" + url + "' title='" + url + "' style='color: #0606e2;font-weight: bold;text-decoration: underline;' target='_blank'>点击查看详情</a><br/>");
				}
				//截图
				result.append(getImgTag(channel.getAddType(),"<span style='margin: 0;font-family: 仿宋;font-size: 15pt;line-height:1.2px;'>【问题截图】：</span>", channel.getImgUrl()));
				k++;
			}
		}
		return result;
	}

	
	/**
	 * 
	 * @Description: 抽提方法， 用于拼接多个截图的部分
	 * @author: sunjy --- 2016年8月10日下午2:59:54
	 * @return
	 * @throws Exception
	 */
	private String getImgTag(Integer addType,String title, String imgUrl) {
		if (!StringUtils.isNotEmpty(imgUrl)) {
			return "";
		}
		String result = "";
		try {
			if(StringUtils.isEmpty(title))
				title = "栏目截图";
			
			result = ""; 
			String[] imgUrls = imgUrl.split("\\|");
			for(String iu : imgUrls){
				//String loopUrl = urlAdapterVar.getWebPath() + iu;
				String loopUrl = urlAdapterVar.getCloubWebPaths() + iu;
				/*//前台添加
				if(addType == 1){
					loopUrl = iu.startsWith("http") ? "" : urlAdapterVar.getCloudWebPath() + iu;
					result += "<img style='width:560px;' src='" + loopUrl + "'/><br/>";
				}else {*/
					//loopUrl = iu.startsWith("http") ? "" : urlAdapterVar.getWebPath() + iu;
					loopUrl = iu.startsWith("http") ? "" : urlAdapterVar.getCloubWebPaths() + iu;
					if (FileUtils.fileExists(loopUrl)) {
						String img64;
						img64 = PicturetoBase64.getImageString(loopUrl);
						result += "<img style='width:560px;' src='data:image/png;base64," + img64 + "'/><br/>";
					}
				//}
				
			}
			if(result.length()>0){//如果有截图才增加这一段
				result = title + ":<br/>" + result;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
