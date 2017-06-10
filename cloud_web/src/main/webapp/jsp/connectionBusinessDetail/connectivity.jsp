<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>深度监测-栏目监测</title>
    <%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/heade.jsp"%>
 	<!-- 三合一 -->
	<link rel="stylesheet"  type="text/css"  href="<%= path %>/css/tabChange.css">
	<link rel="stylesheet"  type="text/css"  href="<%= path %>/css/monitoring/monitoring-tb.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/yunjc.css"/>
 	<script type="text/javascript" src="<%= path %>/js/echarts-all.js"></script>
 	<script type="text/javascript" src="<%= path %>/js/sortSecond.js"></script>
 	
 	
  <body>
  <!--头部       satrt  -->
 <%@ include file="/common/top_tb.jsp"%>
  <!--头部       end  -->
	<div class="main-detail">
		<div class="main-detail-content">
	<%@ include file="/common/leftmenu.jsp"%>
	<!--左侧导航       end  -->
	
	<input type="hidden" id="chartFailNum">
	<input type="hidden" id="dateStr">
         <div class="page-content">
    		<div class="bread-crumbs daily-monitor-box-m">
                <div class="bread-crumbs-content">
                    <span class="cor-0498e4">深度检测</span>
                    <i class="cor-0498e4">></i>
                    <span>栏目检测</span>
                </div>
            </div>
            
            <div class="five_tab_part">
            	<div class="five_tp_top">
			                    <div class="every_block clearfix">
			                        <div class="fl every_tip on" style="width:150px;" id="tab0" >关键栏目连通性</div>
			                        <div class="fl every_tip" style="width:150px;" id="tab1" >业务系统连通性</div>
			                        <div class="fl every_tip" style="width:150px;" id="tab2">栏目更新情况</div>
			                    </div>
			                    <div class="green_line"></div>
	            </div>
	        <div class="txt-describe">
                <p class="single-p">免费监测5个栏目。购买“标准版栏目深度检测”或“全面检测“服务后，所有的栏目都会监测起来。所有监测的栏目都来源于您填报给我们的地址。<a href="javascript:void(0)" onclick="openHref()">点击查看栏目地址以及监测状态></a></p>
            </div>
            	<div class="five_tp_content">
            		<input id="col_id" type="hidden" value="${col}">
            	<!-- 		-----------			首页连通性 START-----------------------------        	 -->
            		<div class="every_tabs on" id="tabs0">
						 <div >
							 	<div class="z_box2">
				                    <h3 class="qu_s">关键栏目连通状况</h3>
				                    <div class="line_chart" >
				                   			<div id="channel_line" style="height: 250px;width: 100%;margin-top: -25px;"></div>
				                    </div>
				                </div>
								<div class="row mar1">
				            	<input type="hidden" id="time_yes_dateChannel" value="${yesterdayStr}"/>
				            	<h3 class="info_fx_h3 bg-13ba59">关键栏目连通统计分析
				            	 <input class="datepicker-input" type="text" id="datepickerChannel" readonly="readonly"/>
				            	<div id="out_excel_idChannel" class="page-btn1 page-button" onclick="channelExcel()" title="点击导出查看所有连不通数据"><i class="write-tup"></i><span>导出详情</span></div>
				            	</h3>
				
				                <div class="t-tab2">
				                		<div class="mark-tab" id="channel_statistics_div" style="display:none">
				                			<div class="cus-server">
							      				<h3>连不通详情列表</h3>
							      				<span class="close-btn">
							      					<img src="./images/close_ico2.png" alt="" onclick="closeDiv()">
							      				</span>
						      				</div>
						      				<div class="report-form">
							      				<table class=" report-line" cellspacing="0" cellpadding="0" id="channel_statistics_info">
							      					
							      				</table>
		      								</div>
				                		</div>
					                    <table cellspacing="0" cellpadding="0" class="table hide" id="channel_table_hide">
					                        <thead>
					                            <tr>
					                                <th rowspan="2">关键栏目名称</th>
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
					                         </<thead>
					                         <tbody id="conlistChannel"></tbody>
					                         <tbody>
					                            <tr id="conSumChannel">
					                            </tr>
					                        </tbody>
					                    </table>
					                    <div class="zanwsj" style="display: none" id="channel_table_hide1">未发现问题</div>
				                </div>
				            </div><!-- /row3 -->
				            
				              <div class="row mar1" style="display:none">
				            	<div class="tab_header row">
				                    <div class="tab-hfont3" style="display: none" id="strChannel">${dateStr}</div>
				                	<h3>关键栏目连通性监测结果<input class="datepicker-input" type="text" id="currentDateker" readonly="readonly"/></h3>
				                    <div class="input-prepend">
				                      <%-- <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span> --%>
				                      <input class="span2 prependedInput" id="keyInputChannel" type="hidden" placeholder="输入问题描述编码...">
				                    </div>
				                    	<div id="out_excel_idChannel" class="page-btn1" onclick="channelExcel()" style="display:none"><i></i>导出列表</div>
				                </div>
				                <div class="tab_box1 row">
					                    <input type="hidden" id="dateText" />
				                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
					                    <table id="channel_table" cellpadding="0" cellspacing="0" class="table t-tab1 hide">
					                    </table>
					                    <div class="zanwsj" id="channel_table_hide2">未发现问题</div>
				                </div>
				                <div id="excel_hidden_id"></div>
				            </div>
						</div>
					</div>    
<!-- 		-----------			首页连通性 END-----------------------------        	 -->
 <!--          -------------   业务系统连同性----START--   ---  -->
					<div class="every_tabs" id="tabs1">
								<div>
				            	 <div class="z_box2">
				                    <h3 class="qu_s">业务系统连通状况</h3>
				                    <div class="line_chart" style="width: 100%;">
				                   			<div id="business_line" style="height: 250px;width: 100%;margin-top: -25px;"></div>
				                   		<input type="hidden" id="pie_contral_id" />
				                    </div>
				                </div>
				                
				                
				                <div class="row mar1">
				                <input type="hidden" id="time_yes_dateBusiness" value="${yesterdayStr}"/>
				            	<h3 class="info_fx_h3 bg-13ba59">业务系统连通统计分析 <input class="datepicker-input" type="text" id="datepickerBusiness" readonly="readonly"/></h3>
				
				                <div class="t-tab2">
				                	<input type="hidden" id="bus_time_date_begin"/>
					                    <table cellspacing="0" cellpadding="0" class="table hide" id="business_table_hide">
					                        <tbody id="tbody_tongji_contral" class="tbody_tongji hide">
					                            <tr>
					                                <th rowspan="2">业务系统名称</th>
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
					                            <tbody id="conlistBusiness">
					                            </tbody>
					                            <tbody>
					                            <tr id="conSum">
					                            </tr>
					                        </tbody>
					                    </table>
					                    <div class="zanwsj" id="business_table_hide1">未发现问题</div>
				               	 </div>
				            	</div><!-- /row3 -->
				                
				             <div class="row mar1">
				            	<div class="tab_header row">
				            		<div class="tab-hfont3" style="display: none" id="strBusiness">${dateStr}</div>
				                	<h3>业务系统连通性监测结果<input class="datepicker-input" type="text" id="currentDatekerBusiness" readonly="readonly"/></h3>
				                    <div class="input-prepend">
				                      <%-- <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span> --%>
				                      <input class="prependedInput span2" id="keyInput" type="hidden" placeholder="输入问题描述编码...">
				                    </div>
				                    	<div id="out_excel_id_Business" class="page-btn1" onclick="businessExcel()" style="display:none;"><i></i>导出列表</div>
				                </div>
				                <div class="tab_box1 row">
				                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
					                    <table id="business_table" cellpadding="0" cellspacing="0" class="table t-tab1 hide">
					                    </table>
					                     <div class="zanwsj" id="business_table_hide2">未发现问题</div>
				                </div>
				                <div id="hidden_id"></div>
				            </div>
		           		 </div>
					</div>
						<!--          -------------   业务系统连同性----END--   ---  -->
						<!---------------------------- 栏目更新START----------------------- -->
				<div class="every_tabs" id = "tabs2">
					<input id="typesId" type="hidden" value="${types}" />
					<input id="columnTB" type="hidden" value="${columnTB}" />
				    <div class="row">
		                <ul class="breadcrumb">
		                  <li><a href="#">内容更新与分析</a> <span class="divider">></span></li>
		                  <li class="active">栏目更新</li>
		                </ul>
		            </div>
		        	<div class="row hide">
		                <div class="t_box2">
		                	<h3>监测说明</h3>
		
		                    <div class="t_box2_con">
		                    	<h5 class="tit-h">考察网站每天信息公开的各栏目信息更新情况，包括：</h5>
								<p>1.动态、要闻类栏目更新明细；</p>
								<p>2.通知公告、政策文件类栏目更新明细；</p>
								<p>3.人事、规划计划类栏目更新明细。</p>
		                    </div>
		                </div>
		            </div><!-- /row1 -->
		            <div class="row has_cbox_div">
		            	
		                <div class="t_box4 info_fx_con">
		                	<h3 class="info_fx_h3 bg-fff">栏目更新总体情况</h3>
		                    <div class="tab-sel1-con tab-header-radio">
		                        <div class="pull-left">时间选择</div>
		                        <div class="pull-left radio-box1">
		                         	<label><input type="radio" name="days" value="1" checked="checked"/>&nbsp;昨天</label>
		                            <label><input type="radio" name="days" value="14" />&nbsp;2周</label>
		                            <label><input type="radio" name="days" value="90"/>&nbsp;3个月</label>
		                            <label><input type="radio" name="days" value="180"/>&nbsp;6个月</label>
		                            <label><input type="radio" name="days" value="365"/>&nbsp;1年</label>
		                        </div>
		                    </div>
			                 <div class="tab-sel1">
			                	<div class="tab-sel1-con tab-header-cbox">
			                        <div class="pull-left">栏目类别</div>
			                        <div class="pull-left">
			                            <dl class="select">
			                                <dt id="typeName">全部</dt>
			                                <dd>
			                                    <ul id="params">
			                                        <input type="hidden" id="types"/>
			                                        <li>全部</li>
			                                        <li value="1">
			                                            <span class="font-704fc1">动态、要闻类</span><span class="font-ff0000">（要求两周内更新）</span>
			                                        </li>
			                                        <li value="2">
			                                            <span class="font-704fc1">通知公告、政策文件类</span><span class="font-ff0000">（要求半年内更新）</span>
			                                        </li >
			                                        <li value="3">
			                                            <span class="font-704fc1">人事、规划计划类</span><span class="font-ff0000">（要求一年内更新）</span>
			                                        </li>
			                                    </ul>
			                                </dd>
			                            </dl>
			                        </div>
			                    </div>
			                </div>
		                    <div class="line_chart">
		                    		<div id="update_channel_bar_id" style="height:257px; width:100%;" ></div>
		                    </div>
		                </div>
		            </div><!-- /row3 -->
		            
		            
		            <div class="row bg-fff">
		            	<div class="tab_header row">
		                	<h3>栏目更新明细</h3>
		                    <div class="tab-hfont1 hide" id="table_data_channelUpdate_hide"></div>
		                    <div class="input-prepend">
		                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
		                      <input  class="prependedInput span2" id="keyInputColumn" type="text" placeholder="输入标题...">
		                    </div>
		                    	<div class="page-btn1 hide" onclick="updateChannelExcel()" id="table_data_channelUpdate_hide1"><i></i>导出列表</div>
		                </div>
		                <div class="tab_box1 row">
			                    <table cellpadding="0" cellspacing="0" class="table t-tab1" id="table_up_channel_detail">
			                    </table>
			                    <div class="zanwsj" id="channel_table_id">未发现问题</div>
		                </div>
		            </div><!-- /bg-fff -->
				    <%@ include file="/common/http.jsp"%>                                
				</div>		
				<!-- 关键栏目连通性END----- -->	
            	</div>
            </div>
            

            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script type="text/javascript" src="<%= path %>/js/connection/connectivity.js?<%= autoVersoin %>"></script>
<script type="text/javascript" src="<%= path %>/js/connection/channelStatisticsInfo.js?<%= autoVersoin %>"></script>
<script type="text/javascript">
//列表排序
new TableSorter("conn_table",1);
</script>
</body>
</html>
