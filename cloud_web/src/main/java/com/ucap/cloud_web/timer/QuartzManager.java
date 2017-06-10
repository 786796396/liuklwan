package com.ucap.cloud_web.timer;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.ucap.cloud_web.dto.ConfigTimerRequest;
import com.ucap.cloud_web.entity.ConfigTimer;
import com.ucap.cloud_web.service.IConfigTimerService;

public class QuartzManager implements BeanFactoryAware {

	private Scheduler scheduler;
	private static BeanFactory beanFactory = null;
	
	@Autowired
	private IConfigTimerService configTimerServiceImpl;
	
	private Logger log = Logger.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private void reScheduleJob() throws Exception, ParseException {
		ConfigTimerRequest request = new ConfigTimerRequest();
		request.setType(1);
		request.setPageSize(Integer.MAX_VALUE);
		List<ConfigTimer> quartzList = configTimerServiceImpl.queryList(request);
	
		// this.getConfigQuartz();
		if (quartzList != null && quartzList.size() > 0) {
			for (ConfigTimer configTimer : quartzList) {
				configQuatrz(configTimer);
			}
		}

	}

	public boolean configQuatrz(ConfigTimer configTimer) {
		
		boolean result = false;
		try {
			// 运行时可通过动态注入的scheduler得到trigger
			CronTriggerBean trigger = (CronTriggerBean) scheduler.getTrigger(configTimer.getTriggerName(),
					Scheduler.DEFAULT_GROUP);
			// 如果计划任务已存在则调用修改方法
			if (trigger != null) {
				change(configTimer, trigger);
			} else {
				// 如果计划任务不存在并且数据库里的任务状态为可用时,则创建计划任务
				if (configTimer.getState().equals("1")) {
					this.createCronTriggerBean(configTimer);
				}
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}

	
	public void change(ConfigTimer configTimer, CronTriggerBean trigger)  
	        throws Exception {  
	    // 如果任务为可用  
	    if (configTimer.getState().equals("1")) {  
	        // 判断从DB中取得的任务时间和现在的quartz线程中的任务时间是否相等  
	        // 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob  
	        if (!trigger.getCronExpression().equalsIgnoreCase(  
	        		configTimer.getCronExpression())) {  
	            trigger.setCronExpression(configTimer.getCronExpression());  
	            scheduler.rescheduleJob(configTimer.getTriggerName(),  
	                    Scheduler.DEFAULT_GROUP, trigger);  
	            log.info(new Date() + ": 更新" + configTimer.getTriggerName() + "计划任务");  
	        }  
	    } else {  
	        // 不可用  
	        scheduler.pauseTrigger(trigger.getName(), trigger.getGroup());// 停止触发器  
	        scheduler.unscheduleJob(trigger.getName(), trigger.getGroup());// 移除触发器  
	        scheduler.deleteJob(trigger.getJobName(), trigger.getJobGroup());// 删除任务  
	        log.info(new Date() + ": 删除" + configTimer.getTriggerName() + "计划任务");  
	  
	    }  
	  
	}  
	
	/** 
	 * 创建/添加计划任务 
	 *  
	 * @param tbcq 
	 *            计划任务配置对象 
	 * @throws Exception 
	 */  
	public void createCronTriggerBean(ConfigTimer configTimer) throws Exception { 
	    // 新建一个基于Spring的管理Job类  
	    MethodInvokingJobDetailFactoryBean mjdfb = new MethodInvokingJobDetailFactoryBean();  
	    mjdfb.setName(configTimer.getJobDetailName());// 设置Job名称  
	    // 如果定义的任务类为Spring的定义的Bean则调用 getBean方法  
	    if (configTimer.getIsSpringbean().equals("1")) {  
	        mjdfb.setTargetObject(beanFactory.getBean(configTimer.getTargetObject()));// 设置任务类  
	    } else {  
	        // 否则直接new对象  
	        mjdfb.setTargetObject(Class.forName(configTimer.getTargetObject())  
	                .newInstance());// 设置任务类  
	    }  
	  
	    mjdfb.setTargetMethod(configTimer.getMethodName());// 设置任务方法  
	    mjdfb.setConcurrent(configTimer.getConcurrent().equals("0") ? false : true); // 设置是否并发启动任务  
	    mjdfb.afterPropertiesSet();// 将管理Job类提交到计划管理类  
	    // 将Spring的管理Job类转为Quartz管理Job类  
	    JobDetail jobDetail = new JobDetail();  
	    jobDetail = (JobDetail) mjdfb.getObject();  
	    jobDetail.setName(configTimer.getJobDetailName());  
	    scheduler.addJob(jobDetail, true); // 将Job添加到管理类  
	    // 新一个基于Spring的时间类  
	    CronTriggerBean c = new CronTriggerBean();  
	    c.setCronExpression(configTimer.getCronExpression());// 设置时间表达式  
	    c.setName(configTimer.getTriggerName());// 设置名称  
	    c.setJobDetail(jobDetail);// 注入Job  
	    c.setJobName(configTimer.getJobDetailName());// 设置Job名称  
	    scheduler.scheduleJob(c);// 注入到管理类  
	    scheduler.rescheduleJob(configTimer.getTriggerName(), Scheduler.DEFAULT_GROUP,  
	            c);// 刷新管理类  
	    log.info(new Date() + ": 新建" + configTimer.getTriggerName() + "计划任务");  
	}  
	  
	public Scheduler getScheduler() {  
	    return scheduler;  
	}  
	  
	public void setScheduler(Scheduler scheduler) {  
	    this.scheduler = scheduler;  
	}  
	  
	public void setBeanFactory(BeanFactory factory) throws BeansException {  
	    this.beanFactory = factory;  
	  
	}  
	  
	public BeanFactory getBeanFactory() {  
	    return beanFactory;  
	}  

}