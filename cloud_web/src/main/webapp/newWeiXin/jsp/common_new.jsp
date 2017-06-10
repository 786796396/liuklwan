<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
long autoVersoin = new Date().getTime();
%>
<meta charset="UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<meta content="portrait" name="screen-orientation">
<meta content="portrait" name="x5-orientation">

<link rel="stylesheet" href="<%= path %>/newWeiXin/css/reset.css"/>
<link rel="stylesheet" href="<%= path %>/newWeiXin/css/common.css"/>
<link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/dropload.css" />


<script type="text/javascript" src="<%= path %>/newWeiXin/js/jquery-1.11.3.min.js"></script> 
<script type="text/javascript" src="<%= path %>/newWeiXin/js/gauge.js"></script>
<script type="text/javascript" src="<%= path %>/newWeiXin/js/dropload.js"></script>
<script type="text/javascript" src="<%= path %>/newWeiXin/js/dropload.min.js"></script>
<script type="text/javascript" src="<%= path %>/newWeiXin/js/fastclick.js"></script>

<script type="text/javascript">
	var webPath = "<%=path%>";
	var autoVersoin = new Date().getTime();
</script>
