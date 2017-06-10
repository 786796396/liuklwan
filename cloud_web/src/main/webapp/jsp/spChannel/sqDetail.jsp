<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String siteCodeParam = request.getParameter("siteCode")==null?"":request.getParameter("siteCode");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
 <link rel="stylesheet"  type="text/css" href="<%=path%>/css/base.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/customMade/customMade.css" />
    <link rel="stylesheet"  type="text/css" href="<%=path%>/css/jquery.mCustomScrollbar.css" />
    <script type="text/javascript" src="<%= path %>/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%= path %>/js/customMade/CustomMade.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js--> 
    <script language="javascript" type="text/javascript"
	src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
<title>个性化定制页面--文章详情页</title>
</head>
<body>
<input type="hidden" id="articleId" value="${id}">
<input type="hidden" id="siteCode" value="${siteCode}">
<div class="container">
<div class="page-head">
    <div class="head-content clearfix">
        <div class="tit-part fl">
            <!-- <img src="../images/NationalEmblem.png" alt="" class="fl"/>
             <h4 class="fl">广东省政府网站监管平台</h4>-->
            <img src="" alt="" id="logo"/>
        </div>
        <!-- <div class="date fr">
            数据更新：2016-11-21 00:00:00
        </div> -->
    </div>
</div>
<div class="main-content clearfix">
    <div class="top-bread_crumbs">
        <span>首页</span>
        <i> > </i>
        <span id="channelName"></span>
    </div>
    <div class="article-box">
        <div class="article-content">
            <div class="article-top">
                <h3 id="title"></h3>
                <p class="c_line">
                    <span id="sourceName"></span>
                    <span id="sourceUrl"></span>
                    <span id="date"></span>
                </p>
                <!--<p class="b_line">
                    <span>【字体大小：大 中 小】</span>
                    <span><i></i>打印</span>
                </p>-->
            </div>
            <div class="article-body" id="content">
                
            </div>
        </div>
    </div>
</div>
<div class="page-footer">
<p id="bottomText"> 版权所有：开普互联提供网站技术支持</p>
</div>
</div>
<script language="javascript" type="text/javascript" src="<%=path%>/js/spChannel/sqDetail.js"></script>

 <script>
 var webPath = "<%=path%>"; 
 $(window).load(function(){
	    $(".content").mCustomScrollbar({
         autoHideScrollbar:true,
         // setHeight:340,
         //theme:"minimal-dark",
           theme:"light-3",
          //theme:"3d";
         autoDraggerLength:false

     });
	});
</script>
</body>
</html>
