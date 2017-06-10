<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>全面监测  网站监测</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/zzchouc.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/wzkp_details.css"/>
<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
</head>
<body>
	<form id="dowForm" enctype="application/x-www-form-urlencoded" name="dowForm" action="<%=path%>/downloadTemp_downFileUrl.action" method="post">
		<input type="hidden" id="filePath" name="filePath"></input>
		<input type="hidden" id="fileName" name="fileName"></input>
	</form>
	<form id="dowFormReport" enctype="application/x-www-form-urlencoded" name="dowFormReport" action="<%=path%>/spotCheckResult_batchReportDowLoad.action" method="post">
		<input type="hidden" id="filePathReport" name="filePathReport"></input>
		<input type="hidden" id="fileNameReport" name="fileNameReport"></input>
	</form>


<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%=path%>/images/common/top-hover.png"/>
        <img class="top-img" src="<%=path%>/images/common/top.png"/>
    </a>
</p>
<div class="page_wrap">
		<c:if test="${fn:length(sessionScope.shiroUser.siteCode)>6}">
			<%@ include file="/common/top_tb.jsp"%>
		</c:if>
		<c:if test="${fn:length(sessionScope.shiroUser.siteCode)<=6}">
			<%@ include file="/common/top.jsp"%>
		</c:if>
<div class="main-container container">
<input id="loginSiteCode" type="hidden" value="${sessionScope.shiroUser.siteCode}">
<input type="hidden" id="scheduleId" value="${scheduleId }">
<input type="hidden" id="startDate" value="${startDate }">
<input type="hidden" id="quDate" value="${quDate }">
<input id="endDate" type="hidden" value="${endDate }">
<input type="hidden" id="servicePeriodIds" value="${servicePeriodId }">
<input id="servicePeriodStatus" type="hidden" value="${state }">
<input id="toType" type="hidden" value="${type }">
<input id ="batchNum" type="hidden" value="${batchNum }">
<input id ="serviceNoId" type="hidden" value="${servicePeriodNum}">
<input id ="finishRe" type="hidden" value="${finishRe}">
<input value = "${scheduleCheckId} " type="hidden" id= "scheduleCheckId">
	<div class="row-fluid">
	
		<%@ include file="/common/leftmenu.jsp"%>
		
				
        <div class="page-content kp" style="padding-top:110px;">
            <div class="kp_title">
                <span class="main_title" id="titleName"></span><!--<i class="point_icon"></i>--><span class="subtitle">>&nbsp;详情</span>
            </div>
            <div class="batch_group-part clearfix">
                <div class="fl batch">第${batchNum }批</div>
                <div class="fl group">第${groupNum }组</div>
                <div class="fl total">(${webCount })个</div>
                <div class="fl task_num">任务号： ${servicePeriodNum }</div>
                <div class="fl state">状态：${status }</div>
             
               
                <div class="fr period">检测周期：${startDate }  至  ${endDate }</div>
            </div>
            
            <div class="data_show-part">
                <div class="data_show-content">
                    <div class="show-part-top clearfix">
                        <div class="search_box fl">
                            <input type="text" class="input_a"  id="searchInfo_id" placeholder="输入网站名称或标识码..."/>
                            <i></i>
                        </div>
                        <div class="checkboxs_box fl">
                        <!--全面检测       satrt  -->
					<input id="siteTypeVal" type="hidden" value="0">
					<c:if test='${(fn:length(sessionScope.shiroUser.siteCode))==6}'>
                            <div class="chart_se fl">
                                               <span id="siteTypeText">网站类型</span>
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul id="siteTypeUl">
                                                	<li  name="siteType" id="siteType" value="0">网站类别</li>
						                            <c:forEach items="${dicList}" var="dic">
						                            	 <li  name="siteType" id="siteType" value="${dic.value}">${dic.name}</li>
													</c:forEach>
                                                </ul>
                                                <!--移入显示块结束-->
                            </div>
                      </c:if>   
                               <div class="chart_se fl">
                                               <span id="resultTypeText">检测结果</span>
                                               <input id="resultTypeVal" type="hidden" value="0">
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul id="resultTypeUl">
                                                	<li value="0" name="monitorType">检测结果</li>
                                                    <li value="1" name="monitorType">合格</li>
                                                    <li value="2" name="monitorType">不合格</li>
                                                </ul>
                                                <!--移入显示块结束-->
                            	</div>
                               <div class="chart_se fl">
                                               <span id="reportTypeText">报告状态</span>
                                               <input id="reportTypeVal" type="hidden" value="${reportType}">
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul id="reportTypeUl">
                                                	<li value="0" name="monitorType">报告状态</li>
                                                    <li value="1" name="monitorType">已完成</li>
                                                    <li value="2" name="monitorType">未完成</li>
                                                </ul>
                                                <!--移入显示块结束-->
                            </div>
                               <div class="chart_se fl">
                                               <span id="noticeTypeText">整改状态</span>
                                               <input id="noticeTypeVal" type="hidden" value="${noticeType}">
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul id="noticeTypeUl">
                                                	<li value="0" name="monitorType">整改状态</li>
                                                    <li value="1" name="monitorType">已通知</li>
                                                    <li value="2" name="monitorType">未通知</li>
                                                    <li value="3" name="monitorType">已反馈</li>
                                                    <li value="4" name="monitorType">未反馈</li>
                                                </ul>
                                                <!--移入显示块结束-->
                            </div>
                            
                               <div class="chart_se fl">
                                               <span id="readTypeText">已读状态</span>
                                                 <input id="readTypeVal" type="hidden" value="${readType}">
                                                <i></i>
                                                <!--移入显示块开始-->
                                                <ul id="readTypeUl">
                                                	<li value="0" name="monitorType">已读状态</li>
                                                    <li value="1" name="monitorType">已读</li>
                                                    <li value="2" name="monitorType">未读</li>
                                                </ul>
                                                <!--移入显示块结束-->
                            </div>                                                  
                        </div>
                        <div class="btns_box fr">
                         <c:if test='${reportCollectResultSize != 0 }'>
                       		<div class="all-export fl" onclick="excelOrg()" onmouseover="showReport()" onmouseout="hideReport()" Style="position:relative; vertical-align:top;"
	                        	 onmouseover="showReportOver(event)" >
	                        	 检测任务报告
                        	 	<div id="reportDiv"  style="position:absolute; top:-34px; left:50%; width:380px; margin-left:-175px; border:1px solid #ccc; background:#fff; color:#000; display:none;">
				      	  			这是整个检测任务的汇总报告，检测任务完成后可以查看。
				   	 			</div>
				   	 		</div>	
                       	</c:if>
                       	<c:if test='${reportCollectResultSize == 0 }'>
                       		<div class="all-export fl unabi"  onmouseover="showReport()" onmouseout="hideReport()" Style="position:relative; vertical-align:top;"
	                        	 onmouseover="showReportOver(event)" >
	                        	 检测任务报告
                        	 	<div id="reportDiv"  style="position:absolute; top:-34px; left:50%; width:380px; margin-left:-175px; border:1px solid #ccc; background:#fff; color:#000; display:none;">
				      	  			这是整个检测任务的汇总报告，检测任务完成后可以查看。
				   	 			</div>
				   	 		</div>	
                       	</c:if>
                            <c:if test="${fn:length(sessionScope.shiroUser.siteCode)==6}">
                            	<div class="notice fl"  id="noticeListId" data-toggle="modal" onclick="openNoticeList()" onmouseover="showPrompt()" onmouseout="hidePrompt()"
                            	style="width:88px; margin-right:10px;position:relative; vertical-align:top;" onmouseover="ShowPrompt(event)" onmouseout="HiddenPrompt()">
                            	通知整改
                            	<div id="promptDiv"  style="position:absolute; top:-34px; left:50%; width:250px; margin-left:-175px; border:1px solid #ccc; background:#fff; color:#000; display:none;">
 				      	  		任务完成后可通知填报单位整改。
				   	 			</div>
                            	</div>
                            </c:if>
                            <div class="more-operate fl"  style="width:88px;">
                            	<span onclick = "moreOperation()">更多操作<i class="down_7-4"></i></span>
                            	<div class="more-p-content" id="moreOperation" style="display:none;">
                            		<ul>
                            			<li onclick="excelWebsiteList()">全部导出</li>
                            			<li onclick="batchReport()">全部下载</li>
                            		</ul>
                            	</div>
                            </div>
                        </div>
                    </div>
                    <div class="show-part-table">
                     <table id="table-SSHXSort" style="table-layout: fixed;">
                            <thead>
                                <tr>
                                    <th class="chekbox_plr5" style='width:20px;'>
                                        <input type="checkbox" name="chk_all" onclick="checkAll()"/>
                                    </th>
                                    <th style="width:90px; text-align:left;"  nowrap>网站标识码/名称</th>
                                    <th style="width:64px;">首页不<br/>连通率<i class="tab_angle"></i></th>
                                    <th style="width:64px;">网站死<br/>链个数<i class="tab_angle"></i></th>
                                    <th style="width:64px;">网站不更<br/>新问题<i class="tab_angle"></i></th>
                                    <th style="width:64px;">空白栏<br/>目个数<i class="tab_angle"></i></th>
                                    <th style="width:64px;">互动回<br/>应问题<i class="tab_angle"></i></th>
                                    <th style="width:64px;">服务不实<br/>用问题<i class="tab_angle"></i></th>
                                    <th class="w50px" style="width:64px;">严重<br/>错误<i class="tab_angle"></i></th>
                                    <c:if test='${ endDate > quDate}'>
                                    	<th id="resultsId" class="w50px" style="width:36px;">检测<br/>结果</th>
                                    </c:if>
                                   	<th id="reportId" class="w50px" style="width:36px;">报告<br/>状态</th>
                                    <th class="w50px" style="width:45px;">查看<br/>报告</th>
                                    <c:if test="${fn:length(sessionScope.shiroUser.siteCode)==6}">
                                    <th class="w50px" style="width:55px;">通知整改</th>
                                    </c:if>
                                </tr>
                            </thead>
                            </table>
                         <table id="table-Sum" style="table-layout: fixed;">   </table>
                        <table id="table-SSHX" name="tableName" value="searchInfo_id" style="table-layout: fixed;">

                            <tbody id="service_period_site_tbody_id">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div>
			<%@ include file="/common/footer.jsp"%>	
			</div>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>
<!-- 整改通知start -->
	<div class="modal fade" id="myModalNotice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="max-width:1080px; min-width:870px; margin-left: -435px; display:none;">
	    <div class="modal-dialog">
	        <div class="modal-content" style="__height: 510px;">
	            <div class="modal-header zg" style=" position: relative;">
	                <div type="button" class="close zg-x" data-dismiss="modal" aria-hidden="true"></div>
	                <h4 class="modal-title zg-title" id="myModalLabel">整改通知</h4>
	            </div>
	            <div class="modal-body zfs-content" style="">
	                <div  class="zfs-fz"><span id="siteName"></span>官网负责人，您好！</div>
	                <div class="zfs-report_d">
	                    <div class="zfs-p2">
	                        <p class="zfs-pb" id="director"></p>
	                        <p class="zfs-pn">在 <span id="requireTime"></span> 为您所负责网站完成了一次全面深度检测。</p>
	                    </div>
	                    <div class="zfs-d_part">
	                        <i class="zfs-d-icon"></i>
	                        <span class="zfs-d_text" id="downWord">报告下载</span>
	                    </div>
	                    <div class="zfs-d_part2">
	                        <i class="zfs-d-icon"></i>
	                        <span class="zfs-d_text" id="SeeReport">报告预览</span>
	                    </div>
	                </div>
	                <div class="zfs-please-change" style="border-bottom:1px solid #e5e5e5;">
	                    <div class="zfs-time-part">收到此通知后，请在
	                        <!-- <div> -->
	                            <!-- <i></i> -->
	                            <input id="datepicker" readonly="readonly" type="text" name="endTime" placeholder="请选择整改期限"/>
	                            <input id="isDown" type="hidden" name="isDown"/>
	                            <input id="siteCode" type="hidden" name="siteCode"/>
	                        <!-- </div> -->
					前完成网站整改
	                    </div>
	                    <div class="zfs-em">
	                        <div class="zfs-mail">
								通知邮箱：
	                            <div class="box">
	                                <div class="box1 marginl26 on_check" id="checkbox1">
	                                    <input type="checkbox" id="boxInput1" name="input1" value="0" style="width: 20px; height: 20px; display: inline-block"/>
	                                </div>
	                                <div class="box2">
	                                    <label for="input1"><span id="linkmanName"></span> <span id="email2"></span> </label>
	                                </div>
	                            </div>
	                            <div class="box">
	                                <div class="box1 on_check" id="checkbox2">
	                                    <input type="checkbox" id="boxInput2" name="input2" value="1" style="width: 20px; height: 20px; display: inline-block"/>
	                                </div>
	                                <div class="box2">
	                                    <label for="input1"><span id="principalName"></span> <span id="email"></span> </label>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="zfs-requir">
	                            <div class="p">整改要求：</div>
	                            <div class="texta">
	                                <textarea name="noticeRequirement" id="noticeRequirement" placeholder="此处编辑“整改要求”"></textarea>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <div class="zfs-please-change">
		                  <div style="display:none;" id="upperOrgAllow" >
		                    	<div class="box1 marginl26 on_check marginb10" id="checkbox_allow">
		                              <input type="checkbox" id="boxInput1_allow" name="input1_allow" value="0" style="width: 20px; height: 20px; display: inline-block"/>
		                              	允许<span id="uppperOrgName"></span>查看此通知并收到邮件整改通知
		                              <input type="hidden" id="upperOrgCode" name="upperOrgCode"/>
		                         </div>
			              </div>
			               <div class="zfs-em" style="display:none;" id="upperOrgId">
	                    
		                    
	                        <div class="zfs-mail">
								通知邮箱：
	                            <div class="box">
	                                <div class="box1 marginl26 on_check" id="checkbox2">
	                                    <input type="checkbox" id="boxInput1_up" name="input1_up" value="0" style="width: 20px; height: 20px; display: inline-block"/>
	                                </div>
	                                <div class="box2">
	                                    <label for="input1_up"><span id="linkmanName_up"></span> <span id="email2_up"></span> </label>
	                                </div>
	                            </div>
	                            <div class="box">
	                                <div class="box1 on_check" id="checkbox2_up">
	                                    <input type="checkbox" id="boxInput2_up" name="input2" value="1" style="width: 20px; height: 20px; display: inline-block"/>
	                                </div>
	                                <div class="box2">
	                                    <label for="input1_up"><span id="principalName_up"></span> <span id="email_up"></span> </label>
	                                </div>
	                            </div>
	                        </div>
	                      
	                    </div>
	                 </div>
	                
	                
	            </div>
	            <div class="modal-footer">
	                <button type="button" class=" btn-default green-btn" id="noticeAdd" data-dismiss="modal">
	                	确定
	                </button>
	                <button type="button" class="btn btn-default white-btn" data-dismiss="modal">
	                	取消
	                </button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<!-- 整改通知查看 -->
	<div class="modal fade" id="myModalNoticeSee" tabindex="-1" role="dialog"
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
	                    <p class="name">网站名称：<span id="siteNames"></span></p>
	                    <p class="zhuban">主办单位：<span id="directors"></span></p>
	                </div>
	                <div class="zck-time">
	                    <p class="time">通知时间：<span id="requireTimes"></span></p>
	                    <p class="mail">通知邮箱：<span id="linkmanNames"></span>&nbsp;<span id="email2s"></span> <span id="principalNames"></span>&nbsp;<span id="emails"></span></p>
	                    <p class="yao">整改要求：<span id="noticeRequirements"></span></p>
	                    <p class="qixian">整改期限：<span id="endTimes"></span></p>
	                </div>
	                <div class="zck-info">
	                    <p class="feedback">整改问题：<span id="questionNum"></span>&nbsp;&nbsp;个</p>
	                    <p class="feedback">整改反馈：<span id="noticeResponses"></span></p>
	                    <p class="report">整改报告：<a href="javascript:void(0);" id="reports"></a></p>
	                    <input type="hidden" id="fid" name="fid">
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
	<!-- 整改通知end -->
	
	 <!--批量发送整改通知  提示Modal  start-->
    <div class="page-modal modal fade hide t_prompt-modal" id="t_prompt" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 570px; left: 50%; margin-left: -285px;">
        <div class="t_p-head clearfix">
            <div type="button" class="close green_head_closeicon"
                 data-dismiss="modal" aria-hidden="true">
            </div>
            <h4 class="fl" id="myModalLabel">
                提示
            </h4>
        </div>
        <div class="t_p-content">
            <p class="tip_txt">确定发送整改通知给该页面所勾选的填报单位？</p>
            <div class="clearfix time-limit_part">
                <span class="fl">反馈截止日期：</span>
                <div class="fl time-inp">
                    <input type="text" placeholder="请选择整改期限" id="noticeList_date"/>
                </div>
            </div>
            <p class="allowed_part" id="allowed_part">
                <input type="checkbox" id="noticeList_isAllow"/>允许被抽查单位的上级查看此通知
            </p>
        </div>
      <!--   <div class="t_p-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
            <div class="ensure fr" >确定</div>
        </div> -->
        
         <div class="modal-footer">
               <button type="button" class=" btn-default green-btn" id="noticeAddList" data-dismiss="modal">
               	确定
               </button>
               <button type="button" class="btn btn-default white-btn" data-dismiss="modal">
               	取消
               </button>
	       </div>
    </div>
    <!--批量发送整改通知  提示Modal  start-->
<script type="text/javascript" src="<%= path %>/js/wordList/requestReport.js"></script>
<script type="text/javascript" src="<%=path%>/js/servicePeriod/websiteListNew.js"></script>
<script language="javascript" type="text/javascript">

</script>
</body>
</html>
