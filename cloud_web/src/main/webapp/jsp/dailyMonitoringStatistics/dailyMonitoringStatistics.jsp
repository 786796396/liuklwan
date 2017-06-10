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
<script type="text/javascript" src="<%=path%>/js/dailyMonitoringStatistics/dailyMonitoringStatistics.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/gauge.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
<title>日常监测统计</title>
</head>
<body class="bg_f5f5f5">
<input id="focusRadio" type="hidden" value="1">
<input id="copyWrite" type="hidden" value="">
<input id="tableWidth" type="hidden" value="">
<input id="tableTop" type="hidden" value="">
<input id="tableWidth2" type="hidden" value="">
<input id="tableTop2" type="hidden" value="">
<input id="tableTitleClass" type="hidden" value="0">
<input id="chartFlag" type="hidden" value="">
<input id="unitVal" type="hidden" value="">
<input id="loginLevel" type="hidden" value="${rsMap.loginLevel }">
<input id="level" type="hidden" value="${rsMap.level }">
<input id="siteCodes" type="hidden" value="${rsMap.orgSiteCode }">
<input id="siteName" type="hidden" value="${rsMap.siteName}">
<input id="tabHide" type="hidden" value="${rsMap.tabHide}">
<input id="code" type="hidden" value="">
<input id="pageSiteCode" type="hidden" value="">
<input id="siteCodeFlag" type="hidden">
<input id="siteCodeFlagType" type="hidden">
<input id="sitenum" type="hidden" value="">
<input id="linkerrprop" type="hidden" value="">
<input id="linkerrpropVal" type="hidden" value="">
<input id="bmType" type="hidden" value="">
<input id="configureTabId" type="hidden" value="0">

<input id="dayVal" type="hidden" value="14">
<input id="contentType" type="hidden" value="1">
<input id="radioVal" type="hidden" value="">
<input id="pageSizeVal" type="hidden" value="1">
<input id="tabId" type="hidden" value="">
<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%=path%>/images/common/top-hover.png"/>
        <img class="top-img" src="<%=path%>/images/common/top.png"/>
    </a>
</p>
<%@ include file="/common/top.jsp"%>
<!-- <iframe class="main_iframe" src="../common/header.html" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="60" style="position:relative; z-index:10;"></iframe> -->
<!-- <div class="page_tit nav"> -->
<!-- 	<i class="z_h_icon pull-left"></i> -->
<!--     <h3 class="pull-left">bm54130001- 广州市政府</h3> -->
<!--     <div class="z_h_time pull-right">数据更新时间：2015-08-16  24：00：00</div> -->
<!-- </div> -->
<div class="main-container container">

	<div class="row-fluid">
<!--         <iframe class="main_iframe" src="../common/leftmenu.html" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="225" style="position:absolute; height:100%; z-index:10;"></iframe> -->
        <c:set var="ba_index" value="504" scope="request" />
				<c:set var="menu" value="#menuBg" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<h4 class="main_title">日常监测统计</h4>
            <div class="five_tab_part">
                <div class="five_tp_top">
                    <div class="every_block clearfix">
                        <div class="fl every_tip on" id="tab0" style="width:150px;display:none" >本级站点</div>
                        <div class="fl every_tip" id="tab1" style="width:150px;display:none" >下级地方门户</div>
                        <div class="fl every_tip" id="tab2" style="width:150px;display:none">下级地方站群</div>
                        <div class="fl every_tip" id="tab3" style="width:150px;display:none" >下级部门站群</div>
                        <div class="fl every_tip" id="tab4" style="width:150px;display:none" >下级部门门户</div>
                        <div class="fr yiny" onclick="configTabInfo()">
                        	<i></i>
                        	<span>将大数据引用到我的网站上</span>
<!--                         	<i></i> -->
                        </div>
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
                            <!--中间开始-->
                            <div class="nav_center fr period">
                                <div class="area bread">
                                    <span id="dayName">两周</span>
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
                            <div class="chart_box show_box">
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
                                    <div class="chart_se">
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
                                           <!--  <h5 class="fl" id="chartTitle">监测不连通 <span>（网站数）</span></h5> -->
                                            
                                        </div>
                                    </div>
                                    <div class="cb_chartContent" id="chartContent" style="height:700px; width: 900px;">
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

                   

                </div>
            </div>
            <div >
				   <%@ include file="/common/footer.jsp"%>	
				   </div>

        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<!--大数据设置开始-->
<div class="modal fade bigdata" id="setting" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;display: none;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative; height: 45px;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true" onclick="showSetting()">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    引用
                </h4>
            </div>
            <div class="modal-body"  style="max-height:500px;">
                <div class="top-tab clearfix">
                    <div class="yiny-every-tab fl on">焦点推荐</div>
                    <div class="yiny-every-tab fl">数据详情</div>
                    <div class="yiny-every-tab fl">引用须知</div>
                </div>
                <div style="height: 450px;">
                    <div  id="focusTab0" name="focusTab">
                        <div class="page_url">
                    <h4>引用地址：</h4>
                    <p id="recommendUrl">
                        http://pucha.kaipuyun.cn/exposure/jiucuo.html?site_code=1310280014&url=http%3A%2F%2Fwww.dcwrmfw.com%2Fguestbook.jspx
                    </p>
                    <p class="remark">
                        ( 复制链接,使用iframe引用即可将我们的数据放置在您的网站上 )
                    </p>
                    <div class="btn_group clearfix">
                        <div class="fl copy" onclick="dailyCopyUrl()" name="copyCon">复制链接</div>
                        <div class="fl preview" onclick="dailyOpenUrl()">预览</div>
                    </div>
                </div>
                        <div class="config">
                        <div class="config-everytip clearfix line-one" >
                            <div class="config-tit fl tit-left">显示指标：</div>
                            <div class="config-det fl">
                                <label for="fir-item">
                                    <input type="radio" name="show-item" id="fir-item1" value="1"/>
                                    <span class="active-input_sel_active" id="fir-itemSpan1">健康指数</span>
                                </label>
                                <label for="fir-item">
                                    <input type="radio" name="show-item" id="fir-item2" value="2"/>
                                    <span class="active-input_sel_active" id="fir-itemSpan2">不连通率</span>
                                </label>
                                <label for="fir-item">
                                    <input type="radio" name="show-item" id="fir-item3" value="3"/>
                                    <span class="active-input_sel_active" id="fir-itemSpan3">更新量</span>
                                </label>
                            </div>
                        </div>
                        <div class="config-everytip clearfix line-two">
                            <div class="config-tit fl tit-left">默认显示条数：</div>
                            <div class="config-det fl tip-numbs">
                                <input type="text" id="showNum"/>
                            </div>
                        </div>
                        <div class="config-everytip clearfix line-three">
                            <div class="config-tit fl tit-left">焦点推荐链接：</div>
                            <div class="config-det fl">
                                <p>点击焦点推荐可以连接到数据详情页，如果您需要嵌套详情页的样式，请在这里修改嵌套后的页面地址,
                                    没有嵌套详情页样式的话，请不要修改下面的链接地址。</p>
                            </div>
                        </div>
                        <div class="config-everytip clearfix tag">
                            <div class="config-tit fl tit-right">Tab标签1：</div>
                            <div class="config-det fl">
                                <input type="text" id="focusUrlOne"/>
                            </div>
                        </div>
                        <div class="config-everytip clearfix tag">
                            <div class="config-tit fl tit-right">Tab标签2：</div>
                            <div class="config-det fl">
                                <input type="text" id="focusUrlTwo"/>
                            </div>
                        </div>
                        <div class="config-everytip clearfix tag">
                            <div class="config-tit fl tit-right">Tab标签3：</div>
                            <div class="config-det fl">
                                <input type="text" id="focusUrlThree"/>
                            </div>
                        </div>
                    </div>
                        <div class="recommend-bottom clearfix">
                        <div class="reset fr" onclick="updateBigAuthDetail(3)">恢复默认值</div>
                        <div class="ensure fr" onclick="updateBigAuthDetail(2)">确定</div>
                    </div>
                    </div>
                    <div style="display: none;" id="focusTab1" name="focusTab">
                        <div class="page_url" style="border-bottom: none;">
                        <h4>引用地址：</h4>
                        <p id="detailsUrl">
                            http://pucha.kaipuyun.cn/exposure/jiucuo.html?site_code=1310280014&url=http%3A%2F%2Fwww.dcwrmfw.com%2Fguestbook.jspx
                        </p>
                        <p class="remark">
                            ( 复制链接,使用iframe引用即可将我们的数据放置在您的网站上 )
                        </p>
                        <div class="btn_group clearfix">
                            <div class="fl copy" onclick="dailyCopyUrl()" name="copyCon">复制链接</div>
                            <div class="fl preview"  onclick="dailyOpenUrl()">预览</div>
                        </div>
                    </div>
                    </div>
                    <div style="display: none;" id="focusTab2" name="focusTab">
                        <div class="explain-box" style="background: none; border: none; padding:0;"  >
                            <div class="every_line">
                                <i></i>
                                <p>
                                    复制链接，使用iframe引用即可将我们的数据放置在您的网站上；
                                </p>
                            </div>
                            <div class="every_line">
                                <i></i>
                                <p>
                                    本页面默认一年有效期；
                                </p>
                            </div>
                            <div class="every_line">
                                <i></i>
                                <p>
                                    输出页面的Tab标签名称可以修改；
                                </p>
                            </div>
                            <div class="every_line">
                                <i></i>
                                <p>
                                    本服务只提供给开普云监管平台的签约客户。
                                </p>
                            </div>
                        </div>
                        <p class="change-tit">修改有效期或Tab标签名称</p>
                        <div class="interval clearfix">
                            <div class="fl left_title">有效期：</div>
                            <div class="fl time_p">
                                <div class="time_start fl">
                                    <input  class="datepicker-input" type="text" id="datepicker_start"/>
                                </div>
                                <span class="fl">至</span>
                                <div class="time_end fl">
                                    <input  class="datepicker-input" type="text" id="datepicker_end"/>
                                </div>
                            </div>
                        </div>
                        <div class="user-defined">
                            <div class="clearfix">
                                <div class="fl left_title" >Tab标签名称1：</div>
                                <div class="fl ">
                                    <div class="defined_inp fl">
                                        <input type="text" id="tabName1"/>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="fl left_title" >Tab标签名称2：</div>
                                <div class="fl ">
                                    <div class="defined_inp fl">
                                        <input type="text" id="tabName2"/>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="fl left_title" >Tab标签名称3：</div>
                                <div class="fl ">
                                    <div class="defined_inp fl">
                                        <input type="text" id="tabName3"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="footer clearfix ">
                            <div class="cancle fr" onclick="showSetting()" data-dismiss="modal" aria-hidden="true">取消</div>
                            <div class="insure fr" onclick="updateBigAuthDetail(1)">确定</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--大数据设置结束-->




<!--大数据提示开始-->
<div class="modal fade bigdata" id="prompt" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%; display: none;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative; height: 45px;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    提示
                </h4>
            </div>
            <div class="modal-body" >
                <p class="prompt_tips">
                    <i class="light_icon"></i>
                    此功能是付费功能，请联系开普云监管平台的服务团队：
                </p>
                <p class="prompt_num">4000-976-005</p>
            </div>
        </div>
    </div>
</div>
<!--大数据提示结束-->



<!--大数据设置开始-->
<!-- <div class="modal fade bigdata" id="setting" tabindex="-1" role="dialog" -->
<!--      aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;"> -->
<!--     <div class="modal-dialog"> -->
<!--         <div class="modal-content" > -->
<!--             <div class="modal-header green_head" style=" position: relative; height: 45px;"> -->
<!--                 <div type="button" class="close green_head_closeicon" -->
<!--                      data-dismiss="modal" aria-hidden="true"> -->
<!--                 </div> -->
<!--                 <h4 class="modal-title green_head_title" id="myModalLabel"> -->
<!--                      设置 -->
<!--                 </h4> -->
<!--             </div> -->
<!--             <div class="modal-body" > -->
<!--                 <div class="page_url"> -->
<!--                     <h4>大数据页面URL地址：</h4> -->
<!--                     <p id="setting_url"> -->
<!--                         http://pucha.kaipuyun.cn/exposure/jiucuo.html?site_code=1310280014&url=http%3A%2F%2Fwww.dcwrmfw.com%2Fguestbook.jspx -->
<!--                     </p> -->
<!--                     <div class="btn_group clearfix"> -->
<!--                         <div class="fl copy" onclick="dailyCopyUrl()" id="copyCon">复制链接</div> -->
<!--                         <div class="fr preview" onclick="dailyOpenUrl()">预览</div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="explain-box"> -->
<!--                     <div class="every_line"> -->
<!--                         <i></i> -->
<!--                         <p> -->
<!--                             复制链接，使用iframe引用即可将我们的数据放置在您的网站上； -->
<!--                         </p> -->
<!--                     </div> -->
<!--                     <div class="every_line"> -->
<!--                         <i></i> -->
<!--                         <p> -->
<!--                             本页面默认一年有效期；超时不能使用时，请登录本界面 <a href="javascript:configTabInfo('modify')" class="link_sty">修改有效期</a>； -->
<!--                         </p> -->
<!--                     </div> -->
<!--                     <div class="every_line"> -->
<!--                         <i></i> -->
<!--                         <p> -->
<!--                             输出页面的Tab标签名称可以<a href="javascript:configTabInfo('modify')" class="link_sty">修改</a>； -->
<!--                         </p> -->
<!--                     </div> -->
<!--                     <div class="every_line"> -->
<!--                         <i></i> -->
<!--                         <p> -->
<!--                             本服务只提供给开普云监管平台的签约客户。 -->
<!--                         </p> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<!--大数据设置结束-->








<!-- <div class="modal fade bigdata" id="modify" tabindex="-1" role="dialog" -->
<!--      aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;"> -->
<!--     <div class="modal-dialog"> -->
<!--         <div class="modal-content" > -->
<!--             <div class="modal-header green_head" style=" position: relative; height: 45px;"> -->
<!--                 <div type="button" class="close green_head_closeicon" -->
<!--                      data-dismiss="modal" aria-hidden="true" onclick="showSetting()"> -->
<!--                 </div> -->
<!--                 <h4 class="modal-title green_head_title" id="myModalLabel"> -->
<!--                    修改 -->
<!--                 </h4> -->
<!--             </div> -->
<!--             <div class="modal-body" > -->
<!--                 <div class="page_url"> -->
<!--                     <h4>大数据页面URL地址：</h4> -->
<!--                     <p id="modify_url"> -->
<!--                         http://pucha.kaipuyun.cn/exposure/jiucuo.html?site_code=1310280014&url=http%3A%2F%2Fwww.dcwrmfw.com%2Fguestbook.jspx -->
<!--                     </p> -->
<!--                 </div> -->
<!--                 <div class="interval clearfix"> -->
<!--                     <div class="fl left_title">有效期：</div> -->
<!--                     <div class="fl time_p"> -->
<!--                         <div class="time_start fl"> -->
<!--                             <input class="datepicker-input" type="text" id="datepicker_start" value=""/> -->
<!--                         </div> -->
<!--                         <span class="fl">至</span> -->
<!--                         <div class="time_end fl"> -->
<!--                             <input class="datepicker-input" type="text" id="datepicker_end" value=""/> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="user-defined"> -->
<!--                     <div class="clearfix"> -->
<!--                         <div class="fl left_title">Tab标签名称1：</div> -->
<!--                         <div class="fl "> -->
<!--                             <div class="defined_inp fl"> -->
<!--                                 <input type="text" id="tabName1"/> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="clearfix"> -->
<!--                         <div class="fl left_title">Tab标签名称2：</div> -->
<!--                         <div class="fl "> -->
<!--                             <div class="defined_inp fl"> -->
<!--                                 <input type="text" id="tabName2"/> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="clearfix"> -->
<!--                         <div class="fl left_title">Tab标签名称3：</div> -->
<!--                         <div class="fl "> -->
<!--                             <div class="defined_inp fl"> -->
<!--                                 <input type="text" id="tabName3"/> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="footer clearfix "> -->
<!--                 <div class="cancle fr" data-dismiss="modal" aria-hidden="true" onclick="showSetting()">取消</div> -->
<!--                 <div class="insure fr"  onclick="updateBigAuthDetail();">确定</div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<script language="javascript" type="text/javascript">
$(function(){ 
    /*组织单位--日常监测统计*/
/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
	$("#datepicker_start").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		numberOfMonths:1,//显示几个月  
		showButtonPanel:true,//是否显示按钮面板  
		dateFormat: 'yy-mm-dd',//日期格式  
		clearText:"清除",//清除日期的按钮名称  
		closeText:"关闭",//关闭选择框的按钮名称  
		currentText:"今天", 
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		defaultDate:GetDateStr(0),//默认日期
// 		minDate : GetDateStr(-365),//最小日期
// 		maxDate:GetDateStr(-7),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
// 		$("#startDate").val(dateText);
		}
	});
	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
	$("#datepicker_end").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		numberOfMonths:1,//显示几个月  
		showButtonPanel:true,//是否显示按钮面板  
		dateFormat: 'yy-mm-dd',//日期格式  
		clearText:"清除",//清除日期的按钮名称  
		closeText:"关闭",//关闭选择框的按钮名称  
		currentText:"今天", 
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		defaultDate:GetDateStr(365),//默认日期
// 		minDate : ,//最小日期
// 		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
// 		$("#endDate").val(dateText);
	}
	});
//      $('#prompt').modal({

//     });

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