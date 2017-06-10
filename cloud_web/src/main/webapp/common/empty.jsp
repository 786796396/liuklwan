<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>升级服务</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
  </head>
  <body>
  <!--头部       satrt  -->
  <%@ include file="/common/top.jsp"%>
  <!--头部       end  -->
<div class="main-container container">
	<div class="row-fluid">
        <!--左侧导航       satrt  -->
        <c:if test="${menuType==3}">
        	<c:set var="tb_index" value="3" scope="request"/>
        	<c:set var="menu" value="#menuWzltx" scope="request"/>
        </c:if>
        <c:if test="${menuType==4}">
        	<c:set var="tb_index" value="4" scope="request"/>
        	<c:set var="menu" value="#menuWzltx" scope="request"/>
        </c:if>
        <c:if test="${menuType==6}">
        	<c:set var="tb_index" value="6" scope="request"/>
        	<c:set var="menu" value="#menuLjkyx" scope="request"/>
        </c:if>
        <c:if test="${menuType==8}">
        	<c:set var="tb_index" value="8" scope="request"/>
        	<c:set var="menu" value="#menuBzwt" scope="request"/>
        </c:if>
        <c:if test="${menuType==9}">
        	<c:set var="tb_index" value="9" scope="request"/>
        	<c:set var="menu" value="#menuBzwt" scope="request"/>
        </c:if>
        <c:if test="${menuType==10}">
        	<c:set var="tb_index" value="10" scope="request"/>
        	<c:set var="menu" value="#menuBzwt" scope="request"/>
        </c:if>
        <c:if test="${menuType==11}">
        	<c:set var="tb_index" value="11" scope="request"/>
        	<c:set var="menu" value="#menuBzwt" scope="request"/>
        </c:if>
        <c:if test="${menuType==16}">
        	<c:set var="tb_index" value="16" scope="request"/>
        	<c:set var="menu" value="#menuNrgx" scope="request"/>
        </c:if>
        <c:if test="${menuType==17}">
        	<c:set var="tb_index" value="17" scope="request"/>
        	<c:set var="menu" value="#menuNrgx" scope="request"/>
        </c:if>
        <%@ include file="/common/leftmenu_tb.jsp"%>
        <!--左侧导航       end  -->
        <div class="page-content">
            <%@ include file="/common/empty-new.jsp"%>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

</body>
</html>
