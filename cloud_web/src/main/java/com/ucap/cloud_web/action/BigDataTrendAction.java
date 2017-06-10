package com.ucap.cloud_web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.HealthyType;
import com.ucap.cloud_web.dto.BigAuthDetailRequest;
import com.ucap.cloud_web.dto.BigDataTrendRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.BigAuthDetail;
import com.ucap.cloud_web.entity.BigDataTrend;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.service.IBigAuthDetailService;
import com.ucap.cloud_web.service.IBigDataTrendService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.util.Des;

/**描述： 日常监控同级的焦点信息跳转
 * 包：com.ucap.cloud_web.action
 * 文件名称：BigDataTrendAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2017-2-15上午9:20:32 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class BigDataTrendAction extends BaseAction {
	
	@Autowired
	private IBigDataTrendService bigDataTrendServiceImpl;
	@Autowired
	private IBigAuthDetailService bigAuthDetailServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;

	private Map<String, Object> paramMap=new HashMap<String, Object>();
	
	private String desCode;
	
	private Integer id;
	
	
	/**
	 * 
	 * @描述:焦点推荐访问页面
	 * @作者:liujc@ucap.com.cn
	 * @时间:2017-2-9下午2:57:26 
	 * @return
	 */
	public String bigDataTrend(){
		
		String flag=null;
		try {
			if(StringUtils.isEmpty(desCode)){
				//失败
				flag="bigDataError";
			}else{
				String orgSiteCode = Des.strDec(desCode);
				if(StringUtils.isEmpty(orgSiteCode)){
					//失败
					flag="bigDataError";
				}else{
					BigAuthDetailRequest bdpdRequest = new BigAuthDetailRequest();
					bdpdRequest.setOrgSiteCode(orgSiteCode);
					bdpdRequest.setDesCode(desCode);
					bdpdRequest.setStatus(1);//1有效， 2无效
					bdpdRequest.setCurDate(DateUtils.getNextDay(new Date(), 0));
					int qCount = bigAuthDetailServiceImpl.queryCount(bdpdRequest);
					if(qCount < 1){
						//失败
						flag="bigDataError";
					}else{
						//成功
						flag="success";
						request.setAttribute("orgSiteCode", orgSiteCode);
						//paramMap.put("orgSiteCode", orgSiteCode);//加密解析出来的siteCode
					}
				}
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:查询数据集合
	 * @作者:zhaody@ucap.com.cn
	 * @时间:2017-2-15上午11:16:50 
	 * @return
	 */
	public void bigGetList(){
		try {
			BigDataTrendRequest bigDataTrendRequest = new BigDataTrendRequest();
			DatabaseTreeInfoRequest databaseTreeInfoRequest = new DatabaseTreeInfoRequest();
			//获取当前登录siteCode
			String siteCode = (String) request.getParameter("siteCode");
			//获取用户id
			bigDataTrendRequest.setSiteCode(siteCode);
			databaseTreeInfoRequest.setSiteCode(siteCode);
			List<DatabaseTreeInfo> queryList = databaseTreeInfoServiceImpl.queryList(databaseTreeInfoRequest);
			if(queryList !=null && queryList.size()>0){
				id = queryList.get(0).getId();
			}
			bigDataTrendRequest.setId(id);
			String nextDay = DateUtils.getNextDay(queryHomePageDate(), "0");
			bigDataTrendRequest.setScanDate(nextDay);
			
			//获取页面权限
			BigAuthDetailRequest bigAuthDetailRequest = new BigAuthDetailRequest();
			bigAuthDetailRequest.setOrgSiteCode(siteCode);
			List<BigAuthDetail> bigAuthList=bigAuthDetailServiceImpl.queryList(bigAuthDetailRequest);
			if(bigAuthList!=null && bigAuthList.size()>0 ){
				BigAuthDetail bigAuthDetail = bigAuthList.get(0);
				Integer focusType = bigAuthDetail.getFocusType();
				Integer showNum = bigAuthDetail.getShowNum();
				HealthyType healthyType = HealthyType.getNameByCode(focusType);
				if(healthyType!=null){
					String sqlName = healthyType.getSqlname();
					bigDataTrendRequest.setSqlName(sqlName);
					bigDataTrendRequest.setPageSize(showNum);
					String tapName = healthyType.getName();
					paramMap.put("tapName", tapName);
				}
				paramMap.put("bDetail",bigAuthDetail);
			}
			
			//查询信息
			List<BigDataTrend> sitesList = bigDataTrendServiceImpl.sitesList(bigDataTrendRequest);
			if(sitesList!=null && sitesList.size()>0){
				paramMap.put("sitesList", sitesList);
			}
		    List<BigDataTrend> portalList = bigDataTrendServiceImpl.portalList(bigDataTrendRequest);
			if(portalList!=null && portalList.size()>0){
				paramMap.put("portalList", portalList);
			}
			List<BigDataTrend> balanceList = bigDataTrendServiceImpl.balanceList(bigDataTrendRequest);
			if(balanceList!=null && balanceList.size()>0){
				paramMap.put("balanceList", balanceList);
			}
			String param = JSONObject.fromObject(paramMap).toString();
			writerPrint(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getDesCode() {
		return desCode;
	}

	public void setDesCode(String desCode) {
		this.desCode = desCode;
	}
}
