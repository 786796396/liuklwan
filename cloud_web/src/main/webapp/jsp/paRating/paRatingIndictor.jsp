<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>网站考评指标体系</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<link rel="stylesheet" href="<%=path%>/css/wpe_AppraisalTask.css"/>
</head>
<body style=" background: #ccc;">
<input type="hidden" id="taskId" value="${taskId}">
<input type="hidden" id="appraisalId" value="${appraisalId}">
<!--自评page开始-->
<div class="self-assess-wrap" id="self-assess-wrap">
    <div class="self-assess-top clearfix">
        <div class="fl title"><span id="taskName"></span>（<span id="appName"></span>）</div>
        <div class="fr close_icon" onclick="closePage()"></div>
    </div>
    <div class="self-assess-content">
        <div class="self-assess-table_part">

            <table id="tables">
                <thead>
                    <tr>
                        <th class="text-center w12p">一级指标</th>
                        <th class="text-center w12p">二级指标</th>
                        <th class="text-center w12p">三级指标</th>
                        <th class="text-center w10p">权重</th>
                        <th class="text-center w15p">内容说明</th>
                    </tr>
                </thead>
                <tbody id="tbodyy">
  
                    

                </tbody>
            </table>
        </div>
    </div>
</div>
<!--自评page结束-->
<!--自评page结束-->
<script language="javascript" type="text/javascript" src="<%=path%>/js/paRating/paRatingIndicator.js"></script>
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
