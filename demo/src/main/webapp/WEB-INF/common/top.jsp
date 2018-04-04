<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/4
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>Title</title>
    <%--<%@ include file="/WEB-INF/views/common.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/top/head.css">
    <script type="text/javascript" src="${ctx}/js/top/jquery-1.12.4.min.js"></script>
</head>
<body>
<%-- ===============================TOP START======================================--%>
<div class="header">
    <%--<div>--%>
        <%--<strong>I do not know where to go,but I have been on the road. </strong>--%>
    <%--</div>--%>
    <div class="headerinner">
        <ul class="headernav">
            <li class="logo">
                <!---<img src="" alt="" />--->
            </li>
            <li><a href="http://www.16sucai.com">Home</a></li>
            <li><a href="#" >投资咨询</a></li>
            <li><a href="#">培训课程</a></li>
            <li><a href="#" >在线体验</a></li>
            <li><a href="#" >about</a></li>
            <li class="search">
                <a class="search_pic"></a>
            </li>
            <li class="list">
                <a></a>
            </li>
        </ul>
        <!-- 搜索 -->
        <form action="">
            <div class="search_main">
                <button class="search_btn" type="submit"></button>
                <input class="search_text" type="text" placeholder="搜索">
                <span class="close_btn"></span>
            </div>
        </form>
        <!-- 会员登录 -->
        <div class="member">
            <p>会员中心</p>
            <ul>
                <li>
                    <img src="${ctx}/img/top/huiyuan1.png" alt="">
                    <a href="../views/login.jsp">登录</a>
                </li>
                <li>
                    <img src="${ctx}/img/top/huiyuan1.png" alt="">
                    <a href="../views/login.jsp">新会员注册</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<%-- ===============================TOP END======================================--%>
</body>
<script>
    $(function(){
        /*搜索*/
        $(".search_pic").click(function(){
            $(".headernav").fadeOut(500);
            $(".search_main").fadeIn(1000);
        });
        $(".search_main .close_btn").click(function(){
            $(".search_main").fadeOut(500);
            $(".headernav").fadeIn(1000);
        });
        /*登录*/
        $(".list a").click(function(){
            $(".member").slideToggle(500);
        });

    });
</script>
</html>
