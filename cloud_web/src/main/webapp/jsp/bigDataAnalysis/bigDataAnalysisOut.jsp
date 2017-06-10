<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatisticsOut.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/jcdata.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/zzchouc.css" />
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/dailyMonitoringStatistics/dailyMonitoringStatisticsOut.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/gauge.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
<title>日常监测统计</title>
</head>
<body>
<input id="initType" type="hidden" value="">
<input id="tabIdOut" type="hidden" value="${rsMap.tabIdOut}">
<input id="tableWidth" type="hidden" value="">
<input id="tableTop" type="hidden" value="">
<input id="tableWidth2" type="hidden" value="">
<input id="tableTop2" type="hidden" value="">
<input id="tableTitleClass" type="hidden" value="1">
<input id="chartFlag" type="hidden" value="">

<input id="unitVal" type="hidden" value="">
<input id="radioVal" type="hidden" value="">
<input id="pageSizeVal" type="hidden" value="1">
<input id="loginLevel" type="hidden" value="${rsMap.loginLevel }">
<input id="level" type="hidden" value="${rsMap.level }">
<input id="siteCodes" type="hidden" value="${rsMap.orgSiteCode }">
<input id="code" type="hidden" value="">
<input id="tabId" type="hidden" value="">
<input id="siteName" type="hidden" value="${rsMap.siteName}">
<input id="tabHide" type="hidden" value="${rsMap.tabHide}">
<input id="pageSiteCode" type="hidden" value="">
<input id="siteCodeFlag" type="hidden">
<input id="siteCodeFlagType" type="hidden">
<input id="sitenum" type="hidden" value="">
<input id="linkerrprop" type="hidden" value="">
<input id="linkerrpropVal" type="hidden" value="">
<input id="bmType" type="hidden" value="">
<input id="dayVal" type="hidden" value="1">
<input id="configureTabId" type="hidden" value="0">
<input id="contentType" type="hidden" value="0">

<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%=path%>/images/common/top-hover.png"/>
        <img class="top-img" src="<%=path%>/images/common/top.png"/>
    </a>
</p>
<div class="main-container container outer_show">

	<div class="row-fluid">
        <div class="page-content" style=" padding-top:27px;">
        	<h4 class="main_title">日常监测统计</h4>
            <div class="five_tab_part">
                <div class="five_tp_top">
                    <div class="every_block clearfix">
                        <div class="fl every_tip on" id="tab0" style="display:none">地方站群</div>
                        <div class="fl every_tip" id="tab1" style="display:none">门户网站</div>
                        <div class="fl every_tip" id="tab2" style="display:none">本级部门</div>
                        <div class="fl every_tip" id="tab3" style="display:none">下级部门站群</div>
                        <div class="fl every_tip" id="tab4" style="display:none">下级部门门户</div>
                    </div>
                    <div class="green_line"></div>
                </div>
                <div class="five_tp_content">
                    <!--五个块-第一个开始-->
                    <div class="every_tab on">
                        <!--头部开始-->
                        <div class="top_nav clearfix">
                            <!--左边开始-->
                            <div class="nav_left fl">
                                <ul class="clearfix">
                                
                                 <li class="fl bread" id="nextNode1" style="display:none">
                                        <span id="nextNodeSpan1">bm0100 <i></i></span>
                                          
                                        <!--移入显示块开始-->
<!--                                         <ul id="nextNodeUl1"> -->
<!--                                             <li>1</li> -->
<!--                                             <li>2</li> -->
<!--                                             <li>3</li> -->
<!--                                         </ul> -->
                                        
                                    </li>
                                <!--移入显示块结束-->
<!--                                         <li class="fl marlr_8">/</li> -->
                                    <li class="fl bread" id="nextNode2" style="display:none">
                                        <span id="nextNodeSpan2">广东省<i></i></span>
                                          
                                        <!--移入显示块开始-->
                                        <ul id="nextNodeUl2">
<!--                                             <li>1</li> -->
<!--                                             <li>2</li> -->
<!--                                             <li>3</li> -->
                                        </ul>
                                       
                                    </li>
                                     <!--移入显示块结束-->
<!--                                         <li class="fl marlr_8">/</li> -->
                                    
                                    <li class="fl bread" id="nextNode3" style="display:none">
                                        <span class="province" id="nextNodeSpan3">广州市 <i></i></span>
                                        
                                        <!--移入显示块开始-->
                                        <ul id="nextNodeUl3">
<!--                                             <li>1</li> -->
<!--                                             <li>2</li> -->
<!--                                             <li>3</li> -->
                                        </ul>
                                        
                                    </li>
                                    <!--移入显示块结束-->
<!--                                         <li class="fl marlr_8">/</li> -->
                                    <li class="fl bread" id="nextNode4" style="display:none">
                                        <span class="area" id="nextNodeSpan4">天河区  <i></i></span>
                                       
                                        <!--移入显示块开始-->
                                        <ul id="nextNodeUl4">
<!--                                             <li>11</li> -->
<!--                                             <li>22</li> -->
<!--                                             <li>33</li> -->
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                </ul>
                            </div>
                            <!--左边结束-->
                            <!--右边开始-->
                            <div class="nav_right fr">
                                <div class="chart fl change on" id="chartDiv">
                                    <i class="fl"></i>
                                    <span class="fl">图表</span>
                                </div>
                                <div class="list fr change " id="listDiv">
                                    <i class="fl"></i>
                                    <span class="fl">列表</span>
                                </div>
                            </div>
                            <!--右边结束-->
                            <!--中间开始-->
                            <div class="nav_center fr period">
                                <div class="area bread">
                                    <span id="dayName">昨天</span>
                                    <i></i>
                                    <ul id="dayUl">
                                        <li value="1" class="dayLi">昨天</li>
                                        <li value="7" class="dayLi">一周</li>
                                        <li value="14" class="dayLi">两周</li>
                                        <li value="30" class="dayLi">一月</li>
                                    </ul>
                                </div>
                                <!--移入显示块结束-->
                            </div>
                            <!--中间结束-->
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list">
                            <!--图表部分开始-->
                            <div class="chart_box show_box" id="chartBoxId">
                                <div class="radio_part" style="position:relative;">
                                    <ul class="clearfix">
<!--                                         <li class="fl every_radio clearfix" id="radioli1"> -->
<!--                                             <label> -->
<!--                                                 <input type="radio" name="chartImg" class="fl" value="linkerrsitenum" id="radio1"/> -->
<!--                                                 <div class="two_lines fr"> -->
<!--                                                     <span>监测不连通</span> -->
<!--                                                     <span class="s2">(网站数)</span> -->
<!--                                                 </div> -->
<!--                                             </label> -->
<!--                                         </li> -->
                                        <li class="fl every_radio clearfix" id="radioli2">
                                            <label>
                                                <input type="radio" name="chartImg" value="2" class="fl" id="radio2"/>
                                                <div class="two_lines fr">
                                                    <span id="radio2span">监测不连通</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli10">
                                            <label>
                                                <input type="radio" name="chartImg" value="10" class="fl" id="radio10"/>
                                                <div class="two_lines fr">
                                                    <span id="radio10span">监测不连通率</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                         <li class="fl every_radio clearfix" id="radioli9">
                                            <label>
                                                <input type="radio" name="chartImg" value="9" class="fl" id="radio9"/>
                                                <div class="two_lines fr">
                                                    <span id="radio9span">首页死链  </span>
                                                    <span class="s2" >(个)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli3">
                                            <label>
                                                <input type="radio" name="chartImg" value="3" class="fl" id="radio3"/>
                                                <div class="two_lines fr">
                                                    <span id="radio3span">首页死链 </span>
                                                    <span class="s2">(平均数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli4">
                                            <label>
                                                <input type="radio" name="chartImg" value="4" class="fl" id="radio4"/>
                                                <div class="two_lines fr">
                                                    <span id="radio4span">首页不更新 </span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli5">
                                            <label>
                                                <input type="radio" name="chartImg" value="5" class="fl" id="radio5"/>
                                                <div class="two_lines fr">
                                                    <span id="radio5span">首页不更新 </span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli6">
                                            <label>
                                                <input type="radio" name="chartImg" value="6" class="fl" id="radio6"/>
                                                <div class="two_lines fr">
                                                    <span id="radio6span">平均更新量 </span>
                                                    <span class="s2" >(条/站)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli7" >
                                            <label>
                                                <input type="radio" name="chartImg" value="7" class="fl" id="radio7"/>
                                                <div class="two_lines fr">
                                                    <span id="radio7span">监测更新量  </span>
                                                    <span class="s2" >(条)</span>
                                                </div>
                                            </label>
                                        </li>
                                          <li class="fl every_radio clearfix" id="radioli8">
                                            <label>
                                                <input type="radio" name="chartImg" value="8" class="fl" id="radio8"/>
                                                <div class="two_lines fr">
                                                    <span id="radio8span">首页未更新天数  </span>
                                                    <span class="s2" >(天)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix" id="radioli11">
                                            <label style="position:relative;">
                                                <input type="radio" name="chartImg" value="11" class="fl" id="radio11"/>
                                                <div class="two_lines fr">
                                                    <span id="radio11span">健康指数</span>
                                                    <span class="s2"></span>
                                                </div>
                                                <i class="health-index-icon"></i>
                                            </label>
                                        </li>
                                    </ul>
                                    <div class="chart_se ">
                                        <span id="pageSizeName">前20项</span>
                                         <i></i>
                                         <!--移入显示块开始-->
                                         <ul id="pageSizeUl">
                                             <li value="1" name="pageSize">前20项</li>
                                             <li value="2" name="pageSize">后20项</li>
                                             <li value="-1" name="pageSize">全部</li>
                                         </ul>
                                         <!--移入显示块结束-->
                                    </div>
                                </div>
                                <div class="cb_chart">
                                    <div class="cb_chartTop">
                                        <div class="top_content   clearfix">
                                            <!-- <h5 class="fl" id="chartTitle">监测不连通 <span>（网站数）</span></h5> -->
                                            <!-- <div class="chart_se fr">
                                               <span id="pageSizeName">前20项</span>
                                                <i></i>
                                                移入显示块开始
                                                <ul id="pageSizeUl">
                                                    <li value="1" name="pageSize">前20项</li>
                                                    <li value="2" name="pageSize">后20项</li>
                                                    <li value="0" name="pageSize">全部</li>
                                                </ul>
                                                移入显示块结束
                                            </div> -->
                                        </div>
                                    </div>
                                    <div class="cb_chartContent" id="chartContent" style="height:700px; width: 900px;">
<!--                                         <img src="../../images/zanyong/chart_1.png" alt=""/> -->
                                    </div>
                                </div>
                            </div>
                            <!--图表部分结束-->

                            <!--列表部分开始-->
                            <div class="list_box  show_box on" id="listBoxId">
                                <!--列表块头部开始-->
                                <div class="lb_top clearfix">
                                    <div class="search_box fl">
                                        <input id="searchInfo_id" type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr">
                                        <i class="report_icon"></i>
                                        <span id="exportExcel" onclick="exportDate()">导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table" id="content">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th style="width: 5.3%; padding-left: 10px; padding-right: 10px;">序号</th>
                                                <th style="width: 14.3%;text-align: left;">组织单位名称/编码</th>
                                                <th style="width:10.06%">监测网站数量</th>
                                                <th style="width: 8.9%">
                                                    监测不连通
                                                    <br>
                                                    <div title="选定时间段内首页不连通率为100%的网站个数">（个）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                                <th style="width: 10.6%">
                                                    监测不连通率
                                                    <br>
                                                    <div title="不连通次数/监测次数">（占比）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                                <th style="width:9.7%">
                                                     首页死链平均数
                                                    <br>
                                                    <div title="总个数/站点数">（个）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                                <th style="width:9%">
                                                    首页不更新
                                                    <br>
                                                    <div title=" 选定周期内首页持续不更新的网站个数">（网站数）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                                <th style="width: 9%">
                                                    首页不更新
                                                    <br>
                                                    <div title="不更新站点数/站点数">（占比）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                                <th style="width: 9%">
                                                    平均更新量
                                                    <br>
                                                    <div title="总更新条数/站点数">（条、站）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                                <th style="width: 9.1%">
                                                    监测更新量
                                                    <br>
                                                    <div title="选定周期内首页或栏目更新的数量">（条）</div>
                                                    <i class="tab_angle"></i>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <!--表部分结束-->
                            </div>
                            <!--列表部分结束-->
                        </div>
                        <!--图标列表部分结束-->
                    </div>
                    <!--五个块-第一个结束-->

                    <!--五个块-第二个开始-->
                    <div class="every_tab">
                        <!--头部开始-->
                        <div class="top_nav clearfix">
                            <!--左边开始-->
                            <div class="nav_left fl">
                                <ul class="clearfix">
                                    <li class="fl bread">
                                        <span>广东省</span>
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="province">广州市</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>1</li>
                                            <li>2</li>
                                            <li>3</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="area">天河区</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                </ul>
                            </div>
                            <!--左边结束-->
                            <!--右边开始-->
                            <div class="nav_right fr">
                                <div class="chart fl change">
                                    <i class="fl"></i>
                                    <span class="fl">图表</span>
                                </div>
                                <div class="list fr change on">
                                    <i class="fl"></i>
                                    <span class="fl">列表</span>
                                </div>
                            </div>
                            <!--右边结束-->
                            <!--中间开始-->
                            <div class="nav_center fr period">
                                <div class="area">
                                    <span>两周</span>
                                    <i></i>
                                    <ul>
                                        <li>11</li>
                                        <li>22</li>
                                        <li>33</li>
                                    </ul>
                                </div>
                                <!--移入显示块结束-->
                            </div>
                            <!--中间结束-->
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list">
                            <!--图表部分开始-->
                            <div class="chart_box show_box">
                                <div class="radio_part">
                                    <ul class="clearfix">
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页死链 </span>
                                                    <span class="s2">(平均数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>平均更新量 </span>
                                                    <span class="s2" >(条/站)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测更新量  </span>
                                                    <span class="s2" >(条)</span>
                                                </div>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="cb_chart">
                                    <div class="cb_chartTop">
                                        <div class="top_content  clearfix">
                                            <h5 class="fl">监测不连通 <span>（网站数）</span></h5>
                                            <div class="chart_se fr">
                                                前20个
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul>
                                                    <li>11</li>
                                                    <li>22</li>
                                                    <li>33</li>
                                                </ul>
                                                <!--移入显示块结束-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cb_chartContent">
                                        <img src="../../images/zanyong/chart_1.png" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <!--图表部分结束-->

                            <!--列表部分开始-->
                            <div class="list_box  show_box on">
                                <!--列表块头部开始-->
                                <div class="lb_top clearfix">
                                    <div class="search_box fl">
                                        <input type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr">
                                        <i class="report_icon"></i>
                                        <span>导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th style="width: 5.3%; padding-left: 10px; padding-right: 10px;">序号</th>
                                            <th style="width: 14.3%;text-align: left;">组织单位名称/编码</th>
                                            <th style="width:10.06%">监测网站数量</th>
                                            <th style="width: 8.9%">
                                                监测不连通
                                                <br>
                                                <div title="选定时间段内首页不连通率为100%的网站个数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 10.6%">
                                                监测不连通率
                                                <br>
                                                <div title="不连通次数/监测次数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9.7%">
                                                首页死链平均数
                                                <br>
                                                <div title="总个数/站点数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9%">
                                                首页不更新
                                                <br>
                                                <div title=" 选定周期内首页持续不更新的网站个数">（网站数）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                首页不更新
                                                <br>
                                                <div title="不更新站点数/站点数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                平均更新量
                                                <br>
                                                <div title="总更新条数/站点数">（条、站）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9.1%">
                                                监测更新量
                                                <br>
                                                <div title="选定周期内首页或栏目更新的数量">（条）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--表部分结束-->
                            </div>
                            <!--列表部分结束-->
                        </div>
                        <!--图标列表部分结束-->
                    </div>
                    <!--五个块-第二个结束-->

                    <!--五个块-第三个开始-->
                    <div class="every_tab">
                        <!--头部开始-->
                        <div class="top_nav clearfix">
                            <!--左边开始-->
                            <div class="nav_left fl">
                                <ul class="clearfix">
                                    <li class="fl bread">
                                        <span>广东省</span>
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="province">广州市</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>1</li>
                                            <li>2</li>
                                            <li>3</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="area">天河区</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                </ul>
                            </div>
                            <!--左边结束-->
                            <!--右边开始-->
                            <div class="nav_right fr">
                                <div class="chart fl change">
                                    <i class="fl"></i>
                                    <span class="fl">图表</span>
                                </div>
                                <div class="list fr change on">
                                    <i class="fl"></i>
                                    <span class="fl">列表</span>
                                </div>
                            </div>
                            <!--右边结束-->
                            <!--中间开始-->
                            <div class="nav_center fr period">
                                <div class="area">
                                    <span>两周</span>
                                    <i></i>
                                    <ul>
                                        <li>11</li>
                                        <li>22</li>
                                        <li>33</li>
                                    </ul>
                                </div>
                                <!--移入显示块结束-->
                            </div>
                            <!--中间结束-->
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list">
                            <!--图表部分开始-->
                            <div class="chart_box show_box">
                                <div class="radio_part">
                                    <ul class="clearfix">
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页死链 </span>
                                                    <span class="s2">(平均数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>平均更新量 </span>
                                                    <span class="s2" >(条/站)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测更新量  </span>
                                                    <span class="s2" >(条)</span>
                                                </div>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="cb_chart">
                                    <div class="cb_chartTop">
                                        <div class="top_content  clearfix">
                                            <h5 class="fl">监测不连通 <span>（网站数）</span></h5>
                                            <div class="chart_se fr">
                                                前20个
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul>
                                                    <li>11</li>
                                                    <li>22</li>
                                                    <li>33</li>
                                                </ul>
                                                <!--移入显示块结束-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cb_chartContent">
                                        <img src="../../images/zanyong/chart_1.png" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <!--图表部分结束-->

                            <!--列表部分开始-->
                            <div class="list_box  show_box on">
                                <!--列表块头部开始-->
                                <div class="lb_top clearfix">
                                    <div class="search_box fl">
                                        <input type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr">
                                        <i class="report_icon"></i>
                                        <span>导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th style="width: 5.3%; padding-left: 10px; padding-right: 10px;">序号</th>
                                            <th style="width: 14.3%;text-align: left;">组织单位名称/编码</th>
                                            <th style="width:10.06%">监测网站数量</th>
                                            <th style="width: 8.9%">
                                                监测不连通
                                                <br>
                                                <div title="选定时间段内首页不连通率为100%的网站个数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 10.6%">
                                                监测不连通率
                                                <br>
                                                <div title="不连通次数/监测次数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9.7%">
                                                首页死链平均数
                                                <br>
                                                <div title="总个数/站点数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9%">
                                                首页不更新
                                                <br>
                                                <div title=" 选定周期内首页持续不更新的网站个数">（网站数）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                首页不更新
                                                <br>
                                                <div title="不更新站点数/站点数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                平均更新量
                                                <br>
                                                <div title="总更新条数/站点数">（条、站）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9.1%">
                                                监测更新量
                                                <br>
                                                <div title="选定周期内首页或栏目更新的数量">（条）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--表部分结束-->
                            </div>
                            <!--列表部分结束-->
                        </div>
                        <!--图标列表部分结束-->
                    </div>
                    <!--五个块-第三个结束-->

                    <!--五个块-第四个开始-->
                    <div class="every_tab">
                        <!--头部开始-->
                        <div class="top_nav clearfix">
                            <!--左边开始-->
                            <div class="nav_left fl">
                                <ul class="clearfix">
                                    <li class="fl bread">
                                        <span>广东省</span>
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="province">广州市</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>1</li>
                                            <li>2</li>
                                            <li>3</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="area">天河区</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                </ul>
                            </div>
                            <!--左边结束-->
                            <!--右边开始-->
                            <div class="nav_right fr">
                                <div class="chart fl change">
                                    <i class="fl"></i>
                                    <span class="fl">图表</span>
                                </div>
                                <div class="list fr change on">
                                    <i class="fl"></i>
                                    <span class="fl">列表</span>
                                </div>
                            </div>
                            <!--右边结束-->
                            <!--中间开始-->
                            <div class="nav_center fr period">
                                <div class="area">
                                    <span>两周</span>
                                    <i></i>
                                    <ul>
                                        <li>11</li>
                                        <li>22</li>
                                        <li>33</li>
                                    </ul>
                                </div>
                                <!--移入显示块结束-->
                            </div>
                            <!--中间结束-->
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list">
                            <!--图表部分开始-->
                            <div class="chart_box show_box">
                                <div class="radio_part">
                                    <ul class="clearfix">
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页死链 </span>
                                                    <span class="s2">(平均数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>平均更新量 </span>
                                                    <span class="s2" >(条/站)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测更新量  </span>
                                                    <span class="s2" >(条)</span>
                                                </div>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="cb_chart">
                                    <div class="cb_chartTop">
                                        <div class="top_content  clearfix">
                                            <h5 class="fl">监测不连通 <span>（网站数）</span></h5>
                                            <div class="chart_se fr">
                                                前20个
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul>
                                                    <li>11</li>
                                                    <li>22</li>
                                                    <li>33</li>
                                                </ul>
                                                <!--移入显示块结束-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cb_chartContent">
                                        <img src="../../images/zanyong/chart_1.png" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <!--图表部分结束-->

                            <!--列表部分开始-->
                            <div class="list_box  show_box on">
                                <!--列表块头部开始-->
                                <div class="lb_top clearfix">
                                    <div class="search_box fl">
                                        <input type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr">
                                        <i class="report_icon"></i>
                                        <span>导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th style="width: 5.3%; padding-left: 10px; padding-right: 10px;">序号</th>
                                            <th style="width: 14.3%;text-align: left;">组织单位名称/编码</th>
                                            <th style="width:10.06%">监测网站数量</th>
                                            <th style="width: 8.9%">
                                                监测不连通
                                                <br>
                                                <div title="选定时间段内首页不连通率为100%的网站个数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 10.6%">
                                                监测不连通率
                                                <br>
                                                <div title="不连通次数/监测次数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9.7%">
                                                首页死链平均数
                                                <br>
                                                <div title="总个数/站点数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9%">
                                                首页不更新
                                                <br>
                                                <div title=" 选定周期内首页持续不更新的网站个数">（网站数）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                首页不更新
                                                <br>
                                                <div title="不更新站点数/站点数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                平均更新量
                                                <br>
                                                <div title="总更新条数/站点数">（条、站）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9.1%">
                                                监测更新量
                                                <br>
                                                <div title="选定周期内首页或栏目更新的数量">（条）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--表部分结束-->
                            </div>
                            <!--列表部分结束-->
                        </div>
                        <!--图标列表部分结束-->
                    </div>
                    <!--五个块-第四个结束-->

                    <!--五个块-第五个开始-->
                    <div class="every_tab">
                        <!--头部开始-->
                        <div class="top_nav clearfix">
                            <!--左边开始-->
                            <div class="nav_left fl">
                                <ul class="clearfix">
                                    <li class="fl bread">
                                        <span>广东省</span>
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="province">广州市</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>1</li>
                                            <li>2</li>
                                            <li>3</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                    <li class="fl marlr_8">/</li>
                                    <li class="fl bread">
                                        <span class="area">天河区</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul>
                                            <li>11</li>
                                            <li>22</li>
                                            <li>33</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </li>
                                </ul>
                            </div>
                            <!--左边结束-->
                            <!--右边开始-->
                            <div class="nav_right fr">
                                <div class="chart fl change">
                                    <i class="fl"></i>
                                    <span class="fl">图表</span>
                                </div>
                                <div class="list fr change on">
                                    <i class="fl"></i>
                                    <span class="fl">列表</span>
                                </div>
                            </div>
                            <!--右边结束-->
                            <!--中间开始-->
                            <div class="nav_center fr period">
                                <div class="area">
                                    <span>两周</span>
                                    <i></i>
                                    <ul>
                                        <li>11</li>
                                        <li>22</li>
                                        <li>33</li>
                                    </ul>
                                </div>
                                <!--移入显示块结束-->
                            </div>
                            <!--中间结束-->
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list">
                            <!--图表部分开始-->
                            <div class="chart_box show_box">
                                <div class="radio_part">
                                    <ul class="clearfix">
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测不连通</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页死链 </span>
                                                    <span class="s2">(平均数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(网站数)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>首页不更新 (网站数)</span>
                                                    <span class="s2">(占比)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>平均更新量 </span>
                                                    <span class="s2" >(条/站)</span>
                                                </div>
                                            </label>
                                        </li>
                                        <li class="fl every_radio clearfix">
                                            <label>
                                                <input type="radio" class="fl"/>
                                                <div class="two_lines fr">
                                                    <span>监测更新量  </span>
                                                    <span class="s2" >(条)</span>
                                                </div>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="cb_chart">
                                    <div class="cb_chartTop">
                                        <div class="top_content  clearfix">
                                            <h5 class="fl">监测不连通 <span>（网站数）</span></h5>
                                            <div class="chart_se fr">
                                                前20个
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul>
                                                    <li>11</li>
                                                    <li>22</li>
                                                    <li>33</li>
                                                </ul>
                                                <!--移入显示块结束-->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="cb_chartContent">
                                        <img src="../../images/zanyong/chart_1.png" alt=""/>
                                    </div>
                                </div>
                            </div>
                            <!--图表部分结束-->

                            <!--列表部分开始-->
                            <div class="list_box  show_box on">
                                <!--列表块头部开始-->
                                <div class="lb_top clearfix">
                                    <div class="search_box fl">
                                        <input type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr">
                                        <i class="report_icon"></i>
                                        <span>导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th style="width: 5.3%; padding-left: 10px; padding-right: 10px;">序号</th>
                                            <th style="width: 14.3%;text-align: left;">组织单位名称/编码</th>
                                            <th style="width:10.06%">监测网站数量</th>
                                            <th style="width: 8.9%">
                                                监测不连通
                                                <br>
                                                <div title="选定时间段内首页不连通率为100%的网站个数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 10.6%">
                                                监测不连通率
                                                <br>
                                                <div title="不连通次数/监测次数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9.7%">
                                                首页死链平均数
                                                <br>
                                                <div title="总个数/站点数">（个）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width:9%">
                                                首页不更新
                                                <br>
                                                <div title=" 选定周期内首页持续不更新的网站个数">（网站数）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                首页不更新
                                                <br>
                                                <div title="不更新站点数/站点数">（占比）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9%">
                                                平均更新量
                                                <br>
                                                <div title="总更新条数/站点数">（条、站）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                            <th style="width: 9.1%">
                                                监测更新量
                                                <br>
                                                <div title="选定周期内首页或栏目更新的数量">（条）</div>
                                                <i class="tab_angle"></i>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 5.3%; text-align: center;">1</td>
                                            <td style="width: 14.3%; text-align: left;">
                                                <a href="" style="color:#2899df;">
                                                    <div style="margin-bottom: 6px;">广州市</div>
                                                    <div>440100</div>
                                                </a>
                                            </td>
                                            <td style="width: 10.06%;text-align: center; color:#2899df; cursor: pointer;">411</td>
                                            <td style="width: 8.9%;text-align: center; color:#2899df; cursor: pointer;">0</td>
                                            <td style="width:10.06%;text-align: center;color:#2899df; cursor: pointer;">14.3</td>
                                            <td style="width: 9.7%;text-align: center;">102</td>
                                            <td style="width: 9%;text-align: center;">26</td>
                                            <td style="width: 9%;text-align: center;">6.33</td>
                                            <td style="width: 9%;text-align: center;">20.07</td>
                                            <td style="width: 9.1%;text-align: center;">11134</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <!--表部分结束-->
                            </div>
                            <!--列表部分结束-->
                        </div>
                        <!--图标列表部分结束-->
                    </div>
                    <!--五个块-第五个结束-->

                </div>
            </div>
            <div >
				   </div>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<script language="javascript" type="text/javascript">
$(function(){ 
    /*组织单位--日常监测统计*/

    
    //图表列表切换；
    $('.change').click(function(){
        var n = $(this).index();
        $('.show_box').removeClass('on');
        $('.change').removeClass('on');
        $(this).addClass('on');
        $('.show_box').eq(n).addClass('on');
    });

    //鼠标移入自定义下拉框后ul显示；移除隐藏；
//     $('.bread').click(function(){
//         $(this).children('ul').show();
//         var p=$(this).children('span');
//         var p_txt=$(this).children('span').html();
//         var oLi=$(this).children('ul').children('li');

//     });

    $("table tbody tr:odd td").css("background","#fafbfc");
}); 
</script>
</body>
</html>