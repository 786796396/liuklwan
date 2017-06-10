var mapper = {
		'110000':'北京',
		'120000':'天津',
		'130000':'河北',
		'140000':'山西',
		'150000':'内蒙古',
		'210000':'辽宁',
		'220000':'吉林',
		'230000':'黑龙江',
		'310000':'上海',
		'320000':'江苏',
		'330000':'浙江',
		'340000':'安徽',
		'350000':'福建',
		'360000':'江西',
		'370000':'山东',
		'410000':'河南',
		'420000':'湖北',
		'430000':'湖南',
		'440000':'广东',
		'450000':'广西',
		'460000':'海南',
		'500000':'重庆',
		'510000':'四川',
		'520000':'贵州',
		'530000':'云南',
		'540000':'西藏',
		'610000':'陕西',
		'620000':'甘肃',
		'630000':'青海',
		'640000':'宁夏',
		'650000':'新疆',
		'bm0100':'china'
};
var loginSiteCode = $("#loginSiteCode").val();
var loginName = $("#loginName").val();

var organName="";
var bobaoLength=0;
$(function () {
	var menuType ="";
	init();
    //显示五个色块
	// dailyStatistics();
	//获取问题播报数据
    getProblems();
	//问题播报
    rollProblems();

    //查询  省市县  关停例外 等
    checkDatas();
	//健康指数
    healthIndex(menuType);
   	//健康指数折线图
    healthLine(menuType);
    //热点图
    healthMap();
    //获取访问数据；
    getVisitData();
    
    
    var timer=null;
    $('.right_parts .content h4').mouseover(function(){
        clearTimeout(timer);
        var obj = $(this);
        timer=setTimeout(function(){
            obj.popover('show');
        },800);
    });

    $('.right_parts .content h4').mouseout(function(){
        clearTimeout(timer);
        var obj = $(this);
        obj.popover('hide');
    });
});
function init(){
	if(loginSiteCode == 'bm0100'){
		loginName="全国";
	}else{
		loginName=mapper[loginSiteCode];
	}
	$("#centerId").html(loginName);
}
function subExit(){
	//window.close();
	window.location.href=webPath+"/siteDataOverview_siteDataOverviewChart.action";
}
//跳转到全屏
function fullScreen(){
	window.open(webPath+"/siteDataOverview_fullScreen.action", "_blank","fullscreen=1");
}
//获取问题播报数据
// 首页不连通  实时的数据  预警表获取； 其他的  在概览表获取
function getProblems(){
	$.ajax({  
		type : "POST",    
		url : webPath+"/early_getDatas.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			if(data.success == 'true'){
				var dataList = data.list;
				bobaoLength = dataList.length;
				var html = "";
				for(var i=0;i<dataList.length;i++){
					var data = dataList[i];
					if(data.siteCodeName.length>9){
						html+="<li><ul class=\"clearfix\"><li class=\"fl websiteName\" title='"+data.siteCodeName+"'>"+data.siteCodeName.substring(0,9)+'...'+
						"</li><li class=\"fl findQues\">"+data.remark+"</li><li class=\"fl \">"+data.scanTime+"</li></ul></li>";
					}else{
						html+="<li><ul class=\"clearfix\"><li class=\"fl websiteName\" title='"+data.siteCodeName+"'>"+data.siteCodeName+
						"</li><li class=\"fl findQues\">"+data.remark+"</li><li class=\"fl \">"+data.scanTime+"</li></ul></li>";
					}
				}
				$("#rollDatas").html(html);
			}
		}
	});
}
//问题播报
function rollProblems(){
	if(bobaoLength>4){
		$('.active_report').liMarquee({
			direction: 'up',
			scrollamount: 20
		});
	}else{
		$('.active_report').liMarquee({
			runshort: false
			});
		$(".str_move,.str_origin").width('100%'); 
	}
}


//获取访问数据；
function getVisitData(){
	var dateList = new Array();
	var valueList = new Array();
	$.ajax({  
		type : "POST",    
		url : webPath+"/jcVisitOrg_getSevenDatas.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			if(data.success == 'true'){
				var dataList = data.list;

				for(var i=0;i<dataList.length;i++){
					var data = dataList[i];
					var ri = data.scanDate.split("-")[2];
					dateList.push(parseInt(ri)+'日');
					valueList.push(Math.round(data.uv/10000));
				}
			}
		}
	});
	var myChart = echarts.init(document.getElementById('visitData'));

	option = {
			backgroundColor: '#0f171c',
		    title: {
		    	text: '   门户网站访问用户数 (单位：万)',
		    	/*subtext:'   (单位：万)',*/
		    	textStyle:{
		    		fontSize: 14,
		    		fontWeight:'normal',
		    		fontFamily : '微软雅黑',
		    		color: '#fff'
		    	},
		    	padding:[22,5,5,5]
//			    itemGap:6,
//		    	subtextStyle:{
//		    	    color: '#a6a7a8',
//		    	    fontWeight:'normal',
//		    	    fontFamily : '微软雅黑',
//		    	    fontSize: 12,
//		    	} 
		    },
		    
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		    	x:'right',
		    	padding:20,
		        data:['访问用户数'],
		        textStyle:{
		          	color:'#fff'
		          }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
//		        feature: {
//		            saveAsImage: {}
//		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                lineStyle:{
                    color:'#fff'
                },
                axisLine:{
  	              lineStyle:{
  	                  color:'#fff'
  	              }
  	            },
		        data: dateList
		    },
		    yAxis: {
		    	lineStyle:{
                    color:'#0f171c'
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                //show:false,
	            type : 'value',
	            axisLine:{
	            	show:false,
	              lineStyle:{
	                  color:'#414a59'
	              }
	            }
		    },
		    series: [
		       
		        {
		            name:'访问用户数',
		            type:'line',
		            //stack: '健康指数',
		            itemStyle : {  
                        normal : {  
                        	color:'#94ed83',  
                            lineStyle:{  
                                color:'#94ed83',
                            }  
                        }  
                    }, 
		            data:valueList
		        }
		    ]
		};
	     myChart.setOption(option);
//	     setTimeout(function (){
//	    	    window.onresize = function () {
//	    	    	myChart.resize();
//	    	    }
//	    	},200);
	     $(window).resize(function(){
	    	 //alert(1);
	    	 myChart.resize();
	     });
}
/**
 * 昨天健康指数
 * @param {} menuType---》省市县类别
 */
function healthIndex(menuType){
	menu = menuType;
	$.ajax({  
		type : "POST",    
		url : webPath+"/indexCount_getIndexSum.action", 
		dataType:"json",
		data:"menuType="+menu,
		async : false,  
		success : function(data) {
			var differential=data.differential;
			var differentialRate = data.differentialRate;
			//折算分数
			var convertScores=data.convertScores;
			//健康分数
			var totalSumNumber=data.totalSumNumber;
			var leadSum=data.leadSum;
			if(leadSum==0){
				leadSum = "1";
			}
			//$("#health_index").next().text("领先全国"+leadSum+"%的政府网站");
            //半圆进度
            var gauge = new Donut(document.getElementById("chart-gauge"));
            gauge.lineWidth = 7;
            gauge.options = {
            		lineWidth: 0.05,
                    colorStart: "#00bcd5",
                    colorStop: "#00bcd5",
                    strokeColor: "#263347",
                    shadowColor: "#263347",
                    angle: 0.26
            };
            gauge.maxValue = 100;
            gauge.set(convertScores==0?1:convertScores);
            //半圆进度end
			if(convertScores>=90){
				$("#health_index").next().text("优");
			}else if(convertScores>=70){
				$("#health_index").next().text("良");
			}else if(convertScores>=60){
				$("#health_index").next().text("中");
			}else{
				$("#health_index").next().text("差");
			}
			//$("#health_index").text(totalSumNumber);//========全国健康指数昨天
		}
	});
}
/**
 * 健康指数折线图
 * @param {} menuType---》省市县类别
 */
function healthLine(menuType){
	menu = menuType;
	var allIndexlist = new Array();
	var datelist = new Array();
	var indexlist = new Array();
	var siteName="";
	
	$.ajax({  
		type : "POST",    
		url : webPath+"/fillUnit_indexCountLineBM.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			allIndexlist=data.allSiteList;
			datelist=data.datelist;
			indexlist=data.siteList;
			organName = data.siteName;
		}
	});
		
		lineSheng(organName,indexlist,datelist,allIndexlist);
}
//省市折线图，两条
function lineSheng(siteName,indexlist,datelist,allIndexlist){
	$("#health_index").text(allIndexlist[allIndexlist.length-1]);
	var organNam = loginName+'政府网站';
	var allIndexListSort = allIndexlist.concat(indexlist);
	var min=0;
	var max = 0;
	if(allIndexListSort.length>0){
		allIndexListSort.sort(function(a,b){return a-b});
		min = parseInt((allIndexListSort[0]/100))*80;
		max = parseInt((allIndexListSort[allIndexListSort.length-1]/100))*120;
	}
	var myChart = echarts.init(document.getElementById('index_line'));

	option = {

		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		    	x:'left',
		    	padding:20,
		        data:[organNam,siteName],
		        textStyle:{
		          	color:'#fff'
		          }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
//		        feature: {
//		            saveAsImage: {}
//		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                lineStyle:{
                    color:'#fff'
                },
                axisLine:{
  	              lineStyle:{
  	                  color:'#fff'
  	              }
  	            },
		        data: datelist//['周一','周二','周三','周四','周五','周六','周日']
		    },
		    yAxis: {
		    	lineStyle:{
                    color:'#fff'
                },
                min:min,
                max:max,
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                //show:false,
	            type : 'value',
	            axisLine:{
	              lineStyle:{
	                  color:'#414a59'
	              }
	            }
		    },
		    series: [
		       
		        {
		            name:organNam,
		            type:'line',
		            //stack: '健康指数',
		            itemStyle : {  
                        normal : {  
                        	color:'#f6bb42',  
                            lineStyle:{  
                                color:'#f6bb42',
                            }  
                        }  
                    }, 
		            data:allIndexlist//[320, 332, 301, 334, 390, 330, 320]
		        },
		        {
		            name:siteName,
		            type:'line',
		            //stack: '健康指数',
		            itemStyle : {  
                        normal : {  
                        	color:'#94ed83',  
                            lineStyle:{  
                                color:'#94ed83',  
                            }  
                        }  
                    },  
		            data:indexlist//[820, 932, 901, 934, 1290, 1330, 1320]
		        }
		    ]
		};
	     myChart.setOption(option);
//	     setTimeout(function (){
//	    	    window.onresize = function () {
//	    	    	myChart.resize();
//	    	    }
//	    	},200);
	     $(window).resize(function(){
	    	 //alert(1);
	    	 myChart.resize();
	     });
}

//获取 正常关停例外   省市县
function checkDatas(){
	$.ajax({  
        type : "POST",  
        url : webPath+"/siteDataOverview_getInfoStates.action",  
        dataType:"json",
        async : false,  
        success : function(data) {
        	if(data.success == 'true'){
        		var count = 0;
        		if(data.siteCode == 'bm0100'){
        			count=data.sumAll;
        		}else{
        			count=data.info.sumAll;
        		}
        		var sumClose = data.info.sumClose;
        		var sumNormal = data.info.sumNormal;
        		var liWai = count - sumClose - sumNormal;
        		var zhengChangPer=((sumNormal/count)*100).toFixed(2);
        		var guanTingPer = ((sumClose/count)*100).toFixed(2);
        		
        		$("#zhengChangPer").html(maxAndMin(zhengChangPer)+'%');
        		$("#guanTingPer").html(maxAndMin(guanTingPer)+'%');
        		$("#liWaiPer").html(maxAndMin((100.00 -guanTingPer-zhengChangPer).toFixed(2))+'%');
        		$("#count1").html(randomNum(count));

        		fenBu(sumNormal,liWai,sumClose);
        	}
        }
	});
	
}
// 全国  政府  网站  情况

function fenBu(zhengchang,liwai,guanting){
	var fenbu = echarts.init(document.getElementById('fenbu'));
	option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{b}: {c} ({d}%)"
	    },
	    color:['#00d7f1', '#fed42a','#e84e50'],
	    title: {
	        text: '网站状况',
	        //subtext: 'From ExcelHome',
	        //sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh',
	        x: 'center',
	        y: 'center',
	        itemGap: 20,
	        textStyle : {
	            color : '#a6a7a8',
	            fontFamily : '微软雅黑',
	            fontSize : 12,
	            fontWeight : 'normal'
	        }
	    },
//	    legend: {
//	        orient: 'vertical',
//	        x: 'left',
//	        data:['省','市','县']
//	    },
	    series: [
	        {
	           // name:'访问来源',
	            type:'pie',
	            radius: ['60%', '70%'],
	            avoidLabelOverlap: true,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: false,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: true
	                }
	            },
	            data:[
	                {value:zhengchang, name:'正常'},
	                {value:liwai, name:'例外'},
	                {value:guanting, name:'关停'},
	            ]
	        }
	    ]
	};
	fenbu.setOption(option,true);
//	 setTimeout(function (){
// 	    window.onresize = function () {
// 	    	fenbu.resize();
// 	    }
// 	},200);
    $(window).resize(function(){
   	 //alert(1);
   	 fenbu.resize();
    });
}



function healthMap(){
	var data = [];
	$.ajax({  
        type : "POST",  
        url :  webPath+"/siteDataOverview_dailyMap.action", 
        dataType:"json",
        async : false,  
        success : function(dat) {
        	if(dat){
        		$.each(dat.body, function(index, obj) {
        			var name=obj.name;
        			var linkerrsiteprop=obj.linkerrsiteprop;
        			//console.info(name);
        			data.push({  
        			    'name': name,  
        			    'value':  Math.round((100-linkerrsiteprop)*100)/100+'%',  
        			});
        			
        			var linkerrsiteprop=dat.linkerrsiteprop;
    	        	var linksuccsiteprop=dat.linksuccsiteprop;
    	        	var html=maxAndMin(linksuccsiteprop)+"<span>%</span>";
    	        	$("#linkErrSiteProp_h").html(html);
    	        	$("#linkErrSiteProp_span").width(maxAndMin(linksuccsiteprop)+"%");
    	     		
    	     		
    	     		var updatesiteprop=dat.updatesiteprop;
    	     		var noupdatesiteprop=dat.noupdatesiteprop;
    	     		var prophtml=maxAndMin(updatesiteprop).toFixed(2)+"<span>%</span>";
    	     		$("#noUpdateSiteProp_h").html(prophtml);
    	        	$("#noUpdateSiteProp_span").width(maxAndMin(updatesiteprop).toFixed(2)+"%");
    	     		
    	     		var indexdeadnum= dat.indexdeadnum;
    	     		$("#indexdeadnum").html(randomNum(indexdeadnum)+"<span>个</span>");
    	     		
    	     		var aveupdatenum=dat.aveupdatenum;
    	     		$("#aveupdatenum").html(randomNum(aveupdatenum)+"<span>条</span>");
    	     		
    	     		var updatenum=dat.updatenum;
    	     		
    	     		$("#updatenum").html(randomNum(updatenum)+"<span>条</span>");
        			
        		});
        	}
        }
	});
	var par = 0.9;
	if('bm0100' == loginSiteCode){
		par = 1.2;	
        geoCoordMap = {
           '西藏自治区':[91.11,29.97],
           '海南省':[110.35,20.02],
           '宁夏回族自治区':[106.27,38.47],
           '广西壮族自治区':[108.33,22.84],
           '福建省':[119.3,26.08],
           '江西省':[115.89,28.68],
           '广东省':[113.23,23.16],
           '山西省':[112.53,37.87],
           '云南省':[102.73,25.04],
           '辽宁省':[123.38,41.8],
           '吉林省':[125.35,43.88],
           '青海省':[101.74,36.56],
           '内蒙古自治区':[111.65,40.82],
           '四川省':[104.06,30.67],
           '浙江省':[120.19,30.26],
           '陕西省':[108.95,34.27],
           '江苏省':[118.78,32.04],
           '贵州省':[106.71,26.57],
           '新疆维吾尔自治区':[87.68,43.77],
           '山东省':[117,36.65],
           '甘肃省':[103.73,36.03],
           '重庆市':[106.54,29.59],
           '北京市':[116.46,39.92],
           '上海市':[121.48,31.22],
           '天津市':[117.2,39.13],
           '河南省':[113.65,34.76],
           '黑龙江省':[126.63,45.75],
           '河北省':[114.48,38.03],
           '湖南省':[113,28.21],
           '安徽省':[117.27,31.86],
           '湖北省':[114.31,30.52]
		       };
			
		}
	       var greenArr = [];
	       var convertData95 = function (data) {
	    	    var res = [];
	    	    for (var i = 0; i < data.length; i++) {
	    	            if(data[i].value.split('%')[0]>=97.00){
	    	                var geoCoord = geoCoordMap[data[i].name];
	    	                if (geoCoord) {
	    	                    res.push({
	    	                        name: data[i].name,
	    	                        value: geoCoord.concat(data[i].value)
	    	                    });
	    	                }
	    	            }
	    	    }
	    	    greenArr = res;
	    	    return res;
	    	};
	       
	       var convertData90 = function (data) {
	    	    var res = [];
	    	    for (var i = 0; i < data.length; i++) {
	    	            if(data[i].value.split('%')[0]>95.00 && 97.00>data[i].value.split('%')[0]){
	    	                var geoCoord = geoCoordMap[data[i].name];
	    	                if (geoCoord) {
	    	                    res.push({
	    	                        name: data[i].name,
	    	                        value: geoCoord.concat(data[i].value)
	    	                    });
	    	                }
	    	            }
	    	    }
	    	    return res;
	    	};
	       var convertData00 = function (data) {
	    	    var res = [];
	    	    for (var i = 0; i < data.length; i++) {
	    	            if(95.00>=data[i].value.split('%')[0]){
	    	                var geoCoord = geoCoordMap[data[i].name];
	    	                if (geoCoord) {
	    	                    res.push({
	    	                        name: data[i].name,
	    	                        value: geoCoord.concat(data[i].value)
	    	                    });
	    	                }
	    	            }
	    	    }
	    	    return res;
	    	};
	       
	       
	       
	       
	       
	       

	       option = {
	           backgroundColor: '#0f171c',
	         //  background-attachment:fixed,
	           title: {
	               text: loginName+'政府网站连通率实时状态图',
	               left: 'center',
	               padding:20,
	               textStyle: {
	            	   fontFamily : '微软雅黑',
	   		    	   fontWeight:'normal',
	                   color: '#fff',
	                   fontSize: 14,
	                   
	               }
	           },
	           tooltip : {
	               trigger: 'item',
	            	  formatter: function (param){
	           			var value = "";
	                      for(var i=0;i<data.length;i++){
	                          //value = data[i].name;
	                          if(param.name == data[i].name){
	                              value = data[i].value;
	                          }
	                      }
	           			return param.name+"</br>连通率："+value;
	           		}

	           },
	           legend: {
	               orient: 'vertical',
	               y: 'bottom',
	               x:'left',
	               data:['97%~100%','95%~97%','0~95%'],
	               textStyle: {
	                   color: '#fff'
	               }
	           },
	           geo: {
	               map: mapper[loginSiteCode],//'china',
	               label: {
	                   emphasis: {
	                       show: false
	                   }
	               },
	               roam: false,
	               scaleLimit:{min:par,max:par},
	               itemStyle: {
	                   normal: {
	                       areaColor: '#323c48',
	                       borderColor: '#111'
	                   },
	                   emphasis: {
	                       areaColor: '#2a333d'
	                   }
	               }
	           },
	           series : [
	               /*{
	                   name: '0~95%',
	                   type: 'scatter',
	                   coordinateSystem: 'geo',
	                   data: convertData00(data),
	                   symbolSize: function (val) {
	                	   return 20; //return (5-((parseInt(val[2])-50)/10))*(5-((parseInt(val[2])-50)/10));
	                   },
	                   
	                   label: {
	                       normal: {
	                           formatter: '{b}',
	                           position: 'right',
	                           show: false
	                       },
	                       emphasis: {
	                           show: true
	                       }
	                   },
	                   itemStyle: {
	                       normal: {
	                           color: '#f44236',
	                           shadowColor: '#f44236'
	                       }
	                   }
	               },*/
	               {
	                   name: '0~95%',
	                   type: 'effectScatter',
	                   coordinateSystem: 'geo',
	                   data: convertData00(data),
	                   symbolSize: function (val) {
	                       return 20;//((parseInt(val[2])-50)/10)*((parseInt(val[2])-50)/10);
	                   },
	                   showEffectOn: 'render',
	                   rippleEffect: {
	                       brushType: 'stroke'
	                   },
	                   hoverAnimation: true,
	                   label: {
	                       normal: {
	                           formatter: '{b}',
	                           position: 'right',
	                           show: true
	                       }
	                   },
	                   itemStyle: {
	                       normal: {
	                           color: '#f44236',
	                           shadowBlur: 10,
	                           shadowColor: '#f44236'
	                       }
	                   },
	                   zlevel: 1
	               },
	               {
	                   name: '95%~97%',
	                   type: 'scatter',
	                   coordinateSystem: 'geo',
	                   data: convertData90(data),
	                   symbolSize: function (val) {
	                	   return  16;//return (5-((parseInt(val[2])-50)/10))*(5-((parseInt(val[2])-50)/10));
	                   },
	                   
	                   label: {
	                       normal: {
	                           formatter: '{b}',
	                           position: 'right',
	                           show: false
	                       },
	                       emphasis: {
	                           show: true
	                       }
	                   },
	                   itemStyle: {
	                       normal: {
	                           color: '#ffc009',
	                           shadowColor: '#ffc009'
	                       }
	                   }
	               },
	               
	               {
	                   name: '97%~100%',
	                   type: 'scatter',
	                   coordinateSystem: 'geo',
	                   data: convertData95(data),
	                   symbolSize: function (val) {
	                	   return  13;// return (5-((parseInt(val[2])-50)/10))*(5-((parseInt(val[2])-50)/10));
	                   },
	                   
	                   label: {
	                       normal: {
	                           formatter: '{b}',
	                           position: 'right',
	                           show: false
	                       },
	                       emphasis: {
	                           show: true
	                       }
	                   },
	                   itemStyle: {
	                       normal: {
	                           color: '#31de37',
	                           shadowColor: '#31de37'
	                       }
	                   }
	               },
	               
	               {
	                   name: '97%~100%',
	                   type: 'effectScatter',
	                   coordinateSystem: 'geo',
	                   data: convertData95(data.sort(function (a, b) {
	                       return parseFloat(b.value) - parseFloat(a.value);
	                   }).slice(0, 5)),
	                   symbolSize: function (val) {
	                       return 13;//((parseInt(val[2])-50)/10)*((parseInt(val[2])-50)/10);
	                   },
	                   showEffectOn: 'render',
	                   rippleEffect: {
	                       brushType: 'stroke'
	                   },
	                   hoverAnimation: true,
	                   label: {
	                       normal: {
	                           formatter: '{b}',
	                           position: 'right',
	                           show: true
	                       }
	                   },
	                   itemStyle: {
	                       normal: {
	                           color: '#0af90a',
	                           shadowBlur: 10,
	                           shadowColor: '#0af90a'
	                       }
	                   },
	                   zlevel: 1
	               }
	           ]
	       };

	       var myChart = echarts.init(document.getElementById('map-wrap'));
		   myChart.setOption(option);
		     $(window).resize(function(){
		    	 //alert(1);
		    	 myChart.resize();
		     });
	    
}
/**
 * 跳转到 日常监测统计页面
 */
function toBigDateJsp(){
	window.location.href=webPath+"/dailyMonitoringStatistics_dailyMonitoringStatistics.action";
}
/**
 * 跳转到站点数据概览
 */
function toHomeJsp(){
	window.location.href=webPath+"/siteDataOverview_siteDataOverview.action";
}
//百分比正负数验证
function maxAndMin(val){
	if(val<0){
		val=0;
	}
	if(val>100){
		val=100;
	}
	return val;
}