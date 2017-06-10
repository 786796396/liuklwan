<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>首页链接可用性</title>
	<%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
</head>
<body>
	<!--头部       satrt  -->
	<%@ include file="/common/top_tb.jsp"%>
	<!--头部       end  -->
	<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="5" scope="request"/>
		<c:set var="menu" value="#menuLjkyx" scope="request"/>
        	<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<div class="row">
                <ul class="breadcrumb">
                    <li><a href="#">日常监测</a> <span class="divider">></span></li>
                    <li class="active">首页链接可用性</li>
                    <li class="jc-ms">
                        <div class="ms-msg">
                            <div class="ms-wen-con" style="height:70px; top:-90px;">
                                <div class="ztm-con">
                                    <p>1.考察网站每天首页上的链接（包括图片、附件、外部链接等）能否正常访问（打不开或错误）；</p>
                                    <p>2.统计网站每天首页上不能正常访问的链接数量。</p>
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
            
            <div class="row">
            	<h3 class="info_fx_h3 bg-324f6d">首页链接可用性趋势</h3>
                <div class="t_box4 info_fx_con">
                    <div class="line_chart" >
                    	<div id="link_home_line_id" style="height:350px;width: 100%;"></div>
                    </div>
                </div>
            </div><!-- /row3 -->
             <div class="row">
            	<div class="tab_header row">
                	<h3>首页链接可用性监测结果 <input class="datepicker-input" type="text" id="datepicker"/></h3>
                	<inuput type = "hidden" id = "datepickerVal">
                    <div class="input-prepend">
                      <span class="add-on"><img alt="search" src="<%=path %>/images/common/search.png"/></span>
                      <input class="prependedInput span2" id="keyInput" type="text" placeholder="输入问题描述编码...">
                    </div>
                    <div id="home_excel_out"></div>
                </div>
                <div class="tab_box1 row">
                
<!--                 	<div class="fl select-box"> -->
<!--                        <span id="siteTypeText">确定不可用链接</span> -->
<!--                        <input type="hidden" id="siteTypeIdVal"> -->
<!--                     		<i></i> -->
<!-- 	                    	<ul id="siteTypeIdUl" style="display: block;"> -->
<!-- 	                      		  <li  name="siteType" id="siteType" value="">全部</li> -->
<!-- 	                              <li  name="siteType" id="siteType" value="1">确定不可用链接</li> -->
<!-- 	                              <li  name="siteType" id="siteType" value="2">疑似不可用链接</li> -->
<!-- 	                        </ul> -->
<!--                     	</div> -->

<!-- 					  	<select id="homeUnavailableSelectId" onchange="homeUnavailableOption()"> -->
<!-- 							<option value =""  >全部</option> -->
<!-- 							<option value ="0" selected="selected">确定不可用链接</option> -->
<!-- 							<option value ="1" >疑似不可用链接</option> -->
<!-- 						</select>    -->
	 				   <div class=" select-box select-box-120 margt16">
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
                    <div class="red-tip"><p>注：以下链接仅在本监测时刻不通，不代表在其他时间不通。</p></div>
                    <div class="dropload-load"><span class="loading"></span>加载中...</div>
                    <table id="linkHome_table" cellpadding="0" cellspacing="0" class="table t-tab1 hide">
                        <thead>
                            <tr>
                            	<th class="th_left" style="width:5%">序号</th>
                            	<th class="th_left" style="width:8%">监测时刻</th>
                                <th class="th_left" >不可用链接URL</th>
                                <th class="th_left" style="width:8%">链接标题</th>
                                <th class="th_left" >父页面URL</th>
                                <th class="th_left" style="width:8%">连接不可用天数</th>
                                <th class="th_left" style="width:8%">资源类型 </th>
                                <th class="th_left" >
                                    <span class="pull-left">问题描述</span>
                                    <div class="http-html"></div>
                                    <%@ include file="/common/http.jsp"%>
                                </th>
                                <th style="width:44px;">定位</th>
                            </tr>
                            </thead>
                            <tbody id="linkList">
                            <input type="text" id="dateText" style="display: none"/>
                            </tbody>
                    </table>
                    <div class="red-tip"><p>注：以下链接仅在本监测时刻不通，不代表在其他时间不通。</p></div>
                     <div class="zanwsj" id="linkHome_table_hide">未发现问题</div>
                </div>
            </div><!-- /tab2 -->

           
            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/echarts-all.js"></script> 
<script language="javascript" type="text/javascript" src="<%=path %>/js/link/link_home_available.js"></script>
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
        selectShow("aaaType");
		
		$("#aaaTypeUl li").click(function(){
			$("#chainSelectId").val(this.value);
			linkHome($("#datepickerVal").val());
		});
		
    })



</script>
</html>