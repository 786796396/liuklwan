<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>云监测  抽查网站</title>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/heade.jsp"%>
		<link rel="stylesheet" href="<%= path %>/css/zzchouc.css"/>
		<script  type="text/javascript" src="<%=path%>/js/sortSecond.js"></script>
		<link rel="stylesheet" href="<%= path %>/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<!-- 三合一 -->
		<link rel="stylesheet"  type="text/css"  href="<%= path %>/css/tabChange.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring.css"></link>
		<!--check-report-modal-->
        <link rel="stylesheet"  type="text/css"  href="<%= path %>/css/check-report-modal_group.css">
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.exhide-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="<%= path %>/js/ztree/jquery.ztree.exedit-3.5.js"></script>
        <script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js-->
        <script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
              

	<body class="bg_f5f5f5">
	
	 <input id="tabId" type="hidden" value="">
	 
	<!--抽查首页面  -->
		<div class="page-wrap">
			<input id="site_code_session" type="hidden" value="${sessionScope.shiroUser.siteCode}">
			<!--头部       satrt  -->
			<%@ include file="/common/top.jsp"%>
			<!--头部       end  -->
			<div id="main-center"class="main-container container">
				<div class="row-fluid">
					<!--左侧导航       satrt  -->
 					<c:if test='${sessionScope.shiroUser.siteCode eq "440000" || sessionScope.shiroUser.siteCode eq "440900"}'> 
						<c:set var="ba_index" value="302" scope="request"/>
						<c:set var="menu" value="#menuDeJc" scope="request"/>
 					</c:if> 
					<c:if test='${sessionScope.shiroUser.siteCode ne "440000" && sessionScope.shiroUser.siteCode ne "440900"}'>
	        			<c:if test='${(fn:length(sessionScope.shiroUser.siteCode))==6}'>
							<c:set var="ba_index" value="302" scope="request"/>
							<c:set var="menu" value="#menuDeJc" scope="request"/>
						</c:if>
					</c:if>  
					
					<%@ include file="/common/leftmenu.jsp"%>
					<!--左侧导航       end  -->
					<div class="page-content paddt-27">
                         <div class="row bread-crumbs daily-monitor-box-m">
			                <div class="bread-crumbs-content">
			                    <span class="cor-0498e4">报告和整改</span>
			                    <i class="cor-0498e4">></i>
			                    <span>网站抽查</span>
			                </div>
           				</div>
                        <div class="five_tab_part">
	                         <div class="five_tp_top">
			                    <div class="every_block clearfix">
			                        <div class="fl every_tip on" id="tab0" >网站抽查</div>
			                        <div class="fl every_tip" id="tab1" >下级抽查汇报</div>
			                        <div class="fl every_tip" id="tab2" style="position: relative;">下级整改反馈
			                        	<div class="msg">
			                                <div class="ms-wen-con">
			                                    <!--<h3>HTTP状态码定义</h3>-->
			                                    <div class="ztm-con">
			                                       	 此处可以查看上级对您管理的网站下发的抽查整改通知以及其对此通知的反馈情况;
			                                    </div>
			                                    <i class="angle-left"></i>
			                                </div>
			                                <div class="ms-icon-wen">
			                                    <i class="i-wen">?</i>
			                                </div>
			                        	</div>
			                        </div>	
			                    </div>
			                    <div class="green_line"></div>
	                		</div>

	                		<div class="five_tp_content">
	                		
	                <!--=========	网站抽查 Start ========-->
	                <div class="every_tabs on">
					<input type="hidden" id="flag_id" value="${flag }">
            		<input type="hidden" id="scheduleId"/>
            		<input type="hidden" id="scheduleId2" value="${scheduleId}">
                    <div class="cur-info">
                        <div class="result-info">
                            <span id="curredContractCode">当前服务合同号：</span>
                            <span>已抽查站次：<i id="spotNum"></i></span>
                            <span>可抽查总站次：<i id="spotSum"></i></span>
                            <span>剩余可抽查站次: <i id="remainNum"></i></span>
                        </div>
                        <div class="select_btn clearfix">
                            <div class="search-part fl">
                                <span class="fl">全局搜索：</span>
                                <div class="input-b fl">
                                    <input id="input_key_word_id" type="text"  value="请输入任务名称"  onfocus="this.value='';this.style.color='#000';"  onblur="if(this.value==''){this.style.color='#b7b7b7';this.value='请输入任务名称'}"/>
                                    <i id= "search_btn_id"></i>
                                </div>
                            </div>
                            <div class="select-part fl">
                                <span class="fl">创建时间：</span>
                                <div class="fl select-box">
                                    <span id="aaaTypeText">请选择</span>
                                    <input id="select_id" type = "hidden" value = ""/>
                                    <i></i>
                                    <!--移入显示块开始-->
                                    <ul id="aaaTypeUl" style="display: none;">
                                    	<li value="" name="monitorType">请选择</li>
                                        <li value="30" name="monitorType">近一个月批次</li>
                                        <li value="90" name="monitorType">近三个月批次</li>
                                    </ul>
                                    <!--移入显示块结束-->
                                </div>
                            </div>
                           
                            <div class="btn-group fr">
                            <c:if test='${sessionScope.shiroUser.siteCode ne "bm0100" }'> 
                                <div class="fl check-report" onclick="reportUp()"  onmouseover="showReport()" onmouseout="hideReport()" data-toggle="modal" data-target="#check-report" 
                                onmouseover="showReportOver(event)" >
                                	抽查汇报
                                	<div class="cchb-tips" id="reportDiv">
                                		可以选择列表任务汇报或者自己填写任务概况汇报。
                                	</div>
                                </div>
                            </c:if>    
                                <c:if  test='${sessionScope.shiroUser.canSeeSpot eq 1}'>
                                <div class="fl new-task" id="new_task">新建任务</div>
                                </c:if>
                            </div>
                            
                        </div>
                    </div>
                
<!-- 			            		==-====--=-=====网站抽查列表div=--=-=-=-=-=-=-=-=-==-=-=-= -->
			            			<div class="chouc-tab-box">
										<table cellspacing="0" cellpadding="0" class="fuzhi-chouc-tab" id="spot_check_schedule_table_id">
											<thead class="fth">
												<tr style="width:100%;">
													<th style="width:5%;">批次</th>
													<th style="width:7%;">组次</th>
													<th class="t-left" style="width:13%;">任务名称</th>
													<th style="width:11%;">任务<br/>周期</th>
													<th style="width:9%;">任务<br/>状态</th>
													<th style="width:10%;">任务<br/>管理</th>
													<th style="width:9%;">站点<br/>数量</th>
													<th style="width:12%;">完成报<br/>告数量 </th>
													<th style="width:12%;">通知整<br/>改数量</th>
													<th style="width:12%;">已读报<br/>告数量</th>
													<th style="" nowrap>查看</th> 
													<c:if test='${sessionScope.shiroUser.siteCode ne "bm0100" }'> 
													<th class="chekbox_plr5 chekbox-th" style='width:20px;'> 
                                        				 <input type="checkbox" name="chk_all" onclick="checkAll()"/> 
                                     				</th> 
                                     				</c:if>
												</tr>
											</thead>
											<tbody id="spot_check_schedule_tbody_id">
											
											</tbody>
											<div class="zanwsj" id="spot_check_no_data_id" style="display:none"></div>
										</table>
										<div class="pay"></div>
									</div>
			            		
			            
	                		</div>
			<!-- 	           --==---======网站抽查-----END  =-=-=-==-==-=- -->
            <!-- ======-=-==-=-=-=-=-=-=   	抽查汇报  -=-=-=-=-START -->
	                		<div class="every_tabs">
	                		<input type="hidden" id="siteCodes" value="${sessionScope.shiroUser.siteCode}">
        			<!-- 	               	  查询条件		 -->
					               <div class="chouc-tab-header">
									<div class="input-prepend-black pull-left">
							                    <label>
							                    	全局搜索：
							                        <input  id="input_key_word_id_report" type="search" placeholder="请输入任务名称/编码">
							                        <span class="add-on" id="search_btn_id_report"><img src="<%=path%>/images/common/search_black.png" alt="search"  style="cursor:pointer;'"></span>
							                    </label>
							                </div>
								   </div>
				<!-- 	                		数据表格			 -->
					                	<div class="chouc-tab-box">
											<table cellspacing="0" cellpadding="0" class="fuzhi-chouc-tab" id="spot_check_schedule_table_id_report">
												<thead class="fth">
													<tr style="width:100%;">
														<th style="width:15%;">单位名称<br/>编码</th>
														<th class="t-left" style="width:10%;">任务名称</th>
														<th style="width:15%;">任务<br/>周期</th>
														<th style="width:9%;">任务<br/>状态</th>
														<th style="width:8%;">站点<br/>数量</th>
														<th style="width:10%;">汇报<br/>时间</th>
														<th style="width:15%;" colspan="2" nowrap>查看</th> 
													</tr>
												</thead>
												<tbody id="spot_check_schedule_tbody_id_report">
												
												</tbody>
												<div class="zanwsj" id="spot_check_no_data_id_report" style="display:none">
												</div>
											</table>
										</div>		
	                			</div>
<!-- 	      =-=-==-=-=-=-=-=-=-          			抽查汇报-=-=-=-=-=-=END -=-=-=-=-=-=-=-=- -->
	       <!-- 	      ======-=-==-=-=-=-=-=-= 下级整改通知 -=-=-=-=-START -->	
	                			<div class="every_tabs">
									<!-- 数据表格 -->
									 
									<div id="top_opacity_id" style="display:none;position:absolute; top:0; left:0; height:116px; width:100%; background:rgba(100,100,100,0.6);z-index:999;"></div>
									
										<div class="row daily-monitor-box-m conditions">
											<div class="conditions-content clearfix">
												<div class="fl select-box">
													<span id="siteTypeText">网站类别</span> 
													<input type="hidden" id="siteTypeIdVal"> <i></i>
													<!--移入显示块开始-->
													<ul id="siteTypeIdUl" style="display: none;">
														<li name="siteType" id="siteType" value="0">网站类别</li>
														<c:forEach items="${dicList}" var="dic">
															<li name="siteType" id="siteType" value="${dic.value}">${dic.name}</li>
														</c:forEach>
													</ul>
													<!--移入显示块结束-->
												</div>
												<div id="dateStr" class="fl detection-date"></div>
												<div class="fl total-box">
													共： <span id="sizeId" class="cor-f20707"></span> 条
												</div>
									<div class="fl select-box select-box-100 margl25">
										 	<span id="feedbacktateText" class="fl tit">反馈状态</span>
				                            <input id="chainSelectId" type="hidden">
				                           	<i></i>
				                           <!--移入显示块开始-->
					                        <ul id="feedbacktateUl" style="display: none;">
					                        	<li value="0" name="monitorType">反馈状态</li>
					                        	<li value="1" name="monitorType">已读已反馈</li>
					                            <li value="2" name="monitorType">未读未反馈</li>
					                            <li value="3" name="monitorType">已读未反馈</li>
					                        </ul>
					                        <!--移入显示块结束-->
				                    </div>
								
												<div class="fr search-box input-b">
													<input id="keyId" type="text" class="search-input fl" placeholder="请输入文件名" /> <i class="fl" id = "feedbackClick"></i>
												</div>
											</div>
										</div>
															
									<div id="left_opacity_id" style="display:none;position:absolute; top:116px; left:0;width:225px; background:rgba(100,100,100,0.6);z-index:999;"></div>
									        	<input id="isScanFlag_id" type="hidden" value="${isScanFlag}">
									            <table id="wordList" class="margt16">
									            	<tbody>
									            	</tbody>
									            </table>
									            <!--底部    start-->
									<br>               			
									<%@ include file="/common/http.jsp"%>	                			
									<div class="modal fade" id="myModalNoticeUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="max-width:1080px; min-width:870px; margin-left: -435px; display:none;">
									    <div class="modal-dialog">
									        <div class="modal-content" style="__height: 510px;">
									        
									            <div class="modal-header zg" style=" position: relative;">
									                <div type="button" class="close zg-x" data-dismiss="modal" aria-hidden="true">
									                </div>
									                <h4 class="modal-title zg-title" id="myModalLabel">整改反馈</h4>
									            </div>
									            <div class="modal-body tfk-content" style="">
									                <div  class="tfk-website">
									                    <p class="name">网站名称：<span id="siteName"></span></p>
									                    <p class="zhuban">主办单位：<span id="director"></span></p>
									                </div>
									                <div class="tfk-time">
									                    <p class="time">通知时间：<span id="requireTime"></span></p>
									                    <p class="yao">整改要求：<span id="noticeRequirement"></span></p>
									                </div>
									                <div class="tfk-info">
										                    <div class="ques">
										                         <p class="time">整改问题: <span id="questionNum"></span>个</p>
										                    </div>
										                     <div class="ques">
										                         <p class="time">整改反馈: <span id="noticeResponse"></span></p>
										                    </div>
										                    <div class="ques">
										                         <p class="yao">整改报告: <span id="noticeReport"></span></p>
										                    </div>
									                </div>
									            </div>
									            <div class="modal-footer">
									                <button type="button" class="btn white-btn"
									                        data-dismiss="modal">关闭
									                </button>
									            </div>
					       				 </div><!-- /.modal-content -->
					   				 </div><!-- /.modal -->
								</div>
									<!-- 查看整改通知 -->
									<div class="modal fade" id="myModalNoticeShow" tabindex="-1" role="dialog"
									     aria-labelledby="myModalLabel" aria-hidden="false" style="max-width:1080px; min-width:870px; margin-left: -435px; display:none;">
									    <div class="modal-dialog">
									        <div class="modal-content" style="__height: 510px;">
									            <div class="modal-header zg" style=" position: relative;">
									                <div type="button" class="close zg-x"
									                        data-dismiss="modal" aria-hidden="true">
									
									                </div>
									                <h4 class="modal-title zg-title" id="myModalLabel">
									                    整改通知
									                </h4>
									            </div>
									            <div class="modal-body zg-content" style="">
									                <div  class="zfs-fz"><span id="siteNames"></span>官网负责人，您好！</div>
									                <div class="report_d">
									                    <div class="p2">
									                        <p class="pb" id="directors"></p>
									                        <p class="pn">在 <span id="requireTimes"></span>为您所负责网站完成了一次全面深度检测。</p>
									                    </div>
									                    <div class="d_part">
									                        <i class="d-icon"></i>
									                        <span class="d_text" id="isDown">报告下载</span>
									                        <input type="hidden" id="fid" name="fid">
									                    </div>
									                </div>
									                <div class="please-change">
									                    <p class="time-part">收到此通知后，请在<span id="endTime"></span>前完成网站整改</p>
									                    <div class="em" style="overflow:hidden;">
									                        <p class="mail">
									                            通知邮箱：
									                            <span id="linkmanName"></span>&nbsp;<span id="email2"></span><span id="principalName"></span>&nbsp;<span id="email"></span>
									                        </p>
									                        <p class="requir">
									                            整改要求：
									                            <span id="noticeRequirements" ></span>
									                        </p>
									                    </div>
									                </div>
									            </div>
									            <div class="modal-footer">
									                <button type="button" class="btn btn-default white-btn"
									                        data-dismiss="modal">关闭
									                </button>
									            </div>
									        </div><!-- /.modal-content -->
									    </div><!-- /.modal -->
									</div>
									<!-- 查看整改通知 -->
									<div class="modal fade" id="myModalNoticeShowFk" tabindex="-1" role="dialog"
									     aria-labelledby="myModalLabel" aria-hidden="false" style="max-width:1080px; min-width:870px; margin-left: -435px; display:none;">
									    <div class="modal-dialog">
									        <div class="modal-content" style="__height: 510px;">
									            <div class="modal-header zg" style=" position: relative;">
									                <div type="button" class="close zg-x"
									                        data-dismiss="modal" aria-hidden="true">
									
									                </div>
									                <h4 class="modal-title zg-title" id="myModalLabel">
									                    整改反馈
									                </h4>
									            </div>
									            <div class="modal-body zck-content" style="">
									                <div  class="zck-website">
									                    <p class="name">网站名称：<span id="siteNamesFk"></span></p>
									                    <p class="zhuban">主办单位：<span id="directorsFk"></span></p>
									                </div>
									                <div class="zck-time">
									                    <p class="time">通知时间：<span id="requireTimesFk"></span></p>
									                    <p class="yao">整改要求：<span id="noticeRequirementsFk"></span></p>
									                </div>
									                <div class="zck-info">
									                    <p class="feedback">整改问题：<span id="questionNumFk"></span></p>
									                    <p class="feedback">整改反馈：<span id="noticeResponsesFk"></span></p>
									                    <p class="report">整改报告：<a href="javascript:void(0);" id="reportsFk"></a><span id="reportsFks"></span></p>
									                    <input type="hidden" id="fidFk" name="fid">
									                </div>
									            </div>
									            <div class="modal-footer">
									                <button type="button" class="green-btn"
									                        data-dismiss="modal">关闭
									                </button>
									            </div>
									        </div><!-- /.modal-content -->             			
									</div>
									</div>	
	                			</div>
	            <!-- 	 ======-=-==-=-=-=-=-=-= 下级整改通知 -=-=-=-=-END -->	
	                		</div>
                		</div>
                		
                		
<!--             			<div class="row page-footer">© Copyright 2015. Ucap Info  All Rights Reserve</div> -->
					<%@ include file="/common/footer.jsp"%>	
					</div>
					<!-- /page-content -->
					
				</div>
	        </div>
		</div>
		<!-- Modal 新建任务 -->
		<div id="new_task_div"  style="display:none;" index="1090" class="pos">
			<div class="page_tit nav pop-page-tit newtask" id="pageTit">
		        <h3 class="pull-left">新建任务</h3>
		        <a class="page-return pull-right page-return-close"><img alt="关闭" onclick="closeTaskDiv();" src="<%=path %>/images/close_ico.png"/></a>
		    </div>
		    <div class="main-container container white-bg">
		        <div class="row-fluid bigbox">
		            <div id="Y_nav" class="page-sidebar2">
		                <span id="Y_block" class="block"></span>
		                <ul class="nav nav-pills nav-stacked">
		                  <li id="rgxzzd" class="nav-item active"><a href="#rengong" data-toggle="tab">人工选择站点</a></li>
		                  <li id="sjxzzd" class="nav-item"><a href="#suij" data-toggle="tab">随机选择站点</a></li>
		                  <li id="fzrwzd" class="nav-item "><a href="#fuzhi" data-toggle="tab">复制任务站点</a></li>
		                </ul>
		            </div>
		            <div class="tab-content bigbox_right">
		            <!-- 右上 -->
		            <div class="bigbox_right_top" id="bigboxRightTop">
		                 	<table class="brt_tab">
		                		<tr class="lineh">
		                                <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
		                                <td style="padding-right: 15px;">抽查批次：</td>
		                                </c:if>
		                                <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
		                                <td style="padding-right: 15px;">考评批次：</td>
		                                </c:if>
		                                <td>
		                                    <span style="color:#b1b1b8; font-size:14px;">第&nbsp;<select id="batchNum"></select>&nbsp;批，第&nbsp;<span id="groupNum"></span>&nbsp;组</span>
		                                </td>
		                            </tr>
		                            <tr class="lineh">
		                                <td style="padding-right: 15px;">任务名称：</td>
		                                <td>
		                                    <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
		                                    <input id="taskName" style="width:756px; color:#b1b1b8; font-size:14px;" type="text" placeholder="填写本次任务站点范围、站点级别、门户类型、抽查比例等信息" required="required"/>
		                                	</c:if>
		                                    <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
		                                    <input id="taskName" style="width:756px; color:#b1b1b8; font-size:14px;" type="text" placeholder="填写本次任务站点范围、站点级别、门户类型、考评比例等信息" required="required"/>
		                                	</c:if>
		                                </td>
		                            </tr>
		                            <tr>
		                                <td style="padding-right: 15px;">监测时间：</td>
		                                <td>
		                                    <div class="datepickerStart-bor">
		                                                <img  src="<%=path %>/images/date.png" alt="search">
		                                                <input type="text" id="datepickerStart" readonly="readonly">
		                                    </div>
		                                    &nbsp;&nbsp;至&nbsp;&nbsp;
		                                    <div class="datepickerEnd-bor">
		                                                <img src="<%=path %>/images/date.png" alt="search">
		                                                <input type="text" id="datepickerEnd" readonly="readonly">
		                                    </div>
		                                    <span class="font-a1a2ac" style="display: none">(最多15到30天)</span>
		                                </td>
		                            </tr>
		                   </table>
		                </div>
		                <!-- 右上 end-->
		                <div id="rengong" class="nav-tab-main active rengongx" >
		                    <!--<div id="rengong"  class="nav-tab-main">-->
		                        <div class="reng-chouc-tab">
		                            <div class="modal-body-header modal-body-header1">
		                                <div class="renwu-modal-box1">
		                                    <!-- <input type="radio" checked name="localMinistries" id="local_radio_id"/> -->
		                                    <label>
		                                        地方
		                                    </label>
		                                    <select id="local_select_id" disabled="disabled">
		                                    </select>
		                                </div>
		                                <div class="renwu-modal-box1" >
		                                    <!-- <input type="radio" name="localMinistries" id="ministries_radio_id"/> -->
		                                    <label>
		                                        部委
		                                    </label>
		                                    <select  id="ministries_select_id">
		                                    </select>
		                                </div>
		                            </div>
		                            <div class="modal-body" style="max-height: none;">
		                                <div class="modal-bodycon-header chouc-header" style="display:none">
		                                    <div class="pull-left chouc-h-r" >
		                                        <label>抽查标准：</label>
		                                        <span class="chouc-num" id="comboNum"></span>
		                                        <div class="chouc-selectpicker">
		                                            <select class="selectpicker" id="comboInfos">
		                                            	<option value='4' selected = "selected">高级版</option>
		                                            </select>
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="tree-cor-msg shuoming">
		                                    *说明:
		                                    <span class="cor-49cbe1 door"><i></i>门户</span>
		                                    <span class="cor-b937b5 excep"><i></i>例外网站</span>
		                                    <span class="cor-f55555 closedw"><i></i>关停网站</span>
		                                </div>
		                                <div class="modal-bodycon chouc-modal-bodycon row overf">
		                                    <div class="modal-con-left">
		                                        <div class="input-prepend">
		                                            <input type="text" placeholder="输入查找关键字" id="prependedInput" class="prependedInput">
		                                            <span class="add-on"><img src="<%=path %>/images/common/search_gray.png"
		                                                                      alt="search">
		                                            </span>
		                                        </div>
		                                        <div class="tree-box ztree" id="treeScrollbar">
		                                            <div id="tree_selected"></div>
		                                        </div>
		                                    </div>
		                                    <div class="modal-con-center center_botton">
		                                        <i class="point"></i>
		                                    </div>
		                                    <div class="modal-con-left ">
		                                        <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
		                                        <h3 class="addh3">已选 <span id="checkCount"></span> 个抽查网站</h3>
		                                        </c:if>
		                                        <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
		                                        <h3 class="addh3">已选 <span id="checkCount"></span> 个考评网站</h3>
		                                        </c:if>
		                                        <input type="hidden" id="restNum">
		                                        <div class="modal-tree-selected" >
		                                            <ul id="choucInfo"></ul>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="modal-footer">
		                                <button class="btn btn-success green-btn" data-toggle="modal" data-target="#choucModal"  id="certainId">
		                                    确 定
		                                </button>
		                                <button data-dismiss="modal" onclick="closeTaskDiv()" class="btn white-btn" id="cancle_id">
		                                    取 消
		                                </button>
		                            </div>
		                        </div><!--.reng-chouc-tab-->
		                    <!--</div>-->
		                </div>
		                <!--*******************/人工任务站点*****************-->
		            	<div id="suij" class="nav-tab-main suijix">
		                    <table class="suij-chouc-tab suiji-chouc-tabx" cellpadding="0" cellspacing="0">
		                        
		                       <c:if test="${fn:startsWith(sessionScope.shiroUser.siteCode,'bm0100')}">
			 		               <tr style=" position:relative;">
			                            <td valign="top">抽查范围：<div class="font-7d818e">(不包括例外<br/>关停网站)</div></td>
			                            <td class="pos_rel">
			                            	<div class="scope-select" id="scope-select_id">
			                                	<div class="list-box fl">
			                                         <input type="text"  placeholder="直辖市" readOnly="true"/>
			                                        <!--  <i></i> -->
			                                         <div class="small-box">
			                                         	  <i class="zhixia"></i>
			                                              <span onclick="getSiteCount('110000','北京')">北京</span>
			                                              <span onclick="getSiteCount('310000','上海')">上海</span>
			                                              <span onclick="getSiteCount('120000','天津')">天津</span>
			                                              <span onclick="getSiteCount('500000','重庆')">重庆</span>
			                                        </div>
			                                    </div>
			                                    <div class="list-box fl">
			                                    		<input type="text"  placeholder="东北地区" readOnly="true"/>
			                                    		<!-- <i></i> -->
			                                    		<div class="small-box2">
			                                              <i class="zhixia"></i>
			                                              <span onclick="getSiteCount('210000','辽宁')">辽宁</span>
			                                              <span onclick="getSiteCount('220000','吉林')">吉林</span>
			                                              <span onclick="getSiteCount('230000','黑龙江')">黑龙江</span>
			                                        </div>
			                                    </div>
			                                    <div class="list-box fl" >
			                                    	<input type="text"  placeholder="华北地区" readOnly="true"/>
			                                    	<!-- <i></i> -->
			                                        <div class="small-box2">
			                                          	 <i class="zhixia"></i>
			                                              <span onclick="getSiteCount('130000','河北')">河北</span>
			                                              <span onclick="getSiteCount('140000','山西')">山西</span>
			                                              <span onclick="getSiteCount('150000','内蒙古')">内蒙古</span>
			                                        </div>
			                                    </div>
			                                    <div class="list-box fl">
			                                    	 <input type="text"  placeholder="中南地区" readOnly="true"/>
			                                    	<!--  <i></i> -->
			                                         <div class="small-box3">
			                                         	<i class="zhixia"></i>
			                                              <span onclick="getSiteCount('410000','河南')">河南</span>
			                                              <span onclick="getSiteCount('420000','湖北')">湖北</span>
			                                              <span onclick="getSiteCount('430000','湖南')">湖南</span>
			                                              <span onclick="getSiteCount('440000','广东')">广东</span>
			                                              <span onclick="getSiteCount('450000','广西')">广西</span>
			                                              <span onclick="getSiteCount('460000','海南')">海南</span>
			                                        </div>
			                                    </div>
			                                     <div class="list-box fl">
			                                    	 <input type="text"  placeholder="华东地区" readOnly="true"/>
			                                    	 <!-- <i></i> -->	
			                                         <div class="small-box4">
			                                         	  <i class="zhixia4"></i>
			                                              <span onclick="getSiteCount('320000','江苏')">江苏</span>
			                                              <span onclick="getSiteCount('330000','浙江')">浙江</span>
			                                              <span onclick="getSiteCount('340000','安徽')">安徽</span>
			                                              <span onclick="getSiteCount('350000','福建')">福建</span>
			                                              <span onclick="getSiteCount('360000','江西')">江西</span>
			                                              <span onclick="getSiteCount('370000','山东')">山东</span>
			                                        </div>
			                                    </div>
			                                    <div class="list-box fl">
			                                    	 <input type="text"  placeholder="西北地区" readOnly="true"/>
			                                    	 <!-- <i></i> -->									
			                                         <div class="small-box5">
			                                         	  <i class="zhixia"></i>
			                                              <span onclick="getSiteCount('610000','陕西')">陕西</span>
			                                              <span onclick="getSiteCount('620000','甘肃')">甘肃</span>
			                                              <span onclick="getSiteCount('630000','青海')">青海</span>
			                                              <span onclick="getSiteCount('640000','宁夏')">宁夏</span>
			                                              <span onclick="getSiteCount('650000','新疆')">新疆</span>
			                                        </div>
			                                    </div>
			                                    <div class="list-box fl">
			                                    	 <input type="text"  placeholder="西南地区" readOnly="true"/>
			                                    	 <!-- <i></i>	 -->								
			                                         <div class="small-box6">
			                                         	  <i class="zhixia"></i>
			                                              <span onclick="getSiteCount('510000','四川')">四川</span>
			                                              <span onclick="getSiteCount('520000','贵州')">贵州</span>
			                                              <span onclick="getSiteCount('530000','云南')">云南</span>
			                                              <span onclick="getSiteCount('540000','西藏')">西藏</span>
			                                        </div>
			                                    </div>
			                                    <div class="list-box fl">
			                                    	<input type="text"  placeholder="新疆兵团" readOnly="true"/>
			                                    	<!-- <i></i> -->	
			                                    	<div class="small-box7">
			                                         	  <i class="zhixia7"></i>
			                                              <span onclick="getSiteCount('BT0000','新疆兵团')">新疆兵团</span>
			                                        </div>								
			                                    </div>
			                                    <div class="list-box fl">
			                                    	<input type="text"  placeholder="全选" readOnly="readonly" onclick="getAllSiteCount()"/>
			                                    </div>
			                                </div>
			                                <div class="scope-select-active" id="scope-select-active_id">
			                                    <div class="scope-sel-act-tit">已选择抽查范围：</div>
			                                    <div class="scope-sel-act-main">
			                                        <ul>
			                                            <li id="li_scope_select_id">
			                                            </li>
			                                                 <!-- <div class="close-tip">北京市(20)<span style="display: none">110000</span></div> --> 
			                                        </ul>
			                                    </div>
			                                </div>
			                            </td>
			                        </tr>
			                    </c:if>
			                    <c:if test='${(fn:indexOf(sessionScope.shiroUser.siteCode, "bm0100"))==-1}'>
			                    	<div class="scope-sel-act-main" style="display: none;">
			                        	<ul>
					                    	<li id="li_scope_select_id">
		                                        <span>${sessionScope.shiroUser.siteCode }</span><p></p>
		                                    </li>
		                                </ul>
		                            </div>
			                    </c:if>
		                        <tr>
		                            <td>站点级别：</td>
		                            <td>
		                                <c:if test="${fn:startsWith(sessionScope.shiroUser.siteCode,'bm0100')}">
			                                <label><input type="checkbox" class="level" name="level" checked="checked" value="1" style="visibility:visible;"/>&nbsp;省级</label>
			                                <label><input type="checkbox" class="level" name="level" value="2" style="visibility:visible;"/>&nbsp;市级</label>
			                                <label><input type="checkbox" class="level" name="level" value="3" style="visibility:visible;"/>&nbsp;县级</label>
		                                </c:if>
		                                <c:if test='${(fn:indexOf(sessionScope.shiroUser.siteCode, "bm0100"))==-1}'>
			                                <c:if test='${(fn:indexOf(sessionScope.shiroUser.siteCode, "0000"))>=0}'>
			                                	<label><input type="checkbox" class="level" name="level" checked="checked" value="1" style="visibility:visible;"/>&nbsp;省级</label>
			                                	<label><input type="checkbox" class="level" name="level" value="2" style="visibility:visible;"/>&nbsp;市级</label>
			                                	<label><input type="checkbox" class="level" name="level" value="3" style="visibility:visible;"/>&nbsp;县级</label>
			                                </c:if>
			                                <c:if test='${(fn:indexOf(sessionScope.shiroUser.siteCode, "0000"))<0 && (fn:indexOf(sessionScope.shiroUser.siteCode, "00"))>=0}'>
			                                	<label><input type="checkbox" class="level" name="level" checked="checked" value="2" style="visibility:visible;"/>&nbsp;市级</label>
			                                	<label><input type="checkbox" class="level" name="level" value="3" style="visibility:visible;"/>&nbsp;县级</label>
			                                </c:if>
			                                <c:if test='${(fn:indexOf(sessionScope.shiroUser.siteCode, "00"))<0}'>
			                                	<label><input type="checkbox" class="level" name="level" checked="checked" value="3" style="visibility:visible;"/>&nbsp;县级</label>
			                                </c:if>
		                                </c:if>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td>门户类型：</td>
		                            <td>
		                                <label><input type="checkbox" class="isorg-class" name="isorg-class" checked="checked"  value="1" style="visibility:visible;"/>&nbsp;门户</label>
		                                <label><input type="checkbox" class="isorg-class" name="isorg-class" value="0" style="visibility:visible;"/>&nbsp;非门户</label>
		                            </td>
		                        </tr>
		                        <tr>
		                            <td colspan="2">
		                                各单位平均抽取比例：<span style="color:#b1b1b8; font-size:14px;"><input id="spot_per_id" style=" width:50px; text-align:center;" type="text"/>&nbsp;%&#12288;已抽取<span id="spot_per_count_id">0</span>个</span>
		                            </td>
		                        </tr>
		                    </table>
		                    <div class="modal-footer" id="suijPageFooter">
		                        <button id="spot_RandomCheck_button"    class="btn btn-success green-btn" data-toggle="modal" data-target="#choucModalRandom">
		           <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>                 
		                            生成抽查列表
		           </c:if>
		           <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>                 
		                            生成考评列表
		           </c:if>
		                        </button>
		                        <button class="btn white-btn" onclick="closeTaskDiv()" data-dismiss="modal">
		                            取 消
		                        </button>
		                    </div>
		                </div>
		                <!--*******************/随机选择站点*****************-->
		                <div id="fuzhi" class="page-content2 nav-tab-main  fuzhix">
		                    <table class="fuzhi-chouc-tab fuzhi-chouc-tabx" cellpadding="0" cellspacing="0">
								<thead class="fth">
									<tr>
										<th style="width:47px;">批次</th>
										<th style="width:47px;">组次</th>
										<th class="t-left">任务名称</th>
										<th>任务周期</th>
										<th style="width:84px;">站点数 <span>(个)</span>
										</th>
										<th style="width:83px;">任务状态</th>
										<th style="width:190px;">操作</th>
									</tr>
								</thead>
								<tbody id="fzhchcrw">
	                        	</tbody>
		                    </table>
		                    <div class="modal-footer" id="suijPageFooter">
		                        <input type="hidden" value="" id="fzhSiteCodes">
		                        <button id="spot_choucModalCopy_button" class="btn btn-success green-btn" data-toggle="modal" data-target="#choucModalCopy">
									确 定
		                        </button>
		                        <button class="btn white-btn" onclick="closeTaskDiv()" data-dismiss="modal">
									取 消
		                        </button>
		                    </div>
		                </div>
		                <!--*******************/复制任务站点*****************-->
		            </div>
		        </div>
		    </div>
		</div>
    	<!-- Modal  抽查提交 -->
	    <div id="spot_submit_div_id" style="display: none">
	 		 <div class="page_tit nav pop-page-tit" id="spot_submit_top_id">
	         </div> 
			<div class="modal-body seth">
	        	<div id="rwNextMenu" class="rwu-next-left marg114">
	            	<ul class="modal-menu" id="provinceResultId">
	
	                </ul>
	            </div>
	            <div class="rwu-next-right marg114">
	            	<div class="rwu-next-operate">
	                	<div class="input-prepend-black pull-left">
	                        <lable style="position:relative;">
	                            <input id="key_search_id" type="search" class="" placeholder="网站名称">
	                            <span class="add-on fangd" style="position:absolute; top:－4px; right:10px;"><img alt="search" onclick="searchKey()" src="<%=path %>/images/common/search_black.png"></span>
	                        </label>
	                    </div>
	                    <button class="btn btn-success pull-right" id="spotCheck_button_sumb" onclick="submitSpotResult()">提交任务</button>
	                    <button class="btn btn-success pull-right" id="white_btn_button" onclick="addSpotSite()" rel="">增加站点</button>
	                </div>
	            	<table class="rw-modal-tab" cellpadding="0" cellspacing="0">
	                	<thead>
	                    	<tr>
	                            <th style="width:150px;" class="first-th">市</th>
	                            <th style="width:150px;">县</th>
	                            <th style="width:100px;">网站标识码</th>
	                            <th>网站名称</th>
	                            <th style="width:150px;">主办单位</th>
	                            <th style="width:100px;">删除</th>
	                        </tr>
	                    </thead>
	               </table>
	               <div id="rwNextTabBox">
		               <table id="rwNextTab" class="rw-modal-tab" cellpadding="0" cellspacing="0">
		                </table>
	               </div>
	            </div>
	        </div>
	    </div>
		<!-- Modal  报告预览 -->
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
		<!-- Modal 人工抽查--确认提示框 -->
		<div id="choucModal" class="page-modal modal fade hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-header modal-header2">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close2"></i></button>
		    <h3 id="myModalLabel">提示</h3>
		  </div>
		  <div class="modal-body" style="max-height:none;">
		    <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
		    <p class="exit-p">为保障抽查质量，建议您设定抽查周期在15天以上。</p>
		    </c:if>
		    <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
		    <p class="exit-p">为保障考评质量，建议您设定考评周期在15天以上。</p>
		    </c:if>
		  </div>
		    <div class="modal-footer">
		      <div class="btn btn-success green-btn" id="certainId2" >确  定</div>
		      <div class="btn white-btn" id="modalHide">取  消</div>
		    </div>
		</div>
	
		<!-- Modal 随机抽查--确认提示框 -->
		<div id="choucModalRandom" class="page-modal modal fade hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabelRandom" aria-hidden="true">
		  <div class="modal-header modal-header2">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close2"></i></button>
		    <h3 id="myModalLabelRandom">提示</h3>
		  </div>
		  <div class="modal-body" style="max-height:none;">
		    <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
		    <p class="exit-p">为保障抽查质量，建议您设定抽查周期在15天以上。</p>
		    </c:if>
		    <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
		    <p class="exit-p">为保障考评质量，建议您设定考评周期在15天以上。</p>
		    </c:if>
		  </div>
		    <div class="modal-footer">
		      <div class="btn btn-success green-btn" id="certainIdRandom" >确  定</div>
		      <div class="btn white-btn" id="modalHideRandom">取  消</div>
		    </div>
		</div>
		<!-- Modal 复制抽查--确认提示框 -->
		<div id="choucModalCopy" class="page-modal modal fade hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabelRandom" aria-hidden="true">
		  <div class="modal-header modal-header2">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close2"></i></button>
		    <h3 id="myModalLabelRandom">提示</h3>
		  </div>
		  <div class="modal-body" style="max-height:none;">
		    <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
		    <p class="exit-p">为保障抽查质量，建议您设定抽查周期在15天以上。</p>
		    </c:if>
		    <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
		    <p class="exit-p">为保障考评质量，建议您设定考评周期在15天以上。</p>
		    </c:if>
		  </div>
		    <div class="modal-footer">
		      <div class="btn btn-success green-btn" id="certainIdCopy" >确  定</div>
		      <div class="btn white-btn" id="modalHideCopy">取  消</div>
		    </div>
		</div>
	
	
<!-- 	抽查汇报的上传 -->

 <div class="page-modal modal fade hide check-report-modal" id="check-report" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 700px;height:424px;left: 50%;top:40%;margin-left: -350px;margin-top:-212px; "> 
    <div class="public-head clearfix">
        <h4 class="fl">抽查汇报</h4>
        <i class="close" data-dismiss="modal" aria-hidden="true"></i>
    </div>
    <form id="formUploadNo" action="" method="post" enctype="multipart/form-data">
    <div id = "divReportUp">
    <div class="public-content check-report-mc">
	        <div class="clearfix every-block">
	
	            <span class="fl">是否需要上传附件：</span>
	            <div class="fl">
	                <div class="left fl">
	                    <input type="radio" id="yes" name="isUp" value="1" checked="checked"/>
	                    <label for="yes">是</label>
	                </div>
	                <div class="right fl">
	                    <input type="radio" id="no" name="isUp" value="2" />
	                    <label for="yes">否</label>
	                </div>
	            </div>
	        </div>
	        <div class="clearfix every-block" id="uploadIsUp">
	            <span class="fl">附件：</span>
	
	            <div class="fl modal-inp-b">
	                <input type="text" class="fl" disabled="disable"  id="affixName" name="affixName"/>
	            </div>
	            <div class="fl upload">
	                <i>上传</i>
	                <a href="#" class="file">
	                 	<input type="file" class="fl" placeholder="上传" name="siteReportup" id="siteReportup"  value="上传"/>
	      			</a>
	            </div>
	        </div>
	        <div class="clearfix every-block">
	            <span class="fl">汇报上级：</span>
	            <div class="fl sitecode">${siteCodeUp }</div>
	        </div>
	    </div>
	   	<input type="hidden"  id= "scheduleIds" name = "scheduleIds" value=""/>
    </div>
    <div id = "divReportUpNo">
    <div class="public-content check-report-mc">
        <div class="clearfix every-block">
            <span class="fl">任务名称：</span>
            <div class="fl modal-inp-b-long colo-7e">
                <input type="text" class="fl" id = "taskNameUp" name = "taskName" placeholder="请输入汇报的抽查任务名称（20字以内）"/>
            </div>
        </div>
        <div class="clearfix every-block">
            <span class="fl">抽查站点数量：</span>
            <div class="fl modal-inp-b-long colo-7e">
                <input type="text" class="fl"  onkeyup="value=value.replace(/[^\d]/g,'') " id = "websiteNum" name="websiteNum" placeholder="请输入抽查站点的数量"/>
            </div>
        </div>
        <div class="clearfix every-block task-p">
            <span class="fl">任务周期：</span>
            <div class="fl">
                <div class="time-p">
                    <input type="text" id = "startDate" readonly="readonly" name="startDate" value="${startDate }"/>
                </div>
                <i class="to">至</i>
                <div class="time-p">
                    <input type="text" id = "endDate" readonly="readonly" name="endDate" value="${endDate }"/>
                </div>
            </div>
        </div>
        <div class="clearfix every-block">
            <span class="fl">汇报上级：</span>
            <div class="fl colo-7e">${siteCodeUp }</div>
        </div>
        <div class="clearfix every-block task-s">
            <span class="fl">任务状态：</span>
            <div class="fl colo-7e">
                <div class="left fl">
                    <input type="radio" id="statesyes" name="status" value="2" checked="checked"/>
                    <label for="yes">已完成</label>
                </div>
                <div class="right fl">
                    <input type="radio" id="statesno" name="status" value = "1"/>
                    <label for="yes">未完成</label>
                </div>
            </div>
        </div>
        <div class="clearfix every-block" >
            <span class="fl">附件：</span>

             <div class="fl modal-inp-b colo-7e">
                <input type="text" class="fl" disabled="disable"  id="affixNameNo" name="affixNameNo"/>
            </div> 
             <div class="fl upload">
                <i>上传</i>
                <a href="#" class="file">
                <input type="file" class="fl" placeholder="上传" name="siteReportupNo" id="siteReportupNo"  value="上传"/>
                </a>
            </div> 
        </div>
    </div>
    </div>
    <div class=" public-bottom clearfix">
        <div class="cancle btns close" data-dismiss="modal" aria-hidden="true">取&nbsp;&nbsp;&nbsp;&nbsp;消</div>
        <div class="ensure btns" id="reportUpNo">确&nbsp;&nbsp;&nbsp;&nbsp;定</div>
    </div>
    </form>
    
   </div>   
<!--第三个modal e -->
	
		<!--周期动态效果控件js引入  -->
		<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
		<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
		<!--周期动态效果控件css引入  -->
 		<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"/>
		<script language="javascript" type="text/javascript" src="<%= path %>/js/datepicker/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="<%= path %>/js/wordList/requestReport.js"></script>
		<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/spotCheckResult.js"></script>
		<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/loadtree.js"></script>
		<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/tables.js"></script>
		<script language="javascript" type="text/javascript" src="<%= path %>/js/spotCheckResult/randomSpot.js"></script>
		 <script type="text/javascript" src="<%= path %>/js/fnReloadAjax.js"></script> 
	</body>
	
	<script language="javascript" type="text/javascript">
		$(function() {
			$(function() {
				$('.select-box').hover(function() {
					$(this).children('ul').show();
				}, function() {
					$(this).children('ul').hide();
				});

				function selectShow(id) {
					$("#" + id + "Ul li").click(function() {
						$("#" + id + "Val").val(this.value);
						$("#" + id + "Text").html($(this).html());
						$("#" + id + "Ul").hide();
					});
				}
				selectShow("siteType");
				selectShow("aaaType");
				selectShow("feedbacktate");

				$("#feedbacktateUl li").click(function() {
					$("#chainSelectId").val(this.value);
					$("#feedbacktateUl").hide();
					$("#feedbacktateText").html($(this).html());
					getWordList();
				});
				$("#siteTypeIdUl li").click(function() {
					$("#siteTypeIdVal").val(this.value);
					$("#siteTypeIdUl").hide();
					$("#siteTypeText").html($(this).html());
					getWordList();
				});
				$("#aaaTypeUl li").click(function(){
					if(this.value == 0){
					$("#select_id").val("");
					}else{
					$("#select_id").val(this.value);
					}
					getTables();
				});

			})
		})
	</script>
</html>
