<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>健康指数排名</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>
<body>
<header class="jiank-header">
	<h3>网站健康指数排名</h3>
	 <div class="zhis-box zhis-box2 mar-r1"><input type="button" id="indexCountSort_return"  value="返回"></a></div>
	 <div class="zhis-box zhis-box2 mar-r2"><input type="button" id="indexCountSort_all"  value="更多 "></a></div>
</header>
<h3 class="tit-h3" id="header_first_id" style="display: none"><i class="i-green"></i>前五名<span class="pull-right">健康指数</span></h3>
<input type="hidden" id="siteCode_id" value="${resultMap.siteCode}">
<input type="hidden" id="level_id" value="${resultMap.level}">
<input type="hidden" id="menuType_id" value="${resultMap.menuType}">
<input type="hidden" id="openId_id" value="${resultMap.openId}">
<div class="container-fluid container-jiank" id="jiank_index_count_first" style="display: none">


</div>
<h3 class="tit-h3" id="header_last_id" style="display: none"><i class="i-red"></i>后五名<span class="pull-right">健康指数</span></h3>
<div class="container-fluid container-jiank mar-bom1" id="jiank_index_count_last" style="display: none">

</div>

<h3 class="tit-h3" id="header_all_id" style="display: none"><i class="i-green"></i>指数排名<span class="pull-right">健康指数</span></h3>
<div class="container-fluid container-jiank mar-bom1" id="jiank_index_count_all" style="display: none">

</div>
<%@ include file="/weixin/jsp/footer.jsp"%>
</body>
<script type="text/javascript" src="<%= path %>/weixin/js/indexCount/indexCountIndex.js"></script>
</html>