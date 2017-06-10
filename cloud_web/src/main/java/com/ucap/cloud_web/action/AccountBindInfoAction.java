package com.ucap.cloud_web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.constant.AccountBindStatus;
import com.ucap.cloud_web.constant.ConnectionState;
import com.ucap.cloud_web.constant.QuestionType;
import com.ucap.cloud_web.constant.SexType;
import com.ucap.cloud_web.dto.AccountBindIdRequest;
import com.ucap.cloud_web.dto.AccountBindInfoLogRequest;
import com.ucap.cloud_web.dto.AccountBindInfoRequest;
import com.ucap.cloud_web.dto.ConnectionBusinessDetailRequest;
import com.ucap.cloud_web.entity.AccountBindId;
import com.ucap.cloud_web.entity.AccountBindInfo;
import com.ucap.cloud_web.entity.AccountBindInfoLog;
import com.ucap.cloud_web.entity.ConnectionBusinessDetail;
import com.ucap.cloud_web.service.IAccountBindIdService;
import com.ucap.cloud_web.service.IAccountBindInfoLogService;
import com.ucap.cloud_web.service.IAccountBindInfoService;
import com.ucap.cloud_web.servlet.message.Token;
import com.ucap.cloud_web.servlet.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;

/**
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class AccountBindInfoAction extends BaseAction {
	
	@Autowired
	private IAccountBindInfoService accountBindInfoServiceImpl;
	@Autowired
	private IAccountBindInfoLogService accountBindInfoLogServiceImpl;
	@Autowired
	private IAccountBindIdService accountBindIdServiceImpl;
	
	//https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
	/**
	 * log日志加载
	 */
	private static Log logger =  LogFactory.getLog(AccountBindInfoAction.class);
	String accountUrl="https://api.weixin.qq.com/cgi-bin/user/info?";
	
	String accountListUrl="https://api.weixin.qq.com/cgi-bin/user/get?";
	
	/**调接口数据 
	 * @Description: 业务系统连通性excel导出
	 * @author qinjy    
	 * https://jgtest.kaipuyun.cn/cloud_web/accountBindInfo_accountBindInfoExcel.action         
	*/
	public  void accountBindInfoExcel(){
		
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[]{"序号","用户的唯一标识","昵称","用户性别","用户所在城市","用户所在省份","用户所在国家","用户的语言","用户头像","用户关注时间","公众号运营者对粉丝的备注","用户所在的分组ID","用户被打上的标签ID列表"};
		list.add(obj1);
		String fileName ="微信公众号-绑定账户列表("+DateUtils.formatStandardDate(new Date())+").xls";
		String title = "绑定账户详细信息"; 
		
		/**
		 * 查询绑定账户信息表获取所有  绑定的信息
		 */
		AccountBindInfoRequest accountRequest=new AccountBindInfoRequest();
		accountRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());//绑定状态
		
		try {
			List<AccountBindInfo> accList=accountBindInfoServiceImpl.queryList(accountRequest);
			if(accList!=null && accList.size()>0){
				logger.info("accList======"+accList.size());
				//获取accessToken
				Token token=CommonUtils.getToken();
				String accessToken=token.getAccessToken();
				accountUrl+="access_token="+accessToken;
				logger.info("=========accountUrl:"+accountUrl);
				for (int i = 0; i < accList.size(); i++) {
					
					AccountBindInfo accountBindInfo=accList.get(i);
					String openId=accountBindInfo.getOpenId();
					String url=accountUrl+"&openid="+openId+"&lang=zh_CN";
					
					logger.info("url========"+url);
					JSONObject jsonStr=CommonUtils.httpRequest(url, "GET", null);
					
			    	if(jsonStr!=null){
						Object[] obj = new Object[13];
						obj[0] = i+1;
						obj[1] = jsonStr.get("openid")!=null?jsonStr.get("openid"):"";
			    		String nickname=(String) jsonStr.get("nickname");
			    		//过滤掉微信昵称中的emoji表情字符串
			    		nickname=CommonUtils.removeFaceCharacter(nickname);
			    		obj[2] = nickname;
			    		obj[3] = jsonStr.get("sex")!=null?SexType.getNameByCode(String.valueOf(jsonStr.get("sex"))):"";
			    		obj[4] = jsonStr.get("city")!=null?jsonStr.get("city"):"";
			    		obj[5] = jsonStr.get("province")!=null?jsonStr.get("province"):"";
			    		obj[6] = jsonStr.get("country")!=null?jsonStr.get("country"):"";
			    		obj[7] = jsonStr.get("language")!=null?jsonStr.get("language"):"";
			    		obj[8] = jsonStr.get("headimgurl")!=null?jsonStr.get("headimgurl"):"";
			    		obj[9] = jsonStr.get("subscribe_time")!=null?getStrTime(String.valueOf(jsonStr.get("subscribe_time"))):"";
			    		obj[10] = jsonStr.get("remark")!=null?jsonStr.get("remark"):"";
			    		obj[11] = jsonStr.get("groupid")!=null?jsonStr.get("groupid"):"";
			    		obj[12] = jsonStr.get("tagid_list")!=null?jsonStr.get("tagid_list"):"";
			    		
			    		
			    		list.add(obj);
			    	}
				}
				ExportExcel.accountBindInfoExcel(fileName, title, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**调接口数据 
	 * @Description: 业务系统连通性excel导出
	 * @author qinjy    
	 * https://jgtest.kaipuyun.cn/cloud_web/accountBindInfo_accountBindInfoExcel2.action?pageNum=1&pageSize=100
	 * http://localhost/cloud_web/accountBindInfo_accountBindInfoExcel2.action?pageNum=1&pageSize=100
	*/
	public  void accountBindInfoExcel2(){
		
		
		logger.info("accountBindInfoExcel2   begin========");
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[]{"序号","用户的唯一标识","昵称","用户性别","用户所在城市","用户所在省份","用户所在国家","用户的语言","用户头像","用户关注时间","公众号运营者对粉丝的备注","用户所在的分组ID","用户被打上的标签ID列表","是否绑定"};
		list.add(obj1);
		String fileName ="微信公众号-绑定账户列表("+DateUtils.formatStandardDate(new Date())+").xls";
		String title = "绑定账户详细信息"; 
		try {
			
			AccountBindInfoLogRequest accountRequest=new AccountBindInfoLogRequest();
			//设置排序字段
			List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
			queryOrderList.add(siteQueryOrder);
			accountRequest.setQueryOrderList(queryOrderList);
			accountRequest.setPageNo(Integer.valueOf(pageNum));
			accountRequest.setPageSize(Integer.valueOf(pageSize));
			List<AccountBindInfoLog>  logList=accountBindInfoLogServiceImpl.queryList(accountRequest);
			if(logList!=null && logList.size()>0){
				for (int i = 0; i < logList.size(); i++) {
					AccountBindInfoLog accountLog=logList.get(i);
					Object[] obj = new Object[14];
					obj[0] = i+1;
					obj[1] = accountLog.getOpenid()!=null?accountLog.getOpenid():"";
		    		
		    		obj[2] = accountLog.getNickname()!=null?accountLog.getNickname():"";
		    		obj[3] = SexType.getNameByCode(String.valueOf(accountLog.getSex()));
		    		obj[4] = accountLog.getCity()!=null?accountLog.getCity():"";
		    		obj[5] = accountLog.getProvince()!=null?accountLog.getProvince():"";
		    		obj[6] = accountLog.getCountry()!=null?accountLog.getCountry():"";
		    		obj[7] = accountLog.getLanguage()!=null?accountLog.getLanguage():"";
		    		obj[8] = accountLog.getHeadimgurl()!=null?accountLog.getHeadimgurl():"";
		    		obj[9] =  accountLog.getSubscribeTime()!=null?accountLog.getSubscribeTime():"";
		    		obj[10] = accountLog.getRemark()!=null?accountLog.getRemark():"";
		    		obj[11] = accountLog.getGroupid()!=null?accountLog.getGroupid():"";
		    		obj[12] = accountLog.getTagidList()!=null?accountLog.getTagidList():"";
		    		
		    		String bindStatus="未绑定";//默认未绑定
		    		//关联绑定账户信息表  添加一个是否绑定标记
		    		AccountBindInfoRequest infoRequest=new AccountBindInfoRequest();
		    		infoRequest.setOpenId(accountLog.getOpenid());
		    		infoRequest.setStatus(AccountBindStatus.ACCOUNT_BIND.getCode());
		    		
		    		List<AccountBindInfo> infoList=accountBindInfoServiceImpl.queryList(infoRequest);
		    		if(infoList !=null && infoList.size()>0){
		    			bindStatus="绑定";//绑定
		    		}
		    		obj[13] = bindStatus;
		    		
		    		list.add(obj);
				}
			}
			ExportExcel.accountBindInfoExcel(fileName, title, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加绑定账户信息表数据
	 * http://localhost/cloud_web/accountBindInfo_addAccountBindId.action
	 */
	public void addAccountBindId(){
		//获取accessToken
		Token token=CommonUtils.getToken();
		String accessToken=token.getAccessToken();
		/**
		 * 获取所有关注过该公众号的  用户
		 */
		accountListUrl+="access_token="+accessToken+"&next_openid=";
		JSONObject accountJson=CommonUtils.httpRequest(accountListUrl, "GET", null);
		if(accountJson !=null){
			JSONObject jsonData=(JSONObject) accountJson.get("data");
			String openIdList=String.valueOf(jsonData.get("openid"));
			String openIdList2=openIdList.substring(1, openIdList.length()-1);
			String[] openIdArr=openIdList2.split(",");
			for(int i=0;i<openIdArr.length;i++){
				String openId=openIdArr[i].replace("\"", "");
				logger.info("==========openId:"+openIdArr[i].replace("\"", ""));
				AccountBindId accountBindId=new AccountBindId();
				accountBindId.setOpenid(openId);
				accountBindIdServiceImpl.add(accountBindId);
			}
		}
	}
	
	/**
	 * 添加绑定账户信息表数据
	 * http://localhost/cloud_web/accountBindInfo_addAccountBindInfoLog.action?pageNum=1&pageSize=100
	 */
	public void addAccountBindInfoLog(){

		/**
		 * 获取所有关注过该公众号的  用户
		 */
		String pageNum=request.getParameter("pageNum");//当前页数
		String pageSize=request.getParameter("pageSize");//每个大小
		
		AccountBindIdRequest accIdRequest=new AccountBindIdRequest();
		//设置排序字段
		List<QueryOrder> queryOrderList=new ArrayList<QueryOrder>();
		QueryOrder siteQueryOrder=new QueryOrder("create_time",QueryOrderType.DESC);
		queryOrderList.add(siteQueryOrder);
		accIdRequest.setQueryOrderList(queryOrderList);
		accIdRequest.setPageNo(Integer.valueOf(pageNum));
		accIdRequest.setPageSize(Integer.valueOf(pageSize));
		
		List<AccountBindId> accIdList=accountBindIdServiceImpl.queryList(accIdRequest);
		if(accIdList !=null && accIdList.size()>0){
			//获取accessToken
			Token token=CommonUtils.getToken();
			String accessToken=token.getAccessToken();
			for(int i=0;i<accIdList.size();i++){
				String openId=accIdList.get(i).getOpenid();
				logger.info("==========openid:"+openId);
				String url=accountUrl+"access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
				logger.info("url========"+url);
				JSONObject jsonStr=CommonUtils.httpRequest(url, "GET", null);
		    	if(jsonStr!=null){
		    		AccountBindInfoLog accountLog=new AccountBindInfoLog();
		    		accountLog.setOpenid(openId);
		    		String nickname=(String) jsonStr.get("nickname");
		    		nickname=nickname.replaceAll("[\\x{10000}-\\x{10FFFF}]", "ucap");
		    		accountLog.setNickname(nickname);
		    		accountLog.setSex(Integer.valueOf(String.valueOf(jsonStr.get("sex"))));
		    		accountLog.setCity(String.valueOf(jsonStr.get("city")));
		    		accountLog.setProvince(String.valueOf(jsonStr.get("province")));
		    		accountLog.setCountry(String.valueOf(jsonStr.get("country")));
		    		accountLog.setLanguage(String.valueOf(jsonStr.get("language")));
		    		accountLog.setHeadimgurl(String.valueOf(jsonStr.get("headimgurl")));
		    		accountLog.setSubscribeTime(getStrTime(String.valueOf(jsonStr.get("subscribe_time"))));
		    		accountLog.setRemark(String.valueOf(jsonStr.get("remark")));
		    		accountLog.setGroupid(String.valueOf(jsonStr.get("groupid")));
		    		accountLog.setTagidList(String.valueOf(jsonStr.get("tagid_list")));
		    		accountBindInfoLogServiceImpl.add(accountLog);
		    	}
			}
		}
	}
	
	/**
	 * 时间戳转换为时间  yyyy-MM-dd HH:mm:ss
	 * @param cc_time
	 * @return
	 */
	public String getStrTime(String cc_time) {  
		String re_StrTime = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		long lcc_time = Long.valueOf(cc_time);  
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));  
		return re_StrTime;  
	}
	
/*	public static void main(String[] args) {
		
		String reg="/[\x{10000}-\x{10FFFF}]/u";
		
		
		
	}*/
}
