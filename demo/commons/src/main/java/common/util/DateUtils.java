package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 *
 * @author liukl
 * @create 2018年6月29日16:39:53
 **/
public class DateUtils {

	private static String STANDARD_FULL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	private static String SHORT_FULL_DATETIME_FORMAT = "yyyyMMddHHmmssSSS";
	private static String STANDARD_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String SHORT_DATETIME_FORMAT = "yyyyMMddHHmmss";
	private static String STANDARD_DATE_FORMAT = "yyyy-MM-dd";
//	private static String STANDARD_DATE_FORMAT_YY = "yyyy/MM/dd";
	private static String SHORT_DATE_FORMAT = "yyyyMMdd";
	private static String SHORT_MMDD_FORMAT = "MMdd";
	private static String STANDARD_TIME_FORMAT = "HH:mm:ss";
	private static String SHORT_TIME_FORMAT = "HHmmss";
	private static String SHORT_MMDD_HHMM_FORMAT = "MM-dd HH:mm";
	private static String SHORT_YYYYMMDD = "yyyy年MM月dd日";
    private static String SHORT_YYYYMM = "yyyyMM";
    private static String SHORT_YYYY = "yyyy";


    /**
     *
     * 描述:格式化日期返回（yyyy年MM月dd日）
     * @return	时间 当前时间的小时数
     */
	public static int formatHour(){
        Calendar c = Calendar.getInstance();
	    return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     *
     * 描述:格式化日期返回（yyyy年MM月dd日）
     * @return	时间 当前时间的小时数
     */
	public static int formatHour(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
	    return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     *
     * 描述:格式化日期返回（当前月）
     * @return	时间 当前月
     */
    public static int formatMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH)+1;
    }

	/**
	 * 
	 * 描述:格式化日期返回（yyyy年MM月dd日）
	 * @param date 	日期时间
	 * @return	日期时间(yyyy年MM月dd日)
	 */
	public static String formatDate(Date date){
		SimpleDateFormat simpleDateFormataa = new SimpleDateFormat(SHORT_YYYYMMDD);
		return simpleDateFormataa.format(date);
	}
	/**
	 * 格式完全日期时间字符串（包含毫秒，标准格式：yyyy-MM-dd HH:mm:ss.S）
	 * 
	 * @param date
	 *            日期时间
	 * @return 完全日期时间字符串(yyyy-MM-dd HH:mm:ss.SSS)
	 */
	public static String formatStandardFullDateTime(Date date) {
		return new SimpleDateFormat(STANDARD_FULL_DATETIME_FORMAT).format(date);
	}

	/**
	 * 格式完全日期时间字符串（包含毫秒，短格式：yyyyMMddHHmmssSSS）
	 * 
	 * @param date 	日期时间
	 * @return 完全日期时间字符串(yyyyMMddHHmmssSSS)
	 */
	public static String formatShortFullDateTime(Date date) {
		return new SimpleDateFormat(SHORT_FULL_DATETIME_FORMAT).format(date);
	}

	/**
	 * 格式日期时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param date 	日期时间
	 * @return 日期时间字符串(yyyy-MM-dd HH:mm:ss)
	 */
	public static String formatStandardDateTime(Date date) {
		return new SimpleDateFormat(STANDARD_DATETIME_FORMAT).format(date);
	}

	/**
	 * 格式日期时间字符串（短格式：yyyyMMddHHmmss）
	 * 
	 * @param date
	 *            日期时间
	 * @return 日期时间字符串(yyyyMMddHHmmss)
	 */
	public static String formatShortDateTime(Date date) {
		return new SimpleDateFormat(SHORT_DATETIME_FORMAT).format(date);
	}

	/**
	 * 格式日期字符串（标准格式：yyyy-MM-dd）
	 * 
	 * @param date 	日期
	 * @return 日期字符串(yyyy-MM-dd)
	 */
	public static String formatStandardDate(Date date) {
		return new SimpleDateFormat(STANDARD_DATE_FORMAT).format(date);
	}

    /**
     *格式日期字符串（标准格式：yyyy/MM/dd）
     * @param date 日期(2015-11-09)
     * @return 日期字符串(yyyy-MM-dd)
     */
	public static String formatStandardDate(String date) {
		return date.replace("-", "/");
	}

	/**
	 * 格式日期字符串（短格式：yyyyMMdd）
	 * 
	 * @param date 	日期
	 * @return 日期字符串(yyyyMMdd)
	 */
	public static String formatShortDate(Date date) {
		return new SimpleDateFormat(SHORT_DATE_FORMAT).format(date);
	}
	/**
	 * 得到当前时间格式日期字符串（短格式：yyyyMMdd）
	 *
	 * @return 日期字符串(yyyyMMdd)
	 */
	public static String formatCurrShortDate() {
		return new SimpleDateFormat(SHORT_DATE_FORMAT).format(getNow());
	}

	/**
	 * 格式日期时间字符串（标准格式：MM-dd HH:mm）
	 * 
	 * @param date 	日期时间
	 * @return 日期时间字符串(MM-dd HH:mm)
	 */
	public static String formatStandardMMDD_HHMMDateTime(Date date) {
		return new SimpleDateFormat(SHORT_MMDD_HHMM_FORMAT).format(date);
	}

	/**
	 * 格式日期字符串（短格式：MMdd）
	 * 
	 * @param date 日期
	 * @return 日期字符串	(MMdd)
	 */
	public static String formatShortMMDDDate(Date date) {
		return new SimpleDateFormat(SHORT_MMDD_FORMAT).format(date);
	}

	/**
	 * 格式时间字符串（标准格式：HH:mm:ss）
	 * 
	 * @param date 	时间
	 * @return 时间字符串	(HH:mm:ss)
	 */
	public static String formatStandardTime(Date date) {
		return new SimpleDateFormat(STANDARD_TIME_FORMAT).format(date);
	}

	/**
	 * 格式时间字符串（短格式：HHmmss）
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串	(HHmmss)
	 */
	public static String formatShortTime(Date date) {
		return new SimpleDateFormat(SHORT_TIME_FORMAT).format(date);
	}



	/**
	 * 将日期字符串转成Date日期
	 * 
	 * @param dateStr 日期字符串
	 * @param type 日期字符串格式类型（eg:yyyyMMddHHmmss）
	 * @return 日期
	 */
	public static Date strToDate(String dateStr, String type) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 解析完全日期时间字符串（包含毫秒，标准格式：yyyy-MM-dd HH:mm:ss.S）
	 * 
	 * @param dateStr 完全日期时间字符串
	 * @return 日期时间	(yyyy-MM-dd HH:mm:ss.SSS)
	 */
	public static Date parseStandardFullDateTime(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(STANDARD_FULL_DATETIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

    /**
     * 解析带有日期格式的字符串，取出日期
     *
     * @param strDate 带有日期的字符串
     * @return 解析后的日期字符串
     */
	public static String analysisInitDate(String strDate){
        if (strDate ==null||("").equals(strDate)) return null;
        Pattern p = Pattern.compile("(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = p.matcher(strDate);

        //yyyyMMdd
        Pattern p1 = Pattern.compile("[1-9]\\d{3}(((0[13578]|1[02])([0-2]\\d|3[01]))|((0[469]|11)([0-2]\\d|30))|(02([01]\\d|2[0-8])))");
        Matcher matcher1 = p1.matcher(strDate);
        if (matcher.find()) {
            String str = matcher.group();
            String str1 = str.replace("年", "-");
            String str2 = str1.replace("月", "-");
            String str3 = str2.replace("日", "");
            String str4 = str3.replace("号", "");
            String str5 = str4.replace("/", "-");
            String str6 = str5.replace(".", "-").trim();
            String yyyy = str6.substring(0,4);
            if(!yyyy.startsWith("20") && !yyyy.startsWith("21")){
                return null;
            }else{
                return str6;
            }
        } else if (matcher1.find()) {
            String yyyyMMdd = matcher1.group();
            String yyyy = yyyyMMdd.substring(0,4);
            if(!yyyy.startsWith("20") && !yyyy.startsWith("21")){
                return null;
            }else{
                return  DateUtils.formatStandardDate(DateUtils.parseShortDate(yyyyMMdd));
            }
        }else {
            return null;
        }
    }


    /**
     * 解析带有日期格式的字符串，取出日期
     *
     * @param strDate 带有日期的字符串
     * @return 解析后的日期
     */
    public static Date analysisStrDate(String strDate) {
        if (strDate ==null||("").equals(strDate)) return getNow();
        try {
            // yyyy年MM月dd日 yyyy年MM月dd号 yyyy-MM-dd yyyy.MM.dd yyyy/MM/dd
            Pattern p = Pattern.compile("(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher matcher = p.matcher(strDate);

            //yyyyMMdd
            Pattern p1 = Pattern.compile("[1-9]\\d{3}(((0[13578]|1[02])([0-2]\\d|3[01]))|((0[469]|11)([0-2]\\d|30))|(02([01]\\d|2[0-8])))");
            Matcher matcher1 = p1.matcher(strDate);

            Pattern p2 = Pattern.compile("(\\d{1,2})(\\-|\\/|\\.|\\月)(\\d{1,2})?");
            Matcher matcher2 = p2.matcher(strDate);
            if (matcher.find()) {
                String str = matcher.group();
                String str1 = str.replace("年", "-");
                String str2 = str1.replace("月", "-");
                String str3 = str2.replace("日", "");
                String str4 = str3.replace("号", "");
                String str5 = str4.replace("/", "-");
                String str6 = str5.replace(".", "-").trim();
                String yyyy = str6.substring(0,4);
                if(!yyyy.startsWith("20") && !yyyy.startsWith("21")){
                    return null;
                }else{
                    return DateUtils.parseDateTime(str6);
                }
            } else if (matcher1.find()) {
                String yyyyMMdd = matcher1.group();
                String yyyy = yyyyMMdd.substring(0,4);
                if(!yyyy.startsWith("20") && !yyyy.startsWith("21")){
                    return null;
                }else{
                    return DateUtils.parseShortDate(yyyyMMdd);
                }
            }else if(matcher2.find()){
                String str = DateUtils.getTodayYearStr() + "-" + matcher2.group();
                String str1 = str.replace("年", "-");
                String str2 = str1.replace("月", "-");
                String str3 = str2.replace("日", "");
                String str4 = str3.replace("号", "");
                String str5 = str4.replace("/", "-");
                String str6 = str5.replace(".", "-").trim();
                return DateUtils.parseDateTime(str6);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }

    }

    /**
     * 解析带有日期格式的字符串，取出日期
     *
     * @param strDate 带有日期的字符串
     * @return 解析后的日期
     */
    public static Date analysisStrDatePublic(String strDate) {
        if (strDate ==null||("").equals(strDate)) return null;
        // yyyy年MM月dd日 yyyy年MM月dd号 yyyy-MM-dd yyyy.MM.dd yyyy/MM/dd
        Pattern p = Pattern.compile("(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher matcher = p.matcher(strDate);

        //yyyyMMdd
        Pattern p1 = Pattern.compile("[1-9]\\d{3}(((0[13578]|1[02])([0-2]\\d|3[01]))|((0[469]|11)([0-2]\\d|30))|(02([01]\\d|2[0-8])))");
        Matcher matcher1 = p1.matcher(strDate);

       if (matcher.find()) {
            String str = matcher.group();
            String str1 = str.replace("年", "-");
            String str2 = str1.replace("月", "-");
            String str3 = str2.replace("日", "");
            String str4 = str3.replace("号", "");
            String str5 = str4.replace("/", "-");
            String str6 = str5.replace(".", "-").trim();
           String yyyy = str6.substring(0,4);
           if(!yyyy.startsWith("20") && !yyyy.startsWith("21")){
               return null;
           }else{
               return DateUtils.parseDateTime(str6);
           }
        } else if (matcher1.find()) {
            String yyyyMMdd = matcher1.group();
            String yyyy = yyyyMMdd.substring(0,4);
            if(!yyyy.startsWith("20") && !yyyy.startsWith("21")){
                return null;
            }else{
                return DateUtils.parseShortDate(yyyyMMdd);
            }
        }else {
            return null;
        }
    }
	/**
	 * 
	 * 描述:传入字符串返回date对象（yyyy-MM-dd）
	 * 
	 * @param data   String
	 * 作者：lxx	2015-11-18下午09:06:16
	 * @param data
	 * @return  Date
	 */
	public static Date parseDateTime(String data){
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(STANDARD_DATE_FORMAT);
			return simpleDateFormat.parse(data);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 解析完全日期时间字符串（包含毫秒，短格式：yyyyMMddHHmmssS）
	 * 
	 * @param dateStr 	完全日期时间字符串
	 * @return 日期时间(yyyyMMddHHmmssSSS)
	 */
	public static Date parseShortFullDateTime(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(SHORT_FULL_DATETIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析日期时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param dateStr 日期时间字符串
	 * @return 日期时间(yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parseStandardDateTime(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(STANDARD_DATETIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析日期时间字符串（短格式：yyyyMMddHHmmss）
	 * 
	 * @param dateStr 日期时间字符串
	 * @return 日期时间	(yyyyMMddHHmmss)
	 */
	public static Date parseShortDateTime(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(SHORT_DATETIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析日期字符串（标准格式：yyyy-MM-dd）
	 * 
	 * @param dateStr 日期字符串yyyy-MM-dd
	 * @return 日期(yyyy-MM-dd)
	 */
	public static Date parseStandardDate(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(STANDARD_DATE_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析日期字符串（短格式：yyyyMMdd）
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 日期(yyyyMMdd)
	 */
	public static Date parseShortDate(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(SHORT_DATE_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析日期字符串（短格式：MMdd）
	 * 
	 * （eg:传入1011 返回：Sun Oct 11 00:00:00 CST 1970）
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 日期（MMdd）
	 */
	public static Date parseShortMMDDDate(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(SHORT_MMDD_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析时间字符串（短格式：HHmmss）
	 * 
	 * （eg:传入130223 返回：Thu Jan 01 13:02:23 CST 1970）
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @return 时间(HHmmss)
	 */
	public static Date parseShortTime(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		try {
			return new SimpleDateFormat(SHORT_TIME_FORMAT).parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 得到现在时间
	 * 
	 * @return Date 当前时间
	 */
	public static Date getNow() {
		Calendar ca = Calendar.getInstance();
		return ca.getTime();
	}

	/**
	 * 获取当前完全日期时间字符串（包含毫秒，标准格式：yyyy-MM-dd HH:mm:ss.S）
	 * 
	 * @return 完全日期时间字符串
	 */
	public static String getNowStandardFullStr() {
		return formatStandardFullDateTime(new Date());
	}

	/**
	 * 获取当前完全日期时间字符串（包含毫秒，短格式：yyyyMMddHHmmssS）
	 * 
	 * @return 完全日期时间字符串(yyyyMMddHHmmssSSS)
	 */
	public static String getNowShortFullStr() {
		return formatShortFullDateTime(new Date());
	}

	/**
	 * 获取当前日期时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 * 
	 * @return 日期时间字符串(yyyy-MM-dd HH:mm:ss)
	 */
	public static String getNowStandardStr() {
		return formatStandardDateTime(new Date());
	}

	/**
	 * 获取当前日期时间字符串（短格式：yyyyMMddHHmmss）
	 * 
	 * @return 日期时间字符串(yyyyMMddHHmmss)
	 */
	public static String getNowShortStr() {
		return formatShortDateTime(new Date());
	}

	/**
	 * 获取当前日期字符串（标准格式：yyyy-MM-dd）
	 * 
	 * @return 日期字符串（标准格式：yyyy-MM-dd）
	 */
	public static String getTodayStandardStr() {
		return formatStandardDate(new Date());
	}

	/**
	 * 获取当前日期字符串（短格式：yyyyMMdd）
	 * 
	 * @return 日期字符串（短格式：yyyyMMdd）
	 */
	public static String getTodayShortStr() {
		return formatShortDate(new Date());
	}

	/**
	 * 获取当前时间的时分秒（格式：HH:mm:ss）
	 * 
	 * @return String 当前时间 时分秒
	 */
	public static String getNowTimeStandardStr() {
		return formatStandardTime(new Date());
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getTodayString() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 取得今天开始时间(yyyy-MM-dd 00:00:00)
	 * 
	 * @return 今天日期开始时间字符串yyyy-MM-dd HH:mm:ss
	 */
	public static String getTodayStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		return sf.format(cal.getTime());
	}

	/**
	 * 取得今天结束时间(yyyy-MM-dd 23:59:59)
	 * 
	 * @return 今天日期结束时间字符串yyyy-MM-dd HH:mm:ss
	 */
	public static String getTodayEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 + 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		return sf.format(cal.getTime());
	}

	/**
	 * 得到今天年份字符
	 * 
	 * @return 今年年份字符4位数yyyy
	 */
	public static String getTodayYearStr() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String dateString = formatter.format(currentTime);
		dateString = dateString.substring(0, 4);
		return dateString;
	}

	/**
	 * 得到今天年份字符
	 * 
	 * @return 今年年份字符2位数yy
	 */
	public static String getTodayYearTwoStr() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String dateString = formatter.format(currentTime);
		dateString = dateString.substring(0, 2);
		return dateString;
	}

	/**
	 * 得到本月月份字符
	 * 
	 * @return 本月月份MM
	 */
	public static String getTodayMonthStr() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String dateString = formatter.format(currentTime);
		return dateString.substring(5, 7);
	}

	/**
	 * 得到今天日期
	 * 
	 * @return int 今天日期天数dd
	 */
	public static int getTodayDays() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String dateString = formatter.format(currentTime);
		int today = Integer.parseInt(dateString.substring(8, 10));
		return today;
	}

	/**
	 * 得到现在小时
	 * 
	 * @return String 现在小时HH
	 */
	public static String getNowHour() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return String 现在分钟数mm
	 */
	public static String getNowMinute() {
		Date currentTime = getNow();
		SimpleDateFormat formatter = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 取得昨天时间(yyyy-MM-dd)
	 * 
	 * @return 昨天日期时间字符串yyyy-MM-dd
	 */
	public static String getYesterdayStr() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATE_FORMAT);
		return sf.format(cal.getTime());
	}

	/**
	 * 得到昨天年份字符
	 * 
	 * @return 昨天年份字符
	 */
	public static String getYesterdayYearStr() {
		String datestr = getNextDay(new Date(), -1);
		datestr = datestr.substring(0, 4);
		return datestr;
	}

	/**
	 * 得到昨天月份字符
	 * 
	 * @return 昨天月份字符
	 */
	public static String getYesterdayMonthStr() {
		String datestr = getNextDay(new Date(), -1);
		datestr = datestr.substring(5, 7);
		return datestr;
	}

	/**
	 * 得到昨天日期字符
	 * 
	 * @return 昨天日期字符
	 */
	public static String getYesterdayDayStr() {
		String datestr = getNextDay(new Date(), -1);
		datestr = datestr.substring(8, 10);
		return datestr;
	}

	/**
	 * 得到前天年份字符
	 * 
	 * @return 前天年份字符
	 */
	public static String getBeforeYesYearStr() {
		String datestr = getNextDay(new Date(), -2);
		datestr = datestr.substring(0, 4);
		return datestr;
	}

	/**
	 * 得到前天月份字符
	 * 
	 * @return 前天月份字符
	 */
	public static String getBeforeYesMonthStr() {
		String datestr = getNextDay(new Date(), -2);
		datestr = datestr.substring(5, 7);
		return datestr;
	}

	/**
	 * 得到前天日期字符
	 * 
	 * @return 前天日期字符
	 */
	public static String getBeforeYesDayStr() {
		String datestr = getNextDay(new Date(), -2);
		datestr = datestr.substring(8, 10);
		return datestr;
	}

	/**
	 * 取得一周前时间（yyyy-MM-dd 00:00:00）
	 * 
	 * @return 一周前的时间字符串
	 */
	@SuppressWarnings("deprecation")
	public static String getStandOneWeekdaytime() {
		Date oneWeek = getOneWeekdaytime();
		oneWeek.setHours(0);
		oneWeek.setMinutes(0);
		oneWeek.setSeconds(0);
		return formatStandardDateTime(oneWeek);
	}

	/**
	 * 取得昨天时间
	 * 
	 * @return 昨天时间 Date
	 */
	public static Date getYesterdaytime() {
		return parseStandardDateTime(getTodayBefore(-1));
	}

	/**
	 * 取得7天前时间
	 * 
	 * @return 7天前时间 Date
	 */
	public static Date getOneWeekdaytime() {
		return parseStandardDateTime(getTodayBefore(-7));
	}

	/**
	 * 取得1个月前时间
	 * 
	 * @return 1个月前时间 Date
	 */
	public static Date getOneMonthdaytime() {
		return parseStandardDateTime(getTodayBefore(-30));
	}

	/**
	 * 取得去年的时间
	 * 
	 * @return String（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getLastYeartime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -2);
		SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		return sf.format(cal.getTime());
	}

	/**
	 * 获取传入日期的下一天
	 * 
	 * @param date
	 *            日期字符串
	 * @return 日期
	 */
	public static Date getStandardDateNext(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取传入日期的上个月（标准格式：yyyy-MM-dd）
	 * 
	 * @param date
	 *            日期字符串
	 * @return 日期字符串yyyy-MM-dd
	 */
	public static String getStandardDateBeforeMonth(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -1);
			SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATE_FORMAT);
			return sf.format(cal.getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取传入日期星期数
	 * 
	 * @param date1
	 *            传入日期
	 * @return int 星期几（eg:1,2,3,4,5,6,7）
	 */
	public static int getWeek(Date date1) {
		Calendar c1 = new GregorianCalendar();
		c1.setTime(date1);
		int dayOfWeek = c1.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek = dayOfWeek - 1;
		}
		return dayOfWeek;
	}

	/**
	 * 根据天数获取今天之前的时间
	 * 
	 * @param day
	 * @return String（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getTodayBefore(int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		SimpleDateFormat sf = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		return sf.format(cal.getTime());
	}

	/**
	 * 根据传入的年，月，日获取日期
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @return 日期
	 */
	public static Date createDate(int year, int month, int date) {
		// return new Date(year-1900,mouth,date);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, date);
		return calendar.getTime();
	}

	/**
	 * 根据传入的年，月，日，小时，分钟，秒获取日期
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hours
	 *            小时
	 * @param minutes
	 *            分钟
	 * @param seconds
	 *            秒
	 * @return Date
	 */
	public static Date createDate(int year, int month, int date, int hours, int minutes, int seconds) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, date);
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 根据传入的long类型日期转成日期类型
	 * 
	 * @param longDate
	 *            long类型日期
	 * @return 日期
	 */
	public static Date longToDate(Long longDate) {
		Date date = getNow();
		date.setTime(longDate);
		return date;
	}

	/**
	 * 将日期类型转成long类型
	 * 
	 * @param date
	 *            将日期转成long类型
	 * @return long类型的数字
	 */
	public static long dateToLong(Date date) {
		if (date == null)
			return 0;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 判断两个日期是否同一天
	 * 
	 * @param date1
	 *            日期一
	 * @param date2
	 *            日期二
	 * @return boolean 是否同一天
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		boolean result = false;
		Calendar c1 = new GregorianCalendar();
		c1.setTime(date1);
		Calendar c2 = new GregorianCalendar();
		c2.setTime(date2);

		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH))
			result = true;

		return result;
	}

	/**
	 * 得到二个日期间的间隔天数
	 * 
	 * @param sj1
	 *            日期1 String(格式：yyyy-MM-dd)
	 * @param sj2
	 *            日期2 String(格式：yyyy-MM-dd)
	 * @return String	yyyy-MM-dd
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(STANDARD_DATE_FORMAT);
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 得到两个日期 之间的 时间差, 以分钟表示
	 * 
	 * @param begin
	 *            开始时间，日期类型
	 * @param end
	 *            结束时间，日期类型
	 * 
	 * @return 两个日期相差分钟数，int类型
	 */
	public static int getMinutesBetween2Days(Date begin, Date end) {
		long dayNumber = 0;
		// 1小时=60分钟=3600秒=3600000
		long mins = 60L * 1000L;
		// long day= 24L * 60L * 60L * 1000L;计算天数之差
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dayNumber = (end.getTime() - begin.getTime()) / mins;
		} catch (Exception e) {
			return -1;
		}

		return (int) dayNumber;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式
	 * 
	 * @param st1
	 *            时间1（格式：HH:MM）
	 * @param st2
	 *            时间2（格式：HH:MM）
	 * @return 字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 * 
	 * @param sj1
	 *            时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 * @param jj
	 *            分钟数
	 * @return 前推或后推时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个日期的前 X分钟的日期
	 * 
	 * @param date
	 *            Date类型日期
	 * @param mins
	 *            int类型分钟
	 * @return Date类型日期
	 */
	public static Date getTimeBefore(Date date, int mins) {
		if (null == date || mins < 0)
			return null;

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(date);

		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - mins);

		return calendar.getTime();
	}
    /**
     * 得到一个下次采集的时间间隔
     *
     * @param mins
     *            int类型分钟
     * @return Date类型日期
     */
    public static Date getTimeAfter(int mins) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.setTime(new Date());

        calendar.add(GregorianCalendar.MINUTE, mins);

        return calendar.getTime();
    }

	/**
	 * 时间前推或后推秒钟,其中sec表示秒.
	 * 
	 * @param sj1
	 *            时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 * @param sec
	 *            秒数int类型
	 * @return 时间字符串（标准格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static String getPreSec(String sj1, int sec) {
		SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATETIME_FORMAT);
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + sec;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间字符串
	 * 
	 * @param nowdate
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @param delay
	 *            前移或后延的天数
	 * @return 时间字符串（标准格式：yyyy-MM-dd）
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
			String mdate = "";
			Date d = parseStandardDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 得到一个时间延后或前移几天的时间
	 * 
	 * @param d
	 *            日期Date类型
	 * @param delay
	 *            延后或前移几天天数
	 * @return 日期字符串yyyy-MM-dd
	 */
	public static String getNextDay(Date d, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
			String mdate = "";
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 得到一个时间延后或前移几天的时间Date
	 * 
	 * @param d
	 *            时间Date类型
	 * @param delay
	 *            前移或后延的天数 int类型
	 * @return 时间Date类型
	 */
	public static Date getNextDayDate(Date d, int delay) {
		try {
			Date din = (Date) d.clone();
			long myTime = (din.getTime() / 1000) + delay * 24 * 60 * 60;
			din.setTime(myTime * 1000);
			return din;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @return 是否闰年 boolean类型
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 * 
		 */
		Date d = parseStandardDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * 
	 * @param dat
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @return 一个月最后一天，时间字符串（标准格式：yyyy-MM-dd）
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 获取一个月的天数
	 * 
	 * @param year
	 *            年份，int类型
	 * @param mon
	 *            月份，int类型
	 * @return 天数，字符串类型
	 */
	public static String getEndDayOfMonth(int year, int mon) {
		String str = "";
		String dat = year + "-" + mon + "-01";
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str = "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str = "30";
		} else {
			if (isLeapYear(dat)) {
				str = "29";
			} else {
				str = "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 是否同一周（注意周期从周日开始）
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周

			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周（如：2015年10月12日返回201542，即2015年10月12日在2015年42周）
	 * 
	 * 
	 * 
	 * @return 当前时间在年度周数
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2015年10月11日所在周的星期一是几号
	 * 
	 * 
	 * @param sdate
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @param num
	 *            星期几字符串（如0，1,2，...6）
	 * @return 时间字符串（标准格式：yyyy-MM-dd）
	 */
	public static String getDateStrByWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = parseStandardDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @return 星期几字符串
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = parseStandardDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 星期日 星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 根据日期返回月份
	 *
	 * @param date
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @return 月份字符串
	 */
	public static String getMonthStr(String date) {
		String reg1 = "^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";
		String reg2 = "^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";
		String reg3 = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
		String reg4 = "^\\d{4}/\\d{1,2}/\\d{1,2}$";
		String month = "";
		Pattern p = Pattern.compile(reg1);
		Matcher m = p.matcher(date);
		if (m.matches()) {
			date = date.substring(0, date.lastIndexOf(" ")).trim();
			month = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/")).trim();
		}
		Pattern p2 = Pattern.compile(reg2);
		Matcher m2 = p2.matcher(date);
		if (m2.matches()) {
			date = date.substring(0, date.lastIndexOf(" ")).trim();
			month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")).trim();
		}
		Pattern p3 = Pattern.compile(reg3);
		Matcher m3 = p3.matcher(date);
		if (m3.matches()) {
			month = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")).trim();
		}
		Pattern p4 = Pattern.compile(reg4);
		Matcher m4 = p4.matcher(date);
		if (m4.matches()) {
			month = date.substring(date.indexOf("/") + 1, date.lastIndexOf("/")).trim();
		}

		return month;
	}

	/**
	 * 根据日期返回几号
	 * 
	 * @param date
	 *            时间字符串（标准格式：yyyy-MM-dd）
	 * @return 字符串日期几号
	 */
	public static String getDayStr(String date) {
		String reg1 = "^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";
		String reg2 = "^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}$";
		String reg3 = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
		String reg4 = "^\\d{4}/\\d{1,2}/\\d{1,2}$";
		String day = "";
		Pattern p = Pattern.compile(reg1);
		Matcher m = p.matcher(date);
		if (m.matches()) {
			date = date.substring(0, date.lastIndexOf(" ")).trim();
			day = date.substring(date.lastIndexOf("/") + 1).trim();
		}

		Pattern p2 = Pattern.compile(reg2);
		Matcher m2 = p2.matcher(date);
		if (m2.matches()) {
			date = date.substring(0, date.lastIndexOf(" ")).trim();
			day = date.substring(date.lastIndexOf("-") + 1).trim();
		}
		Pattern p3 = Pattern.compile(reg3);
		Matcher m3 = p3.matcher(date);
		if (m3.matches()) {
			day = date.substring(date.lastIndexOf("-") + 1).trim();
		}
		Pattern p4 = Pattern.compile(reg4);
		Matcher m4 = p4.matcher(date);
		if (m4.matches()) {
			day = date.substring(date.lastIndexOf("/") + 1).trim();
		}
		return day;
	}
	/**
	 * @Description: 同个两个时间字符串（yyyy-MM-dd）类型的，做差值，求取两个时间字符串相差的天数
	 * @param		beginDate	开始时间
	 * @param		endDate		结束时间
	 * @author cuichx --- 2015-11-11下午3:15:52     
	 * @return
	 */
	public static int getDaysBetween2Days(Date beginDate, Date endDate){
		int minutes=getMinutesBetween2Days(beginDate, endDate);
		int days=minutes/(60*24);
		return days;
	}

    public static void main(String[] args) {

 /*       System.out.println("---"+formatDate(new Date()));
        System.out.println("---"+formatShortDate(new Date()));
        System.out.println("---"+formatShortDateTime(new Date()));
        System.out.println("---"+formatShortFullDateTime(new Date()));
        System.out.println("---"+formatShortMMDDDate(new Date()));
        System.out.println("---"+formatShortTime(new Date()));
*/
        System.out.println("---"+getTodayYearMonth(new Date()));
        System.out.println("---"+getTodayYear(new Date()));
//        System.out.println("---"+formatStandardDate("2015-07-07"));
//        System.out.println("---"+formatStandardDateTime(new Date()));
//        System.out.println("---"+formatStandardFullDateTime(new Date()));
//        System.out.println("---"+formatStandardMMDD_HHMMDateTime(new Date()));
//        System.out.println("---"+formatStandardTime(new Date()));

        //System.out.println("--"+DateUtils.getPreTime(getNowStandardStr(),"-10"));

        //System.out.println(getTimeAfter(new Date(),30));
        System.out.println(formatShortFullDateTime(getTodayStart()));
        System.out.println(formatShortFullDateTime(getTodayEnd()));


    }

    /**
     * 计算相差天数，endDate>newDate 返回正数，否则返回负数
     * @param newDate
     * @param endDate
     * @return
     */
    public static int daysOfTwo(Long newDate, Long endDate) {
	    return (int) (endDate - newDate > 0 ? (endDate-newDate)/(1000*60*60*24) : -(newDate-endDate)/(1000*60*60*24));
    }

    /**
     * 得到今天年份字符
     *
     * @return 今年年份字符4位数yyyy
     */
    public static String getTodayYearMonth(Date date ) {

        return new SimpleDateFormat(SHORT_YYYYMM ).format(date);
    }
    public static String getTodayYear(Date date ) {

        return new SimpleDateFormat(SHORT_YYYY ).format(date);
    }
     public  static Date stringToDate(String dateString ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
        }
        return date;


    }

    /**
     * 当天的00:00:00
     * @return
     */
    public static Date getTodayStart(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date start = calendar.getTime();
        return start;
    }

    /**
     * 当天的23:59:59
     * @return
     */
    public static Date getTodayEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date end = calendar.getTime();
        return end;
    }
}
