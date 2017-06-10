package com.ucap.cloud_web.service.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.publics.util.mail.MailSenderInfo;
import com.publics.util.mail.SimpleMailSender;
import com.publics.util.page.PageVo;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.QueryUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.dao.DatabaseInfoDao;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.MenuModel;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.exception.EmailException;
import com.ucap.cloud_web.util.DesTwo;
import com.ucap.cloud_web.util.StringEncrypt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <br>
 * <b>作者：</b>kefan<br>
 * <b>日期：</b> 2015-11-16 10:54:39 <br>
 * <b>版权所有：<b>版权所有(C) 2015<br>
 */

@Service
public class DatabaseInfoServiceImpl implements IDatabaseInfoService {

	@Autowired
	private DatabaseInfoDao databaseInfoDao;
	@Autowired
	private DicUtils dicUtils;
	
	
	/**
	 * 
	 * @描述:一键检测地址
	 * @作者:shaoxl@ucap.com.cn
	 * @时间:2016年12月13日上午10:36:17 
	 * @param paramMap
	 */
	@Override
	public void getSelfAddress(Map<String, Object> paramMap) {
		try {
			String encryptData = "";
			String type=(String) paramMap.get("type");
			DatabaseInfo databaseInfo=(DatabaseInfo) paramMap.get("databaseInfo");
			String siteCode =databaseInfo.getSiteCode();
			String vcode =databaseInfo.getVcode();
			String siteName=databaseInfo.getName()!=null?databaseInfo.getName():"";	//网站名称
			String province=databaseInfo.getProvince()!=null?databaseInfo.getProvince():"";//省
			String city=databaseInfo.getCity()!=null?databaseInfo.getCity():"";//市
			String county=databaseInfo.getCounty()!=null?databaseInfo.getCounty():"";//县
			String level=databaseInfo.getLevel()!=null?databaseInfo.getLevel():"";//级别
			Integer isorganizational=databaseInfo.getIsorganizational();//非门户/门户
			Integer iscost=databaseInfo.getIscost();	//是否收费
			String director=databaseInfo.getDirector()!=null?databaseInfo.getDirector():"";//网站主办单位
			String url=databaseInfo.getUrl()!=null?databaseInfo.getUrl():"";//网站首页
			String jumpUrl=databaseInfo.getJumpUrl()!=null?databaseInfo.getJumpUrl():"";////首页跳转地址
			String linkman=databaseInfo.getLinkmanName()!=null?databaseInfo.getLinkmanName():"";
			String tel=databaseInfo.getMobile2()!=null?databaseInfo.getMobile2():"";//联系人办公电话
			String mobile= databaseInfo.getTelephone2()!=null?databaseInfo.getTelephone2():"";//联系人手机
			String mail=databaseInfo.getEmail2()!=null?databaseInfo.getEmail2():"";
			if (StringUtils.isNotEmpty(type)) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				if (type.equals("1")) {
					if (jumpUrl != null && !jumpUrl.equals("")) {
						dataMap.put("url", jumpUrl);
					} else {
						dataMap.put("url", url);
					}
					// 把需要的参数放到Map中
					dataMap.put("siteCode", siteCode);
					// 请求时间,格式为yyyyMMddHHmmss,有效时间暂定为1小时
					dataMap.put("time", DateUtils.formatShortDateTime(new Date()));
					// 限制使用是否打开,默认为0 关闭 ,1:开启
					dataMap.put("confineStatus", dicUtils.getValue("oneSelfTest_ConfineStatus"));
					// 每日限制使用次数,默认为3
					dataMap.put("confineTime", dicUtils.getValue("oneSelfTest_ConfineTime"));
					// debug开关 0：关闭(默认)，1：开启
					dataMap.put("debug", dicUtils.getValue("oneSelfTest_Debug"));
					encryptData = StringEncrypt.encrypt(JSONObject.fromObject(dataMap).toString(),
							StringEncrypt.PASSWORD_CRYPT_KEY);
					paramMap.put("encryptData", encryptData);
				} else if (type.equals("2")) {
					// 云搜索
					String requestUrl = "siteCode$$$" + siteCode + "@@@@@siteName$$$" + siteName
							+ "@@@@@province$$$" + province + "@@@@@city$$$" + city + "@@@@@county$$$" + county
							+ "@@@@@level$$$" + level + "@@@@@isorganizational$$$" + isorganizational
							+ "@@@@@iscost$$$" + iscost + "@@@@@director$$$" + director + "@@@@@url$$$" + url
							+ "@@@@@jumpUrl$$$" + jumpUrl + "@@@@@linkman$$$" + linkman + "@@@@@tel$$$" + tel
							+ "@@@@@mobile$$$" + mobile + "@@@@@mail$$$" + mail;
					encryptData = StringEncrypt.encrypt(requestUrl, StringEncrypt.PASSWORD_CRYPT_KEY);
					paramMap.put("encryptData", encryptData);
				} else if (type.equals("3")) {
					List<Map<String, Object>> infoList=new ArrayList<Map<String,Object>>();
					Map<String, Object> dataOrgMap = new HashMap<String, Object>();
					dataOrgMap.put("siteCode", siteCode);
					dataOrgMap.put("vcode", vcode);
					dataOrgMap.put("siteName", siteName);
					dataOrgMap.put("province", province);
					dataOrgMap.put("city", city);
					dataOrgMap.put("county", county);
					dataOrgMap.put("level", level);
					dataOrgMap.put("isorganizational", isorganizational);
					dataOrgMap.put("iscost", iscost);
					dataOrgMap.put("director", director);
					dataOrgMap.put("url", url);
					dataOrgMap.put("jumpUrl", jumpUrl);
					dataOrgMap.put("linkman", linkman);
					dataOrgMap.put("tel", tel);
					dataOrgMap.put("mobile", mobile);
					dataOrgMap.put("mail", mail);
					dataOrgMap.put("isOrg", 0);
					
					infoList.add(dataOrgMap);
					
					encryptData = JSONArray.fromObject(infoList).toString();
					paramMap.put("encryptData", DesTwo.encryptDES(encryptData, null));
				} else {
					paramMap.put("encryptData", encryptData);
				}
			} else {
				paramMap.put("encryptData", encryptData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void getExperienceAddress(Map<String, Object> paramMap) {
		try {
			String encryptData = "";
			String type=(String) paramMap.get("type");
			DatabaseOrgInfo databaseOrgInfo=(DatabaseOrgInfo) paramMap.get("databaseOrgInfo");
			List<DatabaseInfo> infoList=(List<DatabaseInfo>) paramMap.get("infoList");
			String siteCode =databaseOrgInfo.getSiteCode();
			String vcode =databaseOrgInfo.getVcode();
			String siteName=databaseOrgInfo.getName()!=null?databaseOrgInfo.getName():"";	//网站名称
			String mobile= databaseOrgInfo.getLinkmanPhone()!=null?databaseOrgInfo.getLinkmanPhone():"";//联系人手机
			String mail=databaseOrgInfo.getLinkmanEmail()!=null?databaseOrgInfo.getLinkmanEmail():"";
			String linkmanName=databaseOrgInfo.getLinkmanName()!=null?databaseOrgInfo.getLinkmanName():"";//联系人名称
			if (StringUtils.isNotEmpty(type)) {
				List<Map<String, Object>> siteList=new ArrayList<Map<String,Object>>();
				Map<String, Object> dataOrgMap = new HashMap<String, Object>();
				if (type.equals("3")) {
					dataOrgMap.put("siteCode", siteCode);//加密账号
					dataOrgMap.put("siteName", siteName);
					dataOrgMap.put("vcode", vcode);//加密密码
					dataOrgMap.put("province", "");
					dataOrgMap.put("city", "");
					dataOrgMap.put("county", "");
					dataOrgMap.put("level", "");
					dataOrgMap.put("isorganizational", "");
					dataOrgMap.put("iscost", "");
					dataOrgMap.put("director", "");
					dataOrgMap.put("url", "");
					dataOrgMap.put("jumpUrl", "");
					dataOrgMap.put("linkman", linkmanName);
					dataOrgMap.put("tel", "");
					dataOrgMap.put("mobile", mobile);
					dataOrgMap.put("mail", mail);
					dataOrgMap.put("isOrg", 1);
					
					siteList.add(dataOrgMap);
					
					if(infoList!=null && infoList.size()>0){
						System.out.println("==================impl  infoList"+infoList.size());
						for (int i = 0; i < infoList.size(); i++) {
							DatabaseInfo baseInfo=infoList.get(i);
							Map<String, Object> dataTbMap = new HashMap<String, Object>();
							dataTbMap.put("siteCode", baseInfo.getSiteCode()!=null?baseInfo.getSiteCode():"");//加密账号
							dataTbMap.put("siteName", baseInfo.getName()!=null?baseInfo.getName():"");
							dataTbMap.put("vcode", baseInfo.getVcode()!=null?baseInfo.getVcode():"");//加密密码
							dataTbMap.put("province", baseInfo.getProvince()!=null?baseInfo.getProvince():"");
							dataTbMap.put("city", baseInfo.getCity()!=null?baseInfo.getCity():"");
							dataTbMap.put("county", baseInfo.getCounty()!=null?baseInfo.getCounty():"");
							dataTbMap.put("level", baseInfo.getLevel()!=null?baseInfo.getLevel():"");
							dataTbMap.put("isorganizational", baseInfo.getIsorganizational());
							dataTbMap.put("iscost", baseInfo.getIscost());
							dataTbMap.put("director", baseInfo.getDirector());
							dataTbMap.put("url", baseInfo.getUrl());
							dataTbMap.put("jumpUrl", baseInfo.getJumpUrl());
							dataTbMap.put("linkman", baseInfo.getLinkmanName()!=null?baseInfo.getLinkmanName():"");
							dataTbMap.put("tel", baseInfo.getTelephone2()!=null?baseInfo.getTelephone2():"");
							dataTbMap.put("mobile", baseInfo.getMobile2()!=null?baseInfo.getMobile2():"");
							dataTbMap.put("mail", baseInfo.getEmail2()!=null?baseInfo.getEmail2():"");
							dataTbMap.put("isOrg", 0);//填报单位
							
							siteList.add(dataTbMap);
						}
					}
					encryptData = JSONArray.fromObject(siteList).toString();
					
					System.out.println("encryptData================="+encryptData);
					paramMap.put("encryptData", DesTwo.encryptDES(encryptData, null));//对json字符串进行加密
				}else {
					paramMap.put("encryptData", DesTwo.encryptDES(encryptData, null));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public List<DatabaseInfo> getSiteInfoByTree(Map<String, Object> paramMap) {
		return databaseInfoDao.getSiteInfoByTree(paramMap);
	}

	
	
	@Override
	public void add(DatabaseInfo databaseInfo) {
		databaseInfoDao.add(databaseInfo);
	}

	@Override
	public DatabaseInfo get(Integer id) {
		return databaseInfoDao.get(id);
	}

	@Override
	public void update(DatabaseInfo databaseInfo) {
		databaseInfoDao.update(databaseInfo);
	}

	@Override
	public void delete(Integer id) {
		databaseInfoDao.delete(id);
	}

	@Override
	public PageVo<DatabaseInfo> query(DatabaseInfoRequest request) {
		List<DatabaseInfo> databaseInfo = queryList(request);

		PageVo<DatabaseInfo> pv = new PageVo<DatabaseInfo>();
		int count = queryCount(request);

		pv.setData(databaseInfo);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCount(DatabaseInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getDatabaseLinkList()!=null && request.getDatabaseLinkList().size()>0){
			param.put("databaseLinkList", request.getDatabaseLinkList());
		}
		if(request.getDatabaseTreeList()!=null && request.getDatabaseTreeList().size()>0){
			param.put("databaseTreeList", request.getDatabaseTreeList());
		}
		
		return databaseInfoDao.queryCount(param);
	}

	@Override
	public List<DatabaseInfo> queryList(DatabaseInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		if(request.getRelationsPeriodList()!=null && request.getRelationsPeriodList().size()>0){
			param.put("relationsPeriodList", request.getRelationsPeriodList());
		}
		if(request.getDatabaseLinkList()!=null && request.getDatabaseLinkList().size()>0){
			param.put("databaseLinkList", request.getDatabaseLinkList());
		}
		if(request.getDatabaseTreeList()!=null && request.getDatabaseTreeList().size()>0){
			param.put("databaseTreeList", request.getDatabaseTreeList());
		}
		List<DatabaseInfo> list = databaseInfoDao.query(param);
		return list;
	}
	@Override
	public List<DatabaseInfo> queryListDatabaseInfo(Map<String, Object> map) {
		return databaseInfoDao.query(map);
	}
	
	@Override
	public List<String> queryId(DatabaseInfoRequest request){
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<String> list = databaseInfoDao.queryId(param);
		return list;
	}

	@Override
	public DatabaseInfo getUsers(String siteCode) {
		return databaseInfoDao.getUsers(siteCode);
	}

	@Override
	public DatabaseInfo getUser(Map<String, Object> params) {
		return databaseInfoDao.getUser(params);
	}

//	@Override
//	public List<Permission> getAdmin() {
//		return databaseInfoDao.getAdmin();
//	}
//
//	@Override
//	public List<Permission> getGuest(String userName) {
//		return databaseInfoDao.getGuest(userName);
//	}

	@Override
	public String sendEmail(String email) throws EmailException {
		// 根据邮箱查找用户
		// Users user = usersDao.getUserByEmail(email);
		// 如果用户不存在说明邮箱输入有错误
		// if (user == null) {
		// throw new EmailException("邮箱不存在或者输入有错误，请核对后重新输入！");
		// }

		// 向用户发送邮件

		// 加载配置文件
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		MailSenderInfo info = new MailSenderInfo();// 封装对象

		// 邮件服务器相关设置
		String from = prop.getProperty("from");
		// 设置邮件服务器
		info.setMailServerHost(this.getHost(from));
		// 设置用户名
		info.setUserName(prop.getProperty("username"));
		// 设置密码
		info.setPassword(prop.getProperty("password"));

		// 设置发送源
		info.setFromAddress(prop.getProperty("from"));
		// 设置发送目的的
		info.setToAddress(email);
		// 设置主题
		info.setSubject(prop.getProperty("subject"));
		// 设置内容:使用占位符替换相关的内容(使用系统随机生成验证码)
		String emailCaptcha = this.createRandom(true, 6);

		info.setContent(MessageFormat.format(prop.getProperty("content"),
				emailCaptcha));
		// 设置邮件校验：必须加，否则出现：530 Authentication required
		info.setValidate(true);
		// 发送邮件
		boolean flag = false;
		try {
			flag = SimpleMailSender.sendHtmlMail(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return emailCaptcha;
		}
		return null;

	}

	/**
	 * 获得邮件服务器
	 * 
	 * @param email
	 *            ：邮件的接收方
	 * @return
	 * @throws EmailException
	 */
	private String getHost(String email) throws EmailException {
		Map<String, String> hostMap = this.getSupportEmail();
		Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
		Matcher matcher = pattern.matcher(email);
		String key = "unSupportEmail";
		if (matcher.find()) {
			key = "smtp." + matcher.group(1);
		}
		if (hostMap.containsKey(key)) {
			return hostMap.get(key);
		} else {
			throw new EmailException("不支持的邮箱类型");
		}
	}

	/**
	 * 获得系统支持的邮件类型
	 * 
	 * @return
	 */
	public Map<String, String> getSupportEmail() {

		Map<String, String> hostMap = new HashMap<String, String>();
		// 126
		hostMap.put("smtp.126", "smtp.126.com");
		// qq
		hostMap.put("smtp.qq", "smtp.qq.com");

		// 163
		hostMap.put("smtp.163", "smtp.163.com");

		// sina
		hostMap.put("smtp.sina", "smtp.sina.com.cn");

		// tom
		hostMap.put("smtp.tom", "smtp.tom.com");

		// 263
		hostMap.put("smtp.263", "smtp.263.net");

		// yahoo
		hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");

		// hotmail
		hostMap.put("smtp.hotmail", "smtp.live.com");

		// gmail
		hostMap.put("smtp.gmail", "smtp.gmail.com");
		hostMap.put("smtp.port.gmail", "465");

		// ucmp

		return hostMap;
	}

	/**
	 * @Description:java随机生成验证码
	 * @author kefan-- 2015-11-14 下午4:25:51
	 * @param numberFlag
	 *            :生成验证码的种类： true:生成的验证码为数字 false：生成的验证码为字母
	 * @param length
	 *            ：验证码的长度
	 * @return
	 */
	private String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	@Override
	public void updatePassword(DatabaseInfo databaseInfo) {

		databaseInfoDao.modifyPassword(databaseInfo);

	}

	@Override
	public List<MenuModel> getAdminMenuModel() {

		return null;
	}

	@Override
	public List<MenuModel> getGuestMenuModel(Integer userId) {

		return null;
	}

	@Override
	public DatabaseInfo getUsersByEmailAndSiteCode(DatabaseInfo databaseInfo) {
		return this.databaseInfoDao.getUsersByEmailAndSiteCode(databaseInfo);

	}

	@Override
	public List<DatabaseInfo> getChildList(String parentSiteCode) {
		return this.databaseInfoDao.getChildList(parentSiteCode);
		
	}

	@Override
	public List<DatabaseInfo> getChildByLike(Map<String, String> params) {
		
		return this.databaseInfoDao.getChildByLike(params);
	}

	@Override
	public int countChildByLike(Map<String, String> params) {
		return this.databaseInfoDao.countByLike(params);
	}

	@Override
	public int getChildCount(String parentSiteCode) {
		return this.databaseInfoDao.getChildCount(parentSiteCode);
	}

	@Override
	public List<DatabaseInfo> getDatabaseinfoByLike(String siteCode) {
		return this.databaseInfoDao.getDatabaseInfoByLike(siteCode);
	}

	@Override
	public int countDatabaseinfoByLike(String siteCode) {
		return this.databaseInfoDao.countDatabaseInfoByLike(siteCode);
	}
	@Override
	public List<DatabaseInfo> getLocalData(Map<String, Object> map) {
		return databaseInfoDao.getLocalData(map);
	}
//抽查 地方树结构  旧方法 注释掉
//	@Override
//	public List<TreeVo> queryLocalTree(Map<String, Object> map) {
//		return databaseInfoDao.queryLocalTree(map);
//	}
	
	@Override
	public DatabaseInfo findByDatabaseInfoCode(String siteCode) {
		return databaseInfoDao.findByDatabaseInfoCode(siteCode);
	}

	@Override
	public List<DatabaseInfo> querySiteCodeList(Map<String, Object> paramMap) {
		return databaseInfoDao.querySiteCodeList(paramMap);
	}

	
	@Override
	public List<String> querySortCodeList(){
		List<String> list = databaseInfoDao.querySortCodeList();
		return list;
	}

	@Override
	public int queryCountByMap(Map<String, Object> map) {
		return databaseInfoDao.queryCountByMap(map);
	}

	@Override
	public List<DatabaseInfoRequest> queryLevelCountByMap(Map<String, Object> map) {
		return databaseInfoDao.queryLevelCountByMap(map);
	}

	@Override
	public List<DatabaseInfoRequest> queryShiAndXian(Map<String, Object> params) {
		return databaseInfoDao.queryShiAndXian(params);
	}

	@Override
	public List<DatabaseInfo> queryRandomSite(Map<String, Object> paramMap) {
		return databaseInfoDao.queryRandomSite(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryLevelRandomSite(Map<String, Object> paramMap) {
		return databaseInfoDao.queryLevelRandomSite(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryProvince(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryProvince(paramMap);
	}

	@Override
	public List<DatabaseInfo> querySublevel(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.querySublevel(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryBm(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryBm(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryBmSublevel(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryBmSublevel(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryGateway(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryGateway(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryBmGateway(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryBmGateway(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryLevelDepartment(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryLevelDepartment(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryAllSite(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryAllSite(paramMap);
	}

	@Override
	public Integer queryMinLevel(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return databaseInfoDao.queryMinLevel(paramMap);
	}

	@Override
	public List<DatabaseInfoRequest> queryProvinceCountByMap(Map<String, Object> paramMap) {
		return databaseInfoDao.queryProvinceCountByMap(paramMap);
	}
	@Override
	public List<DatabaseInfoRequest> queryProvinceScan(Map<String, Object> paramMap){
		return databaseInfoDao.queryProvinceScan(paramMap);
	}
	@Override
	public List<DatabaseInfoRequest> queryCountBm(Map<String, Object> paramMap) {
		return databaseInfoDao.queryCountBm(paramMap);
	}

	@Override
	public List<DatabaseInfo> queryIsScan(Map<String, Object> pMap) {
		
		return databaseInfoDao.queryIsScan(pMap);
	}
	
	@Override
	public PageVo<DatabaseInfo> queryTemp(DatabaseInfoRequest request) {
		List<DatabaseInfo> databaseInfo = queryListTemp(request);

		PageVo<DatabaseInfo> pv = new PageVo<DatabaseInfo>();
		int count = queryCountTemp(request);

		pv.setData(databaseInfo);
		pv.setPageNo(request.getPageNo());
		pv.setPageSize(request.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	@Override
	public int queryCountTemp(DatabaseInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		return databaseInfoDao.queryCountTemp(param);
	}
	
	@Override
	public List<DatabaseInfo> queryListTemp(DatabaseInfoRequest request) {
		Map<String, Object> param = QueryUtils.getQueryMap(request);
		List<DatabaseInfo> list = databaseInfoDao.queryTemp(param);
		return list;
	}


	@Override
	public List<DatabaseInfo> queryListJoinDatabaseTree(
			HashMap<String, Object> hashMap) {
		return databaseInfoDao.queryListJoinDatabaseTree(hashMap);
	}
	
	@Override
	public PageVo<DatabaseInfo> getDatabaseInfoBycode(DatabaseInfoRequest req) {
		Map<String, Object> param = QueryUtils.getQueryMap(req);
		List<DatabaseInfo> list = databaseInfoDao.getDatabaseInfoBycode(param);
		PageVo<DatabaseInfo> pv = new PageVo<DatabaseInfo>();

		Integer paging = req.getPaging();
		int count = getDatabaseInfoCount(req);
		if (paging == 1) {
			if (count > 100) {
				count = 100;
			}
		}

		pv.setData(list);
		pv.setPageNo(req.getPageNo());
		pv.setPageSize(req.getPageSize());
		pv.setRecordSize(count);
		return pv;
	}

	public int getDatabaseInfoCount(DatabaseInfoRequest sRequest) {
		Map<String, Object> param = QueryUtils.getQueryMap(sRequest);
		return databaseInfoDao.getDatabaseInfoCount(param);
	}

	public DicUtils getDicUtils() {
		return dicUtils;
	}
	public void setDicUtils(DicUtils dicUtils) {
		this.dicUtils = dicUtils;
	}
	

}
