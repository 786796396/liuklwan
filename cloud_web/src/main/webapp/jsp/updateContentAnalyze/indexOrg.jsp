<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>内容更新与分析-内容分析</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<script type="text/javascript" src="<%= path %>/js/echarts-all.js"></script>

<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />

</head>
<body>
<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
        <c:set var="ba_index" value="12" scope="request"/>
		<c:set var="menu" value="#menuFx" scope="request"/>
		<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">内容分析</a> <span class="divider">></span></li>
                  <li class="active">分类分析</li>
                </ul>
            </div>
        	<div class="row">
            	<h3 class="info_fx_h3">信息日更新趋势分析</h3>
                <div class="z_box2 info_fx_con">
                	<div id="contentOrgLine" style="width: 100%;height: 350px">
                    </div>
                </div>
            </div><!-- /row1 -->
            
            <div class="row">
            	<div class="line_chart_box lunb_chart_box">
                  <div class="chart-header row">
                    <h3 class="h3-brand">信息分类统计</h3>
                  </div><!-- /chart-header -->
                  <div class="tab-content">
                  <!--  <div class="chart-h1">共更新7类信息，覆盖分类超过<span style="display:none;">不足</span>50%</div> -->
                          <div class="chart-h1" id="sumDisplay"></div>
                      <div id="ltxContent" class="chart-content chart-content-ltx active">
                          <div class="chart_select_box row">
                          	  <label>选择显示：</label>
                          	  <select class="selectpicker" id="selectOrg">
                          	  <c:if test="${resultMap.level==1}">
                              	<option selected="selected" value="0">全部</option>
                                <option value="1">省门户</option>
                                <option value="2">省部门网站</option>
                                <option value="3">市政府网站</option>
                                <option value="4">区县政府</option>
                          	  </c:if>
                          	  <c:if test="${resultMap.level==2}">
	                              	<option  selected="selected" value="0">全部</option>
	                                <option value="1">市门户</option>
	                                <option value="2">市部门网站</option>
	                                <option value="3">区县政府</option>
                          	  </c:if>
                          	  <c:if test="${resultMap.level==3}">
	                              	<option  selected="selected" value="0">全部</option>
	                                <option value="1">县门户</option>
	                                <option value="2">县级网站</option>
                          	  </c:if>
                              </select>
                          	  <input id="level" type="text" style="display: none">
                          </div>
                          <div class="lunb_chart">
                          
                            <div class="flexslider">
                                <ul class="slides">
									<li style="display:block">
										<!-- -->
										<div id="content_pie" style="width:600px;height:398px">
										</div>
										<p class="flex-caption" id="scan_date"></p>
									</li>
								</ul>
								<ul class="flex-direction-nav">
                                   		 <li><a onclick="contentPiePrevious()" class="flex-prev" tabindex="-1" id="previousHref">Previous</a></li>
                                    	<li><a onclick="contentPieNext()" class="flex-next" id="nextHref">Next</a></li>
                                </ul>
                              </div>
                            
                            
                          </div>
                          <div class="chart-tab-box">
                            <div class="tab_box1 row">
                                <table cellpadding="0" cellspacing="0" class="table">
                                    <tbody id="tbodyChannelOrg">
                                    </tbody>
                                </table>
                            </div>
                            </div>
                      </div><!-- /chart-content-ltx -->
                      
                  </div><!-- /tab-content -->
                </div>
                
            </div><!-- /row3 -->

            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script type="text/javascript" src="<%=path %>/js/flexslider/jquery.flexslider-min.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
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
		dd.find("li").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
}) 
</script>
<script type="text/javascript">
var type = ${resultMap.type};
var userName ='${sessionScope.shiroUser.userName}';
</script>
<script type="text/javascript" src="<%= path %>/js/updateContentAnalyze/update_content_indexOrg.js"></script>
</body>
</html>