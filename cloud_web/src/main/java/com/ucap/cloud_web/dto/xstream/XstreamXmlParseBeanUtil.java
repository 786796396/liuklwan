package com.ucap.cloud_web.dto.xstream;

import java.net.MalformedURLException;
import java.net.URL;

import com.publics.util.utils.StringUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.ucap.cloud_web.entity.Detail;
import com.ucap.cloud_web.entity.Result;

/**
 * Xstream将xml转成Bean
 * 
 * @Title: XstreamXmlParseBeanUtil.java
 * @Package com.ucap.netcheck.dataParse
 * @Description:
 * @author Na.Y
 * @date 2015-9-24 下午2:28:02
 * @version V1.0
 */
public class XstreamXmlParseBeanUtil {

	public static XStream xstream = null;

	private static Root root = null;

	/**
	 * 根据url地址将Xml转成Root对象
	 * 
	 * urlString url地址
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public static Root getRootByUrl(String urlString)
			throws MalformedURLException {
		initXStream(false);
		URL url = new URL(urlString);
		root = (Root) xstream.fromXML(url);
		return root;
	}

	/**
	 * 根据xml字符串将Xml字符串转成Root对象
	 * 
	 * xmlStr xml字符串
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public static Root getRootByXmlStr(String xmlStr)
			throws MalformedURLException {
		initXStream(false);
		root = (Root) xstream.fromXML(xmlStr);
		return root;
	}

	/**
	 * 初始化xstream and xml by Object
	 * 
	 * @param refresh
	 */
	public static void initXStream(boolean refresh) {
		if (xstream == null || refresh == true) {
			xstream = new XStream(new DomDriver());
			// 忽略未知节点 add by Na.Y 20150924
			xstream.ignoreUnknownElements();
			xstream.alias("root", Root.class);
			xstream.alias("result", Result.class);
			xstream.alias("detail", Detail.class);
		}
	}

	public static void main(String[] args) throws MalformedURLException {

		// 1.xml String 转javabean
		// String xml2 =
		// "<root><response>SUCCESS</response><msg>查询成功!</msg><uuid>d09db862-6bd7-4d42-803d-50b78d22b7be2</uuid><taskId>20150323155144284</taskId><status>1</status><homes><home><url>http://www.gd.gov.cn/</url><img>img/23/82CF8243645163EA7E9493D6039769DD_1422703762664.jpg</img><ip>192.168.10.187</ip><port>9000</port><stime>2015-03-31 14:20:55</stime><rtimes><rtime>2015-03-31 14:20:55</rtime></rtimes></home></homes><channels><channel><url>http://zwgk.gz.gov.cn/GZ00/index1.shtml</url><c1>信息公开类</c1><c2>机构设置及职能</c2><c3>组织机构</c3><img>img/20/234C54798799BA59D9DDC66E3A1810A2_1422703762485.jpg</img><ip>192.168.10.187</ip><port>9000</port><stime>2015-03-31 14:20:55</stime><rtimes><rtime>2015-03-31 14:20:55</rtime></rtimes></channel></channels></root>";
		// Root root = XstreamXmlParseBeanUtil.getRootByXmlStr(xml2);

		// System.out.println("root=" + root);

		String url = "http://www.bjhd.gov.cn/root85_127/#%E6%88%BF%20%20%20%E5%B1%8B";
		// String url = "http://www.bjhd.gov.cn/root85_127/a&";
		String encodeUrl = StringUtils.hash(url);

		// 2.url地址请求转javabean
		String urlString2 = "http://121.40.117.83:8080/Monitor/urlLink.ejf?encodeurl="
				+ encodeUrl + "&date=2015-11-19";

		Root rootUrl = XstreamXmlParseBeanUtil.getRootByUrl(urlString2);
		System.out.println("rootUrl=" + rootUrl);
	}
}
