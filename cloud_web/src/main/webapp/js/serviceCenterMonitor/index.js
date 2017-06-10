$(function() {
	
});

function freeCheck(){ //免费检测
	  var uri = webPath + "/connectionHomeDetail_indexOrg.action"; 
	  var url = webPath + "/connectionHomeDetail_webSiteConnected.action";
	  changeCookie(1,uri,null,url);
	
}

function depthLinkAll(){ //全站死链
	var uri = webPath + "/connectionHomeDetail_indexOrg.action"; 
	var url = webPath + "/depthLinkAll_depthLinkAllMain.action";
	  changeCookie(1,uri,null,url);
	
}

function depthChannel(){ //深度检测-栏目
	var uri = webPath + "/connectionHomeDetail_indexOrg.action"; 
	  url = webPath + "/connectionChannelDetail_indexOrg.action";
	  changeCookie(1,uri,null,url);
}

function depthCorrect(){ //深度检测-错别字
	var uri = webPath + "/connectionHomeDetail_indexOrg.action"; 
	var url = webPath + "/correctContent_indexOrg.action";
	  changeCookie(1,uri,null,url);
}

function depthTotal(){ //深度检测-全面检测
	var uri = webPath + "/connectionHomeDetail_indexOrg.action"; 
	var url = webPath + "/servicePeriod_servicePeriod.action?type=1";
	  changeCookie(1,uri,null,url);
}

function depthSafe(){ //深度检测-安全检测
	var uri = webPath + "/monitorSilentResultOrg_indexOrg.action"; 
	var url = webPath + "/monitorSilentResultOrg_indexOrg.action";
	  changeCookie(1,uri,null,url);
}


