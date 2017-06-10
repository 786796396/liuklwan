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
<div class="page-wrap">
	<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	<c:set var="ba_index" value="601" scope="request" />
	<c:set var="menu" value="#menuJiOrg" scope="request" />
<%@ include file="/common/leftmenu.jsp"%>
          <div class="page-content appraisal-task">
            <!--考评任务头部title开始  appraisal-task-top-->
            <div class="task-top">
                <div class="top-title">
                   网站绩效考评任务
                </div>
            </div>
            <!--考评任务头部title结束-->

            <!--考评任务table_list开始 appraisal-table-list-->
            <div class="table-list">
                <div class="table-part">
                    <table>
                        <thead>
                            <tr>
                               <!--  <th>
                                    <input type="checkbox"/>
                                </th> -->
                                <th class="text-center">序号</th>
                                <th class="text-left">任务名称</th>
                                <th class="text-left">自评周期</th>
                                <th class="text-left">考评项目</th>
                                <th class="text-center">任务状态</th>
                                <th class="text-left">操作</th>
                            </tr>
                        </thead>
                        <tbody id="paTable">
                           
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
<input id="paTargetCountId" type="hidden" value="${sessionScope.shiroUser.paTargetCount}">
<script type="text/javascript" src="<%=path%>/js/paProject/paProjectList.js"></script>
<script language="javascript" type="text/javascript">
$(function(){

	/*$(".select").each(function(){
     var s=$(this);
     var z=parseInt(s.css("z-index"));
     var dt=$(this).children("dt");
     var dd=$(this).children("dd");
     var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
     var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
     dt.click(function(){dd.is(":hidden")?_show():_hide();});
     dd.find("li").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
     $("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
     })*/
    $('.change_tab').click(function(){
        $('.change_tab').removeClass('on');
        $(this).addClass('on');
        var n=$(this).index();
        $('.change_tab_content').hide();
        $('.change_tab_content').eq(n).show();
    });

}) 
</script>
</body>
</html>
