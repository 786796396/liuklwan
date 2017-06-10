<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>业务系统连通性</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
  </head>
  <body>
  <!--头部       satrt  -->
  <%@ include file="/common/top_tb.jsp"%>
  <!--头部       end  -->
<div class="main-container container">
	<div class="row-fluid">
		<!--左侧导航       satrt  -->
		<c:set var="tb_index" value="3" scope="request"/>
		<c:set var="menu" value="#menuWzltx" scope="request"/>
		<%@ include file="/common/leftmenu_tb.jsp"%>
		<!--左侧导航       end  -->
        <div class="page-content">
            <c:if test="${sessionScope.shiroUser.iscost==0}">
                <div class="free-html">
                    <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
                </div>
            </c:if>
        	<div class="row">
                <ul class="breadcrumb">
                    <li><a href="#">网站连通性</a> <span class="divider">></span></li>
                    <li class="active">业务系统连通性</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <p style="margin-top:4px;">1.考察网站每天各个业务系统（在线申报系统、查询系统等）能否正常访问；</p>
                                    <p>2.每${que}扫描一次，每次访问时间≥15秒即为该业务系统不能访问。</p>
                                    <p>3. 重点门户网站为5分钟监测一次；</p>
	                                <p>4. 其他付费网站为15分监测一次；</p>
	                                <p>5. 长期连不通或监测异常状态的网站为1天监测一次。</p>
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

            <div class="row t_box5 mar1">

                <div class="z_box2">
                    <h3 class="qu_s">业务系统连通状况</h3>
                    <div class="line_chart" >
                    	<c:if test="${sessionScope.shiroUser.iscost==1}">
                   			<div id="business_line" style="height: 250px;width: 100%;margin-top: -25px;"></div>
                   		</c:if>
                   		<input type="hidden" id="pie_contral_id" />
                    </div>
                    
                </div>

            </div><!-- /row2 -->
            <div class="row mar1">
                <input type="hidden" id="time_yes_date" value="${yesterdayStr}"/>
            	<h3 class="info_fx_h3 bg-13ba59">业务系统连通统计分析 <input class="datepicker-input" type="text" id="datepicker" readonly="readonly"/></h3>

                <div class="t-tab2">
                	<input type="hidden" id="bus_time_date_begin"/>
                	<c:if test="${sessionScope.shiroUser.iscost==1}">
	                    <table cellspacing="0" cellpadding="0" class="table hide" id="business_table_hide">
	                        <tbody id="tbody_tongji_contral" class="tbody_tongji hide">
	                            <tr>
	                                <th rowspan="2" class="th_left">业务系统名称</th>
	                                <th rowspan="2" style="width:200px;">URL</th>
	                                <th colspan="2">成功（＜15秒）</th>
	                                <th colspan="2">超时（≥15秒）</th>
	                                <th rowspan="2">总次数</th>
	                            </tr>
	                            <tr class="th-sub">
	                                <th>次数</th>
	                                <th>占比</th>
	                                <th class="bg-e65e5e">次数</th>
	                                <th class="bg-e65e5e">占比</th>
	                            </tr>
	                            </tbody>
	                            <tbody id="conlist">
	                            </tbody>
	                            <tbody>
	                            <tr id="conSum">
	                            </tr>
	                        </tbody>
	                    </table>
	                    <div class="zanwsj" id="business_table_hide1">业务系统无不连通状况</div>
                    </c:if>
                </div>
                
                
            </div><!-- /row3 -->
            
            <div class="row mar1">
            	<div class="tab_header row">
            		<div class="tab-hfont3" style="display: none" id="str">${dateStr}</div>
                	<h3>业务系统连通性监测结果<input class="datepicker-input" type="text" id="currentDateker" readonly="readonly"/></h3>
                    <div class="input-prepend">
                      <%-- <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span> --%>
                      <input class="prependedInput span2" id="keyInput" type="hidden" placeholder="输入问题描述编码...">
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<div id="out_excel_id" class="page-btn1" onclick="businessExcel()" style="display:none;"><i></i>导出列表</div>
                    </c:if>
                </div>
                <div class="tab_box1 row">
                	<c:if test="${sessionScope.shiroUser.iscost==1}">
                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
	                    <table id="business_table" cellpadding="0" cellspacing="0" class="table t-tab1 hide">
	                    </table>
	                     <div class="zanwsj" id="business_table_hide2">业务系统无不连通状况</div>
                     </c:if>
                </div>
                <div id="hidden_id"></div>
            </div>
             <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<%@ include file="/common/http.jsp"%>
<script type="text/javascript" src="<%=path%>/js/connection/business_table_fenye.js"></script>
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
  </body>
</html>
