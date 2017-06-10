package com.ucap.cloud_web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.publics.util.page.PageVo;
import com.publics.util.page.QueryOrder;
import com.publics.util.page.QueryOrderType;
import com.publics.util.utils.DateUtils;
import com.publics.util.utils.PropertiesUtil;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.bizService.DatabaseTreeBizService;
import com.ucap.cloud_web.bizService.ILogCommonService;
import com.ucap.cloud_web.common.DicUtils;
import com.ucap.cloud_web.constant.ChannelNotUpdateType;
import com.ucap.cloud_web.constant.DatabaseInfoType;
import com.ucap.cloud_web.constant.DatabaseLinkType;
import com.ucap.cloud_web.constant.DatabaseTreeInfoType;
import com.ucap.cloud_web.constant.LogType;
import com.ucap.cloud_web.constant.TrueOrFalseType;
import com.ucap.cloud_web.constant.UserType;
import com.ucap.cloud_web.dto.ChannelPointRequest;
import com.ucap.cloud_web.dto.ChannelPointTempRequest;
import com.ucap.cloud_web.dto.DatabaseInfoRequest;
import com.ucap.cloud_web.dto.DatabaseOrgInfoRequest;
import com.ucap.cloud_web.dto.DatabaseTreeInfoRequest;
import com.ucap.cloud_web.dto.DicChannelRequest;
import com.ucap.cloud_web.dto.TaskDailyRequest;
import com.ucap.cloud_web.dtoResponse.ChannelPointResponse;
import com.ucap.cloud_web.dtoResponse.DatabaseInfoResponse;
import com.ucap.cloud_web.entity.ChannelPoint;
import com.ucap.cloud_web.entity.ChannelPointLog;
import com.ucap.cloud_web.entity.ChannelPointTemp;
import com.ucap.cloud_web.entity.DatabaseInfo;
import com.ucap.cloud_web.entity.DatabaseOrgInfo;
import com.ucap.cloud_web.entity.DatabaseTreeInfo;
import com.ucap.cloud_web.entity.DicChannel;
import com.ucap.cloud_web.entity.TaskDaily;
import com.ucap.cloud_web.service.IChannelPointLogService;
import com.ucap.cloud_web.service.IChannelPointService;
import com.ucap.cloud_web.service.IChannelPointTempService;
import com.ucap.cloud_web.service.IDatabaseInfoService;
import com.ucap.cloud_web.service.IDatabaseOrgInfoService;
import com.ucap.cloud_web.service.IDicChannelService;
import com.ucap.cloud_web.service.ITaskDailyService;
import com.ucap.cloud_web.service.IVerifyUrlLinkService;
import com.ucap.cloud_web.shiro.Constants;
import com.ucap.cloud_web.shiro.ShiroUser;
import com.ucap.cloud_web.util.CommonUtils;
import com.ucap.cloud_web.util.ExportExcel;
import com.ucap.cloud_web.util.HttpClientUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <p>
 * Description:管理信息
 * </p>
 * <p>
 * 
 * @Package：com.ucap.cloud_web.action </p>
 *                                    <p>
 *                                    Title: ManageInfoAction
 *                                    </p>
 *                                    <p>
 *                                    Company: 开普互联
 *                                    </p>
 *                                    <p>
 * @author：sunjq </p>
 *               <p>
 * @date：2015-11-12下午02:05:19 </p>
 */
@SuppressWarnings("serial")
@Controller
public class ManageInfoAction extends BaseAction {
	@Autowired
	private DicUtils dicUtils;
	@Autowired
	private IChannelPointService channelPointServiceImpl;
	@Autowired
	private IChannelPointTempService channelPointTempServiceImpl;
	@Autowired
	private IChannelPointLogService channelPointLogServiceImpl;
	@Autowired
	private IDicChannelService dicChannelServiceImpl;
	@Autowired
	private IVerifyUrlLinkService verifyUrlLinkServiceImpl;
	@Autowired
	private IDatabaseInfoService databaseInfoServiceImpl;
	@Autowired
	private IDatabaseOrgInfoService databaseOrgInfoServiceImpl;
	@Autowired
	private DatabaseTreeBizService databaseTreeBizServiceImpl;
	@Autowired
	private ILogCommonService logCommonServiceImpl;

	private static Log logger = LogFactory.getLog(ManageInfoAction.class);

	private DatabaseInfo databaseInfo;

	private DatabaseOrgInfo databaseOrgInfo;
	private static Properties prop = PropertiesUtil.getProperties("app.properties");
	
	/**************************************** 新管理-站点信息 start **********************************************/
	
	/**
	 * 
	 * @描述:联系人名录
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月18日下午8:09:12
	 */
	public String contact() {
		return "success";
	}

	/**
	 * 
	 * @描述:管理-本级部门数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月11日下午8:27:35
	 * @return
	 */
	public String department() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			String type = request.getParameter("type");
			if (StringUtils.isEmpty(type)) {
				type = "tp";
			}

			request.setAttribute("type", type);
			request.setAttribute("siteCode", siteCode);

			DatabaseTreeInfoRequest dbtRequest = new DatabaseTreeInfoRequest();
			dbtRequest.setOrgSiteCode(siteCode);
			dbtRequest.setIsexp(DatabaseInfoType.NORMAL.getCode());
			dbtRequest.setLayerType(DatabaseLinkType.ISORGANIZATIONAL.getCode());
			dbtRequest.setIsLink(DatabaseTreeInfoType.ISLINK.getCode());
			List<DatabaseTreeInfo> treeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(dbtRequest);
			if (CollectionUtils.isNotEmpty(treeList)) {
				String code = treeList.get(0).getSiteCode();
				if (StringUtils.isNotEmpty(code)) {
					databaseInfo = databaseInfoServiceImpl.getUsers(code);
				} else {
					databaseInfo = null;
				}
			} else {
				databaseInfo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	/**
	 * 
	 * @描述:填报管理-本级部门数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月11日下午8:27:35
	 * @return
	 */
	public String departmentTB() {
		try {

			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			
			String siteCodeJump = request.getParameter("siteCode"); //siteCode不为空说明是从 组织单位跳转过来的
			if(StringUtils.isNotEmpty(siteCodeJump)){
				siteCode = siteCodeJump;
				setCurrentShiroUser(siteCode);  //变更当前用户
			}else{
				ShiroUser shiroUser = getCurrentUserInfo();
				if (shiroUser != null) {
					siteCode = shiroUser.getChildSiteCode();
				}
			}
			String type = request.getParameter("type");
			if (StringUtils.isEmpty(type)) {
				type = "tp";
			}

			request.setAttribute("type", type);
			request.setAttribute("siteCode", siteCode);

			if (StringUtils.isNotEmpty(siteCode)) {
				databaseInfo = databaseInfoServiceImpl.getUsers(siteCode);
			} else {
				databaseInfo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	/**
	 * 
	 * @描述:管理-本机部门-栏目数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月13日下午3:30:22
	 */
	public void getChannelPointInfo() {
		String siteCode = request.getParameter("siteCode");// 查询databaseInfo表
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		try {
			if (StringUtils.isEmpty(siteCode)) {
				resultMap.put("errorMsg", "页面传递的网站标识码不能为空");
				resultMap.put("body", items);
			} else {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("siteCode", siteCode);
				List<ChannelPointResponse> cpList = channelPointServiceImpl.getChannelPointInfo(hashMap);
				if (CollectionUtils.isNotEmpty(cpList)) {
					for (ChannelPointResponse cp : cpList) {
						Map<String, Object> item = new HashMap<String, Object>();

						String channelName = cp.getChannelName();// 栏目名称
						if (StringUtils.isEmpty(channelName)) {
							channelName = "";
						}
						String jumpUrl = cp.getJumpPageUrl();// 跳转URL
						if (StringUtils.isEmpty(jumpUrl)) {
							jumpUrl = "无";
						}
						String updateTime = getDichUpdateTime(cp.getDichId());
						item.put("id", cp.getId());
						item.put("channelName", channelName);
						item.put("channelUrl", cp.getChannelUrl());
						item.put("jumpUrl", jumpUrl);
						item.put("updateTime", updateTime);
						item.put("dicName", cp.getDicName());
						item.put("dichName", cp.getDichName());
						item.put("status", cp.getStatus());
						item.put("linkStatus", cp.getLinkStatus());
						items.add(item);
					}
				}
				resultMap.put("success", "true");
				resultMap.put("body", items);
				resultMap.put("items", items);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "更新数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:管理-本机部门-栏目数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月13日下午3:30:22
	 */
	public void getChannelPointInfoExcel() {
		String siteCode = request.getParameter("siteCode");// 查询databaseInfo表

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String fileName = "重点监测栏目-系统(" + DateUtils.formatStandardDate(new Date()) + ").xls";
		String title = "重点监测栏目/系统";
		String sheetTitle = "重点监测栏目-系统（导出）";

		Object[] obj1 = new Object[] { "序号", "名称", "URL", "跳转URL", "更新期限","类别", "子类" };
		list.add(obj1);
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("siteCode", siteCode);
			List<ChannelPointResponse> cpList = channelPointServiceImpl.getChannelPointInfo(hashMap);
			int num = 0;
			if (CollectionUtils.isNotEmpty(cpList)) {
				for (ChannelPointResponse cp : cpList) {
					num++;
					Object[] obj = new Object[7];

					String channelName = cp.getChannelName();// 栏目名称
					if (StringUtils.isEmpty(channelName)) {
						channelName = "";
					}
					String jumpUrl = cp.getJumpPageUrl();// 跳转URL
					if (StringUtils.isEmpty(jumpUrl)) {
						jumpUrl = "无";
					}

					obj[0] = num;
					obj[1] = channelName;
					obj[2] = cp.getChannelUrl();
					obj[3] = jumpUrl;
					obj[4] = getDichUpdateTime(cp.getDichId());
					obj[5] = cp.getDicName();
					obj[6] = cp.getDichName();
					list.add(obj);
				}
			}
			ExportExcel.channelExcel(fileName, title, sheetTitle, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @描述:栏目删除
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月13日下午7:51:53
	 */
	public void delChannelPoint() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 获取当前登录用户的sitecode
		String userId = getCurrentUserInfo().getSiteCode();
		String ids = request.getParameter("ids");
		try {
			if (StringUtils.isNotEmpty(ids)) {
				String[] split = ids.split(",");
				for (String id : split) {
					if (StringUtils.isNotEmpty(id)) {
						Integer cpId = Integer.valueOf(id);
						ChannelPoint channelPoint = channelPointServiceImpl.get(cpId);
						if (channelPoint != null) {
							channelPoint.setStatus(-1);// 标记删除
							channelPointServiceImpl.update(channelPoint);

							// 根据SiteCode和url查询channelPointTemp
							ChannelPointTempRequest pointTempRe = new ChannelPointTempRequest();
							pointTempRe.setSiteCode(channelPoint.getSiteCode());
							pointTempRe.setChannelUrl(channelPoint.getChannelUrl());
							pointTempRe.setPageSize(Integer.MAX_VALUE);
							List<ChannelPointTemp> queryList = channelPointTempServiceImpl.queryList(pointTempRe);
							for (ChannelPointTemp channelPointTemp : queryList) {
								// 更新id对应数据
								channelPointTempServiceImpl.delete(channelPointTemp.getId());
							}

							// 操作channelPointLog表记录删除操作
							ChannelPointLog channelPointLog = new ChannelPointLog();
							// 记录日志参数
							channelPointLog.setSiteCode(channelPoint.getSiteCode());
							channelPointLog.setChannelName(channelPoint.getChannelName());
							channelPointLog.setChannelUrl(channelPoint.getChannelUrl());
							channelPointLog.setType(1);
							channelPointLog.setUserId(userId);
							channelPointLogServiceImpl.add(channelPointLog);
						}
						// 修改taskDaily 是否启动状态
						TaskDaily taskDaily = getTaskDaily(channelPoint);
						if (null != taskDaily && taskDaily.getIsStart() == 0) {
							taskDaily.setIsStart(1);
							taskDailyServiceImpl.update(taskDaily);
						}
					}
				}
				resultMap.put("success", "删除成功");
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "删除失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * 
	 * @描述:跳转下级信息页面
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月14日上午09:19:21
	 * @return
	 */
	public String lowerLevel() {
		String level = request.getParameter("level"); // 级别
		String state = request.getParameter("state"); // 1正常，2例外，3关停
		request.setAttribute("level", level);
		request.setAttribute("state", state);
		return "success";
	}

	/**
	 * 
	 * @描述:获取下级信息数据
	 * @作者:qinjy@ucap.com.cn
	 * @时间:2017年4月14日上午09:29:21
	 * @return
	 */
	public void getlowerLevelList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		String level = request.getParameter("level"); // 级别
		String state = request.getParameter("state"); // 1正常，2例外，3关停
		// 获取当前组织机构编码
		String siteCode = getCurrentSiteCode();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("orgSiteCode", siteCode);
			hashMap.put("isLink", DatabaseTreeInfoType.ISLINK.getCode());
			if(StringUtils.isNotEmpty(state)){
				hashMap.put("isExp", state);
			}
			if(StringUtils.isNotEmpty(level)){
				hashMap.put("level", level);  //  -1默认无用,1本级门户，2本级部门，3下属单位，6其他
			}
			List<DatabaseInfoResponse> resList = channelPointServiceImpl.getlowerLevelList(hashMap);
			if (CollectionUtils.isNotEmpty(resList)) {
				for (DatabaseInfoResponse res : resList) {
					Map<String, Object> item = new HashMap<String, Object>();

					if (StringUtils.isNotEmpty(res.getJumpUrl())) {
						item.put("url", res.getJumpUrl());// 跳转url地址
					} else {
						item.put("url", res.getUrl());// 首页url地址
					}
					item.put("siteCode", res.getSiteCode());
					item.put("name", res.getName());
					item.put("director", res.getDirector());
					item.put("channelPointNum", res.getChannelPointNum());
					if (res.getChannelPointNum() != null && res.getChannelPointNum() != 0) {
						item.put("type", 1); // 1 显示栏目信息
					} else {
						item.put("type", 2); // 2 不显示栏目信息
					}

					item.put("siteManageUnit", res.getDirector());// 单位名称
					item.put("officeAddress", res.getAddress());// 办公地址
					item.put("relationName", res.getPrincipalName());// 负责人
					item.put("relationCellphone", res.getTelephone());// 负责人手机
					item.put("relationPhone", res.getMobile());// 负责人办公电话
					item.put("relationEmail", res.getEmail());// 负责人电子邮箱
					item.put("linkman", res.getLinkmanName());// 联系人
					item.put("linkmanCellphone", res.getTelephone2());// 联系人手机
					item.put("linkmanPhone", res.getMobile2());// 联系人办公电话
					item.put("linkmanEmail", res.getEmail2());// 联系人邮箱
					
					items.add(item);
				}
			}
			resultMap.put("success", "true");
			resultMap.put("body", items);
			resultMap.put("items", items);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("errorMsg", "下级信息数据异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**************************************** 新管理-站点信息 end **********************************************/
	
	
	
	/**
	 * @Description: 组织单位：获取客户信息
	 * @author sunjiaqi --- 2015-11-12下午02:07:36
	 */
	public String index() {
		try {
			//引导预警判断参数
			
			String guideNext = request.getParameter("guideNext");
			if(StringUtils.isNotEmpty(guideNext)){
				request.setAttribute("guideNext", guideNext);
			}
			String top = request.getParameter("top");
			if (top != null) {
				request.setAttribute("top", top);
			} else {
				request.setAttribute("top", 1);
			}
			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}

			String type = request.getParameter("type");
			if (StringUtils.isEmpty(type)) {
				type = "tp";
			}

			request.setAttribute("type", type);
			request.setAttribute("siteCode", siteCode);
			if (top != null && "8".equals(top)) {
				DatabaseOrgInfoRequest orgRequest = new DatabaseOrgInfoRequest();
				orgRequest.setStieCode(siteCode);

				List<DatabaseOrgInfo> orgList = databaseOrgInfoServiceImpl.queryList(orgRequest);
				if (orgList != null && orgList.size() > 0) {
					databaseOrgInfo = orgList.get(0);
				} else {
					databaseOrgInfo = null;
				}
			} else {
				/*DatabaseLinkRequest databaseLinkRequest=new DatabaseLinkRequest();
				databaseLinkRequest.setOrgSiteCode(siteCode);
				databaseLinkRequest.setIsexp(1);
				databaseLinkRequest.setType(1);
				List<DatabaseLink> linkList=databaseLinkServiceImpl.queryList(databaseLinkRequest);*/
				
				//改用tree表查询
				DatabaseTreeInfoRequest dbtRequest = new DatabaseTreeInfoRequest();
				dbtRequest.setOrgSiteCode(siteCode);
				dbtRequest.setIsexp(1);
				dbtRequest.setLayerType(1);
				dbtRequest.setIsLink(1);
				List<DatabaseTreeInfo> treeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(dbtRequest);
				
				if(treeList.size()>0){
					//DatabaseLink databaseLink=linkList.get(0);
					DatabaseTreeInfo databaseTreeInfo = treeList.get(0);
					DatabaseInfoRequest request = new DatabaseInfoRequest();
					request.setSiteCode(databaseTreeInfo.getSiteCode());
					List<DatabaseInfo> dbList = databaseInfoServiceImpl.queryList(request);
					if (null != dbList && dbList.size() > 0) {
						databaseInfo = dbList.get(0);
					} else {
						databaseInfo = null;
					}
				}else{
					databaseInfo = null;
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	/**
	 * @Description: 填报单位--管理页面--信息管理--基本信息页面数据初始化
	 * @author cuichx --- 2016-3-3上午10:30:54
	 * @return
	 */
	public String indexTB() {

		try {
			String top = request.getParameter("top");
			//引导预警判断
			String guideNext = request.getParameter("guideNext");
			if(StringUtils.isNotEmpty(guideNext)){
				request.setAttribute("guideNext", guideNext);
			}
			if (top != null) {
				request.setAttribute("top", top);
				String toBaoSongUrl=prop.getProperty("toBaoSongUrl");
				request.setAttribute("toBaoSongUrl", toBaoSongUrl);
			} else {
				request.setAttribute("top", 1);
			}
			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			String siteCodeJump = request.getParameter("siteCode"); //siteCode不为空说明是从 组织单位跳转过来的
			if(StringUtils.isNotEmpty(siteCodeJump)){
				siteCode = siteCodeJump;
				setCurrentShiroUser(siteCode);  //变更当前用户
			}else{
				ShiroUser shiroUser = getCurrentUserInfo();
				if (shiroUser != null) {
					siteCode = shiroUser.getChildSiteCode();
				}
			}
			String type = request.getParameter("type");
			if(StringUtils.isEmpty(type)){
				type = "tp";
			}
			
			request.setAttribute("type", type);
			request.setAttribute("siteCode", siteCode);
			// 根据网站标识码，查询databaseInfo表获取基本信息
			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCodeLike(siteCode);

			List<DatabaseInfo> databaseList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if (databaseList != null && databaseList.size() > 0) {
				databaseInfo = databaseList.get(0);
			} else {
				databaseInfo = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	/**
	 * @Description: 栏目信息添加保存
	 * @author cuichx --- 2016-3-2上午9:51:00
	 */
	public void addChannel() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject jb = getJSONObject();
			if (jb == null) {
				resultMap.put("errorMsg", "页面提交的栏目信息不能为空！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				String channelName = jb.getString("channelName");// 栏目名称
				String channelUrl = jb.getString("channelUrl"); // 栏目url
				String jumpUrl = jb.getString("jumpUrl");// 跳转url
				String modifySiteCode = jb.getString("modifySiteCode");// 网站标识码
				String dicChannelId = jb.getString("dicChannelId");// 父类栏目id
				String dicChannelSonId = jb.getString("dicChannelSonId");// 子类栏目id

				ChannelPoint channelPoint = new ChannelPoint();
					channelPoint.setCreator(getCurrentUserInfo().getSiteCode());
					channelPoint.setCreated(DateUtils.formatStandardDateTime(new Date()));
				if (StringUtils.isNotEmpty(channelName)) {
					//
					channelPoint.setChannelName(channelName);
				}
				if (StringUtils.isNotEmpty(modifySiteCode)) {
					channelPoint.setSiteCode(modifySiteCode);
				}
				if (StringUtils.isNotEmpty(dicChannelId)) {
					channelPoint.setDicChannelId(Integer.valueOf(dicChannelId));
				}
				if (StringUtils.isNotEmpty(dicChannelSonId)) {
					channelPoint.setDicChannelSonId(Integer.valueOf(dicChannelSonId));
				}
				if (StringUtils.isNotEmpty(channelUrl)) {
					channelPoint.setChannelUrl(channelUrl);
				}
				if (StringUtils.isNotEmpty(jumpUrl)) {
					channelPoint.setJumpPageUrl(jumpUrl);
				}
				String encodeUrl = StringUtils.hash(StringUtils.isEmpty(jumpUrl) ? channelUrl : jumpUrl);

				// 保存时默认监测状态为未监测
				channelPoint.setStatus(0);
				//连通状态连通状态（0：不连通，1：连通）
				channelPoint.setLinkStatus(1);
				channelPoint.setEncodeUrl(encodeUrl);

				// 验证url唯一性
				if (!checkUrlUnique(modifySiteCode, channelUrl, jumpUrl, encodeUrl)) {
					resultMap.put("errorMsg", "此URL已被使用，请重新输入");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				} else {
					channelPointServiceImpl.add(channelPoint);
					resultMap.put("success", "添加栏目成功");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "添加栏目异常");
			writerPrint(JSONObject.fromObject(resultMap).toString());

		}
	}

	/**
	 * 验证url唯一性
	 * 
	 * @param url
	 * @return
	 */
	private boolean checkUrlUnique(String siteCode, String channelUrl, String jumpPageUrl, String encodeUrl) {

		if (!StringUtils.isEmpty(channelUrl)) {
			ChannelPointRequest channelPointRequest = new ChannelPointRequest();
			channelPointRequest.setSiteCode(siteCode);
			channelPointRequest.setChannelUrl(channelUrl);
			channelPointRequest.setStatusIn("0,1");
			List<ChannelPoint> listChannelPoint = channelPointServiceImpl.queryList(channelPointRequest);

			if (listChannelPoint != null && listChannelPoint.size() > 0) {
				return false;
			}
		}

		if (!StringUtils.isEmpty(jumpPageUrl)) {
			ChannelPointRequest channelPointRequest = new ChannelPointRequest();
			channelPointRequest.setSiteCode(siteCode);
			channelPointRequest.setJumpPageUrl(jumpPageUrl);
			channelPointRequest.setStatusIn("0,1");
			List<ChannelPoint> listChannelPoint = channelPointServiceImpl.queryList(channelPointRequest);

			if (!CollectionUtils.isEmpty(listChannelPoint)) {
				return false;
			}
		}

		if (!StringUtils.isEmpty(encodeUrl)) {
			ChannelPointRequest channelPointRequest = new ChannelPointRequest();
			channelPointRequest.setSiteCode(siteCode);
			channelPointRequest.setEncodeUrl(encodeUrl);
			channelPointRequest.setStatusIn("0,1");
			List<ChannelPoint> listChannelPoint = channelPointServiceImpl.queryList(channelPointRequest);

			if (!CollectionUtils.isEmpty(listChannelPoint)) {
				return false;
			}
		}
		return true;

	}

	@Autowired
	private ITaskDailyService taskDailyServiceImpl;

	/**
	 * 获取日任务
	 * 
	 * @param channelPoint
	 * @return
	 */
	private TaskDaily getTaskDaily(ChannelPoint channelPoint) {
		TaskDailyRequest request = new TaskDailyRequest();
		request.setSiteCode(channelPoint.getSiteCode());
		request.setWebsiteInfoId(channelPoint.getWebsiteInfoId());
		List<TaskDaily> taskList = taskDailyServiceImpl.queryList(request);
		if (CollectionUtils.isEmpty(taskList)) {
			return null;
		}
		return taskList.get(0);
	}

	/**
	 * @Description:重点栏目删除
	 * @author cuichx --- 2016-3-3下午2:55:49
	 */
	public void delChannel() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取当前登录用户的sitecode
		String userId = getCurrentUserInfo().getSiteCode();
		try {
			JSONObject jb = getJSONObject();
			JSONArray ids = jb.getJSONArray("ids");
			for (int t = 0; t < ids.size(); t++) {

				Integer id = Integer.parseInt(ids.get(t).toString());
				ChannelPoint channelPoint = channelPointServiceImpl.get(id);
				if (channelPoint != null) {
					channelPoint.setStatus(-1);// 标记删除
					channelPointServiceImpl.update(channelPoint);
					//根据SiteCode和url查询channelPointTemp
					ChannelPointTempRequest pointTempRe = new ChannelPointTempRequest();
					pointTempRe.setSiteCode(channelPoint.getSiteCode());
					pointTempRe.setChannelUrl(channelPoint.getChannelUrl());
					pointTempRe.setPageSize(Integer.MAX_VALUE);
					List<ChannelPointTemp> queryList = channelPointTempServiceImpl.queryList(pointTempRe);
					for (ChannelPointTemp channelPointTemp : queryList) {
						//更新id对应数据
						channelPointTempServiceImpl.delete(channelPointTemp.getId());
					}
					//操作channelPointLog表记录删除操作
					ChannelPointLog channelPointLog = new ChannelPointLog();
					//记录日志参数
					channelPointLog.setSiteCode(channelPoint.getSiteCode());
					channelPointLog.setChannelName(channelPoint.getChannelName());
					channelPointLog.setChannelUrl(channelPoint.getChannelUrl());
					channelPointLog.setType(1);
					channelPointLog.setUserId(userId);
					channelPointLogServiceImpl.add(channelPointLog);
					Map<String, String> params=new HashMap<String, String>();
					params.put("siteCode", channelPoint.getSiteCode());
					params.put("encodeUrl", channelPoint.getEncodeUrl());
					params.put("flag", "0");
					HttpClientUtils.basicGet(dicUtils.getValue("delChannelTask"), params);
				}

				// 修改taskDaily 是否启动状态
				TaskDaily taskDaily = getTaskDaily(channelPoint);
				if (null != taskDaily && taskDaily.getIsStart() == 0) {
					taskDaily.setIsStart(1);
					taskDailyServiceImpl.update(taskDaily);
				}
			}
			resultMap.put("succeed", "删除成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", "删除失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 重点栏目取消监测
	 * @author Nora --- 2015-12-16下午06:37:45
	 */
	public void cancelChannel() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject jb = getJSONObject();
			JSONArray ids = jb.getJSONArray("ids");
			for (int t = 0; t < ids.size(); t++) {

				Integer id = Integer.parseInt(ids.get(t).toString());
				ChannelPoint channelPoint = channelPointServiceImpl.get(id);
				channelPoint.setStatus(0);
				channelPointServiceImpl.update(channelPoint);

				// 修改taskDaily 是否启动状态
				TaskDaily taskDaily = getTaskDaily(channelPoint);
				if (null != taskDaily && taskDaily.getIsStart() == 0) {
					taskDaily.setIsStart(1);
					taskDailyServiceImpl.update(taskDaily);
				}
			}

			resultMap.put("succeed", "取消监测成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("error", "取消监测失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}

	}

	/**
	 * @Description: 重点栏目恢复监测
	 * @author Nora --- 2015-12-16下午06:37:45
	 */
	public void reCheckChannel() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			JSONObject jb = getJSONObject();
			JSONArray ids = jb.getJSONArray("ids");
			for (int t = 0; t < ids.size(); t++) {

				Integer id = Integer.parseInt(ids.get(t).toString());
				ChannelPoint channelPoint = channelPointServiceImpl.get(id);
				channelPoint.setStatus(1);
				channelPointServiceImpl.update(channelPoint);

				// 修改taskDaily 是否启动状态
				TaskDaily taskDaily = getTaskDaily(channelPoint);
				if (null != taskDaily && taskDaily.getIsStart() == 0) {
					taskDaily.setIsStart(1);
					taskDailyServiceImpl.update(taskDaily);
				}
			}

			resultMap.put("succeed", "重点监测成功");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			resultMap.put("error", "重点监测失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}

	}

	/**
	 * @Description: 滑动条设置栏目监测状态
	 * @author sunjiaqi --- 2016-1-28下午5:35:13
	 */
	public void operateChannelInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));// id
			String isActive = request.getParameter("isActive");// 状态
			if (isActive.equals("false")) {
				ChannelPoint channelPoint = channelPointServiceImpl.get(id);
				channelPoint.setStatus(0);//状态（监测中：1，未监测：0,标记删除：-1），取消监测将状态置为0，删除记录为标记删除状态值为-1
				channelPoint.setLable(1);//手动改变监测状态标记 0表示 取消改动(由未监测改为监测) 1 表示前台将监测手动改为未监测
				channelPoint.setModifier(getCurrentUserInfo().getSiteCode());
				channelPoint.setModified(DateUtils.formatStandardDateTime(new Date()));
				channelPointServiceImpl.update(channelPoint);

				// 修改taskDaily 是否启动状态
				TaskDaily taskDaily = getTaskDaily(channelPoint);

				if (null != taskDaily && taskDaily.getIsStart() == 1) {
					taskDaily.setIsStart(1);
					taskDaily.setModifyTime(new Date());
					taskDailyServiceImpl.update(taskDaily);
				}
				resultMap.put("success", "监测状态修改成功！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				ChannelPoint channelPoint = channelPointServiceImpl.get(id);
				/**
				 * 免费版和付费版验证 如果是免费版，启动监测的栏目个数不能超过5个 如果是付费版，没有个数限制
				 */
				String siteCode = channelPoint.getSiteCode();
				// 通过网站标识码查询databaseInfo表，判断是免费版还是付费版
				DatabaseInfoRequest databaseRequest = new DatabaseInfoRequest();
				databaseRequest.setSiteCodeLike(siteCode);

				int iscost = 0;
				List<DatabaseInfo> databaseList = databaseInfoServiceImpl.queryList(databaseRequest);
				if (databaseList != null && databaseList.size() > 0) {
					DatabaseInfo databaseInfo = databaseList.get(0);
					iscost = databaseInfo.getIscost();// 是否收费
				}
				if (iscost == 0) {// 免费版
					// 判断监测栏目个数是否超过5个，如果超过5个提示错误信息
					ChannelPointRequest channelRequest = new ChannelPointRequest();
					channelRequest.setSiteCode(siteCode);
					channelRequest.setStatus(TrueOrFalseType.TRUE.getCode());// 监测中
					int count = channelPointServiceImpl.queryCount(channelRequest);
					if (count >= 5) {
						resultMap.put("errorMsg", "免费监测栏目最多5个，如果您想监测更多栏目请联络客服人员：4000-976-005");
						writerPrint(JSONObject.fromObject(resultMap).toString());
					} else {
						channelPoint.setStatus(1);
						channelPoint.setLable(0);//手动改变监测状态标记 0表示 取消改动(由未监测改为监测) 1 表示前台将监测手动改为未监测
						channelPoint.setModifier(getCurrentUserInfo().getSiteCode());
						channelPoint.setModified(DateUtils.formatStandardDateTime(new Date()));
						channelPointServiceImpl.update(channelPoint);

						// 修改taskDaily 是否启动状态
						TaskDaily taskDaily = getTaskDaily(channelPoint);
						if (null != taskDaily && taskDaily.getIsStart() == 0) {
							taskDaily.setIsStart(1);
							taskDaily.setModifyTime(new Date());
							taskDailyServiceImpl.update(taskDaily);
						}
						resultMap.put("success", "监测状态修改成功！");
						writerPrint(JSONObject.fromObject(resultMap).toString());

					}
				} else {// 付费版
					channelPoint.setStatus(1);
					channelPoint.setLable(0);//手动改变监测状态标记 0表示 取消改动(由未监测改为监测) 1 表示前台将监测手动改为未监测
					channelPoint.setModifier(getCurrentUserInfo().getSiteCode());
					channelPoint.setModified(DateUtils.formatStandardDateTime(new Date()));
					channelPointServiceImpl.update(channelPoint);

					// 修改taskDaily 是否启动状态
					TaskDaily taskDaily = getTaskDaily(channelPoint);
					if (null != taskDaily && taskDaily.getIsStart() == 0) {
						taskDaily.setIsStart(1);
						taskDaily.setModifyTime(new Date());
						taskDailyServiceImpl.update(taskDaily);
					}
					resultMap.put("success", "监测状态修改成功！");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "监测状态修改异常！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取栏目信息
	 * @author sunjiaqi --- 2015-11-16下午03:25:11
	 */
	public void getChannelInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String pos = request.getParameter("pos");// 分页参数
			String size = request.getParameter("size");// 分页参数
			String term = request.getParameter("sSearch");// 关键字查询条件
			String siteCode = request.getParameter("siteCode");// 查询databaseInfo表

			ChannelPointRequest channelPointRequest = new ChannelPointRequest();
			if (StringUtils.isNotEmpty(pos)) {
				channelPointRequest.setPageNo(Integer.valueOf(pos));
			}
			if (StringUtils.isNotEmpty(size)) {
				channelPointRequest.setPageSize(Integer.valueOf(size));
			}
			if (StringUtils.isNotEmpty(term)) {
				channelPointRequest.setTerm(term);
			}
			if (StringUtils.isEmpty(siteCode)) {
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				resultMap.put("errorMsg", "页面传递的网站标识码不能为空");
				resultMap.put("body", items);
				resultMap.put("totalRecords", 0);
				resultMap.put("iTotalDisplayRecords", 0);
				resultMap.put("hasMoreItems", true);
			} else {
				// 设置排序字段
				List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
				QueryOrder siteQueryOrder = new QueryOrder("status", QueryOrderType.DESC);
				querySiteList.add(siteQueryOrder);
				channelPointRequest.setQueryOrderList(querySiteList);
				channelPointRequest.setStatusFlag("notNull");
				channelPointRequest.setSiteCode(siteCode);
				List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
				PageVo<ChannelPoint> cpPVList = channelPointServiceImpl.query(channelPointRequest);// 依据siteCode查询网站下面的重点监测栏目
				if (null != cpPVList.getData() && cpPVList.getData().size() > 0) {
					List<ChannelPoint> cpList = cpPVList.getData();
					for (int i = 0; i < cpList.size(); i++) {
						Map<String, Object> item = new HashMap<String, Object>();
						ChannelPoint cp = cpList.get(i);

						String channelName = cp.getChannelName();// 栏目名称
						if (StringUtils.isEmpty(channelName)) {
							channelName = "";
						}
						String channelUrl = cp.getChannelUrl();// 栏目URL
						String jumpUrl = cp.getJumpPageUrl();// 跳转URL
						int dicChanneld = cp.getDicChannelId();// 类别
						DicChannel dicChannel = dicChannelServiceImpl.get(dicChanneld);

						String dicChannelName = "";
						if (null != dicChannel) {
							dicChannelName = dicChannel.getChannelName();
						}

						int dicChannelSonId = cp.getDicChannelSonId();// 子类
						String dicChannelSonIdName = "";

						DicChannel dicChannelSon = dicChannelServiceImpl.get(dicChannelSonId);
						String updateTime = "";
						if (null != dicChannelSon) {
							dicChannelSonIdName = dicChannelSon.getChannelName();
							updateTime = getDichUpdateTime(dicChannelSon.getId());
						}

						item.put("id", cp.getId());
						item.put("channelName", channelName);
						item.put("channelUrl", channelUrl);

						if (StringUtils.isEmpty(jumpUrl)) {
							jumpUrl = "无";
						}
						item.put("jumpUrl", jumpUrl);
						item.put("updateTime",updateTime);
						item.put("dicChannelName", dicChannelName);
						item.put("dicChannelSonIdName", dicChannelSonIdName);
						item.put("status", cp.getStatus());
						item.put("linkStatus", cp.getLinkStatus());
						items.add(item);
					}
				}
				resultMap.put("body", items);
				resultMap.put("items", items);
				resultMap.put("totalRecords", cpPVList.getRecordSize());
				resultMap.put("iTotalDisplayRecords", cpPVList.getRecordSize());
				resultMap.put("hasMoreItems", true);
			}
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "更新数据异常");
		}
	}

	/**
	 * @Description: 栏目信息信息--Excel导出 --根据页面传递的条件导出
	 * @author cuichx --- 2016-3-3下午3:58:43
	 */
	public void channelExportExcel() {

		// 监测栏目
		String channelPointIds = request.getParameter("channelPointIds");
		String siteCode = request.getParameter("siteCode");
		String term = request.getParameter("term");// 关键字查询条件

		String fileName = "重点监测栏目-系统(" + DateUtils.formatStandardDate(new Date()) + ").xls";
		String title = "重点监测栏目/系统";
		String sheetTitle = "08重点监测栏目-系统（导出）";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[] { "序号", "名称", "URL", "跳转URL", "更新期限","类别", "子类" };
		list.add(obj1);

		try {
			// 查询栏目信息
			ChannelPointRequest channelPointRequest = new ChannelPointRequest();

			if (!StringUtils.isEmpty(channelPointIds)) {
				String[] channelPointIdArray = channelPointIds.split(",");// 按照,拆分分别记录
				channelPointRequest.setChannelPointIdArray(channelPointIdArray);
			}
			if (StringUtils.isNotEmpty(siteCode)) {
				channelPointRequest.setSiteCode(siteCode);
			}
			if (StringUtils.isNotEmpty(term)) {
				channelPointRequest.setTerm(term);
			}
			channelPointRequest.setPageSize(Integer.MAX_VALUE);
			// 将标记删除的记录过滤掉
			channelPointRequest.setStatusFlag("notNull");

			List<ChannelPoint> listChannelPoint = channelPointServiceImpl.queryList(channelPointRequest);

			if (listChannelPoint.size() > 0) {
				for (int i = 0; i < listChannelPoint.size(); i++) {

					ChannelPoint channelPoint = listChannelPoint.get(i);
					String channelName = channelPoint.getChannelName() != null ? channelPoint.getChannelName() : "";

					String channelUrl = channelPoint.getChannelUrl() != null ? channelPoint.getChannelUrl() : "";
					String jumpUrl = channelPoint.getJumpPageUrl() != null ? channelPoint.getJumpPageUrl() : "无";

					int dicChanneld = channelPoint.getDicChannelId();// 类别
					DicChannel dicChannel = dicChannelServiceImpl.get(dicChanneld);

					String dicChannelName = "";
					if (null != dicChannel) {
						dicChannelName = dicChannel.getChannelName();
					}

					int dicChannelSonId = channelPoint.getDicChannelSonId();// 子类
					String dicChannelSonName = "";

					String updateTime = "";
					DicChannel dicChannelSon = dicChannelServiceImpl.get(dicChannelSonId);
					if (null != dicChannelSon) {
						dicChannelSonName = dicChannelSon.getChannelName();
						updateTime = getDichUpdateTime(dicChannelSon.getId());
					}
					Object[] obj = new Object[7];
					obj[0] = i + 1;
					obj[1] = channelName;
					obj[2] = CommonUtils.setHttpUrl(channelUrl); //  判断是否有http头
					obj[3] = CommonUtils.setHttpUrl(jumpUrl); //  判断是否有http头
					obj[4] = updateTime;
					obj[5] = dicChannelName;
					obj[6] = dicChannelSonName;
					list.add(obj);
				}
			}
			ExportExcel.channelExcel(fileName, title, sheetTitle, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("导出 异常！！！错误信息=" + e.getMessage());
		}
	}

	/**
	 * @Description:添加栏目信息页面初始化
	 * @author cuichx --- 2016-3-1下午5:54:58
	 */
	public void initAddChannel() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			// 获取页面上送的父类栏目id
			String parentId = request.getParameter("parentId");
			String siteCode = request.getParameter("siteCode");
			if (StringUtils.isEmpty(parentId)) {
				parentId = "1";
			}
			logger.info("initAddChannel parentId:" + parentId);
			if (StringUtils.isEmpty(siteCode)) {
				ShiroUser shiroUser = getCurrentUserInfo();
				if (shiroUser != null) {
					siteCode = shiroUser.getSiteCode();
				}
			}

			// 根据网站标识码查询databaseInfo表

			DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
			databaseInfoRequest.setSiteCodeLike(siteCode);

			List<DatabaseInfo> databaseList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
			if (databaseList != null && databaseList.size() > 0) {
				DatabaseInfo dataInfo = databaseList.get(0);
				resultMap.put("siteCode", dataInfo.getSiteCode());
				resultMap.put("siteName", dataInfo.getName());
			}
			// 父类栏目id
			resultMap.put("listDicChannel", getDicChannelList());
			// 子类栏目id
			resultMap.put("listDicChannelSon", getDicChannelSonList(Integer.parseInt(parentId)));

			writerPrint(JSONObject.fromObject(resultMap).toString());

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("errorMsg", "获取获取栏目类型失败！");
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}

	}

	/**
	 * @Description: 获取栏目类型  @author Nora --- 2015-11-23下午18:01:53       @return
	 *                           
	 */
	public void loadDicChannelList() {

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String parentId = request.getParameter("parentId");

			if (StringUtils.isEmpty(parentId)) {
				parentId = "1";
			}
			map.put("listDicChannel", getDicChannelList());
			map.put("listDicChannelSon", getDicChannelSonList(Integer.parseInt(parentId)));
			writerPrint(JSONObject.fromObject(map).toString());

		} catch (Exception e) {
			e.printStackTrace();
			map.put("errmsg", "获取获取栏目类型失败！");
			writerPrint(JSONObject.fromObject(map).toString());
		}
	}

	private List<DicChannel> getDicChannelList() {
		DicChannelRequest request = new DicChannelRequest();
		request.setParentId(0);
		return dicChannelServiceImpl.queryList(request);
	}

	private List<DicChannel> getDicChannelSonList(int parentId) {
		DicChannelRequest request = new DicChannelRequest();
		request.setParentId(parentId);
		return dicChannelServiceImpl.queryList(request);
	}

	/**
	 * @Description: 基本信息修改提交
	 * @author sunjiaqi --- 2015-11-13下午07:26:29
	 */
	public void modifyBaseInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			JSONObject jb = getJSONObject();
			if (jb == null) {
				resultMap.put("errorMsg", "页面提交的栏目信息不能为空！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				String top = jb.getString("top");// 非点击管理按钮的页面 修改数据

				String siteCode = jb.getString("siteCode");// 网站标识码
				String siteName = jb.getString("siteName");// 网站名称
				String director = jb.getString("director");// 主办单位
				String address = jb.getString("address");// 办公地址
				String url = jb.getString("url");// 首页url
				String jumpUrl = jb.getString("jumpUrl");// 跳转url

				// 负责人信息
				String principalName = "";// 负责人姓名
				String telephone = "";// 负责人手机
				String email = "";// 负责人电子邮箱

				// 联系人信息
				String linkmanName = "";// 联系人姓名
				String telephone2 = "";// 联系人手机
				String email2 = "";// 联系人电子邮箱
				if (top != null && top.equals("8")) {
					// 负责人信息
					principalName = jb.getString("principalName");// 负责人姓名
					telephone = jb.getString("telephone");// 负责人手机
					email = jb.getString("email");// 负责人电子邮箱

					// 联系人信息
					linkmanName = jb.getString("linkmanName");// 联系人姓名
					telephone2 = jb.getString("telephone2");// 联系人手机
					email2 = jb.getString("email2");// 联系人电子邮箱
				}

				// 判断encodeUrl是否发生变化
				String oldEncodeUrl = "";
				if (StringUtils.isNotEmpty(jumpUrl)) {
					oldEncodeUrl = StringUtils.hash(jumpUrl);
				} else {
					oldEncodeUrl = StringUtils.hash(url);
				}
				// 查询databaseInfo
				DatabaseInfoRequest dataInfoRequest = new DatabaseInfoRequest();
				dataInfoRequest.setSiteCode(siteCode);
				List<DatabaseInfo> dataList = databaseInfoServiceImpl.queryList(dataInfoRequest);
				if (dataList != null && dataList.size() > 0) {
					DatabaseInfo oldDataInfo = dataList.get(0);
					String oldStr = oldDataInfo.toString();
					DatabaseInfo dataInfo = dataList.get(0);

					String encodeUrl = dataInfo.getEncodeUrl();
					if (!encodeUrl.equals(oldEncodeUrl) || dataInfo.getIsScan() == 1) {
						// 修改task_daily 修改为启动状态
						TaskDailyRequest taskRequest = new TaskDailyRequest();
						if(StringUtils.isNotEmpty(databaseInfo.getSiteCode())){
							taskRequest.setSiteCode(databaseInfo.getSiteCode());
							List<TaskDaily> taskList = taskDailyServiceImpl.queryList(taskRequest);
							if (taskList != null && taskList.size() > 0) {
								TaskDaily taskDaily = taskList.get(0);
								taskDaily.setIsStart(1);
								taskDaily.setModifyTime(new Date());
								taskDaily.setIsScan(1);
								taskDailyServiceImpl.update(taskDaily);
							}
						}
					
					}
					// dataInfo.setIsScan(1);
					// 封装页面上送的信息
					dataInfo.setName(siteName);
					dataInfo.setDirector(director);
					dataInfo.setAddress(address);
					dataInfo.setUrl(null);
					dataInfo.setJumpUrl(null);
					dataInfo.setManageInfoUrl(url);
					dataInfo.setManageInfoJumpUrl(jumpUrl);

					if (top != null && top.equals("8")) {
						dataInfo.setPrincipalName(principalName);
						dataInfo.setTelephone(telephone);
						dataInfo.setEmail(email);

						dataInfo.setLinkmanName(linkmanName);
						dataInfo.setTelephone2(telephone2);
						dataInfo.setEmail2(email2);
					}
					databaseInfo.setModifyTime(new Date());
					dataInfo.setEncodeUrl(oldEncodeUrl);
					// 更新基本信息
					String newStr = dataInfo.toString();
					databaseInfoServiceImpl.update(dataInfo);
					
					
					//添加databaseInfo修改日志 add by Na.Y 20160929
					//当前类名，方法名
					try {
						String claseName = Thread.currentThread().getStackTrace()[1].getClassName();
						String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
						logCommonServiceImpl.createLog(dataInfo.getSiteCode(), DatabaseInfo.class.getName(), oldStr,newStr,
								LogType.DB_U_B, Constants.getCurrendUser().getUserName(), claseName +"."+methodName);
						logger.info("成功加入日志");
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("edit databaseInfo save log error...errorMsg:" + e.getMessage());
					}

					resultMap.put("succees", "更新成功");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				} else {
					resultMap.put("errorMsg", "基本信息表中不存在该站点信息，无法修改！");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}
		} catch (Exception e) {
			resultMap.put("error", "更新失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
	}
	
	public void modifyDatabaseInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			JSONObject jb = getJSONObject();
			if (jb == null) {
				resultMap.put("errorMsg", "数据不能为空！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				String siteCode = jb.getString("siteCode");// 网站标识码
				String siteName = jb.getString("siteName");// 网站名称

				// 联系人信息
				String linkmanName = jb.getString("linkmanName");// 联系人姓名
				String telephone2 = jb.getString("telephone2");// 联系人手机
				String email2 = jb.getString("email2");// 联系人电子邮箱
				
				//新增联系人two
				String linkman_name_two = jb.getString("linkmanNametwo");// 联系人姓名
				String linkman_phone_two = jb.getString("telephone3");// 联系人手机
				String linkman_email_two = jb.getString("email3");// 联系人电子邮箱
				
				
				String linkman_name_three = jb.getString("linkmanNamethree");// 联系人姓名
				String linkman_phone_three = jb.getString("telephone4");// 联系人手机
				String linkman_email_three = jb.getString("email4");// 联系人电子邮箱
				
				
				// 查询
				DatabaseInfoRequest infoRequest = new DatabaseInfoRequest();
				infoRequest.setSiteCode(siteCode);
				List<DatabaseInfo> dataList = databaseInfoServiceImpl.queryList(infoRequest);

				if (dataList != null && dataList.size() > 0) {
					DatabaseInfo oldDataInfo = dataList.get(0);
					String oldStr = oldDataInfo.toString();
					DatabaseInfo dataInfo = dataList.get(0);

					// 封装页面上送的信息
					dataInfo.setName(siteName);

					dataInfo.setLinkmanName(linkmanName);
					dataInfo.setTelephone2(telephone2);
					dataInfo.setEmail2(email2);
					
					dataInfo.setLinkmanNameTwo(linkman_name_two);
					dataInfo.setLinkmanPhoneTwo(linkman_phone_two);
					dataInfo.setLinkmanEmailTwo(linkman_email_two);
					
					dataInfo.setLinkmanNameThree(linkman_name_three);
					dataInfo.setLinkmanPhoneThree(linkman_phone_three);
					dataInfo.setLinkmanEmailThree(linkman_email_three);
					
					dataInfo.setModifyTime(new Date());
					String newStr = dataInfo.toString();
					// 更新填报机构基本信息
					databaseInfoServiceImpl.update(dataInfo);
					
					//添加databaseInfo修改日志 add by Na.Y 20160929
					//当前类名，方法名
					try {
						String claseName = Thread.currentThread().getStackTrace()[1].getClassName();
						String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
						logCommonServiceImpl.createLog(dataInfo.getSiteCode(), DatabaseInfo.class.getName(), oldStr, newStr,
								LogType.DB_U_B, Constants.getCurrendUser().getUserName(), claseName +"."+methodName);
						logger.info("成功加入日志");
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("edit databaseInfo save log error...errorMsg:" + e.getMessage());
					}
					
					resultMap.put("succees", "更新成功");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				} else {
					resultMap.put("errorMsg", "基本信息表中不存在，无法修改！");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}
		} catch (Exception e) {
			resultMap.put("error", "更新失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}

	}

	public void modifyOrgInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			JSONObject jb = getJSONObject();
			if (jb == null) {
				resultMap.put("errorMsg", "页面提交的栏目信息不能为空！");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			} else {
				String siteCode = jb.getString("siteCode");// 网站标识码
				String siteName = jb.getString("siteName");// 网站名称

				// 负责人信息
				String principalName = jb.getString("principalName");// 负责人姓名
				String telephone = jb.getString("telephone");// 负责人手机
				String email = jb.getString("email");// 负责人电子邮箱

				// 联系人one信息
				String linkmanName = jb.getString("linkmanName");// 联系人姓名
				String telephone2 = jb.getString("telephone2");// 联系人手机
				String email2 = jb.getString("email2");// 联系人电子邮箱
				
				// 联系人two信息
				String linkmanNametwo = jb.getString("linkmanNametwo");// 联系人姓名
				String telephone3 = jb.getString("telephone3");// 联系人手机
				String email3 = jb.getString("email3");// 联系人电子邮箱
				
				// 联系人three信息
				String linkmanNamethree = jb.getString("linkmanNamethree");// 联系人姓名
				String telephone4 = jb.getString("telephone4");// 联系人手机
				String email4 = jb.getString("email4");// 联系人电子邮箱
				

				// 查询
				DatabaseOrgInfoRequest orgInfoRequest = new DatabaseOrgInfoRequest();
				orgInfoRequest.setStieCode(siteCode);
				List<DatabaseOrgInfo> dataOrgList = databaseOrgInfoServiceImpl.queryList(orgInfoRequest);

				if (dataOrgList != null && dataOrgList.size() > 0) {
					DatabaseOrgInfo dataOrgInfo = dataOrgList.get(0);

					// 封装页面上送的信息
					dataOrgInfo.setName(siteName);

					dataOrgInfo.setPrincipalName(principalName);
					dataOrgInfo.setPrincipalPhone(telephone);
					dataOrgInfo.setPrincipalEmail(email);

					dataOrgInfo.setLinkmanName(linkmanName);
					dataOrgInfo.setLinkmanPhone(telephone2);
					dataOrgInfo.setLinkmanEmail(email2);

					dataOrgInfo.setLinkmanNametwo(linkmanNametwo);
					dataOrgInfo.setLinkmanPhonetwo(telephone3);
					dataOrgInfo.setLinkmanEmailtwo(email3);
					
					dataOrgInfo.setLinkmanNamethree(linkmanNamethree);
					dataOrgInfo.setLinkmanPhonethree(telephone4);
					dataOrgInfo.setLinkmanEmailthree(email4);

					dataOrgInfo.setModifyTime(new Date());
					// 更新组织机构基本信息
					databaseOrgInfoServiceImpl.update(dataOrgInfo);

					resultMap.put("succees", "更新成功");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				} else {
					resultMap.put("errorMsg", "基本信息表中不存在，无法修改！");
					writerPrint(JSONObject.fromObject(resultMap).toString());
				}
			}
		} catch (Exception e) {
			resultMap.put("error", "更新失败");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}

	}

	/**
	 * 在线校验Url连通性
	 * 
	 * @Description:
	 * @author Nora --- 2015-11-17下午09:28:25
	 */
	public void onlineVerifyUrl() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			JSONObject jb = getJSONObject();
			String url = jb.getString("url");
			if (StringUtils.isNotEmpty(url)) {
				if (!url.contains("http")) {
					url = "http://" + url;
				}
			}
			boolean flag = verifyUrlLinkServiceImpl.verifyUrlLink(url);

			if (flag) {
				resultMap.put("result", true);
			} else {
				resultMap.put("result", false);
			}

			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("result", false);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		}
	}

	/**
	 * @Description: 获取组织单位管理各个层级的站点信息列表
	 * @author sunjiaqi --- 2016-3-1下午5:45:50
	 */
	public void getLevelMesg() {
		try {
			String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
			ShiroUser shiroUser = getCurrentUserInfo();
			if (shiroUser != null) {
				siteCode = shiroUser.getSiteCode();
			}
			String departmentLevel = request.getParameter("departmentLevel");
			int userType = getCurrentUserInfo().getUserType();
			if (departmentLevel.equals("1")) {

				if (userType == UserType.TYPE_PROVINCE.getCode()) {
					departmentLevel = "1";
				} else {
					departmentLevel = "3";
				}
			} else if (departmentLevel.equals("2")) {
				if (userType == UserType.TYPE_PROVINCE.getCode()) {
					departmentLevel = "2";
				} else {
					departmentLevel = "4";
				}
			} else if (departmentLevel.equals("6")) {
				departmentLevel = "6";
			} else if (departmentLevel.equals("7")) {
				departmentLevel = "7";
			} else if (departmentLevel.equals("8")) {
				departmentLevel = "8";
			}

			List<DatabaseInfo> dataBaseLISt = queryDatebaseInfoListByType(departmentLevel, siteCode);
			ChannelPointRequest channelRequest = new ChannelPointRequest();
			channelRequest.setStatusFlag("notNull"); //查询 除【标记删除】以外的所有栏目
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Object> list = new ArrayList<Object>(0);
			if (null != dataBaseLISt && dataBaseLISt.size() > 0) {
				for (int i = 0; i < dataBaseLISt.size(); i++) {
					DatabaseInfo dbInfo = dataBaseLISt.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("siteCode", dbInfo.getSiteCode());
					map.put("siteName", dbInfo.getName());
					map.put("zbdw", dbInfo.getDirector());
					if (null != dbInfo.getJumpUrl() && !dbInfo.getJumpUrl().equals("")) {
						map.put("url", dbInfo.getJumpUrl());
					} else {
						map.put("url", dbInfo.getUrl());
					}
					if (dbInfo.getIsorganizational() == 1) {
						map.put("isOrg", true);
					} else {
						map.put("isOrg", false);
					}
					map.put("siteManageUnit", dbInfo.getDirector());// 单位名称
					map.put("officeAddress", dbInfo.getAddress());// 办公地址
					map.put("relationName", dbInfo.getPrincipalName());// 负责人
					map.put("relationCellphone", dbInfo.getTelephone());// 负责人手机
					map.put("relationPhone", dbInfo.getMobile());// 负责人办公电话
					map.put("relationEmail", dbInfo.getEmail());// 负责人电子邮箱
					map.put("linkman", dbInfo.getLinkmanName());// 联系人
					map.put("linkmanCellphone", dbInfo.getTelephone2());// 联系人手机
					map.put("linkmanPhone", dbInfo.getMobile2());// 联系人办公电话
					map.put("linkmanEmail", dbInfo.getEmail2());// 联系人邮箱
					
					channelRequest.setSiteCode(dbInfo
							.getSiteCode());
					// 该siteCode所有的栏目数量
					Integer channelNum = channelPointServiceImpl
							.queryCount(channelRequest);
					map.put("channelNum", channelNum);// 栏目数量
					list.add(map);
				}
				resultMap.put("totalRecords", dataBaseLISt.size());
				resultMap.put("iTotalDisplayRecords", dataBaseLISt.size());
			} else {
				resultMap.put("totalRecords", 0);
				resultMap.put("iTotalDisplayRecords", 0);
			}
			resultMap.put("body", list);
			resultMap.put("items", list);
			resultMap.put("hasMoreItems", false);
			writerPrint(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Description: 组织单位获取站点下面的栏目信息
	 * @author sunjiaqi --- 2016-3-1下午5:46:42
	 */
	public void getWebChannelInfos() {
		String siteCode = request.getParameter("siteCode");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> items = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(siteCode)) {
			ChannelPointRequest channelPointRequest = new ChannelPointRequest();
			channelPointRequest.setSiteCode(siteCode);
			channelPointRequest.setStatusFlag("-1"); //-1表示删除  查询删除标记不为-1的数据
			channelPointRequest.setPageNo(0);
			List<QueryOrder> querySiteList = new ArrayList<QueryOrder>();
			QueryOrder siteQueryOrder = new QueryOrder("status", QueryOrderType.DESC);
			querySiteList.add(siteQueryOrder);
			channelPointRequest.setQueryOrderList(querySiteList);
			channelPointRequest.setPageSize(Integer.MAX_VALUE);
			PageVo<ChannelPoint> cpPVList = channelPointServiceImpl.query(channelPointRequest);// 依据siteCode查询网站下面的重点监测栏目
			if (null != cpPVList.getData() && cpPVList.getData().size() > 0) {
				List<ChannelPoint> cpList = cpPVList.getData();
				for (int i = 0; i < cpList.size(); i++) {
					Map<String, Object> item = new HashMap<String, Object>();
					ChannelPoint cp = cpList.get(i);
					String channelName = cp.getChannelName();// 栏目名称
					String channelUrl = cp.getChannelUrl();// 栏目URL
					String jumpUrl = cp.getJumpPageUrl();// 跳转URL
					int dicChanneld = cp.getDicChannelId();// 类别
					DicChannel dicChannel = dicChannelServiceImpl.get(dicChanneld);
					String dicChannelName = "";
					if (null != dicChannel) {
						dicChannelName = dicChannel.getChannelName();
					}
					int dicChannelSonId = cp.getDicChannelSonId();// 子类
					String dicChannelSonIdName = "";
					DicChannel dicChannelSon = dicChannelServiceImpl.get(dicChannelSonId);
					String updateTime = "";
					if (null != dicChannelSon) {
						dicChannelSonIdName = dicChannelSon.getChannelName();
						updateTime = getDichUpdateTime(dicChannelSon.getId());
					}
					item.put("id", cp.getId());
					item.put("channelName", channelName);
					item.put("channelUrl", channelUrl);
					if (StringUtils.isEmpty(jumpUrl)) {
						jumpUrl = "无";
					}
					item.put("jumpUrl", jumpUrl);
					
				
					item.put("updateTime",updateTime);
					item.put("dicChannelName", dicChannelName);
					item.put("dicChannelSonIdName", dicChannelSonIdName);
					item.put("status", cp.getStatus());
					item.put("linkStatus", cp.getLinkStatus());
					items.add(item);
				}
			} else {
				DatabaseInfoRequest databaseInfoRequest = new DatabaseInfoRequest();
				databaseInfoRequest.setSiteCodeLike(siteCode);
				List<DatabaseInfo> diList = databaseInfoServiceImpl.queryList(databaseInfoRequest);
				if (null != diList && diList.size() > 0) {
					DatabaseInfo db = diList.get(0);
					resultMap.put("principalName", db.getPrincipalName());// 负责人
					resultMap.put("telephone", db.getTelephone());// 负责人手机
					resultMap.put("mobile", db.getMobile());// 负责人办公电话
					resultMap.put("email", db.getEmail());// 负责人邮箱
					resultMap.put("linkmanName", db.getLinkmanName());// 联系人
					resultMap.put("telephone2", db.getTelephone2());// 联系人手机
					resultMap.put("mobile2", db.getMobile2());// 联系人办公电话
					resultMap.put("email2", db.getEmail2());// 联系人邮箱
				}
			}
		}
		resultMap.put("items", items);
		writerPrint(JSONObject.fromObject(resultMap).toString());
	}

	/**
	 * @Description: 获取组织单位下所有填报单位的联系方式
	 * @author sunjiaqi --- 2016-3-1下午5:46:42
	 */
	public void getContractInfos() {
		String siteCode = "";// 获取填报单位siteCode,siteCode和客户编码是一样的
		ShiroUser shiroUser = getCurrentUserInfo();
		if (shiroUser != null) {
			siteCode = shiroUser.getSiteCode();
		}
		
		if(StringUtils.isEmpty(siteCode)){
			return;
		}
		
		//查询条件：主办单位，联系人或负责人姓名模糊查询
		String contractsQuery = request.getParameter("contractsQuery");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Object> items = new ArrayList<Object>();

		/*DatabaseLinkRequest linkRequest = new DatabaseLinkRequest();
		linkRequest.setOrgSiteCode(siteCode);
		linkRequest.setLinkStatus(1);
		linkRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseLink> databaseLinkList = databaseLinkServiceImpl.queryList(linkRequest);*/
		
		//改为tree表查询
		DatabaseTreeInfoRequest dbtRequest = new DatabaseTreeInfoRequest();
		dbtRequest.setOrgSiteCode(siteCode);
		dbtRequest.setIsLink(1);
		dbtRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseTreeInfo> treeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(dbtRequest);

		if (CollectionUtils.isEmpty(treeList)) {
			return;
		}

		for (DatabaseTreeInfo databaseTreeInfo : treeList) {

			DatabaseInfoRequest dabaBaseRequest = new DatabaseInfoRequest();
			dabaBaseRequest.setSiteCode(databaseTreeInfo.getSiteCode());
			dabaBaseRequest.setContractsQuery(contractsQuery);
			List<DatabaseInfo> databaseInfos = databaseInfoServiceImpl.queryList(dabaBaseRequest);
			if (CollectionUtils.isEmpty(databaseInfos)) {
				continue;
			}
			DatabaseInfo databaseInfo = databaseInfos.get(0);
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("siteCode", databaseInfo.getSiteCode());//网站标识码
			item.put("name", databaseInfo.getName());//网站名称
			item.put("director", databaseInfo.getDirector());//网站主办单位
			
			item.put("principalName", databaseInfo.getPrincipalName());//负责人
			item.put("principalPost", databaseInfo.getPrincipalPost());//负责人职位
			item.put("mobile", databaseInfo.getMobile());//负责人工作电话
			item.put("telephone", databaseInfo.getTelephone());//负责人手机
			item.put("email", databaseInfo.getEmail());//负责人邮箱
			
			item.put("linkmanName", databaseInfo.getLinkmanName());//联系人
			item.put("mobile2", databaseInfo.getMobile2());//联系人工作电话
			item.put("telephone2", databaseInfo.getTelephone2());//联系人手机
			item.put("email2", databaseInfo.getEmail2());//联系人邮箱
			items.add(item);
		}

		resultMap.put("items", items);
		writerPrint(JSONObject.fromObject(resultMap).toString());
	}
	
	/**
	 * @Description: 联系人名录信息--Excel导出 --获取组织单位下所有填报单位的联系方式导出
	 * @author Na.Y --- 2016-09-08下午3:58:43
	 */
	public void contractInfosExportExcel() {

		String siteCode = "";// 获取组织单位siteCode,siteCode和客户编码是一样的
		ShiroUser shiroUser = getCurrentUserInfo();
		if (shiroUser != null) {
			siteCode = shiroUser.getSiteCode();
		}
		
		if(StringUtils.isEmpty(siteCode)){
			return;
		}
		
		//查询条件：主办单位，联系人或负责人姓名模糊查询
		String contractsQuery = request.getParameter("contractsQuery");
		
		/*DatabaseLinkRequest linkRequest = new DatabaseLinkRequest();
		linkRequest.setOrgSiteCode(siteCode);
		linkRequest.setLinkStatus(1);
		linkRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseLink> databaseLinkList = databaseLinkServiceImpl.queryList(linkRequest);*/
		
		//改为tree表查询
		DatabaseTreeInfoRequest dbtRequest = new DatabaseTreeInfoRequest();
		dbtRequest.setOrgSiteCode(siteCode);
		dbtRequest.setIsLink(1);
		dbtRequest.setPageSize(Integer.MAX_VALUE);
		List<DatabaseTreeInfo> treeList = databaseTreeBizServiceImpl.getDatabaseTreeInfoList(dbtRequest);

		if (CollectionUtils.isEmpty(treeList)) {
			return;
		}

		String fileName = "联系人名录-(" + DateUtils.formatStandardDate(new Date()) + ").xls";
		String title = "联系人名录";
		String sheetTitle = "联系人名录（导出）";
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Object[] obj1 = new Object[] { "序号","标识码","网站名称", "单位", "人员类别", "姓名", "职务", "工作电话","手机","邮箱" };
		list.add(obj1);
		
		int i=0;
		try {
			if(treeList.size()>0){
				for (DatabaseTreeInfo databaseTreeInfo : treeList) {

					DatabaseInfoRequest dabaBaseRequest = new DatabaseInfoRequest();
					dabaBaseRequest.setSiteCode(databaseTreeInfo.getSiteCode());
					dabaBaseRequest.setContractsQuery(contractsQuery);
					List<DatabaseInfo> databaseInfos = databaseInfoServiceImpl.queryList(dabaBaseRequest);
					if (CollectionUtils.isEmpty(databaseInfos)) {
						continue;
					}
					
					DatabaseInfo databaseInfo = databaseInfos.get(0);
					//填报单位网站标识码
					String siteCodeStr = StringUtils.isNotEmpty(databaseInfo.getSiteCode())?databaseInfo.getSiteCode():"--";
					//填报单位网站名称
					String name = StringUtils.isNotEmpty(databaseInfo.getName())?databaseInfo.getName():"--";
					//网站主办单位
					String director = StringUtils.isNotEmpty(databaseInfo.getDirector())?databaseInfo.getDirector():"--";
					//负责人
					String principalName = StringUtils.isNotEmpty(databaseInfo.getPrincipalName())?databaseInfo.getPrincipalName():"--";
					//负责人职位
					String principalPost = StringUtils.isNotEmpty(databaseInfo.getPrincipalPost())?databaseInfo.getPrincipalPost():"--";
					//负责人工作电话
					String mobile = StringUtils.isNotEmpty(databaseInfo.getMobile())?databaseInfo.getMobile():"--";
					//负责人手机
					String telephone = StringUtils.isNotEmpty(databaseInfo.getTelephone())?databaseInfo.getTelephone():"--";
					//负责人邮箱
					String email = StringUtils.isNotEmpty(databaseInfo.getEmail())?databaseInfo.getEmail():"--";
					//联系人
					String linkmanName = StringUtils.isNotEmpty(databaseInfo.getLinkmanName())?databaseInfo.getLinkmanName():"--";
					//联系人工作电话
					String mobile2 = StringUtils.isNotEmpty(databaseInfo.getMobile2())?databaseInfo.getMobile2():"--";
					//联系人手机
					String telephone2 = StringUtils.isNotEmpty(databaseInfo.getTelephone2())?databaseInfo.getTelephone2():"--";
					//联系人邮箱
					String email2 = StringUtils.isNotEmpty(databaseInfo.getEmail2())?databaseInfo.getEmail2():"--";
					
					Object[] principal = new Object[10];
					principal[0] = i + 1;
					principal[1] = siteCodeStr;
					principal[2] = name;
					principal[3] = director;
					principal[4] = "负责人";
					principal[5] = principalName;
					principal[6] = principalPost;
					principal[7] = mobile;
					principal[8] = telephone;
					principal[9] = email;
					list.add(principal);	
					Object[] linkman = new Object[10];
					linkman[0] = "";
					linkman[1] = "";
					linkman[2] = "";
					linkman[3] = "";
					linkman[4] = "联系人";
					linkman[5] = linkmanName;
					linkman[6] = "--";
					linkman[7] = mobile2;
					linkman[8] = telephone2;
					linkman[9] = email2;
					list.add(linkman);			
					i=i+1;
				}
			}
			ExportExcel.contractExcel(fileName, title, sheetTitle, list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("导出 异常！！！错误信息=" + e.getMessage());
		}
	}

	public PageVo<DatabaseInfo> getCountWebsiteInfo(String siteCode, String level, Integer pos, Integer size) {
		PageVo<DatabaseInfo> databaseInfoList = null;
		try {
			DatabaseInfoRequest request = new DatabaseInfoRequest();
			request.setPageNo(pos);
			request.setPageSize(size);
			List<QueryOrder> queryOrderList = new ArrayList<QueryOrder>();
			queryOrderList.add(new QueryOrder("isorganizational", QueryOrderType.DESC));
			request.setQueryOrderList(queryOrderList);
			request.setLinkStatus(1);// 查询能连通的URL
			request.setIsexp(1);// 查询正常状态的数据
			if (!siteCode.startsWith("bm")) {
				if (siteCode.endsWith("0000")) {
					if (level.equals("1")) {// 点击查看本级部门
						request.setSiteCodeLike(siteCode);
						request.setIsorganizational(0);
						request.setLevel("1");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					} else if (level.equals("2")) {
						request.setSiteCodeLike(siteCode.substring(0, 2));
						request.setIsorganizational(1);
						request.setLevel("2");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					}
				} else if (siteCode.endsWith("00")) {
					if (level.equals("1")) {
						request.setSiteCodeLike(siteCode);
						request.setIsorganizational(0);
						request.setLevel("1");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					} else if (level.equals("2")) {
						request.setSiteCodeLike(siteCode.substring(0, 4));
						request.setIsorganizational(1);
						request.setLevel("3");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					}
				} else {
					request.setSiteCodeLike(siteCode);
					request.setLevel("3");
					request.setIsorganizational(1);
					databaseInfoList = databaseInfoServiceImpl.query(request);
					request.setIsorganizational(0);
					databaseInfoList = databaseInfoServiceImpl.query(request);
				}
			} else if (siteCode.startsWith("bm")) {
				if (siteCode.endsWith("00")) {
					if (level.equals("1")) {
						request.setSiteCodeLike(siteCode);
						request.setIsorganizational(0);
						request.setLevel("1");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					} else if (level.equals("2")) {
						request.setSiteCodeLike(siteCode.substring(0, 4));
						request.setIsorganizational(1);
						request.setLevel("2");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					}
				} else {
					if (level.equals("1")) {
						request.setSiteCodeLike(siteCode);
						request.setIsorganizational(0);
						request.setLevel("2");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					} else if (level.equals("2")) {
						request.setSiteCodeLike(siteCode);
						request.setIsorganizational(1);
						request.setLevel("3");
						databaseInfoList = databaseInfoServiceImpl.query(request);
					}
				}
			}
			return databaseInfoList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @描述:根据子类id 获得更新期限
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/14
	 * @param dicId 子类id
	 * @return
	 */
	public String getDichUpdateTime(Integer dichId){
		int id = 0;
		String updateTime = "";
		if(dichId != null){
			id = dichId;
		}
		if(id == 0){
			updateTime = "-";
		}else if(id == ChannelNotUpdateType.FIVE.getCode()){
			updateTime = ChannelNotUpdateType.TWO_WEEK_UPDATESTR.getName();
		}else if(id == ChannelNotUpdateType.SIX.getCode() || id == ChannelNotUpdateType.SEVEN.getCode()){
			updateTime = ChannelNotUpdateType.SIX_MOUTH_UPDATESTR.getName();
		}else{
			updateTime = ChannelNotUpdateType.ONE_YEAR_UPDATESTR.getName();
		}
		return updateTime;
	}
	
	/**
	 * @描述:页面请求Id 获得更新期限
	 * @作者:luocheng@ucap.com.cn
	 * @时间:2017/4/14
	 * @return
	 */
	public void getUpdateTime(){
		String idStr = request.getParameter("id"); // 栏目id
		Map<String, String> resultMap  = new HashMap<String,String>();
		String updateTime = "";
		if(StringUtils.isNotEmpty(idStr) && !"".equals(idStr)){
			int id = Integer.valueOf(idStr);
			updateTime = getDichUpdateTime(id);
		}
		resultMap.put("success", "true");
		resultMap.put("updateTime", updateTime);
		writerPrint(JSONObject.fromObject(resultMap).toString());
	}

	public DatabaseOrgInfo getDatabaseOrgInfo() {
		return databaseOrgInfo;
	}

	public void setDatabaseOrgInfo(DatabaseOrgInfo databaseOrgInfo) {
		this.databaseOrgInfo = databaseOrgInfo;
	}

	public DatabaseInfo getDatabaseInfo() {
		return databaseInfo;
	}

	public void setDatabaseInfo(DatabaseInfo databaseInfo) {
		this.databaseInfo = databaseInfo;
	}

	public DicUtils getDicUtils() {
		return dicUtils;
	}

	public void setDicUtils(DicUtils dicUtils) {
		this.dicUtils = dicUtils;
	}
	
}
