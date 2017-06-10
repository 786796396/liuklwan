package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.PaRatingChannelRequest;
import com.ucap.cloud_web.dto.PaRatingDetailRequest;
import com.ucap.cloud_web.dto.PaRatingRequest;
import com.ucap.cloud_web.dto.PaTargetTaskRequest;
import com.ucap.cloud_web.dto.SysAttachRequest;
import com.ucap.cloud_web.entity.PaChannelPoint;
import com.ucap.cloud_web.entity.PaQuota;
import com.ucap.cloud_web.entity.PaRating;
import com.ucap.cloud_web.entity.PaRatingChannel;
import com.ucap.cloud_web.entity.PaRatingDetail;
import com.ucap.cloud_web.entity.PaTargetTask;
import com.ucap.cloud_web.entity.PaTask;
import com.ucap.cloud_web.entity.SysAttach;
import com.ucap.cloud_web.service.IPaChannelPointService;
import com.ucap.cloud_web.service.IPaQuotaService;
import com.ucap.cloud_web.service.IPaRatingChannelService;
import com.ucap.cloud_web.service.IPaRatingDetailService;
import com.ucap.cloud_web.service.IPaRatingService;
import com.ucap.cloud_web.service.IPaTargetTaskService;
import com.ucap.cloud_web.service.IPaTaskService;
import com.ucap.cloud_web.service.ISysAttachService;
import com.ucap.cloud_web.service.IVerifyUrlLinkService;
import com.ucap.cloud_web.util.CommonUtils;
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
public class PaRatingDetailAction extends BaseAction{
	private static Logger logger = Logger.getLogger(PaRatingDetailAction.class);
	@Autowired
	private IPaRatingDetailService paRatingDetailServiceImpl;
	@Autowired
	private IPaQuotaService paQuotaServiceImpl;
	@Autowired
	private IPaRatingService paRatingServiceImpl;
	@Autowired
	private IPaTargetTaskService paTargetTaskServiceImpl;
	@Autowired
	private IPaRatingChannelService paRatingChannelServiceImpl;
	@Autowired
	private IVerifyUrlLinkService verifyUrlLinkServiceImpl;
	@Autowired
	private ISysAttachService sysAttachServiceImpl;
	@Autowired
	private IPaChannelPointService paChannelPointServiceImpl;
	@Autowired
	private IPaTaskService paTaskServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;//配置文件
	/**
	 * 跳转到自评页面
	 * linhb - 2016-8-23
	 */
	public String paRatingDetailInfo(){
		String taskId = request.getParameter("taskId");
		String targetTaskId = request.getParameter("targetTaskId");
		String taskName =  request.getParameter("taskName");
		request.setAttribute("taskName", taskName);
		if(StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(targetTaskId)){
			PaRatingRequest pRequest = new PaRatingRequest();
			pRequest.setPaTargetTaskId(Integer.parseInt(targetTaskId));
			pRequest.setTaskId(Integer.parseInt(taskId));
			request.setAttribute("targetTaskId", targetTaskId);
			request.setAttribute("taskId", taskId);
			List<PaRating> pList = paRatingServiceImpl.queryList(pRequest);
			if(pList.size()>0){
				request.setAttribute("companyName", pList.get(0).getCompanyName());
				request.setAttribute("ratingName", pList.get(0).getRatingName());
				request.setAttribute("ratingPhone", pList.get(0).getRatingPhone());
				request.setAttribute("ratingId", pList.get(0).getId());
				
				request.setAttribute("other", pList.get(0).getOther());
				request.setAttribute("plan", pList.get(0).getPlan());
				request.setAttribute("problemsSuggestions", pList.get(0).getProblemsSuggestions());
				request.setAttribute("overallSituation", pList.get(0).getOverallSituation());
			}else{
				return "";
			}
		}else{
			return "";
		}
		return "success";
	}
	/**
	 * 获取一级指标
	 */
	public void getOneRating(){
		String ratingId = request.getParameter("ratingId");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			PaRatingDetailRequest pRequest = new PaRatingDetailRequest();
			if(StringUtils.isNotEmpty(ratingId)){
				pRequest.setRatingId(Integer.parseInt(ratingId));
			}
			List<PaRatingDetail> list =  paRatingDetailServiceImpl.groupByOneName(pRequest);
			
			map.put("list", list);
			map.put("success", "sueccess");
			logger.info("getOneRating getOneRating:获取一级指标");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取 二 三 级指标
	 */
	public void getOtherList(){
		String ratingId = request.getParameter("ratingId");
		String oneName = request.getParameter("oneName");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			PaRatingDetailRequest pRequest = new PaRatingDetailRequest();
			if(StringUtils.isNotEmpty(ratingId)){
				pRequest.setRatingId(Integer.parseInt(ratingId));
			}
			if(StringUtils.isNotEmpty(oneName)){
				pRequest.setQuota1(oneName);
			}
			pRequest.setPageSize(Integer.MAX_VALUE);
			ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			QueryOrder queryOrder = new QueryOrder("create_time", QueryOrderType.ASC);
			queryOrderList.add(queryOrder);
			pRequest.setQueryOrderList(queryOrderList);
			List<PaRatingDetail> list = paRatingDetailServiceImpl.queryList(pRequest);
			PaRatingChannelRequest paRatingChannelRequest = new PaRatingChannelRequest();
			for (PaRatingDetail paRatingDetail : list) {
				paRatingChannelRequest.setRatingDetailId(paRatingDetail.getId());
				paRatingChannelRequest.setAddType(1);
				int count = paRatingChannelServiceImpl.queryCount(paRatingChannelRequest);
				paRatingDetail.setChanlCount(count);
			}
			map.put("list", list);	
			map.put("success", "success");
			logger.info("getOneRating getOneRating:获取二级  三级指标");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
		}
		
	}
	/**
	 * 通过自评详情 id 获取 已经填报的数据（栏目名称  url 等）
	 */
	public void getWriteData(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String ratingDetailId = request.getParameter("ratingDetailId");
			if(StringUtils.isNotEmpty(ratingDetailId)){
				PaRatingDetailRequest pRequest = new PaRatingDetailRequest();
				pRequest.setRatingDetailId(Integer.parseInt(ratingDetailId));
				List<PaRatingDetail> list = paRatingDetailServiceImpl.getWriteData(pRequest);
				map.put("list", list);	
				writerPrint(JSONObject.fromObject(map).toString());
			}else{
				logger.info("通过自评详情 id 获取 已经填报的数据（栏目名称  url 等） 时 ratingDetailId 为空");
			}
		} catch (Exception e) {
			map.put("listCount", -1);
			e.printStackTrace();
		}
	}
	/**
	 * 通过自评详情 id 获取 当前指标；
	 */
	public void getCurrentIn(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String ratingDetailId = request.getParameter("ratingDetailId");
			if(StringUtils.isNotEmpty(ratingDetailId)){
				PaRatingDetail paRatingDetail = paRatingDetailServiceImpl.get(Integer.parseInt(ratingDetailId));
				map.put("quota1", paRatingDetail.getQuota1());
				map.put("quota2", paRatingDetail.getQuota2());
				map.put("quota3", paRatingDetail.getQuota3());
				map.put("ratingScore", paRatingDetail.getRatingScore()<0?null:paRatingDetail.getRatingScore());
				map.put("checkContent", paRatingDetail.getAppraisalContent());
				map.put("success", "true");
				if(paRatingDetail.getQuotaId()>0){
					PaQuota paQuota = paQuotaServiceImpl.get(paRatingDetail.getQuotaId());
					map.put("weight", paQuota.getWeight());
				}
				
				
				writerPrint(JSONObject.fromObject(map).toString());
			}else{
				map.put("success", "false");
				logger.info("通过自评详情 id 获取当前指标=========");
			}
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
		}
	}
	/**
	 * 通过自评详情 id 获取 已经填报的数据（栏目名称  url 等）
	 */
	public void getImgsAndAttch(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String paRatingChannelId = request.getParameter("paRatingChannelId");
			if(StringUtils.isNotEmpty(paRatingChannelId)){
				List<PaRatingChannel> list = new ArrayList<PaRatingChannel>();
				PaRatingChannelRequest pRequest = new PaRatingChannelRequest();
				pRequest.setId(Integer.parseInt(paRatingChannelId));
				PaRatingChannel paRatingChannel = paRatingChannelServiceImpl.get(Integer.parseInt(paRatingChannelId));
				
				SysAttachRequest sRequest = new SysAttachRequest();
				sRequest.setTbKey(String.valueOf(paRatingChannel.getId()));
				sRequest.setTbName("pa_rating_channel");
				sRequest.setPageSize(Integer.MAX_VALUE);
				List<SysAttach> sList = sysAttachServiceImpl.queryList(sRequest);
				String paths="";
				String aliasName="";
				for (SysAttach sysAttach : sList) {
					paths+=("|"+sysAttach.getPath());
					aliasName+=("|"+sysAttach.getAliasName());
				}
				paRatingChannel.setAliasName(aliasName);
				paRatingChannel.setPath(paths);
				if(paRatingChannel.getId()>0){
					list.add(paRatingChannel);
					map.put("list", list);	
				}else{
					map.put("list", new ArrayList<PaRatingChannel>());	
				}
				writerPrint(JSONObject.fromObject(map).toString());
			}else{
				logger.info("通过自评详情 id 获取 已经填报的数据（栏目名称  url 等）paRatingChannelId   为空");
			}
		} catch (Exception e) {
			map.put("listCount", -1);
			e.printStackTrace();
		}
	}
	
	/**
	 * 在线校验Url连通性
	 * 
	 * @Description:
	 * @author linhb --- 2016-09-05下午09:28:25
	 */
	public void onCheckUrl() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			String url = request.getParameter("url");
			if (StringUtils.isNotEmpty(url)) {
				if (!url.contains("http")) {
					url = "http://" + url;
				}
			}
			boolean flag = verifyUrlLinkServiceImpl.verifyUrlLink(url);

			if (flag) {
				resultMap.put("result", "true");
			} else {
				resultMap.put("result", false);
			}

			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}
	/**
	 * 填报单位  指标填报
	 */
	public void addChannel(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String channelName = request.getParameter("channelName");
			String channelUrl = request.getParameter("channelUrl");
			String ratingDetailId = request.getParameter("ratingDetailId");
			String siteCode = request.getParameter("siteCode");
			String s = request.getParameter("ht");
			s = s.replaceAll("<div>", "");
			s = s.replaceAll("<br>", "");
			s = s.replaceAll("</div>", "");
			s = s.replaceAll("<p>", "");
			s = s.replaceAll("</p>", "");
			String[] as = s.split("cloud_web/ewebeditor/uploadfile/");
			
			// 处理截图上传
			String pastenames = "";
			String[] pastes =s.split("src=\"data:image/png;base64,");
			for(int i=1;i<pastes.length;i++){
				String name = CommonUtils.GenerateImage(pastes[i].split("\"")[0], urlAdapterVar.getCloubWebPaths()+"/ewebeditor/uploadfile/");
				if(i==1){
					pastenames+="/ewebeditor/uploadfile/"+name;
				}else{
					pastenames+="|/ewebeditor/uploadfile/"+name;
				}
			}
			/**
			 * 需要保存到  channel_point  栏目名称 栏目地址
			 * 保存到附件表中 
			 * 保存到  自评详情表中
			 * 保存到关联表中
			 */
			String imgs = "";//图片路径
			String paths= "";//附件路径
			String names= "";//附件对应的真实名称
			for (int i = 1; i < as.length; i++) {
				String ss = as[i];
				if(ss.contains("target=\"_blank\"")){//附件
					String[] arrs = ss.split("\"");
					System.out.println(arrs[0]);//保存到  文件夹下的名称
					String[] ass = ss.split("target=\"_blank\">");
					System.out.println(ass[1].split("</a>")[0]);// 上传时的真实名称
					if("".equals(paths)){
						paths+="/ewebeditor/uploadfile/"+arrs[0];
					}else{
						paths+="|/ewebeditor/uploadfile/"+arrs[0];
					}
					if("".equals(names)){
						names+=ass[1].split("</a>")[0];
					}else{
						names+="|"+ass[1].split("</a>")[0];
					}
				}else{//图片  
					System.out.println("    ----------");
					String[] arrs = ss.split("\"");
					if("".equals(imgs)){
						imgs+="/ewebeditor/uploadfile/"+arrs[0];
					}else{
						imgs+="|/ewebeditor/uploadfile/"+arrs[0];
					}
					System.out.println(arrs[0]); //图片的名称
					System.out.println("----------     ");
				}
			}
			//保存  栏目 
			PaChannelPoint pacPoint = new PaChannelPoint();
			pacPoint.setChannelName(channelName);
			pacPoint.setChannelUrl(channelUrl);
			pacPoint.setSiteCode(siteCode);
			//============需要添加一个字段标识为  绩效考核  添加的栏目
			paChannelPointServiceImpl.add(pacPoint);
			//添加栏目 详情关联表
			PaRatingChannel pChannel = new PaRatingChannel();
			pChannel.setAddType(1);
			pChannel.setChannelId(pacPoint.getId());
			if(StringUtils.isNotEmpty(ratingDetailId)){
				pChannel.setRatingDetailId(Integer.parseInt(ratingDetailId));
			}else{
				return;
			}
			if(imgs.length()>0){
				imgs+="|"+pastenames;
			}else{
				imgs+=pastenames;
			}
			pChannel.setImgUrl(imgs);
			paRatingChannelServiceImpl.add(pChannel);
			
			if(paths.length()>0){
				//保存附件 
				SysAttach sAttach = new SysAttach();
				sAttach.setTbName("pa_rating_channel");
				sAttach.setTbKey(pChannel.getId());
				// 一对多的保存
				String[] arrpaths = paths.split("\\|");
				String[] arrnames = names.split("\\|");
				for(int i=0;i<arrpaths.length;i++){
					sAttach.setAliasName(arrnames[i]);//对应外界 需要显示的名字；
					sAttach.setPath(arrpaths[i]);
					sysAttachServiceImpl.add(sAttach);
				}
			}

			map.put("success", "true");
			logger.info(" 填报单位  指标填报========================");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	
	/**
	 * 填报单位  指标填报 修改
	 */
	public void updateChannel(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String channelName = request.getParameter("channelName");
			String channelUrl = request.getParameter("channelUrl");
			String paRatingChannelId = request.getParameter("paRatingChannelId");
			String s = request.getParameter("ht");
			s = s.replaceAll("<div>", "");
			s = s.replaceAll("<br>", "");
			s = s.replaceAll("</div>", "");
			s = s.replaceAll("<p>", "");
			s = s.replaceAll("</p>", "");
			String[] as = s.split("cloud_web/ewebeditor/uploadfile/");
			// 处理截图上传
			String pastenames = "";
			String[] pastes =s.split("src=\"data:image/png;base64,");
			for(int i=1;i<pastes.length;i++){
				String name = CommonUtils.GenerateImage(pastes[i].split("\"")[0], urlAdapterVar.getCloubWebPaths()+"/ewebeditor/uploadfile/");
				if(i==1){
					pastenames+="/ewebeditor/uploadfile/"+name;
				}else{
					pastenames+="|/ewebeditor/uploadfile/"+name;
				}
			}
			
			/**
			 * 需要保存到  channel_point  栏目名称 栏目地址
			 * 保存到附件表中 
			 * 保存到  自评详情表中
			 * 保存到关联表中
			 */
			String imgs = "";//图片路径
			String paths= "";//附件路径
			String names= "";//附件对应的真实名称
			for (int i = 1; i < as.length; i++) {
				String ss = as[i];
				if(ss.contains("target=\"_blank\"")){//附件
					String[] arrs = ss.split("\"");
					System.out.println(arrs[0]);//保存到  文件夹下的名称
					String[] ass = ss.split("target=\"_blank\">");
					System.out.println(ass[1].split("</a>")[0]);// 上传时的真实名称
					if("".equals(paths)){
						paths+="/ewebeditor/uploadfile/"+arrs[0];
					}else{
						paths+="|/ewebeditor/uploadfile/"+arrs[0];
					}
					if("".equals(names)){
						names+=ass[1].split("</a>")[0].split("<img")[0];
					}else{
						names+="|"+ass[1].split("</a>")[0].split("<img")[0];
					}
				}else{//图片  
					System.out.println("    ----------");
					String[] arrs = ss.split("\"");
					if("".equals(imgs)){
						imgs+="/ewebeditor/uploadfile/"+arrs[0];
					}else{
						imgs+="|/ewebeditor/uploadfile/"+arrs[0];
					}
					System.out.println(arrs[0]); //图片的名称
					System.out.println("----------     ");
				}
			}
			PaRatingChannel paRatingChannel = new PaRatingChannel();
			if(StringUtils.isNotEmpty(paRatingChannelId)){
				paRatingChannel = paRatingChannelServiceImpl.get(Integer.parseInt(paRatingChannelId));
			}else{
				logger.info(" 填报单位  指标填报 修改==自评栏目关联表  为空    ============");
				return;
			}
			if(imgs.length()>0){
				imgs+="|"+pastenames;
			}else{
				imgs+=pastenames;
			}
			paRatingChannel.setImgUrl(imgs);
			// 更新 自评关联表 
			paRatingChannelServiceImpl.update(paRatingChannel);
			//更新  栏目 
			PaChannelPoint paChannelPoint = paChannelPointServiceImpl.get(paRatingChannel.getChannelId());
			paChannelPoint.setChannelName(channelName);
			paChannelPoint.setChannelUrl(channelUrl);
			//============需要添加一个字段标识为  绩效考核  添加的栏目
			paChannelPointServiceImpl.update(paChannelPoint);

			// 先删除  然后  添加
			SysAttachRequest sRequest = new SysAttachRequest();
			sRequest.setTbKey(String.valueOf(paRatingChannel.getId()));
			sRequest.setTbName("pa_rating_channel");
			sysAttachServiceImpl.deleteByTbNameAndId(sRequest);
			
			if(paths.length()>0){
				//保存附件 
				SysAttach sAttach = new SysAttach();
				sAttach.setTbName("pa_rating_channel");
				sAttach.setTbKey(paRatingChannel.getId());
				// 一对多的保存
				String[] arrpaths = paths.split("\\|");
				String[] arrnames = names.split("\\|");
				for(int i=0;i<arrpaths.length;i++){
					sAttach.setAliasName(arrnames[i]);//对应外界 需要显示的名字；
					sAttach.setPath(arrpaths[i]);
					sysAttachServiceImpl.add(sAttach);
				}
			}
			map.put("success", "true");
			logger.info("填报单位  指标填报 修改==   ========================");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	
	/**
	 * 填报单位  指标填报    删除
	 */
	public void deleteChannel(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String paRatingChannelId = request.getParameter("paRatingChannelId");
			PaRatingChannel paRatingChannel = new PaRatingChannel();
			if(StringUtils.isNotEmpty(paRatingChannelId)){
				paRatingChannel = paRatingChannelServiceImpl.get(Integer.parseInt(paRatingChannelId));
			}else{
				logger.info(" 填报单位  指标填报 删除  ==自评栏目关联表  为空    ============");
				return;
			}
			// 删除 自评关联表 
			paRatingChannelServiceImpl.delete(paRatingChannel.getId());
			//删除  栏目 
			paChannelPointServiceImpl.delete(paRatingChannel.getChannelId());

			
			//删除附件记录 
			SysAttachRequest sAttach = new SysAttachRequest();
			sAttach.setTbName("pa_rating_channel");
			sAttach.setTbKey(String.valueOf(paRatingChannel.getId()));
			sAttach.setPageSize(Integer.MAX_VALUE);
			List<SysAttach> list = sysAttachServiceImpl.queryList(sAttach);
			for (SysAttach sysAttach : list) {
				sysAttachServiceImpl.delete(sysAttach.getId());
			}
			
			map.put("success", "true");
			logger.info("填报单位  指标填报 删除==   ========================");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	
	/**
	 * 填报单位  指标填报    删除
	 */
	public void savePaRating(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String ratingDetailId = request.getParameter("ratingDetailId");
			String ratingExplain = request.getParameter("ratingExplain");
			String ratingScore = request.getParameter("ratingScore");
			
			PaRatingDetail paRatingDetail = new PaRatingDetail();
			if(StringUtils.isNotEmpty(ratingDetailId)){
				paRatingDetail = paRatingDetailServiceImpl.get(Integer.parseInt(ratingDetailId));
			}else{
				logger.info(" 填报单位  指标填报  保存详情  ==自评栏目关联表  为空    ============");
				return;
			}
			paRatingDetail.setRatingExplain(ratingExplain);
			paRatingDetail.setRatingStauts((short)1);//已经填报
			if(StringUtils.isNotEmpty(ratingScore)){
				paRatingDetail.setRatingScore(Float.valueOf(ratingScore));
			}else{
				paRatingDetail.setRatingScore((float) -100.00);
			}
			paRatingDetailServiceImpl.update(paRatingDetail);
			
			String ratingId = request.getParameter("ratingId");
			PaRatingDetailRequest pRequest = new PaRatingDetailRequest();
			pRequest.setRatingId(Integer.parseInt(ratingId));
			double countSum = paRatingDetailServiceImpl.queryCount(pRequest);
			
			pRequest.setRatingStauts(1);
			double countFin = paRatingDetailServiceImpl.queryCount(pRequest);
			double par = countFin/countSum;
			//DecimalFormat  d = new DecimalFormat("#.##");
			String targetTaskId = request.getParameter("targetTaskId");
			if(StringUtils.isNotEmpty(targetTaskId)){
				PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(Integer.parseInt(targetTaskId));
				if(Integer.parseInt(targetTaskId)>0){
					paTargetTask.setRatingProgress(par*(double)100);
					paTargetTaskServiceImpl.update(paTargetTask);
				}
			}
			
			map.put("success", "true");
			logger.info("填报单位  指标填报  保存详情  == ========================");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	
	/**
	 * 填报单位 核查是否填报完成
	 */
	public void checkIsAllFinash(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String ratingId = request.getParameter("ratingId");
			String targetTaskId = request.getParameter("targetTaskId");
			int count =0;
			if(StringUtils.isNotEmpty(ratingId)){
				PaRatingDetailRequest pRequest = new PaRatingDetailRequest();
				pRequest.setRatingId(Integer.parseInt(ratingId));
				pRequest.setRatingStauts(2);
				count = paRatingDetailServiceImpl.queryCount(pRequest);
			}else{
				logger.info("  填报单位 核查是否填报完成     自评id有问题  ============");
				return;
			}
			if(count>0){
				map.put("success", "noFinash");
			}else{
				map.put("success", "finash");
				if(StringUtils.isNotEmpty(targetTaskId)){
					PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(Integer.parseInt(targetTaskId));
					if(Integer.parseInt(targetTaskId)>0){
						paTargetTask.setRatingState((short)1);
						// 单个站点的 有填报中修改为  已提交
						paTargetTaskServiceImpl.update(paTargetTask);
						
						//对整个任务做自评进度处理；
						PaTargetTaskRequest tRequest = new PaTargetTaskRequest();
						tRequest.setTaskId(paTargetTask.getTaskId());
						tRequest.setStauts(1);
						int sum = paTargetTaskServiceImpl.queryCount(tRequest);
						tRequest.setRatingState("1");
						int cou = paTargetTaskServiceImpl.queryCount(tRequest);
						PaTask paTask = paTaskServiceImpl.get(paTargetTask.getTaskId());
						paTask.setRatingProgress(((double)cou/(double)sum)*100);
						paTaskServiceImpl.update(paTask);
						
						//修改填报人  填报名称  联系电话
						String ratingName = request.getParameter("ratingName");
						String companyName = request.getParameter("companyName");
						String ratingPhone = request.getParameter("ratingPhone");
						
						String other = request.getParameter("other");
						String plan = request.getParameter("plan");
						String problemsSuggestions = request.getParameter("problemsSuggestions");
						String overallSituation = request.getParameter("overallSituation");
						
						PaRating pRating = paRatingServiceImpl.get(Integer.parseInt(ratingId));
						pRating.setCompanyName(companyName);
						pRating.setRatingName(ratingName);
						pRating.setRatingPhone(ratingPhone);
						
						pRating.setOther(other);
						pRating.setPlan(plan);
						pRating.setProblemsSuggestions(problemsSuggestions);
						pRating.setOverallSituation(overallSituation);
						
						paRatingServiceImpl.update(pRating);
						map.put("success", "success");
					}
				}
			}
			logger.info(" 填报单位 核查是否填报完成 ========================");
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
}
