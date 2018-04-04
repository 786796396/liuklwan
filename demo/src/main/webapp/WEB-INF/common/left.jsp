<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>Title</title>
    <%--<%@ include file="/WEB-INF/views/common.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/left/nav.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font/iconfont.css">
    <script type="text/javascript" src="${ctx}/js/left/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/left/nav.js"></script>
</head>
<body>
<%-- ===============================LEFT START======================================--%>
<div class="nav nav-mini">
    <div class="nav-top">
        <div id="mini" style="border-bottom:1px solid rgba(255,255,255,.1)"><img src="${ctx}/img/left/mini.png" ></div>
    </div>
    <ul>
        <li class="nav-item">
            <a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>网站配置</span><i class="my-icon nav-more"></i></a>
            <ul>
                <li><a href="javascript:;"><span>网站设置</span></a></li>
                <li><a href="javascript:;"><span>友情链接</span></a></li>
                <li><a href="javascript:;"><span>分类管理</span></a></li>
                <li><a href="javascript:;"><span>系统日志</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;"><i class="my-icon nav-icon icon_2"></i><span>文章管理</span><i class="my-icon nav-more"></i></a>
            <ul>
                <li><a href="javascript:;"><span>站内新闻</span></a></li>
                <li><a href="javascript:;"><span>站内公告</span></a></li>
                <li><a href="javascript:;"><span>登录日志</span></a></li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>订单管理</span><i class="my-icon nav-more"></i></a>
            <ul>
                <li><a href="javascript:;"><span>订单列表</span></a></li>
                <li><a href="javascript:;"><span>打个酱油</span></a></li>
                <li><a href="javascript:;"><span>也打酱油</span></a></li>
            </ul>
        </li>
    </ul>
</div>
<%-- ===============================LEFT END======================================--%>
</body>
</html>
