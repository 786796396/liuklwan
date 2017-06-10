// JavaScript Document
var scan = isScan;
var exp = isexp;
//var timer=null;
$(document).ready(function(e) {
    $(".ms-icon-wen").hover(function(){
        $(this).siblings(".ms-wen-con").fadeIn();
    },function(){
        $(this).siblings(".ms-wen-con").fadeOut();
    });
    $(".footer-year").html(new Date().getFullYear());
	// cbox样式
	$("input[type='checkbox'],input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
    // 搜索获得焦点样式
    $(".prependedInput").focus(function() {
        if (!$(this).parent(".input-prepend").hasClass("input-prepend-black")) {
            $(this).parent(".input-prepend").css("border-color", "#fff");
        }
    });
    $(".prependedInput").blur(function(){
        if (!$(this).parent(".input-prepend").hasClass("input-prepend-black")) {
            $(this).parent(".input-prepend").css("border-color", "#603cba");
        }
    });
	$(window).scroll(function () {
		if ($(window).scrollTop() > 100) {
			$("#backToTop").fadeIn(500);
		} else {
			$("#backToTop").fadeOut(500);
		}
	});
	$("#backToTop").click(function () {
		$('body,html').animate({scrollTop: 0}, 600);
		return false;
	});
	$('.hiddenn').css('display','none');
	$("#alertViews").click(function(){
		alertView(scan);
	});
	$("#clickUpdate").click(function(){
		window.location.href = webPath+"/manageInfo_indexTB.action";
	});
	$("#checkURL").click(function(){
		window.location.href = webPath+"/manageInfo_indexTB.action";
	});
	$("#clickIp").click(function(){
		$("#showIPs").show();
		$("#copyCon").zclip({
			path: webPath+"/js/ZeroClipboard.swf",
			copy: function(){
				console.info($(".copyText").text());
			return $(".copyText").text();
			},
			beforeCopy:function(){/* 按住鼠标时的操作 */
				$(this).css("color","orange");
			},
			afterCopy:function(){/* 复制成功后的操作 */
				$(this).css("color","white");
	        }
		});
	});
	   $("#copyCon").click(function(){
		/* 定义所有class为copy标签，点击后可复制被点击对象的文本 */
	    $("#copyCon").zclip({
			path: webPath+"/js/ZeroClipboard.swf",
			copy: function(){
				console.info($(".copyText").text());
			return $(".copyText").text();
			},
			beforeCopy:function(){/* 按住鼠标时的操作 */
				$(this).css("color","orange");
			},
			afterCopy:function(){/* 复制成功后的操作 */
				$(this).css("color","white");
	        }
		});
	});
	$("#closeCon").click(function(){
		$("#showIPs").hide();
	});
	
 
//预警引导查询 引导状态进行
	var siteCode = $("#shirUserCode").val();	
	
});

function alertView(iscan){
	//$('.hiddenn').css('display','none');
	if(iscan == 3 || iscan == 6 || iscan == 7 || iscan == 8|| iscan == 9 || iscan == 10 || iscan == 11){
		 $('#myModal_1').css('display', 'block');
		 $('#myModal_1').modal({});
	}else if(iscan == 4){
		 $('#myModal_3').css('display', 'block');
		 $('#myModal_3').modal({});
	}else if(iscan == 12){
		 $('#myModal_2').css('display', 'block');
		 $('#myModal_2').modal({});
	}else if(iscan == 14){
		 $('#myModal_4').css('display', 'block');
		 $('#myModal_4').modal({});
	}else if(iscan == 13){
		 $('#myModal_5').css('display', 'block');
		 $('#myModal_5').modal({});
	}else if(iscan == 2|| iscan ==5 || iscan ==16){
		 $("#showIPs").hide();
		 $('#myModal_6').css('display', 'block');
		 $('#myModal_6').modal({});
	}else if(iscan == 15){
		 $('#myModal_8').css('display', 'block');
		 $('#myModal_8').modal({});
	}else if(iscan == 17){
		 $('#myModal_9').css('display', 'block');
		 $('#myModal_9').modal({});
	}else if(iscan == 0){
		if(exp == 2){//例外
			$('#myModal_11').css('display', 'block');
			$('#myModal_11').modal({});
		}else if(exp == 3){////关停
			$('#myModal_10').css('display', 'block');
			$('#myModal_10').modal({});
		}
	}
}
// 如果url 不带http  添加上
function checkUrlHTTTP(oldUrl){
	if(oldUrl != null &&  oldUrl != ''){
		if(oldUrl != '无' && oldUrl.indexOf("http") != 0){
			oldUrl ='http://'+oldUrl;
		}
	}else if(oldUrl == null || oldUrl == 'null'){
		oldUrl ="";
	}
	return oldUrl;
}
//var url = org.url;
//if(url.substr(0, 4)!='http'){
//	url = "http://"+url;
//}

function nameHover(wrapId){
    $("#"+wrapId).on("mouseover",".info-box", function(){
        var conTop=$(this).offset().top-$(document).scrollTop()+25,
            conLeft=$(this).offset().left;
        $(this).find(".info-con").css({
            left:conLeft+"px",
            top:conTop+"px"
        });
        $(this).addClass("info-box-hover");
        $(this).find(".info-con").show();
    });
    $("#"+wrapId).on("mouseout",".info-box", function(){
        $(this).find("chouc-hover-div").hide();
        $(this).removeClass("info-box-hover");
        $(this).find(".info-con").hide();
    });
}
function GetDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = dd.getMonth() + 1;// 获取当前月份的日期
	var d = dd.getDate();
	 if (m < 10){
		 m = "0" + m;
	 } 
	if (d < 10){
		d = "0" + d;
	}
	return y + "-" + m + "-" + d;
}
//获得上一年在昨天这一天的日期   
function getLastYearYestdy(date){ 
	var date = new Date();  
	var strYear = date.getFullYear() - 1;     
	var strDay = date.getDate();     
	var strMonth = date.getMonth()+1;   
	if(strMonth<10){     
		strMonth="0"+strMonth;     
	}   
	if(strDay<10){     
		strDay="0"+strDay;     
	}   
	datastr = strYear+"-"+strMonth+"-"+strDay;   
	return datastr;   
}  

/**
 * 相同内容合并单元格
 * 使用方法：$(“#table1″).rowspan(0);//传入的参数是对应的列数从0开始，哪一列有相同的内容就输入对应的列数值
 */
jQuery.fn.rowspan = function(colIdx) { 
    return this.each(function() {
        var that;
        $('tr', this).each(function(row) {
            $('td:eq(' + colIdx + ')', this).filter(':visible').each(function(col) {
                if (that != null && $(this).html() == $(that).html()) {
                    rowspan = $(that).attr("rowSpan");
                    if (rowspan == undefined) {
                        $(that).attr("rowSpan", 1);
                        rowspan = $(that).attr("rowSpan");
                    }
                    rowspan = Number(rowspan) + 1;
                    $(that).attr("rowSpan", rowspan);
                    $(this).hide();
                } else {
                    that = this;
                }
            });
        });
    });
}
//我的监测报告按钮  ---点击事件
function checkReport(){
	console.log("======webPath======="+webPath);
	window.open(webPath+"/wordList_index.action?isScanFlag="+true);
}

/** 
* 对Date的扩展，将 Date 转化为指定格式的String 
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符 
* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
* eg: 
* (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
* (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 
* (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 
* (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 
* (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18 
*/ 
Date.prototype.pattern=function(fmt) { 
var o = { 
"M+" : this.getMonth()+1, //月份 
"d+" : this.getDate(), //日 
"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时 
"H+" : this.getHours(), //小时 
"m+" : this.getMinutes(), //分 
"s+" : this.getSeconds(), //秒 
"q+" : Math.floor((this.getMonth()+3)/3), //季度 
"S" : this.getMilliseconds() //毫秒 
}; 
var week = { 
"0" : "\u65e5", 
"1" : "\u4e00", 
"2" : "\u4e8c", 
"3" : "\u4e09", 
"4" : "\u56db", 
"5" : "\u4e94", 
"6" : "\u516d" 
}; 
if(/(y+)/.test(fmt)){ 
fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
} 
if(/(E+)/.test(fmt)){ 
fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]); 
} 
for(var k in o){ 
if(new RegExp("("+ k +")").test(fmt)){ 
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
} 
} 
return fmt; 
} 

/**
 * @param isOrg  1位组织 2为填报
 * @param urlOne 修改1级cooke的url
 * @param urlTwo 修改2级cooke的url
 * @param urlThree  修改3级cooke的url
 */
function changeCookie(isOrg,urlOne,urlTwo,urlThree){
	if(isOrg == 1){//组织
			if(urlOne != null){
				$.cookie('top_cookie', urlOne, {path:'/'});
			}
			if(urlTwo != null ){
				$.cookie('twoMenu_cookie', urlTwo, {path:'/'});
			}
			if(urlThree != null ){
				$.cookie('twoMenu_cookie', urlThree, {path:'/'});
			}
			$.cookie('type_cookie', "2", {path:'/'});
		}else if(isOrg == 2){//填报
			if(urlOne != null ){
				$.cookie('topTB_cookie', urlOne, {path:'/'});
			}
			if(urlTwo != null ){
				$.cookie('twoMenuTB_cookie', urlTwo, {path:'/'});
			}
			if(urlThree != null ){
				$.cookie('twoMenuTB_cookie', urlThree, {path:'/'});
			}
			$.cookie('typeTB_cookie', "2", {path:'/'});
		}
}

// 点击标识码跳转到填报单位 默认选中 网站监测  网站监测概况
function fillJumpTb(){ 
	var url = webPath + "/fillUnit_gailan.action";
	changeCookie(2,url,url,null);
}


function copyInformation (type,name){  // type  1  没有数据    2  正在加载中
	if(type == 1){
		var html = '';
		html += ' <tr>';
		html += ' <td colspan="7" class="jiangbei-part" style="padding-bottom: 100px;">';
		html += ' <i class="publi-ico_2 jiangbei"></i><p>'+name+'</p>';
		html += ' </td></tr>';
		return html;
	}else{
		var zHtml = '';
		zHtml += '<tr><td colspan="7"  style="padding-bottom: 100px;">';
		zHtml += '<div class="loading-2"><div class="spinner">';
		zHtml += '<div class="spinner-container container1">';
		zHtml += '<div class="circle1"></div><div class="circle2"></div><div class="circle3"></div><div class="circle4"></div>';
		zHtml += '</div>';
		zHtml += '<div class="spinner-container container2">';
		zHtml += '<div class="circle1"></div><div class="circle2"></div><div class="circle3"></div><div class="circle4"></div>';
		zHtml += '</div>';
		zHtml += '<div class="spinner-container container3">';
		zHtml += '<div class="circle1"></div><div class="circle2"></div><div class="circle3"></div><div class="circle4"></div>';
		zHtml += '</div></div>';
		zHtml += '<span>玩命加载中，请稍候...</span>';
		zHtml += '</div></td></tr>';
		return zHtml;
	}
}
/**
 * @描述:广告的隐藏方法
 * @作者:liujc@ucap.com.cn
 * @时间:2017-5-22上午11:47:01
 */
function hideAdvert() {
	$("#advertDiv").hide();
	$("#advertDiv").attr("display");
	var adDiv = $("#advertDiv").is(":hidden");//true 隐藏
	if (adDiv) {
		// 	隐藏
		$('.other-products-part').css("paddingTop","110px");
		$(".base-info").css("top","50px");
		$("#sidebar").css("top","90px");
		$(".page-content").css("paddingTop","105px");
	} else {
		$('.other-products-part').css("paddingTop","170px");
		$(".base-info").css("top","110px");
		$("#sidebar").css("top","150px");
		$(".page-content").css("paddingTop","165px");
	}
}
/**
 * @描述:初始化广告显示状态
 * @作者:liujc@ucap.com.cn
 * @时间:2017-5-22上午11:47:01
 */
function initAdvert() {
	var advertDivShowType=$("#advertDivShowType").val();
	if(advertDivShowType =="show"){
		$("#advertDiv").show();
	}else{
		$("#advertDiv").hide();
	}
	var adDiv = $("#advertDiv").is(":hidden");//true 隐藏
	if (adDiv) {
		// 	隐藏
		$('.other-products-part').css("paddingTop","110px");
		$(".base-info").css("top","50px");
		$("#sidebar").css("top","90px");
		$(".page-content").css("paddingTop","105px");
	} else {
		$('.other-products-part').css("paddingTop","170px");
		$(".base-info").css("top","110px");
		$("#sidebar").css("top","150px");
		$(".page-content").css("paddingTop","165px");
}
}
//var date = new Date(); 
//window.alert(date.pattern("yyyy-MM-dd hh:mm:ss")); 