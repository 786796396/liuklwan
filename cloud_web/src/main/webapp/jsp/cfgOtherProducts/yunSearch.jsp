<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>云搜索</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
	<!--整合公共样式-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/commonbyb.css"/>
<!--只需引该页面相关的样式，其他样式项目header.jsp 里有-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/other-products-introduce.css"/>
</head>

<body class="bg_f5f5f5">
<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 6 }">
<c:if test="${fn:length(sessionScope.shiroUser.childSiteCode) == 6 }">
 <%@ include file="/common/top.jsp"%>
</c:if>
</c:if>

<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 6 }">
<c:if test="${fn:length(sessionScope.shiroUser.childSiteCode) == 10 }">
 <%@ include file="/common/top_tb.jsp"%>
</c:if>
</c:if>
<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 10 }">
<c:if test="${fn:length(sessionScope.shiroUser.childSiteCode) == 10 }">
 <%@ include file="/common/top_tb.jsp"%>
</c:if>
</c:if>
	<div class="main-detail">
		<div class="main-detail-content">
        <div class="other-products-part">
            <div class="other-products-content">
                <div class="main-title">
                    <h4>中国政府网站群云搜索平台</h4>            
                </div>
                <div class="other-products-block">
                    <div class="row-fluid">
                        <div class="span3">
                            <i class="op-ico jmhf"></i>
                            <h5>界面皮肤随时换</h5>
                            <p>搜索页面自定义设置，滚动播放，轻松匹配网站整体风格。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico zwlmfl"></i>
                            <h5>政务栏目分类搜索</h5>
                            <p>信息公开、办事服务、互动交流、政策文件分类搜索。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico jyhss"></i>
                            <h5>集约化搜索</h5>
                            <p>本级部门及下属单位网站群同意搜索。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico yhssfx"></i>
                            <h5>用户搜索分析</h5>
                            <p>搜索流量、来访地区、搜索词排名、热搜词统计等、了解公众需求，优化网站结构，提升公众满意度。</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span3">
                            <i class="op-ico fzgn"></i>
                            <h5>辅助功能</h5>
                            <p>搜索置顶、只能推荐、敏感词屏蔽、相关推荐搜索词等。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico zzgl"></i>
                            <h5>站长管理</h5>
                            <p>全部功能都由站长管理，操作简单易懂，维护管理更便捷。</p>
                        </div>
                    </div>
					<div id="cfgYeUrl"></div>
                    <div class="login-use com-btns" style="display:none;">
                        <i class="publi-ico_2 touxiang"></i>
                        <span>登录使用</span>
                    </div>
                        
                </div>
            
            </div>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div>
    </div>								
	</div>										
	
	
	
<input type ='hidden' value="${sessionScope.shiroUser.siteCode}" id="shirUserCode">	
<input type ='hidden' value="2" id="yunType">	
<form id="searchformUrl" name="searchformUrl" method="post">
<input name="data" type="hidden" value="" id="dataSearchUrl"/>
</form>
<script type="text/javascript" src="<%= path %>/js/cfgOtherProducts/yunSearch.js"></script>
<script type="text/javascript">
	$('.span3').mouseover(function(){
        $(this).addClass('active');
	});
	$('.span3').mouseout(function(){
	    $(this).removeClass('active');
	});
</script>

</body>
</html>
