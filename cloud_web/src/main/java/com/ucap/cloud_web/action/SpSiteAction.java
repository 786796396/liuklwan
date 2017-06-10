package com.ucap.cloud_web.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.dto.ContractInfoRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DicItemRequest;
import com.ucap.cloud_web.dto.SpSiteRequest;
import com.ucap.cloud_web.entity.ContractInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.entity.SpSite;
import com.ucap.cloud_web.service.IContractInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDatabaseTreeInfoService;
import com.ucap.cloud_web.service.IDicItemService;
import com.ucap.cloud_web.service.ISpSiteService;


/**
 * <p>Description: 个性化</p>
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: MonitorIncludeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：luxg </p>
 * <p>@date：2016-11-22上午10:25:42 </p>
 */
@SuppressWarnings("serial")
@Controller

public class SpSiteAction extends BaseAction{
	@Autowired
	private ISpSiteService spSiteServiceImpl;
	@Autowired
	private IDatabaseTreeInfoService databaseTreeInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private IContractInfoService contractInfoServiceImpl;
	@Autowired
	private IDicItemService dicItemServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;// 配置文件
	@Autowired
	private DicUtils dicUtils;
	

	private DatabaseOrgInfo databaseOrgInfo;
	private String logoFileName;
	private String logoName;// 上传报告附件
	
	public String index(){
		
		return "success";
	}
	
	/**
	 * @Description: 获取个性化信息
	 * @author luxg --- 2015-11-22下午12:07:36
	 */
	public String getSpSite() {
		try {
			String flag = request.getParameter("flag");
			
			SpSite spSite=new SpSite();
			String siteCode = getCurrentUserInfo().getSiteCode();
			SpSiteRequest sprequest=new SpSiteRequest();
			sprequest.setSiteCode(siteCode);
			//查询状态为开通的站点;状态（0：未开通，1：开通，2：停用）
			sprequest.setStatus(1);
			sprequest.setDelFlag(0);
			sprequest.setNowDate(DateUtils.formatStandardDate(new Date()));
			int isHasMap = getCurrentUserInfo().getIsHasMap();
			if("1".equals(flag)||"2".equals(flag)){
				request.setAttribute("flag", flag);
				
				sprequest.setTemplateType(Integer.parseInt(flag));
				List<SpSite> spSites= spSiteServiceImpl.queryList(sprequest);
				
				spSite=spSites.get(0);
			
				//校验logo地址
				if (StringUtils.isNotBlank(spSite.getLogo())) {
					//拼接图片回显的访问路径
					spSite.setLogo(urlAdapterVar.getSpUrl()+spSite.getLogo());
				}else{
					spSite.setLogo("");
				}
				request.setAttribute("type1", 1);
				request.setAttribute("type2", 1);
				request.setAttribute("isShow", isHasMap);
			}else {
				request.setAttribute("flag", 0);
			
				// 模板一是否 使用中
				sprequest.setTemplateType(1);
				int num = spSiteServiceImpl.queryCount(sprequest);
				request.setAttribute("type1", num);
				sprequest.setTemplateType(2);
				int s = spSiteServiceImpl.queryCount(sprequest);
				if(s==0){
					// 查询该组织单位  是否具有正在运行的 合同
					ContractInfoRequest cRequest = new ContractInfoRequest();
					cRequest.setExecuteStatus(1);
					cRequest.setSiteCode(siteCode);
					cRequest.setOrgFlag(1);
					cRequest.setTypeFlag(1);
					List<ContractInfo> list = contractInfoServiceImpl.queryList(cRequest);
					if(list.size()>0){
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						//SpSite sSite=new SpSite();
						spSite.setAddFrom(1);
						spSite.setTemplateType(2);
						spSite.setDelFlag(0);
						spSite.setStatus(1);
						
						spSite.setEffectiveBeginDate(formatter.format(list.get(0).getContractBeginTime()));
						spSite.setEffectiveEndDate(formatter.format(list.get(0).getContractEndTime()));
						if(isHasMap==1){
							spSite.setDisplayModule("1,2,5,6");
						}else{
							spSite.setDisplayModule("1,5,6");
						}
						spSite.setSiteCode(siteCode);
						DicItemRequest dRequest = new DicItemRequest();
						dRequest.setEnName("spChannel_temlate2");
						dRequest.setDelStatus("0");
						List<DicItem> dicItems = dicItemServiceImpl.queryList(dRequest);
						if(dicItems.size()>0){
							spSite.setUrl(dicItems.get(0).getValue()+"?siteCode="+siteCode+"&templateType=2");
						}
						DatabaseOrgInfoRequest r = new DatabaseOrgInfoRequest();
						r.setStieCode(siteCode);
						List<DatabaseOrgInfo> l = databaseOrgInfoServiceImpl.queryList(r);
						if(l.size()>0){
							spSite.setSiteName(l.get(0).getName());
						}
						spSiteServiceImpl.add(spSite);
						request.setAttribute("type2", 1);
						request.setAttribute("isShow", isHasMap);
					}else{
						request.setAttribute("type2", 0);
					}
				}else{
					// 模板二是否 使用中
					request.setAttribute("type2", 1);
				}
			}
			request.setAttribute("spSite", spSite);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	public String previewImg(){
		String flag = request.getParameter("flag");
		request.setAttribute("flag", flag);
		return "success";
	}
	
	/**
	 * 
	 */
//	public String isShow(String siteCode){
//		String strTwo = siteCode.substring(0, 2);
//		if("bm".equalsIgnoreCase(strTwo)||"BT".equalsIgnoreCase(strTwo)){
//			return "2";
//		}
//		String strFour = siteCode.substring(2, 6);
//		if("11".equals(strTwo)||"12".equals(strTwo)||"31".equals(strTwo)||"50".equals(strTwo)){
//			if("0000".equals(strFour)){
//				return "1";
//			}
//			return "2";
//		}
//		DatabaseTreeInfoRequest dRequest = new DatabaseTreeInfoRequest();
//		dRequest.setSiteCode(siteCode);
//		dRequest.setIsBigdata(1);
//		dRequest.setIsBm(1);
//		int sum = databaseTreeInfoServiceImpl.queryCount(dRequest);
//		if(sum>0){
//			return "2";
//		}
//		return "1";
//	}
	/**
	 * @Description: 修改信息
	 * @author luxg --- 2015-11-22下午03:21:55
	 */
	public void updateSpSite() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject jb = getJSONObject();
			if (jb == null) {
				resultMap.put("errorMsg", "数据不能为空！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				String id = jb.getString("id");// 
				SpSite spSite=spSiteServiceImpl.get(Integer.parseInt(id));
				String flag = jb.getString("flag");// 
				if("1".equals(flag)){
					spSite.setDisplayModule(jb.getString("plays"));
					spSite.setBottomText(jb.getString("bottomText"));
					if(jb.getString("logo")!=null &&!"".equals(jb.getString("logo"))){
						spSite.setLogo(jb.getString("logo").replace(urlAdapterVar.getSpUrl(), ""));
					}
				}else{
					spSite.setDisplayModule(jb.getString("plays"));
				}
				spSiteServiceImpl.update(spSite);
				resultMap.put("succees", "更新成功");	
			}			
			writerPrint(JSONObject.fromObject(resultMap).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 上传图片Logo
	 * @author luxg --- 2015-11-22下午03:21:55
	 */
			
public void uplodeSpSite(){
	FileOutputStream fos=null;
	InputStream in=null;
	try {
		String imgSize = dicUtils.getValue("individualization_size");  // 大小
//		String imgWidth = dicUtils.getValue("individuation_width");     // 宽度
		String imgHeight = dicUtils.getValue("individuation_height");    // 高度
		
		//图片的回显路径
		String showUrl = urlAdapterVar.getSpUrl() + urlAdapterVar.getSpLogoUrl();

		long l = Long.valueOf(imgSize).longValue();
		// 文件保存目录URL
		String saveUrl = urlAdapterVar.getJiXiaoWord() + urlAdapterVar.getSpLogoUrl();
		//允许最大上传文件大小 struts.xml struts.multipart.maxSize=3G
		long maxSize = l;                        //上传图片的大小

		response.setContentType("text/html; charset=UTF-8");
	 
		if(!ServletFileUpload.isMultipartContent(request)){
		    getError("请选择文件。");
		    return;
		}
	//检查目录
	File uploadDir = new File(saveUrl);
	File dirFile = null;
	if(!uploadDir.isDirectory()){
		getError("上传目录不存在。");
		//创建文件夹
	    dirFile = new File(saveUrl);
	    if (!dirFile.exists()) {
	    	dirFile.mkdirs();
	    }
	    if (!dirFile.isDirectory()) {
	    	getError("上传目录不存在 。");
	    	return;
	    }	 
	}
	//Struts2 请求 包装过滤器
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	//获得上传的文件名
	String fileName = wrapper.getFileNames("logoName")[0];//imgFile,imgFile,imgFile
	//获得文件过滤器
	File file = wrapper.getFiles("logoName")[0];
	
	//检查扩展名
	String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	if( !fileExt .equals("png")  && !fileExt.equals("jpg") ){
		getError("只能上传jpg和png文件");
		return ;
	}
	//检查文件大小
//	long length = file.length();
	if (file.length() > maxSize) {
	        getError("上传文件大小超过限制。");
	        return;
	} 	
	//重构上传图片的名称 
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	String newImgName = df.format(new Date()) + "_"
	                + new Random().nextInt(1000) + "." + fileExt;
	byte[] buffer = new byte[1024];
	//获取文件输出流
	fos = new FileOutputStream(saveUrl  + newImgName);
	in = new FileInputStream(file);
	BufferedImage bufferedImg = ImageIO.read(in);
	Integer height = bufferedImg.getHeight();
	
	Integer imgH = Integer.valueOf(imgHeight);
	if( height > imgH ){ //图片的宽和高
		getError("上传文件规格过大。");
		return;
	}
	//获取内存中当前文件输入流
	 in = new FileInputStream(file);
	
	        int num = 0;
	        while ((num = in.read(buffer)) > 0) {
	                fos.write(buffer, 0, num);
	        }
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        resultMap.put("success", "图片上传成功");
	        //图片的回显路径
	        resultMap.put("splogo", showUrl + newImgName);
	        //图片的保存路径
	        resultMap.put("url", urlAdapterVar.getSpLogoUrl() + newImgName);
	        writerPrint(JSONObject.fromObject(resultMap).toString());
	} catch (Exception e) {
	        e.printStackTrace(System.err);
	} finally {
	        try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
	/**
	 * 输出信息
	 * @param response
	 * @param message
	 */
	private void getError(String message) {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("error", 1);
		resultMap.put("message", message);
		writerPrint(JSONObject.fromObject(resultMap).toString());
	}




	public String getLogoFileName() {
		return logoFileName;
	}
	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
}
