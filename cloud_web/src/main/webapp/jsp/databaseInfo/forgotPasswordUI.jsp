<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>


<title>forgot-password</title>

</head>

<body>
<div class="navbar navbar-rel">
  <div class="navbar-inner">
    <div class="container-fluid">
      <a class="brand" href="<%=path%>/users_login.action"><img alt="logo" src="<%= path%>/images/common/logo.png"/></a>
      <div class="nav-collapse">
        <div class="pull-left navbar-logotit">政府网站云监管平台</div>
        <p class="navbar-text pull-right">服务热线：400-876-5486</p>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>
<div class="forgot_pw_container">
    <h2 class="forgot_pw_tit">欢迎回来，请您登录</h2>
    <div class="steps_nav_box">
    	<div class="nav_num_box">
            <div class="nav_num"></div>
            <div class="nav_num_active active1"><div class="nav_num_rig_active"></div></div>
        </div>
        <ul class="nav_con">
        	<li class="nav_li_con1 active"><div class="nav_con">输入用户名</div></li>
            <li class="nav_li_con2"><div class="nav_con">验证</div></li>
            <li class="nav_li_con3"><div class="nav_con">重置密码</div></li>
            <li class="nav_li_con4"><div class="nav_con">完成</div></li>
        </ul>
    </div>
    <div class="forgot_pw_main forgot_pw_main1">
        <div class="input-box">
           	<input id="userId" name="userId" type="hidden"  />
            <input type="text" id="userName" name="userName" class="login_input1" placeholder="用户名">
        </div>
        <div id="userNameError" class="error-msg" style="display:block;"></div>
        <div id="captchaDiv" class="v-code">
            <input id="captcha" name="captcha" type="text" class="login_input1" placeholder="验证码">
            <div class="v-code-box">
            	<a style="cursor: pointer;" title="点击更换验证码" onclick="javascript:changeCaptcha()" >
        			<img id="captchaImage"  alt="验证码" src="<%= path%>/kaptcha.jpg"/>
       			</a>
            </div>
        </div>
        <div id="captchaError" class="error-msg"></div>
        <a id="nextStepHref1" class="btn btn-large btn-primary" href="javascript:void(0)" onClick="nextStep(1)">下一步</a>
    </div><!-- /forgot_pw_main1 -->
    <div class="forgot_pw_main forgot_pw_main2" style="display:none;">
        <div class="input-box">
            <input id="email" name="email" type="text" class="login_input1" placeholder="邮箱">
        </div>
        <div id="emailError" class="error-msg" style="display:block;"></div>
        <div id="emailCaptchaDiv" class="v-code">
            <input id="emailCaptcha" name="emailCaptcha" type="text" class="login_input1" placeholder="邮件验证码">
            <div id="activitiCodeDiv" onclick="javascript:getActivitiCode()" class="v-code-btn">获取验证码</div>
        </div>
        <div class="error-msg" id="emailCaptchaError"></div>
        <a id="nextStepHref2" class="btn btn-large btn-primary" href="javascript:void(0)" onClick="nextStep(2)">下一步</a>
    </div><!-- /forgot_pw_main2 -->
    <div class="forgot_pw_main forgot_pw_main3" style="display:none;">
    	<div class="popover-box">
            <div id="passwordDiv" class="input-box" data-content="长度为8-16个字符，区分大小写，至少包含两种类型" data-placement="right" data-toggle="popover">
                <input id="resetPassword"  type="password" class="login_input1" placeholder="设置新密码">
            </div>
        </div>
        <div id="resetPasswordDiv" class="input-box" data-content="长度为8-16个字符，区分大小写，至少包含两种类型" data-toggle="popover">
            <input id="newPassword" type="password" class="login_input1" placeholder="重复新密码">
        </div>
        <div class="forgot_pw_btn">
            <a id="nextStepHref3" class="btn btn-large btn-primary" href="javascript:void(0)" onClick="nextStep(3)">下一步</a>
            <a  id="resetBtn"  class="btn btn-large btn-qx" href="javascript:void(0)">重置</a>
        </div>
    </div><!-- /forgot_pw_main3 -->
    <div class="forgot_pw_main forgot_pw_main4" style="display:none;">
        <h3 class="pw-h3">新密码已生效</h3>
        <div id="pwNum" class="pw-num">5</div>
        <a class="btn btn-large btn-primary" onclick="readyLogin()" href="javascript:void(0)" id="readyLogin">返回登录</a>
    </div><!-- /forgot_pw_main4 -->
</div> <!-- /container -->

<div class="login_bg"></div>
<footer class="login_footer">
  <p>© Copyright <span class="footer-year">2015</span>. Ucap Info  All Rights Reserved</p>
</footer>

<script type="text/javascript" src="<%= path %>/js/forgetPassword.js"></script>


<script type="text/javascript">
$(function () {
	
	//页面加载完之后使下一步超链接不能点击
	$("#nextStepHref1").removeAttr("onclick").css({background:"#D9D9D9 none repeat scroll 0% 0%"});
	$("#nextStepHref2").removeAttr("onclick").css({background:"#D9D9D9 none repeat scroll 0% 0%"});
	$("#nextStepHref3").removeAttr("onclick").css({background:"#D9D9D9 none repeat scroll 0% 0%"});
	//$("#resetBtn").removeAttr("onclick");
	$("#activitiCodeDiv").removeAttr("onclick");
	
	$("[data-toggle='popover']").popover();
	$(".popover-box").mouseleave(function(){
	   $(this).find("[data-toggle='popover']").popover("hide");
	});
	$("#resetBtn").click(function(){
		$("#newPw").val("");
		$("#repeatPw").val("");
	});
	
	
	//重置密码
	/* $("#resetBtn").click(function(){
	
		var userId=$("#userId").val();
		var resetPassword=$("#resetPassword").val();
	
		
		//发送ajax请求	
		$.ajax({
		type : "post",
		url : "users_ajaxResetPassword.action",
		data : "userId=" + userId+"&password="+resetPassword,
		dataType : "json",
		success : function(result) {
			alert(result.errorMessage);
			//$("#" + errorSpan).show().text(result.errorMessage);
			return false;
		}

	});
		
	}); */
});
function nextStep(i){

		var flag1=true;
		var flag2=true;
	
	//=======================================
	if(i==1){//校验用户名和验证码是否为空
		flag1=validateLoginName($("#userName"));
		flag2=validateCaptcha($("#captcha"));
		if(!flag1||!flag2){
			return false ;
		}
	}else if(i==2){
		flag1=validateEmail($("#email"));
		flag2=validateEmailCaptcha($("#emailCaptcha"));
		if(!flag1||!flag2){
			return false ;
		}
	}else if(i==3){
		flag1=validateResetPassword($("#resetPassword"));
		flag2=validateNewPassword($("#newPassword"));
		if(!flag1||!flag2){
			return false ;
		}
		//校验通过，发送ajax请求，更新密码
		var userId=$("#userId").val();
		var resetPassword=$("#resetPassword").val();
		var userName=$("#userName").val();
	
		var flag=true;
		
		//发送ajax请求	
		$.ajax({
			async:false,
			type : "post",
			url : "users_ajaxResetPassword.action",
			data : "userId=" + userId+"&password="+resetPassword,
			dataType : "json",
			success : function(result) {
				if(result.errorMessage!=null&&result.errorMessage!=""){
					flag=false;
					alert(result.errorMessage);
				}else{
					flag=true;
				}
			}

		});
		
		if(!flag){
			return false;
		}

	
	}
	//=======================================

	$(".forgot_pw_main"+i).hide();
	var nextI=i+1;
	$(".forgot_pw_main"+nextI).show();
	$(".nav_num_active").removeClass("active"+i).addClass("active"+nextI);
	$(".nav_li_con"+nextI).addClass("active");
	if(i==3){
		var pwNum=5;
		var setI;
		setI=setInterval(function(){
			if(pwNum==1){
				clearInterval(setI);
				window.location.href="<%= path%>/users_login.action?userName="+userName; 
			}else{
				$("#pwNum").html(pwNum-1);
				pwNum=pwNum-1;
			}
		},1000);	
	}
}

/**
 * 点击更换验证码
 */
function changeCaptcha() {
	$("#captchaImage").attr("src", "<%= path%>/kaptcha.jpg?"+new Date().getTime());
}


//获取验证码：发送邮件
function getActivitiCode(){

	//获取邮箱
	var email=$("#email").val();
	
	
	$.ajax({
		type : "post",
		url : "users_ajaxSendEmail.action",
		data : "email=" + email,
		dataType : "json",
		success : function(result) {
			$("#" + errorSpan).show().text(result.errorMessage);
			return false;
		}

	});

	

}



//重置密码
function clearPassword(){

	$("#resetPassword").val("");
	$("#newPassword").val("");

}

//立即登录
function readyLogin(){
	
	var userName=$("#userName").val();
	window.location.href="<%= path%>/databaseInfo_login.action?userName="+userName;

}



</script>
</body>
</html>
