<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>预警明细</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>
<body>
<input type="hidden" id="siteCode_id" value="${resultMap.siteCode }">
<div id="early_detail_id">
	<div id="early_detail_list_id">
    </div>
  <div id="conn_table_hide" class="zanwsj" style="display: none"></div>
</div>
<!-- <div class="more-font" id="get_more_id">加载更多>></div> -->
<%@ include file="/weixin/jsp/footer.jsp"%>
<script type="text/javascript" language="javascript" src="<%=path %>/weixin/js/earlyDetail/earlyDetailIndex.js"></script>
</body>

</html>
