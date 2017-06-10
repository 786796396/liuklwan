var map = {};
var isCycle = '';
$(function(){
	//获取下拉数据
	getDic();
	//填充下拉数据
	putSelect();
	//获取数据
	getTbData();
	
	nameHover("tobodyy");
});
function sub(){
	var reportName = $.trim($("#reportName").val());
	if(reportName.length == 0){
		changeCss("reportName", "该选项为必填项");
		return false;
	}
	
	var reportReason = $('#reportReason option:selected').val();//选中的值
	if(reportReason == -10){
		changeCss("reportReason", "请选择报备原因");
		return false;
	}
	
	var reportDetail = $.trim($("#reportDetail").val());
	if(reportDetail.length == 0){
		changeCss("reportDetail", "该选项为必填项");
		return false;
	}
	var startDate='';
	var endDate='';
	var startTime='';
	var endTime='';
	if(isCycle ==1){//为周期
		startDate = $.trim($("#startDate").val());
		if(startDate.length == 0){
			changeCss("startDate", "该选项为必填项");
			return false;
		}
		endDate = $.trim($("#endDate").val());
		if(endDate.length == 0){
			changeCss("endDate", "该选项为必填项");
			return false;
		}
		startTime = $.trim($("#startTime").val());
		if(startTime.length == 0){
			changeCss("startTime", "该选项为必填项");
			return false;
		}
		endTime = $.trim($("#endTime").val());
		if(endTime.length == 0){
			changeCss("endTime", "该选项为必填项");
			return false;
		}
	}else{
		startDate = $.trim($("#reportStartDate").val());
		if(startDate.length == 0){
			changeCss("reportStartDate", "该选项为必填项");
			return false;
		}
		endDate = $.trim($("#reportEndDate").val());
		if(endDate.length == 0){
			changeCss("reportEndDate", "该选项为必填项");
			return false;
		}
	}
	
	
	var siteCode = $('#siteCode').val();
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        data:{
			'tempReport.siteCode':siteCode,
			'tempReport.reportName':reportName,
			'tempReport.reportReason':reportReason,
			'tempReport.reportDetail':reportDetail,
			'tempReport.isCycle':isCycle,
			'tempReport.reportStartDate':startDate,
			'tempReport.reportEndDate':endDate,
			'tempReport.startTime':startTime,
			'tempReport.endTime':endTime
		},
        dataType: "json",
        url: webPath + "/tempReport_add.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if(data){
        		if(data.success == "true"){
        			alert("添加成功");
        			$('#baobei_t_modal').modal('hide');
        			//$("#baobei_t_modal").hide();
        			//$(".modal-backdrop").hide();
        			$("#reportName").val('');
        			$("#reportReason").val('-10');
        			$("#reportDetail").val('');
        			$("#reportStartDate").val('');
        			$("#reportEndDate").val('');
        			$("#startDate").val('');
        			$("#endDate").val('');
        			$("#startTime").val('');
        			$("#endTime").val('');
        			getTbData();
        			
        			
        		}else {
        			alert("添加失败");
                }
        	}else{
        		alert("添加失败");
        	}
        },error: function () {// 请求失败处理函数
        	alert("添加失败");
        }
    });
	
}
//修改出错的input的外观
function changeCss(id, Validatemsg) {
	   $("#" + id).tips({
	       side: 1,
	       msg: Validatemsg,
	       bg: 'red',
	       time: 1
	   });
	   $("#" + id).focus();
	   //$("#" + id).css("border", "1px solid red");
}

function putSelect(){
	var html="<option value=\"-10\">请选择...</option>";
	for(var key in map){
		 html+="<option value="+key+">"+map[key]+"</option>";
	}
	$("#reportReason").html(html);
}
function tan(){
	$('#baobei_t_modal').modal({backdrop: 'static', keyboard: false});
	$("#radioLoca [name='isCycle']").on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		   isCycle =$(this).val();
		   if(isCycle == 1){
			   $("#cycle").css('display','block');
			   $("#last").css('display','none');
		   }else{
			   $("#cycle").css('display','none');
			   $("#last").css('display','block');
		   }
	}); 
}
function getTbData(){
	user={
			siteCode:$('#siteCode').val()
		}
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        data:user,
        dataType: "json",
        url: webPath + "/tempReport_getTbDataList.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var list = data.list;
        		var html = "";
        		for(var j=0;j<list.length;j++){
        			var baseData = list[j];
        			html+="<tr><td class=\"text-center\" style=\"font-size: 13px; color: #000;\">"+(j+1)+"</td>" +
        					"<td class=\"text-center\">"+baseData.reportName+"</td>"+
                            "<td class=\"text-center\">"+map[baseData.reportReason].split('（')[0]+"</td>";
		        			if(baseData.reportDetail.length>10){
		        				html+="<td class=\"text-center\"  style=\"position: relative;\"><div class=\"info-box\">"+
		        				"<span>"+baseData.reportDetail.substring(0,10)+"..."+"</span>"+
	                            "<div class=\"chouc-hover-div\"></div><div class=\"info-con\" style=\"word-break:break-all;word-break: break-word;\">"+
	                            baseData.reportDetail+"</div></div></td>";
	                            
		        			}else{
		        				html+="<td class=\"text-center\">"+baseData.reportDetail+"</td>";
		        			}
                            
		        			html+="<td class=\"text-center\">"+baseData.reportDate+"</td>";
                            if(baseData.isCycle == '2'){
                            	html+="<td class=\"text-center\" style=\"width:16%;\">"+baseData.reportStartDate+"至"+baseData.reportEndDate+"</td>";
                            }else{
                            	html+="<td class=\"text-center\" style=\"width:16%;\">"+baseData.reportStartDate+"至"+baseData.reportEndDate+"每日"+baseData.startTime+"至"+baseData.endTime+"</td>";
                            }
                            
                            if(baseData.status == '1'){
                            	html+="<td class=\"text-center\"><div><span class=\"wait_audit\">待审核</span></div></td></tr>";
                            }else if(baseData.status == '2'){
                            	html+="<td class=\"text-center\"><div><span class=\"wait_audit\"><a href=\"javascript:void(0)\" style=\"text-decoration:none;\">通过</a></span></div></td></tr>";
                            }else {
                            	html+="<td class=\"text-center\"><div><span class=\"s_reject\">驳回</span></div></td></tr>";
                            	
                            }
                            
        		}
        		$("#tobodyy").html(html);
        		
            } else {
            }
        },error: function () {// 请求失败处理函数
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

