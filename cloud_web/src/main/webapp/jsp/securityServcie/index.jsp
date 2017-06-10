<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 	<title>服务不实用</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
  </head>
  
<body class="bg_f5f5f5">
<input type ="hidden" value="${sessionScope.shiroUser.siteCode }" id = "siteCode">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="11" scope="request"/>
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
                  <li class="active">服务不实用</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <p style="margin-top:10px;">考察办事指南要素是否准确、完整。常见的是办事指南要素内容不准确，或办事指南要素类别缺失，如：事项名称、设定依据、申请条件、办理材料、办理地点、办理时间、联系电话、办理流程等缺失或内容不准确。</p>
                                    <p>本服务是周期性检测服务，在服务周期内才会有检测结果。</p>
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

        	<!-- /row1 -->
            <div class="row t_box5" style="display:none;">
                <div class="span4 pie-chart" style="display:none;">
                    <h3>本期检测进度</h3>
                    <div class="pie-chart-con">
	                    <c:if test="${sessionScope.shiroUser.iscost==1}">
	                        <div id="service_pie_id" style="height: 220px; width: 100%;"></div>
	                    </c:if>
                    </div>
                </div>

                <div id="qusBox" class="z_box2" >
                    <h3 class="qu_s" id="service_period_total"></h3>
                    <div class="pie-chart-tab pie-chart-mar2" >
	                    <c:if test="${sessionScope.shiroUser.iscost==1}">
	                        <table cellpadding="0" cellspacing="0" class="table" id="service_table_id" >
	                            <tbody id="tbody_serviceProblemType_id">
	                            </tbody>
	                            <tfoot id="tfoot_serviceProblemType_id">
	                            </tfoot>
	                        </table>
	                    </c:if>
                    </div>
                   <input type="hidden" id="service_period_id">
                </div>

            </div><!-- /row2 -->
            <div class="row2 t_box6" style="display:none;">
                  <div class="t-chart-header row">
                    <h3 class="t-h3">问题趋势</h3>
                  </div><!-- /chart-header -->
                  <div class="tongj_chart" >
                  		<c:if test="${sessionScope.shiroUser.iscost==1}">
		                  <div id="service_unuse_bar_id" style="height:260px ;width:100%;">
		                  </div>
	                  </c:if>
                  </div><!-- /tab-content -->
                
            </div><!-- /row -->
            <div class="row3">
            	<div class="tab_header row">
                	<h3>服务不实用检测结果</h3>
                		<div class="fl unlink-count margl25">
							共： <span id="sizeId" ></span> 条
						</div>
                    <div class="tab-hfont1" id="service_service_period_id"></div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="prependedInput span2" id="prependedInput" type="text" placeholder="输入办事事项..." onchange="worklike()">
                      <input type="hidden" id = "servicePeriodIdZZ" value="${servicePeriodIdZZ }">
                      <c:if test="${sessionScope.shiroUser.iscost==1}">
                      <span style="font:normal 14px/30px '微软雅黑'; color:#fff;">检测周期：</span>
                      <select id="scanCycleId" onchange="affairsChange()">
					  </select>
                      </c:if>
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<div class="page-btn1 hide" id="table_security_service_excel" onclick="serviceExcel()"><i></i>导出列表</div>
               		</c:if>
                </div>
                <div class="tab_box1 row">
               		<c:if test="${sessionScope.shiroUser.iscost==1}">
                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
	                    <table cellpadding="0" cellspacing="0" class="table t-tab1 hide" id="table_security_service_id">
	                       <thead>
	                        	<tr>
	                            	<th class="th_left" style="width:56px;">序号</th>
	                                <th class="th_left" style="width:216px;">办事事项/地址</th>
	                                <th class="th_left" style="width:116px;">问题类型</th>
	                                <th class="th_left">问题描述</th>
	                                <th class="th_left" style="width:216px;">父页面url</th>
	                                <th class="th_left">问题发现时间</th>
	                                <th style="width:71px;">截图</th>
	                            </tr>
	                       </thead>
	                        <tbody id="tbody_service_list_id">
	                        </tbody>
	                    </table>
	                    <div class="zanwsj" id="table_security_service"></div>
                    </c:if>
                </div>
            </div>
           <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script>
<script language="javascript" type="text/javascript" src="<%= path %>/js/security/security_servcie.js"></script>
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  /> 

</body>
</html>
