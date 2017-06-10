$(function() {
	$.ajax({
	    async: false,
	    cache: false,
	    type: 'POST',
	    dataType: "json",
	    data:{siteCode:$("#siteCode").val()},
	    url: webPath + "/bigDataTrend_bigGetList.action",// 请求的action路径
	    success: function (data) { // 请求成功后处理函数。
	    	//趋势数值颜色
	    	var trendColor=null;
	    	//判断趋势数据正负
	    	var typeNumber = null;
	    	//接受集合不同的参数
	    	var paraOne=null;
	    	var parameterOne=null;
	    	var paraTwo=null;
	    	var parameterTwo=null;
	    	var paraThree=null;
	    	var parameterThree=null;
	    	var sitesList = data.sitesList;
	    	var portalList = data.portalList;
	    	var balanceList = data.balanceList;
	    	var bDetail = data.bDetail;
	    	var tapName = data.tapName;
//	    	本级站点
	    	$("#tabNameOne").html(bDetail.tabNameOne);
	    	
	    	$("#tabNameTwo").html(bDetail.tabNameTwo);
//	    	下级地方站群
	    	$("#tabNameThree").html(bDetail.tabNameThree);
	    	$(".tapName").text(tapName);
	    	//下级地方站群
	    	if(sitesList!=null){
	    		for (var i=0;i<sitesList.length;i++) {
		    	    if (sitesList[i].name.length > 10) {
		    	    	sitesList[i].name = sitesList[i].name.substring(0,10)+"...";
		    	    }
		    	    if(bDetail.focusType==1){
		    	    	paraOne=sitesList[i].healthindex;
		    	    	if(paraOne==null){
		    	    		paraOne="";
		    	    	}
		    	    	parameterOne=sitesList[i].healthindexTrend;
		    	    	if(parameterOne!=null && parameterOne!=""){
		    	    		parameterOne=sitesList[i].healthindexTrend+"%";
		    	    	}else{
		    	    		parameterOne="0";
		    	    	}
		    	    }else if(bDetail.focusType==2){
		    	    	paraOne=sitesList[i].linkerrsiteprop;
		    	    	if(paraOne!=null && paraOne!=""){
		    	    		paraOne=sitesList[i].linkerrsiteprop+"%";
		    	    	}else{
		    	    		paraOne="";
		    	    	}
		    	    	parameterOne=sitesList[i].linkerrsitepropTrend;
		    	    	if(parameterOne!=null && parameterOne!=""){
		    	    		parameterOne=sitesList[i].linkerrsitepropTrend+"%";
		    	    	}else{
		    	    		parameterOne="0";
		    	    	}
		    	    }else if(bDetail.focusType==3){
		    	    	paraOne=sitesList[i].updatenum;
		    	    	if(paraOne==null){
		    	    		paraOne="";
		    	    	}
		    	    	parameterOne=sitesList[i].updatenumTrend;
		    	    	if(parameterOne!=null && parameterOne!=""){
		    	    		parameterOne=sitesList[i].updatenumTrend+"%";
		    	    	}else{
		    	    		parameterOne="0";
		    	    	}
		    	    }
	    	    	typeNumber=parameterOne.indexOf("-");
		    	    if(typeNumber<0){
		    	    	trendColor="fl third-row colo-f00";
		    	    }else{
		    	    	trendColor="fl third-row colo-9ac";
		    	    }
		    	    $("#balance").append("<li class='tb-detail'>"+
		                    "<ul class='clearfix'>"+
		                    "<a href="+bDetail.focusUrlThree+" target="+"_blank"+"><li class='fl first-row'><span>"+sitesList[i].name+"</span></li></a>"+
		                    "<a href="+bDetail.focusUrlThree+" target="+"_blank"+"><li class='fl second-row'>"+paraOne+"</li></a>"+
		                    "<li class='"+trendColor+"'>"+parameterOne+"</li>"+
		                    "</ul>"+
	    	    		"</li>");
					}
	    	}else{
	    		$("#balance").append("<li><div class='zanwsj'>暂无数据</div></li>");
	    	}	
	    	//下级地方门户
	    	if(portalList!=null){
	    		for (var m=0;m<portalList.length;m++) {
    				if (portalList[m].name.length > 10) {
    					portalList[m].name = portalList[m].name.substring(0,10)+"...";
		    	    }
    				if(bDetail.focusType==1){
    					paraTwo=portalList[m].healthindex;
    					
		    	    	if(paraTwo==null){
		    	    		paraTwo="";
		    	    	}
		    	    	parameterTwo=portalList[m].healthindexTrend;
		    	    	if(parameterTwo!=null && parameterTwo!=""){
		    	    		parameterTwo=portalList[m].healthindexTrend+"%";
		    	    	}else{
		    	    		parameterTwo="0";
		    	    	}
		    	    }else if(bDetail.focusType==2){
		    	    	paraTwo=portalList[m].linkerrsiteprop;
		    	    	if(paraTwo!=null && paraTwo!=""){
		    	    		paraTwo=portalList[m].linkerrsiteprop+"%";
		    	    	}else{
		    	    		paraTwo="";
		    	    	}
		    	    	parameterTwo=portalList[m].linkerrsitepropTrend;
		    	    	if(parameterTwo!=null && parameterTwo!=""){
		    	    		parameterTwo=portalList[m].linkerrsitepropTrend+"%";
		    	    	}else{
		    	    		parameterTwo="0";
		    	    	}
		    	    }else if(bDetail.focusType==3){
		    	    	paraOne=portalList[m].updatenum;
		    	    	if(paraOne==null){
		    	    		paraOne="";
		    	    	}
		    	    	parameterTwo=portalList[m].updatenumTrend;
		    	    	if(parameterTwo!=null && parameterTwo!=""){
		    	    		parameterTwo=portalList[m].updatenumTrend+"%";
		    	    	}else{
		    	    		parameterTwo="0";
		    	    	}
		    	    }
    				typeNumber=parameterTwo.indexOf("-");
    				if(typeNumber<0){
			    	    	trendColor="fl third-row colo-f00";
			    	    }else{
			    	    	trendColor="fl third-row colo-9ac";
			    	    }
    				$("#portal").append("<li class='tb-detail'>"+
		    				"<ul class='clearfix'>"+
		    				"<a href="+bDetail.focusUrlTwo+" target="+"_blank"+"><li class='fl first-row'><span>"+portalList[m].name+"</span></li></a>"+
		    				"<a href="+bDetail.focusUrlTwo+" target="+"_blank"+"><li class='fl second-row'>"+paraTwo+"</li></a>"+
		    				"<li class='"+trendColor+"'>"+parameterTwo+"</li>"+
		    				"</ul>"+
		    		"</li>");
		    	};
	    	}else{
	    		$("#portal").append("<li><div class='zanwsj'>暂无数据</div></li>"); 
	    	}
	    	//本级站点
	    	if(balanceList!=null){
	    		for (var j=0;j<balanceList.length;j++) {
	    			if (balanceList[j].name.length > 10) {
	    				balanceList[j].name = balanceList[j].name.substring(0,10)+"...";
		    	    	}
	    			if(bDetail.focusType==1){
		    	    	paraThree=balanceList[j].healthindex;
		    	    	if(paraThree==null){
		    	    		paraThree="";
		    	    	}
		    	    	parameterThree=balanceList[j].healthindexTrend;
		    	    	if(parameterThree!=null && parameterThree!=""){
		    	    		parameterThree=balanceList[j].healthindexTrend+"%";
		    	    	}else{
		    	    		parameterThree="0";
		    	    	}
		    	    }else if(bDetail.focusType==2){
		    	    	paraThree=balanceList[j].linkerrsiteprop;
		    	    	if(paraThree!=null && paraThree!=""){
		    	    		paraThree=balanceList[j].linkerrsiteprop+"%";
		    	    	}else{
		    	    		paraThree="";
		    	    	}
		    	    	parameterThree=balanceList[j].linkerrsitepropTrend;
		    	    	if(parameterThree!=null && parameterThree!=""){
		    	    		parameterThree=balanceList[j].linkerrsitepropTrend+"%";
		    	    	}else{
		    	    		parameterThree="0";
		    	    	}
		    	    }else if(bDetail.focusType==3){
		    	    	paraThree=balanceList[j].updatenum;
		    	    	if(paraThree==null){
		    	    		paraThree="";
		    	    	}
		    	    	parameterThree=balanceList[j].updatenumTrend;
		    	    	if(parameterThree!=null && parameterThree!=""){
		    	    		parameterThree=balanceList[j].updatenumTrend+"%";
		    	    	}else{
		    	    		parameterThree="0";
		    	    	}
		    	    }
	    			typeNumber=parameterThree.indexOf("-");
	    			if(typeNumber<0){
			    	    	trendColor="fl third-row colo-f00";
			    	    }else{
			    	    	trendColor="fl third-row colo-9ac";
			    	    }
	    			$("#sites").append("<li class='tb-detail'>"+
		    				"<ul class='clearfix'>"+
		    				"<a href="+bDetail.focusUrlOne+" target="+"_blank"+"><li class='fl first-row'><span>"+balanceList[j].name+"</span></li></a>"+
		    				"<a href="+bDetail.focusUrlOne+" target="+"_blank"+"><li class='fl second-row'>"+paraThree+"</li></a>"+
		    				"<li class='"+trendColor+"'>"+parameterThree+"</li>"+
		    				"</ul>"+
		    		"</li>");
		    	}
	    	}else{
	    		$("#sites").append("<li><div class='zanwsj'>暂无数据</div></li>");
	    	}
	    }
	});
});

//显示下级地方站群信息
function spaceSites() {
	$("#tabNameOne").addClass("on");
	$("#tabNameTwo").removeClass("on");
	$("#tabNameThree").removeClass("on");
	$("#spaceSites").attr("style", "display:block;");
	$("#spacePortal").attr("style", "display:none;");
	$("#spaceBalance").attr("style", "display:none;");
}
//显示下级地方门户信息
function spacePortal() {
	$("#tabNameOne").removeClass("on");
	$("#tabNameTwo").addClass("on");
	$("#tabNameThree").removeClass("on");
	$("#spaceSites").attr("style", "display:none;");
	$("#spacePortal").attr("style", "display:block;");
	$("#spaceBalance").attr("style", "display:none;");
}
//显示本级站点信息
function spaceBalance() {
	$("#tabNameOne").removeClass("on");
	$("#tabNameTwo").removeClass("on");
	$("#tabNameThree").addClass("on");
	$("#spaceSites").attr("style", "display:none;");
	$("#spacePortal").attr("style", "display:none;");
	$("#spaceBalance").attr("style", "display:block;");
}

