<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>首页连通性</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<script type="text/javascript" src="<%= path %>/js/echarts-all.js"></script>
 	<script type="text/javascript" src="<%= path %>/js/sortSecond.js"></script>
  </head>
  <body>
  <!--头部       satrt  -->
  <%@ include file="/common/top_tb.jsp"%>
  <!--头部       end  -->
	<div class="main-container container">
	<div class="row-fluid">
	<!--左侧导航       satrt  -->
	<c:set var="tb_index" value="2" scope="request"/>
	<c:set var="menu" value="#menuWzltx" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	<!--左侧导航       end  -->
         <div class="page-content">
    		<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">日常监测</a> <span class="divider">></span></li>
                  <li class="active">首页连通性</li>
                  <li class="jc-ms">
                    <div class="ms-msg">
                        <div class="ms-wen-con">
                            <div class="ztm-con">
                                <p style="margin-top:3px;">1. 考察网站每天首页打不开的次数；</p>
                                <p>2. 每${que}扫描一次，每次访问时间≥15秒首页打不开即为不连通一次；</p>
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
            <div class="row">
                <h3 class="info_fx_h3 bg-13ba59">首页连通性状况</h3>
                <div class="t_box4 info_fx_con">
                    <h4 class="font2"></h4>
                    <div  class="line_chart" >
                        <div id="home_line" style="height: 350px;width: 100%;"></div>
                    </div>
                </div>
            </div><!-- /row3 -->
            <div class="row">
            	<div class="t_box3 mar1">
            		<input type="hidden" id="time_yes_date" value="${yesterdayStr}"/>
                	<h3>首页连通状况 <input class="datepicker-input" type="text" id="datepicker" readonly="readonly"/></h3>
                    <div class="row">
                    	<div class="span5 pie-chart">
                        	<div  class="pie-chart-con">
                        		<div id="home_bar" style="height: 190px;width: 100%;"></div>
                            </div>
                        </div>
                        <div class="span7">
                        	<div class="pie-chart-tab">
                            	<table cellpadding="0" cellspacing="0" class="table">
                                	<tbody>
                                    	<tr>
                                        	<th class="text-left">分类</th>
                                            <th>次数</th>
                                            <th class="text-right" style="width:80px;">占比</th>
                                        </tr>
                                        <tr>
                                        	<td class="text-left">连通成功 （ < 15秒 ）</td>
                                            <td id="successNum"></td>
                                            <td  class="text-right"><div class="pie-chart-icon"><%-- <img src="<%=path%>/images/zanyong/t-3.png"/> --%></div>
                                            <div id="successProportion"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td class="text-left">连通超时 （ ≥ 15秒 ）</td>
                                            <td id="errorNum"></td>
                                            <td class="text-right">
                                            <div class="pie-chart-icon"><%-- <img src="<%=path%>/images/zanyong/t-3.png"/> --%></div>
                                            <div id="errorProportion"></div>
                                            </td>
                                        </tr>
                                      <tr>
                                        <td class="text-left">总次数</td>
                                        <td id="tj_count" ></td>
                                        <td></td>
                                      </tr>
                                    </tbody>
                                    <tfoot id="tfoot_id">
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /row2 -->

            
            <div class="row mar1">
            	<div class="tab_header row">
                    <div class="tab-hfont3" style="display: none" id="str">${dateStr}</div>
                	<h3>监测结果概览<input class="datepicker-input" type="text" id="currentDateker" readonly="readonly"/></h3>
                    <div class="input-prepend">
                      <%-- <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span> --%>
                      <input id="conlist_term" class="prependedInput span2" type="hidden" placeholder="输入问题描述编码...">
                    </div>
                    <div id="out_excel_id"></div>
                </div>
                <div class="tab_box1 row">
                	<input type="hidden" id="time_tool_min_date"/>
                    <div class="dropload-load"><span class="loading"></span>加载中...</div>
                    <table id="conn_table" cellpadding="0" cellspacing="0" class="table t-tab1 hide">
                        <thead>
                            <tr>
                            	<th class="th_left" style="width:80px; text-align:left;">序号</th>
                                <th  class="th_left" style="width:190px; text-align:left;">时间 <i class="tab_angle"></i></th>
                                <th >连通状态 </th>
                                <th class="th_left" style="text-align:left;">
                                    <span class="pull-left">问题描述</span>
                                    <div class="http-html"></div>
                                    <%@ include file="/common/http.jsp"%>
                                </th>
                            </tr>
                          </thead>
                          <tbody id="conlist">
                        </tbody>
                    </table>
                     <div class="zanwsj" id="conn_table_hide">未发现问题</div>
                </div>
            </div>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script type="text/javascript" src="<%= path %>/js/connection/connction_index.js"></script>
<script type="text/javascript">
//列表排序
new TableSorter("conn_table",1);
</script>
</body>
</html>
