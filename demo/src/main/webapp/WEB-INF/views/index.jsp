<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/3
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>Title</title>
    <%@ include file="/WEB-INF/views/common.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/left/nav.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/font/iconfont.css">
    <script type="text/javascript" src="${ctx}/js/left/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/left/nav.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/top/head.css">
    <script type="text/javascript" src="${ctx}/js/top/jquery-1.12.4.min.js"></script>
</head>
<body>

<%-- ===============================TOP START======================================--%>
<div class="header">
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
                    <a href="login.jsp">登录</a>
                </li>
                <li>
                    <img src="${ctx}/img/top/huiyuan1.png" alt="">
                    <a href="login.jsp">新会员注册</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<%-- ===============================TOP END======================================--%>

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
<div style="text-align:center;margin:-650px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
    <p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>
</div>
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
