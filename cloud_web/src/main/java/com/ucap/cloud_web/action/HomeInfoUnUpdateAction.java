package com.ucap.cloud_web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.SecurityHomeChannelRequest;
import com.ucap.cloud_web.dto.SecurityHomeSituationRequest;
import com.ucap.cloud_web.dto.UpdateHomeDetailRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.SecurityHomeChannel;
import com.ucap.cloud_web.entity.SecurityHomeSituation;
import com.ucap.cloud_web.entity.UpdateHomeDetail;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.ISecurityHomeChannelService;
import com.ucap.cloud_web.service.ISecurityHomeSituationService;
import com.ucap.cloud_web.service.IUpdateHomeDetailService;

/**
 * <p>
 * Description:首页不更新表格数据
 * </p>
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: HomeInfoUnUpdateAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：sunjq </p>
 *               <p>
 * @date：2015-11-12上午10:47:31 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class HomeInfoUnUpdateAction extends BaseAction {
	@Autowired
	private IUpdateHomeDetailService updateHomeDetailServiceImpl;
	@Autowired
	private ISecurityHomeChannelService securityHomeChannelServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;
	
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private ISecurityHomeSituationService securityHomeSituationServiceImpl;
	
	// static private Logger logger =
	// Logger.getLogger(HomeInfoUnUpdateAction.class);

	private String siteName;// 网站名称
	private String url;// 首页URl
	private String jumpUrl;
	private String scanDate;// 监测日期
	private String lastUpdateDate;// 最后更新日期
	private long unUpdateDay;// 未更新天数
	private int towWeekUpNumber;// 两周内信息更新量
	private String imgUrl;// 快照地址
	private String imgMax;// 大图跳转地址
	String siteBeginServiceDate;// 页面时间控件的起始时间
	
	private String lastAccessDate;//最后正常访问日期
	
	private String siteCodeResult;//网站标识码


	/**
	 * @Description: 填报单位获取首页不更新信息
	 * @author sunjiaqi --- 2015-11-17下午03:05:23
	 * @return
	 */
	public String index() {
		siteName = "";
		url = "";
		jumpUrl = "";
		lastUpdateDate = "";
		unUpdateDay = 0;
		towWeekUpNumber = 0;
		imgUrl = null;
		imgMax = null;
		lastAccessDate="";
		siteCodeResult="";
		String timeDate = request.getParameter("scanDate");
		// 先从website——info获取 优先jumpUrl 其次HomeUrl
		try {
			//siteCode处理由组织单位跳转到该页面时，session的修改
			String siteCode = request.getParameter("siteCode");
			if(StringUtils.isNotEmpty(siteCode)){
				setCurrentShiroUser(siteCode);
			}else{
				// 从session中获取10位填报单位网站标识码
				siteCode = getCurrentUserInfo().getChildSiteCode();
				if (StringUtils.isEmpty(siteCode)) {
					siteCode = getCurrentUserInfo().getSiteCode();
				}
			}
			siteCodeResult = siteCode;
			
			DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
			databaseRequest.setSiteCodeLike(siteCode);
			List<DatabaseInfo>  databaseList=databaseInfoServiceImpl.queryList(databaseRequest);
			String homeUrl="";
			DatabaseInfo databaseInfo=new DatabaseInfo();
			if(databaseList!=null && databaseList.size()>0){
				databaseInfo=databaseList.get(0);
				siteName = databaseInfo.getName();
				if (StringUtils.isNotEmpty(databaseInfo.getJumpUrl())) {
					url =databaseInfo.getJumpUrl();
					if(!url.contains("http")){
						jumpUrl = "http://"+url;
					}else{
						jumpUrl = url;
					}
				} else {
					url = databaseInfo.getUrl();
					if(!url.contains("http")){
						jumpUrl = "http://"+url;
					}else{
						jumpUrl = url;
					}
				}
				homeUrl = StringUtils.isEmpty(databaseInfo.getJumpUrl()) ? databaseInfo.getUrl() : databaseInfo.getJumpUrl();
			}else{
				
			}
			if (StringUtils.isEmpty(timeDate)) {
				timeDate = DateUtils.getYesterdayStr();// 获取选择时间
			}

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			/*查询是否有人工添加的，如果有人工添加的则优先去人工添加的，如果没有人工添加的则保持原来的逻辑处理*/
			SecurityHomeSituationRequest shsr = new SecurityHomeSituationRequest();
			shsr.setSiteCode(siteCode);
			shsr.setScanDate(timeDate);
			shsr.setPageNo(0);
			shsr.setPageSize(Integer.MAX_VALUE);
			List<SecurityHomeSituation> list = securityHomeSituationServiceImpl.queryList(shsr);
			if(list != null && !list.isEmpty()){
				SecurityHomeSituation shs = list.get(0);
				
				if (StringUtils.isNotEmpty(shs.getModifyDate())) {
					lastUpdateDate = shs.getModifyDate();
					Date yesDay = df.parse(DateUtils.getYesterdayStr());
					Date d2 = df.parse(lastUpdateDate);
					long diff = yesDay.getTime() - d2.getTime();
					unUpdateDay = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数

			
//				resultMap.put("unUpdateDay", unUpdateDay);
//				resultMap.put("lastUpdateDate", lastUpdateDate);
//				resultMap.put("scanDate", timeDate);
//				resultMap.put("imgUrl", "");
//				resultMap.put("imgMax", "");
//				resultMap.put("lastAccessDate", "");
				lastAccessDate="暂无数据";
				scanDate = DateUtils.getYesterdayStr();
				imgUrl="";
				imgMax="";
				}
			}else{
				//先查首页信息不更新表，如果有数据直接获取
				//再查首页更新明细表，再获取数据
				SecurityHomeChannelRequest  securityHomeChannelRequest = new SecurityHomeChannelRequest();
				securityHomeChannelRequest.setScanDate(timeDate);
				securityHomeChannelRequest.setType(1);
				securityHomeChannelRequest.setSiteCode(siteCode);
				List<SecurityHomeChannel> securityHomeChannelList =  securityHomeChannelServiceImpl.queryList(securityHomeChannelRequest);
				//先查询不更新数据，如果有就不用取更新明细表了
				if(null!=securityHomeChannelList && securityHomeChannelList.size()>0){
					SecurityHomeChannel securityHomeChannel = securityHomeChannelList.get(0);
					
					scanDate = timeDate;
					if(StringUtils.isNotEmpty(securityHomeChannel.getImageUrl()) && StringUtils.isNotNull(securityHomeChannel.getImageUrl())){
						imgMax = urlAdapterVar.getImgUrl() + securityHomeChannel.getImageUrl();
					}
//					if(StringUtils.isNotEmpty(securityHomeChannel.getLitImg()) && StringUtils.isNotNull(securityHomeChannel.getLitImg())){
//						imgUrl = urlAdapterVar.getLitImgUrl() + securityHomeChannel.getLitImg();
//					}
					if(StringUtils.isNotEmpty(securityHomeChannel.getImageUrl()) ){
						imgUrl = urlAdapterVar.getImgUrl() + securityHomeChannel.getImageUrl();
					}
					Date yesDay = df.parse(DateUtils.getYesterdayStr());
					
					if(StringUtils.isNotEmpty(securityHomeChannel.getModifyDate())){
						lastUpdateDate = securityHomeChannel.getModifyDate();
						Date d2 = df.parse(lastUpdateDate);
						long diff = yesDay.getTime() - d2.getTime();
						unUpdateDay = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数
					}else{
						lastUpdateDate="暂无数据";
					}
					
					if(StringUtils.isNotEmpty(securityHomeChannel.getLastAccessDate())){
						lastAccessDate = securityHomeChannel.getLastAccessDate();
					}else{
						lastAccessDate="暂无数据";
					}
				}else{
					// 查询首页更新明细表
					UpdateHomeDetailRequest updateHomeDetailRequest = new UpdateHomeDetailRequest();
					updateHomeDetailRequest.setBeginScanDate(timeDate);
					updateHomeDetailRequest.setSiteCode(siteCode);
					updateHomeDetailRequest.setHomeUrl(homeUrl);
					UpdateHomeDetail uhd = updateHomeDetailServiceImpl.getNearest(updateHomeDetailRequest);
					if (uhd != null) {
						lastUpdateDate = uhd.getUpdateTime();
						scanDate = timeDate;
						if(StringUtils.isNotEmpty(uhd.getImgUrl()) && StringUtils.isNotNull(uhd.getImgUrl())){
							imgMax = urlAdapterVar.getImgUrl() + uhd.getImgUrl();
						}
//						if(StringUtils.isNotEmpty(uhd.getLitImg()) && StringUtils.isNotNull(uhd.getLitImg())){
//							imgUrl = urlAdapterVar.getLitImgUrl() + uhd.getLitImg();
//						}
						if(StringUtils.isNotEmpty(uhd.getImgUrl()) && StringUtils.isNotNull(uhd.getImgUrl())){
							//快照路径
							imgUrl = urlAdapterVar.getImgUrl() + uhd.getImgUrl();
						}
						Date yesDay = df.parse(DateUtils.getYesterdayStr());
						Date d2 = df.parse(lastUpdateDate);
						long diff = yesDay.getTime() - d2.getTime();
						unUpdateDay = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数
						lastAccessDate=scanDate;
					}else{
						lastUpdateDate = "暂无数据";
						lastAccessDate="暂无数据";
						scanDate = DateUtils.getYesterdayStr();
					}
				}
			}
		
			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	/**
	 * @Description: 渲染表格前端日期联动
	 * @author sunjiaqi --- 2015-11-20下午06:21:35
	 */
	public void getTables() {
		// 先从website——info获取 优先jumpUrl 其次HomeUrl
		try {
			// 从session中获取10位填报单位网站标识码
			String siteCode = getCurrentUserInfo().getChildSiteCode();
			if (StringUtils.isEmpty(siteCode)) {
				siteCode = getCurrentUserInfo().getSiteCode();
			}
			siteCodeResult = siteCode;
			String timeDate = request.getParameter("timeDate");// 获取选择时间
			DatabaseInfo databaseInfo=new DatabaseInfo();
			String homeUrl="";
			DatabaseInfoRequest databaseRequest=new DatabaseInfoRequest();
			databaseRequest.setSiteCodeLike(siteCode);
			List<DatabaseInfo>  databaseList=databaseInfoServiceImpl.queryList(databaseRequest);
			if(databaseList!=null && databaseList.size()>0){
				databaseInfo=databaseList.get(0);
				homeUrl = StringUtils.isEmpty(databaseInfo.getJumpUrl()) ? databaseInfo.getUrl() : databaseInfo.getJumpUrl();
				siteName = databaseInfo.getName();
				if (StringUtils.isNotEmpty(databaseInfo.getJumpUrl())) {
					url =databaseInfo.getJumpUrl();
				} else {
					url = databaseInfo.getUrl();
				}
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("siteCodeResult", siteCodeResult);
		
			/*查询是否有人工添加的，如果有人工添加的则优先去人工添加的，如果没有人工添加的则保持原来的逻辑处理*/
			SecurityHomeSituationRequest shsr = new SecurityHomeSituationRequest();
			shsr.setSiteCode(siteCode);
			shsr.setScanDate(timeDate);
			shsr.setPageNo(0);
			shsr.setPageSize(Integer.MAX_VALUE);
			List<SecurityHomeSituation> list = securityHomeSituationServiceImpl.queryList(shsr);
			if(list != null && !list.isEmpty()){
				SecurityHomeSituation shs = list.get(0);
				
				if (StringUtils.isNotEmpty(shs.getModifyDate())) {
					String lastUpdateDate = shs.getModifyDate();
					Date yesDay = DateUtils.parseDateTime(timeDate);
					Date d2 = DateUtils.parseDateTime(lastUpdateDate);
					long diff = yesDay.getTime() - d2.getTime();
					unUpdateDay = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数

			
				resultMap.put("unUpdateDay", unUpdateDay);
				resultMap.put("lastUpdateDate", lastUpdateDate);
				resultMap.put("scanDate", timeDate);
				resultMap.put("imgUrl", "");
				resultMap.put("imgMax", "");
				resultMap.put("lastAccessDate", "暂无数据");
				}
			}else{
				//先查首页信息不更新表，如果有数据直接获取
				//再查首页更新明细表，再获取数据
				SecurityHomeChannelRequest  securityHomeChannelRequest = new SecurityHomeChannelRequest();
				securityHomeChannelRequest.setScanDate(timeDate);
				securityHomeChannelRequest.setType(1);
				securityHomeChannelRequest.setSiteCode(siteCode);
				List<SecurityHomeChannel> securityHomeChannelList =  securityHomeChannelServiceImpl.queryList(securityHomeChannelRequest);
				//先查询不更新数据，如果有就不用取更新明细表了
				if(null!=securityHomeChannelList && securityHomeChannelList.size()>0){
					SecurityHomeChannel securityHomeChannel = securityHomeChannelList.get(0);
					lastUpdateDate = securityHomeChannel.getModifyDate();
					scanDate = timeDate;
					imgMax = urlAdapterVar.getImgUrl() + securityHomeChannel.getImageUrl();
					imgUrl = urlAdapterVar.getLitImgUrl() + securityHomeChannel.getLitImg();
					Date yesDay = DateUtils.parseDateTime(timeDate);
					if(StringUtils.isNotEmpty(lastUpdateDate)){
						Date d2 = DateUtils.parseDateTime(lastUpdateDate);
						long diff = yesDay.getTime() - d2.getTime();
						unUpdateDay = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数
						
						if(StringUtils.isNotEmpty(securityHomeChannel.getLastAccessDate())){
							lastAccessDate = securityHomeChannel.getLastAccessDate();
						}else{
							lastAccessDate="暂无数据";
						}
						resultMap.put("unUpdateDay", unUpdateDay);
						resultMap.put("lastUpdateDate", lastUpdateDate);
						resultMap.put("scanDate", timeDate);
						resultMap.put("imgUrl", imgUrl);
						resultMap.put("imgMax", imgMax);
						resultMap.put("lastAccessDate", lastAccessDate);
					}
					
				}else{
					// 查询首页更新明细表
					UpdateHomeDetailRequest updateHomeDetailRequest = new UpdateHomeDetailRequest();
					updateHomeDetailRequest.setBeginScanDate(timeDate);
					updateHomeDetailRequest.setSiteCode(siteCode);
					updateHomeDetailRequest.setHomeUrl(homeUrl);
					UpdateHomeDetail uhd = updateHomeDetailServiceImpl.getNearest(updateHomeDetailRequest);
					if (uhd != null) {
						lastUpdateDate = uhd.getUpdateTime();
						scanDate = timeDate;
						imgMax = urlAdapterVar.getImgUrl() + uhd.getImgUrl();
						imgUrl = urlAdapterVar.getLitImgUrl() + uhd.getLitImg();
						Date yesDay = DateUtils.parseDateTime(timeDate);
						if(StringUtils.isNotEmpty(lastUpdateDate)){
						Date d2 = DateUtils.parseDateTime(lastUpdateDate);
						long diff = yesDay.getTime() - d2.getTime();
						unUpdateDay = diff / (1000 * 60 * 60 * 24);// 计算出未更新天数
						resultMap.put("unUpdateDay", unUpdateDay);
						resultMap.put("lastUpdateDate", lastUpdateDate);
						resultMap.put("scanDate", timeDate);
						resultMap.put("imgUrl", imgUrl);
						resultMap.put("imgMax", imgMax);
						resultMap.put("lastAccessDate", scanDate);
						}
					}else{
						resultMap.put("unUpdateDay", unUpdateDay);
						resultMap.put("lastUpdateDate", "未能获取数据");
						resultMap.put("towWeekUpNumber", "");
						resultMap.put("scanDate", timeDate);
						resultMap.put("lastAccessDate", "未能获取数据");
					}
				}
			}
			
			
			
			
			
			
			
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public long getUnUpdateDay() {
		return unUpdateDay;
	}

	public void setUnUpdateDay(long unUpdateDay) {
		this.unUpdateDay = unUpdateDay;
	}
	

	public String getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(String lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public int getTowWeekUpNumber() {
		return towWeekUpNumber;
	}

	public void setTowWeekUpNumber(int towWeekUpNumber) {
		this.towWeekUpNumber = towWeekUpNumber;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgMax() {
		return imgMax;
	}

	public void setImgMax(String imgMax) {
		this.imgMax = imgMax;
	}

	public String getSiteBeginServiceDate() {
		return siteBeginServiceDate;
	}

	public void setSiteBeginServiceDate(String siteBeginServiceDate) {
		this.siteBeginServiceDate = siteBeginServiceDate;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getSiteCodeResult() {
		return siteCodeResult;
	}

	public void setSiteCodeResult(String siteCodeResult) {
		this.siteCodeResult = siteCodeResult;
	}

	
	
	

}
