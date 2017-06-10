<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<!--    <title>栏目信息不更新</title> -->
   <title>基本信息</title>
   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/heade.jsp"%>
    <script language="javascript" type="text/javascript" src="<%= path %>/js/updateChannel/update_channel_detail.js"></script>
    <!-- 连通性结果弹框样式 -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/lastConnection.css" /> 
  </head>
 <body class="bg_f5f5f5">
 <input type ="hidden" value="${sessionScope.shiroUser.siteCode }" id = "siteCode">
 <input id="radioVal" type="hidden" value="">
 <input id="systemTwo" type="hidden" value="1">
 <input id="scanDateSystem" type="hidden">
 <input id="modeTB" type="hidden" value="${modeTB}">
  <input id="isSenior" type="hidden" value ="${isSenior}">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	  <c:set var="tb_index" value="8" scope="request"/>
	  <c:set var="menu" value="#menuBzwt" scope="request"/>
	  <%@ include file="/common/leftmenu_tb.jsp"%>
        <div class="page-content">
            <c:if test="${sessionScope.shiroUser.iscost==0}">
                <div class="free-html">
                    <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
                </div>
            </c:if>
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">内容保障问题</a> <span class="divider">></span></li>
                  <li class="active">基本信息</li>
                    <li class="jc-ms" style="display:none;">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <h5 class="tit-h" style="margin-top:10px;">考察网站栏目信息应更新而实际未更新的情况。</h5>
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
            	<div class="tab_header row">
                	<h3>基本信息检测结果 
<!--                 	<input class="datepicker-input" type="text" id="datepicker" value="${yesDate}"/> -->
                	</h3>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="span2 prependedInput" id="keyInput" type="text" placeholder="输入栏目名称、url...">
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<div id="unupdate_excel_out" class="page-btn1" style="display:none"  onclick="unUpdateChannelExcel()"><i></i>导出列表</div>
                    </c:if>
                </div>
                 <div class="detection-mode-box clearfix" style="padding-bottom:12px;background:#fff; border-bottom:1px solid #eceeee;">
	                <div class="everymode fl" style="margin-left:26px;">
		                    <input type="radio" name="mode" id="manual"  value = "1"/>
		                    <label for="manual">高级版</label>
	                </div>
	                <div class="everymode fl">
	                    <input type="radio" name="mode" id="system"  value = "2"/>
	                    <label for="system">标准版</label>
	                </div>
            	</div>
                <div class="tab-sel1">
                    <div class="tab-sel1-con">
                        <div  class="pull-left">栏目类别</div>
                        <div  class="pull-left">
                            <dl class="select">
                                <dt>全部</dt>
                                <dd>
                                    <ul id="params">
                                        <input type="hidden" id="types" value="0"/>
                                        <!-- <li value="4">
                                            <span class="font-704fc1">逾期不更新</span>
                                        </li> -->
                                         <li value="0">
                                            <span class="font-704fc1">全部</span>
                                        </li>
                                        <li value="1">
                                            <span class="font-704fc1">动态、要闻类</span><!-- <span class="font-ff0000">（要求两周内更新）</span> -->
                                        </li>
                                        <li value="2">
                                            <span class="font-704fc1">通知公告、政策文件类</span><!-- <span class="font-ff0000">（要求半年内更新）</span> -->
                                        </li >
                                        <li value="3">
                                            <span class="font-704fc1">人事、规划计划类</span><!-- <span class="font-ff0000">（要求一年内更新）</span> -->
                                        </li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <div class="pull-left" id="updateId">检测结果</div>
                        <div id="hight" class="pull-left">
                            <dl class="select-3">
                                <dt>逾期不更新</dt>
                                <dd>
                                    <ul id="isUpdate">
                                        <input type="hidden" id="updateVal" value="1"/>
                                        <li value="1">
                                            <span class="font-704fc1">逾期不更新</span>
                                        </li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                           <div id="low" class="pull-left">
                            <dl class="select-3">
                                <dt>逾期不更新</dt>
                                <dd>
                                    <ul id="isUpdate">
                                        <input type="hidden" id="updateVal" value="1"/>
                                        <li value="0">
                                            <span class="font-704fc1">全部</span>
                                        </li>
                                        <li value="1">
                                            <span class="font-704fc1">逾期不更新</span>
                                        </li>
                                        <li value="2">
                                            <span class="font-704fc1">正常更新</span>
                                        </li >
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <c:if test="${sessionScope.shiroUser.iscost==1}">
                         <div class=""> 
	                         <div id="blankDetailMonitoringPeriod" style="display:none;">
		                        <div class="pull-left">检测周期</div>
		                        <div class="pull-left">
		                            <dl class="selectselect" id = "selectTwo">
		                            	<dt id="dtp"></dt>
		                                <dd id = "ddb">
		                                        <input type="hidden" id="typesDate" value="${servicePdIdZZ}"/>
		                                </dd>
		                            </dl>
		                        </div> 
	                         </div>
                        
	                        <div id="detectionTime" style="display:none;">
		                        <div class="pull-left" >检测时间: </div>
								<div class="pull-left" id="detectionTimeOne">${yesDate}</div>
							</div>
						</c:if>
                        <div class="pull-right">
                        	<div class="ms-msg ms-msg2">
                                <div class="ms-wen-con">
                                    <div class="ztm-con">
                                        <ul>
                                            <li>【动态、要闻类】 2周更新</li>
                                            <li>【通知公告、政策文件类】 6个月更新</li>
                                            <li style="border-bottom:0px none;">【人事、规划计划类】 一年更新</li>
                                        </ul>
                                    </div>
                                    <i class="angle4"></i>
                                </div>
                                <div class="ms-icon-wen">
                                    <i class="i-wen">?</i>
                                </div>
                            </div>
                        	<!--************/ms-msg*****************-->
                        </div>
                        </div>
                        
                    </div>
                </div>
                <div class="tab_box1 row">
                <input type="hidden" id="dateText"/>
                	<input type="hidden" id="unupdate_channel_timetool_min"/>
                	<c:if test="${sessionScope.shiroUser.iscost==1}">
                        <div class="dropload-load"><span class="loading"></span>加载中...</div>
<!-- 	                    <table cellpadding="0" cellspacing="0" class="table t-tab1  width100" id="table_unUpdate_channel"> -->
<!-- 	                    </table> -->
<table cellpadding="0" cellspacing="0" class="table t-tab1 hide" id="table_unUpdate_channel_id">
	                        <thead>
	                            <tr id="th">
	                            	<th class="th_center" style="width:40px;">序号</th>
	                                <th class="th_left" style="width:10%;">栏目名称</th>
	                                <th class="th_left" style="width:105px;">栏目类别</th>
	                                <th class="th_left" style="width:150px;">问题类型</th>
	                                <th class="th_left">网址</th>
	                                <th class="th_left" style="width:100px;" id="bihidden">监测时间</th>
	                                <th class="th_left" style="width:100px;">最后更新时间</th>
	                                <th class="th_center" style="width:120px;">未更新天数</th>
<!-- 	                                <th style="width:120px;">逾期未更新</th> -->
	                                <th style="width:100px;">快照/截图</th>
	                                <!-- <th style="width:150px;style="position:relative;" onmouseover="commonscandateOver()" onmouseout="commonscandateOut()">最后正常监测时间 </th>-->
	                                 <div id="lud_detail" style="display: none;">
		                                                <div class="top">
		                                                    <p class="line_top"><span>*</span>监测服务器最后一次正常连通网站的时间。</p>
		                                                    <p class="line-bott">为了更全面的进行监测，建议联系您的网站管理员将开普云监管IP地址加入白名单，<span class="ip_address">查看IP地址 <i class="down"></i></span></p>
		                                                </div>
		                                                <div class="hidden_part">
		                                                    <div class="center">
		                                                        <div class="content" >
		                                                            <ul class="clearfix" id="whiteListIP"></ul>
		                                                        </div>
		                                                    </div>
		                                                    <div class="bottom clearfix">
		                                                        <div class="fl copy_all" id="commonscandate_copyAllId">复制全部</div>
		                                                        <div class="fl g_close" id="commonscandate_copyCloseId">关闭</div>
		                                                    </div>
		                                                </div>
                                            		</div>
	                                </th>
	                            </tr>
	                         </thead>
	                        <tbody id="tbody_unUpdate_channel_id">
	                        </tbody>
	                    </table>
	                    <div class="zanwsj" id="table_response_channel_hide1"></div>
                    </c:if>
                </div>
                <%@ include file="/common/footer.jsp"%>
            </div><!-- /tab2 -->
          
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<!-- 连通性结果弹框 -->
<%@ include file="/common/connectionDialog.jsp"%>
<script language="javascript" type="text/javascript">

$(function(){ 
var scanDateSystem = $("#scanDateSystem").val();
	$(document).ready(function(e) {
	//cbox样式
		$("input[type='checkbox'],input[type='radio']").iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
		});
	})

    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
  

	/*============================
	@author:flc
	@time:2015-10-08
	============================*/
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("li").click(function(){
			dt.html($(this).html());_hide();
			var params=$(this).val();
			$("#types").val(params);
			
			comboBoxChoice(params);
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	});
	$(".selectselect").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("li").click(function(){
			dt.html($(this).html());_hide();
			var params=$(this).val();
			$("#typesDate").val(params);
			comboBoxChoice(params);
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".selectselect").first().is(s) ? _hide():"";});
	});
	
	$(".select-3").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("li").click(function(){
			dt.html($(this).html());_hide();
			var params=$(this).val();
			$("#updateVal").val(params);
			comboBoxChoice(params);
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select-3").first().is(s) ? _hide():"";});
	});
	
	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/

	$("#datepicker").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		numberOfMonths:1,//显示几个月  
		showButtonPanel:true,//是否显示按钮面板  
		dateFormat: 'yy-mm-dd',//日期格式  
		clearText:"清除",//清除日期的按钮名称  
		closeText:"关闭",//关闭选择框的按钮名称  
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		defaultDate:GetDateStr(-1),//默认日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
		  //列表选择
		  unUpdateChannelList(dateText);
		} 
	});
	
	 
	 $("#table_unUpdate_channel_filter").hide();
     $("#table_unUpdate_channel_length").hide();
	//条件选择
	conditionalChoice();
	
}); 

	//下拉框选择
	function comboBoxChoice(params){
		//var key=$("#keyInput").val();
// 		table_unUpdate_channel.fnReloadAjax(webPath+ "/upChannel_updateChannelPage.action?");
		var systemTwo = $("#systemTwo").val();
      if(scanDateSystem != 2 || systemTwo !=1){
      	basicInfoList();
      }
	}
	//导出Excel
	function unUpdateChannelExcel(){
		var type=$("#types").val();
		var key=$("#keyInput").val();
		var servicePdId = $("#typesDate").val();//当前合同下周期
		var siteCode = $("#siteCode").val();
		var systemTwo = $("#systemTwo").val();//用来判断系统检测
		var updateVal = $("#updateVal").val();
		window.location.href=webPath+"/upChannel_unUpdateChannelExcel.action?type="+type+"&servicePdId="+servicePdId+"&key="+key+"&siteCode="+siteCode+"&systemTwo="+systemTwo+"&updateVal="+updateVal;

	}
	function conditionalChoice(){
		//关键字查询
		$("#keyInput").keyup(function() {
// 			table_unUpdate_channel.fnReloadAjax(webPath+ "/upChannel_updateChannelPage.action");
			basicInfoList();
		});
	}
	function unUpdateChannelList(date){
		$("#dateText").val(date);
		basicInfoList();
// 		table_unUpdate_channel.fnReloadAjax(webPath+ "/upChannel_updateChannelPage.action");
	}
</script>
<!-- 连通性弹框js -->
<script  type="text/javascript" src="<%=path%>/js/connectionShow.js"></script>
</body> 

</html>
