/*解决登录页面嵌套的问题*/
/*
 if (window.parent != window) {
 window.parent.location.reload(true);
 }
 */

//组织单位  登录
function formSubmitOrg(){
			var htm = '<div class="tab-pane fade active in" id="home">' +
					'<form id="loginForm" target="_parent" action="users_loginsForm.action" method="post">' +
					'<div class="login-input-box">' +
					'<div class="error-msg" id="loginError"></div>' +
					'<input id="userName" class="login_input1 ph-input form-control" name="userName" placeholder="用户名" _pad="30" type="text">' +
					'</div>' +
					'<div class="login-input-box">' +
					'<input id="password" class="login_input1 ph-input form-control" name="passWord" maxlength="18" placeholder="密码" _pad="30" type="password">' +
					'</div>' +
					'<div class="error-msg" id="passWordError"></div>' +
					'<c:set var="kaptchaSession" value="${sessionScope.kaptchaSession }" scope="request"/>' +
					'<c:if test="${kaptchaSession >2 }">' +
//					'<div id="captchaDiv" class="v-code">' +
//					'<input id="captcha" type="text" class="login_input1 ph-input" name="captcha" placeholder="验证码" _pad="30">' +
//					'<div class="v-code-box">' +
//					'<a style="cursor: pointer;" title="点击更换验证码" onclick="javascript:changeCaptcha()" >' +
//					'<img id="captchaImage" style="height: 45px;margin-top: -3px;width: 127px;" alt="验证码" src="<%= path%>/kaptcha.jpg"/>' +
//					'</a>' +
//					'</div>' +
//					'</div>' +
					'</c:if>' +
					'<div class="zhuangtai" style="display:none;"> <span class="dlzt">' +
					'<input value="" type="checkbox">' +
					'&nbsp;记住登录状态</span> <span class="jym"><a href="#">忘记密码？</a></span> </div>' +
					'</form>' +
					'<div class="dlk">' +
					'<input id="loginHref2" type="button" value="立即登录">' +
					'</div>' +
					'</div>';
					
			$('#myTabContent').html(htm);
			
			
			// 添加回车绑定事件
			document.onkeydown=function(event){
				var e = event || window.event || arguments.callee.caller.arguments[0];        
				 if(e && e.keyCode==13){ // enter 键
					 document.getElementById("loginHref2").click();
				}
			};
		}
//填报单位 登录
function formSubmit(){
	var htm = '<div class="tab-pane fade active in" id="ios">' +
			'<form id="loginForm" target="_parent" action="users_loginsForm.action" method="post">' +
			'<div class="login-input-box">' +
			'<div class="error-msg" id="loginError"></div>' +
			'<input id="userName" class="login_input1 ph-input form-control" name="userName" placeholder="网站标识码" _pad="30" type="text">' +
			'</div>' +
			'<div class="login-input-box">' +
			'<input id="password" class="login_input1 ph-input form-control" name="passWord" maxlength="18" placeholder="校验码" _pad="30" type="password">' +
			'</div>' +
			'<c:set var="kaptchaSession" value="${sessionScope.kaptchaSession }" scope="request"/>' +
//					'<c:if test="${kaptchaSession >2 }">' +
//					'<div id="captchaDiv" class="v-code">' +
//					'<input id="captcha" type="text" class="login_input1 ph-input" name="captcha" placeholder="验证码" _pad="30">' +
//					'<div class="v-code-box">' +
//					'<a style="cursor: pointer;" title="点击更换验证码" onclick="javascript:changeCaptcha()" >' +
//					'<img id="captchaImage" style="height: 45px;margin-top: -3px;width: 127px;" alt="验证码" src="<%= path%>/kaptcha.jpg"/>' +
//					'</a>' +
//					'</div>' +
//					'</div>' +
//					'</c:if>' +
			'<div class="zhuangtai" style="display:none;"> <span class="dlzt">' +
			'<input value="" type="checkbox">' +
			'&nbsp;记住登录状态</span> <span class="jym"><a href="#">忘记校验码？</a></span> </div>' +
			'</form>' +
			'<div class="dlk">' +
			'<input id="loginHref" type="button" value="立即登录">' +
			'</div>' +
			'</div>';
	$('#myTabContent').html(htm);
	
	
	// 添加回车绑定事件
	document.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];        
		 if(e && e.keyCode==13){ // enter 键
			 document.getElementById("loginHref").click();
		}
	};
}


		
function getLoginNum(){
	$.ajax({
		type : "POST",
		url : "users_getLoginNum.action",
		dataType : "json",
		async : false,
		success : function(data) {
			var htm = '<h2><font>'+data.healthScores+'</font><i class="'+data.bottom+'">'+data.differential+'  （'+data.differentialRate+'%）</i></h2>' +
					'<h4>全国政府网站整体健康指数</h4>' +
					'<p><font>监测站点</font><span class="span1">'+data.databaseInfoCount+'</span></p>' +
					'<p><font>监测页面</font><span id="pageNum" class="span1" _val="'+data.pageNum+'">'+data.pageNum+'</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
					'<span class="question-font"><font>发现问题</font><span id="questionNum" class="span2" _val="'+data.errorNum+'">'+data.errorNum+'</span></span></p>';
		$("#loginNum").html(htm);
		}
	});
}
$(function() {
	//退出时清除cookie里的值
	//组织
	$.removeCookie('type_cookie');   
	$.removeCookie('top_cookie');   // 一级菜单  头部
	$.removeCookie('twoMenu_cookie');  // 二级菜单  
	//填报
	$.removeCookie('typeTB_cookie');
	$.removeCookie('topTB_cookie');  // 一级菜单  头部
	$.removeCookie('twoMenuTB_cookie'); // 二级菜单  
	
	$.cookie('type_cookie', "1", {path:'/'});
	$.cookie('typeTB_cookie', "1", {path:'/'});
	
	getLoginNum();
	
	formSubmitOrg();
	
	
	$("input[type='checkbox']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	$(".ph-input").iePlaceholder();
	$(".error-msg").each(function() {
		var errorText = $(this).text();
		if (errorText != null && errorText != "") {
			$(this).show();
		}
	});

	$.each($("input"), function() {
		var obj = $(this);
		obj.mouseover(function() {// 当鼠标划过时，
			obj.addClass('onFocus');
		}).mouseout(function() {// 当鼠标离开时
			obj.removeClass('onFocus');
		});
	});
	var checkObj = $("#checkImage");
	checkObj.mouseover(function() {
		checkObj.addClass('onFocus');
	}).mouseout(function() {
		checkObj.removeClass('onFocus');
	});
    //获取焦点样式
    $("#userName").focus(function() {
        //$(this).attr("class","login_input1 ph-input");
    });
    $("#password").focus(function() {
       // $(this).attr("class","login_input1 ph-input");
    });
    $("#captcha").focus(function() {
       $(this).parent("#captchaDiv").attr("class","v-code");
    });
	// 校验用户名:当失去焦点时，验证
    $("#myTabContent").on("blur","#userName",function() {
    	validateLoginName($(this));
//    }
//	$("#userName").blur(function() {
//		validateLoginName($(this));
	});

	// 校验密码
	$("#myTabContent").on("blur","#password",function() {
//	$("#password").blur(function() {
		validatePassword($(this));
	});

	// 校验验证码
	$("#captcha").blur(function() {
		validateCaptcha($(this));
	});

	// 校验邮箱
	$("#email").blur(function() {
		validateEmail($(this));
	});

	// 校验邮件验证码
	$("#emailCaptcha").blur(function() {

		validateEmailCaptcha($(this));
	});

	// 修改密码中的密码校验
	$("#resetPassword").blur(function() {
		validateResetPassword($(this));
	});
	// 修改密码中的新密码校验
	$("#newPassword").blur(function() {
		validateNewPassword($(this));
	});
	
	$("#myTabContent").on("click","#loginHref2",function() {
//	$("#loginHref").bind(
//			"click",
//			function() {
				var flag=true;
				var userName = $.trim($("#userName").val());
				
				if(!validateLoginName($("#userName"))){
					//$("#loginError").show().text("账号不能为空，请重新输入");
					flag=false;
					return ;
				}
				
				if(userName.length!=6){
					$("#loginError").show().text("账号格式输入错误");
					$("#loginError").css('display','block'); 
					flag=false;
					return ;
				}
				if(flag!=false&&!validatePassword($("#password"))){
					$("#loginError").show().text("密码格式错误");
					flag=false;
				}
//				if($("#captcha").val()!=null){
//					if(!validateCaptcha($("#captcha"))){
//						flag=false;
//						$("#captcha").val("");
//					}
//				}
				
				if(flag){
					var siteCode;
					$.ajax({
						cache: true,
						type: "POST",
						dataType:"json",
						url:"users_loginsForm.action",
						data:$('#loginForm').serialize(),// 你的formid
						async: false,
					    success: function(data) {
						    siteCode = data.siteCode;
						    if(data.message!=""){
						    	$("#loginError").show().text(data.message);
						    	flag = false;
						    }
					    }
					});

					if(flag){
						//$("#loginForm").submit();
						if(siteCode!=null&&siteCode!=""){
							if(siteCode.length<=6){
								window.location.href=webPath+"/connectionHomeDetail_indexOrg.action";
							}else {
								window.location.href=webPath+"/fillUnit_gailan.action";
							}
						}
					}
					
					
				}

			});
	$("#myTabContent").on("click","#loginHref",function() {
//	$("#loginHref").bind(
//			"click",
//			function() {
				var flag=true;
				var userName = $.trim($("#userName").val());
				if(!validateLoginName($("#userName"))){
					//$("#loginError").show().text("账号不能为空，请重新输入");
					flag=false;
					return ;
				}
				if(userName.length!=10){
					$("#loginError").show().text("账号格式输入错误");
					$("#loginError").css('display','block'); 
					flag=false;
					return ;
				}
//				if(flag!=false&&!validatePassword($("#password"))){
//					$("#loginError").show().text("密码格式错误");
//					flag=false;
//				}
//				if($("#captcha").val()!=null){
//					if(!validateCaptcha($("#captcha"))){
//						flag=false;
//						$("#captcha").val("");
//					}
//				}
				
				if(flag){
					var siteCode;
					$.ajax({
						cache: true,
						type: "POST",
						dataType:"json",
						url:"users_loginsForm.action",
						data:$('#loginForm').serialize(),// 你的formid
						async: false,
					    success: function(data) {
					    	 siteCode = data.siteCode;
						    if(data.message!=""){
						    	$("#loginError").show().text(data.message);
						    	flag = false;
						    }
					    }
					});

					
					
					if(flag){
						if(siteCode!=null&&siteCode!=""){
							if(siteCode.length<=6){
								window.location.href=webPath+"/connectionHomeDetail_indexOrg.action";
							}else {
								window.location.href=webPath+"/fillUnit_gailan.action";
							}
						}
						
//						$("#loginForm").submit();
					}
				}

			});

	// 表单提交时校验表单的正确性

});

function hideLoginBezel() {
	$("#loginBezel").toggle();
	$("#pointerOpen").toggle();
}

// 用户名校验
function validateLoginName(username) {
	var flag = true;
	// 获得输入框中的值
	var usernameVal = $.trim(username.val());
	// 获得对应的Span对象
	var errorSpan = username.attr("name") + "Error";
	var regular = /^[a-zA-Z_0-9][a-zA-Z_0-9]{3,18}$/;
	// 校验用户名不能为空
	if (!usernameVal) {
		//$("#" + errorSpan).show().text("账号不能为空，请重新输入");
		//$("#userName").addClass("login_input1 info_error");
		flag = false;
		$("#loginError").show().text("账号不能为空，请重新输入");
	} else if (!regular.test(usernameVal)) {
		//$("#" + errorSpan).show().text("用户名格式错误");
		flag = false;
		$("#loginError").show().text("用户名格式错误");
	}
	// ajax校验用户名是否存在
	// $.ajax({
	// type : "post",
	// url : "databaseInfo_ajaxUserName.action",
	// data : "userName=" + usernameVal,
	// dataType : "json",
	// success : function(result) {
	//			
	// if(result!=null&&result.userNameError!=null){
	// $("#" + errorSpan).show().text(result.userNameError);
	// }else{
	// $("#userId").val(result.userId);
	// }
	//			
	//			
	// return false;
	// }
	//
	// });
	if (flag) {
		//$("#userName").removeClass("info_error");
		//$("#userName").addClass("info_right");
		//$("#" + errorSpan).text("").hide();
		$("#loginError").text("").hide();
	} else {
		//$("#userName").addClass("info_error");
	}
	return flag;

};

// 密码校验
function validatePassword(password) {

	var flag = true;
	// 获得输入框中的值   
	var passwordVal = $.trim(password.val());
	// 获得对应的Span对象
	var errorSpan = password.attr("name") + "Error";
	var regular = /^.{3,18}$/;

	// 校验密码能为空
	if (!passwordVal) {
		flag = false;
		$("#loginError").show().text("密码不能为空，请重新输入");
		//$("#" + errorSpan).show().text("密码不能为空，请重新输入");
	} else if (!regular.test(passwordVal)) {// 教研密码的格式
		flag = false;
		
		$("#loginError").show().text("密码格式错误");
		//$("#" + errorSpan).show().text("密码格式错误");
	}

	if (flag) {
		$("#loginError").show().text("");
		//$("#" + errorSpan).hide().text("");
		//$("#password").removeClass("info_error");
		//$("#password").addClass("info_right");
	} else {
		//$("#password").addClass("info_error");

	}

	return flag;
};

// 验证码校验
function validateCaptcha(Captcha) {

	var flag = true;
	// 获得输入框中的值
	var CaptchaVal = Captcha.val();
	// 获得对应的Span对象
	var errorSpan = Captcha.attr("name") + "Error";
	// 校验用户名不能为空
	if (!CaptchaVal) {
		flag = false;
		$("#" + errorSpan).show().text("验证码不能为空，请重新输入");
	} else if (CaptchaVal.length != 5) {// 校验验证码的长度
		$("#" + errorSpan).show().text("验证码的长度有误！");
		flag = false;
	}
	// ajax校验验证码
	$.ajax({
		type : "post",
		url : "users_ajaxCaptcha.action",
		data : "captcha=" + CaptchaVal,
		dataType : "json",
		success : function(result) {
			$("#" + errorSpan).show().text(result);
			return false;
		}

	});
	if (flag) {
		//$("#captchaDiv").removeClass("info_error");
		//$("#captchaDiv").addClass("info_right");
		$("#" + errorSpan).show().text("");
	} else {
		$//("#captchaDiv").addClass("info_error");

	}
	return flag;

};

