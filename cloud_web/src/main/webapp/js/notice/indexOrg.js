$(function() {

	/*
	 * ============================ @author:datepicker @time:2015-10-20
	 * ============================
	 */
	//选框样式；
	$(document).ready(function(e) {
		//checkbox样式
		$("input[type='checkbox'],input[type='radio']").iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
		});

	});
	
	//上传文件名显示
	var file = $('#noticeReport');
	aim = $('#affixName');
	file.on('change', function( e ){
		 //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
		//以下获取上传文件名不兼容IE浏览器
		//var name = e.currentTarget.files[0].name;
		
		var src=e.target || window.event.srcElement;
		var filename=src.value;
		var name = filename.substring( filename.lastIndexOf('\\')+1 );
		aim.val( name );
		aim.removeAttr("disabled");
	});
	
	
	$("#feedDeadlineDate").datepicker(
			{// 添加日期选择功能
				inline : true,
				showOn :"both" ,
				buttonImage : webPath + "/images/date.png",
				buttonImageOnly : true,
				numberOfMonths : 1,// 显示几个月
				showButtonPanel : true,// 是否显示按钮面板
				dateFormat : 'yy-mm-dd',// 日期格式
				clearText : "清除",// 清除日期的按钮名称
				closeText : "关闭",// 关闭选择框的按钮名称
				yearSuffix : '年', // 年的后缀
				showMonthAfterYear : true,// 是否把月放在年的后面
				defaultDate : GetDateStr(0),// 默认日期
				minDate : GetDateStr(0),// 最小日期
				// maxDate : GetDateStr(50),// 最大日期
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				onSelect : function(dateText, inst) {
				}
			});

	$("#publishDateStart").datepicker(
			{// 添加日期选择功能
				inline : true,
				showOn: "both",
				buttonImage : webPath + "/images/date.png",
				buttonImageOnly : true,
				numberOfMonths : 1,// 显示几个月
				showButtonPanel : true,// 是否显示按钮面板
				dateFormat : 'yy-mm-dd',// 日期格式
				clearText : "清除",// 清除日期的按钮名称
				closeText : "关闭",// 关闭选择框的按钮名称
				yearSuffix : '年', // 年的后缀
				showMonthAfterYear : true,// 是否把月放在年的后面
//				todayBtn: true, 
				defaultDate : GetDateStr(0),// 默认日期
				// minDate : GetDateStr(0),// 最小日期
				// maxDate : GetDateStr(50),// 最大日期
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				onSelect : function(dateText, inst) {
					getNoticeSender();
				}
			});
	
	$.datepicker._gotoToday = function (id) {
        $(id).datepicker('setDate', new Date()).datepicker('hide').blur();
        getNoticeSender();
    }; 

	$("#publishDateEnd").datepicker(
			{// 添加日期选择功能
				inline : true,
				showOn: "both",
				buttonImage : webPath + "/images/date.png",
				buttonImageOnly : true,
				numberOfMonths : 1,// 显示几个月
				showButtonPanel : true,// 是否显示按钮面板
				dateFormat : 'yy-mm-dd',// 日期格式
				clearText : "清除",// 清除日期的按钮名称
				closeText : "关闭",// 关闭选择框的按钮名称
				yearSuffix : '年', // 年的后缀
				showMonthAfterYear : true,// 是否把月放在年的后面
				defaultDate : GetDateStr(0),// 默认日期
				// minDate : GetDateStr(0),// 最小日期
				// maxDate : GetDateStr(50),// 最大日期
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				onSelect : function(dateText, inst) {
					getNoticeSender();
				}
			});

	getNoticeSender();

	$("#formUpload").ajaxForm({
		url : "notice_createOrUpdateNotice.action",
		type : "post", // 请求方式
		dataType : "json", // 响应的数据类型
		async : false, // 异步
		success : function(data) {
			alert(data.success);
			window.location.href = webPath + "/notice_indexOrg.action";
		},
		error : function() {
			alert(data.errorMsg);
		}
	});

	// 整改反馈
	$("#noticeUp").bind("click", function() {
		var topic = $("#topic").val();
		var contents = $("#contents").val();
		var isFeedback =$('input[name="isFeedback"]:checked ').val();
		var feedDeadlineDate = $("#feedDeadlineDate").val();
		var noticeReport = $("#noticeReport").val();
		if(noticeReport != null && noticeReport != ""){
			var hz = noticeReport.substring(noticeReport.lastIndexOf(".")+1,noticeReport.length);
			if(hz != "pdf" && hz != "doc" && hz != "docx" && hz!="jpg" && hz!="png"){
				alert("只能上传jpg、png、pdf和word文件");
				return false;
			}
		}
		// 空校验
		if(topic == ""){
			alert("请输入发布主题");
			return false;
		}
		// 空校验
		if(contents == ""){
			alert("请输入发布内容");
			return false;
		}
		
		if(isFeedback==1&&feedDeadlineDate==""){
			alert("请选择反馈截止日期");
			return false;
		}
		
		if(topic != ""){
			if(topic.length>100){
				alert("发布主题过长，请重新输入发布主题");
				return false;
			}
		}
		if(contents != ""){
			if(contents.length>250){
				alert("发布内容过长，请重新输入发布内容");
				return false;
			}
		}
		
		$("#formUpload").submit();
	});

	$("#topicSearch").keyup(function() {
		getNoticeSender();
	});
	$("#publishDateStart").keyup(function() {
		getNoticeSender();
	});
	$("#publishDateEnd").keyup(function() {
		getNoticeSender();
	});
	
	$('#isFeedbackTrue').on('ifChecked', function(event){  
		changeFeedbackTrue();
	}); 
	$('#isFeedbackFalse').on('ifChecked', function(event){  
		changeFeedbackFalse();
	}); 
});

/**
 * 获取通知列表
 */
function getNoticeSender() {
	var topicSearch = $("#topicSearch").val();
	var publishDateStart = $("#publishDateStart").val();
	var publishDateEnd = $("#publishDateEnd").val();
	$("#noticeSenderContentId").html("");
	// $("#sumNumNotice").html(0);
	$.ajax({
		type : "POST",
		url : webPath + "/notice_getNoticeSender.action?topicSearch="
				+ topicSearch + "&publishDateStart=" + publishDateStart
				+ "&publishDateEnd=" + publishDateEnd,
		dataType : "json",
		async : false,
		success : function(data) {
			$("#sumNumNotice").html(data.size);
			var html = '';
			if (data.size > 0) {
				$.each(data.items, function(index, obj) {
					var topic = obj.topic;
					var contents = obj.contents;
					var contentsSubStr = obj.contentsSubStr;
					var affix = obj.affix;
					var publishDate = obj.publishDate;
					var feedResult = obj.feedResult;
					if (null == topic || '' == topic) {
						topic = '--';
					}
					if (null == contents || '' == contents) {
						contents = '--';
					}
					
					if (null == publishDate || '' == publishDate) {
						publishDate = '未发布';
						feedResult='--';
					}
					
					var contentsLength = contents.length;
					
					html += '<tr>';
					html += '<td class="text-center num">' + (index + 1)
							+ '</td>';
					html += '<td class="text-left">' + topic + '</td>';
					
					var maxLength=10;
					if(contentsLength<=maxLength){
						html += '<td class="text-left">' + contents + '</td>';
					}else{
						html += '<td class="text-left" style="position: relative;" >';
						html += ' <div class="info-box">';
						html += '  <a href="" class="norm">'+contents.substring(0, maxLength)+'...</a>';
						html +='   <div class="chouc-hover-div"></div>';
						html +='  <div class="info-con">'+contents+'</div></div></td>';
					}

				
					if (null == affix || '' == affix) {
						html += '<td class="text-left">--</td>';
					}else{
						html += '<td class="text-left special_state" onclick="downLoadNoticeSendAffix('+obj.noticeSenderId+')">' + affix + '</td>';
					}
					
					html += '<td class="text-left">' + publishDate + '</td>';
					
					if(feedResult!='--'&&feedResult!='未开通'){
						html += '<td class="text-center"><a class="cor-f31616" onclick="toIndexOrgFeed('+obj.noticeSenderId+')"> ' + feedResult + '</a></td>';
					}else{
						html += '<td class="text-center">' + feedResult + '</td>';
					}
					
					html += '<td class="text-center">';
					if(obj.status==0){
						//未发布
						html += '<span class="special_state" onclick="deleteNoticeSender('+obj.noticeSenderId+')">删除</span>';
						html += '<span class="special_state" data-toggle="modal" data-target="#create" onclick="openUpdateNotice('+obj.noticeSenderId+')">修改</span>';
						html += '<span class="special_state" onclick="publishNotice('+obj.noticeSenderId+')">发布</span>';
					}else{
						//已发布：已发布的通知不可【修改】只能【查看】
						//删除变灰色，不可点击
						html += '<span>删除</span>';
						html += '<span class="special_state" data-toggle="modal" data-target="#view" onclick="openLookNotice('+obj.noticeSenderId+')">查看</span>';
						html += '<span>发布</span>';
					}
					
					
					html += '</td>';
					html += '</tr>';
					
				});
				$("#noticeSenderContentId").html(html);
			}else{
				 var text = copyInformation(1,"暂无数据");
    			 $("#noticeSenderContentId").append(text);
			}

		}
	});
}

//组织单位：跳转到反馈列表
function toIndexOrgFeed(noticeSenderId){
	window.location.href = webPath + "/notice_indexOrgFeed.action?noticeSenderId="+noticeSenderId;
}


// 发布通知
function publishNotice(noticeSenderId){
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/notice_publishNotice.action?noticeSenderId="+noticeSenderId,
        success:function(data){
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		alert(data.success);
// $("#add_site_id").attr("style","display:none");//添加站点和全部启动不可点击
// $("#start_all_id").attr("style","display:none");//全部启动不可点击
        		window.location.reload(true);
        	}
        },error:function(data){
        	alert(data.errorMsg);
        }
    });
	
}

//创建通知打开窗口
function openCreateNotice() {
	//$('#formUpload').find('input,textarea,radio,file,button').removeAttr("disabled");
	$("#feedDeadlineDate").datepicker('enable');
	$("#title").html("创建通知");
	$("#topic").val('');
	$("#contents").val('');
	$("#feedDeadlineDate").val('');
	$("#affixName").val('');
	$("#id").val('');
	$("#noticeReport").val('');
	$('#affixName').attr("disabled", "disabled" );// 再改成disabled
}

//修改通知打开窗口
function openUpdateNotice(noticeSenderId) {
	//$('#formUpload').find('input,textarea,radio,file,button').removeAttr("disabled");
	$("#title").html("修改通知");
	$("#id").val(noticeSenderId);
	$("#noticeReport").val('');
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : webPath + "/notice_getNoticeSenderById.action?id="
				+ noticeSenderId,
		success : function(data) {

			$("#topic").val(data.topic);
			$("#contents").val(data.contents);
			$("#feedDeadlineDate").val(data.feedDeadlineDate);
			$("#affixName").val(data.affixName);
			if(data.isFeedback==0){
				$('#isFeedbackFalse').iCheck('check'); 
				$('#isFeedbackTrue').iCheck('uncheck'); 
			}else{
				//$("#isFeedbackTrueDiv").attr("checked",true); 
				$('#isFeedbackTrue').iCheck('check'); 
				$('#isFeedbackFalse').iCheck('uncheck'); 

			}
			
			if(data.affixUrl==null||data.affixUrl==''){
				$('#affixName').attr("disabled", "disabled" );// 再改成disabled
			}
		}
	});
}


//修改查看打开窗口
function openLookNotice(noticeSenderId) {

	$("#id").val(noticeSenderId);
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : webPath + "/notice_getNoticeSenderById.action?id="
				+ noticeSenderId,
		success : function(data) {

			$("#topicDivId").html(data.topic);
			$("#contentsDivId").html(data.contents);
			$("#feedDeadlineDateDivId").val(data.feedDeadlineDate);
			
			
			if(data.affixName!=null||data.affixName==''){
				$("#affixNameDivId").html('<a onclick="downLoadNoticeSendAffix(' +noticeSenderId+')">'+data.affixName+'</a>');
			}else{
				$("#affixNameDivId").html("无");
			}
			//$('#formUpload').find('input,textarea,radio,file,button').attr('readonly',true);
			//$('#formUpload').find('input,textarea,radio,file,button').attr("disabled","disabled");
			//$("#feedDeadlineDateDivId").datepicker('disable');
			if(data.isFeedback==0){
				$('#isFeedbackFalseDiv').iCheck('check'); 
				$('#isFeedbackTrueDiv').iCheck('uncheck'); 
				//$("#isFeedbackFalseDiv").attr("checked",true); 
				//$("input[type='checkbox'],input[type='radio']").iCheck('disable');
				$("#isFeedbackFalseDiv").iCheck('disable');
				$("#isFeedbackTrueDiv").iCheck('disable');
				
			}else{
				//$("#isFeedbackTrueDiv").attr("checked",true); 
				$('#isFeedbackTrueDiv').iCheck('check'); 
				$('#isFeedbackFalseDiv').iCheck('uncheck'); 
				
				//$("input[type='checkbox'],input[type='radio']").iCheck('disable');
				$("#isFeedbackFalseDiv").iCheck('disable');
				$("#isFeedbackTrueDiv").iCheck('disable');

			}
			
		}
	});
}



// 删除通知
function deleteNoticeSender(noticeSenderId){
	
	if(confirm('确定删除吗 ？')){
		 $.ajax({
		        async: false,
		        cache: false,
		        type: 'POST',
		        dataType: "json",
		        url: webPath + "/notice_deleteNoticeSender.action?noticeSenderId="+noticeSenderId,
		        success:function(data){
		        
		        	if(data.errorMsg){
		        		alert(data.errorMsg);
		        	}else{
		        		alert(data.success);
		        		window.location.reload(true);
		        	}
		        },error:function(data){
		        	alert(data.errorMsg);
		        }
		    });
	}

}

/*function changeFile() {
	var noticeReport = $("#noticeReport").val();
	$("#affixName").val(noticeReport);
}*/

function changeFeedbackFalse(){
	$("#feedDeadlineDate").attr("disabled","disabled");// 再改成disabled
	$("#feedDeadlineDate").datepicker('disable');
	$("#feedDeadlineDate").val('');
	
	
	//$('#feedDeadlineDate').iCheck('uncheck'); 
}

function changeFeedbackTrue(){
	$("#feedDeadlineDate").attr("disabled","");// 再改成disabled
	$("#feedDeadlineDate").datepicker('enable');
}

//通知附件
function downLoadNoticeSendAffix(noticeSenderId){
	if(noticeSenderId){
		window.location.href = webPath + "/notice_downLoadNoticeSendAffix.action?noticeSenderId="+noticeSenderId;
	}
}
