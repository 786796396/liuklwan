<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>监测结果</title>
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
	<c:set var="tb_index" value="2" scope="request"/>
	<c:set var="menu" value="#menuWzltx" scope="request"/>
	<%@ include file="/common/leftmenu_tb.jsp"%>
	<!--左侧导航       end  -->
         <div class="page-content">
    		<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">网站连通性</a></li>
                </ul>
            </div>
            <div class="row">
                <h3 class="info_fx_h3 bg-13ba59">网站连通性</h3>
                <div class="t_box4 info_fx_con">
                    <h4 class="font2"></h4>
                    <div  class="line_chart" >
                        <div class="pie-chart-tab">
                            <table cellpadding="0" cellspacing="0" class="table">
                                <tbody>
                                    <tr>
                                        <th class="text-left">名称</th>
                                        <th>次数</th>
                                    </tr>
                                    <tr>
                                        <td class="text-left">首页连通性</td>
                                        <td>10</td>
                                    </tr>
                                    <tr>
                                        <td class="text-left">业务系统连通性</td>
                                        <td>20</td>
                                    </tr>
                                  <tr>
                                    <td class="text-left">关键栏目连通性</td>
                                    <td>30</td>
                                  </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div><!-- /row3 -->
          
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</body>
</html>
