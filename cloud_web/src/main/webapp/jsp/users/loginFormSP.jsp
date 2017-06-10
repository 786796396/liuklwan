<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title></title>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/heade.jsp"%>
        <link rel="stylesheet" type="text/css" href="<%= path%>/css/new-login.css" />
	</head>
<script type="text/javascript">
	var siteCode = '${sessionScope.shiroUser.siteCode}';
	if(siteCode!=null&&siteCode!=""){
		if(siteCode.length<=6){
			window.location.href ="https://jg.kaipuyun.cn/cloud_web/connectionHomeDetail_indexOrg.action";
		}else {
			window.location.href ="https://jg.kaipuyun.cn/cloud_web/fillUnit_gailan.action";
		}
	}
</script>
<body>
</body>
</html>
