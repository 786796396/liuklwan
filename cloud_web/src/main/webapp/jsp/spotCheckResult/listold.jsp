<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>云监测  抽查网站</title>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/heade.jsp"%>
		<script  type="text/javascript" src="<%=path%>/js/sortSecond.js"></script>
		<link rel="stylesheet" href="<%= path %>/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.exhide-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.exedit-3.5.js"></script>
        <script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js-->
	</head>

	<body style="background:#f5f5f5;">
		<div class="page-wrap">
			<!--头部       satrt  -->
			<%@ include file="/common/top.jsp"%>
			<!--头部       end  -->
			<div class="main-container container">
				<div class="row-fluid">
					<!--左侧导航       satrt  -->
					<c:set var="ba_index" value="6" scope="request"/>
					<c:set var="menu" value="" scope="request"/>
					<%@ include file="/common/leftmenu.jsp"%>
					<!--左侧导航       end  -->
					<div class="page-content">
					<%-- <%@ include file="/common/empty-new.jsp"%> --%>
					<div class="header-box row">
						<h3 class="data-h2">网站抽查</h3>
						<span class="font-787878">已抽查站次：<span id="spotNum"></span>
							次&#12288;可抽查总站次：<span id="spotSum"></span> 次</span>
					</div>
					<div class="chouc-tab-header">
						<div class="input-prepend-black pull-left">
							<label> 全局搜索： <input type="search" placeholder="网站标识码和名称"
								class=""> <span class="add-on"><img
									src="/cloud_web/images/common/search_black.png" alt="search">
							</span> </label>
						</div>
						<div class="time-sear pull-left">
							<label> 创建时间： <select>
									<option>近三个月批次</option>
							</select> </label>
						</div>
						<div class="btn-3ac42d add-renw" data-toggle="modal"
							data-target="#renwuModal">新建任务</div>
					</div>
					<div class="chouc-tab-box">
						<table cellspacing="0" cellpadding="0" class="fuzhi-chouc-tab">
							<thead>
								<tr>
									<th>批次</th>
									<th>组次</th>
									<th class="t-left">任务名称</th>
									<th>任务周期</th>
									<th>站点数 <span>(个)</span></th>
									<th style="width:80px;">任务状态</th>
									<th style="width:190px;"></th>
									<th>启动抽查</th>
								</tr>
							</thead>
							<tbody id="accordion">
								<tr>
									<td><div class="fuzhi-font1">${batchNum}</div></td>
									<td>${groupNum}</td>
									<td class="t-left">
										<div class="fuzhi-font2">${taskName}</div>
									</td>
									<td>
										<div class="fuzhi-font2">起：${dateStart}</div>
										<div class="fuzhi-font2">止：${endStart}</div>
									</td>
									<td><a href="#">${webSum}</a>
									</td>
									<td>${state}</td>
									<td>
										<div class="add-zd">
											<i></i>&nbsp;增加站点
										</div>
									</td>
									<td>
										<i class="radio-phone" onclick="initLi(this);"></i>
									</td>
									
								</tr>
							</tbody>
						</table>
					</div>
					<!--底部    start-->
					<div class="row page-footer">© Copyright 2015. Ucap Info All
						Rights Reserve</div>
					<%-- 	<%@ include file="/common/footer.jsp"%> --%>
					<!--底部    end-->

				</div>

				<!-- /page-content -->
				</div>
			</div>
			<!-- /container -->
		</div>
		<!-- Modal -->
		<div id="renwuModal"
			class="page-fixed-modal page-modal modal fade hide" id="myModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-header modal-header2">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					<i class="dialog-close2"></i>
				</button>
				<h3 id="myModalLabel" >
					新建任务
				</h3>
			</div>
			<div class="modal-body-header">
				<div class="renwu-modal-box1">
					<input type="radio" checked name="localMinistries" id="local_radio_id"/>
					<label>
						地方
					</label>
					<select  disabled  id="local_select_id">
					</select>
				</div>
				<div class="renwu-modal-box1" >
					<input type="radio" name="localMinistries" id="ministries_radio_id"/>
					<label>
						部委
					</label>
					<select  disabled id="ministries_select_id">
					</select>
				</div>
			</div>
		<div class="modal-body" style="max-height: none;">
			<div class="modal-bodycon-header chouc-header">
                <div class="pull-left chouc-h-r">
                    <label>抽查标准：</label>
                    <span class="chouc-num" id="comboNum"></span>
                    <div class="chouc-selectpicker">
                        <select class="selectpicker" id="comboInfos"></select>
                    </div>
                </div>
                <div class="pull-left">
                    监测时间：
                    <div class="datepickerStart-bor">
                        <img src="<%=path %>/images/date.png" alt="search">
                        <input type="text" id="datepickerStart">
                    </div>
                    &nbsp;&nbsp;至&nbsp;&nbsp;
                    <div class="datepickerEnd-bor">
                        <img src="<%=path %>/images/date.png" alt="search">
                        <input type="text" id="datepickerEnd">
                    </div>
                    <span class="font-a1a2ac" style="display: none">(最多15到30天)</span>
                </div>
			</div>
            <div class="tree-cor-msg">
                *说明:
                <span class="cor-49cbe1"><i></i>门户</span>
                <span class="cor-b937b5"><i></i>例外网站</span>
                <span class="cor-f55555"><i></i>关停网站</span>
            </div>
			<div class="modal-bodycon chouc-modal-bodycon row">
				<div class="modal-con-left">
					<div class="input-prepend">
						<input type="text" placeholder="输入查找关键字" id="prependedInput" class="prependedInput">
						<span class="add-on"><img
							src="<%=path %>/images/common/search_gray.png" alt="search">
						</span>
					</div>
					<div class="tree-box ztree" id="treeScrollbar">
                        <div id="tree_selected"></div>
					</div>
				</div>
				<div class="modal-con-center">
					<i class="point"></i>
				</div>
				<div class="modal-con-right">
					<h3>已选 <span id="checkCount"></span> 个抽查网站</h3>
					<input type="hidden" id="restNum">
					<div class="modal-tree-selected" >
						<ul id="choucInfo"></ul>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
				<input type="hidden" id="scheduleId"/>
				<!-- <button class="btn btn-success green-btn" data-toggle="modal" data-target="#choucModal"  id="certainId">
					确 定
				</button> -->
				<button data-dismiss="modal" class="btn white-btn">
					取 消
				</button>
			</div>
		</div>
<form id="reportDown" enctype="application/x-www-form-urlencoded" name="reportDown" action="<%=path%>/spotCheckResult_checkExportReport.action" method="post">
	<input type="hidden" id="orgSiteCode" name="orgSiteCode" value=""></input>
	<input type="hidden" id="spotsiteCodes" name="spotsiteCodes" value=""></input>
	<input type="hidden" id="checkNumber" name="checkNumber" value=""></input>
</form>
<!-- Modal -->
<div id="preview" class="page-dialog modal hide" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-header">
      <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
      <h3 id="myModalLabel_show"><i class="view"></i>报告预览</h3>
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
                	<div id="bgContent" class="tab-pane active" style="height: 1000px">
                	 <%@ include file="/jsp/reportManageLog/pdfIndex.jsp"%>
                	</div>
                    <div id="flContent" class="tab-pane">附录内容</div>
                </div>
            </div><!--/Body content-->
          </div>
        </div>
                
    </div>
</div>
<!-- Modal -->
<div id="choucModal" class="page-modal modal fade hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header modal-header2">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close2"></i></button>
    <h3 id="myModalLabel">提示</h3>
  </div>
  <div class="modal-body" style="max-height:none;">
    <p class="exit-p">为保障抽查质量，建议您设定抽查周期在15天以上。</p>
  </div>
    <div class="modal-footer">
      <div class="btn btn-success green-btn" id="certainId2">确  定</div>
      <div class="btn white-btn" id="modalHide">取  消</div>
    </div>
</div>
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"/>
<script language="javascript" type="text/javascript" src="<%= path %>/js/datepicker/jquery-ui-timepicker-addon.js"></script>
<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/spotCheckResult.js"></script>
<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/loadtree.js"></script>
<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/tables.js"></script>
</body>
</html>
