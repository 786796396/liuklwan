<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>日常监测-网站访问量</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css" />
	<script language="javascript" type="text/javascript" src="<%=path%>/js/echarts-all.js"></script>
 	<script language="javascript" type="text/javascript" src="<%=path%>/js/detection.js"></script>
 	<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
 	
 	
  </head>
  <body class="bg_f5f5f5">
  	<%@ include file="/common/top.jsp"%>
	<div class="main-detail">
		<div class="main-detail-content">
			<%@ include file="/common/leftmenu.jsp"%>
			<div class="page-content">
				<div class="bread-crumbs daily-monitor-box-m">
					<div class="bread-crumbs-content">
						<span class="cor-0498e4">日常监测</span> <i class="cor-0498e4">></i> <span>网站访问量</span>
					</div>
				</div>
				<div class="txt-describe">
					<p id="yunfxId" class="single-p">
						网站访问量可以反映网民访问您的网站浏览了网页的次数以及用户数，以天为单位进行统计。您可以购买我们的云分析产品，获得更加准确和丰富的网民行为分析数据，包括网民地域分析、时段分析、热点内容分析等。<a
							href="<%= path %>/cfgOtherProducts_yunAnalytics.action" target="_blank">查看云分析产品详情 > </a>
					</p>
				</div>
				<div class="daily-monitor-box-m conditions">
					<div class="conditions-content clearfix">
						<input id="yesterdayId" type="hidden" value="${yesterday}">
						<div class="fl select-box">
							<span id="siteTypeText">网站类别</span> <input id="siteTypeVal"
								type="hidden" value="0"> <i></i>
							<!--移入显示块开始-->
							<ul id="siteTypeUl" style="display: none;">
								<li value="0" name="monitorType">网站类别</li>
								<c:forEach items="${dicList}" var="dic">
									<li value="${dic.value}" name="monitorType">${dic.name}</li>
								</c:forEach>
							</ul>
							<!--移入显示块结束-->
						</div>
						<div class="fl period-box">
							<span class="fl tit">监测时间：</span>
							<div class="input-b fl t5-inp-box">
								<input class="datepicker-input" type="text" id="monitoringDate"
									readonly="readonly" />
							</div>
						</div>
						<div class="fl total-box">
							共：<span class="cor-f20707" id="sizeId"></span>条
						</div>
						<div class="fr export-box export-all">
							<i></i> <span onclick="siteVisitsListExcel()">全部导出</span>
						</div>
						<div class="fr search-box input-b">
							<input id="keyId" type="text" class="search-input fl"
								value="输入网站名称和标识码"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='输入网站名称和标识码'}" /> <i class="fl"></i>
						</div>
					</div>
				</div>
				<div class="data-show">
					<table id="table-webSort" cellpadding="0" cellspacing="0"
						class="table">
						<thead>
							<tr>
								<th class="th-center" style="width:70px;">序号</th>
								<th class=" th-center">网站标识码</th>
								<th class=" th-left">网站名称</th>
								<th class=" th-center">首页URL</th>
								<th class=" th-center">浏览量</th>
								<th class=" th-center">访客量</th>
								<th class=" th-center">图表</th>
							</tr>
						</thead>
					</table>
					<table id="table-Sum" class="table"></table>
					<table id="table-web" class="table">
						<tbody id="siteVisitsTbody">
						</tbody>
					</table>
				</div>
				<!--底部    start-->
				<%@ include file="/common/footer.jsp"%>
				<!--底部    end-->
			</div>
		</div>


		<!--弹框  图-->
		<div class="modal fade bigdata" id="siteVisitsPrompt" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="false"
			style="width: 957px; height: 418px; margin-left: -478px; left: 50%; display: none;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header green_head"
						style="position: relative; height: 45px;">
						<div type="button" class="close green_head_closeicon"
							data-dismiss="modal" aria-hidden="true"></div>
						<h4 class="modal-title green_head_title" id="myModalLabel"
							style="height: 45px; line-height: 45px;">访问量走势图</h4>
					</div>

					<div class="trend-SearchEngine clearfix"
						style="height: auto; margin: 0; border: none;">
						<div class="trend-top" style="border: none;">
							<div class="trend-title fl fs-n-18-Mic color-3c3d45">
								<div class="siteVisits_sel sv-chart_se fl">
									<span id="chartTypeText">访客量走势图</span> <i></i>
									<!--移入显示块开始-->
									<ul id="chartTypeUl" style="display: none;">
										<li value="3" name="chartType">访客量走势图</li>
										<li value="2" name="chartType">浏览量走势图</li>
									</ul>
									<!--移入显示块结束-->
								</div>
							</div>
							<div class="trend-time fr fs-n-14-Mic color-646464">
								<!-- <span>时间控制：</span> -->
								<div class="seveal_inp clearfix">
									<div class="every_inp">
										<input type="radio" id="twoWeeks" name="a" checked="checked"
											value="-14" /> <label for="yesterday">2周</label>
									</div>
									<div class="every_inp">
										<input type="radio" id="twoMonths" name="a" value="-30" /> <label
											for="twoWeeks">1个月</label>
									</div>
									<div class="every_inp">
										<input type="radio" id="threeMonths" name="a" value="-90" />
										<label for="twoMonths">3个月</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-body" id="siteVisitsChartDiv"
						style="width: 927px; height: 310px; border: none; padding: 0 15px;">

					</div>
				</div>
			</div>
		</div>


	</div>


	<script type="text/javascript" src="<%= path %>/js/siteVisits/index.js"></script>

</body>
</html>