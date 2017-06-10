<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>填报单位概览</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
 	<link rel="stylesheet" href="<%= path %>/css/jquery.pagewalkthrough.css">
	<script>
		//退出时清除cookie里的值
	//组织
	$.removeCookie('type_cookie');   
	$.removeCookie('top_cookie');   // 一级菜单  头部
	$.removeCookie('twoMenu_cookie');  // 二级菜单  
	//填报
	$.removeCookie('typeTB_cookie');
	$.removeCookie('topTB_cookie');  // 一级菜单  头部
	$.removeCookie('twoMenuTB_cookie'); // 二级菜单  
	
	$.cookie('type_cookie', "1", {path:'/'});
	$.cookie('typeTB_cookie', "1", {path:'/'});
			var _trackData = _trackData || [];                
			_trackData.push(["userset","userid", '${sessionScope.shiroUser.siteCode}']);
			_trackData.push(["userset", "username", '${sessionScope.shiroUser.userName}']);
			_trackData.push(["userset", 'loginregist', 'login']);  
	</script>

<style type="text/css">
#jpwNext{ position: absolute; bottom: 27px; left: 60px; opacity: 0; width:100px; height:45px;}
#jpwPrevious{display: none;}
#jpwClose{ display: block;}
.overlay-hole{ padding:13px;}
.step span{position:absolute; bottom:28px; left:60px;display:inline-block; width:120px; height:40px; cursor:pointer;}
.first-steps i{position:absolute; top:10px; right:91px;display:inline-block; width:24px; height:20px; cursor:pointer; background:#fff; opacity:0; filter:alpha(opacity=0);}
</style>
</head>
<body>
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="1" scope="request"/>
		<c:set var="menu" value="" scope="request"/>
        <%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
             <%-- <h3 class="data-h3">${sessionScope.shiroUser.siteName}健康指数</h3> --%>
        	<div class="row">
            	<div class="z_box1 span4">
                	<div class="z_box_header">
                        <div class="star-msg">
                            <div class="star-msg-main wen-con">
                                <p>本系统对全国政府网站每日实时监测，通过大数据分析得出全国政府网站的健康指数，作为一个针对全国政府网站运行状态的参考。</p>
                                <i class="angle1"></i>
                            </div>
                            <div class="star-box icon-wen">
                                <i class="i-wen">?</i>
                            </div>
                        </div>
                        <i class="zhi-s-i" style="display:none;"></i>
                        <i class="zhis-i" id="convertScores_you_id" style="display:none;"></i>
                    </div>
                    <div class="chart-box">
                        <div class="chart-body">
                            <canvas width="224" height="224" id="chart-gauge"></canvas>
                            <div class="chart-info">
                                <h3 class="zhi_s">健康指数</h3>
                                 <!--/z_box_header-->
                                <div class="num1" id="totalScores_id"></div>
                                <div class="num1_msg" id="gtTotalSum_id"></div>
                            </div>
                        </div>
                    </div>
                   
                    <div class="num2" id="pre_scores_id">
                    </div>
                </div>
                <div class="z_box2 span8">
<!--                 	<div class="row">
                        <h3 class="qu_s pull-left">健康指数趋势</h3>
                        <ul class="qus_nav pull-right">
                            <li class="qus_icon1"><i></i>全国政府网站</li>
                            <li class="qus_icon2"><i></i>中国政府网</li>
                        </ul>
                    </div> -->
                    <div class="line_chart"  >
                    	<div id="index_count_line_id" style="height: 270px;width: 100%;margin-top: 10px"></div>
                    </div>
                </div>
            </div>
            <h3 class="data-h3">当前监测结果(昨天)</h3>
            <div class="row row-sp3">
            	<div class="databox span2">
                    <div class="bg-603cba" href="dangqianjianc.html">
                        <span class="num1"><span id="connectionSum_id">0</span>次</span>
                        <span class="databox-bom">网站连通性</span>
                    </div>
                    <!--色块更改部分s-->
                    <div class="colo-block">
                        <ul>
                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/connectionHomeDetail_index.action')" href="<%=path %>/connectionHomeDetail_index.action"><span class="fl">首页不连通</span><span class="fr" id="coHomeId"></span></a></li>
                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/connectionBusinessDetail_connectivity.action')" href="<%=path%>/connectionBusinessDetail_connectivity.action?col=0"><span class="fl">栏目不连通</span><span class="fr" id="coChannelId"></span></a></li>
                            <li class="clearfix everyline last-li"><a onclick="jumpPage('<%=path %>/connectionBusinessDetail_connectivity.action')" href="<%=path%>/connectionBusinessDetail_connectivity.action?col=1"><span class="fl">业务系统不连通</span><span class="fr" id="coBusinessId"></span></a></li>
                        </ul>
                    </div>
                    <i class="bdt-ddd3f4 bdt">
                    </i>
                    <!--色块更改部分e-->
                </div>
                <div class="databox span2">
                   	 <div class="bg-eb3c00" href="dangqianjianc.html">
                        <span class="num1"><span id="linkSum_id"></span>个</span>
                        <span class="databox-bom">不可用链接</span>
                    </div>
                    <!--色块更改部分s-->
                    <div class="colo-block">
                        <ul>
                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/linkHome_linkHomeIndex.action')" href="<%=path%>/linkHome_linkHomeIndex.action"><span class="fl">首页</span><span class="fr" id="lkHomeId"></span></a></li>
 	                        <li class="clearfix everyline last-li"><a onclick="jumpPage('<%=path %>/linkAll_linkAllDetailIndex.action')" href="<%=path%>/linkAll_linkAllDetailIndex.action"><span class="fl">全站</span><span class="fr" id="linkAllSum"></span></a></li>
                        </ul>
                    </div>
                    <i class="bdt-f6d8cd bdt">
                    </i>
                    <!--色块更改部分e-->
                </div>
                <div class="databox span2">
<%--                 	<a class="red-num" href="yuj.html"><!-- <i class="left-redbg"></i><span>888</span><i class="right-redbg"></i> --></a>
                    <a class="bg-ff9700" onclick="jumpPage('<%=path %>/securityGuaranteeTb_securityGuaranteeMainTb.action')" href="<%=path %>/securityGuaranteeTb_securityGuaranteeMainTb.action">
                        <span class="num1"><span id="secu_span_id">0</span>个</span>
                        <c:if test="${sessionScope.shiroUser.iscost == 0}">
                        	<span class="num1_msg">问题（<span id="freeFont">昨天</span>）</span>
                        </c:if>
                       <c:if test="${sessionScope.shiroUser.iscost == 1}">
                        	<span class="num1_msg">问题（<span id="freeFont">周期</span>）</span>
                        </c:if>
                        <span class="databox-bom">内容保障问题</span>
                    </a> --%>
                    
                     <div class="bg-ff9700" href="dangqianjianc.html">
	                        <span class="num1"><span id="secu_span_id"></span>个</span>
	                        <span class="databox-bom">内容保障问题</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/homeInfoUnUpdate_index.action')" href="<%=path%>/homeInfoUnUpdate_index.action"><span class="fl">首页不更新</span><span class="fr" id="seHomeId"></span></a></li>
	                       		  <li class="clearfix everyline last-li">
                                    <span class="fl cur-p" id="cur-p">栏目不更新
                                        <i class="down_7-4 ico"></i>
                                        <i class="second-box">
                                           	<p class="">
                                              	 <a id="basicTitle" onclick="jumpPage('<%=path %>/securityGuaranteeTb_securityGuaranteeMainTb.action')" href="<%=path%>/securityGuaranteeTb_securityGuaranteeMainTb.action?typeId=0" class="clearfix"><span class="fl">基本信息</span> <span class="fr" id="sBasic"></span></a>
                                          	</p>

                                            <p>
                                                <a id="blankTitle" onclick="jumpPage('<%=path %>/securityGuaranteeTb_securityGuaranteeMainTb.action')" href="<%=path%>/securityGuaranteeTb_securityGuaranteeMainTb.action?typeId=1" class="clearfix"><span class="fl">空白栏目</span> <span class="fr" id="sBlank"></span></a>
                                            </p>
                                            <p>
                                                <a id="responseTitle" onclick="jumpPage('<%=path %>/securityGuaranteeTb_securityGuaranteeMainTb.action')" href="<%=path%>/securityGuaranteeTb_securityGuaranteeMainTb.action?typeId=2" class="clearfix"><span class="fl">互动回应差</span> <span class="fr" id="sResponse"></span></a>
                                            </p>
                                            <p>
                                                <a id="serviceTitle" onclick="jumpPage('<%=path %>/securityGuaranteeTb_securityGuaranteeMainTb.action')" href="<%=path%>/securityGuaranteeTb_securityGuaranteeMainTb.action?typeId=3" class="clearfix"><span class="fl">服务不实用</span> <span class="fr" id="sService"></span></a>
                                            </p>
                                        </i>
                                    </span>
                                    <span class="fr" id="seChannelId"></span>
                               <!-- </a>-->
                            </li>
	                        </ul>
	                    </div>
	                    <i class="bdt-f7ddb6 bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
                    
                    
                    
                </div>
                <div class="databox span2">
                	<c:if test="${sessionScope.shiroUser.iscost==0}"> 
	                   	<div class="bg-9f00a7" href="dangqianjianc.html">
	                        <span class="num1"><span>未开通</span></span>
	                        <span class="databox-bom">疑似错别字</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/correctContent_index.action')" href="<%=path%>/correctContent_index.action"><span class="fl">疑似错别字</span><span class="fr">未监测</span></a></li>
	                        </ul>
	                    </div>
	                    <i class="bdt-e5b9e7 bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
                    </c:if>
                    <c:if test="${sessionScope.shiroUser.iscost==1}"> 
	                   	<div class="bg-9f00a7" href="dangqianjianc.html">
	                        <span class="num1"><span id="content_span_id"></span>个</span>
	                        <span class="databox-bom">疑似错别字</span>
	                    </div>
	                    <!--色块更改部分s-->
	                    <div class="colo-block">
	                        <ul>
	                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/correctContent_index.action')" href="<%=path%>/correctContent_index.action"><span class="fl">疑似错别字</span><span class="fr" id="contcorrectId"></span></a></li>
	                        </ul>
	                    </div>
	                    <i class="bdt-e5b9e7 bdt">
	
	                    </i>
	                    <!--色块更改部分e-->
                    </c:if>
                </div>
                <div class="databox span2">
                   	 <div class="bg-009788" href="dangqianjianc.html">
                        <span class="num1"><span id="updateContentSum_id"></span>条</span>
                        <span class="databox-bom">内容更新量</span>
                    </div>
                    <!--色块更改部分s-->
                    <div class="colo-block">
                        <ul>
                            <li class="clearfix everyline"><a onclick="jumpPage('<%=path %>/updateHome_updateHomeIndex.action')" href="<%=path%>/updateHome_updateHomeIndex.action"><span class="fl">首页更新</span><span class="fr" id="upHomeTbId"></span></a></li>
                            <li class="clearfix everyline last-li"><a onclick="jumpPage('<%=path %>/connectionBusinessDetail_connectivity.action')" href="<%=path%>/connectionBusinessDetail_connectivity.action?col=2"><span class="fl">栏目更新</span><span class="fr" id="upChannelTbId"></span></a></li>
                        </ul>
                    </div>
                    <i class="bdt-8fdcd4 bdt">

                    </i>
                    <!--色块更改部分e-->
                </div>
        		 <div class="databox span2">
	        		 <c:if test="${sessionScope.shiroUser.isSafetyService==0}"> 
	   						<div class="bg-607d8b" href="dangqianjianc.html">
								<span class="num1"><span>未开通</span></span> <span
									class="databox-bom">网站安全</span>
							</div>
							<!--色块更改部分s-->
							<div class="colo-block">
								<a onclick="changeCookie(2,'<%=path %>/monitorSilentResult_indexTB.action','null','<%=path %>/monitorSilentResult_indexTB.action');" href="<%=path%>/monitorSilentResult_indexTB.action"><p>网站安全能为您做什么？</p></a>
							</div>
							<i class="bdt-c4dde9 bdt"> </i>
							<!--色块更改部分e-->
	                 </c:if>
	                  <c:if test="${sessionScope.shiroUser.isSafetyService!=0}"> 
							<div class="bg-607d8b" href="dangqianjianc.html">
								<span class="num1"><span id="question_span_id"></span>个</span> 
								<span class="databox-bom">网站安全</span>
							</div>
							<!--色块更改部分s-->
							<div class="colo-block">
								<ul>
		                            <li class="clearfix everyline"><a onclick="changeCookie(2,'<%=path %>/monitorSilentResult_indexTB.action','null','<%=path %>/monitorSilentResult_indexTB.action');" href="<%=path%>/monitorSilentResult_indexTB.action"><span class="fl">问题数</span><span class="fr" id="quesSumId"></span></a></li>
		                         	<li class="clearfix everyline last-li"><a onclick="changeCookie(2,'<%=path %>/monitorSilentResult_indexTB.action','null','<%=path %>/monitorSilentResult_indexTB.action');" href="<%=path%>/monitorSilentResult_indexTB.action"><span class="fl">风险值</span><span class="fr" id="riskNum"></span></a></li>
		                        </ul>
							</div>
							<i class="bdt-c4dde9 bdt"> </i>
							<!--色块更改部分e-->
	                  </c:if>
                </div> 
            </div><!-- /row2 -->
            <div class="row">
            	<div class="line_chart_box">
                  <div class="chart-header row">
                    <h3 class="h3-brand">健康与安全分析</h3>
                    <ul id="navPills" class="nav nav-pills pull-right">
                      <li class="active"><a href="#ltxContent" data-toggle="tab" id="conn_site_id">网站连通性</a><i></i></li>
                      <li><a href="#kyxContent" data-toggle="tab" id="link_unuse_id" >链接可用性</a><i></i></li>
                      <li><a href="#bzwtContent" data-toggle="tab" id="security_bzh_id">内容保障问题</a><i></i></li>
                      <li><a href="#zqxContent" data-toggle="tab" id="correct_cont_id">疑似错别字</a><i></i></li>
                      <!-- <li><a href="#aqContent" data-toggle="tab">网站安全</a><i></i></li> -->
                      <li><a href="#gxContent" data-toggle="tab" id="update_cont_id">内容更新</a><i></i></li>
                      <c:if test="${(sessionScope.shiroUser.orgToInfo==1 && sessionScope.shiroUser.isOrgSafetyService == 1)|| sessionScope.shiroUser.orgToInfo!=1}"> 
                      <li><a href="#sqContent" data-toggle="tab" id="safeQuestion">网站安全</a><i></i></li>
                      </c:if>
                    </ul>
                  </div><!-- /chart-header -->
                  <div class="tab-content">
                      <div id="ltxContent" class="chart-content chart-content-ltx active">
                          <div class="jiank_chart">
                         	 <div id="conn_site_line_id" style="height:300px; width: 100%;"></div>
                          </div>
                      </div><!-- /chart-content-ltx -->
                      <div id="kyxContent" class="chart-content chart-content-kyx" >
                          <div class="chart_msg row">
                            <ul class="chart-link">
                           		<li><input type="radio"  name="lau" class="link-unuse" id="linkHome_radio_id"  checked="checked" />&nbsp;首页不可用链接</li>
                                <li><input type="radio"  name="lau" class="link-unuse" id="linkAll_radio_id" />&nbsp;全站不可用链接</li>
                            </ul>
                          </div>
                          <div class="jiank_chart" >
                          	<div  class="jiank_chart_con" >
                          		<div id="linkHomeUnuse_id" style="height:260px; width: 100%;"></div>
                          	</div>
                          	<div  class="jiank_chart_con" >
                          		<div id="linkAllUnuse_id" style="height:260px; width: 100%;display: none"></div>
                          	</div>
                          </div>
                          
                      </div><!-- /chart-content-kyx -->
                      <div id="bzwtContent" class="chart-content chart-content-bzwt">
                          <div class="jiank_chart" rel="22"   >
                          		<div id="security_id" style="height:290px; width: 100%;" ></div>
                          </div>
                      </div><!-- /chart-content-bzwt -->
                      <div id="zqxContent" class="chart-content chart-content-zqx">
                          <div class="jiank_chart" rel="11"   >
                          	<div id="correct_content_id" style="height:290px; width: 100%;"></div>
                          </div>
                      </div><!-- /chart-content-zqx -->
                      <div id="aqContent" class="chart-content chart-content-aq">
                          <div class="chart_msg row">
                            <ul class="chart-link">
                                <li><input type="radio" />全站不可用链接</li>
                                <li><input type="radio" />首页不可用链接</li>
                            </ul>
                          </div>
                          <div class="jiank_chart">
                          </div>
                      </div><!-- /chart-content-aq -->
                      <div id="gxContent" class="chart-content chart-content-gx">
                          <div class="jiank_chart" >
                          		<div id="update_chnn_line_id" style="height:290px; width: 100%;"></div>
                          </div>
                      </div>
                      <c:if test="${(sessionScope.shiroUser.orgToInfo==1 && sessionScope.shiroUser.isOrgSafetyService == 1)|| sessionScope.shiroUser.orgToInfo!=1}"> 
                      <div id="sqContent"  class="chart-content chart-content-sq ">
                          <div class="jiank_chart">
                         	 <div id="safe_question_id" style="height:290px; width: 100%;"></div>
                          </div>
                      </div>
                      </c:if>
                      <!-- /chart-content-gx -->
                  </div><!-- /tab-content -->
                </div>
                
            </div><!-- /row3 -->
           <%@ include file="/common/footer.jsp"%>
    </div>
    
 
<input type="hidden" id="guideState" value="${sessionScope.shiroUser.guideState}">	
<c:if test='${sessionScope.shiroUser.guideState != 1}'>
<div id="walkthrough-content">
    <div id="walkthrough-1">
    	<div class="first-steps step" style="position:relative;">
    		<i class="btn-close" onclick = "closeYindao()"></i>
    		<img src="<%=path%>/images/yd/yd-1.png" alt=""  style="max-width:none;"/>
    	</div>
    </div>
    <div id="walkthrough-2">
    	<div class="second-steps step">
        	<img src="<%=path%>/images/yd/yd-2.png" alt="" style="margin-top:-90px; max-width:none;" />
        	<a id="jpwClose"></a>
       	</div>
    </div>
    <div id="walkthrough-3">
    	<div class="third-steps step">
       		<img src="<%=path%>/images/yd/yd-3.png" alt="" style="margin-top:-90px; max-width:none;" />
   		</div>
    </div>
    <div id="walkthrough-4">
    	<div class='fourth-steps step'>
        	<img src="<%=path%>/images/yd/yd-4.png" alt="" style="margin-top:-90px; max-width:none;" />
        </div>
    </div>
    <div id="walkthrough-5">
    	<div class="fifth-steps step">
        	<img src="<%=path%>/images/yd/yd-7(tb).png" alt="" style="margin-left:-110px; margin-top:-85px; max-width:none;" />
        </div>
    </div>
    <div id="walkthrough-6">
    	<div class="sixth-steps step">
        	<img src="<%=path%>/images/yd/yd-6.png" alt="" style="margin-left:-140px; margin-top:-90px; max-width:none;" />
        </div>
    </div>
</div>
</c:if>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts-all.js"></script>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/gauge.js"></script> 
<script language="javascript" type="text/javascript" src="<%=path %>/js/fillUnit/connection_gailan.js?<%= autoVersoin %>"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/next.js"></script>
<script>
var guideState = $("#guideState").val();
 $(function() {
 if(guideState != 1){
 $('ul li').removeClass('active');
  $(document.body).css({
   "overflow-x":"hidden",
   "overflow-y":"hidden"
 });
 var i = $('.bgzg').parent().parent();
    $('body').pagewalkthrough({
        name: 'introduction',
        steps: [
		{ popup: {content: '#walkthrough-1',type: 'modal' }
        }, {wrapper: '#center-part',popup: {content: '#walkthrough-2',type: 'tooltip',position: 'bottom',style:'left:90px;top:120px;',lockScrolling:'false'}
        }, {wrapper: '#ulId',popup: {content: '#walkthrough-3',type: 'tooltip',position: 'bottom',style:'left:90px;bottom:40px;',lockScrolling:'false'}
        }, {wrapper: '#ulTwId',popup: {content: '#walkthrough-4',type: 'tooltip',position: 'bottom',style:'left:90px;bottom:45px;',lockScrolling:'false'}
        }, {wrapper: i,popup: {content: '#walkthrough-5',type: 'tooltip',position: 'right',style:'left:60px;bottom:36px;',lockScrolling:'true'}
        },{wrapper: '#glLiId',popup: {content: '#walkthrough-6',type: 'tooltip',position: 'bottom',lockScrolling:'false'}
        }],onClose:function(){
			 $.ajax({
 				type : "POST",
 				url :webPath+"/databaseInfo_updateGuiteState.action",
 				data:{
 					siteCode:siteCode,
 				},
 				async : false,
 				dataType:"json",
 				success : function(data) {
 					$("#walkthrough-content").hide();
 					$('ul li').eq(0).addClass('active');
 					$(document.body).css({
					   "overflow-x":"auto",
					   "overflow-y":"auto"
					});
 				}
 			});
		}
    });
    $('body').pagewalkthrough('show');
 }
});
</script> 
</body>
</html>
