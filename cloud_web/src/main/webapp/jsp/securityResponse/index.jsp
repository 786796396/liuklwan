<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>互动回应差</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
  </head>
<body class="bg_f5f5f5;">
<input type ="hidden" value="${sessionScope.shiroUser.siteCode }" id = "siteCode">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="10" scope="request"/>
		<c:set var="menu" value="#menuBzwt" scope="request"/>
        <%@ include file="/common/leftmenu_tb.jsp"%>
        <div class="page-content">
            <c:if test="${sessionScope.shiroUser.iscost==0}">
                <div class="free-html">
                    <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
                </div>
            </c:if>
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">内容保障问题</a> <span class="divider">></span></li>
                  <li class="active">互动回应差</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <p>1.考察网站一个检测周期（如一个月或一个季度）内政务咨询类、调查征集类、互动访谈类等互动栏目的建设、使用情况；</p>
                                    <p>2.对政务咨询类栏目（在线访谈、调查征集、举报投诉类栏目除外）中的“公众信件”、“留言”需要及时答复处理的若超过三个月未回应即触发预警；</p>
                                    <p>3.本服务是周期性检测服务，在服务周期内才会有检测结果。</p>
                                </div>
                                <i class="angle1"></i>
                            </div>
                            <div class="ms-icon-wen">
                                <i class="i-wen">?</i>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="row" style="display:none;">
            	<div class="t_box3 row">
                    
                    	<div class="span4 pie-chart">
                        	<h3 class="chart-h3">本期监测进度</h3>
                        	<div class="pie-chart-con">
                        		<c:if test="${sessionScope.shiroUser.iscost==1}">
	                        		<div id="response_pie_id" style="height: 180px;width: 330px;">
	                        		</div>
                        		</c:if>
                            </div>
                        </div>
                        <div class="span8">
                        	<h3 class="pie-chart-h3">互动回应栏目开通情况</h3>
                        	<c:if test="${sessionScope.shiroUser.iscost==1}">
	                        	<div class="pie-chart-tab pie-chart-mar1" id="pie-chart-tab_total">
	                            	<table cellpadding="0" cellspacing="0" class="table">
	                                	<tbody id="table_body_list_id">
	                                    </tbody>
	                                    <tfoot id="table_foot_list_id">
	                                    </tfoot>
	                                </table>
	                            </div>
	                            <div class="zanwsj" style="display:none;" id="response_check_percent" >无互动回应差情况</div>
                            </c:if>
                        </div>
                </div>
            </div><!-- /row2 -->
            <!-- mar1 -->
            <div class="row2">
            	<div class="tab_header row">
                	<h3>互动回应差检测结果</h3>
                	<input type="hidden" id="service_period_id" style="display: none">
                    <div class="tab-hfont1" id="response_channel_period_id"></div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="prependedInput span2" id="prependedInput" type="text" placeholder="输入栏目名称..." onchange="responseScanningPeriodLlike()">
                   	  <input type="hidden" id = "servicePeriodIdZZ" value="${servicePeriodIdZZ }">
                      <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<span style="font:normal 14px/30px '微软雅黑'; color:#fff;">检测周期：</span>
                    	<select id="scanCycleId" onchange="responseScanningPeriodChange()">
						</select>
                    	</c:if>
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<div id="response_excel_out" class="page-btn1" onclick="responseChannelExcel()"><i></i>导出列表</div>
                    </c:if>
                </div>
                <div class="tab_box1 row">
                <c:if test="${sessionScope.shiroUser.iscost==1}">
                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
	                    <table cellpadding="0" cellspacing="0" class="table t-tab1 hide" id="table_response_channel_id">
	                        <thead>
	                            <tr>
	                            	<th class="th_left" style="width:40px;">序号</th>
	                                <th class="th_left" style="width:150px;">栏目类型</th>
	                                <th class="th_left" style="width:150px;">栏目名称</th>
	                                <th class="th_left" style="width:200px;">问题类型</th>
	                                <th class="th_left">问题描述</th>
	                                <th class="th_left">问题发现时间</th>
	                                <th style="width:55px;">截图</th>
	                            </tr>
	                         </thead>
	                        <tbody id="tbody_response_channel_id">
	                        </tbody>
	                    </table>
	                    <div class="zanwsj" id="table_response_channel_hide"></div>
                    </c:if>
                </div>
            </div>
            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/security/security_response.js"></script>
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />

</body>
</html>
