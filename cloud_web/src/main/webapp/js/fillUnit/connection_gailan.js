
$(function(){
	initAdvert();//初始化广告
	if(sessionStorage.getItem("key") == null || sessionStorage.getItem("key") ==undefined ){
		sessionStorage.setItem("key", 0);
	}else{
		sessionStorage.setItem("key", 1);
	}
	if(sessionStorage.getItem("key") == 0 ){
		alertView(isScan);
	}
	$("#cur-p").click(function(){
		$(".second-box").show();
		if($(".second-box").hasClass("on")){
			$(".second-box").removeClass("on");
			$(".second-box").hide();
		}else{
			$(".second-box").addClass("on");
			$(".second-box").show();
		}
		
	});
	if(isScan != 1){
		if(orgToInfo != "1"){
			$(".green_head_closeicon").click(function(){
				logOut();	
				return;
			});
			$(document).click(function(e){
				var _con = $('.modal'); // 设置目标区域
				if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
					logOut();
					return;
				}
			});
		}else{
			$(".green_head_closeicon").click(function(){
				sessionStorage.removeItem("key");
				window.close();
				return;
			});
			$(document).click(function(e){
				var _con = $('.modal'); // 设置目标区域
				if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
					sessionStorage.removeItem("key");
					window.close();
					return;
				}
			});
		}
	}
    if(iscost==0){
        $("#freeFont").html("昨天");
    }else{
        $("#freeFont").html("周期");
    }
    var sTime;
    $(".no-data-box").click(function(){
        clearTimeout(sTime)
        if($(this).find(".no-data").css("display")=="none"){
            $(this).find(".no-data").fadeIn();
        }
    });
    $(".no-data-box").mouseleave(function(){
        sTime=setTimeout(function(){
            if($(".no-data-box").find(".no-data").css("display")=="block"){
                $(".no-data-box").find(".no-data").fadeOut();
            }
        },5000);
    });
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
    $(".star-msg").hover(function(){
        $(this).find(".star-msg-main").fadeIn();
    },function(){
        $(this).find(".star-msg-main").fadeOut();
    });

   	//加载当前检测结果数据
	getFillUnitSum();
	
	//getTbSecuritySum();
	// 获取  安全扫描  最后一天的数据总数
	getLastData();
	//健康指数趋势
   	setTimeout(function(){
   		indexCountLine();
	},100);
	
	
   	//健康分析--默认打开网站连通性折线图
   	setTimeout(function(){
		connectionSite();
	},100);
	
	
	//链接可用性    单击事件
	$("#link_unuse_id").click(function(){
		setTimeout(function(){
			linkHomeUnuseSum();
		},100);
	});
	//网站连通性单击事件
	$("#conn_site_id ").click(function(){
		setTimeout(function(){
			connectionSite();
		},100);
		
	});
	//内容保障问题单击事件
	$("#security_bzh_id ").click(function() {
		if (iscost == 1) {// 付费版
			securityLine();
		} else {// 免费版
			securityLine2();
		}

	});
	
	//内容正确性单击事件
	$("#correct_cont_id ").click(function(){
		setTimeout(function(){
			correctContentLine();
		},100);
	});
	//内容更新单击事件
	$("#update_cont_id ").click(function(){
		setTimeout(function(){
			updateContent();
		},100);
	});
	//网站安全
	if((orgToInfo==1 && isOrgSafetyService==1) || orgToInfo!=1){
		$("#safeQuestion ").click(function(){
			setTimeout(function(){
				safeQuestion();
			},10);
		});
	}else{
//		$(".databox.span2").css("width","18%");
	}

	
	$("input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	$("#linkAll_radio_id").on('ifClicked', function(event){
		document.getElementById("linkHomeUnuse_id").style.display ="none";
 		document.getElementById("linkAllUnuse_id").style.display ="block";
 		
 		setTimeout(function(){
			linkAllUnuseSum();
		},100);
 		
	});
	$("#linkHome_radio_id").on('ifClicked', function(event){
		document.getElementById("linkAllUnuse_id").style.display ="none";
 		document.getElementById("linkHomeUnuse_id").style.display ="block";
 		
 		setTimeout(function(){
			linkHomeUnuseSum();
		},100);
 		
	});
	
});
//新菜单引导关闭
function closeYindao(){
	
	$.ajax({
			type : "POST",
			url :webPath+"/databaseInfo_updateGuiteState.action",
			data:{
				siteCode:siteCode
			},
			async : false,
			dataType:"json",
			success : function(data) {
				$(document.body).css({
					   "overflow-x":"auto",
					   "overflow-y":"auto"
				});
				//修改状态成功后隐藏
				$("#walkthrough-content").hide();
				$("#jpWalkthrough").hide();
				$("#jpwOverlay").hide();
			}
		});
}
//概览页面点击二维码图片  动态生成临时二维码
/*function getErweiImage(){
	
    $.ajax({
    	type:"post",
    	async:false,
       url:webPath+"/fillUnit_getErWeiImgeUrl.action",
       dataType:"json",
       success : function(data){
    	   if(data.errorMsg){
    		   alert(data.errorMsg);
    	   }else{
    		   $("#erwei_image_id").attr("style","width:240px; height:240px; position:absolute; border:1px solid #000; display:block;");
    		   $("#erwei_image_id").html("<i onclick='closeWindows()'style='width: 25px; height: 25px; background:#4CB050; color:#fff;position: absolute; right: -26px; top: 1px; display: inline-block; text-align: center; line-height: 25px; font-style: normal;'>X</i><img style='width:100%; height:100%;' src='"+data.erweiUrl+"'></img>");
    	   }
       },error:function(data){
    	   alert(data.errorMsg);
       }
    });
	
}

//关闭二维码窗口
function closeWindows(){
	$("#erwei_image_id").attr("style","display:none");
}*/


function  ConditionsSelect(){
	   $(':radio').on('ifChecked', function(event){
	 	/*  table_update_home.fnReloadAjax(webPath+ "/updateHome_updateHomePage.action"); */
		});
	}
function getLastData(){
	//网站安全
	// 有  三种状态   付费执行中    已过期    未检测   // 组织单位  跳转过来的状态
	var flag = 2;
	// 组织单位  付费点击过来  所有的数据都可以查看（只有组织付费的开通此业务的 组织单位才可以点击跳转过来）
	if(orgToInfo == 1 && isOrgSafetyService==1 ){
		flag = 1;
	}

	// 对于填报单位自己的  付费的数据处理
	if(orgToInfo != 1 && isSafetyService==1 ){
		flag = 2;
	}
	// 对于过期的数据处理
	if(orgToInfo != 1 && isSafetyService==2 ){
		flag = 3;
	}
	var user = {
			flag:flag
			};
    $.ajax({
    	type:"post",
    	data:user,
    	async:false,
       url:webPath+"/monitorSilentResult_getLastDay.action",
       dataType:"json",
       success : function(data){
    	   if(data.success == "true"){
    		   $("#question_span_id").html(data.securityQuestionNum);//安全问题总数
    		   $("#quesSumId").html(data.securityQuestionNum);//安全问题总数
    		   $("#riskNum").html(data.riskNum);//安全问题总数
    	   }
       },error:function(data){
       }
    });
}
//加载当前检测结果数据 健康指数统计数据获取  5个色块统计数据获取
function getFillUnitSum(){
    $.ajax({
    	type:"post",
    	async:false,
       url:webPath+"/fillUnit_homePageSearch.action",
       dataType:"json",
       success : function(data){
    	   if(data.errorMsg){
//    		   alert(data.errorMsg);
    	   }else{
	   	       	var convertScores=data.convertScores;//昨天折算分数
		       	var totalScores=data.totalScores;//昨天健康指数
		       	var gtTotalSum=data.gtTotalSum;//健康指数领先全国的百分比
		       	var upTotal=data.upTotal;//相比前天健康分数增加
		       	var upTotalPer=data.upTotalPer;//相比前天健康分数增加的百分比
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
		       	//网站健康度
		       	if(convertScores>=90){
		       		$("#convertScores_you_id").html("优");
		               //$("#convertScores_you_id").parents(".z_box1").css("background-color","#4cb050");
		       	}else if(convertScores>=70 && convertScores<90){
		       		$("#convertScores_you_id").html("良");
		               //$("#convertScores_you_id").parents(".z_box1").css("background-color","#00bcd5");
		       	}else if(convertScores>=60 && convertScores<70){
		       		$("#convertScores_you_id").html("中");
		               //$("#convertScores_you_id").parents(".z_box1").css("background-color","#ff9700");
		       	}else {
		       		$("#convertScores_you_id").html("差");
		               //$("#convertScores_you_id").parents(".z_box1").css("background-color","#f44236");
		       	}
		       	//昨天健康指数
		       	$("#totalScores_id").html(totalScores);
		       	//健康指数领先全国的百分比
		       	if(gtTotalSum == 0){
		       		gtTotalSum = "1";
		       	}
		       	$("#gtTotalSum_id").html("领先全国  "+gtTotalSum+"% 的政府网站");
		
		       	//相比前天统计数据
		       	if(upTotal>0){
		               $("#pre_scores_id").html("<span class='font1'>相比昨天</span><i></i>"+upTotal+"&nbsp;&nbsp;(+"+upTotalPer+")");
		       	}else{
		       		$("#pre_scores_id").addClass("down");
		               $("#pre_scores_id").html("<span class='font1'>相比昨天</span><i></i>"+upTotal+"&nbsp;&nbsp;("+upTotalPer+")");
		       	}
		       	
		       	//5个色块数据
		       	//连通性
		       	$("#connectionSum_id").html(data.connectionSum);
		       	$("#coHomeId").html(data.connectionHome);
		       	$("#coChannelId").html(data.connectionChannel);
		       	$("#coBusinessId").html(data.connectionBus);
		       	
		       	//链接可用性
		       	$("#linkSum_id").html(data.linkNum);
		       	$("#lkHomeId").html(data.linkHome);
		       	if(data.linkAll=="-1"){
		       		$("#linkAllSum").html("未监测");
		       	}else{
		       		$("#linkAllSum").html(data.linkAll);
		       	}
		       	
		       	
		       	//内容保障问题
		       	$("#secu_span_id").html(data.securityQuestion);
		       	$("#seHomeId").html(data.securityHome);
		       	
		       	
		        if(iscost==0){
		        	$("#sBasic").html("-");
		        	$("#seChannelId").html("未监测");
		        }else{
		        	$("#sBasic").html(data.securityBasic);
		        	$("#seChannelId").html(data.securityChannel);
		        }
		       	
		       	if(data.securityBlank=="-1"){
		       		$("#sBlank").html("-");
		       	}else{
		       		$("#sBlank").html(data.securityBlank);
		       	}
		       	if(data.securityBlank=="-1"){
		       		$("#sResponse").html("-");
		       	}else{
		       		$("#sResponse").html(data.securityResponse);
		       	}
		       	if(data.securityBlank=="-1"){
		       		$("#sService").html("-");
		       	}else{
		       		$("#sService").html(data.securityService);
		       	}
		       	
		       	//疑似错别字
		       	if("1"==iscost){
			       	$("#content_span_id").html(data.contentSum);
			       	$("#contcorrectId").html(data.contentSum);
		       	}else{
			       	$("#content_span_id").html("0");
			       	$("#contcorrectId").html("0");
		       	}
		       	
		       	//内容更新
		    	$("#updateContentSum_id").html(data.updateContentSum);
		       	$("#upHomeTbId").html(data.updateHome);
		       	$("#upChannelTbId").html(data.updateChannel);
		       	
		       	$("#quesSumId").html(data.securitySum);
    	   }
       },error:function(data){
//    	   alert(data.errorMsg);
       }
    });
}

//健康指数趋势
function indexCountLine(){
	var allIndexlist = new Array();
	var datelist = new Array();
	var indexlist = new Array();
	var siteName="";
	
	$.ajax({  
		type : "POST",    
		url : webPath+"/fillUnit_indexCountLine.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			
			allIndexlist=data.allSiteList;
			datelist=data.datelist;
			indexlist=data.siteList;
			if(data.siteName.length>20){
				siteName=data.siteName.substring(0,20)+"...";
			}else{
				siteName=data.siteName;
			}
		}
	});
	
	var myChart = echarts.init(document.getElementById('index_count_line_id'));
    option = {
		  	title : {
		        text: '健康指数趋势',
		        x:'left',
		        y:'10',
		        textStyle:{
		        	align:'right',
		        	fontFamily:'微软雅黑',
		        	color:'#34495e',
		        	fontWeight:'500'
		        }
		    },
    		color:['#7BB6ED','#94ED83'],
			dataZoom : {
				show : false,
				realtime : true,
				height: 20,
				start : 100,
				end : 0
			},
    	    legend: {
    	    	 x: 'right',
    	    	 y: '15',
    	    	 padding:3,
    	    	 itemGap:30,
    	         data:['全国政府网站',siteName]
    	    },
 		    tooltip : {
 		        trigger: 'axis',
	 		    borderColor: '#48b',
	            axisPointer: {
		            color: 'rgba(150,150,150,0.3)',
		            width: 'auto',
		            type: 'default'
	           	},
		       	formatter:function(a,b,c){
			        	var res = "";
			        	if(a.length == 2){
			        		res = a[0][1]+"<br>"+a[0][0]+"："+a[0][2]+"<br>"+a[1][0]+"：\t"+a[1][2]+"<br>";
			        	} else {
			        		res = a[0][1]+" <br/> "+a[0][0]+"："+a[0][2];
			        	}
			        	return res;
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
 		            data : datelist
 		        }
 		    ],
 		    yAxis : [
 		        {
 		        	name:'分数',
 		        	nameTextStyle:{
 		        		color:'black'
 		        	},
  		            axisLine:{
  		                lineStyle: {
  		                    color: '#ffffff',
  		                    width:1
  		                },
  		                type : 'value'
  		            },
  		            min:400,
  		            splitNumber:5
  		            
 		        }
 		    ],
 		    grid:{
 		    	 x:37,
 		    	 x2:0,
	  	    	borderColor:'#fff'
	  	    },
 		    series : [
 		        {
 		        	symbol:'emptyCircle',
 		        	symbolSize:4,
 		            name:'全国政府网站',
 		            type:'line',
 		            data:allIndexlist
 		           
 		        },
 		       {
 		        	symbol:'emptyCircle',
 		        	symbolSize:4,
 		            name:siteName,
 		            type:'line',
 		            data:indexlist
 		           
 		        }
 		    ]
 		};
     myChart.setOption(option);
}
//网站连通性--健康分析折线图
function connectionSite(){
	//网站连通性
	var homelist = new Array();
	var channellist = new Array();
	var scanTimelist = new Array();
	var businesslist = new Array();
	 $.ajax( {  
	     type : "POST",    
	     url : webPath+"/fillUnit_connectionAllBar.action", 
	     dataType:"json",
	     async : false,  
	     success : function(data) {
	    	 homelist=data.homelist;
	    	 channellist=data.channellist;
	    	 scanTimelist=data.scanTimelist;
	    	 businesslist=data.businesslist;
	     }
	  });
	var myChart = echarts.init(document.getElementById('conn_site_line_id'));
	option = {
			color:['#FA524F','#46CDE1','#63D677'],
			dataZoom : {
		        show : false,
		        realtime : true,
		        height: 20,
		        start : 100,
		        end : 0
		    },
		    legend: {
		    	x: 'right',
		    	y: '30',
		    	itemGap:60,
		    	padding:40,
		        data:['首页','业务系统','关键栏目']
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
	    	                res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'次';
	    	            }
	    	            setTimeout(function (){
	    	                // 仅为了模拟异步回调
	    	                callback(ticket, res);
	    	            }, 0);
	    	        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            axisLine:false,  
		            splitLine:false,
		            axisTick:false,
		            data : scanTimelist
		        }
		    ],
		    grid:{
	  	    	borderColor:'#fff',
	  	    	x:38,
	  	    	x2:25
	  	    },
		    yAxis : [
		        {
		        	name:'不连通数  (次)\n',
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
		    series : [
		        {
		        	barMaxWidth:'10',
		            name:'首页',
		            type:'bar',
		            data:homelist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'业务系统',
		            type:'bar',
		            data:businesslist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'关键栏目',
		            type:'bar',
		            data:channellist
		           
		        }
		    ]
		};
	 myChart.setOption(option);
}
//网站安全条形图
function safeQuestion(){
	//网站安全
	// 有  三种状态   付费执行中    已过期    未检测   // 组织单位  跳转过来的状态
	var flag = 2;
	// 组织单位  付费点击过来  所有的数据都可以查看（只有组织付费的开通此业务的 组织单位才可以点击跳转过来）
	if(orgToInfo == 1 && iscost==1 ){
		flag = 1;
	}

	// 对于填报单位自己的  付费的数据处理
	if(orgToInfo != 1 && isSafetyService==1 ){
		flag = 2;
	}
	// 对于过期的数据处理
	if(orgToInfo != 1 && isSafetyService==2 ){
		flag = 3;
	}
	var user = {
			flag:flag
			};
	// 对于未开通的数据处理
	var weaklist = new Array();
	var horselist = new Array();
	var updatelist = new Array();
	var darklist = new Array();
	var outlist = new Array();
	var scanTimelist = new Array();
	 $.ajax( {  
	     type : "POST",    
	     url : webPath+"/monitorSilentResult_getSevenDatas.action", 
	     dataType:"json",
	     async : false,  
	     data:user,
	     success : function(data) {
	    	 if(data.success == 'true'){
	    		 weaklist = data.weaklist ;
	    		 horselist = data.horselist ;
	    		 updatelist = data.updatelist ;
	    		 darklist = data.darklist ;
	    		 outlist = data.outlist ;
	    		 scanTimelist = data.scanTimelist ;
	    	 }
	     }
	  });
	var myChart = echarts.init(document.getElementById('safe_question_id'));
	option = {
			color:['#9c28b1','#ea1e63','#607d8b','#9e9e9e','#795547'],
			dataZoom : {
		        show : false,
		        realtime : true,
		        height: 20,
		        start : 100,
		        end : 0
		    },
		    legend: {
		    	x: 'right',
		    	y: '30',
		    	itemGap:60,
		    	padding:40,
		        data:['网站脆弱性','网站挂马','变更/篡改','网站暗链','网站泄露']
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
	    	                res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'个';
	    	            }
	    	            setTimeout(function (){
	    	                // 仅为了模拟异步回调
	    	                callback(ticket, res);
	    	            }, 0);
	    	        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            axisLine:false,  
		            splitLine:false,
		            axisTick:false,
		            data : scanTimelist
		        }
		    ],
		    grid:{
	  	    	borderColor:'#fff',
	  	    	x:38,
	  	    	x2:25
	  	    },
		    yAxis : [
		        {
		        	name:'   安全问题数  (个)\n',
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
		    series : [
		        {
		        	barMaxWidth:'10',
		            name:'网站脆弱性',
		            type:'bar',
		            data:weaklist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'网站挂马',
		            type:'bar',
		            data:horselist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'变更/篡改',
		            type:'bar',
		            data:updatelist
		           
		        },
		        {
		        	barMaxWidth:'10',
		            name:'网站暗链',
		            type:'bar',
		            data:darklist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'网站泄露',
		            type:'bar',
		            data:outlist
		           
		        }
		    ]
		};
	 myChart.setOption(option);
}
//全站链接不可用
function linkAllUnuseSum(){
    var unuseLinkNum = [];
    var periodNumDate =[];
    var scanDateStr = new Array();
    var oldServicePeriod = new Array();
   // var yearAndMouth=[];
    var myChart_line = echarts.init(document.getElementById('linkAllUnuse_id'));
    $.ajax( {  
        type : "POST",  
        url : webPath+"/fillUnit_linkAllUnuseRebuild.action",  
        dataType:"json",
        async : false,  
        success : function(data) {
             for (var i = 0; i < data.length; i++) {
            	 periodNumDate.push("第"+data[i].periodNum+"期");
            	 unuseLinkNum.push(data[i].websiteSum);
            	 scanDateStr.push(data[i].periodStr);
            	 oldServicePeriod.push(data[i].oldServicePeriod);
			}
        }
       });
    option = {
    		color:['#4CD2D1'],
     		dataZoom : {
     	        show : true,
     	        realtime : true,
     	        height: 20,
     	        start : 100,
     	        end : 60
     	    },
  		    tooltip : {
  		        trigger: 'axis',
  		        borderColor: '#48b',
  		        axisPointer: {
  		        color: 'rgba(150,150,150,0.3)',
                width: 'auto',
                type: 'default'
                },
                //formatter:"{b}<br>全站{a}：\t{c}个",
                formatter: function (params,ticket,callback) {
              	  var res = params[0].name;
	                  for (var i = 0, l = params.length; i < l; i++) {
	                	  res += '<br/>全站' + params[i].seriesName + '：' + params[i].value+'个';
	                	  var index = 1;
	                	  if(params[0].name.length == 3){
	                		  index = params[0].name.substring(1,2);
	                	  }else if(params[0].name.length == 4){
	                		  index = params[0].name.substring(1,3);
	                	  }else if(params[0].name.length == 5){
	                		  index = params[0].name.substring(1,4);
	                	  }
	                	  
	                	  res += "<br/>扫描时间：" + scanDateStr[index-1];
	                	  if(oldServicePeriod[index-1] == 1){  //老统计规则
	                		  res += "<br/>注释： 此数据统计为[所有不可用链接]的总数量，不区分站内外和确定疑似死链";
	                	  }else if(oldServicePeriod[index-1] == 2){
	                		  res += "<br/>注释： 此数据统计为[站内确定不可用链接]的总数量";
	                	  }
	                  }
	                  setTimeout(function (){
	                      // 仅为了模拟异步回调
	                      callback(ticket, res);
	                  }, 0);
	              },
	            padding:5
  		    },
  		    calculable : true,
  		    xAxis : [
  		        {
  		        	splitLine:false,
  		        	axisLine:false,  
  		        	axisTick:false,
  		            type : 'category',
  		            data : periodNumDate
  		        }
  		    ],
	  		grid:{
	  			borderColor:'#fff',
	  			x:50,
	  			x2:40
	  	    },
  		    yAxis : [
  		        {
  		        	name:'不可用链接数  (个)\n',
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
  		    series : [
  		        {
  		        	symbol:'emptyCircle',
  		        	symbolSize:4,
  		            name:'不可用链接数',
  		            type:'line',
  		            data:unuseLinkNum
  		           
  		        }
  		    ]
    	};
    myChart_line.setOption(option,true);
}

//首页链接不可用
function linkHomeUnuseSum(){

    var unuseLinkHomeNum = [];
    var mouthHomeDate =[];
   // var yearAndMouth=[];
    var myChart_line = echarts.init(document.getElementById('linkHomeUnuse_id'));
    $.ajax( {  
        type : "POST",  
        url : webPath+"/fillUnit_linkHomeUnuse.action",  
        dataType:"json",
        async : false,
        success : function(homeData) {
        	 var homelist=homeData;
             for (var j = 0; j < homelist.length; j++) {
            	 mouthHomeDate.push(homelist[j].scanDate);
            	 unuseLinkHomeNum.push(homelist[j].errorNum);
			}
        }
       });
    option = {
    		color:['#BAAEE0'],
     		dataZoom : {
     	        show : false,
     	        realtime : true,
     	        height: 20,
     	        start : 100,
     	        end :0
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
	                      res += '<br/>首页' + params[i].seriesName + '：' + params[i].value+'个';
	                  }
	                  setTimeout(function (){
	                      // 仅为了模拟异步回调
	                      callback(ticket, res);
	                  }, 0);
	              },
	            padding:15
  		    },
  		    calculable : false,
  		    xAxis : [
  		        {
 		        	splitLine:false,
  		        	axisLine:false,  
  		        	 axisTick:false,
  		            type : 'category',
  		            data : mouthHomeDate
  		        }
  		    ],
		    grid:{
	  	    	borderColor:'#fff',
	  	    	x:50,
	  	    	x2:40,
	  	    	y:40
	  	    	
	  	    },
  		    yAxis : [
  		        {
  		        	name:'不可用链接数  (个)\n',
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
  		    series : [
  		        {
 		        	symbol:'emptyCircle',
  		        	symbolSize:4,
  		            name:'不可用链接数',
  		            type:'line',
  		            data:unuseLinkHomeNum
  		           
  		        }
  		    ]
  		};
    myChart_line.setOption(option);
}

//内容更新
function updateContent(){

    var updateHomeSum = [];
    var updateChannelSum = [];
    var updateChannelDate =[];
   // var yearAndMouth=[];
    var myChart_line = echarts.init(document.getElementById('update_chnn_line_id'));
    $.ajax( {  
        type : "POST",  
        url : webPath+"/fillUnit_updateChAndHome.action",  
        dataType:"json",
        async : false,  
		success : function(data) {
			updateChannelDate=data.dateList;
			updateHomeSum=data.homeList;
			updateChannelSum=data.channelList;
		}
       });
    option = {
    		color:['#7BB6ED','#94ED83'],
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
	                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'条';
	                  }
	                  setTimeout(function (){
	                      // 仅为了模拟异步回调
	                      callback(ticket, res);
	                  }, 0);
	              },
	           padding:15
 		    },
    	    dataZoom : {
    	        show : false,
    	        realtime : true,
    	        height: 20,
    	        start : 100,
    	        end : 0
    	    },
    	    legend: {
    	    	x: 'right',
    	    	y: '30',
    	    	itemGap:60,
    	    	padding:40,
    	        data:['首页','栏目']
    	    },
      	    calculable:false,
    	    xAxis : [
    	        {
    	        	splitLine:false,
    	        	axisLine:false,
    	        	axisTick:false,
    	            type : 'category',
    	            data : updateChannelDate
    	        }
    	    ],
    	    grid:{
	  	    	borderColor:'#fff',
	  	    	x:40,
	  	    	x2:40
	  	    },
    	    yAxis : [
    	        {
    	        	name:'更新数量  (条)\n',
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
    	    series : [
    	        {
 		        	symbol:'emptyCircle',
 		        	symbolSize:4,
    	            name:'首页',
    	            type:'line',
    	            data:updateHomeSum
    	        },
    	        {
 		        	symbol:'emptyCircle',
 		        	symbolSize:4,
    	            name:'栏目',
    	            type:'line',
    	            data:updateChannelSum
    	        }
    	    ]
  
    	};
    myChart_line.setOption(option,true);
}


//内容正确性--健康分析折线图
function correctContentLine(){
  var correctContentListSum = [];
  var correntDate =[];
 // var yearAndMouth=[];
  var myChart_line = echarts.init(document.getElementById('correct_content_id'));
  $.ajax( {  
      type : "POST",  
      url : webPath+"/fillUnit_correctContentLine.action",  
      dataType:"json",
      async : false,  
		success : function(data) {
			
			var correntList=data.correntList;
			var timeList=data.timeList;
			
			for(var i=0;i<timeList.length;i++){
				correntDate.push(timeList[i]);
			}
			for(var j=0;j<correntList.length;j++){
				correctContentListSum.push(correntList[j]);
			}
		}
     });
  option = {
  		dataZoom : {
  	        show : false,
  	        realtime : true,
  	        height: 20,
  	        start : 100,
  	        end : 0
  	    },
		    tooltip : {
		        trigger: 'axis',
		        borderColor: '#48b',
              axisPointer: {
	                color: 'rgba(150,150,150,0.3)',
	                width: 'auto',
	                type: 'default'
              },
              formatter:"{b}<br>内容正确性：  {c}个"
		    },
		    calculable : true,
		    xAxis : [
		        {
		        	splitLine:false,
		        	axisLine:false,
		        	axisTick:false, 
		            type : 'category',
		            data : correntDate
		        }
		    ],
		    yAxis : [
		        {
		        	name:'问题数  (个)\n',
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
	  	    	x:40,
	  	    	x2:40
	  	    },
		    series : [
		        {
		        	name:'内容正确性',
		        	symbol:'emptyCircle',
		        	symbolSize:4,
		            type:'line',
		            data:correctContentListSum
		           
		        }
		    ]
		};
  myChart_line.setOption(option,true);
}
//内容保障性--健康分析折线图--付费版（按周期展示数据）
function securityLine(){

	 setTimeout(function(){
	var securityList = new Array();
	var homeMonthList = new Array();
	var securityServiceList = new Array();
	var homeNumList = new Array();
	var blackList = new Array();
	var noChannelList = new Array();
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/fillUnit_securityLine.action",  
        dataType:"json",
        async : false,  
        success : function(data) {
       	 homeMonthList=data.dateList;
       	 homeNumList=data.homeList;
       	 noChannelList=data.channelList;
       	 blackList=data.blankList;
       	 securityList=data.resposeList;
       	 securityServiceList=data.serviceList;
        },error:function(data){
			 // console.log(data.errorMsg);
        }
     });
	var myChart = echarts.init(document.getElementById('security_id'));
   option = {
   		dataZoom : {
    	        show : true,
    	        realtime : true,
    	        height: 20,
    	        start : 100,
    	        end : 60
    	    },
   	    legend: {
   	    	x: 'right',
   	    	y: '30',
   	    	itemGap:30,
   	    	padding:25,
   	        data:['首页信息不更新','栏目信息不更新','空白栏目','互动回应差','服务不实用']
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
		              }
		    },
		    calculable : true,
		    xAxis : [
		        {
		        	splitLine:false,
		        	axisLine:false,
		        	axisTick:false,
		            type : 'category',
		            data : homeMonthList
		        }
		    ],
		    yAxis : [
		        {
		        	name:'问题数  (个)\n',
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
		    	x2:50,
	  	    	borderColor:'#fff'
	  	    },
		    series : [
		        {
		        	symbol:'emptyCircle',
	 		        symbolSize:4,
		            name:'首页信息不更新',
		            type:'line',
		            data:homeNumList
		           
		        },
		       {
		        	symbol:'emptyCircle',
	 		        symbolSize:4,
		            name:'栏目信息不更新',
		            type:'line',
		            data:noChannelList
		           
		        },
		       {
		        	symbol:'emptyCircle',
	 		        symbolSize:4,
		            name:'空白栏目',
		            type:'line',
		            data:blackList
		           
		        },
		       {
		        	symbol:'emptyCircle',
	 		        symbolSize:4,
		            name:'互动回应差',
		            type:'line',
		            data:securityList
		           
		        },
		       {
		        	symbol:'emptyCircle',
	 		        symbolSize:4,
		            name:'服务不实用',
		            type:'line',
		            data:securityServiceList
		           
		        }
		    ]
		};
    myChart.setOption(option);
	 },100);

	
	
}

//内容保障性--健康分析堆积图--免费版（按天数展示数据）
function securityLine2(){
	 setTimeout(function(){
			var homeMonthList = new Array();
			var homeNumList = new Array();
			var noChannelList = new Array();
			 $.ajax( {  
		        type : "POST",  
		        url : webPath+"/fillUnit_securityLine.action",  
		        dataType:"json",
		        async : false,  
		        success : function(data) {
		        	
		       	 homeMonthList=data.dateList;
		       	 homeNumList=data.homeList;
		       	 noChannelList=data.channelList;
		        },error:function(data){
					 // console.log(data.errorMsg);
		        }
		     });
			var myChart = echarts.init(document.getElementById('security_id'));
		   option = {
		   		dataZoom : {
		    	        show : false,
		    	        realtime : true,
		    	        height: 20,
		    	        start : 100,
		    	        end : 60
		    	    },
		   	    legend: {
		   	    	x: 'right',
		   	    	y: '30',
		   	    	itemGap:30,
		   	    	padding:25,
		   	        data:['首页信息不更新','栏目信息不更新']
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
				              }
				    },
				    calculable : true,
				    xAxis : [
				        {
				        	splitLine:false,
				        	axisLine:false,
				        	axisTick:false,
				            type : 'category',
				            data : homeMonthList
				        }
				    ],
				    yAxis : [
				        {
				        	name:'问题数  (个)\n',
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
				    	x2:50,
			  	    	borderColor:'#fff'
			  	    },
				    series : [
				        {
				        	symbol:'emptyCircle',
			 		        symbolSize:4,
				            name:'首页信息不更新',
				            type:'line',
				            data:homeNumList
				           
				        },
				       {
				        	symbol:'emptyCircle',
			 		        symbolSize:4,
				            name:'栏目信息不更新',
				            type:'line',
				            data:noChannelList
				           
				        }
				    ]
				};
		    myChart.setOption(option);
			 },100);
}

/**
 * 获得填报单位首页内容保障问题色块  数据
 */
function getTbSecuritySum(){
	 $.ajax( {  
	        type : "POST",  
	        url : webPath+"/fillUnit_getTbSecuritySum.action",  
	        dataType:"json",
	        async : false,  
	        success : function(data) {
	        	$("#secu_span_id").html(data.securitySum);
	        },error:function(data){
				 // console.log(data.errorMsg);
	        }
	     });
}

//跳转
function jumpPage(uriThree){
	var uri = webPath + "/fillUnit_gailan.action";
	changeCookie(2,uri,null,uriThree);
}