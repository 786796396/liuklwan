package com.ucap.cloud_web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.FileZipUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.dto.PaProjectRequest;
import com.ucap.cloud_web.entity.PaTargetTask;
import com.ucap.cloud_web.entity.PaTask;
import com.ucap.cloud_web.service.IPaProjectService;
import com.ucap.cloud_web.service.IPaTargetTaskService;
import com.ucap.cloud_web.service.IPaTaskService;
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
public class PaProjectAction extends BaseAction{
	private static Logger logger = Logger.getLogger(PaProjectAction.class);
	@Autowired
	private IPaProjectService paProjectServiceImpl;
	@Autowired
	private IPaTargetTaskService paTargetTaskServiceImpl;
	@Autowired
	private UrlAdapterVar urlAdapterVar;
	@Autowired
	private IPaTaskService paTaskServiceImpl;
	/**
	 * 跳转页面
	 * linhb - 2016-8-23
	 */
	public String paProjectList(){
		return "success";
	}
	public String paProjectDetail(){
		request.setAttribute("taskId", request.getParameter("taskId"));
		request.setAttribute("projectId", request.getParameter("projectId"));
		request.setAttribute("startDate", request.getParameter("startDate"));
		request.setAttribute("endDate", request.getParameter("endDate"));
		request.setAttribute("projectName", request.getParameter("projectName"));
		request.setAttribute("stauts", request.getParameter("stauts"));
		request.setAttribute("taskName", request.getParameter("taskName"));
		return "success";
	}
	/**
	 * 查询网站绩效考评任务
	 * linhb - 2016-8-23
	 */
	public void paProjectTaskList(){
		try {
			PaProjectRequest paProjectRequest = new PaProjectRequest();
			Map<String,Object> map = new HashMap<String, Object>();
			//获取当前登录人的网站标识码
			String siteCodeParam = getCurrentSiteCode();
			if(StringUtils.isNotEmpty(siteCodeParam)){//组织单位
				paProjectRequest.setSiteCode(siteCodeParam);
			}else{
				return;
			}
			logger.info("paProjectRequest()===paProjectList=");
			List<PaTask> list= paProjectServiceImpl.queryTaskList(paProjectRequest);
			map.put("list", list);
			writerPrint(JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 下载文件   路径
	 */
	public void downWord(){
		try {
			String paTargetTaskId = request.getParameter("paTargetTaskId");
			if(StringUtils.isNotEmpty(paTargetTaskId)){
				PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(Integer.parseInt(paTargetTaskId));
				//上传路径
				String path = paTargetTask.getUploadReportPath();
				// 自动生成的路径
				String pathAuto = paTargetTask.getCreateReportPath();
				if(StringUtils.isNotEmpty(path)){
					String name = path.substring(path.lastIndexOf("/")+1);
					String filePath = urlAdapterVar.getJiXiaoWord()+path;
					outPutFile(filePath,name);
				}else if(StringUtils.isNotEmpty(pathAuto)){
					String name = pathAuto.substring(pathAuto.lastIndexOf("/")+1);
					String filePath = urlAdapterVar.getJiXiaoWord()+pathAuto;
					outPutFile(filePath,name);
				}else{
					logger.info("downWord()===   绩效考核  下载文件   文件路径  不存在  ====================");
				}
			}
		} catch (Exception e) {
			logger.info("downWord()===   绩效考核  下载文件   出现异常====================");
			e.printStackTrace();
		}
		
	}
	/**
	 * 下载文件   路径
	 */
	public void downRatingWord(){
		try {
			String paTargetTaskId = request.getParameter("paTargetTaskId");
			if(StringUtils.isNotEmpty(paTargetTaskId)){
				PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(Integer.parseInt(paTargetTaskId));
				// 自动生成的路径
				String pathAuto = paTargetTask.getRatingReportPath();
				if(StringUtils.isNotEmpty(pathAuto)){
					String name = pathAuto.substring(pathAuto.lastIndexOf("/")+1);
					String filePath = urlAdapterVar.getCloubWebPaths()+pathAuto;
					outPutFile(filePath,name);
				}else{
					logger.info("downWord()===   自评报告  下载文件   文件路径  不存在  ====================");
				}
			}
		} catch (Exception e) {
			logger.info("downWord()===   自评报告  下载文件   出现异常====================");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * @Description: 下载报告使用
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
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName); // 设置头部信息
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		// 开始向网络传输文件流
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
		}
	}
	/**
	 * 批量下载文件
	 */
	public void downWordOrZip(){
		String arrs =  request.getParameter("ids");
		String tName =  request.getParameter("tName");
		String[] arr = arrs.split(",");
		try {
			List<String> fileNames = new ArrayList<String>();// 文件路径  及名称
			for (String paTargetTaskId : arr) {
				if(StringUtils.isNotEmpty(paTargetTaskId)){
					PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(Integer.parseInt(paTargetTaskId));
					//上传路径
					String path = paTargetTask.getUploadReportPath();
					// 自动生成的路径
					String pathAuto = paTargetTask.getCreateReportPath();
					if(StringUtils.isNotEmpty(path)){
						String filePath = urlAdapterVar.getJiXiaoWord()+path;
						fileNames.add(filePath);
					}else if(StringUtils.isNotEmpty(pathAuto)){
						String filePath = urlAdapterVar.getJiXiaoWord()+pathAuto;
						fileNames.add(filePath);
					}else{
						logger.info("downWord()===   绩效考核  下载文件   文件路径  不存在  ====================");
					}
				}
			}
			
			if(fileNames.size()>0){
				Map<String, String> fileMap = getNamemap(urlAdapterVar.getJiXiaoWord(),fileNames);
				outPutFile(fileMap.get("filePath"),tName+"考评报告.zip");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量下载自评报告
	 */
	public void downWordRatingOrZip(){
		String arrs =  request.getParameter("ids");
		String tName =  request.getParameter("tName");
		String[] arr = arrs.split(",");
		try {
			List<String> fileNames = new ArrayList<String>();// 文件路径  及名称
			for (String paTargetTaskId : arr) {
				if(StringUtils.isNotEmpty(paTargetTaskId)){
					PaTargetTask paTargetTask = paTargetTaskServiceImpl.get(Integer.parseInt(paTargetTaskId));
					// 自动生成的路径
					String pathAuto = paTargetTask.getRatingReportPath();
					if(StringUtils.isNotEmpty(pathAuto)){
						String filePath = urlAdapterVar.getCloubWebPaths()+pathAuto;
						fileNames.add(filePath);
					}else{
						logger.info("downWord()===   自评报告  下载文件   文件路径  不存在  ====================");
					}
				}
			}
			
			if(fileNames.size()>0){
				Map<String, String> fileMap = getNamemap(urlAdapterVar.getCloubWebPaths(),fileNames);
				outPutFile(fileMap.get("filePath"),tName+"自评报告.zip");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> getNamemap(String webPath,List<String> fileNames) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (fileNames != null && fileNames.size() > 0) {
			//下载文件列表打包
			List<File> fileList = new ArrayList<File>();
			for (String s : fileNames) {
				File f = new File(s);
				//验证存在性
				if(f.exists()){
					fileList.add(f);
				}
			}
			/**
			 * 创建一个临时压缩文件， 我们会把文件流全部注入到这个文件中 这里的文件你可以自定义是.rar还是.zip
			 */
			// modify by masl 临时压缩文件路径修改为：\download\wordfile\2016\08\21\****.zip
			//DateTest dateTest = new DateTest();
			String tempfile = webPath + "/download/wordfile/" + DateFormatUnit.DATE.getDateStr(new Date()).replace("-", "/") + "/";
			File folder = new File(tempfile);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			String uuid = UUID.randomUUID().toString();
			String fileName = tempfile + "/" + uuid + ".zip";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 创建文件输出流
			FileOutputStream fous = new FileOutputStream(file);
			ZipOutputStream zipOut = new ZipOutputStream(fous);
			/**
			 * 这个方法接受的就是一个所要打包文件的集合， 还有一个ZipOutputStream
			 */
			FileZipUtil.zipFile(fileList, zipOut);
			zipOut.close();
			fous.close();
			map.put("filePath", file.getPath());
		}
		return map;
	}
	
	/**
	 * @Description: 退回已经提交的自评报告
	 * @author: luocheng@ucap.com.cn 2016-12-29
	 */
	 public void backWord(){
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		 try {
			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			 //从页面获取需要回退报告的ids
			 String ids = request.getParameter("ids");
			 String[] idArray = null;
			 if(StringUtils.isNotEmpty(ids)){
				 idArray = ids.split(",");
			 }
			 for (String id : idArray) {
				 PaTargetTask parTargetTask =  paTargetTaskServiceImpl.get(Integer.parseInt(id));
				 if(parTargetTask != null){
					 int taskId = parTargetTask.getTaskId();
					 PaTask paTask =  paTaskServiceImpl.get(taskId);
					 if(paTask != null){
						 Date startDate = df.parse(paTask.getStartDate()); //自评开始日期
						 Date endDate = df.parse(paTask.getEndDate()); //自评结束日期
						 Date nowDate = DateUtils.getNow();
						 if(nowDate.before(endDate) && startDate.before(nowDate)){ //当前时间在  自评开始时间和结束时间之内的才可以退回报告
							 parTargetTask.setRatingState((short)3); //在自评时间内  修改此条数据的自评报告状态为 3 --填报中
							 paTargetTaskServiceImpl.update(parTargetTask);
						 }else{
							 resultMap.put("success", "false");
							 resultMap.put("msg", "自评时间已过，不可退回");
							 OutputJson(resultMap);
							 return;
						 }
					 }
				 }
			}
			 resultMap.put("success", "true");
			 resultMap.put("msg", "已成功退回");
			 OutputJson(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			 resultMap.put("success", "false");
			 resultMap.put("msg", "发生未知错误");
			 OutputJson(resultMap);
		}
	 }
	
	/**
	 *时间转换 枚举
	 * 
	 * @version 1.0
	 * @author zhaojp
	 */
	public enum DateFormatUnit {
		/** 枚举元素 */
		DAY("dd"),DATE("yyyy-MM-dd"), TIME("HH:mm:ss"),DATE_TIME("yyyy-MM-dd HH:mm:ss"), SHORT_DATE("yyyyMMdd"), SHORT_DATE_TIME("yyyyMMddHHmmss"), YEAR_AND_MONTH("yyyy-MM"), DATE_AND_HOUR(
				"yyyy-MM-dd HH");

		/**
		 * 初始化转换格式
		 * 
		 * @param pattern
		 *            转换格式
		 */
		DateFormatUnit(String pattern) {
			this.pattern = pattern;
		}

		/**
		 * 获得一个新的格式化对象
		 * 
		 * @return java.text.SimpleDateFormat 日期格式化对象
		 */
		public SimpleDateFormat getNewFormatter() {
			return new SimpleDateFormat(pattern);
		}

		/**
		 * 将日期对象转换为字符串
		 * 
		 * @param date
		 *            时间对象
		 * @return java.lang.String 字符串对象
		 */
		public String getDateStr(Date date) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.format(date);
		}

		/**
		 * 将毫秒转换为日期对象的字符串
		 * 
		 * @param mill
		 *            毫秒
		 * @return java.lang.String 字符串对象
		 */
		public String getDateStr(long mill) {
			return getDateStr(new Date(mill));
		}

		/**
		 * 将字符串转换为日期对象
		 * 
		 * @param dateStr
		 *            日期字符串
		 * @return java.util.Date 日期对象
		 */
		public Date getDateByStr(String dateStr) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				return formatter.parse(dateStr);
			} catch (ParseException e) {
				return null;
			}
		}

		/**
		 * 获得毫秒 获取失败则返回-1L
		 * 
		 * @param dateStr
		 *            日期字符串
		 * @return long 毫秒
		 */
		public long getTimeByStr(String dateStr) {
			return getDateByStr(dateStr).getTime();
		}

		/** 格式化格式 */
		private String pattern;
	}
}
