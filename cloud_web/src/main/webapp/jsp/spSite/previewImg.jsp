<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
</head>
<body class="control_info-config" style="text-align:center">
<c:if test="${flag ==1 }">
<img alt="" src="<%=path%>/images/customMade/preview-img.jpg">
</c:if>
<c:if test="${flag ==2 }">
<img alt="" src="<%=path%>/images/customMade/preview-img2.jpg">
</c:if>
</body>
</html>
