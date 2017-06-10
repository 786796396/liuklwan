var channeldataArray = [];
var sumNum;
var previousDate;
var nextDate;
$(function() {

	// 饼图
	contentPie('');
	// 折线图
	contentLine();
	// 列表
	// homeList();

});

function contentPiePrevious() {
    if(!$("#previousHref").hasClass("flex-disabled")) {
        contentPie(previousDate);
    }
}

function contentPieNext() {
    if(!$("#nextHref").hasClass("flex-disabled")) {
        contentPie(nextDate);
    }
}

function loadChannelData(scanDate) {
	channeldataArray = [];
	// 饼图
	$
			.ajax({
				type : "POST",
				url : webPath + "/updateContentAnalyze_loadContentCount.action",
				dataType : "json",
				data : {
					'scanDate' : scanDate
				},
				async : false,
				success : function(data) {
					sumNum = data.sumNum;
					if (data.isHasPrevious == false) {
						$("#previousHref").addClass("flex-disabled");
					} else {
						$("#previousHref").removeClass("flex-disabled");
					}

					if (data.isHasNext == false) {
						$("#nextHref").addClass("flex-disabled");
					} else {
						$("#nextHref").removeClass("flex-disabled");
					}

					previousDate = data.previousDate;
					nextDate = data.nextDate;


					$("#sumNum").empty();
					$("#scan_date").empty();
					$("#sumNum").append(sumNum);
					$("#scan_date").append(data.scanDate);
					$("#sumDisplay").empty();

				//alert(eval(data.listChannel).length);
				
				    var channelLength= eval(data.listChannel).length;
				   
				    if(channelLength<7){
				    	$("#sumDisplay").append("共更新"+channelLength+"类信息，覆盖分类不足50%");
				    }else{
				    	$("#sumDisplay").append("共更新"+channelLength+"类信息，覆盖分类超过50%");
				    }

					if (data.listChannel == '' || sumNum == 0) {
						$("#tbodyChannel").empty();
						var tHead = "<tr><th class='th_left' style='width:200px';>栏目分类</th><th>更新个数</th><th>占比</th></tr>";
						$("#tbodyChannel").append(tHead);
						$("#sumProportion").empty();
						$("#sumProportion").append(0);
						return;
					}

					$("#sumProportion").empty();
					$("#sumProportion").append('100%');

					$("#tbodyChannel").empty();
					var tHead = "<tr><th class='th_left' style='width:200px';>栏目分类</th><th>更新个数</th><th>占比</th></tr>";
					$("#tbodyChannel").append(tHead);

					$.each(data.listChannel, function(index, channelData) {
						channeldataArray.push({
							name : channelData.channelName,
							value : channelData.proportion
						});

						// $("#tbodyChannel").empty();

						// var tHead = "<tr><th class='th_left'
						// style='width:200px';>栏目分类</th><th>更新条数</th><th>占比</th></tr>";

						$("#tbodyChannel").append(
								" <tr><td class='td_left'>"
										+ channelData.channelName + "</td><td>"
										+ channelData.updateNum + "</td><td>"
										+ channelData.proportion + '%'
										+ "</td></tr>");
					});

				}
			});

}

function contentPie(scanDate) {

	loadChannelData(scanDate);
	var myChart_bar = echarts.init(document.getElementById('content_pie'));
	var labelBottom = {
		normal : {
			label : {
				show : true,
				position : 'center'
			},
			labelLine : {
				show : true
			}
		},
		emphasis : {
			color : 'rgba(0,0,0,0)'
		}
	};
	option = {

			 title: {
			        text: sumNum +"个",
			        subtext: '信息更新每日统计',
			        x: 'center',
			        y: 'center',
			        itemGap: 20,
			        textStyle : {
			            color : 'rgba(30,144,255,0.8)',
			            fontFamily : '微软雅黑',
			            fontSize : 35,
			            fontWeight : 'bolder'
			        }
			    },
		calculable : true,
		series : [ {
			name : '访问来源',
			type : 'pie',
			radius : [ '50%', '70%' ],
			itemStyle : {
				normal : {
					label : {
						show : true,
						textStyle : {
							fontSize : 14
						},
						formatter : '{b} \n{c}%'
					},
					labelLine : {
						show : true
					}
				}

				/*emphasis : {
					// color: 'rgba(0,0,0,0)',
					label : {
						show : true,
						position : 'center',
						formatter : sumNum + "条\n 信息更新，每日统计",
						textStyle : {
							fontSize : '20',
							fontWeight : 'bold'
						}
					}
				}*/
			},
			data : channeldataArray,
			calculable : false
		} ]
	};
	myChart_bar.setOption(option, true);

}

function contentLine() {
	// 折线图
	var myChart_line = echarts.init(document.getElementById('content_line'));
	var updateNum = new Array();
	var updateDate = new Array();
	$.ajax({
		type : "POST",
		url : webPath + "/updateContentAnalyze_getUpdateContentLine.action",
		// data : "content=" + content,
		dataType : "json",
		async : false,
		success : function(data) {
			for ( var int = 0; int < data.length; int++) {
				updateNum.push(data[int].updateNum);
				updateDate.push(data[int].scanDate);

			}
		}
	});
	option = {
		color : [ '#BAA7E3' ],
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				color : 'rgba(150,150,150,0.3)',
				width : 'auto',
				type : 'default'
			},
			 padding:15,
			 backgroundColor: 'rgba(0,0,0,0.5)',
             textStyle : {
		           fontSize: 13
		           },
			 formatter: function (params,ticket,callback) {
                 var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
                 for (var i = 0, l = params.length; i < l; i++) {
                     res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'个';
                 }
                 setTimeout(function (){
                     // 仅为了模拟异步回调
                     callback(ticket, res);
                 }, 0);
             },

			grid : {
				y : 20
			}
		},
		dataZoom : {
			show : true,
			realtime : true,
			height : 20,
			start : 100,
			end : 40
		},
		xAxis : [ {
			type : 'category',
			axisLine : false,
			splitLine : false,
			axisTick : false,
			boundaryGap : false,
			data : updateDate
		} ],
		yAxis : [ {
			name : '更新个数\n',
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
		grid:{
  	    	borderColor:'#fff',
  	    	x:33,
  	    	x2:10
  	    },
		series : [ {
			symbol : 'emptyCircle',
			symbolSize : 4,
			name : '更新个数',
			type : 'line',
			data : updateNum
		} ],
		calculable : false
	};
	myChart_line.setOption(option, true);
}