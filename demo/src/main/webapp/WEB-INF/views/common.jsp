<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="page"/>
<script type="text/javascript">
    <%String path = request.getContextPath();%>
    var webPath = "<%=path%>";
</script>