	
	var isOrgCost=2;
	var isOrgAdvance=2;
	var servicePeroidId="";
	var siteCode="";
	var openId="";
	var checkType=0;
	var indexCount="";
	$(function(){
		FastClick.attach(document.body);
		
		pingTurn();
		//getSelcetList();
		
	    //下拉框
	    var showItem = document.querySelector(".show-item");
	    var showUl=document.querySelector('.show-ul');
	    showItem.addEventListener("touchstart", function(){
	    	showUl.style.display='block';
	    });
		$(".show-ul >li").click(function(e){
		    e.stopPropagation();
			$(".show-ul > li").removeClass("active");//清空所有的active
			$(this).addClass("active");//选中的添加active
			$(".show-item").html($(this).html());
			hasFlag=false;
			$(".show-ul").attr("style","display:none");
			checkType=$(this).val();
			initPageData();
		});
		$(document).click(function(e){
		    e.stopPropagation();
		       var _con = $('.show-ul');
		       var _con2=$(".show-item");
		       if(!_con.is(e.target) && _con.has(e.target).length == 0 &&(!_con2.is(e.target) && _con2.has(e.target).length == 0)){
		    	   showUl.style.display='none';
		       }
			});
		
		isOrgCost=$("#isOrgCost_id").val();
		isOrgAdvance=$("#isOrgAdvance_id").val();
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
			 initIndexRound(indexCount,new Date().getTime());
		}, false);
	}
	
	//下拉框展示选项
/*	function getSelcetList(){
		$(".show-ul").html("");
		var selectStr="";
		selectStr=" <li value='0'>全部站点</li><li value='1'>本级门户</li><li value='2'>本级部门</li>";
		
		var isHaveNext=$("#isHaveNext_id").val();
		if(isHaveNext && isHaveNext==1){
			selectStr+=" <li class='last-item' value='3'>下属站点</li>";
			$(".show-ul").html(selectStr);
		}else{
			$(".show-ul").html(selectStr);
		}
	}*/
	
	
	
	//页面数据初始化
	function initPageData(){
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/newToken_checkResultOrg.action?siteCode="+siteCode
	        			+"&isOrgAdvance="+isOrgAdvance+"&checkType="+checkType
	        			+"&isOrgCost="+isOrgCost+"&servicePeroidId="+servicePeroidId,
	        dataType:"json",
	        success : function(data){
	           var resultMap=data;//后台返回数据
	           if(resultMap.errormsg){
	           		alert(resultMap.errormsg);
	           }else{
	        	   //控制页面显示隐藏
	        	   contrlShowOrHide();
	        	   //通用
	        	   indexCount=resultMap["indexCount"];//健康指数
	        	   var leadAvgRate=resultMap["leadAvgRate"];//健康指数领先全国
	        	   var typeSite=resultMap["typeSite"];//各类型站点数
	        	   $("#indexCount_id").html(indexCount);
	        	   $("#leadAvgRate_id").html(leadAvgRate);
	        	   $("#typeSite_id").html(typeSite);
	        	   
	        	   
	        	   //健康指数半圆加载
	        	   initIndexRound(indexCount,new Date().getTime());
	        	   //免费版数据
	        	   var connHome=resultMap["connHome"];//首页不连通数
	        	   var updateHome=resultMap["updateHome"];//首页更新量
	        	   var updateHomeSite=resultMap["updateHomeSite"];//首页更新站点数
	        	   var securityHome=resultMap["securityHome"];//首页更新不及时
	        	   var linkHome=resultMap["linkHome"];//首页不可用链接数
	        	   var linkHomeSite=resultMap["linkHomeSite"];//首页不可用站点数
	        	   
	        	   $("#connHome_id").html(connHome+"个首页连不通");
	        	   $("#updateHome_id").html(updateHome+"条更新");
	        	   $("#updateHomeSite_id").html(updateHomeSite+"个网站");
	        	   $("#securityHome_id").html(securityHome+"个网站");
	        	   $("#linkHome_id").html(linkHome+"个问题");
	        	   $("#linkHomeSite_id").html(linkHomeSite+"个网站");
	        	   
	        	   //标准
	        	   if(isOrgCost==1){
	            	   var connChannel=resultMap["connChannel"];//关键栏目不连通数
	            	   var connChannelSite=resultMap["connChannelSite"];//关键栏目不连通站点数
	            	   var connBusiness=resultMap["connBusiness"];//业务系统不连通数
	            	   var connBusinessSite=resultMap["connBusinessSite"];//业务系统不连通站点数
	            	   var updateChannel=resultMap["updateChannel"];//栏目更新量
	            	   var updateChannelSite=resultMap["updateChannelSite"];//栏目更新站点数
	            	   var securityChannel=resultMap["securityChannel"];//栏目更新不及时
	            	   var securityChannelSite=resultMap["securityChannelSite"];//栏目更新不及时
	            	   var contCorrectNum=resultMap["contCorrectNum"];//错别字
	            	   var contCorrectSite=resultMap["contCorrectSite"];//错别字站点数
	            	   
	            	   $("#connChannel_id").html(connChannel+"个问题");
	            	   $("#connChannelSite_id").html(connChannelSite+"个网站");
	            	   $("#connBusiness_id").html(connBusiness+"个问题");
	            	   $("#connBusinessSite_id").html(connBusinessSite+"个网站");
	            	   $("#updateChannel_id").html(updateChannel+"个更新");
	            	   $("#updateChannelSite_id").html(updateChannelSite+"个网站");
	            	   $("#securityChannel_id").html(securityChannel+"个问题");
	            	   $("#securityChannelSite_id").html(securityChannelSite+"个网站");
	            	   //内容正确性
	        		   $("#contCorrectNum_id").html(contCorrectNum+"个问题");
	        		   $("#contCorrectSite_id").html(contCorrectSite+"个网站 ");
	        	   }
	        	   //高级
	        	   if(isOrgAdvance==1){
	        		   //服务周期
	        		  // servicePeroidId=resultMap["servicePeroidId"];
	        		   
	            	   var linkAll=resultMap["linkAll"];//全站死链
	            	   var linkAllSite=resultMap["linkAllSite"];//全站死链站点数
	            	   var securityBlank=resultMap["securityBlank"];//空白栏目
	            	   var securityBlankSite=resultMap["securityBlankSite"];//空白栏目站点数
	            	   var securityResponse=resultMap["securityResponse"];//互动回应差
	            	   var securityResponseSite=resultMap["securityResponseSite"];//互动回应差站点数
	            	   var securityService=resultMap["securityService"];//服务不实用
	            	   var securityServiceSite=resultMap["securityServiceSite"];//服务不实用站点数
	            	   var securityFatalError=resultMap["securityFatalError"];//严重问题
	            	   var securityFatalSite=resultMap["securityFatalSite"];//严重问题站点数
	            	   
	            	   $("#linkAll_id").html(linkAll+"个问题");
	            	   $("#linkAllSite_id").html(linkAllSite+"个网站");
	            	   $("#securityBlank_id").html(securityBlank+"个问题");
	            	   $("#securityBlankSite_id").html(securityBlankSite+"个网站");
	            	   $("#securityResponse_id").html(securityResponse+"个问题");
	            	   $("#securityResponseSite_id").html(securityResponseSite+"个网站");
	            	   $("#securityService_id").html(securityService+"个问题");
	            	   $("#securityServiceSite_id").html(securityServiceSite+"个网站");
	            	   $("#securityFatalError_id").html(securityFatalError+"个问题");
	            	   $("#securityFatalSite_id").html(securityFatalSite+"个网站");
	        	   }
	           }
	        },error : function(data){
	        	alert("查询数据异常");
	        	return;
	        }
		});
	}
		
	//健康指数半圆加载
	function initIndexRound(convertScores,dateStr){
		//获取屏幕的宽度
	    var clientWidth = 0;
	    //安卓系统获取屏幕宽度并不像ios那样灵敏，有延迟，因此需要等待
        setTimeout(function(){
        	clientWidth=document.body.clientWidth; 
        	//根据设计图中的canvas画布的占比进行设置
    	    var canvasWidth = Math.floor(clientWidth*372/640);
    	    $("#chart-gauge").attr("width",canvasWidth+'px');
    	    $("#chart-gauge").attr("height",(canvasWidth)+'px');
    	    //半圆进度
    	    var gauge = new Donut(document.getElementById("chart-gauge"));
    	    $("#chart-gauge").attr("style","width: "+canvasWidth+"px; height:"+canvasWidth+" px;")
    	    gauge.set(0);
    	    gauge.lineWidth = 16;
    	    gauge.options = {
    	        lineWidth: 0.8,
    	        colorStart: "#fff",
    	        colorStop: "#fff",
    	        strokeColor: "#4eca8e",
    	        shadowColor: "#e1e1e1",
    	        angle: 0.25
    	    };
    	    //gauge.maxValue = 100;
    	    gauge.set(convertScores);

         },100);
	    
	}
	//控制页面显示隐藏
	function contrlShowOrHide(){
		if(isOrgCost==1){//标准版
			$("#shendu_id").show();
			if(isOrgAdvance==1){//高级版
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
		
	//获取字符串中的数字
	function getNumberByreg(str){
		return str.replace(/[^0-9]+/g, '')
	}
	
	//健康指数排名按钮功能添加
	function indexCountSort(){
		console.log("siteCode="+siteCode+"  checkType="+checkType+"  openId="+openId);
		window.location.href=webPath+"/newToken_indexCountIndex.action?siteCode="+siteCode+"&checkType="+checkType+"&openId="+openId;
	}
	
	//首页连不通
	function connHomeOrg(){
		var connHome=getNumberByreg($("#connHome_id").html());
		window.location.href=webPath+"/freeToken_connHomeOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
				+"&connHome="+connHome;
		
	}
	
	//首页更新量点击事件
	function updateHomeOrg(){
		var updateHomeSite=getNumberByreg($("#updateHomeSite_id").html());
		var updateHome=getNumberByreg($("#updateHome_id").html());
		window.location.href=webPath+"/freeToken_updateHomeOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
				+"&updateHome="+updateHome+"&updateHomeSite="+updateHomeSite;
	}
	
	//首页更新不及时点击事件
	function securityHomeOrg(){
		var securityHome=getNumberByreg($("#securityHome_id").html());
		window.location.href=webPath+"/freeToken_securityHomeOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&securityHome="+securityHome;
		
	}
	
	//首页不可用链接
	function linkHomeOrg(){
		
		var linkHome=getNumberByreg($("#linkHome_id").html());//首页不可用链接量
		var linkHomeSite=getNumberByreg($("#linkHomeSite_id").html());//首页不可用站点数
		
		window.location.href=webPath+"/freeToken_linkHomeOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&linkHome="+linkHome+"&linkHomeSite="+linkHomeSite;
	}
	
	//关键栏目不连通
	function connChannelOrg(){
		var connChannel=getNumberByreg($("#connChannel_id").html());
		var connChannelSite=getNumberByreg($("#connChannelSite_id").html());
		
		window.location.href=webPath+"/costToken_connChannelOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&connChannel="+connChannel+"&connChannelSite="+connChannelSite;
	}
	
	//业务系统连不通
	function connBusinessOrg(){
		var connBusiness=getNumberByreg($("#connBusiness_id").html());
		var connBusinessSite=getNumberByreg($("#connBusinessSite_id").html());
		
		window.location.href=webPath+"/costToken_connBusinessOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&connBusiness="+connBusiness+"&connBusinessSite="+connBusinessSite;
	}
	//栏目内容更新
	function updateChannelOrg(){
		var updateChannel=getNumberByreg($("#updateChannel_id").html());
		var updateChannelSite=getNumberByreg($("#updateChannelSite_id").html());
		
		window.location.href=webPath+"/costToken_updateChannelOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&updateChannel="+updateChannel+"&updateChannelSite="+updateChannelSite;
	}
	//栏目更新不及时
	function securityChannelOrg(){
		var securityChannel=getNumberByreg($("#securityChannel_id").html());
		var securityChannelSite=getNumberByreg($("#securityChannelSite_id").html());
		
		window.location.href=webPath+"/costToken_securityChannelOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&securityChannel="+securityChannel+"&securityChannelSite="+securityChannelSite;
	}
	
	//错别字 
	function contCorrectOrg(){
		var contCorrectNum=getNumberByreg($("#contCorrectNum_id").html());
		var contCorrectSite=getNumberByreg($("#contCorrectSite_id").html());
		
		window.location.href=webPath+"/costToken_contCorrectOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&contCorrectNum="+contCorrectNum+"&contCorrectSite="+contCorrectSite;
		
	}
	//互动回应差
	function securityResponseOrg(){
		var securityResponse=getNumberByreg($("#securityResponse_id").html());
		var securityResponseSite=getNumberByreg($("#securityResponseSite_id").html());
		
		window.location.href=webPath+"/advanceToken_securityResponseOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&securityResponse="+securityResponse+"&securityResponseSite="+securityResponseSite+"&servicePeroidId="+servicePeroidId;
		
	}
	//服务不实用
	function securityServiceOrg(){
		var securityServiceSite=getNumberByreg($("#securityServiceSite_id").html());
		var securityService=getNumberByreg($("#securityService_id").html());
		
		window.location.href=webPath+"/advanceToken_securityServiceOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&securityService="+securityService+"&securityServiceSite="+securityServiceSite+"&servicePeroidId="+servicePeroidId;
		
	}
	
	//空白栏目
	function securityBlankOrg(){
		var securityBlank=getNumberByreg($("#securityBlank_id").html());
		var securityBlankSite=getNumberByreg($("#securityBlankSite_id").html());
		
		window.location.href=webPath+"/advanceToken_securityBlankOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&securityBlank="+securityBlank+"&securityBlankSite="+securityBlankSite+"&servicePeroidId="+servicePeroidId;
	}
	
	//严重问题
	function securityFatalErrorOrg(){
		var securityFatalError=getNumberByreg($("#securityFatalError_id").html());
		var securityFatalSite=getNumberByreg($("#securityFatalSite_id").html());
		
		window.location.href=webPath+"/advanceToken_securityFatalErrorOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&securityFatalError="+securityFatalError+"&securityFatalSite="+securityFatalSite+"&servicePeroidId="+servicePeroidId;
	}
	//全站链接可用性
	function linkAllOrg(){
		var linkAll=getNumberByreg($("#linkAll_id").html());
		var linkAllSite=getNumberByreg($("#linkAllSite_id").html());
		
		window.location.href=webPath+"/advanceToken_linkAllOrgIndex.action?siteCode="+siteCode+"&checkType="+checkType
		+"&linkAll="+linkAll+"&linkAllSite="+linkAllSite+"&servicePeroidId="+servicePeroidId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
