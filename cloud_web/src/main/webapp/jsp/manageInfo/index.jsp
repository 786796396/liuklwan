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
				<c:set var="ba_index" value="18" scope="request" />
				<c:set var="menu" value="#menuFx1" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
				<c:if test="${top == 8}">
				<div class="page-content2 tab-content">
					<div id="khxx" class="row guanl-con active">
						<div>
							<div class="modal-container">
								<div class="modal-nav-box">
									<ul class="nav nav-pills">
										<li class="active">
											<a href="#basicInfo" data-toggle="tab">基本信息</a>
										</li>
									</ul>
								</div>
								<!--/modal-nav-->
								<div class="tab-content">
									<div id="basicInfo" class="modal-tab2 active">
										<input type="hidden" id="siteCode" value="${databaseOrgInfo.siteCode}"> <input type="hidden" id="home_url_state_id" value="0"> <input type="hidden" id="jump_url_state_id" value="0">
										<%-- 									<form id="base_info_form" action="<%=path %>/manageInfo_modifyBaseInfo.action" method="post"> --%>
										<table class="basic-tab" cellpadding="0" cellspacing="0" style="margin-left:0;">
											<tr>
												<td class="tit-td" style="width:135px;">组织单位编码：</td>
												<td style="width:200px;">${databaseOrgInfo.siteCode}</td>
												<td class="tit-td" style="width:80px;">账号状态：</td>
												<td><c:if test="${databaseOrgInfo.type==1}">可用</c:if><c:if test="${databaseOrgInfo.type==2}">不可用</c:if></td>
											</tr>
											<tr>
												<td class="tit-td"><span class="red">*</span>组织单位名称：</td>
												<td colspan="3"><input type="text" style="width:392px;" value="${databaseOrgInfo.name}" name="databaseOrgInfo.name" id="siteName_table" onblur="checkSiteName_table()" /></td>
											</tr>
										</table>
										<div class="accordion-heading">
											<h3>负责人信息</h3>
										</div>
										<div class="hline"></div>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;姓 名：</td>
												<td><input type="text" style="width:292px;" id="responsibleName" value="${databaseOrgInfo.principalName}"  />
												</td>
											</tr>
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;手机：</td>
												<td>
													<input type="text" style="width:292px;" id="responsiblePhone" onblur="checkRelationCellphone_table2()" value="${databaseOrgInfo.principalPhone}"  /> 
												</td>
											</tr>
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;电子邮箱：</td>
												<td>
													<input type="text" style="width:292px;" id="responsibleEmail" onblur="checkRelationEmail_table2()" value="${databaseOrgInfo.principalEmail}"  />
												</td>
											</tr>
										</table>
										<div class="accordion-heading">
											<h3>联系人信息</h3>
										</div>
										<div class="hline"></div>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td"><span class="red">*</span>姓 名：</td>
												<td><input type="text" style="width:292px;" id="relationName_table" onblur="checkRelationName_table()" value="${databaseOrgInfo.linkmanName}" name="databaseInfo.linkmanName" />
												</td>
											</tr>
											<tr>
												<td class="tit-td"><span class="red">*</span>手机：</td>
												<td><input type="text" style="width:292px;" id="relationCellphone_table" onblur="checkRelationCellphone_table()" value="${databaseOrgInfo.linkmanPhone}" name="databaseInfo.linkmanPhone" /> 
											</td>
											</tr>
											<tr>
												<td class="tit-td"><span class="red">*</span>电子邮箱：</td>
												<td><input type="text" style="width:292px;" id="relationEmail_table" onblur="checkRelationEmail_table()" value="${databaseOrgInfo.linkmanEmail}" name="databaseInfo.linkmanEmail" /></td>
											</tr>
											<tr>
												<td></td>
											</tr>
										</table>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;姓 名：</td>
												<td><input type="text" style="width:292px;" id="relationNametwo_table" onblur="checkRelationNametwo_table()" value="${databaseOrgInfo.linkmanNametwo}" name="databaseInfo.linkmanNametwo" />
												</td>
											</tr>
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;手机：</td>
												<td><input type="text" style="width:292px;" id="relationCellphonetwo_table" onblur="checkRelationCellphonetwo_table()" value="${databaseOrgInfo.linkmanPhonetwo}" name="databaseInfo.linkmanPhonetwo" /> 
											</td>
											</tr>
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;电子邮箱：</td>
												<td><input type="text" style="width:292px;" id="relationEmailtwo_table" onblur="checkRelationEmailtwo_table()" value="${databaseOrgInfo.linkmanEmailtwo}" name="databaseInfo.linkmanEmailtwo" /></td>
											</tr>
											<tr>
												<td></td>
											</tr>
										</table>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;姓 名：</td>
												<td><input type="text" style="width:292px;" id="relationNamethree_table" onblur="checkRelationNamethree_table()" value="${databaseOrgInfo.linkmanNamethree}" name="databaseInfo.linkmanNamethree" />
												</td>
											</tr>
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;手机：</td>
												<td><input type="text" style="width:292px;" id="relationCellphonethree_table" onblur="checkRelationCellphonethree_table()" value="${databaseOrgInfo.linkmanPhonethree}" name="databaseInfo.linkmanPhonethree" /> 
											</td>
											</tr>
											<tr>
												<td class="tit-td">&nbsp;&nbsp;&nbsp;&nbsp;电子邮箱：</td>
												<td><input type="text" style="width:292px;" id="relationEmailthree_table" onblur="checkRelationEmailthree_table()" value="${databaseOrgInfo.linkmanEmailthree}" name="databaseInfo.linkmanEmailthree" /></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="button" id="submitBaseModify_id" class="btn btn-primary" value="保 存" onclick="submitBaseOrgModify();" />
												</td>
											</tr>
										</table>
										<!-- 	</form> -->
									</div>
									<div id="contacts"  class="modal-tab2">
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
				</c:if>
				<c:if  test="${top != 8}">
				<div class="page-content2 tab-content">
					<div id="khxx" class="row guanl-con active">
						<div>
							<div class="modal-container">
								<div class="modal-nav-box">
									<ul class="nav nav-pills">
										<li class="active">
											<a href="#basicInfo" data-toggle="tab">基本信息</a>
										</li>
										<li>
											<a href="#columnInfo" data-toggle="tab">栏目信息</a>
										</li>
									</ul>
								</div>
								<!--/modal-nav-->
								<div class="tab-content">
									<div id="basicInfo" class="modal-tab2 active">
										<input type="hidden" id="siteCode" value="${databaseInfo.siteCode}"> <input type="hidden" id="home_url_state_id" value="0"> <input type="hidden" id="jump_url_state_id" value="0">
										<%-- 									<form id="base_info_form" action="<%=path %>/manageInfo_modifyBaseInfo.action" method="post"> --%>
										<table class="basic-tab" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td" style="width:135px;">网站标识码：</td>
												<td style="width:200px;"><a target="_blank" class="wz-name wz-name-w2" href="<%=path%>/fillUnit_gailan.action?siteCode=${databaseInfo.siteCode}">${databaseInfo.siteCode}</a></td>
												<td class="tit-td" style="width:80px;">账号状态：</td>
												<td><c:if test="${databaseInfo.state==0}">可用</c:if> <c:if test="${databaseInfo.state==1}">不可用</c:if></td>
											</tr>
											<tr>
												<td class="tit-td"><span class="red">*</span>网站名称：</td>
												<td colspan="3"><input type="text" style="width:392px;" value="${databaseInfo.name}" name="databaseInfo.name" id="siteName_table" onblur="checkSiteName_table()" /></td>
											</tr>
											<tr>
												<td class="tit-td"><span class="red">*</span>主办单位：</td>
												<td colspan="3"><input type="text" style="width:392px;" id="siteManageUnit_table" onblur="checkSiteManageUnit_table()" value="${databaseInfo.director}" name="databaseInfo.director" /></td>
											</tr>
											<tr>
												<td class="tit-td"><span class="red">*</span>办公地址：</td>
												<td colspan="3"><input type="text" style="width:392px;" id="officeAddress_table" onblur="checkOfficeAddress_table()" value="${databaseInfo.address}" name="databaseInfo.address" /></td>
											</tr>
											<tr>
												<td class="tit-td" valign="top"><span class="red">*</span>首页URL：</td>
												<td colspan="3" class="url-td"><input class="failure-input" type="text" style="width:392px;" value="${databaseInfo.manageInfoUrl}" id="homeUrl_table" onblur="checkHomeUrl_table()" name="databaseInfo.url" />
													<div class="btn-07c1f2" id="home_url_conn_test" onclick="onLineCheckHomeUrl();">连通测试</div>
													<div class="column-loading-box" id="home_url_loading-box">
														<div id="home_loading_id">
															<span>连通测试中，可能需要几十秒钟，请稍候......</span><span class="loading-num">75%</span>
														</div>
														<div class="column-loading">
															<div class="column-loading-con"></div>
														</div>
													</div>
													<div class="info-tip2">(为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，可联系客服。)</div></td>
											</tr>
											<tr class="disabled-tr" id="jumpUrl_tr">
												<td class="tit-td" valign="top">
													<div class="tz-cbox" id="checkBox">
														<input type="checkbox" id="checkbox_tr" />
													</div>
													<div class="tz-tit">
														跳转URL：
														<div class="info-tip3">（监测入口）</div>
													</div></td>
												<td colspan="3" class="url-td"><input type="text" style="width:392px;" disabled="disabled" id="jumpUrl_table" value="${databaseInfo.manageInfoJumpUrl}" name="databaseInfo.jumpUrl" />
													<div class="btn-07c1f2" id="jump_home_url_conn_test" onclick="onLineCheckHomeJumpUrl()">连通测试</div>
													<div class="column-loading-box" id="jump_url_loading-box">
														<div id="jump_loading_id">
															<span>连通测试中，可能需要几十秒钟，请稍候......</span><span class="loading-num">1%</span>
														</div>
														<div class="column-loading">
															<div class="column-loading-con"></div>
														</div>
													</div>
													<div class="info-tip2">(为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，可联系客服。)</div></td>
											</tr>
											<tr>
											<td></td><td><input type="button" id="submitBaseModify_id" class="btn btn-primary" value="保 存" onclick="submitBaseModify();" />
											</tr>
										</table>

									</div>
									<!--/basicInfo-->
									
									<div id="columnInfo" class="modal-tab2">
										<div class="column-group">
											<div class="tab_header row tab_header_white">
												<div class="tab_header_white_con">
													<h3>重点监测栏目/系统</h3>
													<div class="input-prepend input-prepend-black">
														<span class="add-on"> <!--<img alt="search"
														src="<%=path%>/images/common/search_black.png" /> </span> 
													<input class="span2 prependedInput" id="channelInfo_term" type="text"
														placeholder="输入关键字..." >
												-->
													</div>
													<div class="btn-2dba62" onclick="channelExportExcel()" id="channelExportExcelId">导出列表</div>
													<div class="btn-2dba62" onclick="delChannelList()">删 除</div>
													<div class="btn-2dba62" onclick="addColumnModal()">添 加</div>
													<div class="btn-2dba62" onclick="reCheckChannel()" style="display:none;">重点监测</div>
													<!--<div class="btn-2dba62" data-toggle="modal"
													data-target="#columnModal">
													发现栏目
												</div>
												<div class="btn-00a1cb">
													在线校验
												</div>
												
												<div class="btn-939eaf" onclick="cancelCheck()">
													取消监测
												</div> -->
												</div>
												<div class="info-tip4">
													<span>*</span>免费用户可以免费监测5个栏目的更新情况，收费用户可以监测全部报送栏目；
												</div>
												<div class="info-tip4">
													<span>*</span>本版本暂不提供修改URL，如需修改请先删除后再添加。
												</div>
											</div>
											<div class="columninfo-body">
												<div id="channelList">
													<div class="dropload-load">
														<span class="loading"></span>加载中...
													</div>
													<table id="table_data_channel_columnTab" cellpadding="0" cellspacing="0" class="table">
														<tbody>
														</tbody>
													</table>
												</div>
												<!--  <div id="addTab" class="add-tab"></div>  -->
											</div>
											<!-- /accordion-body -->
										</div>
										<!-- /accordion-group -->
									</div>
									<!--/columnInfo-->
						
						
						</c:if>
								</div>
								<!--/tab-content-->
							</div>
						</div>
					</div>
					<!--/khxx -->
					<div id="ddxx" class="row guanl-con">
						<div class="star-tip">
							<i></i>代表门户
						</div>
						<div class="accordion" id="accordion2">
							<div class="accordion-inner">
								<div class="dropload-load">
									<span class="loading"></span>加载中...
								</div>
								<table id="table_data_webInfos" cellpadding="0" cellspacing="0" class="table dblclick-tab">
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!--/ddxx -->
					<div id="fwdd" class="row guanl-con">
						<c:if test="${sessionScope.shiroUser.isOrgCost==0}">
							<div class="free-html">
								<i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;
								<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>
								&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱：
								<a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
							</div>
						</c:if>
						<div class="contract-body" id="noneContractInfo">
							<div class="contract-main">
								<table cellpadding="0" cellspacing="0">
									<tbody>
										<tr>
											<td class="contract-infotit">合同号：</td>
											<td style="width:300px;" id="contractCode"></td>
											<td class="contract-infotit">临时合同号：</td>
											<td id="contractCodeTemp"></td>
										</tr>
										<tr>
											<td class="contract-infotit">合同名称：</td>
											<td id="contractName"></td>
											<td class="contract-infotit">合同周期：</td>
											<td id="contractTime"></td>
										</tr>
										<tr>
											<td class="contract-infotit">站点数量：</td>
											<td id="websiteCount"></td>
											<td class="contract-infotit">抽查总站次：</td>
											<td id="spotCheckCount"></td>
										</tr>
										<tr>
											<td class="contract-infotit">高级版期数：</td>
											<td id="advancePeroidNum"></td>
											<td class="contract-infotit">普通版期数：</td>
											<td id="commonPeriodNum"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="accordion">
							<div id="noOrder" class="mar1" style="display:none;">
								<div class="free-html">
									<i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;
									<a target="_blank" href="http://jg.kaipuyun.cn/ce/banben/version.shtml">点击这里</a>
									&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱：
									<a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
								</div>
							</div>
							<div id="accordion3" class="accordion-inner"></div>
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
	<!-- Modal -->
	<div id="edit" class="page-dialog modal hide" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-header">
			<button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
			<h3 id="myModalLabel_show"></h3>
		</div>
		<div class="modal-body">
			<input type="hidden" id="channel_url_state_id" value="0"> <input type="hidden" id="channel_jump_url_state_id" value="0">
			<div class="modal-container">
				<div class="modal-nav-box" id="orderDatailId">
					<ul class="nav nav-pills">
						<li class="active">
							<a href="javascript:void(0);">栏目信息</a>
						</li>
					</ul>
				</div>
				<!--/modal-nav-->
				<div class="tab-content">
					<!--/basicInfo-->
					<div id="columnInfo" class="modal-tab2 active">
						<div class="column-group">
							<!--******************内容为空时********************-->
							<div class="column-undefined" id="column_undefined" style="display: none">
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
	<!-- Modal -->
	<div id="columnModal" class="modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="">
		<div class="modal-header modal-header2">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				<i class="dialog-close2"></i>
			</button>
			<h3 id="myModalLabel"></h3>
		</div>
		<div class="modal-body">
			<div class="modal-tab modal-tab-mar1 row" style="height:370px;">
				<div class="columninfo-body">
					<table cellpadding="0" cellspacing="0" class="table">
						<tbody>
							<tr>
								<th style="width: 28px;"><input type="checkbox" /></th>
								<th style="width: 30px;">序号</th>
								<th>名称</th>
								<th style="width: 175px;">URL</th>
								<th style="width: 175px;">跳转URL</th>
								<th style="width: 120px;">类别</th>
								<th style="width: 80px;">子类</th>
								<th class="text-center" style="width: 80px;">操作</th>
								<th class="text-center" style="width: 10px;">状态</th>
							</tr>
							<tr>
								<td><input type="checkbox" /></td>
								<td>1</td>
								<td></td>
								<td>
									<div class="ellipsis-box"></div></td>
								<td>
									<div class="ellipsis-box"></div></td>
								<td>
									<div class="ellipsis-box">办事服务</div></td>
								<td>便民电话</td>
								<td class="text-center"><i class="editor-i"></i><i class="delete-i"></i></td>
							</tr>
							<tr>
								<td><input type="checkbox" /></td>
								<td>1</td>
								<td></td>
								<td>
									<div class="ellipsis-box"></div></td>
								<td>
									<div class="ellipsis-box"></div></td>
								<td>
									<div class="ellipsis-box">办事服务</div></td>
								<td>便民电话</td>
								<td class="text-center"><i class="editor-i"></i><i class="delete-i"></i></td>
							</tr>
							<tr class="editor-tr">
								<td><input type="checkbox" /></td>
								<td>1</td>
								<td><input type="text" class="basic-input" style="width: 90%;" /></td>
								<td><input type="text" class="basic-input" style="width: 121px;" /> <b class="online-check">在线校验</b>
									<div class="info-tip5">为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，可联系客服。</div></td>
								<td><input type="text" class="basic-input" style="width: 121px;" /> <b class="online-check">在线校验</b></td>
								<td>
									<div class="tab-selectpicker-box">
										<select class="selectpicker">
											<option>办事服务</option>
										</select>
									</div></td>
								<td>
									<div class="tab-selectpicker-box">
										<select class="selectpicker">
											<option>便民电话</option>
										</select>
									</div></td>
								<td class="text-center"><i class="editor-i selected"></i><i class="delete-i selected"></i></td>
							</tr>
						</tbody>
					</table>
					<div class="pagination">
						<ul class="pagination">
							<li>
								<a href="#">上一页</a>
							</li>
							<li class="active">
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">5</a>
							</li>
							<li>
								<a href="#">下一页</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- /accordion-body -->
			</div>
			<!-- /dialog-table -->
		</div>
		<div class="modal-footer">
			<button data-dismiss="modal" class="btn white-btn">取 消</button>
		</div>
	</div>
	<%@ include file="modal.jsp"%>
	<!-- （弹出层栏目信息，服务信息）js-->
	<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/manageTbInfo.js"></script>
	<!-- （弹出层基本信息）站点信息js -->
	<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/websiteInfoTb.js"></script>
	
	<!-- 客户信息js-->
	<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/customerInfo.js"></script>
	<!-- 订单信息js-->
	<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/orderInfo.js"></script>
	<!-- 校验工具类js -->
	<script language="javascript" type="text/javascript" src="<%=path%>/js/validateUtil.js"></script>
	<!-- /container -->
	<script type="text/javascript" src="<%=path%>/js/flexslider/jquery.flexslider-min.js"></script>
</body>
</html>