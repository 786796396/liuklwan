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
<body class="bg_f5f5f5">
  <!--头部       satrt  -->
  <%@ include file="/common/top.jsp"%>
  <!--头部       end  -->
<div class="main-container container">
	<div class="row-fluid">
	<!--左侧导航       satrt  -->
	<c:set var="ba_index" value="7" scope="request"/>
	<c:set var="menu" value="#" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	
		
       <!--  <iframe class="main_iframe" src="../common/leftmenu.html" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="225" style="position:absolute; height:100%; z-index:10;"></iframe> -->
        <div class="page-content notice_z">
            <!--需要填充的内容开始-->
            <div class="title">
                通知
            </div>
            <div class="result_part clearfix">
                <div class="fl result_data">
                    查询结果：<span id="sumNumNotice">0</span>条
                </div>
                <div class="fl search_box">
                    <input type="text" id="topicSearch"  placeholder="请输入主题"/>
                    <i onclick="getNoticeSender()"></i>
                </div>
                <div class="fl release_date">
                    <span class="fl release_date_tit">发布日期：</span>
                    <div class="clearfix input_box">
                        <div class="fl">
                            <input type="text" id="publishDateStart"/>
                        </div>
                        <span class="fl">至</span>
                        <div class="fl">
                            <input type="text" id="publishDateEnd"/>
                        </div>
                    </div>
                </div>
                <div class="fr create" data-toggle="modal" data-target="#create" onclick="openCreateNotice()">
                    创建
                </div>
            </div>
            <div class="table_part" id="index_org-tab">
                <table>
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 5%">序号</th>
                            <th class="text-left" style="width: 15%">主题</th>
                            <th class="text-left" style="width: 20%">内容</th>
                            <th class="text-left" style="width: 15%">附件</th>
                            <th class="text-left" style="width: 10%">发布日期</th>
                            <th class="text-center" style="width: 10%">反馈</th>
                            <th class="text-center" style="width: 15%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="noticeSenderContentId">
 
                        <!-- <tr>
                            <td class="text-center num">5</td>
                            <td class="text-left">关于国办抽查通知</td>
                            <td class="text-left">国办抽查，请做好网站整改工作。</td>
                            <td class="text-left">关于国办抽查通知.doc</td>
                            <td class="text-left">未发布</td>
                            <td class="text-center">--</td>
                            <td class="text-center">
                                <span>删除</span>
                                <span>修改</span>
                                <span>发布</span>
                            </td>
                        </tr> -->
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
        <h4 class="fl" id="title">
            创建通知
        </h4>
    </div>
    <div class="create_m-content">
    <form id="formUpload" action="" method="post" enctype="multipart/form-data">
      <input type="hidden" id="id" name="id"/>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                发布单位：
            </div>
            <div class="fl right_inp show_div">
                <%-- <input type="text" value="${sessionScope.shiroUser.userName }"  readonly="true"/> --%>
                ${sessionScope.shiroUser.userName }
            </div>
        </div>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                发布主题：
            </div>
            <div class="fl right_inp">
                <input type="text" id="topic" name="topic"/>
            </div>
        </div>
        <div class="contact_inp clearfix margb-17">
            <div class="fl tit">
                <!-- <i class="red-mi"></i>-->
               发布内容：
            </div>
            <div class="fl right_inp">
                <textarea name="contents" id="contents" style="resize: none;"></textarea>
            </div>
        </div>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                附件：
            </div>
            <div class="fl right_inp">
                <input type="text" disabled="disable"  id="affixName" name="affixName"/>
            </div>
            <div class="fl">
               <!--  上传附件 -->
             	 
                 	<!-- <input type="file" placeholder="上传附件" name="noticeReport" id="noticeReport" onchange="changeFile()" value="上传附件"/> -->
                 	
                 	<a href="#" class="file">
                 		<input type="file" placeholder="上传附件" name="noticeReport" id="noticeReport"  value="上传附件"/>上传附件
      				</a>
                 	
      
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
                                <input type="radio" name="isFeedback" checked="checked" value='1' id="isFeedbackTrue"/>
                                是
                            </label>
                        </div>
                        <div class="fl no">
                            <label>
                                <input type="radio" name="isFeedback" value='0' id="isFeedbackFalse" />
                                否
                            </label>
                        </div>
                    </div>
                </div>
                <div class="date_bottom clearfix">
                    <div class="fl margr-17 text_tit">反馈截止日期：</div>
                    <div class="fl time_inp">
                        <input type="text" name="feedDeadlineDate" id="feedDeadlineDate"/>
                    </div>
                </div>
            </div>
        </div>
        <!-- <div class="create_m-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
            <div class="ensure fr release">直接发布</div>
            <div class="ensure save fr">保存</div>
        </div> -->
        <div class="modal-footer">
                <button type="button" class=" btn-sucess green-btn"
                        data-dismiss="modal" id="noticeUp">确定
                </button>
                <button type="button" class="btn white-btn"
                        data-dismiss="modal">取消
                </button>
            </div>
        </form>
    </div>
</div>
<!--创建通知modal结束-->
<!--查看modal开始-->
<div class="page-modal modal fade hide create_modal" id="view" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 900px; left: 50%; margin-left: -450px;">
    <div class="create_m_top clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl" id="title">
            查看通知
        </h4>
    </div>
    <div class="create_m-content">
      <input type="hidden" id="id" name="id"/>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                发布单位：
            </div>
            <div class="fl  show_div boderb" style="width: 430px;">
                <%-- <input type="text" value="${sessionScope.shiroUser.userName }"  readonly="true"/> --%>
                ${sessionScope.shiroUser.userName }
            </div>
        </div>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                发布主题：
            </div>
            <div class="fl show_div boderb" id="topicDivId" style="width:430px; line-height:24px; margin-top:8px; height:auto;">
                <!-- <input type="text" id="topic" name="topic"/> -->
            </div>
        </div>
        <div class="contact_inp clearfix margb-17">
            <div class="fl tit">
               发布内容：
            </div>
            <div class="fl show_div report_content" id="contentsDivId"  style="font-family:'Microsoft YaHei','微软雅黑';">
                <!-- <textarea name="contents" id="contents" style="resize: none;"></textarea> -->
            </div>
        </div>
        <div class="title_inp clearfix margb-17">
            <div class="fl tit">
                <!--<i class="red-mi"></i>-->
                附件：
            </div>
            <div class="fl  show_div boderb" id="affixNameDivId" style="width:430px;">
               <!--  <input type="text" disabled="disable"  id="affixName" name="affixName"/> -->
            </div>
            <div class="fl">
               <!--  上传附件 -->
               	 
                 	<!-- <input type="file" placeholder="上传附件" name="noticeReport" id="noticeReport" onchange="changeFile()" value="上传附件"/> -->
      
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
                                <input type="radio" id="isFeedbackTrueDiv" />
                                是
                            </label>
                        </div>
                        <div class="fl no">
                            <label>
                                <input type="radio" id="isFeedbackFalseDiv" />
                                否
                            </label>
                        </div>
                    </div>
                </div>
                <div class="date_bottom clearfix">
                    <div class="fl margr-17 text_tit">反馈截止日期：</div>
                    <div class="fl time_inp">
                        <input type="text" name="feedDeadlineDateDivId" id="feedDeadlineDateDivId" readonly/>
                    </div>
                </div>
            </div>
        </div>
        <!-- <div class="create_m-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
            <div class="ensure fr release">直接发布</div>
            <div class="ensure save fr">保存</div>
        </div> -->
       <!-- <div class="modal-footer">
                <button type="button" class=" btn-sucess green-btn"
                        data-dismiss="modal" id="noticeUp">确定
                </button>
                <button type="button" class="btn white-btn"
                        data-dismiss="modal">取消
                </button>
        </div> -->
    </div>
</div>
<!--查看modal结束-->
<%-- <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/leftmenu.js"></script> --%>
<!-- <script type="text/javascript" src="../../js/common.js"></script> -->
<!-- <script type="text/javascript" src="../../js/dropload.min.js"></script>加载分页 -->
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/dropload.min.js"></script>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/notice/indexOrg.js"></script>

<script language="javascript" type="text/javascript">
$(function(){
    /*该页面用到的js*/
    $(".baobei_table_part table tr:odd").css("background","#f7faff");
    $(".baobei_table_part table tbody tr:odd").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","");
    });

    $(".baobei_table_part table tbody tr:even").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","#f7faff");
    });

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
	
	nameHover("index_org-tab");
})
</script>
</body>
</html>
