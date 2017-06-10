package com.ucap.cloud_web.constant;

public enum TimedTaskLogType {
	
	//(1:首页更新，2：栏目更新，3.首页死链，4.内容正确性 ，5,获取更新，首页死链，错别字数据任务，6.连通性任务，7.汇总数据任务，8.健康指数任务)
	HOME_UPDATE(1, "首页更新错误日志"),
	CHANNEL_UPDATE(2, "栏目更新错误日志"),
	HOME_LINK(3, "首页死链错误日志"),
	CONTENT_CORRECT(4, "内容正确性错误"),
	FETCH_TASK(5,"获取首页死链，更新数据任务"),
	CONNECT_TASK(6,"连通性任务(5分钟、15分钟、1天队列)"),
	RESULT_TASK(7,"汇总数据任务"),
	INDEX_TASK(8,"健康指数任务"),
	FETCH_UPDATE_TASK(9,"获取更新数据任务"),
	SYNCDB_STATUS_TASK(10,"同步基本数据状态任务"),	
	INDEX_F_TASK(12,"站点数据健康指数任务"),
	FETCH_LINK_ALL(13,"全站死链任务"),
	CONNECTION_HOME_TASK(14,"站点连通性状态切换"),
	CHANNEL_TASK(15,"栏目同步任务"),
	CONNECTEXP_TASK(17,"关停例外连通性任务"),
	PEROID_COUNT_TASK(18,"按服务周期统计站点数据任务"),
	ANALYZE_RESULT(19,"大数据分析-同步组织单位汇总数据任务"),
	ANALYZE_SITE_RESULT(20,"大数据分析-同步填报单位汇总数据任务"),
	SYNCDB_INCLUDE(21,"获取百度收录数据任务"),
	SILENT_COUNT(22,"无声安全汇总数据任务"),
	SILENT_FETCH(23,"无声安全获取数据任务"),
	SILENT_START(24,"启动无声安全任务"),
	SILENT_STOP(25,"关闭无声安全任务"),
	ANALYZE_HEALTH(26,"大数据汇总健康指数任务"),
	ANALYZE_TREND(27,"大数据指数趋势任务"),
	PEROID_SITE_TASK(28,"填报单位：服务周期汇总数据任务"),
	PEROID_ORG_TASK(29,"组织单位：服务周期汇总数据任务"),
	CONNECTION_ALL_TASK(30,"站点连通性扫描次数任务"),
	ABNORMAL_TASK(31,"同步疑似异常任务信息任务"),
	BIG_HOME_TASK(101,"大数据首页生成json数据任务");
	
	
	private Integer code;
	private String name;

	private TimedTaskLogType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
