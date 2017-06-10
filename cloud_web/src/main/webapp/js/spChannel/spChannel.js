var menuType = 0;
var isBigId = 0;
var siteId;
var displayModule = new String();
var arr = new Array(); 
$(function(){
	$("#colorPiece").hide();
	$("#hiddenMap").hide();
	$("#articleId").hide();
	
	spClannelBasicTable();
    siteId = $("#siteId").val();
    arr = displayModule.split(',');//注split可以用字符或字符串分割  
    
    if(arr != null && arr != ""){
        for(var i = 0; i < arr.length; i++){
        	var type = arr[i];  
        	if(type == 1){  // 显示模块（1:日常检测,2:网站概况,3:栏目,4:大数据,5:政府网站基础信息数据库,6:政府网站网民找错数据）
        		connectedRate();  // 折线图
        	    automatic();   // 每5秒跳转一次
        		$("#colorPiece").show();
        	}
        	
        	if(type == 2){  
        		isBigId = 1;
        		queryCode();  // 地图
        		$("#hiddenMap").show();
        	}
        	
        	if(type == 3){  
        		$("#articleId").show();
        	} 
        	
        }
    }else{
    	$("#colorPiece").hide();
    	$("#hiddenMap").hide();
    	$("#articleId").hide();
    }
    
    healthIndex(menuType); //健康指数
    
	spConfigurationTable();
	
	noticeTable(isBigId);
	
    
});

//个性化基本信息查询    
function spClannelBasicTable() {
	$.ajax({
		type : "POST",
		url : webPath + "/spChannel_spClannelBasic.action",
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.success=='true') {
				if(data.type == 1){
					$("#name").html(data.name);
					$("#siteId").val(data.siteCode);
					$("#loginConfigId").val(data.loginConfig);
					displayModule = data.displayModule;
				}else{
					window.location.href = webPath + "/spChannel_spClannelError.action";
				}
			
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}


//每5秒自动切换
function automatic(){
    var oBox=document.getElementById('colorPiece');
    var aBtn=oBox.getElementsByTagName('span');
    var aDiv=document.getElementsByClassName('chart-content');
    var timer=null;
    var now=0;
    for (var i=0; i<aBtn.length; i++)
    {
        aBtn[i].index=i;
      
        aBtn[i].onclick=function (){
            for (var i=0; i<aBtn.length; i++)
            {
                $(aBtn[i]).removeClass('active');
                $(aDiv[i]).removeClass('active');
                aDiv[i].style.display="none";
            }
           
            $(this).addClass('active');
            $(aDiv[this.index]).addClass('active');
            aDiv[this.index].style.display="";
        };
    }
    function tab(){
            now++;
            if (now == aBtn.length)
            {
                now=0;
            }
            if(now == 0){
            	connectedRate();
            }else if(now == 1){
            	deadRate();
            }else if(now == 2){
            	datesiteRate();
            }
            
            for (var i=0; i<aBtn.length; i++)
            {
                $(aBtn[i]).removeClass('active');
                $(aDiv[i]).removeClass('active');
                aDiv[i].style.display="none";
            }

            $(aBtn[now]).addClass('active');
            $(aDiv[now]).addClass('active');
            aDiv[now].style.display="";
    };

    timer=setInterval(tab,5000);

    $('#tab-content').mouseover(function(){
        clearInterval(timer);
    });
    $('#tab-content').mouseout(function(){
        timer=setInterval(tab,5000);
    });
}

//获取各省code值
function queryCode(){
	var level = 2;	
	$.ajax({
		type : "POST",
		url : webPath + "/bigDataAnalysis_getNextCityAndCountry.action",
		dataType : "json",
		data:{
	    	"level":level,
	    	"orgSiteCode":siteId,
	    	},    
		async : false,
		success : function(data) {
			var html = "";
			 $.each(data.body, function(index, obj) {
				 var i = index+1;
				 if(i%2==0){
			    		html+='<li style="background-color:#f5f9fc;"><ul class="clearfix padd-ul"><li class="fl number">'+i+'</li><li class="fl area">'+obj.name+'</li><li class="fl total">'+obj.sitenum+'</li></ul></li>';
			        }else{
			        	html+='<li><ul class="clearfix padd-ul"><li class="fl number">'+i+'</li><li class="fl area">'+obj.name+'</li><li class="fl total">'+obj.sitenum+'</li></ul></li>';
			        }
			 });
			
			 $("#monitoringSiteId").append(html);
	          $.get(webPath +"/js/map/provinceJson/"+ siteId +".json",function(obj){
	        	    echarts.registerMap('henan', obj);//hennanJson名称取自henan.js里的var  henanJson变量名
	 		        var chart = echarts.init(document.getElementById('main'));
	 		        var convertData00 = function (data) {
	 		    	    var res = [];
	 		    	    for (var i = 0; i < data.length; i++) {
	 		    	                    res.push({
	 		    	                        name: data[i].name,
	 		    	                        value: data[i].sitenum
	 		    	                    });
	 		    	    }
	 		    	    return res;
	 		    	};
	 		        
	 		        chart.setOption({
	 				    tooltip: {
	 				    trigger: 'item',
	 				    formatter: function (param){
	 	           			var value = "";
	 	           			var datab = data.body;
	 	                      for(var i=0;i<datab.length;i++){
	 	                          if(param.name == datab[i].name){
	 	                              value = datab[i].sitenum;
	 	                          }
	 	                      }
	 	           			return param.name+"</br>监测网站数量："+param.value;
	 	           		}
	 				    },
	 				    legend: {
	 				        orient: 'vertical',
	 				        left: 'left',
	 				    },
	 				    visualMap: {
	 				    show:false,
	 				    splitList: [
	 		                {start: 301},
	 		                {start: 201, end: 300},
	 		                {start: 151, end: 200},
	 		                {start: 101, end: 150},
	 		                {end: 100}
	 				    ],
	 				    color: ['#ea7997', '#4fc5f7', '#88a2f9', '#f9b961', '#5ed2a5'],
//		 			        min: 0,
//		 			        max: 400,
//		 			        color:['#3e3a1d','#cf13bb','red','green','yellow','blue'],
	 			        left: 'left',
	 			        top: 'bottom',
	 			       // text: ['高','低'],          // 文本，默认为数值文本
	 			        calculable: true
	 		   	 },
	 				    series: [
	 				        {
	 				            type: 'map',
	 				            mapType: 'henan',
	 				            roam: false,
	 				            showLegendSymbol: false,
	 				            scaleLimit:{max:2, min:1.2},
	 				            label: {
	 				            	normal: {
	 				                    show:  false
	 				                },
	 				                emphasis: {
	 				                    show: true
	 				                }
	 				            },
	 				             data:convertData00(data.body)
	 				        },
	 				    ]
	 				        });
	          });
	   
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
/**
 * 昨天健康指数
 * @param {} menuType---》省
 */
function healthIndex(menuType){
	$.ajax({  
		type : "POST",    
		url : webPath+"/spChannel_getIndexSum.action", 
		dataType:"json",
		data:{menuType:menuType,siteCode:siteId},
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
			$("#health_index").next().text("领先全国"+leadSum+"%的政府网站");
            //半圆进度
            var gauge = new Donut(document.getElementById("chart-gauge"));
            gauge.lineWidth = 7;
            gauge.options = {
                lineWidth: 0.05,
                colorStart: "#ffffff",
                colorStop: "#ffffff",
                strokeColor: "#25902a",
                shadowColor: "#25902a",
                angle: 0.26
            };
            gauge.maxValue = 100;
            gauge.set(convertScores==0?1:convertScores);
            //半圆进度end
			if(convertScores>=90){
				$("#sum_style").text("优");
                //$("#sum_style").parents(".z_box1").css("background-color","#4cb050");
			}else if(convertScores>=70){
				$("#sum_style").text("良");
               // $("#sum_style").parents(".z_box1").css("background-color","#00bcd5");
			}else if(convertScores>=60){
				$("#sum_style").text("中");
                //$("#sum_style").parents(".z_box1").css("background-color","#ff9700");
			}else{
				$("#sum_style").text("差");
                //$("#sum_style").parents(".z_box1").css("background-color","#f44236");
			}
			$("#health_index").text(totalSumNumber);
//            $("#health_index").text("2105.92");
//            $("#totalSumNumber").text(138.30+" +"+0.56+"%");
			if(differential>0){
                $("#totalSumNumber").html(differential+"&nbsp;&nbsp;(+"+differentialRate+")");
			}else{
				$("#index_ico").addClass("down");
                $("#totalSumNumber").html(differential+"&nbsp;&nbsp;("+differentialRate+")");
			}
		}
	});
}

//栏目文章信息
function noticeTable(isBigId) {
	$.ajax({
		type : "POST",
		url : webPath + "/spChannel_spColumnArticle.action",
		data: {isBigData:isBigId,siteCode:siteId},
		dataType : "json",
		async : false,
		success : function(data) {
			var gghtml = "";
			var zdhtml = "";
			var item = "";
			if (data.success=='true') {
				var articlist = data.articlist;
				var chlist = data.chlist;
				var slist = data.slist;
				$("#noticeName").html(data.noticeName);
				if (data.size!=0) {
					// 通知公告信息
					$.each(articlist, function(index, obj) {
							if(obj.title != null && obj.title != "undefined"){
								gghtml+="<li>";
								gghtml+='<i></i><a target="_blank" href="'+webPath+'/spChannel_sqDetail.action?id='+obj.artId+'&siteCode='+siteId+'" title="'+obj.title+'">'+obj.title+'</a>';
								gghtml+='</li>';
							}
						});
					
					$("#uLId").append(gghtml);
					
					// 除通知公告外的栏目信息及文章信息
					$.each(chlist, function(index, zdobj) {
						if(index == 0 || index == 2){
							item = "item";
						}else{
							item = "";
						}
						//有置顶
						if(zdobj.clannelName != null && zdobj.clannelName != "undefined"){
							zdhtml+='<div class="fl '+item+' news_block" ><p class="big-title"><i></i>'+zdobj.clannelName+'</p>';
						}
						if(zdobj.title != null && zdobj.title != "undefined"){
							zdhtml+='<div class="artical"><h5>'+zdobj.title+'</h5>';
						}else{
							zdhtml+='<div class="artical"><h5></h5>';
						}
						if(zdobj.summary != null && zdobj.summary != "undefined"){
							zdhtml+='<a target="_blank" href="'+webPath+'/spChannel_sqDetail.action?id='+zdobj.aId+'&siteCode='+siteId+'" ><p>'+zdobj.summary+'<span>[详细]</span></p></a></div>';
						}else{
							zdhtml+='<p><span></span></p></div>';
						}
						
						if(slist != null){
							//无置顶  spaId
							$.each(slist, function(index, zdobjs) {
								var chId = zdobj.chId; 
								var chaId = zdobjs.chaId; 
								if(chId != null && chaId != null){
									if(chId == chaId){
										if(zdobjs.sTitle != null && zdobjs.sTitle != "undefined"){
											zdhtml+='<div class="list"><ul class="list-ul">';
											zdhtml+='<li class="clearfix"><i class="fl"></i><a class="fl" target="_blank" href="'+webPath+'/spChannel_sqDetail.action?id='+zdobjs.spaId+'&siteCode='+siteId+'" >'+zdobjs.sTitle+'</a><span class="fr">'+zdobjs.createTime+'</span></li>';
											zdhtml+='</ul></div>';
										}
									}
								}
							});
						}else{
						}
						
						zdhtml+='</div>';
					});
					
					$("#articleId").append(zdhtml);
				}
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}


//列表查询    
function spConfigurationTable() {
	$.ajax({
		type : "POST",
		url : webPath + "/spChannel_spConfiguration.action",
		data:{siteCode:siteId},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.success=='true') {
				$('#logo').attr("src",data.logo);
				$("#url").html(data.url);
				$("#bottomText").html(data.bottomText);
				$("#isBigData").val(data.displayModule);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

//最近7天的不连通率
function connectedRate(){
	$("#ltxContent").show();
	var timeList = new Array();
	var correntList = new Array();
	var min = 0;
	var max = 0;
	var myChart = echarts.init(document.getElementById('ltxContent'));
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/spChannel_monitoringData.action", 
        data:{siteCode:siteId},
        dataType:"json",
        async : false,  
        success : function(data) {
       	 timeList=data.timelist;
          	 correntList=data.countlist;
          	 
          	min = Math.min.apply(null, correntList);
          	max = Math.max.apply(null, correntList);
        }
     });
	 option = {
			    title: {
			    	text: '连通率(%)\n',
			    	textStyle:{
			    		fontSize: 14,
			    		fontWeight:'normal',
			    		fontFamily : '微软雅黑',
			    		color: '#0f171c'
			    	},
			    	padding:[22,5,5,5]
			    },
			    tooltip: {
			        trigger: 'axis',
					  formatter: function (params,ticket,callback) {
			                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
			                  for (var i = 0, l = params.length; i < l; i++) {
			                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'%';
			                  }
			                  setTimeout(function (){
			                      // 仅为了模拟异步回调
			                      callback(ticket, res);
			                  }, 0);
			              },
		    	    padding:15
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: true,
	                axisLabel: {
	                    show: true,
	                },
	                axisLine:{
	  	              lineStyle:{
	  	                  color:'#0f171c'
	  	              }
	  	            },
			        data: timeList
			    },
			    yAxis: {
			    	 min:min,
		             max:max,
			    	lineStyle:{
	                    color:'#0f171c'
	                },
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#0f171c'
	                    }
	                },
	                //show:false,
		            type : 'value',
		            axisLine:{
		              lineStyle:{
		                  color:'#0f171c'
		              }
		            }
			    },
			    series: [
			        {
			            name:'连通率',
			            type:'line', 
			            itemStyle : {  
	                        normal : {  
	                        	label : {show: true},
	                        	color:'#32bc98',  
	                            lineStyle:{  
	                                color:'#32bc98',
	                            }  
	                        }  
	                    }, 
			            data:correntList,
			        }
			    ]
			};
    myChart.setOption(option);
}

//最近7天的死链率
function deadRate(){
	$("#kyxContent").show();
	var timeList = new Array();
	var deadlist = new Array();
	var myChart = echarts.init(document.getElementById('kyxContent'));
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/spChannel_monitoringData.action",  
        data:{siteCode:siteId},
        dataType:"json",
        async : false,  
        success : function(data) {
       	 timeList=data.timelist;
       	 	deadlist=data.deadlist;
       	 	
       	 min = Math.min.apply(null, deadlist);
       	 max = Math.max.apply(null, deadlist);
        }
     });
	 option = {
			    title: {
			    	text: '可用性(%)\n',
			    	textStyle:{
			    		fontSize: 14,
			    		fontWeight:'normal',
			    		fontFamily : '微软雅黑',
			    		color: '#0f171c'
			    	},
			    	padding:[22,5,5,5]
			    },
			    tooltip: {
			        trigger: 'axis',
					  formatter: function (params,ticket,callback) {
			                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
			                  for (var i = 0, l = params.length; i < l; i++) {
			                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'%';
			                  }
			                  setTimeout(function (){
			                      // 仅为了模拟异步回调
			                      callback(ticket, res);
			                  }, 0);
			              },
		    	    padding:15
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: true,
	                axisLabel: {
	                    show: true,
	                },
	                axisLine:{
	  	              lineStyle:{
	  	                  color:'#0f171c'
	  	              }
	  	            },
			        data: timeList
			    },
			    yAxis: {
			    	 min:min,
		             max:max,
			    	lineStyle:{
	                    color:'#0f171c'
	                },
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#0f171c'
	                    }
	                },
	                //show:false,
		            type : 'value',
		            axisLine:{
		            	show:false,
		              lineStyle:{
		                  color:'#0f171c'
		              }
		            }
			    },
			    series: [
			        {
			            name:'可用性',
			            type:'line', 
			            itemStyle : {  
	                        normal : {  
	                        	label : {show: true},
	                        	color:'#32bc98',  
	                            lineStyle:{  
	                                color:'#32bc98',
	                            }  
	                        }  
	                    }, 
			            data:deadlist
			        }
			    ]
			};
    myChart.setOption(option);
}
//最近7天的更新量
function datesiteRate(){
	$("#gxContent").show();
	var timeList = new Array();
	var datesitelist = new Array();
	var myChart = echarts.init(document.getElementById('gxContent'));
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/spChannel_monitoringData.action",  
        data:{siteCode:siteId},
        dataType:"json",
        async : false,  
        success : function(data) {
       	 timeList=data.timelist;
       	 datesitelist=data.datesitelist;
       	 
      	 min = Math.min.apply(null, datesitelist);
       	 max = Math.max.apply(null, datesitelist);
        }
     });
	 option = {
			    title: {
			    	text: '更新量(条)\n',
			    	textStyle:{
			    		fontSize: 14,
			    		fontWeight:'normal',
			    		fontFamily : '微软雅黑',
			    		color: '#0f171c'
			    	},
			    	padding:[22,5,5,5]
			    },
			    tooltip: {
			        trigger: 'axis',
					  formatter: function (params,ticket,callback) {
			                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
			                  for (var i = 0, l = params.length; i < l; i++) {
			                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'条';
			                  }
			                  setTimeout(function (){
			                      // 仅为了模拟异步回调
			                      callback(ticket, res);
			                  }, 0);
			              },
		    	    padding:15
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: true,
	                axisLabel: {
	                    show: true,
	                },
	                axisLine:{
	  	              lineStyle:{
	  	                  color:'#0f171c'
	  	              }
	  	            },
			        data: timeList
			    },
			    yAxis: {
			    	min:min,
		            max:max,
			    	lineStyle:{
	                    color:'#0f171c'
	                },
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#0f171c'
	                    }
	                },
	                //show:false,
		            type : 'value',
		            axisLine:{
		            	show:false,
		              lineStyle:{
		                  color:'#0f171c'
		              }
		            }
			    },
			    series: [
			        {
			            name:'更新量',
			            type:'bar', 
						barMaxWidth:'30',
			            itemStyle : {  
	                        normal : {  
	                        	label : {show: true},
	                        	color:'#32bc98',  
	                            lineStyle:{  
	                                color:'#32bc98',
	                            }  
	                        }  
	                    }, 
			            data:datesitelist
			        }
			    ]
			};
    myChart.setOption(option);
}
