$(function() {
	$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
	/*
	 * ============================ @author:datepicker @time:2015-10-20
	 * ============================
	 */
	$("#datepicker").datepicker(
		{// 添加日期选择功能
			inline : true,
			numberOfMonths : 1,// 显示几个月
			showButtonPanel : true,// 是否显示按钮面板
			dateFormat : 'yy-mm-dd',// 日期格式
			clearText : "清除",// 清除日期的按钮名称
			closeText : "关闭",// 关闭选择框的按钮名称
			yearSuffix : '年', // 年的后缀
			showMonthAfterYear : true,// 是否把月放在年的后面
			defaultDate : GetDateStr(-1),// 默认日期
			minDate : getLastYearYestdy(),//最大时间
			maxDate : GetDateStr(-1),// 最大日期
			monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
					'9月', '10月', '11月', '12月' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ]
		});
})