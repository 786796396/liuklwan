var channeldataArray = [];
var sumNum;
var previousDate;
var previousDates;
var nextDates;
var nextDate;
var scanDates;
$(function() {
	$("#selectOrg").change(function(){
		var val=$(this).val();
		$("#level").val(val);
		contentPie(scanDates,val,type);
	});
	//列表
	loadChannelDataList('',type);
	
	// 饼图
	contentPie('','0',type);
	//折线图
	contentOrgLine(type,userName);

});
//折线图
function contentOrgLine(type,userName){
	if(type==1){//省级网站登录
		shengline(type,userName);
	}else if(type==2){//市级网站登录
		shiline(type,userName);
	}else if(type==3){//县级网站登录
		xianline(type,userName);
	}
}
//市级网站登录
function shiline(type,userName){
	setTimeout(function(){
		var legendName = [userName,'市政府部门网站','区县政府网站'];
		var datalist = new Array();
		var shiPortalslist = new Array();
		var shiDepartmentlist = new Array();
		var xianlist = new Array();
		 $.ajax( {  
	         type : "POST",  
	         url : webPath+"/updateContentAnalyze_contentOrgLine.action",  
	         data : "type=" + type,  
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	        	 datalist=data.datalist;
	        	 shiPortalslist=data.shiPortalslist;
	        	 shiDepartmentlist=data.shiDepartmentlist;
	        	 xianlist=data.xianlist;
	         }
	      });
		var myChart = echarts.init(document.getElementById('contentOrgLine'));
	    option = {
	    		//color:['#32BB9D','#6ACC5B','#F3B94D','#4B85D9'],
	    		//#eb7070   #3cbb9d  #64cf4e  #f7b844  #4e88dc
	    		color:['#3cbb9d','#64cf4e','#f7b844','4e88dc'],
				dataZoom : {
					show : true,
					realtime : true,
					height: 20,
					start : 100,
					end : 80
				},
	    	    legend: {
	    	    	 x: 'right',
	    	    	 y: '30',
	    	    	 padding:40,
	    	    	 itemGap:30,
	    	        data:legendName
	    	    },
	 		    tooltip : {
	 		        trigger: 'axis',
		 		    borderColor: '#48b',
		            axisPointer: {
		            color: 'rgba(150,150,150,0.3)',
		            width: 'auto',
		            type: 'default'
		           },
		           formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'个';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              },
	    	    padding:15
	 		    },
	 		    calculable : true,
	 		    xAxis : [
	 		        {
	 		        	splitLine:false,
	 		        	axisLine:false,
	 		        	axisTick:false,
	 		            type : 'category',
	 		            data : datalist
	 		        }
	 		    ],
	 		    yAxis : [
	 		        {
	 		        	name:'更新个数  (个)\n',
	 		        	nameTextStyle:{color:'black'},
	  		            axisLine:{
	  		                lineStyle: {
	  		                    color: '#ffffff',
	  		                    width:1
	  		                },
	  		            type : 'value'
	  		            }
	 		        }
	 		    ],
	 		    grid:{
		  	    	borderColor:'#fff',
		  	    	x:42,
		  	    	x2:15
		  	    },
	 		    series : [
	 		        {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:userName,
	 		            type:'line',
	 		            data:shiPortalslist
	 		           
	 		        },
	 		       {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:'市政府部门网站',
	 		            type:'line',
	 		            data:shiDepartmentlist
	 		           
	 		        },
	 		       {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:'区县政府网站',
	 		            type:'line',
	 		            data:xianlist
	 		           
	 		        }
	 		    ]
	 		};
	     myChart.setOption(option);
	},100);
}
function shengline(type,userName){
	setTimeout(function(){
		var legendName = [userName,'省部门网站','市级网站','县级网站'];
		var datalist = new Array();
		var shengPortalslist = new Array();
		var shengDepartmentlist = new Array();
		var shiGovernmentlist = new Array();
		var xianlist = new Array();
		$.ajax( {  
			type : "POST",  
			url : webPath+"/updateContentAnalyze_contentOrgLine.action",  
			data : "type=" + type,  
			dataType:"json",
			async : false,  
			success : function(data) {
				datalist=data.datalist;
				shengPortalslist=data.shengPortalslist;
				shengDepartmentlist=data.shengDepartmentlist;
				shiGovernmentlist=data.shiGovernmentlist;
				xianlist=data.xianlist;
			}
		});
		var myChart = echarts.init(document.getElementById('contentOrgLine'));
		option = {
				//color:['#32BB9D','#6ACC5B','#F3B94D','#4B85D9'],
				//#eb7070   #3cbb9d  #64cf4e  #f7b844  #4e88dc
                color:[ '#3cbb9d','#64cf4e' ,'#f7b844' ,'4e88dc' ],
				dataZoom : {
					show : true,
					realtime : true,
					height: 20,
					start : 100,
					end : 40
				},
				legend: {
					x: 'right',
					y: '30',
					padding:40,
					itemGap:30,
					data:legendName
				},
				tooltip : {
					trigger: 'axis',
					borderColor: '#48b',
					axisPointer: {
						color: 'rgba(150,150,150,0.3)',
						width: 'auto',
						type: 'default'
					},
					formatter: function (params,ticket,callback) {
						var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
						for (var i = 0, l = params.length; i < l; i++) {
							res += '<br/>' + params[i].seriesName + '：' + params[i].value+'个';
						}
						setTimeout(function (){
							// 仅为了模拟异步回调
							callback(ticket, res);
						}, 0);
					},
					padding:15
				},
				calculable : true,
				xAxis : [
				         {
				        	 splitLine:false,
				        	 axisLine:false,
				        	 axisTick:false,
				        	 type : 'category',
				        	 data : datalist
				         }
				         ],
				         yAxis : [
				                  {
				                	  name:'平均更新个数  (个)\n',
				                	  nameTextStyle:{color:'black'},
				                	  axisLine:{
				                		  lineStyle: {
				                			  color: '#ffffff',
				                			  width:1
				                		  },
				                		  type : 'value'
				                	  }
				                  }
				                  ],
				                  grid:{
				                	  borderColor:'#fff',
				                	  x:42,
				  		  	    	  x2:15
				                  },
				                  series : [
				                            {
				                            	symbol:'emptyCircle',
				                            	symbolSize:4,
				                            	name:userName,
				                            	type:'line',
				                            	data:shengPortalslist
				                            	
				                            },
				                            {
				                            	symbol:'emptyCircle',
				                            	symbolSize:4,
				                            	name:'省部门网站',
				                            	type:'line',
				                            	data:shengDepartmentlist
				                            	
				                            },
				                            {
				                            	symbol:'emptyCircle',
				                            	symbolSize:4,
				                            	name:'市级网站',
				                            	type:'line',
				                            	data:shiGovernmentlist
				                            	
				                            },
				                            {
				                            	symbol:'emptyCircle',
				                            	symbolSize:4,
				                            	name:'县级网站',
				                            	type:'line',
				                            	data:xianlist
				                            	
				                            }
				                            ]
		};
		myChart.setOption(option);
	},100);
}
//县级
function xianline(type,userName){
	setTimeout(function(){
		var legendName = [userName,'县级网站'];
		var datalist = new Array();
		var xianDepartmentlist = new Array();
		var xianlist = new Array();
		 $.ajax( {  
	         type : "POST",  
	         url : webPath+"/updateContentAnalyze_contentOrgLine.action",  
	         data : "type=" + type,  
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	        	 datalist=data.datalist;
	        	 xianDepartmentlist=data.xianDepartmentlist;
	        	 xianlist=data.xianlist;
	         }
	      });
		var myChart = echarts.init(document.getElementById('contentOrgLine'));
	    option = {
	    		//color:['#32BB9D','#6ACC5B'],
	    		//#eb7070   #3cbb9d  #64cf4e  #f7b844  #4e88dc
                color:[ '#3cbb9d','#64cf4e'],
				dataZoom : {
					show : true,
					realtime : true,
					height: 20,
					start : 100,
					end : 40
				},
	    	    legend: {
	    	    	 x: 'right',
	    	    	 y: '30',
	    	    	 padding:40,
	    	    	 itemGap:30,
	    	        data:legendName
	    	    },
	 		    tooltip : {
	 		        trigger: 'axis',
		 		    borderColor: '#48b',
		            axisPointer: {
		            color: 'rgba(150,150,150,0.3)',
		            width: 'auto',
		            type: 'default'
		           },
		           formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'个';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              },
	    	    padding:15
	 		    },
	 		    calculable : true,
	 		    xAxis : [
	 		        {
	 		        	splitLine:false,
	 		        	axisLine:false,
	 		        	axisTick:false,
	 		            type : 'category',
	 		            data : datalist
	 		        }
	 		    ],
	 		    yAxis : [
	 		        {
	 		        	name:'平均更新个数  (个)\n',
	 		        	nameTextStyle:{color:'black'},
	  		            axisLine:{
	  		                lineStyle: {
	  		                    color: '#ffffff',
	  		                    width:1
	  		                },
	  		            type : 'value'
	  		            }
	 		        }
	 		    ],
	 		    grid:{
		  	    	borderColor:'#fff',
		  	    	x:42,
		  	    	x2:15
		  	    },
	 		    series : [
	 		        {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:userName,
	 		            type:'line',
	 		            data:xianDepartmentlist
	 		           
	 		        },
	 		       {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:'县级网站',
	 		            type:'line',
	 		            data:xianlist
	 		           
	 		        }
	 		    ]
	 		};
	     myChart.setOption(option);
	},100);
}
function contentPiePrevious() {
    if(!$("#previousHref").hasClass("flex-disabled")) {
        $('.dropdown-menu.inner>li').eq(0).children('a').click();
        contentPie(previousDate, 0, type);
        loadChannelDataList(previousDates, type);
    }
}

function contentPieNext() {
    if(!$("#nextHref").hasClass("flex-disabled")) {
        $('.dropdown-menu.inner>li').eq(0).children('a').click();
        contentPie(nextDate, 0, type);
        loadChannelDataList(nextDates, type);
    }
}
//列表
function loadChannelDataList(objDate,type){
	$.ajax({
		type : "POST",
		url : webPath + "/updateContentAnalyze_loadContentListOrg.action",
		dataType : "json",
		data : {
			'scanDate' : objDate,
			'type':type
		},
		async : false,
		success : function(data) {
			$("#tbodyChannelOrg").empty();
			previousDates = data.previousDate;
			nextDates = data.nextDate;
			if(type==2){
				var tHead = "<tr><th class='th_left th_bg1' rowspan='2'>相关分类</th><th class='th_bg2' colspan='2'>市门户</th><th class='th_bg3' colspan='2'>市部门网站</th>" +
				"<th class='th_bg4' colspan='2'>区县政府</th></tr>" +
				"<tr><th class='th_bg6'>更新条数</th><th class='th_bg6'>占比</th><th class='th_bg7'>更新条数</th>" +
				"<th class='th_bg7'>占比</th><th class='th_bg8'>更新条数</th><th class='th_bg8'>占比</th></tr>";
			}else if(type==1){
				var tHead = "<tr><th class='th_left th_bg1' rowspan='2'>相关分类</th><th class='th_bg2' colspan='2'>省门户</th>" +
						"<th class='th_bg3' colspan='2'>省部门网站</th><th class='th_bg3' colspan='2'>市政府网站</th>" +
				"<th class='th_bg4' colspan='2'>区县政府</th></tr>" +
				"<tr><th class='th_bg6'>更新条数</th><th class='th_bg6'>占比</th><th class='th_bg7'>更新条数</th>" +
				"<th class='th_bg7'>占比</th><th class='th_bg8'>更新条数</th><th class='th_bg8'>占比</th><th class='th_bg8'>更新条数</th><th class='th_bg8'>占比</th></tr>";
			}else if(type==3){
				var tHead = "<tr><th class='th_left th_bg1' rowspan='2'>相关分类</th><th class='th_bg2' colspan='2'>县门户</th>" +
				"<th class='th_bg3' colspan='2'>县部门</th></tr>" +
				"<tr><th class='th_bg6'>更新条数</th><th class='th_bg6'>占比</th><th class='th_bg7'>更新条数</th>" +
				"<th class='th_bg7'>占比</th></tr>";
			}
			$("#tbodyChannelOrg").append(tHead);
			if (data.listChannel.length<=0) {
				$("#tbodyChannelOrg").append("<tr><th colspan='9'>无数据</th></tr>");
				return;
			}
			if(type==2){
				$.each(data.listChannel, function(index, channelData) {
					var htm=" <tr><td class='td_left'>"+channelData.channelName+"</td>"
							+"<td class='td_eef4fb'>"+channelData.shiPortals+"</td>";
							if(channelData.shiPortalsPercent==0){
								htm+="<td class='td_eef4fb'>"+channelData.shiPortalsPercent+"</td>";
							}else{
								htm+="<td class='td_eef4fb'>"+channelData.shiPortalsPercent+"%</td>";
							}
							htm+="<td class='td_f3fafd'>"+channelData.shiDepartment+"</td>"
							if(channelData.shiDepartmentPercent==0){
								htm+="<td class='td_f3fafd'>"+channelData.shiDepartmentPercent+"</td>";
							}else{
								htm+="<td class='td_f3fafd'>"+channelData.shiDepartmentPercent+"%</td>";
							}
							htm+="<td class='td_f1fcfd'>"+channelData.xian+"</td>"
							if(channelData.xianPercent==0){
								htm+="<td class='td_f1fcfd'>"+channelData.xianPercent+"</td>";
							}else{
								htm+="<td class='td_f1fcfd'>"+channelData.xianPercent+"%</td>";
							}
									+"</tr>";
				$("#tbodyChannelOrg").append(htm);
				});
			}else if(type==1){
				$.each(data.listChannel, function(index, channelData) {
					var htm=" <tr><td class='td_left'>"+channelData.channelName+"</td>"
							+"<td class='td_eef4fb'>"+channelData.shengPortals+"</td>";
							if(channelData.shengPortalsPercent==0){
								htm+="<td class='td_eef4fb'>"+channelData.shengPortalsPercent+"</td>";
							}else{
								htm+="<td class='td_eef4fb'>"+channelData.shengPortalsPercent+"%</td>";
							}
								htm+="<td class='td_f3fafd'>"+channelData.shengDepartment+"</td>"
							if(channelData.shengDepartmentPercent==0){
								htm+="<td class='td_f3fafd'>"+channelData.shengDepartmentPercent+"</td>";
							}else{
								htm+="<td class='td_f3fafd'>"+channelData.shengDepartmentPercent+"%</td>";
							}
								htm+="<td class='td_f1fcfd'>"+channelData.shiGovernment+"</td>"
							if(channelData.shiGovernmentPercent==0){
								htm+="<td class='td_f1fcfd'>"+channelData.shiGovernmentPercent+"</td>";
							}else{
								htm+="<td class='td_f1fcfd'>"+channelData.shiGovernmentPercent+"%</td>";
							}
								htm+="<td class='td_f3faf0'>"+channelData.xian+"</td>"
							if(channelData.xianPercent==0){
								htm+="<td class='td_f3faf0'>"+channelData.xianPercent+"</td>";
							}else{
								htm+="<td class='td_f3faf0'>"+channelData.xianPercent+"%</td>";
							}
								htm+="</tr>";
					$("#tbodyChannelOrg").append(htm);
				});
			}else if(type==3){
				$.each(data.listChannel, function(index, channelData) {
					var htm =" <tr><td class='td_left'>"+channelData.channelName+"</td>"
							+"<td class='td_eef4fb'>"+channelData.xianDepartment+"</td>";
							if(channelData.xianDepartmentPercent==0){
								htm+="<td class='td_eef4fb'>"+channelData.xianDepartmentPercent+"</td>";
							}else{
								htm+="<td class='td_eef4fb'>"+channelData.xianDepartmentPercent+"%</td>";
							}
								htm+="<td class='td_f3fafd'>"+channelData.xian+"</td>";
							if(channelData.xianPercent==0){
								htm+="<td class='td_f3fafd'>"+channelData.xianPercent+"</td>";
							}else{
								htm+="<td class='td_f3fafd'>"+channelData.xianPercent+"%</td>";
							}
									+"</tr>";
					$("#tbodyChannelOrg").append(htm);
				});
			}
		}
	});
}
function loadChannelDataPie(scanDate,level,type) {
	channeldataArray = [];
	// 饼图
	$.ajax({
				type : "POST",
				url : webPath + "/updateContentAnalyze_loadContentCountOrg.action",
				dataType : "json",
				data : {
					'scanDate' : scanDate,
					'level':level,
					'type':type
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
					scanDates = data.scanDates;
					$("#sumNum").empty();
					$("#scan_date").empty();
					$("#sumNum").append(sumNum);
					$("#scan_date").append(data.scanDate);
					$("#sumDisplay").empty();
					
					var channelLength= eval(data.listChannel).length;
					if(channelLength<7){
					    $("#sumDisplay").append("共更新"+channelLength+"类信息，覆盖分类不足50%");
					 }else{
					    $("#sumDisplay").append("共更新"+channelLength+"类信息，覆盖分类超过50%");
					 }
					
					if(data.listChannel.length!=null){
						$.each(data.listChannel, function(index, channelData) {
							channeldataArray.push({
								name : channelData.channelName,
								value : channelData.proportion
							});
						});
					}else{
					}
				}
			});
}

function contentPie(scanDate,level,type) {
	loadChannelDataPie(scanDate,level,type);
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
			        text: sumNum +"条",
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
			startAngle:180,
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