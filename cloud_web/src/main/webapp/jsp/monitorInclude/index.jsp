<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
   <title>搜索引擎收录</title>
   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/heade.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css" />
  </head>
  
<body class="bg_f5f5f5">
	<!--头部       satrt  -->
	<%@ include file="/common/top_tb.jsp"%>
	<!--头部       end  -->
	<input type="hidden" id="siteCode" value="${siteCode}">
<div class="main-container container mp mp-t">
	<div class="row-fluid">
		<c:set var="tb_index" value="30" scope="request"/>
		<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
           <h4 class="main_title">搜索引擎收录</h4>
           <div class="five_tab_part">
                <div class="five_tp_top">
                    <div class="every_block clearfix">
                        <div class="fl every_tip on">
                            <i class="baidu"></i>
                        </div>
<!--                         <div class="fl every_tip"> -->
<!--                             <i class="soug"></i> -->
<!--                         </div> -->
<!--                         <div class="fl every_tip"> -->
<!--                             <i class="sanlo"></i> -->
<!--                         </div> -->
                    </div>
                    <div class="green_line"></div>
                </div>
                
                <div class="five_tp_content">
                    <!--五个块-第一个开始-->
                    <div class="every_tab on">
                        <!--搜索引擎收录趋势开始-->
                        <div class="trend-SearchEngine clearfix">
                            <div class="trend-top">
                                <div class="trend-title fl fs-n-18-Mic color-3c3d45">
                                    搜索引擎收录趋势
                                </div>
                                <div class="trend-time fr fs-n-14-Mic color-646464">
                                    <span>时间控制：</span>
                                    <div class="seveal_inp clearfix">
                                        <div class="every_inp">
                                            <input type="radio" id="yesterday" value="-1" name="days" />
                                            <label for="yesterday">昨天</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="twoWeeks" value="-14" name="days" checked="checked"/>
                                            <label for="twoWeeks">2周</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="twoMonths" value="-60" name="days"/>
                                            <label for="twoMonths">2个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="fourMonths" value="-120" name="days"/>
                                            <label for="fourMonths">4个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="sixMonths" value="-180" name="days"/>
                                            <label for="sixMonths">6个月</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <!--搜索引擎收录趋势结束-->
		                <div class="t_box4 info_fx_con">
		                    <div class="line_chart">
		                    	<div id="includeChartDiv" style="height:350px; width:100%;">
		                        
		                        </div>
		                    </div>
		                </div>
                        </div>
                       
                        <!--搜索引擎收录明细开始-->
                        <div class="row mar1">
			            	<div class="tab_header row">
			                	<h3>引擎收录明细</h3>
			                	<span class="fl total" id="monitor_total_id">共1条</span>
			                    <%-- <div class="input-prepend">
			                      <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span>
			                      <input id="conlist_term" class="prependedInput span2"  placeholder="输入问题描述编码...">
			                    </div> --%>
			                    <div id="out_excel_id" class="page-btn1" onclick="monitorExcel();"><i></i>导出列表</div>
			                </div>
			                <div class="tab_box1 row">
			                	<input type="hidden" id="time_tool_min_date"/>
			                    <div class="dropload-load"><span class="loading"></span>加载中...</div>
			                    <table id="conn_tableSort" cellpadding="0" cellspacing="0" class="table t-tab1">
			                        <thead>
			                            <tr>
			                            	<th  style="width:5%; text-align:center;">序号</th>
			                                <th  style="width:20%; text-align:left;" class="numOrder">更新时间 <i class="tab_angle"></i></th>
			                                <th class="td-center numOrder" style="width:20%">站点收录网页数 <i class="tab_angle"></i></th>
			                                <th class="td-center numOrder" style="width:20%">域收录网页数<i class="tab_angle"></i></th>
			                            </tr>
			                          </thead>
			                          
			                    </table>
			                      <table id="conn_table" cellpadding="0" cellspacing="0" class="table t-tab1">
			                      <tbody id="conlist">
			                      </tbody>
			                    </table>
			                   
			                     <div class="zanwsj hide" id="conn_table_hide">未发现问题</div>
			                </div>
			            </div>
<!--                         <div class="detail-SearchEngine">
                            <div class="tab_header row">
                                <h3>搜索引擎收录明细</h3>
                                <span class="fl total">共1条</span>
                                <div class="input-prepend" tabindex="-1">
                                    <span class="add-on"><img alt="search" src="../../images/common/search.png"/></span>
                                    <input class="span2 prependedInput" type="text" placeholder="输入关键字...">
                                </div>
                                <div class="page-btn1"><i></i>导出Excel文件</div>
                            </div>
                            <div class="table_part">
                                <table>
                                    <thead>
                                        <tr>
                                            <th class="number">序号</th>
                                            <th>更新时间</th>
                                            <th>站点收录个数</th>
                                            <th>域收录个数</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="td-center">1</td>
                                            <td class="td-center">2016-11-01  16:50:55</td>
                                            <td class="td-center">125</td>
                                            <td class="td-center">125</td>
                                        </tr>
                                        <tr>
                                            <td class="td-center">2</td>
                                            <td class="td-center">2016-11-01  16:50:55</td>
                                            <td class="td-center">125</td>
                                            <td class="td-center">125</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div> -->
                        <!--搜索引擎收录明细结束-->
                    </div>
                    <!--五个块-第一个结束-->

                    <!--五个块-第二个开始-->
                    <div class="every_tab">
                        1
                    </div>
                    <!--五个块-第二个结束-->

                    <!--五个块-第三个开始-->
                    <div class="every_tab">
                       2
                    </div>
                    <!--五个块-第三个结束-->
                </div>
            </div>
                
            <%-- <div class="row">
            	<h3 class="info_fx_h3 bg-fff">首页更新趋势</h3>
                <div class="t_box4 info_fx_con">
                    <div class="line_chart">
                    	<div id="up_home_line_id" style="height:350px; width:100%;">
                        
                        </div>
                    </div>
                </div>
            </div><!-- /row3 -->
            <div class="row">
            	<div class="tab_header row">
                	<h3>首页更新明细</h3>
                    <div class="tab-hfont1 hide" id="table_data_homeUpdate_hide">共 <span></span> 条</div>
                    <div class="input-prepend hide" id="table_data_homeUpdate_hide1">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input  class="prependedInput span2" id="keyInput" type="text" placeholder="输入标题...">
                    </div>
                    <div class="page-btn1 hide" onclick="updateHomeExcel()" id="table_data_homeUpdate_hide2"><i></i>导出列表</div>
                </div>
                <div class="tab-sel1">
                    <div class="tab-sel1-con">
                        <div class="pull-left">时间选择</div>
                        <div class="pull-left radio-box1">
                        	<label><input type="radio" name="days"  value="1" checked="checked"/>&nbsp;昨天</label>
                            <label><input type="radio" name="days"  value="14" />&nbsp;2周</label>
                            <label><input type="radio" name="days" value="30" />&nbsp;1个月</label>
                            <label><input type="radio" name="days" value="90" />&nbsp;3个月</label>
                            <label><input type="radio" name="days" value="188" />&nbsp;6个月</label>
                            <label><input type="radio" name="days" value="365" />&nbsp;1年</label>
                        </div>
                    </div>
                </div>
                <div class="tab_box1 row">
                    <div class="dropload-load"><span class="loading"></span>加载中...</div>
                    <table cellpadding="0" cellspacing="0" class="table t-tab1" id="table_update_home">
                    </table>
                </div>
            </div> --%>
          <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
	<!-- 搜索引擎收录js引入 -->
	  <script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
	<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
	<script language="javascript" type="text/javascript" src="<%= path %>/js/monitorInclude/index.js"></script>
</body>
</html>