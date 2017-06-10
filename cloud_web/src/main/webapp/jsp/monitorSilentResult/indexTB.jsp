<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>安全问题</title>
	<%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/heade.jsp"%>
    <link rel="stylesheet" href="<%=path%>/css/SecurityScanning.css"/>
    
    	<!--整合公共样式-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/commonbyb.css"/>
	<!--只需引该页面相关的样式，其他样式项目header.jsp 里有-->
<!-- 	<link rel="stylesheet" type="text/css" href="<%=path%>/css/SafetyMonitoring-noCharge.css"/> -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/yunjc.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/other-products-introduce.css"/>
  </head>
<body>
<%@ include file="/common/top_tb.jsp"%>
<div class="main-detail">
	<div class="main-detail-content ss row-fluid">
	 	<c:if test="${sessionScope.shiroUser.isSafetyService != 0}">
		<%@ include file="/common/leftmenu.jsp"%>
		</c:if>        
		
        <c:if test="${sessionScope.shiroUser.isSafetyService==0}">
        <div class="other-products-part">
            <div class="other-products-content">
	<!--需要填充的内容开始-->
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
                </div>
            </div>
         	
         	
         	
<!-- 购买页面 -->
            </c:if>  
            
           <c:if test="${sessionScope.shiroUser.isSafetyService!=0}"> 
           <div class="page-content">
        	 <!--需要填充的内容开始-->
            <h4 class="data-h3">安全扫描</h4>

            <div class="row">
                <div class="span4 z_box2 pos-rel">
					<div class="z_box_header" style="position:absolute; top:20px; right:20px; z-index:101; margin:0;">
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
							<!-- <i class="zhi-s-i" style="display: none;"></i> <i id="sum_style" class="zhis-i" style="display: none;">差</i> -->
						</div>
						<div class="risk-box" id="feng">
						</div>
                </div>
               <div class="z_box2 span8"><div id="fengQu" style="height: 270px; width: 100%; margin-top: 10px"></div></div>
            </div>

            <div class=" mart-20 ques_stat">
                <div class="head-bg-1bbc9b clearfix" >
                    <div class="fl tit">问题统计</div>
                    <div class="fr jc_date">监测日期： <span id="scanDate"></span></div>
                </div>

                <table id="abc">
                    <thead>
                        <tr>
                            <th class="project td_left">监测项</th>
                            <th>网站问题数</th>
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
                        </tr>
                        <tr>
                            <td class="project td_left">
                            	<div class="info-box">
										<span>网站挂马</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">识别恶意代码，防止网站搜索引擎排名降低、网站数据泄露。</div>
									</div>
                            </td>
                            <td><span id="horse"></td>
                        </tr>
                        <tr>
                            <td class="project td_left">
                            	<div class="info-box">
										<span>变更/篡改</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">监测恶意篡改，防止不良信息及恶意言论发布，导致社会恐慌或引发政治危机。</div>
									</div>
                            </td>
                            <td><span id="update"></td>
                        </tr>
                        <tr>
                            <td class="project td_left">
                            	<div class="info-box">
										<span>网站暗链</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">识别网站暗链，防止植入诈骗、色情、赌博、反动信息，损伤政府形象及公信力。</div>
									</div>
                            </td>
                            <td><span id="dark"></td>
                        </tr>
                        <tr>
                            <td class="project paddl-30 td_left">
                            	<div class="info-box">
										<span>网站泄露</span>
										<div class="chouc-hover-div"></div>
										<div class="info-con" style="word-break: break-word; left: 940px; top: 333px; display: none;">监测内容泄露，防止机密文件、重要信息泄露，造成重大安全事故。</div>
									</div>
                            </td>
                            <td><span id="out"></td>
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

            <div class="row mart-14 ques-detail-part">
                <h3 class="data-h3">问题详情</h3>
                <div class="content-box">
                    <div class="content-top">
                        <div class="block-Parts">
                            <ul class="clearfix" id="title1">
                                <li class="fl active">
                                    <a class="clickAct" num="1" href="javascript:void(0);">网站脆弱性</a>
                                    <b class="green-caret"></b>
                                </li>
                                <li class="fl">
                                    <a class="clickAct" num="2" href="javascript:void(0);">网站挂马</a>
                                    <b class="green-caret"></b>
                                </li>
                                <li class="fl">
                                    <a class="clickAct" num="3" href="javascript:void(0);">变更/篡改</a>
                                    <b class="green-caret"></b>
                                </li>
                                <li class="fl">
                                    <a class="clickAct" num="4" href="javascript:void(0);">网站暗链</a>
                                    <b class="green-caret"></b>
                                </li>
                                <li class="fl">
                                    <a class="clickAct" num="5" href="javascript:void(0);">网站泄露</a>
                                    <b class="green-caret"></b>
                                </li>
                            </ul>
                        </div><!-- /nav-pills-togtab-box -->

                        <div class="single-Part">
                            <div class="every-s active" id="numFirst">
                                <ul class="clearfix" id="titleLi2">
                                    <li class="fl active"><a href="javascript:void(0);" class="title2" type="1" >SQL注入漏洞</a></li>
                                    <li class="fl"><a href="javascript:void(0);" class="title2" type="5" >应用漏洞  </a></li>
                                    <li class="fl"><a href="javascript:void(0);" class="title2" type="2" >XSS跨站脚本漏洞</a></li>
                                    <li class="fl"><a href="javascript:void(0);" class="title2" type="3" >CGL漏洞</a></li>
                                    <li class="fl"><a href="javascript:void(0);" class="title2" type="4" >CSRF漏洞</a></li>
                                    <li class="fl"><a href="javascript:void(0);" class="title2" type="6" >表单破解</a></li>
                                </ul>
                            </div>
                            <div class="every-s" id="numFive">
                                <ul class="clearfix"  id="titleLi3">
                                    <li class="fl  active"><a href="javascript:void(0);" class="title3" type="1">文件泄露</a></li>
                                    <li class="fl "><a href="javascript:void(0);" class="title3" type="2">内容泄露</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="table_tabs">
                            <div class="table_tabs-top clearfix">
                                <div class="total fl">共<span id="sumCount"></span>条</div>
                                <div class="period fl">
                                    <div class="fl">
                                        <input type="text" id="startDate"/>
                                    </div>
                                    <span class="fl">至</span>
                                    <div class="fl">
                                        <input type="text" id="endDate"/>
                                    </div>
                                </div>
                                <div class=" daoc fr" onclick="questionDetailExcel()">
                                    导出列表
                                </div>
                            </div>
                            <div class="table-group">
                                <div class="table_tabs-content active ques_gail">
                                    <table>
                                        <thead>
                                            <tr id="thead">
                  <!--                               <th style="width: 5%;">序号</th>
                                                <th style="width: 10%;">级别</th>
                                                <th style="width: 10%;">漏洞类型</th>
                                                <th style="width: 20%;" class="th-left">URL地址</th>
                                                <th style="width: 10%;">方法</th>
                                                <th style="width: 15%;" id="isShow">参数</th>
                                                <th style="width: 15%;">扫描时间</th> -->
                                            </tr>
                                        </thead>
                                        <tbody id="tbody">
<!--                                             <tr>
                                                <td style="width: 5%;" class="td-center">1</td>
                                                <td style="width: 10%;" class="td-center">低</td>
                                                <td style="width: 10%;" class="td-center">代码健壮 </td>
                                                <td style="width: 20%;"class="td-left"><a href="javascript:void(0);" style="color: #2899df; word-break: break-all;">http://www.bjhd.gov.cn/govinfo_new/... </a></td>
                                                <td style="width: 10%;" class="td-center">参数化</td>
                                                <td style="width: 15%;" class="td-center">sql_str</td>
                                                <td style="width: 15%;" class="td-center">2016-10-16  14:22:22</td>
                                            </tr> -->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
           	 </div>
           	 <%@ include file="/common/footer.jsp"%>
             </div>
              </c:if>  
<c:if test="${sessionScope.shiroUser.isSafetyService == 0}">
<%@ include file="/common/footer.jsp"%>
</c:if>
       <!-- /page-content -->
    </div>
</div> <!-- /container -->

<script type="text/javascript" src="<%=path%>/js/echarts.js"></script> 
<script type="text/javascript" src="<%= path %>/js/monitorSilentResult/monitorSilentResult.js"></script> 
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
