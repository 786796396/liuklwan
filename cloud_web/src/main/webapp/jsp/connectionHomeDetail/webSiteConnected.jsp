<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>日常监测-首页连通率</title>
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
	<c:set var="ba_index" value="201" scope="request"/>
	<c:set var="menu" value="#menuJc" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	<!--左侧导航       end  -->
        <div class="page-content paddt-27">
			<div class="row-fluid">
			           <div class="row bread-crumbs daily-monitor-box-m">
			               <div class="bread-crumbs-content">
			                   <span class="cor-0498e4">日常监测</span>
			                   <i class="cor-0498e4">></i>
			                   <span>首页连通率</span>
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
			                    <div class="fl select-box">
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
			                    <div class="fl checkbox-box">
			                        <label>
			                            <input name="checkboxId" type="checkbox" value="5">
			                            	不连通率超过5%
			                        </label>
			                    </div>
			                   <div class="fr export-box export-all">
			                       <i></i>
			                     	  <span onclick="webSiteConnectedExcel()">全部导出</span>
			                   </div>
			                   <div class="fr search-box input-b">
			                       <input id="keyId" type="text" class="search-input fl"   value="输入网站名称和标识码"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='输入网站名称和标识码'}" />
			                       <i class="fl"></i>
			                   </div>
			               </div>
			           </div>
			           <div class="data-show">
			               <table id="table-webSort"  cellpadding="0" cellspacing="0" class="table">
				               <thead>
				                   <tr>
				                       	<th class="w15p first-row" style="width:15%; padding-left:26px;">网站名称</th>
				                        <th class="w20p th-center" style="width:20%;text-align: center;">首页URL</th>
				                        <th id="thId" class="w10p th-center jiancepl" style="width:10%; text-align: center; position:relative;">监测频率
				                           <div id="divId" style="display:none; position:absolute;width:272px;__height: 38px;padding:5px 10px 5px 10px;font:normal 12px/38px 'Microsoft Yahei';color: #6a6f7b;background: #fff;border: 1px solid #c9c9c9;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;z-index: 5;">
				                                	<p style='margin:0;padding:0;line-height:16px;text-align:left;'>重点门户网站为5分钟监测一次；</p>
													<p style='margin:0;padding:0;line-height:16px;text-align:left;'>其他付费网站为15分钟监测一次；</p>
				 									<p style='margin:0;padding:0;line-height:16px;text-align:left;'>长期连不通或监测异常状态的网站为1天监测一次。</p>
				                            </div> 
						                </th>
				                        <th class="w10p th-center" style="width:10%;text-align: center;">连接总次数<i class="tab_angle"></i></th>
				                        <th class="w10p th-center" style="width:10%;text-align: center;">连通次数<i class="tab_angle"></i></th>
				                        <th class="w10p th-center" style="width:10%;text-align: center;">连通率<i class="tab_angle"></i></th>
				                        <th class="w10p th-center" style="width:10%;text-align: center;">连不通次数<i class="tab_angle"></i></th>
				                        <th class="w10p th-center" style="width:10%;text-align: center;">不连通率<i class="tab_angle"></i></th>
				                   </tr>
				               </thead>
				           </table>
			           <table id="table-Sum" class="table"></table>
			           <table id="table-web" class="table">
			           		   <tbody id="webSiteConnectedTbody">
			               		</tbody> 
			           </table>
			           </div> 
			          </div>
			          <!--底部    start-->
		            <%@ include file="/common/footer.jsp"%>
		            <!--底部    end-->
					    </div>
           
        </div><!-- /page-content -->
    </div>
</div>  
<script type="text/javascript" src="<%= path %>/js/connection/web_site_connected.js"></script>
</body>
</html>