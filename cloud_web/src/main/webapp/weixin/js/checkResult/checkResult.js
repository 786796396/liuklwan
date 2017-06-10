
var openId="";
var level="";//组织单位层级关系
var siteCode="";//网站标识码
var zhesuan="";
var totalSum="";
var siteName="";
//全部
var count="";
//本级部门
var currentCount="";
//下属单位
var nextCount="";
//组织机构菜单
var menuType="";
$(function(){
	//初始化该页面时，给全局变量赋值
	openId=$("#openId_id").val();
	siteCode=$("#siteCode_id").val();
	siteName=$("#siteName_id").val();
	count=$("#count_id").val();
	currentCount=$("#currentCount_id").val();
	nextCount=$("#nextCount_id").val();
	
	level=$("#level_id").val();
	zhesuan=$("#zhesuan_id").val();
	totalSum=$("#totalSum_id").val();
	
	//页面初始化时，圆环进度赋值
	getJianKangMap(zhesuan);
	if(siteCode.length==6){//只有组织单位，才初始化部门控件
		$("#org_header_id").attr("style","display: block");
		//组织单位的div展示
		$("#org_content_id").attr("style","display: block");
		//将填报单位的div隐藏
		$("#tb_content_id").attr("style","display: none");
		//部门选择控件初始化，仅初始化一次
		bumenInit(siteCode);
		//组织单位页面初始化时，健康分数赋值
		if(zhesuan>=90){
			$("#index_count_id").html(totalSum);
		}else if(zhesuan>=70 && zhesuan<90){
			$("#index_count_id").html(totalSum);
		}else if(zhesuan>=60 && zhesuan<70){
			$("#index_count_id").html(totalSum);
		}else{
			$("#index_count_id").html(totalSum);
		}
	}else{//填报单位
		$("#org_header_id").attr("style","display: none");
		//组织单位的div展示
		$("#org_content_id").attr("style","display: none");
		//将填报单位的div隐藏
		$("#tb_content_id").attr("style","display: block");
		
		//填报单位页面初始化时，健康分数赋值
		if(zhesuan>=90){
			$("#index_count_tb_id").html(totalSum);
		}else if(zhesuan>=70 && zhesuan<90){
			$("#index_count_tb_id").html(totalSum);
		}else if(zhesuan>=60 && zhesuan<70){
			$("#index_count_tb_id").html(totalSum);
		}else{
			$("#index_count_tb_id").html(totalSum);
		}
	}
	
	$(".dropdown-menu > li").click(function(){
		$(".dropdown-menu > li").removeClass("active");//清空所有的active
		$(this).addClass("active");//选中的添加active
		$(".tab-content > .tab-pane").removeClass("active");//清空所有的统计结果的active
		$($(this).find("a").attr("href")).addClass("active");
		$(".nav-titcon").html($(this).find("a").html());//将选中的下拉选项的值显示
		
		//主要是用于发送ajax请求
		menuType=$(this).find("a").attr("_menuType");
		//主要是用于拼装统计数据标签的id(前半部分)
		var typeId=$(this).find("a").attr("href");

		getCurrentCheckResult(menuType,typeId,openId);
	});
});


//部门选择控件初始化
function bumenInit(siteCode){
	var listStr="";
	if(level!=""){
		if(level==2){//如果网站标识码是省级，menuType  全部       1-省部门   2-省登录--下级单位（市级） 5省登录-省门户 
			listStr+="<li class='active'><a data-toggle='tab' _menuType='' href='#all'>全部("+count+")</a></li>"+
			"<li><a data-toggle='tab' _menuType='5' href='#current'>"+siteName+"(1)</a></li>"+
			"<li><a data-toggle='tab' _menuType='1' href='#province'>本级部门("+currentCount+")</a></li>"+
			"<li><a data-toggle='tab' _menuType='2' href='#city'>下属单位("+nextCount+")</a></li>";
		}else if(level==3){//如果网站标识码为市级，menuType  全部       3市登录--市部门    4 市登录--下级单位  5市级登录--市门户
			listStr = listStr + "<li class='active'><a data-toggle='tab' _menuType='' href='#all'>全部("+count+")</a></li>"+
			"<li><a data-toggle='tab' _menuType='5' href='#current'>"+siteName+"(1)</a></li>"+
			"<li><a data-toggle='tab' _menuType='3' href='#city'>本级部门("+currentCount+")</a></li>"+
			"<li><a data-toggle='tab' _menuType='4' href='#area'>下属单位("+nextCount+")</a></li>"
		}else if(level==4){// 如果网站标识码为县级,menuType  全部      3-县部门      5县登录-县门户
			listStr+="<li class='active'><a data-toggle='tab' _menuType='' href='#all'>全部("+count+")</a></li>"+
			"<li><a data-toggle='tab' _menuType='5' href='#current'>"+siteName+"(1)</a></li>"+
			"<li><a data-toggle='tab' _menuType='3' href='#area'>本级部门("+currentCount+")</a></li>"
		}
		$("#bumen_id").append(listStr);
	}
}

//组织单位数据初始化页面   或者  选中下拉框中的某一个选中，触发事件，执行该方法,获取对应的统计数据       
function getCurrentCheckResult(menuType,typeId,openId){
	//发送ajax请求，获取数据
	$.ajax({
		   type : "POST",
		   async : false,
		   url : webPath+"/token_getcurrentCheckResult.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType,  
		   dataType:"json",
	       success : function(data){
				var connNumId="";//网站不连通个数
				var linkNumId="";//不可用链接个数
				var contGuaranteNumId="";//内容保障问题个数
				var contCorrectNumId="";//内容正确性
				var websiteSafeId="";//网络安全
				var contUpdateId="";//内容更新个数
				var totalSum="";//健康指数统计总分

				connNumId=typeId+"_connNum";
				linkNumId=typeId+"_linkNum";
				contGuaranteNumId=typeId+"_contGuaranteNum";
				contCorrectNumId=typeId+"_contCorrectNum";
				websiteSafeId=typeId+"_websiteSafe";
				contUpdateId=typeId+"_contUpdate";
				
				
				//先将数据清空
				$(connNumId).html("");
				$(linkNumId).html("");
				$(contGuaranteNumId).html("");
				$(contCorrectNumId).html("");
				$(websiteSafeId).html("");
				$(contUpdateId).html("");
				
				$("#index_count_id").html("");
				$("#index_count_href_id").attr("href","");

				$("#zhesuan_id").val(data.zhesuan);
				//$("#zhesuan_id").attr("value",data.zhesuan);
				getJianKangMap($("#zhesuan_id").val());
				//添加数据
				if(data.zhesuan>=90){
					$("#index_count_id").html(data.totalSum);
				}else if(data.zhesuan>=70 && data.zhesuan<90){
					$("#index_count_id").html(data.totalSum);
				}else if(data.zhesuan>=60 && data.zhesuan<70){
					$("#index_count_id").html(data.totalSum);
				}else{
					$("#index_count_id").html(data.totalSum);
				}
				
				//健康指数领先全国网站的百分比
				$("#gtPercent_id").html("");
				$("#gtPercent_id").html(data.gtPercent+"%");
				
				//控制群内健康指数排名的显示和隐藏   本级门户不显示   menuType为5-省级门户  6-市级门户 8-县级门户
				if(menuType==5 || menuType==6 || menuType==8){
					$("#index_count_href_id").attr("style","display:none");
				}else{
					$("#index_count_href_id").attr("style","display:block");
				}
			
				//$("#index_count_href_id").attr("href",webPath+"/token_indexCountIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId);

				$(connNumId).html("<span>"+data.connNum+"</span>");
				$(linkNumId).html("<span>"+data.linkNum+"</span>");
				$(contGuaranteNumId).html("<span>"+data.contGuaranteNum+"</span>");
				$(contCorrectNumId).html("<span>"+data.contCorrectNum+"</span>");
				$(websiteSafeId).html("<span>"+data.websiteSafe+"</span>");
				$(contUpdateId).html("<span>"+data.contUpdate+"</span>");
	        }
	});
}
//半圆进度加载设置
function getJianKangMap(zhesuan){
	//半圆进度
	if(siteCode.length==6){
		var gauge = new Donut(document.getElementById("chart-gauge"));
		gauge.lineWidth = 20;
		gauge.options = {
	      lineWidth: 0.05,
	      colorStart: "#2db85b",
	      colorStop: "#2db85b",
	      strokeColor: "#e1e1e1",
	      shadowColor: "#e1e1e1",
	      angle: 0.25
	    };
		gauge.maxValue = 2400;
		gauge.set(zhesuan==0?1:zhesuan);
	    
	}else{
		var gauge = new Donut(document.getElementById("chart-gauge_id"));
		gauge.lineWidth = 20;
		gauge.options = {
	      lineWidth: 0.05,
	      colorStart: "#2db85b",
	      colorStop: "#2db85b",
	      strokeColor: "#e1e1e1",
	      shadowColor: "#e1e1e1",
	      angle: 0.25
	    };
		gauge.maxValue = 2400;
		if(zhesuan==0){
			gauge.set(1);
		}else{
			gauge.set(zhesuan);
		}
	}
}
//健康指数排名按钮功能添加
function indexCountSort(){
	//console.log("siteCode="+siteCode+"  level="+level+"  menuType="+menuType+"  openId="+openId);
	window.location.href=webPath+"/token_indexCountIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId;
}

//网站连不通色块点击事件--跳转到连通性页面
function connNumSort(){
	//console.log("siteCode="+siteCode+"  level="+level+"  menuType="+menuType+"  openId="+openId);
	window.location.href=webPath+"/token_connectionAllIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId;
}
//首页链接可用性色块点击事件--跳转到链接可用性页面
function linkNumSort(){
	//console.log("siteCode="+siteCode+"  level="+level+"  menuType="+menuType+"  openId="+openId);
	window.location.href=webPath+"/token_linkHomeIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId;
}

//内容保障问题色块点击事件--跳转到内容保障问题页面
function contGuaranteNumSort(){
	//console.log("siteCode="+siteCode+"  level="+level+"  menuType="+menuType+"  openId="+openId);
	window.location.href=webPath+"/token_securityIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId;
}

//严重错别字色块点击事件--跳转到内容正却行页面
function contCorrectNumSort(){
	//console.log("siteCode="+siteCode+"  level="+level+"  menuType="+menuType+"  openId="+openId);
	window.location.href=webPath+"/token_correctContentIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId;
}

//新稿件色块点击事件--跳转到内容更新页面
function contUpdateSort(){
	//console.log("siteCode="+siteCode+"  level="+level+"  menuType="+menuType+"  openId="+openId);
	window.location.href=webPath+"/token_updateIndex.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType+"&openId="+openId;
}
