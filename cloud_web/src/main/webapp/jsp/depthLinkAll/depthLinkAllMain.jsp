<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/dropload.css" />
<!--检测-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css" />
<!--tab切换-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tabChange.css" />
<script type="text/javascript"
	src="<%=path%>/js/depthLinkAll/deptLinkAllMain.js"></script>
<title>深度检测-全站链接可用性</title>
</head>
<body class="bg_f5f5f5">

	<p id="backToTop" style="display:none;">
		<a title="回到顶部" href="javascript:void(0);"> <img
			src="<%=path%>/images/common/top-hover.png" /> <img class="top-img"
			src="<%=path%>/images/common/top.png" /> </a>
	</p>
	<%@ include file="/common/top.jsp"%>
	<div class="main-container container">

		<div class="row-fluid">
			<c:set var="ba_index" value="307" scope="request" />
			<c:set var="menu" value="#menuDeJc" scope="request" />
			<%@ include file="/common/leftmenu.jsp"%>
			<div class="page-content">
				<!--栏目检测 头部标题部分s-->
				<div class="row bread-crumbs daily-monitor-box-m">
					<div class="bread-crumbs-content">
						<span class="cor-0498e4">深度检测</span>
						<i class="cor-0498e4">></i>
						<span>全站链接可用性</span>
						<div class="question-mark">
							<div class="ms-msg">
                        		<div class="ms-wen-con"  style="top:-93px;width:385px;">
		                            <div class="ztm-con">
		                                <p>1.检察下属单位不可用链接数量。</p>
                                        <p>2.站内确定不可用链接（404,403）会被计入问题统计。</p>
                                        <p>3.本服务是周期性检测服务，在服务周期内才会有检测结果。</p>
		                            </div>
                            		<i class="angle1"></i>
                        		</div>
		                        <div class="ms-icon-wen">
		                            <i class="i-wen">?</i>
		                        </div>
                    		</div>
						 </div>
					</div>
				</div>

				<div class="pay"></div>

				<div class="row daily-monitor-box-m conditions">
					<div class="conditions-content clearfix">
						<div class="fl select-box">
							<span id="siteTypeText">网站类别</span> <input type="hidden"
								id="siteTypeIdVal"> <i></i>
							<!--移入显示块开始-->
							<ul id="siteTypeIdUl" style="display: none;">
								<li name="siteType" id="siteType" value="0">网站类别</li>
								<c:forEach items="${dicList}" var="dic">
									<li name="siteType" id="siteType" value="${dic.value}">${dic.name}</li>
								</c:forEach>
							</ul>
							<!--移入显示块结束-->
						</div>
						<div id="dateStr" class="fl detection-date"></div>
						<div class="fl period-box">
							<span class="fl tit">检测周期：</span>
							<div class="fl select-box select-box-long serviceOne">
								<span id="aaaTypeText">${nearservicePeriodLinkAll}</span> <input
									type="hidden" id="servicePeriodIdVal"> <input
									type="hidden" id="servicePeriodIdText"> <i></i>
								<!--移入显示块开始-->
								<ul id="servicePeriodIdUl" style="display: none;">
									<c:forEach items="${servicePeriodLinkAll}" var="sp">
										<li name="servicePeriodId" id="servicePeriodId"
											value="${sp.id}">${sp.startDate}至${sp.endDate}</li>
									</c:forEach>
								</ul>
								<!--移入显示块结束-->
							</div>
						</div>
						<div class="fl total-box">
							共： <span id="sizeId" class="cor-f20707"></span> 条
						</div>
						<div class="fr export-box export-all"
							onclick="depthLinkAllExcel()">
							<i></i> 全部导出
						</div>
						<div class="fr search-box input-b">
							<input id="keyId" type="text" class="search-input fl"
								value="输入网站名称和标识码"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='输入网站名称和标识码'}" /> <i class="fl"></i>
						</div>
					</div>
				</div>
				<!--列表块头部结束-->
				<!--表部分开始-->
				<div class="data-show" id="content">
					<table class="table">
						<thead id="tHead">
						</thead>
					</table>
					<table id="table-web" class="table">
						<tbody id="webSiteConnectedTbody">
						</tbody>
					</table>
					<div class="zanwsj" style="display: none"
						id="webSiteConnectedloadingHide">正在加载中……</div>
					<div class="zanwsj" style="display: none" id="webSiteConnectedHide">没有查询到相应数据</div>
				</div>
				<!--表部分结束-->
				<div>
					<%@ include file="/common/footer.jsp"%>
				</div>

			</div>
			<!-- /page-content -->
		</div>
	</div>
	<!-- /container -->

	<script language="javascript" type="text/javascript">
		$(function() {
			$(function() {
				$('.select-box').hover(function() {
					$(this).children('ul').show();
				}, function() {
					$(this).children('ul').hide();
				});

				function selectShow(id) {
					$("#" + id + "Ul li").click(function() {
						$("#" + id + "Val").val(this.value);
						$("#" + id + "Text").html($(this).html());
						$("#" + id + "Ul").hide();
					});
				}
				selectShow("siteType");
				selectShow("aaaType");

				$("#servicePeriodIdUl li").click(function() {
					$("#servicePeriodIdVal").val(this.value);
					$("#servicePeriodIdText").val($(this).html());
					$("#servicePeriodIdUl").hide();
					$("#aaaTypeText").html($(this).html());
					changeFunction();
				});

				$("#siteTypeIdUl li").click(function() {
					$("#siteTypeIdVal").val(this.value);
					$("#siteTypeIdUl").hide();
					$("#siteTypeText").html($(this).html());
					changeFunction();
				});

				$(document).ready(function(e) {
					//cbox样式
					$("input[type='checkbox'],input[type='radio']").iCheck({
						checkboxClass : 'icheckbox_square-blue',
						radioClass : 'iradio_square-blue',
					});
				})

			})
		})
	</script>
</body>
</html>