<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>云监测  全面监测</title>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/heade.jsp"%>
		<script  type="text/javascript" src="<%=path%>/js/sortSecond.js"></script>
        <script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js-->
		<script type="text/javascript" src="<%=path%>/js/servicePeriod/servicePeriod.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css"></link>
	</head>

	<body class="bg_f5f5f5">
		<!--服务周期首页面  -->
		<div class="page-wrap">
			<input id="site_code_session" type="hidden" value="${sessionScope.shiroUser.siteCode}">
			<input id="types" type="hidden" value="${type}">
			<!--头部       satrt  -->
			<c:if test="${type == 2}">
				  <%@ include file="/common/top_tb.jsp"%>
			</c:if>
			<c:if test="${type == 1}">
				  <%@ include file="/common/top.jsp"%>
			</c:if>
			<!--头部       end  -->
			<div id="main-center"class="main-container container">
				<div class="row-fluid">
					<!--左侧导航       satrt  -->
					<c:set var="ba_index" value="301" scope="request"/>
					<c:set var="menu" value="#menuDeJc" scope="request"/>
					<%@ include file="/common/leftmenu.jsp"%>
					<!--左侧导航       end  -->
					<div class="page-content paddt-27">
            			<input type="hidden" id="siteCodes" value="${sessionScope.shiroUser.siteCode}">
            			<input type="hidden" id="scheduleId"/>
            			<input type="hidden" id="scheduleId2" value="${scheduleId}">
            			
            		 <div class="row bread-crumbs daily-monitor-box-m">
		                <div class="bread-crumbs-content">
		                    <span class="cor-0498e4">报告和整改</span>
		                    <i class="cor-0498e4">></i>
		                    <span>全面检测</span>
		                </div>
		            </div>
		            
			            <div class="chouc-tab-header">
			           		
			            	<div class="input-prepend-black pull-left">
			                    <label>
			                    	全局搜索：
			                        <input  id="input_key_word_id" type="search"  value="请输入周期任务号"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='请输入周期任务号'}">
			                        <span class="add-on" id="search_btn_id"><img src="<%=path%>/images/common/search_black.png" alt="search"  style="cursor:pointer;'"></span>
			                    </label>
			                </div>
			                <div class="time-sear pull-left">
			                	<label>
			                    	创建时间：
			                    	<select id="select_id">
			                    		<option value="" selected="selected">--请选择--</option>
			                        	<option value="30" >近一个月批次</option>
			                        	<option value="90" >近三个月批次</option>
			                        </select>
			                    </label>
			                </div>
			            </div>
						<div class="chouc-tab-box">
							<table cellspacing="0" cellpadding="0" class="fuzhi-chouc-tab" id="spot_check_schedule_table_id">
								<thead class="fth">
									<tr style="width:100%;">
										<th style="width:51px;">批次</th>
										<th style="width:51px;">组次</th>
										<th class="t-left" style="width:86px;">周期任务号</th>
										<th style="width:94px;">任务<br/>周期</th>
										<th style="width:94px;">任务<br/>状态</th>
										<th style="width:100px;">站点<br/>数量</th>
										<th style="width:100px;">完成报<br/>告数量 </th>
										<c:if test='${(fn:length(sessionScope.shiroUser.siteCode))==6}'>
									    <th style="width:100px;">通知整<br/>改数量</th>
									    <th style="width:100px;">已读报<br/>告数量</th>
									    </c:if>
									    <th style="width:100px;" nowrap>查看</th>
									</tr>
								</thead>
								<tbody id="service_period_tbody_id">
									<tr>
										<td colspan="10">暂无结果数据</td>
									</tr>
								</tbody>
							</table>
							<div class="pay"></div>
						</div>
						 <!--底部    start-->
			            <%@ include file="/common/footer.jsp"%>
			            <!--底部    end-->
					</div>
					<!-- /page-content -->
				</div>
	        </div>
		</div>
	</body>
</html>
