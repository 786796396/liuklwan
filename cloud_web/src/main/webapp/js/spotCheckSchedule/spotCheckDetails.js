var ServicePeriodStatusFormat={"0":"未开始服务","1":"服务中","2":"已完成服务"};
var checkReportResultsFormat={"0":"未通知","1":"未反馈","2":"已反馈"};
//抽查状态（-1:未提交，0：未启动，1:检查中，2：扫描完成 ，3：报告完成）
var resultStateList={
		"-1":"未提交",
		"0":"未启动",
		"1":"检查中",
		"2":"扫描完成",
		"3":"报告完成"
};

//隐藏提示框

function showPrompt() {
    $('#promptDiv').show();
}
function hidePrompt() {
    $('#promptDiv').hide();
}



   //传入 event 对象
function ShowPrompt(objEvent) {
var divObj = document.getElementById("promptDiv");
divObj.style.visibility = "visible";
//使用这一行代码，提示层将出现在鼠标附近
//divObj.style.left = objEvent.clientX + 5;   //clientX 为鼠标在窗体中的 x 坐标值       
divObj.style.left = 60 + 5;
divObj.style.top = objEvent.clientY + 5;     //clientY 为鼠标在窗体中的 y 坐标值
}


var servicePeriodId=$("#servicePeriodIds").val();
var listSize;
$(function(){
	var loginSiteCode=$("#loginSiteCode").val();
	//填报单位隐藏 通知整改
	if(loginSiteCode.length>6){
		$("#checkNotice_div").hide();
//		$('#checkNotice_id').iCheck('disable');
	}
	
	
	//从那个页面跳转来的
	var toType=$("#toType").val();
	if(toType=="0"){
		$("#titleName").html('抽查汇报');
		$("#allowed_part").show();
	}else{
		$("#titleName").html('抽查汇报');
		$("#allowed_part").hide();
		
	}
	  $('.chart_se').hover(function(){
		  $(this).children('ul').show();
	  },function(){
	        $(this).children('ul').hide();
	  });
	  selectShow("siteType");
	  selectShow("resultType");
	  selectShow("reportType");
	  selectShow("noticeType");
	  selectShow("readType");
	  
	//选择天数事件
//	$("#siteTypeUl li").click(function(){
//		$("#siteTypeVal").val(this.value);
//		$("#siteTypeText").html($(this).html());
//		$("#siteTypeUl").hide();
//	});
	
	if($("#reportTypeVal").val()==1){
		$("#reportTypeVal").val(1);
		$("#reportTypeText").html('已完成');
		$("#checkReport_id").prop("checked",true);
	}else{
		$("#checkReport_id").prop("checked",false);
	}
	if($("#noticeTypeVal").val()==1){
		$("#noticeTypeVal").val(1);
		$("#noticeTypeText").html('已通知');
		$("#checkNotice_id").prop("checked",true);
	}else{
		$("#checkNotice_id").prop("checked",false);
	}	
	if($("#readTypeVal").val()==1){
		$("#readTypeVal").val(1);
		$("#readTypeText").html('已读');
		$("#checkRead_id").prop("checked",true);
	}else{
		$("#checkRead_id").prop("checked",false);
	}	
	
	//抽查		全面检测
		getSpotResult();

	
	

	
	
	
	
	   /** 整改报告start **/
    //通知整改新增
    $("#noticeAdd").bind("click",function(){
    	var boxInput1 = "";
    	var boxInput2 = "";
    	//上级组织单位
    	var boxInput1_up = "";
    	var boxInput2_up = "";
    	if($("#boxInput1").prop("checked")){
    		boxInput1 = $("#boxInput1").val();
//    		boxInput1="15801337476@163.com";
    	}
    	if($("#boxInput2").prop("checked")){
    		boxInput2 = $("#boxInput2").val();
//    		boxInput2="15801337476@163.com";
    	}
     	if($("#boxInput1_up").prop("checked")){
    		boxInput1_up = $("#boxInput1_up").val();
//    		boxInput1="15801337476@163.com";
    	}
    	if($("#boxInput2_up").prop("checked")){
    		boxInput2_up = $("#boxInput2_up").val();
//    		boxInput2="15801337476@163.com";
    	}
   
    	if($("#datepicker").val()==null || $("#datepicker").val()==""){
    		alert("请选择整改期限");
    		return false;
    	}
    	var noticeRequirement = $("#noticeRequirement").val();
    	if(noticeRequirement != ""){
    		if(noticeRequirement.length>250){
    			alert("内容过长，请重新输入整改要求");
    			return false;
    		}
    	}
    	noticeAdd(boxInput2,boxInput1,boxInput2_up,boxInput1_up);
    });
    
    /** 整改报告start **/
    //通知整改新增(批量)
    $("#noticeAddList").bind("click",function(){
    	
    	if($("#noticeList_date").val()==null || $("#noticeList_date").val()==""){
    		alert("请选择整改期限");
    		return false;
    	}
    
    	noticeAddList();
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
    /** 整改报告end **/
    
    
    $("#datepicker").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		onSelect: function(dateText, inst) {
//			initPage(dateText);
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
    
    $("#noticeList_date").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		onSelect: function(dateText, inst) {
//			initPage(dateText);
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
   
    //设置允许上级组织单位查看此通知并收到邮件整改通知 
    $('#boxInput1_allow').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	$('#boxInput1_allow').val(1);
    	$("#upperOrgId").css('display','block');
    	
    }); 
    
    $('#boxInput1_allow').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	$('#boxInput1_allow').val(0);
    	$("#upperOrgId").css('display','none'); 	
    }); 
    
    //（批量通知整改）设置允许上级组织单位查看此通知并收到邮件整改通知 
    $('#noticeList_isAllow').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	$('#noticeList_isAllow').val(1);
    }); 
    
    $('#noticeList_isAllow').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	$('#noticeList_isAllow').val(0);
    }); 
   
});

//一键汇报ajax请求传参批次
function aKeyToReport(){
	var batchNum = $("#batchNum").val();
	var scheduleCheckId = $("#scheduleCheckId").val();
	$.ajax({
		async:false,
//		cache:false,
		type:"POST",
		dataType:"json",
		url:webPath +"/spotCheckResult_getSpotCheckSchedulBatchNum.action?batchNum="+batchNum +"&scheduleCheckId="+scheduleCheckId,// 请求的action路径
		success:function(data){//成功回调函数
			console.info(data.huibao);
			alert(data.huibao);
		}
//		 error : function (data) {//错误回调函数
//				alert("系统错误，请联系管理员！");
//	      	}
		});

};



function selectShow(id){
	$("#"+id+"Ul li").click(function(){
		$("#"+id+"Val").val(this.value);
		$("#"+id+"Text").html($(this).html());
		$("#"+id+"Ul").hide();
		getSpotResult();
	});
}





//根据抽查批次，查询抽查结果表
function getSpotResult(){
	var loginSiteCode=$("#loginSiteCode").val();
	var servicePeriodId=$("#servicePeriodIds").val();//周期ID
	var servicePeriodStatus = $("#servicePeriodStatus").val();//周期状态
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	var scheduleId=$("#scheduleId").val();
	
	var checkSiteType=$("#siteTypeVal").val();
	var checkResult=$("#resultTypeVal").val();
	var checkReport=$("#reportTypeVal").val();
	var checkNotice=$("#noticeTypeVal").val();
	var checkRead=$("#readTypeVal").val();
	var urlStr="";
	//从那个页面跳转来的
	var toType=$("#toType").val();
	//抽查
	if(toType == 0){
		urlStr="/spotCheckSchedule_getSpotResult.action"+"?scheduleId="+scheduleId+"&servicePeriodId="+servicePeriodId;
	}else{
		//全面检测 ?servicePeriodId="+servicePeriodId
		urlStr="/servicePeriod_getwebsiteList.action"+"?servicePeriodId="+servicePeriodId;
	}
	$.ajax({
		async:false,
		cache:false,
		type:"POST",
		dataType:"json",
		url:webPath +urlStr ,// 请求的action路径
		data:{
			checkSiteType:checkSiteType,
			checkResult:checkResult,
			checkReport:checkReport,
			checkNotice:checkNotice,
			checkRead:checkRead
		},
		success:function(data){
			var listStr="";
			var listSumStr="";//汇总 html
			listSize=data.listSize;
			if(data.listSize==0){
				$("#service_period_site_tbody_id").html("");
				$("#table-Sum").html("");
				$("#table-Sum").hide();
				listStr+="<tr><td colspan='12'>暂无结果数据</td></tr>";
				$("#service_period_site_tbody_id").append(listStr);
			}else{
				
				var connErrorProportionSum=0;
				var allLinkSum=0;
				var allQuestoinSum=0;
				var securityBlankSum=0;
				var securityResponseSum=0;
				var allServiceSum=0;
				var allErrorSum=0;
				//清空表格数据
				$("#service_period_site_tbody_id").html("");
				$("#table-Sum").show();
				$("#table-Sum").html("");
				
				//汇总
				listSumStr+="<tr class='summary_tr' >" +
					   "<td colspan='2' class='summary_td' style='color: #4a5b6c;width:90px'>汇总 <i></i></td>" +
                       "<td style='width:64px;'><b id='connErrorProportionSum'>111</b></td>" +
                       "<td style='width:64px;'><b id='allLinkSum'>222</b></td>" +
                       "<td style='width:64px;'><b id='allQuestoinSum'>333</b></td>" +
                       "<td style='width:64px;'><b id='securityBlankSum'>444</b></td>" +
                       "<td style='width:64px;'><b id='securityResponseSum'>555</b></td>" +
                       "<td style='width:64px;'><b id='allServiceSum'>666</b></td>" +
                       "<td style='width:64px;'><b id='allErrorSum'>777</b></td>" ;
	                   	if(toType != 0){
	                   		listSumStr+="<td style='width:36px;'></td>" ;
	                   	}
						listSumStr+="<td style='width:36px;'></td><td style='width:45px;'></td>";
                       if(loginSiteCode.length==6){
                    	   listSumStr+= "<td style='width:55px;'></td>";
                       }
                       listSumStr+= "</tr>";
				var list;
				//  数据包
				if(toType == 0){
					//抽查
					list=data.returnList
				}else{
					//全面检测 
					list=data.detectionPeroidList;
				}
				var hideFlag=true;
				$.each(list, function(index, obj) {
					
					var siteCode=obj.siteCode;
					var isDown=obj.isDown;
					
					var detectionPeroidCount;
					//数据包里的 detectionPeroidCount对象
					if(toType == 0){
						//抽查 
						 detectionPeroidCount=obj.detectionPeroidCount;
					}else{
						//全面检测 
						detectionPeroidCount=obj;
						
					}
					var url=obj.url;
					if(!url.match("http")){
	        			url="http://"+url;
	        		}
					//不连通率
					var connErrorProportion=detectionPeroidCount.connErrorProportion!=""?detectionPeroidCount.connErrorProportion:0;
					//网站死链个数
					var allLink=detectionPeroidCount.linkHome+detectionPeroidCount.linkAll;
					//首页不更新问题
					var securityHome =detectionPeroidCount.securityHome==1?1:0;
					//栏目不更新问题
					var securityChannel=detectionPeroidCount.securityChannel;
					//网站不更新问题
					var allQuestoin=securityHome+securityChannel;
					//服务不实用
					var allService=detectionPeroidCount.serviceGuide+detectionPeroidCount.serviceDownload+detectionPeroidCount.serviceConn;  
					//严重错误
					var allError=detectionPeroidCount.seriousCorrect+detectionPeroidCount.seriousUnreal+detectionPeroidCount.seriousViolence+detectionPeroidCount.seriousOthers;
					if(obj.isHide==0){
						
						 connErrorProportionSum=connErrorProportion*1+connErrorProportionSum;
						 allLinkSum=allLink+allLinkSum;
						 allQuestoinSum=allQuestoin+allQuestoinSum;
						 securityBlankSum=detectionPeroidCount.securityBlank+securityBlankSum;
						 securityResponseSum=detectionPeroidCount.securityResponse+securityResponseSum;
						 allServiceSum=allService+allServiceSum;
						 allErrorSum=allError+allErrorSum;	
					listStr+="<tr>" ;
//					"<td><input type='checkbox' /></td>" ;
//					wz-name td_left font_701999
					 listStr+="<td class='wz-name' style='width:90px;'><a target='_blank' class='keykey colo_1ca8dd_tn' href='"+webPath+"/fillUnit_gailan.action?siteCode="+obj.siteCode+"'>"+obj.siteCode+"</a>" +
					"<br/><a target='_blank' style=\"display:inline-block;margin-top:5px;\" class='key colo_1ca8dd_tn' href='"+url+"'>"+obj.siteName+"</a></td>";
					
					 
					listStr+="<td style='width:64px;' ><a class='colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','home_ability')\" ><span class='sort-num'>"+connErrorProportion+"</span>%</a></td>"+
                     "<td style='width:64px;'  ><a class='sort-num colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','link_ability')\" >"+allLink+"</a></td>"+
                     "<td style='width:64px;'  ><a class='sort-num colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','info_update_one')\" >"+allQuestoin+"</a></td>"+
                     "<td style='width:64px;'  ><a class='sort-num colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','info_update_two')\" >"+detectionPeroidCount.securityBlank+"</a></td>"+
                     "<td style='width:64px;'  ><a class='sort-num colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','security_response_info')\" >"+detectionPeroidCount.securityResponse+"</a></td>"+
                     "<td style='width:64px;'  ><a class='sort-num colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','security_servcie_info')\" >"+allService+"</a></td>"+
                     "<td style='width:64px;'  ><a class='sort-num colo_1ca8dd_tn' href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','correct_content_info')\" >"+allError+"</a></td>";
					if(toType!=0){
						listStr+="<td style='width:36px;'><span>"+detectionPeroidCount.siteCheckResultName+"</span></td>" ;
                   	}
					//控制  查看报告、下载报告是否 可用
					if(servicePeriodStatus!=0){
						if(obj.isDown==1){
							//报告状态列 已完成
							listStr+="<td class='td_left' style='width:36px;' class='sort-num'><span>已完成</span></td>";
							//查看报告和下载报告  可用
							listStr+="<td class='td_left clearfix' style='width:45px;'>" +
							"<span class='span_t'><a class='colo_1ca8dd_tn'  title='查看报告' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\">预览</a></span>" +
							"<span class='span_t'><a class='colo_1ca8dd_tn'  onclick=\"uploadReport('"+siteCode+"','"+servicePeriodId+"','"+servicePeriodStatus+"','"+isDown+"')\" title='下载报告' ><input type='hidden' value='"+obj.isDown+"'>下载</a></span></td>";
						}else{
							//报告状态列 未完成
							listStr+="<td class='td_left' style='width:36px;' class='sort-num'><span>未完成</span></td>";
							//查看报告和下载报告 		 不可用
							listStr+="<td class='td_left clearfix' style='width:45px;'>" +
							"<span class='span_t'><a class='colo_1ca8dd_tn' title='查看报告' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\">预览</a></span>" +
							"<span class='span_t'><a   title='下载报告' ><input type='hidden' value='"+obj.isDown+"'>下载</a></span></td>";
						}
					}else{
						//报告状态列 未完成    
						listStr+="<td class='td_left' style='width:36px;' class='sort-num'><span>未完成</span></td>";
						
//						 不可用  onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\"
//						class='colo_1ca8dd_tn'
						listStr+="<td class='td_left clearfix' style='width:36px;'>" +
						"<span class='span_t'><a  title='查看报告' >预览</a></span>" +
						"<span class='span_t'><a   title='下载报告' ><input type='hidden' value='"+obj.isDown+"'>下载</a></span></td>";//qmjc
					}
					
					//填报单位隐藏 通知整改
				if(loginSiteCode.length==6){
					//控制 通知整改、查看反馈是否可用
					//绿色按钮  未通知 
					if(servicePeriodStatus!=0 && obj.isDown==1 && obj.checkReportResult==0){
						listStr+="<td class='clearfix' style='width: 55px;'>" +
					    "<span class='span_t'>未通知</span></td></tr>";
					}else if(servicePeriodStatus!=0 && obj.isDown==1 && obj.checkReportResult==1){
						//已通知   未反馈 灰色
						if(detectionPeroidCount.isRead==0){
							//未读
							listStr+="<td class='clearfix' style='width: 55px;'><span class='span_t'>已通知</span>" +
							"<span class='span_t' title='填报单位未完成整改'>未读未反馈</span></td></tr>";
						}else{
							//已读
							listStr+="<td class='clearfix' style='width: 55px;'><span class='span_t'>已通知</span>" +
							"<span class='span_t' title='填报单位未完成整改'>已读未反馈</span></td></tr>";
						}
						
					}else if(servicePeriodStatus!=0 && obj.isDown==1 && obj.checkReportResult==2){
						//已通知  已反馈    查看反馈绿色
						listStr+="<td class='clearfix' style='width: 55px;'><span class='span_t'>已通知</span>" +
						"<span class='gzzg-blue span_t' onclick=\"getNotice('"+siteCode+"')\" data-toggle='modal'" +
						" data-target='#myModalNoticeSee' title='填报单位已完成整改'>查看反馈<input type='hidden' value='"+obj.isDown+"'></span></td></tr>";
					}else{
						//未通知   无 不可以操作的灰色按钮  通知整改 灰色
						listStr+="<td class='clearfix' style='width: 55px;'><span class='span_t'>未通知</span>" +
						"</td></tr>";
					}
                    }
				
					listStr +="</tr>";
					hideFlag=false;
				}
				});
				if(hideFlag){
					var hideStr="";
					$("#service_period_site_tbody_id").html("");
					$("#table-Sum").html("");
					$("#table-Sum").hide();
					hideStr+="<tr><td colspan='12'>暂无结果数据</td></tr>";
					$("#service_period_site_tbody_id").append(hideStr);
				}else{
					$("#service_period_site_tbody_id").append(listStr);
					$("#table-Sum").append(listSumStr);
					$("#connErrorProportionSum").html(getAvgProportion(connErrorProportionSum,data.listSize)+"%");
					$("#allLinkSum").html(allLinkSum);
					$("#allQuestoinSum").html(allQuestoinSum);
					$("#securityBlankSum").html(securityBlankSum);
					$("#securityResponseSum").html(securityResponseSum);
					$("#allServiceSum").html(allServiceSum);
					$("#allErrorSum").html(allErrorSum);
				}
				
				
				
			}
			
			//列表排序
			new TableSorter("table-SSHX",2,3,4,5,6,7,8);
			$("#table-SSHXSort th").on('click', function(event){
				
				if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
					
					$("#table-SSHX .tab_angle").attr("class","tab_angle");
					$(this).find(".tab_angle").addClass("tab_angle_top");
					$(this).find(".tab_angle").removeClass("tab_angle_bottom");
				}else{
					$("#table-SSHX .tab_angle").attr("class","tab_angle");
					$(this).find(".tab_angle").addClass("tab_angle_bottom");
					$(this).find(".tab_angle").removeClass("tab_angle_top");
				}
			  });
			//列表检索
			$("#searchInfo_id").keyup(function(){
			    var searchInfo=$("#searchInfo_id").val();
				 if(searchInfo==""){
					 if(listSize!=0){
						 $("#table-Sum").show();
					 }
					 $("table tr").show();
					 var num=0;
					 $("table").find(".wz-name").each(function(index, element) {
							 num+=1;
							 $(this).parents("tr").find(".font_701999").html(num);
					});
				 }else{
					 if(listSize!=0){
						 $("#table-Sum").hide();
					 }
					 
					 var num=0;
					 $("table").find(".wz-name").each(function(index, element) {
						 if($(this).html().indexOf(searchInfo)<0){
							$(this).parents("tr").hide();
						 }else{
							 num+=1;
							 $(this).parents("tr").show();
							 $(this).parents("tr").find(".font_701999").html(num);
						 }
					});
				 }
			});
			//复选框添加样式
			$("input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue'
			});	
			$("[name='checkNotice']").on('ifChanged', function(event){

		        var checkType = $(this).prop("checked");
		       
				if($(this).val()==1){
					//报告状态 已完成  勾选
					 if(checkType){
						 $("#reportTypeVal").val(1);
				     }else{
				    	 $("#reportTypeVal").val(0);
				     }
					
				}else if($(this).val()==3){
					//通知整改 勾选
					 if(checkType){
						 $("#noticeTypeVal").val(1);
				     }else{
				    	 $("#noticeTypeVal").val(0);
				     }
				}else if($(this).val()==4){
					//通知整改 勾选
					 if(checkType){
						 $("#readTypeVal").val(1);
				     }else{
				    	 $("#readTypeVal").val(0);
				     }
				}
				//抽查		全面检测
				getSpotResult();
				});
			
			//全选 复选框  
//			$("[name='chk_all']").on('ifChanged', function(event){
//			    var checkType = $(this).prop("checked");
//			   
//			    if ($("input[name='chk_all']").prop("checked")) {
//					$("input[name='chk_list']").prop("checked", true);
//				} else {
//					$("input[name='chk_list']").prop("checked", false);
//				}
//			  //复选框添加样式
//			    $("input[name='chk_list']").iCheck({
//			    	checkboxClass : 'icheckbox_square-blue',
//			    	radioClass : 'iradio_square-blue'
//			    });	
//		});
		},error:function(data){
//			alert(data.errorMsg);
		}
	});
}




/**
 * 查看报告的点击事件
 */
function searchReport(siteCode,servicePeriodId,startDate,endDate,tabId){
	requestReport("tabId="+tabId+"&siteCode="+siteCode+"&servicePeriodId="+servicePeriodId+"&startDate="+startDate+"&endDate="+endDate);
}

/**
 * 下载报告点击事件  table列表
 */
function uploadReport(siteCode,servicePeriodId,servicePeriodStatus,isDown){
//	var siteCode = $(obj).parent().parent().find(".font_701999").find("a").html();
//	var isDown = $(obj).parent().find("input").val();
	if(servicePeriodStatus!=0 && isDown != 0){
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        url: webPath + "/spotCheckResult_getUploadPathByServiceRepriod.action?servicePeriodId="+servicePeriodId+"&siteCode="+siteCode,
	        success: function (data) {
	        	if(data.errorMsg){
	        		alert(data.errorMsg);
	        	}else{
	        		
					$("#filePath").val(data["filePath"]);
					$("#fileName").val(data["fileName"]);
					if($("#filePath").val() != ""){
						$("#dowForm").submit();
					}
	        	}
			},error: function (data) {// 请求失败处理函数
	            alert(data.errorMsg);
	        }
	    });
    }
}

/** 整改报告start **/

//整改通知,发送通知页
function spotPass(siteCode,isDown){
//	var siteCode=$(obj).parent().parent().find(".font_701999").find("a").html();
//	var isDown = $(obj).parent().find("input").val();
//	
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
				$("#boxInput1").val(data.databaseInfo['email2']);
				$("#boxInput2").val(data.databaseInfo['email']);
				$("#datepicker").val("");
				$("#noticeRequirement").val("");
				if($("#checkbox1").attr("class").indexOf("on_check")==-1){
					$("#checkbox1").addClass("on_check");
				}
				if($("#checkbox2").attr("class").indexOf("on_check")==-1){
					$("#checkbox2").addClass("on_check");
				}
				
				//add by Na.Y 2016.11.03 添加 抽查组织单位!=上级组织单位，添加是否通知上级组织单位
				$("#upperOrgCode").val(data.upperOrgInfo['siteCode']);
				if(data.upperOrgInfo!=null&&data.upperOrgInfo['siteCode']!=data.loginOrgCode){
					$("#upperOrgAllow").css('display','block');
					$("#uppperOrgName").html(data.upperOrgInfo['name']);
					//联系人
					$("#linkmanName_up").html(data.upperOrgInfo['linkmanName']);
					$("#email2_up").html(data.upperOrgInfo['linkmanEmail']);
					$("#boxInput1_up").val(data.upperOrgInfo['linkmanEmail']);
					
					//负责人
					$("#principalName_up").html(data.upperOrgInfo['principalName']);
					$("#email_up").html(data.upperOrgInfo['principalEmail']);
					$("#boxInput2_up").val(data.upperOrgInfo['principalEmail']);
					
				}
				
				
				
      	}
		},error: function (data) {// 请求失败处理函数
          alert(data.errorMsg);
      }
  });
}


//整改通知反馈查看
function getNotice(siteCode){
//	var siteCode=$(obj).parent().parent().find(".font_701999").find("a").html();
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckNotice_getNotice.action?siteCode="+siteCode+"&servicePeriodId="+servicePeriodId,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
				$("#siteNames").html(data.databaseInfo['name']);
				$("#requireTimes").html(data.requireTime);
				$("#directors").html(data.spotCheckNotice['director']);
				var noticeRequirement= null==data.spotCheckNotice['noticeRequirement']?"":data.spotCheckNotice['noticeRequirement'];
				$("#noticeRequirements").html(noticeRequirement);
				$("#noticeIds").val(data.spotCheckNotice['id']);
				
				var noticeResponse= null == data.spotCheckNotice['noticeResponse']?"":data.spotCheckNotice['noticeResponse'];
				$("#noticeResponses").html(noticeResponse);
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
function noticeAdd(boxInput1,boxInput2,boxInput1_up,boxInput2_up){
	var scheduleId=$("#scheduleId").val();
	//从那个页面跳转来的
	var toType=$("#toType").val();
	var urlStr="";
	if(toType==0){
		//抽查
		urlStr="/spotCheckNotice_noticeAdd.action";
	}else{
		//全面检测
		urlStr="/spotCheckNotice_noticeAddAll.action";
	}
	
	
	$.ajax({
		async: false,
		cache: false,
		type: 'POST',
		dataType: "json",
		url: webPath + urlStr,
		data : {
			siteCode:$("#siteCode").val(),
			servicePeriodId:servicePeriodId,
			scheduleId:scheduleId,
			director:$("#director").html(),
			datepicker:$("#datepicker").val(),
			noticeRequirement:$("#noticeRequirement").val(),
			boxInput1:boxInput1,
			boxInput2:boxInput2,
			boxInput1_up:boxInput1_up,
			boxInput2_up:boxInput2_up,
			upperOrgCode:$("#upperOrgCode").val(),
			isAllowUpper:$("#boxInput1_allow").val(),
			type:toType
		},
		success: function (data) {
			if(data.errorMsg){
				alert(data.errorMsg);
			}else{
				alert(data.success);
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
      url: webPath + "/spotCheckResult_getUploadPathByServiceRepriod.action?servicePeriodId="+servicePeriodId+"&siteCode="+siteCode,
      success: function (data) {
      	if(data.errorMsg){
      		alert(data.errorMsg);
      	}else{
      		$("#filePath").val(data["filePath"]);
				$("#fileName").val(data["fileName"]);
				if($("#filePath").val() != ""){
					$("#dowForm").submit();
				}
			/*	var filePath=data["filePath"];
				var fileName=data["fileName"];
				window.location.href=webPath+"/reportManageLog_getWordFile.action?filePath="+filePath+"&fileName="+fileName;*/
      	}
		},error: function (data) {// 请求失败处理函数
          alert(data.errorMsg);
      }
  });
}

/**
* 整改通知，查看报告的点击事件
*/
function searchReportWord(){
	var siteCode=$("#siteCode").val();
	var servicePeriodStatus = $("#servicePeriodStatus").val();
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
  if(servicePeriodStatus != 0){
	 requestReport(siteCode,servicePeriodId,startDate,endDate)
	}
}
//整改通知反馈附件
function downLoadWordFile(){
	var fid = $("#fid").val();
	if(fid){
		window.location.href = webPath + "/spotCheckNotice_getDownNoticeWord.action?fid="+fid;
	}
}

/** 整改报告end **/


function excelWebsiteList(){
    var servicePeriodId=$("#servicePeriodIds").val();//周期ID
    //alert("servicePeriodId=="+servicePeriodId);
    //从那个页面跳转来的
	var toType=$("#toType").val();
    window.location.href=webPath+"/servicePeriod_excelWebsiteList.action?servicePeriodId="+servicePeriodId+"&type="+toType;
}
//汇总报告下载
function excelOrg(){
	var siteCode=$("#loginSiteCode").val();
	 var servicePeriodId=$("#servicePeriodIds").val();//周期ID
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/servicePeriod_excelOrg.action?servicePeriodId="+servicePeriodId+"&siteCode="+siteCode,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		
				$("#filePath").val(data["filePath"]);
				$("#fileName").val(data["fileName"]);
				if($("#filePath").val() != ""){
					$("#dowForm").submit();
				}
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
//	window.location.href=webPath+"/servicePeriod_excelWebsiteList.action?servicePeriodId="+servicePeriodId+"&siteCode="+siteCode;
}

//全选/反选
function checkAll(){
	if ($("input[name='chk_all']").prop("checked")) {
		$("input[name='chk_list']").prop("checked", true);
	} else {
		$("input[name='chk_list']").prop("checked", false);
	}
//复选框添加样式
$("input[type='checkbox']").iCheck({
	checkboxClass : 'icheckbox_square-blue',
	radioClass : 'iradio_square-blue'
});	
}

//打开批量通知整改窗口
function openNoticeList(){
	
	//全选		
	var arrChk = $("input[name='chk_list']:checked");
	var id = "";
	$(arrChk).each(function(index,element) {
		//遍历得到每个checkbox的value值
//			id.push($(this).val());
	if($(arrChk).length-1==index){
		id+=$(this).val();
	}else{
		id+=$(this).val()+",";
	}
	
	});
	
	if(id==""){
		alert("请选择一个站点。");
		$('#noticeListId').attr("data-target","#");
		return;
	}
	$('#noticeListId').attr("data-target","#t_prompt");
}

//通知整改批量
function noticeAddList(){
	var scheduleId=$("#scheduleId").val();
	var noticeDate=$("#noticeList_date").val();
	var isAllowUpper=$("#noticeList_isAllow").val();
	//从那个页面跳转来的
	var toType=$("#toType").val();
	var urlStr="";
	if(toType==0){
		//抽查
		urlStr="/spotCheckNotice_noticeAddSome.action";
	}else{
		//全面检测
		urlStr="/spotCheckNotice_noticeAddAllSome.action";
	}
	//全选		
	var arrChk = $("input[name='chk_list']:checked");
	var id = "";
	$(arrChk).each(function(index,element) {
		//遍历得到每个checkbox的value值
//			id.push($(this).val());
		if($(arrChk).length-1==index){
			id+=$(this).val();
		}else{
			id+=$(this).val()+",";
		}
		
		});
	
		if(id==""){
			alert("请选择一个站点。");
			return;
		}
	
	$.ajax( {
		type : "POST",//用POST方式传输
		cache : false,
		async : false,
		dataType : "JSON",//数据格式:JSON
		url:webPath + urlStr,// 请求的action路径
		data : {
			siteCodeStr : id,
			scheduleId : scheduleId,
			servicePeriodId:servicePeriodId,
			noticeDate:noticeDate,
			isAllowUpper:isAllowUpper,
			type:toType
		},
		success : function(data) {
			alert("整改通知下发成功");
			
		}
	});
	getSpotResult();
	
}

/**
 * 获取不连通比例（没有%号），保留2位小数点（平均值）
 * 
 * @param num
 *            数量
 * @param totalNum
 *            总数
 * @return
 */
function getAvgProportion( sumPoportion,  totalNum) {

	if (sumPoportion == 0 || totalNum == 0) {
		return "0";
	}

	var proportion = sumPoportion / totalNum;

	if (proportion <= 0) {
		return "0";
	}

	if (proportion >= 100) {
		return "100";
	}
	
	return proportion.toFixed(2);
}



//全面检测
function getRelationPeriod(){
	var servicePeriodId=$("#servicePeriodIds").val();//周期ID
	var servicePeriodStatus = $("#servicePeriodStatus").val();//周期状态
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	var checkReport=$("#reportTypeVal").val();
	var checkNotice=$("#noticeTypeVal").val();
	//获取周期站点数据
	$.ajax({
		async:false,
		cache:false,
		type:"POST",
		dataType:"json",
		url:webPath + "/servicePeriod_getwebsiteList.action?servicePeriodId="+servicePeriodId,// 请求的action路径
		data:{
			checkReport:checkReport,
			checkNotice:checkNotice
		},
		success:function(data){
//			if(data.errorMsg){
//				alert(data.errorMsg);
//			}else{
				var returnList = data["detectionPeroidList"];
				var listStr="";
				if(returnList.length>0){
					$("#service_period_site_tbody_id").html("");
					var connErrorProportionSum=0;
					var allLinkSum=0;
					var allQuestoinSum=0;
					var securityBlankSum=0;
					var securityResponseSum=0;
					var allServiceSum=0;
					var allErrorSum=0;
					for(var i=0;i<returnList.length;i++){
						if(returnList[i]["isHide"]==0){
						var siteCode=returnList[i]["siteCode"];
						var isDown=returnList[i]["isDown"];
						
						var url=returnList[i]["url"];
						if(!url.match("http")){
		        			url="http://"+url;
		        		}
						//占比
						var connErrorProportion=returnList[i]["connErrorProportion"]!=""?returnList[i]["connErrorProportion"]:0;
						//网站死链个数
						var allLink=returnList[i]["linkHome"]+returnList[i]["linkAll"];
						//首页不更新问题
						var securityHome =returnList[i]["securityHome"]==1?1:0;
						//栏目不更新问题
						var securityChannel=returnList[i]["securityChannel"];
						//网站不更新问题
						var allQuestoin=securityHome+securityChannel;
						//服务不实用
						var allService=returnList[i]["serviceGuide"]+returnList[i]["serviceDownload"]+returnList[i]["serviceConn"];  
						//严重错误
						var allError=returnList[i]["seriousCorrect"]+returnList[i]["seriousUnreal"]+returnList[i]["seriousViolence"]+returnList[i]["seriousOthers"];
						
						//汇总
						listStr+="<tr class='summary_tr'>" +
							   "<td colspan='2' class='summary_td' style='color: #4a5b6c;'>汇总 <i></i></td>" +
                               "<td><b id='connErrorProportionSum'>0%</b></td>" +
                               "<td><b id='allLinkSum'>0</b></td>" +
                               "<td><b id='allQuestoinSum'>0</b></td>" +
                               "<td><b id='securityBlankSum'>0</b></td>" +
                               "<td><b id='securityResponseSum'>0</b></td>" +
                               "<td><b id='allServiceSum'>0</b></td>" +
                               "<td><b id='allErrorSum'>0</b></td>" +
                               "<td></td>" +
                               "<td></td>" +
                               "<td></td>" +
                               "</tr>";
						 connErrorProportionSum=connErrorProportion*1+connErrorProportionSum;
						 allLinkSum=allLink+allLinkSum;
						 allQuestoinSum=allQuestoin+allQuestoinSum;
						 securityBlankSum=returnList[i]["securityBlank"]+securityBlankSum;
						 securityResponseSum=returnList[i]["securityResponse"]+securityResponseSum;
						 allServiceSum=allService+allServiceSum;
						 allErrorSum=allError+allErrorSum;
						listStr+="<tr>" +
								"<td><input type='checkbox'/></td>" +
								"<td class='td_left font_701999'><a target='_blank' class='keykey' href='"+webPath+"/fillUnit_gailan.action?siteCode="+returnList[i]["siteCode"]+"'>"+returnList[i]["siteCode"]+"</a>" +
								"<br/><a target='_blank' style=\"display:inline-block;margin-top:5px;\" class='key' href='"+url+"/'>"+returnList[i]["siteName"]+"</a></td>";
//						if(returnList[i]["siteCode"]==1){//门户网站
//							listStr+="<td class='td_left'><div class='info-box wz-name'><i class='star'></i><a target='_blank' style=\"display:inline-block;margin-top:5px;\" class='key' href='"+returnList[i]["url"]+"/'>"+returnList[i]["name"]+"</a>"
//							+"<div class='chouc-hover-div'></div><div class='info-con'>"
//                            +"<div><label>单位名称：</label>"+returnList[i]["director"]+"</div>"
//                            +"<div><label>办公地址：</label>"+(returnList[i]["address"]||"无")+"</div>"
//                            +"<h3>负责人信息</h3>"
//                            +"<div><label>姓名：</label>"+returnList[i]["principalName"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["telephone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["mobile"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["email"]+"</div>"
//                            +"<h3>联系人信息</h3>"
//                            +"<div><label>姓名：</label>"+returnList[i]["linkmanName"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["telephone2"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["mobile2"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["email2"]+"</div>"
//                            +"</div>"
//                            +"</div></td>";
//						}else{//非门户
//							listStr+="<td class='td_left'><div class='info-box wz-name'><a target='_blank' style=\"display:inline-block;margin-top:5px;\" class='key' href='"+returnList[i]["url"]+"'>"+returnList[i]["name"]+"</a>"
//							+"<div class='chouc-hover-div'></div><div class='info-con'>"
//                            +"<div><label>单位名称：</label>"+returnList[i]["director"]+"</div>"
//                            +"<div><label>办公地址：</label>"+(returnList[i]["address"]||"无")+"</div>"
//                            +"<h3>负责人信息</h3>"
//                            +"<div><label>姓名：</label>"+returnList[i]["principalName"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["telephone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["mobile"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["email"]+"</div>"
//                            +"<h3>联系人信息</h3>"
//                            +"<div><label>姓名：</label>"+returnList[i]["linkmanName"]+"&nbsp;&nbsp;<label>手机：</label>"+returnList[i]["telephone2"]+"&nbsp;&nbsp;<label>办公电话：</label>"+returnList[i]["mobile2"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+returnList[i]["email2"]+"</div>"
//                            +"</div>"
//							+"</div></td>";
//						}
//						"<td class='td_left' style='width:60px;'><span title='"+returnList[i]["province"]+"'>"+returnList[i]["province"]+"</span></td>"+
//						"<td class='td_left' style='width:60px;'><span  title='"+returnList[i]["city"]+"'>"+returnList[i]["city"]+"</span></td>"+
//						"<td class='td_left' style='width:60px;'><span  title='"+returnList[i]["county"]+"'>"+returnList[i]["county"]+"</span></td>"+
						
						
						
						listStr+="<td><a href='#' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"','link_ability')\" class='colo_1ca8dd_tn'>"+connErrorProportion+"%</a></td>"+
                         "<td><a href='#' class='colo_1ca8dd_tn'>"+allLink+"</a></td>"+
                         "<td><a href='#' class='colo_1ca8dd_tn'>"+allQuestoin+"</a></td>"+
                         "<td><a href='#' class='colo_1ca8dd_tn'>"+returnList[i]["securityBlank"]+"</a></td>"+
                         "<td><a href='#' class='colo_1ca8dd_tn'>"+returnList[i]["securityResponse"]+"</a></td>"+
                         "<td><a href='#' class='colo_1ca8dd_tn'>"+allService+"</a></td>"+
                         "<td><a href='#' class='colo_1ca8dd_tn'>"+allError+"</a></td>";
						
						//控制  查看报告、下载报告是否 可用
						if(servicePeriodStatus!=0){
							if(returnList[i]["isDown"]==1){
								//报告状态列 已完成
								listStr+="<td class='td_left' style='width:60px;'><span>已完成</span></td>";
								//查看报告和下载报告
								listStr+="<td class='td_left clearfix'><span class='yulan-active fl' title='查看报告' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\"></span>" +
								"<span class='download-active fr' onclick=\"uploadReport('"+siteCode+"','"+servicePeriodId+"','"+servicePeriodStatus+"','"+returnList[i]["isDown"]+"')\" title='下载报告' ><input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td>";
							}else{
								//报告状态列 未完成
								listStr+="<td class='td_left' style='width:60px;'><span>未完成</span></td>";
								//查看报告和下载报告
								listStr+="<td class='td_left clearfix'><span class='yulan-active fl' title='查看报告' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\"></span>" +
								"<span class='download-qmjc fr' onclick=\"uploadReport('"+siteCode+"','"+servicePeriodId+"','"+servicePeriodStatus+"','"+returnList[i]["isDown"]+"')\" title='下载报告' ><input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td>";
							}
						}else{
							//报告状态列 未完成
							listStr+="<td class='td_left' style='width:60px;'><span>未完成</span></td>";
							listStr+="<td class='td_left clearfix'><span class='yulan-active fl' title='查看报告' onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\"></span>" +
							"<span class='download-qmjc fr' onclick=\"uploadReport('"+siteCode+"','"+servicePeriodId+"','"+servicePeriodStatus+"','"+returnList[i]["isDown"]+"')\" title='下载报告' ><input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td>";//qmjc
						}
						//控制 通知整改、查看反馈是否可用
						//绿色按钮  未通知 
//						if(servicePeriodStatus!=0 && returnList[i]["isDown"]==1 && returnList[i]["checkReportResult"]==0){
//							listStr+="<td class='clearfix' style='width: 110px;'>" +
//						    "<span>未通知</span><br/><span class='tzzg-green' onclick=\"spotPass('"+siteCode+"','"+isDown+"')\" data-toggle='modal' data-target='#myModalNotice'>通知整改<input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td></tr>";
//						}else if(servicePeriodStatus!=0 && returnList[i]["isDown"]==1 && returnList[i]["checkReportResult"]==1){
//							//已通知   未反馈
//							listStr+="<td class='clearfix' style='width: 110px;'><span>已通知</span><br/><span class='gzzg-gray' title='填报单位未完成整改'>查看反馈</span></td></tr>";
//						}else if(servicePeriodStatus!=0 && returnList[i]["isDown"]==1 && returnList[i]["checkReportResult"]==2){
//							//已通知  已反馈
//							listStr+="<td class='clearfix' style='width: 110px;'><span>已通知</span><br/><span class='ckfk-yellow' onclick=\"getNotice('"+siteCode+"')\"  data-toggle='modal' data-target='#myModalNoticeSee' title='填报单位已完成整改'>查看反馈<input type='hidden' value='"+returnList[i]["isDown"]+"'></span></td></tr>";
//						}else{
//							//未通知   无 不可以操作的灰色按钮
//							listStr+="<td class='clearfix' style='width: 110px;'><span>未通知</span><br/><span class='gzzg-gray'>通知整改</span></td></tr>";
//						}
						listStr +="</tr>";
						if(returnList.length-1==i){
							$("#service_period_site_tbody_id").append(listStr);
						}
						
					}
						
					}
					$("#connErrorProportionSum").html(connErrorProportionSum);
					$("#allLinkSum").html(allLinkSum);
					$("#allQuestoinSum").html(allQuestoinSum);
					$("#securityBlankSum").html(securityBlankSum);
					$("#securityResponseSum").html(securityResponseSum);
					$("#allServiceSum").html(allServiceSum);
					$("#allErrorSum").html(allErrorSum);
				}
//			}
		},error:function(data){
			alert(data.errorMsg);
		}
	});
}
