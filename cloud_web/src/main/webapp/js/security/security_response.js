$(function() {
	$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
	// 周期控件数据初始化
	// timeTool();
	// 饼图--空白栏目本期检测进度
	// responseChannelPie();

	// 互动回应差栏目统计
	// responseChannelSum(serviceId);
	//扫描周期
	responseScanningPeriod()
	// 列表查询
	responseChannelList();

	// 检索
	$("#prependedInput").keyup(
			function() {
				var searchInfo = $("#prependedInput").val();
				if (searchInfo == "") {
					$("#table_response_channel_id tr").show();
				} else {
					$("#tbody_response_channel_id").find("._channelName").each(
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
	$("#response_time_tool_id li").click(function() {
		var active = $(this).hasClass("active");
		if (active == false) {
			$("#response_time_tool_id li").removeClass("active");
			$(this).addClass("active");
		}
		// 获取选中周期的id
		var servicePdId = $(this).find("span").html();
		var servicePdNum = $(this).find(".font-size1").html();

		$("#response_channel_period_id").html(servicePdNum);

		// 赋值用于列表查询的时候获取数据
		$("#service_period_id").val(servicePdId);

		// 点击时间按钮，联动列表查询
		responseChannelList();
		// 互动回应差栏目统计
		// responseChannelSum(serviceId);
	});
});
//监测周期点击事件
function responseScanningPeriodChange(){
	responseChannelList()
}
//扫描周期
function responseScanningPeriod() {
	var siteCode = $("#siteCode").val();
	var servicePeriodIdZZ = $("#servicePeriodIdZZ").val();
	$.ajax( {
		type : "POST",
		url : webPath+"/securityResponse_responseScanningPeriod.action",
		data:{
			siteCode:siteCode
		},
		dataType:"json",
		async : false,
		success : function(data) {
			if(data.size == 0){
				$("#scanCycleId").html("<option>暂无周期！</option>");
			} else{
				var html='';
				$.each(data.scanCycleList, function(index, obj) {
					if(servicePeriodIdZZ == obj.id ){
						html+='<option value='+obj.id+' selected="selected">'+obj.cycleDate+'</option>';
					}else{
						html+='<option value='+obj.id+'>'+obj.cycleDate+'</option>';
					}
				});
				
				$("#scanCycleId").html(html);
			}
		},
		error:function(data){
			if(data.success == 'false'){
//				alert(data.errorMsg);
			}
		}
	});
}
// 周期控件数据初始化
function timeTool() {
	var currentPeriodNum = 0;
	$.ajax({
		type : "POST",
		url : webPath + "/securityResponse_getTimeTool.action",
		dataType : "json",
		async : false,
		success : function(data) {
			var timeToolList = data.serviceList;
			var liStr = "";
			$("#response_time_tool_id").html("");
			if (timeToolList) {
				if (timeToolList.length > 0) {
					currentPeriodNum = timeToolList.length - 1;
					// 隐藏字段 服务周期id初始化数据
					$("#service_period_id").val(
							timeToolList[currentPeriodNum].id);
					// 列表上方 第几周 数据初始化
					$("#response_channel_period_id").html(
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
				$("#response_time_tool_id").append(liStr);

				if (timeToolList[0].info != ""
						&& timeToolList[0] != null) {
					$("#data_box1_footer_nodata").html(
							timeToolList[0].info);
				}
			} else {
				liStr += "<div id='conn_table_hide' class='zanwsj'>暂无周期数据</div>";
				$("#response_time_tool_id").append(liStr);
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
		// startAt: currentPeriodNum,//定位当前周期，该值需要后台获取
		// slideshowSpeed: 50000, // 自动播放速度毫秒
		// directionNav: true, //Boolean: (true/false)是否显示左右控制按钮
		// controlNav: false, //Boolean: usage是否显示控制菜单//什么是控制菜单？
		// slideshow: false,//载入页面时，是否自动播放
		// animationLoop: false, // "disable" classes at either end 是否循环滚动 循环播放
		itemWidth : 133,
		itemMargin : 1,
		minItems : 2,
		maxItems : 8

	/*
	 * before: function(i){ //获取选中期数 periodNum=i.animatingTo+1;
	 * $("#response_channel_period_id").html("第"+periodNum+"期");
	 * 
	 * //获取周期id var serviceId=$("#service_period_id").val();
	 * 
	 * //点击时间按钮，联动列表查询 responseChannelList(serviceId); //互动回应差栏目统计
	 * //responseChannelSum(serviceId);
	 *  },
	 * 
	 * after: function(i){}
	 */
	});
}

// 饼图--互动回应差栏目本期检测进度
function responseChannelPie() {
	var comPercent = [];
	var uncomPercent = [];
	var myChart_line = echarts.init(document.getElementById("response_pie_id"));
	$.ajax({
		type : "POST",
		url : webPath + "/securityResponse_responseChannelPie.action",
		dataType : "json",
		async : false,
		success : function(homeData) {
			comPercent.push(homeData.comPercent);
			uncomPercent.push(homeData.uncomPercent);
		}
	});
	option = {
		color : [ '#2DCC70', '#D6DAE3' ],
		tooltip : {
			trigger : 'item',
			formatter : "{b}"
		},
		calculable : true,
		series : [ {
			itemStyle : {
				normal : {
					labelLine : {
						length : 0
					}
				},
			},
			calculable : false,// 取消虚线
			name : '访问来源',
			type : 'pie',
			center : [ '48%' ],
			radius : [ '50%', '70%' ],
			data : [ {
				value : comPercent,
				name : '已完成 \n' + comPercent + '%'
			}, {
				value : uncomPercent,
				name : '未完成\n' + uncomPercent + '%'
			} ]
		} ]
	};
	myChart_line.setOption(option, true);
}

// 互动回应差栏目统计
function responseChannelSum(serviceId) {
	$.ajax({
		type : "POST",
		url : webPath + "/securityResponse_responseChannelSum.action",
		data : "serviceId=" + serviceId,
		dataType : "json",
		async : false,
		success : function(homeData) {
			// 清空列表数据
			$("#table_body_list_id").html("");
			$("#table_foot_list_id").html("");
			var responseList = homeData.resultList;
			if (homeData.errorMsg) {
			} else {
				if (responseList) {
					// 显示列表
					$("#pie-chart-tab_total").attr("style",
							"display:block");
					// 隐藏暂无数据
					$("#response_check_percent").attr("style",
							"display:none");

					var table_body_list = "<tr><th class='text-left'>栏目类型</th><th>栏目数量 （个）</th> </tr>";
					for ( var j = 0; j < homeData.resultList.length; j++) {
						table_body_list += "<tr><td class='text-left'>"
								+ homeData.resultList[j].channelName
								+ "</td><td>"
								+ homeData.resultList[j].channelSum
								+ "</td> </tr>";
					}
					$("#table_body_list_id").append(table_body_list);

					var table_foot_list = "<tr><td class='text-left'>总计</td><td>"
							+ homeData.total + "</td></tr>"
					$("#table_foot_list_id").append(table_foot_list);
				} else {
					// 隐藏列表数据
					$("#pie-chart-tab_total").attr("style",
							"display:none");
					// 显示暂无数据
					$("#response_check_percent").attr("style",
							"display:block");
				}
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

function responseScanningPeriodLlike(){
	responseChannelList()
}
// 列表查询
function responseChannelList() {
	var terms = $("#prependedInput").val();
	var servicePdId = $("#scanCycleId option:selected").val();
	var str = "暂无周期！"
	if(servicePdId == str){
		// 隐藏列表数据
		$("#table_response_channel_id").addClass("hide");
		$(".dropload-load").hide();
		// 隐藏第几周期
		$("#response_channel_period_id").addClass("hide");
		// 隐藏导出excel
		$("#response_excel_out").addClass("hide");
		
		$("#table_response_channel_hide1").hide();
		$("#table_response_channel_hide2").hide();
		$("#table_response_channel_hide").show();
		$("#table_response_channel_hide").html("暂无周期！");
	}else{
	// 获取周期id
	var serviceId = $("#service_period_id").val();
	$.ajax({
		type : "POST",
		url : webPath + "/securityResponse_responseChannelList.action",
		data:{
			terms:terms,
			servicePdId:servicePdId,
		},
		dataType : "json",
		async : false,
		success : function(data) {
			var conlist = data.returnList;
			var list = "";
			// 清空表数据
			$("#tbody_response_channel_id").html("");
			if (data.errorMsg) {
				// alert(data.errorMsg);
			} else {
				if (data.dataSize) {
					// 显示列表数据
					$("#table_response_channel_id").removeClass("hide");
					$(".dropload-load").show();
					// 显示第几周期
					$("#response_channel_period_id")
							.removeClass("hide");
					// 显示导出excel
					$("#response_excel_out").removeClass("hide");
					for ( var i = 0; i < conlist.length; i++) {
						var siteUrl = checkUrlHTTTP(conlist[i].channelUrl);
						list += "<tr><td class='td_left font_701999'>"
								+ (i + 1)
								+ "</td>"
								+ "<td class='td_left'><div class='wz-name'>"
								+ conlist[i].dicChannelId
								+ "</div></td>"
								+ "<td class='td_left'><a class='.wz-name _channelName' href='"
								+ siteUrl
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
								+ conlist[i].scanDate
								+ "</td>";
						if (conlist[i].imgUrl.match("htm")) {
							list += "<td><i class='kuaiz' onclick='getShot(\""
									+ conlist[i].imgUrl
									+ "\")'></i></td></tr>";
						} else {
//							list += "<td><a target='_blank' href='"
//									+ conlist[i].imgUrl
//									+ "'><img alt='截图' src='"
//									+ webPath
//									+ "/images/jiet.png'/></a></td></tr>";
							list += "<td><a target='_blank' href='"
								+ "jsp/onlineReport/cutImgs.jsp?imgUrl="+conlist[i].imgUrl + "&litImgUrl="+data.litImgUrl+"'><img alt='截图' src='"
								+ webPath
								+ "/images/jiet.png'/></a></td></tr>";
						}
					}
					// 隐藏暂无数据
					$("#table_response_channel_hide").hide();
					$("#table_response_channel_hide").html("");
					$("#table_response_channel_id").siblings(
							".dropload-load").hide();
					$("#tbody_response_channel_id").append(list);
				} else {
					// 隐藏列表数据
					$("#table_response_channel_id").addClass("hide");
					$(".dropload-load").hide();
					// 隐藏第几周期
					$("#response_channel_period_id").addClass("hide");
					// 隐藏导出excel
					$("#response_excel_out").addClass("hide");
					if(data.servicePeriodStatus == 1){
						// 显示暂无数据
						$("#table_response_channel_hide").show();
						$("#table_response_channel_hide").html("检测中，暂未发现问题！");
					}else if(data.servicePeriodStatus == 2){
						$("#table_response_channel_hide").show();
						$("#table_response_channel_hide").html("本周期未发现互动回应差情况！");
					}else if(data.servicePeriodStatus == 0){
						$("#table_response_channel_hide").show();
						$("#table_response_channel_hide").html("检测中，暂未发现问题！");
					}
				}
			}

		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
	}	
}

// excel导出
function responseChannelExcel() {
	var serviceId = $("#scanCycleId option:selected").val();
	var key = $("#prependedInput").val();
	window.location.href = webPath
			+ "/securityResponse_responseChannelExcel.action?serviceId="
			+ serviceId + "&key=" + key;
}
// 快照页面跳转
function getShot(imgUrl) {
	window.open(imgUrl);
}