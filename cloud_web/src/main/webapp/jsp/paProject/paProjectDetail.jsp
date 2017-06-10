<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>网站考评</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<link rel="stylesheet" href="<%=path%>/css/wpe_AppraisalTask.css"/>
</head>
<body>
<input id="projectId" type="hidden" value="${projectId}">
<input id="taskId" type="hidden" value="${taskId}">
<input id="appraisalId" type="hidden">
<div class="page-wrap bg_fff">
<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	<c:set var="ba_index" value="29" scope="request" />
	<c:set var="menu" value="#menuJiOrg" scope="request" />
	<%@ include file="/common/leftmenu.jsp"%>
          <div class="page-content appraisal-task" style="padding-top: 25px;">
			<!--考评任务详情头部title开始  appraisal-task_details-top-->
            <div class="task-top clearfix">
                <div class="top-title fl">
                    ${taskName}
                </div>
                <div class="fr">
                    <i class="return"  onclick="goback()"></i>
                </div>
            </div>
            <!--考评任务详情头部title开始  appraisal-task_details-top-->

            <!--考评任务详情 详细列表部分开始-->
            <div class="details-list-part">
                <i></i>
                <div class="project">
                    <div>
                        <span>考评项目：</span>
                        ${projectName }
                    </div>
                </div>
                <div class="period_state clearfix">
                    <div class="fl">
                        <span>自评周期：</span>
                        ${startDate }至${endDate }
                    </div>
                     <div class="fl state">
                        <span>考评状态：</span>
                        <c:if test="${stauts==1}">未启动</c:if>
                        <c:if test="${stauts==2}">进行中</c:if>
                        <c:if test="${stauts==3}">已完成</c:if>
                        
                    </div>
                </div>
            </div>
            <!--考评任务详情 详细列表部分结束-->
            <!--考评任务详情 详细table部分开始-->
            <div class="details-table-part">
                <div class="three-tab-top">
                    <div class="three-part clearfix" id="getName">
                    </div>
                </div>
                <div class="table-box">
                    <div class="table-box_top clearfix">
                        <div class="fl sites-num">
                            站点数：
                            <span class="colo-fb6e52" id="infos"></span>
                        </div>
  <!--                       <div class="fl select-box">
                            <select name="" id="selectt">
                                <option value="">考评状态</option>
                                <option value="3">未考评</option>
                                <option value="2">考评中</option>
                                <option value="1">考评完成</option>
                            </select>
                        </div> -->
                        <div class="fl search-box">
                            <input type="text" id="siteOrName" placeholder="输入网站名称或标识码"/>
                            <i></i>
                        </div>
                        <div onclick="lookIndor()" class="fl" style="margin-left:30px; font:normal 14px 'Microsoft YaHei','微软雅黑'; color:#54c3f2; text-decoration:underline; cursor:pointer; height:30px;line-height:30px;">
                        	查看考评指标
                        </div>
                        
                         <div class="fr ziping-sendback" onclick="backRatingWord()">
                          
                            自评退回
                        </div>
                        
                        <div class="fr down_load" onclick="downWord()">
                            <i></i>
                            考评报告
                        </div>
                        <div class="fr down_load" onclick="downRatingWord()" style=" margin-right:20px;">
                            <i></i>
                            自评报告
                        </div>
                    </div>
                    <div class="table-part change_box on">
                        <table>
                            <thead>
                            <tr>
                                <th class="text-center w4p">
                                    <input type="checkbox" id="zong" name="zong"/>
                                </th>
                                <th class="text-center w5p">序号</th>
                                <th class="text-left w10p">网站标识码</th>
                                <th class="text-left w15p">网站名称</th>
                                <th class="text-center w10p">自评状态</th>
                                <!-- <th class="text-center w10p">考评状态</th> -->
                                <th class="text-center w15p">操作</th>
                            </tr>
                            </thead>
                            <tbody id="targets">
                               
                            </tbody>
                        </table>
                    </div>
                    
                    </div>
                </div>
             <%@ include file="/common/footer.jsp"%>	
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>
<script type="text/javascript" src="<%=path%>/js/paProject/paProjectDetail.js"></script> 
<script language="javascript" type="text/javascript">
$(function(){


    
    var tableWidth=$("#table-SSHXSort").parent(".lb_table").width();
    var tableTop=document.getElementById("table-SSHXSort").offsetTop;
    if(tableWidth>0){
        $("#tableWidth").val(tableWidth);
    }
    if(tableTop>0){
        $("#tableTop").val(tableTop);
    }
    $("#table-SSHXSort").width($("#tableWidth").val());
    var tabHeaderTop=$("#tableTop").val()*1-105;
    $(window).scroll(function (e) {
        if(tabHeaderTop < $(window).scrollTop()){
            if(tableTitleClass==0){
                $("#table-SSHXSort").addClass("view-head-fixed");
            }else{
                $("#table-SSHXSort").addClass("view-head-fixed_two");
            }
        }else{

            if(tableTitleClass==0){
                $("#table-SSHXSort").removeClass("view-head-fixed");
            }else{
                $("#table-SSHXSort").removeClass("view-head-fixed_two");
            }

        }
        return false;
    });

}) 
</script>
</body>
</html>
