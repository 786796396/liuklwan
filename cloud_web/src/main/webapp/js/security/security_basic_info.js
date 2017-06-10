
$(function() {
	$(".tab_box1 table tr:odd td").css("background", "#fafbfc");

	// 周期控件数据初始化
	//timeTool();

	// 列表数据
	basicInfoList();

	// 检索
	$("#prependedInput").keyup(
		function() {
			var searchInfo = $("#prependedInput").val();
			if (searchInfo == "") {
				$("#table_basic_channel_id tr").show();
			} else {
				$("#tbody_basic_channel_id").find("._channelName").each(
						function(index, element) {
							if ($(this).html().indexOf(searchInfo) < 0) {
								$(this).parents("tr").hide();
							} else {
								$(this).parents("tr").show();
							}
						});
			}
		});
	// 周期控件单击事件
	$("#basic_time_tool_id li").click(function() {
		var active = $(this).hasClass("active");
		if (active == false) {
			$("#basic_time_tool_id li").removeClass("active");
			$(this).addClass("active");
		}
		// 获取选中周期的id
		var servicePdId = $(this).find("span").html();
		var servicePdNum = $(this).find(".font-size1").html();

		$("#basic_channel_period_id").html(servicePdNum);

		// 赋值用于列表查询的时候获取数据
		$("#service_period_id").val(servicePdId);

		// 点击时间按钮，联动列表查询
		basicInfoList();

	});

});

// 周期控件数据初始化
function timeTool() {
	var currentPeriodNum = 0;
	$.ajax({
		type : "POST",
		url : webPath + "/securityBasicInfo_getTimeTool.action",
		dataType : "json",
		async : false,
		success : function(data) {
			var timeToolList = data.serviceList;
			var liStr = "";
			$("#basic_time_tool_id").html("");
			if (timeToolList) {
				if (timeToolList.length > 0) {
					currentPeriodNum = timeToolList.length - 1;
					// 隐藏字段 服务周期id初始化数据
					$("#service_period_id").val(
							timeToolList[currentPeriodNum].id);
					// 列表上方 第几周 数据初始化
					$("#basic_channel_period_id").html(
							"第" + (currentPeriodNum + 1) + "期");

					for ( var i = 0; i < timeToolList.length; i++) {
						if (i == currentPeriodNum) {
							liStr += "<li class='active'><div class='font-size1'>第"
									+ (i + 1)
									+ "期<span id=servicePd"
									+ i
									+ " style='display:none'>"
									+ timeToolList[i].id
									+ "</span></div>"
									+ timeToolList[i].startDate
									+ "</li>"
						} else {
							liStr += "<li><div class='font-size1'>第"
									+ (i + 1) + "期<span id=servicePd"
									+ i + " style='display:none'>"
									+ timeToolList[i].id
									+ "</span></div>"
									+ timeToolList[i].startDate
									+ "</li>"
						}
					}
				}
				$("#basic_time_tool_id").append(liStr);

				if (timeToolList[0].info != ""
						&& timeToolList[0] != null) {
					$("#data_box1_footer_nodata").html(
							timeToolList[0].info);
				}
			} else {
				liStr += "<div id='conn_table_hide' class='zanwsj'>暂无周期数据</div>";
				$("#basic_time_tool_id").append(liStr);
			}

		}
	});

	/*
	 * ============================ @author:轮播 @time:2015-10-09
	 * 
	 * ============================
	 */

	$("#flexsliderMore").flexslider({
		animation : "slide",
		itemWidth : 133,
		itemMargin : 1,
		minItems : 2,
		maxItems : 8
	});
}

// 列表查询
function basicInfoList() {
	// 获取周期id
	var serviceId = $("#service_period_id").val();
	$.ajax({
		type : "POST",
		url : webPath + "/securityBasicInfo_basicInfoList.action",
		data : "serviceId=" + serviceId,
		dataType : "json",
		async : false,
		success : function(data) {
			var conlist = data.returnList;
			var list = "";
			// 清空表数据
			$("#tbody_basic_channel_id").html("");
			if (data.errorMsg) {
				// alert(data.errorMsg);
			} else {
				if (conlist) {
					// 显示列表数据
					$("#table_basic_channel_id").removeClass("hide");
					// 隐藏暂无数据
					$("#table_response_channel_hide1").addClass("hide");
					$(".dropload-load").show();
					// 显示第几周期
					$("#basic_channel_period_id").removeClass("hide");
					// 显示导出excel
					$("#basic_excel_out").removeClass("hide");
					for ( var i = 0; i < conlist.length; i++) {
						list += "<tr><td class='td_left font_701999'>"
								+ (i + 1)
								+ "</td>"
								+ "<td class='td_left'><a class='.wz-name _channelName' href='"
								+ conlist[i].channelUrl
								+ "' target='_blank' title='"
								+ conlist[i].channelName
								+ "'>"
								+ conlist[i].channelName
								+ "</a></td>"
								+ "<td class='td_left'>"
								+ conlist[i].problemTypeId
								+ "</td>"
								+ "<td class='td_left'><div class='wz-name ellipsis-w1' title='"
								+ conlist[i].problemDesc + "' >"
								+ conlist[i].problemDesc
								+ "</div></td>"
								+ "<td class='td_left'>"
								+ conlist[i].serviceTime + "</td>"
								+ "<td class='td_left'>"
								+ conlist[i].modifyTime + "</td>";

						if (conlist[i].imgUrl.match("htm")) {
							list += "<td><i class='kuaiz' onclick='getShot(\""
									+ conlist[i].imgUrl
									+ "\")'></i></td></tr>";
						} else {
							list += "<td><a target='_blank' href='"
									+ conlist[i].imgUrl
									+ "'><img alt='截图' src='"
									+ webPath
									+ "/images/jiet.png'/></a></td></tr>";
						}
					}
					$("#table_basic_channel_id").siblings(".dropload-load").hide();
					$("#tbody_basic_channel_id").append(list);
				} else {
					// 隐藏列表数据
					$("#table_basic_channel_id").addClass("hide");
					// 显示暂无数据
					$("#table_response_channel_hide1").removeClass("hide");
					$(".dropload-load").hide();
					// 隐藏第几周期
					$("#basic_channel_period_id").addClass("hide");
					// 隐藏导出excel
					$("#basic_excel_out").addClass("hide");
				}
			}

		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

// excel导出
function basicInfoExcel() {
	var serviceId = $("#service_period_id").val();
	var key = $("#prependedInput").val();
	window.location.href = webPath
			+ "/securityBasicInfo_basicInfoExcel.action?serviceId=" + serviceId
			+ "&key=" + key;
}
// 快照页面跳转
function getShot(imgUrl) {
	window.open(imgUrl);
}