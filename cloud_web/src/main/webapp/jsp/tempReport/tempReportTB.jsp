<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>临时报备</title>
    <%@ include file="/common/meta.jsp"%>
 <%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/>
 	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dailyMonitoringStatistics.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css" />
	<link rel="stylesheet" href="<%=path%>/css/llbb.css"/>

  </head>
  <body class="bg_f5f5f5">
  <input type="hidden" id="siteCode" value="${siteCode}">
		<!--头部       satrt  -->
		<%@ include file="/common/top_tb.jsp"%>
		<!--头部       end  -->
	<div class="main-detail">
		<div class="main-detail-content">
				<%@ include file="/common/leftmenu.jsp"%>
			<div class="page-content">
				<div class="daily-monitor-box-m conditions">
					<div class="bread-crumbs daily-monitor-box-m">
					<div class="bread-crumbs-content">
						<span class="">网站临时报备</span> <!-- <i class="cor-0498e4">></i> <span>搜索引擎收录</span> -->
					</div>
					</div>
					<div class="txt-describe" style="margin-bottom:15px;">
						<p class="single-p">
							此页面可以申请临时报备（关停；例外），一旦申请通过，在申请时间内发现的问题将不再计入统计。申请时间结束后将恢复正常监测状态。</p>
					</div>
					<div class="conditions-content clearfix" style="border-top:1px solid #ddd;border-bottom:1px solid #ddd;">
                		
               				<input type="button" value="申请报备" onclick="tan()" style="width:92px;height:32px;text-align: center;color: rgb(80, 81, 82);background: rgb(241, 245, 249);display:inline-block;font: normal 13px/32px 'Microsoft YaHei';border: 1px solid #bec9d3;"/> 
            			
					</div>
				<div class="data-show">
					<table id="table-webSort" cellpadding="0" cellspacing="0"
						class="table">
						<thead>
							<tr>
								<th class="w20p th-center" style="width:70px;">序号</th>
								<th class="w20p th-center">申请人</th>
								<th class="w20p th-center">报备原因</th>
								<th class="w20p th-center">详细说明</th>
								<th class="w20p th-center">报备时间</th>
								<th class="w20p th-center">影响日期</th>
								<th class="w20p th-center">状态</th>
								
							</tr>
						</thead>
					</table>
					<div class="baobei_table_part" style="margin:0 !important;">
					<table id="temporary_result_table_id"  cellpadding="0" cellspacing="0" class="table temporarytab">
						<tbody id="tobodyy">
						</tbody>
					</table>
					</div>
				</div>
				<!--底部    start-->
				<%@ include file="/common/footer.jsp"%>
				<!--底部    end-->
			</div>
		</div>
	</div>
	<!-- Modal -->



<div class="page-modal modal fade hide baobei_t_modal" id="baobei_t_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 900px; left: 50%; margin-left: -450px;">
    <div class="baob_m_top clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl">
            申请报备
        </h4>
    </div>
    <div class="baob_m-content">
        <div class="title_inp clearfix">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
               申请人：
            </div>
            <div class="fl right_inp">
                <input type="text" id="reportName"/>
            </div>
        </div>
        <div class="content_sel clearfix">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
               报备原因：
            </div>
            <div class="fl right_inp">
                <select name="reportReason" id="reportReason">
                </select>
            </div>
        </div>
        <div class="contact_inp clearfix">
            <div class="fl tit">
            <!-- <i class="red-mi"></i>-->
            详细说明：
            </div>
            <div class="fl right_inp">
                <textarea name="" id="reportDetail" style="resize: none;"></textarea>
            </div>
        </div>
        <div class="time_checkbox clearfix">
            <div class="fl tit">
                <!-- <i class="red-mi"></i>-->
                影响日期：
            </div>
            <div class="fl right_inp">
                <div id = "radioLoca" class="radio_parts clearfix">
                    <div class="fl" id="continue">
                        <label>
                            <input type="radio" class="radioItem" name="isCycle" value="2" checked="checked" />
                            持续性
                        </label>
                    </div>
                    <div class="fl" id="period">
                        <label>
                            <input type="radio" class="radioItem" name="isCycle" value="1"/>
                            周期性
                        </label>
                    </div>
                </div>

 				<div class="time_p on" id="last">
                    <div class="top clearfix">
                        <div class="data fl">
                         <input class="Wdate" type="text" id="reportStartDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s',maxDate:'#F{$dp.$D(\'reportEndDate\')}'})"> 
                         </div>
                        <span class="fl">至</span>
                        <div class="data fl">
                        <input class="Wdate" type="text" id="reportEndDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %H:%m:%s',minDate:'#F{$dp.$D(\'reportStartDate\')}'})"> 
                        </div>
                    </div>
                </div>


                <div class="time_p on" style="display: none" id="cycle">
                    <div class="top clearfix">
                        <div class="data fl">
                         <input class="Wdate" type="text" id="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endDate\')}'})"> 
                         </div>
                        <span class="fl">至</span>
                        <div class="data fl">
                        <input class="Wdate" type="text" id="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startDate\')}'})"> 
                        </div>
                    </div>
                    
                <div class="center">每天</div>

                <div class="top clearfix">
                        <div class="data fl">
                         <input class="Wdate" type="text" id="startTime" onfocus="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'})"> 
                         </div>
                        <span class="fl">至</span>
                        <div class="data fl">
                        <input class="Wdate" type="text" id="endTime" onClick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"> 
                        </div>
                 </div>
            </div>
        </div>
    </div>
    <div class="baob_m-footer clearfix">
        <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
        <div class="ensure fr" onclick="sub()">提交</div>
    </div>
</div></div>
<script language="javascript" type="text/javascript" src="<%=path%>/js/tempReport/tempReportTB.js"></script>
	<script language="javascript" type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.tips.js"></script>
	<script language="javascript" type="text/javascript">
		$(function(){
		
		    $(".baobei_table_part table tr:odd").css("background","#f7faff");
		    $(".baobei_table_part table tbody tr:odd").hover(function(){
		        $(this).css("background","#b5f0dd");
		    },function(){
		        $(this).css("background","");
		    });
		
		    $(".baobei_table_part table tbody tr:even").hover(function(){
		        $(this).css("background","#b5f0dd");
		    },function(){
		        $(this).css("background","#f7faff");
		    });
		
			$(window).scroll(function () {
				if ($(window).scrollTop() > 100) {
					$("#backToTop").fadeIn(500);
				} else {
					$("#backToTop").fadeOut(500);
				}
			});
			$("#backToTop").click(function () {
				$('body,html').animate({scrollTop: 0}, 600);
				return false;
			});
			$("input[type='checkbox'],input[type='radio']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue'
			});
			
		})
	
	</script>
</body>
</html>