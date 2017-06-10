<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>深度检测-疑似错别字</title>
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
	<c:set var="ba_index" value="306" scope="request"/>
	<c:set var="menu" value="#menuDeJc" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	<!--左侧导航       end  -->
        <div class="page-content paddt-27">
			<div class="row-fluid">
			           <div class="row bread-crumbs daily-monitor-box-m">
			               <div class="bread-crumbs-content">
			                   <span class="cor-0498e4">深度检测</span>
			                   <i class="cor-0498e4">></i>
			                   <span>疑似错别字</span>
			               </div>
			           </div>
			             <div class="free-html" id="chargeId" style="display: none">
		                    <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
		                </div>
			           <div id="searchId" style="display: none" class="row daily-monitor-box-m conditions">
			               <div class="conditions-content clearfix">
			               	<input id="yesterdayId" type="hidden" value="${yesterday}">
			                   <div class="fl select-box">
			                        <span id="aaaTypeText">错误类型</span>
		                            <input id="aaaTypeVal" type="hidden" value="0">
		                            <i></i>
		                            <!--移入显示块开始-->
		                            <ul id="aaaTypeUl" style="display: none;">
		                                <li value="0" name="monitorType">全部</li>
		                                <li value="1" name="monitorType">一般错误</li>
		                                <li value="3" name="monitorType">严重错误</li>
		                            </ul>
		                            <!--移入显示块结束-->
			                    </div>
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
			                           <input class="datepicker-input" type="text" id="startDate" readonly="readonly"/>
			                       </div>
			                       <span class="fl todate">至</span>
			                       <div class="input-b fl">
			                           <input class="datepicker-input" type="text" id="endDate" readonly="readonly"/>
			                       </div>
			                   </div>
			                   <div class="fl total-box">
			                      	 共：<span class="cor-f20707" id="sizeId"></span>条
			                   </div>
			                   <div class="fr export-box export-all">
			                       <i></i>
			                     	  <span onclick="correctTableExcel()">全部导出</span>
			                   </div>
			                   <div class="fr search-box input-b">
			                       <input id="keyId" type="text" class="search-input fl" value="输入网站名称和标识码"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='输入网站名称和标识码'}"/>
			                       <i class="fl"></i>
			                   </div>
			               </div>
			           </div>
			           <div id="contentId" style="display: none" class="data-show">
			               <table id="table-correctSort" cellpadding="0" cellspacing="0" class="table">
				               <thead>
				                   <tr>
				                       <th class="w30p first-row" style="width:30%;padding-left:26px;">网站名称</th>
				                       <th class="w30p th-center" style="width:30%; text-align:center;">首页URL</th>
				                       <th class="w30p th-center" style="width:30%; text-align:center;">问题个数<i class="tab_angle"></i></th>
				                   </tr>
				               </thead>
				           </table>
				           <table id="table-Sum" class="table"></table>
				           <table id="table-correct" class="table">
				           		       <tbody id="correctTbody">
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
<script type="text/javascript" src="<%= path %>/js/correctContent/correct_content_indexOrg.js"></script>
</body>
</html>