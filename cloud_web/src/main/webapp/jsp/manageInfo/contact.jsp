<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>云监测 管理-客户信息</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
</head>
<body class="bg_f5f5f5">
	<%@ include file="/common/top.jsp"%>
	<div class="page-wrap">
		<div class="main-container container">
				<input id="typeId" value="${type}" type="hidden"/>
				<input id="top" value="${top}" type="hidden"/>
				<input id="TB" value="org" type="hidden"/>
			<div class="row-fluid">
				<%@ include file="/common/leftmenu.jsp"%>
				<div class="page-content2 tab-content">
					<div id="khxx" class="row guanl-con active">
						<div>
							<div class="modal-container">
								<div class="modal-nav-box">
									<ul class="nav nav-pills">
										<li class="active">
											<a href="#contacts" data-toggle="tab">联系人名录</a>
										</li>
									</ul>
								</div>
								<div class="tab-content">
									<div id="contacts"  class="modal-tab2 active">
										<div class="contacts_content">
											<div class="cc_top clearfix">
												<div class="cc_top_left fl">
													<input type="text" id="contractsQuery" placeholder="请输入网站名称、单位、姓名"  onkeyup="getContractInfo()"/>
													<i onclick="getContractInfo()"></i>
												</div>
												<div class="cc_top_right fr" onclick="exportContracts()">
													导出
												</div>
											</div>
											<div class="cc_table">
												<table style="table-layout:fixed;">
													<thead>
														<tr>
															<th class="text-center" style="width:29px;">序号</th>
															<th class="text-left">标识码</th>
															<th class="text-left">网站名称</th>
															<th class="text-left">单位</th>
															<th class="text-center">人员类别</th>
															<th class="text-center">姓名</th>
															<th class="text-center">职务</th>
															<th class="text-center" style="width:100px;">工作电话</th>
															<th class="text-center">手机</th>
															<th class="text-center">邮箱</th>
														</tr>
													</thead>
													<tbody id="contractContentId">
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!--/basicInfo-->
								</div>
								<!--/tab-content-->
							</div>
						</div>
					</div>
					<!--/fwdd -->
					<%@ include file="/common/footer.jsp"%>
					<!-- /page-footer -->
				</div>
				<!-- /page-content -->
				<!-- /container -->
			</div>
		</div>
	</div>
	<%@ include file="modal.jsp"%>
	<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/contact.js"></script>
</body>
</html>