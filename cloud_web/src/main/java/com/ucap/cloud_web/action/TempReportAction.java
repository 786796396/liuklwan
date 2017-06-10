package com.ucap.cloud_web.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DicItemRequest;
import com.ucap.cloud_web.dto.TempReportRequest;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.TempReport;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.service.ITempReportService;
import com.ucap.cloud_web.util.ExportExcel;

/**
 * 描述： 临时报备action
 * 包：com.ucap.cloud_web.action
 * 文件名称：TempReportAction
 * 公司名称：开普互联
 * 作者：linhb@ucap.com.cn
 * 时间：2016-9-13下午2:58:39 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TempReportAction extends BaseAction{
	
	@Autowired
	private ITempReportService tempReportServiceImpl;
	@Autowired
	private IDicItemService dicItemServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	private TempReport tempReport;
	
	private String fileName; // 下载文件名称
	private InputStream excelFile; // 下载文件流
	public String getFileName() throws Exception {
		String tempName = "微信用户信息.xls";
	    fileName = new String(tempName.getBytes(), "ISO8859-1");
	    System.out.println(fileName);
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String tempReportOrg(){
		try {
			//获取当前登录siteCode
			String siteCode=getCurrentUserInfo().getSiteCode();
			
			String endDate = DateUtils.formatStandardDate(new Date());
			String startDate = DateUtils.getNextDay(new Date(), -6);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("siteCode", siteCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public String tempReportTB(){
		try {
			//获取当前登录siteCode
			String siteCode=getCurrentUserInfo().getSiteCode();
			request.setAttribute("siteCode", siteCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	/**
	 * 添加报备
	 * @return
	 */
	public void add(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(tempReport.getSiteCode() != null && !"".equals(tempReport.getSiteCode())){
				String treeInfo = databaseTreeBizServiceImpl.getUpperOrgCode(tempReport.getSiteCode());
				if(StringUtils.isNotEmpty(treeInfo)){
					TempReport tReport = new TempReport();
					tReport.setIsCycle(tempReport.getIsCycle());
					tReport.setReportDate(DateUtils.formatStandardDate(new Date()));
					tReport.setStatus(2);
					tReport.setReportDetail(tempReport.getReportDetail());
					tReport.setReportName(tempReport.getReportName());
					tReport.setReportReason(tempReport.getReportReason());
					tReport.setReportEndDate(tempReport.getReportEndDate());
					tReport.setReportStartDate(tempReport.getReportStartDate());
					tReport.setStartTime(tempReport.getStartTime());
					tReport.setEndTime(tempReport.getEndTime());
					tReport.setOrgSiteCode(treeInfo);
					tReport.setSiteCode(tempReport.getSiteCode());
					tempReportServiceImpl.add(tReport);
				}
				map.put("success", "true");
			}else{
				map.put("success", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
		}
		writerPrint(JSONObject.fromObject(map).toString());
		
	}
	/**
	 * @Title: 填报单位  获取数据
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-9-18下午2:21:34
	 * @return
	 */
	public void getTbDataList(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String siteCode = request.getParameter("siteCode");
			if (siteCode != null) {
				TempReportRequest tRequest = new TempReportRequest();
				
				tRequest.setSiteCode(siteCode);
				tRequest.setPageSize(Integer.MAX_VALUE);
				
				List<QueryOrder> queryList=new ArrayList<QueryOrder>();
				QueryOrder queryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
				queryList.add(queryOrder);
				tRequest.setQueryOrderList(queryList);
				
				List<TempReport> list = tempReportServiceImpl.queryList(tRequest);
				map.put("list", list);
				map.put("success", "true");
				writerPrint(JSONObject.fromObject(map).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	/**
	 * @Title: 获取  填报单位数据
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-9-14下午2:21:34
	 * @return
	 */
	public void getDataList(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String orgSiteCode = request.getParameter("siteCode");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String reportReason = request.getParameter("reportReason");
			String siteCodeOrName = request.getParameter("siteCodeOrName");
			
			if (orgSiteCode != null) {
				TempReportRequest tRequest = new TempReportRequest();
				if(startDate != null && !"".equals(startDate)){
					tRequest.setStartDate(startDate);
				}
				if(endDate!= null && !"".equals(endDate)){
					tRequest.setEndDate(endDate);			
				}
				if(siteCodeOrName != null && !"".equals(siteCodeOrName)){
					tRequest.setNameOrSiteCode(siteCodeOrName);
				}
				if(reportReason != null && !"".equals(reportReason)){
					if(reportReason.length()>0){
						tRequest.setNameOrSiteCode(null);
						tRequest.setReportReason(reportReason.substring(0,reportReason.length()-1));
					}
				}
				
				DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
				treeInfoRequest.setSiteCode(orgSiteCode);
				treeInfoRequest.setIsBigdata(1);
				List<DatabaseTreeInfo> treeInfo = databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
				String code = "";
				Integer levelLower = null;
				Integer levellowerlow = null;
				if(treeInfo != null && treeInfo.size()>0){
					 code = treeInfo.get(0).getCode();
					 levelLower = Integer.valueOf(treeInfo.get(0).getLevel())+1;
					 levellowerlow = Integer.valueOf(treeInfo.get(0).getLevel())+2;
				}
				String pos = request.getParameter("pos");
				if(StringUtils.isNotEmpty(pos)){
					tRequest.setPageNo(Integer.parseInt(pos));
				}
				String size = request.getParameter("size");
				if(StringUtils.isNotEmpty(size)){
					tRequest.setPageSize(Integer.parseInt(size));
				}
				tRequest.setCodeLike(code);
				tRequest.setLevelLower(levelLower);
				tRequest.setLevellowerlow(levellowerlow);
				PageVo<TempReport> query = new PageVo<TempReport>();
				query = tempReportServiceImpl.queryLowerSubordinateUnits(tRequest); 
				List<TempReport> data = query.getData();
				map.put("list", data);
				map.put("totalRecords", query.getData().size());
				map.put("iTotalDisplayRecords", query.getRecordSize());
				map.put("success", "true");
				writerPrint(JSONObject.fromObject(map).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}
	/**
	 * @Title: 导出  excel
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-9-14下午2:21:34
	 * @return
	 */
	public void exportWord(){
		try {
			String siteCode = request.getParameter("siteCode");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String reportReason = request.getParameter("reportReason");
			String siteCodeOrName = request.getParameter("siteCodeOrName");
			if (siteCode != null) {
				TempReportRequest tRequest = new TempReportRequest();
				if(startDate != null && !"".equals(startDate)){
					tRequest.setStartDate(startDate);
				}
				if(endDate!= null && !"".equals(endDate)){
					tRequest.setEndDate(endDate);			
				}
				if(siteCodeOrName != null && !"".equals(siteCodeOrName)){
					tRequest.setNameOrSiteCode(siteCodeOrName);
				}
				if(reportReason != null && !"".equals(reportReason)){
					if(reportReason.length()>0){
						tRequest.setNameOrSiteCode(null);
						tRequest.setReportReason(reportReason.substring(0,reportReason.length()-1));
					}
				}
				
				DatabaseTreeInfoRequest treeInfoRequest = new DatabaseTreeInfoRequest();
				treeInfoRequest.setSiteCode(siteCode);
				treeInfoRequest.setIsBigdata(1);
				List<DatabaseTreeInfo> treeInfo = databaseTreeInfoServiceImpl.queryList(treeInfoRequest);
				String code = treeInfo.get(0).getCode();
				Integer levelLower = Integer.valueOf(treeInfo.get(0).getLevel())+1;
				Integer levellowerlow = Integer.valueOf(treeInfo.get(0).getLevel())+2;
				tRequest.setCodeLike(code);
				tRequest.setLevelLower(levelLower);
				tRequest.setLevellowerlow(levellowerlow);
				tRequest.setPageSize(Integer.MAX_VALUE);
				PageVo<TempReport> query = new PageVo<TempReport>();
				query = tempReportServiceImpl.queryLowerSubordinateUnits(tRequest); 
				List<TempReport> list = query.getData();
				
				DicItemRequest dRequest = new DicItemRequest();
				dRequest.setPid("9");
				dRequest.setPageSize(Integer.MAX_VALUE);
				dRequest.setDelStatus("0");
				List<DicItem> dicList = dicItemServiceImpl.queryList(dRequest);
				Map<String,String> dMap = new HashMap<String, String>();
				for (DicItem dicItem : dicList) {
					String name = dicItem.getName();
					if(name.contains("（")){
						String[] names = name.split("（");
						name=names[0];
					}
					dMap.put(dicItem.getValue(), name);
		
				}	
				ArrayList<Object[]> listExl = new ArrayList<Object[]>();
				Object[] obj1 = new Object[]{"网站标识码","网站名称","申请人","报备原因","详细说明","报备日期","影响日期","状态"};
				listExl.add(obj1);
				String fileName = "申报数据("+DateUtils.formatStandardDate(new Date())+").xls";
				String title = "申报数据"; 	
					
					
					
		        for (int i = 0; i < list.size(); i++){  
		        	Object[] obj = new Object[8];
		        	TempReport stu = list.get(i);  
		        	obj[0] = stu.getSiteCode();
		        	obj[1] = stu.getWebName();
		        	obj[2] = stu.getReportName();
		        	obj[3] = dMap.get(stu.getReportReason()+"");
		        	obj[4] = stu.getReportDetail();
		        	
		            if(stu.getCreateTime().length()>10){
		            	obj[5]=stu.getReportDate();
		            }else{
		            	obj[5]="";
		            }
		            
		            if(stu.getIsCycle()==1){
		            	obj[6] = stu.getReportStartDate()+"至"+stu.getReportEndDate()+"每天"+stu.getStartTime()+"至"+stu.getEndTime();
		            }else{
		            	obj[6] = stu.getReportStartDate()+"至"+stu.getReportEndDate();
		            }
		            
		            if(stu.getStatus()==1){
		            	obj[7] = "待审核";
		            }else if(stu.getStatus()==2){
		            	obj[7] = "通过";
		            }else{
		            	obj[7] = "驳回";
		            }
		            listExl.add(obj);
				}
		        ExportExcel.tempReportExcel(fileName, title, listExl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * linhb 2016-09-14
	 * 修改数据
	 * 
	 */
	public void update(){
		String id = request.getParameter("id");
		String passOrReject = request.getParameter("passOrReject");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			if(id != null && passOrReject != null && !"".equals(id)&&!"".equals(passOrReject)){
				TempReport tempReport=tempReportServiceImpl.get(Integer.parseInt(id));
				if("pass".equals(passOrReject)){
					tempReport.setStatus(2);
				}else{
					tempReport.setStatus(3);
				}
				tempReportServiceImpl.update(tempReport);
				map.put("success", "true");
			}else{
				map.put("success", "false");
			}
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			map.put("success", "false");
			e.printStackTrace();
		}
		
	}
	
	public TempReport getTempReport() {
		return tempReport;
	}
	public void setTempReport(TempReport tempReport) {
		this.tempReport = tempReport;
	}
	
	
}
