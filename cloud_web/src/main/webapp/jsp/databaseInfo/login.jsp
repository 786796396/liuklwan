<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>开普互联政府网站云监管平台</title>
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
<div class="login_container">
    <h2 class="login_tit">欢迎回来，请您登录</h2>
    <div class="login-input-box">
        <input id="userName" type="text" class="login_input1" name="userName" value="${userName }">
    </div>
    <div class="error-msg"  id="userNameError">${errors.userNameError }</div>
    <div class="login-input-box">
        <input id="password" type="password" class="login_input1" name="password" value="${password }">
    </div>
    <div class="error-msg" id="passwordError">${errors.passwordError }</div>
    
    <div id="captchaDiv" class="v-code">
        <input id="captcha" type="text" class="login_input1" name="captcha" value="${captcha }">
        <div class="v-code-box">
        	<a style="cursor: pointer;" title="点击更换验证码" onclick="javascript:changeCaptcha()" >
        		<img id="captchaImage" alt="验证码" src="<%= path%>/kaptcha.jpg"/>
       		</a>
        </div>
    </div>
    <div class="error-msg" id="captchaError">${errors.captchaError }</div>
    <div class="row-fluid checkbox_box">
    	<label class="checkbox pull-left"><input type="checkbox" id="memberPass"/>记住我</label>
        <a href="databaseInfo_forgotPasswordUI.action" target="_blank" class="pull-right">忘记密码？</a>
    </div>
    <a id="loginHref" class="btn btn-large btn-primary" href="javascript:void(0)">立即登录</a>
</div> <!-- /container -->
<div class="login_bg"></div>
<footer class="login_footer">
  <p>© Copyright <span class="footer-year">2015</span>. Ucap Info  All Rights Reserved</p>
</footer>

<script type="text/javascript" src="<%= path %>/js/login.js"></script>

<script type="text/javascript">
/**
 * 点击更换验证码
 */
function changeCaptcha() {
	$("#captchaImage").attr("src", "<%= path%>/kaptcha.jpg?"+new Date().getTime());
}

</script>

</body>
</html>
