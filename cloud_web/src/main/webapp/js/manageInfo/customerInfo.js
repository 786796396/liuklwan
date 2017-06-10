$(function () {
	// 客户信息表：接收预警信息
	setIsMobileReceive();
	setIsEmailReceive();
	

	$(".two-cbox").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});

	$("#mobileReceiveCheckBox").on('ifChecked', function(event) {
		$("#mobileReceiveId").val(1);
	});

	$("#mobileReceiveCheckBox").on('ifUnchecked', function(event) {
		$("#mobileReceiveId").val(0);
	});

	$("#emailReceiveCheckBox").on('ifChecked', function(event) {
		$("#emailReceiveId").val(1);
	});

	$("#emailReceiveCheckBox").on('ifUnchecked', function(event) {
		$("#emailReceiveId").val(0);
	});
	$("#baseCheckBoxPhone").on('ifChecked', function(event) {
		$("#mobileReceive").val(1);
	});

	$("#baseCheckBoxPhone").on('ifUnchecked', function(event) {
		$("#mobileReceive").val(0);
	}); 
	
	
	$("#baseCheckBoxEmail").on('ifChecked', function(event) {
		$("#emailReceive").val(1);
	});

	$("#baseCheckBoxEmail").on('ifUnchecked', function(event) {
		$("#emailReceive").val(0);
	}); 
  
})


// 是否邮箱接收预警信息
function setIsMobileReceive(){
	
	var isMobileReceive = $("#mobileReceiveId").val();
	
	if(isMobileReceive=='1'){
		$("#mobileReceiveCheckBox").attr("checked",'true');// 全选
	}
	
	
}

//是否邮箱接收预警信息
function setIsEmailReceive(){
	
	var isEmailReceive = $("#emailReceiveId").val();
	
	if(isEmailReceive=='1'){
		$("#emailReceiveCheckBox").attr("checked",'true');// 全选
	}
}

/**
 * 提交修改基本信息
 */
function submitCustomerInfoModify(){
	
	if(!checkCustomerParams()){
		return; 
	}
	
	$("#base_info_form").ajaxSubmit({
	    dataType: "json",
	    success: function (responseText) {
	    	if(responseText.succeed){
	    		alert(responseText.succeed);
	    	}else if(responseText.error){
	    		alert(responseText.error);
	    	}
	    },
	    error: function (response) {
	    	alert("更新失败");
	    }
	});
}

//==============================js校验start===================
/**
 * js校验：客户参数
 * @returns {Boolean}
 */
function checkCustomerParams() {
	if(!checkAddress()){
		return false;
	}
	
	if(!checkRelationName()){
		return false;
	}
	
	if(!checkMobilePhone()){
		return false;
	}
	
	if(!checkTelephone()){
		return false;
	}
	
	if(!checkCustomerEmail()){
		return false;
	}
	
	return true;
}

/**
 * 客户信息：办公地址校验
 * @returns {Boolean}
 */
function checkAddress(){
	var address = $('#addressId' ).val();
	$('#addressId' ).siblings(".error-msg").remove();
	if(isNull(address)){
		$('#addressId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入办公地址</span>");
		return false;
	}
	
	return true;
}

/**
 * 客户信息：姓名校验
 * @returns {Boolean}
 */
function checkRelationName(){
	var relationName = $('#relationNameId' ).val();
	$('#relationNameId' ).siblings(".error-msg").remove();
	if(isNull(relationName)){		
		$('#relationNameId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入姓名</span>");
		return false;
	}
	
	return true;
}

/**
 * 客户信息：手机号校验
 * @returns {Boolean}
 */
function checkMobilePhone(){
	var mobilePhone = $('#mobilePhoneId' ).val();
	$('#mobilePhoneId' ).siblings(".error-msg").remove();
	if(isNull(mobilePhone)){
		$('#mobilePhoneId' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入手机号</span>");
		return false;
	}
	if(!checkMobile(mobilePhone)){
		$('#mobilePhoneId' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;手机号格式不正确</span>");
		return false;
	}
	
	return true;
	
}

/**
 * 客户信息：办公电话校验
 * @returns {Boolean}
 */

function checkTelephone(){
	var telephone = $('#telephoneId' ).val();
	$('#telephoneId' ).siblings(".error-msg").remove();
	if(isNull(telephone)){
		$('#telephoneId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入办公电话</span>");
		return false;
	}

	if(!checkPhone(telephone)){
		$('#telephoneId' ).after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;办公电话格式不正确</span>");
		return false;
	}
	
	return true;
	
}

/**
 * 客户信息：邮箱校验
 * @returns {Boolean}
 */
function checkCustomerEmail(){
	var email = $('#emailId' ).val();
	$('#emailId' ).siblings(".error-msg").remove();
	if(isNull(email)){
		$('#emailId' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入电子邮箱</span>");
		return false;
	}	
	if(!checkEmail(email)){
		$('#emailId' ).parent().append("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;电子邮箱格式不正确</span>");
		return false;
	}
	return true;
	
}

//==============================js校验end===================