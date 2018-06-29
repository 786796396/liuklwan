package common.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对抓取到特殊的html文章中，包含unicode的进行转码处理为中文文字
 * @author lpf
 *
 */
public class HtmlUtil {

    private static Logger logger = LoggerFactory.getLogger(HtmlUtil.class);
    // 定义script的正则表达式
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    // 定义style的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

	public static String convertHtmlCode(String line) {
		StringBuffer sb = new StringBuffer();
		int start = 0;
		boolean flag = false;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '&' && (i + 1) < line.length() && line.charAt(i + 1) == '#') {
				flag = true;
				start = i;
			}
			if (flag) {
				if (line.charAt(i) == ';') {
					String tmp = line.substring(start + 2, i);
					try {
						int code = Integer.parseInt(tmp);
						sb.append((char) code);
					} catch (NumberFormatException e) {
					    logger.error("对于特殊文章转码处理失败："+e.toString());
					}
					flag = false;
				}
			} else {
				sb.append(line.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 将html中的实体转义为相应的符号
	 * @param html
	 * @return
	 */
	public static String unescape(String html) {
		html = html.replaceAll("&amp;",  "&");
		html = html.replaceAll("&quot;", "\"");
		html = html.replaceAll("&apos;", "'");
		html = html.replaceAll("&lt;" ,  "<");
		html = html.replaceAll("&gt;",   ">" );
		html = html.replaceAll("&nbsp;", " " );
		return html;
	}

	/**
	 * 将html中的特殊符号转为相应的实体
	 * @param html
	 * @return
	 */
	public static String escape(String html) {
		html = html.replaceAll("&" , "&amp;" );
		html = html.replaceAll("\"", "&quot;");
		html = html.replaceAll("'" , "&apos;");
		html = html.replaceAll("<" , "&lt;"  );
		html = html.replaceAll(">" , "&gt;"  );
		return html;
	}

	/**
	 * 格式化html代码，只保留&lt;br&gt;标签，&lt;p&gt;则替换为&lt;br&gt;
	 * @param html
	 * @return
	 */
	public static String htmlWithParagraph(final String html) {
		if(null == html) return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<html.length(); ) {
			char c = html.charAt(i);
			if(c == '<') {
				if(i+3<=html.length() && html.substring(i, i+3).equalsIgnoreCase("<br")) {
					sb.append("<br>");
					int index = html.indexOf('>', i+1);
					if(index > i+1) i = index+1;
					else i += 4;
					continue;
				} else if(i+3<html.length() && html.substring(i, i+3).equalsIgnoreCase("</p")) {
					sb.append("<br>");
					i += 4;
					continue;
				} else if(i+5<html.length() && html.substring(i, i+5).equalsIgnoreCase("</div")) {
					sb.append("<br>");
					i += 6;
					continue;
				} else {
					int firstGreat = html.indexOf('>', i+1);
					if(firstGreat > i) {
						i = firstGreat+1;
						continue;
					}
				}
			}
			sb.append(c);
			i++;
		}
		return sb.toString();

	}

	/**
	 * 过滤html源代码，只保留tags指定的标签
	 * @param html
	 * @param oldTag
     * @param newTag
	 * @return
	 */
	public static String htmlReplaceTag(final String html, String oldTag, String newTag) {
		if(null == html) return "";
		String content = html;
		content = content.replaceAll("<" + oldTag + ">", "<" + newTag + ">");
		content = content.replaceAll("</" + oldTag + ">", "</" + newTag + ">");
		return content;
	}

	/**
	 * 格式化html代码，只保留br、font、b标签，p、div则替换为br标签.<br/>
	 * 这个给58网站很需要
	 * @param html
	 * @return
	 */
	public static String htmlWithParagraph2(final String html) {
		if(null == html) return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<html.length(); ) {
			char c = html.charAt(i);
			if(c == '<') {
				if(i+3<=html.length() && html.substring(i, i+3).equalsIgnoreCase("<br")) {

					sb.append("<br>");
					int index = html.indexOf('>', i+1);
					if(index > i+1) i = index+1;
					else i += 4;
					continue;
				} else if(i+3<=html.length() && html.substring(i, i+3).equalsIgnoreCase("<b>")) {
					sb.append("<b>");
					int index = html.indexOf('>', i+1);
					if(index > i+1) i = index+1;
					else i += 3;
					continue;
				} else if(i+3<html.length() && html.substring(i, i+3).equalsIgnoreCase("</b")) {
					sb.append("</b>");
					int index = html.indexOf('>', i+1);
					if(index > i+1) i = index+1;
					else i += 4;
					continue;
				} else if(i+5<=html.length() && html.substring(i, i+5).equalsIgnoreCase("<font")) {
					int index = html.indexOf('>', i+1);
					if(index > i) {
						String font = html.substring(i, index+1);
						sb.append(font);
						i = index + 1;
					} else i++;
					continue;
				} else if(i+6<html.length() && html.substring(i, i+6).equalsIgnoreCase("</font")) {
					sb.append("</font>");
					int index = html.indexOf('>', i+1);
					if(index > i+1) i = index+1;
					else i += 7;
					continue;
				} else if(i+3<html.length() && html.substring(i, i+3).equalsIgnoreCase("</p")) {
					sb.append("<br>");
					i += 4;
					continue;
				} else if(i+5<html.length() && html.substring(i, i+5).equalsIgnoreCase("</div")) {
					sb.append("<br>");
					i += 6;
					continue;
				} else {
					int firstGreat = html.indexOf('>', i+1);
					if(firstGreat > i) {
						i = firstGreat+1;
						continue;
					}
				}
			}
			sb.append(c);
			i++;
		}
		return sb.toString();

	}

	/**
	 * 格式化html代码，将&lt;br&gt;、&lt;p/&gt;、&lt;div/&gt;则替换为\n;
	 * @param html
	 * @return
	 */
	public static String html2TextParagraph(String html) {
		if(null == html) return "";
		html = html.replace("&nbsp;", "");
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<html.length(); ) {
			char c = html.charAt(i);
			if(c == '<') {
				if(i+4<=html.length() && html.substring(i, i+4).equalsIgnoreCase("<br>")) {
					sb.append("\n");
					i += 4;
					continue;
				} else if(i+5<=html.length() && html.substring(i, i+5).equalsIgnoreCase("<br/>")) {
					sb.append("\n");
					i += 5;
					continue;
				} else if(i+4<=html.length() && html.substring(i, i+4).equalsIgnoreCase("</p>")) {
					sb.append("\n");
					i += 4;
					continue;
				} else if(i+6<=html.length() && html.substring(i, i+6).equalsIgnoreCase("</div>")) {
					sb.append("\n");
					i += 5;
					continue;
				} else {
					int firstGreat = html.indexOf('>', i+1);
					if(firstGreat > i) {
						i = firstGreat+1;
						continue;
					}
				}
			}
			sb.append(c);
			i++;
		}
		return sb.toString();

	}

	/**
	 * 将纯文本中的回车换行变为br
	 * @param text
	 * @return
	 */
	public static String textToHtmlWithBr(final String text) {
		if(null == text) return "";
		String input = text.trim();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length(); ) {
			char c = input.charAt(i);
			if(c == '\n') {
				int pointer = i+1;
				while(pointer<input.length()) {
					char now = input.charAt(pointer);
					if(now != '\r' && now != '\n') {
						i = pointer;
						break;
					}

					pointer++;
				}
				sb.append("<br>");
				i = pointer;
			} else {
				sb.append(c);
				i++;
			}
		}
		return sb.toString();
	}

	/**
	 *
	 * 将文本转化为带段落格式的html文本，段落用&lt;P/&gt;扩起来，首行空两格。<br>
	 * 一般用于将从&lt;textarea/&gt;输入到数据库的文本显示到网页上，保留段落标记。
	 * @param text
	 * @return
	 */
	public static String textToHtmlParagraph(final String text) {
		if(null == text) return "";
		String input = text.trim();
		StringBuilder sb = new StringBuilder();
		sb.append("<p style=\"text-indent :2em; \">");
		for(int i=0; i<input.length(); ) {
			char c = input.charAt(i);
			if(c == '\n') {
				int pointer = i+1;
				while(pointer<input.length()) {
					char now = input.charAt(pointer);
					if(now != '\r' && now != '\n') {
						i = pointer;
						break;
					}

					pointer++;
				}
				sb.append("</p>");
				sb.append("<p style=\"text-indent :2em; \">");
				i = pointer;
			} else {
				sb.append(c);
				i++;
			}
		}
		sb.append("</p>");

//		String html = sb.toString();
//		return escape(html);
		return sb.toString();
	}

	/**
	 * 输入的是html源代码，格式化后，仅仅包含纯文本
	 * @param input
	 * @return
	 */
	public static String html2Text(final String input) {
		String html = unescape(input);
		StringBuilder sb = new StringBuilder();
		boolean inTag = false;
		for(int i=0; i<html.length(); i++) {
			if(inTag) {
				if(html.charAt(i) == '>'){
					inTag = false;
				}
			} else {
				if(html.charAt(i) == '<') inTag = true;
				else sb.append(html.charAt(i));
			}
		}

		return sb.toString();
	}

    public static String doc2Text(final Document inputDoc) {
        Document doc = inputDoc.clone();
        Elements scripts = doc.select("script");
        if(scripts != null) {
            for (Element script : scripts) script.remove();
        }

        Elements metas = doc.select("meta");
        if(metas != null) {
            for (Element meta : metas) meta.remove();
        }

        Elements styles = doc.select("style");
        if(styles != null) {
            for (Element style : styles) style.remove();
        }

        Elements textareas = doc.select("textarea");
        if(textareas != null) {
            for (Element textarea : textareas) textarea.remove();
        }
        return doc.text();
    }


    /**
	 * 将字符串中的html标签 &gt,&nbsp等替换成""
	 * 用于后台目标网站列表描述 和绑定端口列表描述的显示
	 */
	public static String HtmlToStr(String content){
		if(null == content) return "";
		content = content.replaceAll("&lt;", "");
		content = content.replaceAll("&gt;", "");
		content = content.replaceAll("&nbsp;", "");
		content = content.replaceAll("<p.*?>","");
		content = content.replaceAll("<brs*/?>","");
		//去掉其它的<>之间的东西 　
		content = content.replaceAll("<.*?>","");
		return content;
	}

    /**
     * @param htmlStr
     * @return
     *  删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        if(htmlStr == null){return null;}
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 从html中获取去掉script，style标签，html标签，空格回车标签 的纯文本
     * @param htmlStr
     * @return
     */
    public static String getTextFromHtml(String htmlStr){
        htmlStr = html2Text(htmlStr);
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        // htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);
        return htmlStr;
    }

    public static void main(String[] args) {
        String str = "<div style='text-align:center;'> 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></div>";
        System.out.println(getTextFromHtml(str));
    }
}
