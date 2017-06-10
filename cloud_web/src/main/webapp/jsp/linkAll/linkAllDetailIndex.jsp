<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>全站链接可用性</title>
 	<%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
</head>
<body class="bg_f5f5f5">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-detail">
	<div class="main-detail-content">
		<%@ include file="/common/leftmenu.jsp"%>
	<div class="row-fluid">
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                  <li><a href="#">深度监测</a> <span class="divider">></span></li>
                  <li class="active">全站链接可用性</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con" style="width:400px;">
                                <div class="ztm-con">
                                        <p>1. 考察网站一个检测周期（如一个月或一个季度）内全站页面上的链接（包括图片、附件、外部链接等）能否正常访问（打不开或错误）；</p>
		                                <p>2. 统计不能正常访问的链接数量；</p>
		                                <p>3. 只有类型为 站内 确定不可用链接才会计入问题统计；</p>
		                                <p>4. 目前只将404,403记为确定不可用链接，其他类型记为疑似不可用链接。</p>
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
            <div class="row"><!-- mar1 -->
            	<div class="tab_header row">
                	<h3>全站链接可用性监测结果</h3>
                    <div class="tab-hfont1" id="last_period_id"></div>
                    <div class="fl unlink-count" id="conutNotAvailable"></div>
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path%>/images/common/search.png"/></span>
                      <input class="span2 prependedInput" id="link_all_table_key" onchange="linkAllScanCycleChange()" type="text" placeholder="输入错误描述编码..."> 
                    </div>
                    <div id ="excel_out_id" class="page-btn1"   onclick="linkAllExcle()"><i></i>导出列表</div>
                </div>
                 <div class="outer-bord">
                	<div class="conditions-content-sec clearfix">
                		<span class="fl margl25">不可用链接类型：</span>
                		<div class="fl select-box select-box-60 margt16">
							 <span id="siteTypeText">站内</span>
	                            <input id="stationInsOut" type="hidden" value="1">
	                           	<i></i>
	                           <!--移入显示块开始-->
		                        <ul id="siteTypeUl" style="display: none;">
		                        	<li           name="monitorType">全部</li>
		                            <li value="1" name="monitorType">站内</li>
		                            <li value="2" name="monitorType">站外</li>
		                        </ul>
		                        <!--移入显示块结束-->
	                    </div>
	                    <div class="fl select-box select-box-120 margt16">
							 <span id="aaaTypeText">确定不可用链接</span>
	                            <input id="chainSelectId" type="hidden" value="1">
	                           	<i></i>
	                           <!--移入显示块开始-->
		                        <ul id="aaaTypeUl" style="display: none;">
		                        	<li value="" name="monitorType">全部</li>
		                        	<li value="1" name="monitorType">确定不可用链接</li>
		                            <li value="2" name="monitorType">疑似不可用链接</li>
		                        </ul>
		                        <!--移入显示块结束-->
	                    </div>
	                    <div class="fl period-box">
	                    	<span class="fl tit">扫描周期：</span>
	                    	<div class="fl select-box select-box-long margt16">
                    		   <span id="bbbTypeText"></span>
		                       <input type = "hidden" id = "servicePeriodIdZZ" value="${servicePeriodIdZZ }">
		                       <i></i>
	                           <!--移入显示块开始-->
		                        <ul id="bbbTypeUl" style="display: none;">
		                        </ul>
		                        <!--移入显示块结束-->
	                         </div>
	                   </div>
                	</div>
                <div class="tab_box1 row"  id="table_link_all_div">
                	<input id="iscostId" type="hidden" value="${sessionScope.shiroUser.iscost}">
                   <table class="table_2" id="table_link_all" style="width:100%; table-layout:fixed;">
                       <thead>
                       <tr class="table_2_tr">
                           <th style="width:40px; text-align: center;">序号</th>
                           <th style="width:126px; text-align: left;" >链接url</th>
<!--                            <th class="url" style="width:68px;text-align: center;" nowrap>层次</th> -->
                           <th class="titl" style="width:120px; text-align: left;">链接标题</th>
                           <th class="url" style="width:200px;text-align: left;" nowrap>父页面URL</th>
                           <th class="type" style="width:100px;text-align:left">父页面标题</th>
<!--                            <th class="ques" style="width:100px;text-align:center">资源描述 -->
<!--                            </th> -->
                           <th class="ques" style="width:100px;text-align:center;"><span class="fl">问题描述</span><div class='http-html'></div>
                           </th>
                           <th class="posi" style="width:60px;text-align:center;" nowrap>定位
                           </th>
                       </tr>
                       </thead>
                      <tbody id="table_link_all_tbody"></tbody>
                   </table>
                    <div class="zanwsj" id="table_link_all_hide">
	             
                    </div>
				  <div class="load-more"  id="excelMore" onclick="linkAllExcle()">
					  <i></i>
				  </div>
                </div>
              </div>
              </div>
              </c:if>
              <%@ include file="/common/footer.jsp"%>
            </div><!-- /tab2 -->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<%@ include file="/common/http.jsp"%>
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<!-- 分页查询列表js -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/link/link_all_detail.js"></script>
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"  />

</body>
<script type="text/javascript">
 $(function(){
        $('.select-box').hover(function(){
            $(this).children('ul').show();
        },function(){
            $(this).children('ul').hide();
        });


        function selectShow(id){
            $("#"+id+"Ul li").click(function(){
                $("#"+id+"Val").val(this.value);
                $("#"+id+"Text").html($(this).html());
                $("#"+id+"Ul").hide();
            });
        }
        selectShow("siteType");
        selectShow("aaaType");
		selectShow("bbbType");
		
		$("#siteTypeUl li").click(function(){
			$("#stationInsOut").val(this.value);
			getTablesData();
		});
		$("#aaaTypeUl li").click(function(){
			$("#chainSelectId").val(this.value);
			getTablesData();
		});
		$("#bbbTypeUl li").click(function(){
			$("#servicePeriodIdZZ").val(this.value);
			getTablesData();
		});
		
    })

</script>
</html>