package com.ucap.cloud_web.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.publics.util.utils.StringUtils;

import sun.misc.BASE64Decoder;



/**
 * 文件工具类 CommonUtils
 * 
 * @author LXY
 * @version 1.0
 */
public class CommonUtils {

	/**
	 * 从实体类中url 非 http开头的添加上http://
	 */
	public static String setHttpUrl(String url) {
		if(StringUtils.isNotEmpty(url)){
			url = url.trim();
			if(!url.startsWith("http")){
				url = "http://" + url;
			}else if("".equals(url)){
				url="";
			}
		}
		return url;
	}
	/**
	 * @Title: Wrap 
	 * @Description: 第 len 个字符换行显示
	 * @author: masl@ucap.com.cn 2016年9月20日下午3:15:04
	 * @param @param str ：要换行显示的字符串
	 * @param @param len : 长度
	 * @param @param wrapFeeds ： 换行符号（如：<br/> 、 \n）
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public static String Wrap(String str, int len, String wrapFeeds) {
		if (str == null) {
			return "";
		}
		String chinese = "[\u0391-\uFFE5]";
		int valueLength = 0;
		StringBuilder newStr = new StringBuilder();
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < str.length(); i++) {
			/* 获取一个字符 */
			String temp = str.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
			newStr.append(temp);
			if (valueLength >= len) {
				valueLength = 0;
				newStr.append("<br/>");
			}
		}
		return newStr.toString();
	}
	/**
	 * 获取不连通比例（没有%号），保留2位小数点（平均值）
	 * 
	 * @param num
	 *            数量
	 * @param totalNum
	 *            总数
	 * @return
	 */
	public static String getAvgProportion(double sumPoportion, int totalNum) {

		if (sumPoportion == 0 || totalNum == 0) {
			return "0";
		}

		double proportion = sumPoportion / totalNum;

		if (proportion <= 0) {
			return "0";
		}

		if (proportion >= 100) {
			return "100";
		}

		return StringUtils.formatDouble(2, proportion);
	}
	/**
	 * 获取不连通比例（没有%号），保留2位小数点（平均值）
	 * 
	 * @param num
	 *            数量
	 * @return
	 */
	public static String getAvgProportion(double sumPoportion) {

		if (sumPoportion == 0 ) {
			return "0";
		}

		double proportion = sumPoportion ;

		if (proportion <= 0) {
			return "0";
		}

		if (proportion >= 100) {
			return "100";
		}

		return StringUtils.formatDouble(2, proportion);
	}
	
	/**base64字符串转化成图片  
	 * 
	 * @param imgStr base64 编码
	 * @param path 存储路径
	 * @return 名字
	 */
    public static String GenerateImage(String imgStr,String path){   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null){//图像数据为空  
        	return "";  
        } 
        BASE64Decoder decoder = new BASE64Decoder();  
        try{  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i){  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
          //重构上传图片的名称 
    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    		String newImgName = df.format(new Date()) + "_"
    		                + new Random().nextInt(1000) + ".jpg";
            //生成jpeg图片  
            String imgFilePath = path+newImgName;//"d://222.jpg";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return newImgName;  
        }catch (Exception e){  
            return "";  
        }  
    } 
    /**
     * 
     * @描述: 满10正常返回，不满10 返回 0X 
     * @作者:linhb@ucap.com.cn
     * @时间:2017年1月23日下午1:56:00 
     * @param i
     * @return
     */
    public static String isTen(int i){  
    	String str="";
        try{  
        	if(i<10){
        		str = "0"+String.valueOf(i);
			}else{
				str=String.valueOf(i);
			}
        }catch (Exception e){  
        	e.printStackTrace();
        }  
        return str;  
    } 
}
