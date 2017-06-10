package com.ucap.cloud_web.action;

import java.util.ArrayList;
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

import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.constant.SecurityQuestionType;
import com.ucap.cloud_web.dto.SecurityQuestionRequest;
import com.ucap.cloud_web.entity.SecurityQuestion;
import com.ucap.cloud_web.service.ISecurityQuestionService;
import com.ucap.cloud_web.util.ExportExcel;

/**
 * 描述： 安全问题
 * 包：com.ucap.cloud_web.action
 * 文件名称：SecurityQuestion
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-9-2上午10:03:41 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SecurityQuestionAction extends BaseAction{
	@Autowired
	private ISecurityQuestionService securityQuestionServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	private static Log logger = LogFactory.getLog(SecurityQuestionAction.class);
	
	
	/**
	 * @Title: 安全问题 index页面
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-2上午10:19:44
	 * @return
	 */
	public String index(){
		try {
			//siteCode处理由组织单位跳转到该页面时，session的修改
			String siteCode = request.getParameter("siteCode");
			
			if(StringUtils.isNotEmpty(siteCode)){
				setCurrentShiroUser(siteCode);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public String safetyScanning(){
		return "success";
	}
	
	
	/**
	 * @Title: 查询安全问题 列表查询
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-9-2上午10:19:49
	 */
	public void securityQuestionPage(){
		Map<String,Object> map_list=new HashMap<String, Object>();
		List<Object> returnList=new ArrayList<Object>();
		try {
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			

			/**
			 * 通过网站标识码 scandate   查询安全问题详情表
			*/
			SecurityQuestionRequest securityQuestionRequest=new SecurityQuestionRequest();
			securityQuestionRequest.setSiteCode(siteCode);
			List<QueryOrder> queryBlankList = new ArrayList<QueryOrder>();
			//前台去掉周期，发现时间倒排序
			QueryOrder siteQueryOrder=new QueryOrder("scan_date",QueryOrderType.DESC);
			queryBlankList.add(siteQueryOrder);
			securityQuestionRequest.setQueryOrderList(queryBlankList);
			
			//查询昨天的数据
			String yesDate = DateUtils.getNextDay(new Date(), -1);
			securityQuestionRequest.setScanDate(yesDate);
			securityQuestionRequest.setDelFlag(0);
			int total=0;
			List<SecurityQuestion> questionList=new ArrayList<SecurityQuestion>();
			questionList=securityQuestionServiceImpl.queryList(securityQuestionRequest);
			if(questionList!=null && questionList.size()>0){
				for(SecurityQuestion securityQuestion : questionList){
					total=total+1;
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("dataNumber", total+1);//序号
					map.put("scanDate",securityQuestion.getScanDate());//监测时间
					String indicatorName="";//三级指标
					if(securityQuestion.getDicIndicatorId()!=null){
						 indicatorName=SecurityQuestionType.getNameByCode(securityQuestion.getDicIndicatorId());
					}else{
						indicatorName="";
					}
					
					map.put("indicatorName", indicatorName);//三级指标
					map.put("problemDesc", securityQuestion.getProblemDesc());//问题描述
					map.put("webVersion",securityQuestion.getWebVersion());//浏览器版本
					map.put("path", securityQuestion.getPath());//附件路径
					String imgStr=securityQuestion.getImgUrl();
//					if(imgStr.contains("htm")){//快照
//						map.put("imgUrl", urlAdapterVar.getImgUrl()+imgStr);//快照
//					}else{
						map.put("imgUrl", imgStr);//截图
//					}
					returnList.add(map);
				}
				map_list.put("returnList", returnList);//列表查询数据
				map_list.put("total", total);//安全问题个数
			}else{
				map_list.put("returnList", "");//列表查询数据
				map_list.put("total", 0);//安全问题个数
			}
			map_list.put("litImgUrl", urlAdapterVar.getLinkUrl());
			logger.info("map_list="+map_list);
			writerPrint(JSONObject.fromObject(map_list).toString());	
		} catch (Exception e) {
			e.printStackTrace();
			map_list.put("errorMsg", "查询安全问题详情数据异常！");
			writerPrint(JSONObject.fromObject(map_list).toString());
		}
	}
	/**
	 * @Description: 安全问题  excel导出
	 * @author cuichx --- 2015-11-25上午11:42:02
	 */
	public void questionDetailExcel(){
		try {
			//获取页面参数
			//从session中获取10位填报单位网站标识码
			String siteCode=getCurrentUserInfo().getChildSiteCode();
			if(StringUtils.isEmpty(siteCode)){
				siteCode=getCurrentUserInfo().getSiteCode();
			}
			
			//封装数据中查询的结果
			ArrayList<Object[]> list = new ArrayList<Object[]>();
			//excel标题
			Object[] obj1 = new Object[]{"序号","监测时间","三级指标","问题描述","浏览器版本","附件","截图"};
			list.add(obj1);
			//内容保障问题-空白栏目监测结果(YYYY-MM-DD)
			String fileName = "安全问题监测结果("+DateUtils.formatStandardDate(new Date())+").xls";
			String title = "安全问题监测结果"; 
			
			//dto对象封装页面参数
			SecurityQuestionRequest securityQuestionRequest=new SecurityQuestionRequest();
			securityQuestionRequest.setSiteCode(siteCode);
			
			//查询今天之前的数据
			String yesDate = DateUtils.getNextDay(new Date(), 0);
			securityQuestionRequest.setScanDate(yesDate);
			
			//根据服务周期id和网站标识码查询空白栏目详情表
			List<SecurityQuestion>  scuDetailList=securityQuestionServiceImpl.queryList(securityQuestionRequest);
			if(scuDetailList!=null && scuDetailList.size()>0){
				for(int i=0;i<scuDetailList.size();i++){
					SecurityQuestion securityQuestion=scuDetailList.get(i);
					Object[] obj = new Object[7];
					obj[0]=i+1;
					
					String scanDate=securityQuestion.getScanDate();//监测时间
					if(StringUtils.isNotEmpty(scanDate)){
						obj[1]=scanDate;
					}else{
						obj[1]="";
					}
					
					//三级指标
					if(securityQuestion.getDicIndicatorId()!=null){
						obj[2]=SecurityQuestionType.getNameByCode(securityQuestion.getDicIndicatorId());
					}else{
						obj[2]="";
					}
					
					
					
					if(StringUtils.isNotEmpty(securityQuestion.getProblemDesc())){
						obj[3]=securityQuestion.getProblemDesc();//问题描述
					}else{
						obj[3]="";//问题描述
					}
					
					String webVersion=securityQuestion.getWebVersion();//浏览器版本
					if(StringUtils.isNotEmpty(webVersion)){
						obj[4]=securityQuestion.getProblemDesc();//浏览器版本
					}else{
						obj[4]="";//浏览器版本
					}
					
					String path=securityQuestion.getPath();//附件路径
					if(StringUtils.isNotEmpty(path)){
						obj[5]=urlAdapterVar.getLinkUrl()+path;//附件路径
					}else{
						obj[5]="";//附件路径
					}
					
					String imgUrl=securityQuestion.getImgUrl();//截图
					String[] imgUrlStr=imgUrl.split("\\|");
						for(int j=0;j<imgUrlStr.length;j++){
							if(StringUtils.isNotEmpty(imgUrlStr[j])){
								imgUrlStr[j]=urlAdapterVar.getLinkUrl()+imgUrlStr[j];
							}
							
						}
						obj[6]=imgUrlStr;
					list.add(obj);
				}
			}
			ExportExcel.securityQuestionExcel(fileName, title, list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
