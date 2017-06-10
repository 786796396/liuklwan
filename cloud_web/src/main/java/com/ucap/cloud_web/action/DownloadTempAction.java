package com.ucap.cloud_web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 
 * <p>
 * Description: 文件下载使用
 * </p>
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: downloadTempAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：masl@ucap.com.cn </p>
 *                          <p>
 * @date：2016-6-30下午4:32:45 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DownloadTempAction extends BaseAction {
	

	/**
	 * 
	 * @Description: 文件下载
	 * @author: masl@ucap.com.cn 2016-6-30下午4:34:42
	 */
	public void downFileUrl() {
		String fileName = request.getParameter("fileName");
		String filePath = request.getParameter("filePath");

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
		} finally {

		}
	}

/**
 * @Title: url地址下载文件
 * @Description:
 * @author liujc@ucap.com.cn	2016-9-20下午6:25:55
 */
}
