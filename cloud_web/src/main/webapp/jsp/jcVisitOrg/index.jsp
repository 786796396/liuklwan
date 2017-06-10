<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css"/>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/jcVisitOrg/index.js"></script>
<title>网站访问量</title>
</head>
<body class="bg_f5f5f5">
<input id="level" type="hidden" value="${level}">
<input id="siteCode" type="hidden" value="${siteCode}">
<input id="databaseTreeInfoId" type="hidden" value="${databaseTreeInfoId}">
<input id="webName" type="hidden" value="${sessionScope.shiroUser.userName}">

<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%=path%>/images/common/top-hover.png"/>
        <img class="top-img" src="<%=path%>/images/common/top.png"/>
    </a>
</p>
<%@ include file="/common/top.jsp"%>
<div class="main-container container mp">

	<div class="row-fluid">
        <c:set var="ba_index" value="505" scope="request" />
				<c:set var="menu" value="#menuBg" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<h4 class="main_title">网站访问量</h4>
            <div class="five_tab_part">
                <div class="five_tp_top">
                     <div class="every_block clearfix"> 
                        <div class="fl every_tip on" id="tab0" style="width:150px;display:none">下级地方站群</div>
                        <div class="fl every_tip" id="tab1" style="width:150px;display:none">下级地方门户</div>
                        <div class="fl every_tip" id="tab2" style="width:150px;display:none">本级站点</div>
                        <div class="fl every_tip" id="tab3" style="width:150px;display:none">下级部门站群</div>
                        <div class="fl every_tip" id="tab4" style="width:150px;display:none">下级部门门户</div> 
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
                                
                                 <li class="fl bread" id="nextNode1" >
                                        <span id="nextNodeSpan1" style="display:none"></span>
                                          
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
                                <div class="chart fl change" id="chartDiv">
                                    <i class="fl"></i>
                                    <span class="fl" >图表</span>
                                </div>
                                <div class="list fr change on">
                                    <i class="fl"></i>
                                    <span class="fl" >列表</span>
                                </div>
                            </div>
                             <div class="fr data-date" style="line-height: 37px; color: #727272;">昨天</div>
                            <!--右边结束-->
                        
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list" style="margin-top:0;">
                            <!--图表部分开始-->
                            <div class="chart_box show_box">
                                <div class="radio_part" style="position:relative;">
                                   <ul class="clearfix">
                                        <li class="fl every_radio clearfix org" id="radioli2">
                                            <label>
                                                <input type="radio" name="chartImgg" value="1" checked="checked" class="fl" id="radio2"/>
                                                <div class="two_lines fr">
                                                    <span id="radio2span">浏览量平均值</span>
                                                </div>
                                            </label>
                                        </li>
                                        
                                        <li class="fl every_radio clearfix org" id="radioli3">
                                            <label>
                                                <input type="radio" name="chartImgg" value="" class="fl" id="radio3"/>
                                                <div class="two_lines fr">
                                                    <span id="radio3span">访客量平均值 </span>
                                                </div>
                                            </label>
                                        </li> 
                                        
                                        
                                        <li class="fl every_radio clearfix tb" id="radioli2">
                                            <label>
                                                <input type="radio" name="chartImg" value="1" checked="checked" class="fl" id="radio2"/>
                                                <div class="two_lines fr">	
                                                    <span id="radio2span">浏览量</span>
                                                </div>
                                            </label>
                                        </li>
                                        
                                        <li class="fl every_radio clearfix tb" id="radioli3">
                                            <label>
                                                <input type="radio" name="chartImg" value="" class="fl" id="radio3"/>
                                                <div class="two_lines fr">
                                                    <span id="radio3span">访客量</span>
                                                </div>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="cb_chart">
                                    <div class="cb_chartTop">
                                        <div class="top_content   clearfix">
                                           <!--  <h5 class="fl" id="chartTitle">监测不连通 <span>（网站数）</span></h5> -->
                                            
                                        </div>
                                    </div>
                                    <div class="cb_chartContent" id="chartContent" style="height:700px; width: 900px; position:relative; top:-20px;">
<!--                                         <img src="../../images/zanyong/chart_1.png" alt=""/> -->
                                    </div>
                                </div>
                            </div>
                            <!--图表部分结束-->

                            <!--列表部分开始-->
                            <div class="list_box  show_box on">
                                <!--列表块头部开始-->
                                <div class="lb_top clearfix" style="padding-bottom:5px;">
                                    <div class="search_box fl">
                                        <input id="searchInfo_id" type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr" id="exportExcelDiv">
                                        <i class="report_icon"></i>
                                        <span id="exportExcel" onclick="exportDate()">导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table" id="content">
                                     <table class="table"> 
                                         <thead> 
                                             <tr id="titleB"> 
                                             </tr> 
                                         </thead> 
                                         <tbody id="bodyB"> 
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
                </div>
            </div>
            <div >
				   <%@ include file="/common/footer.jsp"%>	
				   </div>

        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->


<!--弹框  图-->
<div class="modal fade bigdata" id="prompt" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:957px;height:418px; margin-left: -478px; left: 50%; display: none;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative; height: 45px;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel" style="height:45px; line-height:45px;">
                    访问量走势图
                </h4>
            </div>
            
            <div class="trend-SearchEngine clearfix" style="height:auto; margin:0; border:none;">
                            <div class="trend-top" style="border:none;">
                                <div class="trend-title fl fs-n-18-Mic color-3c3d45">
                                     <div class="siteVisits_sel sv-chart_se fl">
                                        <span id="siteTypeText">访客量走势图</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul id="siteTypeUl" style="display: none;">
                                            <li value="3" name="monitorType">访客量走势图</li>
                                            <li value="2" name="monitorType">浏览量走势图</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </div>
                                </div>
                                <div class="trend-time fr fs-n-14-Mic color-646464" >
                                    <!-- <span>时间控制：</span> -->
                                    <div class="seveal_inp clearfix">
                                        <div class="every_inp">
                                            <input type="radio" id="twoWeeks" name="a" value="-14"/>
                                            <label for="yesterday">2周</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="twoMonths" name="a" checked="checked" value="-30"/>
                                            <label for="twoWeeks">1个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="threeMonths" name="a" value="-90"/>
                                            <label for="twoMonths">3个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="sixMonths" name="a" value="-180"/>
                                            <label for="fourMonths">6个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="year" name="a" value="-365"/>
                                            <label for="sixMonths">1年</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
            <div class="modal-body" id="includeChartDiv" style="width:927px; height:310px; border:none; padding:0 15px;">
            	
            </div>
        </div>
    </div>
</div>
<!---->

    
</div>
<script language="javascript" type="text/javascript">
    $("table tbody tr:odd td").css("background","#fafbfc");
</script>
</body>
</html>