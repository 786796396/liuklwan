<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    <title>通知反馈--组织单位</title>
	 <%@ include file="/common/meta.jsp"%>
	 <%@ include file="/common/heade.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/zTreeStyle/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css" />
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/NotificationAndFeedback.css"/>
 </head>
<body>
   <!--头部       satrt  -->
 <%@ include file="/common/top.jsp"%>
 <input id ="noticeSenderId" type="hidden" value="${noticeSenderId}"/>

<div class="main-container container" >
	<div class="row-fluid">
	<!--左侧导航       satrt  -->
	<c:set var="ba_index" value="31" scope="request"/>
	<c:set var="menu" value="#" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	
        <div class="page-content notice_z notice-list_z">
            <!--需要填充的内容开始-->
            <div class="top_title">
                <span class="level_o">通知&nbsp;>&nbsp;</span>关于国办抽查
            </div>
            <div class="result_part clearfix">
                <div class="fl result_data margr-none">
                    通知网站总数：<span id="receiverSumCount">0</span>条
                </div>
                <div class="fl result_data">
                    已反馈网站数：<span id="feedCount">0</span>个
                </div>
                <div class="fl search_box">
                    <input type="text" placeholder="请输入网站标识码或名称" id="siteSearch"/>
                    <i onclick="getNoticeFeedBack()"></i>
                </div>
                <div class="fr release_date">
                    <span class="fl release_date_tit">反馈日期：</span>
                    <div class="clearfix input_box">
                        <div class="fl">
                            <input type="text" id="feedDateStart"/>
                        </div>
                        <span class="fl">至</span>
                        <div class="fl">
                            <input type="text" id="feedDateEnd"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table_part">
                <table id="index_org_feed-tab">
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 5%">序号</th>
                            <th class="text-left" style="width: 10%">网站标识码</th>
                            <th class="text-left" style="width: 15%">网站名称</th>
                            <th class="text-left" style="width: 15%">反馈内容</th>
                            <th class="text-left" style="width: 10%">附件</th>
                            <th class="text-center" style="width: 10%">反馈时间</th>
                            <th class="text-center" style="width: 15%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="noticeReceiverContentId">
                    </tbody>
                </table>
            </div>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<!--创建通知modal开始-->
<div class="page-modal modal fade hide create_modal" id="create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 900px; left: 50%; margin-left: -450px;">
    <div class="create_m_top clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl">
            创建通知
        </h4>
    </div>
    <div class="create_m-content">
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                发布单位：
            </div>
            <div class="fl right_inp">
                <input type="text"/>
            </div>
        </div>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                发布主题：
            </div>
            <div class="fl right_inp">
                <input type="text"/>
            </div>
        </div>
        <div class="contact_inp clearfix margb-17">
            <div class="fl tit">
                <!-- <i class="red-mi"></i>-->
               发布内容：
            </div>
            <div class="fl right_inp">
                <textarea name="" id="" style="resize: none;"></textarea>
            </div>
        </div>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                附件：
            </div>
            <div class="fl right_inp">
                <input type="text"/>
            </div>
            <div class="report fl">
                上传附件
            </div>
        </div>
        <div class="setting_part clearfix">
            <div class="fl tit">
                <!-- <i class="red-mi"></i>-->
                设置：
            </div>

            <div class="fl right_inp">
                <div class="radio_top clearfix">
                    <div class="fl margr-17 text_tit">是否开通下级反馈：</div>
                    <div class="radio_parts clearfix">
                        <div class="fl sure">
                            <label>
                                <input type="radio" name="yn"/>
                                是
                            </label>
                        </div>
                        <div class="fl no">
                            <label>
                                <input type="radio" name="yn"/>
                                否
                            </label>
                        </div>
                    </div>
                </div>
                <div class="date_bottom clearfix">
                    <div class="fl margr-17 text_tit">反馈截止日期：</div>
                    <div class="fl time_inp">
                        <input type="text"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="create_m-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
            <div class="ensure fr release">直接发布</div>
            <div class="ensure save fr">保存</div>
        </div>
    </div>
</div>

<!-- <script type="text/javascript" src="../../js/common.js"></script> -->
<!-- <script type="text/javascript" src="../../js/dropload.min.js"></script>加载分页 -->
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/dropload.min.js"></script>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/notice/indexOrgFeed.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
    /*该页面用到的js*/
    $(".notice_z table tr:odd").css("background","#f7faff");
    /*$(".notice_z table tbody tr:odd").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","");
    });

    $(".notice_z table tbody tr:even").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","#f7faff");
    });*/

	$(window).scroll(function () {
		if ($(window).scrollTop() > 100) {
			$("#backToTop").fadeIn(500);
		} else {
			$("#backToTop").fadeOut(500);
		}
	});
	$("#backToTop").click(function () {
		$('body,html').animate({scrollTop: 0}, 600);
		return false;
	});
	
	nameHover("index_org_feed-tab");
});


</script>
</body>
</html>
