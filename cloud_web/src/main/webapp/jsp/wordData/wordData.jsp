<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>关键栏目连通性</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
  </head>
  <body>
  <!--头部       satrt  -->
  <%@ include file="/common/top_tb.jsp"%>
  <!--头部       end  -->
<div class="main-container container">
	<div class="row-fluid">
		<!--左侧导航       satrt  -->
		<c:set var="tb_index" value="4" scope="request"/>
		<c:set var="menu" value="#menuWzltx" scope="request"/>
		<%@ include file="/common/leftmenu_tb.jsp"%>
		<!--左侧导航       end  -->
        <div class="page-content">
            <table>
            	<thead>
            		<tr>
            			<th></th>
            			<th></th>
            			<th></th>
            		</tr>
            	</thead>
            	<tbody>
            		<tr>
            			<td></td>
            			<td></td>
            			<td></td>
            		</tr>
            		<tr>
            			<td></td>
            			<td></td>
            			<td></td>
            		</tr>
            		<tr>
            			<td></td>
            			<td></td>
            			<td></td>
            		</tr>
            	</tbody>
            </table>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<%@ include file="/common/http.jsp"%>
<script type="text/javascript" src="<%=path%>/js/connection/channel_table_fenye.js"></script>
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript">
$(function(){
    $(".ms-icon-wen").hover(function(){
        $(this).siblings(".ms-wen-con").fadeIn();
    },function(){
        $(this).siblings(".ms-wen-con").fadeOut();
    });
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
	//统计分析
	channelStatistics();
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
		minDate : $("#channel_time_tool_mindate").attr("value"),//最小日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
		//统计分析
		channelStatistics(dateText);
		//列表
		channelList();
		//饼图
/* 		if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="false"){//没有统计数据
			//显示暂无数据
			$("#business_table_pie").attr("style","display:block");
			//隐藏环饼状图
			$("#channel_bar").attr("style","height: 200px;width: 100%;display:none");
		}else{//存在统计数局
			//隐藏暂无数据
			$("#business_table_pie").attr("style","display:none");
			//显示环饼状图
			$("#channel_bar").attr("style","height: 200px;width: 100%;display:block");
			setTimeout(function(){
				channelbar();
			},100);
		} */
		
		}
	}); 
	//饼图
/* 	if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="false"){
		//显示暂无数据
		$("#business_table_pie").attr("style","display:block");
		//隐藏环饼状图
		$("#channel_bar").attr("style","height: 200px;width: 100%;display:none");
	}else{
		//隐藏暂无数据
		$("#business_table_pie").attr("style","display:none");
		//显示环饼状图
		$("#channel_bar").attr("style","height: 200px;width: 100%;display:block");
		setTimeout(function(){
			channelbar();
		},100);
	} */
	
	//折线图
	setTimeout(function(){
			channelLine();
	},100);
   /*
	关键字查询
	*/
	$("#keyInput").keyup(
		function() {
			table_data_channelConnectivity.fnReloadAjax(webPath+"connectionChannelDetail_queryList.action");
		});

});

//excel导出
function channelExcel(){
	var scanDate=$("#dateText").val();
	var key=$("#keyInput").val();
	window.location.href=webPath+"/connectionChannelDetail_channelExcel.action?scanDate="+scanDate+"&key="+key;
}
//连动分页查询
function channelList(){
	table_data_channelConnectivity.fnClearTable();
	table_data_channelConnectivity.fnReloadAjax(webPath+"connectionChannelDetail_queryList.action");
}
//连动统计分析
function channelStatistics(time){
	if(time==null){
			time = "";
		}
	 $.ajax( {  
         type : "POST",  
         url : "<c:url value="/connectionChannelDetail_getChannelStatistics.action"/>",   
         data : "date=" + time,  
         dataType:"json",
         async : false,  
         success : function(data) {
         
        	 var conlist = data.connChannelList;
        	 var list = "";
        	 var consum="";
        	 var listExecl="";
        	 $("#conlist").html("");
        	 $("#conSum").html("");
        	 
        	 if(data.siteBeginServiceDate!=""){
        		 $("#channel_time_tool_mindate").attr("value",data.siteBeginServiceDate);
        	 }
        	 
             for (var i = 0; i < conlist.length; i++) {
            	list +="<tr><td class='td_left'>"+conlist[i].name+"</td>"; 
           	  	if(conlist[i].url.match("http")){
           	  		list +="<td class='td_left'><div class='url-ellipsis' title='"+conlist[i].url+"'><a target='_blank' href='"+conlist[i].url+"'>"+conlist[i].url+"</div></td>";
           	  	}else{
           	  		list +="<td class='td_left'><div class='url-ellipsis' title='http://"+conlist[i].url+"'><a target='_blank' href='http://"+conlist[i].url+"'>http://"+conlist[i].url+"</div></td>";
           	  	
           	  	}	
            	list +=	"<td>"+conlist[i].successNum+"</td>" +"<td>"+(conlist[i].successProportion)+"%</td>"+
            	  		"<td>"+conlist[i].errorNum+"</td>"+"<td>"+(conlist[i].errorProportion)+"%</td>"+
            	  		"<td>"+(conlist[i].successNum+conlist[i].errorNum)+"</td></tr>";
             }
              list+="<input type='hidden' id='successProportion_bar' value='"+(data.successProportion)+"' />" +
              		"<input type='hidden' id='errorProportion_bar' value='"+(data.errorProportion)+"' />";
              if(conlist.length>0){
              		$("#channel_table_hide").removeClass("hide");
              		$("#channel_table_hide1").addClass("hide");
             		consum+="<td colspan='2' class='t-td-font1'>汇&nbsp;&nbsp;&nbsp;&nbsp;总</td>"+"<td>"+data.successNum+"</td>";
              		if(data.successProportion!=null){
              			consum+="<td>"+(data.successProportion)+"%</td>";
              		}else{
              	 		consum+="<td></td>";
              		}
              		consum+="<td class='bg-e65e5e'>"+(parseInt(data.errorNum))+"</td>";
              		if(data.errorProportion!=null){
              			consum+="<td class='bg-e65e5e'>"+(data.errorProportion)+"%</td>";
              		}else{
              			consum+="<td ></td>";
              		}
             		 consum+="<td>"+(parseInt(data.successNum)+parseInt(data.errorNum))+"</td>";
              }else{
              		$("#channel_table_hide").addClass("hide");
              		$("#channel_table_hide1").removeClass("hide");
              }
              
              $("#conn_table").siblings(".dropload-load").hide();
              $("#conlist").append(list);
              $("#conSum").append(consum);
              $("#datepicker").val(data.date);
              //$("#yesterdayStr_2").text(data.yesterdayStr);
              $("#yesterdayStr_1").text(data.date);
              $("#yesterdayStr_3").text(data.date);
              $("#dateText").val(data.date);
         }
        });
}
//连动饼图
/* function channelbar(){
	//成功占比
	var successProportion = $("#successProportion_bar").val();
	//不连通占比
	var errorProportion = $("#errorProportion_bar").val();
    var myChart_bar = echarts.init(document.getElementById('channel_bar'));
    option = {
       color:['#d5d9e2','#2dcc70'],
       tooltip : {
           trigger: 'item',
           formatter: "{b}"
       },
       calculable : true,
       series : [
          {
            itemStyle: {
            	emphasis: {
            	      labelLine:{
            	          length:0
            	      }
            	 }
            },
            calculable : false,//取消虚线
            name:'访问来源',
            type:'pie',
            radius : ['40%','60%'],
            center: ['50%', '60%'],
            data:[
            	{value:errorProportion, name:'连通超时\n'+(errorProportion)+'%'},
            	{value:successProportion, name:'成功连通\n'+(successProportion)+'%'}
            ]
            }
            ]
        };
     myChart_bar.setOption(option,true);
} */
function channelLine(){ 
			var myChart_line = echarts.init(document.getElementById('channel_line'));
            var errorNum = new Array();
            var errorDate = new Array();
            $.ajax( {  
                type : "POST",  
                url : "<c:url value="/connectionChannelDetail_getChannelLine.action"/>",  
                // data : "content=" + content,  
                dataType:"json",
                async : false,  
                success : function(data) {
	                 for (var i = 0; i < data.length; i++) {
	                	 errorNum.push(data[i].errorNum);
	                	 errorDate.push(data[i].scanDate);
					}
                }
               });
            option = {
            	    tooltip : {
            	        trigger: 'axis',
            	         axisPointer: {
				         color: 'rgba(150,150,150,0.3)',
				        width: 'auto',
				        type: 'default'
				      },
				       formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'个';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              }
            	    },
            	    dataZoom : {
            	        show : true,
            	        realtime : true,
            	        height: 20,
            	        start : 100,
            	        end : 50
            	    },
            	    xAxis : [
            	        {
            	        	 axisLine:false,
                            axisTick:false,
		    	        	splitLine:false,
            	            type : 'category',
            	            boundaryGap : false,
            	            data : errorDate
            	        }
            	    ],
            	   grid:{
            	   		x:40,
	  	   				borderColor:'#fff'
	  	    		}, 
            	    yAxis : [
	                  {
	                      name:'连通超时次数\n',
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
            	            name:'超时次数',
            	            type:'line',
            	            symbol:'rectangle',
		    	            symbolSize:4,
            	            data:errorNum
            	        }
            	    ],
            	    calculable:false
            	};
          myChart_line.setOption(option,true);
}
</script>

  </body>
</html>
