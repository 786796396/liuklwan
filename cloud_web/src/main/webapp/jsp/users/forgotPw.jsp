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
<div class="login_container fw_con">
    <h2 class="fw_tit">找回登录密码</h2>
    <form id="loginForm" action="users_login.action" method="post">
    <div class="login-input-box">
        <input id="userName" type="text" class="login_input1 ph-input" name="userName" value="110000" placeholder="用户名" _pad="30">
    </div>
    <div class="error-msg"  id="userNameError">${errors.userNameError }</div>
   	<div id="captchaDiv" class="v-code">
        <input id="captcha" type="text" class="login_input1 ph-input" name="captcha" value="${captcha }" placeholder="验证码" _pad="30">
        <div class="v-code-box">
            <a style="cursor: pointer;" title="点击更换验证码" onclick="javascript:changeCaptcha()" >
                <img id="captchaImage" style="height: 45px;margin-top: -3px;width: 127px;" alt="验证码" src="<%= path%>/kaptcha.jpg"/>
            </a>
        </div>
    </div>
    
    <div class="error-msg" id="captchaError">${json.message }</div>
    </form>
    <a id="fwHref" class="btn btn-large btn-primary mar1" >下一步</a>
</div> <!-- /container -->
<div class="login_bg"></div>
<div class="login_footer">
  <p>© Copyright <span class="footer-year">2015</span>. Ucap Info  All Rights Reserved</p>
</div>
<!-- Modal -->
<div id="fwModal" class="page-modal modal fade hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header modal-header2">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close2"></i></button>
    <h3 id="myModalLabel">提示</h3>
  </div>
  <div class="modal-body" style="max-height:none;">
    <div class="fw-modal">
    	<div>您使用的是《全国政府网站信息报送系统》账号，</div>
        <div>请联系报送系统客服找回！</div>
    </div>
  </div>
  <div class="modal-footer">
      <a href="/" class="btn btn-success green-btn">确  定</a>
    </div>
</div>
<script type="text/javascript" src="<%= path %>/js/login.js"></script>

<script type="text/javascript">
/**
 * 点击更换验证码
 */
function changeCaptcha() {
	$("#captchaImage").attr("src", "<%= path%>/kaptcha.jpg?"+new Date().getTime());
}
$("#fwHref").click(function(){
	$('#fwModal').modal('show');
});
</script>

</body>
</html>
