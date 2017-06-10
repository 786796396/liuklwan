<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String siteCodeParam = request.getParameter("siteCode")==null?"":request.getParameter("siteCode");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

response.addHeader("Cache-Control", "no-cache");
response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");
long autoVersoin = new Date().getTime();
%>
<link rel="shortcut icon" href="<%= path %>/images/common/favicon.ico">
<link rel="Bookmark" href="<%= path %>/images/common/favicon.ico">


<link rel="stylesheet" type="text/css" href="<%= path %>/css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/js/icheck/blue.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/bootstrap-select.css"/>
<link rel="stylesheet" media="screen"  href="<%= path %>/css/datepicker/jquery-ui.css"  />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/jquery.dataTables_themeroller.css" />
<link rel="stylesheet"  type="text/css" href="<%=path%>/css/jquery.mCustomScrollbar.css" /><!--滚动条css-->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/reset_default.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/viewport.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/leftmenu.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/pucha.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/addcss.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/nine.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/yujingyindao.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/yunjc.css" />
<%-- <!-- 新css -->
<!--header-->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/header-new.css"/>
<!--base-info-->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/base-info.css"/>
<!--left-menu-bar-->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/left-menu-bar.css"/>
<!--ico-->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/icons.css"/>
<!--common-->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/common-new.css"/>
 --%>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/common-new.css"/>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/module-1.css"/>

<script type="text/javascript" src="<%= path %>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%= path %>/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%= path %>/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= path %>/js/bootstrap-select.js"></script>
<script type="text/javascript" src="<%= path %>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js-->
<script type="text/javascript" src="<%= path %>/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%= path %>/js/fnReloadAjax_lw.js"></script>
<script type="text/javascript" src="<%= path %>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= path %>/js/datepicker/jquery-ui.js"></script>
<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%= path %>/js/icheck/icheck.js"></script>
<script type="text/javascript" src="<%= path %>/js/placeholder.js"></script>
<script type="text/javascript" src="<%= path %>/js/underscore.js"></script>
<script type="text/javascript" src="<%= path %>/js/jquery.zclip.min.js"></script>
<!-- 新js -->

<script type="text/javascript">
	var webPath = "<%=path%>";
	var siteCodeParam = "<%=siteCodeParam%>";
	
	var iscost = '${sessionScope.shiroUser.iscost}';
	var isOrgCost = '${sessionScope.shiroUser.isOrgCost}';
	var isScan = '${sessionScope.shiroUser.isScan}';
	var isexp = '${sessionScope.shiroUser.isexp}';
	var orgToInfo = '${sessionScope.shiroUser.orgToInfo}';
	if(orgToInfo == '1'){
		isScan = '${sessionScope.shiroUser.orgToIsScan}';
		isexp = '${sessionScope.shiroUser.orgToIsexp}';
	}
	var isSafetyService = '${sessionScope.shiroUser.isSafetyService}';
	var isOrgSafetyService = '${sessionScope.shiroUser.isOrgSafetyService}';
	var isHasMap = '${sessionScope.shiroUser.isHasMap}';
	$("#tipClose").click(function(){
		$(this).parent(".gl-tooltip").hide();
	});
	
	
	$(function(){
		$("#tipClose").click(function(){
			$(this).parents(".gl-tooltip").hide();	
		});
		
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");

});
</script>
<script type="text/javascript" src="<%= path %>/js/common.js"></script>
