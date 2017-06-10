<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>内容正确性</title>
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
	<c:set var="tb_index" value="12" scope="request"/>
	<%@ include file="/common/leftmenu_tb.jsp"%>
	<!--左侧导航       end  -->
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">内容正确性</a></li>
                </ul>
            </div>
        	<div class="row">
            	<div class="data_box1 span4">
                	<h3>监测周期</h3>
                	 <div id="flexsliderMore" class="flexslider-more carousel">
                      <ul class="slides">
                        <li><div class="font-size1">第1期</div>2014年5月1日</li>
                        <li><div class="font-size1">第2期</div>2014年5月1日</li>
                        <li><div class="font-size1">第3期</div>2014年5月1日</li>
                        <li><div class="font-size1">第4期</div>2014年5月1日</li>
                        <li><div class="font-size1">第5期</div>2014年5月1日</li>
                        <li><div class="font-size1">第6期</div>2014年5月1日</li>
                        <li><div class="font-size1">第7期</div>2014年5月1日</li>
                        <li><div class="font-size1">第8期</div>2014年5月1日</li>
                        <li><div class="font-size1">第9期</div>2014年5月1日</li>
                        <li><div class="font-size1">第10期</div>2014年5月1日</li>
                        <li><div class="font-size1">第11期</div>2014年5月1日</li>
                      </ul>
                    </div>
                    <div class="data_box1_footer">可看上期之前的数据</div>
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
                    <h4 class="font3">不准确个数</h4>
                    <div class="pie-chart-con">
                        <img style="margin:25px 0px 0px 30px;" src="<%= path %>/images/zanyong/t-2.png"/>
                    </div>
                </div>

                <div class="t_box6 span6">
                    <h3>内容正确性趋势</h3>
                    <h4 class="font3">问题总数</h4>
                    <div class="pie-chart-con">
                        <img style="margin:5px 0px 0px 30px; height:210px;" src="<%= path %>/images/zanyong/t_5.jpg"/>
                    </div>
                </div>

            </div><!-- /row2 -->
            
            <div class="row">
            	<div class="tab_header row">
                	<h3>内容正确性监测结果</h3>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%= path %>/images/common/search.png"/></span>
                      <input class="span2 prependedInput" type="text" placeholder="输入关键字...">
                    </div>
                    <div class="page-btn1"><i></i>导出列表</div>
                </div>
                <div class="tab_box1 row">
                    <table cellpadding="0" cellspacing="0" class="table t-tab1">
                        <tbody>
                            <tr>
                            	<th class="th_left" style="width:40px;">序号</th>
                                <th class="th_left" style="width:200px;">错误类型</th>
                                <th class="th_left">问题描述</th>
                                <th class="th_left" style="width:280px;">网页URL</th>
                                <th style="width:55px;">截图</th>
                            </tr>
                            <tr>
                                 <td class="td_left font_701999">1</td>
                                 <td class="td_left">领导姓名</td>
                                 <td class="td_left"><div class="wz-name">首页/新闻动态</div></td>
                                 <td class="td_left"><div class="url-ellipsis">www.ucap.com</div></td>
                                 <td><img alt="截图" src="<%= path %>/images/jiet.png"/></td>
                            </tr>
                            <tr>
                                 <td class="td_left font_701999">1</td>
                                 <td class="td_left">领导姓名</td>
                                 <td class="td_left"><div class="wz-name">首页/新闻动态</div></td>
                                 <td class="td_left"><div class="url-ellipsis">www.ucap.com</div></td>
                                 <td><img alt="快照" src="<%= path %>/images/kuaiz.png" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="pagination">分页</div>
                </div>
            </div>
             <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />
<script language="javascript" type="text/javascript">
$(function(){ 
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
	/*============================
	@author:轮播
	@time:2015-10-09
	============================*/
	$("#flexsliderMore").flexslider({
		slideshowSpeed: 50000, // 自动播放速度毫秒
		directionNav: true, //Boolean:  (true/false)是否显示左右控制按钮
		controlNav: false, //Boolean:  usage是否显示控制菜单//什么是控制菜单？
		slideshow: false,//载入页面时，是否自动播放
		animationLoop: false //  "disable" classes at either end 是否循环滚动 循环播放
	});
}) 
</script>
</body>
</html>
