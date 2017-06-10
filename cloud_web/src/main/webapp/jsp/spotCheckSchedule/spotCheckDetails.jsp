<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>抽查汇报详情</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/zzchouc.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/wzkp_details.css"/>
<script language="javascript" type="text/javascript" src="<%=path%>/js/connection/con_home_sort.js"></script>
</head>

<body class="bg_f5f5f5">
	<form id="dowForm" enctype="application/x-www-form-urlencoded" name="dowForm" action="<%=path%>/downloadTemp_downFileUrl.action" method="post">
		<input type="hidden" id="filePath" name="filePath"></input>
		<input type="hidden" id="fileName" name="fileName"></input>
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
<input id="endDate" type="hidden" value="${endDate }">
<input type="hidden" id="servicePeriodIds" value="${servicePeriodId }">
<input id="servicePeriodStatus" type="hidden" value="${state }">
<input id="toType" type="hidden" value="${type }">

<input id ="batchNum" type="hidden" value="${batchNum }">
	
   <input value = "${scheduleCheckId} " type="hidden" id= "scheduleCheckId">

	<div class="row-fluid">
	<!--抽查左侧导航       satrt  -->
	<c:if test='${type == 0 }'>
	<c:if test='${sessionScope.shiroUser.siteCode eq "440000" || sessionScope.shiroUser.siteCode eq "440900"}'>
						<c:set var="ba_index" value="302" scope="request"/>
						<c:set var="menu" value="#menuDeJc" scope="request"/>
					</c:if>
					<c:if test='${sessionScope.shiroUser.siteCode ne "440000" && sessionScope.shiroUser.siteCode ne "440900"}'>
	        			<c:if test='${(fn:length(sessionScope.shiroUser.siteCode))==6}'>
							<c:set var="ba_index" value="302" scope="request"/>
							<c:set var="menu" value="menuDeJc" scope="request"/>
						</c:if>
					</c:if>
					<%@ include file="/common/leftmenu.jsp"%>
	</c:if>
					<!--抽查左侧导航       end  -->
	<!--全面检测左侧导航       start  -->				
	<c:if test='${type != 0 }'>
		<c:if test="${fn:length(sessionScope.shiroUser.siteCode)>6}">
				<c:set var="ba_index" value="131" scope="request" />
				<c:set var="menu" value="#" scope="request" />
				<%@ include file="/common/leftmenu_tb.jsp"%>
		</c:if>
		<c:if test="${fn:length(sessionScope.shiroUser.siteCode)<=6}">
				<c:set var="ba_index" value="131" scope="request" />
				<c:set var="menu" value="#" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
		</c:if>		
	</c:if>	
	<!--全面检测左侧导航       end  -->			
				
        <div class="page-content kp" style="padding-top:27px;">
            <div class="kp_title">
                <span class="main_title" id="titleName">抽查汇报</span><!--<i class="point_icon"></i>--><span class="subtitle">>&nbsp;详情</span>
            </div>
            <div class="batch_group-part clearfix">
                <div class="fl batch">第${batchNum }批</div>
                <div class="fl group">第${groupNum }组</div>
                <div class="fl total">(${webCount })个</div>
<!--                 <div class="fl task_num">任务号： ${servicePeriodNum }</div> -->
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
					<c:if test='${type != 0 }'>
					<input id="siteTypeVal" type="hidden" value="0">
					<c:if test='${(fn:length(sessionScope.shiroUser.siteCode))==6}'>
                      </c:if>   
                            
                            </c:if>
                            <c:if test='${type == 0 }'>
							</c:if>
                        </div>
                        <div class="btns_box fr">
                        <c:if test='${type != 0 }'>
                        
                         <c:if test='${reportCollectResultSize != 0 }'>
                       		<div class="all-export fl" onclick="excelOrg()">检测任务报告</div>
                       	</c:if>
                       	<c:if test='${reportCollectResultSize == 0 }'>
                       		<div class="all-export fl unabi">检测任务报告</div>
                       	</c:if>
                        </c:if>
<!--                         	<div class="all-export fl" id="aKeyToReport"  onclick="aKeyToReport()" >一键汇报</div> -->
                            <div class="all-export fl" onclick="excelWebsiteList()">全部导出</div>
<!--                             <div class="all-export fl" id="aKeyToReport"  onclick="aKeyToReport()" >全部下载</div> -->
                            
                        </div>
                    </div>
                    <div class="show-part-table">
                     <table id="table-SSHXSort" style="table-layout: fixed;">
                            <thead>
                                <tr>
                                    <th style="width:90px;"  nowrap>网站标识码/名称</th>
                                    <th style="width:64px;">首页不<br/>连通率<i class="tab_angle"></i></th>
                                    <th style="width:64px;">网站死<br/>链个数<i class="tab_angle"></i></th>
                                    <th style="width:64px;">网站不更<br/>新问题<i class="tab_angle"></i></th>
                                    <th style="width:64px;">空白栏<br/>目个数<i class="tab_angle"></i></th>
                                    <th style="width:64px;">互动回<br/>应问题<i class="tab_angle"></i></th>
                                    <th style="width:64px;">服务不实<br/>用问题<i class="tab_angle"></i></th>
                                    <th class="w50px" style="width:64px;">严重错误<i class="tab_angle"></i></th>
                                    <c:if test='${type != 0 }'>
                                    <th class="w50px" style="width:40px;">检测结果</th>
                                    </c:if>
                                    <th class="w50px" style="width:40px;">报告状态</th>
                                    <th class="w50px" style="width:50px;">查看报告</th>  
                                    <c:if test="${fn:length(sessionScope.shiroUser.siteCode)==6}">
                                    <th class="w50px" style="width:60px;">通知整改</th>
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
</div>
<script type="text/javascript" src="<%= path %>/js/wordList/requestReport.js"></script>
<script type="text/javascript" src="<%=path%>/js/spotCheckSchedule/spotCheckDetails.js"></script>
<script language="javascript" type="text/javascript">

</script>
</body>
</html>
