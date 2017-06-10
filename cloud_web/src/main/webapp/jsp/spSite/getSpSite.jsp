<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>云监测 管理-个性化设置</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/base.css"/>
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-new.css"/>
</head>
<body class="control_info-config bg_fff">
<!-- <div class="page-wrap bg_fff"> -->
	<%@ include file="/common/top.jsp"%>
<div class="main-detail">
<div class="main-detail-content"> 
	<c:set var="ba_index" value="1" scope="request" />
	<c:set var="menu" value="#menuFx1" scope="request" />
	<%@ include file="/common/leftmenu.jsp"%>
	<input type='hidden' id='type11'  value='${type1}'>
	<input type='hidden' id='type22'  value='${type2}'>
	<input type='hidden' id='flag'  value='${flag}'>
	<input type='hidden' id='idid'  value='${spSite.id}'>
	<input type='hidden' id='isShow'  value='${isShow}'>
	<input type='hidden' id='displayModle'  value='${spSite.displayModule}'>
	<div class="page-content" style="overflow:hidden;">
            <!--专属页面配置头部开始-->
            <div class="info-setting_top">
                <div class="two_part clearfix">
                    <div class="Station-group fl on change_tab">
                    <c:if test="${flag ==0 }">
                        <span>专属页面</span>
                    </c:if>
                    <c:if test="${flag !=0 }">
                        <span>个性化信息设置</span>
                    </c:if>
                        <i class="left"></i>
                    </div>
                </div>
                <div class="line"></div>
                <c:if test="${flag ==0 }">
                 <p id="pageHover">
                    <i></i>
                   	 如何配置专属页面？
                </p>
                </c:if>
            </div>
            <!--专属页面配置头部结束-->
            <!--专属配置内容部分开始-->
            <div class="info-setting_content">
                <div class="group change_tab_content on">
<!--                 无数据 -->
				<c:if test="${flag ==0 }">
                	<div class="template">
                        <p class="login-page_m">专属页面是为您单独定制的登陆页面，此页面只会展示和您网站相关的数据。</p>
                        <div class="clearfix mub-box">
                            <div class="fl item">
                                <div class="img-box"  onclick="previewImg1()">
                                    <img src="<%=path%>/images/customMade/muban_1.jpg" alt="模板一"/>
                                    <div class="opa-bg"></div>
                                    <div class="fangd_icon"></div>
                                </div>
                                <p>模板-001</p>
                                <c:if test="${type1>0 }">
                                	<p class="using-ziy">（使用中）</p>
                                </c:if>
                                 <c:if test="${type1==0 }">
                                	<p class="wei-ziy">（未启用）</p>
                                </c:if>
                                </div>
                            <div class="fl">
                                <div class="img-box"  onclick="previewImg2()">
                                    <img src="<%=path%>/images/customMade/muban_2.jpg" alt="模板二"/>
                                    <div class="opa-bg"></div>
                                    <div class="fangd_icon"></div>
                                    <div class="new_icon">NEW</div>
                                </div>
                                <p>模板-002</p>
                                 <c:if test="${type2>0 }">
                                	<p class="using-ziy">（使用中）</p>
                                </c:if>
                                 <c:if test="${type2==0 }">
                                	<p class="wei-ziy">（未启用）</p>
                                </c:if>
                            </div>
                            <div class="evlapp">
                            	<ol>
                            		<li><a href="javascript:void(0);">1. 联系下方销售人员购买服务</a></li>
                            		<li><a href="javascript:void(0);">2. 点击模板进行配置</a></li>
                            		<li><a href="javascript:void(0);">3. 配置完成后保存</a></li>
                            	</ol>
                            </div>
                        </div>

                    </div>
                    </c:if>
<!--                 有数据 -->
                <c:if test="${flag ==1 }">
                    
                    <div class="base-info-part module-part">
                        <div class="clearfix">
                            <div class="fl left-tit">
                                <i></i>
                                <span>基本信息</span>
                            </div>
                            <em></em>
                        </div>
                        <div class="base-info-content">
                            <div class="every-line clearfix">
                                <span class="tit-left fl"> 网站标识码：</span>
                                <div class="content-right fl colo-7e">${spSite.siteCode}</div>
                            </div>
                            <div class="every-line clearfix">
                                <span class="tit-left fl"> 登录URL地址：</span>
                                <div class="content-right fl">
                                   <p>
                                       <span class="colo-64"  id="urlUrl">${spSite.domain}</span>
                                        <input type="hidden" id="uuUrl" value="${spSite.domain}">
                                       <i class="copy-url"  id="copyUrl">复制URL链接</i>
                                   </p>
                                </div>
                            </div>
                            <div class="every-line clearfix">
                                <span class="tit-left fl">服务有效期限：</span>
                                <div class="content-right fl colo-7e" >${fn:substring(spSite.effectiveBeginDate, 0, 4) }年 ${fn:substring(spSite.effectiveBeginDate, 5, 7) }月 ${fn:substring(spSite.effectiveBeginDate, 8, 10) }日  &nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp; ${fn:substring(spSite.effectiveEndDate, 0, 4) }年 ${fn:substring(spSite.effectiveEndDate, 5, 7) }月 ${fn:substring(spSite.effectiveEndDate, 8, 10) }日</div>
                            </div>
                        </div>
                    </div>
                    <div class="module-config-part module-part ">
                        <div class="clearfix">
                            <div class="fl left-tit">
                                <i></i>
                                <span>模块配置</span>
                            </div>
                            <em></em>
                        </div>
                        <div class="module-config-content">
                            <div class="every-line clearfix">
                                <span class="tit-left fl">条形LOGO：</span>
                                <div class="content-right fl">
                                    <p>
                                        <i class="add-logo AddLogo_btn">添加条形LOGO</i>
                                        <form id="formUpload" action="" method="post" enctype="multipart/form-data">
											<input type="hidden" id="siteCode" value="${spSite.siteCode}">
                            				<input type="file"  name="logoName" id="logoName"  style="display: none;">
                            				<!-- <p class="logo-size fl">推荐尺寸：最大高度100px；格式为jpg,png,最好为透明背景;大小:1MB以内<br/></p> -->
                        				</form>
                                        <span class="colo-95">logo最大高度为75像素，宽度按照比例。 格式为png透明背景 ，logo文件大小要小于200KB。</span>
                                    </p>
                                    <div class="AddLogo_area"  id="AddLogo_area">
                                        <c:if test="${spSite.logo != '' && spSite.logo != null}"> 
                    						<img  src="${spSite.logo}"  style="height:60px"/>
                    					 </c:if> 
											 <input type='hidden' id='logo'  value='${spSite.logo}'>	
                                    </div>
                                </div>
                            </div>
                            <div class="every-line clearfix">
                                <span class=" fl copyRight-tit">版权信息：</span>
                                <div class="content-right fl colo-7e">
                                    <input type="text" id="bottomText" value="${spSite.bottomText }"/>
                                </div>
                            </div>
                            <div class="every-line clearfix">
                                <span class="tit-left fl"> 显示模块配置：</span>
                                <div class="content-right fl colo-7e">
                                    <div class="checkboxs-box fl">
                                        <div class="fl">
                                            <input type="checkbox" id="flag11"  <c:if test="${fn:contains(spSite.displayModule,'1')}">checked</c:if> />
                                            <label for="flag11">日常监测数据</label>
                                        </div>
                                        <c:if test="${isShow==1}">
                                        <div class="fl">
                                            <input type="checkbox" id="flag12"   <c:if test="${fn:contains(spSite.displayModule,'2')}">checked</c:if> />
                                            <label for="flag12">网站概况</label>
                                        </div>
                                        </c:if>
                                        <div class="fl">
                                            <input type="checkbox" id="flag13"   <c:if test="${fn:contains(spSite.displayModule,'3')}">checked</c:if> />
                                            <label for="flag13">栏目</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn-part clearfix">
                        <div class="com-btns preview-btn fl" onclick="previewImg()">预览</div>
                        <div class="com-btns save-btn fl">提交</div>
                    </div>
                    </c:if>
                    
                    
                    
                    <c:if test="${flag ==2 }">
                    <div class="base-info-part module-part">
                        <div class="clearfix">
                            <div class="fl left-tit">
                                <i></i>
                                <span>基本信息</span>
                            </div>
                            <em></em>
                        </div>
                        <div class="base-info-content">
                            <div class="every-line clearfix">
                                <span class="tit-left fl"> 网站标识码：</span>
                                <div class="content-right fl colo-7e">${spSite.siteCode}</div>
                            </div>
                            <div class="every-line clearfix">
                                <span class="tit-left fl"> iframe调用地址：</span>
                                <div class="content-right fl">
                                   <p>
                                       <span class="colo-64" id="urlUrl">${spSite.url}</span>
                                       <input type="hidden" id="uUrl" value="${spSite.url}">
                                       <i class="copy-url" id="copyUrl">复制URL链接</i>
                                   </p>
                                    <div class="colo-7e">(复制链接，使用iframe引用即可将平台的数据放置在您的网站上)</div>
                                </div>
                            </div>
                            <div class="every-line clearfix last-line">
                                <span class="tit-left fl">服务有效期限：</span>
                                <div class="content-right fl colo-7e"  >${fn:substring(spSite.effectiveBeginDate, 0, 4) }年 ${fn:substring(spSite.effectiveBeginDate, 5, 7) }月 ${fn:substring(spSite.effectiveBeginDate, 8, 10) }日  &nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp; ${fn:substring(spSite.effectiveEndDate, 0, 4) }年 ${fn:substring(spSite.effectiveEndDate, 5, 7) }月 ${fn:substring(spSite.effectiveEndDate, 8, 10) }日</div>
                            </div>
                        </div>
                    </div>
                    <div class="module-config-part module-part ">
                        <div class="clearfix">
                            <div class="fl left-tit">
                                <i></i>
                                <span>模块配置</span>
                            </div>
                            <em></em>
                        </div>
                        <div class="module-config-content">
                            <div class="every-line clearfix">
                                <span class="tit-left fl"> 显示模块配置：</span>
                                <div class="content-right fl colo-7e">
                                    <div class="checkboxs-box fl">
                                        <div class="fl">
                                            <input type="checkbox" id="flag21" <c:if test="${fn:contains(spSite.displayModule,'1')}">checked</c:if> />
                                            <label for="flag21">日常监测数据</label>
                                        </div>
                                        <c:if test="${isShow==1}">
	                                        <div class="fl">
	                                            <input type="checkbox" id="flag22"  <c:if test="${fn:contains(spSite.displayModule,'2')}">checked</c:if> />
	                                            <label for="flag22">网站概况</label>
	                                        </div>
                                        </c:if>
                                        <div class="fl">
                                            <input type="checkbox" id="flag25"  <c:if test="${fn:contains(spSite.displayModule,'5')}">checked</c:if> />
                                            <label for="flag25">政府网站基础信息数据库</label>
                                        </div>
                                        <div class="fl">
                                            <input type="checkbox" id="flag26"  <c:if test="${fn:contains(spSite.displayModule,'6')}">checked</c:if> />
                                            <label for="flag26">政府网站网民纠错数据</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn-part clearfix">
                        <div class="com-btns preview-btn fl" onclick="previewImg()">预览</div>
                        <div class="com-btns save-btn fl">提交</div>
                    </div>
                    </c:if>
                    
                </div>
            </div>
            <!--消息设置内容部分结束-->
            <div >
				   <%@ include file="/common/footer.jsp"%>	
			</div>
        </div>	
	</div>
</div>
<!-- </div>	 -->
	<script type="text/javascript" src="<%=path%>/js/spSite/spSite.js"></script>
</body>
</html>
