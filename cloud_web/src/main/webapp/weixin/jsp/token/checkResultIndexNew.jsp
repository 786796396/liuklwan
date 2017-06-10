<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>${resultMap.siteName}</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>

<body style="background:#fbfbfb;">
<header class="header-nav" id="org_header_id">
  <h2 class="nav-titcon">全部</h2>
  <a id="jumbo_menu_button_id" class="group-nav jumbo-menu-button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  	<i class="jumbo-menu-button-middle"></i>
  </a>
  <ul class="dropdown-menu" id="bumen_id">
	<!-- 部门展现 -->
  </ul>
</header>
<!-- 组织单位 -->
<div class="tab-content" id="org_content_id">
	<div class="result-num-body">
        <div class="result-num" id="index_count_id">2105.92</div>
        <div class="chart-tit">健康指数领先全国  <span class="color-3aaf66" id="gtPercent_id">${resultMap.gtPercent}%</span> 的政府网站</div>
        <a class="btn-a" href="${resultMap.hrefLink}" id="index_count_href_id">健康排名</a>
    </div>
        

    <div class="tab-pane active" id="all">

        <div class="jianc-result-body row">
        	<input type="hidden" id="siteCode_id" value="${resultMap.siteCode}">
        	<input type="hidden" id="level_id" value="${resultMap.level}">
        	<input type="hidden" id="zhesuan_id" value="${resultMap.zhesuan}">
        	<input type="hidden" id="totalSum_id" value="${resultMap.totalSum}">
        	<div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path%>/weixin/images/jianc/icon1.png"/></div>
                <div class="jianc-result-tit pull-left">网站连不通<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="all_connNum"><span>${resultMap.connNum}</span>次</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="首页不可用链接" src="<%=path%>/weixin/images/jianc/icon2.png"/></div>
                <div class="jianc-result-tit pull-left">首页不可用链接<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="all_linkNum"><span>${resultMap.linkNum}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="内容保障问题" src="<%=path%>/weixin/images/jianc/icon3.png"/></div>
                <div class="jianc-result-tit pull-left">内容保障问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="all_contGuaranteNum"><span>${resultMap.contGuaranteNum}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="严重错别字" src="<%=path%>/weixin/images/jianc/icon4.png"/></div>
                <div class="jianc-result-tit pull-left">严重错别字<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="all_contCorrectNum"><span>${resultMap.contCorrectNum}</span>条</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="安全问题" src="<%=path%>/weixin/images/jianc/icon5.png"/></div>
                <div class="jianc-result-tit pull-left">安全问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="all_websiteSafe"><span>${resultMap.websiteSafe}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="新稿件" src="<%=path%>/weixin/images/jianc/icon6.png"/></div>
                <div class="jianc-result-tit pull-left">新稿件<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="all_contUpdate"><span>${resultMap.contUpdate}</span>条</div>
            </div>
        </div>
    </div><!--**********************/all************************-->
    <div class="tab-pane" id="current">
<%--     	<h2 class="tit-h2">站群监测结果</h2>
        <div class="chart-box">
        	<div class="chart-body">
            	<img src="<%=path%>/weixin/images/example/chart.png"/>
            </div>
        </div>
        <div class="chart-tit">健康指数领先全国 <span class="color-3aaf66">93%</span> 的政府网站</div> --%>
        <div class="jianc-result-body row">
        	<div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path%>/weixin/images/jianc/icon1.png"/></div>
                <div class="jianc-result-tit pull-left">网站连不通<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="current_connNum"><span>${resultMap.connNum}</span>次</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="首页不可用链接" src="<%=path%>/weixin/images/jianc/icon2.png"/></div>
                <div class="jianc-result-tit pull-left">首页不可用链接<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="current_linkNum"><span>${resultMap.linkNum}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="内容保障问题" src="<%=path%>/weixin/images/jianc/icon3.png"/></div>
                <div class="jianc-result-tit pull-left">内容保障问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="current_contGuaranteNum"><span>${resultMap.contGuaranteNum}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="严重错别字" src="<%=path%>/weixin/images/jianc/icon4.png"/></div>
                <div class="jianc-result-tit pull-left">严重错别字<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="current_contCorrectNum"><span>${resultMap.contCorrectNum}</span>条</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="安全问题" src="<%=path%>/weixin/images/jianc/icon5.png"/></div>
                <div class="jianc-result-tit pull-left">安全问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="current_websiteSafe"><span>${resultMap.websiteSafe}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path%>/weixin/images/jianc/icon6.png"/></div>
                <div class="jianc-result-tit pull-left">新稿件<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="current_contUpdate"><span>${resultMap.contUpdate}</span>条</div>
            </div>
        </div>
    </div><!--**********************/current************************-->
    <div class="tab-pane" id="province">
<%--     	<h2 class="tit-h2">站群监测结果</h2>
        <div class="chart-box">
        	<div class="chart-body">
            	<img src="<%=path%>/weixin/images/example/chart.png"/>
            </div>
        </div>
        <div class="chart-tit">健康指数领先全国 <span class="color-3aaf66">93%</span> 的政府网站</div> --%>
        <div class="jianc-result-body row">
        	<div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path%>/weixin/images/jianc/icon1.png"/></div>
                <div class="jianc-result-tit pull-left">网站连不通<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="province_connNum"><span>${resultMap.connNum}</span>次</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="首页不可用链接" src="<%=path%>/weixin/images/jianc/icon2.png"/></div>
                <div class="jianc-result-tit pull-left">首页不可用链接<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="province_linkNum"><span>${resultMap.linkNum}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="内容保障问题" src="<%=path%>/weixin/images/jianc/icon3.png"/></div>
                <div class="jianc-result-tit pull-left">内容保障问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="province_contGuaranteNum"><span>${resultMap.contGuaranteNum}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="严重错别字" src="<%=path%>/weixin/images/jianc/icon4.png"/></div>
                <div class="jianc-result-tit pull-left">严重错别字<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="province_contCorrectNum"><span>${resultMap.contCorrectNum}</span>条</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="安全问题" src="<%=path%>/weixin/images/jianc/icon5.png"/></div>
                <div class="jianc-result-tit pull-left">安全问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="province_websiteSafe"><span>${resultMap.websiteSafe}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="新稿件" src="<%=path%>/weixin/images/jianc/icon6.png"/></div>
                <div class="jianc-result-tit pull-left">新稿件<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="province_contUpdate"><span>${resultMap.contUpdate}</span>条</div>
            </div>
        </div>
    </div><!--**********************/province************************-->
    <div class="tab-pane" id="city">
<%--     	<h2 class="tit-h2">站群监测结果</h2>
        <div class="chart-box">
        	<div class="chart-body">
            	<img src="<%=path%>/weixin/images/example/chart.png"/>
            </div>
        </div>
        <div class="chart-tit">健康指数领先全国 <span class="color-3aaf66">93%</span> 的政府网站</div> --%>
        <div class="jianc-result-body row">
        	<div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path%>/weixin/images/jianc/icon1.png"/></div>
                <div class="jianc-result-tit pull-left">网站连不通<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="city_connNum"><span>${resultMap.connNum}</span>次</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="首页不可用链接" src="<%=path%>/weixin/images/jianc/icon2.png"/></div>
                <div class="jianc-result-tit pull-left">首页不可用链接<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="city_linkNum"><span>${resultMap.linkNum}</span>个</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="内容保障问题" src="<%=path%>/weixin/images/jianc/icon3.png"/></div>
                <div class="jianc-result-tit pull-left">内容保障问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="city_contGuaranteNum"><span>${resultMap.contGuaranteNum}</span>个</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="严重错别字" src="<%=path%>/weixin/images/jianc/icon4.png"/></div>
                <div class="jianc-result-tit pull-left">严重错别字<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="city_contCorrectNum"><span>${resultMap.contCorrectNum}</span>条</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="安全问题" src="<%=path%>/weixin/images/jianc/icon5.png"/></div>
                <div class="jianc-result-tit pull-left">安全问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="city_websiteSafe"><span>${resultMap.websiteSafe}</span>个</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="新稿件" src="<%=path%>/weixin/images/jianc/icon6.png"/></div>
                <div class="jianc-result-tit pull-left">新稿件<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="ciry_contUpdate"><span>${resultMap.contUpdate}</span>条</div>
                
            </div>
        </div>
    </div><!--**********************/city************************-->
     <div class="tab-pane" id="area">
<%--     	<h2 class="tit-h2">站群监测结果</h2>
        <div class="chart-box">
        	<div class="chart-body">
            	<img src="<%=path%>/weixin/images/example/chart.png"/>
            </div>
        </div>
        <div class="chart-tit">健康指数领先全国 <span class="color-3aaf66">93%</span> 的政府网站</div> --%>
        <div class="jianc-result-body row">
        	<div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path%>/weixin/images/jianc/icon1.png"/></div>
                <div class="jianc-result-tit pull-left">网站连不通<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="area_connNum"><span>${resultMap.connNum}</span>次</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="首页不可用链接" src="<%=path%>/weixin/images/jianc/icon2.png"/></div>
                <div class="jianc-result-tit pull-left">首页不可用链接<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="area_linkNum"><span>${resultMap.linkNum}</span>个</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="内容保障问题" src="<%=path%>/weixin/images/jianc/icon3.png"/></div>
                <div class="jianc-result-tit pull-left">内容保障问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="area_contGuaranteNum"><span>${resultMap.contGuaranteNum}</span>个</div>
                
            </div>
            <div class="jianc-result-tab row">
            
             <a class="bg-9f00a7" href="<%=path %>/currentMonitoringResults_index.action?menuI=3&menuType=">
            	<div class="jianc-result-img pull-left"><img alt="严重错别字" src="<%=path%>/weixin/images/jianc/icon4.png"/></div>
                <div class="jianc-result-tit pull-left">严重错别字<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="area_contCorrectNum"><span>${resultMap.contCorrectNum}</span>条</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="安全问题" src="<%=path%>/weixin/images/jianc/icon5.png"/></div>
                <div class="jianc-result-tit pull-left">安全问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="area_websiteSafe"><span>${resultMap.websiteSafe}</span>个</div>
                
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="新稿件" src="<%=path%>/weixin/images/jianc/icon6.png"/></div>
                <div class="jianc-result-tit pull-left">新稿件<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right" id="area_contUpdate"><span>${resultMap.contUpdate}</span>条</div>
                
            </div>
        </div>
    </div><!--**********************/area************************-->
     
</div>
<!-- 填报单位 -->
<div class="tab-content tianb-tab-content" id="tb_content_id">
	<h2 class="tit-h2">健康指数</h2>
    <div class="tab-pane active">
        <div class="chart-box">
        	<div class="chart-body">
            	<canvas width="224" height="224" id="chart-gauge_id"></canvas>
                <div class="chart-info">
                    <div class="chart-num" id="index_count_tb_id"></div>
                </div>
                <div class="chart-info-left">差</div>
                <div class="chart-info-right">优</div>
            </div>
        </div>
        <div class="chart-tit">健康指数领先全国 <span class="color-3aaf66">${resultMap.gtPercent}%</span> 的政府网站</div>
        <div class="jianc-result-body row">
        	<div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="网站连不通" src="<%=path %>/weixin/images/jianc/icon1.png"/></div>
                <div class="jianc-result-tit pull-left">网站连不通<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right"><span>${resultMap.connNumTB}</span>次</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="首页不可用链接" src="<%=path %>/weixin/images/jianc/icon2.png"/></div>
                <div class="jianc-result-tit pull-left">首页不可用链接<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right"><span>${resultMap.linkNumTB}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="内容保障问题" src="<%=path %>/weixin/images/jianc/icon3.png"/></div>
                <div class="jianc-result-tit pull-left">内容保障问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right"><span>${resultMap.contGuaranteNumTB}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="严重错别字" src="<%=path %>/weixin/images/jianc/icon4.png"/></div>
                <div class="jianc-result-tit pull-left">严重错别字<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right"><span>${resultMap.contCorrectNumTB}</span>条</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="安全问题" src="<%=path %>/weixin/images/jianc/icon5.png"/></div>
                <div class="jianc-result-tit pull-left">安全问题<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right"><span>${resultMap.websiteSafeTB}</span>个</div>
            </div>
            <div class="jianc-result-tab row">
            	<div class="jianc-result-img pull-left"><img alt="新稿件" src="<%=path %>/weixin/images/jianc/icon6.png"/></div>
                <div class="jianc-result-tit pull-left">新稿件<span>(昨天)</span></div>
                <div class="jianc-result-num pull-right"><span>${resultMap.contUpdateTB}</span>条</div>
            </div>
        </div>
    </div><!--**********************/all************************-->
</div>

<%@ include file="/weixin/jsp/footer.jsp"%>
<script language="javascript" type="text/javascript" src="<%= path %>/weixin/js/checkResult/checkResult.js"></script>

</body>
</html>
