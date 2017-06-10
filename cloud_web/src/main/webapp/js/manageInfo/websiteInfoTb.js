var isHomeUrlLegal=true;
var isHomeJumpUrlLegal=true;
var isCheckHomeUrl=false;
var isCheckHomeJumpUrl=false;
var homeUrlOld;
var homeJumpUrlOld;
var isJumpUrlChange;
var urlVal;

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



//=========js校验start
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

/**
 * 站点信息：首页URL在线校验是否通过校验
 * @returns {Boolean}
 */
function checkHomeUrl_OnLineCheck(){
	//var homeUrl_table = $('#homeUrl_table' ).val();
	if(!isHomeUrlLegal&&!isCheckHomeUrl){
		$('#checkHomeUrlWaitingId').siblings(".error-msg").remove();
		$('#checkHomeUrlWaitingId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请校验首页URL</span>");
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
		
		if(!isHomeJumpUrlLegal&&!isCheckHomeJumpUrl){
			$('#checkJumpUrlWaitingId').siblings(".error-msg").remove();
			$('#checkJumpUrlWaitingId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请校验跳转URL</span>");
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


//跳转url校验
function clearHomeJumpUrlCheck(){
	//跳转url
	var jumpUrl_table = $("#jumpUrl_table").val();
	var homeUrl_table = $("#homeUrl_table").val();

	
	$("#jumpUrl_table").siblings(".error-msg").remove();
	if(isNull(jumpUrl_table)){
		$("#jumpUrl_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	if(!isNull(homeUrl_table) && (jumpUrl_table==homeUrl_table)){
		$("#jumpUrl_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL和跳转URL不能相同</span>");
		return false;
	}
	return true;
}

//首页url不能

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

//联系人姓名非空校验
function checkRelationName_table(){
	var officeAddress_table = $("#relationName_table" ).val();
	$("#relationName_table" ).siblings(".error-msg").remove();
	if(isNull(officeAddress_table)){
		$("#relationName_table" ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入联系人</span>");
		return false;
	}
	
	return true;
}
/**
 * 联系人手机号校验
 * @returns {Boolean}
 */
function checkRelationCellphone_table(){

	
	var relationCellphone_table = $("#relationCellphone_table").val();
	$("#relationCellphone_table").siblings(".error-msg").remove();

	if(isNull(relationCellphone_table)){
		$("#relationCellphone_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入手机号</span>");
		return false;
	}

	if(!checkMobile(relationCellphone_table)){
		$("#relationCellphone_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;手机号格式不正确</span>");
		return false;
	}
	
	return true;
	
}

/**
 * 联系人：办公电话校验
 * @returns {Boolean}
 */
function checkRelationPhone_table(){
	var relationPhone_table = $("#relationPhone_table").val();
	$("#relationPhone_table").siblings(".error-msg").remove();
	
	
	if(isNull(relationPhone_table) ){
		$("#relationPhone_table").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入办公电话</span>");
		return false;
	}

	if(!checkPhone(relationPhone_table)){
		$("#relationPhone_table").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;办公电话格式不正确</span>");
		return false;
	}
	
	return true;
	
}



/**
 * 联系人：邮箱校验
 * @returns {Boolean}
 */
function checkRelationEmail_table(){
	var relationEmail_table = $("#relationEmail_table").val();
	$("#relationEmail_table").siblings(".error-msg").remove();

	if(isNull(relationEmail_table)){
		$("#relationEmail_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入电子邮箱</span>");
		return false;
	}
	
	if(!checkEmail(relationEmail_table)){
		$("#relationEmail_table").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;电子邮箱格式不正确</span>");
		return false;
	}
	return true;
}

/**
 * 负责人手机格式校验
 */
function checkRelationCellphone_table2(){
	var relationCellphone_table2=$("#relationCellphone_table2").val();
	$("#relationCellphone_table2").siblings(".error-msg").remove();
	
	if(!checkMobile(relationCellphone_table2) && !isNull(relationCellphone_table2)){
		$("#relationCellphone_table2").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;手机号格式不正确</span>");
		return false;
	}
	return true;
}

/**
 * 负责人办公电话格式校验
 */
function checkRelationPhone_table2(){
	var relationPhone_table2 = $("#relationPhone_table2").val();
	$("#relationPhone_table2").siblings(".error-msg").remove();

	if(!checkPhone(relationPhone_table2) && !isNull(relationPhone_table2)){
		$("#relationPhone_table2").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;办公电话格式不正确</span>");
		return false;
	}
	
	return true;
	
}
/**
 * 负责人电子邮箱格式校验
 */
function checkRelationEmail_table2(){
	
	var relationEmail_table2 = $("#relationEmail_table2").val();
	$("#relationEmail_table2").siblings(".error-msg").remove();

	if(!checkEmail(relationEmail_table2) && !isNull(relationEmail_table2)){
		$("#relationEmail_table2").parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;电子邮箱格式不正确</span>");
		return false;
	}
	return true;
}









//=========js校验start
/**
 * 监测站点信息参数
 * @returns {Boolean}
 */
function checkWebsiteParams() {
	
	if(!checkSiteName_table()){
		return false;
	}
	
	if(!checkSiteManageUnit_table()){
		return false;
	}
	if(!checkOfficeAddress_table()){
		return false;
	}
	
	if(!checkHomeUrl_table()){
		return false;
	}
	if(!checkRelationName_table()){
		return false;
	}
	if(!checkRelationCellphone_table()){
		return false;
	}
	if(!checkRelationPhone_table()){
		return false;
	}
	if(!checkRelationEmail_table()){
		return false;
	}

	return true;
}


function submitBaseOrgModify(){
	

	//基本信息
	var siteCode=$("#siteCode").val();
	var siteName_table=$("#siteName_table").val();
	
	 //负责人信息  可以为空  
	 var principalName=$("#responsibleName").val();
	 var telephone=$("#responsiblePhone").val();
	 var email=$("#responsibleEmail").val();
	
	//联系人one信息
	var relationName_table=$("#relationName_table").val();
	var relationCellphone_table=$("#relationCellphone_table").val();
	var relationEmail_table=$("#relationEmail_table").val();
	
	//联系人two信息
	var relationNametwo_table=$("#relationNametwo_table").val();
	var relationCellphonetwo_table=$("#relationCellphonetwo_table").val();
	var relationEmailtwo_table=$("#relationEmailtwo_table").val();
	
	//联系人three信息
	var relationNamethree_table=$("#relationNamethree_table").val();
	var relationCellphonethree_table=$("#relationCellphonethree_table").val();
	var relationEmailthree_table=$("#relationEmailthree_table").val();
	
	//基本信息校验
	 if(isNull(siteName_table)){
		 alert("网站名称为空，请输入栏目名称");
		 return;
	 }
	 //负责人信息校验
	 if(isNull(principalName)){
		 principalName="";
	 }
	 if(isNull(telephone)){
		 telephone="";
	 }else{
		 if(!checkMobile(telephone)){
			 alert("请输入正确格式的负责人手机号码");
			 return;
		 }
	 }
	 if(isNull(email)){
		 email="";
	 }else{
		 if(!isEmail(email)){
			 alert("请输入正确格式的负责人电子邮箱");
			 return;
		 }
	 }

	 if(siteCode.length > 6){
		 
	 }else{
		 //联系人one信息校验
		 if(isNull(relationName_table)){
			 alert("联系人姓名为空，请输入联系人姓名");
			 return;
		 }
		 
		 if(isNull(relationCellphone_table)){
			 alert("联系人手机为空，请输入联系人手机");
			 return;
		 }else{
			 if(!checkMobile(relationCellphone_table)){
				 alert("请输入正确格式的联系人手机号码");
				 return;
			 }
		 }
		 if(isNull(relationEmail_table)){
			 alert("电子邮箱为空，请输入电子邮箱");
			 return;
		 }else{
			 if(!isEmail(relationEmail_table)){
				 alert("请输入正确格式的联系人电子邮箱");
				 return;
			 }
		 }
	 }
	 
	 //联系人two信息校验
	 
	 if(!isNull(relationCellphonetwo_table)){
		 if(!checkMobile(relationCellphonetwo_table)){
			 alert("请输入正确格式的联系人2手机号码");
			 return;
		 }
	 }
	 
	 if(!isNull(relationEmailtwo_table)){
		 if(!isEmail(relationEmailtwo_table)){
			 alert("请输入正确格式的联系人2电子邮箱");
			 return;
		 }
	 }
		 
	 //联系人three信息校验
	 if(!isNull(relationCellphonethree_table)){
		 if(!checkMobile(relationCellphonethree_table)){
			 alert("请输入正确格式的联系人3手机号码");
			 return;
		 }
	 }
		 
	 if(!isNull(relationEmailthree_table)){
		 if(!isEmail(relationEmailthree_table)){
			 alert("请输入正确格式的联系人3电子邮箱");
			 return;
		 }
	 }
	
	//防止保存连续点击多次disabled="disabled"
	$("#submitBaseModify_id").attr("disabled",true); 
	 
	 var jsonObj = {};
	 	jsonObj["siteCode"] = siteCode;//网站标识码
		jsonObj["siteName"] = siteName_table;//网站名称
		
		if(siteCode.length > 6){
			
		}else{
			//负责人信息 
			jsonObj["principalName"] = principalName;//负责人姓名
			jsonObj["telephone"] = telephone;//负责人手机
			jsonObj["email"] = email;//负责人电子邮箱
		}
		

		//联系人one信息
		jsonObj["linkmanName"] = relationName_table;//联系人one姓名
		jsonObj["telephone2"] = relationCellphone_table;//联系人one手机
		jsonObj["email2"] = relationEmail_table;//联系人one电子邮箱
		
		//联系人two信息
		jsonObj["linkmanNametwo"] = relationNametwo_table;//联系人two姓名
		jsonObj["telephone3"] = relationCellphonetwo_table;//联系人two手机
		jsonObj["email3"] = relationEmailtwo_table;//联系人two电子邮箱
		
		//联系人three信息
		jsonObj["linkmanNamethree"] = relationNamethree_table;//联系人three姓名
		jsonObj["telephone4"] = relationCellphonethree_table;//联系人three手机
		jsonObj["email4"] = relationEmailthree_table;//联系人three电子邮箱
		
		var isOrg =  $("#TB").val();
		if(isOrg == 'TB'){
			$.ajax({
			 	 type:"post",
			  	 async:false,
			     url: webPath+"/manageInfo_modifyDatabaseInfo.action",
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
		 
		}else{
			$.ajax({
			 	 type:"post",
			  	 async:false,
			     url: webPath+"/manageInfo_modifyOrgInfo.action",
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

}
function toBaoSong(){
	window.open($("#toBaoSongUrl").val()+"?siteCode="+$("#siteCode").val()+"&vCode="+$("#vcode").val(),"_blank");
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
	
	 var top=$("#top").val();
	 //负责人信息  可以为空  
	 if(top == '8'){
		 var principalName=$("#responsibleName").val();
		 var telephone=$("#responsiblePhone").val();
		 var mobile=$("#responsibleMobile").val();
		 var email=$("#responsibleEmail").val();
		
		//联系人信息
		var relationName_table=$("#relationName_table").val();
		var relationCellphone_table=$("#relationCellphone_table").val();
		var relationPhone_table=$("#relationPhone_table").val();
		var relationEmail_table=$("#relationEmail_table").val();
		
	 }


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
	 
	 if(top == '8'){
		 //负责人信息校验
		 if(isNull(principalName)){
			 principalName="";
		 }
		 if(isNull(telephone)){
			 telephone="";
		 }else{
			 if(!checkMobile(telephone)){
				 alert("请输入正确格式的负责人手机号码");
				 return;
			 }
		 }
		 if(isNull(mobile)){
			 mobile="";
		 }else{
			 if(!checkPhone(mobile)){
				 alert("请输入正确格式的负责人办公电话");
				 return;
			 }
		 }
		
		 if(isNull(email)){
			 email="";
		 }else{
			 if(!isEmail(email)){
				 alert("请输入正确格式的负责人电子邮箱");
				 return;
			 }
		 }
		 //联系人信息校验
			
		 if(isNull(relationName_table)){
			 alert("联系人姓名为空，请输入联系人姓名");
			 return;
		 }
		 
		 if(isNull(relationCellphone_table)){
			 alert("联系人手机为空，请输入联系人手机");
			 return;
		 }else{
			 if(!checkMobile(relationCellphone_table)){
				 alert("请输入正确格式的联系人手机号码");
				 return;
			 }
		 }
		 
		 if(isNull(relationPhone_table)){
			 alert("办公电话为空，请输入办公电话");
			 return;
		 }else{
			 if(!checkPhone(relationPhone_table)){
				 alert("请输入正确格式的联系人办公电话");
				 return;
			 }
		 }
		 
		 if(isNull(relationEmail_table)){
			 alert("电子邮箱为空，请输入电子邮箱");
			 return;
		 }else{
			 if(!isEmail(relationEmail_table)){
				 alert("请输入正确格式的联系人电子邮箱");
				 return;
			 }
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
		jsonObj["top"] = top;//区分页面
		
		if(top == 8){
			//负责人信息 
			jsonObj["principalName"] = principalName;//负责人姓名
			jsonObj["telephone"] = telephone;//负责人手机
			jsonObj["mobile"] = mobile;//负责人办公电话
			jsonObj["email"] = email;//负责人电子邮箱

			//联系人信息
			jsonObj["linkmanName"] = relationName_table;//联系人姓名
			jsonObj["telephone2"] = relationCellphone_table;//联系人手机
			jsonObj["mobile2"] = relationPhone_table;//联系人办公电话
			jsonObj["email2"] = relationEmail_table;//联系人电子邮箱
		}
		

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







