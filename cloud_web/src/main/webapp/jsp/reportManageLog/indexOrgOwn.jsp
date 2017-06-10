<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>组织单位-报告管理</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
  </head>
<body>
<div class="page-wrap">
<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="ba_index" value="8" scope="request"/>
		<c:set var="menu" value="#menuBg" scope="request"/>
		<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<div class="row">
            	    <div class="tabbable tabbable-new"> <!-- Only required for left/right tabs -->
            	      <input id="ptCurrentPeriodNum"  type="hidden" />
            	      <input id="allCurrentPeriodNum"  type="hidden" />
                      <ul class="nav nav-tabs" id="combo_type_id">
                        <li class="active"><a href="#tab1" data-toggle="tab" id="to_pt_combo">标准版监测套餐</a></li>
                        <li><a href="#tab2" data-toggle="tab" id="to_all_combo">高级版监测套餐</a></li>
                      </ul>
                      <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                          <div class="tabbable-section">
                            <div id="flexsliderMore1" class="flexslider-more carousel">
                              <ul class="slides" id="ptCombo_id">
                              </ul>
                            </div>
                          </div>
                        </div>
                        
                        <div class="tab-pane" id="tab2">
                          <div class="tabbable-section">
                            <div id="flexsliderMore2" class="flexslider-more carousel">
                              <ul class="slides" id="allCombo_id">
                              </ul>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
            </div><!-- /row1 -->
            
            <div class="row">
              <div class="tab-content tab-content-border tab-css1">
            	<div class="tab_header row tab_header_white">
                	<div class="tab_header_white_con">
                        <!-- <div class="header-select pull-left" id="show_all_select_div" >
                        	<select class="selectpicker" id="show_all_select">
                                <option>显示全部</option>
                            </select>
                        </div> -->
                        <div class="input-prepend input-prepend-black" id="key_hidden_id">
                          <span class="add-on"><img alt="search" src="<%=path %>/images/common/search_black.png"/></span>
                          <input id="siteList_tbody_id_term" class="prependedInput span2"  type="text" placeholder="输入关键字...">
                        </div>
                        <div class="page-green-btn1" id="batchDownExcel" onclick="batchDownExcelFn();"><i></i>导出列表</div>
                        <div class="page-green-btn1" id="batchDownReport" onclick="batchDownReportFn();"><i class="dc"></i>批量导出报告</div>
                        <div class="page-blue-btn1"  id="sendMail_id" onclick="emailSuperviseFn();"><i></i>邮件督办</div>
                    </div>
                </div><!-- /tab_header -->
                <div class="tab_box1 row">
                    <table cellpadding="0" cellspacing="0" class="table" id="report_table">
                        <tbody>
                            <tr>
                            	<th class="th_left" style="width:28px;"><input type="checkbox" id="select_all"/></th>
                            	<th class="th_left" style="width:30px;">序号</th>
                            	<th class="th_left" style="width:90px;">网站标识码</th>
                                <th class="th_left">网站名称</th>
                                <!-- <th class="th_left" style="width:120px;">网站类型</th> -->
                                <th style="width:180px;">发送时间</th>
                                <th style="width:120px;">发送状态</th>
                                <th style="width:130px;">预览报告</th>
                            </tr>
                        </tbody>
                        <tbody id="siteList_tbody_id">
                        </tbody>
                    </table>
                    <div class="zanwsj hide" id="table_no_data_contral">暂无数据</div>
                </div><!-- /首页连通性 -->
                
                </div>
            </div><!-- /row -->
            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>
<!-- Modal -->
<div id="preview" class="page-dialog modal hide" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-header">
      <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
      <h3 id="myModalLabel11"><i class="view"></i>报告预览</h3>
    </div>
    <div class="modal-body">
    	<div id="flexsliderMore3" class="flexslider-more carousel">
          <ul class="slides">
            <li class="active"><div class="font-size1">第1期</div>2014年5月1日</li>
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
        
        <div class="modal-container">
          <div class="modal-con-header row">
            
            <div class="page-green-btn1"><i class="dc"></i>导出报告</div>
            <div class="page-blue-btn1"><i></i>邮件督办</div>
            <div class="zt-font pull-right"><i></i>成功</div>
            <h4 class="pull-right">发送状态：</h4>
          </div><!-- /modal-con-header -->
          <div class="modal-con-row row">
            <div class="modal-con-menu">
                <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#bgContent">报告</a></li>
    <!--<li><a data-toggle="tab" href="#flContent">附录</a></li>-->
                </ul>
              
            </div><!--/Sidebar content-->
            <div class="modal-con">
            	<div class="tab-content">
                	<%@ include file="pdfIndex.jsp"%>
                    <div id="flContent" class="tab-pane">附录内容</div>
                </div>
            </div><!--/Body content-->
          </div>
        </div>
                
    </div>
</div>
<form id="excelDown" enctype="application/x-www-form-urlencoded" name="excelDown" action="<%=path%>/reportManageLog_batchDownloadExcel.action" method="post">
	<input type="hidden" id="reportSitecodeEx" name="reportSitecodeEx"></input>
	<input type="hidden" id="servicePeriodNumEx" name="servicePeriodNumEx"></input>
</form>
<form id="reportDown" enctype="application/x-www-form-urlencoded" name="reportDown" action="<%=path%>/reportManageLog_batchDownloadReport.action" method="post">
	<input type="hidden" id="reportSitecode" name="reportSitecode" value=""></input>
	<input type="hidden" id="reportPeriodNum" name="reportPeriodNum" value=""></input>
</form>

<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"/>
<script language="javascript" type="text/javascript" src="<%= path %>/js/reportManageLog/reportManage.js"></script>
<script language="javascript" type="text/javascript">
	var periodId="";//期号id
	var menuType = ${result_map.menuType};
	var comboType="";//套餐类型
</script>

</body>
</html>
