$(function(){
	//左侧菜单遮罩层控制高度
	var oSheight=$('#sidebar').height();
	$('#left_opacity_id').css('height',oSheight+20);
	
	var isScanFlag=$("#isScanFlag_id").val();
	//点击我的监测报告跳转过来的
	if(isScanFlag && "true"==isScanFlag){
		//头部和左侧菜单天机遮罩层
		$("#top_opacity_id").show();
		$("#left_opacity_id").show();
	}else{
		$("#top_opacity_id").hide();
		$("#left_opacity_id").hide();
	}
	getWordList();

	
	//下载报告
	$("#isDown").bind("click",function(){
		var fid = $("#fid").val();
		downLoadWordFile(fid);
	});
	
	$("#reportsFk").bind("click",function(){
		var fid = $("#fidFk").val();
		downLoadWordFileFk(fid);
    });
	
	$("#formUpload").ajaxForm({
		url :"spotCheckNotice_updateNotice.action",
		type : "post", // 请求方式
		dataType : "json", // 响应的数据类型
		async :false, // 异步
		cache:false,
		success : function(data){
			window.location.href = webPath + "/wordList_index.action";
		},
		error : function(){
			alert(data.errorMsg);
		}
	});
	
	//整改反馈
	$("#noticeUp").bind("click",function(){
    	//$("#formUpload").attr("action", webPath + "/spotCheckNotice_updateNotice.action");
		var quesNum = $("#questionNum").val();
		var noticeResponse = $("#noticeResponse").val();
		var noticeReport = $("#noticeReport").val();
		if(noticeReport != null && noticeReport != ""){
			var hz = noticeReport.substring(noticeReport.lastIndexOf(".")+1,noticeReport.length);
			if(hz != "pdf" && hz != "doc" && hz != "docx" && hz!="jpg" && hz!="png"){
				alert("只能上传jpg、png、pdf和word文件");
				return false;
			}
		}
		if(noticeResponse != ""){
			if(noticeResponse.length>250){
				alert("内容过长，请重新输入反馈内容");
				return false;
			}
		}
		if(quesNum != ""){
			if(isNaN(quesNum)){
				alert('请输入数字');
				var quesNum = $("#questionNum").val("");
				return false;
			}
		}else{
			alert('请输入整改问题数');
			return false;
		}
		$("#formUpload").submit();
    });
	//判断是否显示关闭按钮
	if($("#isScanFlag_id").val()=="true"){
		$("#but").show();
	}
});
var table;
function getWordList(){
	var table_title = [];
	table_title.push({"mDataProp": "fileName", "bSortable": false,"sTitle": "文件名", "sClass": "t-left p_name", "bVisible": true, "tabIndex": -8});
	table_title.push({"mDataProp": "scnType", "bSortable": false,"sTitle": "检测形式", "sClass": "t-center p_sty", "bVisible": true, "tabIndex": -7});
	table_title.push({"mDataProp": "director","bSortable": false, "sTitle": "检测单位", "sClass": "t-left p_uint", "bVisible": true, "tabIndex": -6});
	table_title.push({"mDataProp": "taskTime", "bSortable": false,"sTitle": "检测周期", "sClass": "t-left p_p", "bVisible": true, "tabIndex": -5});
	table_title.push({"mDataProp": "download","bSortable": false, "sTitle": "检测报告", "sClass": "t-left p_rep", "bVisible": true, "tabIndex": -4});
	table_title.push({"mDataProp": "fk_status","bSortable": false, "sTitle": "反馈状态", "sClass": "t-center p_sta", "bVisible": true, "tabIndex": -3});
	table_title.push({"mDataProp": "isRead","bSortable": false, "sTitle": "已读状态", "sClass": "t-center p_al", "bVisible": true, "tabIndex": -2});
	table_title.push({"mDataProp": "notice","bSortable": false, "sTitle": "操作","sClass": "t-center p_op", "bVisible": true, "tabIndex": -1});
	if(undefined != table){
		table.fnClearTable();
		table.fnReloadAjax(webPath+ "/wordList_getWordList.action");
	}else{
		table = $("#wordList").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 10,
        "aaSorting": [],
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     // 可选中行，single单行。multi多行
        },
        "oLanguage": {
            "sSearch": "",
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
            "sZeroRecords": "没有要显示的数据！"
        },
        "bInfo": false,  
        "bLengthChange": false,
        "bFilter": false, // 过滤功能
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bDestroy": true,
        "sServerMethod": "POST",
        "sAjaxSource": webPath + "/wordList_getWordList.action",//getList
        "sAjaxDataProp": function (data) {
        	if(data && data.body){	
        		for (var i = 0; i < data.body.length; i++) {
        			var fid = data.body[i]["fid"];
        			var checkReportResult = data.body[i]["checkReportResult"];
        			var siteCode = data.body[i]["siteCode"];
        			var servicePeriodId = data.body[i]["servicePeriodId"];
        			var startDate = data.body[i]["startDate"];
        			var endDate = data.body[i]["endDate"];
        			var scnId=data.body[i]["scnId"];
        			
        			data.body[i]["download"] = "<span class='yulan-active fl' title='查看报告'" +
//        			\''+ obj.groupNum+ '\'
        			" onclick=\"searchReport('"+siteCode+"','"+scnId+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\"></span><i class='down_load8 fl' onclick='downLoadWordFile("+fid+","+scnId+");'></i>";
        			if(checkReportResult==2){//已反馈
        				data.body[i]["fk_status"] = "<span data-toggle='modal' data-target='#myModalNoticeShowFk' class='already_fk' onclick=\"getNoticeFk('"+fid+"','"+siteCode+"','"+servicePeriodId+"');\">已反馈</span>";
        			}else{//1未反馈
        				data.body[i]["fk_status"] = "<span class='not_yet_fk'>未反馈</span>";
        			}
        			
        			if(data.body[i]["isRead"] && "已读"==data.body[i]["isRead"]){
            			data.body[i]["notice"] = "<span data-toggle='modal' data-target='#myModalNoticeShow' class='tzzg-green' onclick=\"getNotice('"+fid+"','"+scnId+"','"+siteCode+"','"+servicePeriodId+"');\">查看通知</span>"
            			+"<span data-toggle='modal' data-target='#myModalNoticeUp' class='ckfk-yellow' onclick=\"getNotice('0','"+scnId+"','"+siteCode+"','"+servicePeriodId+"');\">提交反馈</span>";
        			}else{
            			data.body[i]["notice"] = "<span data-toggle='modal' data-target='#myModalNoticeShow' class='tzzg-green' onclick=\"getNotice('"+fid+"','"+scnId+"','"+siteCode+"','"+servicePeriodId+"');\">查看通知</span>"
            			+"<span  class='ckfk-gray'>提交反馈</span>";
        			}
        		}
        	}
        	
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": table_title,
        "fnCreatedRow":function (id) {
        	
        },
        "fnInitComplete": function () {
        	
        },
        "fnPreDrawCallback": function(oSettings) {
        	
        }
    });
}
}
/**
 * 查看报告的点击事件  先更新状态再打开报告
 */
function searchReport(siteCode,scnId,servicePeriodId,startDate,endDate){
	requestReport("siteCode="+siteCode+"&servicePeriodId="+servicePeriodId+"&startDate="+startDate+"&endDate="+endDate);
}
//获取整改通知反馈
function getNotice(fid,scnId,siteCode,servicePeriodId){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckNotice_getNotice.action?siteCode="+siteCode+"&servicePeriodId="+servicePeriodId+"&scnId="+scnId,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		
        		//列表数据刷新
        		getWordList();

				$("#siteNameW").html(data.databaseInfo['name']);
				$("#requireTime").html(data.requireTime);
				$("#director").html(data.spotCheckNotice['director']);
				$("#noticeRequirement").html(data.spotCheckNotice['noticeRequirement']);
				$("#noticeId").val(data.spotCheckNotice['id']);
				
				$("#siteNames").html(data.databaseInfo['name']);
				$("#requireTimes").html(data.requireTime);
				$("#directors").html(data.spotCheckNotice['director']);
				$("#noticeRequirements").html(data.spotCheckNotice['noticeRequirement']);
				if(data.spotCheckNotice['email2'] != null && data.spotCheckNotice['email2'] != ""){
					$("#linkmanName").html(data.databaseInfo['linkmanName']);
					$("#email2").html(data.databaseInfo['email2']);
				}
				if(data.spotCheckNotice['email'] != null && data.spotCheckNotice['email'] != ""){
					$("#principalName").html(data.databaseInfo['principalName']);
					$("#email").html(data.databaseInfo['email']);
				}
				$("#endTime").html(data.endTime);
				$("#fid").val(fid);
				$("#noticeResponse").val("");
				$("#questionNum").val("");
				$("#noticeReport").val("");
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

//查看整改通知反馈
function getNoticeFk(fid,siteCode,servicePeriodId){
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
        		var noticeRequirementsFk = "无";
				if(data.spotCheckNotice['noticeRequirement'] != null && data.spotCheckNotice['noticeRequirement'] != ""){
					noticeRequirementsFk = data.spotCheckNotice['noticeRequirement'];
				}
				var questionNumFk = "未填写";
//				if(data.spotCheckNotice['questionNum'] != null && data.spotCheckNotice['questionNum'] != ""){
					questionNumFk = data.spotCheckNotice['questionNum']+"&nbsp;&nbsp;个";
//				}
				var noticeResponsesFk = "未填写";
				if(data.spotCheckNotice['noticeResponse'] != null && data.spotCheckNotice['noticeResponse'] != ""){
					noticeResponsesFk = data.spotCheckNotice['noticeResponse'];
				}
				var reportsFks = "未上传";
				if(data.spotCheckNotice['noticeReportName'] != null && data.spotCheckNotice['noticeReportName'] != ""){
					reportsFks = data.spotCheckNotice['noticeReportName'];
					$("#reportsFk").html(reportsFks);
				}else{
					$("#reportsFk").hide();
					$("#reportsFks").html(reportsFks);
				}
        		$("#siteNamesFk").html(data.databaseInfo['name']);
				$("#requireTimesFk").html(data.requireTime);
				$("#directorsFk").html(data.spotCheckNotice['director']);
				$("#noticeRequirementsFk").html(noticeRequirementsFk);
				$("#noticeIdsFk").val(data.spotCheckNotice['id']);
				$("#noticeResponsesFk").html(noticeResponsesFk);
				$("#endTimesFk").html(data.endTime);
				if(data.spotCheckNotice['noticeReportUrl'] != null){
					$("#fidFk").val(data.spotCheckNotice['id']);
				}
				$("#questionNumFk").html(questionNumFk);
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

function downLoadWordFile(fid,scnId){
	if(scnId){
		if(fid){
			$.ajax({
		        async: false,
		        cache: false,
		        type: 'POST',
		        dataType: "json",
		        url: webPath + "/wordList_updateNotice.action?id="+scnId,
		        success: function (data) {
		        	if(data.errorMsg){
		        		alert(data.errorMsg);
		        	}else{
		        		//列表数据刷新
		        		getWordList();
		        		window.open(webPath + "/reportManageLog_getCanSeeWordFile.action?fid="+fid);
//		        		window.location.href = webPath + "/reportManageLog_getCanSeeWordFile.action?fid="+fid;
		        	}
				},error: function (data) {// 请求失败处理函数
		            alert(data.errorMsg);
		        }
		    });
			
		}
	}else{
		window.open(webPath + "/reportManageLog_getCanSeeWordFile.action?fid="+fid);
	}

}
function downLoadWordFileFk(fid){
	if(fid){
		window.open(webPath + "/spotCheckNotice_getDownNoticeWord.action?fid="+fid);
//		window.location.href = webPath + "/spotCheckNotice_getDownNoticeWord.action?fid="+fid;
	}
}

/**
 * 关闭当前窗口功能
 */
function clodeWindow() {
	window.close();
}
