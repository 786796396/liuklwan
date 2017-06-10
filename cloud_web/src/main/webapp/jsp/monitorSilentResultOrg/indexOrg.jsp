<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>安全问题</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/SecurityScanning.css" />
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/connection/con_home_sort.js"></script>
	<!--整合公共样式-->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/commonbyb.css"/>
<!--只需引该页面相关的样式，其他样式项目header.jsp 里有-->
<!-- <link rel="stylesheet" type="text/css" href="<%=path%>/css/SafetyMonitoring-noCharge.css"/> -->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/yunjc.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/other-products-introduce.css"/>
</head>
<body class="bg_f5f5f5">
 <%@ include file="/common/top.jsp"%>
	<div class="main-detail">
	   <div class="main-detail-content ss row-fluid">
		 <c:if test="${sessionScope.shiroUser.isOrgSafetyService != 0}">
		<%@ include file="/common/leftmenu.jsp"%>
		</c:if>
		<c:if test="${sessionScope.shiroUser.isOrgSafetyService==0}">
			 <div class="other-products-part">
	         <div class="other-products-content">
        		<div class="main-title">
                    <h4>云安全能为您提供什么服务？</h4>
                </div>
                <div class="safeM-content">
                    <div class="item-box clearfix">
                        <div class="each-item fl spe-item">
                            <i class="yun-aq wzcrx"></i>
                            <h5>网站脆弱性监测</h5>
                            <p>扫描网站漏洞，避免用户的隐私信息的泄露、操作系统被修改或控制、硬盘数据被破坏。</p>
                        </div>
                        <div class="each-item fl spe-item">
                            <i class="yun-aq wzgm"></i>
                            <h5>网站挂马监测</h5>
                            <p>识别恶意代码，防止网站搜索引擎排名降低、网站数据泄露。</p>
                        </div>
                        <div class="each-item fl spe-item">
                            <i class="yun-aq bgcg"></i>
                            <h5>变更/篡改监测</h5>
                            <p>监测恶意篡改，防止不良信息及恶意言论发布，导致社会恐慌或引发政治危机。</p>
                        </div>
                        <div class="each-item fl spe-item">
                            <i class="yun-aq wzal"></i>
                            <h5>网站暗链监测</h5>
                            <p>识别网站暗链，防止植入诈骗、色情、赌博、反动信息，损伤政府形象及公信力。</p>
                        </div>
                        <div class="each-item fl">
                            <i class="yun-aq wzxl"></i>
                            <h5>网站泄露监测</h5>
                            <p>监测内容泄露，防止机密文件、重要信息泄露，造成重大安全事故。</p>
                        </div>
                    </div>
                   
                    <div class="apply-btn com-btns" style="display:block;">
                    <a href="http://www.boxpro.cn/boxpro/p/Eyo-GJyFx" target="_blank">
                        <i class="publi-ico_2 shoppingcar"></i>
                        <span>申请试用</span>
                     </a>
                    </div>
<!-- 购买页面 -->
				</div>
				</div>
				</c:if>
				
				<c:if test="${sessionScope.shiroUser.isOrgSafetyService!=0}">
				<div class="page-content">
				<!--需要填充的内容开始-->
				<h4 class="data-h3">安全监测</h4>
				<div class="row">
					<div class="span4 z_box2 pos-rel">
						<div class="z_box_header" style="position:absolute; top:20px; right:20px; z-index:100; margin:0;">
							<div class="star-msg">
								<div class="star-msg-main wen-con" style="display: none; width:300px; padding-top:5px;">
									<p class="ss-wen-desc">根据安全问题数量及问题严重性评估安全风险值。</p>
									<p class="ss-wen-desc">风险值越高，问题越严重。</p>
									<i class="angle1"></i>
								</div>
								<div class="star-box ss-icon-wen">
									<i class="i-wen">?</i>
								</div>
							</div>
						</div>
						<div class="risk-box" id="feng">
						</div>
					</div>					
					<div class="z_box2 span8"><div id="fengQu" style="height: 270px; width: 100%; margin-top: 10px"></div></div>
				</div>
				<div class=" mart-20 ques_stat">
					<div class="head-bg-1bbc9b clearfix">
						<div class="fl tit">问题统计</div>
						<div class="fr jc_date">
							监测日期： <span id="scanDate"></span>
						</div>
					</div>

					<table id="abc">
						<thead>
							<tr>
								<th class="project td_left">监测项</th>
								<th>问题总数</th>
								<th>问题网站数</th>
							</tr>
						</thead>
						<tbody class="monitor-items-tab">
							<tr>
								<td class="project td_left">
									<div class="info-box">
										<span>网站脆弱性</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">扫描网站漏洞，避免用户的隐私信息的泄露、操作系统被修改或控制、硬盘数据被破。</div>
									</div>
								</td>
								<td><span id="weak"></span></td>
								<td><span id="weakSite"></span></td>
							</tr>
							<tr>
								<td class="project td_left">
									<div class="info-box">
										<span>网站挂马</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">识别恶意代码，防止网站搜索引擎排名降低、网站数据泄露。</div>
									</div>
								</td>
								<td><span id="horse"></span></td>
								<td><span id="horseSite"></span></td>
							</tr>
							<tr>
								<td class="project td_left">
									<div class="info-box">
										<span>变更/篡改</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">监测恶意篡改，防止不良信息及恶意言论发布，导致社会恐慌或引发政治危机。</div>
									</div>
								</td>
								<td><span id="update"></span></td>
								<td><span id="updateSite"></span></td>
							</tr>
							<tr>
								<td class="project td_left">
									<div class="info-box">
										<span>网站暗链</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">识别网站暗链，防止植入诈骗、色情、赌博、反动信息，损伤政府形象及公信力。</div>
									</div>
								</td>
								<td><span id="dark"></span></td>
								<td><span id="darkSite"></span></td>
							</tr>
							<tr>
								<td class="project paddl-30 td_left">
									<div class="info-box">
										<span>网站泄露</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">监测内容泄露，防止机密文件、重要信息泄露，造成重大安全事故。</div>
									</div>
								</td>
								<td><span id="out"></span></td>
								<td><span id="outSite"></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class=" mart-20 ques-trend">
					<div class="title-bdt-1bbc9b">
						<span>问题数趋势</span>
					</div>
					<div class="content" id="quesQu"></div>
				</div>
				<div class=" mart-20">
					<div class="tab_header row">
						<h3>安全问题概览</h3>
						<input type="hidden" >
							<div class="input-prepend" tabindex="-1">
								<span class="add-on"><img alt="search"
									src="<%=path%>/images/common/search.png" /></span> <input
									class="span2 prependedInput" id="keyId"
									 type="text" placeholder="输入关键字...">
							</div>
							<div class="fl period">
								<div class="fl" style="width:100px; border:none; background:none; -webkit-border-radius: 0; -moz-border-radius: 0;border-radius: 0;">
									<select id="selId" onchange="optSelect()" style="width:100px;">
									  <option value ="0">昨天</option>
									  <option value ="1">一周</option>
									  <option value ="2">两周</option>
									  <option value="3">一个月</option>
									</select>
								</div>
							</div>
							<div id="excel_out_id" class="page-btn1" onclick="monitorExcle()">
								<i></i>导出列表
							</div>
					</div>
					<div class="ques_gail">
						<!-- <table class="tablesorter">
							<thead>
								<tr>
									<th>网站标识码</th>
									<th>网站名称</th>
									<th>风险值</th>
									<th>网站脆弱性</th>
									<th>网站挂马</th>
									<th>变更/篡改</th>
									<th>网站暗链</th>
									<th>网站泄露</th>
								</tr>
							</thead>
							<tbody id="monitor_SilentOrg_tbody"> 
							</tbody>
						</table> -->
						<table id="monitor_SilentOrg_tbodySort" cellpadding="0" cellspacing="0"
									class="table">
								<tr>
									<th class="th-left" style="width:5%;">网站标识码</th>
									<th class="th-left" style="width:10%;">网站名称</th>
									<th class="th-center numOrder" style="width:5%; position: relative; text-align:center;">风险值<i class="tab_angle"></i></th>
									<th class="th-center numOrder" style="width:5%; position: relative; text-align:center;">网站脆弱性<i class="tab_angle"></i></th>
									<th class="th-center numOrder" style="width:5%; position: relative; text-align:center;">网站挂马<i class="tab_angle"></i></th>
									<th class="th-center numOrder" style="width:5%; position: relative; text-align:center;">变更/篡改<i class="tab_angle"></i></th>
									<th class="th-center numOrder" style="width:5%; position: relative; text-align:center;">网站暗链<i class="tab_angle"></i></th>
									<th class="th-center numOrder" style="width:5%; position: relative; text-align:center;">网站泄露<i class="tab_angle"></i></th>
								</tr>
						</table>
						<table id="monitor_SilentOrg_tbody"></table>
						<div class="zanwsj" id="monitor_SilentOrg_hide">无数据</div>
					</div>
					<%@ include file="/common/footer.jsp"%>
				</div>
				</c:if>
<!-- 				</div> -->
<c:if test="${sessionScope.shiroUser.isOrgSafetyService == 0}">
				<%@ include file="/common/footer.jsp"%>
</c:if>				
			</div>
		</div>
	</div>
	<!-- /container -->

	<script type="text/javascript" src="<%=path%>/js/echarts.js"></script>
	<script type="text/javascript"
		src="<%= path %>/js/monitorSilentResultOrg/monitorSilentResultOrg.js"></script>
	<script type="text/javascript">
		 $(".ss-icon-wen").hover(function(){
	        $(this).siblings(".wen-con").fadeIn();
	    },function(){
	        $(this).siblings(".wen-con").fadeOut();
   		 });
   		 $(function(){
   		 	nameHover("abc");
   		 })
   		$(".each-item").mouseover(function(){
        	$(this).addClass('active');
	    });
	    $(".each-item").mouseout(function(){
	        $(this).removeClass('active');
	    });
	</script>

</body>
</html>
