$(function() {

});

function freeCheckTb() { // 免费检测
	var uri = webPath + "/fillUnit_gailan.action";
	var url = webPath + "/connectionHomeDetail_index.action";
	changeCookie(2, uri, null, url);

}

function depthLinkAllTb() { // 全站死链
	var uri = webPath + "/fillUnit_gailan.action";
	var url = webPath + "/linkAll_linkAllDetailIndex.action";
	changeCookie(2, uri, null, url);

}

function depthChannelTb() { // 深度检测-栏目
	var uri = webPath + "/fillUnit_gailan.action";
	var url = webPath + "/connectionBusinessDetail_connectivity.action";
	changeCookie(2, uri, null, url);
}

function depthCorrectTb() { // 深度检测-错别字
	var uri = webPath + "/fillUnit_gailan.action";
	var url = webPath + "/correctContent_index.action";
	changeCookie(2, uri, null, url);
}

function depthTotalTb() { // 深度检测-全面检测
	var uri = webPath + "/fillUnit_gailan.action";
	var url = webPath + "/servicePeriod_servicePeriod.action?type=2";
	changeCookie(2, uri, null, url);
}

function depthSafeTb() { // 深度检测-安全检测
	var uri = webPath + "/monitorSilentResult_indexTB.action";
	var url = webPath + "/monitorSilentResult_indexTB.action";
	changeCookie(2, uri, null, url);
}
