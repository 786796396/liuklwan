<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/jcdata.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/zzchouc.css" />
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/baiduBigData/baiduBigData.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>

<title>搜索引擎收录</title>
</head>
<body class="bg_f5f5f5">
<input id="siteCode" type="hidden" value="${siteCode}">
<input id="level" type="hidden" value="${level}">
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
        <c:set var="ba_index" value="503" scope="request" />
				<c:set var="menu" value="#menuBg" scope="request" />
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
                                <div class="chart fl change" id="chartDiv">
                                    <i class="fl"></i>
                                    <span class="fl" >图表</span>
                                </div>
                                <div class="list fr change on">
                                    <i class="fl"></i>
                                    <span class="fl" >列表</span>
                                </div>
                            </div>
                            <!--右边结束-->
                        
                        </div>
                        <!--头部结束-->

                        <!--图标列表部分开始-->
                        <div class="chart_list">
                            <!--图表部分开始-->
                            <div class="chart_box show_box">
                                <div class="radio_part" style="position:relative;">
                                   
                                    <div class="chart_se">
                                               <span id="monitorTypeName">站点收录网页数</span>
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul id="monitorTypeUl">
                                                    <li value="1" name="monitorType">站点收录网页数</li>
                                                    <li value="2" name="monitorType">域收录网页数</li>
                                                </ul>
                                                <!--移入显示块结束-->
                                            </div>
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
                                <div class="lb_top clearfix">
                                    <div class="search_box fl">
                                        <input id="searchInfo_id" type="text" placeholder="输入网站名称或者标识码"/>
                                        <i></i>
                                    </div>
                                    <div class="report_list fr" id="exportExcelDiv">
                                        <i class="report_icon"></i>
                                        <span id="exportExcel" onclick="exportDatas()">导出列表</span>
                                    </div>
                                </div>
                                <!--列表块头部结束-->
                                <!--表部分开始-->
                                <div class="lb_table" id="content">
                                     <table  id="bodyBSort"> 
                                         <thead> 
                                         	 <tr id="titleB"> 
                                             </tr>
                                       </thead>
                                       </table>
                                       <table id="bodyB" class="table">
                                       <tbody id="bodys">
                                            
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


<!--大数据提示开始-->
<div class="modal fade bigdata" id="prompt" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:957px;height:418px; margin-left: -478px; left: 50%; display: none;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative; height: 45px;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    搜索引擎收录趋势
                </h4>
            </div>
            <div class="modal-body" id="includeChartDiv" style="width:927px; height:343px; border:none;">
            	
            </div>
        </div>
    </div>
</div>
<!--大数据提示结束-->
</div>
<script language="javascript" type="text/javascript">
$(function(){ 
    $("table tbody tr:odd td").css("background","#fafbfc");
}); 
</script>
</body>
</html>