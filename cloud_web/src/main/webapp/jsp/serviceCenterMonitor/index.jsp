<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>服务中心-监测服务</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/> 
 	<link rel="stylesheet" href="<%=path%>/css/serviceCenter.css"/>
 	<link rel="stylesheet"  type="text/css" href="<%=path%>/css/bread-crumbs.css" /><!--面包屑-->
  </head>
  <body class="bg_f5f5f5">
	<form id="searchform" name="searchform" method="post">
		<input name="data" type="hidden" value="" id="dataUrl" />
	</form>
	<%@ include file="/common/top.jsp"%>
	<div class="main-detail">
		<div class="main-detail-content">
			<%@ include file="/common/leftmenu.jsp"%>
			<div class="page-content">
				<div class="bread-crumbs">
					<div class="bread-crumbs-content">
						<span class="cor-0498e4">服务中心</span> <i class="cor-0498e4">></i> <span>网站监测服务</span>
					</div>
				</div>

				<div class="service-block">
					<div class="row-fluid clearfix">
						<div class="span6 half-part clearfix">
							<i class="sc-ico wzmfjc"></i>
							<div class="txt-des">
								<h4>
									网站免费监测<span class="charge-txt bg-0a6">免费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>首页连通性 -- 每 15分钟 自动连接首页一次</li>
									<li><i class="li-p"></i>首页死链扫描 -- 扫描首页，找出不能正常访问的链接</li>
									<li><i class="li-p"></i>首页更新及时性 -- 分析首页的最近更新时间</li>
									<li><i class="li-p"></i>首页更新量 -- 分析首页的更新文章的发布数量</li>
									<li><i class="li-p"></i>网站访问量--反映网民访问您的网站浏览了网页的次数以及用户数</li>
									<li><i class="li-p"></i>搜索引擎收录--反映您的网站在百度被收录的网页数量</li>
									<li><i class="li-p"></i>在线查看实时数据</li>
								</ul>
								<b class="already-open">已开通服务</b>
								<a onClick="freeCheck()" href="<%=path%>/connectionHomeDetail_webSiteConnected.action">立即体验&nbsp;></a>
							</div>
						</div>
						<div class="span6 half-part clearfix">
							<i class="sc-ico yjjc"></i>
							<div class="txt-des">
								<h4>
									一键检测<span class="charge-txt bg-0a6">免费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>一键检测是开普云推出的免费体验服务</li>
									<li><i class="li-p"></i>输入url，系统立即开始扫描，实时反馈扫描结果</li>
									<li><i class="li-p"></i>为了降低系统资源消耗，仅扫描您输入的当前页面</li>
									<li><i class="li-p"></i>检测指标：页面连通性、页面最后更新日期、页面死链</li>
								</ul>
										<b class="already-open">已开通服务</b><a href="javascript:void(0);"><span onclick="queryDatabaseOrgInfo('一键检测','http://114.55.51.247/YjgZj/index.jsp','1');">立即体验&nbsp;></span></a>
							
							</div>
						</div>
					</div>
					<div class="row-fluid clearfix">
						<div class="span6 half-part clearfix">
							<i class="sc-ico qzslsm"></i>
							<div class="txt-des">
								<h4>
									全站死链扫描<span class="charge-txt bg-ff911a">收费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>全站扫描，深层及找出不能正常访问的链接</li>
									<li><i class="li-p"></i>死链过滤配置，根据您设置的url自动过滤不属于您网站的死链</li>
									<li><i class="li-p"></i>死链快照，留存快照便于查找死链进行网站改进和优化</li>
									<li><i class="li-p"></i>问题预警、在线查看实时数据</li>
								</ul>
								<c:choose>
									<c:when test="${isAdvancedContract == 1}">
										<b class="already-open" >已开通服务</b>
										<a onClick="depthLinkAll()" href="<%=path%>/depthLinkAll_depthLinkAllMain.action">立即体验&nbsp;></a>
									</c:when>
									<c:otherwise>
										<b class="click-open" onclick="openService()">点击开通</b>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="span6 half-part clearfix">
							<i class="sc-ico lmsdjc"></i>
							<div class="txt-des">
								<h4>
									栏目深度监测<span class="charge-txt bg-ff911a">收费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>包含所有关键栏目的深度监测</li>
									<li><i class="li-p"></i>栏目连通性 -- 每 15分钟 自动连接一次</li>
									<li><i class="li-p"></i>栏目死链扫描 -- 扫描所有关键栏目,找出不能正常访问的链接</li>
									<li><i class="li-p"></i>栏目更新监测 -- 分析栏目的更新情况</li>
									<li><i class="li-p"></i>问题预警</li>
									<li><i class="li-p"></i>在线查看实时数据</li>
								</ul>
								
								<c:choose>
									<c:when test="${isDepth == 1}">
										<b class="already-open" >已开通服务</b>
										<a onClick="depthChannel()" href="<%=path%>/connectionChannelDetail_indexOrg.action">立即体验&nbsp;></a>
									</c:when>
									<c:otherwise>
										<b class="click-open" onclick="openService()">点击开通</b>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="row-fluid clearfix">
						<div class="span6 half-part clearfix">
							<i class="sc-ico cbzsm"></i>
							<div class="txt-des">
								<h4>
									错别字扫描<span class="charge-txt bg-ff911a">收费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>深层扫描新发稿件文章，根据语义语境检测疑似错别字，并给出推荐修改文案</li>
									<li><i class="li-p"></i>留存错别字定位快照，方便查找错别字进行修改</li>
									<li><i class="li-p"></i>严重错别字预警，第一时间发现问题</li>
									<li><i class="li-p"></i>在线查看实时数据</li>
								</ul>
								
								<c:choose>
									<c:when test="${isCorrect == 1}">
										<b class="already-open" >已开通服务</b>
										<a onClick="depthCorrect()" href="<%=path%>/correctContent_indexOrg.action">立即体验&nbsp;></a>
									</c:when>
									<c:otherwise>
										<b class="click-open" onclick="openService()">点击开通</b>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="span6 half-part clearfix">
							<i class="sc-ico qmjc"></i>
							<div class="txt-des">
								<h4>
									全面检测<span class="charge-txt bg-ff911a">收费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>全站死链扫描 -- 深层及找出不能正常访问的链接</li>
									<li><i class="li-p"></i>网站不连通 -- 首页和关键栏目的连通性，每5分钟自动连接一次</li>
									<li><i class="li-p"></i>首页和栏目不更新问题 -- 分析首页和关键栏目的更新情况</li>
									<li><i class="li-p"></i>严重错别字 -- 问题字词扫描，人工确认严重错别字</li>
									<li><i class="li-p"></i>其他严重问题 -- 空白栏目扫描、互动回应差、服务不实用问题</li>
									<li><i class="li-p"></i>预警消息 -- 发现问题实时预警，及时防范触及红线</li>
									<li><i class="li-p"></i>在线报告 -- 在线报告实时预览，下载分析报告和数据</li>
								</ul>
								
								<c:choose>
									<c:when test="${isAdvancedContract == 1}">
										<b class="already-open" >已开通服务</b>
										<a onClick="depthTotal()" href="<%=path%>/servicePeriod_servicePeriod.action?type=1">立即体验&nbsp;></a>
									</c:when>
									<c:otherwise>
										<b class="click-open" onclick="openService()">点击开通</b>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<div class="row-fluid clearfix">
						<div class="span6 half-part clearfix">
							<i class="sc-ico aqsm"></i>
							<div class="txt-des">
								<h4>
									安全扫描 <span class="charge-txt bg-ff911a">收费</span>
								</h4>
								<ul>
									<li><i class="li-p"></i>网站脆弱性监测 -- SQL注入漏洞、应用漏洞、脚本漏洞、表单破解</li>
									<li><i class="li-p"></i>网站挂马监测 -- 挂马严重等级、类型、方法、参数</li>
									<li><i class="li-p"></i>变更/篡改监测 -- 发现篡改和变更的问题页面URL</li>
									<li><i class="li-p"></i>网站暗链监测 -- 扫描暗链，给出问题页面URL</li>
									<li><i class="li-p"></i>内容泄露监测 -- 文件泄露和内容泄露监测</li>
									<li><i class="li-p"></i>问题预警、在线查看实时数据</li>
								</ul>
								
								<c:choose>
									<c:when test="${sessionScope.shiroUser.isOrgSafetyService !=0}">
										<b class="already-open" >已开通服务</b>
										<a onClick="depthSafe()" href="<%=path%>/monitorSilentResultOrg_indexOrg.action">立即体验&nbsp;></a>
									</c:when>
									<c:otherwise>
										<b class="click-open" onclick="openService()">点击开通</b>
									</c:otherwise>
								</c:choose>
								
							</div>
						</div>
					</div>
				</div>

				<!--底部    start-->
				<%@ include file="/common/footer.jsp"%>
				<!--底部    end-->
			</div>
		</div>
	</div>
	<%@ include file="/common/service_open.jsp"%>
	<script type="text/javascript">
		function queryDatabaseOrgInfo(typeName, url, type) {
			$.ajax({
				type : "POST",
				url : webPath + "/databaseInfo_queryDatabaseOrgInfo.action",
				data : {
					typeName : typeName,
					url : url,
					type : type
				},
				dataType : "json",
				async : false,
				success : function(data) {
					$("#dataUrl").val(data.dataUrl);
					document.searchform.action = data.url;
					document.searchform.target = '_blank';//这一句是关键
					document.searchform.submit();
				}
			});
		}
		
		
	</script>
	<script type="text/javascript" src="<%=path%>/js/serviceCenterMonitor/index.js"></script>
</body>
</html>