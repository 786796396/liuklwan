var out;
var channelUrlVal=0;
var siteCode;

$(function (){
	siteCode = $("#siteCode").val();
	
	getChannelPointInfo();
	
	$('#checkChannalAllId').iCheck('uncheck'); 

	$( "#checkChannalAllId").on('ifChecked' , function (event){
		$('input[name=delId]').iCheck('check'); 
	}); 
	
	$( "#checkChannalAllId").on('ifUnchecked' , function (event){
		$('input[name=delId]').iCheck('uncheck'); 
	}); 
	
	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
		 if(searchInfo==""){
			 $("#table_data_channel_columnTab tr").show();
		 }else{
			 $("#table_data_channel_columnTab").find(".wz-name").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().parent().find(".cor-01a5ec").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });
	
 	$("#homeUrl_table").removeClass("failure-input");
	$("#jumpUrl_table").removeClass("failure-input");
	 $("#checkbox_tr").on('ifChecked', function(event){
		 $("#jumpUrl_tr").removeClass("disabled-tr");
		 $('#jumpUrl_table').removeAttr("disabled"); 
		 $('#checkHomeJumpUrlBId').show(); 
		 isHomeUrlLegal=true;
		 isHomeJumpUrlLegal=false;
		 $("#homeUrl_table").siblings("i").remove();
		 $("#homeUrl_table").siblings(".error-msg").remove();
		 $("#homeUrl_table").removeClass("failure-input");	 
		 $("#jumpUrl_table").siblings(".error-msg").remove();
	});
	$("#checkbox_tr").on('ifUnchecked', function(event){
		$("#jumpUrl_tr").addClass("disabled-tr");
		 $('#jumpUrl_table').attr("disabled","disabled"); 
		 $('#checkHomeJumpUrlBId').hide();
		 
		 $("#jumpUrl_table").removeClass("failure-input");
		 $("#jumpUrl_table").siblings("i").remove();
		 $("#jumpUrl_table").siblings(".error-msg").remove();
		 isCheckHomeJumpUrl=false;
		 isCheckHomeUrl=false;
		 isHomeUrlLegal=false;
		 isHomeJumpUrlLegal=true;
	});
	$("#checkbox_tr2").on('ifChecked', function(event){
	    $("#jumpUrl_tr2").removeClass("disabled-tr");
	    $('#jump_url_id').removeAttr("disabled");
	
	
	    $("#channel_url_id").siblings("i").remove();
	    $("#channel_url_id").siblings(".error-msg").remove();
	    $("#channel_url_id").removeClass("failure-input");
	    $("#jump_url_id").siblings(".error-msg").remove();
	});
	$("#checkbox_tr2").on('ifUnchecked', function(event){
	    $("#jumpUrl_tr2").addClass("disabled-tr");
	    $('#jump_url_id').attr("disabled","disabled");
	
	
	    $("#jump_url_id").removeClass("failure-input");
	    $("#jump_url_id").siblings("i").remove();
	    $("#jump_url_id").siblings(".error-msg").remove();
	    $('#jump_url_id').val('');
	});
	
	var typeId = $("#typeId").val();
	if(typeId == 1){//组织跳转过来
		$("#ulIds li").removeClass("active");
		$("#culId").addClass("active");  
		$("#basicInfo").hide();
		$("#columnInfo").show();
	}
	
	$("#baseId").click(function(){
		$("#basicInfo").show();
		$("#columnInfo").hide();
	});
	
	$("#culId").click(function(){
		$("#basicInfo").hide();
		$("#columnInfo").show();
	});
	
});


//网站名称不能为空
function checkSiteName_table(){
	var siteName_table = $('#siteName_table' ).val();
	$('#siteName_table' ).siblings(".error-msg").remove();
	if(isNull(siteName_table)){
		$('#siteName_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入网站名称</span>");
		return false;
	}
	return true;
}
//主办单位非空校验
function checkSiteManageUnit_table(){
	var siteManageUnit_table = $('#siteManageUnit_table' ).val();
	$('#siteManageUnit_table' ).siblings(".error-msg").remove();
	if(isNull(siteManageUnit_table)){
		$('#siteManageUnit_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入网站主管单位</span>");
		return false;
	}
	
	return true;
}
//办公地址非空校验
function checkOfficeAddress_table(){
	var officeAddress_table = $('#officeAddress_table' ).val();
	$('#officeAddress_table' ).siblings(".error-msg").remove();
	if(isNull(officeAddress_table)){
		$('#officeAddress_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入办公地址</span>");
		return false;
	}else{
		if(officeAddress_table.length>100){
			$('#officeAddress_table').after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;办公地址长度不能超过100</span>");
			return false;
		}
	}
	return true;
}
//首页url不能为空
function checkHomeUrl_table(){
	var homeUrl_table = $("#homeUrl_table").val();
	$("#homeUrl_table").siblings(".error-msg").remove();
	if(isNull(homeUrl_table)){
        $("#home_url_loading-box").hide();//在错误信息之前先把校验信息清空掉
		$("#homeUrl_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	return true;
}
/**
 * 首页url线校验事件
 */
function onLineCheckHomeUrl(){

    var isEmpty = checkHomeUrl_table();
    if(!isEmpty){
        return;
    }
	//首先移除class
	//add by sunjq 先将div置为灰色并且不可点击
	$("#home_url_conn_test").addClass("dis-btn-07c1f2").removeAttr("onclick");
	//enda dd
	$("#home_url_conn_test").siblings(".column-loading-box").removeClass("column-loading-sub");
	$("#home_url_conn_test").siblings(".column-loading-box").removeClass("column-loading-error");
	$("#home_url_conn_test").siblings(".error-msg").remove();
	$("#home_url_loading-box").attr("style","display:block");
	//进度条加载
	progress("#home_url_loading-box");
	//获取栏目url
	var homeUrl = $("#homeUrl_table").val();
	//验证栏目url是否合法
	var isLe = onLineCheckUrl(homeUrl,"home_url_loading-box","home_loading_id");
}
/**
 * 进度条加载到80%
 * @param testUrl
 */
function progress(testUrl){
	var urlConnTest=testUrl;
	channelUrlVal = channelUrlVal + 4;
    $(urlConnTest).find(".loading-num").siblings("span").html("连通测试中，可能需要几十秒钟，请稍候......");
	$(urlConnTest).find(".column-loading-con").css("width",channelUrlVal+"%");
  	$(urlConnTest).find(".loading-num").html(channelUrlVal+"%");
	if (channelUrlVal <= 80) {
		out=setTimeout("progress('"+urlConnTest+"')", 100);
	}
}
/**
 * @param url
 * @returns 返回URL是否合法
 */
function onLineCheckUrl(url,id1,id2){
	var jsonObj = {};
    jsonObj["url"] = url;
	var isLegal = false;
	
	$.ajax({
        async: true,
        type: "POST",
        url: webPath+"/manageInfo_onlineVerifyUrl.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json"
    }).done(function (response) {
    	isLegal = response.result;
    	loadPlanBar(id1,id2, isLegal);

    }).fail(function (data) {
    	loadPlanBar(id1,id2, isLegal);
    	alert("验证错误！");
    }).always(function () {
    	clearTimeout(out);
    });	
	return isLegal;
	
}
/**
 * 
 * @param url1 
 * @param url2
 * @returns
 */
function loadPlanBar(id1,id2, isLegal){
	if(isLegal){//url合法
		isChannelUrlLegal=true;
		$("#"+id1).addClass("column-loading-sub");
    	
		$("#"+id2).html("<span>连通测试成功！</span><span class='loading-num'>100%</span>");
		channelUrlVal = 0;
		$("#"+id1).find(".column-loading-con").css("width","100%");
		$("#"+id1).find(".loading-num").html("100%");
		
		if(id1 == "home_url_loading-box"){
			//add by sunjq 打开按钮事件和颜色
			$("#home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeUrl()");
			if(isLegal){
				//首页url校验且成功
				$("#home_url_state_id").val("1");
			}else{
				$("#home_url_state_id").val("0");
			}

			//enda dd
		}else if(id1 == "jump_url_loading-box"){
			//add by sunjq 打开按钮事件和颜色
			$("#jump_home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeJumpUrl()");
			
			if(isLegal){
				//跳转url校验且成功
				$("#jump_url_state_id").val("1");
			}else{
				$("#jump_url_state_id").val("0");
			}
			//enda dd
		}else if(id1 == "model_channel_loading_box"){
			//add by sunjq 打开按钮事件和颜色     onLineCheckHomeJumpUrl
			$("#channel_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick"," onLineCheckChannelUrl()");
			
			if(isLegal){
				//栏目url校验且成功
				$("#channel_url_state_id").val("1");
			}else{
				$("#channel_url_state_id").val("0");
			}
			
			//enda dd
		}else if(id1 == "channel_jump_loading_box"){
			//add by sunjq 打开按钮事件和颜色
			$("#jump_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckjumpUrl()");
			
			if(isLegal){
				//栏目跳转url校验且成功
				$("#channel_jump_url_state_id").val("1");
			}else{
				$("#channel_jump_url_state_id").val("0");
			}
			//enda dd
		}
	}else{
		$("#"+id1).addClass("column-loading-error");
		
		$("#"+id2).html("<span>开普云监测服务器连不通您填写的 URL!</span><span class='loading-num'>100%</span>");
		channelUrlVal = 0;
		$("#"+id1).find(".column-loading-con").css("width","100%");
		$("#"+id1).find(".loading-num").html("100%");

        if(id1 == "home_url_loading-box"){
            //add by sunjq 打开按钮事件和颜色
            $("#home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeUrl()");
            //enda dd
        }else if(id1 == "jump_url_loading-box"){
            //add by sunjq 打开按钮事件和颜色
            $("#jump_home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeJumpUrl()");
            //enda dd
        }
        else if(id1 == "model_channel_loading_box"){
            //add by sunjq 打开按钮事件和颜色
            $("#channel_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckChannelUrl()");
            //enda dd
        }
        else if(id1 == "channel_jump_loading_box"){
            //add by sunjq 打开按钮事件和颜色
            $("#jump_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckjumpUrl()");
            //enda dd
        }
	}
}
/**
* 在线校验跳转URL
*/
function onLineCheckjumpUrl(){
	
	channelUrlVal = 0;
    if (!$("#channel_url_conn_test").parents("#jumpUrl_tr2").hasClass("disabled-tr")) {
    	
    	
    	
    	$("#channel_jump_loading_box").siblings(".error-msg").remove();
    	$("#channel_jump_loading_box").removeClass("column-loading-sub");
    	$("#channel_jump_loading_box").removeClass("column-loading-error");
    	$("#channel_jump_loading_box").attr("style","display:block");
    	//获取跳转栏目url
    	var jumpUrl = $("#jump_url_id").val();
    	//获取栏目url
    	var channelUrl = $("#channel_url_id").val();
    	
    	if(!isNull(jumpUrl) && (jumpUrl==channelUrl)){
    		
    		$("#channel_jump_loading_box").attr("style","display:none");
    		$("#channel_jump_loading_box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL和跳转URL不能相同</span>");
    		$("#jump_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckjumpUrl()");
    	}else{
    		var testUrl="#channel_jump_loading_box";
    		if(isNull(jumpUrl)){
    			$('#jump_url_conn_test' ).siblings(".column-loading-box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL不能为空</span>");
    			$("#channel_jump_loading_box").attr("style","display:none");
    		}else{
    			
    			$("#jump_url_conn_test").addClass("dis-btn-07c1f2").removeAttr("onclick");
    			//进度条加载
    			progress("#channel_jump_loading_box");	
    			//验证栏目url是否合法
    			var isLe = onLineCheckUrl(jumpUrl,"channel_jump_loading_box","modal_jump_loading_id");
    		}
    	}
    }
}
/**
 * 在线校验跳转URL
 */
function onLineCheckHomeJumpUrl(){
	
	//优先判断是否为空
	$("#jump_url_loading-box").removeClass("column-loading-sub");
	$("#jump_url_loading-box").removeClass("column-loading-error");
	$("#jump_url_loading-box").siblings(".error-msg").remove();
	$("#jump_url_loading-box").attr("style","display:block");
	
	
	//获取跳转栏目url
	var jumpUrl = $("#jumpUrl_table").val();
	//获取栏目url
	var channelUrl = $("#homeUrl_table").val();
	
	if(isNull(jumpUrl)){
		$("#jump_url_loading-box").attr("style","display:none");
		$("#jumpUrl_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	
	if(!isNull(jumpUrl) && (jumpUrl==channelUrl)){
		$("#jump_url_loading-box").attr("style","display:none");
		$("#jump_url_loading-box").siblings(".info-tip2").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL和跳转URL不能相同</span>");
	}else{
		if(isNull(jumpUrl)){
			$("#jump_url_loading-box").attr("style","display:none");
			$('#jump_url_loading-box' ).siblings("..info-tip2").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL不能为空</span>");
			
		}else{
			//在进度条加载时候，控制连通性测试按钮是否能够点击
			$("#jump_home_url_conn_test").addClass("dis-btn-07c1f2").removeAttr("onclick");
			//进度条加载
			progress("#jump_url_loading-box");	
			//验证栏目url是否合法
			var isLe = onLineCheckUrl(jumpUrl,"jump_url_loading-box","jump_loading_id");
		}
	}
	
}

/**
 * 栏目url线校验事件
 */
function onLineCheckChannelUrl(){
	//获取栏目url
	var channelUrl = $("#channel_url_id").val();
	$("#model_channel_loading_box").attr("style","display:none");
	$("#model_channel_sr").attr("style","display:none");
	if(isNull(channelUrl)){
		$("#model_channel_loading_box").siblings(".error-msg").remove();
		$("#model_channel_loading_box").after("<span id='model_channel_sr' style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	channelUrlVal=0;
	$("#channel_url_conn_test").addClass("dis-btn-07c1f2").removeAttr("onclick");
	
	$("#model_channel_loading_box").removeClass("column-loading-sub");
	$("#model_channel_loading_box").removeClass("column-loading-error");
	$("#model_channel_loading_box").attr("style","display:block");

	
	
	
	//进度条加载
	progress("#model_channel_loading_box");
	//验证栏目url是否合法
	var isLe = onLineCheckUrl(channelUrl,"model_channel_loading_box","loading_channel_url_id");
	
}
/**
 * 提交修改基本信息数据时，添加校验
 */
function submitBaseModify(){
	//基本信息
	var siteCode=$("#siteCode").val();
	var siteName_table=$("#siteName_table").val();
	var siteManageUnit_table=$("#siteManageUnit_table").val();
	var officeAddress_table=$("#officeAddress_table").val();
	var homeUrl_table=$("#homeUrl_table").val();
	var jumpUrl_table=$("#jumpUrl_table").val();
	
	//基本信息校验
	 if(isNull(siteName_table)){
		 alert("网站名称为空，请输入栏目名称");
		 return;
	 }
	 if(isNull(siteManageUnit_table)){
		 alert("主办单位为空，请输入主办单位");
		 return;
	 }
	 if(isNull(officeAddress_table)){
		 alert("办公地址为空，请输入办公地址");
		 return;
	 }
	 if(isNull(homeUrl_table)){
		 alert("首页URL为空，请输入首页URL");
		 return;
	 }
	 //如果跳转ur不为空，则跳转url必须校验且校验成功，首页ur可以校验失败;
	 //如果跳转url为空，首页url必须校验且成功
	 if(jumpUrl_table!=""){//跳转url不为空
		var jumpUrlState=$("#jump_url_state_id").val();
		if(jumpUrlState=="0"){
			alert("跳转URL未校验或者校验失败");
			return;
		}
		if(homeUrl_table==jumpUrl_table){
			 alert("首页URL和跳转URL不能相同");
			 return;
		 }
	 }else{//跳转url为空
		var homeUrlState=$("#home_url_state_id").val();
		if(homeUrlState=="0"){
			alert("首页URL未校验或者校验失败");
			return;
		}
	 }
	 
	//防止保存连续点击多次disabled="disabled"
	$("#submitBaseModify_id").attr("disabled",true); 
	 
	 var jsonObj = {};
	 	jsonObj["siteCode"] = siteCode;//网站标识码
		jsonObj["siteName"] = siteName_table;//网站名称
		jsonObj["director"] = siteManageUnit_table;//主办单位
		jsonObj["address"] = officeAddress_table;//办公地址
		jsonObj["url"] = homeUrl_table;//首页url
		jsonObj["jumpUrl"] = jumpUrl_table;//跳转url
		jsonObj["top"] = null;//区分页面

	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/manageInfo_modifyBaseInfo.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json",
        success:function(data){
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
				alert(data.succees);
        	}
        	//代码逻辑处理往完成之后，将其设置为保存可点击
           $("#submitBaseModify_id").attr("disabled",false);
        	
        },error:function(data){
		   alert(data.errorMsg);
		 //代码逻辑处理往完成之后，将其设置为保存可点击
		   $("#submitBaseModify_id").attr("disabled",false);
	   }
     });
	
}

//**************************栏目信息************************************
function getChannelPointInfo(){
 	 $.ajax( {  
         type : "POST",  
         url : webPath+"/manageInfo_getChannelPointInfo.action",  
         data : {
        	 siteCode:siteCode 
         },  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 if(data.success == 'true'){
        		 $("#table_data_channel_columnTab").html("");
        		 var html = '';
        		 var list = data.body;
        		 var num = 0;
        		 if(list.length > 0 && list != null){
        			 $.each(list, function(index, obj) {
        				 num++;
        				 var url = obj.channelUrl;
        				 if(url != ""){
        					 if(!url.match("http")){
     	        				 url="http://"+url;
     	        			 }
        				 }
        				 
        				 html+='<tr>';
        				 html+='<td class="td-left" style="width:5%;text-align: left;"><input type="checkbox" value="'+obj.id+'" name="delId"/></td>';
        				 html+='<td class="td-left" style="width:10%;text-align: left;">'+num+'</td>';
        				 html+='<td class="td-left" style="width:15%;text-align: left;"><span class="wz-name">'+obj.channelName+'</span></td>';
        				 if(url != null && url.length > 25){
        					 html+='<td class="td-left" style="width:15%;text-align: left;" title="'+url+'"><a href="'+url+'" target="_blank" class="sort-num cor-01a5ec url">'+url.substring(0, 25)+'</a></td>';
        				 }else{
        					 html+='<td class="td-left" style="width:15%;text-align: left;" title="'+url+'"><a href="'+url+'" target="_blank" class="sort-num cor-01a5ec url">'+url+'</a></td>';
        				 }
        				 
        				 if(obj.jumpUrl == "无"){
        					 html+='<td class="td-center" style="width:10%;text-align: left;" >'+obj.jumpUrl+'</td>'; 
        				 }else{
        					 if(obj.jumpUrl.length > 25){
        						 html+='<td class="td-center" style="width:10%;text-align: left;" title="'+obj.jumpUrl+'"><a href="'+obj.jumpUrl+'" target="_blank" class="sort-num cor-01a5ec url">'+obj.jumpUrl.substring(0, 25)+'</a></td>';
        					 }else{
        						 html+='<td class="td-center" style="width:10%;text-align: left;" title="'+obj.jumpUrl+'"><a href="'+obj.jumpUrl+'" target="_blank" class="sort-num cor-01a5ec url">'+obj.jumpUrl+'</a></td>';
        					 }
        				 }
        				 html+='<td class="td-center" style="width:10%;text-align: left;" >'+obj.updateTime+'</td>';
        				 html+='<td class="td-center"  style="width:10%;text-align: left;" >'+obj.dicName+'</td>';
        				 html+='<td class="td-center"  style="width:10%;text-align: left;">'+obj.dichName+'</td>';
        				 
    					 if(obj.status == 1){  // 状态（监测中：1，未监测：0,标记删除：-1），取消监测将状态置为0，删除记录为标记删除状态值为-1
    						 html+='<td class="td-center" style="width:5%;text-align: left;"><i class="radio-phone active orgToInfo" onclick="initLi(this);" _vid="'+obj.id+'"></i></td>';
            				 html+='<td class="td-center" style="width:10%;text-align: left;" title="正常">正常</td>';
    					 }else{
    						 html+='<td class="td-center" style="width:5%;text-align: left;"><i class="radio-phone orgToInfo" onclick="initLi(this);" _vid="'+obj.id+'"></i></td>';
            				 html+='<td class="td-center" style="width:10%;text-align: left;color:red"  title="未设置监测该栏目，左侧选中即可">未监测</td>';
    					 }
        			
        				 html+='</tr>';                 
        			 });
        			 $("#table_data_channel_columnTab").append(html);
        		 } 
        	 }
         },
         error:function(data){
 			alert(data.errorMsg);
 		 }
       });
 	$("input[type='checkbox']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
}
/**
 * 滑动条操作栏目状态
* @param li
*/
function initLi(li){
	var vid = $(li).attr("_vid");
	var isActive = false;
	if($(li).hasClass("active")){
		$(li).removeClass("active");
	}else{
		$(li).addClass("active");
		isActive = true;
	}
	$.ajax({
		type : "POST",
		url : webPath + "/manageInfo_operateChannelInfo.action?id="+vid+"&isActive="+isActive,
		dataType : "json",
		async : false,
		success : function(data) {
			if(data.success){
				alert(data.success);
				getChannelPointInfo();
			}else{
				$(li).removeClass("active");
				alert(data.errorMsg);
			}
		},
		error:function(data){
			$(li).removeClass("active");
			alert(data.errorMsg);
		}
	});
}
//栏目导出Excel
function channelExportExcel(){
   window.location.href=webPath+"/manageInfo_getChannelPointInfoExcel.action?siteCode="+siteCode;
}
/**
 * 批量删除栏目信息
 */
function delChannelList(){
	var ids = new Array();	
    $("input[name=delId]:checked").each(function(){
    	ids.push($(this).attr('value'));
	});

    if(ids.length==0){
        alert("请选择栏目！");
        return
    }

    if(!confirm("确定要删除记录吗?")){
        return false
    }
	
	if (_.isEmpty(ids)) {
        return;
    }
	$.ajax({
		type : "POST",
		url: webPath+"/manageInfo_delChannelPoint.action",
        data: {
        	ids:ids.toString()
        },
        dataType: "json",
		async : false,
		success : function(data) {
			if(data.success){
				alert(data.success);
				getChannelPointInfo();
			}else{
				alert(data.errorMsg);
			}
		},
		error:function(data){
			alert(data.errorMsg);
		}
	});
}
/**
 * 添加栏目--页面初始化
 */
function addColumnModal(){
	//显示弹出框
	$("#addColumnModal").modal("show");

	//清空数据
	 //栏目名称
	 $("#channel_name_id").val("");
	 //栏目url
	 $("#channel_url_id").val("");
	 //跳转url
	 $("#jump_url_id").val("");
	 
	 //清除错误提示信息
	 $("#channel_url_id").siblings(".error-msg").remove();
	 $("#jump_url_id").siblings(".error-msg").remove();
	 
	 
	 $("#model_channel_loading_box").attr("style","display:none");
	 $("#channel_jump_loading_box").attr("style","display:none");
	
	 // 调用ajax请求获取栏目类型loadDicChannelList下拉框初始化数据
	 var dicChannelList="";	 
	 var dicChannelSonList="";
	 var siteCode="";
	 var siteName="";
	 
	 
	 var siteCode=$("#siteCode").val();
	 
	 $.ajax({
			type : "POST",
			url : webPath + "/manageInfo_initAddChannel.action?siteCode="+siteCode,
			dataType : "json",
			async : false,
			success : function(data) {
				//下拉框先清空
				$("#parent_channel_id").html("");
				$("#sun_channel_id").html("");
				
				//获取父类下拉框中所有的选项
				$.each(data.listDicChannel,
						function(index, channelData) {	
					dicChannelList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"	
				});
				
				$("#parent_channel_id").append(dicChannelList);
				
				//获取子类栏目中的现象
				$.each(data.listDicChannelSon,
						function(index, channelData) {		
					dicChannelSonList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"
					
				});
				$("#sun_channel_id").append(dicChannelSonList);
				getUpdateTime();
				//网站标识码
				siteCode=data.siteCode;
				
				$("#site_code_id").html(siteCode);
				//网站名称
				siteName=data.siteName;
				$("#site_name_id").html(siteName);
				
				//栏目名称 url清空
				$("#channel_name_id").html("");
				$("#channel_url_id").html("");
				$("#jump_url_id").html("");
			},error:function(data){
			}
		});
}
//添加栏目信息页面---点击确定按钮将栏目信息保存到数据库
function addChannel(){ 
	 
	 
	 //网站标识码
	 var siteCode = $("#site_code_id").html();
	 //栏目名称
	 var channelName=$("#channel_name_id").val();
	 //栏目url
	 var channelUrl = $("#channel_url_id").val();
	 //跳转url
	 var jumpUrl = $("#jump_url_id").val();
	 
	 var dicChannelId = $("#parent_channel_id").find("option:selected").val();
	 var dicChannelSonId = "";
	 if($("#sun_channel_id").find("option:selected").val()){
		 dicChannelSonId =  $("#sun_channel_id").find("option:selected").val();
	 }
	 var dicChannelText = $("#parent_channel_id").find("option:selected").text();
	 var dicChannelSonText = $("#sun_channel_id").find("option:selected").text();
	 if(isNull(channelName)){
		 alert("栏目名称为空，请输入栏目名称");
		 return;
	 }
	 
	 if(isNull(channelUrl)){
		 alert("栏目URL为空，请输入栏目URL");
		 return;
	 }
	 
	 //如果跳转url不为空，跳转url必须校验且成功，栏目url允许校验失败
	 //如果跳转url为空，首页url必须校验且成功
	 if(!isNull(jumpUrl)){
		 var jumpChannelUrlState=$("#channel_jump_url_state_id").val();
		 if(jumpChannelUrlState=="0"){
			 alert("跳转URL未校验或者校验失败，不能保存！");
			 return;
		 }

		if(jumpUrl==channelUrl){
			 alert("栏目URL与跳转URL不能相同！");
			 return;
		}
	 }else{
		var channelUrlState= $("#channel_url_state_id").val();
		if(channelUrlState=="0"){
			 alert("栏目URL未校验或者校验失败，不能保存！");
			 return;
		}
	 }
	 
	//防止添加栏目按钮点击多次--去除a标签的onclick属性
	$("#addChannel_btn_id").removeAttr('onclick');
	
	 var jsonObj = {};
		jsonObj["channelName"] = channelName;
		jsonObj["channelUrl"] = channelUrl;
		jsonObj["jumpUrl"] = jumpUrl;
		jsonObj["modifySiteCode"] = siteCode;
		jsonObj["dicChannelId"] = dicChannelId;
		jsonObj["dicChannelSonId"] = dicChannelSonId;	
		
		$.ajax({
	        async: false,
	        type: "POST",
	        url: webPath+"/manageInfo_addChannel.action",
	        dataType: "json",
	        data: JSON.stringify(jsonObj),
	        contentType: "application/json"
	    }).done(function (response) {
	    	if(response.success){
	    		alert(response.success);
	    		//隐藏弹出框
	    		$("#addColumnModal").modal("hide");
	    		getChannelPointInfo();
	    	}else if(response.errorMsg){
	    		alert(response.errorMsg);
	    		return;
	    	}
	    }).fail(function (data) {
	    	alert(data.errorMsg);
	    	return;
	    }).always(function () {
			//业务逻辑执行成功之后，将
			$("#addChannel_btn_id").attr('onclick',"addChannel()");
	    });
}
//下拉框添加联动效果
$("#parent_channel_id").on("change", function () {
	var parentId = $(this).find("option:selected").val();
	 var dicChannelList="";	 
	 var listDicChannelList="";
	 $.ajax({
			type : "POST",
			url : webPath + "/manageInfo_initAddChannel.action?parentId="+parentId,
			dataType : "json",
			async : false,
			success : function(data) {
				//先清空子类栏目
				$("#sun_channel_id").html("");
				
				if(data.listDicChannelSon && data.listDicChannelSon.length > 0){
					//获取子类栏目中的现象
					$.each(data.listDicChannelSon,function(index, channelData) {		
						listDicChannelList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"
						
					});
					$("#sun_channel_id").append(listDicChannelList);
					getUpdateTime();
				}	
			}
				
		});
 });

//下拉框添加联动效果
$("#sun_channel_id").on("change", function () {
	getUpdateTime();
 });


function getUpdateTime(){
	var id = $("#sun_channel_id").val();
	$.ajax( {  
        type : "POST",  
        url : webPath+"/manageInfo_getUpdateTime.action?id="+id,  
        dataType:"json",
        async : false,  
        success : function(data) {
        	if(data.success){
        		$("#updateTime").html(data.updateTime);
        	}
        }
	});
}
