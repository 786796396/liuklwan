$(function() {

	/*
	 * ============================ @author:datepicker @time:2015-10-20
	 * ============================
	 */
	$("#datepickerC").datepicker(
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
				defaultDate : GetDateStr(-1),// 默认日期
				minDate : $("#minDateSelect").val(),// 最小日期
				maxDate : GetDateStr(-1),// 最大日期
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				onSelect : function(dateText, inst) {
					// 连通性结果
					var siteCode = $('#siteCode').val();
					var url = $('#url').val();
					var type = $('#type').val();
					showConnectionDialog(siteCode, null, dateText, url, type);
				}
			});

});

//连通性弹框：参数siteCode,最后正常监测日期（备用），扫描时间（传null默认昨天），url,type(2:代表获取栏目连通性数据 1：首页连通性数据)
function showConnectionDialog(siteCode, lastAccessDate, scanDate, url, type) {
	$('#last-update').modal({})

	if (null == scanDate) {
		scanDate = "";
	}
	if (null == url) {
		url = '';
	}
	if (null == type) {
		type = 1
	}
	$("#siteCode").val(siteCode);
	$("#url").val(url);
	$("#type").val(type);

	//$("#datepickerC").val(scanDate);
	
	$.ajax({
		type : "POST",
		url : webPath + "/securityHomeChannel_getConnection.action?siteCode="
				+ siteCode + "&scanDate=" + scanDate + "&type=" + type
				+ "&url=" + url,
		dataType : "json",
		async : false,
		success : function(data) {
			$("#datepickerC").val(data.scanDate);
			$("#connectDialogResultId").html("");
			var list = data.body;
			if (null != list && list.length > 0) {
				var htmlResult;
				for ( var i = 0; i < list.length; i++) {
					var questionCode = list[i].questionCode;
					// console.log(i+","+questionCode);
					htmlResult += '<tr><td class="text-center w10p">' + (i + 1)
							+ '</td>' + '<td class="text-left w25p">'
							+ list[i].scanTime + '</td>'
							+ '<td class="text-center w20p">超时</td>'
							+ '<td class="text-left w20p">'
							+ ' <span class="state_num">'
							+ list[i].questionCode + '</span>' + ' <span>'
							+ list[i].questionDescribe + '</span>' + '</td>'
							+ '</tr>';
				}

				$("#connectDialogResultId").append(htmlResult);

			} else {
				$("#connectDialogResultId").append(
						"<tr><td class='text-center w100p' colspan='4'>无不连通数据</td></tr>");
			}

		},
		error : function() {

		}
	});

}