var MOBILE_REG = /^(0|86|17951)?(13[0-9]|1[56][012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
//验证是否手机号 mobile或者null,空
function isMobile(obj) {
    var controlObj = $.trim(obj);
    if (MOBILE_REG.test(obj)) {
        return true;
    } else {
        return false;
    }
}
var adviceId=0;
var relayHtml='';
var closeRelay='';
$(function () {
	//获取留言
	adviceList();
	// 点击回复
	replayClick();
	//回复 留言信息；
	relayL();
	// 留言页面的取消功能
	closeRelays();
	$('.opinion_white').click(function(){
		$('#opinion_white').modal({backdrop: 'static', keyboard: false});
	});
});


//修改出错的input的外观
function changeCss(id, Validatemsg) {
	   $("#" + id).tips({
	       side: 1,
	       msg: Validatemsg,
	       bg: 'red',
	       time: 1
	   });
	   $("#" + id).focus();
	   //$("#" + id).css("border", "1px solid red");
}
// 提交  留言
function sub(){

	var adTitle = $.trim($("#adTitle").val());
	var adContext = $.trim($("#adContext").val());
	var adLinkPersion = $.trim($("#adLinkPersion").val());
	var adPhone = $.trim($("#adPhone").val());
	if(adTitle.length == 0){
		changeCss("adTitle", "该选项为必选项");
		return false;
	}
	if(adContext.length == 0){
		changeCss("adContext", "该选项为必选项");
		return false;
	}
	if(adLinkPersion.length == 0){
		changeCss("adLinkPersion", "该选项为必选项");
		return false;
	}

	if(!isMobile(adPhone)){
		changeCss("adPhone", "请输入有效的手机号");
		return false;
	}
	if(checkLength('adTitle',500,"内容过长") == '22'){
		return '';
	}
	if(checkLength('adContext',2000,"内容过长") == '22'){
		return '';
	}
	if(checkLength('adLinkPersion',50,"内容过长") == '22'){
		return '';
	}
	var user = {
			adTitle:adTitle,
			adContext:adContext,
			adLinkPersion:adLinkPersion,
			adPhone:adPhone,
			siteCode:$("#curSitecode").val()
        };
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        data:user,
        dataType: "json",
        url: webPath + "/paAdvice_addAdvice.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		if(data.success == "true"){
        			alert("添加成功");
        			$('#opinion_white').modal('hide');
        			adviceList();
        			replayClick();
        			relayL();
        			closeRelays();
        		}else{
        			alert("添加失败");
        		}
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}

// 点击回复  未读更新为0
function adviceList(){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paAdvice_getAdviceList.action?siteCode="+$("#curSitecode").val(),// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var html = "";
        		var list = data.list;
        		for(var i=0;i<list.length;i++){
        			var baseData = list[i];
        			html+="<tr><td class=\"text-center colo-4a4f78\">"+(1+i)+"</td>"+
                    "<td class=\"text-left\">"+baseData.title+"</td>"+
                    "<td class=\"text-center\">"+baseData.content+"</td>"+
                    "<td class=\"text-center\">"+baseData.senderName+"</td>"+
                    "<td class=\"text-center\">"+baseData.senderPhone+"</td>"+
                    "<td class=\"text-center reply_box\" style=\"position: relative;\">"+
                        "<div class=\"colo-01a5ec reply\" adviceId="+baseData.id+">回复[<span class=\"colo-c91125 noReadNum\" >"+baseData.noReadNum+"</span>]</div>"+
                        "<div class=\"dialogue\"><div class=\"backg\"><div class=\"session_box container\"></div>"+
                        "<div class=\"textarea_box\"><textarea style=\"resize: none;\" id=\"textIn\" class=\"textIn\"></textarea><div class=\"clearfix duih-btn\">"+
                                "<span class=\"fr ensure replaysSub\"  adviceId="+baseData.id+">提交</span><span class=\"fr cancel closeRelay\">取消</span></div></div></div></div></td></tr>";
        		}
        		$("#tbodyy").html(html);
        		
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}
// 留言页面的 取消功能
function closeRelays(){
	 $('.closeRelay').click(function(){
		 closeRelay.removeClass('act');
	 });
}

//回复 留言信息；
function relayL(){
    $('.replaysSub').click(function(){
    	var id=$(this).attr("adviceId");
    	var context = $(this).parent().parent().find(".textIn").val();
    	
    	if(checkLength('textIn',2000,"内容过长") == '22'){
    		return '';
    	}
    	
    	if(context == '' || context == null){
    		alert("请输入内容");
    		return;
    	}
    	var user = {
    			adviceId:id,
    			context:context
            };
    	$.ajax({
            async: false,
            cache: false,
            type: 'POST',
            data:user,
            dataType: "json",
            url: webPath + "/paAdvice_addRelays.action",// 请求的action路径
            success: function (data) { // 请求成功后处理函数。
            	if (data) {
            		if(data.success == "true"){
            			alert("回复成功");
            			getReplays(adviceId,relayHtml);
            			$(".textIn").val("");
            		}else{
            			alert("回复失败");
            		}
                } else {
                }
            },error: function () {// 请求失败处理函数
            }
        });
    });
}

// 点击回复按钮
function replayClick(){
    $('.reply_box .reply').click(function(){
    	
    	adviceId=$(this).attr("adviceId");
    	relayHtml = $(this).parent().find(".container");
    	closeRelay = $(this).parent();
        if($(this).parent().hasClass('act')){
            $(this).parent().removeClass('act');
        }else{
           $('.reply_box .reply').parent().removeClass('act');
           $(this).parent().addClass('act');
           $(this).find(".noReadNum").html('0');
           getReplays(adviceId,relayHtml);
        }
    });
}
// 获取留言信息
function getReplays(id,htmlAttr){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        //data:user,
        dataType: "json",
        url: webPath + "/paAdvice_getAdviceDetailList.action?adviceId="+id,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var html = "";
        		var list = data.list;
        		for(var i=0;i<list.length;i++){
        			var dataBase = list[i];
        			if(dataBase.adviceState ==1){//前台留言
        				html+="<div class=\"ask clearfix\"><div class=\"txt fl\"><p class=\"name_time clearfix\">"+
                                "<span class=\"fl\">"+dataBase.name+"</span>"+
                                "<span class=\"fr time\">"+dataBase.createTime.substring(0,16)+"</span></p>"+
                              "<p class=\"nr\">"+dataBase.content+"</p><i class=\"point_icon_f\"></i></div>"+
                              "<div class=\"dh_img fr\"><i></i></div></div>";
        				
        			}else if(dataBase.adviceState ==2){
        				html+="<div class=\"ques clearfix\"><div class=\"dh_img fl\"><i></i></div><div class=\"txt fr\"><p class=\"name_time clearfix\">"+
                                "<span class=\"fl\">"+dataBase.name+"</span>"+
                                "<span class=\"fr time\">"+dataBase.createTime.substring(0,16)+"</span></p>"+
                            "<p class=\"nr\">"+dataBase.content+"</p>"+
                            "<i class=\"point_icon_c\"></i></div></div>";
        			}
        		}
        		htmlAttr.html(html);
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}
function checkLength(id,length,content){
	if($.trim($("#"+id).val()).length>length){
		changeCss(id, content);
		return "22";
	}
	return "1";
}