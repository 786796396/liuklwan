<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String siteCodeParam = request.getParameter("siteCode")==null?"":request.getParameter("siteCode");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
 <head>
		<title>开普云监管</title>
		<%@ include file="/common/meta.jsp"%>
        <link rel="shortcut icon" href="<%= path %>/images/common/favicon.ico">
		<link rel="Bookmark" href="<%= path %>/images/common/favicon.ico">
       	<link rel="stylesheet" type="text/css" href="<%= path %>/css/cloud-login/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%= path %>/css/cloud-login/login.css"/>
		<script type="text/javascript" src="<%= path %>/js/cloud-login/jquery.js"></script>
		<script type="text/javascript" src="<%= path %>/js/cloud-login/bootstrap.js"></script>
		<script type="text/javascript" src="<%= path %>/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="<%= path %>/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%= path %>/js/bootstrap-select.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js-->
		<script type="text/javascript" src="<%= path %>/js/jquery.dataTables.js"></script>
		<script type="text/javascript" src="<%= path %>/js/fnReloadAjax_lw.js"></script>
		<script type="text/javascript" src="<%= path %>/js/jquery.form.js"></script>
		<script type="text/javascript" src="<%= path %>/js/datepicker/jquery-ui.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/icheck/icheck.js"></script>
		<script type="text/javascript" src="<%= path %>/js/placeholder.js"></script>
        <script type="text/javascript" src="<%= path %>/js/cloud-login/jquery.animateNumber.min.js"></script>
		<script type="text/javascript" src="<%= path %>/js/common.js"></script>
		<script type="text/javascript" src="<%= path %>/js/underscore.js"></script>
        
		<link rel="stylesheet" type="text/css" href="<%= path %>/js/icheck/blue.css" />
		</head>
  
  <body>
    <div class="container-fluid banner">
      <div class="row">
        <div class="col-md-7 col-xs-12 col-sm-6 banner_left">
          <div class="sm-banner-bg"><img src="<%= path %>/images/banner_l.png" src="login-css/banner_l.png" alt="" class="img-responsive"></div>
          <div class="tj_list" id="loginNum">
           
          </div>
        </div>
        <div class="col-md-4 col-xs-12 col-sm-5 pull-right loginbox">
          <div class="login">
            <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a aria-expanded="true" onclick="formSubmitOrg()" href="javascript:void(0)" data-toggle="tab">组织单位 <span>(主管)</span></a> </li>
              <li class=""><a aria-expanded="false" onclick="formSubmit()" href="javascript:void(0)" data-toggle="tab" class=" text-right">填报单位 <span>(主办)</span></a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
              
            </div>
          </div>
        </div>
      </div>
    </div>
    <script type="text/javascript" src="<%= path %>/js/logins.js"></script>
    <script type="text/javascript">
    var webPath = "<%=path%>";
		$(function(){
			var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
			randomNumF("pageNum",1000,10,20);
			randomNumF("questionNum",3000,1,5);
			function randomNumF(id,time,minNum,maxNum){
				setInterval(function(){
					var idNum=$("#"+id).attr("_val");
					var calculateNum=maxNum-minNum;
					var randomNum=Math.floor(Math.random()*calculateNum+minNum);
					var randomNumEnd=parseInt(idNum)+randomNum;
					$("#"+id).attr("_val",randomNumEnd);
					$("#"+id).animateNumber({
						number: randomNumEnd,
						numberStep: comma_separator_number_step
					});
					
					idNum=idNum+randomNum;
				},time);
			}

		});
		
		/**
		 * 点击更换验证码
		 */
		function changeCaptcha() {
			$("#captchaImage").attr("src", "<%= path%>/kaptcha.jpg?"+new Date().getTime());
		}
	</script>
  </body>
</html>
