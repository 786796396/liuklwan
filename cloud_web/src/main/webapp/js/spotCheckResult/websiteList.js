
var batchNum="";
var groupNum="";
var scheduleId="";
var status="";
var flagAll;//抽查进度id
$(function(){
	$("#datepicker").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		onSelect: function(dateText, inst) {
			initPage(dateText);
		},
		numberOfMonths:1,//显示几个月  
		showButtonPanel:false,//是否显示按钮面板  
		dateFormat: 'yy-mm-dd',//日期格式  
		clearText:"清除",//清除日期的按钮名称  
		closeText:"关闭",//关闭选择框的按钮名称  
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		defaultDate:GetDateStr(0),//默认日期
		minDate:0,//最小日期
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六']
	});
	
	//批次
	batchNum=$("#batchNum_id").val();
	//组次
	groupNum=$("#groupNum_id").val();
	flagAll = $("#flagAll").val();

	//获取抽查进度表数据
	getSpotSchedule();
	
	//根据抽查批次，查询抽查结果表
	getSpotResult();
	
	
    //抽查站点详情页面--关键字查询
    $("#keyWord_id").keyup(function(){
		var searchInfo=$("#keyWord_id").val();
		 if(searchInfo==""){
			 $("#result_table_id tr").show();
		 }else{
			 $("#result_table_id").find(".key").each(function(index, element) {
				 if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });
    
    //增加站点--点击事件
    $("#add_site_id").bind("click",function(){
    	window.location.href=webPath+"/spotCheckResult_list.action?scheduleId="+scheduleId+"&flag=1";
    });
    //导出excel--点击事件
    $("#excel_out_id").bind("click",function(){
    	var keyWord=$("#keyWord_id").val();
    	window.location.href=webPath+"/spotCheckResult_excelSpotResult.action?scheduleId="+scheduleId+"&keyWord="+keyWord;
    });
    //全部启动
    $("#start_all_id").bind("click",function(){
    	openAllCheckResult();
    });
    //通知整改新增
    $("#noticeAdd").bind("click",function(){
    	var boxInput1 = "";
    	var boxInput2 = "";
    	if($("#checkbox1").attr("class").indexOf("on_check")!=-1){
    		boxInput1 = $("#boxInput1").val();
    	}
    	if($("#checkbox2").attr("class").indexOf("on_check")!=-1){
    		boxInput2 = $("#boxInput2").val();
    	}
    	if($("#datepicker").val()==null || $("#datepicker").val()==""){
    		alert("请选择整改期限");
    		return false;
    	}
    	/*if(boxInput1=="" && boxInput2==""){
    		alert("请选择通知邮箱");
    		return false;
    	}*/
    	var noticeRequirement = $("#noticeRequirement").val();
    	if(noticeRequirement != ""){
    		if(noticeRequirement.length>250){
    			alert("内容过长，请重新输入整改要求");
    			return false;
    		}
    	}
    	noticeAdd(boxInput2,boxInput1);
    });
    //通知整改下载报告
    $("#downWord").bind("click",function(){
    	uploadReportWord();
    });
	//通知整改预览报告
    $("#SeeReport").bind("click",function(){
    	searchReportWord();
    });
    //通知整改报告下载
    $("#reports").bind("click",function(){
    	downLoadWordFile();
    });
});

//全部启动
function openAllCheckResult(){
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_openAllCheckResult.action?scheduleId="+scheduleId,
        success:function(data){
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		alert(data.success);
        		$("#add_site_id").attr("style","display:none");//添加站点和全部启动不可点击
            	$("#start_all_id").attr("style","display:none");//全部启动不可点击
        		window.location.reload(true);
        	}
        },error:function(data){
        	alert(data.errorMsg);
        }
    });
	
}

//状态（0：未启动，1：检查中，2：检查完成）
var statusList={
		"0":"未启动",
		"1":"检查中",
		"2":"检查完成"
};
//获取抽查进度表数据
function getSpotSchedule(){
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_getSpotSchedule.action?batchNum="+batchNum+"&groupNum="+groupNum+"&flagAll="+flagAll,// 请求的action路径
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		
                $("#batch_id").html("第"+data["batchNum"]+"批");
                $("#group_id").html("第"+data["groupNum"]+"组");
                $("#number_id").html("("+data["spotWebsiteNum"]+"个)");
                $("#state_id").html("任务状态："+statusList[data["status"]]);
                $("#taskName_id").html(data["taskName"]);
                $("#data_id").html(data["startDate"]+"&nbsp;至&nbsp;"+data["endDate"]);
                if(data["status"]!=0){
            		$("#add_site_id").attr("style","display:none");//添加站点和全部启动不可点击
                	$("#start_all_id").attr("style","display:none");//全部启动不可点击

                }
                
                scheduleId=data["scheduleId"];
                status=data["status"];
        	}
		},error:function(data){
			alert(data.errorMsg);
		}
  });
}

//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
var resultStateList={
		"-1":"未提交",
		"0":"未启动",
		"1":"检查中",
		"2":"扫描完成",
		"3":"报告完成"
};
//检查结果（1：合格，0：单项否决,2:未检查）
var checkResultList={
		"0":"单项否决",
		"1":"合格",
		"2":"未检查"	
};
//报告审核结果（0：待审核，1：通过,2:不通过）
var checkReportResultList={
		"0":"未通知",
		"1":"未反馈",
		"2":"已反馈"
};

//nameHover 站名信息悬浮显示
nameHover("spot_result_tbody_id");

//根据抽查批次，查询抽查结果表
function getSpotResult(){
	
	$.ajax({
		async:false,
		cache:false,
		type:"POST",
		dataType:"json",
		url:webPath + "/spotCheckResult_getSpotResult.action?scheduleId="+scheduleId,// 请求的action路径
		success:function(data){
			if(data.errorMsg){
				alert(data.errorMsg);
			}else{
				var returnList=data["returnList"];
				
				var listStr="";
				//清空表格数据
				$("#spot_result_tbody_id").html("");
				if(returnList && returnList.length>0){
					
					for(var i=0;i<returnList.length;i++){
						
						listStr+="<tr><td class='td_left font_701999'><a target='_blank' href='"+webPath+"/fillUnit_gailan.action?siteCode="+returnList[i]["siteCode"]+"'>"+returnList[i]["siteCode"]+"</a></td>";
						
						if(returnList[i]["siteCode"]==1){//门户网站
							listStr+="<td class='td_left'><div class='info-box wz-name'><i class='star'></i><a target='_blank' class='key' href='"+returnList[i]["url"]+"/'>"+returnList[i]["siteName"]+"</a>"
							+"<div class='chouc-hover-div'></div><div class='info-con'>"
                            +"<div><label>单位名称：</label>"+returnList[i]["siteManageUnit"]+"</div>"
                            +"<div><label>办公地址：</label>"+(returnList[i]["officeAddress"]||"无")+"</div>"
                            +"<h3>负责人信息</h3>"
                            +"<div><label>姓名：</label>"+returnList[i]["relationName"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["relationCellphone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["relationPhone"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["relationEmail"]+"</div>"
                            +"<h3>联系人信息</h3>"
                            +"<div><label>姓名：</label>"+returnList[i]["linkman"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["linkmanCellphone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["linkmanPhone"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["linkmanEmail"]+"</div>"
                            +"</div>"
                            +"</div></td>";
						}else{//非门户
							listStr+="<td class='td_left'><div class='info-box wz-name'><a target='_blank' class='key' href='"+returnList[i]["url"]+"'>"+returnList[i]["siteName"]+"</a>"
							+"<div class='chouc-hover-div'></div><div class='info-con'>"
                            +"<div><label>单位名称：</label>"+returnList[i]["siteManageUnit"]+"</div>"
                            +"<div><label>办公地址：</label>"+(returnList[i]["officeAddress"]||"无")+"</div>"
                            +"<h3>负责人信息</h3>"
                            +"<div><label>姓名：</label>"+returnList[i]["relationName"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["relationCellphone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["relationPhone"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["relationEmail"]+"</div>"
                            +"<h3>联系人信息</h3>"
                            +"<div><label>姓名：</label>"+returnList[i]["linkman"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["linkmanCellphone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["linkmanPhone"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["linkmanEmail"]+"</div>"
                            +"</div>"
							+"</div></td>";
						}
						
						listStr+="<td class='td_left' style='width:60px;'><span title='"+returnList[i]["province"]+"'>"+returnList[i]["province"]+"</span></td>"+
						"<td class='td_left' style='width:60px;'><span  title='"+returnList[i]["city"]+"'>"+returnList[i]["city"]+"</span></td>"+
						"<td class='td_left' style='width:60px;'><span  title='"+returnList[i]["county"]+"'>"+returnList[i]["county"]+"</span></td>"+
						"<td class='td_left'>"+resultStateList[returnList[i]["resultState"]]+"</td>"+
						"<td class='td_left'>"+checkReportResultList[returnList[i]["checkReportResult"]]+"</td>";
/*						"<td class='td_left'>"+checkResultList[returnList[i]["checkResult"]]+"</td>"+
						"<td class='td_left'>合格</td>"+*/
						if(status != 0){
							if(returnList[i]["isDown"]==1){
								listStr+="<td class='td_left clearfix'><span class='yulan-active fl' title='查看报告' onclick='searchReport(this)'></span><span class='download-active fr' onclick='uploadReport(this)' title='下载报告' ><input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td>";
							}else{
								listStr+="<td class='td_left clearfix'><span class='yulan-active fl' title='查看报告' onclick='searchReport(this)'></span><span class='download-qmjc fr' onclick='uploadReport(this)' title='下载报告' ><input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td>";
							}
						}else{
							listStr+="<td class='td_left clearfix'><span class='yulan-qmjc fl' title='查看报告' onclick='searchReport(this)'></span><span class='download-qmjc fr' onclick='uploadReport(this)' title='下载报告' ><input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td>";
						}
						if(returnList[i]["checkReportResult"]==0 && returnList[i]["resultState"]==3){
							listStr+="<td class='clearfix' style='width: 110px;'><span class='tzzg-green' onclick='spotPass(this)' data-toggle='modal' data-target='#myModalNotice'>通知整改</span></td></tr>";
						}else if(returnList[i]["checkReportResult"]==1 && returnList[i]["resultState"]==3){
							listStr+="<td class='clearfix' style='width: 110px;'><span class='ckfk-gray' title='填报单位未完成整改'>查看反馈</span></td></tr>";
						}else if(returnList[i]["checkReportResult"]==2 && returnList[i]["resultState"]==3){
							listStr+="<td class='clearfix' style='width: 110px;'><span class='ckfk-yellow' onclick='getNotice(this)' data-toggle='modal' data-target='#myModalNoticeSee' title='填报单位已完成整改'>查看反馈<input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td></tr>";
						}else{
							listStr+="<td class='clearfix' style='width: 110px;'><span class='ckfk-gray'>通知整改</span></td></tr>";
						}
					}
					$("#spot_result_tbody_id").append(listStr);
				}
			}
		},error:function(data){
			alert(data.errorMsg);
		}
	});
}

//整改通知,发送通知页
function spotPass(obj){
	var siteCode=$(obj).parent().parent().find(".font_701999").find("a").html();
	var isDown = $(obj).parent().find("input").val();
	
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckNotice_getDataBaseInfo.action?siteCode="+siteCode,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
				$("#siteName").html(data.databaseInfo['name']);
				$("#linkmanName").html(data.databaseInfo['linkmanName']);
				$("#email2").html(data.databaseInfo['email2']);
				$("#principalName").html(data.databaseInfo['principalName']);
				$("#email").html(data.databaseInfo['email']);
				$("#requireTime").html(data.requireTime);
				$("#director").html(data.director);
				$("#isDown").val(isDown);
				$("#siteCode").val(data.databaseInfo['siteCode']);
				$("#boxInput1").val(data.databaseInfo['linkmanName']+","+data.databaseInfo['email2']);
				$("#boxInput2").val(data.databaseInfo['principalName']+","+data.databaseInfo['email']);
				$("#datepicker").val("");
				$("#noticeRequirement").val("");
				if($("#checkbox1").attr("class").indexOf("on_check")==-1){
					$("#checkbox1").addClass("on_check");
				}
				if($("#checkbox2").attr("class").indexOf("on_check")==-1){
					$("#checkbox2").addClass("on_check");
				}
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

//整改通知反馈查看
function getNotice(obj){
	var siteCode=$(obj).parent().parent().find(".font_701999").find("a").html();
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckNotice_getNotice.action?siteCode="+siteCode+"&scheduleId="+scheduleId,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
				$("#siteNames").html(data.databaseInfo['name']);
				$("#requireTimes").html(data.requireTime);
				$("#directors").html(data.spotCheckNotice['director']);
				$("#noticeRequirements").html(data.spotCheckNotice['noticeRequirement']);
				$("#noticeIds").val(data.spotCheckNotice['id']);
				$("#noticeResponses").html(data.spotCheckNotice['noticeResponse']);
				if(data.spotCheckNotice['email2'] != null && data.spotCheckNotice['email2'] != ""){
					$("#linkmanNames").html(data.databaseInfo['linkmanName']);
					$("#email2s").html(data.databaseInfo['email2']);
				}
				if(data.spotCheckNotice['email'] != null && data.spotCheckNotice['email'] != ""){
					$("#principalNames").html(data.databaseInfo['principalName']);
					$("#emails").html(data.databaseInfo['email']);
				}
				$("#endTimes").html(data.endTime);
				$("#reports").html(data.spotCheckNotice['noticeReportName']);
				if(data.spotCheckNotice['noticeReportUrl'] != null){
					$("#fid").val(data.spotCheckNotice['id']);
				}
				$("#questionNum").html(data.spotCheckNotice['questionNum']);
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

//整改通知,保存
function noticeAdd(boxInput1,boxInput2){
	$.ajax({
		async: false,
		cache: false,
		type: 'POST',
		dataType: "json",
		url: webPath + "/spotCheckNotice_noticeAdd.action",
		data : {
			siteCode:$("#siteCode").val(),
			scheduleId:scheduleId,
			director:$("#director").html(),
			datepicker:$("#datepicker").val(),
			noticeRequirement:$("#noticeRequirement").val(),
			boxInput1:boxInput1,
			boxInput2:boxInput2
		},
		success: function (data) {
			if(data.errorMsg){
				alert(data.errorMsg);
			}else{
				alert(data.success);
				$("#myModalNotice").hide();
				getSpotResult();
			}
		},error: function (data) {// 请求失败处理函数
			alert(data.errorMsg);
		}
	});
}

/**
 * 整改通知，下载报告点击事件
 */
function uploadReportWord(){
	var siteCode=$("#siteCode").val();
    var isDown = $("#isDown").val();
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_getUploadPath.action?scheduleId="+scheduleId+"&siteCode="+siteCode,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		$("#filePath").val(data["filePath"]);
				$("#fileName").val(data["fileName"]);
				if($("#downames").val() != ""){
					$("#dowForm").submit();
				}
				/*var filePath=data["filePath"];
				var fileName=data["fileName"];
				window.location.href=webPath+"/reportManageLog_getWordFile.action?filePath="+filePath+"&fileName="+fileName;*/
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

/**
 * 下载报告点击事件
 */
function uploadReport(obj){
	var siteCode=$(obj).parent().parent().find(".font_701999").find("a").html();
    var isDown = $(obj).parent().find("input").val();
	if(uploadReport != 0 && isDown != 0){
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        url: webPath + "/spotCheckResult_getUploadPath.action?scheduleId="+scheduleId+"&siteCode="+siteCode,
	        success: function (data) {
	        	if(data.errorMsg){
	        		alert(data.errorMsg);
	        	}else{
	        		$("#filePath").val(data["filePath"]);
					$("#fileName").val(data["fileName"]);
					if($("#downames").val() != ""){
						$("#dowForm").submit();
					}
					/*var filePath=data["filePath"];
					var fileName=data["fileName"];
					window.location.href=webPath+"/reportManageLog_getWordFile.action?filePath="+filePath+"&fileName="+fileName;*/
	        	}
			},error: function (data) {// 请求失败处理函数
	            alert(data.errorMsg);
	        }
	    });
    }
}

/**
 * 查看报告的点击事件
 */
function searchReport(obj){
	var siteCode=$(obj).parent().parent().find(".font_701999").find("a").html();
    if(status != 0){
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        url: webPath + "/spotCheckResult_getServicePeriod.action?scheduleId="+scheduleId,
	        success: function (data) {
	        	if(data.errorMsg){
	        		alert(data.errorMsg);
	        	}else{
	        		var servicePeriodId=data["servicePeriodId"];//服务周期id
					var startDate=data["startDate"];//开始日期
					var endDate=data["endDate"];//结束日期
					requestReport("siteCode="+siteCode+"&servicePeriodId="+servicePeriodId+"&startDate="+startDate+"&endDate="+endDate);
	        	}
			},error: function (data) {// 请求失败处理函数
	            alert(data.errorMsg);
	        }
	    });
	}
}

/**
 * 整改通知，查看报告的点击事件
 */
function searchReportWord(){
	var siteCode=$("#siteCode").val();
    if(status != 0){
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        url: webPath + "/spotCheckResult_getServicePeriod.action?scheduleId="+scheduleId,
	        success: function (data) {
	        	if(data.errorMsg){
	        		alert(data.errorMsg);
	        	}else{
	        		var servicePeriodId=data["servicePeriodId"];//服务周期id
					var startDate=data["startDate"];//开始日期
					var endDate=data["endDate"];//结束日期
					requestReport("siteCode="+siteCode+"&servicePeriodId="+servicePeriodId+"&startDate="+startDate+"&endDate="+endDate);
	        	}
			},error: function (data) {// 请求失败处理函数
	            alert(data.errorMsg);
	        }
	    });
	}
}

function downLoadWordFile(){
	var fid = $("#fid").val();
	if(fid){
		window.location.href = webPath + "/spotCheckNotice_getDownNoticeWord.action?fid="+fid;
	}
}
