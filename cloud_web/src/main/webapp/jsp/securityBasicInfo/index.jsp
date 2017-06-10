<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>基本信息</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
  </head>
<body>
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="19" scope="request"/>
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
                  <li class="active">基本信息</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <p>1.考察监测时间点前2周内，动态、要闻类信息，是否更新；</p>
                                    <p>2.考察监测时间点前6个月内，通知公告、政策文件类信息，是否更新；</p>
                                    <p>3.考察监测时间点前1年内，人事、规划计划类信息，是否更新；</p>
                                    <p>4.考察机构设置及职能、动态、要闻、通知公告、政策文件、规划计划、人事等信息是否准确。</p>
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

        	<!-- / mar1 -->
            <div class="row1">
            	<div class="tab_header row">
                	<h3>基本信息监测结果</h3>
                	<input type="hidden" id="service_period_id" style="display: none">
                    <div class="tab-hfont1" id="basic_channel_period_id"></div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="prependedInput span2" id="prependedInput" type="text" placeholder="输入栏目名称...">
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<div id="response_excel_out" class="page-btn1" onclick="basicInfoExcel()"><i></i>导出列表</div>
                    </c:if>
                </div>
                <div class="tab_box1 row">
                <c:if test="${sessionScope.shiroUser.iscost==1}">
                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
	                    <table cellpadding="0" cellspacing="0" class="table t-tab1 hide" id="table_basic_channel_id">
	                        <thead>
	                            <tr>
	                            	<th class="th_left" style="width:40px;">序号</th>
	                                <th class="th_left" style="width:200px;">栏目名称</th>
	                                <th class="th_left" style="width:200px;">问题类型</th>
	                                <th class="th_left">问题描述</th>
	                                <th class="th_left" style="width:200px;">周期时间</th>
	                                <th class="th_left" style="width:150px;">修改时间</th>
	                                <th style="width:55px;">截图</th>
	                            </tr>
	                         </thead>
	                        <tbody id="tbody_basic_channel_id">
	                        </tbody>
	                    </table>
	                    <div class="zanwsj" id="table_response_channel_hide1">暂无基本信息数据</div>
                    </c:if>
                </div>
            </div>
            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/security/security_basic_info.js"></script>
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />

</body>
</html>
