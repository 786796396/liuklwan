package com.ucap.cloud_web.action;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ucap.cloud_web.bizService.BigDataHomeBizService;
import com.ucap.cloud_web.timer.TaskAddEarly;
import com.ucap.cloud_web.timer.TaskSendEarly;


/**
 * 描述： 后台发送预警调用接口
 * 包：com.ucap.cloud_web.action
 * 文件名称：SendEarlyAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-10-18下午1:34:49 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SendEarlyAction extends BaseAction{
	@Autowired
	private TaskSendEarly taskSendEarly;
	@Autowired
	private TaskAddEarly taskAddEarly;
	@Autowired
	private BigDataHomeBizService bigDataHomeBizServiceImpl;
	private Integer type;
	private String ids;

	/**
	 * log日志加载
	 */
	private static Log logger =  LogFactory.getLog(SendEarlyAction.class);
	
	public void sendSingleEarlyInfo(){
		try {
			logger.info("================SendEarlyAction===ids:"+ids+"====type=="+type);
			System.out.println("================SendEarlyAction===ids:"+ids+"====type=="+type);
			String[] arr = ids.split(",");
			List<String> list = Arrays.asList(arr);
			taskSendEarly.sendSingleEarlyInfoHT(list, type);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void testTime(){
		//  http://localhost:8080/cloud_web/sendEarly_testTime.action?type=4
		try {
			String s = request.getParameter("type");
			logger.info(s+"========================================");
			if("1".equals(s)){//实时预警
				taskSendEarly.sendConnEarlySS();
			}else if("2".equals(s)){//填报单位  定时
				taskSendEarly.sendEarlyTimer();
			}else if("3".equals(s)){//组织单位  定时
				taskSendEarly.sendOrgEarlyTimer();
			}else if("4".equals(s)){//日报
				taskSendEarly.sendDailyEarlyInfo();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addEarly(){
	//  http://localhost:8080/cloud_web/sendEarly_addEarly.action?type=1
		try {
			String s = request.getParameter("type");
			logger.info(s+"========================================");
			if("1".equals(s)){//日报预警数据统计（添加数据）
				taskAddEarly.addEarlyDayReport();
			}else if("2".equals(s)){//单站预警数据统计（添加数据）
				taskAddEarly.addEarlyDay();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void bigPage(){
		try {
			logger.info("bigPage=====================================开始了     ===");
			bigDataHomeBizServiceImpl.createBigDataHomeJson();
			logger.info("bigPage=====================================结束了      ===");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}


	
}
