$(function() {
	// 隐藏暂无数据
	$("#monitor_SilentOrg_hide").hide();
	if(isOrgSafetyService != 0){
		monthDataOrg();
	}
	
	//使用它要在加载数据那里清下数据缓存
	/*$('table').tablesorter({          
		widgets        : ['zebra'],   // 参数是增加表格隔行变色
		sortList: [                   
		           [0,0]			  // 默认排序方式第一个是列，每二个0是降序1是升序
		],
		usNumberFormat : false,
		sortReset      : false,        //  排序重置
		sortRestart    : false,
	 	headers : {
	 		0 : {sorter:false},  //  指定某一行没有排序
			1 : {sorter:false}  //  指定某一行没有排序
		} 
	}); */
});

function optSelect() {
	monthDataOrg();
}
// 列表查询    
function monthDataOrg() {
	var key = $("#keyId").val();
	var res = $("#selId option:selected").val();
	$.ajax({
		type : "POST",
		url : webPath + "/monitorSilentResultOrg_getMonthDatasOrg.action",
		data:{terms:key, orgType:isOrgSafetyService, res:res},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.success=='true') {
				// 清空表数据
				$("#monitor_SilentOrg_tbody").html("");
				var list = data.list;
				if(data.size != 0 ){
					var html = "";
					// 显示列表数据
					$("#monitor_SilentOrg_tbody").show();
					// 隐藏暂无数据
					$("#monitor_SilentOrg_hide").hide();
					$.each(list, function(index, obj) {
						var aHtml = "";
	            		if(isOrgSafetyService == 0){  // 0 未监测  1 进行中 2 已过期
	            			aHtml = '<td class="td-left" style="width:2%;"><font color="#08c" class="codes" >'+obj.siteCode+'</font></td>';
	            		}else if(isOrgSafetyService == 1){
	            			aHtml = '<td class="td-left" style="width:2%;"><a onclick=\"jump();\" class="sort-num" title="'+obj.titleName+'" target="_blank"  href="'+webPath+'/monitorSilentResult_indexTB.action?siteCode='+obj.siteCode+'"><font color="#08c" class="codes" >'+obj.siteCode+'</font></a></td>';
	            		}else if(isOrgSafetyService == 2){
	            			aHtml = '<td class="td-left" style="width:2%;"><font color="#08c" class="codes" >'+obj.siteCode+'</font></td>';
	            		}
						
						html+="<tr>";   
						html+= aHtml;//标识码
						html+='<td class="td-left " style="width:10%;" title="'+obj.titleName+'"><a target="_blank" href="'+obj.url+'"><font color="'+obj.fontColor+'" class="wz-name">'+obj.name+'</font></td>';//名称
						html+='<td class="td-center" style="width:5%;" ><font color="#08c">'+obj.riskNum+'</font></td>';//风险值
						html+='<td class="td-center" style="width:5%;" ><font color="#08c">'+obj.fragilityNum+'</font></td>';//脆弱性问题数
						html+='<td class="td-center" style="width:5%;" ><font color="#08c">'+obj.trojanNum+'</font></td>';//挂马问题数
						html+='<td class="td-center" style="width:5%;" ><font color="#08c">'+obj.tamperNum+'</font></td>';//变更/篡改问题数
						html+='<td class="td-center" style="width:5%;" ><font color="#08c">'+obj.darkNhainNum+'</font></td>';//网站暗链数
						html+='<td class="td-center" style="width:5%;" ><font color="#08c">'+obj.leakedNum+'</font></td>';//内容泄漏数
		          		html+='</tr>';
						});
					
					$("#monitor_SilentOrg_tbody").append(html);
				}else {
					// 隐藏列表数据
					$("#monitor_SilentOrg_tbody").hide();
					// 显示暂无数据
					$("#monitor_SilentOrg_hide").show();
				}
				getFeng(data.risk);
				getFengQu(data.risklist,data.scanTimelist);
				$("#scanDate").html(data.scanDate);
				$("#weak").html(data.weak);
				$("#horse").html(data.horse);
				$("#update").html(data.update);
				$("#dark").html(data.dark);
				$("#out").html(data.out);
				
				$("#weakSite").html(data.weakSite);
				$("#horseSite").html(data.horseSite);
				$("#updateSite").html(data.updateSite);
				$("#darkSite").html(data.darkSite);
				$("#outSite").html(data.outSite);
				getQuesQu(data.scanTimelist,data.weaklist,data.horselist,data.updatelist,data.darklist,data.outlist);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});

//	$("table").trigger("update");  //  tablesorter清除数据缓存
	
	//列表排序
	new TableSorter("monitor_SilentOrg_tbody",2,3,4,5,6,7);   
	$("#monitor_SilentOrg_tbodySort th").on('click', function(event){
		
		if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
			
			$("#monitor_SilentOrg_tbody .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_top");
			$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		}else{
			$("#monitor_SilentOrg_tbody .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_bottom");
			$(this).find(".tab_angle").removeClass("tab_angle_top");
		}
	  });
	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
		 if(searchInfo==""){
			 $("#monitor_SilentOrg_tbody tr").show();
		 }else{
			 $("#monitor_SilentOrg_tbody").find(".wz-name").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().parent().parent().find(".codes").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });
	
}

function jump(){
	var  url = webPath + "/monitorSilentResult_indexTB.action";
	changeCookie(2,url,null,null);
}
function getQuesQu(scanTimelist,weaklist,horselist,updatelist,darklist,outlist){
	var myChart = echarts.init(document.getElementById('quesQu'));
	option = {
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
	            stack: '总量',
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
//excel导出
function monitorExcle() {
	var key = $("#keyId").val();
	var res = $("#selId option:selected").val();
	window.location.href = webPath
			+ "/monitorSilentResultOrg_monitorExcle.action?terms=" + key + "&res=" + res;
}