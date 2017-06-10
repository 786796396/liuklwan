$(function() {
	$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
	monitoringPeriod();
	// 周期控件数据初始化
	// getTimeTool();

	// 饼图--服务不实用本期检测进度
	// servicePie();

	// 服务不实用统计
	// serviceProblemType();

	// 服务不实用--柱状图
	/*
	 * setTimeout(function(){ serviceBar(); },100);
	 */
	// 列表查询
	serviceProblemPage();

	// 检索
	$("#prependedInput").keyup(
			function() {
				var searchInfo = $("#prependedInput").val();
				if (searchInfo == "") {
					$("#table_security_service_id tr").show();
				} else {
					$("#table_security_service_id").find(".serviceItem").each(
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
	$("#service_time_tool_id li").click(function() {
		var active = $(this).hasClass("active");
		if (active == false) {
			$("#service_time_tool_id li").removeClass("active");
			$(this).addClass("active");
		}
		// 获取选中周期的id
		var servicePdId = $(this).find("span").html();
		var servicePdNum = $(this).find(".font-size1").html();

		$("#service_service_period_id").html(servicePdNum);
		$("#service_period_total").html(servicePdNum + "  问题统计");

		// 赋值用于列表查询的时候获取数据
		$("#service_period_id").val(servicePdId);

		// 服务不实用统计
		serviceProblemType();
		// 列表查询
		serviceProblemPage();

	});

})
// 监测周期
function monitoringPeriod() {
	var siteCode = $("#siteCode").val();
	var servicePeriodIdZZ = $("#servicePeriodIdZZ").val();
	$.ajax({
		type : "POST",
		url : webPath + "/securityServcie_monitoringPeriod.action",
		dataType : "json",
		data : {
			siteCode : siteCode
		},
		async : false,
		success : function(data) {
			if (data.size == 0) {
				$("#scanCycleId").html("<option>暂无周期！</option>");
			} else {
				var html = '';
				$.each(data.scanCycleList, function(index, obj) {
					if (servicePeriodIdZZ == obj.id) {
						html += '<option value=' + obj.id
								+ ' selected="selected">' + obj.cycleDate
								+ '</option>';
					} else {
						html += '<option value=' + obj.id + '>' + obj.cycleDate
								+ '</option>';
					}
				});
				$("#scanCycleId").html(html);
			}
		},
		error : function(data) {
			if (data.success == 'false') {
				// alert(data.errorMsg);
			}
		}
	});
}
// excel导出
function serviceExcel() {
	var servicePdId = $("#scanCycleId option:selected").val();
	var key = $("#prependedInput").val();
	window.location.href = webPath
			+ "/securityServcie_serviceExcel.action?servicePdId=" + servicePdId
			+ "&key=" + key;
}

// 周期控件数据初始化
function getTimeTool() {
	var currentPeriodNum = 0;
	$
			.ajax({
				type : "POST",
				url : webPath + "/securityServcie_getTimeTool.action",
				dataType : "json",
				async : false,
				success : function(data) {
					var timeToolList = data.serviceList;
					var liStr = "";
					$("#service_time_tool_id").html(size);
					if (timeToolList) {
						if (timeToolList.length > 0) {

							currentPeriodNum = timeToolList.length - 1;
							// 隐藏字段 服务周期id初始化数据
							$("#service_period_id").val(
									timeToolList[currentPeriodNum].id);

							// 列表上方 第几周 数据初始化
							$("#service_service_period_id").html(
									"第" + (currentPeriodNum + 1) + "期");
							$("#service_period_total").html(
									"第" + (currentPeriodNum + 1) + "期  问题统计");

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
						$("#service_time_tool_id").append(liStr);
						if (timeToolList[0].info != ""
								&& timeToolList[0] != null) {
							$("#data_box1_footer_nodata").html(
									timeToolList[0].info);
						}
					} else {
						liStr += "<div id='conn_table_hide' class='zanwsj'>暂无服务不实用情况</div>";
						$("#service_time_tool_id").append(liStr);

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
		// animationLoop: false,// "disable" classes at either end 是否循环滚动 循环播放
		itemWidth : 133,
		itemMargin : 1,
		minItems : 2,
		maxItems : 8
	/*
	 * before: function(i){ //获取选中期数 periodNum=i.animatingTo+1;
	 * $("#service_service_period_id").html("第"+periodNum+"期");
	 * $("#service_period_total").html("第"+periodNum+"期"); //获取周期id var
	 * serviceId=$("#service_period_id").val();
	 * 
	 * //服务不实用统计 serviceProblemType(serviceId); //列表查询
	 * serviceProblemPage(serviceId); },
	 * 
	 * after: function(i){}
	 */

	});
}
// 饼图--服务不实用检测进度
function servicePie() {
	var comPercent = [];
	var uncomPercent = [];
	var myChart_line = echarts.init(document.getElementById("service_pie_id"));
	$.ajax({
		type : "POST",
		url : webPath + "/securityServcie_servicePie.action",
		dataType : "json",
		async : false,
		success : function(homeData) {
			if (homeData.errorMsg) {
			} else {
				comPercent.push(homeData.comPercent);
				uncomPercent.push(homeData.uncomPercent);
			}
		},
		error : function(data) {
			// alert(date.errorMsg);
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
				}
			},
			calculable : false,// 取消虚线
			name : '访问来源',
			type : 'pie',
			radius : [ '45%', '65%' ],
			center : [ '50%', '50%' ],
			data : [ {
				value : comPercent,
				name : '已完成\n' + comPercent + '%'
			}, {
				value : uncomPercent,
				name : '未完成\n' + uncomPercent + '%'
			} ]
		} ]
	};
	myChart_line.setOption(option, true);
}
// 服务不实用统计
function serviceProblemType() {
	var serviceId = $("#service_period_id").val();
	$
			.ajax({
				type : "POST",
				url : webPath
						+ "/securityServcie_serviceProblemType.action?servicePdId="
						+ serviceId,
				dataType : "json",
				async : false,
				success : function(data) {
					var problemList = data.returnList;

					// 清空列表数据
					$("#tfoot_serviceProblemType_id").html("");
					$("#tbody_serviceProblemType_id").html("");

					var tbodyLiStr = "";
					// 清空列表数据
					$("#tfoot_serviceProblemType_id").html("");
					if (problemList.length > 0) {
						$("#qusBox").show();// 隐藏暂无数据
						// 列表数据显示
						$("#service_table_id").show();
						// 标题
						tbodyLiStr += "<tr><th class='text-left' width='150px'>序号</th><th class='text-left'>问题类型</th><th width='150px'>问题个数</th></tr>";
						for ( var i = 0; i < problemList.length; i++) {
							if (i == 0) {
								tbodyLiStr += "<tr><td class='text-left'>"
										+ (1) + "</td>"
										+ "<td class='text-left'>"
										+ problemList[0].problemType + "</td>"
										+ "<td>" + problemList[0].serviceSum
										+ "</td></tr>";
							} else if (i == 1) {
								tbodyLiStr += "<tr><td class='text-left'>"
										+ (2) + "</td>"
										+ "<td class='text-left'>"
										+ problemList[1].problemType + "</td>"
										+ "<td>" + problemList[1].serviceSum
										+ "</td></tr>";
							} else if (i == 2) {
								tbodyLiStr += "<tr><td class='text-left'>"
										+ (3) + "</td>"
										+ "<td class='text-left'>"
										+ problemList[2].problemType + "</td>"
										+ "<td>" + problemList[2].serviceSum
										+ "</td></tr>";
							} else {
								tbodyLiStr += "<tr><td class='text-left'>"
										+ (4) + "</td>"
										+ "<td class='text-left'>"
										+ problemList[3].problemType + "</td>"
										+ "<td>" + problemList[3].serviceSum
										+ "</td></tr>";
							}
						}
						$("#tbody_serviceProblemType_id").append(tbodyLiStr);
						var tfootLiStr = "<tr><td class='text-left' colspan='2'>合计</td><td>"
								+ data.problemSum + "</td></tr>";
						$("#tfoot_serviceProblemType_id").append(tfootLiStr);

					} else {
						$("#qusBox").hide();// 暂无数据
						$("#service_table_id").hide();
					}
				},
				error : function(data) {
					// console.log(data.errorMsg);
				}
			});
}
function affairsChange() {
	serviceProblemPage();
}
function worklike() {
	serviceProblemPage();
}


// 列表查询
function serviceProblemPage(id) {
	$("#sizeId").html("");
	var terms = $("#prependedInput").val();
	var servicePdId = $("#scanCycleId option:selected").val();
	var str = "暂无周期！"
	if (servicePdId == str) {
		// 隐藏周期统计数据
		$("#service_service_period_id").addClass("hide");
		// 导出excel隐藏
		$("#table_security_service_excel").addClass("hide");
		// 隐藏整个列表
		$("#table_security_service_id").addClass("hide");
		$("#table_security_service").show();
		$("#table_security_service").html("暂无周期！");
		$(".dropload-load").hide();
	} else {
		$
				.ajax({
					type : "post",
					async : false,
					url : webPath
							+ "/securityServcie_serviceProblemPage.action",
					data : {
						terms : terms,
						servicePdId : servicePdId,
					},
					dataType : "json",
					success : function(serviceData) {
						if (serviceData.success == 'false') {
							// alert(serviceData.errorMsg);
						} else {
							var pageList = serviceData.pageList;
							var liStr = "";
							// 先清空，再添加数据
							$("#tbody_service_list_id").html("");
							if (pageList.length > 0) {
								var size = pageList.length;
								$("#sizeId").html(size);

								// 显示整个列表数据
								$("#table_security_service_id").removeClass(
										"hide");
								// 导出excel显示
								$("#table_security_service_excel").removeClass(
										"hide");
								// 隐藏暂无数据
								// $("#table_security_service").addClass("hide");
								$("#table_security_service").hide();
								$("#table_security_service").html("");
								$(".dropload-load").show();

								for ( var i = 0; i < pageList.length; i++) {
									var siteUrl = checkUrlHTTTP(pageList[i].url);
									var parentUrl = checkUrlHTTTP(pageList[i].parentUrl);
									liStr += "<tr><td class='td_left font_701999'>"
											+ (i + 1) + "</td>";

									if (siteUrl != "") {
										liStr += "<td class='td_left'><a class='url-ellipsis serviceItem' href='"
												+ siteUrl
												+ "'  target='_blank' title='"
												+ pageList[i].serviceItem
												+ "'>";
										if(pageList[i].serviceItem.length>10){
											liStr += (pageList[i].serviceItem.substring(0, 10))
												      + "...</a></td>";	
										}else if(pageList[i].serviceItem == ""){
											liStr += "无</a></td>";		
										}else{
											liStr += (pageList[i].serviceItem.substring(0, 10))
													+ "</a></td>";		
										}
											
									} else {
										/*
										 * liStr += "<td class='td_left'> <div
										 * id ='div" + i + "' class='ztm-con'
										 * style='display:none;'>
										 * <p style='margin-top:10px;'>无地址</p></div>
										 * <a class='url-ellipsis serviceItem'
										 * href='javascript:void(0);'
										 * target='_blank' title='" +
										 * pageList[i].serviceItem + "'
										 * onmouseover=\"mouseOver('" + i +
										 * "');\" onmouseout=\"mouseOut('" + i +
										 * "');\">" + (pageList[i].serviceItem
										 * .substring(0, 10)) + "</a></td>";
										 * onmouseover = \"nameHover('tbody_service_list_id');\"
										 */
										if(pageList[i].serviceItem.length>10){
											liStr += "<td class='td_left'><div  class='info-box lineh-normal'><span><a id ='div" + i + "' class='url-ellipsis serviceItem'  onClick = \"jumpOlick('" + i +"');\" onmouseover=\"mouseOver('" + i +"','" + pageList[i].serviceItem + "');\" href='javascript:void(0);'>"
											+ (pageList[i].serviceItem
													.substring(0, 10))
											+ "...</a></span></div></td>";
										}else if(pageList[i].serviceItem == ""){
											liStr += "无</a></td>";		
										}else{
											liStr += "<td class='td_left'><div  class='info-box lineh-normal'><span><a id ='div" + i + "' class='url-ellipsis serviceItem'  onClick = \"jumpOlick('" + i +"');\" onmouseover=\"mouseOver('" + i +"','" + pageList[i].serviceItem + "');\" href='javascript:void(0);'>"
											+ (pageList[i].serviceItem
													.substring(0, 10))
											+ "</a></span></div></td>";
										}
									

									}

									liStr += "<td class='td_left'><div  title='"
											+ pageList[i].problemTypeName
											+ "'>"
											+ (pageList[i].problemTypeName
													.substring(0, 10))
											+ "</div></td>"
											+ "<td class='td_left'><div title='"
											+ pageList[i].problemDesc
											+ "'>"
											+ (pageList[i].problemDesc
													.substring(0, 10))
											+ "</div></td>"

											+ "<td class='td_left'><a class='url-ellipsis' href='"
											+ parentUrl
											+ "' target='_blank' title='"
											+ parentUrl
											+ "'>"
											+ parentUrl
											+ "</a></td>"
											+ "<td class='td_left'>"
											+ pageList[i].scanDate + "</td>";

									if (pageList[i].imgUrl.match("htm")) {// 快照
										liStr += "<td><i class='kuaiz' onclick=\"getShot('"
												+ pageList[i].imgUrl
												+ "');\"></i></td></tr>";
									} else {// 截图
										imgUrl = pageList[i].imgUrl;
										// liStr += "<td><a target='_blank'
										// href='"
										// + pageList[i].imgUrl
										// + "'><img alt='截图' src='"
										// + webPath
										// +
										// "/images/jiet.png'/></a></td></tr>";
										liStr += "<td><a target='_blank' href='"
												+ "jsp/onlineReport/cutImgs.jsp?imgUrl="
												+ pageList[i].imgUrl
												+ "&litImgUrl="
												+ serviceData.litImgUrl
												+ "'><img alt='截图' src='"
												+ webPath
												+ "/images/jiet.png'/></a></td></tr>";
									}
								}
								$("#table_security_service_id").siblings(
										".dropload-load").hide();
								$("#tbody_service_list_id").append(liStr);
							} else {
								// 隐藏周期统计数据
								$("#service_service_period_id")
										.addClass("hide");
								// 导出excel隐藏
								$("#table_security_service_excel").addClass(
										"hide");
								// 隐藏整个列表
								$("#table_security_service_id")
										.addClass("hide");

								$(".dropload-load").hide();
								if (serviceData.servicePeriodStatus == 1) {
									// 显示暂无数据
									$("#table_security_service").show();
									$("#table_security_service").html(
											"检测中，暂未发现问题！");
								} else if (serviceData.servicePeriodStatus == 2) {
									$("#table_security_service").show();
									$("#table_security_service").html(
											"本周期未发现服务不实用情况！");
								} else if (serviceData.servicePeriodStatus == 0) {
									$("#table_security_service").show();
									$("#table_security_service").html(
											"检测中，暂未发现问题！");
								}

							}
						}
					},
					error : function(data) {
						// console.log(data.errorMsg);
					}
				});
	}
}

// 服务不实用柱状图
function serviceBar() {

	var typeOne = [];
	var typeTwo = [];
	var typeThree = [];
	var typeFour = [];
	var periodList = [];
	// 基于准备好的dom，初始化echarts图表
	var myChart_bar = echarts.init(document
			.getElementById("service_unuse_bar_id"));
	$.ajax({
		type : "post",
		async : false,
		url : webPath + "/securityServcie_serviceProblemBar.action",
		dataType : "json",
		success : function(data) {
			var list = data.periodList;
			typeOne = data.typeOne;
			typeTwo = data.typeTwo;
			typeThree = data.typeThree;
			typeFour = data.typeFour;
			if (list != null) {
				for ( var i = 0; i < list.length; i++) {
					periodList.push("第" + list[i] + "期");
				}
			}

		}
	});
	option = {
		dataZoom : {
			show : true,
			realtime : true,
			height : 20,
			start : 100,
			end : 40
		},
		legend : {
			x : 'right',
			y : '30',
			itemGap : 30,
			padding : 40,
			data : [ '办事指南要素缺失', '办事指南要素不准确', '附件未提供下载', '附件无法下载' ]
		},
		tooltip : {
			trigger : 'axis',
			borderColor : '#48b',
			axisPointer : {
				color : 'rgba(150,150,150,0.3)',
				width : 'auto',
				type : 'default'
			},
			formatter : "{b}<br>{a}：  {c}个<br>{a1}：  {c1}个<br>{a2}：  {c2}个<br>{a3}：  {c3}个",
		},
		calculable : true,
		grid : {
			borderColor : '#fff',
			x : 50,
			y : 50,
			x2 : 50,
			y2 : 50
		},
		xAxis : [ {
			axisLine : false,
			splitLine : false,
			axisTick : false,
			type : 'category',
			data : periodList
		} ],
		yAxis : [ {
			name : '问题个数（个）',
			nameTextStyle : {
				color : 'black'
			},
			axisLine : {
				lineStyle : {
					color : '#ffffff',
					width : 1
				}
			},
			type : 'value'
		} ],
		series : [ {
			name : '办事指南要素缺失',
			type : 'bar',
			barWidth : 30,
			data : typeOne,
			stack : 'sum',
			itemStyle : {
				normal : {
					color : '#66c3ef',
					barBorderColor : '#66c3ef',
					barBorderWidth : 6,
					barBorderRadius : 0,
					label : {
						show : true,
						position : 'insideTop'
					}
				}
			},
		}, {
			name : '办事指南要素不准确',
			type : 'bar',
			barWidth : 30,
			data : typeTwo,
			stack : 'sum',
			itemStyle : {
				normal : {
					color : '#78cd50',
					barBorderColor : '#78cd50',
					barBorderWidth : 6,
					barBorderRadius : 0,
					label : {
						show : true,
						position : 'insideTop'
					}
				}
			}
		}, {
			name : '附件未提供下载',
			type : 'bar',
			barWidth : 30,
			data : typeThree,
			stack : 'sum',
			itemStyle : {
				normal : {
					color : '#fbbb3c',
					barBorderColor : '#fbbb3c',
					barBorderWidth : 6,
					barBorderRadius : 0,
					label : {
						show : true,
						position : 'insideTop'
					}
				}
			}
		}, {
			name : '附件无法下载',
			type : 'bar',
			barWidth : 30,
			data : typeFour,
			stack : 'sum',
			itemStyle : {
				normal : {
					color : '#ff625e',
					barBorderColor : '#ff625e',
					barBorderWidth : 6,
					barBorderRadius : 0,
					label : {
						show : true,
						position : 'insideTop'
					}
				}
			}
		},

		]
	};
	// 为echarts对象加载数据
	myChart_bar.setOption(option, true);
}
// 快照页面跳转
function getShot(imgUrl) {
	window.open(imgUrl);
}

function jumpOlick(id){
	$("#div"+id).prop('title', '无地址');  
}

function mouseOver(id,url){
	$("#div"+id).prop('title', url);  
}

