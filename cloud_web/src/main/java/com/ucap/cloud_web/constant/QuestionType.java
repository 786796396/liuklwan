package com.ucap.cloud_web.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 问题代码类型
 * 
 * @author: Nora
 */
public enum QuestionType {
	
	Q1("-1", "未知错误"),
	Q301("301", "请求的网页已永久移动到新位置"),
	Q302("302", "请求的网页暂时移动到新位置"),
	Q400("400","（错误请求） 服务器不理解请求的语法"), 
	
	Q401("401", "未授权"), 
	Q402("402", "要求付费"), 
	Q403("403", "（禁止） 服务器拒绝请求"), 
	Q404("404", "请求的网页不存在"), 
	
	Q405("405", "不允许的方法"), 
	Q406("406", "不被采纳"), 
	Q407("407", "要求代理授权"), 
	Q408("408", "链接超时"),

	Q409("409", "冲突"), 
	Q410("410", "过期的"), 
	Q411("411", "要求的长度"), 
	Q412("412", "前提不成立"),

	Q413("413", "请求实例太大"), 
	Q414("414", "请求URI太大"), 
	Q415("415", "不支持的媒体类型"), 
	Q416("416", "无法满足的请求范围"),
	Q417("417", "失败的预期"),
	
	Q500("500", "（服务器内部错误） 服务器遇到错误，无法完成请求"), 
	Q501("501", "未被使用"), 
	Q502("502", " 网关错误"), 
	Q503("503", "服务不可用"), 
	Q504("504", "网关超时"), 
	Q505("505", "HTTP版本未被支持"), 
	Q521("521", "不接受邮件"), 
	
	CORRECT("1000","网站出现严重错别字!"),
	WEESK2("2","2周内首页栏目未更新！"),
	column_14("14","2周内动态、要闻未更新！"),
	column_180("180","6个月内通知公告、政策文件未更新！"),
	column_365("365","1年内人事、规划计划未更新！");

	private String code;
	private String name;

	private QuestionType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getNameByCode(String code) {
		for (QuestionType c : QuestionType.values()) {
			try {
				if (StringUtils.equals(c.getCode(), code)) {
					return c.name;
				}
			} catch (Exception e) {
				return "";
			}
			
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}
