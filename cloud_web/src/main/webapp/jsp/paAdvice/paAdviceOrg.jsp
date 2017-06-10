<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title></title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<link rel="stylesheet" href="<%=path%>/css/wpe_AppraisalTask.css"/>
</head>
<body>
<input id="appraisalId" type="hidden">
<div class="page-wrap ">
<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="ba_index" value="602" scope="request" />
		<c:set var="menu" value="#menuJiOrg" scope="request" />
		<%@ include file="/common/leftmenu.jsp"%>
          <div class="page-content appraisal-task">
			<!--考评任务详情头部title开始  appraisal-task_details-top-->
            <div class="task-top">
                    <div class="top-title">
                       意见建议
                    </div>
                    <div class="opinion_white">
                        <i></i>
                        意见建议
                    </div>
                </div>
            <!--考评任务详情头部title开始  appraisal-task_details-top-->

            <!--考评任务详情 详细列表部分开始-->
<div class="table-list">
                    <div class="table-part">
                        <table>
                            <thead>
                                <tr>
<!--                                     <th class="text-center w4p">
                                        <input type="checkbox"/>
                                    </th> -->
                                    <th class="text-center w5p">序号</th>
                                    <th class="text-left w10p">标题</th>
                                    <th class="text-center ">内容</th>
                                    <th class="text-center w7p">联系人</th>
                                    <th class="text-center w10p">联系电话</th>
                                    <th class="text-center w10p">操作</th>
                                </tr>
                            </thead>
                            <tbody id="tbodyy">

                               
                            </tbody>
                        </table>
                    </div>
                </div>
             <%@ include file="/common/footer.jsp"%>	
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>
<!-- Modal -->
<div class="page-modal modal fade hide opinion_white-modal" id="opinion_white" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 960px; left: 50%; margin-left: -480px;">
    <div class="op_w-head clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
             <input id="curSitecode" type="hidden" value="${sessionScope.shiroUser.siteCode }"/>
        </div>
        <h4 class="fl" id="myModalLabel">
            添加意见建议
        </h4>
    </div>
    <div class="op_w-content">
        <div class="title_inp clearfix">
            <div class="fl tit">
                <i class="red-mi"></i>
                标题：
            </div>
            <div class="fl right_inp">
                <input type="text" id="adTitle"/>
            </div>
        </div>
        <div class="content_inp clearfix">
            <div class="fl tit">
                <i class="red-mi"></i>
                内容：
            </div>
            <div class="fl right_inp">
               <textarea id="adContext"></textarea>
            </div>
        </div>
        <div class="contact_inp clearfix">
            <div class="fl">
                <div class="fl tit">
                    <i class="red-mi"></i>
                    联系人：
                </div>
                <div class="fl right_inp">
                    <input type="text" id="adLinkPersion"/>
                </div>
            </div>
            <div class="fr phone_num">
                <div class="fl tit">
                    <i class="red-mi"></i>
                    手机：
                </div>
                <div class="fl right_inp">
                    <input type="text" id="adPhone"/>
                </div>
            </div>
        </div>
        <div class="note_part">
            <p class="note">注：我们可能会通过线下方式与您电话沟通，请确保填写的手机号码正确。</p>
        </div>
    </div>
    <div class="op_w-footer clearfix">
        <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
        <div class="ensure fr" onclick="sub()">确定</div>
    </div>
</div>
<!-- /Modal -->

<script type="text/javascript" src="<%=path%>/js/paAdvice/paAdvice.js"></script> 
<script type="text/javascript" src="<%=path%>/js/jquery.tips.js"></script>
</body>
</html>
