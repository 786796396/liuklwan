var map = {};
var infoTitle=[];
infoTitle.push({"mDataProp": "num", "bSortable": false,"sTitle": "序号", "sClass": "center th-center", "bVisible": true,"tabIndex": -10,"sWidth": "8%"});
infoTitle.push({"mDataProp": "siteCode", "bSortable": false,"sTitle": "网站标识码", "sClass": "center th-center", "bVisible": true,"tabIndex": -9,"sWidth": "9%"});
infoTitle.push({"mDataProp": "webName", "bSortable": false,"sTitle": "网站名称", "sClass": "center  th-center", "bVisible": true,"tabIndex": -8,"sWidth": "9%"});
infoTitle.push({"mDataProp": "reportName", "bSortable": false,"sTitle": "申请人", "sClass": "center  th-center", "bVisible": true,"tabIndex": -7,"sWidth": "9%"});
infoTitle.push({"mDataProp": "reportReason", "bSortable": false,"sTitle": "报备原因", "sClass": "center  th-center", "bVisible": true,"tabIndex": -6});
infoTitle.push({"mDataProp": "reportDetail", "bSortable": false,"sTitle": "详细说明", "sClass": "center  th-center", "bVisible": true,"tabIndex": -5});
infoTitle.push({"mDataProp": "reportDate", "bSortable": false,"sTitle": "报备日期", "sClass": "center  th-center", "bVisible": true,"tabIndex": -4});
infoTitle.push({"mDataProp": "reportStartDate", "bSortable": false,"sTitle": "影响日期", "sClass": "center  th-center", "bVisible": true,"tabIndex": -3,"sWidth": "16%"});
infoTitle.push({"mDataProp": "id", "bSortable": false,"sTitle": "状态", "sClass": "center  th-center", "bVisible": true,"tabIndex": -2});
infoTitle.push({"mDataProp": "perNo", "bSortable": false,"sTitle": "操作", "sClass": "center  th-center", "bVisible": true,"tabIndex": -1});
var temporary_result_table_id;
$(function(){
	getDic();
	getData();
	initDate();
	nameHover("tobodyy");
	$("#siteCodeOrName").keyup(function(){
		 var siteCodeOrName=$('#siteCodeOrName').val();
			var reportReason="";
			if(siteCodeOrName != ""){
				for(var key in map){
					var s = map[key].indexOf(siteCodeOrName);
					if(s!= -1){
						reportReason+=key+"," 
					}
				}
				$("#reportReason").val(reportReason);
				var str = $("#reportReason").val();
			}
			temporary_result_table_id.fnClearTable();   
			temporary_result_table_id.fnReloadAjax(webPath
					+ "/tempReport_getDataList.action");
	   });
}) 

function getData(){

	getPage();
}
function getPage(){
	temporary_result_table_id= $("#temporary_result_table_id").dataTable({
		 "sDom": '<"top"T<"clear">>frt<"toolbar">lip',  
	        "bDestroy": true,
	        "bAutoWidth": false,
	        "bDeferRender": true,
	        "bJQueryUI": true,
	        "searching":false,
	        "sPaginationType": "full_numbers",
	        "iDisplayLength": 15,
	        "aaSorting": [],
	        "oTableTools": {
	            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
	            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
	        },
	        "oLanguage": {
	            "sSearch": "查询:",
	            "sLengthMenu": '本页显示 <select>' +
	                '<option value="50">50</option>' +
	                '<option value="100">100</option>'+
	                '<option value="200">200</option>' +
	                '<option value="500">500</option>'+
	                '<option value="1000">1000</option>'+
	                '</select> ',
	            "oPaginate": {
	                "sFirst": "首页",
	                "sLast": "尾页",
	                "sNext": "下一页",
	                "sPrevious": "上一页"
	            },
	            "sInfo": "共 _TOTAL_ 条记录 (当前 _START_ 至 _END_)",
	            "sLoadingRecords": "数据正在加载...",
	            "sInfoFiltered": "",
	            "sInfoEmpty": "共 0 条记录",
	            "sZeroRecords": "组织下不存在站点数据。"
	        },
	        "bInfo": true,
	        "bPaginate": true,
	        "bRetrieve": true,
	        "bServerSide": true,
	        "bLengthChange": false,
	        "sAjaxSource": webPath+ "/tempReport_getDataList.action",
	        "sAjaxDataProp": function (data) {
	        	var list = data.list;
	        	$("#countSum").html(data.iTotalDisplayRecords);
	        	var i= '';
	        	for ( var j = 0; j < list.length; j++) {
	        		var baseData = list[j];
	        		i = j+1;
	        		list[j]["num"]=  i;
	        		list[j]["siteCode"]=  baseData.siteCode;
	        		list[j]["webName"] = baseData.webName;
	        		list[j]["reportName"] =  baseData.reportName;
     			if(baseData.reportReason == '6'){
     				list[j]["reportReason"]=map[baseData.reportReason].split('（')[0];
     			}else{
     				list[j]["reportReason"] = map[baseData.reportReason];
     			}
	        			if(baseData.reportDetail.length>10){
	        				list[j]["reportDetail"]="<td class='text-center'  style='position: relative;'><div class='info-box'>"+
	        				"<span>"+baseData.reportDetail.substring(0,10)+"..."+"</span>"+
	        				"<div class='chouc-hover-div'></div><div class='info-con' style='word-break:break-all;word-break: break-word;'>"+baseData.reportDetail+"</div></div></td>";
	        			}else{
	        				list[j]["reportDetail"]=baseData.reportDetail;
	        			}
	        			list[j]["reportDate"]=baseData.reportDate;
     			if(baseData.isCycle == '2'){
     				list[j]["reportStartDate"]=baseData.reportStartDate+"至"+baseData.reportEndDate;
     			}else{
     				list[j]["reportStartDate"]=baseData.reportStartDate+"至"+baseData.reportEndDate+"每日"+baseData.startTime+"至"+baseData.endTime;
     			}
     			
     			if(baseData.status == '1'){
     				list[j]["perNo"]="<div><span class='reject blue rejectBlue' onclick='reject("+baseData.id+")'>驳回</span>&nbsp;<span class='pass blue passBlue' onclick='pass("+baseData.id+")'>通过</span></div>";
     				list[j]["id"]="<div><span class='wait_audit'>待审核</span></div>";
     				
     			}else if(baseData.status == '2'){      
     				list[j]["perNo"]="<div><span class='reject blue rejectBlue' onclick='reject("+baseData.id+")'>驳回</span>  &nbsp; <span class='pass'>通过</span></div>";
     				list[j]["id"]="<div><span class='wait_audit' style='color:green'>通过</span></div>";
     				
     			}else{
     				list[j]["perNo"] ="<div><span class='reject'>驳回</span>   &nbsp;<span class='pass'>通过</span></div>";
     				list[j]["id"]="<div><span class='s_reject'>驳回</span></div>";
     				
     			}
     		}
	        	return list;	        	
	        },
	        "bProcessing": false,
	        "fnServerData": fnDataTablesPipeline,
	        "aoColumns": infoTitle,
	        "fnInitComplete": function () {
	        	$("#table_data").parent().parent().removeAttr("style");
	            $("#table_data_filter").css("margin-right", "75%");
	            $("#temporary_result_table_id_filter").hide();
	            $("#temporary_result_table_id_length").hide();
	            $("#temporary_result_table_id_info").hide();
	            var table_dataManager_wrapper = $("#table_data");
	            table_dataManager_wrapper.find("th").css("cursor", "default");
	        },
	        "fnPreDrawCallback": function(oSettings) {
	        	// httpHtmlF();
	        }
	        
	 });
}
function nameHover(wrapId){
    $("#"+wrapId).on("mouseover",".info-box", function(){
        var conTop=$(this).offset().top-$(document).scrollTop()+25,
                conLeft=$(this).offset().left;
        $(this).find(".info-con").css({
            left:conLeft+"px",
            top:conTop+"px"
        });
        $(this).addClass("info-box-hover");
        $(this).find(".info-con").show();
    });
    $("#"+wrapId).on("mouseout",".info-box", function(){
        $(this).find("chouc-hover-div").hide();
        $(this).removeClass("info-box-hover");
        $(this).find(".info-con").hide();
    });
}
function exportWord(){
	var siteCodeOrName=$('#siteCodeOrName').val();
	var reportReason="";
	if(siteCodeOrName != ""){
		for(var key in map){
			var s = map[key].indexOf(siteCodeOrName);
			if(s!= -1){
				reportReason+=key+"," 
			}
		}
		
	}
	window.location.href=webPath+"/tempReport_exportWord.action?siteCode="+$('#siteCode').val()+"&siteCodeOrName="+siteCodeOrName+"&reportReason="+reportReason+"&endDate="+$('#endDate').val()+"&startDate="+$('#startDate').val();

}
function pass(id){
	
	user={
			id:id,
			passOrReject:'pass'
		}

$.ajax({
    async: false,
    cache: false,
    type: 'POST',
    data:user,
    dataType: "json",
    url: webPath + "/tempReport_update.action",// 请求的action路径
    success: function (data) { // 请求成功后处理函数。
    	if (data) {
    		if(data.success == "true"){
    			alert("修改成功");
    			var siteCodeOrName=$('#siteCodeOrName').val();
    			var reportReason="";
    			if(siteCodeOrName != ""){
    				for(var key in map){
    					var s = map[key].indexOf(siteCodeOrName);
    					if(s!= -1){
    						reportReason+=key+"," 
    					}
    				}
    				$("#reportReason").val(reportReason);
    				var str = $("#reportReason").val();
    			}
    			temporary_result_table_id.fnClearTable();   
    			temporary_result_table_id.fnReloadAjax(webPath
    					+ "/tempReport_getDataList.action");
    		}else{
    			alert("修改失败");
    		}
        } else {
        }
    },error: function () {// 请求失败处理函数
    }
});
}
function reject(id){

		user={
			id:id,
			passOrReject:'reject'
		}
		$.ajax({
		    async: false,
		    cache: false,
		    type: 'POST',
		    data:user,
		    dataType: "json",
		    url: webPath + "/tempReport_update.action",// 请求的action路径
		    success: function (data) { // 请求成功后处理函数。
		    	if (data) {
		    		if(data.success == "true"){
		    			alert("修改成功");
		    			var siteCodeOrName=$('#siteCodeOrName').val();
		    			var reportReason="";
		    			if(siteCodeOrName != ""){
		    				for(var key in map){
		    					var s = map[key].indexOf(siteCodeOrName);
		    					if(s!= -1){
		    						reportReason+=key+"," 
		    					}
		    				}
		    				$("#reportReason").val(reportReason);
		    				var str = $("#reportReason").val();
		    			}
		    			temporary_result_table_id.fnClearTable();   
		    			temporary_result_table_id.fnReloadAjax(webPath
		    					+ "/tempReport_getDataList.action");
		    		}else{
		    			alert("修改失败");
		    		}
		        } else {
		        }
		    },error: function () {// 请求失败处理函数
		    }
		});
}
function getDic(){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/dicItem_getData.action?pid=9",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		if(data.success == "true"){
        			var list = data.list;
        			for(var i=0;i<list.length;i++){
        				var dataList = list[i];
        				map[dataList.value]=dataList.name;
        			}
        		}
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}

function initDate(){
	$("#startDate").datepicker({//添加日期选择功能
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
		defaultDate:'2016-12-07',//默认日期
//		minDate:0,//最小日期
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六']
	});
	$("#endDate").datepicker({//添加日期选择功能
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
		defaultDate:new Date(),//默认日期
//		minDate:0,//最小日期
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六']
	});
	
}

function initPage(dateText){
	var siteCodeOrName=$('#siteCodeOrName').val();
	var reportReason="";
	if(siteCodeOrName != ""){
		for(var key in map){
			var s = map[key].indexOf(siteCodeOrName);
			if(s!= -1){
				reportReason+=key+"," 
			}
		}
		$("#reportReason").val(reportReason);
		var str = $("#reportReason").val();
	}
	temporary_result_table_id.fnClearTable();   
	temporary_result_table_id.fnReloadAjax(webPath
			+ "/tempReport_getDataList.action");
}
