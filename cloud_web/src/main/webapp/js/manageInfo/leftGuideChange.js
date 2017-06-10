//预警引导样式
var siteCode = $("#guideSiteCode").val();
  window.onload=window.onresize=function() {
   var guideNext = $("#guideNext").val();
        var ostep = document.getElementById('steps-sec');
        oH = document.documentElement.clientHeight || document.body.clientHeight;
        oW = document.documentElement.clientWidth || document.body.clientWidth;
        var ostep_height = oH+59;
        var ostep_width = oW;
        ostep.style.height = ostep_height + 'px';
        ostep.style.width = ostep_width + 'px';
        if(guideNext==1){
        	$("#ydfifsteps").show();
        	$("#yd-second-steps").hide();
        	 $("#tb-yd-second-steps").hide();
        }
        
	$.ajax({
		type : "POST",
		url :webPath+"/databaseInfo_selectGuiteState.action",
		data:{
			siteCode:siteCode
		},
		async : false,
		dataType:"json",
		success : function(data) {
			if(data.guiDeState==1){
			  $("#steps-sec").hide();
	        $("#yd-second-steps").hide();
	        $("#tb-yd-second-steps").hide();
			}
		}
	});
// 	/隐藏头部预警
	$("#steps").hide(); 
	$("#guideSelection").hide();
    } 
     //预警引导事件
    function guideClose(){
	    $("#steps-sec").hide(); 
	    $("#ydfifsteps").hide();
	    $("#tb-yd-second-steps").hide();
	 	$.ajax({
				type : "POST",
				url :webPath+"/databaseInfo_updateGuiteState.action",
				data:{
					siteCode:siteCode,
				},
				async : false,
				dataType:"json",
				success : function(data) {
// 					if(data.success){
// 						 $("#steps-sec").hide(); 
// 	   					 $("#ydfifsteps").hide();
// 					}
				}
					
			});
    }
    function nextChange(){
	    if(${(fn:length(sessionScope.shiroUser.siteCode))!=6 && (sessionScope.shiroUser.iscostOwn == 1)}){
			$("#steps-sec").hide();
			location.href = webPath+'/configEarly_configEarlyTB.action';
		}
		else if(${(fn:length(requestScope.siteCode))==6 && (fn:length(sessionScope.shiroUser.siteCode))==6 && (sessionScope.shiroUser.isOrgCost == 1)})
		{
			$("#steps-sec").hide();
			location.href = webPath+'/configEarly_configEarlyOrg.action';
		};
    }
    function nextChangeLast(){
    	if(${(fn:length(sessionScope.shiroUser.siteCode))!=6 && (sessionScope.shiroUser.iscostOwn == 1)}){
			$("#steps-sec").hide();
			location.href = webPath+'/configEarly_configEarlyTB.action?nextChangeLast=6';
		}
		else if(${(fn:length(requestScope.siteCode))==6 && (fn:length(sessionScope.shiroUser.siteCode))==6 && (sessionScope.shiroUser.isOrgCost == 1)})
		{
			$("#steps-sec").hide();
			location.href = webPath+'/configEarly_configEarlyOrg.action?nextChangeLast=6';
		};
    
    	
    }