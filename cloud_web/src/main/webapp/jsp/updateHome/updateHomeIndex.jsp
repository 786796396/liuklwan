<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>首页更新</title>
   <%@ include file="/common/meta.jsp"%>
  <%@ include file="/common/heade.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<!-- 首页更新js引入 -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/updateHome/update_home_detail.js"></script>
  </head>
  
<body>
	<!--头部       satrt  -->
	<%@ include file="/common/top_tb.jsp"%>
	<!--头部       end  -->
	<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="15" scope="request"/>
		<c:set var="menu" value="#menuNrgx" scope="request"/>
		<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">日常监测</a> <span class="divider">></span></li>
                  <li class="active">首页更新</li>
                </ul>
            </div>
        	<div class="row hide">
                <div class="t_box2">
                	<h3>监测说明</h3>

                    <div class="t_box2_con" style="height:172px;">
                    	<p style="margin-top:10px;">1.考察网站每天首页信息更新的数量；</p>
                        <p>2.考察网站每天首页信息更新的明细。</p>
                    </div>
                </div>
            </div><!-- /row1 -->
            <div class="row">
            	<h3 class="info_fx_h3 bg-fff">首页更新趋势</h3>
                <div class="t_box4 info_fx_con">
                    <div class="line_chart">
                    	<div id="up_home_line_id" style="height:350px; width:100%;">
                        
                        </div>
                    </div>
                </div>
            </div><!-- /row3 -->
             
            
            <div class="row">
            	<div class="tab_header row">
                	<h3>首页更新明细</h3>
                    <div class="tab-hfont1 hide" id="table_data_homeUpdate_hide">共 <span></span> 条</div>
                    <div class="input-prepend hide" id="table_data_homeUpdate_hide1">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input  class="prependedInput span2" id="keyInput" type="text" placeholder="输入标题...">
                    </div>
                    <div class="page-btn1 hide" onclick="updateHomeExcel()" id="table_data_homeUpdate_hide2"><i></i>导出列表</div>
                </div>
                <div class="tab-sel1">
                    <div class="tab-sel1-con">
                        <div class="pull-left">时间选择</div>
                        <div class="pull-left radio-box1">
                        	<label><input type="radio" name="days"  value="1" checked="checked"/>&nbsp;昨天</label>
                            <label><input type="radio" name="days"  value="14" />&nbsp;2周</label>
                            <label><input type="radio" name="days" value="30" />&nbsp;1个月</label>
                            <label><input type="radio" name="days" value="90" />&nbsp;3个月</label>
                            <label><input type="radio" name="days" value="188" />&nbsp;6个月</label>
                            <label><input type="radio" name="days" value="365" />&nbsp;1年</label>
                        </div>
                    </div>
                </div>
                <div class="tab_box1 row">
                    <div class="dropload-load"><span class="loading"></span>加载中...</div>
                    <table cellpadding="0" cellspacing="0" class="table t-tab1" id="table_update_home">
                    </table>
                </div>
            </div>
          <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript">
$(function(){ 
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
    $("input[type='checkbox'],input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
    conditionalChoice();
	$("#table_update_home_filter").hide();
	$("#table_update_home_length").hide();
    linkHomeUnuseSum();
})
</script>
<script type="text/javascript">
function  conditionalChoice(){
	$("input[name='days']").on(
				'ifChecked',
				function(event) {
					table_update_home.fnReloadAjax(webPath
							+ "/updateHome_updateHomePage.action");
				});
		$("#keyInput").keyup(
				function() {
					table_update_home.fnReloadAjax(webPath
							+ "/updateHome_updateHomePage.action");
				});
	}
function updateHomeExcel(){
	var days=$("input[name='days']:checked").val();
	var key=$("#keyInput").val();
	window.location.href=webPath+"/updateHome_updateHomeExcel.action?days="+days+"&key="+key;
}
</script>
<script type="text/javascript">
	function linkHomeUnuseSum(){
		    var updateHomeNum = [];
		    var yearAndMouthNum =[];
		    var myChart_line = echarts.init(document.getElementById("up_home_line_id"));
		    $.ajax( {  
		        type : "POST",  
		        url : "<c:url value="/updateHome_updateConLine.action"/>",  
		        dataType:"json",
		        async : false,
		        success : function(homeData) {
		             for (var j = 0; j < homeData.length; j++) {
		            	var yearAndMouth=homeData[j].yearMouthDay;
		            	yearAndMouthNum.push(yearAndMouth);
		            	updateHomeNum.push(homeData[j].updateNum);
					}
		        }
		       });
		    option = {
				    color:[
		               '#36cd4f'
		              ],
		    	    tooltip : {
		    	        trigger: 'axis',
		    	         borderColor: '#48b',
		    	         backgroundColor: 'rgba(0,0,0,0.5)',
		    	          axisPointer: {
				         color: 'rgba(150,150,150,0.3)',
				        width: 'auto',
				        type: 'default'
				      },
				       padding:15,
		              textStyle : {
				           fontSize: 12
				           },
		              formatter: function (params,ticket,callback) {
		  	            var res =params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		  	            for (var i = 0, l = params.length; i < l; i++) {
		  	                res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'条';
		  	            }
		  	            setTimeout(function (){
		  	                callback(ticket, res);
		  	            }, 0)
		  	        } 
		    	    },
		    	    dataZoom : {
		    	        show : true,
		    	        realtime : true,
		    	        height: 20,
		    	        start : 100,
		    	        end : 60
		    	    },
		    	    grid:{
		    	    	borderColor:'#fff'
                     }, 
		    	    xAxis : [
		    	        {
		    	            axisLine:false,
                            axisTick:false,
		    	        	splitLine:false,
		    	            type : 'category',
		    	            boundaryGap : false,
		    	            data : yearAndMouthNum
		    	        }
		    	    ],
		    	    yAxis : [
	                  {
	                      name:'每天更新数量\n',
	                      nameTextStyle:{color:'black'},
	                      axisLine:{
	                          lineStyle: {
	                              color: '#ffffff',
	                              width:1
	                          }
	                      },
	                      type : 'value'
	                  }
	                  ],
		    	    series : [
		    	        {
		    	            name:'更新数量',
		    	            type:'line',
		    	            symbol:'emptyCircle',
		    	            symbolSize:4,
		    	            itemStyle: {
		    	            backgroundColor:'rgba(0,0,0,0.5)',
                               normal: {
                                 areaStyle: {
                                   type: 'default',
                                   color:'#e9f9ed'
                                      }
                                }},
		    	            data: updateHomeNum
		    	        }
		    	    ],
		    	    calculable:false
		    	};
		    myChart_line.setOption(option,true);
}											
</script>


</body>
</html>