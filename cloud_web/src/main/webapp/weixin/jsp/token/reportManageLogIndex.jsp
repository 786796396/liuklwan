<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>站群监测报告通知</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>
<body>
<h3 class="tit-h3" id="report_service_Pdnum"></h3>
<div class="container-fluid mar-bom1">
	<input type="hidden" id="openId_id" value="${resultMap.openId}">
	<ul id="ul_reportMg_list">
    </ul>
</div>
<%@ include file="/weixin/jsp/footer.jsp"%>
</body>
<script type="text/javascript" language="javascript">

//全局变量  发送状态
var sendStateArr = {
		"0":"失败",
		"1":"成功"
};
$(function(){
	//初始化该页面时，给全局变量赋值
	var openId=$("#openId_id").val();

	//预警信息页面数据初始化
	getReportManageLog(openId);

	});
	//预警信息页面数据初始化
function getReportManageLog(openId){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/token_getReportManageLog.action?openId="+openId,
        dataType:"json",
        success : function(data){
	        if(data.errormsg){
	        	//隐藏标题
	        	$("#report_service_Pdnum").attr("style","display:none");
	        	alert(data.errormsg);
	        }else{
	            var  listStr="";
	        	//先清空列表数据
	        	$("#ul_reportMg_list").html("");
	        	//显示标题
	        	$("#report_service_Pdnum").attr("style","display:block");
	        	$("#report_service_Pdnum").html("第"+data.periodNum+"期("+data.startTime+")");
	        	
	        	var reportList=data.returnList;
	        	if(reportList.length>0){
		            for(var i=0;i<reportList.length;i++){
		             	listStr=listStr+"<li><span class='con-tit ellipsis col-xs-9 col-md-8'>"+reportList[i].siteName+"</span><span class='con-status col-xs-3 col-md-4 text-right'>"+sendStateArr[reportList[i].sendState]+"</span></li>";
		            }
		            $("#ul_reportMg_list").append(listStr);  
	        	}
	        }
        }
     });
}
</script>
</html>
