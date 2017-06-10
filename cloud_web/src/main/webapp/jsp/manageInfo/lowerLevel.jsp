<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>云监测 管理-客户信息</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<!--module-->
<!--     <link rel="stylesheet"  type="text/css" href="<%= path %>/css/site-info-manage.css" />站点信息 -->
<!--     <link rel="stylesheet" href="<%= path %>/css/yunjc2.css"/> -->
<!--     <script language="javascript" type="text/javascript" -->
<!-- 	src="<%= request.getContextPath() %>/js/gauge.js"></script> -->
   
</head>
<body class="bg_f5f5f5">
<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%= path %>/images/common/top-hover.png"/>
        <img class="top-img" src="<%= path %>/images/common/top.png"/>
    </a>
</p>
<%@ include file="/common/top.jsp"%>
<div class="page-wrap">
<div class="main-container container">
<div class="main-detail">
    <div class="main-detail-content">
    	<%@ include file="/common/leftmenu.jsp"%>
        <input id="level" value="${level}" type="hidden"/>
        <input id="state" value="${state}" type="hidden"/>
        <div class="page-content">
				<!--  /modal-nav开始-->
		  		<div class="tab-container">
					<div class="tab-container-con">
						<table cellpadding="0" cellspacing="0" class="tab-overall">
							<thead>
			                   <tr>
			                   		<th style="width:10%;text-align: left;">序号</th>
			                        <th style="width:20%;text-align: left;">网站标识码</th>
			                        <th style="width:20%;text-align: left;">网站名称</th>
			                        <th style="width:20%;text-align: left;">主办单位</th>
			                        <th style="width:10%;text-align: left;">栏目数量</th>
			                        <th style="width:10%;text-align: left;">栏目信息</th>
			                   </tr>
						 	</thead>
						 	<tbody id="lowerLevelTable"></tbody>
						</table>
					</div>
				</div>   
				<!-- /modal-nav结束 -->
            
            <!--/khxx -->

             <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div>
    </div>
</div>	
</div>
</div>
	<!-- Modal -->
	<div id="edit" class="page-dialog modal hide tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-header">
			<button aria-hidden="true" data-dismiss="modal" class="close" onclick="closeId()"
				type="button"></button>
			<h3 id="myModalLabel_show"></h3>
		</div>
		<div class="modal-body">
			<div class="modal-container">
				<div class="modal-nav-box" id="orderDatailId">
					<ul class="nav nav-pills">
						<li class="active"><a href="javascript:void(0);">栏目信息</a></li>
					</ul>
				</div>
				<!--/modal-nav-->
				<div class="tab-content">
					<!--/basicInfo-->
					<div id="columnInfo" class="modal-tab2 active">
						<div class="column-group">
							<!--******************内容为空时********************-->
							<div class="column-undefined" id="column_undefined"
								style="display: none">
								<h3 class="red-font">该站点没有栏目信息，请联系网站负责人维护网站栏目信息</h3>
								<div class="accordion-heading">
									<h3>负责人信息</h3>
								</div>

								<div class="hline"></div>
								<table class="basic-tab" cellpadding="0" cellspacing="0">
									<tr>
										<td class="tit-td">姓 名：</td>
										<td id="principalName"></td>
									</tr>
									<tr>
										<td class="tit-td">手机：</td>
										<td id="principalTelephone"></td>
									</tr>
									<tr>
										<td class="tit-td">办公电话：</td>
										<td id="principalMobile"></td>
									</tr>
									<tr>
										<td class="tit-td">电子邮箱：</td>
										<td id="principalEmail"></td>
									</tr>
								</table>
								<div class="accordion-heading">
									<h3>联系人信息</h3>
								</div>




								<div class="hline"></div>
								<table class="basic-tab" cellpadding="0" cellspacing="0">
									<tr>
										<td class="tit-td">姓 名：</td>
										<td id="linkmanNameChannle"></td>
									</tr>
									<tr>
										<td class="tit-td">手机：</td>
										<td id="telephone2Chanele"></td>
									</tr>
									<tr>
										<td class="tit-td">办公电话：</td>
										<td id="mobile2Chanele"></td>
									</tr>
									<tr>
										<td class="tit-td">电子邮箱：</td>
										<td id="email2Chanele"></td>
									</tr>
								</table>
							</div>
							<!--******************/内容为空时********************-->
							<div class="columninfo-body">
								<div id="channelList1" class="accordion-inner bor-none">
									<!-- <table id="table_data_channel_columnTab" cellpadding="0" cellspacing="0"
											class="table">
											<tbody>
												
											</tbody>
										</table> -->
								</div>
							</div>
							<!-- /accordion-body -->
						</div>
						<!-- /accordion-group -->
					</div>
					<!--/columnInfo-->
					<!--/serviceInfo-->
				</div>
				<!--/tab-content-->
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/manageInfo/lowerLevel.js"></script>
</body>
</html>