package com.ucap.cloud_web.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.dto.BigAuthDetailRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.BigAuthDetail;
import com.ucap.cloud_web.service.IBigAuthDetailService;
import com.ucap.cloud_web.service.IBigDataAnalysisService;
import com.ucap.cloud_web.util.Des;

/**
 * <p>Description: </p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: BigAuthDetailAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjy </p>
 * <p>@date：2016-6-28下午5:59:21 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class BigAuthDetailAction extends BaseAction {

	@Autowired
	private IBigAuthDetailService bigAuthDetailServiceImpl;
	@Autowired
	private IBigDataAnalysisService bigDataAnalysisServiceImpl;
	private String startDate;
	private String endDate;
	private String tabName1;
	private String tabName2;
	private String tabName3;
	private String updateType;//1:修改tab名字和开始时间结束时间 2：修改焦点推荐内容 3：重置焦点推荐内容
	private BigAuthDetail bigAuthDetailBean;
	/**
	 * @Description: 外部调用验证大数据访问权限的接口
	 * @author: sunjy --- 2016-6-27下午6:35:20
	 */
	@RequestMapping(value="/BDPermissionValidate",method=RequestMethod.GET)
	@ResponseBody
	public String BDPermissionValidate(){
		
		System.out.println("DESCODE");
		try {
			String desCode = request.getParameter("DESCODE");
			String orgSiteCode = Des.strDec(desCode);
			
			BigAuthDetailRequest bdpdRequest = new BigAuthDetailRequest();
			bdpdRequest.setOrgSiteCode(orgSiteCode);
			bdpdRequest.setDesCode(desCode);
			bdpdRequest.setStatus(1);//1有效， 2无效
			
			int qCount = bigAuthDetailServiceImpl.queryCount(bdpdRequest);
			Map<String, Object> returnMap = Maps.newHashMap();
			if(qCount <= 1){
				returnMap.put("validate", "failed");
				return JSONObject.fromObject(returnMap).toString();
			}else{
				returnMap.put("validate", "succeeded");
				returnMap.put("JumpUrl", "https://www.baidu.com");
				return JSONObject.fromObject(returnMap).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * @Title:查询 siteCode加密 信息 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-24上午9:05:44
	 */
	public void queryBigAuthDetail(){
		try {
			BigAuthDetailRequest request = new BigAuthDetailRequest();
			request.setOrgSiteCode(getCurrentUserInfo().getSiteCode());
			List<BigAuthDetail> existCnt = bigAuthDetailServiceImpl.queryList(request);
			writerPrint(JSONArray.fromObject(existCnt).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	/**
	 * @Title:新增 siteCode加密 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-23下午3:55:11
	 */
	public void addBigAuthDetail(){
		try {
			String orgSiteCode=getCurrentUserInfo().getSiteCode();
			//验证是否已经存在记录
			BigAuthDetailRequest request = new BigAuthDetailRequest();
			request.setOrgSiteCode(orgSiteCode);
			List<BigAuthDetail> existCnt = bigAuthDetailServiceImpl.queryList(request);
			BigAuthDetail bigDataAuthDetail = new BigAuthDetail();
			System.out.println("existCnt.size()================================"+existCnt.size());
			String desCode = Des.strEnc(orgSiteCode);
			String focusUrl=getBasePath()+"/bigDataTrend_bigDataTrend.action?desCode="+desCode;//焦点推送访问地址
		    String focusInfoUrl=getBasePath()+"/bigDataAnalysis_bigDataAnalysisOut.action?desCode="+desCode;//焦点推送详情访问地址
			if(existCnt.size() ==0 ){//不存在
				//加密orgSiteCode
				
				bigDataAuthDetail.setOrgSiteCode(orgSiteCode);
				bigDataAuthDetail.setDesCode(desCode);
				bigDataAuthDetail.setStatus(1);
				bigDataAuthDetail.setStartDate(DateUtils.getNextDay(new Date(), 0));
				bigDataAuthDetail.setEndDate(DateUtils.getNextDay(new Date(), 365));
				bigDataAuthDetail.setTabNameOne("本级站点");
				bigDataAuthDetail.setTabNameTwo("下级地方门户");
				bigDataAuthDetail.setTabNameThree("下级地方站群");
				bigDataAuthDetail.setFocusType(1);
				bigDataAuthDetail.setShowNum(5);
				bigDataAuthDetail.setFocusUrlOne(focusInfoUrl+"&tabId=2");
				bigDataAuthDetail.setFocusUrlTwo(focusInfoUrl+"&tabId=1");
				bigDataAuthDetail.setFocusUrlThree(focusInfoUrl+"&tabId=0");
				//设置创建和更改时间
				Date ndate = new Date();
				bigDataAuthDetail.setCreateTime(ndate);
				bigDataAuthDetail.setModifyTime(ndate);
				bigAuthDetailServiceImpl.add(bigDataAuthDetail);
				//大数据外部引用详情页
//				String url=focusInfoUrl+"&tabId=0";
//				bigDataAuthDetail.setUrl(url);
//				String indexUrl=getBasePath()+"/bigDataAnalysis_bigDataAnalysisOut.action?desCode="+desCode;
//				bigDataAuthDetail.setIndexUrl(indexUrl);
			}else{
				//存在
				bigDataAuthDetail=existCnt.get(0);
				bigDataAuthDetail.setId(null);
				bigDataAuthDetail.setOrgSiteCode(orgSiteCode);
				if(StringUtils.isEmpty(bigDataAuthDetail.getStartDate())){
					bigDataAuthDetail.setStartDate(DateUtils.getNextDay(new Date(), 0));
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getEndDate())){
					bigDataAuthDetail.setEndDate(DateUtils.getNextDay(new Date(), 365));
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getTabNameOne())){
					bigDataAuthDetail.setTabNameOne("本级站点");
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getTabNameTwo())){
					bigDataAuthDetail.setTabNameTwo("下级地方门户");
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getTabNameThree())){
					bigDataAuthDetail.setTabNameThree("下级地方站群");
				}
				if(bigDataAuthDetail.getFocusType()==null){
					bigDataAuthDetail.setFocusType(1);
				}
				if(bigDataAuthDetail.getShowNum()==null){
					bigDataAuthDetail.setShowNum(5);
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getFocusUrlOne())){
					bigDataAuthDetail.setFocusUrlOne(focusInfoUrl+"&tabId=0");
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getFocusUrlTwo())){
					bigDataAuthDetail.setFocusUrlTwo(focusInfoUrl+"&tabId=1");
				}
				if(StringUtils.isEmpty(bigDataAuthDetail.getFocusUrlThree())){
					bigDataAuthDetail.setFocusUrlThree(focusInfoUrl+"&tabId=2");
				}
				
				Date ndate = new Date();
				bigDataAuthDetail.setModifyTime(ndate);
				
				bigAuthDetailServiceImpl.update(bigDataAuthDetail);
			}
			//大数据外部引用详情页
			String url=focusInfoUrl+"&tabId=0";
			DatabaseTreeInfoRequest databaseTreeInfoRequest =new DatabaseTreeInfoRequest();
			databaseTreeInfoRequest.setOrgSiteCode(orgSiteCode);
			boolean isLocal=bigDataAnalysisServiceImpl.isLocalSites(databaseTreeInfoRequest);
			if(isLocal){
				//只显示本级站点
				url=focusInfoUrl+"&tabId=0";
			}
			bigDataAuthDetail.setUrl(url);
			//焦点推荐页面  指标页
			bigDataAuthDetail.setIndexUrl(focusUrl);
			writerPrint(JSONArray.fromObject(bigDataAuthDetail).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Title:修改 siteCode  tabName  date 
	 * @Description:
	 * @author liujc@ucap.com.cn	2016-8-24上午9:05:04
	 */
	public void updateBigAuthDetail(){
		try {
			String orgSiteCode=getCurrentUserInfo().getSiteCode();
			String desCode = Des.strEnc(orgSiteCode);
		    String focusInfoUrl=getBasePath()+"/bigDataAnalysis_bigDataAnalysisOut.action?desCode="+desCode;//焦点推送详情访问地址
			//更新修改时间字段
			BigAuthDetail bigAuthDetail = new BigAuthDetail();
			bigAuthDetail.setOrgSiteCode(orgSiteCode);
			//updateType1:修改tab名字和开始时间结束时间 2：修改焦点推荐内容 3：重置焦点推荐内容
			if(updateType.equals("1")){
				bigAuthDetail.setStartDate(startDate);
				bigAuthDetail.setEndDate(endDate);
				bigAuthDetail.setTabNameOne(tabName1);
				bigAuthDetail.setTabNameTwo(tabName2);
				bigAuthDetail.setTabNameThree(tabName3);
			}else if(updateType.equals("2")){
				bigAuthDetail.setFocusType(bigAuthDetailBean.getFocusType());
				bigAuthDetail.setShowNum(bigAuthDetailBean.getShowNum());
				bigAuthDetail.setFocusUrlOne(bigAuthDetailBean.getFocusUrlOne());
				bigAuthDetail.setFocusUrlTwo(bigAuthDetailBean.getFocusUrlTwo());
				bigAuthDetail.setFocusUrlThree(bigAuthDetailBean.getFocusUrlThree());
			}else if(updateType.equals("3")){
				bigAuthDetail.setFocusType(1);
				bigAuthDetail.setShowNum(5);
				bigAuthDetail.setFocusUrlOne(focusInfoUrl+"&tabId=0");
				bigAuthDetail.setFocusUrlTwo(focusInfoUrl+"&tabId=1");
				bigAuthDetail.setFocusUrlThree(focusInfoUrl+"&tabId=2");
			}
			
			
			
			Date ndate = new Date();
			bigAuthDetail.setModifyTime(ndate);
			
			bigAuthDetailServiceImpl.update(bigAuthDetail);
			
			BigAuthDetailRequest request = new BigAuthDetailRequest();
			request.setOrgSiteCode(orgSiteCode);
			List<BigAuthDetail> existCnt = bigAuthDetailServiceImpl.queryList(request);
			BigAuthDetail bigDataAuthDetail = new BigAuthDetail();
			if(existCnt.size()>0){
				bigDataAuthDetail=existCnt.get(0);
			}
			writerPrint(JSONArray.fromObject(bigDataAuthDetail).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTabName1() {
		return tabName1;
	}
	public void setTabName1(String tabName1) {
		this.tabName1 = tabName1;
	}
	public String getTabName2() {
		return tabName2;
	}
	public void setTabName2(String tabName2) {
		this.tabName2 = tabName2;
	}
	public String getTabName3() {
		return tabName3;
	}
	public void setTabName3(String tabName3) {
		this.tabName3 = tabName3;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public BigAuthDetail getBigAuthDetail() {
		return bigAuthDetailBean;
	}
	public void setBigAuthDetail(BigAuthDetail bigAuthDetail) {
		this.bigAuthDetailBean = bigAuthDetail;
	}
	public BigAuthDetail getBigAuthDetailBean() {
		return bigAuthDetailBean;
	}
	public void setBigAuthDetailBean(BigAuthDetail bigAuthDetailBean) {
		this.bigAuthDetailBean = bigAuthDetailBean;
	}
}
