<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>栏目更新</title>
   <%@ include file="/common/meta.jsp"%>
   <%@ include file="/common/heade.jsp"%>
  </head>
<body>
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="16" scope="request"/>
		<c:set var="menu" value="#menuNrgx" scope="request"/>
		<%@ include file="/common/leftmenu_tb.jsp"%>
        <div class="page-content">
			<input id="typesId" type="hidden" value="${types}" />
            <c:if test="${sessionScope.shiroUser.iscost==0}">
                <div class="free-html">
                    <i></i><span class="font-bold">提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href="http://jg.kaipuyun.cn/ce/banben/version.shtml" target="_blank">点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href="mailto:jianguan@ucap.com.cn">jianguan@ucap.com.cn</a>
                </div>
            </c:if>
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
                    	<c:if test="${sessionScope.shiroUser.iscost==1}">
                    		<div id="update_channel_bar_id" style="height:257px; width:100%;" ></div>
                    	</c:if>
                    </div>
                </div>
            </div><!-- /row3 -->
            
            
            <div class="row bg-fff">
            	<div class="tab_header row">
                	<h3>栏目更新明细</h3>
                    <div class="tab-hfont1 hide" id="table_data_channelUpdate_hide"></div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input  class="prependedInput span2" id="keyInput" type="text" placeholder="输入标题...">
                    </div>
                    <c:if test="${sessionScope.shiroUser.iscost==1}">
                    	<div class="page-btn1 hide" onclick="updateChannelExcel()" id="table_data_channelUpdate_hide1"><i></i>导出列表</div>
                    </c:if>
                </div>
                <div class="tab_box1 row">
                	<c:if test="${sessionScope.shiroUser.iscost==1}">
	                    <table cellpadding="0" cellspacing="0" class="table t-tab1" id="table_up_channel_detail">
	                    </table>
	                    <div class="zanwsj" id="channel_table_id">栏目暂无更新情况</div>
                    </c:if>
                </div>
            </div><!-- /bg-fff -->
            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<!-- 栏目更新js引入 -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/updateChannel/update_channel_dt.js"></script> 
<script language="javascript" type="text/javascript">
var typesId = $("#typesId").val();
$(function(){ 
	
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
    $("input[type='checkbox'],input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	updateChannelBar();
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
			var params="5,6,7,8,9,10,11,12,13,14,15,16";
			if($(this).val()==1){
				params="5";
			}else if($(this).val()==2){
				params="6,7";
			}else if($(this).val()==3){
				params="8,9,10,11,12,13,14,15,16";
			}
			$("#types").val(params);
			
			//列表数据
			selectUpdateChannel();
			//柱状图加载
   			updateChannelBar();
			
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
	
	
	
   $("#table_up_channel_detail_filter").hide();
   $("#table_up_channel_detail_length").hide();
  	conditionalChoice();
	
	if(typesId == "tp"){
		$("#typeName").html("全部");
	}else{
		if(typesId == 1){
			$("#typeName").html('<span class="font-704fc1">动态、要闻类</span><span class="font-ff0000">（要求两周内更新）</span>');
		}else if(typesId == 2){
			$("#typeName").html('<span class="font-704fc1">通知公告、政策文件类</span><span class="font-ff0000">（要求半年内更新）</span>');
		}else if(typesId == 3){
			$("#typeName").html('<span class="font-704fc1">人事、规划计划类</span><span class="font-ff0000">（要求一年内更新）</span>');
		}
	}
  	
})

//类型选择下拉框处理
function selectUpdateChannel(params){
	table_up_channel_detail.fnReloadAjax(webPath+"/updateChannelDetail_updateChannelPage.action");
	
}

//栏目跟新页面excel导出
function  updateChannelExcel(){
	
	//jquery获取复选框值    
	 var types=$("#types").val();  
	 var days=""; 
	 $("input[name='days']:checked").each(function(index,element){  
	  	days=$(this).val();  
	  });
	  var key=$("#keyInput").val();

	window.location.href=webPath+"/updateChannelDetail_updateChannelExcel.action?type="+types+"&days="+days+"&key="+key;
}
//栏目更新分页查询
 function  conditionalChoice(){
	$("input[name='days']").on('ifChecked',function(event) {
		table_up_channel_detail.fnReloadAjax(webPath+ "/updateChannelDetail_updateChannelPage.action"); 
		//柱状图加载
   		updateChannelBar();
	});
 
	$("#keyInput").keyup(
			function() {
				table_up_channel_detail.fnReloadAjax(webPath
						+ "/updateChannelDetail_updateChannelPage.action");
			}); 
	}
	
function updateChannelBar(){
			var updateNumDate=[];
			var channelNameDate=[];
			var types = "";
			if(typesId == "tp"){
				types = $("#types").val();
			}else{
				if(typesId == 1){
					types="5";
				}else if(typesId == 2){
					types="6,7";
				}else if(typesId == 3){
					types="8,9,10,11,12,13,14,15,16";
				}
			}
			var days=$("input[name='days']:checked").val();
			var myBarChart = echarts.init(document.getElementById("update_channel_bar_id"));
		    $.ajax( {  
			        type : "POST",  
			        url : webPath+"/updateChannelDetail_updateChannelBar.action?type="+types+"&days="+days,
			        dataType:"json",
			        async : false,
			        success : function(homeData) {
			             for (var j = 0; j < homeData.length; j++) {
			            	 var updateNum=homeData[j].updateNum;
			            	 var channelName=homeData[j].channelName;
			            	 updateNumDate.push(updateNum);
			            	 channelNameDate.push(channelName);
						}
			        }
		       });
			option = {
			
				tooltip : {
			    	 trigger: 'axis',
		             borderColor: '#48b',
		             axisPointer: {
			             color: 'rgba(150,150,150,0.3)',
			             width: 'auto',
			             type: 'default'
	              	},
	             	formatter:"{b}<br/>{a}：{c}个",
                 		padding:15,
                 		textStyle : {
		           			fontSize: 12,
		          		 }
		   			 },
				    xAxis : [
				        {
				            axisLine:false,
                            axisTick:false,
				            type : 'category',
				            splitLine:false,
				            data : channelNameDate,
				            axisLabel: {
				            rotate: 30
				            }
				        },
				      ],
		    	    yAxis : [
	                  {
	                      name:'更新栏目数\n',
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
		    	    grid:{
		    	    	borderColor:'#fff',
         /*                  x:35,
                          y:5,*/
                          y2:65,
                     }, 
				    series : [
				        {
				            name:'更新栏目数',
				            type:'bar',
				            barWidth: 40, 
				            data:updateNumDate,
				            stack: 'a',
				            itemStyle: {
                                  normal: {
                                   color: function(params) {
                                var colorList = [
			                          '#F75352','#46CDE1','#62D975','#F6D64F','#3BA6DC',
			                           '#6FCA63','#D30D13','#BE32D9','#46E0DE','#B9ACD8',
			                           '#62D975','#F7D550','#C8A5DB'
			                        ];
			                        return colorList[params.dataIndex]
                                },
		                           label: {
				                        show: true,
				                        position: 'insideTop',
				                        formatter: '{c}',
				                        textStyle:{
				                        fontSize:18
				                        }
		                            } 
                                },
                                  emphasis : {
                                   color: function(params) {
                                var colorList = [
			                          '#F75352','#46CDE1','#62D975','#F6D64F','#3BA6DC',
			                           '#6FCA63','#D30D13','#BE32D9','#46E0DE','#B9ACD8',
			                           '#62D975','#F7D550','#C8A5DB'
			                        ];
			                        return colorList[params.dataIndex]
                                },
		                           label: {
				                        show: true,
				                        position: 'insideTop',
				                        formatter: '{c}',
				                        textStyle:{
				                        fontSize:18
				                        }
		                            } 
                                },
                               }
				        }
				    ]
				};
		 myBarChart.setOption(option,true);
 	}                   
</script>
</body>
</html>
