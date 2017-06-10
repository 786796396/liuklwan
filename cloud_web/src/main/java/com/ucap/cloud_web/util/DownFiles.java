package com.ucap.cloud_web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import org.apache.struts2.ServletActionContext;

import com.publics.util.utils.FileZipUtil;

public class DownFiles {
	
	
	/**
	 * @param splits 前端传递的下载文件列表
	 * @return Map  filePath 生成的下载文件地址
	 * 				fileName 下载的文件名
	 * @throws Exception 
	 */
	public Map<String,String> getNamemap(List<String>fileNames) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		if(fileNames!=null && fileNames.size() >0 ){
				//下载文件列表打包
				List<File> fileList = new ArrayList<File>();
				for (String s : fileNames) {
					File f = new File(s);
					fileList.add(f);
				}
				/**
				 * 创建一个临时压缩文件， 我们会把文件流全部注入到这个文件中 这里的文件你可以自定义是.rar还是.zip
				 */
				String tempfile = ServletActionContext.getServletContext().getRealPath("");
				String uuid = UUID.randomUUID().toString();
				File file = new File(tempfile + "/" + uuid + ".zip");
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
}
