var isHomeUrlLegal=false;
var isCheckHomeUrl=false;
var isCheckHomeJumpUrl=false;
var homeUrlOld;
var homeJumpUrlOld;
var isJumpUrlChange;

/**
 * 获取基本信息
 */
function getBaseInfo(){
	
	tableDBClick(modifyWebsiteInfoId);
	
	/*$("#checkbox_tr").on('ifChecked', function(event){
		 $("#jumpUrl_tr").removeClass("disabled-tr");
		 $('#jumpUrl_table').removeAttr("disabled"); 
		 $('#checkHomeJumpUrlBId').show(); 
	});
	$("#checkbox_tr").on('ifUnchecked', function(event){
		$("#jumpUrl_tr").addClass("disabled-tr");
		 $('#jumpUrl_table').attr("disabled","disabled"); 
		 $('#checkHomeJumpUrlBId').hide();
	});
	$("#basicInfo").siblings().removeClass("active");
	isCheckHomeJumpUrl= false;
	$.ajax({
	     async: true,
	     type: "GET",
	     url: webPath+ "/manageInfo_getDBClickInfo.action?websiteInfoId="+ modifyWebsiteInfoId,
	     dataType: "json",
	     contentType: "application/text"
	 }).done(function (response) {
	  if(response){
	     $("#siteCode_table").html(response.siteCode);
	     $("#siteName_table").val(response.siteName);
	     $("#homeUrl_table").val(response.homeUrl);
	     $("#jumpUrl_table").val(response.jumpUrl);
	     $("#siteManageUnit_table").val(response.siteManageUnit);
	     $("#officeAddress_table").val(response.officeAddress);
	     $("#relationName_table").val(response.relationName);
	     $("#relationPhone_table").val(response.relationPhone);
	     $("#relationCellphone_table").val(response.relationCellphone);
	     $("#relationEmail_table").val(response.relationEmail);
	     
	     //获取未修改前的首页url和首页跳转url add by Nora 20151206
	     homeUrlOld=response.homeUrl;
	     homeJumpUrlOld=response.jumpUrl;
	 }
	}).fail(function (data) {
	  alert("错误！");
	}).always(function () {

	});*/
}



/**
 * 在线校验首页URL
 *//*
function onLineCheckHomeUrl(ob){
	isCheckHomeUrl=true;
	$("#checkHomeUrlBId").hide();
	$("#checkHomeUrlWaitingId").show();
	
	$('#checkHomeUrlBId' ).siblings( ".error-msg").remove();
	$('#checkHomeUrlBId').siblings('i').remove();
	$('#checkHomeUrlBId').css('display','none');
	$('#checkHomeUrlWaitingId').css('display','');
	
	
	//首先移除class
	$(ob).siblings(".column-loading-box").removeClass("column-loading-sub");
	$(ob).siblings(".column-loading-box").removeClass("column-loading-error");
	$(ob).siblings(".error-msg").remove();
	
	var homeUrl = $("#homeUrl_table").val();
	var isLe = onLineCheckUrl(homeUrl);
	

	isHomeUrlLegal=false;
	if(isLe){
		isHomeUrlLegal=true;
		$(ob).siblings(".column-loading-box").addClass("column-loading-sub");
    	
		$("#home_loading_id").html("<span>连通测试成功！</span><span class='loading-num'>100%</span>");
    	
		$(ob).siblings(".column-loading-box").find(".column-loading-con").css("width",100+"%");
		$(ob).siblings(".column-loading-box").find(".loading-num").html(100+"%");
	}else{
		$(ob).siblings(".column-loading-box").addClass("column-loading-error");
		
		$("#home_loading_id").html("<span> 开普云监测服务器连不通您填写的 URL !</span><span class='loading-num'>100%</span>");
    	
		$(ob).siblings(".column-loading-box").find(".column-loading-con").css("width",100+"%");
		$(ob).siblings(".column-loading-box").find(".loading-num").html(100+"%");

	}
	
}*/





//=========js校验start
function checkSiteName_table(){
	var siteName_table = $('#siteName_table' ).val();
	$('#siteName_table' ).siblings(".error-msg").remove();
	if(isNull(siteName_table)){
		$('#siteName_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入网站名称</span>");
		return false;
	}
	
	return true;
}
function jumpUrlCheck_table(){
	var jumpUrl_table = $('#jumpUrl_table' ).val();
	if($("#checkbox_tr").is(':checked')){
		if(isNull(siteName_table)){
			$('#jumpUrl_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入跳转URL</span>");
			return false;
		}
		
		if(isJumpUrlChange==true&&isCheckHomeJumpUrl== false){
			if($('#checkJumpUrlWaitingId').siblings(".error-msg").length==0){
				$('#checkJumpUrlWaitingId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请校验跳转URL</span>");
			}
			return false;
		}
		
		if(!isNull(jumpUrl_table)&&isCheckHomeJumpUrl== false){
			if($('#checkJumpUrlWaitingId').siblings(".error-msg").length==0){
				$('#checkJumpUrlWaitingId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请校验跳转URL</span>");
			}
          return false ;
     }
		
		

	}
	return true;
}


//首页url非空校验
function checkHomeUrl_table(){
	var homeUrl_table = $('#homeUrl_table' ).val();
	$('#homeUrl_table' ).siblings(".error-msg").remove();
	if(isNull(homeUrl_table)){
		$('#homeUrl_table' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入首页URL</span>");
		return false;
	}
	/*if(isHomeUrlLegal==false){
		onLineCheckHomeUrl(this);
	}*/
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

//办公地址校验
function checkOfficeAddress_table(){
	var officeAddress_table = $('#officeAddress_table' ).val();
	$('#officeAddress_table' ).siblings(".error-msg").remove();
	if(isNull(officeAddress_table)){
		$('#officeAddress_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入办公地址</span>");
		return false;
	}
	
	return true;
}

function checkRelationName_table(){
	var officeAddress_table = $('#relationName_table' ).val();
	$('#relationName_table' ).siblings(".error-msg").remove();
	if(isNull(officeAddress_table)){
		$('#relationName_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入联系人</span>");
		return false;
	}
	
	return true;
}


/**
 * 站点信息：办公电话校验
 * @returns {Boolean}
 */
function checkRelationPhone_table(){
	var relationPhone_table = $('#relationPhone_table' ).val();
	$('#relationPhone_table' ).siblings(".error-msg").remove();
	if(isNull(relationPhone_table)){
		$('#relationPhone_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入办公电话</span>");
		return false;
	}

	if(!checkPhone(relationPhone_table)){
		$('#relationPhone_table' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;办公电话格式不正确</span>");
		return false;
	}
	
	return true;
	
}

/**
 * 站点信息：手机号校验
 * @returns {Boolean}
 */
function checkRelationCellphone_table(){
	var relationCellphone_table = $('#relationCellphone_table' ).val();
	$('#relationCellphone_table' ).siblings(".error-msg").remove();
	if(isNull(relationCellphone_table)){
		$('#relationCellphone_table' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入手机号</span>");
		return false;
	}
	if(!checkMobile(relationCellphone_table)){
		$('#relationCellphone_table' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;手机号格式不正确</span>");
		return false;
	}
	
	return true;
	
}

/**
 * 站点信息：邮箱校验
 * @returns {Boolean}
 */
function checkRelationEmail_table(){
	var relationEmail_table = $('#relationEmail_table' ).val();
	$('#relationEmail_table' ).siblings(".error-msg").remove();
	if(isNull(relationEmail_table)){
		$('#relationEmail_table' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入电子邮箱</span>");
		return false;
	}
	
	if(!checkEmail(relationEmail_table)){
		$('#relationEmail_table' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;电子邮箱格式不正确</span>");
		return false;
	}
	
	return true;
	
}

/**
 * 站点信息：首页URL在线校验是否通过校验
 * @returns {Boolean}
 */
function checkHomeUrl_OnLineCheck(){
	var homeUrl_table = $('#homeUrl_table' ).val();
	if(homeUrlOld!=homeUrl_table&&isCheckHomeUrl==false){
		$('#checkHomeUrlWaitingId').siblings(".error-msg").remove();
		$('#checkHomeUrlWaitingId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请校验首页URL</span>");
		return false;
	}
	
	if(homeUrlOld!=homeUrl_table&&isHomeUrlLegal==false){
		return false;
	}
	
	var jumpUrl_table = $('#jumpUrl_table' ).val();
	if(!isNull(jumpUrl_table)&&homeJumpUrlOld!=jumpUrl_table&&isCheckHomeJumpUrl==false){
		if($('#checkJumpUrlWaitingId').siblings(".error-msg").length==0){
			$('#checkJumpUrlWaitingId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请校验跳转URL</span>");
		}
		return false;
	}
	
	if(!isNull(jumpUrl_table)&&homeJumpUrlOld!=jumpUrl_table&&isHomeUrlLegal==false){
		return false;
	}
	/*$('#homeUrl_table' ).siblings(".error-msg").remove();
	if(isNull(homeUrl_table)){
		$('#homeUrl_table' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入首页URL</span>");
		return false;
	}*/
	
	return true;
}




//=========js校验start
/**
 * 监测站点信息参数
 * @returns {Boolean}
 */
function checkWebsiteParams() {
	
	if(!jumpUrlCheck_table()){
		return false;
	}
	
	if(!checkSiteName_table()){
		return false;
	}
	
	if(!checkHomeUrl_table()){
		return false;
	}
	
	if(!checkSiteManageUnit_table()){
		return false;
	}
	
	if(!checkOfficeAddress_table()){
		return false;
	}
	
	if(!checkRelationName_table()){
		return false;
	}
	
	if(!checkRelationPhone_table()){
		return false;
	}
	
	if(!checkRelationCellphone_table()){
		return false;
	}
	
	if(!checkRelationEmail_table()){
		return false;
	}
	
	if(!checkHomeUrl_OnLineCheck()){
		return false;
	}
	
	
	return true;
}


/**
 * 提交修改基本信息数据
 */
function submitBaseModify(){
	
	if(!checkWebsiteParams()){
		return;
	}
	
	var jsonObj = {};
	jsonObj["modifyWebsiteInfoId"] = modifyWebsiteInfoId;
	jsonObj["siteName"] = $("#siteName_table").val() || "";
	jsonObj["homeUrl"] = $("#homeUrl_table").val() || "";
	jsonObj["jumpUrl"] = $("#jumpUrl_table").val() || "";
	jsonObj["siteManageUnit"] = $("#siteManageUnit_table").val() || "";
	jsonObj["officeAddress"] = $("#officeAddress_table").val() || "";
	jsonObj["relationName"] = $("#relationName_table").val() || "";
	jsonObj["relationPhone"] = $("#relationPhone_table").val() || "";
	jsonObj["relationCellphone"] = $("#relationCellphone_table").val() || "";
	jsonObj["relationEmail"] = $("#relationEmail_table").val() || "";
	
	 $.ajax({
	        async: true,
	        type: "POST",
	        url: webPath+"/manageInfo_modifyBaseInfo.action",
	        dataType: "json",
	        data: JSON.stringify(jsonObj),
	        contentType: "application/json",
	        beforeSend: function () {
	            $("#loading_mask_div").show();
	            $("#list_loading").show();
	        }
	    }).done(function (response) {
	    	if(response.succeed){
	    		alert(response.succeed);
	    		homeUrlOld=$("#homeUrl_table").val();
	    	    homeJumpUrlOld=$("#jumpUrl_table").val();
	    	 
	    	}else if(response.error){
	    		alert(response.error);
	    	}
	    }).fail(function (data) {
	    	alert("错误！");
	    }).always(function () {

	});
}

/**
 * 提交修改基本信息数据
 */
function submitBaseModify(){
	
	if(!checkWebsiteParams()){
		return;
	}
	
	var jsonObj = {};
	jsonObj["modifyWebsiteInfoId"] = modifyWebsiteInfoId;
	jsonObj["siteName"] = $("#siteName_table").val() || "";
	jsonObj["homeUrl"] = $("#homeUrl_table").val() || "";
	jsonObj["jumpUrl"] = $("#jumpUrl_table").val() || "";
	jsonObj["siteManageUnit"] = $("#siteManageUnit_table").val() || "";
	jsonObj["officeAddress"] = $("#officeAddress_table").val() || "";
	jsonObj["relationName"] = $("#relationName_table").val() || "";
	jsonObj["relationPhone"] = $("#relationPhone_table").val() || "";
	jsonObj["relationCellphone"] = $("#relationCellphone_table").val() || "";
	jsonObj["relationEmail"] = $("#relationEmail_table").val() || "";
	jsonObj["officeAddress"] = $("#officeAddress_table").val() || "";
	jsonObj["emailReceive"] = $("#emailReceive").val() || "";
	jsonObj["mobileReceive"] = $("#mobileReceive").val() || "";
	
	 $.ajax({
	        async: true,
	        type: "POST",
	        url: webPath+"/manageInfo_modifyBaseInfo.action",
	        dataType: "json",
	        data: JSON.stringify(jsonObj),
	        contentType: "application/json",
	        beforeSend: function () {
	            $("#loading_mask_div").show();
	            $("#list_loading").show();
	        }
	    }).done(function (response) {
	    	if(response.succeed){
	    		alert(response.succeed);
	    	}else if(response.error){
	    		alert(response.error);
	    	}
	    }).fail(function (data) {
	    	alert("错误！");
	    }).always(function () {

	});
}
