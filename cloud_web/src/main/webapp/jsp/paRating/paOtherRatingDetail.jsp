<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>网站绩效考评-网站自评</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<link rel="stylesheet" href="<%=path%>/css/wpe_AppraisalTask.css"/>
</head>
<body style=" background: #ccc;">
<input type="hidden" id="taskId" value="${taskId}">
<input type="hidden" id="paTargetTaskId" value="${paTargetTaskId}">
<!--自评page开始-->
<div class="self-assess-wrap" id="self-assess-wrap">
    <div class="self-assess-top clearfix">
        <div class="fl title">${siteName}考评</div>
        <div class="fr close_icon"  onclick="closePage()"></div>
    </div>
    <div class="self-assess-content">
        <div class="self-assess-table_part">
<!--             <div class="table_part_title clearfix">
                <div class="fl people_name">
                    <span>填报人：</span><span id="siteName"></span>
                </div>
                <div class="fl gove_name">
                    <span>单位名称：</span><span id="companyName"></span>
                </div>
                <div class="fl phone_num">
                    <span>联系手机：</span><span id="phone"></span>
                </div>
            </div> -->
            <table id="tables">
                <thead>
                    <tr>
                        <th class="text-center w12p">一级指标</th>
                        <th class="text-center w12p">二级指标</th>
                        <th class="text-center w12p">三级指标</th>
                        <th class="text-center w10p">绩效评分</th>
                        <th class="text-center w10p">问题类型</th>
                        <th class="text-center w15p">打分说明</th>
                        <th class="text-center w15p">栏目网址</th>
                        <th class="text-center w15p">附件及截图</th>
                    </tr>
                </thead>
                <tbody id="tbodyy">
                </tbody>
            </table>
        </div>
    </div>
</div>
<!--自评page结束-->
<script language="javascript" type="text/javascript" src="<%=path%>/js/paRating/paOtherRating.js"></script>
<script>
    window.onload=window.onresize=window.onscroll=function(){
        //var oselt_assess_box=document.getElementById('self-assess-wrap');
        oH_c=document.documentElement.clientHeight||document.body.clientHeight;
        oScrollT=document.body.scrollTop;
        //oW=document.documentElement.clientWidth||document.body.clientWidth;
        //oH_num=oH+'px';
        //alert(oH_num);
        oH=oH_c;
        $('#self-assess-wrap').css('min-height',oH+'px');
    }
</script>
</body>
</html>
