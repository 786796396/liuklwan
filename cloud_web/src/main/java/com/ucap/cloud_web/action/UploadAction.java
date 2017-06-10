package com.ucap.cloud_web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Description: </p> 首页更新页面--填报单位
 * <p>@Package：com.ucap.cloud_web.action </p>
 * <p>Title: UpdateHomeAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：linhb </p>
 * <p>@date：2016-10-28上午9:24:26 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UploadAction extends BaseAction {
	
	public void fileUpload() throws Exception{
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "ewebeditor/uploadfile/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/ewebeditor/uploadfile/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,sql,txt,zip,rar,gz,bz2,pdf");

		//允许最大上传文件大小 struts.xml struts.multipart.maxSize=3G
		long maxSize = 3000000000l;
		 
		response.setContentType("text/html; charset=UTF-8");
		 
		if(!ServletFileUpload.isMultipartContent(request)){
		    getError("请选择文件。");
		    return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			getError("上传目录不存在。");
		    return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
		    getError("上传目录没有写权限。");
		    return;
		}
		
		//检查目录写权限
		if(!uploadDir.canWrite()){
		    getError("上传目录没有写权限。");
		    return;
		}
		 
		String dirName = request.getParameter("dir");//image
//		if (dirName == null) {
//		    dirName = "image";
//		}
//		if(!extMap.containsKey(dirName)){
//		    getError("目录名不正确。");
//		    return;
//		}
		//创建文件夹
//		savePath += dirName + "/";//D:\Tomcat6.0\webapps\zswz\attached/image/
//		saveUrl += dirName + "/";///zswz/attached/image/
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
		    saveDirFile.mkdirs();
		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String ymd = sdf.format(new Date());
//		savePath += ymd + "/";//D:\Tomcat6.0\webapps\zswz\attached/image/20111129/
//		saveUrl += ymd + "/";///zswz/attached/image/20111129/
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
		    dirFile.mkdirs();
		}
		if (!dirFile.isDirectory()) {
		    getError("上传目录不存在 。");
		    return;
		}
		//检查目录写入权限
		if (!dirFile.canWrite()) {
		    getError("上传目录没有写入权限。");
		    return;
		}
		 
		//Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		//获得上传的文件名
		String fileName = wrapper.getFileNames("imgFile")[0];//imgFile,imgFile,imgFile
		//获得文件过滤器
		File file = wrapper.getFiles("imgFile")[0];
		 
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
		    getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
		    return;
		}
		//检查文件大小
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
		FileOutputStream fos = new FileOutputStream(savePath +"/" + newImgName);
		//获取内存中当前文件输入流
		InputStream in = new FileInputStream(file);
		try {
		        int num = 0;
		        while ((num = in.read(buffer)) > 0) {
		                fos.write(buffer, 0, num);
		        }
		} catch (Exception e) {
		        e.printStackTrace(System.err);
		} finally {
		        in.close();
		        fos.close();
		}
		//发送给 KE 
//		JSONObject obj = new JSONObject();
//		obj.put("error", 0);
//		obj.put("url", saveUrl +"/" + newImgName);
//		///zswz/attached/image/20111129/  image 20111129195421_593.jpg
//		obj.toJSONString();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("error", 0);
		//resultMap.put("url", saveUrl +"/" + newImgName);
		resultMap.put("url", saveUrl +"/" + newImgName);
		resultMap.put("fileName", fileName);
		writerPrint(JSONObject.fromObject(resultMap).toString());
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
}
