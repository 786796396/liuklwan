<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>云监测  忘记密码</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
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
    <h2 class="forgot_pw_tit">找回登录密码</h2>
    <div class="steps_nav_box">
    	<div class="nav_num_box">
            <div class="nav_num"></div>
            <div class="nav_num_active active1"><div class="nav_num_rig_active"></div></div>
        </div>
        <ul class="nav_con">
        	<li class="nav_li_con1 active"><div class="nav_con">输入用户名</div></li><%--
            <li class="nav_li_con2"><div class="nav_con">验证</div></li>
           <li class="nav_li_con3"><div class="nav_con">重置密码</div></li> --%>
            <li class="nav_li_con4"><div class="nav_con">完成</div></li>
        </ul>
    </div>
    <div class="forgot_pw_main forgot_pw_main1">
    	<div id="emailError" class="error-msg" style="display:block;"></div>
    
        <%--<div class="web-name">网站标识码</div>
        --%><div class="input-box">
           	<input id="userId" name="userId" type="hidden"  />
            <input type="text" id="userName" name="userName" class="login_input1 ph-input" placeholder="网站标识码" _pad="30">
        </div>
        <div id="userNameError" class="error-msg" style="display:block;"></div>
        <%--<div class="web-name">主管单位</div>
        --%><div class="input-box">
            <input type="text" id="director" name="director" class="login_input1 ph-input" placeholder="主管单位" _pad="30">
        </div>
        <div id="captchaDiv" class="v-code">
            <input id="captcha" name="captcha" type="text" class="login_input1 ph-input" placeholder="验证码" _pad="30">
            <div class="v-code-box">
            	<a style="cursor: pointer;" title="点击更换验证码" onclick="javascript:changeCaptcha()" >
        			<img id="captchaImage" style="height: 45px;margin-top: -3px;width: 127px;" alt="验证码" src="<%= path%>/kaptcha.jpg"/>
       			</a>
            </div>
        </div>
        <div id="captchaError" class="error-msg"></div>
        <a id="nextStepHref1" class="btn btn-large btn-primary" href="javascript:void(0)" onClick="nextStep(1)">下一步</a>
    </div><!-- /forgot_pw_main1 -->
    <div class="forgot_pw_main forgot_pw_main4" style="display:none;">
        <h3 class="pw-h3">原密码已发送至您的邮箱</h3>
        <!--<div id="pwNum" class="pw-num">5</div>-->
        <a class="btn btn-large btn-primary" onclick="readyLogin()" href="javascript:void(0)" id="readyLogin">返回登录</a>
    </div><!-- /forgot_pw_main4 -->
</div> <!-- /container -->

<div class="login_bg"></div>
<div class="login_footer">
  <p>© Copyright <span class="footer-year">2015</span>. Ucap Info  All Rights Reserved</p>
</div>

<script type="text/javascript" src="<%= path %>/js/forgetPassword.js"></script>


<script type="text/javascript">
$(function () {
	$(".ph-input").iePlaceholder();
	//页面加载完之后使下一步超链接不能点击
	$("#nextStepHref1").removeAttr("onclick").css({background:"#D9D9D9 none repeat scroll 0% 0%"});
	//$("#nextStepHref2").removeAttr("onclick").css({background:"#D9D9D9 none repeat scroll 0% 0%"});
	//$("#nextStepHref3").removeAttr("onclick").css({background:"#D9D9D9 none repeat scroll 0% 0%"});
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
		url : "databaseInfo_ajaxResetPassword.action",
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
		
		
		var $username = $("#userName").val();
		
		var flag= true;
		
		$.ajax( {  
            type : "POST",  
            url : "users_sendPasswordEmail.action",
            data:{siteCode:$username},
            dataType:"json",
            async : false,  
            success : function(data) {
            	flag = data.status;
            	if(!flag){
            		$("#emailError").show();
            		$("#emailError").text(data.message);
            	}
            }
        });
		
		if(!flag){
			return false;
		}
		$(".forgot_pw_main"+i).hide();
		var nextI=i+1;
		$(".forgot_pw_main"+4).show();
		$(".nav_num_active").removeClass("active"+i).addClass("active"+4);
		$(".nav_li_con"+4).addClass("active");
	
	
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
		url : "databaseInfo_ajaxSendEmail.action",
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
	window.location.href="<%= path%>/databaseInfo_readyLogin.action?siteCode="+userName;

}



</script>
</body>
</html>
