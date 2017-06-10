<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/dropload.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/overview.css" />
<script type="text/javascript" src="<%=path%>/js/bigDataAnalysis/bigData.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/siteDataOverview/siteDataOverview.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
<title>站点数据概览</title>
</head>
<body class="bg_f5f5f5">
<input id="siteCode" type="hidden" value="${siteCode}">
<input id="level" type="hidden" value="${level}">
<input id="databaseTreeInfoId" type="hidden" value="${databaseTreeInfoId}">
<input id="webName" type="hidden" value="${sessionScope.shiroUser.userName}">
	<div class="page_wrap">
		<%@ include file="/common/top.jsp"%>
		<div class="main-container container ">
			<div class="row-fluid">
				<c:set var="ba_index" value="502" scope="request" />
				<c:set var="menu" value="#menuBg" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
				<div class="page-content">
					<h4 class="main_title">站点数据概览</h4>
					<!--全国健康指数部分开始-->
					<%@ include file="/common/jkzs.jsp" %>
					<!--全国健康指数部分结束-->
					<div class="ViewData_content">
		                <div class="vdc_top clearfix top_nav">
		                    
		                    
		                    <!--左边开始-->
                            <div class="nav_left fl">
                                <ul class="clearfix">
                                
                                 <li class="fl bread" id="nextNode1" style="display:none">
                                        <span id="nextNodeSpan1">bm0100 <i></i></span>
                                          
                                        <!--移入显示块开始-->
<!--                                         <ul id="nextNodeUl1"> -->
<!--                                             <li>1</li> -->
<!--                                             <li>2</li> -->
<!--                                             <li>3</li> -->
<!--                                         </ul> -->
                                        
                                    </li>
                                <!--移入显示块结束-->
<!--                                         <li class="fl marlr_8">/</li> -->
                                    <li class="fl bread" id="nextNode2" style="display:none">
                                        <span id="nextNodeSpan2">广东省<i></i></span>
                                          
                                        <!--移入显示块开始-->
                                        <ul id="nextNodeUl2">
<!--                                             <li>1</li> -->
<!--                                             <li>2</li> -->
<!--                                             <li>3</li> -->
                                        </ul>
                                       
                                    </li>
                                     <!--移入显示块结束-->
<!--                                         <li class="fl marlr_8">/</li> -->
                                    
                                    <li class="fl bread" id="nextNode3" style="display:none">
                                        <span  id="nextNodeSpan3">广州市 <i></i></span>
                                        
                                        <!--移入显示块开始-->
                                        <ul id="nextNodeUl3">
<!--                                             <li>1</li> -->
<!--                                             <li>2</li> -->
<!--                                             <li>3</li> -->
                                        </ul>
                                        
                                    </li>
                                    <!--移入显示块结束-->
<!--                                         <li class="fl marlr_8">/</li> -->
                                    <li class="fl bread" id="nextNode4" style="display:none">
                                        <span  id="nextNodeSpan4">天河区  <i></i></span>
                                       
                                        <!--移入显示块开始-->
                                        <ul id="nextNodeUl4">
<!--                                             <li>11</li> -->
<!--                                             <li>22</li> -->
<!--                                             <li>33</li> -->
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                </ul>
                            </div>
                            <!--左边结束-->
		                    
		                    
		                    
		                    <!--右边开始-->
		                    <div class="lb_top2 clearfix fr">
		                        <div class="report_list fr" id="exportExcel" onclick="exportDate()">
		                            <i class="report_icon"></i>
		                            <span>导出列表</span>
		                        </div>
		                        <div class="search_box fr">
		                            <input type="text" id="keyWord_id" placeholder="输入网站名称或者标识码"/>
		                            <i></i>
		                        </div>
		                    </div>
		                    <!--右边开始-->
		                </div>
		                <!--表部分开始-->
		                <div class="lb_table" id="tableDiv">
		               			 <table  id="bodyBSort"> 
                                         <thead> 
                                         	 <tr id="titleB"> 
                                             </tr>
                                       </thead>
                                 </table>
                                 <table id="bodyB" class="table">
                                       <tbody id="bodys">
                                        </tbody> 
                       		   	 </table> 
                       		   	 
                       		   	 <table  id="clickk"> 
                                         <thead> 
                                         	 <tr> 
		                           				<th style="width:10%; text-align:left;">序号</th>
					                            <th style="width:20%; text-align:left;">网站名称</th>
					                            <th style="width:10%;">网站标识码</th>
					                            <th style="width:10%;">省</th>
					                            <th style="width:10%;">市</th>
					                            <th style="width:10%;">县</th>
					                            <th style="width:10%;">报送状态</th>
					                            <th style="width:20%;">不监测原因</th>
					                            <th style="width:20%;"><span title="最近7天连通次数占总连接次数的比例">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监测连通率（占比）</span></th>
                                             </tr>
                                       </thead>
                                 </table>
		                    <!-- 统计概览 -->
		                    <!-- <table id="result_table_idSort" class="table result_table_idSort" cellpadding="0" cellspacing="0" style="margin-bottom:0px;">
		                        <tr  id="titleB">
		                            <th style="width:10%; padding-left: 10px; padding-right: 10px; text-align:left;">序号</th>
		                            <th style="width:20%; text-align: left;">组织单位名称/编码</th>
		                            <th class="numOrder" style="width:10%; margin-right: 7.6%;">上报站点  <i class="tab_angle"></i></th>
		                            <th class="numOrder" style="width:10%; margin-right: 7.6%;">关停站点  <i class="tab_angle"></i></th>
		                            <th class="numOrder" style="width:10%; margin-right: 7.6%;">例外站点  <i class="tab_angle"></i></th>
		                            <th class="numOrder" style="width:10%; margin-right: 10%;">暂不监测站点  <i class="tab_angle" style="right: 0px;"></i></th>
		                            <th class="numOrder" style="width:10%">全面监测站点  <i class="tab_angle" style="right: 12px;"></i></th>
		                        </tr>
		                    </table>
		                    <table id="result_table_id" value="keyWord_id" name="tableName" class="table">
		                        <tbody id="bodys">
		                            
		                        </tbody>
		                    </table> -->
		                    <!-- 站点数据概览 -->
							<!-- <table id="siteCode_result_table_id" border="0" cellpadding="0" cellspacing="0">
							<thead>
		                        <tr>
		                            <th style="width:10%; text-align:left;">序号</th>
		                            <th style="width:20%; text-align:left;">网站名称</th>
		                            <th style="width:10%;">网站标识码</th>
		                            <th style="width:10%;">省</th>
		                            <th style="width:10%;">市</th>
		                            <th style="width:10%;">县</th>
		                            <th style="width:10%;">报送状态</th>
		                            <th style="width:20%;">不监测原因</th>
		                            <th style="width:20%;"><span title="最近7天连通次数占总连接次数的比例">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;监测连通率（占比）</span></th>
		                        </tr>
		                        </thead>
								<tbody id="siteCode_result_table">
		                            
		                        </tbody>
							</table> -->
		                </div>
		                <div class="no-data" style="display:none" id="noDataDiv"><i></i>本功能为收费项目，请联系开普云监管服务团队  <span >4000-976-005</span></div>
		                <!--表部分结束-->
		            </div>
					<div style="padding-left:30px">
						<%@ include file="/common/footer.jsp"%>
					</div>
					<!--表格部分结束-->
				</div>
			</div>
		</div>
	</div>
</body>
</html>