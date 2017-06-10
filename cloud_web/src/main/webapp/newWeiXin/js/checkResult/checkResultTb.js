
var isCost=2;
var isAdvance=2;
var siteCode="";
var openId="";
var servicePeroidId="";
var convertScores="";
$(function(){
	FastClick.attach(document.body);
	
	pingTurn();

	isCost=$("#is_cost_id").val();
	isAdvance=$("#is_advance_id").val();
	siteCode=$("#siteCode_id").val();
	openId=$("#open_id").val();
	servicePeroidId=$("#servicePeroidId_id").val();

    //页面数据初始化
    initPageData();
});

	//屏幕旋转事件
	function pingTurn(){
		var evt = "onorientationchange" in window ? "orientationchange" : "resize";
		window.addEventListener(evt, function() {
			 initIndexRound(convertScores,new Date().getTime());
		}, false);
	}
	//页面数据初始化
	function initPageData(){
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/newToken_checkResultTB.action?siteCode="+siteCode+"&isAdvance="+isAdvance+"&servicePeroidId="+servicePeroidId,
	        dataType:"json",
	        success : function(data){
	        	
	           var resultMap=data;//后台返回数据
	           if(resultMap.errormsg){
	           		alert(resultMap.errormsg);
	           }else{
	        	   //控制页面显示隐藏
	        	   contrlShowOrHide();
	        	   //通用
	        	    convertScores=resultMap["convertScores"];//折算分数
	        	   var indexCount=resultMap["indexCount"];//健康指数
	        	   var leadAvgRate=resultMap["leadAvgRate"];//健康指数领先全国
	        	   
	        	   $("#indexCount_id").html(indexCount);
	        	   $("#leadAvgRate_id").html(leadAvgRate);
	        	   
	        	   //健康指数半圆加载
	        	   initIndexRound(convertScores);
	        	   
	        	   //免费版数据
	        	   var connHome=resultMap["connHome"];//首页不连通数
	        	   var updateHome=resultMap["updateHome"];//首页更新量
	        	   var securityHome=resultMap["securityHome"];//首页更新不及时
	        	   var linkHome=resultMap["linkHome"];//首页更新不及时
	        	   
	        	   $("#connHome_id").html(connHome+"次");
	        	   $("#updateHome_id").html(updateHome+"条更新");
	        	   $("#linkHome_id").html(linkHome+"个问题");
	        	   if(securityHome =="0"){//首页更新及时
	        		   $("#securityHome_id").html("更新及时");
	        	   }else{
	        		   $("#securityHome_id").html("更新不及时");
	        	   }
	        	   
	        	   
	        	   //标准
	        	   if(isCost==1){
	            	   var connChannel=resultMap["connChannel"];//关键栏目不连通数
	            	   var connBusiness=resultMap["connBusiness"];//业务系统不连通数
	            	   var updateChannel=resultMap["updateChannel"];//栏目更新量
	            	   var securityChannel=resultMap["securityChannel"];//栏目更新不及时
	            	   var contCorrectNum=resultMap["contCorrectNum"];//错别字
	            	   
	            	   $("#connChannel_id").html(connChannel+"个问题");
	            	   $("#connBusiness_id").html(connBusiness+"个问题");
	            	   $("#updateChannel_id").html(updateChannel+"个更新");
	            	   $("#securityChannel_id").html(securityChannel+"个问题");
	            	   
	            	   //内容正确性
	        		   $("#contCorrectNum_id").html(contCorrectNum+"个问题");
	        	   }
	        	   //高级
	        	   if(isAdvance==1){
	        		   
	            	   var linkAll=resultMap["linkAll"];//全站死链
	            	   var securityBlank=resultMap["securityBlank"];//空白栏目
	            	   var securityResponse=resultMap["securityResponse"];//互动回应差
	            	   var securityService=resultMap["securityService"];//服务不实用
	            	   var securityFatalError=resultMap["securityFatalError"];//严重问题
	            	   
	            	   $("#linkAll_id").html(linkAll+"个问题");
	            	   $("#securityBlank_id").html(securityBlank+"个问题");
	            	   $("#securityResponse_id").html(securityResponse+"个问题");
	            	   $("#securityService_id").html(securityService+"个问题");
	            	   $("#securityFatalError_id").html(securityFatalError+"个问题");
	        	   }
	           }
	        },error : function(data){
	        	alert("查询数据异常");
	        	return;
	        }
	 });
	}
	//健康指数半圆加载
	function initIndexRound(convertScores,timesStr){
		//获取屏幕的宽度
	    var clientWidth = 0;
	    //安卓系统获取屏幕宽度并不像ios那样灵敏，有延迟，因此需要等待
		setTimeout(function(){
			clientWidth=document.body.clientWidth; 
		    //根据设计图中的canvas画布的占比进行设置
		    var canvasWidth = Math.floor(clientWidth*372/640);
	        document.getElementById("chart-gauge").setAttribute("width",canvasWidth+'px');
	        document.getElementById("chart-gauge").setAttribute("height",canvasWidth+'px');
		    //半圆进度
		    var gauge = new Donut(document.getElementById("chart-gauge"));
		    $("#chart-gauge").attr("style","width: "+canvasWidth+"px; height:"+canvasWidth+" px;")
		    gauge.lineWidth = 16;
		    gauge.options = {
		        lineWidth: 0.05,
		        colorStart: "#fff",
		        colorStop: "#fff",
		        strokeColor: "#4eca8e",
		        shadowColor: "#e1e1e1",
		        angle: 0.25
		    };
		    gauge.set(convertScores);
        },100);
	}
	//控制页面显示隐藏
	function contrlShowOrHide(){
		if(isCost==1){//标准版
			$("#shendu_id").show();
			if(isAdvance==1){//高级版
				$("#isAdvance_id1").show();
				$("#isAdvance_id2").show();
				$("#isAdvance_id3").show();
			}else{
				$("#isAdvance_id1").hide();
				$("#isAdvance_id2").hide();
				$("#isAdvance_id3").hide();
			}
		}else{
			$("#shendu_id").hide();
		}
	}
	function getNumberByreg(str){
		return str.replace(/[^0-9]+/g, '')
	}
	
	//首页不连通
	function connHomeTb(){
		window.location.href=webPath+"/freeToken_connHomeTbIndex.action?siteCode="+siteCode;
	}
	//首页更新量点击事件
	function updateHomeTb(){
		var updateHome=getNumberByreg($("#updateHome_id").html());
		var siteName=$("#title_id").html();
		window.location.href=webPath+"/freeToken_updateHomeTbIndex.action?siteCode="+siteCode+"&siteName="+siteName
				+"&updateHome="+updateHome;
	}
	
	//首页更新不及时点击事件
	function securityHomeTb(){
		var name=$("#title_id").html();
		window.location.href=webPath+"/freeToken_securityHomeTbIndex.action?siteCode="+siteCode+"&siteName="+name;
		
	}
	
	//首页不可用链接点击事件
	function linkHomeTb(){
		var name=$("#title_id").html();
		var linkHome=getNumberByreg($("#linkHome_id").html());
		window.location.href=webPath+"/freeToken_linkHomeTbIndex.action?siteCode="+siteCode+"&linkHome="+linkHome+"&siteName="+name;
	}
	
	//关键栏目不连通----跳转到填报单位
	function connChannelTb(){
		var name=$("#title_id").html();
		var connChannel=getNumberByreg($("#connChannel_id").html());
		window.location.href=webPath+"/costToken_connChannelTbIndex.action?siteCode="+siteCode
		+"&connChannel="+connChannel+"&siteName="+name;
	}
	
	//业务系统不连通---跳转到填报单位
	function connBusinessTb(){
		var name=$("#title_id").html();
		var connBusiness=getNumberByreg($("#connBusiness_id").html());
		window.location.href=webPath+"/costToken_connBusinessTbIndex.action?siteCode="+siteCode
		+"&connBusiness="+connBusiness+"&siteName="+name;
	}
	//栏目内容更新
	function updateChannelTb(){
		var updateChannel=getNumberByreg($("#updateChannel_id").html());
		var name=$("#title_id").html();
		window.location.href=webPath+"/costToken_updateChannelTbIndex.action?siteCode="+siteCode+"&siteName="+name
		+"&updateChannel="+updateChannel;
	}
	
	//栏目更新不及时
	function securityChannelTb(){
		var name=$("#title_id").html();
		var notUpdateNum=getNumberByreg($("#securityChannel_id").html());
		
		window.location.href=webPath+"/costToken_securityChannelTbIndex.action?siteCode="+siteCode+"&notUpdateNum="+notUpdateNum+"&siteName="+name	;
	}
	
    //错别字
    function contCorrectTb(){
    	var name=$("#title_id").html();
    	var contCorrectNum=getNumberByreg($("#contCorrectNum_id").html());
		window.location.href=webPath+"/costToken_contCorrectTbIndex.action?siteCode="+siteCode+"&contCorrectNum="+contCorrectNum
		+"&siteName="+name;
    }
    
    //互动回应差
    function securityResponseTb(){
    	var name=$("#title_id").html();
    	var securityResponse=getNumberByreg($("#securityResponse_id").html());
    	
		window.location.href=webPath+"/advanceToken_securityResponseTbIndex.action?siteCode="+siteCode
		+"&securityResponse="+securityResponse+"&siteName="+name+"&servicePeroidId="+servicePeroidId;
    }
    
    //服务不实用
    function securityServiceTb(){
    	var name=$("#title_id").html();
    	var securityService=getNumberByreg($("#securityService_id").html());
    	
		window.location.href=webPath+"/advanceToken_securityServiceTbIndex.action?siteCode="+siteCode
		+"&securityService="+securityService+"&siteName="+name+"&servicePeroidId="+servicePeroidId;
    }
    
    //空白栏目
    function securityBlankTb(){
    	var name=$("#title_id").html();
    	var securityBlank=getNumberByreg($("#securityBlank_id").html());
    	
		window.location.href=webPath+"/advanceToken_securityBlankTbIndex.action?siteCode="+siteCode
		+"&securityBlank="+securityBlank+"&siteName="+name+"&servicePeroidId="+servicePeroidId;
    }
    
    //严重问题
    function securityFatalErrorTb(){
    	var name=$("#title_id").html();
    	var securityFatalError=getNumberByreg($("#securityFatalError_id").html());
    	
		window.location.href=webPath+"/advanceToken_securityFatalErrorTbIndex.action?siteCode="+siteCode
		+"&fatalErrorNum="+securityFatalError+"&siteName="+name+"&servicePeroidId="+servicePeroidId;
    }
    
    //全站死链
    function linkAllTb(){
    	var name=$("#title_id").html();
    	var linkAll=getNumberByreg($("#linkAll_id").html());
		window.location.href=webPath+"/advanceToken_linkAllTbIndex.action?siteCode="+siteCode
		+"&websiteSum="+linkAll+"&siteName="+name+"&servicePeroidId="+servicePeroidId;
    }
	