<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>内容正确性</title>
	<%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/heade.jsp"%>
  </head>
<body>
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	<c:set var="tb_index" value="12" scope="request"/>
	<%@ include file="/common/leftmenu_tb.jsp"%>
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">内容正确性</a></li>
                </ul>
            </div>
        	<div class="row">
            	<div class="data_box1 span4">
                	<h3>监测时间</h3>
                	 <div id="flexsliderMore" class="flexslider-more carousel">
                      <ul class="slides" id="time_tool_id">
                      </ul>
                    </div>
                    <div class="data_box1_footer" id="data_box1_footer_nodata">可看上期之前的数据</div>
                </div>
                <div class="t_box2 span8">
                	<h3>监测说明</h3>

                    <div class="t_box2_con">
                    	<p>考察网站是否出现了严重字词问题和信息内容不准确问题。</p>
						<p>1.严重字词包括：严重错别字（例如，将党和国家领导人姓名写错）、虚假或伪造内容（例如，严重不符合实际情况的文字、图片、视频）以及反动、暴力、色情等内容；严重字词问题一经发现即触发预警。</p>
						<p>2.信息内容不准确包括：动态、要闻、通知公告、政策文件、机构设置及职能、规划计划、人事等信息不准确。</p>
                    </div>
                </div>
            </div><!-- /row1 -->
             <div class="row t_box5">
                <div class="pie-chart span6">
                    <h3>昨天新增网页内容正确性</h3>
                    <div class="pie-chart-con">
                        <div id="correct_content_bar" style="height: 220px;width: 100%"></div>
                       <!--  <img style="margin:25px 0px 0px 30px;" src="../../images/zanyong/t-2.png"/> -->
                    </div>
                </div>

                <div class="t_box6 span6">
                    <h3>内容正确性趋势</h3>
                    <div class="pie-chart-con">
                    <div id="correct_content_line" style="height: 220px;width: 100%"></div>
                        <!-- <img style="margin:5px 0px 0px 30px; height:210px;" src="../../images/zanyong/t_5.jpg"/> -->
                    </div>
                </div>

            </div><!-- /row2 -->
            
            <div class="row">
            	<div class="tab_header row">
                	<h3>内容正确性监测结果</h3>
                    <div class="input-prepend">
                     <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="span2 prependedInput" id="keyInput" type="text" placeholder="输入关键字...">
                    </div>
                    <div class="page-btn1 hide" onclick="correctContentExcel()" id="table_accuracy_content_hide"><i></i>导出列表</div>
                </div>
                <div class="tab_box1 row">
                <input type="hidden" id="periodNum"/>
                    <table cellpadding="0" cellspacing="0" class="table t-tab1 hide" id="table_accuracy_content">
                    </table>
                     <div class="zanwsj" id="table_accuracy_content_hide1">暂无数据</div>
                </div>
            </div>
           <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/accuracyOfContent/accuracy_content_page.js"></script> 
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />
<script type="text/javascript">
$(function(){
 //关键字查询
 $("#keyInput").keyup(
		function() {
					table_accuracy_content.fnReloadAjax(webPath
							+ "/accuracyOfContent_accuracyContentPage.action");
			});
  //周期控件数据初始化
  timeTool();
  //初始化柱状图
  correctContentBar();
  //初始化折线图
  correctContentLine();			
})
</script>
<script type="text/javascript">
//导出Excel
function correctContentExcel(){
window.location.href=webPath+"/accuracyOfContent_correctContentExcel.action?periodNum="+$("#periodNum").val();
}
//周期控件数据初始化
function timeTool(){
var currentPeriodNum=0;
		$.ajax( {  
		   type : "POST",  
		   url : "<c:url value="/accuracyOfContent_getTimeTool.action"/>",  
		   dataType:"json",
		   async : false,
		   success : function(data) {
		       		currentPeriodNum=data.currentPeriodNum;
		   			var timeToolList=data.timeToolList;
		   			var liStr="";
		   			if(timeToolList.length >0){
			       		for(var i=0;i<timeToolList.length;i++){
			       			liStr +="<li><div class='font-size1'>第"+timeToolList[i].periodNum+"期</div>"+timeToolList[i].startTime+"</li>";
			       		}
		       		}
		       		$("#time_tool_id").append(liStr);
		       		if(timeToolList[0].info!="" && timeToolList[0]!=null){
		       			$("#data_box1_footer_nodata").html(timeToolList[0].info);
		       		}
		       }
			});
		
			/*============================
			@author:轮播
			@time:2015-10-09
			
			============================*/
		
			$("#flexsliderMore").flexslider({
				startAt: currentPeriodNum,//定位当前周期，该值需要后台获取
				slideshowSpeed: 50000, // 自动播放速度毫秒
				directionNav: true, //Boolean:  (true/false)是否显示左右控制按钮
				controlNav: false, //Boolean:  usage是否显示控制菜单//什么是控制菜单？
				slideshow: false,//载入页面时，是否自动播放
				animationLoop: false,//  "disable" classes at either end 是否循环滚动 循环播放
				before: function(i){
				//获取选中期数
				periodNum=i.animatingTo+1;
				//点击时间按钮，联动列表查询
				correctContentPage(periodNum);
				}
			});
}
//联动分页查询
function correctContentPage(periodNum){
$("#periodNum").val(periodNum);
table_accuracy_content.fnReloadAjax(webPath+ "/accuracyOfContent_accuracyContentPage.action");
}
</script>
<script type="text/javascript">
//初始化柱状图
function correctContentBar(){
setTimeout(function(){
 var unAccuracyNum=[];
 var correctType=[];
var myBarChart = echarts.init(document.getElementById("correct_content_bar"));
 $.ajax( {  
	        type : "POST",  
	        url : "<c:url value="/accuracyOfContent_correctContentBar.action"/>",
	        dataType:"json",
	        async : false,
	        success : function(data) {
	             for (var j = 0; j < data.length; j++) {
	            	 var num=data[j].unAccuracyNum;
	            	 var type=data[j].correctType;
	            	 unAccuracyNum.push(num);
	            	 correctType.push(type);
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
				    xAxis : [
				        {
				            axisLine:false,
                            axisTick:false,
				            type : 'category',
				            splitLine:false,
				            data : correctType
				        },
				      ],
		    	    yAxis : [
		    	        { 
		    	            name:'不准确个数\n',
		    	            nameTextStyle:{color:'black'},
		    	            type : 'value',
		    	            axisLine:{
          			          lineStyle: {
          			            color: '#ffffff',
          			               width:1
          			                }
                                }
		    	            
		    	        },
		    	    ],
		    	    grid:{
		    	     borderColor:'#fff',
                          x:45,
                          y:40,
                          x2:20,
                          y2:20
                     }, 
				    series : [
				        {
				           name:'不准确个数',
				            type:'bar',
				            barWidth: 30, 
				            data:unAccuracyNum,
				            stack: 'a',
				            itemStyle: {
                                  normal: {
                                   color: function(params) {
                                var colorList = [
			                          '#F75352','#46CDE1','#62D975','#F6D64F'
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
			                          '#F75352','#46CDE1','#62D975','#F6D64F'
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
                                }
                               }
				        }
				    ]
				};
		 myBarChart.setOption(option,true);
},100);
}
//初始化折线图
function  correctContentLine(){
setTimeout(function(){
  var problemNum=[];
  var periodNum=[];
  var year="";
  var myCharts_line=echarts.init(document.getElementById("correct_content_line"));
   $.ajax( {  
		        type : "POST",  
		        url : webPath+"/accuracyOfContent_correctContentLine.action",  
		        dataType:"json",
		        async : false,
		        success : function(data) {
		             for (var j = 0; j < data.length; j++) {
		            	 periodNum.push("第"+data[j].periodNum+"期");
		            	 problemNum.push(data[j].problemNum);
		            	 year=data[j].year+"年";
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
                 formatter:year+"{b}<br/>{a}：{c}个",
                 padding:15,
                 textStyle : {
		           fontSize: 12
		           }
             },
    	    dataZoom : {
    	        show : true,
    	        realtime : true,
    	        height: 20,
    	        start : 20,
    	        y:200,
    	        end : 80
    	    },
    	    grid:{
    	      borderColor:'#fff',
                 x:50,
                 y:40,
                 x2:20
            }, 
    	    xAxis : [
    	        {
	        	 type : 'category',
	        	 axisLine:false,
	        	 splitLine:false,
	        	 axisTick:false,
    	         data : periodNum
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
