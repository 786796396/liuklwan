<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>网站绩效考评-往期任务</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<link rel="stylesheet" href="<%=path%>/css/wpe_AppraisalTask.css"/>
</head>
<body>
<input type="hidden" id="siteCode" value="${sessionScope.shiroUser.siteCode}">
<div class="page-wrap bg_fff">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	<c:set var="tb_index" value="28" scope="request" />
	<c:set var="menu" value="#menuInfoJi" scope="request" />
	<%@ include file="/common/leftmenu.jsp"%>
          <div class="page-content appraisal-task" style="padding-top: 25px;">
          <input id="paTargetCountId" type="hidden" value="${sessionScope.shiroUser.paTargetCount}">
          <input id="orgToInfoId" type="hidden" value="${sessionScope.shiroUser.orgToInfo}">
            <!--考评任务头部title开始  appraisal-task-top-->
            <div class="task-top">
                <div class="top-title">
                   往期自评任务
                </div>
            </div>
            <!--考评任务头部title结束-->

            <!--考评任务table_list开始 appraisal-table-list-->
 <div class="table-list">
                <div class="table-part">
                    <table>
                        <thead>
                            <tr>
<!--                                 <th class="w5p">
                                    <input type="checkbox"/>
                                </th> -->
                                <th class="text-center">序号</th>
                                <th class="text-left">任务名称</th>
                                <th class="text-left">自评周期</th>
                                <th class="text-center">自评状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody id="ttbody">

                        </tbody>
                    </table>
                </div>
            </div>
            <!--消息设置内容部分结束-->
             <%@ include file="/common/footer.jsp"%>	
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>
<!-- Modal -->
<div class="page-modal modal fade hide report_sm" id="report_sm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 960px; left: 50%; margin-left: -480px;">
    <div class="op_w-head clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl" id="myModalLabel">
        <span id="taskName"></span>自评说明
        </h4>
    </div>

    <div class="rsm-content_part op_w-content"><!--rsm(report_sm)-->
        <div class="rsm-content_box">
            <div class="list-box-c">
                <p>
                    1、 本系统自 <span class="colo-fe6c00">&nbsp;&nbsp;<span id="nian1"></span>年<span id="yue1"></span>月<span id="ri1"></span>日&nbsp;&nbsp;</span>至<span class="colo-fe6c00">&nbsp;&nbsp;<span id="nian2"></span>年<span id="yue2"></span>月<span id="ri2"></span>日&nbsp;&nbsp;</span>，接受自评自查申报。逾期不再接受申报；
                </p>
                                <p>
                    2、 请参照评估指标和评分细则，对每个三级指标项（若无三级指标则指二级指标）按权重值进行自查评分。
                </p>
                <p>
                    3、 每次可以填报部分指标，点击保存后下次继续填报，点击&nbsp;&nbsp;<span class="colo-b219de under_L cur_P" onclick="getIndictor()">自评指标</span>&nbsp;&nbsp;预览指标；
                </p>
                <p>
                    4、填写完成后请点击"提交数据"按钮正式提交。正式提交前可以反复修改，提交后不可以修改。<span class="colo-fe6c00"><span id="nian3"></span>年<span id="yue3"></span>月<span id="ri3"></span>日24时</span>前需完成 
                </p>
                <p class="txt_indent">
                   正式提交，未提交则不可继续填报。
                </p>
                <p>
                    5、 填报时应尽可能准确、全面填写，否则将影响年终评分工作。
                </p>
            </div>
            <div class="title_inp clearfix">
                <div class="fl tit">
                    <i class="red-mi"></i>
                   填报人：
                </div>
                <div class="fl right_inp">
                    <input type="text" id="ratingName"/>
                </div>
            </div>
            <div class="contact_inp clearfix">
                <div class="fl dwmc">
                    <div class="fl tit">
                        <i class="red-mi"></i>
                        单位名称：
                    </div>
                    <div class="fl right_inp">
                        <input type="text" id="siteName"/>
                    </div>
                </div>
                <div class="fr phone_num">
                    <div class="fl tit">
                        <i class="red-mi"></i>
                        手机：
                    </div>
                    <div class="fl right_inp" style="position:relative;">
                        <input type="text" id="ratingPhone" />
                       <span id="phoneSpan" style="position:absolute; right:34px; bottom:-24px;color: red; font:normal 12px '微软雅黑';">(如:010-82350961或18610070037)</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="op_w-footer clearfix">
    	<span style="height:35px; font:normal 14px/35px '微软雅黑'; color:red; margin-left:20px;">为达到更好的浏览和使用效果，建议您使用IE8以上、谷歌、火狐浏览器。</span>
        <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
        <div class="ensure fr" onclick="subRating()" id="startT">开始填报</div>
    </div>
</div>
<!-- /Modal -->
<script type="text/javascript" src="<%=path%>/js/paTask/paTaskInfo.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.tips.js"></script>
<!-- <script language="javascript" type="text/javascript">
$(function(){

    $('.past_operate .begin_p').click(function(){
       /* if($(this).attr('id')){
            $(this).attr('id','self-report_sm');
        }else{
            $(this).attr('id','self-report_sm');
        }*/
        $(this).attr({'data-toggle':'modal','data-target':'#report_sm'});
    });

}) 
</script> -->
</body>
</html>
