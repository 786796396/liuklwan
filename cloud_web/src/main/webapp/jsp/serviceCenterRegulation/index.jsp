<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>服务中心-网站群监管服务</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
  	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/> 
 	<link rel="stylesheet" href="<%=path%>/css/serviceCenter.css"/>
 	<link rel="stylesheet"  type="text/css" href="<%=path%>/css/bread-crumbs.css" /><!--面包屑--> 
  </head>
  <body class="bg_f5f5f5">
  	<%@ include file="/common/top.jsp"%>
	<div class="main-detail">
		<div class="main-detail-content">
			<%@ include file="/common/leftmenu.jsp"%>
			<div class="page-content">
					<div class="bread-crumbs">
                <div class="bread-crumbs-content">
                    <span class="cor-0498e4">服务中心</span>
                    <i class="cor-0498e4">></i>
                    <span>网站群监管服务</span>
                </div>
            </div>
            <div class="service-block">
                <div class="row-fluid clearfix">
                    <div class="span6 half-part clearfix">
                        <i class="sc-ico wzccfw"></i>
                        <div class="txt-des">
                            <h4>网站抽查服务<span class="charge-txt bg-ff911a">收费</span></h4>
                            <ul>
                                <li><i class="li-p"></i>定期对所管理的网站群进行抽查检测</li>
                                <li><i class="li-p"></i> 按区域、按比例随机抽查，人工抽查，灵活控制</li>
                                <li><i class="li-p"></i> 配备检测结果和分析报告，第一时间掌握抽查情况支持反馈整改，上下级互动完成网站整改工作。</li>
                            </ul>
                            
                           	 <c:choose>
									<c:when test="${isAdvancedContract == 1}">
											  <b class="already-open" id="spotStateId" >已开通服务</b>
     										  <a onClick="spot()" href="<%=path%>/spotCheckResult_list.action">立即体验&nbsp;></a>
  										  </c:when>
									<c:otherwise>
									          <b class="click-open" id="spotStateId" onclick="openService()">点击开通</b>
									 </c:otherwise>
								</c:choose>
                            
                            
                        </div>
                    </div>
                    <div class="span6 half-part clearfix">
                        <i class="sc-ico dsjfx"></i>
                        <div class="txt-des">
                            <h4>大数据分析<span class="charge-txt bg-ff911a">收费</span></h4>
                            <ul>
                                <li><i class="li-p"></i>分层级，不同维度的的大数据统计，摸底网站群情况，宏观了解网站；</li>
                                <li><i class="li-p"></i> 运营数据，做到心中有数；全国8万家政府网站大数据，开普云独家优势，数据更客观、更体现总体趋势。</li>
                            </ul>
                            <b class="already-open">已开通服务</b>
                            <c:choose>
									<c:when test='${sessionScope.shiroUser.siteCode == "bm0100" || (fn:contains(sessionScope.shiroUser.siteCode, "0000") && !fn:containsIgnoreCase(sessionScope.shiroUser.siteCode, "T0000"))}'>
     										  <a onClick="bigNumber(1)" href="<%=path%>/siteDataOverview_siteDataOverviewChart.action">立即体验&nbsp;></a>
  										  </c:when>
									<c:otherwise>
									          <a onClick="bigNumber(2)" href="<%=path%>/dailyMonitoringStatistics_dailyMonitoringStatistics.action">立即体验&nbsp;></a>
									 </c:otherwise>
								</c:choose>
                          
                        </div>
                    </div>
                </div>
                <div class="row-fluid clearfix">
                    <div class="span6 half-part clearfix">
                        <i class="sc-ico jxkp"></i>
                        <div class="txt-des">
                            <h4>绩效考评<span class="charge-txt bg-ff911a">收费</span></h4>
                            <ul>
                                <li><i class="li-p"></i>为政府网站绩效考评定制，完美实现内部考评。</li>
                                <li><i class="li-p"></i>不同区域、不同行业的内部考评通过导入考评指标和网站群的方式完成考评。</li>
                                <li><i class="li-p"></i>灵活方便，实用强大的考评管理工具。</li>
                            </ul>
								<c:choose>
									<c:when test="${sessionScope.shiroUser.paTargetCount !=0 }">
     										<b class="already-open" id="paTatgetState" >已开通服务</b>
     										<a onClick="proJect()" href="<%=path%>/paProject_paProjectList.action">立即体验&nbsp;></a>
  										  </c:when>
									<c:otherwise>
									       <b class="click-open" id="paTatgetState"  onclick="openService()">点击开通</b>
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
	<script type="text/javascript" src="<%=path%>/js/serviceCenterRegulation/index.js"></script>

</body>
</html>