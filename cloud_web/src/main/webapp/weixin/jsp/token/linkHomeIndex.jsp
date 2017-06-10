<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>首页链接可用性</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>
<body>
<div id="link_home_id">
	<input id="siteCode_id" type="hidden" value="${resultMap.siteCode}">
	<input id="openId_id" type="hidden" value="${resultMap.openId}">
	<input id="level_id" type="hidden" value="${resultMap.level}">
	<input id="menuType_id" type="hidden" value="${resultMap.menuType}">
	<div id="link_home_table_id">
    </div>
  <div id="conn_table_hide" class="zanwsj" style="display: none"></div>
</div>
<%@ include file="/weixin/jsp/footer.jsp"%>
<script type="text/javascript" language="javascript" src="<%=path %>/weixin/js/link/linkHome.js"></script>
</body>

</html>
