<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>网站访问量</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css"/>
	<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jcVisitSitecode/index.js"></script>
</head>
  
<body style="background:#f5f5f5;">
	<!--头部       satrt  -->
	<%@ include file="/common/top_tb.jsp"%>
	<!--头部       end  -->
	<input type="hidden" id="siteCode" value="${siteCode}">


<div class="main-container container mp mp-t"> <!--mp:major portal-->
	<div class="row-fluid"> 
		<c:set var="tb_index" value="32" scope="request"/>
		<c:set var="menu" value="#" scope="request"/>
			<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content paddt-27" >
        	<h4 class="main_title">网站访问量</h4>
            <div class="five_tab_part">
                <div class="five_tp_content">
                    <!--*个块-第一个开始-->
                    <div class="every_tab on">
                        <!--搜索引擎收录趋势开始-->
                        <div class="trend-SearchEngine clearfix" style="height:460px;">
                             <div class="trend-top">
                                <div class="trend-title fl fs-n-18-Mic color-3c3d45" style="margin:7px 0 0 20px;">
                                  <div class="siteVisits_sel tb-sv-chart_se fl">
                                        <span id="siteTypeText">访客量走势图</span>
                                        <i></i>
                                        <!--移入显示块开始-->
                                        <ul id="siteTypeUl" style="display: none;">
                                            <li value="1" name="monitorType">访客量走势图</li>
                                            <li value="0" name="monitorType">浏览量走势图</li>
                                        </ul>
                                        <!--移入显示块结束-->
                                    </div>
                                   <!--  <select class="siteVisits_sel" onChange="getSiteVisit()" id="selectSiteVisit" >
                                        <option value="0">浏览量走势图</option>
                                        <option value="1">访客量走势图</option>
                                    </select> -->
                                </div>
                                <div class="trend-time fr fs-n-14-Mic color-646464">
                                    <span>时间控制：</span>
                                    <div class="seveal_inp clearfix">
                                        <div class="every_inp">
                                            <input type="radio" id="twoWeeks" value="-14" name="days"/>
                                            <label for="twoWeeks">2周</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="oneMonths" value="-30" name="days"checked="checked"/>
                                            <label for="oneMonths">1个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="threeMonths" value="-90" name="days"/>
                                            <label for="threeMonths">3个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="sixMonths" value="-180" name="days"/>
                                            <label for="sixMonths">6个月</label>
                                        </div>
                                        <div class="every_inp">
                                            <input type="radio" id="twelveMonths" value="-365" name="days"/>
                                            <label for="twelveMonths">1年</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
	                         <div class="t_box4" style="height:388px;border:none;">
			                    <div class="line_chart">
			                    	<div id="jcVisisChart" style="height:380px; width:105%;">
			                        	
			                        </div>
			                    </div>
			                </div>
                        </div>
                    </div>
                 </div>
            </div>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            
         </div><!-- /page-content -->
            
             
     </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/dropload.min.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
    /*$(".prependedInput").focus(function(){
        $(this).parent(".input-prepend").css("border-color","#fff");
    });
    $(".prependedInput").blur(function(){
        $(this).parent(".input-prepend").css("border-color","#603cba");
    });
	*//*-------------------------滚动加载开始---------------------*//*
	var counter = 0;
    // 每页展示4个
    var num = 4;
    var pageStart = 0,pageEnd = 0;*/
		
    // dropload
   /* $('#droploadTab').dropload({
        scrollArea : window,
        loadDownFn : function(me){
            $.ajax({
                type: 'GET',
                url: 'json/more.json',
                dataType: 'json',
                success: function(data){
                    var result = '';
                    counter++;
                    pageEnd = num * counter;
                    pageStart = pageEnd - num;
                    for(var i = pageStart; i < pageEnd; i++){
                        result += '<tr><td class="td_left font_701999"><a href="www.baidu.com" target="_blank">bm54130004</a></td></tr>';
						
                        if((i + 1) >= data.lists.length){
                            // 锁定
                            me.lock();
                            // 无数据
                            me.noData();
                            break;
                        }
                    }
                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $("#droploadTab table tbody").append(result);
                        // 每次数据加载完，必须重置
                        me.resetload();
                    },1000);
                },
                error: function(xhr, type){
                    alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
        }
    });*/


    /*组织单位--日常监测统计*/

    //tab切换
    //各大块切换；
   /* $('.every_tip').click(function(){
        var n = $(this).index();
        $('.every_tab').removeClass('on');
        $('.every_tip').removeClass('on');
        $(this).addClass('on');
        $('.every_tab').eq(n).addClass('on');
    });*/


    //表格隔行换色；
   /* $("table tbody tr:odd td").css("background","#fafbfc");*/
}) 
</script>
</body>
</html>