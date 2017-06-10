var ratingId = $("#ratingId").val();
var targetTaskId = $("#targetTaskId").val();
var quota1="";
var weight = 10;
//点击 修改  或者  开始自评的时候 使用的
var ratingDetailId =-1;
var S = '';
$('.open_zs').click(function(){
	//alert($('.open_zs').html());
	/*if($('.open_zs').html()=="自评总述&gt;&gt;"){
		$('.zs_everyPart').slideDown();
		$(this).html('自评总述&lt;&lt;');
	}else{
		$('.zs_everyPart').slideUp();
		$(this).html('自评总述&gt;&gt;');
	}*/
	if($(this).hasClass('zs_s')){
		$(this).removeClass('zs_s').addClass('zs_z');
		$('.zs_everyPart').slideUp();
	}else{
		$(this).removeClass('zs_z').addClass('zs_s');
		$('.zs_everyPart').slideDown();
	}
});

$(function () {
//	var ue = UE.getEditor('editor');

	//$('#myModal').modal({backdrop: 'static', keyboard: false})
	// 初始化 页面 关闭按钮  添加按钮  修改按钮  删除按钮
	initAll();
	getPaName();
	saveNext();
    /*tab切换*/
	changeTab();
	blurr();
    //初始化 弹框
    initTan();
});
KindEditor.ready(function(K) {
	S=K;
	window.editor = K.create('#editor', {
		items:['source', 'undo', 'redo','image' ,  'insertfile', 'fullscreen'],
		langType : 'zh-CN',
		width : '550px',
		minWidth: '500px',
		minHeight: '100px',
		height:'300px',
		resizeType:0,
		allowFileUpload:true,
	    uploadJson : webPath + '/upload_fileUpload.action'
	   
	});
    if (window.addEventListener){
    	pastimg();  
    }
    
	});
var ifrbady;  
function pastimg() {  
    $("iframe").each(function (i, v) {  
        //otype = $(v).attr("type");  
        if ($(v).attr("class") == "ke-edit-iframe") {  
            // $(v)[0].style.border = 0;  
            ifrbady = $(v)[0].contentWindow.document.body;  
            $(v)[0].contentWindow.document.body.id = "editor";  
        }  
    });  
    var imgReader = function (item) {  
        var blob = item.getAsFile(),  
            reader = new FileReader();  
  
  
        reader.onload = function (e) {  
            var img = new Image();  
  
  
            img.src = e.target.result;  
            //document.body.appendChild(img);  
            ifrbady.appendChild(img);  
        };  
  
  
        reader.readAsDataURL(blob);  
    };  
  
  
    ifrbady.addEventListener('paste', function (e) {  
        var clipboardData = e.clipboardData,  
        i = 0,  
        items, item, types;  
        if (clipboardData) {  
            items = clipboardData.items;  
            if (!items) {  
                return;  
            }  
            item = items[0];  
            types = clipboardData.types || [];  
            for (; i < types.length; i++) {  
                if (types[i] === 'Files') {  
                    item = items[i];  
                    break;  
                }  
            }  
            if (item && item.kind === 'file' && item.type.match(/^image\//i)) {  
                imgReader(item);  
            }  
        }  
    });  
}  
function changeTab(){
	$('.change-tab').click(function(){
        $('.change-tab').removeClass('on');
        $(this).addClass('on');
        //var n=$(this).index();
        $('.change_box').show();
        //$('.change_box').eq(n).show();
        var onename = $(this).find("span").attr("title");
        getTagetList(onename);
    });
}
var ratingExplainIsOrNo = "";
function initTan(){
	$('.beginFill').click(function(){
		ratingExplainIsOrNo = "";
	   // $(this).attr({'data-toggle':'modal','data-target':'#begin-zp'});
	    $('#begin-zp').modal({backdrop: 'static', keyboard: false});
	    ratingDetailId = $(this).attr('ratingDetailId');
	    getCurrentIn(ratingDetailId);
	    getWriteData(ratingDetailId);
    });
	$('.rewhite').click(function(){
	    //$(this).attr({'data-toggle':'modal','data-target':'#begin-zp'});
	    $('#begin-zp').modal({backdrop: 'static', keyboard: false});
	    ratingDetailId = $(this).attr('ratingDetailId');
	    ratingExplainIsOrNo = $(this).parent().parent().find(".ratingExplain").html();
	    $("#ratingExplain").val(ratingExplainIsOrNo);
	    getCurrentIn(ratingDetailId);
	    getWriteData(ratingDetailId);

    });
}
function getCurrentIn(ratingDetailId){
	$("#currentIn").html("");
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_getCurrentIn.action?ratingDetailId="+ratingDetailId,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		weight = data.weight;
        		$("#weight").html(weight.toFixed(2));
        		$("#ratingScore").attr('placeholder','(0.00-'+weight.toFixed(2)+')');
        		var html = "";
        		if(data.success == "true"){
        			if(data.quota1 != null && data.quota1 != ''){
        				quota1=data.quota1;
        				html+="<span style='display:inline;'>"+data.quota1+"</span>";
        			}
        			if(data.quota2 != null && data.quota2 != ''){
        				html+="<i></i><span style='display:inline;'>"+data.quota2+"</span>";
        			}
        			if(data.quota3 != null && data.quota3 != ''){
        				html+="<i></i><span class=\"last_leveal\" style='display:inline;'>"+data.quota3+"</span>";
        			}
        		}
        		$("#currentIn").html(html);
        		$("#checkContent").html(data.checkContent);
        		$("#ratingScore").val(data.ratingScore);
            } else {
            	alert("请求异常");
            }
        },error: function () {// 请求失败处理函数
        }
    });
}
//function checkFlag(){
//	$(document).click(function(e){
//		var _con = $('.modal'); // 设置目标区域
//		if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
//			//logOut();
//			//$('#add_box').slideUp();
//	        //$('.lm_bg').hide();
//	        $('.modal-backdrop').css('display','none');
//			//return;
//		}
//	});
//}
// 获取是否填写数据
function getWriteData(ratingDetailId){
	$("#divdiv").html("");
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_getWriteData.action?ratingDetailId="+ratingDetailId,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var dataList = data.list;
        		
        		if(dataList!= null && dataList.length>0){
        			var html = "";
        			for ( var i = 0; i < dataList.length; i++) {
						var dataBase = dataList[i];
		                html+="<div class=\"everyTip clearfix\"><div paRatingChannelId="+dataBase.id+" class=\"fl dele-icon\"></div>"+
	                        "<div class=\"fl column-name\">"+dataBase.channelName+
	                        "</div><div class=\"fl column-address\">"+dataBase.channelUrl+
	                        "</div><div class=\"fl modify paRatingChannelId\" paRatingChannelId="+dataBase.id+">修改</div></div>";
        			}
        			$("#divdiv").html(html);
        			checkpaRatingDetail();
        			deleteChannel();
        			
        		}
        		$("#ratingExplain").val(ratingExplainIsOrNo);
        		
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}
//截图  页面 回填数据
function checkpaRatingDetail(){
	var checkId =-1;
	var chUrl = "";
	var chName="";
	$('.paRatingChannelId').click(function(){
        $('#add_box').slideDown();
        $('.lm_bg').show();
        $("#addOrUpdate").val("update");
        editor.html('');
        checkId =  $(this).attr("paRatingChannelId");
        $("#paRatingChannelId").val(checkId);
        chName =   $(this).parent().find(".column-name").html();
        chUrl  =   $(this).parent().find(".column-address").html();
        chName = chName.replace(/\&amp;/g, "&");
        chUrl = chUrl.replace(/\&amp;/g, "&");
    	$("#channelName").val(chName);
    	$("#channelUrl").val(chUrl);
    	// 通过 paRatingChannelId 获取 图片 附件 
    	var user = {
    			paRatingChannelId:checkId
            };
    	$.ajax({
            async: false,
            cache: false,
            data:user,
            type: 'POST',
            dataType: "json",
            url: webPath + "/paRatingDetail_getImgsAndAttch.action",// 请求的action路径
            success: function (data) { // 请求成功后处理函数。
    		    if(data){
    		    	//editor.html("");
    		    	var dataList = data.list;
            		if(dataList!= null && dataList.length>0){
            			var dataBase = dataList[0];
            	    	var pathName = window.location.pathname.substring(1);
            			var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
            			
            	    	var imgUrlStr =dataBase.imgUrl ;
            	    	var imgUrlStrArr = "";//图片拆分后的
            	    	
            	    	if(imgUrlStr!= null && imgUrlStr.length>0){
            	    		imgUrlStrArr = imgUrlStr.split("|");
            	    	}
            	    	var content = "<p>";
            	    	if(imgUrlStr.length > 0){
            	    		for(var i=0;i<imgUrlStrArr.length;i++){
            	    			if(imgUrlStrArr[i] != null && imgUrlStrArr[i] != ""){
            	    				content += ("<img src='" + "/" + webName +imgUrlStrArr[i] + "' border='0'><br><br>");
            	    			}
            	    		}
            	    	}
            	    	var path = dataBase.path;//附件路径
            	    	var names = dataBase.aliasName;//附件别名
            	    	if(path!= null && path.length>0){
            	    		pathArr = path.split("|");
            	    		namesArr = names.split("|");
            	    		for(var i=0;i<pathArr.length;i++){
            	    			if(pathArr[i] != null && pathArr[i] != ""){
            	    				content +="<a href='" + "/" + webName +pathArr[i] + "' target='_blank'>"+namesArr[i]+"</a><br>";
            	    			}
            	    		}
            	    	}
            	    	content += "<br><br><br></p>";    
            	    	//S.Html('#editor', content);
            	    	editor.html(content);
//            	    	$("#content1").val(content);
//            	    	reloadleft();
            		}
    		    } else {
    		    	alert("添加失败");
    		    }
            },
            error: function () {// 请求失败处理函数
            	alert("添加失败");
            }
        });

	});

}
function reloadleft(){
//	var leftiframeid = document.getElementById('eWebEditor1');//left为对应iframe的id
//	leftiframeid.src = "ewebeditor/ewebeditor.htm?id=content1&style=coolblue";//ileft.html为frame的页面
}
//添加栏目  或者  修改栏目
function addOrUpdateChannel(){
	 var updateOrAdd = $("#addOrUpdate").val();
	if(updateOrAdd =="add"){
		addChannel();
	}else if(updateOrAdd =="update"){
		updateChannel();
	}
}
var URL_REG =/((http|ftp|https):)?\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/\~\+#]*[\w\-\@?^=%&amp;/\~\+#])?/;
//验证是否手机号 mobile或者null,空
function isUrl(obj) {
  var controlObj = $.trim(obj);
  if (URL_REG.test(controlObj)) {
      return true;
  } else {
      return false;
  }
}
//添加栏目数据
function addChannel(){
	
	
	
	ratingExplainIsOrNo = $("#ratingExplain").val();
	var channelName = $("#channelName").val();
	var channelUrl = $("#channelUrl").val();
	if(checkLength('channelName',1000,"内容过长") == '22'){
		return '';
	}
	if(checkLength('channelUrl',1000,"内容过长") == '22'){
		return '';
	}
	
	
	//var ratingDetailId = ratingDetailId;
	var siteCode =  $("#siteCode").val();
	//var ht = document.getElementById("eWebEditor1").contentWindow.getHTML();
	editor.sync(); 
    //alert($("#editor").val());
	ht=$("#editor").val();
	if(channelName.length == 0){
		changeCss("channelName", "该选项为必选项");
		return false;
	}
//	if(channelUrl.length == 0){
//		changeCss("channelUrl", "该选项为必选项");
//		return false;
//	}
	if(!isUrl(channelUrl)){
		changeCss("channelUrl", "请输入有效的网址");
		return false;
	}
	var user = {
			channelName:channelName,
			channelUrl:channelUrl,
			ratingDetailId:ratingDetailId,
			siteCode:siteCode,
			ht:ht
        };
	$.ajax({
        async: false,
        cache: false,
        data:user,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_addChannel.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
		    if(data){
	        	if(data.success == 'true'){
	        		alert("添加成功");
	        		$('#add_box').slideUp();
	                $('.lm_bg').hide();
	                $("#addOrUpdate").val("--");
	        		getWriteData(ratingDetailId);
	        	}
		    } else {
		    	alert("添加失败");
		    }
        },
        error: function () {// 请求失败处理函数
        	alert("添加失败");
        }
    });

}
//删除栏目
function deleteChannel(){
	ratingExplainIsOrNo = $("#ratingExplain").val();
    $('.dele-icon').click(function(){
         //$(this).parent().remove();
         var paRatingChannelId=$(this).attr("paRatingChannelId");
     	var user = {
    			paRatingChannelId:paRatingChannelId
            };
    	$.ajax({
            async: false,
            cache: false,
            data:user,
            type: 'POST',
            dataType: "json",
            url: webPath + "/paRatingDetail_deleteChannel.action",// 请求的action路径
            success: function (data) { // 请求成功后处理函数。
    		    if(data){
    		        	if(data.success == "true"){
    		        		alert("删除成功");
    		        		getWriteData(ratingDetailId);
    		        	}
    		    } else {
    		    	alert("删除失败");
    		    }
            },
            error: function () {// 请求失败处理函数
            	alert("删除失败");
            }
        });
    });
}
//修改 栏目
function updateChannel(){
	var channelName = $("#channelName").val();
	var channelUrl = $("#channelUrl").val();
	if(checkLength('channelName',1000,"内容过长") == '22'){
		return '';
	}
	if(checkLength('channelUrl',1000,"内容过长") == '22'){
		return '';
	}
	var paRatingChannelId = $("#paRatingChannelId").val();
	//var ht = document.getElementById("eWebEditor1").contentWindow.getHTML();
	editor.sync(); 
    //alert($("#editor").val());
	ht=$("#editor").val();
	if(channelName.length == 0){
		changeCss("channelName", "该选项为必选项");
		return false;
	}
//	if(channelUrl.length == 0){
//		changeCss("channelUrl", "该选项为必选项");
//		return false;
//	}
	if(!isUrl(channelUrl)){
		changeCss("channelUrl", "请输入有效的网址");
		return false;
	}
	var user = {
			channelName:channelName,
			channelUrl:channelUrl,
			ratingDetailId:ratingDetailId,
			paRatingChannelId:paRatingChannelId,
			ht:ht
        };
	$.ajax({
        async: false,
        cache: false,
        data:user,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_updateChannel.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
		    if(data){
		        	if(data.success == "true"){
		        		alert("更新成功");
		        		$('#add_box').slideUp();
		                $('.lm_bg').hide();
		                $("#addOrUpdate").val("--");
		                getWriteData(ratingDetailId);
		        	}
		    } else {
		    	alert("更新失败");
		    }
        },
        error: function () {// 请求失败处理函数
        	alert("更新失败");
        }
    });

}


//修改出错的input的外观
function changeCss(id, Validatemsg) {
	   $("#" + id).tips({
	       side: 1,
	       msg: Validatemsg,
	       bg: 'red',
	       time: 1
	   });
	   $("#" + id).focus();
}


// 返回上一级
function goback(){
	 //window.location.href=history.go(-1);	
	window.location.href=webPath + "/paTask_paTaskInfo.action";
}
// 获取一级 指标
function getPaName(){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_getOneRating.action?ratingId="+ratingId,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var dataList = data.list;
        		if(dataList!= null && dataList.length>0){
        			var html = "";
        			for ( var i = 0; i < dataList.length; i++) {
						var dataBase = dataList[i];
						if(quota1==""){
							if(i==0){
								if(dataBase.quota1.length>5){
									html +="<div class=\"fl change-tab on\"><span title="+dataBase.quota1+">"+dataBase.quota1.substring(0,5)+"..</span><i class=\"left\"></i></div>";
								}else{
									html +="<div class=\"fl change-tab on\"><span title="+dataBase.quota1+">"+dataBase.quota1+"</span><i class=\"left\"></i></div>";
								}
								getTagetList(dataBase.quota1);
							}else{
								if(dataBase.quota1.length>5){
									html +="<div class=\"fl change-tab margl-1\"><span title="+dataBase.quota1+">"+dataBase.quota1.substring(0,5)+"..</span><i class=\"left\"></i></div>";
								}else{
									html +="<div class=\"fl change-tab margl-1\"><span title="+dataBase.quota1+">"+dataBase.quota1+"</span><i class=\"left\"></i></div>";
								}
								
							}
						}else{
							if(dataBase.quota1==quota1){
								if(dataBase.quota1.length>5){
									html +="<div class=\"fl change-tab on\"><span title="+dataBase.quota1+">"+dataBase.quota1.substring(0,5)+"..</span><i class=\"left\"></i></div>";
								}else{
									html +="<div class=\"fl change-tab on\"><span title="+dataBase.quota1+">"+dataBase.quota1+"</span><i class=\"left\"></i></div>";
								}
								
								getTagetList(dataBase.quota1);
							}else{
								if(dataBase.quota1.length>5){
									html +="<div class=\"fl change-tab margl-1\"><span title="+dataBase.quota1+">"+dataBase.quota1.substring(0,5)+"..</span><i class=\"left\"></i></div>";
								}else{
									html +="<div class=\"fl change-tab margl-1\"><span title="+dataBase.quota1+">"+dataBase.quota1+"</span><i class=\"left\"></i></div>";
								}
								
							}
						}
						
        			}
        			$("#getName").html(html);
        		}
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}
// 获取最终级别的指标  填写数据
function getTagetList(oneName){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_getOtherList.action?",//oneName="+oneName+"&ratingId="+ratingId, 请求的action路径
        data:{
        	'oneName':oneName,
        	'ratingId':ratingId
        },
        success: function (data) { // 请求成功后处理函数。
        	if (data.success == "success") {
        		var dataList = data.list;
        		if(dataList!= null && dataList.length>0){
        			var html = "";
        			for ( var i = 0; i < dataList.length; i++) {
						var dataBase = dataList[i];
						html +="<tr><td class=\"text-center fs\">"+dataBase.quota2+"</td>"+
                        	   "<td class=\"text-center w7p fs\">"+dataBase.quota3+"</td>"+
                               "<td class=\"text-center\">"+dataBase.appraisalContent+"</td>";
						if(dataBase.ratingScore<0){
							html+="<td class=\"text-center\">"+"</td>"+
		                               "<td class=\"text-center ratingExplain\">"+dataBase.ratingExplain+"</td>"+
		                               "<td class=\"text-center\">"+dataBase.chanlCount+"</td>";
						}else{
							html+="<td class=\"text-center\">"+dataBase.ratingScore+"</td>"+
		                               "<td class=\"text-center ratingExplain\">"+dataBase.ratingExplain+"</td>"+
		                               "<td class=\"text-center\">"+dataBase.chanlCount+"</td>";
						}
                               
						if(dataBase.ratingStauts == 1){
							html +="<td class=\"text-center\"><span class=\"\">已填报</span></td>"+
								   "<td class=\"text-center tb-op\"><div class=\"rewhite\" ratingDetailId="+dataBase.id+" >修改</div></td></tr>";
						}else{
							html +="<td class=\"text-center\"><span class=\"colo-fb0012\">未填报</span></td>"+
							       "<td class=\"text-center tb-op\"><div class=\"beginFill\" ratingDetailId="+dataBase.id+">开始填报</div></td></tr>";
						}
                         
        			}
        			$("#targets").html(html);
        			initTan();
        		}else{
        			$("#targets").html("");
        		}
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}
// 初始化 页面 关闭按钮  添加按钮  修改按钮  删除按钮
function initAll(){ 
	$("#overallSituation").text($("#overallSituation2").html());
	$("#problemsSuggestions").text($("#problemsSuggestions2").html());
	$("#other").text($("#other2").html());
	$("#plan").text($("#plan2").html());
    $('.add').click(function(){
        $('#add_box').slideDown();
        $('.lm_bg').show();
        $("#addOrUpdate").val("add");
        $("#channelName").val("");
        $("#channelUrl").val("");
//        $("#content1").val("");
//        reloadleft();
        editor.html("");
    });

    $('#lm_close').click(function(){
    	$('#add_box').slideUp();
        $('.lm_bg').hide();
        $("#addOrUpdate").val("--");
    });
    $('#moddel').click(function(){
    	getPaName();
    	changeTab();
    });
    $('#closeCheckM').click(function(){
    	$('#submit-data').modal('hide');
    });

    
    submitData();
}
var MOBILE_REG = /^(0|86|17951)?(13[0-9]|1[56][012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
var TELEPHONE_REG = /\d{3}-\d{8}|\d{4}-\d{7}/; //固定电话正则验证

//验证是否手机号 mobile和固定电话号码 是否否和格式  或者null,空
function isMobile(obj) {
  var controlObj = $.trim(obj);
  if (MOBILE_REG.test(obj)) {
      return true;
  }else if(TELEPHONE_REG.test(obj)){
  	return true;
  } else {
      return false;
  }
}
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
function submitData(){
    $('.submit-data').click(function(){
    	 
    	var ratingName  = $.trim($("#ratingName").val());
    	if(ratingName.length == 0){
    	   changeCss("ratingName", "此处为必填项"); 
    	   return false;
    	}
    	var companyName  = $.trim($("#companyName").val());
    	if(companyName.length == 0){
    		   changeCss("companyName", "此处为必填项"); 
    		   return false;
    	}
    	var ratingPhone  = $.trim($("#ratingPhone").val());
    	if(!isMobile(ratingPhone)){
    		changeCss("ratingPhone", "请输入有效的手机号或固定电话号码");
    		return false;
    	}
    	
    	if(checkLength('ratingName',50,"内容过长") == '22'){
    		return '';
    	}
    	if(checkLength('companyName',150,"内容过长") == '22'){
    		return '';
    	}
    	
    	var overallSituation  = $.trim($("#overallSituation").val());
    	if(checkLength('overallSituation',3000,"内容过长") == '22'){
    		return '';
    	}
//    	if(overallSituation.length == 0){
//    	   changeCss("overallSituation", "此处为必填项"); 
//    	   return false;
//    	}
    	var problemsSuggestions  = $.trim($("#problemsSuggestions").val());
    	if(checkLength('problemsSuggestions',3000,"内容过长") == '22'){
    		return '';
    	}
//    	if(problemsSuggestions.length == 0){
//    		   changeCss("problemsSuggestions", "此处为必填项"); 
//    		   return false;
//    	}
    	var plan  = $.trim($("#plan").val());
    	if(checkLength('plan',3000,"内容过长") == '22'){
    		return '';
    	}
//    	if(plan.length == 0){
//    	   changeCss("plan", "此处为必填项"); 
//    	   return false;
//    	}
    	var other  = $.trim($("#other").val());
    	if(checkLength('other',3000,"内容过长") == '22'){
    		return '';
    	}
//    	if(other.length == 0){
//    		   changeCss("other", "此处为必填项"); 
//    		   return false;
//    	}
    	
    	if(!confirm("提交数据后不可以修改，是否确认提交？")){
   		 	return '';
   	 	}	
    	var user = {
    			ratingName:ratingName,
    			companyName:companyName,
    			ratingPhone:ratingPhone,
    			ratingId:ratingId,
    			
    			other:other,
    			plan:plan,
    			problemsSuggestions:problemsSuggestions,
    			overallSituation:overallSituation,
    			
    			targetTaskId:targetTaskId
            };
    	$.ajax({
            async: false,
            cache: false,
            data:user,
            type: 'POST',
            dataType: "json",
            url: webPath + "/paRatingDetail_checkIsAllFinash.action",// 请求的action路径
            success: function (data) { // 请求成功后处理函数。
    		    if(data){
    	        	if(data.success == 'success'){
    	        		createWord();
    	        		alert("提交成功");
    	        		goback();
    	        	}else if(data.success == 'noFinash'){
    	        		$('#submit-data').modal('show');
    	        	}
    		    } else {
    		    	alert("提交失败");
    		    }
            },
            error: function () {// 请求失败处理函数
            	alert("提交失败");
            }
        });
    	
    });
}
/**
 * @param url
 * @returns 返回URL是否合法
 */
function onLineUrl(){
    var url = $("#channelUrl").val();
	var isLegal = false;
	var user = {
			url:url
        };
	$.ajax({
        async: false,
        cache: false,
        data:user,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRatingDetail_onCheckUrl.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
		    if(data){
	        	if(data.result == 'true'){
	        		changeCss("channelUrl", "连通成功");
	        	}else{
	        		changeCss("channelUrl", "连通失败");
	        	}
		    } else {
		    	changeCss("channelUrl", "连通失败");
		    }
        },
        error: function () {// 请求失败处理函数
        	changeCss("channelUrl", "连通失败");
        }
    });

	
}
function saveNext(){
	$('.saveNext').click(function(){
	    var ratingExplain = $("#ratingExplain").val();
	    var ratingScore = $.trim($("#ratingScore").val());
	    if(ratingScore != ''){
	    	if (isNaN(ratingScore)) {  
	    		changeCss("ratingScore", "请输入有效的分数值");
	    		return;
	        }else if(ratingScore <0 || ratingScore>weight){
	        	changeCss("ratingScore", "请输入有效的分数值");
	    		return;
	        }else{
	        	ratingScore = parseFloat(ratingScore);
	        } 
	    }
	    
	    if($.trim(ratingExplain).length<1){
    		changeCss("ratingExplain", "此处为必填项");
    		return;
    	}
    	if(checkLength('ratingExplain',1500,"内容过长") == '22'){
    		return '';
    	}
		var user = {
				ratingScore:ratingScore,
				ratingExplain:ratingExplain,
				ratingId:ratingId,
				targetTaskId:targetTaskId,
				ratingDetailId:ratingDetailId
	        };
		$.ajax({
	        async: false,
	        cache: false,
	        data:user,
	        type: 'POST',
	        dataType: "json",
	        url: webPath + "/paRatingDetail_savePaRating.action",// 请求的action路径
	        success: function (data) { // 请求成功后处理函数。
			    if(data){
		        	if(data.success == 'true'){
		        		
		        		//createWord();
		        		alert("保存成功");
		        		getPaName();
		        		changeTab();
		        		//$('#begin-zp').attr({'data-dismiss':'modal'});
		        		$('#begin-zp').modal('hide');
		        	}else{
		        		alert("保存失败");
		        	}
			    } else {
			    	alert("保存失败");
			    }
	        },
	        error: function () {// 请求失败处理函数
	        	alert("保存失败");
	        }
	    });
	});
}

function createWord(){
	$.ajax({
        async: true,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paTask_createWord.action?ratingId="+ratingId,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
		    if(data){
	        	if(data.success == 'true'){
	        	}
		    } else {
		    }
        }
    });
}

function blurr(){
	$(".blurr").blur(function(){
	    	var ratingName  = $.trim($("#ratingName").val());
	    	var companyName  = $.trim($("#companyName").val());
	    	var ratingPhone  = $.trim($("#ratingPhone").val());
	    	if(checkLength('ratingName',50,"内容过长") == '22'){
	    		return '';
	    	}
	    	if(checkLength('companyName',150,"内容过长") == '22'){
	    		return '';
	    	}
	    	var overallSituation  = $.trim($("#overallSituation").val());
	    	var problemsSuggestions  = $.trim($("#problemsSuggestions").val());
	    	var plan  = $.trim($("#plan").val());
	    	var other  = $.trim($("#other").val());
	    	
	    	
	    	if(checkLength('overallSituation',3000,"内容过长") == '22'){
	    		return '';
	    	}
	    	if(checkLength('problemsSuggestions',3000,"内容过长") == '22'){
	    		return '';
	    	}
	    	if(checkLength('plan',3000,"内容过长") == '22'){
	    		return '';
	    	}
	    	if(checkLength('other',3000,"内容过长") == '22'){
	    		return '';
	    	}
	    	
	    	var user = {
	    			ratingName:ratingName,
	    			companyName:companyName,
	    			ratingPhone:ratingPhone,
	    			other:other,
	    			plan:plan,
	    			problemsSuggestions:problemsSuggestions,
	    			ratingId:ratingId,
	    			overallSituation:overallSituation
	            };
	    	
	    	$.ajax({
	            async: true,
	            cache: false,
	            type: 'POST',
	            data:user,
	            dataType: "json",
	            url: webPath + "/paRating_updateRating.action?ratingId="+ratingId,// 请求的action路径
	            success: function (data) { // 请求成功后处理函数。
	    		    if(data){
	    	        	if(data.success == 'true'){
	    	        	}
	    		    } else {
	    		    }
	            }
	        });
	    	
		});
}
function checkLength(id,length,content){
	if($.trim($("#"+id).val()).length>length){
		changeCss(id, content);
		return "22";
	}
	return "1";
}
