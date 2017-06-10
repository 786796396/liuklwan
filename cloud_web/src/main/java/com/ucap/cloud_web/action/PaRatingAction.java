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
import com.ucap.cloud_web.dto.PaQuotaRequest;
import com.ucap.cloud_web.dto.PaRatingRequest;
import com.ucap.cloud_web.dto.SysAttachRequest;
import com.ucap.cloud_web.entity.PaAppraisal;
import com.ucap.cloud_web.entity.PaQuota;
import com.ucap.cloud_web.entity.PaRating;
import com.ucap.cloud_web.entity.PaRatingDetail;
import com.ucap.cloud_web.entity.PaTargetTask;
import com.ucap.cloud_web.entity.PaTask;
import com.ucap.cloud_web.entity.SysAttach;
import com.ucap.cloud_web.service.IPaAppraisalService;
import com.ucap.cloud_web.service.IPaQuotaService;
import com.ucap.cloud_web.service.IPaRatingDetailService;
import com.ucap.cloud_web.service.IPaRatingService;
import com.ucap.cloud_web.service.IPaTargetTaskService;
import com.ucap.cloud_web.service.IPaTaskService;
import com.ucap.cloud_web.service.ISysAttachService;
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
public class PaRatingAction extends BaseAction{
	private static Logger logger = Logger.getLogger(PaRatingAction.class);
	@Autowired
	private IPaRatingService paRatingServiceImpl;
	@Autowired
	private IPaRatingDetailService paRatingDetailServiceImpl;
	@Autowired
	private IPaTargetTaskService paTargetTaskServiceImpl;
	@Autowired
	private IPaQuotaService paQuotaServiceImpl;
	@Autowired
	private IPaTaskService paTaskServiceImpl;
	@Autowired
	private IPaAppraisalService paAppraisalServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private ISysAttachService sysAttachServiceImpl;
	/**
	 * 跳转到自评页面
	 * linhb - 2016-8-23
	 */
	public String paRatingDetail(){
		request.setAttribute("taskId", request.getParameter("taskId"));
		request.setAttribute("paTargetTaskId", request.getParameter("paTargetTaskId"));
		request.setAttribute("siteName", request.getParameter("siteName"));
		System.out.println(request.getParameter("taskId"));
		System.out.println(request.getParameter("paTargetTaskId"));
		return "success";
	}
	/**
	 * 跳转到考评页面
	 * linhb - 2016-8-23
	 */
	public String paOtherRatingDetail(){
		request.setAttribute("taskId", request.getParameter("taskId"));
		request.setAttribute("paTargetTaskId", request.getParameter("paTargetTaskId"));
		request.setAttribute("siteName", request.getParameter("siteName"));
		System.out.println(request.getParameter("taskId"));
		System.out.println(request.getParameter("paTargetTaskId"));
		return "success";
	}
	/**
	 * 查询自评详情
	 * linhb - 2016-8-25
	 */
	public void paRatingList(){
		try {
			PaRatingRequest paRatingRequest = new PaRatingRequest();
			Map<String,Object> map = new HashMap<String, Object>();
			String taskId = request.getParameter("taskId");
			String addType = request.getParameter("addType");
			String paTargetTaskId = request.getParameter("paTargetTaskId");
			if(StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(paTargetTaskId)&& StringUtils.isNotEmpty(addType)){//组织单位
				paRatingRequest.setTaskId(Integer.parseInt(taskId));
				paRatingRequest.setPaTargetTaskId(Integer.parseInt(paTargetTaskId));
				paRatingRequest.setAddType(Integer.parseInt(addType));//查看 自评
			}else{
				return;
			}
			logger.info("paProjectRequest()===paProjectList   查询自评详情   =");
			paRatingRequest.setPageSize(Integer.MAX_VALUE);
			List<PaRating> listRating= paRatingServiceImpl.queryList(paRatingRequest);
			String ratingName="";
			String companyName ="";
			String phone = "";
			
			String overallSituation="";
			String problemsSuggestions ="";
			String other = "";
			String plan="";
			if(listRating.size()>0){
				ratingName = listRating.get(0).getRatingName();
				companyName =  listRating.get(0).getCompanyName();
				phone = listRating.get(0).getRatingPhone();
				
				overallSituation=listRating.get(0).getOverallSituation();
				problemsSuggestions =listRating.get(0).getProblemsSuggestions();
				other = listRating.get(0).getOther();
				plan=listRating.get(0).getPlan();
			}
			map.put("ratingName", ratingName);
			map.put("companyName", companyName);
			map.put("phone", phone);
			
			map.put("overallSituation", StringUtils.isNotEmpty(overallSituation)?overallSituation:"");
			map.put("problemsSuggestions", StringUtils.isNotEmpty(problemsSuggestions)?problemsSuggestions:"");
			map.put("other", StringUtils.isNotEmpty(other)?other:"");
			map.put("plan", StringUtils.isNotEmpty(plan)?plan:"");
			if(addType.equals("1")){
				map.put("paRatingUrl", urlAdapterVar.getCloudWebLinkUrl());
			}else{
				map.put("paRatingUrl", urlAdapterVar.getLinkUrl());
			}
			List<PaRatingDetail> list= paRatingServiceImpl.queryFour(paRatingRequest);
			for (PaRatingDetail paRatingDetail : list) {
				if(paRatingDetail.getPaRatingChannelId() != null && paRatingDetail.getPaRatingChannelId()>0){
					SysAttachRequest sRequest = new SysAttachRequest();
					sRequest.setTbKey(String.valueOf(paRatingDetail.getPaRatingChannelId()));
					sRequest.setTbName("pa_rating_channel");
					sRequest.setPageSize(Integer.MAX_VALUE);
					List<SysAttach> sList = sysAttachServiceImpl.queryList(sRequest);
					String paths="";
					String aliasName="";
					for (SysAttach sysAttach : sList) {
						paths+=("|"+sysAttach.getPath());
						aliasName+=("|"+sysAttach.getAliasName());
					}
					paRatingDetail.setAliasName(aliasName);
					paRatingDetail.setPath(paths);
				}
			}
			map.put("list",list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加自评详情
	 * linhb - 2016-8-25
	 */
	public void addRating(){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		try {
			logger.info("addRating()===addRating=");
			String companyName = request.getParameter("companyName");
			String ratingName = request.getParameter("ratingName");
			String ratingPhone = request.getParameter("ratingPhone");
			String siteCode = request.getParameter("siteCode");
			String taskId = request.getParameter("taskId");
			String targetTaskId = request.getParameter("targetTaskId");
			String appraisalId = request.getParameter("appraisalId");
			if(StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(siteCode)&& StringUtils.isNotEmpty(targetTaskId)&& StringUtils.isNotEmpty(appraisalId)){//组织单位
				PaRating pRating = new PaRating();
				
				PaRatingRequest pRatingRequest = new PaRatingRequest();
				pRatingRequest.setTaskId(Integer.parseInt(taskId));
				pRatingRequest.setPaTargetTaskId(Integer.parseInt(targetTaskId));
				pRatingRequest.setPageSize(Integer.MAX_VALUE);
				
				ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
				QueryOrder queryOrder = new QueryOrder("create_time", QueryOrderType.ASC);
				queryOrderList.add(queryOrder);
				pRatingRequest.setQueryOrderList(queryOrderList);
				
				
				List<PaRating> listPaRating = paRatingServiceImpl.queryList(pRatingRequest);
				//如果后台  开始考评了  ，对应的指标会添加到  自评详情表中
				if(listPaRating.size()>0){
					pRating=listPaRating.get(0);
					
					pRating.setRatingName(ratingName);
					pRating.setCompanyName(companyName);
					pRating.setSiteCode(siteCode);
					pRating.setRatingPhone(ratingPhone);
					
					paRatingServiceImpl.update(pRating);
					
					//修改 任务对象关联表  状态
					PaTargetTask paTargetTask= paTargetTaskServiceImpl.get(Integer.parseInt(targetTaskId));
					paTargetTask.setRatingState((short)3);
					paTargetTaskServiceImpl.update(paTargetTask);
				}else{
					pRating.setRatingName(ratingName);
					pRating.setCompanyName(companyName);
					pRating.setSiteCode(siteCode);
					pRating.setRatingPhone(ratingPhone);
					pRating.setTargetTaskId(Integer.parseInt(targetTaskId));
					pRating.setTaskId(Integer.parseInt(taskId));
					
					// pRating 可以获取到  该条记录的id
					paRatingServiceImpl.add(pRating);
					//修改 任务对象关联表  状态
					PaTargetTask paTargetTask= paTargetTaskServiceImpl.get(Integer.parseInt(targetTaskId));
					paTargetTask.setRatingState((short)3);
					paTargetTaskServiceImpl.update(paTargetTask);
					//自评详细表中添加数据
					PaQuotaRequest qRequest = new PaQuotaRequest();
					qRequest.setAppraisalId(Integer.parseInt(appraisalId));
					//通过评估对象 id 获取到的 指标数据
					qRequest.setPageSize(Integer.MAX_VALUE);
					List<PaQuota> list = paQuotaServiceImpl.queryList(qRequest);
					Map<Integer, PaQuota> map = new HashMap<Integer, PaQuota>();
					List<PaQuota> rtnList =new ArrayList<PaQuota>();

					for(PaQuota pq : list){
						map.put(pq.getId(), pq);
						if(pq.getIsFinal()!=null&& pq.getIsFinal()==1){//为最低层指标
							rtnList.add(pq);
						}
					}
					//循环填充上级指标
					for(PaQuota pq : rtnList){
						PaQuota parent = null;
						
						//3层的
						if(pq.getLayer() == 3){
							pq.setLayer3(pq.getName());
							parent = map.get(pq.getParentId());
							if(parent != null){
								pq.setLayer2(parent.getName());
								parent = map.get(parent.getParentId());
								if(parent != null){
									pq.setLayer1(parent.getName());
								}
							}
						}
						
						//2层的
						if(pq.getLayer() == 2){
							pq.setLayer2(pq.getName());
							parent = map.get(pq.getParentId());
							if(parent != null){
								pq.setLayer1(parent.getName());
							}
						}
						
						//1层的
						if(pq.getLayer() == 1){
							pq.setLayer1(pq.getName());
						}
						// 把最底层的指标放到  自评详情表中；
						PaRatingDetail paRatingDetail = new PaRatingDetail();
						paRatingDetail.setQuota1(pq.getLayer1());
						paRatingDetail.setQuota2(pq.getLayer2());
						paRatingDetail.setQuota3(pq.getLayer3());
						paRatingDetail.setRatingId(pRating.getId());
						paRatingDetail.setQuotaId(pq.getId());
						paRatingDetail.setQuotaParentId(pq.getParentId());
						paRatingDetail.setRatingStauts((short)2);//未填  状态
						paRatingDetail.setScoreRules(pq.getScoreRules());
						paRatingDetail.setAppraisalContent(pq.getContent());
						paRatingDetail.setScoreStauts((short)2);// 未打分状态
						paRatingDetail.setScoreRules(pq.getScoreRules());
						
						paRatingDetailServiceImpl.add(paRatingDetail);
					}
				}
				returnMap.put("success", "true");
				returnMap.put("ratingId", pRating.getId());
			}else{
				return;
			}
			
			writerPrint(JSONObject.fromObject(returnMap).toString());
		} catch (Exception e) {
			returnMap.put("success", false);
			writerPrint(JSONObject.fromObject(returnMap).toString());
			e.printStackTrace();
		}
	}
	/**
	 * 添加自评详情
	 * linhb - 2016-8-25
	 */
	public void getIndictor(){
		String appraisalId = request.getParameter("appraisalId");
		String taskId = request.getParameter("taskId");
		Map<String,Object> mapOut = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(appraisalId)){
				//自评详细表中添加数据
				PaQuotaRequest qRequest = new PaQuotaRequest();
				qRequest.setAppraisalId(Integer.parseInt(appraisalId));
				//通过评估对象 id 获取到的 指标数据
				qRequest.setPageSize(Integer.MAX_VALUE);
				ArrayList<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
				QueryOrder queryOrder = new QueryOrder("create_time", QueryOrderType.ASC);
				queryOrderList.add(queryOrder);
				qRequest.setQueryOrderList(queryOrderList);
				List<PaQuota> list = paQuotaServiceImpl.queryList(qRequest);
				Map<Integer, PaQuota> map = new HashMap<Integer, PaQuota>();
				List<PaQuota> rtnList =new ArrayList<PaQuota>();

				for(PaQuota pq : list){
					map.put(pq.getId(), pq);
					if(pq.getIsFinal()!=null&& pq.getIsFinal()==1){//为最低层指标
						rtnList.add(pq);
					}
				}
				List<PaQuota> listNew = new ArrayList<PaQuota>();
				//循环填充上级指标
				for(PaQuota pq : rtnList){
					PaQuota parent = null;
					
					//3层的
					if(pq.getLayer() == 3){
						pq.setLayer3(pq.getName());
						parent = map.get(pq.getParentId());
						if(parent != null){
							pq.setLayer2(parent.getName());
							parent = map.get(parent.getParentId());
							if(parent != null){
								pq.setLayer1(parent.getName());
							}
						}
					}
					
					//2层的
					if(pq.getLayer() == 2){
						pq.setLayer2(pq.getName());
						parent = map.get(pq.getParentId());
						if(parent != null){
							pq.setLayer1(parent.getName());
						}
					}
					
					//1层的
					if(pq.getLayer() == 1){
						pq.setLayer1(pq.getName());
					}
					listNew.add(pq);
				}
				PaTask pTask =paTaskServiceImpl.get(Integer.parseInt(taskId));
				PaAppraisal pAppraisal = paAppraisalServiceImpl.get(Integer.parseInt(appraisalId));
				mapOut.put("list", listNew);
				mapOut.put("taskName", pTask.getTaskName());
				mapOut.put("aName", pAppraisal.getName());
				mapOut.put("success", "true");
				writerPrint(JSONObject.fromObject(mapOut).toString());
			}else{
				return;
			}
		} catch (Exception e) {
			mapOut.put("success", false);
			writerPrint(JSONObject.fromObject(mapOut).toString());
			e.printStackTrace();
		}
		

	}
	
	/**
	 * 添加自评详情
	 * linhb - 2016-8-25
	 */
	public String paRatingIndictor(){
		String appraisalId = request.getParameter("appraisalId");
		String taskId = request.getParameter("taskId");
		request.setAttribute("appraisalId",appraisalId);
		request.setAttribute("taskId",taskId);
		return "success";
	}
	/**
	 * 添加自评详情
	 * linhb - 2016-8-25
	 */
	public void updateRating(){
		try {
			String ratingId = request.getParameter("ratingId");
			String ratingName = request.getParameter("ratingName");
			String companyName = request.getParameter("companyName");
			String ratingPhone = request.getParameter("ratingPhone");
			String overallSituation = request.getParameter("overallSituation");
			String problemsSuggestions = request.getParameter("problemsSuggestions");
			String plan = request.getParameter("plan");
			String other = request.getParameter("other");
			if(StringUtils.isNotEmpty(ratingId)){
				PaRating paRating = paRatingServiceImpl.get(Integer.parseInt(ratingId));
				paRating.setRatingName(ratingName);
				paRating.setCompanyName(companyName);
				paRating.setRatingPhone(ratingPhone);
				paRating.setOverallSituation(overallSituation);
				paRating.setProblemsSuggestions(problemsSuggestions);
				paRating.setPlan(plan);
				paRating.setOther(other);
				paRatingServiceImpl.update(paRating);
			}
		} catch (Exception e) {
		}
	}
	/**
	 * 通过targetTaskId 获取自评数据
	 * linhb - 2016-8-25
	 */
	public void getRatingByOtherId(){
		Map<String,Object> mapOut = new HashMap<String, Object>();
		try {
			String taskId = request.getParameter("taskId");
			String targetTaskId = request.getParameter("targetTaskId");
			if(StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(targetTaskId)  ){
				PaRatingRequest rRequest = new PaRatingRequest();
				rRequest.setPaTargetTaskId(Integer.parseInt(targetTaskId));
				rRequest.setTaskId(Integer.parseInt(taskId));
				List<PaRating> list= paRatingServiceImpl.queryList(rRequest);
				if(list.size()>0){
					mapOut.put("paRatingId", list.get(0).getId());
					mapOut.put("success", "true");
				}
			}
			writerPrint(JSONObject.fromObject(mapOut).toString());	
		} catch (Exception e) {
			mapOut.put("success", "false");
			writerPrint(JSONObject.fromObject(mapOut).toString());
		}
	}
}
