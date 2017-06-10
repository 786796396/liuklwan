<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
    <title>通知反馈--填报单位</title>
	 <%@ include file="/common/meta.jsp"%>
	 <%@ include file="/common/heade.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/zTreeStyle/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css" />
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/NotificationAndFeedback.css"/>
 </head>
<body class="bg_f5f5f5">
  <!--头部       satrt  -->
 <%@ include file="/common/top_tb.jsp"%>
<div class="main-container container" >
	<div class="row-fluid">
	<!--左侧导航       satrt  -->
	<c:set var="tb_index" value="29" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content notice_z notice_t">
            <!--需要填充的内容开始-->
            <div class="title">
                通知
            </div>
            <div class="result_part clearfix">
                <div class="fl result_data">
                    查询结果：<span id='sumNumNotice'>0</span>条
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

                <div class="fr search_box">
                    <input type="text" placeholder="请输入主题" id="topicSearch"/>
                    <i onclick='getNoticeReceiverFeed()'></i>
                </div>
               <!-- <div class="fr create" data-toggle="modal" data-target="#create">
                    创建
                </div>-->
            </div>
            <div class="table_part">
                <table id="index_R-tab">
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 5%">序号</th>
                            <th class="text-left" style="width: 20%">主题</th>
                            <th class="text-left" style="width: 20%">内容</th>
                            <th class="text-left" style="width: 15%">附件</th>
                            <th class="text-left" style="width: 10%">反馈日期</th>
                            <th class="text-left" style="width: 10%">查看状态</th>
                            <th class="text-center" style="width: 10%">反馈状态</th>
                            <th class="text-center" style="width: 15%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="noticeContentId">
                        <tr>
                            <td class="text-center num" >1</td>
                            <td class="text-left" >关于国办抽查通知</td>
                            <td class="text-left">国办抽查，请做好网站整改工作。</td>
                            <td class="text-left special_state">关于国办抽查通知.doc</td>
                            <td class="text-left">2016-09-19</td>
                             <td class="text-center">已提交</td>
                            <td class="text-center">已提交</td>
                            <td class="text-center">
                                <span class="special_state tzxq">通知详情</span>
                                <span class="special_state t_fk">反馈</span>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
             <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<!--填报单位--通知modal开始-->
<div class="page-modal modal fade hide create_modal" id="t_notice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 900px; left: 50%; margin-left: -450px;">
    <div class="create_m_top clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true" id="closeReceiveButton">
        </div>
        <h4 class="fl">
            通知
        </h4>
    </div>
    <div class="create_m-content">
        <div class="t_notice-part clearfix">
            <div class="fl single_part" style="width:60%;">
                <div class="every_line clearfix">
                    <div class="left-tit fl">发布单位： </div>
                    <div class="right_con fl" id="orgSiteName"></div>
                </div>
                <div class="every_line clearfix">
                    <div class="left-tit fl" >发布主题：</div>
                    <div class="right_con fl" id="topic" style="line-height:24px;width:calc(100% - 135px); margin-top:13px;">国办抽查通知</div>
                </div>
                <div class="every_line clearfix">
                    <div class="left-tit fl">是否需要反馈：</div>
                    <div class="right_con fl" id="isFeedback">是</div>
                </div>
            </div>
            <div class="fl single_part" style="width:40%;">
                <div class="every_line clearfix">
                    <div class="left-tit fl">发布日期：</div>
                    <div class="right_con fl date" id="publishDate">2016-12-19</div>
                </div>
                <div class="every_line clearfix">
                    <div class="left-tit fl">附件：</div>
                    <div class="right_con fl"><span class="special_state" id="affix">国办抽查通知.pdf</span></div>
                </div>
                 <div class="every_line clearfix">
                    <div class="left-tit fl">反馈截止日期：</div>
                    <div class="right_con fl date" id="feedDeadlineDate">2016年12月01日</div>
                </div>
            </div>
        </div>
        
        <div class="t_notice-content-report">
        	<div class="every_line clearfix contents">
                  <div class="left-tit fl ">发布内容：</div>
                  <div class="right_con fl " id="contents">国办要抽查，请及时检查整改网站</div>
            </div>
        </div>
        
        <div class="t_notice-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">关闭</div>
            <!--<div class="ensure fr release">直接发布</div>
            <div class="ensure save fr">保存</div>-->
        </div>
    </div>
</div>
<!--填报单位--通知modal结束-->

<!--填报单位--反馈modalt开始-->
<div class="page-modal modal fade hide create_modal" id="t_feedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 900px; left: 50%; margin-left: -450px;">
    <div class="create_m_top clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl">
            通知反馈
        </h4>
    </div>
    <div class="create_m-content">
      <form id="formUpload" action="" method="post" enctype="multipart/form-data">
       <input type="hidden" id="noticeReceiverId" name="noticeReceiverId"/>
        <div class="t_notice-feedback">
            <div>
                <span>通知</span>
                <div class="every_line clearfix">
                    <div class="left fl">主题： </div>
                    <div class="right fl" id="topicFeedId">国办抽查通知</div>
                </div>
                <div class="every_line clearfix content">
                    <div class="left fl">内容：</div>
                    <div class="right fl" id="contentFeedId">国办要抽查，请及时检查整改网站</div>
                </div>
                <div class="every_line clearfix">
                    <div class="left fl">附件：</div>
                    <div class="right  fl special_state"  id="affixFeedId">国国办抽查通知.pdf</div>
                </div>
            </div>
            <div class="t_fk-box">
                <span>反馈</span>
                <div class="t_fk-content">
                    <div class="content clearfix">
                        <div class="tit fl">内容：</div>
                        <div class="fl">
                            <textarea name="feedContents" id="feedContents"></textarea>
                        </div>

                    </div>
                    <div class="attach clearfix">
                        <div class="tit fl">附件：</div>
                        <div class="fl">
                             <input type="text" class="fl"  id="feedAffixName" name="feedAffixName" disabled="disable" />
                             	 <a href="#" class="file fl">
		                 		<input type="file" placeholder="上传附件" name="noticeReport" id="noticeReport"  value="上传附件"/>上传附件
		      				</a>
                        </div>
                        <!--   <div class="fl scfj">
                          
                        </div> -->
               
                    </div>
                </div>
            </div>
        </div>
       <!--  <div class="t_notice-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
           <div class="ensure fr release">直接发布</div>
            <div class="ensure save fr">提交</div>
        </div> -->
        
        <div class="modal-footer">
                <button type="button" class=" btn-sucess green-btn"
                        data-dismiss="modal" id="noticeUp">提交
                </button>
                <button type="button" class="btn white-btn"
                        data-dismiss="modal">取消
                </button>
          </div>
        </form>
    </div>
</div>
<!--填报单位--反馈modal结束-->

<!--填报单位--chakanmodalt开始-->
<div class="page-modal modal fade hide create_modal" id="t_look" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 900px; left: 50%; margin-left: -450px;">
    <div class="create_m_top clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl">
            通知反馈查看
        </h4>
    </div>
    <div class="create_m-content">
        <div class="t_notice-feedback">
            <div>
                <span>通知</span>
                <div class="every_line clearfix">
                    <div class="left fl">主题： </div>
                    <div class="right fl" id="topicFeedIdLook">国办抽查通知</div>
                </div>
                <div class="every_line clearfix content">
                    <div class="left fl">内容：</div>
                    <div class="right fl " id="contentFeedIdLook">国办要抽查，请及时检查整改网站</div>
                </div>
                <div class="every_line clearfix">
                    <div class="left fl">附件：</div>
                    <div class="right  fl special_state"  id="affixFeedIdLook">国国办抽查通知.pdf</div>
                </div>
            </div>
            <div>
                <span>反馈</span>
                <div class="every_line clearfix content" style="margin-top:13px;">
                    <div class="left fl">内容：</div>
                    <div class="right fl " id="feedContentsLook">国办要抽查，请及时检查整改网站</div>
                </div>
                <div class="every_line clearfix">
                    <div class="left fl">附件：</div>
                    <div class="right  fl special_state"  id="feedAffixNameLook">国国办抽查通知.pdf</div>
                </div>
            </div>
            
           <!--  <div class="t_fk-box">
                <span>反馈</span>
                <div class="t_fk-content">
                    <div class="content clearfix">
                        <div class="tit fl">内容：</div>
                        <div class="fl" id="feedContentsLook">
                            <textarea name="feedContents" id="feedContents"></textarea>
                        </div>

                    </div>
                    <div class="attach clearfix">
                        <div class="tit fl">附件：</div>
                        <div class="fl" id="feedAffixNameLook">
                        附件名称
                             <input type="text" class="fl"  id="feedAffixNameLook"/>
                             	 <a href="#" class="file fl">
		                 		<input type="file" placeholder="上传附件" name="noticeReport" id="noticeReport" onchange="changeFile()" value="上传附件"/>上传附件
		      				</a>
                        </div>
                          <div class="fl scfj">
                          
                        </div>
               
                    </div>
                </div>
            </div> -->
        </div>
       <!--  <div class="t_notice-footer clearfix">
            <div class="cancel fr" data-dismiss="modal" aria-hidden="true">取消</div>
           <div class="ensure fr release">直接发布</div>
            <div class="ensure save fr">提交</div>
        </div> -->
        
       <!--  <div class="modal-footer">
                <button type="button" class=" btn-sucess green-btn"
                        data-dismiss="modal" id="noticeUp">提交
                </button>
                <button type="button" class="btn white-btn"
                        data-dismiss="modal">取消
                </button>
          </div> -->
    </div>
</div>
<!--填报单位--查看modal结束-->
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/leftmenu.js"></script>
<!-- <script type="text/javascript" src="../../js/common.js"></script> -->
<!-- <script type="text/javascript" src="../../js/dropload.min.js"></script>加载分页 -->
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/dropload.min.js"></script>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/notice/indexReceiver.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
    /*该页面用到的js*/
    $(".notice_t table tr:odd").css("background","#f7faff");
   /* $(".notice_z table tbody tr:odd").hover(function(){
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

    $('.tzxq').click(function(){
        //alert(1);
        $(this).attr({'data-toggle':'modal','data-target':'#t_notice'});
    });

    $('.t_fk').click(function(){
        //alert(1);
        $(this).attr({'data-toggle':'modal','data-target':'#t_feedback'});
    });
    
     $('.t_ck').click(function(){
        //alert(1);
        $(this).attr({'data-toggle':'modal','data-target':'#t_look'});
    });
    
    nameHover("index_R-tab"); 
})


</script>
</body>
</html>
