package common.util;

import org.apache.commons.lang3.CharUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	
	/**
	 * 生成和不大于max的3个随机数
	 * @author lihui
	 * @param max
	 * @return
	 */
	public static int[] getThreeInt(long max){
		if(max < 6) return new int[]{0,0,0};
		int maxInt = (int) (max/3);
		int[] arrInt = new int[3];
		Random rd = new Random();
		arrInt[0] = rd.nextInt(maxInt);
		arrInt[1] = rd.nextInt(maxInt);
		arrInt[2] = rd.nextInt(maxInt);
		return arrInt;
	}

	/**
	 * 生成min到max区间的随机数
	 * @author lihui
	 * @param min
	 * @param max
	 * @return
	 */
	public static long getRandomRegion(int min,int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
	}
	
	/**
	 * 获得8位随机数
	 * @author lihui
	 * @return
	 */
	public static long get8Random(){
		Random random = new Random();
		long retLong = random.nextInt(100000000);
		if(retLong < 10000000) retLong += 10000000;
		return retLong;
	}
	
	/**
	 * 找最后一个空格（包括全角、半角空格）前的文字
	 * 
	 * @param nodeValue
	 * @return
	 */
	public static String getStringAfterLastBlank(String nodeValue) {
		String tmp = nodeValue.trim();
		int index = tmp.lastIndexOf(' ');
		if (index < 0) {
			index = tmp.lastIndexOf('　');
		}
		if (index > 0) {
			return tmp.substring(index + 1).trim();
		}
		return tmp.trim();
	}

	/**
	 * 找第一个空格（包括全角、半角空格）前的文字
	 * 
	 * @param nodeValue
	 * @return
	 */
	public static String getStringBeforeFirstBlank(String nodeValue) {
		String tmp = nodeValue.trim();
		int index = tmp.indexOf(' ');
		if (index < 0) {
			index = tmp.indexOf('　');
		}
		if (index < 0) {
			index = tmp.indexOf("&nbsp;");
		}
		if (index > 0) {
			return tmp.substring(0, index).trim();
		}
		return tmp.trim();
	}

	/**
	 * 找字符串中最后一个空格（包括全角、半角空格）后的文字
	 * 
	 * @param nodeValue
	 * @return
	 */
	public static String getAfterFirstBlankString(String nodeValue) {
		String tmp = nodeValue.trim();
		// System.out.println("tmp:"+tmp);
		int index = tmp.lastIndexOf(' ');
		// System.out.println("index:"+index);
		if (index < 0) {
			index = tmp.indexOf('　');
		}
		if (index < 0) {
			index = tmp.indexOf("&nbsp;");
		}
		if (index > 0) {
			return tmp.substring(index, nodeValue.length()).trim();
		}
		return tmp.trim();
	}

	/**
	 * 提取原始字符串nodeValue中prefix以后的子串
	 * 
	 * @param nodeValue
	 * @param prefix
	 * @return
	 */
	public static String stripAfter(String nodeValue, String prefix) {
		if (isEmpty(nodeValue))
			return "";

		String tmp = nodeValue.trim();
		int begin = 0;
		if (!isEmpty(prefix)) {
			int index = tmp.indexOf(prefix);
			if (index >= 0) {
				begin = index + prefix.length();
				return trim(tmp.substring(begin).trim());
			}
		}

		return trim(tmp);
	}

	/**
	 * 提取最后出现prefix字串的位置之后的字符串
	 * 
	 * @param nodeValue
	 *            原始字符串
	 * @param prefix
	 *            最后出现的子串
	 * @return
	 */
	public static String stripAfterLast(String nodeValue, String prefix) {
		if (isEmpty(nodeValue))
			return "";
		String tmp = nodeValue.trim();
		int begin = 0;
		if (!isEmpty(prefix)) {
			int index = tmp.lastIndexOf(prefix);
			if (index >= 0) {
				begin = index + prefix.length();
				return trim(tmp.substring(begin).trim());
			}
		}

		return trim(tmp);
	}

	/**
	 * 提取原始字符串nodeValue中suffix以前的子串
	 * 
	 * @param nodeValue
	 * @param suffix
	 * @return
	 */
	public static String stripBefore(String nodeValue, String suffix) {
		if (isEmpty(nodeValue))
			return "";

		String tmp = nodeValue.trim();
		int end = tmp.length();
		if (!isEmpty(suffix)) {
			int index = tmp.indexOf(suffix);
			if (index >= 0) {
				end = index;
				return trim(tmp.substring(0, end).trim());
			}
		}

		return trim(nodeValue);
	}

	/**
	 * 在字符串nodeValue中找"prefix"以后，"suffix"以前的字符串<br/>
	 * 如果prefix为空，则找end以前的字符串；如果suffix为空，则找before以后的字符串
	 * 
	 * @param nodeValue
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	public static String strip(String nodeValue, String prefix, String suffix) {
		if (isEmpty(nodeValue))
			return "";

		String tmp = nodeValue.trim();
		int begin = 0;
		int end = tmp.length();

		if (!isEmpty(prefix)) {
			int index = tmp.indexOf(prefix);
			if (index >= 0)
				begin = index + prefix.length();
			else
				return ""; // 不包含前缀
		}

		if (!isEmpty(suffix)) {
			int index = tmp.indexOf(suffix, begin);
			if (index > 0)
				end = index;
			else
				return ""; // 不包含后缀
		}

		return trim(end > begin ? tmp.substring(begin, end).trim() : tmp);
	}

	/**
	 * 查找字符串中的手机号码或电话号码.
	 * 匹配座机或手机号码的正则表达式是：((\d{3,4}[-_－—]?)?\d{7,8}([-_－—]?\d{1,7})?)|(0?1\d{10})
	 * 
	 * @param source
	 * @return
	 */
	public static String searchMobileOrTel(String source) {
		return searchAndReturn(source, "((\\d{3,4}[-_－—]?)?\\d{7,8}([-_－—]?\\d{1,7})?)|(0?1\\d{10})");
	}
	
	/**
	 * 截取字符串中的数字和单位(若首字符不是数字,则无法截取,返回null)
	 * @param str
	 * @return
	 */
	public static Map<String, String> searchNumAndUnit(String str) {
		Map<String, String> NumUnitMap = null;
		if (!CharUtils.isAsciiNumeric(str.charAt(0))) {
			return NumUnitMap;
		}
		NumUnitMap = new HashMap<>();
		int index = 0;
		for (int i = 0; i < str.length(); i++) {
			if (!CharUtils.isAsciiNumeric(str.charAt(i)) && i > 0) {
				index = i;
				NumUnitMap.put("number", str.substring(0, index));
				NumUnitMap.put("unit", str.substring(index));
				break;
			}
		}
		return NumUnitMap;
	}
	
	public static String searchNum(String source) {
		int startIndex = 0;
		for (int i = 0; i < source.length(); i++) {
			char cha = source.charAt(i);
			if (cha >= 48 && cha <= 57) {
				startIndex = i;
				break;
			}
		}
		source = source.substring(startIndex);
		if (source.indexOf(",") != -1) {
			source = source.replace(",", "");
		}
		return searchAndReturn(source, "(^[0-9,]*)\\.?([0-9]*)|(^[0-9]*)");
	}
	
	public static String searchAndReturn(String source, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		boolean b = matcher.find();
		if (b) {
			return source.substring(matcher.start(), matcher.end());
		}
		return null;
	}

	public static List<String> searchAll(String source, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		List<String> results = new ArrayList<String>();
		while (matcher.find()) {
			results.add(matcher.group());
		}
		return results;
	}

	public static String regexText(String source, String regex, int group) {
	    try {
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(source);
	        boolean b = matcher.find();
	        if (b) {
	            return matcher.group(group);
	        }
        }
        catch (Exception e) {

        }
	    return null;
	}

	public static String regexText(String source, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		boolean b = matcher.find();
		if (b) {
			return matcher.group();
		}
		return null;
	}

	public static boolean isMobile(String mobile) {
		Pattern pattern = Pattern.compile("1[3-5|8]\\d{9}");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	public static boolean isMobileOrTel(String mobile) {
		if (mobile.startsWith("13") || mobile.startsWith("15") || mobile.startsWith("18"))
			return isMobile(mobile);
		Pattern pattern = Pattern.compile("(\\d{3,4}[-_－—]?)?\\d{7,8}([-_－—]?\\d{1,7})?");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

	/**
	 * 在一个输入字符串中找数字，只返回所有的数字拼成的串。这个方法可以从中间用空格、-隔开的字符串抽取电话号码
	 * 
	 * @param input
	 * @return
	 */
	public static String stripNumber(String input) {
		if (null == input)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c >= '0' && c <= '9')
				sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 同{@link #stripNumber(String)}方法 。 多保留了400电话中的转分机
	 * 
	 * @param input
	 * @return
	 */
	public static String stripNumber2(String input) {
		if (null == input)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if ((c >= '0' && c <= '9') || c == '转')
				sb.append(c);
		}
		return sb.toString();
	}

	private static String encodeUrlParam(String value, String charset) throws UnsupportedEncodingException {
		if (value == null) {
			return "";
		}

		try {
			String decoded = URLDecoder.decode(value, charset);

			String result = "";
			for (int i = 0; i < decoded.length(); i++) {
				char ch = decoded.charAt(i);
				result += (ch == '#') ? "#" : URLEncoder.encode(String.valueOf(ch), charset);
			}

			return result;
		} catch (IllegalArgumentException e) {
			return value;
		}
	}

	public static String encodeUrl(String url, String charset) throws UnsupportedEncodingException {
		if (url == null) {
			return "";
		}

		int index = url.indexOf("?");
		if (index >= 0) {

			String result = url.substring(0, index + 1);
			String paramsPart = url.substring(index + 1);
			StringTokenizer tokenizer = new StringTokenizer(paramsPart, "&");
			while (tokenizer.hasMoreTokens()) {
				String definition = tokenizer.nextToken();
				int eqIndex = definition.indexOf("=");
				if (eqIndex >= 0) {
					String paramName = definition.substring(0, eqIndex);
					String paramValue = definition.substring(eqIndex + 1);
					result += paramName + "=" + encodeUrlParam(paramValue, charset) + "&";
				} else {
					result += encodeUrlParam(definition, charset) + "&";
				}
			}

			if (result.endsWith("&")) {
				result = result.substring(0, result.length() - 1);
			}

			return result;

		}

		return url;
	}

	/**
	 * Checks if specified link is full URL.
	 * 
	 * @param link
	 * @return True, if full URl, false otherwise.
	 */
	public static boolean isFullUrl(String link) {
		if (link == null) {
			return false;
		}
		link = link.trim().toLowerCase();
		return link.startsWith("http://") || link.startsWith("https://") || link.startsWith("file://");
	}

	/**
	 * 考虑各种链接格式，构造正确的url地址
	 * 
	 * @param pageUrl
	 *            当前页url地址
	 * @param link
	 *            链接属性
	 * @author liupf@buge.cn
	 */
	public static String fullUrl(String pageUrl, String link) {
        if (link == null)
            return "";

        if (!isFullUrl(pageUrl)) { // 保证当前页链接是以http://开始
            pageUrl = "http://" + pageUrl;
        }

        if (isFullUrl(link)) { // link本来就是绝对地址
            return link;
        } else if (link != null && link.startsWith("?")) { // link是查询参数，"?name=lpf"
            int qindex = pageUrl.indexOf('?');
            if (qindex < 0) { // pageUrl=http://www.test.com/user，返回"http://www.test.com/use?name=lpf"
                return pageUrl + link;
            } else { // pageUrl=http://www.test.com/user?name=one，返回"http://www.test.com/use?name=lpf"
                return pageUrl.substring(0, qindex) + link;
            }
        } else if (link.startsWith("//")) { // link是从根目录开始的地址
            int pageindex=pageUrl.indexOf("//");
            return pageUrl.substring(0,pageindex)+link;
        } else if(link.startsWith("/")){
            return makeUrl(pageUrl, link);
        }else if (link.startsWith("./")) { // link是从"./"开始的地址
            int qindex = pageUrl.indexOf('?');
            int lastSlashIndex = pageUrl.lastIndexOf('/');
            if(qindex>0){
                String qpage=pageUrl.substring(0,qindex);
                int lSlashIndex = qpage.lastIndexOf('/');
                if(lastSlashIndex>0) {
                    return qpage.substring(0, lSlashIndex + 1) + link.substring(1);
                }else {
                    return pageUrl.substring(0, lastSlashIndex) + link.substring(1);
                }
            }
			if (lastSlashIndex > 8) {
				return pageUrl.substring(0, lastSlashIndex) + link.substring(1);
			} else {
				return pageUrl + link.substring(1);
			}
		} else if (link.startsWith("../")) { // link是从"../"开始的地址，回退一级
			return makeAssembleUrl(pageUrl, link);
		} else {
		    if(pageUrl.contains("?")){
                int qindex = pageUrl.indexOf('?');
                String qpageUrk=pageUrl.substring(0,qindex);
                int yindex=qpageUrk.lastIndexOf('/');
                return qpageUrk.substring(0,yindex+1)+link;
            }
			int lastSlashIndex = pageUrl.lastIndexOf('/');
            int linkIndex=link.lastIndexOf('/');
			if (lastSlashIndex > 8) {
			   /* if(linkIndex!=-1) {
                    String sublink = link.substring(0, linkIndex);
                    String pageIndex = pageUrl.substring(0, lastSlashIndex);
                    if (pageIndex.endsWith(sublink)) {
                        int hh=pageIndex.indexOf(sublink);
                        String url=pageIndex.substring(0,hh);
                        return url+link;
                    }
                }*/   //注释掉对有两级相同目录的链接截取
                return pageUrl.substring(0, lastSlashIndex) + "/" + link;
			} else {
                return pageUrl + "/" + link;
			}
		}

	}

	private static String makeAssembleUrl(String base, String multiOmission) {
		String OMISSION = "../";
		String relative = multiOmission;
		while (relative.startsWith(OMISSION)) {
			base = getParentUrl(base);
			relative = relative.substring(OMISSION.length());
		}
		relative=relative.replace("./","");
        return base + relative;
	}

	private static String getParentUrl(String url) {
	    if(url.contains("?")){
	        int uindex=url.indexOf('?');
	        url=url.substring(0,uindex);
        }
		int lastSlashIndex = url.lastIndexOf('/');
		String rest = url.substring(0, lastSlashIndex);
		lastSlashIndex = rest.lastIndexOf('/');
		String domain=LinkUtil.getDomain(url);
		if(rest.substring(lastSlashIndex+1,rest.length()).equals(domain)){
		    return rest+"/";
        } else if (lastSlashIndex > 8) {
			return url.substring(0, lastSlashIndex + 1);
		} else {
			return url + "/";
		}
	}

	public static String makeUrl(String pageUrl, String path) {
		try {
                URL base = new URL(pageUrl);
                StringBuilder sb = new StringBuilder();
                sb.append(base.getProtocol());
                sb.append("://");
                sb.append(base.getHost());

                if (base.getPort() != 80 && base.getPort() > 0)
                    sb.append(":" + base.getPort());
                if (path.charAt(0) != '/')
                    sb.append('/');
                sb.append(path);
                return sb.toString();
		} catch (Exception e) {

		}

		return "";
	}

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
	 * Checks if a string is empty (ie is null or empty).
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || (str.equals("")) || (str.equals("null")) || str.equals(" ") || str.equals("　");
	}

	public static boolean notEmpty(String str) {
		return (str != null && !str.equals("") && !str.equals("null") && !str.equals(" ") && !str.equals("　"));
	}

	/**
	 * 将网页上取到的unicode编码字符串，转为java可以识别的内码。<br/>
	 * 例如：通过json传递到浏览器客户端的字符串是"\u4e0d\u9650\"，本方法转换后，
	 * 得到字符串"不限"
	 * @param s
	 * @return
	 */
	public static String hexToString(String s) {
		int i = 0;
		StringBuffer buf = new StringBuffer(1000);

		if (s == null)
			return ""; // if null we return null (to avoid any errors)

		while (i <= s.length() - 6) {
			char c = s.charAt(i);
			char next = s.charAt(i + 1);
			if (c == '\\' && (next == 'u' || next == 'U')) {
				String tmp = s.substring(i + 2, i + 6);
				try {
					int code = Integer.parseInt(tmp, 16);
					char unicode = (char) code;
					buf.append(unicode);
					i += 6;
				} catch (NumberFormatException e) {

					buf.append(c);
					i++;
				}
			} else {
				buf.append(c);
				i++;
			}

		}

		return buf.toString();
	}

	/**
	 * 将中文数字转换为罗马数字
	 * 
	 * @param s
	 * @return
	 */
	private static long toLong(String s) {
		long sum = 0;
		for (int i = 0; i < s.length(); i++) {
			int result = toInt(s.charAt(i));
			if (result >= 0 && result <= 10) {
				if (i + 1 < s.length()) {
					char next = s.charAt(i + 1);
					if (next == '千' || next == '仟')
						sum += result * 1000;
					else if (next == '百' || next == '佰')
						sum += result * 100;
					else if (next == '十' || next == '拾') {
						if (s.charAt(i) == '零')
							sum += 10;
						else
							sum += result * 10;
					} else {
						if (i - 1 > 0) {
							char prev = s.charAt(i - 1);
							if (prev == '千' || prev == '仟')
								sum += result * 100;
							else if (prev == '百' || prev == '佰')
								sum += result * 10;
							else if (prev == '十' || prev == '拾')
								sum += result;
						} else {
							sum += result;
						}
					}

				} else if (i - 1 > 0) {
					char prev = s.charAt(i - 1);
					if (prev == '千' || prev == '仟')
						sum += result * 100;
					else if (prev == '百' || prev == '佰')
						sum += result * 10;
					else
						sum += result;

				} else
					sum += result;
			}
		}
		return sum;
	}

	static char[] NUMBERS = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十' };
	static char[] NUMBERS_TW = { '零', '壹', '貳', '叁', '肆', '伍', '陸', '柒', '捌', '玖', '拾' };

	public static int toInt(char c) {
		if (c == '两')
			return 2;

		for (int i = 0; i < 11; i++) {
			if (c == NUMBERS[i] || c == NUMBERS_TW[i])
				return i;
		}
		if (c >= '0' && c <= '9')
			return c - '0';
		return -1;
	}

	/**
	 * 将一串数字变成阿拉伯数字。可以是中文（简体或繁体），也可以是字符串的阿拉伯数字。
	 * 
	 * @param old
	 * @return
	 * @throws NumberFormatException
	 */
	public static long str2Long(final String old) throws NumberFormatException {
	    String s = old.replaceAll(",", "");
		if (s.matches(".*[亿|万|千|百|十].*")) {
			int yi = s.indexOf('亿');
			long sum = 0;
			if (yi > 0) {
				sum += toLong(s.substring(0, yi)) * 10 ^ 8;
				String rest = s.substring(yi + 1);
				int wan = rest.indexOf('万');
				if (wan < 0)
					wan = rest.indexOf('萬');
				if (wan > 0) {
					sum += toLong(rest.substring(0, wan)) * 10000 * 10000;
					rest = s.substring(wan + 1);
					sum += toLong(rest);
				} else
					sum += toLong(rest);
			} else {
				int wan = s.indexOf('万');
				if (wan < 0)
					wan = s.indexOf('萬');
				if (wan > 0) {
					sum += toLong(s.substring(0, wan)) * 10000;
					if (wan + 1 == s.length() - 1) {// 如二万五, 五是5000
						sum += toInt(s.charAt(wan + 1)) * 1000;

					} else {
						String rest = s.substring(wan + 1);
						sum += toLong(rest);

					}
				} else
					sum += toLong(s);
			}
			return sum;
		} else {
			if (s.charAt(0) > 256) {
                return toLong(s);
            } else {
                return (int) Double.parseDouble(s);
            }
		}
	}

	public static String getHost(String urlString) {
		URL url;
		try {
			url = new URL(urlString);
			return url.getHost();
		} catch (MalformedURLException e) {
			return "";
		}
	}

	/**
	 * 去掉特殊无效字符，保留<128的字符，简体中文字符,全角ASCII、全角中英文标点、半宽片假名、半宽平假名、半宽韩文字母：FF00-FFEF.
	 * 参考：http://www.ipmtea.net/javascript/201009/23_292.html
	 * 
	 * @param source
	 * @return
	 */
	public static String filterSpecialChars(String source) {
		if (CommonUtil.isEmpty(source))
			return "";

		StringBuilder sb = new StringBuilder();
		int total = source.length();
		for (int i = 0; i < total; i++) {
			char c = source.charAt(i);
			if (c < 128 || (c >= '\u4E00' && c <= '\u9FA5') || (c >= '\uFF00' && c <= '\uFFFF')) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String trim(String source) {
		if (null == source)
			return "";

		int start = 0, end = source.length() - 1;
		for (int i = 0; i < source.length(); i++) {
			int code = source.charAt(i);
			if (code != ' ' && code != '　' && code != '\n' && code != '\r' && code != '\t') {
				start = i;
				break;
			}
		}

		for (int i = source.length() - 1; i >= 0; i--) {
			int code = source.charAt(i);
			if (code != ' ' && code != '　' && code != '\n' && code != '\r' && code != '\t' && code != 160) {
				end = i + 1;
				break;
			}
		}

		if (start < end) {
			return source.substring(start, end);
		} else {
			return source;
		}
	}

	/**
	 * 将房屋总价单位为元转换为万元的方法
	 * 
	 * @param nodeValue
	 * @return
	 */
	public static String price(String nodeValue) {
		if (nodeValue.trim().length() > 0) {
			return "" + Double.parseDouble(nodeValue) / 10000 + "";
		} else {
			return "";
		}
	}

	/**
	 * 拼接日期字符串，页面显示xx月xx日时可用该方法，如:08月10日
	 * 
	 * @param nodeValue
	 * @return
	 */
	public static String pubDate(String nodeValue) {
		if (nodeValue != null && nodeValue.trim().length() > 0) {
			String[] array = nodeValue.split("月");
			String year = "";
			String month = "";
			String day = "";
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			year = String.valueOf(cal.get(Calendar.YEAR));
			if (array.length > 0) {
				month = array[0];
			}
			if (array.length >= 2) {
				day = array[1].substring(0, array[1].indexOf("日"));
				;
			}
			return year + "-" + month + "-" + day;
		} else {
			return "";
		}
	}

	/**
	 * @param map
	 *            <String,float> map 排序的MAP
	 * @param sequence
	 *            sequence 排序方式 desc 、asc
	 * @throws Exception
	 * @return String
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Integer> sortMap(Map<String, Integer> map, String sequence) {
		Object[] keyArray = (Object[]) map.keySet().toArray();
		Object[] valueArray = (Object[]) map.values().toArray();
		int keyArrayLength = keyArray.length;
		String tmpKey = null;
		int tmpValue = 0;

		for (int i = 0; i < keyArrayLength; i++) {
			for (int j = 0; j < keyArrayLength - i - 1; j++) {
				int value1 = (Integer) valueArray[j];
				int value2 = (Integer) valueArray[j + 1];

				if (sequence.equals("desc")) {
					// 降序
					if (value2 > value1) {
						tmpValue = (Integer) valueArray[j + 1];
						valueArray[j + 1] = valueArray[j];
						valueArray[j] = tmpValue;

						tmpKey = (String) keyArray[j + 1];
						keyArray[j + 1] = keyArray[j];
						keyArray[j] = tmpKey;
					}
				} else if ("asc".equals(sequence)) {
					if (value2 < value1) {
						tmpValue = (Integer) valueArray[j + 1];
						valueArray[j + 1] = valueArray[j];
						valueArray[j] = tmpValue;

						tmpKey = (String) keyArray[j + 1];
						keyArray[j + 1] = keyArray[j];
						keyArray[j] = tmpKey;
					}
				} else {
					if (value2 < value1) {
						tmpValue = (Integer) valueArray[j + 1];
						valueArray[j + 1] = valueArray[j];
						valueArray[j] = tmpValue;

						tmpKey = (String) keyArray[j + 1];
						keyArray[j + 1] = keyArray[j];
						keyArray[j] = tmpKey;
					}
				}
			}
		}

		Map mapReturn = new HashMap();
		for (int i = 0; i < keyArrayLength; i++) {
			mapReturn.put(keyArray[i], valueArray[i]);
		}
		return mapReturn;

	}

	/**
	 * 判断字符串是否是A~Z中的一个
	 * 
	 * @param str
	 * @return true:是字母 false:不是字母
	 */
	public static boolean checkChar(String str) {
		if (str.length() == 1 && str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成指定位数的整数
	 * 
	 * @param bits
	 * @return
	 */
	public static long randomNumber(int bits) {
		return Math.round(Math.random() * (long) Math.pow(10, bits));
	}

	/**
	 * 根据类型，推算时间
	 * 
	 * @param type
	 *            0:天数，1:月数
	 * @param number
	 *            数量
	 * @param asc
	 *            升序
	 * @param now
	 *            当前时间
	 * @return
	 */
	public static Date getNeedDate(int type, int number, boolean asc, Date now) {
		Date fromDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		if (asc) {
			if (type == 0) {
				date = date + number + 1;
			} else if (type == 1) {
				month = month + number;
			}
		} else {
			if (type == 0) {
				date = date - number - 1;

			} else if (type == 1) {
				month = month + number;
			}
		}
		cal.set(year, month, date);
		fromDate = cal.getTime();
		return fromDate;
	}

	/**
	 * 切分空格字符串
	 * 
	 * @return
	 */
	public static String[] splitBlankStr(String source) {
		if (isEmpty(source))
			return null;
		if (source.indexOf("　") >= 0)
			source = source.replace("　", " ");
		source = trim(source);
		return source.split(" ");
	}

	/**
	 * 字符串URL转码，将带有百分号加数字的转为相应符号
	 * 
	 * @param source
	 * @return
	 */
	public static String decodeStr(String source) {
		try {
			return URLDecoder.decode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 将格式为yy的年转为普通格式yyyy的年
	 * 
	 * @param year
	 *            如：98
	 * @return 如：1998
	 */
	public static String commonYear(String year) {
		try {
			year = new SimpleDateFormat("yyyy").format(new SimpleDateFormat("yy").parse(year));
		} catch (ParseException e) {
		}
		return year;
	}

	/**
	 * 与当前年份计算减去或增加一定数量后的年份
	 * 
	 * @param number
	 *            数量
	 * @param minus
	 *            true:减法 false:加法
	 * @return
	 */
	public static String computeYear(int number, boolean minus) {
		Calendar cal = Calendar.getInstance();
		if (!minus)
			number = -number;
		cal.add(Calendar.YEAR, number);
		return new SimpleDateFormat("yyyy").format(cal.getTime());
	}

	/**
	 * 对应javascript中escape方法
	 * @param str
	 * @return
	 */
	public static String escape(String str) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(str.length() * 6);
		for (i = 0; i < str.length(); i++) {
			j = str.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 对应javascript中unescape方法
	 * @param str
	 * @return
	 */
	public static String unescape(String str) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(str.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < str.length()) {
			pos = str.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (str.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(str.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(str.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(str.substring(lastPos));
					lastPos = str.length();
				} else {
					tmp.append(str.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/**
	 * double类型数字精确小数点
	 * @param number 数字
	 * @param dot 精确位数
	 * @return double
	 */
	public static double doubleFormat(double number, int dot){
		BigDecimal bd = new BigDecimal(number);
		number = bd.setScale(dot, BigDecimal.ROUND_UP).doubleValue();
		return number;
	}

	public static String getLocalHostName() {
		String hostName;
		try {
			/**返回本地主机。*/
			InetAddress addr = InetAddress.getLocalHost();
			/**获取此 IP 地址的主机名。*/
			hostName = addr.getHostName();
		}catch(Exception ex){
			hostName = "";
		}

		return hostName;
	}

	public static String listJoin(List<String> list, String join) {
		StringBuilder sb = new StringBuilder();
		if(list == null || list.isEmpty()) return sb.toString();

		for(int i = 0; i < list.size() - 1; i++) {
			sb.append(list.get(i));
			sb.append(join);
		}
		sb.append(list.get(list.size() - 1));
		return sb.toString();
	}

    /**
     *
     * 过滤移除非法字符
     * @param content
     * @return
     */
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i+j]=0x30;
                }
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }
}
