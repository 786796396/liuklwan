$(function() {

	/*
	 * ============================ @author:datepicker @time:2015-10-20
	 * ============================
	 */
	$("#feedDeadlineDate").datepicker(
			{// 添加日期选择功能
				inline : true,
				showOn: "both",
				buttonImage : webPath + "/images/date.png",
				buttonImageOnly : true,
				numberOfMonths : 1,// 显示几个月
				showButtonPanel : false,// 是否显示按钮面板
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
					// // 连通性结果
					// var siteCode = $('#siteCode').val();
					// var url = $('#url').val();
					// var type = $('#type').val();
					// showConnectionDialog(siteCode, null, dateText, url,
					// type);
				}
			});

	$("#feedDateStart").datepicker(
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
					getNoticeReceiverFeed();
				}
			});

	$("#feedDateEnd").datepicker(
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
					getNoticeReceiverFeed();
				}
			});

	$.datepicker._gotoToday = function (id) {
        $(id).datepicker('setDate', new Date()).datepicker('hide').blur();
        getNoticeReceiverFeed();
    }; 
    
	getNoticeReceiverFeed();

	$("#formUpload").ajaxForm({
		url : "notice_submitFeedBack.action",
		type : "post", // 请求方式
		dataType : "json", // 响应的数据类型
		async : false, // 异步
		success : function(data) {
			if(data.success){
				window.location.href = webPath + "/notice_indexReceiver.action";
			}else{
				alert(data.errorMsg);
			}
		},
		error : function() {
			alert(data.errorMsg);
		}
	});

	// 整改反馈
	$("#noticeUp").bind("click", function() {
		var noticeReceiverId = $("#noticeReceiverId").val();
		var feedContents = $("#feedContents").val();
	
		// 空校验
		if(feedContents == ""){
			alert("请输入反馈内容");
			return false;
		}
		if(feedContents != ""){
			if(feedContents.length>250){
				alert("反馈内容过长，请重新输入反馈内容");
				return false;
			}
		}
		
		//alert($("#feedAffixName").val());
		$("#formUpload").submit();
	});

	$("#topicSearch").keyup(function() {
		getNoticeReceiverFeed();
	});
	$("#feedDateStart").keyup(function() {
		getNoticeReceiverFeed();
	});
	$("#feedDateEnd").keyup(function() {
		getNoticeReceiverFeed();
	});
	
	//上传文件名显示
	var file = $('#noticeReport');
	aim = $('#feedAffixName');
	file.on('change', function( e ){
		//以下获取上传文件名不兼容IE浏览器
		//var name = e.currentTarget.files[0].name;
		
		var src=e.target || window.event.srcElement;
		var filename=src.value;
		var name = filename.substring( filename.lastIndexOf('\\')+1 );
		aim.val( name );
		aim.removeAttr("disabled");
	});
	
});

/**
 * 获取通知列表
 */
function getNoticeReceiverFeed() {
	var topicSearch = $("#topicSearch").val();
	var feedDateStart = $("#feedDateStart").val();
	var feedDateEnd = $("#feedDateEnd").val();
	$("#noticeContentId").html("");
	// $("#sumNumNotice").html(0);
	$.ajax({
		type : "POST",
		url : webPath + "/notice_getNoticeReceiverFeed.action?topicSearch="
				+ topicSearch + "&feedDateStart=" + feedDateStart
				+ "&feedDateEnd=" + feedDateEnd,
		dataType : "json",
		async : false,
		success : function(data) {
		
			$("#sumNumNotice").html(data.receiverSumCount);
			var html = '';
			if (data.receiverSumCount > 0) {
				$.each(data.items, function(index, obj) {
					var topic = obj.topic;
					if (null == topic || '' == topic) {
						topic = '--';
					}
					var contents = obj.contents;
					var contentsSubStr = obj.contentsSubStr;
					var affixName = obj.affixName;
				    var feedDeadlineDate = obj.feedDeadlineDate;
					var feedDate = obj.feedDate;
					var affixUrl = obj.affixUrl;
					
					if (null == feedDate || '' == feedDate) {
						feedDate = '--';
					}
					if (null == contents || '' == contents) {
						contents = '--';
					}
					var isRead = obj.isRead;
	
					var status = obj.status;
					
					var isOverDeadLineDate = obj.isOverDeadLineDate;
					
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
					
					if (null == affixName || '' == affixName) {
						html += '<td class="text-left">--</td>';
					}else{
						//附件url为空
						html += '<td class="text-left special_state" onclick="downLoadNoticeSendAffix('+obj.noticeSenderId+')">' + affixName + '</td>';
						
					}
					
					html += '<td class="text-center">' + feedDate + '</td>';
					var readStatusId = "readStatus_"+obj.noticeReceiverId;
					if(isRead==0){
						html += '<td class="text-left"><span class="cor-f31616" id='+readStatusId+'>未读</span></td>';
					}else{
						html += '<td class="text-left"><span id='+readStatusId+'>已读</span></td>';
					}	
			
					
					//反馈状态（0：无需反馈，1：未提交，2：已提交，3：退回）
					if(status==0){
						html += '<td class="text-center"><span>无需反馈</span></td>';
					}else if(status==1){
						html += '<td class="text-center"><span class="cor-f31616">未提交</span></td>';
					}else if(status==2){
						html += '<td class="text-center"><span>已提交</span></td>';
					}else if(status==3){
						html += '<td class="text-center"><span class="cor-f31616">退回</span></td>';
					}
					
					html+='<td class="text-center">';
					html+='<span class="special_state tzxq" onclick="openReadNotice('+obj.noticeReceiverId+')"+>通知详情</span>';
                    if(status==1||status==3){
                    	if(isOverDeadLineDate==0){
                    		html+='<span class="special_state t_fk" onclick="openNoticeFeedDiglog('+obj.noticeReceiverId+')"+>反馈</span>';
                    	}else{
//                    		html+='<span class="special_state t_fk"+>反馈功能已截止'+'</span>';
                    		
                    		html+='<span class="special_state" onclick="deadline()">反馈</span>';
                    	}
                    	
                    }else if(status==2){
                    	html+='<span class="special_state t_ck" onclick="openLookNoticeFeed('+obj.noticeReceiverId+')"+>查看</span>';
                    }else if(status==0){
                    	html+='<span>查看</span>';
                    }
                    
					html+='</td>';
					html += '</tr>';
					
				});
				
				$("#noticeContentId").html(html);
			}else{
			var text = copyInformation(1,"暂无数据");
   			 $("#noticeContentId").append(text);
			}

		}
	});
}

function deadline(){
	alert("反馈功能已截止");
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


//打开通知反馈窗口
function openNoticeFeedDiglog(noticeReceiverId) {
	$("#noticeReceiverId").val(noticeReceiverId);
	$( "#readStatus_" +noticeReceiverId).html("已读");
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : webPath + "/notice_getNoticeSenderAndReceiver.action?noticeReceiverId="
				+ noticeReceiverId,
		success : function(data) {	
			$("#topicFeedId").html(data.topic);
			$("#contentFeedId").html(data.contents);	
//			$("#affixFeedId").html(data.affixName);
			if(data.affixName==''||data.affixName==null){
				$("#affixFeedId").removeClass("special_state");
				$("#affixFeedId").html("无");
			}else{
				$("#affixFeedId").addClass("special_state");
				$("#affixFeedId").html("<a onclick='downLoadNoticeSendAffix("+data.noticeSenderId+ ")'>"+data.affixName+"</a>" );
			}
	
			$("#feedContents").val(data.feedContents);
			$("#feedAffixName").val(data.feedAffixName);
		}
	});
}


//查看反馈
function openLookNoticeFeed(noticeReceiverId) {
	
	$( "#readStatus_" +noticeReceiverId).html("已读");
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : webPath + "/notice_getNoticeSenderAndReceiver.action?noticeReceiverId="
				+ noticeReceiverId,
		success : function(data) {	
			$("#topicFeedIdLook").html(data.topic);
			$("#contentFeedIdLook").html(data.contents);	
//			$("#affixFeedId").html(data.affixName);
			if(data.affixName==''||data.affixName==null){
				$("#affixFeedIdLook").removeClass("special_state");
				$("#affixFeedIdLook").html("无");
			}else{
				$("#affixFeedIdLook").addClass("special_state");
				$("#affixFeedIdLook").html("<a onclick='downLoadNoticeSendAffix("+data.noticeSenderId+ ")'>"+data.affixName+"</a>" );
			}
	
			$("#feedContentsLook").html(data.feedContents);
			
			if(data.feedAffixName==''||data.feedAffixName==null){
				$("#feedAffixNameLook").removeClass("special_state");
				$("#feedAffixNameLook").html("无");
			}else{
				$("#feedAffixNameLook").addClass("special_state");
				$("#feedAffixNameLook").html("<a onclick='downLoadFeedAffix("+noticeReceiverId+ ")'>"+data.feedAffixName+"</a>" );
			}
		}
	});
}


//查看通知打开窗口阅读
function openReadNotice(noticeReceiverId) {
	$("#readStatus_"+noticeReceiverId).html("已读");
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : "json",
		url : webPath + "/notice_readNotice.action?noticeReceiverId="
				+ noticeReceiverId,
		success : function(data) {
			
			$("#orgSiteName").html(data.orgSiteName);
			$("#publishDate").html(data.publishDate);
			$("#topic").html(data.topic);
			$("#contents").html(data.contents);
			if(data.isFeedback==1){
				$("#isFeedback").html("是");
			}else{
				$("#isFeedback").html("否");
			}
			if(null!=data.feedDeadlineDate&&''!=data.feedDeadlineDate){
				$("#feedDeadlineDate").html(data.feedDeadlineDate);
			}else{
				$("#feedDeadlineDate").html("未开通");
			}
			
			if(null!=data.affixName&&''!=data.affixName){
				$("#affix").addClass("special_state");
				$("#affix").html("<a onclick='downLoadNoticeSendAffix("+data.id+")'>"+data.affixName+"</a>");
			}else{
				$("#affix").html("无");
				$("#affix").removeClass("special_state");
			}
		}
	});
}


function changeFile() {
	var noticeReport = $("#noticeReport").val();
	$("#feedAffixName").val(noticeReport);
}

//通知反馈附件
function downLoadFeedAffix(noticeReceiverId){
	if(noticeReceiverId){
		window.location.href = webPath + "/notice_downLoadFeedAffix.action?noticeReceiverId="+noticeReceiverId;
	}
}

//通知附件
function downLoadNoticeSendAffix(noticeSenderId){
	if(noticeSenderId){
		window.location.href = webPath + "/notice_downLoadNoticeSendAffix.action?noticeSenderId="+noticeSenderId;
	}
}

