<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>组织单位-首页</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%= path %>/css/dropload.css" />
	
<link rel="stylesheet" href="<%= path %>/css/jquery.pagewalkthrough.css">

<script language="javascript" type="text/javascript"
	src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
<script language="javascript" type="text/javascript"
	src="<%= request.getContextPath() %>/js/gauge.js"></script>
<script language="javascript" type="text/javascript"
	src="<%= request.getContextPath() %>/js/connection/con_home_sort.js"></script>
<!-- 您页面原有的代码 -->
		<script>
		//退出时清除cookie里的值
	//组织
	$.removeCookie('type_cookie');   
	$.removeCookie('top_cookie');   // 一级菜单  头部
	$.removeCookie('twoMenu_cookie');  // 二级菜单  
	//填报
	$.removeCookie('typeTB_cookie');
	$.removeCookie('topTB_cookie');  // 一级菜单  头部
	$.removeCookie('twoMenuTB_cookie'); // 二级菜单  
	
	$.cookie('type_cookie', "1", {path:'/'});
	$.cookie('typeTB_cookie', "1", {path:'/'});
			var _trackData = _trackData || [];                
			_trackData.push(["userset","userid", '${sessionScope.shiroUser.siteCode}']);
			_trackData.push(["userset", "username", '${sessionScope.shiroUser.userName}']);
			_trackData.push(["userset", 'loginregist', 'login']);  
		</script>
<style type="text/css">
#jpwNext{ position: absolute; bottom: 27px; left: 60px; opacity: 0; width:120px; height:60px;}
#jpwPrevious{display: none;}
#jpwClose{ display: block;}
.overlay-hole{ padding:13px;}
.first-steps i{position:absolute; top:10px; right:91px;display:inline-block; width:24px; height:20px; cursor:pointer;background:#fff; opacity:0; filter:alpha(opacity=0);}

</style>
</head>
<body>
	<!--头部       satrt  -->
	<%@ include file="/common/top.jsp"%>
	<!--头部       end  -->
	<div class="main-container container">
		<div class="row-fluid">
			<!--左侧导航       satrt  -->
			<c:set var="ba_index" value="1" scope="request" />
			<c:set var="menu" value="#" scope="request" />
			<%@ include file="/common/leftmenu.jsp"%>
			<!--左侧导航       end  -->
			<div class="page-content">
				<c:if test='${sessionScope.shiroUser.siteCode eq "bm0100"}'>
					<h3 class="data-h3">国务院政府网网站健康指数</h3>
				</c:if>
				<c:if test='${sessionScope.shiroUser.siteCode ne "bm0100"}'>
					<h3 class="data-h3">${sessionScope.shiroUser.userName }网站健康指数</h3>
				</c:if>
				<div class="row">
					<div class="z_box1 span4">
						<div class="z_box_header">
							<div class="star-msg">
								<div class="star-msg-main wen-con">
									<p>本系统对全国政府网站每日实时监测，通过大数据分析得出全国政府网站的健康指数，作为一个针对全国政府网站运行状态的参考。</p>
									<i class="angle1"></i>
								</div>
								<div class="star-box icon-wen">
									<i class="i-wen">?</i>
								</div>
							</div>
							<i class="zhi-s-i" style="display: none;"></i> <i id="sum_style"
								class="zhis-i" style="display: none;"></i>
						</div>

						<div class="chart-box">
							<div class="chart-body">
								<canvas width="224" height="224" id="chart-gauge"></canvas>
								<div class="chart-info">
									<h3 class="zhi_s">健康指数</h3>
									<!--/z_box_header-->
									<div class="num1" id="health_index"></div>
									<div class="num1_msg"></div>
								</div>
							</div>
						</div>


						<div class="num2" id="index_ico">
							<span class="font1">相比昨天</span><i></i><span id="totalSumNumber">0&nbsp;&nbsp;(0%)</span>
						</div>
					</div>
					<div class="z_box2 span8">
						<div class="row">
						</div>
						<div class="line_chart" id="index_line"
							style="height: 270px; width: 100%; margin-top: 10px"></div>
					</div>
				</div>
				<!-- /row1 -->
				<h3 class="data-h3">当前监测结果(昨天)</h3>
	            <div class="row">
	            	<div class="databox span2">
	                	<!-- <a class="red-num" href="yuj.html"><i class="left-redbg"></i><span>8</span><i class="right-redbg"></i></a> -->
	                    <div class="bg-603cba" href="dangqianjianc.html">
	                        <span class="num1"><span id="connId"></span>次</span>
	                        <span class="databox-bom">网站连通性</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/connectionHomeDetail_webSiteConnected.action')" href="<%=path %>/connectionHomeDetail_webSiteConnected.action"><span class="fl">首页不连通</span><span class="fr" id="coHomeId"></span></a></li>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/connectionChannelDetail_indexOrg.action')" href="<%=path%>/connectionChannelDetail_indexOrg.action?col=0"><span class="fl">栏目不连通</span><span class="fr" id="coChannelId"></span></a></li>
	                            <li class="clearfix everyline last-li"><a onclick="jumpPage('<%=path %>/connectionChannelDetail_indexOrg.action')" href="<%=path%>/connectionChannelDetail_indexOrg.action?col=1"><span class="fl">业务系统不连通</span><span class="fr" id="coBusinessId"></span></a></li>
	                        </ul>
	                    </div>
	                    <i class="bdt-ddd3f4 bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
	                </div>
	                <div class="databox span2">
	                    <div class="bg-eb3c00" href="dangqianjianc.html">
	                        <span class="num1"><span id="linkId"></span>个</span>
	                        <span class="databox-bom">不可用链接</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/linkHome_linkHomeIndexOrg.action')" href="<%=path%>/linkHome_linkHomeIndexOrg.action"><span class="fl">首页</span><span class="fr" id="lkHomeId"></span></a></li>
  	                            <li class="clearfix everyline last-li"><a onclick="jumpPage('<%=path %>/depthLinkAll_depthLinkAllMain.action')" href="<%=path%>/depthLinkAll_depthLinkAllMain.action"><span class="fl">全站</span><span class="fr" id="linkAllSum"></span></a></li>
	                        </ul>
	                    </div>
	                    <i class="bdt-f6d8cd bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
	                </div>
	                <div class="databox span2">
<!-- 	                	<a class="red-num" href="yuj.html"><i class="left-redbg"></i><span>888</span><i class="right-redbg"></i></a>
 -->	                    <div class="bg-ff9700" href="dangqianjianc.html">
	                        <span class="num1"><span id="contguaranteId"></span>个</span>
	                        <span class="databox-bom">内容保障问题</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/securityHomeChannel_indexOrg.action')" href="<%=path%>/securityHomeChannel_indexOrg.action"><span class="fl">首页不更新</span><span class="fr" id="seHomeId"></span></a></li>
	                        	<!-- <li class="clearfix everyline last-li" onclick="sChange();"><a id="channelTitle" href="javascript:void(0);"><span class="fl">栏目不更新</span><span class="fr" id="seChannelId"></span></a></li> -->
	                       		  <li class="clearfix everyline last-li">
                                <!--<a href="javascript:void(0);">-->
                                    <span class="fl cur-p" id="cur-p">栏目不更新
                                        <i class="down_7-4 ico"></i>
                                        <i class="second-box">
                                            <p class="">
                                                <a id="basicTitle" onclick="jumpPage('<%=path %>/securityGuarantee_securityGuaranteeMain.action')" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=0" class="clearfix"><span class="fl">基本信息</span> <span class="fr" id="sBasic"></span></a>
                                            </p>
                                            <p>
                                                <a id="blankTitle" onclick="jumpPage('<%=path %>/securityGuarantee_securityGuaranteeMain.action')" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=1" class="clearfix"><span class="fl">空白栏目</span> <span class="fr" id="sBlank"></span></a>
                                            </p>
                                            <p>
                                                <a id="responseTitle" onclick="jumpPage('<%=path %>/securityGuarantee_securityGuaranteeMain.action')" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=2" class="clearfix"><span class="fl">互动回应差</span> <span class="fr" id="sResponse"></span></a>
                                            </p>
                                            <p>
                                                <a id="serviceTitle" onclick="jumpPage('<%=path %>/securityGuarantee_securityGuaranteeMain.action')" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=3" class="clearfix"><span class="fl">服务不实用</span> <span class="fr" id="sService"></span></a>
                                            </p>
                                        </i>
                                    </span>
                                    <span class="fr" id="seChannelId"></span>
                               <!-- </a>-->
                            </li>
	                        </ul>
	                        
	                       <%--  <div id="sDiv" style="display: none;">
	                       			<li class="clearfix everyline"><a id="basicTitle" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=0"><span class="fl">基本信息</span><span class="fr" id="sBasic"></span></a></li>
	                       			<li class="clearfix everyline"><a id="blankTitle" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=1"><span class="fl">空白栏目</span><span class="fr" id="sBlank"></span></a></li>
	                       			<li class="clearfix everyline"><a id="responseTitle" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=2"><span class="fl">互动回应</span><span class="fr" id="sResponse"></span></a></li>
	                       			<li class="clearfix everyline"><a id="serviceTitle" href="<%=path%>/securityGuarantee_securityGuaranteeMain.action?typeId=3"><span class="fl">服务不实用</span><span class="fr" id="sService"></span></a></li>
	                        </div> --%>
	                    </div>
	                    <i class="bdt-f7ddb6 bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
	                </div>
	                
	                <div class="databox span2">
	                	 <c:if test="${sessionScope.shiroUser.isOrgCost==0}"> 
		                    <div class="bg-9f00a7" href="dangqianjianc.html">
		                        <span class="num1"><span>未开通</span></span>
		                        <span class="databox-bom">疑似错别字</span>
		                    </div>
		                    <!--色块更改部分s-->
		                    <div class="colo-block">
		                        <ul>
		                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/correctContent_indexOrg.action')" href="<%=path%>/correctContent_indexOrg.action"><span class="fl">疑似错别字</span><span class="fr">未监测</span></a></li>
		                        </ul>
		                    </div>
		                    <i class="bdt-e5b9e7 bdt">
		
		                    </i>
		                    <!--色块更改部分e-->
	                    </c:if>
	                	<c:if test="${sessionScope.shiroUser.isOrgCost==1}"> 
		                    <div class="bg-9f00a7" href="dangqianjianc.html">
		                        <span class="num1"><span id="contcorrect"></span>个</span>
		                        <span class="databox-bom">疑似错别字</span>
		                    </div>
		                    <!--色块更改部分s-->
		                    <div class="colo-block">
		                        <ul>
		                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/correctContent_indexOrg.action')" href="<%=path%>/correctContent_indexOrg.action"><span class="fl">疑似错别字</span><span class="fr" id="contcorrectId"></span></a></li>
		                        </ul>
		                    </div>
		                    <i class="bdt-e5b9e7 bdt">
		
		                    </i>
		                    <!--色块更改部分e-->
	                    </c:if>
	                </div>
	                <div class="databox span2">
	                    <div class="bg-009788" href="dangqianjianc.html">
	                        <span class="num1"><span id="contenUpdateId"></span>条</span>
	                        <span class="databox-bom">内容更新量</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/updateHome_updateHomeIndexOrg.action')" href="<%=path%>/updateHome_updateHomeIndexOrg.action"><span class="fl">首页更新</span><span class="fr" id="upHomeId"></span></a></li>
	                            <li class="clearfix everyline last-li"><a onclick="jumpPage('<%=path %>/connectionChannelDetail_indexOrg.action')" href="<%=path%>/connectionChannelDetail_indexOrg.action?col=2"><span class="fl">栏目更新</span><span class="fr" id="upChannelId"></span></a></li>
	                        </ul>
	                    </div>
	                    <i class="bdt-8fdcd4 bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
	                </div>
					<c:if test="${sessionScope.shiroUser.isOrgSafetyService!=0}">
						<div class="databox span2">
							<div class="bg-607d8b" href="dangqianjianc.html">
								<span class="num1"><span id="quesSum"></span>个</span> 
								<span class="databox-bom">网站安全</span>
							</div>
							<!--色块更改部分s-->
							<div class="colo-block">
								<ul>
		                            <li class="clearfix everyline"><a onclick="changeCookie(1,'<%=path %>/monitorSilentResultOrg_indexOrg.action','null','<%=path %>/monitorSilentResultOrg_indexOrg.action');" href="<%=path%>/monitorSilentResultOrg_indexOrg.action"><span class="fl">问题数</span><span class="fr" id="quesSumId"></span></a></li>
		                            <li class="clearfix everyline last-li"><a onclick="changeCookie(1,'<%=path %>/monitorSilentResultOrg_indexOrg.action','null','<%=path %>/monitorSilentResultOrg_indexOrg.action');" href="<%=path%>/monitorSilentResultOrg_indexOrg.action"><span class="fl">风险值</span><span class="fr" id="riskNum"></span></a></li>
		                        </ul>
							</div>
							<i class="bdt-c4dde9 bdt"> </i>
							<!--色块更改部分e-->
						</div>
					</c:if>
					<c:if test="${sessionScope.shiroUser.isOrgSafetyService==0}">
						<div class="databox span2">
							<div class="bg-607d8b" href="dangqianjianc.html">
								<span class="num1"><span>未开通</span></span> <span
									class="databox-bom">网站安全</span>
							</div>
							<!--色块更改部分s-->
							<div class="colo-block">
								<a onclick="changeCookie(1,'<%=path %>/monitorSilentResultOrg_indexOrg.action','null','<%=path %>/monitorSilentResultOrg_indexOrg.action');" href="<%=path%>/monitorSilentResultOrg_indexOrg.action"><p>网站安全能为您做什么？</p></a>
							</div>
							<i class="bdt-c4dde9 bdt"> </i>
							<!--色块更改部分e-->
						</div>
					</c:if>
				</div>
				<!-- /row2 -->
				<div class="row">
					<div class="line_chart_box">
						<div class="chart-header row">
							<h3 class="h3-brand">健康分析</h3>
							<ul id="navPills" class="nav nav-pills pull-right">
								<li class="active"><a onclick="websiteConnection()"
									href="#ltxContent" data-toggle="tab">网站连通性</a><i></i></li>
								<li><a onclick="link_keyong()" href="#kyxContent"
									data-toggle="tab">链接可用性</a><i></i></li>
								<li><a id="saveQuestion" onclick="saveQuestion()"
									href="#bzwtContent" data-toggle="tab">内容保障问题</a><i></i></li>
								<li><a onclick="content_corrent_line()" href="#zqxContent"
									data-toggle="tab">疑似错别字</a><i></i></li>
								<!-- <li><a href="#aqContent" data-toggle="tab">网站安全</a><i></i></li> -->
								<li><a onclick="content_update_bar()" href="#gxContent"
									data-toggle="tab">内容更新</a><i></i></li>
								<c:if test="${sessionScope.shiroUser.isOrgSafetyService!=0}">
									<li><a onclick="siteQue()" href="#sqContent"
										data-toggle="tab">网站安全</a><i></i></li>
								</c:if>		
							</ul>
						</div>
						<!-- /chart-header -->
						<div class="tab-content">
							<div id="ltxContent"
								class="chart-content chart-content-ltx active">
								<div class="chart_msg row">
									<div id="website_bar" style="height: 300px; width: 100%;"></div>
								</div>
							</div>
							<!-- /chart-content-ltx -->
							<div id="kyxContent" class="chart-content chart-content-kyx">
								<div class="chart_msg row">
									<ul class="chart-link">
										<li><input name="zuzhi_link" type="radio" id="link_Home"
											checked="checked" />首页不可用链接</li>
										<li><input name="zuzhi_link" type="radio" id="link_All" />全站不可用链接</li>
									</ul>
								</div>
								<div class="">
									<div id="link_Home_div" class="jiank_chart_con"
										style="height: 260px; width: 100%"></div>
									<div id="link_All_div" class="jiank_chart_con"
										style="height: 260px; width: 100%" ; display:none;></div>

								</div>
							</div>
							<!-- /chart-content-kyx -->
							<div id="bzwtContent" style="height: 300px; width: 100%;"
								class="chart-content chart-content-bzwt"></div>
							<!-- /chart-content-bzwt -->

							<div id="zqxContent" style="height: 300px; width: 100%;"
								class="chart-content chart-content-zqx"></div>
							<!-- /chart-content-zqx -->
							<div id="aqContent" class="chart-content chart-content-aq">
								<div class="chart_msg row">
									<h4>不可用链接数 （个）</h4>
									<ul class="chart-link">
										<li><input type="radio" />全站不可用链接</li>
										<li><input type="radio" />首页不可用链接</li>
									</ul>
								</div>
								<div class="jiank_chart">
									<img src="<%=path%>/images/zanyong/2.jpg">
								</div>
							</div>
							<!-- /chart-content-aq -->
							<div id="gxContent" class="chart-content chart-content-gx">
								<div id="content_update_bar" style="height: 300px; width: 100%"></div>
							</div>
							<div id="sqContent" class="chart-content chart-content-sq">
								<div id="siteQue" style="height: 300px; width: 100%"></div>
							</div>
							<!-- /chart-content-gx -->
						</div>
						<!-- /tab-content -->
					</div>

				</div>
				<!-- /row3 -->
				<div class="row">
					<div class="tab-header-fixed-box">
						<div id="tabHeaderfixed">
							<div class="tab_header row">
								<h3>
									监测结果概览&nbsp;<span>（不包含关停例外）</span>
								</h3>
								<div class="input-prepend">
									<span class="add-on"><img alt="search"
										src="<%=path%>/images/common/search.png" /></span> <input
										id="searchInfo" class="span2 prependedInput" type="text"
										placeholder="输入网站名称...">
								</div>
								<div id="indexOrgExcel"></div>
							</div>
							<div class="tab_box1 pad-b row">
								<table id="index_org_tableSort" cellpadding="0" cellspacing="0"
									class="table">
									<tr>
										<th class="th_left" style="text-align: left; width: 80px;">网站标识码</th>
										<th class="th_left" style="text-align: left;">网站名称</th>
										<th style="width: 80px;">健康指数<i class="tab_angle"></i></th>
										<th style="width: 105px;">首页不连通
											<div class="fonts_12">（次）</div>
											<i class="tab_angle"></i>
										</th>
										<th style="width: 135px;">首页不可用链接
											<div class="fonts_12">（个）</div>
											<i class="tab_angle"></i>
										</th>
										<th style="width: 115px;">首页更新情况
											<div class="fonts_12"></div>
											<i class="tab_angle"></i>
										</th>
										<th style="width: 90px;">疑似错别字
											<div class="fonts_12">（个）</div>
											<i class="tab_angle"></i>
										</th>
										<th style="width: 80px;">安全问题
											<div class="fonts_12">（个）</div>
											<i class="tab_angle"></i>
										</th>
										<th style="width: 80px;">新稿件
											<div class="fonts_12">（条）</div>
											<i class="tab_angle"></i>
										</th>
									</tr>
								</table>
							</div>
						</div>
					</div>
					<div class="tab_box1 row" id="droploadTab">
						<table id="index_org_table" cellpadding="0" cellspacing="0"
							class="table">
							<tbody id="listOrg"></tbody>
						</table>
					</div>
				</div>
				<!--底部    start-->
				<%@ include file="/common/footer.jsp"%>
				<!--底部    end-->
			</div>
			<!-- /page-content -->
		</div>
	</div>
	<!-- /container -->
	<!--加载分页  -->
	<script language="javascript" type="text/javascript"
		src="<%= request.getContextPath() %>/js/dropload.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="<%= request.getContextPath() %>/js/connection/connction_indexOrg.js?<%= autoVersoin %>"></script>
<input type="hidden" id="guideState" value="${sessionScope.shiroUser.guideState}">	
<c:if test='${sessionScope.shiroUser.guideState != 1}'>
<div id="walkthrough-content">
    <div id="walkthrough-1">
    	<div class="first-steps step" style="position:relative;">
    		<i class="btn-close" onclick = "closeYindao()"></i>
    		<img src="<%=path%>/images/yd/yd-1.png" alt=""  style="max-width:none;"/>
    	</div>
    </div>
    <div id="walkthrough-2">
    	<div class="second-steps step">
        	<img src="<%=path%>/images/yd/yd-2.png" alt="" style="margin-top:-90px; max-width:none;" />
        	<a id="jpwClose"></a>
       	</div>
    </div>
    <div id="walkthrough-3">
    	<div class="third-steps step">
       		<img src="<%=path%>/images/yd/yd-3.png" alt="" style="margin-top:-90px; max-width:none;" />
   		</div>
    </div>
    <div id="walkthrough-4">
    	<div class='fourth-steps step'>
        	<img src="<%=path%>/images/yd/yd-4.png" alt="" style="margin-top:-90px; max-width:none;" />
        </div>
    </div>
    <div id="walkthrough-5">
    	<div class="fifth-steps step">
        	<img src="<%=path%>/images/yd/yd-5.png" alt="" style="margin-left:-110px; margin-top:-100px; max-width:none;" />
        </div>
    </div>
    <div id="walkthrough-6">
    	<div class="sixth-steps step">
        	<img src="<%=path%>/images/yd/yd-6.png" alt="" style="margin-left:-140px; margin-top:-90px; max-width:none;" />
        </div>
    </div>
</div>
</c:if>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/next.js"></script>
</body>
</html>
