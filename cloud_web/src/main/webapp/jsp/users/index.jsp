<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>业务系统连通性</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<script type="text/javascript" src="<%=path%>/js/connection/business_table_fenye.js"></script>
  </head>
  <body>
  <!--头部       satrt  -->
  <%@ include file="/common/top.jsp"%>
  <!--头部       end  -->
    
<div class="main-container container">
	<div class="row-fluid">
		<!--左侧导航       satrt  -->
		<%@ include file="/common/leftmenu.jsp"%>
		<!--左侧导航       end  -->
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">网站连通性</a> <span class="divider">></span></li>
                  <li class="active">业务系统连通性</li>
                </ul>
            </div>
        	<div class="row">
            	<div class="t_box1 span4 datepicker-box">
                	<div id="datepicker"></div>
                    <div class="datepicker-bottom">仅可查看昨天之前的数据</div>
                </div>
                <div class="t_box2 span8">
                	<h3>监测说明</h3>

                    <div class="t_box2_con">
                    	<p>目前监控已经拥有将近14个省的20多个监测点，规划中有20余个监测点，拥有电信、联通、移动、铁通、鹏博士等多条线路，同时深度的结合了我们的网站测速项目遍布全国的监测点，确保了云监控的检测效果</p>
                    </div>
                </div>
            </div><!-- /row1 -->
            <div class="row t_box5">
                <div class="span4 pie-chart">
                    <h3>业务系统连通状况</h3>
                    <div class="time-font">
						<fmt:parseDate value="${yesterdayStr}" pattern="yyyy-MM-dd" var="receiveDate"></fmt:parseDate>
                	 	<fmt:formatDate value="${receiveDate}" pattern="yyyy年MM月dd日" ></fmt:formatDate>
					</div>
                    <div class="pie-chart-con" id="business_bar" style="height: 240px;width: 330px">
                    </div>
                </div>

                <div class="z_box2 span8">
                    <h3 class="qu_s">业务系统连通趋势</h3>
                    <div id="business_line" class="line_chart" style="height: 300px;width: 660px;margin-top: -44px;">
                    </div>
                </div>

            </div><!-- /row2 -->
            <div class="row">
            	<h3 class="info_fx_h3 bg-13ba59">业务系统连通统计分析 <span class="time-font">
					<fmt:parseDate value="${yesterdayStr}" pattern="yyyy-MM-dd" var="receiveDate"></fmt:parseDate>
                	 <fmt:formatDate value="${receiveDate}" pattern="yyyy年MM月dd日" ></fmt:formatDate>
				</span></h3>

                <div class="t-tab2">
                    <table cellspacing="0" cellpadding="0" class="table">
                        <tbody>
                            <tr>
                                <th rowspan="2" class="th_left">业务系统名称</th>
                                <th rowspan="2" style="width:200px;">URL</th>
                                <th colspan="2">成功（＜15秒）</th>
                                <th colspan="2">超时（≥15秒）</th>
                                <th rowspan="2">总次数</th>
                            </tr>
                            <tr class="th-sub">
                                <th>次数</th>
                                <th>占比</th>
                                <th class="bg-e65e5e">次数</th>
                                <th class="bg-e65e5e">占比</th>
                            </tr>
                            <c:forEach items="${connBusiList}" var="list">
	                            <tr>
	                                 <td class="td_left">${list.name}</td>
	                                 <td><div class="url-ellipsis">${list.url}</div></td>
	                                 <td>${list.successNum}</td>
	                                 <td>${list.successProportion}%</td>
	                                 <td>${list.errorNum}</td>
	                                 <td>${list.errorProportion}%</td>
	                                 <td>${list.successNum+list.errorNum}</td>
	
	                            </tr>
                            </c:forEach>
                            <tr>
                                 <td colspan="2" class="t-td-font1">汇&nbsp;&nbsp;&nbsp;&nbsp;总</td>
                                 <td>${successNum}</td>
                                 <td>
	                                 <c:if test="${successProportion!=null}">
	                                     ${successProportion}%
	                                  </c:if>
                                  </td>
                                 <td class="bg-e65e5e">${errorNum}</td>
                                 <td class="bg-e65e5e">
                                 	<c:if test="${errorProportion!=null}">
	                                     ${errorProportion}%
	                                  </c:if>
                                 </td>
                                 <td>${successNum+errorNum}</td>

                            </tr>
                        </tbody>
                    </table>
                </div>
            </div><!-- /row3 -->
            
            <div class="row mar1">
            	<div class="tab_header row">
                	<h3>业务系统连通性监测结果</h3>
                    <div class="tab-hfont3">
						<fmt:parseDate value="${yesterdayStr}" pattern="yyyy-MM-dd" var="receiveDate"></fmt:parseDate>
                	 	<fmt:formatDate value="${receiveDate}" pattern="yyyy年MM月dd日" ></fmt:formatDate>
					</div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span>
                      <input class="span2" id="prependedInput" type="text" placeholder="输入关键字...">
                    </div>
                    <div class="page-btn1"><i></i>导出列表</div>
                </div>
                <div class="tab_box1 row">
                    <table id="business_table" cellpadding="0" cellspacing="0" class="table t-tab1">
                    </table>
                </div>
            </div>
             <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script type="text/javascript" src="<%=path%>/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/map/echarts.js"></script>
<script language="javascript" type="text/javascript">
/*//成功占比
var successProportion = ${successProportion};
//不连通占比
var errorProportion = ${errorProportion};

require.config({
    paths:{
        echarts:'<%=path%>/js/map'
    }
});
require(
        [
            'echarts',
            'echarts/chart/pie'
        ],
        function(ec) {
            var myChart_bar = ec.init(document.getElementById('business_bar'));
            option = {
            	    tooltip : {
            	        trigger: 'item',
            	        formatter: "{b}"
            	    },
            	    calculable : true,
            	    series : [
            	        {
            	        	itemStyle: {
            	                normal: {
            	                 labelLine:{
            	                      	length:0
            	                      }
            	                }
            	            },
            	        	calculable : false,//取消虚线
            	            name:'访问来源',
            	            type:'pie',
            	            radius : ['45%', '70%'],
            	            center: ['48%', '60%'],
            	            data:[
            	                {value:successProportion, name:'成功连通'+successProportion+'%'},
            	                {value:errorProportion, name:'连通超时'+errorProportion+'%'}
            	            ]
            	        }
            	    ]
            	};
         myChart_bar.setOption(option,true);
        }
);*/
</script>

<script language="javascript" type="text/javascript">
require.config({
    paths:{
        echarts:'<%=path%>/js/map'
    }
});
require(
        [
            'echarts',
            'echarts/chart/line'
        ],
        function(ec) {
            var myChart_line = ec.init(document.getElementById('business_line'));
            var errorNum = new Array();
            var errorDate = new Array();
            $.ajax( {  
                type : "POST",  
                url : "<c:url value="/connectionBusinessDetail_getBusinessTrendLine.action"/>",  
                // data : "content=" + content,  
                dataType:"json",
                async : false,  
                success : function(data) {
	                 for (var int = 0; int < data.length; int++) {
	                	 errorNum.push(data[int].errorNum);
	                	 errorDate.push(data[int].scanDate);
					}
                }
               });
            option = {
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    dataZoom : {
            	        show : true,
            	        realtime : true,
            	        height: 20,
            	        start : 70,
            	        end : 100
            	    },
            	    xAxis : [
            	        {
            	        	splitLine:false,
            	            type : 'category',
            	            boundaryGap : false,
            	            data : errorDate
            	        }
            	    ],
            	    yAxis : [
            	        {
            	        	name:'超时更新次数',
            	            type : 'value'
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'超时次数',
            	            type:'line',
            	            data:errorNum
            	        }
            	    ],
            	    calculable:false
            	};
          myChart_line.setOption(option,true);
        }
);

</script>

  </body>
</html>
