<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>临时报备</title>
    <%@ include file="/common/meta.jsp"%>
 <%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css" />
	<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
 	<script language="javascript" type="text/javascript" src="<%=path%>/js/tempReport/tempReportOrg.js"></script>
  	<script language="javascript" type="text/javascript">
		$(function(){
		     $(".baobei_table_part table tr:odd").css("background","#f7faff"); 
		     $(".baobei_table_part table tbody tr:odd").hover(function(){
		        $(this).css("background","#b5f0dd");
		    },function(){
		        $(this).css("background","");
		    });
		
		    $("#temporary_result_table_id tbody td:even").parent().find('.odd').hover(function(){
		        $(this).css("background","#b5f0dd");
		    },function(){
		        $(this).css("background","#f7faff");
		    }); 
		
			$(window).scroll(function () {
				if ($(window).scrollTop() > 100) {
					$("#backToTop").fadeIn(500);
				} else {
					$("#backToTop").fadeOut(500);
				}
			});
			$("#backToTop").click(function () {
				$('body,html').animate({scrollTop: 0}, 600);
				return false;
			});
		});
	</script>
  </head>
  <body class="bg_f5f5f5">
  <input type="hidden" id="siteCode" value="${siteCode}">
		<!--头部       satrt  -->
		<%@ include file="/common/top.jsp"%>
		<!--头部       end  -->
	<div class="main-detail">
		<div class="main-detail-content">
				<%@ include file="/common/leftmenu.jsp"%>
			<div class="page-content">
				<div class="daily-monitor-box-m conditions">
					<div class="bread-crumbs daily-monitor-box-m">
					<div class="bread-crumbs-content">
						<!-- <span class="cor-0498e4">网站临时报备</span>  <i class="cor-0498e4">></i> --><span>网站临时报备</span>
					</div>
					</div>
					<div class="txt-describe" style="margin-bottom:15px;">
						<p class="single-p">
							此页面可以查看下级网站申请的临时报备数据，一旦申请通过，在申请时间内发现的问题将不再计入统计。申请时间结束后将恢复正常监测状态。</p>
					</div>
					<div class="conditions-content clearfix" style="border-top:1px solid #ddd;">
                	<!-- <div class="fr search-box input-b">
							<input type="text"  id="siteCodeOrName"  placeholder="输入网站名称和标识码"   onfocus="this.value='';this.style.color='#000';"   />
                    <i></i>
						</div> -->
                	<div class="fl period-box">
							<span class="fl tit">报备日期：</span>
							<div class="input-b fl t5-inp-box" style="width:135px;">
								<input readonly="readonly" class="datepicker-input" style="width:112px;" type="text"  name="startDate" id="startDate" value="${startDate}"/>
							</div>
							<span class="fl tit" style="padding:0 15px;">至</span>
							<div class="input-b fl t5-inp-box" style="width:135px;">
								<input class="datepicker-input" style="width:112px;" type="text" readonly="readonly" name="endDate" id="endDate" style="background: white" value="${endDate}"/>
							</div>
					</div>	
						<div class="fr export-box export-all">
							<i></i> <span onclick="exportWord()">全部导出</span>
						</div>
						<div class="fr search-box input-b input-search">
							<input type="text"  id="siteCodeOrName"  placeholder="输入网站名称和标识码"   onfocus="this.value='';this.style.color='#000';"   />
                    <i></i>
						</div>
                		<div class="search_result fl" style="padding:6px 15px;">查询结果：<span class="" id="countSum"></span></span>条</div>
					</div>
					
				</div>
				<div class="data-show">
					<table id="table-webSort" cellpadding="0" cellspacing="0"
						class="table">
					</table>
					<div class="baobei_table_part">
					<input type="hidden" id= "reportReason" />
					<table id="temporary_result_table_id"  cellpadding="0" cellspacing="0" class="table tabresult">
						<tbody id="tobodyy">
						</tbody>
					</table>
					</div>
				</div>
				<!--底部    start-->
				<%@ include file="/common/footer.jsp"%>
				<!--底部    end-->
			</div>
		</div>
	</div>
	
</body>
</html>