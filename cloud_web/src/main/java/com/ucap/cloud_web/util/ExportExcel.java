package com.ucap.cloud_web.util;

import java.awt.Color;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.publics.util.utils.DateUtils;
import com.ucap.cloud_web.dto.ExcelAddCellRequest;


/**
 * <p>Description: Excle 导出</p>
 * <p>@Package：com.ucap.cloud_web.util </p>
 * <p>Title: ExportExcel </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：sunjiang </p>
 * <p>@date：2015年11月16日下午3:15:29 </p>
 */
public class ExportExcel {
	private static Logger logger = Logger.getLogger(ExportExcel.class);

	/**
	 * 
	 * @描述:日常检测-搜索引擎收录/网站访问量
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月29日下午7:10:48
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String searchEngineTableExcel(String fileName, String title, List<Object[]> listContent) {

		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/vnd.ms-excel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			int rowLenght = listContent.get(0).length;

			WritableSheet sheet = workbook.createSheet(title, 0); // sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行

			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);// 列宽
			sheet.setRowView(0, 550);// 行高
			sheet.setRowView(1, 550);

			sheet.setColumnView(0, 25);
			sheet.setColumnView(1, 25);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 25);

			for (int x = 2; x < listContent.size() + 2; x++) {
				sheet.setRowView(x, 410);
			}

			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);// 设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(), color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);

			sheet.addCell(new Label(0, 0, title, wc));

			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));

			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(), wcf_center_color.getGreen(),
					wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			// 正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行

			// 超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false,
					UnderlineStyle.SINGLE, Colour.BLUE2);
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行

			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[]) listContent.get(y);
				if (y == 0) {
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1 + y, obj[z].toString(), wcf_center));
					}
				} else {
					for (int z = 0; z < obj.length; z++) {
						// if(z==2 ||z==5|| z==9){
						// String formu =
						// "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";
						// Formula formula = new Formula(z, 1+y,
						// formu,linkType);
						// sheet.addCell(formula);
						// }else{
						ExcelAddCellRequest request = new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
						// sheet.addCell(new Label(z, 1+y, obj[z].toString(),
						// contentType));
						// }
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;

	}
	
	/**
	 * 
	 * @描述:日常检测-首页连通率excel/栏目更新情况
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2016年12月29日下午7:10:48
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String webSiteConnectedTableExcel(String fileName, String title, List<Object[]> listContent) {

		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/vnd.ms-excel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);

			int rowLenght = listContent.get(0).length;

			WritableSheet sheet = workbook.createSheet(title, 0); // sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行

			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);// 列宽
			sheet.setRowView(0, 550);// 行高
			sheet.setRowView(1, 550);

			sheet.setColumnView(0, 25);
			sheet.setColumnView(1, 25);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 25);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 25);
			sheet.setColumnView(8, 26);
			sheet.setColumnView(9, 26);

			for (int x = 2; x < listContent.size() + 2; x++) {
				sheet.setRowView(x, 410);
			}

			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);// 设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(), color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);

			sheet.addCell(new Label(0, 0, title, wc));

			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));

			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(), wcf_center_color.getGreen(),
					wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			// 正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行

			// 超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false,
					UnderlineStyle.SINGLE, Colour.BLUE2);
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行

			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[]) listContent.get(y);
				if (y == 0) {
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1 + y, obj[z].toString(), wcf_center));
					}
				} else {
					for (int z = 0; z < obj.length; z++) {
						// if(z==2 ||z==5|| z==9){
						// String formu =
						// "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";
						// Formula formula = new Formula(z, 1+y,
						// formu,linkType);
						// sheet.addCell(formula);
						// }else{
						ExcelAddCellRequest request = new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
						// sheet.addCell(new Label(z, 1+y, obj[z].toString(),
						// contentType));
						// }
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * @Description: excle 导出
	 * @author sunjiang --- 2015年11月16日下午3:15:57
	 * @param fileName
	 *            文件名称
	 * @param title
	 *            标题
	 * @param listContent
	 *            正文内容
	 * @param content
	 *            第二行红色字体的内容 适合于
	 *            06内容更新-02栏目更新-01应两周内更新的栏目详情（导出）,06内容更新-02栏目更新-02应六个月内更新的栏目详情（
	 *            导出）,06内容更新-02栏目更新-03应一年内更新的栏目详情（导出）
	 * @return
	 */
	public final static String contentUpdateExcel(String fileName,String title, 
			List<Object[]> listContent,String content) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			WritableCellFormat contentUpdateStyle = new WritableCellFormat(contentUpdateFont);
			contentUpdateStyle.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentUpdateStyle.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentUpdateStyle.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentUpdateStyle.setWrap(true); // 文字是否换行
			Color contentUpdateColor = Color.decode("#92D050");// 自定义颜色
			workbook.setColourRGB(Colour.BLUE, contentUpdateColor.getRed(),contentUpdateColor.getGreen(), contentUpdateColor.getBlue());
			contentUpdateStyle.setBackground(Colour.BLUE);
			
			sheet.addCell(new Label(0, 0, title, wc));
			sheet.addCell(new Label(0, 1, content, contentUpdateStyle));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 41);
			sheet.setColumnView(3, 14);
			sheet.setColumnView(4, 19);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 44);
			for (int x = 0; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 540);
			}
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 2+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2){
//							for (int a = 0; a < obj.length; a++) {
//								String formu = "HYPERLINK(\""+obj[a]+"\",\""+obj[a]+"\")";  
//								Formula formula = new Formula(2, 2+y, formu,linkType);  
//								sheet.addCell(formula);  
//							}
//						}else if(z==6){
//							for (int a = 0; a < obj.length; a++) {
//								String formu = "HYPERLINK(\""+obj[a]+"\",\""+obj[a]+"\")";  
//								Formula formula = new Formula(6, 2+y, formu,linkType);  
//								sheet.addCell(formula);  
//							}
//						}else{

							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(2);
							request.setValue(obj[z]);
							addCell(request);
						
//							sheet.addCell(new Label(z, 2+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 01网站连通性-01首页连通性监测（导出）
	 * @author sunjiang --- 2015年11月17日下午6:48:24     
	 * @param fileName 文件名
	 * @param title	标题名
	 * @param listContent 正文
	 * @return
	 */
	public final static String websiteConnHomeExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 23);
			sheet.setColumnView(2, 27);
			sheet.setColumnView(3, 53);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	/**
	 * @Description: 导出历史申报
	 * @author linhb 2016-09-14 
	 * @param fileName 文件名
	 * @param title	标题名
	 * @param listContent 正文
	 * @return
	 */
	public final static String tempReportExcel(String fileName,String title, 
			List<Object[]> list) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = list.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 40);
			sheet.setColumnView(2, 40);
			sheet.setColumnView(3, 40);
			sheet.setColumnView(4, 40);
			sheet.setColumnView(5, 40);
			sheet.setColumnView(6, 40);
			sheet.setColumnView(7, 20);
			for (int x = 2; x < list.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < list.size(); y++) {
				Object[] obj = (Object[])list.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 01网站连通性-02业务系统连通性监测结果（导出）,01网站连通性-03关键栏目连通性监测结果（导出）
	 * @author sunjiang --- 2015年11月17日下午7:28:21     
	 * @param fileName  文件名
	 * @param title 标题
	 * @param listContent 正文内容
	 * @return
	 */
	public final static String websiteConnOtherExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 30);
			sheet.setColumnView(2, 40);
			sheet.setColumnView(3, 22);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 50);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(2, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 填报单位：06内容更新-01首页更新明细（导出）
	 * @author sunjiang --- 2015年11月18日下午8:24:04     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String contentUpdateDetailExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 30);
			sheet.setColumnView(2, 54);
			sheet.setColumnView(3, 56);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式			
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==3){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(3, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 02链接可用性-01首页链接可用性-01长期不可用链接（导出）
	 * @author sunjiang --- 2015年11月19日下午3:32:55     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String HomeLinkLongExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 47);
			sheet.setColumnView(2, 43);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 20);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
		
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==1){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(1, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 02链接可用性-01首页链接可用性-02首页链接可用性监测结果（导出）
	 * @author sunjiang --- 2015年11月19日下午3:49:25     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String HomeLinkHomeExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 25);
			sheet.setColumnView(2, 47);
			sheet.setColumnView(3, 38);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 25);
			sheet.setColumnView(6, 40);
			sheet.setColumnView(7, 40);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 810);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(2, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else if(z==4){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(4, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else if(z==7){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(7, 1+y, formu,linkType);  
//							sheet.addCell(formula); 
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 06内容更新-02栏目更新-04栏目更新明细（导出）
	 * @author sunjiang --- 2015年11月20日下午3:32:45     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String contentUpdateFourExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(fileName, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 35);
			sheet.setColumnView(3, 14);
			sheet.setColumnView(4, 14);
			sheet.setColumnView(5, 50);
			sheet.setColumnView(6, 50);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==5 || z==6){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 03内容保障问题-02栏目信息不更新监测结果（导出）
	 * @author zhurk --- 2015-11-25下午1:20:31     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String UnUpdateChannelExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 18);
			sheet.setColumnView(2, 18);
			sheet.setColumnView(3, 27);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 13);
			sheet.setColumnView(6, 29);
			sheet.setColumnView(7, 40);
			sheet.setColumnView(8, 40);
			sheet.setColumnView(9, 40);
			sheet.setColumnView(10, 40);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			//sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
			Object[] obj = (Object[])listContent.get(y);
			if(y==0){
			for (int z = 0; z < obj.length; z++) {
			sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
			}
			}else{
			for (int z = 0; z < obj.length; z++) {
//				if(z==4){
//					String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//					Formula formula = new Formula(z, 1+y, formu,linkType);  
//					sheet.addCell(formula);  
//				}else 
				if(z==9){
////					String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
////					Formula formula = new Formula(z, 1+y, formu,linkType);  
////					sheet.addCell(formula); 
					String[]  imgUrlStr= (String[]) obj[z];
					for(int i=0;i<imgUrlStr.length;i++){
//						String url=String.valueOf(imgUrlStr[i]);
//						String formu = "HYPERLINK(\""+url+"\",\""+url+"\")";  
//						Formula formula = new Formula(z+i, 1+y, formu,linkType);  
//						sheet.addCell(formula); 
						String url=String.valueOf(imgUrlStr[i]);
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(url);
						addCell(request);
					}
				}else{
					ExcelAddCellRequest request =new ExcelAddCellRequest();
					request.setSheet(sheet);
					request.setNormalFont(normalFont);
					request.setZ(z);
					request.setY(y);
					request.setyNum(1);
					request.setValue(obj[z]);
					addCell(request);
//					sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
				}
			}
			}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 04错别字监测结果(导出)
	 * @author zhurk --- 2015-12-3上午10:16:48     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String correctContentExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 21);
			sheet.setColumnView(2, 33);
			sheet.setColumnView(3, 33);
			sheet.setColumnView(4, 48);
			sheet.setColumnView(5, 48);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==4){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(4, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else if(z==5){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(5, 1+y, formu,linkType);  
//							sheet.addCell(formula); 
//						}
//						else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);							
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	//----------------------------------------组织单位---------------------------------------------------
	/** @Description: 12当前监测结果-01网站连通性-01首页连通性（导出）
	 * @author zhurk --- 2015-11-21下午6:15:39     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String organConnHomeExcel(String fileName,String title, 
			List<Object[]> listContent){
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 42);
			sheet.setColumnView(3, 29);
			sheet.setColumnView(4, 24);
			sheet.setColumnView(5, 19);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
			Object[] obj = (Object[])listContent.get(y);
			if(y==0){
			for (int z = 0; z < obj.length; z++) {
			sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
			}
			}else{
			for (int z = 0; z < obj.length; z++) {
//				if(z==3){
//					String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//					Formula formula = new Formula(3, 1+y, formu,linkType);  
//					sheet.addCell(formula);  
//				}else{
					ExcelAddCellRequest request =new ExcelAddCellRequest();
					request.setSheet(sheet);
					request.setNormalFont(normalFont);
					request.setZ(z);
					request.setY(y);
					request.setyNum(1);
					request.setValue(obj[z]);
					addCell(request);
//					sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//				}
			}
			}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 12当前监测结果-01网站连通性-02业务系统连通性（导出）,12当前监测结果-01网站连通性-03关键栏目连通性（导出）
	 * @author zhurk --- 2015-11-21下午8:55:58     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String organConnOtherExcel(String fileName,String title, 
			List<Object[]> listContent){
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 42);
			sheet.setColumnView(3, 19);
			sheet.setColumnView(4, 24);
			sheet.setColumnView(5, 19);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 17当前监测结果-06内容更新-01首页更新（导出）,17当前监测结果-06内容更新-02栏目更新（导出）
	 * @author zhurk --- 2015-11-21下午9:12:55     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String organUpdateExcel(String fileName,String title, 
			List<Object[]> listContent){
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 45);
			sheet.setColumnView(3, 44);
			sheet.setColumnView(4, 22);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
			Object[] obj = (Object[])listContent.get(y);
			if(y==0){
			for (int z = 0; z < obj.length; z++) {
			sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
			}
			}else{
			for (int z = 0; z < obj.length; z++) {
//				if(z==3){
//					String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//					Formula formula = new Formula(3, 1+y, formu,linkType);  
//					sheet.addCell(formula);  
//				}else{
					ExcelAddCellRequest request =new ExcelAddCellRequest();
					request.setSheet(sheet);
					request.setNormalFont(normalFont);
					request.setZ(z);
					request.setY(y);
					request.setyNum(1);
					request.setValue(obj[z]);
					addCell(request);
//					sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//				}
			}
			}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 13当前监测结果-02链接可用性-01首页链接可用性（导出）,13当前监测结果-02链接可用性-02全站链接可用性（导出）
	 * @author zhurk --- 2015-11-21下午9:24:53     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String organAvailiableExcel(String fileName,String title, 
			List<Object[]> listContent){
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 42);
			sheet.setColumnView(3, 40);
			sheet.setColumnView(4, 19);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
			Object[] obj = (Object[])listContent.get(y);
			if(y==0){
			for (int z = 0; z < obj.length; z++) {
			sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
			}
			}else{
			for (int z = 0; z < obj.length; z++) {
//				if(z==3){
//					String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//					Formula formula = new Formula(3, 1+y, formu,linkType);  
//					sheet.addCell(formula);  
//				}else{
					ExcelAddCellRequest request =new ExcelAddCellRequest();
					request.setSheet(sheet);
					request.setNormalFont(normalFont);
					request.setZ(z);
					request.setY(y);
					request.setyNum(1);
					request.setValue(obj[z]);
					addCell(request);
//					sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//				}
			}
			}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 14当前监测结果-03内容保障问题-01首页信息不更新（导出）
	 * @author zhurk --- 2015-11-21下午9:33:56     
     * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	*/
	public final static String organNotUpdateHomeExcel(String fileName,String title, 
			List<Object[]> listContent){
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 42);
			sheet.setColumnView(3, 22);
			sheet.setColumnView(4, 22);
			sheet.setColumnView(5, 24);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description: 14当前监测结果-03内容保障问题-02栏目信息不更新（导出）,14当前监测结果-03内容保障问题-03空白栏目监测（导出）
	 *                14当前监测结果-03内容保障问题-04互动回应差（导出）,14当前监测结果-03内容保障问题-05服务不实用（导出）
	 *                14当前监测结果-03内容保障问题-06基本信息（导出）
	 *                15当前监测结果-04错别字-01错别字（导出）
	 * @author zhurk --- 2015-11-21下午9:33:56     
	 * @param fileName          文件名称
	 * @param title             标题
	 * @param listContent       正文内容
	 * @return           
	 */
	public final static String organNotUpdateOrOtherExcel(String fileName,String title, 
			List<Object[]> listContent){
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 45);
			sheet.setColumnView(3, 44);
			sheet.setColumnView(4, 22);
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
			Object[] obj = (Object[])listContent.get(y);
			if(y==0){
			for (int z = 0; z < obj.length; z++) {
			sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
			}
			}else{
			for (int z = 0; z < obj.length; z++) {
//				if(z==3){
//					String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//					Formula formula = new Formula(3, 1+y, formu,linkType);  
//					sheet.addCell(formula);  
//				}else{
					ExcelAddCellRequest request =new ExcelAddCellRequest();
					request.setSheet(sheet);
					request.setNormalFont(normalFont);
					request.setZ(z);
					request.setY(y);
					request.setyNum(1);
					request.setValue(obj[z]);
					addCell(request);
//					sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//				}
			}
			}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 01监测结果概览（导出）
	 * @author sunjiang --- 2015年11月23日下午4:22:57     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String checkResultExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			//sheet.mergeCells(0, 2, 1, 2);// 标题 合并列
			
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			sheet.setRowView(2, 550);
			
			sheet.setColumnView(0, 15);
			sheet.setColumnView(1, 50);
			sheet.setColumnView(2, 17);
			sheet.setColumnView(3, 17);
			sheet.setColumnView(4, 17);
			sheet.setColumnView(5, 17);
			sheet.setColumnView(6, 17);
			sheet.setColumnView(7, 17);
			sheet.setColumnView(8, 17);
			sheet.setColumnView(9, 17);
			
			for (int x = 3; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			//sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			

			//标题字体红色
			WritableFont redFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			WritableCellFormat title_center_red = new WritableCellFormat(redFont);
			title_center_red.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			title_center_red.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			title_center_red.setAlignment(Alignment.CENTRE); // 文字水平对齐
			title_center_red.setWrap(true); // 文字是否换行
			title_center_red.setBackground(Colour.ORANGE);
			
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}
				/*else if(y==1){
					for (int z = 0; z < obj.length; z++) {
						if(z==0){
							sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
						}else{
							sheet.addCell(new Label(z, 1+y, obj[z].toString(), title_center_red));
						}
					}
				}*/
				else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 13当前监测结果-02链接可用性-02全站链接可用性（导出）
	 * @author cuichx --- 2015-11-23下午6:51:12     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String linkAllDetailExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/vnd.ms-excel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 25);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 9);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 25);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 25);
			sheet.setColumnView(8, 26);
			sheet.setColumnView(9, 26);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2 ||z==5|| z==9){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @Description: 03内容保障问题-03空白栏目监测结果（导出）
	 * @author cuichx --- 2015-11-23下午6:51:12     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String blankDetailExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 35);


			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else 
						if(z==6){
							String[]  imgUrlStr= (String[]) obj[z];
							for(int i=0;i<imgUrlStr.length;i++){
//								String url=String.valueOf(imgUrlStr[i]);
//								String formu = "HYPERLINK(\""+url+"\",\""+url+"\")";  
//								Formula formula = new Formula(z+i, 1+y, formu,linkType);  
//								sheet.addCell(formula); 
								String url=String.valueOf(imgUrlStr[i]);
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(url);
								addCell(request);
							}
						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @Description: 03内容保障问题-04互动回应差监测结果（导出）
	 * @author cuichx --- 2015-11-25下午1:40:54     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String responseChannelExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 25);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 35);

			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						if(z==6){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
							String[]  imgUrlStr= (String[]) obj[z];
							for(int i=0;i<imgUrlStr.length;i++){
								String url=String.valueOf(imgUrlStr[i]);
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(url);
								addCell(request);
//								String formu = "HYPERLINK(\""+url+"\",\""+url+"\")";  
//								Formula formula = new Formula(z+i, 1+y, formu,linkType);  
//								sheet.addCell(formula); 
							}
						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @Description: 03内容保障问题-05服务不实用监测结果（导出）
	 * @author cuichx --- 2015-12-18下午4:12:27     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	public final static String serviceUnuseExcel(String fileName, String title,
			ArrayList<Object[]> listService) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listService.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 25);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 100);

			
			for (int x = 2; x < listService.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listService.size(); y++) {
				Object[] obj = (Object[])listService.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==4 ){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//							
//						}else 
						if(z==7){
							String[]  imgUrlStr= (String[]) obj[z];
							for(int i=0;i<imgUrlStr.length;i++){
//								String url=String.valueOf(imgUrlStr[i]);
//								String formu = "HYPERLINK(\""+url+"\",\""+url+"\")";  
//								Formula formula = new Formula(z+i, 1+y, formu,linkType);  
//								sheet.addCell(formula); 
								String url=String.valueOf(imgUrlStr[i]);
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(url);
								addCell(request);
							}
						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Description: 03内容保障问题-06基本信息监测结果（导出）
	 * @author cuichx --- 2015-11-25下午1:40:54     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String basicChannelExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 25);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 35);
			sheet.setColumnView(6, 60);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==6){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @Title: 安全问题监测结果（导出）/日常监测-内容保障问题导出/日常监测-首页链接可用性
	 * @Description:
	 * @author liujc@ucap.com.cn 2016-9-2下午1:35:00
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String securityQuestionExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 25);
			sheet.setColumnView(5, 35);
			sheet.setColumnView(6, 35);

			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==5){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else if(z==6){
//							Object[]  imgUrlStr= (Object[]) obj[z];
//							for(int i=0;i<imgUrlStr.length;i++){
//								String url=String.valueOf(imgUrlStr[i]);
//								String formu = "HYPERLINK(\""+url+"\",\""+url+"\")";  
//								Formula formula = new Formula(z+i, 1+y, formu,linkType);  
//								sheet.addCell(formula); 
//							}
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * @Description: 06订单（导出）
	 * @author sunjiang --- 2015-12-7下午3:32:47     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String orderExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 32);
			sheet.setColumnView(3, 27);
			sheet.setColumnView(4, 27);
			sheet.setColumnView(5, 19);
			sheet.setColumnView(6, 16);
			sheet.setColumnView(7, 27);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行


			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==3||z==4){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/** @Description:02  XXX政府网站群预警信息（导出）
	 * @author zhurk --- 2015-12-08下午4:50:49     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return           
	*/
	public final static String earlyWarningExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 18);
			sheet.setColumnView(2, 50);
			sheet.setColumnView(3, 18);
			sheet.setColumnView(4, 15);


			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/** @Description: 24网站抽查-成绩单（导出）
	 * @author zhurk --- 2015-12-10下午2:25:02     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return           
	*/
	public final static String spotCheckExcel(String fileName,String title, 
			List<Object[]> listContent) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listContent.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 43);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 18);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 14);
			sheet.setColumnView(8, 13);
			
			
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @Description: 08重点监测栏目-系统（导出）
	 * @author Nora --- 2015-12-8下午14:32:47     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String channelExcel(String fileName,String title, String sheetTitle,
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(sheetTitle, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 25);
			sheet.setColumnView(2, 40);
			sheet.setColumnView(3, 40);
			sheet.setColumnView(4, 27);
			sheet.setColumnView(5, 27);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2||z==3){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * @Description: z组织单位报告管理--批量导出
	 * @author cuichx --- 2015-12-18下午4:12:27     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	public final static String batchDownloadExcel(String fileName, String title,
			ArrayList<Object[]> listService) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			int rowLenght = listService.get(0).length;
			
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(false); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 25);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 25);

			
			for (int x = 2; x < listService.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
					
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			/** ************标题描述---内容更新样式************ */
			sheet.addCell(new Label(0, 0, title, wc));
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listService.size(); y++) {
				Object[] obj = (Object[])listService.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * @Description: 抽查---更新信息明细
	 * @author sunjiang --- 2016-1-18上午11:54:10     
	 * @param fileName
	 * @param titleUpdateDetail
	 * @param listContentUpdateDetail
	 * @return
	 */
	public final static String spotUpdateDetailExcel(String fileName,String titleUpdateDetail, 
			List<Object[]> listContentUpdateDetail) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheetUpdateDetail = workbook.createSheet(titleUpdateDetail, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheetUpdateDetail.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			int rowLenghtUpdateDetail = listContentUpdateDetail.get(0).length;
			
			sheetUpdateDetail.mergeCells(0, 0, rowLenghtUpdateDetail - 1, 0);// 标题 合并列
			sheetUpdateDetail.setColumnView(0, 17);//列宽
			sheetUpdateDetail.setRowView(0, 600);//行高
			sheetUpdateDetail.setRowView(1, 600);
			
			sheetUpdateDetail.setColumnView(0, 8);
			sheetUpdateDetail.setColumnView(1, 18);
			sheetUpdateDetail.setColumnView(2, 30);
			sheetUpdateDetail.setColumnView(3, 18);
			sheetUpdateDetail.setColumnView(4, 18);
			sheetUpdateDetail.setColumnView(5, 35);
			sheetUpdateDetail.setColumnView(6, 35);
			
			for (int x = 2; x < listContentUpdateDetail.size()+2; x++) {
				sheetUpdateDetail.setRowView(x, 500);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#D9D9D9");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheetUpdateDetail.addCell(new Label(0, 0, titleUpdateDetail, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheetUpdateDetail.addCell(new Label(0, 0, titleUpdateDetail, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行


			Color wcf_center_color = Color.decode("#F2F2F2");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContentUpdateDetail.size(); y++) {
				Object[] obj = (Object[])listContentUpdateDetail.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetUpdateDetail.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==5||z==6){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheetUpdateDetail.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheetUpdateDetail);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheetUpdateDetail.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * @Description: 
	 * @author sunjiang --- 2016-1-19下午5:58:02     
	 * @param fileName
	 * @param map--param   {title: sheel标题， siteName：网站名称，periodNum：期号，startDate：开始时间，endDate：结束时间，url：首页Url，reportDate：报告日期  }
	 * @param titleEarly  预警标题
	 * @param listContentEarly	预警列表
	 * @param titleConnection	不连通标题
	 * @param listContentConnection		不连通标题列表
	 * @param titleLink	不可用链接标题
	 * @param listContentLink	不可用链接内容
	 * @param titleCorrect	错别字标题
	 * @param listContentCorrect	错别字列表
	 * @param titleUpdateDetail	更新信息标题
	 * @param listContentUpdateDetail	更新信息明细
	 * @return
	 */
	public final static String spotExcel(String fileName,Map<String, Object> param,String titleEarly,List<Object[]> listContentEarly,
			String titleConnection,List<Object[]> listContentConnection
			,String titleLink,List<Object[]> listContentLink
			,String titleCorrect,List<Object[]> listContentCorrect
			,String titleUpdateDetail,List<Object[]> listContentUpdateDetail) {
		
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
//			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** ************样式开始************** */
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.NONE); // 线条
			wcf_left.setBackground(Colour.WHITE);
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#D9D9D9");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			
			WritableFont spotTitleFont = new WritableFont(WritableFont.createFont("黑体"), 22, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat spotWc = new WritableCellFormat(spotTitleFont);
			spotWc.setBorder(Border.ALL, BorderLineStyle.NONE); // 线条
			spotWc.setBackground(Colour.WHITE);
			spotWc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			spotWc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			
			WritableFont spotcontent = new WritableFont(WritableFont.createFont("仿宋"), 12, WritableFont.NO_BOLD);//设置单元格字体
			WritableCellFormat spotContentWc = new WritableCellFormat(spotcontent);
			spotContentWc.setBorder(Border.ALL, BorderLineStyle.NONE); // 线条
			spotContentWc.setBackground(Colour.WHITE);
			spotContentWc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			spotContentWc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			
			WritableCellFormat spotContentWcLeft = new WritableCellFormat(spotcontent);
			spotContentWcLeft.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			spotContentWcLeft.setBorder(Border.ALL, BorderLineStyle.NONE); // 线条
			spotContentWcLeft.setBackground(Colour.WHITE);
			spotContentWcLeft.setAlignment(Alignment.LEFT); // 文字水平对齐
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#F2F2F2");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			/** ************样式结束************ */
			
			
			
			
			WritableSheet sheet = workbook.createSheet((String) param.get("title"), 0); //sheet标签
			//去边框，填充白色背景
			for (int y = 0; y < 100; y++) {
				for (int z = 0; z < 20; z++) {
					sheet.addCell(new Label(z, y, "", wcf_left));
				}
			}
			
			
			sheet.setColumnView(0, 11);//列宽
			sheet.setColumnView(1, 130);//列宽
			
			sheet.setRowView(0, 750);//行高
			sheet.setRowView(1, 1000);//行高
			sheet.setRowView(2, 950);//行高
			sheet.setRowView(3, 550);//行高
			sheet.setRowView(4, 400);//行高
			sheet.setRowView(5, 400);//行高
			sheet.setRowView(7, 1000);//行高
			sheet.setRowView(9, 550);//行高
			sheet.setRowView(10, 550);//行高
			sheet.setRowView(11, 550);//行高
			sheet.setRowView(12, 400);//行高
			sheet.setRowView(13, 550);//行高
			sheet.setRowView(14, 1300);//行高
			
			//网站名称
			String siteName = param.get("siteName")==null?"":(String)param.get("siteName");
			//期号
			int periodNum = param.get("periodNum")==null?0:Integer.parseInt((String)param.get("periodNum"));
			//开始时间
			String startDate = param.get("startDate")==null?"":(String)param.get("startDate");
			//结束时间
			String endDate = param.get("endDate")==null?"":(String)param.get("endDate");
			//首页url
			String url = param.get("url")==null?"":(String)param.get("url");
			//报告日期
			String reportDate = param.get("reportDate")==null?"":(String)param.get("reportDate");
			
			
			sheet.addCell(new Label(1, 1, siteName+"网站", spotWc));
			sheet.addCell(new Label(1, 2, "监测报告", spotWc));
			sheet.addCell(new Label(1, 3, "第"+periodNum+"期", spotContentWc));
			sheet.addCell(new Label(1, 4, startDate+"  至  "+endDate, spotContentWc));
			sheet.addCell(new Label(1, 9, "                      网站名称："+siteName, spotContentWcLeft));
			sheet.addCell(new Label(1, 10, "                      首页网址："+url, spotContentWcLeft));
			sheet.addCell(new Label(1, 11, "                      报告日期："+reportDate, spotContentWcLeft));
			sheet.addCell(new Label(1, 14, "开普互联科技有限公司", spotContentWc));
			
			
			
			
			/** ************预警开始************ */
			int rowLenght = listContentEarly.get(0).length;
			
			WritableSheet sheetEarly = workbook.createSheet(titleEarly, 1); //sheet标签
			
			sheetEarly.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheetEarly.setColumnView(0, 17);//列宽
			sheetEarly.setRowView(0, 600);//行高
			sheetEarly.setRowView(1, 600);
			sheetEarly.setColumnView(0, 8);
			sheetEarly.setColumnView(1, 30);
			sheetEarly.setColumnView(2, 40);
			sheetEarly.setColumnView(3, 55);
			sheetEarly.setColumnView(4, 30);

			
			for (int x = 2; x < listContentEarly.size()+2; x++) {
				sheetEarly.setRowView(x, 500);
			}
			
			sheetEarly.addCell(new Label(0, 0, titleEarly, wc));
			
			for (int y = 0; y < listContentEarly.size(); y++) {
				Object[] obj = (Object[])listContentEarly.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetEarly.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
							sheetEarly.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** ************预警结束************ */
			
			
			
			
			
			/** ************不连通开始************ */
			WritableSheet sheetConnection = workbook.createSheet(titleConnection, 2); //sheet标签

//			jxl.SheetSettings sheetset = sheetConnection.getSettings();
//			sheetset.setProtected(false);
			int rowLenghtConnection = listContentConnection.get(0).length;
			
			sheetConnection.mergeCells(0, 0, rowLenghtConnection - 1, 0);// 标题 合并列
			sheetConnection.setColumnView(0, 17);//列宽
			sheetConnection.setRowView(0, 600);//行高
			sheetConnection.setRowView(1, 600);
			
			sheetConnection.setColumnView(0, 8);
			sheetConnection.setColumnView(1, 45);
			sheetConnection.setColumnView(2, 30);
			sheetConnection.setColumnView(3, 90);
			
			for (int x = 2; x < listContentConnection.size()+2; x++) {
				sheetConnection.setRowView(x, 500);
			}
			
			
			sheetConnection.addCell(new Label(0, 0, titleConnection, wc));
			
			for (int y = 0; y < listContentConnection.size(); y++) {
				Object[] obj = (Object[])listContentConnection.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetConnection.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==1){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheetConnection.addCell(formula);  
//						}else{
							sheetConnection.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** ************不连通结束************ */
			
			
			

			
			
			/** ************不可用链接开始************ */
			WritableSheet sheetLink = workbook.createSheet(titleLink, 3); //sheet标签

			int rowLenghtLink = listContentLink.get(0).length;
			sheetLink.mergeCells(0, 0, rowLenghtLink - 1, 0);// 标题 合并列
			sheetLink.setColumnView(0, 17);//列宽
			sheetLink.setRowView(0, 600);//行高
			sheetLink.setRowView(1, 600);
			
			sheetLink.setColumnView(0, 8);
			sheetLink.setColumnView(1, 15);
			sheetLink.setColumnView(2, 35);
			sheetLink.setColumnView(3, 35);
			sheetLink.setColumnView(4, 30);
			sheetLink.setColumnView(5, 15);
			sheetLink.setColumnView(6, 30);
			sheetLink.setColumnView(7, 35);
			
			sheetLink.addCell(new Label(0, 0, titleLink, wc));
			
			for (int x = 2; x < listContentLink.size()+2; x++) {
				sheetLink.setRowView(x, 500);
			}
			
			for (int y = 0; y < listContentLink.size(); y++) {
				Object[] obj = (Object[])listContentLink.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetLink.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2||z==7||z==3){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheetLink.addCell(formula);  
//						}else{
							sheetLink.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			
			/** ************不可用链接结束************ */
			
			
			
			/** ************错别字开始************ */
			
			WritableSheet sheetCorrect = workbook.createSheet(titleCorrect, 4); //sheet标签
			
			int rowLenghtCorrect = listContentCorrect.get(0).length;
			sheetCorrect.mergeCells(0, 0, rowLenghtCorrect - 1, 0);// 标题 合并列
			sheetCorrect.setColumnView(0, 17);//列宽
			sheetCorrect.setRowView(0, 600);//行高
			sheetCorrect.setRowView(1, 600);
			
			sheetCorrect.setColumnView(0, 8);
			sheetCorrect.setColumnView(1, 10);
			sheetCorrect.setColumnView(2, 20);
			sheetCorrect.setColumnView(3, 35);
			sheetCorrect.setColumnView(4, 45);
			sheetCorrect.setColumnView(5, 45);
			
			for (int x = 2; x < listContentCorrect.size()+2; x++) {
				sheetCorrect.setRowView(x, 500);
			}
			
			
			sheetCorrect.addCell(new Label(0, 0, titleCorrect, wc));
			
			
			
			for (int y = 0; y < listContentCorrect.size(); y++) {
				Object[] obj = (Object[])listContentCorrect.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetCorrect.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==4||z==5){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheetCorrect.addCell(formula);  
//						}else{
							sheetCorrect.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			
			/** ************错别字结束************ */
			
			
			/** ************更新明细开始************ */
			
			WritableSheet sheetUpdateDetail = workbook.createSheet(titleUpdateDetail, 5); //sheet标签

			int rowLenghtUpdateDetail = listContentUpdateDetail.get(0).length;
			
			sheetUpdateDetail.mergeCells(0, 0, rowLenghtUpdateDetail - 1, 0);// 标题 合并列
			sheetUpdateDetail.setColumnView(0, 17);//列宽
			sheetUpdateDetail.setRowView(0, 600);//行高
			sheetUpdateDetail.setRowView(1, 600);
			
			sheetUpdateDetail.setColumnView(0, 8);
			sheetUpdateDetail.setColumnView(1, 18);
			sheetUpdateDetail.setColumnView(2, 30);
			sheetUpdateDetail.setColumnView(3, 18);
			sheetUpdateDetail.setColumnView(4, 18);
			sheetUpdateDetail.setColumnView(5, 35);
			sheetUpdateDetail.setColumnView(6, 35);
			
			sheetUpdateDetail.addCell(new Label(0, 0, titleUpdateDetail, wc));
			
			for (int x = 2; x < listContentUpdateDetail.size()+2; x++) {
				sheetUpdateDetail.setRowView(x, 500);
			}
			
			for (int y = 0; y < listContentUpdateDetail.size(); y++) {
				Object[] obj = (Object[])listContentUpdateDetail.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetUpdateDetail.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==5||z==6){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheetUpdateDetail.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheetUpdateDetail.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			
			/** ************更新明细结束************ */
			
			
			
			
			
			
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * @Description: 抽查---不连通问题明细
	 * @author sunjiang --- 2016-1-18下午5:08:51     
	 * @param fileName
	 * @param titleConnection
	 * @param listContentConnection
	 * @return
	 */
	public final static String spotConnectionExcel(String fileName,String titleConnection, 
			List<Object[]> listContentConnection) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContentConnection.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheetConnection = workbook.createSheet(titleConnection, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheetConnection.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheetConnection.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheetConnection.setColumnView(0, 17);//列宽
			sheetConnection.setRowView(0, 600);//行高
			sheetConnection.setRowView(1, 600);
			
			sheetConnection.setColumnView(0, 8);
			sheetConnection.setColumnView(1, 45);
			sheetConnection.setColumnView(2, 30);
			sheetConnection.setColumnView(3, 90);
			
			for (int x = 2; x < listContentConnection.size()+2; x++) {
				sheetConnection.setRowView(x, 500);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#D9D9D9");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheetConnection.addCell(new Label(0, 0, titleConnection, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheetConnection.addCell(new Label(0, 0, titleConnection, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#F2F2F2");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContentConnection.size(); y++) {
				Object[] obj = (Object[])listContentConnection.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetConnection.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==1){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheetConnection.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheetConnection);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheetConnection.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 抽查--不可用链接明细
	 * @author sunjiang --- 2016-1-18下午5:43:29     
	 * @param fileName
	 * @param titleLink
	 * @param listContentLink
	 * @return
	 */
	public final static String spotLinkExcel(String fileName,String titleLink, 
			List<Object[]> listContentLink) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheetLink = workbook.createSheet(titleLink, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheetLink.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			int rowLenghtLink = listContentLink.get(0).length;
			sheetLink.mergeCells(0, 0, rowLenghtLink - 1, 0);// 标题 合并列
			sheetLink.setColumnView(0, 17);//列宽
			sheetLink.setRowView(0, 600);//行高
			sheetLink.setRowView(1, 600);
			
			sheetLink.setColumnView(0, 8);
			sheetLink.setColumnView(1, 15);
			sheetLink.setColumnView(2, 35);
			sheetLink.setColumnView(3, 35);
			sheetLink.setColumnView(4, 30);
			sheetLink.setColumnView(5, 15);
			sheetLink.setColumnView(6, 30);
			sheetLink.setColumnView(7, 35);
			
			for (int x = 2; x < listContentLink.size()+2; x++) {
				sheetLink.setRowView(x, 500);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#D9D9D9");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheetLink.addCell(new Label(0, 0, titleLink, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheetLink.addCell(new Label(0, 0, titleLink, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#F2F2F2");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContentLink.size(); y++) {
				Object[] obj = (Object[])listContentLink.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetLink.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==2||z==7||z==3){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheetLink.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheetLink);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheetLink.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Description:抽查--- 错别字
	 * @author sunjiang --- 2016-1-18下午6:22:19     
	 * @param fileName
	 * @param titleCorrect
	 * @param listContentCorrect
	 * @return
	 */
	public final static String spotCorrectExcel(String fileName,String titleCorrect, 
			List<Object[]> listContentCorrect) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
//			HttpServletResponse response = ServletActionContext.getResponse();
//			OutputStream os = response.getOutputStream();// 取得输出流
//			response.reset();// 清空输出流
//			response.setHeader("Content-disposition", "attachment; filename="
//					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
//			// 设定输出文件头
//			response.setContentType("application/msexcel");// 定义输出类型
//			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
			File file = new File(fileName);
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheetCorrect = workbook.createSheet(titleCorrect, 1); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheetCorrect.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			int rowLenghtCorrect = listContentCorrect.get(0).length;
			sheetCorrect.mergeCells(0, 0, rowLenghtCorrect - 1, 0);// 标题 合并列
			sheetCorrect.setColumnView(0, 17);//列宽
			sheetCorrect.setRowView(0, 600);//行高
			sheetCorrect.setRowView(1, 600);
			
			sheetCorrect.setColumnView(0, 8);
			sheetCorrect.setColumnView(1, 10);
			sheetCorrect.setColumnView(2, 20);
			sheetCorrect.setColumnView(3, 35);
			sheetCorrect.setColumnView(4, 45);
			sheetCorrect.setColumnView(5, 45);
			
			for (int x = 2; x < listContentCorrect.size()+2; x++) {
				sheetCorrect.setRowView(x, 500);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#D9D9D9");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheetCorrect.addCell(new Label(0, 0, titleCorrect, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheetCorrect.addCell(new Label(0, 0, titleCorrect, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#F2F2F2");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContentCorrect.size(); y++) {
				Object[] obj = (Object[])listContentCorrect.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheetCorrect.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==4||z==5){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheetCorrect.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheetCorrect);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheetCorrect.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Description: 抽查站点详情页面--抽查结果数据导出
	 * @author cuichx --- 2016-5-5下午8:44:46     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	public final static String  spotResultExcel(String fileName, String title,
			ArrayList<Object[]> list) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = list.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 20);
			for (int x = 2; x < list.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < list.size(); y++) {
				Object[] obj = (Object[])list.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * @Description: 抽查站点、全面监测详情页面--按服务周期汇总结果数据导出
	 * @author cuichx --- 2016-5-5下午8:44:46     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	public final static String  detectionPeroidExcel(String fileName, String title,
			ArrayList<Object[]> list) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = list.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 6);
			sheet.setColumnView(1, 16);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 20);
			sheet.setColumnView(7, 15);
			sheet.setColumnView(8, 15);
			sheet.setColumnView(9, 15);
			sheet.setColumnView(10, 15);
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 15);
			sheet.setColumnView(13,15);
			sheet.setColumnView(14, 15);
			sheet.setColumnView(15, 15);
			for (int x = 2; x < list.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < list.size(); y++) {
				Object[] obj = (Object[])list.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==3){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//					}else{
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//					}
						
					
				}
				}
				
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		String fileName = "f:/抽查---不连通问题明细.xls";
		
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("title", "封皮");
		paramMap.put("siteName", "央视网站");
		paramMap.put("periodNum", "2");
		paramMap.put("startDate", "2015年01月01日");
		paramMap.put("endDate", "2015年01月02日");
		paramMap.put("url", "http://www.baidu.com");
		paramMap.put("reportDate", "2015年01月01日");
		
		
		String titleEarly = "预警信息统计明细";

		ArrayList<Object[]> listContentEarly = new ArrayList<Object[]>();
		Object[] obj1 = new Object[] { "序号", "二级指标名称", "问题类型", "问题描述", "发现时间"};

		Object[] obj = new Object[] { "1", "首页连通性", "站点无法访问", "404错误",
				"2015.10.22 11:23:47" };
		listContentEarly.add(obj1);
		listContentEarly.add(obj);
		listContentEarly.add(obj);
		listContentEarly.add(obj);
		listContentEarly.add(obj);
		listContentEarly.add(obj);
		    
		
		String titleConnection = "不连通问题明细";

		ArrayList<Object[]> listContentConnection = new ArrayList<Object[]>();
		Object[] obj1Connection = new Object[] { "序号", "URL", "时间", "问题描述"};

		Object[] objConnection = new Object[] { "1", "http://www.nc.fcom1fsd3.com", "2015.10.22 11:23:47", "404 服务器成功处理了请求"};
		listContentConnection.add(obj1Connection);
		listContentConnection.add(objConnection);
		listContentConnection.add(objConnection);
		listContentConnection.add(objConnection);
		listContentConnection.add(objConnection);
		listContentConnection.add(objConnection);
		
		
		String titleLink = "不可用链接明细";
		
		ArrayList<Object[]> listContentLink = new ArrayList<Object[]>();
		Object[] obj1Link = new Object[] {  "序号", "监测时间", "不可用链接URL", "父页面URL","链接标题","资源描述","问题描述","快照"};
		
		Object[] objLink = new Object[] { "1", "2015/10/1", "http://www.nc.fcom1fsd3.com", "http://www.nc.fcom1fsd3.com","陈世泽常委开展街道动员工作会议","图片(站内)","500 服务器找不到请求","http://www.nc.fcom1fsd3.com"};
		listContentLink.add(obj1Link);
		listContentLink.add(objLink);
		listContentLink.add(objLink);
		listContentLink.add(objLink);
		listContentLink.add(objLink);
		listContentLink.add(objLink);
		
		
		
		String titleCorrect = "错别字监测结果明细";
		
		ArrayList<Object[]> listContentCorrect = new ArrayList<Object[]>();
		Object[] obj1Correct = new Object[] {"序号", "监测深度", "错误类型", "问题描述","网页URL","快照"};
		
		Object[] objCorrect = new Object[] { "1", "2", "疑似错误", "“总理”写成“总经理”","http://www.nc.mo.gov","http://www.nc.mo.gov"};
		listContentCorrect.add(obj1Correct);
		listContentCorrect.add(objCorrect);
		listContentCorrect.add(objCorrect);
		listContentCorrect.add(objCorrect);
		listContentCorrect.add(objCorrect);
		listContentCorrect.add(objCorrect);
		
		
		String titleUpdateDetail = "更新信息明细";
		
		ArrayList<Object[]> listContentUpdateDetail = new ArrayList<Object[]>();
		Object[] obj1UpdateDetail = new Object[] { "序号","更新时间", "标题", "栏目","分类","栏目URL","快照"};
		
		Object[] objUpdateDetail = new Object[] { "1", "2015.8.18 08:07", "推动简政放权向纵深发展", "地方动态","通知公告","http://www.nc.mo.gov","http://www.nc.fcom1fsd3.com"};
		listContentUpdateDetail.add(obj1UpdateDetail);
		listContentUpdateDetail.add(objUpdateDetail);
		listContentUpdateDetail.add(objUpdateDetail);
		listContentUpdateDetail.add(objUpdateDetail);
		listContentUpdateDetail.add(objUpdateDetail);
		listContentUpdateDetail.add(objUpdateDetail);
		
		    
	   ExportExcel.spotExcel(fileName,paramMap, titleEarly, listContentEarly, titleConnection, listContentConnection, titleLink, listContentLink, titleCorrect, listContentCorrect, titleUpdateDetail, listContentUpdateDetail);
	}
	
	
	
	
	/**
	 * @Description: 大数据导出excel
	 * @author cuichx --- 2016-5-28下午4:12:39     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public final static String bigDataExcel(String fileName,List<Map<String, Object>> list,String searchDate) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File("D:\\123\\"+fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			 
			
			for(int i=0;i<list.size();i++){
				
				Map<String, Object> map=list.get(i);
				List<Object[]> shList=(List<Object[]>) map.get("list");
				String title=(String) list.get(i).get("title");
				
				int rowLenght=shList.get(0).length;
				
				WritableSheet sheet = workbook.createSheet(title+DateUtils.formatShortDate(new Date()), i); //sheet标签
				/** **********设置纵横打印（默认为纵打）、打印纸***************** */
				jxl.SheetSettings sheetset = sheet.getSettings();
				sheetset.setProtected(false);
				
				/** ************设置单元格字体************** */
//				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
				WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				// 用于正文居左
				WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
				wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_left.setWrap(true); // 文字是否换行
				sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
				sheet.setColumnView(0, 17);//列宽
				sheet.setRowView(0, 540);//行高
				sheet.setRowView(1, 555);
				
				if(rowLenght==9){
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 20);
					sheet.setColumnView(2, 20);
					sheet.setColumnView(3, 20);
					
					sheet.setColumnView(4, 20);
					sheet.setColumnView(5, 20);
					sheet.setColumnView(6, 30);
					sheet.setColumnView(7, 30);
					sheet.setColumnView(8, 30);
				}else{
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 20);
					sheet.setColumnView(2, 30);
					sheet.setColumnView(3, 30);
					sheet.setColumnView(4, 30);
					sheet.setColumnView(5, 20);
					sheet.setColumnView(6, 30);
					sheet.setColumnView(7, 30);
					sheet.setColumnView(8, 30);
					sheet.setColumnView(9, 30);
					sheet.setColumnView(10, 30);
					sheet.setColumnView(11, 30);
				}

				for (int x = 2; x < shList.size()+2; x++) {
					sheet.setRowView(x, 420);
				}
//				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//				WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
				
				/** ************标题************ */
				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
				WritableCellFormat wc = new WritableCellFormat(titleFont);
				wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wc.setWrap(true); // 文字是否换行
				// 标题颜色
				Color color = Color.decode("#00B150");
				workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
				wc.setBackground(Colour.BRIGHT_GREEN);
				
				sheet.addCell(new Label(0, 0, title, wc));
				///////////////////////////////////////////////////////////////////////////////////////////////
				/** ************标题描述---内容更新样式************ */
				
				sheet.addCell(new Label(0, 0, title, wc));
				// 标题 合并列
				//sheet.setColumnView(0, new String(content).length());
				//sheet.mergeCells(0, 1, rowLenght - 1, 1);
				
				
				// 列表标题----用于标题居中
				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
				WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
				wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_center.setWrap(true); // 文字是否换行
				
				Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
				workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
				wcf_center.setBackground(Colour.ORANGE);
				//正文样式
				WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				WritableCellFormat contentType = new WritableCellFormat(normalFont);
				contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				contentType.setWrap(true); // 文字是否换行
				//超链接样式
				WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
				WritableCellFormat linkType = new WritableCellFormat(linkFont);
				linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				linkType.setWrap(true); // 文字是否换行
				
				for (int y = 0; y < shList.size(); y++) {
					Object[] obj = (Object[])shList.get(y);
					if(y==0){
						for (int z = 0; z < obj.length; z++) {
							sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
						}
					}else{
						for (int z = 0; z < obj.length; z++) {
//							if(obj.length==11 && z==10){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheet.addCell(formula);  
//							}else{
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(obj[z]);
								addCell(request);
//							}
						}
					}
				}
			}
			

			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * 
	 * @描述:网站访问量 
	 * @作者:linhb@ucap.com.cn
	 * @时间:2016年12月15日下午1:45:39 
	 * @param fileName
	 * @param list
	 * @param searchDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static String getVisitDatas(String fileName,List<Map<String, Object>> list) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作簿************ */
			/** **********创建工作表************ */
			 
			
			for(int i=0;i<list.size();i++){
				
				Map<String, Object> map=list.get(i);
				List<Object[]> shList=(List<Object[]>) map.get("list");
				String title=(String) list.get(i).get("title");
				
				int rowLenght=shList.get(0).length;
				
				WritableSheet sheet = workbook.createSheet(title+DateUtils.formatShortDate(new Date()), i); //sheet标签
				/** **********设置纵横打印（默认为纵打）、打印纸***************** */
				jxl.SheetSettings sheetset = sheet.getSettings();
				sheetset.setProtected(false);
				
				/** ************设置单元格字体************** */
//				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
				WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				// 用于正文居左
				WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
				wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_left.setWrap(true); // 文字是否换行
				sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
				sheet.setColumnView(0, 17);//列宽
				sheet.setRowView(0, 540);//行高
				sheet.setRowView(1, 555);
				
				if(rowLenght==5){
					sheet.setColumnView(0, 40);
					sheet.setColumnView(1, 40);
					sheet.setColumnView(2, 20);
					sheet.setColumnView(3, 20);
					sheet.setColumnView(4, 20);
				}else{
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 30);
					sheet.setColumnView(2, 30);
					sheet.setColumnView(3, 30);
				}

				for (int x = 2; x < shList.size()+2; x++) {
					sheet.setRowView(x, 420);
				}
				
				/** ************标题************ */
				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
				WritableCellFormat wc = new WritableCellFormat(titleFont);
				wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wc.setWrap(true); // 文字是否换行
				// 标题颜色
				Color color = Color.decode("#00B150");
				workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
				wc.setBackground(Colour.BRIGHT_GREEN);
				
				sheet.addCell(new Label(0, 0, title, wc));
				///////////////////////////////////////////////////////////////////////////////////////////////
				/** ************标题描述---内容更新样式************ */
				
				sheet.addCell(new Label(0, 0, title, wc));
				// 标题 合并列
				//sheet.setColumnView(0, new String(content).length());
				//sheet.mergeCells(0, 1, rowLenght - 1, 1);
				
				
				// 列表标题----用于标题居中
				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
				WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
				wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_center.setWrap(true); // 文字是否换行
				
				Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
				workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
				wcf_center.setBackground(Colour.ORANGE);
				//正文样式
				WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				WritableCellFormat contentType = new WritableCellFormat(normalFont);
				contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				contentType.setWrap(true); // 文字是否换行
				//超链接样式
				WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
				WritableCellFormat linkType = new WritableCellFormat(linkFont);
				linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				linkType.setWrap(true); // 文字是否换行
				
				for (int y = 0; y < shList.size(); y++) {
					Object[] obj = (Object[])shList.get(y);
					if(y==0){
						for (int z = 0; z < obj.length; z++) {
							sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
						}
					}else{
						for (int z = 0; z < obj.length; z++) {
//							if(obj.length==11 && z==10){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheet.addCell(formula);  
//							}else{
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(obj[z]);
								addCell(request);
//							}
						}
					}
				}
			}
			

			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * @Description: 大数据导出excel
	 * @author cuichx --- 2016-5-28下午4:12:39     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public final static String baiduBigDataExcel(String fileName,List<Map<String, Object>> list,String searchDate) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File("D:\\123\\"+fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			 
			
			for(int i=0;i<list.size();i++){
				
				Map<String, Object> map=list.get(i);
				List<Object[]> shList=(List<Object[]>) map.get("list");
				String title=(String) list.get(i).get("title");
				
				int rowLenght=shList.get(0).length;
				
				WritableSheet sheet = workbook.createSheet(title+DateUtils.formatShortDate(new Date()), i); //sheet标签
				/** **********设置纵横打印（默认为纵打）、打印纸***************** */
				jxl.SheetSettings sheetset = sheet.getSettings();
				sheetset.setProtected(false);
				
				/** ************设置单元格字体************** */
//				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
				WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				// 用于正文居左
				WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
				wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_left.setWrap(true); // 文字是否换行
				sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
				sheet.setColumnView(0, 17);//列宽
				sheet.setRowView(0, 540);//行高
				sheet.setRowView(1, 555);
				
				if(rowLenght==5){
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 30);
					sheet.setColumnView(2, 20);
					sheet.setColumnView(3, 20);
					
					sheet.setColumnView(4, 20);
					sheet.setColumnView(5, 20);
					sheet.setColumnView(6, 30);
					sheet.setColumnView(7, 30);
				}else{
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 30);
					sheet.setColumnView(2, 30);
					sheet.setColumnView(3, 30);
					sheet.setColumnView(4, 30);
					sheet.setColumnView(5, 20);
					sheet.setColumnView(6, 30);
					sheet.setColumnView(7, 30);
					sheet.setColumnView(8, 30);
					sheet.setColumnView(9, 30);
					sheet.setColumnView(10, 30);
				}

				for (int x = 2; x < shList.size()+2; x++) {
					sheet.setRowView(x, 420);
				}
//				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//				WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
				
				/** ************标题************ */
				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
				WritableCellFormat wc = new WritableCellFormat(titleFont);
				wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wc.setWrap(true); // 文字是否换行
				// 标题颜色
				Color color = Color.decode("#00B150");
				workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
				wc.setBackground(Colour.BRIGHT_GREEN);
				
				sheet.addCell(new Label(0, 0, title, wc));
				///////////////////////////////////////////////////////////////////////////////////////////////
				/** ************标题描述---内容更新样式************ */
				
				sheet.addCell(new Label(0, 0, title, wc));
				// 标题 合并列
				//sheet.setColumnView(0, new String(content).length());
				//sheet.mergeCells(0, 1, rowLenght - 1, 1);
				
				
				// 列表标题----用于标题居中
				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
				WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
				wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_center.setWrap(true); // 文字是否换行
				
				Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
				workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
				wcf_center.setBackground(Colour.ORANGE);
				//正文样式
				WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				WritableCellFormat contentType = new WritableCellFormat(normalFont);
				contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				contentType.setWrap(true); // 文字是否换行
				//超链接样式
				WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
				WritableCellFormat linkType = new WritableCellFormat(linkFont);
				linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				linkType.setWrap(true); // 文字是否换行
				
				for (int y = 0; y < shList.size(); y++) {
					Object[] obj = (Object[])shList.get(y);
					if(y==0){
						for (int z = 0; z < obj.length; z++) {
							sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
						}
					}else{
						for (int z = 0; z < obj.length; z++) {
//							if(obj.length==5 && z==1){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheet.addCell(formula);  
//							}else{
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(obj[z]);
								addCell(request);
//							}
						}
					}
				}
			}
			

			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	public static void addCell(ExcelAddCellRequest request){
		try {
//			int type=4;// 1百分比  2整数  3小数  4文本
			
			 WritableSheet sheet=request.getSheet();
			 WritableFont normalFont=request.getNormalFont();
			 int z=request.getZ();
			 int y=request.getY();
			 int yNum=request.getyNum();
			 String value="";
			 if("".equals(request.getValue()) || null==request.getValue()){
				 value="";
			 }else{
				 value=String.valueOf(request.getValue());
			 }
			
			if(isDecimal("^[0-9]+(\\.[0-9]+)?%$",value) == true){
				//百分比
				value=value.substring(0, value.length()-1);
				WritableCellFormat formatType = new WritableCellFormat(normalFont,NumberFormats.PERCENT_FLOAT);
				formatType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				formatType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				formatType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				formatType.setWrap(true); // 文字是否换行
				sheet.addCell(new Number(z, yNum+y, Double.valueOf(value)/100 , formatType));
			}else if(isDecimal("^[0-9]+$",value) == true){
				//整数
				WritableCellFormat formatType = new WritableCellFormat(normalFont,NumberFormats.INTEGER);
				formatType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				formatType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				formatType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				formatType.setWrap(true); // 文字是否换行
				sheet.addCell(new Number(z, yNum+y, Double.valueOf(value) , formatType));
			}else if(isDecimal("^[0-9]+\\.[0-9]+$",value) == true){
				//小数
				WritableCellFormat formatType = new WritableCellFormat(normalFont,NumberFormats.FLOAT);
				formatType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				formatType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				formatType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				formatType.setWrap(true); // 文字是否换行
				sheet.addCell(new Number(z, yNum+y, Double.valueOf(value) , formatType));
			}else{
				//文本
				WritableCellFormat contentType = new WritableCellFormat(normalFont);
				contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				contentType.setWrap(true); // 文字是否换行
				sheet.addCell(new Label(z, yNum+y, value, contentType));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
//		String s = "100%";  
//		Pattern pat = Pattern.compile(regEx);  
//		Matcher mat = pat.matcher(s);  
//		boolean rs = mat.find(); 
		
	}
	public static boolean isDecimal(String regEx,String str){  
		  return Pattern.compile(regEx).matcher(str).matches();  
	}
	/**
	 * @Description: 大数据导出excel
	 * @author cuichx --- 2016-5-28下午4:12:39     
	 * @param fileName
	 * @param title
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public final static String bigDataExcel2(String fileName,List<Map<String, Object>> list,String searchDate) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File("D:\\123\\"+fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			 
			
			for(int i=0;i<list.size();i++){
				
				Map<String, Object> map=list.get(i);
				List<Object[]> shList=(List<Object[]>) map.get("list");
				String title=(String) list.get(i).get("title");
				
				int rowLenght=shList.get(0).length;
				
				WritableSheet sheet = workbook.createSheet(title+DateUtils.formatShortDate(new Date()), i); //sheet标签
				/** **********设置纵横打印（默认为纵打）、打印纸***************** */
				jxl.SheetSettings sheetset = sheet.getSettings();
				sheetset.setProtected(false);
				
				/** ************设置单元格字体************** */
//				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
				WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				// 用于正文居左
				WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
				wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_left.setWrap(true); // 文字是否换行
				sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
				sheet.setColumnView(0, 17);//列宽
				sheet.setRowView(0, 540);//行高
				sheet.setRowView(1, 555);
				
				if(rowLenght==8){
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 20);
					sheet.setColumnView(2, 20);
					sheet.setColumnView(3, 20);
					
					sheet.setColumnView(4, 20);
					sheet.setColumnView(5, 20);
					sheet.setColumnView(6, 30);
					sheet.setColumnView(7, 30);
//					sheet.setColumnView(8, 30);
				}else{
					sheet.setColumnView(0, 30);
					sheet.setColumnView(1, 20);
					sheet.setColumnView(2, 30);
					sheet.setColumnView(3, 30);
					sheet.setColumnView(4, 30);
					sheet.setColumnView(5, 20);
					sheet.setColumnView(6, 30);
					sheet.setColumnView(7, 30);
					sheet.setColumnView(8, 30);
					sheet.setColumnView(9, 30);
					sheet.setColumnView(10, 30);
					sheet.setColumnView(11, 30);
					sheet.setColumnView(12, 30);
					sheet.setColumnView(13, 30);
					sheet.setColumnView(14, 30);
				}

				for (int x = 2; x < shList.size()+2; x++) {
					sheet.setRowView(x, 420);
				}
//				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//				WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
				
				/** ************标题************ */
				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
				WritableCellFormat wc = new WritableCellFormat(titleFont);
				wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wc.setWrap(true); // 文字是否换行
				// 标题颜色
				Color color = Color.decode("#00B150");
				workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
				wc.setBackground(Colour.BRIGHT_GREEN);
				
				sheet.addCell(new Label(0, 0, title, wc));
				///////////////////////////////////////////////////////////////////////////////////////////////
				/** ************标题描述---内容更新样式************ */
				
				sheet.addCell(new Label(0, 0, title, wc));
				// 标题 合并列
				//sheet.setColumnView(0, new String(content).length());
				//sheet.mergeCells(0, 1, rowLenght - 1, 1);
				
				
				// 列表标题----用于标题居中
				WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
				WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
				wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
				wcf_center.setWrap(true); // 文字是否换行
				
				Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
				workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
				wcf_center.setBackground(Colour.ORANGE);
				//正文样式
				WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
				WritableCellFormat contentType = new WritableCellFormat(normalFont);
				contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				contentType.setWrap(true); // 文字是否换行
				//超链接样式
				WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
				WritableCellFormat linkType = new WritableCellFormat(linkFont);
				linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
				linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
				linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
				linkType.setWrap(true); // 文字是否换行
				
				for (int y = 0; y < shList.size(); y++) {
					Object[] obj = (Object[])shList.get(y);
					if(y==0){
						for (int z = 0; z < obj.length; z++) {
							sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
						}
					}else{
						for (int z = 0; z < obj.length; z++) {
//							if(obj.length==11 && z==10){
//								String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//								Formula formula = new Formula(z, 1+y, formu,linkType);  
//								sheet.addCell(formula);  
//							}else{
								ExcelAddCellRequest request =new ExcelAddCellRequest();
								request.setSheet(sheet);
								request.setNormalFont(normalFont);
								request.setZ(z);
								request.setY(y);
								request.setyNum(1);
								request.setValue(obj[z]);
								addCell(request);
//								sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//							}
						}
					}
				}
			}
			

			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * @Description: 大数据分析-----站点统计数据/日常监测-网站连通率
	 * @author cuichx --- 2016-5-29下午2:23:07
	 * @param fileName
	 * @param title
	 * @param list
	 * @return
	 */
	public final static String bigDataSiteExcel(String fileName, String title,
			ArrayList<Object[]> list) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = list.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
//			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 30);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 30);
			sheet.setColumnView(6, 30);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 30);
			sheet.setColumnView(10, 30);
			for (int x = 2; x < list.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < list.size(); y++) {
				Object[] obj = (Object[])list.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==10){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * @Description: 大数据分析-----站点统计数据
	 * @author cuichx --- 2016-5-29下午2:23:07     
	 * @param fileName
	 * @param title
	 * @param list
	 * @return
	 */
	public final static String baiduBigDataSiteExcel(String fileName, String title,
			ArrayList<Object[]> list) {
		String result = "系统提示：Excel文件导出成功！";
		//以下内容输出到Excel
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = list.get(0).length;
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */
			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签
			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
			
			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
//			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 540);//行高
			sheet.setRowView(1, 555);
			
			sheet.setColumnView(0, 30);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 20);
			sheet.setColumnView(3, 20);
			
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 30);
			sheet.setColumnView(6, 30);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 30);
			sheet.setColumnView(10, 30);
			for (int x = 2; x < list.size()+2; x++) {
				sheet.setRowView(x, 420);
			}
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行
			
			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < list.size(); y++) {
				Object[] obj = (Object[])list.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
//						if(z==1){
//							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
//							Formula formula = new Formula(z, 1+y, formu,linkType);  
//							sheet.addCell(formula);  
//						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
//						}
					}
				
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();
			
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * @Description: 联系人目录（导出）
	 * @author Nora --- 2015-12-8下午14:32:47     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String contractExcel(String fileName,String title, String sheetTitle,
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(sheetTitle, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			
			
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 25);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 25);
			sheet.setColumnView(6, 25);
			sheet.setColumnView(7, 25);
			sheet.setColumnView(8, 25);
			sheet.setColumnView(9, 25);
			
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
			for (int x = 2; x < listContent.size()+2;) {				
				//合并第一列第一行到第六列第一行的所有单元格
				//sheet.mergeCells(0,0,5,0); 
				//合并第一列 相邻两个数的行
				sheet.mergeCells(0,x,0,x+1); 
				//合并第二列 相邻两个数的行
				sheet.mergeCells(1,x,1,x+1);
				sheet.mergeCells(2,x,2,x+1);
				sheet.mergeCells(3,x,3,x+1);
				x=x+2;
			}

			
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			//sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			
			//超链接样式
//			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
//			WritableCellFormat linkType = new WritableCellFormat(linkFont);
//			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
//			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
//			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
//			linkType.setWrap(true); // 文字是否换行
			
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						ExcelAddCellRequest request =new ExcelAddCellRequest();
						request.setSheet(sheet);
						request.setNormalFont(normalFont);
						request.setZ(z);
						request.setY(y);
						request.setyNum(1);
						request.setValue(obj[z]);
						addCell(request);
//						sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @Description: 填报单位：搜索引擎导出
	 * @author sunjiang --- 2015年11月18日下午8:24:04     
	 * @param fileName
	 * @param title
	 * @param listContent
	 * @return
	 */
	public final static String monitorIncludeExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 30);
			sheet.setColumnView(2, 54);
			sheet.setColumnView(3, 56);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			
//			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//			WritableFont contentUpdateFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED); 
			
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式			
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @Description: 01网站连通性-02业务系统连通性监测结果（导出）,01网站连通性-03关键栏目连通性监测结果（导出）
	 * @author sunjiang --- 2015年11月17日下午7:28:21     
	 * @param fileName  文件名
	 * @param title 标题
	 * @param listContent 正文内容
	 * @return
	 */
	public final static String accountBindInfoExcel(String fileName,String title, 
			List<Object[]> listContent) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			// 定义输出流，以便打开保存对话框_______________________end
			
			int rowLenght = listContent.get(0).length;
			
			/** **********创建工作簿************ */
			// 定义输出流，以便打开保存对话框______________________begin
//			File file = new File(fileName);
//			WritableWorkbook workbook = Workbook.createWorkbook(file);
			// 定义输出流，以便打开保存对话框_______________________end
			/** **********创建工作表************ */

			WritableSheet sheet = workbook.createSheet(title, 0); //sheet标签

			/** **********设置纵横打印（默认为纵打）、打印纸***************** */
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);

			/** ************设置单元格字体************** */
//			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);
			WritableFont NormalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			// 用于正文居左
			WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
			wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_left.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_left.setWrap(true); // 文字是否换行
		
			
			sheet.mergeCells(0, 0, rowLenght - 1, 0);// 标题 合并列
			sheet.setColumnView(0, 17);//列宽
			sheet.setRowView(0, 550);//行高
			sheet.setRowView(1, 550);
			
			sheet.setColumnView(0, 8);
			sheet.setColumnView(1, 30);
			sheet.setColumnView(2, 40);
			sheet.setColumnView(3, 30);
			sheet.setColumnView(4, 30);
			sheet.setColumnView(5, 30);
			sheet.setColumnView(6, 30);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 30);
			sheet.setColumnView(10, 30);
			sheet.setColumnView(11, 30);
			sheet.setColumnView(12, 30);
			sheet.setColumnView(13, 30);
			
			for (int x = 2; x < listContent.size()+2; x++) {
				sheet.setRowView(x, 410);
			}
			/** ************标题************ */
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 14, WritableFont.BOLD);//设置单元格字体
			WritableCellFormat wc = new WritableCellFormat(titleFont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wc.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wc.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wc.setWrap(true); // 文字是否换行
			// 标题颜色
			Color color = Color.decode("#00B150");
			workbook.setColourRGB(Colour.BRIGHT_GREEN, color.getRed(),color.getGreen(), color.getBlue());
			wc.setBackground(Colour.BRIGHT_GREEN);
			
			sheet.addCell(new Label(0, 0, title, wc));
			
			///////////////////////////////////////////////////////////////////////////////////////////////
			/** ************标题描述---内容更新样式************ */
			
			sheet.addCell(new Label(0, 0, title, wc));
			// 标题 合并列
			//sheet.setColumnView(0, new String(content).length());
			//sheet.mergeCells(0, 1, rowLenght - 1, 1);
			
			
			// 列表标题----用于标题居中
			WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
			WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_center.setWrap(true); // 文字是否换行

			Color wcf_center_color = Color.decode("#93D150");// 自定义颜色
			workbook.setColourRGB(Colour.ORANGE, wcf_center_color.getRed(),wcf_center_color.getGreen(), wcf_center_color.getBlue());
			wcf_center.setBackground(Colour.ORANGE);
			//正文样式
			WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), 10);
			WritableCellFormat contentType = new WritableCellFormat(normalFont);
			contentType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			contentType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			contentType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			contentType.setWrap(true); // 文字是否换行
			//超链接样式
			WritableFont linkFont = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false,UnderlineStyle.SINGLE,Colour.BLUE2); 
			WritableCellFormat linkType = new WritableCellFormat(linkFont);
			linkType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			linkType.setAlignment(Alignment.CENTRE); // 文字水平对齐
			linkType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			linkType.setWrap(true); // 文字是否换行
			
			for (int y = 0; y < listContent.size(); y++) {
				Object[] obj = (Object[])listContent.get(y);
				if(y==0){
					for (int z = 0; z < obj.length; z++) {
						sheet.addCell(new Label(z, 1+y, obj[z].toString(), wcf_center));
					}
				}else{
					for (int z = 0; z < obj.length; z++) {
						if(z==8){
							String formu = "HYPERLINK(\""+obj[z]+"\",\""+obj[z]+"\")";  
							Formula formula = new Formula(8, 1+y, formu,linkType);  
							sheet.addCell(formula);  
						}else{
							ExcelAddCellRequest request =new ExcelAddCellRequest();
							request.setSheet(sheet);
							request.setNormalFont(normalFont);
							request.setZ(z);
							request.setY(y);
							request.setyNum(1);
							request.setValue(obj[z]);
							addCell(request);
//							sheet.addCell(new Label(z, 1+y, obj[z].toString(), contentType));
						}
					}
				}
			}
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			workbook.write();
			/** *********关闭文件************* */
			workbook.close();

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			logger.info(result);
			e.printStackTrace();
		}
		return result;
	}
}