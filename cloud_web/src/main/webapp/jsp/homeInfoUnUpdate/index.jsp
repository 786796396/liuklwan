<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>填报单位 首页信息不更新</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
<style type="text/css">
.shot{background: url("<%=path%>/images/onlineReport/ph-1.jpg") 0 0;background-size: 100% 100%;display: block;height: 100%; cursor: pointer;}
.shot:hover{opacity: 0.8;}
</style>
<!-- 连通性结果弹框样式 -->
<link rel="stylesheet" type="text/css" href="<%=path%>/css/lastConnection.css" /> 
</head>

<body>
	<!--头部       satrt  -->
	<%@ include file="/common/top_tb.jsp"%>
	<!--头部       end  -->
	<div class="page-wrap">
		
		<div class="main-container container">
			<div class="row-fluid">
				<c:set var="tb_index" value="7" scope="request" />
				<c:set var="menu" value="#menuBzwt" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
				<div class="page-content">
					<div class="row">
						<ul class="breadcrumb">
							<li><a href="#">日常监测</a> <span class="divider">></span></li>
							<li class="active">首页不更新</li>
                            <li class="jc-ms" style="display:none;">
                                <div class="ms-msg">
                                    <div class="ms-wen-con">
                                        <div class="ztm-con">
                                            <p style="margin-top:10px;">1.考察网站两周内首页栏目信息是否更新；</p>
                                            <p>2.两周内首页栏目信息更新总量为0条即触发预警。</p>
                                        </div>
                                        <i class="angle1"></i>
                                    </div>
                                    <div class="ms-icon-wen">
                                        <i class="i-wen">?</i>
                                    </div>
                                </div>
                            </li>
						</ul>
					</div>


					<div class="row">
						<div class="tab_header row">
							<h3>首页信息不更新监测结果  <input class="datepicker-input" type="text" id="datepicker"  value="${scanDate}"/></h3>
						</div>
						<div class="imgzoom">
							<div class="imgzoom-box">
								<div class="imgzoom-min">
                					<a href="javascript:void(0);" style="width:372px;height:440px" class="shot" onclick="getShot('${imgUrl}')">
<!--                 					 <img id="minImg" alt="image" src="<%=path%>/images/def-img.jpg" style="width:372px;height:440px" /> -->
                					 </a>
									
								</div>
							</div>
							<div class="imgzoom-basicinfo" >
								<input type="hidden" id="unupdate_home_timetool_min"
									value="${siteBeginServiceDate}" />
							<span id="unUpdateDay">
								<c:if test="${unUpdateDay<=14}">
									<div class="alert alert-success">
										<i></i>恭喜您，首页信息更新很及时 !
									</div>
								</c:if> 

								<c:if test="${unUpdateDay>14}">
									<div class="alert alert-warning">
										<i></i>首页已经超过2星期未更新了，请及时更新！
									</div>
								</c:if>
								</span>
								<table cellpadding="0" cellspacing="0" class="impassability-tab">

									<tbody>
										<tr>
											<td class="td-tit" style="width:150px;">网站名称：</td>
											<td><span class="font-size2" id="wzmc">
													${siteName} </span></td>
										</tr>
										<tr>
											<td class="td-tit">首页网址：</td>
											<td id="url"><a href="${jumpUrl}" target="_blank">${url}</a></td>
										</tr>
										<tr>
											<td class="td-tit">监测日期：</td>
											<td id="scanDate">${scanDate}</td>
										</tr>
										<tr>
											<td class="td-tit">最后更新日期：</td>
											<td id="lastUpdateDate" id="lastUpdateDate">${lastUpdateDate}</td>
										</tr>
										<tr>
											<td class="td-tit" style="position:relative;" onmouseover="commonscandateOver()" onmouseout="commonscandateOut()">最后正常访问日期：
												<div id="lud_detail" style="display: none; left:55px;top:30px;">
		                                                <div class="top">
		                                                    <p class="line_top"><span>*</span>监测服务器最后一次正常连通网站的时间。</p>
		                                                    <p class="line_top"><span>*</span>如果您发现最后正常访问日期小于监测日期，可能是以下原因：</p>
		                                                    <p class="line-bott"><span>1.</span>安全狗，可以联系您的网站管理员将开普云监管ip地址加入白名单，<span class="ip_address" onclick="ip_address()">查看IP地址 <i class="down"></i></span></p>
		                                                    <p class="line-bott" name="flag"><span>2.</span>js加载</p>
		                                                    <p class="line-bott" name="flag"><span>3.</span>网站有安全防护措施，云监管获取不到源码</p>
		                                                    <p class="line-bott" name="flag"><span>4.</span>稿件的链接的域名和首页地址的域名不一致</p>
		                                                    <p class="line-bott" name="flag"><span>5.</span>时间格式不正确（建议格式：年/月/日）</p>
		                                                </div>
		                                                <div class="hidden_part">
		                                                    <div class="center">
		                                                        <div class="content" >
		                                                            <ul class="clearfix" id="whiteListIP"></ul>
		                                                        </div>
		                                                    </div>
		                                                    <div class="bottom clearfix">
		                                                        <div class="fl copy_all" id="commonscandate_copyAllId" >复制全部</div>
		                                                        <div class="fl g_close" id="commonscandate_copyCloseId" onclick="commonscandate_copyCloseId()">关闭</div>
		                                                    </div>
		                                                </div>
                                            		</div>
											</td>
<%-- 											<td id="lastAccessDate" id="lastAccessDate"><a href='javascript:void(0)' onclick="showConnectionDialog('${siteCodeResult}','${lastAccessDate}','${scanDate}')">${lastAccessDate}</a></td>
 --%>											<td style="color:#999999">${lastAccessDate}&nbsp;<i  onmouseover="commonscandateOver()" onmouseout="commonscandateOut()"></i></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- /tab2 -->
					<%@ include file="/common/footer.jsp"%>
				</div>
				<!-- /page-content -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- Modal -->
	<div id="imgzoomModal" class="page-modal modal fade hide" id="myModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="imgzoom-max">
			<div class="imgzoom-maxImage">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<img id="maxImg" alt="image" src="${imgMax}" />
			</div>
		</div>
	</div>
	<!-- 连通性结果弹框 -->
	<%@ include file="/common/connectionDialog.jsp"%>
	<script language="javascript" type="text/javascript"
		src="<%=path%>/js/homeInfoUnUpdate/homeInfoUnUpdate.js"></script>
	<!-- 连通性弹框js -->
	<script  type="text/javascript" src="<%=path%>/js/connectionShow.js"></script>
	<script type="text/javascript" src="<%= path %>/js/commonscandate.js"></script>
</body>
</html>
