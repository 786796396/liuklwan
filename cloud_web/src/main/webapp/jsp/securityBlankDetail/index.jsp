<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>空白栏目</title>
	<%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/heade.jsp"%>
  </head>
<body class="bg_f5f5f5">
<input type ="hidden" value="${sessionScope.shiroUser.siteCode }" id = "siteCode">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="9" scope="request"/>
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
                  <li class="active">空白栏目</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <p>1.考察网站一个检测周期（如一个月或一个季度）内空白栏目的数量；</p>
                                    <p>2.空白栏目，即无内容的栏目；</p>
                                    <p>3.空白栏目数量≥3个即触发预警。</p>
                                    <p>4.本服务是周期性检测服务，在服务周期内才会有检测结果</p>
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
                <div class="span5 pie-chart">
                    <h3>本期监测进度</h3>
                    <div class="time-font"></div>
                    <div class="pie-chart-con" >
                    	<c:if test="${sessionScope.shiroUser.iscost==1}">
                    		<div id="blank_detail_pie_id" style="height: 220px;width: 100%;"></div>
                    	</c:if>
                    </div>
                </div>

                <div class="z_box2 span7">
                    <h3 class="qu_s">空白栏目趋势</h3>
                    <div class="line_chart" >
	                    <c:if test="${sessionScope.shiroUser.iscost==1}">
	                    	<div id="blank_detail_line_id" style="height: 220px;width: 100%;"></div>
	                    </c:if>
                    </div>
                </div>

            </div><!-- /mar1 -->
            <div class="row2">
            	<div class="tab_header row">
                	<h3>空白栏目检测结果</h3>
                	 <input id="service_peroid_id" type="hidden" style="display:none">
                    <div class="tab-hfont1" id="blank_channel_period_id"></div>
                    <div class="tab-hfont1" id="blank_channel_total_id"></div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="prependedInput span2" id="prependedInput" type="text" placeholder="输入栏目名称..."  onchange="linkAllBlankCycleChange()">
                      <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<span style="font:normal 14px/30px '微软雅黑'; color:#fff;">检测周期：</span>
	                    	<select id="scanCycleId" onchange="blankDetailMonitoringPeriod()">
							</select>
                    	</c:if>
                    	<input type="hidden" id="servicePeriodIdZZ" value="${servicePeriodIdZZ}" >
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                   		<div id="blank_channel_excel" class="page-btn1"  onclick="blankDetailExcel()"><i></i>导出列表</div>
                	</c:if>
                </div>
                <div class="tab_box1 row">
                	<c:if test="${sessionScope.shiroUser.iscost==1}">
                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
	                    <table cellpadding="0" cellspacing="0" class="table t-tab1 hide" id="table_blank_channel_id">
	                    	<thead>
	                            <tr>
	                            	<th class="th_left" style="">序号</th>
	                                <th class="channelName th_left" style="">栏目名称</th>
	                                <th class="th_left" style="">栏目URL</th>
	                                <th class="th_left">路径</th>
	                                <th class="th_left">问题描述</th>
	                                <th class="th_left">问题发现时间</th>
	                                <th style="">截图</th>
	                            </tr>
	                         </thead> 
	                        <tbody id="tbody_blank_channel_id">
	                        </tbody>
	                    </table>
	                    <div class="zanwsj" id="table_blank_channel_hide"></div>
                    </c:if>
                </div>
            </div><!-- /tab2 -->
          <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/security/security_blank_detail.js"></script> 
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />

</body>
</html>
