<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>深度检测-栏目监测</title>
    <%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/tabChange.css"/>
 	<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css" />
	<script language="javascript" type="text/javascript" src="<%=path%>/js/detection.js"></script>
 	<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
 	</head>
  <body class="bg_f5f5f5">
  <!--头部       satrt  -->
 <%@ include file="/common/top.jsp"%>
  <!--头部       end  -->
<div class="main-container container">
	<div class="row-fluid">
	<!--左侧导航       satrt  -->
	<c:set var="ba_index" value="304" scope="request"/>
	<c:set var="menu" value="#menuDeJc" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	<!--左侧导航       end  -->
	<div class="row-fluid">
				<input id="colId" type="hidden" value="${col}"/>
				<div class="page-content paddt-27">
					<!--需要填充的内容开始-->
					<!--栏目检测 头部标题部分s-->
					<div class="row bread-crumbs daily-monitor-box-m">
						<div class="bread-crumbs-content">
							<span class="cor-0498e4">深度检测</span> <i class="cor-0498e4">></i>
							<span>栏目检测</span>
						</div>
					</div>
					<!--栏目检测 头部标题部分e-->
					<div class="five_tab_part">
						<div class="five_tp_top">
							<div class="every_block clearfix">
								<div class="fl every_tip" id="tab0" onclick="keyColumnClick(0)" style="width:150px;">关键栏目连通性</div>
	                        	<div class="fl every_tip" id="tab1" onclick="keyColumnClick(1)" style="width:150px;">业务系统连通性</div>
	                        	<div class="fl every_tip" id="tab2" onclick="keyColumnClick(2)" style="width:150px;">栏目更新情况</div>
							</div>
							<div class="green_line"></div>
						</div>
					</div>
					<div class="row daily-monitor-box-m conditions">
						<div class="conditions-content clearfix">
							<div class="fl select-box">
								 <span id="siteTypeText">网站类别</span>
	                             <input id="siteTypeVal" type="hidden" value="0">
	                            <i></i>
	                            <!--移入显示块开始-->
	                            <ul id="siteTypeUl" style="display: none;">
	                             	<li value="0" name="monitorType">网站类别</li>
	                            	<c:forEach items="${dicList}" var="dic">
										 <li value="${dic.value}" name="monitorType">${dic.name}</li>
									</c:forEach>
	                            </ul>
	                            <!--移入显示块结束-->
							</div>
							<div id="aaaTypeId" class="fl select-box">
								 <span id="aaaTypeText">检测频率</span>
	                            <input id="aaaTypeVal" type="hidden" value="0">
	                            <i></i>
	                            <!--移入显示块开始-->
	                            <ul id="aaaTypeUl" style="display: none;">
	                                <li value="0" name="monitorType">检测频率</li>
	                                <li value="1" name="monitorType">5分钟一次</li>
	                                <li value="3" name="monitorType">15分钟一次</li>
	                                <li value="6" name="monitorType">1天一次</li>
	                            </ul>
	                            <!--移入显示块结束-->
							</div>
							<div class="fl period-box">
								<input id="yesterdayId" type="hidden" value="${yesterday}">
	                            <span class="fl tit">监测时间：</span>
	                            <div class="input-b fl">
			                        <input class="datepicker-input" type="text" id="startDate" readonly="readonly"/>
	                            </div>
	                            <span class="fl todate">至</span>
	                            <div class="input-b fl">
	                                <input class="datepicker-input" type="text" id="endDate" readonly="readonly"/>
	                            </div>
							</div>
							<div class="fl total-box">
								共：<span class="cor-f20707" id="sizeId"></span> 条
							</div>
							<div class="fr export-box export-all">
								<i></i>
	                            <span onclick="keyColumnExcel()">全部导出</span>
							</div>
							<div class="fr search-box input-b">
								<input id="keyId" type="text" class="search-input fl"  value="输入网站名称和标识码"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='输入网站名称和标识码'}"/>
	                            <i class="fl"></i>
							</div>
						</div>
					</div>
					<div class="data-show">
			               <table id="table-webSort"  cellpadding="0" cellspacing="0" class="table">
				           </table>
				           <table id="table-Sum" class="table"></table>
				           <table id="table-web" class="table">
				           		   <tbody id="keyColumnTbody">
				               		</tbody>
				           </table>
			           </div>
					 <!--底部    start-->
		            <%@ include file="/common/footer.jsp"%>
		            <!--底部    end-->
				</div>
				<!-- /page-content -->
			</div>
    </div>
</div>  
<script type="text/javascript" src="<%= path %>/js/connection/key_column.js"></script>
</body>
</html>