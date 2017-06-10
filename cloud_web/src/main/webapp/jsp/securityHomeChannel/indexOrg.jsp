<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>日常监测-首页不更新</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/>
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
	<c:set var="ba_index" value="203" scope="request"/>
	<c:set var="menu" value="#menuJc" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	<!--左侧导航       end  -->
   <div class="page-content paddt-27">
			<div class="row-fluid">
			           <div class="row bread-crumbs daily-monitor-box-m">
			               <div class="bread-crumbs-content">
			                   <span class="cor-0498e4">日常监测</span>
			                   <i class="cor-0498e4">></i>
			                   <span>首页不更新</span>
			               </div>
			           </div>
			           <div class="row daily-monitor-box-m conditions">
			               <div class="conditions-content clearfix">
			               	<input id="yesterdayId" type="hidden" value="${yesterday}">
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
			                   <div class="fl period-box">
			                       <span class="fl tit">监测时间：</span>
			                       <div class="input-b fl">
			                           <input class="datepicker-input" type="text" id="scanDate" readonly="readonly"/>
			                       </div>
			                   </div>
			                   <div class="fl total-box">
			                      	 共：<span class="cor-f20707" id="sizeId"></span>条
			                   </div>
			                    <div class="fl checkbox-box">
			                        <label>
			                            <input name="checkboxId" type="checkbox" checked="checked" value="14">
			                            	超过2周未更新
			                        </label>
			                    </div>
			                   <div class="fr export-box export-all">
			                       <i></i>
			                     	  <span onclick="securityHomeTableExcel()">全部导出</span>
			                   </div>
			                   <div class="fr search-box input-b">
			                       <input id="keyId" type="text" class="search-input fl" value="输入网站名称和标识码"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='输入网站名称和标识码'}"/>
			                       <i class="fl"></i>
			                   </div>
			               </div>
			           </div>
			           <div class="data-show">
			               <table id="table-securityHomeSort" cellpadding="0" cellspacing="0" class="table">
			               <thead>
			                   <tr>
			                       <th class="w15p first-row" style="width:15%;padding-left:26px;">网站名称</th>
			                       <th class="w20p th-center" style="width:20%; text-align:center;">首页URL</th>
			                       <th class="w10p th-center" style="width:10%; text-align:center;">最后更新日期</th>
			                       <th class="w10p th-center" style="width:10%; text-align:center;">未更新天数<i class="tab_angle"></i></th>
			                       <th class="w10p th-center" style="width:10%; text-align:center;">首页不更新</th>
			                   </tr>
			               </thead>
			           </table>
			           <table id="table-securityHome" class="table">
			           		    <tbody id="securityHomeTbody">
			               </tbody>
			           </table>
			           </div>
			    </div>
           <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div>  
<script type="text/javascript" src="<%= path %>/js/securityHomeChannel/indexOrg.js"></script>
</body>
</html>