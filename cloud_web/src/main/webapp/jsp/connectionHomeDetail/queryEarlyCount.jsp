<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${result_map.earlyCount==0}">
	<li class="nav_operater" style="display: none;"><a href="<%=request.getContextPath() %>/early_index.action?siteCode=<%=request.getParameter("siteCode")==null?"":request.getParameter("siteCode")%>"><i class="yj"></i>预警</a></li>
</c:if>
<c:if test="${result_map.earlyCount!=0}">
	<li class="nav_operater operater_red"><a onclick="updateEarlyCount()" href="<%=request.getContextPath() %>/early_index.action?siteCode=<%=request.getParameter("siteCode")==null?"":request.getParameter("siteCode")%>" ><i class="yj"></i>预警
		<b id="early_count">
		<c:if test="${result_map.earlyCount>999}">999+</c:if>
		<c:if test="${result_map.earlyCount<=999}">${result_map.earlyCount}</c:if>
		</b>
		<input id="early_hidden" type="hidden" value="${result_map.earlyCount}"/>
	</a></li>
</c:if>

