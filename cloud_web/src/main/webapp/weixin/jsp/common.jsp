<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="description" content="">
<meta name="author" content="">


<link rel="stylesheet" type="text/css" href="<%= path %>/weixin/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/weixin/css/reset_default.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/weixin/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/weixin/css/dropload.css" />


<script type="text/javascript" src="<%= path %>/weixin/js/jquery-1.11.3.min.js"></script> 

<script type="text/javascript" src="<%= path %>/weixin/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="<%= path %>/weixin/js/gauge.js"></script>
<script type="text/javascript" src="<%= path %>/weixin/js/gauge.min.js"></script>

<script type="text/javascript" src="<%= path %>/weixin/js/dropload.js"></script>
<script type="text/javascript" src="<%= path %>/weixin/js/dropload.min.js"></script>

<script type="text/javascript">
	var webPath = "<%=path%>";
</script>
