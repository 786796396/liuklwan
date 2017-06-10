var flag = 1; // 1 组织单位付费跳转过来   2 填报单位登录 付费的  3 填报单位登录 已过期的
$(function() {
	// 有  三种状态   付费执行中    已过期    未检测   // 组织单位  跳转过来的状态
	
	// 组织单位  付费点击过来  所有的数据都可以查看（只有组织付费的开通此业务的 组织单位才可以点击跳转过来）
	if(orgToInfo == 1 && isOrgSafetyService==1 ){
		flag = 1;
		initClick();
		initData();
		initDate();
	}

	// 对于填报单位自己的  付费的数据处理  有正在运行的  安全任务
	if(orgToInfo != 1 && isSafetyService==1 ){
		flag = 2;
		initClick();
		initData();
		initDate();
	}
	// 对于过期的数据处理
	if(orgToInfo != 1 && isSafetyService==2 ){
		flag = 3;
		initClick();
		initData();
		initDate();
	}
	// 对于未开通的数据处理
});

// 列表查询
function initData() {
	var user = {
			flag:flag
			};
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResult_getMonthDatas.action",
		dataType : "json",
		data:user,
		async : false,
		success : function(data) {
			if (data.success=='true') {
				getFeng(data.risk);
				getFengQu(data.risklist,data.scanTimelist);
				$("#scanDate").html(data.scanDate);
				$("#weak").html(data.weak);
				$("#horse").html(data.horse);
				$("#update").html(data.update);
				$("#dark").html(data.dark);
				$("#out").html(data.out);
				getQuesQu(data.scanTimelist,data.weaklist,data.horselist,data.updatelist,data.darklist,data.outlist);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
function getQuesQu(scanTimelist,weaklist,horselist,updatelist,darklist,outlist){
	var myChart = echarts.init(document.getElementById('quesQu'));
	option = {
		backgroundColor:'#fff',
	    title: {
	        text: '问题数（个）'
	    },
	    tooltip: {
	        trigger: 'axis'
	       
	    },
	    legend: {
	        x:'right',
	        padding: [10,24] ,
	        textStyle:{
	            color: '#62656d',
	            fontSize:12
	        },
	        data:['网站脆弱性','网站挂马','变更/篡改','网站暗链','网站泄露']
	    },
	    toolbox: {
	            left: 'center',
	            feature: {
	                dataZoom: {
	                    yAxisIndex: 'none'
	                }
	            }
	        },

	     dataZoom : {
	            handleColor:'#008acd',
	            fillerColor:'#e9e5f2',
	            dataBackgroundColor:'#008acd',
	            backgroundColor:'#f7f7f7',
	            show : true,
	            realtime : true,
	            height: 20,
	            start : 100,
	            end : 0
	        },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '12%',
	        containLabel: true
	    },
	    toolbox: {
	        // feature: {
	        //     saveAsImage: {}
	        // }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisLine:{
	                lineStyle:{
	                    color:'#e4e4e4'
	                }
	        },
	        axisLabel:{
	            textStyle:{
	                color: '#333'
	            } 
	        },
	        data: scanTimelist//['周一','周二','周三','周四','周五','周六','周日']
	    },
	    yAxis: {
	        axisLine:{
	                lineStyle:{
	                    color:'#fff'
	                }
	        },
	        axisLabel:{
	            textStyle:{
	                color: '#333'
	            } 
	        },
	        type: 'value'
	    },
	    series: [
	        {
	            name:'网站脆弱性',
	            type:'line',
	            //stack: '总量',
	            itemStyle: {
	                normal: {
	                    color:'#9c28b1',
	                    lineStyle:{
	                        color:'#9c28b1'
	                    }
	                }
	            },
	            data:weaklist//[120, 132, 101, 134, 90, 230, 210]
	        },
	        {
	            name:'网站挂马',
	            type:'line',
	           // stack: '总量',
	            itemStyle: {
	                normal: {
	                    color:'#ea1e63',
	                    lineStyle:{
	                        color:'#ea1e63'
	                    }
	                }
	            },
	            data:horselist//[220, 182, 191, 234, 290, 330, 310]
	        },
	        {
	            name:'变更/篡改',
	            type:'line',
	           // stack: '总量',
	            itemStyle: {
	                normal: {
	                     color:'#607d8b',
	                    lineStyle:{
	                        color:'#607d8b'
	                    }
	                }
	            },
	            data:updatelist//[150, 232, 201, 154, 190, 330, 410]
	        },
	        {
	            name:'网站暗链',
	            type:'line',
	            //stack: '总量',
	            itemStyle: {
	                normal: {
	                    color:'#9e9e9e',
	                    lineStyle:{
	                        color:'#9e9e9e'
	                    }
	                }
	            },
	            data:darklist//[320, 332, 301, 334, 390, 330, 320]
	        },
	        {
	            name:'网站泄露',
	            type:'line',
	           // stack: '总量',
	            itemStyle: {
	                normal: {
	                    color:'#795547',
	                    lineStyle:{
	                        color:'#795547'
	                    }
	                }
	            },
	            data:outlist//[820, 932, 901, 934, 1290, 1330, 1320]
	        }
	    ]
	};
	    myChart.setOption(option, true);
}

function getFengQu(risklist,scanTimelist){
	var myChart = echarts.init(document.getElementById('fengQu'));
	option = {
		backgroundColor:'#fff',
	    title: {
	        text: '风险趋势'
	    },
	    tooltip: {
	        trigger: 'axis'
	       
	    },
//	    legend: {
//	        x:'right',
//	        padding: [10,24] ,
//	        textStyle:{
//	            color: '#62656d',
//	            fontSize:12
//	        },
//	        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
//	    },
	    toolbox: {
	            left: 'center',
	            feature: {
	                dataZoom: {
	                    yAxisIndex: 'none'
	                }
	            }
	        },

	     dataZoom : {
	            handleColor:'#008acd',
	            fillerColor:'#e9e5f2',
	            dataBackgroundColor:'#008acd',
	            backgroundColor:'#f7f7f7',
	            show : true,
	            realtime : true,
	            height: 20,
	            start : 100,
	            end : 0
	        },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '15%',
	        containLabel: true
	    },
	    toolbox: {
	        // feature: {
	        //     saveAsImage: {}
	        // }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisLine:{
	                lineStyle:{
	                    color:'#e4e4e4'
	                }
	        },
	        axisLabel:{
	            textStyle:{
	                color: '#333'
	            } 
	        },
	        data: scanTimelist//['周一','周二','周三','周四','周五','周六','周日']
	    },
	    yAxis: {
	        axisLine:{
	                lineStyle:{
	                    color:'#fff'
	                }
	        },
	        axisLabel:{
	            textStyle:{
	                color: '#333'
	            } 
	        },
	        type: 'value'
	    },
	    series: [
	        {
	            name:'风险值',
	            type:'line',
	           // stack: '总量',
	            itemStyle: {
	                normal: {
	                    color:'#795547',
	                    lineStyle:{
	                        color:'#795547'
	                    }
	                }
	            },
	            data:risklist//[820, 932, 901, 934, 1290, 1330, 1320]
	        }
	    ]
	};
	    myChart.setOption(option, true);
}

function getFeng(feng){
	var myChart = echarts.init(document.getElementById('feng'));
	option = {
		backgroundColor:'#fff',
	    tooltip : {
	    	show:false,
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    toolbox: {
	        feature: {
	           // restore: {},
	            //saveAsImage: {}
	        }
	    },

	    series: [
	        {
	            name: '业务指标',
	            type: 'gauge',
	            radius: '100%',
	            detail: {
	            	formatter:'{value}',
	            	 offsetCenter: [0, '60%']
	        	},
	            data: [{value: 50, name: '风险值'}],
	            title : {
	            	show : true,
	    			offsetCenter: [0, '-30%'],
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontSize: 20,
	                   	//fontStyle: '微软雅黑',
	                    color: '#34495e',
	                    shadowColor : '#34495e'//, //默认透明
	                    //shadowBlur: 10
	                }
	            },
	            axisLine:{
			    show: true,
			    lineStyle: {
			        color: [
			            [0.2, '#ffeb3c'],
			            [0.8, '#ff9700'],
			            [1, '#f44236']
			        ],
			        width: 24
			    }
	    },
	        }
	    ]
	};
	    option.series[0].data[0].value = feng;
	    myChart.setOption(option, true);

}
// 网站脆弱性  6 中类型
var types =1;
// 网站泄露  2 中 类型
var ltypes =1;
// 6中类型中  表头有差别
var isShows=2;
// 一级表头
var titleOne = 1;
//
//excel导出
function questionDetailExcel() {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(titleOne == 1){
		window.location.href = webPath+ "/monitorSilentResult_getExcel.action?startDate="+startDate+"&endDate="+endDate+"&titleOne="+titleOne+"&type="+types+"&isShow="+isShows ;
	}else if(titleOne == 2){
		window.location.href = webPath+ "/monitorSilentResult_getExcel.action?startDate="+startDate+"&endDate="+endDate+"&titleOne="+titleOne ;
	}else if(titleOne == 3){
		window.location.href = webPath+ "/monitorSilentResult_getExcel.action?startDate="+startDate+"&endDate="+endDate+"&titleOne="+titleOne ;
	}else if(titleOne == 4){
		window.location.href = webPath+ "/monitorSilentResult_getExcel.action?startDate="+startDate+"&endDate="+endDate+"&titleOne="+titleOne ;
	}else if(titleOne == 5){
		window.location.href = webPath+ "/monitorSilentResult_getExcel.action?startDate="+startDate+"&endDate="+endDate+"&titleOne="+titleOne+"&type="+ltypes ;
	}
}
// 时间插件 点击后执行的 查询
function getCheckDatas(){
	if(titleOne == 1){
		getWeaks(types,isShows);
	}else if(titleOne == 2){
		getHorse();
	}else if(titleOne == 3){
		getUpdateDatas();
	}else if(titleOne == 4){
		getDarkDatas();
	}else if(titleOne == 5){
		getLeakDatas(ltypes);
	}
}

function initClick(){
	$(".clickAct").click(function(){
		$("#title1").find("li").removeClass("active");
		$(this).parent().addClass('active');
		var num = $(this).attr("num");
		if(num == 1){
			titleOne = 1;
			$(".single-Part").show();
			$("#numFirst").addClass('active');
			$("#numFive").removeClass('active');
			getWeaks(1,2);
			//initTitle2();
		}else if(num == 2){
			titleOne = 2;
			$(".single-Part").hide();
			getHorse();
		}else if(num == 3){
			titleOne = 3;
			$(".single-Part").hide();
			getUpdateDatas();
		}else if(num == 4){
			titleOne = 4;
			$(".single-Part").hide();
			getDarkDatas();
		}else if(num == 5){
			titleOne = 5;
			$(".single-Part").show();
			$("#numFive").addClass('active');
			$("#numFirst").removeClass('active');
			ltypes=1;
			getLeakDatas(1);
		}
		
	});
	
	
	types =1;
	isShows=2;
	titleOne = 1;
	initTitle2();
	initTitle3();
	//$("#isShow").hide();
	getWeaks(1,2);
}
// 网站泄露
function getLeakDatas(leakData){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var user = {
			flag:flag,
			startDate:startDate,
			type:leakData,
			endDate:endDate
			};
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResult_getLeakDatas.action",
		dataType : "json",
		data:user,
		async : false,
		success : function(data) {
			if (data.success=='true') {
				var datalist = data.datalist;
				var html="";
				var titleHt="";
				titleHt+="<th style=\"width: 5%;\">序号</th>"+
				"<th style=\"width: 10%;\">级别</th>"+
                "<th style=\"width: 20%;\" class=\"th-left\">问题URL</th>"+
                "<th style=\"width: 15%;\">匹配字符</th>"+
                "<th style=\"width: 15%;\">扫描时间</th>";
				$("#thead").html(titleHt);
				$("#sumCount").html(datalist.length);
				for(var i=0;i<datalist.length;i++){
					var base = datalist[i];
					 html+="<tr>"+
	                 "<td style=\"width: 5%;\" class=\"td-center\">"+(i+1)+"</td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+mapLevel[base.level]+"</td>"+
	                 "<td style=\"width: 20%;\"class=\"td-left\"><a href=\"javascript:void(0);\" style=\"color: #2899df; word-break: break-all;\">"+base.purl+"</a></td>"+
	                 "<td style=\"width: 15%;\" class=\"td-center\">"+base.matchstr+"</td>"+
	                 "<td style=\"width: 15%;\" class=\"td-center\">"+base.stime+"</td></tr>";
				}
				$("#tbody").html(html);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
//网站暗链
function getDarkDatas(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	var user = {
			flag:flag,
			startDate:startDate,
			endDate:endDate
			};
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResult_getDarkDatas.action",
		dataType : "json",
		data:user,
		async : false,
		success : function(data) {
			if (data.success=='true') {
				var datalist = data.datalist;
				var html="";
				var titleHt="";
				titleHt+="<th style=\"width: 5%;\">序号</th>"+
				"<th style=\"width: 10%;\">级别</th>"+
                "<th style=\"width: 20%;\" class=\"th-left\">问题URL</th>"+
                "<th style=\"width: 15%;\">扫描时间</th>";
				$("#thead").html(titleHt);
				$("#sumCount").html(datalist.length);
				for(var i=0;i<datalist.length;i++){
					var base = datalist[i];
					 html+="<tr>"+
	                 "<td style=\"width: 5%;\" class=\"td-center\">"+(i+1)+"</td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+mapLevel[base.level]+"</td>"+
	                 "<td style=\"width: 20%;\"class=\"td-left\"><a href=\"javascript:void(0);\" style=\"color: #2899df; word-break: break-all;\">"+base.purl+"</a></td>"+
	                 "<td style=\"width: 15%;\" class=\"td-center\">"+base.stime+"</td></tr>";
				}
				$("#tbody").html(html);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
//变更 
function getUpdateDatas(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	var user = {
			flag:flag,
			startDate:startDate,
			endDate:endDate
			};
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResult_getUpdateDatas.action",
		dataType : "json",
		data:user,
		async : false,
		success : function(data) {
			if (data.success=='true') {
				var datalist = data.datalist;
				var html="";
				var titleHt="";
				titleHt+="<th style=\"width: 5%;\">序号</th>"+
                "<th style=\"width: 20%;\" class=\"th-left\">问题URL</th>"+
                "<th style=\"width: 15%;\">扫描时间</th>";
				$("#thead").html(titleHt);
				$("#sumCount").html(datalist.length);
				for(var i=0;i<datalist.length;i++){
					var base = datalist[i];
					 html+="<tr>"+
	                 "<td style=\"width: 5%;\" class=\"td-center\">"+(i+1)+"</td>"+
	                 "<td style=\"width: 20%;\"class=\"td-left\"><a href=\"javascript:void(0);\" style=\"color: #2899df; word-break: break-all;\">"+base.purl+"</a></td>"+
	                 "<td style=\"width: 15%;\" class=\"td-center\">"+base.stime+"</td></tr>";
				}
				$("#tbody").html(html);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
//网站挂马
function getHorse(){
	//$("#isShow").show();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	var user = {
			flag:flag,
			startDate:startDate,
			endDate:endDate
			};
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResult_getHorses.action",
		dataType : "json",
		data:user,
		async : false,
		success : function(data) {
			if (data.success=='true') {
				var datalist = data.datalist;
				var html="";
				var titleHt="";
				titleHt+="<th style=\"width: 5%;\">序号</th>"+
                "<th style=\"width: 10%;\">级别</th>"+
                "<th style=\"width: 10%;\">漏洞类型</th>"+
                "<th style=\"width: 20%;\" class=\"th-left\">问题URL</th>"+
                "<th style=\"width: 10%;\">方法</th>"+
                "<th style=\"width: 15%;\">参数</th>"+
                "<th style=\"width: 15%;\">扫描时间</th>";
				$("#thead").html(titleHt);
				$("#sumCount").html(datalist.length);
				for(var i=0;i<datalist.length;i++){
					var base = datalist[i];
					 html+="<tr>"+
	                 "<td style=\"width: 5%;\" class=\"td-center\">"+(i+1)+"</td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+mapLevel[base.level]+"</td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+mapType[base.loopholeType]+" </td>"+
	                 "<td style=\"width: 20%;\"class=\"td-left\"><a href=\"javascript:void(0);\" style=\"color: #2899df; word-break: break-all;\">"+base.purl+"</a></td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+base.method+"</td>"+
					 "<td style=\"width: 15%;\" class=\"td-center\">"+base.param+"</td>"+
					 "<td style=\"width: 15%;\" class=\"td-center\">"+base.stime+"</td></tr>";
				}
				$("#tbody").html(html);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
// 网站脆弱性
var mapLevel={
    1:'低级',
    2:'高级'
};
var mapType={
    1:'配置风险',
    2:'信息泄露'
};
// isShow 代表  sql 注入  表头的变化
function getWeaks(type,isShow){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	var user = {
			flag:flag,
			startDate:startDate,
			endDate:endDate,
			type:type
			};
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResult_getWeaks.action",
		dataType : "json",
		data:user,
		async : false,
		success : function(data) {
			if (data.success=='true') {
				var datalist = data.datalist;
				var html="";
				var titleHt="";
				
				titleHt+="<th style=\"width: 5%;\">序号</th>"+
                "<th style=\"width: 10%;\">级别</th>"+
				"<th style=\"width: 10%;\">漏洞类型</th>"+
				"<th style=\"width: 20%;\" class=\"th-left\">问题URL</th>"+
				"<th style=\"width: 10%;\">方法</th>";
				if(isShow == 1 ){
					titleHt+="<th style=\"width: 15%;\" >参数</th>";
				}
				titleHt+="<th style=\"width: 15%;\">扫描时间</th>";
				$("#thead").html(titleHt);
				$("#sumCount").html(datalist.length);
				for(var i=0;i<datalist.length;i++){
					var base = datalist[i];
					 html+="<tr>"+
	                 "<td style=\"width: 5%;\" class=\"td-center\">"+(i+1)+"</td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+mapLevel[base.level]+"</td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+mapType[base.loopholeType]+" </td>"+
	                 "<td style=\"width: 20%;\"class=\"td-left\"><a href=\"javascript:void(0);\" style=\"color: #2899df; word-break: break-all;\">"+base.purl+"</a></td>"+
	                 "<td style=\"width: 10%;\" class=\"td-center\">"+base.method+"</td>";
					 if(isShow == 1 ){
						 html+="<td style=\"width: 15%;\" class=\"td-center\">"+base.param+"</td>";
					 }
	                 html+="<td style=\"width: 15%;\" class=\"td-center\">"+base.stime+"</td></tr>";
				}
				$("#tbody").html(html);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}



function initTitle2(){
	$(".title2").click(function(){
		$("#titleLi2").find("li").removeClass("active");
		$(this).parent().addClass('active');
		var weakType = $(this).attr("type");
		types = weakType;
		if(weakType == 1 || weakType == 5){
			//$("#isShow").hide();
			isShows = 2;
			getWeaks(weakType,2);
		}else{
			isShows =1;
			//$("#isShow").show();
			getWeaks(weakType,1);
		}
	});
}

function initTitle3(){
	$(".title3").click(function(){
		$("#titleLi3").find("li").removeClass("active");
		$(this).parent().addClass('active');
		var leakType = $(this).attr("type");
		leakTypes = leakType;
		ltypes=leakType;
		getLeakDatas(leakType);
	});
}



function initDate(){
	$("#startDate").datepicker(
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
					getCheckDatas();
				}
			});

	$("#endDate").datepicker(
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
					getCheckDatas();
				}
			});
}

