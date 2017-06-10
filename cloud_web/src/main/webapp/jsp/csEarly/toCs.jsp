<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<script type="text/javascript" src="<%=path%>/js/early/csEarly.js"></script>
<title>预警测试页面</title>
</head>
<body>
<input type="button" onclick="dailyEarly()" value="发送日报"><br/>

<select id="dailyType">
<option value="1" >首页连不通--当天</option>
<option value="6">严重错别字--当天</option>
<option value="8">首页连不通比例累计超过3%--昨天</option>
<option value="9">严重问题--当天</option>
<option value="10">首页超过10天未更新--昨天</option>
<option value="11">空白栏目超过4个--昨天</option>
<option value="12">基本信息不更新的栏目超过8个--昨天</option>
<option value="13">互动回应栏目长期未回应--昨天</option>
</select>
<input type="button" onclick="sendOrgSingleEarlyInfo()" value="单站预警">
</body>
</html>