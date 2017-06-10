<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>组织单位抽查</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
    <link rel="stylesheet" href="<%= path %>/css/zzchouc.css"/>
    <link rel="stylesheet" href="<%= path %>/css/base.css"/>
</head>
<body>
<%-- form提示下载文件 --%>
<form id="dowForm" enctype="application/x-www-form-urlencoded" name="dowForm" action="<%=path%>/downloadTemp_downFile.action" method="post">
	<input type="hidden" id="filePath" name="filePath"></input>
	<input type="hidden" id="fileName" name="fileName"></input>
</form>
<div class="page_wrap">
    <%@ include file="/common/top.jsp"%>
    <div class="main-container container ">
        <div class="row-fluid">
           	<c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
				<c:set var="ba_index" value="10" scope="request"/>
				<c:set var="menu" value="#menuJc" scope="request"/>
			</c:if>
			<c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
      			<c:if test='${((fn:length(sessionScope.shiroUser.siteCode))==6 && (fn:indexOf(sessionScope.shiroUser.siteCode,"0000"))>=0) || (fn:startsWith(sessionScope.shiroUser.siteCode,"bm0100"))}'>
					<c:set var="ba_index" value="9" scope="request"/>
					<c:set var="menu" value="#" scope="request"/>
				</c:if>
			</c:if>
			<%@ include file="/common/leftmenu.jsp"%>
            <div class="page-content">
               <div class="title_box clearfix">
                   <c:if test='${sessionScope.shiroUser.siteCode ne "440000"}'>
                   <h3 class="title  fl">网站抽查</h3>
                   </c:if>
                   <c:if test='${sessionScope.shiroUser.siteCode eq "440000"}'>
                   <h3 class="title  fl">网站考评</h3>
                   </c:if>
                   <span class="fl"> >&nbsp; &nbsp;站点数明细</span>
               </div>
               <input type="hidden" id="batchNum_id" value="${batchNum }">
               <input type="hidden" id="groupNum_id" value="${groupNum }">
               <input type="hidden" id="flagAll" value="${flagAll }">
               <div class="center_content bg-fff">
                   <!--头部开始-->
                   <div class="tab_head clearfix">
                       <div class="batch fl" id="batch_id"></div>
                       <div class="group fl" id="group_id"></div>
                       <div class="number fl" id="number_id"></div>
                       <div class="state fl" id="state_id"></div>
                       <div class="report fr green" style="cursor:pointer" id="excel_out_id">
	                       <i class="icon_report"></i>
	                       <span>导出列表</span>
                       </div>
                       <div class="all fr green" style="cursor:pointer" id="start_all_id">
	                       <i class="icon_all"></i>
	                       <span>全部启动</span>
                       </div>
                       <div class="add fr green" style="cursor:pointer" id="add_site_id">
                           <i class="icon_add"></i>
                           <span>增加站点</span>
                       </div>
                   </div>
                   <!--头部结束-->

                   <!--任务名称开始-->
                   <div class="tab_taskname clearfix">
                       <div class="task_name fl">
                           <span class="name">任务名称：</span>
                           <span class="city" id="taskName_id"></span>
                       </div>
                       <div class="time_period fr">
                           <span class="time">监测周期：</span>
                           <span class="data" id="data_id">2016-03-01&nbsp;至&nbsp;2016-04-01</span>
                       </div>
                   </div>
                   <!--任务名称结束-->

                   <!--搜索框开始-->
                   <div class="search">
                       <div class="pos-relative">
                           <input type="text"  id="keyWord_id" placeholder="输入网站名称" class="search_box" outline="none"/>
                           <i class="search_icon"></i>
                       </div>
                   </div>
                   <!--搜索框结束-->

                   <!--table_part开始-->
                   <div class="table_part">
                       <div class="tab_firts">
                           <table class="table bg-fff tab" id="result_table_id">
                           		<thead>
                                    <tr>
                                        <!--<th class="th_left">
                                           <div class="check_box">
                                                <input type="checkbox" class="b_check" id="ck1"/>
                                                <label for="ck1"></label>
                                            </div>
                                            
                                        </th>-->
                                        <th class="th_left">网站标识码</th>
                                        <th class="th_left">网站名称</th>
                                        <th class="th_left pos-relative" style="width: 60px;">省/部</th>
                                        <th class="th_left pos-relative" style="width: 60px;">市</th>
                                        <th class="th_left pos-relative" style="width: 60px;">县</th>
                                        <th class="th_left pos-relative">检查状态</th>
                                        <th class="th_left pos-relative">报告状态</th>
<!--                                         <th class="th_left pos-relative">检查结果</th>
                                        <th class="th_left pos-relative">人工核查</th> -->
                                        <th class="th_center pos-relative" style="width: 80px;">报告</th>
                                        <th class="th_center" style="width: 100px;"></th>
                                    </tr>
								</thead>
                               <tbody id="spot_result_tbody_id">
 
                               </tbody>
                           </table>
                       </div>
                   </div>
                   <!--table_part结束-->
               </div>
               <div class="row page-footer">© Copyright 2015. Ucap Info  All Rights Reserve</div>
            </div>
        </div>
    </div>
</div>
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
                <div class="zfs-please-change">
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
<script type="text/javascript" src="<%= path %>/js/spotCheckResult/websiteList.js"></script>
<script>
    $(function(){
        $(".tab tbody tr:odd td").css("background","#fafbfc");
    });
    /* $(function () { 
	    $("#myModal").modal({
	    });
    }); */
    $(function(){
        $(".box1").on("click",function(){
            //alert(1);
            if($(this).hasClass("on_check")){
                $(this).removeClass("on_check");
            }else{
                $(this).addClass("on_check");
            }
            //$(this).hasClass("on_check")?$(this).removeClass("on_check"):$(this).addClass("on_check");
            //或者这么写
            //$(this).toggleClass( "on_check" );
        });
    });
</script>
</body>
</html>
