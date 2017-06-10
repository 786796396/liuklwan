<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>监测结果</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
  </head>
  <body>
  <!--头部       satrt  -->
  <%@ include file="/common/top_tb.jsp"%>
  <!--头部       end  -->
	<div class="main-container container">
	<input id="siteCode_id" type="hidden" value="${siteCode}">
	<input id="menuNum_id" type="hidden" value="${menuNum}">
	<input id="channelIds_id" type="hidden" value="${channelIds}">
	<input id="isHomePage_id" type="hidden" value="${isHomePage}">
	<div class="row-fluid">
	<!--左侧导航       satrt  -->
	<c:set var="tb_index" value="${menuNum}" scope="request"/>
	<c:set var="menu" value="${menuValue}" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	
	<!--左侧导航       end  -->
         <div class="page-content">
    		<div class="row" id="tj_all_title_id">
            </div>
            <div class="row" id="tj_all_body_id">
            </div><!-- /row3 -->
          
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<script language="javascript" type="text/javascript" src="<%=path %>/js/jumpPage/jumpPage.js"></script>
</body>
</html>
