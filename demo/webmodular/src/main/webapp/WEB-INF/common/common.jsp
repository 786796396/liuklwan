<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="page"/>
<script type="text/javascript" src="${ctx}/static/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<link rel="icon" href="${ctx}/img/index/lin.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/commomContent.css" />
<script type="text/javascript">
    <%String path = request.getContextPath();%>
    var webPath = "<%=path%>";
</script>