<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <title></title>
    <%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
    <link rel="stylesheet" href="<%=path%>/css/data_error.css"/>
</head>
<body>
<div class="error-content" id="error_box">
    <i class="error-bg"></i>
    <div class="content_box">
        <p class="img_part">
            <img src="<%=path%>/images/nang.jpg" alt=""/>
            <span>出错了！</span>
        </p>
        <div class="txt1">
            数据调用失败，请确认您是否有注册密钥，
        </div>
        <div class="txt2">
            或联系开普云监管平台服务团队: <span class="colo-03a00b">4000-976-005</span>
        </div>
    </div>
</div>
<script>
    window.onload=window.onresize=function(){
        var oError_box=document.getElementById('error_box');
        oH=document.documentElement.clientHeight||document.body.clientHeight;
        oW=document.documentElement.clientWidth||document.body.clientWidth;
        //alert(oH);
        var oError_box_height=oH-43;
        var oError_box_width=oW-50;
        oError_box.style.height=oError_box_height+'px';
        oError_box.style.width=oError_box_width+'px';
    }
</script>
</body>
</html>