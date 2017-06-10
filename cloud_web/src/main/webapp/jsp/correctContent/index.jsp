<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>错别字</title>
	<%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/heade.jsp"%>
  </head>
<body class="bg_f5f5f5">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
<%@ include file="/common/leftmenu.jsp"%>
	<div class="row-fluid">
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                   <li><a href="#">深度检测</a> <span class="divider">></span></li>
                   <li class="active">错别字</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con">
                                <div class="ztm-con">
                                    <p>1.考察严重字词问题，包括：严重错别字（例如，将党和国家领导人姓名写错）、虚假或伪造内容（例如，严重不符合实际情况的文字、图片、视频）以及反动、暴力、色情等内容；严重字词问题一经发现即触发预警。</p>
                                    <p>2.考察信息内容不准确问题，包括：动态、要闻、通知公告、政策文件、机构设置及职能、规划计划、人事等信息不准确。</p>
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
			<c:if test="${sessionScope.shiroUser.iscost==0}">
                <div class="free-html">
                    <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
                </div>
            </c:if>
            <c:if test="${sessionScope.shiroUser.iscost==1}">
             <div class="row t_box5 mar1">
                <div class="pie-chart span6" style="display:none;">
                    <h3>昨天新增网页错别字</h3>
                    <div class="pie-chart-con">
                        <div id="correct_content_bar" style="height: 220px;width: 100%"></div>
                    </div>
                </div>

                <div class="t_box6">
                    <h3>疑似错别字统计</h3>
                    <div class="pie-chart-con">
                   		<div id="correct_content_line" style="height: 220px;width: 100%"></div>
                    </div>
                </div>

            </div><!-- /row2 -->
            <div class="row">
            	<div class="tab_header row">
                	<h3>疑似错别字监测结果 <input class="datepicker-input" type="text" id="datepicker_start"/></h3>
                    <h3><input class="datepicker-input" type="text" id="datepicker_end" /></h3>
                    <div class="input-prepend">
                      	<span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      	<input class="span2 prependedInput" id="keyInput" type="text" placeholder="输入疑似错误...">
                    </div>
                    <div class="page-btn1 hide" onclick="correctContentExcel()" id="table_accuracy_content_hide"><i></i>导出列表</div>
                </div>
                <div class="tab_box1 row">
                	<input type="hidden" id="select_id"/>
                	<input type="hidden" id="siteBeginServiceDate_id" value="${returnMap.serviceBeginDate}"/>
                	<input type="hidden" id="scanDate_id_start" value=""/>
                	<input type="hidden" id="scanDate_id_end" value=""/>
                	
                	<div class="tab-sel1-con">
                        <div class="pull-left">错误类型：</div>
                        <div class="pull-left">
                         	<label class="error-label qb"><input type="radio" name="error" checked="checked"/>全部</label>
                        	<label class="error-label yz"><input type="radio" name="error" /> 严重错误</label>
                            <label class="error-label yb"><input type="radio" name="error"/> 一般错误</label>
                        </div>
                    </div>
                    <div class="dropload-load"><span class="loading"></span>加载中...</div>
                    <table cellpadding="0" cellspacing="0" class="table t-tab1" id="table_accuracy_content">
                    </table>
                   <!-- <div class="zanwsj" id="table_accuracy_content_hide1">监测无严重错误</div>  -->
                </div>
            </div>
            </c:if>
           <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/correctContent/correct_content_page.js"></script> 

<script type="text/javascript">
$(function(){
	$(".tab_box1 table tr:odd td").css("background","#fafbfc");
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
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		minDate:GetDateStr(-90),//最小日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
		$("#scanDate_id_start").val(dateText);
			//分页列表数据--错别字监测结果
			correctContentPage("");
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
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		defaultDate:GetDateStr(-1),//默认日期
		minDate:GetDateStr(-90),//最小日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
		$("#scanDate_id_end").val(dateText);
			//分页列表数据--错别字监测结果
			correctContentPage("");
	}
	});
	$(".error-label").click(function(){
		if($(this).hasClass("yz")){
			$("#select_id").val(3);
			selectCorrectContent();
		}else if($(this).hasClass("yb")){
			$("#select_id").val(1);
			selectCorrectContent();
		}else{
			//全部
			$("#select_id").val("");
			selectCorrectContent();
		}
	});
	//初始化柱状图
	//correctContentBar();
	//初始化折线图
	correctContentLine();	
	 //关键字查询
 	$("#keyInput").keyup(
		function() {
			table_accuracy_content.fnReloadAjax(webPath+"/correctContent_correctContentPage.action");
		}
	);
/* 	
	$("#table_accuracy_content_queryAll_id").click(function(){
		var correctType="all";
  		correctContentPage(correctType);
	}); */
})

//错误类型   下拉框事件
function selectCorrectContent(){
	table_accuracy_content.fnReloadAjax(webPath+"/correctContent_correctContentPage.action");
}


//导出Excel
function correctContentExcel(){
	var beginScanDate=$("#scanDate_id_start").val();
	var endScanDate=$("#scanDate_id_end").val();
	var selectType=$("#select_id").val();
	var key=$("#keyInput").val();
	window.location.href=webPath+"/correctContent_correctContentExcel.action?beginScanDate="+beginScanDate+"&endScanDate="+endScanDate+"&selectType="+selectType+"&key="+key;
}

//联动分页查询
function correctContentPage(correctType){
	if(correctType==null){
		correctType="";
	}
	if($("#scanDate_id_start").val()>$("#scanDate_id_end").val()){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}
   	table_accuracy_content.fnReloadAjax(webPath+"/correctContent_correctContentPage.action?correctType="+correctType);
   	
	
}
</script>
<script type="text/javascript">

//初始化折线图
function  correctContentLine(){
	setTimeout(function(){
		  var problemNum=[];
		  var scanDateList=[];
		  var myCharts_line=echarts.init(document.getElementById("correct_content_line"));
		   $.ajax( {  
		        type : "POST",  
		        url : webPath+"/correctContent_correctContentLine.action",  
		        dataType:"json",
		        async : false,
		        success : function(data) {
		             for (var j = 0; j < data.length; j++) {
		            	 problemNum.push(data[j].wrongNum);
		            	 scanDateList.push(data[j].scanDate);
					}
		        }
		   });
		   option = {
			  tooltip : {
					trigger: 'axis',
					backgroundColor: 'rgba(0,0,0,0.4)',
					axisPointer: {
	                width: 'auto',
	                type: 'default'
	                 },
	                 formatter:"{b}<br/>{a}：{c}个",
	                 padding:15,
	                 textStyle : {
			           fontSize: 12
			           }
	             },
	            dataZoom : {
			        show : true,
			        realtime : true,
			        height: 20,
			        start : 100,
			        y:200,
			        end : 60
		   		 },
	    	    grid:{
	    	      borderColor:'#fff',
	                 x:30,
	                 y:40,
	                 x2:20
	            }, 
	    	    xAxis : [
	    	        {
		        	 type : 'category',
		        	 axisLine:false,
		        	 splitLine:false,
		        	 axisTick:false,
	    	         data : scanDateList
	    	        }
	    	    ],
	    	    yAxis : [
	    	        {
	    	            name: "问题总数\n",
	    	            nameTextStyle:{color:'black'},
	    	            type : 'value',
	    	            axisLine:{
	   			          lineStyle: {
	   			            color: '#ffffff',
	   			               width:1
	   			                }
	                         }
	    	        }
	    	    ],
	    	    series : [
	    	        {
	    	            name:'问题总数',   
	    	            type:'line',
	    	            symbol:'circle',
			    	    symbolSize:4,
	    	            data: problemNum,
	    	            itemStyle:{
	    	              normal:{
	    	              color:'#02ABAE'
	    	              }
	    	            }
	    	        }
	    	    ],
	    	    calculable:false
	    	};    
		   myCharts_line.setOption(option,true);
	},100);
}

</script>
</body>
</html>
