$(function() {
	$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
	//加载监测周期
	blankColumnMonitoringPeriod();
	/*
	 * ============================ @author:flc @time:2015-10-08
	 * ============================
	 */
	$(".select").each(function() {
		var s = $(this);
		var z = parseInt(s.css("z-index"));
		var dt = $(this).children("dt");
		var dd = $(this).children("dd");
		var _show = function() {
			dd.slideDown(200);
			dt.addClass("cur");
			s.css("z-index", z + 1);
		}; // 展开效果
		var _hide = function() {
			dd.slideUp(200);
			dt.removeClass("cur");
			s.css("z-index", z);
		}; // 关闭效果
		dt.click(function() {
			dd.is(":hidden") ? _show() : _hide();
		});
		dd.find("li").click(function() {
			dt.html($(this).html());
			_hide();
		}); // 选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i) {
			!$(i.target).parents(".select").first().is(s) ? _hide() : "";
		});
	});
	// 周期控件数据初始化
	// timeTool();

	// 空白栏目本期检测进度
	// blankChannelSum();
	// 折线图--空白栏目趋势
	/*
	 * setTimeout(function() { blankChannelLine(); }, 100);
	 */

	// 检索
	$("#prependedInput").keyup(
			function() {
				var searchInfo = $("#prependedInput").val();
				if (searchInfo == "") {
					$("#table_blank_channel_id tr").show();
				} else {
					$("#table_blank_channel_id").find(".channelName").each(
							function(index, element) {
								if ($(this).html().indexOf(searchInfo) < 0) {
									$(this).parents("tr").hide();
								} else {
									$(this).parents("tr").show();
								}
							});
				}
			});

	// 空白栏目列表查询
	blankDetailPage();

	// 周期时间控件添加点击事件
	$("#time_tool_id li").click(function() {
		var active = $(this).hasClass("active");
		if (active == false) {
			$("#time_tool_id li").removeClass("active");
			$(this).addClass("active");
		}
		// 获取选中周期的id
		var servicePdId = $(this).find("span").html();
		var servicePdNum = $(this).find(".font-size1").html();

		$("#blank_channel_period_id").html(servicePdNum);

		// 赋值用于列表查询的时候获取数据
		$("#service_peroid_id").val(servicePdId);

		// 点击时间按钮，联动列表查询
		blankDetailPage();

	});

});
//like查询
function linkAllBlankCycleChange(){
	blankDetailPage()
}
function blankDetailMonitoringPeriod(){
	blankDetailPage()
}
//加载监测周期
function blankColumnMonitoringPeriod(){
	var siteCode = $("#siteCode").val();
    var servicePeriodIdZZ = $("#servicePeriodIdZZ").val();	
	$.ajax( {
		type : "POST",
		url : webPath+"/securityBlankDetail_blankDetailMonitoringPeriod.action",
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

// 时间控件初始化
function timeTool() {
	var currentPeriodNum = 0;
	$
			.ajax({
				type : "POST",
				url : webPath + "/securityBlankDetail_getTimeTool.action",
				dataType : "json",
				async : false,
				success : function(data) {
					var timeToolList = data.serviceList;
					var liStr = "";
					$("#time_tool_id").html("");
					// 清空数据
					if (timeToolList) {
						if (timeToolList.length > 0) {
							currentPeriodNum = timeToolList.length - 1;
							// 列表查询面的第几周期显示---赋值--只有第一次打开页面的时候会执行此操作
							$("#blank_channel_period_id").html(
									"第" + (currentPeriodNum + 1) + "期");
							// 初始化服务周期id
							$("#service_peroid_id").val(
									timeToolList[currentPeriodNum].id);

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
											+ "</li>";
								} else {
									liStr += "<li><div class='font-size1'>第"
											+ (i + 1) + "期<span id=servicePd"
											+ i + " style='display:none'>"
											+ timeToolList[i].id
											+ "</span></div>"
											+ timeToolList[i].startDate
											+ "</li>";
								}

							}
						}

						$("#time_tool_id").append(liStr);
						if (timeToolList[0].info != ""
								&& timeToolList[0] != null) {
							$("#data_box1_footer_nodata").html(
									timeToolList[0].info);
						}
					} else {
						liStr += "<div id='conn_table_hide' class='zanwsj'>暂无数据</div>";
						$("#time_tool_id").append(liStr);
					}

				}
			});

	/*
	 * ============================ @author:轮播 @time:2015-10-09
	 * ============================
	 */
	$("#flexsliderMore").flexslider({
		animation : "slide",
		// startAt: currentPeriodNum,//定位当前周期，该值需要后台获取
		// slideshowSpeed: 50000, // 自动播放速度毫秒
		// directionNav: true, //Boolean: (true/false)是否显示左右控制按钮
		// controlNav: false, //Boolean: usage是否显示控制菜单//什么是控制菜单？
		// slideshow: false,//载入页面时，是否自动播放
		itemWidth : 133,
		itemMargin : 1,
		minItems : 2,
		maxItems : 8
	// animationLoop: false,// "disable" classes at either end 是否循环滚动 循环播放

	/*
	 * before: function(i){ //选中的服务周期id var
	 * serviceId=$("#servicePd"+i.animatingTo).html(); //选中的服务周期期数
	 * periodNum=i.animatingTo+1;
	 * $("#blank_channel_period_id").html("第"+periodNum+"期"); //点击时间按钮，联动列表查询
	 * blankDetailPage(serviceId); }, after: function(i){ }
	 */
	});

}

// 饼图--空白栏目本期检测进度
function blankChannelSum() {
	var comPercent = [];
	var uncomPercent = [];
	var myChart_line = echarts.init(document
			.getElementById("blank_detail_pie_id"));
	$.ajax({
		type : "POST",
		url : webPath + "/securityBlankDetail_blankDetailPie.action",
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
			calculable : false,// 取消虚线
			name : '访问来源',
			type : 'pie',
			radius : [ '45%', '70%' ],
			center : [ '50%', '60%' ],
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

// 折线图--空白栏目趋势
function blankChannelLine() {
	var periodNumDate = [];
	var unuseLinkHomeNum = [];
	var myChart_line = echarts.init(document
			.getElementById("blank_detail_line_id"));
	$.ajax({
		type : "POST",
		url : webPath + "/securityBlankDetail_blankDetailLine.action",
		dataType : "json",
		async : false,
		success : function(homeData) {
			for ( var j = 0; j < homeData.length; j++) {
				periodNumDate.push("第" + homeData[j].retuenPeriodNum + "期");
				unuseLinkHomeNum.push(homeData[j].blankNum);
			}
		}
	});
	option = {
		color : [ '#4CD2D1' ],
		dataZoom : {
			show : true,
			realtime : true,
			height : 20,
			start : 100,
			end : 40
		},
		tooltip : {
			trigger : 'axis',
			borderColor : '#48b',
			axisPointer : {
				color : 'rgba(150,150,150,0.3)',
				width : 'auto',
				type : 'default'
			},
			formatter : "{b}<br>{a}：  {c}个",
			padding : 15
		},
		calculable : true,
		xAxis : [ {
			splitLine : false,
			axisLine : false,
			axisTick : false,
			type : 'category',
			data : periodNumDate
		} ],
		grid : {
			borderColor : '#fff',
			x : 50,
			y : 50,
			x2 : 50,
			y2 : 50
		},
		yAxis : [ {
			name : '空白栏目个数  (个)\n',
			nameTextStyle : {
				color : 'black'
			},
			axisLine : {
				lineStyle : {
					color : '#ffffff',
					width : 1
				},
				type : 'value'
			}
		} ],
		series : [ {
			symbol : 'emptyCircle',
			symbolSize : 4,
			name : '空白栏目个数',
			type : 'line',
			data : unuseLinkHomeNum

		} ]
	};
	myChart_line.setOption(option, true);
}
// 列表查询
function blankDetailPage() {
	//加载查询框参数
	var terms = $("#prependedInput").val();
	var servicePdId = $("#scanCycleId option:selected").val();
	var str = "暂无周期！";
	if(servicePdId==str){
		// 空控制当前查询周期的显示和隐藏
		$("#blank_channel_period_id").attr("style",
				"display:none");
		// 表格数据的显示和隐藏
		$("#table_blank_channel_id").hide();
		// 导出excel显示和隐藏
		$("#blank_channel_excel").attr("style", "display:none");
		// 显示暂无数据
		$("#table_blank_channel_hide").show();
		$("#table_blank_channel_hide").html("暂无周期！");
	}else{
	$.ajax({
		type : "POST",
		url : webPath+ "/securityBlankDetail_blankDetailPage.action",
		dataType : "json",
		async : false,
		data:{
			terms:terms,
			servicePdId:servicePdId,
		},
		success : function(data) {
			var conlist = new Array();
			conlist = data.returnList;

			var list = "";
			// 清空数据
			$("#tbody_blank_channel_id").html("");

			if (conlist) {
				// 空控制当前查询周期的显示和隐藏

				$("#blank_channel_period_id").attr("style",
						"display:block");
				// 表格数据的显示和隐藏
				$("#table_blank_channel_id").show();
				// 导出excel显示和隐藏
				$("#blank_channel_excel")
						.attr("style", "display:block");
				// 隐藏暂无数据
//				$("#table_blank_channel_hide").attr("style",
//						"display:none");
				$("#table_blank_channel_hide").hide();
				$("#table_blank_channel_hide").html("");
				if (conlist.length > 0) {
					$("#blank_channel_total_id").html(
							"共" + data.total + "个空白栏目");
					for ( var i = 0; i < conlist.length; i++) {
						var siteUrl = checkUrlHTTTP(conlist[i].url);
						list += "<tr><td class='td_left font_701999' style='width:50px;'>"
								+ (i + 1)
								+ "</td>"
								+ "<td class='td_left channelName' style='width:150px;'>"
								+ conlist[i].channelName
								+ "</td>"
								+ "<td class='td_left' style='width:200px;'><a class='url-ellipsis1' href='"
								+ siteUrl
								+ "' target='_blank' title='"
								+ siteUrl
								+ "'>"
								+ (siteUrl.substring(0, 40))
								+ "</a></td>"
								+ "<td class='td_left' style='width:150px;'><div class='wz-name' style='width:250px;'>"
								+ conlist[i].path + "</div></td>"
								+ "<td class='td_left' style='width:150px;'>"
								+ conlist[i].problemDesc
								+ "</td>"
								+"<td class='td_left' style='width:150px;'>"
								+conlist[i].scanDate
								+"</td>";
						if (conlist[i].imgUrl.match("htm")) {// 快照
							list += "<td style='width:100px;'><i class='kuaiz' onclick='getShot(\""
									+ conlist[i].imgUrl
									+ "\")'></i></td></tr>";
						} else {// 截图
							imgUrl = conlist[i].imgUrl;
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
					$("#table_blank_channel_hide").hide();
					$("#table_blank_channel_hide").html("");
					$("#table_blank_channel_id").siblings(
							".dropload-load").hide();
					$("#tbody_blank_channel_id").append(list);
				}
			} else {
				// 空控制当前查询周期的显示和隐藏
				$("#blank_channel_period_id").attr("style",
						"display:none");
				// 表格数据的显示和隐藏
				$("#table_blank_channel_id").hide();
				// 导出excel显示和隐藏
				$("#blank_channel_excel").attr("style", "display:none");
				// 显示暂无数据
//				$("#table_blank_channel_hide").attr("style",
//						"display:block");
				if(data.servicePeriodStatus == 1){
					$("#table_blank_channel_hide").show();
					$("#table_blank_channel_hide").html("检测中，暂未发现问题！");
				}else if(data.servicePeriodStatus == 2){
					$("#table_blank_channel_hide").show();
					$("#table_blank_channel_hide").html("本周期未发现空白栏目！");
				}else if(data.servicePeriodStatus == 0){
					$("#table_blank_channel_hide").show();
					$("#table_blank_channel_hide").html("检测中，暂未发现问题！");
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
function blankDetailExcel() {
	// 服务周期id
	var serviceId = $("#scanCycleId option:selected").val();
	var key = $("#prependedInput").val();
	window.location.href = webPath
			+ "/securityBlankDetail_blankDetailExcel.action?serviceId="
			+ serviceId + "&key=" + key;
}

// 快照页面跳转
function getShot(imgUrl) {
	window.open(imgUrl);
}
