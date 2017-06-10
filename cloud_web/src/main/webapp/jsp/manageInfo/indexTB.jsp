<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>云监测 管理-客户信息</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
</head>
<body>
	<%@ include file="/common/top_tb.jsp"%>
<!-- 	<div class="main-container container ss"> -->
	<div class="main-detail">
		<div class="main-detail-content">
		<%@ include file="/common/leftmenu.jsp"%>
	<input id="typeId" value="${type}" type="hidden"/>
	<input id="top" value="${top}" type="hidden"/>
	<input id="TB" value="TB" type="hidden"/>
	
		<div class="row-fluid">
		
			<c:if test="${top == 8}">
				<input id="toBaoSongUrl" value="${toBaoSongUrl}" type="hidden"/>
				<input id="vcode" value="${databaseInfo.vcode}" type="hidden"/>
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
										<input type="hidden" id="siteCode" value="${databaseInfo.siteCode}"> <input type="hidden" id="home_url_state_id" value="0"> <input type="hidden" id="jump_url_state_id" value="0">
										<%-- 									<form id="base_info_form" action="<%=path %>/manageInfo_modifyBaseInfo.action" method="post"> --%>
										<table class="basic-tab" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td" style="width:135px;">网站标识码：</td>
												<td style="width:200px;">${databaseInfo.siteCode}</td>
												<td class="tit-td" style="width:80px;">账号状态：</td>
												<td><c:if test="${databaseInfo.state==0}">可用</c:if><c:if test="${databaseInfo.state==1}">不可用</c:if></td>
											</tr>
											<tr>
												<td class="tit-td">网站单位名称：</td>
												<%-- <td colspan="3"><input type="text" style="width:392px;" value="${databaseInfo.name}" name="databaseOrgInfo.name" id="siteName_table" onblur="checkSiteName_table()" /></td> --%>
												<td colspan="3"><p  style="width:392px; margin:0;" ><input type="hidden" id="siteName_table" name="databaseOrgInfo.name" value="${databaseInfo.name}" onblur="checkSiteName_table()">${databaseInfo.name}</p>
																<input type="hidden">
												</td>
											</tr>
										</table>
										<div class="accordion-heading">
											<h3>负责人信息</h3>
										</div>
										<div class="hline"></div>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">姓 名：</td>
												<td><p type="text" style="width:292px;margin:0;" id="responsibleName" >${databaseInfo.principalName}</p>
												</td>
											</tr>
											<tr>
												<td class="tit-td">职 务：</td>
												<td><p type="text" style="width:292px;margin:0;" id="responsiblePost" >${databaseInfo.principalPost}</p>
												</td>
											</tr>
											
											<tr>
												<td class="tit-td">手机：</td>
												<td>
													<p style="width:292px;margin:0;" id="responsiblePhone" onblur="checkRelationCellphone_table2()" >${databaseInfo.telephone}</p>
												</td>
											</tr>
											<tr>
												<td class="tit-td">办公电话：</td>
												<td><p type="text" style="width:292px;margin:0;" id="responsibleMobile">${databaseInfo.mobile}</p>
												</td>
											</tr>
											<tr>
												<td class="tit-td">电子邮箱：</td>
												<td>
													<p type="text" style="width:292px;margin:0;" id="responsibleEmail" onblur="checkRelationEmail_table2()" >${databaseInfo.email}</p>
												</td>
											</tr>
										</table>
										<div class="accordion-heading">
											<h3>联系人信息</h3>
										</div>
										<div class="hline"></div>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">姓 名：</td>
												<td>
<!-- 													<input type="text" style="width:292px;" id="relationName_table" onblur="checkRelationName_table()" value="${databaseInfo.linkmanName}" name="databaseInfo.linkmanName" /> -->
													<p style="width:292px;margin:0;"  onblur="checkRelationName_table()"  name="databaseInfo.linkmanName" ><input type="hidden" value="${databaseInfo.linkmanName}" id="relationName_table">${databaseInfo.linkmanName}</p>
												</td>
											</tr>
											
											<tr>
												<td class="tit-td">手机：</td>
												<td>
<!-- 													<input type="text" style="width:292px;" id="relationCellphone_table" onblur="checkRelationCellphone_table()" value="${databaseInfo.telephone2}" name="databaseInfo.linkmanPhone" />  -->
												<p style="width:292px;margin:0;"  onblur="checkRelationCellphone_table()"  name="databaseInfo.linkmanPhone"><input type="hidden" value="${databaseInfo.telephone2}" id="relationCellphone_table">${databaseInfo.telephone2}</p> 
												</td>
											</tr>
											<tr>
												<td class="tit-td">办公电话：</td>
												<td><p type="text" style="width:292px;margin:0;" id="relationName_table2"  name="databaseOrgInfo.principalName">${databaseInfo.mobile2}</p>
												</td>
											</tr>
											<tr>
												<td class="tit-td">电子邮箱：</td>
												<td>
<!-- 												<input type="text" style="width:292px;" id="relationEmail_table" onblur="checkRelationEmail_table()" value="${databaseInfo.email2}" name="databaseInfo.linkmanEmail" /> -->
													<p style="width:292px;margin:0;"  onblur="checkRelationEmail_table()"  name="databaseInfo.linkmanEmail"><input type="hidden" value="${databaseInfo.email2}" id="relationEmail_table">${databaseInfo.email2}</p>
												</td>
											</tr>
											<tr>
												<td></td>
												<td>
													<input type="button" id="submitBaseModify_id" class="btn btn-primary" value="报送系统中修改" onclick="toBaoSong();" />
												</td>
											</tr>
										</table>
										
										
								<span style="margin-left:180px;color: '#6A5ACD';">------------------------------------------------------------------------------------------------------------------</span><br><br>		
								<span style="margin-left:180px;font-size: 16px;">新增联系人：</span>	
										<input type="hidden" id="TB" value="TB" >	
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">姓 名：</td>
												<td><input type="text" style="width:292px;" id="relationNametwo_table" onblur="checkRelationNametwo_table()" value="${databaseInfo.linkmanNameTwo}" name="databaseInfo.linkmanNametwo" />
												</td>
											</tr>
											<tr>
												<td class="tit-td">手机：</td>
												<td><input type="text" style="width:292px;" id="relationCellphonetwo_table" onblur="checkRelationCellphonetwo_table()" value="${databaseInfo.linkmanPhoneTwo}" name="databaseInfo.telephone3" /> 
											</td>
											</tr>
											<tr>
												<td class="tit-td">电子邮箱：</td>
												<td><input type="text" style="width:292px;" id="relationEmailtwo_table" onblur="checkRelationEmailtwo_table()" value="${databaseInfo.linkmanEmailTwo}" name="databaseInfo.email3" /></td>
											</tr>
											<tr>
												<td></td>
											</tr>
										</table>
										<table class="basic-tab column-mar1" cellpadding="0" cellspacing="0">
											<tr>
												<td class="tit-td">姓 名：</td>
												<td><input type="text" style="width:292px;" id="relationNamethree_table" onblur="checkRelationNamethree_table()" value="${databaseInfo.linkmanNameThree}" name="databaseInfo.linkmanNamethree" />
												</td>
											</tr>
											<tr>
												<td class="tit-td">手机：</td>
												<td><input type="text" style="width:292px;" id="relationCellphonethree_table" onblur="checkRelationCellphonethree_table()" value="${databaseInfo.linkmanPhoneThree}" name="databaseInfo.telephone3" /> 
											</td>
											</tr>
											<tr>
												<td class="tit-td">电子邮箱：</td>
												<td><input type="text" style="width:292px;" id="relationEmailthree_table" onblur="checkRelationEmailthree_table()" value="${databaseInfo.linkmanEmailThree}" name="databaseInfo.email4" /></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="button" id="submitBaseModify_id" class="btn btn-primary" value="保 存" onclick="submitBaseOrgModify();" />
												</td>
											</tr>
										</table>
										<!-- 	</form> -->
									</div>
									<!--/basicInfo-->
				</c:if>
							</div>
							<!--/tab-content-->
						</div>

					</div>
				</div>
			   
			<!-- /page-content -->
		</div>
			<%@ include file="modal.jsp"%>
			<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/manageTbInfo.js"></script>
			<!-- 站点信息js -->
			<script language="javascript" type="text/javascript" src="<%=path %>/js/manageInfo/websiteInfoTb.js"></script>
			<!-- 校验工具类js -->
			<script language="javascript" type="text/javascript" src="<%=path %>/js/validateUtil.js"></script>
			
		</div>
	</div>
 
	<script type="text/javascript" src="<%=path%>/js/flexslider/jquery.flexslider-min.js"></script>
	
</body>
</html>
