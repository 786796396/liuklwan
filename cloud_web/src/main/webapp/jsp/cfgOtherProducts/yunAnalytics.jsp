<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>云分析</title>
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
<input type ="hidden" value="${sessionScope.shiroUser.siteCode}">
<input type ="hidden" value="${sessionScope.shiroUser.childSiteCode}">
	<div class="main-detail">
	<div class="main-detail-content">
       <div class="other-products-part">
            <div class="other-products-content">
                <div class="main-title">
                    <h4>云分析是开普云旗下提供 “网站用户行为分析” 的产品</h4>
                </div>
                <div class="other-products-block">
                    <div class="row-fluid">
                        <div class="span3">
                            <i class="op-ico ymtyfx"></i>
                            <h5>页面体验分析</h5>
                            <p>页面热点图、页面区块图、点击量排行、访问量排行。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico lxfx"></i>
                            <h5>流量分析</h5>
                            <p>流量变化诊断、访问量趋势、浏览趋势、访客趋势。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico zqtydb"></i>
                            <h5>站群体验对比</h5>
                            <p>本级部门及下属单位网站群用户体验排行。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico sstyfx"></i>
                            <h5>实时体验分析</h5>
                            <p>来源贡献度、来源网站、来源页面、搜索引擎排行、搜索关键词排行。</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span3">
                            <i class="op-ico fkfx"></i>
                            <h5>访客分析</h5>
                            <p>跳出率、忠诚度、活跃度、留存度、地域分布、轨迹分析。</p>
                        </div>
                        <div class="span3">
                            <i class="op-ico fklyfx"></i>
                            <h5>访问来源分析</h5>
                            <p>来源贡献度、来源网站、来源页面、搜索引擎排行、搜索关键词排行。</p>
                        </div>
                    </div>
                    
					<div id = "cfgYeUrl">
<!-- 						<div  class="apply-btn com-btns" style="display:none;"> -->
<!-- 	                        <i class="publi-ico_2 shoppingcar"></i> -->
<!-- 	                        <span>申请订购</span> -->
<!-- 	                    </div> -->
					</div>
                    
<!--                     <div class="login-use com-btns" style="display:block;"> -->
<!--                         <i class="publi-ico_2 touxiang"></i> -->
<!--                         <span>登录使用</span> -->
<!--                     </div> -->
                </div>
                	
            </div>
           	 <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->	
        </div>
         
    </div>	
      				
	</div>										
	
	
<input type ='hidden' value="" id="openType">	
<input type ='hidden' value="${sessionScope.shiroUser.siteCode}" id="shirUserCode">	
<input type ='hidden' value="3" id="yunType">	
<form id="searchformUrl" name="searchformUrl" method="post">
<input name="data" type="hidden" value="" id="dataSearchUrl"/>
</form>
<script type="text/javascript" src="<%= path %>/js/cfgOtherProducts/yunAnalytics.js"></script>
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
