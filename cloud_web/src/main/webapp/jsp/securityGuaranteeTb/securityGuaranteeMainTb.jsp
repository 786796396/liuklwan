<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/dropload.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/monitoring/monitoring-tb.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/common-new.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icons.css" />
 <!-- 连通性结果弹框样式 -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/lastConnection.css" /> 
<!--tab切换-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tabChange.css" />
<script type="text/javascript" src="<%=path%>/js/securityGuaranteeTb/securityGuaranteeMainTb.js"></script>
<!-- 连通性弹框js -->
<script  type="text/javascript" src="<%=path%>/js/connectionShow.js"></script>
<title>深度检测-内容保障问题</title>
</head>
<body class="bg_f5f5f5">
<input type ="hidden" value="${sessionScope.shiroUser.siteCode }" id = "siteCode">
 <input id="radioVal" type="hidden" value="">
 <input id="systemTwo" type="hidden" value="1">
 <input id="scanDateSystem" type="hidden">
 <input id="modeTB" type="hidden" value="${modeTB}">
 <input id="isSenior" type="hidden" value ="${isSenior}">
 <input id="yesterdayStr" type="hidden" value="${yesterdayStr}">
 <input id="typeIdTb" type="hidden" value="${typeIdTb}">
 <input id="modeTb" type="hidden" value="${modeTb}">
 <input id="servicePeriodIdTb" type="hidden" value ="${servicePeriodIdTb}">
 <input id="spTbStr" type="hidden" value="${spTbStr}">
<!--  跳转的三个数据传递过来  需要处理跳转问题 -->
 
<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%=path%>/images/common/top-hover.png"/>
        <img class="top-img" src="<%=path%>/images/common/top.png"/>
    </a>
</p>
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">

	<div class="row-fluid">
        <c:set var="tb_index" value="12" scope="request" />
				 <c:set var="menu" value="#menuBzwt" scope="request"/>
				<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content">
        	<!--栏目检测 头部标题部分s-->
            <div class="row bread-crumbs daily-monitor-box-m">
                <div class="bread-crumbs-content">
                    <span class="cor-0498e4">深度检测</span>
                    <i class="cor-0498e4">></i>
                    <span >内容保障问题</span>
                </div>
            </div>
            <input type="hidden" id="typeIdJump" value="${typeIdJump}">
            <!--栏目检测 头部标题部分e-->
            <div class="five_tab_part">
                <div class="five_tp_top">
                    <div class="every_block clearfix">
                        <div id="tab0" class="fl every_tip on" onclick="securityBasic()">基本信息</div>
                        <div id="tab1" class="fl every_tip" onclick="securityBlank()">空白栏目</div>
                        <div id="tab2" class="fl every_tip" onclick="securityResponse()">互动回应差</div>
                        <div id="tab3" class="fl every_tip" onclick="securityService()">服务不实用</div>
<%--                        <div id="tab4" class="fl every_tip" onclick="securityFatal()">严重错误</div>--%>
                        <input type="hidden" id="typeId">
                    </div>
                    <div class="green_line"></div>
                </div>
            </div>
             <div class="pay">
             </div>
             <div class="detection-mode-box clearfix">
                <div class="everymode fl">
                    <input type="radio" name="mode" id="manual" value="1"/>
                    <label for="manual">高级版</label>
                </div>
                <div class="everymode fl">
                    <input type="radio" name="mode" id="system" value="2"/>
                    <label for="system">标准版</label>
                </div>
            </div>
            <div class="row daily-monitor-box-m conditions" >
                <div class="conditions-content clearfix ">
                       <div id="dateStr" class="fl detection-date"></div>
			           <div class="fl period-box ">
                        <span class="fl tit" id="period">检测周期：</span>
                        <div class="fl select-box select-box-long periodOne" style="width:185px;">
                       		<span id="typeTextBasic">${nearbasicPeriodList}</span>
                        	<span id="servicePeriodIdTextBasic"></span>
                        	<input type="hidden" id="typeTextBasicId" value="${nearbasicPeriodListId }">
                        	<input type="hidden" id="servicePeriodIdValBasic">
                        	<input type="hidden" id="servicePdIdTbBasic">
                            <i></i>
                            <ul id="servicePeriodIdUlBasic" style="display: none; width:190px;">
                            	 <c:forEach items="${basicPeriodList}" var="spBasic">
									 <li name="basicPeriodList" id="basicPeriodList" value="${spBasic.id}" >${spBasic.cycleDate}</li>
								</c:forEach>
                            </ul>
                        </div>
                         <div class="fl select-box select-box-long periodTwo" style="width:185px;">
                         <span id="typeText">${nearSecurityList}</span>
                        	<span id="servicePeriodIdText"></span>
                        	<input type="hidden" id="typeTextId" value="${nearSecurityListId }">
                        	<input type="hidden" id="servicePeriodIdVal">
                        	<input type="hidden" id=servicePdIdTb>
                            <i></i>
                            <!--移入显示块开始-->
                            <ul id="servicePeriodIdUl" style="display: none; width:190px;">
                            	 <c:forEach items="${securityList}" var="spSecurity">
									 <li name="securityList" id="securityList" value="${spSecurity.id}" >${spSecurity.cycleDate}</li>
								</c:forEach>
                            </ul>
                            <!--移入显示块结束-->
                        </div>
                        
                        <span class="fl tit" id="channel">栏目类别：</span>
                        <div class="fl select-box select-box-long channelOne" style="width:144px;">
                        	<span id="channelText">全部</span>
                        	<input type="hidden" id="channelIdVal" value="0">
                            <i></i>
                            <ul id="channelUl" style="display: none;width:150px;">
							  <li  value="0" >全部</li>
                              <li  value="1" >动态、要闻类</li>
                              <li  value="2" >通知公告、政策文件类</li>
                              <li  value="3" >人事、规划计划类</li>	
                            </ul>
                        </div>
                        
                        <span class="fl tit" id="result">检测结果：</span>
                        <div class="fl select-box select-box-long resultOne" style="width:144px;">
                        	<span id="resultTextOne">逾期不更新</span>
                        	<input type="hidden" id="resultIdVal" value="1">
                            <i></i>
                            <ul id="resultUlOne" style="display: none;width:150px;">
                           	   <li  value="0" >全部</li>
							   <li  value="1" >逾期不更新</li>
							   <li  value="2" >正常更新</li>
                            </ul>
                        </div>
                         <div class="fl select-box select-box-long resultTwo" style="width:130px;">
                        	<span id="resultTextTwo">逾期不更新</span>
                        	<input type="hidden" id="resultIdVal" value="1">
                            <i></i>
                            <ul id="resultUlTwo" style="display: none; width:150px;">
							  <li  value="0" >全部</li>
                              <li  value="1" >逾期不更新</li>
                              <li  value="2" >正常更新</li>
                            </ul>
                        </div>
                        
                        
                        
			          </div>
                    <div class="fl total-box">
                        共： 
                        <span id="sizeId" class="cor-f20707"></span>
                        条 
                    </div>
                    <div class="fr export-box export-all" onclick="securityExcel()">
                        <i></i>
                        全部导出
                    </div>
                    <div class="fr search-box input-b" style="width:140px;">
                        <input id="keyId" type="text" class="search-input fl" placeholder="输入网站名称和标识码" style="width:108px;"/>
                        <i class="fl"></i>
                    </div>
                </div>
            </div>
            <!--列表块头部结束-->
            <!--表部分开始-->
              <div class="data-show" id="content">
                  <table class="table">
                      <thead id="tHead">
                      </thead>
                  </table>
                  <table id="table-web" class="table">
					 <tbody id="webSiteConnectedTbody">
					</tbody>
				</table>
  <div class="zanwsj" style="display: none" id="webSiteConnectedloadingHide">正在加载中……</div>
 <!-- <div class="zanwsj" style="display: none" id="webSiteConnectedHide">检测中，暂未发现问题！</div> -->
            </div>
            <!--表部分结束-->
            <div >
				   <%@ include file="/common/footer.jsp"%>	
		    </div>

        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

	
	<%@ include file="/common/connectionDialog.jsp"%>
	<script language="javascript" type="text/javascript">
		$(function() {
			$(function() {
				$(".select-box").hover(function() {
					$(this).children("ul").show();
				}, function() {
					$(this).children("ul").hide();
				});

				function selectShow(id) {
					$("#" + id + "Ul li").click(function() {
						$("#" + id + "Val").val(this.value);
						$("#" + id + "Text").html($(this).html());
						$("#" + id + "Ul").hide();
					});
				}
				// selectShow("siteType");
				//selectShow("aaaType");

				$("#servicePeriodIdUl li").click(function() {
					$("#servicePeriodIdVal").val(this.value);
					$("#servicePeriodIdText").val($(this).html());
					$("#servicePeriodIdUl").hide();
					$("#typeText").html($(this).html());
					changeFunction();
				});

				$("#servicePeriodIdUlBasic li").click(function() {
					$("#servicePeriodIdValBasic").val(this.value);
					$("#servicePeriodIdTextBasic").val($(this).html());
					$("#servicePeriodIdUlBasic").hide();
					$("#typeTextBasic").html($(this).html());
					changeFunction();
				});

				$("#channelUl li").click(function() {
					$("#channelIdVal").val(this.value);
					$("#channelUl ").hide();
					$("#channelText").html($(this).html());
					changeFunction();
				});

				$("#resultUlOne li").click(function() {
					$("#resultIdVal").val(this.value);
					$("#resultUlOne ").hide();
					$("#resultTextOne").html($(this).html());
					changeFunction();
				});
				
				$("#resultUlTwo li").click(function() {
					$("#resultIdVal").val(this.value);
					$("#resultUlTwo ").hide();
					$("#resultTextTwo").html($(this).html());
					changeFunction();
				});

				$(document).ready(function(e) {
					//cbox样式
					$("input[type='checkbox'],input[type='radio']").iCheck({
						checkboxClass : 'icheckbox_square-blue',
						radioClass : 'iradio_square-blue',
					});
				})

			})
		})
	</script>
	<script type="text/javascript" src="<%= path %>/js/commonscandate.js"></script>
</body>
</html>