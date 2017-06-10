<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>下级整改通知（组织单位）</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" href="<%= path %>/css/zzchouc.css"/>
  </head>
  <body style="position:relative;" class="bg_f5f5f5">
  <!--头部       satrt  -->
  <%@ include file="/common/top_tb.jsp"%>
  <!--头部       end  -->
  <input id="onlineReportUrl" type="hidden" value="${onlineReportUrl}"></input>
<div id="top_opacity_id" style="display:none;position:absolute; top:0; left:0; height:116px; width:100%; background:rgba(100,100,100,0.6);z-index:999;"></div>
<div id="left_opacity_id" style="display:none;position:absolute; top:116px; left:0;width:225px; background:rgba(100,100,100,0.6);z-index:999;"></div>
<div class="main-container container">
		<!--左侧导航       satrt  -->
		<%@ include file="/common/leftmenu.jsp"%>
		<!--左侧导航       end  -->
        <div class="page-content shut-down">
        	<i id="but" class="sd-close-icon" style="display: none" onclick="clodeWindow()"></i>
        	<input id="isScanFlag_id" type="hidden" value="${isScanFlag}">
            <table id="wordList">
            	<tbody>
            	</tbody>
            </table>
            <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
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
                    <p class="name">网站名称：<span id="siteNameW"></span></p>
                    <p class="zhuban">主办单位：<span id="director"></span></p>
                </div>
                <div class="tfk-time">
                    <p class="time">通知时间：<span id="requireTime"></span></p>
                    <p class="yao">整改要求：<span id="noticeRequirement"></span></p>
                </div>
                <div class="tfk-info">
                    <form id="formUpload" action="" method="post" enctype="multipart/form-data">
	                    <div class="ques">
	                        <span>整改问题:</span>
	                        <input type="hidden" name ="noticeId" id="noticeId"/>
	                        <input type="text" name ="questionNum" id="questionNum" value=""/>
	                        <span>个</span>
	                    </div>
	                    <div class="txt">
	                        <div class="p">整改反馈：</div>
	                        <div class="texta">
	                            <textarea name="noticeResponse" id="noticeResponse" placeholder="请填写整改反馈"></textarea>
	                        </div>
	                    </div>
	                    <div class="baogao">
	                        <span>整改报告:</span>
	                        <input type="file" placeholder="上传附件" name="noticeReport" id="noticeReport"/>
	                        <span class="upload">如有详细整改报告，请上传</span>
	                    </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class=" btn-sucess green-btn"
                        data-dismiss="modal" id="noticeUp">确定
                </button>
                <button type="button" class="btn white-btn"
                        data-dismiss="modal">取消
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
                    <!-- <p class="mail">通知邮箱：<span id="linkmanNamesFk"></span>&nbsp;<span id="email2s"></span> <span id="principalNames"></span>&nbsp;<span id="emails"></span></p> -->
                    <p class="yao">整改要求：<span id="noticeRequirementsFk"></span></p>
                    <!-- <p class="qixian">整改期限：<span id="endTimesFk"></span></p> -->
                </div>
                <div class="zck-info">
                    <p class="feedback">整改问题：<span id="questionNumFk"></span></p>
                    <p class="feedback">整改反馈：<span id="noticeResponsesFk"></span></p>
                    <p class="report" style="position: initial;">整改报告：<a href="javascript:void(0);" id="reportsFk"></a><span id="reportsFks"></span></p>
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
<script type="text/javascript" src="<%= path %>/js/wordList/requestReport.js"></script>
<script type="text/javascript" src="<%= path %>/js/wordList/index.js"></script>
</body>
</html>
