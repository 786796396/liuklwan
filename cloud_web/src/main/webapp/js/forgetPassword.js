/*解决登录页面嵌套的问题*/
/*
 if (window.parent != window) {
 window.parent.location.reload(true);
 }
 */

$(function() {
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
        $(this).attr("class","login_input1 ph-input");
    });
    $("#password").focus(function() {
        $(this).attr("class","login_input1 ph-input");
    });
    $("#captcha").focus(function() {
        $(this).parent("#captchaDiv").attr("class","v-code");
    });
    $("#email").focus(function() {
        $(this).attr("class","login_input1 ph-input");
    });
    $("#emailCaptcha").focus(function() {
        $(this).parent("#emailCaptchaDiv").attr("class","v-code");
    });
    $("#resetPassword").focus(function() {
        $(this).attr("class","login_input1 ph-input");
    });
    $("#newPassword").focus(function() {
        $(this).attr("class","login_input1 ph-input");
    });
	// 校验用户名:当失去焦点时，验证
	$("#userName").blur(function() {
		validateLoginName($(this));
	});

	// 校验密码
	$("#password").blur(function() {
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

	$("#loginHref").bind(
			"click",
			function() {

				var $username = $("#userName").val();
				var $password = $("#password").val();
				var $captcha = $("#captcha").val();
				var $memberPass = $("#memberPass").prop("checked");

				location.href = "users_login.action?userName=" + $username
						+ "&password=" + $password + "&memberPass="
						+ $memberPass + "&captcha=" + $captcha;

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
	var usernameVal = username.val();
	// 获得对应的Span对象
	var errorSpan = username.attr("name") + "Error";
	var regular = /^[a-zA-Z_0-9][a-zA-Z_0-9]{3,18}$/;
	// 校验用户名不能为空
	if (!usernameVal) {
		$("#" + errorSpan).show().text("账号不能为空，请重新输入");
		flag = false;
	} else if (!regular.test(usernameVal)) {// 教研用户名的格式
		$("#" + errorSpan).show().text("用户名格式错误");
		flag = false;
	} else {
		// ajax校验用户名是否存在
		$.ajax({
			async : false,
			type : "post",
			url : "databaseInfo_ajaxUserName.action",
			data : "userName=" + usernameVal,
			dataType : "json",
			success : function(result) {
				if (result != null && result.userNameError != null) {
					flag = false;
					$("#" + errorSpan).show().text(result.userNameError);
				} else {
					$("#userId").val(result.userId);
					$("#director").val(result.director);
					$("#" + errorSpan).text("").hide();
				}
			}

		});
	}

	if (flag) {
		$("#userName").removeClass("info_error");
		$("#userName").addClass("info_right");
	} else {
		$("#userName").addClass("info_error");
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

		$("#" + errorSpan).show().text("验证码不能为空，请重新输入");

		flag = false;
	} else if (CaptchaVal.length != 5) {// 校验验证码的长度
		$("#" + errorSpan).show().text("验证码的长度有误！");
		flag = false;
	} else {
		// ajax校验验证码
		$.ajax({
			async : false,
			type : "post",
			url : "databaseInfo_ajaxCaptcha.action",
			data : "captcha=" + CaptchaVal,
			dataType : "json",
			success : function(result) {
				$("#" + errorSpan).show().text(result);
				flag = false;
			}
		});
	}

	if (flag) {
		$("#" + errorSpan).show().text("");
		$("#captchaDiv").removeClass("info_error");
		$("#captchaDiv").addClass("info_right");
		$("#nextStepHref1").attr("onclick", "nextStep(1)").css({
			background : "#2CAEE4"
		});

	} else {
		$("#captchaDiv").addClass("info_error");
	}

	return flag;
	// 如果校验通过的话，使下一步超链接按钮变为可用状态

};

// 邮箱校验
function validateEmail(email) {
	var flag = true;
	// 获得输入框中的值
	var emailVal = email.val();
	// 获得对应的Span对象
	var errorSpan = email.attr("name") + "Error";
	// 校验邮箱不能为空
	if (!emailVal) {
		$("#" + errorSpan).show().text("邮箱不能为空！");
		flag = false;
	} else {
		// 获得用户名
		var $username = $("#userName").val();
		// ajax校验邮箱
		$.ajax({
			async : false,
			type : "post",
			url : "databaseInfo_ajaxEmail.action",
			data : "email=" + emailVal + "&" + "userName=" + $username,
			dataType : "json",
			success : function(result) {
				flag = false;
				$("#" + errorSpan).show().text(result);
			}

		});
	}

	if (flag) {// 如果校验通过，使获取验证码的字体变为蓝色
		$("#" + errorSpan).hide();
		$("#email").removeClass("info_error");
		$("#email").addClass("info_right");
		$("#activitiCodeDiv").css({
			color : "#2CAEE4"
		}).attr("onclick", "getActivitiCode()");

	} else {
		$("#email").addClass("info_error");

	}

	return flag;
};

// 邮件验证码的校验
function validateEmailCaptcha(emailCaptcha) {
	var flag = true;

	// 获得输入框中的值
	var emailCaptchaVal = emailCaptcha.val();
	// 获得对应的Span对象
	var errorSpan = emailCaptcha.attr("name") + "Error";
	// 校验邮箱不能为空
	if (!emailCaptchaVal) {
		$("#" + errorSpan).show().text("验证码不能为空！");
		flag = false;
	} else {
		// ajax校验邮件验证码
		$.ajax({
			type : "post",
			url : "databaseInfo_ajaxEmailCaptcha.action",
			data : "emailCaptcha=" + emailCaptchaVal,
			dataType : "json",
			success : function(result) {
				flag = false;
				$("#" + errorSpan).show().text(result);
			}

		});
	}

	// 如果邮箱验证码校验通过，改变下一步的样式
	if (flag) {
		$("#" + errorSpan).show().text("");
		$("#emailCaptchaDiv").removeClass("info_error");
		$("#emailCaptchaDiv").addClass("info_right");
		$("#nextStepHref2").attr("onclick", "nextStep(2)").css({
			background : "#2CAEE4"
		});

	} else {
		$("#emailCaptchaDiv").addClass("info_error");
	}

	return flag;
};

// 修改密码中的密码校验
function validateResetPassword(password) {

	var flag = true;

	// 获得输入框中的值
	var passwordVal = password.val();

	var regular = /^[a-zA-Z_0-9][a-zA-Z_0-9]{3,18}$/;

	// 校验密码能为空
	if (!passwordVal) {
		// $("#passwordDiv").attr("data-content","密码不能为空");
		alert("密码不能为空！");
		flag = false;
	} else {
		// 教研密码的格式
		if (!regular.test(passwordVal)) {
			// $("#passwordDiv").attr("data-content","密码格式错误");
			alert("密码格式错误！");
			flag = false;
		}
	}

	if (flag) {
		$("#resetPassword").removeClass("info_error");
		$("#resetPassword").addClass("info_right");
		$("#resetBtn").attr("onclick", "clearPassword()").css({
			background : "#2CAEE4"
		});
	} else {
		$("#resetPassword").addClass("info_error");
	}

	return flag;
};

// 修改密码中的密码确认
function validateNewPassword(password) {

	var flag = true;
	// 获得输入框中的值
	var passwordVal = password.val();

	var regular = /^[a-zA-Z_0-9][a-zA-Z_0-9]{3,18}$/;

	// 校验密码能为空
	if (!passwordVal) {
		// $("#newPasswordDiv").attr("data-content","新密码不能为空");
		alert("新密码不能为空");
		flag = false;
	} else if (!regular.test(passwordVal)) {// 教研密码的格式
		// $("#newPasswordDiv").attr("data-content","新密码格式错误");
		alert("新密码格式错误");
		flag = false;
	} else {
		var oldPasswordVal = $("#resetPassword").val();
		// 校验两次的密码是否一致
		if (passwordVal != oldPasswordVal) {
			// $("#newPasswordDiv").attr("data-content","两次密码不一致");
			alert("两次密码不一致！");
			flag = false;
		}
	}

	if (flag) {
		$("#newPassword").removeClass("info_error");
		$("#newPassword").addClass("info_right");
		// 改变下一步按钮的颜色
		$("#resetBtn").attr("onclick", "clearPassword()").css({
			background : "#2CAEE4"
		});
		$("#nextStepHref3").attr("onclick", "nextStep(3)").css({
			background : "#2CAEE4"
		});
	} else {
		$("#newPassword").addClass("info_error");
	}

	return flag;
};
