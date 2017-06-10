<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>内容更新与分析-内容分析</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<script type="text/javascript" src="<%= path %>/js/echarts-all.js"></script>

<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />

</head>
<body>
	<%@ include file="/common/top_tb.jsp"%>
	<div class="main-container container">
		<div class="row-fluid">
			<c:set var="tb_index" value="17" scope="request"/>
			<c:set var="menu" value="#menuNrgx" scope="request"/>
			<%@ include file="/common/leftmenu_tb.jsp"%>
			<div class="page-content">
			    <c:if test="${sessionScope.shiroUser.iscost==0}">
                    <div class="free-html">
                        <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
                    </div>
                </c:if>
				<div class="row">
					<ul class="breadcrumb">
						<li><a href="#">内容更新与分析</a> <span class="divider">></span></li>
						<li class="active">内容分析</li>
					</ul>
				</div>
                <div class="row hide">
                    <div class="t_box2">
                        <h3>监测说明</h3>
    
                        <div class="t_box2_con">
                            <p>1、根据网站每天更新的内容，分析各类信息的数据更新情况、占比情况、变化趋势；</p>
                            <p>2、当日更新信息覆盖50%以上的分类；</p>
                            <p>3、信息分类：工作动态、通知公告、政府文件（政策）、人事信息、规划计划、领导信息、机构信息、地区介绍、财政信息、重大项目、统计信息、信息公开目录和其它。</p>
                        </div>
                    </div>
                </div><!-- /row1 -->
                <div class="row  mar1">
					<h3 class="info_fx_h3">信息日更新趋势分析</h3>
					<div class="z_box2 info_fx_con">

						<!-- <div class="row">
							<h4 class="font2 pull-left">更新条数</h4>
						</div> -->
						<div class="line_chart mar2">
							<c:if test="${sessionScope.shiroUser.iscost==1}">
								<div id="content_line" style="width:100%; height:330px"></div>
							</c:if>
						</div>
					</div>
				</div>
				<!-- /row1 -->

				<div class="row">
					<div class="line_chart_box lunb_chart_box">
						<div class="chart-header row">
							<h3 class="h3-brand">信息分类统计</h3>
						</div>
						<!-- /chart-header -->
						<div class="tab-content">
						<c:if test="${sessionScope.shiroUser.iscost==1}">
						<!--  <div class="chart-h1">共更新7类信息，覆盖分类超过<span style="display:none;">不足</span>50%</div> -->
                          <div class="chart-h1" id="sumDisplay"></div>
							<div id="ltxContent"
								class="chart-content chart-content-ltx mar1 active">

								<div class="lunb_chart">

									<div class="flexslider">
										<ul class="slides">
											<li style="display:block">
												<!-- -->
												<div id="content_pie" style="width:500px;height:398px">
												</div>
												<p class="flex-caption" id="scan_date"></p>
											</li>
										</ul>
										
										<ul class="flex-direction-nav">
										 <!-- <li><a href="#" class="flex-prev flex-disabled" tabindex="-1">Previous</a></li> -->
                                   		 <li><a onclick="contentPiePrevious()" class="flex-prev" tabindex="-1" id="previousHref">Previous</a></li>
                                    	<li><a onclick="contentPieNext()" class="flex-next" id="nextHref">Next</a></li>
                                		</ul>
										
									</div>


								</div>
								<div class="chart-tab-box" id="tableDiv">
									<div class="tab_box1 row">
										<table cellpadding="0" cellspacing="0" class="table t-tab3">
											<tbody id="tbodyChannel">
												<!-- <tr>
													<th class="th_left" style="width:200px;">栏目分类</th>
													<th>更新条数</th>
													<th>占比</th>
												</tr> -->
												<!-- <tr>
													<td class="td_left">工作动态</td>
													<td>265</td>
													<td>265</td>
												</tr> -->
												
											</tbody>			
											<tfoot>
												<tr>
													<td class="td_left">合计</td>
													<td id="sumNum"></td>
													<td id="sumProportion"></td>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</div>
							<!-- /chart-content-ltx -->
						</c:if>
						</div>
						<!-- /tab-content -->
					</div>

				</div>
				<!-- /row3 -->

				<%@ include file="/common/footer.jsp"%><!-- /page-footer -->
			</div>
			<!-- /page-content -->
		</div>
	</div>

	
	<script language="javascript" type="text/javascript">
		$(function() {
			$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
			/*============================
			@author:flc
			@time:2015-10-08
			============================*/
			$(".select").each(
					function() {
						var s = $(this);
						var z = parseInt(s.css("z-index"));
						var dt = $(this).children("dt");
						var dd = $(this).children("dd");
						var _show = function() {
							dd.slideDown(200);
							dt.addClass("cur");
							s.css("z-index", z + 1);
						}; //展开效果
						var _hide = function() {
							dd.slideUp(200);
							dt.removeClass("cur");
							s.css("z-index", z);
						}; //关闭效果
						dt.click(function() {
							dd.is(":hidden") ? _show() : _hide();
						});
						dd.find("li").click(function() {
							dt.html($(this).html());
							_hide();
						}); //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
						$("body").click(
								function(i) {
									!$(i.target).parents(".select").first().is(
											s) ? _hide() : "";
								});
					})
		})
	</script>
	<script type="text/javascript" src="<%= path %>/js/updateContentAnalyze/update_content_index.js"></script>
</body>
</html>