$(function() {

	
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
					getNoticeFeedBack();
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
					getNoticeFeedBack();
				}
			});
	
	$.datepicker._gotoToday = function (id) {
        $(id).datepicker('setDate', new Date()).datepicker('hide').blur();
        getNoticeFeedBack();
    }; 

	getNoticeFeedBack();
	
	$("#siteSearch").keyup(function() {
		getNoticeFeedBack();
	});
	
	$("#feedDateStart").keyup(function() {
		getNoticeFeedBack();
	});
	$("#feedDateEnd").keyup(function() {
		getNoticeFeedBack();
	});

});

/**
 * 组织单位：获取反馈列表情况
 */
function getNoticeFeedBack() {
	var noticeSenderId = $("#noticeSenderId").val();
	var siteSearch = $("#siteSearch").val();
	var feedDateStart = $("#feedDateStart").val();
	var feedDateEnd = $("#feedDateEnd").val();
	$("#noticeReceiverContentId").html("");
	$.ajax({
		type : "POST",
		url : webPath + "/notice_getNoticeFeedBack.action?noticeSenderId="
				+ noticeSenderId + "&siteSearch=" + siteSearch
				+ "&feedDateStart=" + feedDateStart + "&feedDateEnd="
				+ feedDateEnd,
		dataType : "json",
		async : false,
		success : function(data) {
			var html = '';
			$("#receiverSumCount").html(data.receiverSumCount);
			$("#feedCount").html(data.feedCount);
			if (data.receiverSumCount > 0) {
				$.each(data.items, function(index, obj) {
					var siteCode = obj.siteCode;
					var siteName = obj.siteName;
					var feedContents = obj.feedContents;
					var feedContentsSubStr = obj.feedContentsSubStr;
					var feedAffixName = obj.feedAffixName;
					var feedAffixUrl = obj.feedAffixUrl;
					var feedDate = obj.feedDate;
					var status = obj.status;
					
					
					if (null == feedContents || '' == feedContents) {
						feedContents = '--';
					}
					
					if (null == feedDate || '' == feedDate) {
						feedDate = '未反馈';
					}
					var feedContentsLength = feedContents.length;
					
					html += '<tr>';
					html += '<td class="text-center num">' + (index + 1)
							+ '</td>';
					html += '<td class="text-left">' + siteCode + '</td>';
					html += '<td class="text-left">' + siteName + '</td>';
					
					var maxLength=10;
					if (feedContentsLength<=maxLength) {
						html += '<td class="text-left">' + feedContents + '</td>';
					}else{
						html += '<td class="text-left" style="position: relative;" >';
						html += ' <div class="info-box">';
						html += '  <a href="">'+feedContents.substring(0, maxLength)+'...</a>';
						html +='   <div class="chouc-hover-div"></div>';
						html +='  <div class="info-con">'+feedContents+'</div></div></td>';
					}
					
					
					if (null == feedAffixName || '' == feedAffixName) {
						html += '<td class="text-left text-left">--</td>';
					}else{
						/*html += '<td class="text-left text-left zg_report">' + feedAffixName + '</td>';*/
						html += '<td class="text-left text-left zg_report" onclick="downLoadFeedAffix('+obj.noticeReceiverId+')">' + feedAffixName + '</td>';
					}
					
					html += '<td class="text-center">' + feedDate + '</td>';
					if(status==3){
						html += '<td class="text-center"><span>已退回</span></td>';
					}else if(status==2){
						html += '<td class="text-center"><span class="send-back" onclick="returnNoticeFeed('+obj.noticeReceiverId+')">退回</span></td>';
					}else{
						html += '<td class="text-center"><span>退回</span></td>';
					}
					
					html += '</tr>';
					
				});
				$("#noticeReceiverContentId").html(html);
			}
		}
	});
	
}


//退回通知反馈
function returnNoticeFeed(noticeReceiverId){
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/notice_returnNoticeFeed.action?noticeReceiverId="+noticeReceiverId,
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

//通知反馈附件
function downLoadFeedAffix(noticeReceiverId){
	if(noticeReceiverId){
		window.location.href = webPath + "/notice_downLoadFeedAffix.action?noticeReceiverId="+noticeReceiverId;
	}
}
