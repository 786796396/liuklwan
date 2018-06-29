package common.util;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * 2017/8/12: 增加了获取根域名getRootDomain方法
 * <p>
 * 获取链接url相关的信息
 * Created by wangwl on 2017/7/13.
 * Modified by Xia Tian 2017/8/12
 */
public class LinkUtil {

    /**
     * The constant logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(LinkUtil.class);
    private static final int INDEX_VALUE=-1;//新链接
    public static final int Invalid_url=0;//无效链接
    public static final int media_url=1;//图片
    public static final int normal_url=2;//网页
    public static final int accessory_url=3;//附件
    public static final int js_url=4; // js
    public static final int css_url=5; //css
    public static final int video_url=6; //视频
    /**
     * Gets host.
     *
     * @param urlString the url string
     * @return the host
     */
    public static String getHost(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
            return url.getHost();
        } catch (MalformedURLException e) {
            logger.error("获取getHost失败："+e.toString());
            return "";
        }
    }

    /**
     * 这里假设url总是以http://开始的，寻找子域名字串。比如： <br/>
     * http://news.sina.com.cn/c/2006-11-30/052711658476.shtml<br/>
     * 子域名字符串应该是sina.com.cn。
     * 如果是IP地址，则直接返回IP地址本身， 如http://203.75.155.56/index.html, 返回203.75.155.56
     *
     * @param url the url
     * @return root domain. The null will be return when you give an null input value
     */
    public static String getRootDomain(String url) {
        if (url == null) return null;
        return checkRootDomain(url);
    }

    private static String checkRootDomain(String url) {
        return getRootDomainOfHost(getDomain(url));
    }

    /**
     * 获取hostname的根域名
     *
     * @param hostname the hostname. Parser the hostname then result its domain name conform
     * rfc 1035 rfc 1123 and rfc 2181
     * @return root domain of host
     */
    public static String getRootDomainOfHost(String hostname) {
        if (hostname == null || hostname.isEmpty()) {
            logger.error("hostname: " + hostname + " can't be parser.");
            return null;
        }

        String tempDomain = hostname.toLowerCase();
        if (tempDomain.startsWith("www."))
            tempDomain = hostname.substring(4);
        Pattern ptn = Pattern.compile("^\\w");
        if (! ptn.matcher(tempDomain).find()) {
            logger.error("hostname: "+hostname+" contain unexpected character");
            return "";
        }


        String parts[] = tempDomain.split("\\.");
        if ( parts.length <= 1 ) {
            logger.error("Host name: " + hostname + " isn't well format");
            return "";
        }

        String extension = null;
        String prefix    = null;
        if ( parts.length == 2 ) {
            extension = parts[1];
            if (!(TopDomainNameExtension.TOP_EXTENSIONS.contains("." + extension) ||
                TopDomainNameExtension.COUNTRY_EXTENSIONS.contains("." + extension))) {
                logger.error("Extension: " + extension + " is illegal.");
                return "";
            }
            prefix = parts[0];
        }

        if ( parts.length >= 3 ) {
            int pLen = parts.length;
            int extLen = 0;
            if (TopDomainNameExtension.TOP_EXTENSIONS.contains("." + parts[pLen - 1])) {
                extLen = 1;
            } else {
                if (TopDomainNameExtension.COUNTRY_EXTENSIONS.contains("." + parts[pLen - 1]))
                    extLen = 1;
                if (extLen == 1 && TopDomainNameExtension.TOP_EXTENSIONS.contains("." + parts[pLen - 2]))
                    extLen = 2;
            }

            if (extLen == 0) {
                return numberUrlDispose(hostname);
            }
            else {
                extension = extLen == 1 ? parts[pLen - 1] : parts[pLen - 2] + "." + parts[pLen - 1];
                prefix = parts[pLen - extLen - 1];
            }
        }

        return prefix + "." + extension;
    }


    /**
     * 根据当前url得到当前域名domain
     *
     * @param url the url
     * @return domain
     */
    public static String getDomain(String url) {
        //在url转换之前，将中文的冒号、点修改为英文的，空格去掉
        String newUrl = url.replace("：", ":")
            .replace("。", ".").replace(" ", "").replace("\\\\","");
        try {
            URL u = new URL(newUrl);
            return u.getHost().toLowerCase();//统一使用小写
        } catch (MalformedURLException e) {
            String reg = "^(https?)://.+$";
            if (!newUrl.matches(reg)) {
                return url;
            }
            logger.error("获取url的域名异常：url="+url+" ##错误信息"+e.toString());
            return null;
        }
    }

    /**
     * 对中文和无http协议的url做处理
     * @param url
     * @return
     */
    public static String StringHttpURL(String url) {
        url=url.trim();
        String pageUrl=url.toLowerCase();
        String strUrl = null;
        String reg = "^(https?|ftp|file)://.+$";
        if (!pageUrl.matches(reg)) {
            url = "http://" + url;
        }
            return url;
    }

    /**
     * 对数字url的处理方法
     * @param url
     * @return
     */
    public static String numberUrlDispose(String url){
        String reg="((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
        if(url.matches(reg)){
            return url;
        }
        logger.error("Domain: " + url + " is illegal.");
        return null;
    }

    /**办事大厅
     * 对链接标题进行判断 过滤掉下一页等分页链接
     * @param title
     * @return
     */
    public static boolean filterNextPage(String title){
        if(StringUtils.isEmpty(title)){return false;}
        String reg = "^\\d+|分享|更多>>|更多|<<更多|更多\\+|部门动态|信访接待|信息公开|关于我们|English|繁体版|服务投诉|局长信箱|楼房出售|公共事业|权责清单" +
            "|省局领导|媒体视点|头条推荐|直属单位|派驻机构|镇街冬天|网站导航|新闻发布|信件选登|纳税咨询|投诉回复|咨询回复|违纪举报|违法检举|领导简介|政务公告" +
            "|队伍建设|机构设置|政声传递|退出|领导专栏|各地税讯|工作动态|政务公开|网上服务|公众互动|统计数据|招标采购|人事信息|部门信息|中央要闻|领导活动|通知公告" +
            "|城管执法监督举报|行政处罚|行政法规|政策文件|县市新闻|会内新闻|站长统计|行政许可|办事指南|便民服务|通知公告|视频库|公开规定|图片新闻|教育服务" +
            "|专题专栏|网站地图|隐私保护|政务动态|主要职责|政民互动|网址域名|法律法规|联系方法|政务联播 |招考录用|住房服务|公开年报|企业开办|网上调查|建议咨询" +
            "|法律责任|交通指引|土地管理|风景名胜|著名人物|对外关系|历史沿革|丰台简历|地理环境|行政区划|人口民族||政策法规|应急管理|区长信箱|收费依据|专项工作" +
            "|统计信息|党群工作|财政信息|行政职责|内设机构|国务院信息|网站首页|天气预报|气象监测|预警应急|搜索|农业改革|投资旅游|就业服务|公开目录|公开指南|图片新闻" +
            "|中央要闻|咨询与建议|天然气与管道|社会责任报告|国际油气业务|社会公益活动" +
            "|石油工程建设|炼油与化工|产品与服务|环境与社会|油田动态|媒体聚焦|Русский|Español|油品市场动态|油品与市场|工程技术服务|勘探与生产|在线访谈" +
            "|版权声明|高级搜索|法律|地方性法规|政府规章|规范性文件|相关法规|首页|第一页|上一页|<|<<|<<<|下一页|>|>>|>>>|尾页|末页$";
        if(title.trim().matches(reg)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 对mime类型做判断，区分text image application等
     * 对应siteDeadLinkDetail的资源类型（1图片、2网页、3附件、4其他）
     * @param contentType
     * @return
     */
    public static int mimeTypeDispose(String contentType){
        if(null==contentType)return Invalid_url;
        if(contentType.trim().startsWith("audio")){
            return video_url;
        }else if(contentType.trim().startsWith("image")){
            return media_url;
        }else if (contentType.trim().startsWith("text")){
            return normal_url;
        }else if(contentType.trim().startsWith("application")){
            return accessory_url;
        }else {
            return Invalid_url;
        }
    }
    /**
     * 通过url的后缀去简单判断
     * 对应的资源类型（1图片、2网页、3附件、4js、5css、6视频）
     * @param url
     * @return
     */
    public static int resourceType(String url){
        if(null==url)return Invalid_url;
        url=url.toLowerCase();//防止文件格式大小写不统一，先把所有的url字符转成小写
        String regImg=".+(.bmp|.jpg|.png|.tiff|.gif|.pcx|.tga|.exif|.fpx|.svg|.psd|.cdr|.pcd|.dxf|.ufo|.eps|.ai|.raw|.wmf)$";
        String regVideo = ".+(.avi|.mov|.navi|.3gp|.mkv|.flv|.f4v|.webm|.ra|.rm|.rmvb|.wmv|.mp4|.mp3|.mpeg|.mpe|.mpg|.asf|.divx|.vob|.dat)$";
        String redAccessory =".+(.doc|.docx|.xlsx|.pdf|.txt|.xls|.rar|.wps|.rtf|.zip|.cvs|.exe|.ceb|.pcx|.pptx|.mht|.ppt)$";
        String regJs=".+(.js)$";
        String regCss=".+(.css)$";
        if(url.matches(regImg)){
            return media_url;
        }else if(url.matches(regVideo)){
            return video_url;
        }else if(url.matches(redAccessory)){
            return accessory_url;
        }else if(url.matches(regJs)){
            return js_url;
        }else if(url.matches(regCss)){
            return css_url;
        }else {
            return normal_url;
        }
    }

    /**
     * 格式化 抽取的baseUri 用于相对路径 ./   ../ 的情况
     * @param baseUri
     * @return 格式化后的新链接
     */
    public static String formatBaseUri(String baseUri) throws Exception {
        //fixme
        String rst = null;
        if(StringUtils.isEmpty(baseUri)) return null;
        //last Dot Index
        int ldidx = baseUri.lastIndexOf('.');
        //last Slash Index
        int lsidx = baseUri.lastIndexOf('/');

        if(lsidx > ldidx && !baseUri.endsWith("/")){
            rst = baseUri + "/";
        }else{
            rst = baseUri;
        }
        return rst;
    }

    /**
     * 格式化 抽取的baseUri 用于相对路径 ./   ../ 的情况
     * @param doc  Document 提取uri出来
     */
    public static void formatBaseUri(Document doc) throws Exception {
        String baseUri = doc.baseUri();
        if(StringUtils.isNotEmpty(baseUri)){
            String newBaseUri = formatBaseUri(baseUri);
            doc.setBaseUri(newBaseUri);
        }
    }

}
