$(function() {
	
});

function bigNumber(isOrg){ //大数据跳转定位
	var uri = "";
	var url = "";
	if(isOrg == 1){
	  uri = webPath + "/siteDataOverview_siteDataOverviewChart.action";
	  url = webPath + "/siteDataOverview_siteDataOverviewChart.action";
	}else if(isOrg == 2){
	  uri = webPath + "/siteDataOverview_siteDataOverview.action";
	  url = webPath + "/dailyMonitoringStatistics_dailyMonitoringStatistics.action";
	}
	changeCookie(1,uri,url,null);
}

function spot(){ //抽查跳转定位
	var uri = webPath + "/connectionHomeDetail_indexOrg.action";
	var url = webPath + "/spotCheckResult_list.action";
	changeCookie(1,uri,null,url);
}

function proJect(){ //绩效跳转定位
	var uri = webPath + "/paProject_paProjectList.action";
	changeCookie(1,uri,uri,null);
}

