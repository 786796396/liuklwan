<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>填报单位-信息管理</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/base.css"/>
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<style type="text/css">
    #startHour,#endHour{     
    border-radius: 0;
    width: 169px;
    padding: 0 0 0 10px;
    height: 28px;
    margin-bottom: 0px; 
    }
</style>
</head>
<body>
<input type="hidden" id="earlyType" value="${cEarly.earlyType }">
<input type="hidden" id="siteCode" value="${cEarly.siteCode}">
<input type="hidden" id="startHourP" value="${cEarly.startHour }">
<input type="hidden" id="endHourP" value="${cEarly.endHour}">
<div class="page-wrap bg_fff">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="2" scope="request"/>
		<!-- <c:set var="menu" value="#menuLjkyx" scope="request"/> -->
       <%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content" style="padding-top: 115px;">
            <!--消息设置头部开始-->
            <div class="info-setting_top">
                <div class="two_part clearfix">
                    <div class="Station-group fl on change_tab">
                        <span>单站预警消息</span>
                        <i class="left"></i>
                    </div>
                </div>
                <div class="line"></div>
            </div>
            <!--消息设置头部结束-->
            <!--消息设置内容部分开始-->
 			 <div class="info-setting_content">
                <div class="single change_tab_content on">
                    <div class="Index_items  clearfix every_part">
                    	<input id="errmsg_id" type="hidden" value="${errmsg}">
                        <div class="Index_items_left fl every_title">单站预警的指标项：</div>
                        <div class="Index_items_right fl">
                            <p> <label><input type="checkbox" id="homeConnection" value="${cEarly.homeConnection}" name="homeConnection"/> 首页不连通实时预警</label></p>
                            <p> <label><input type="checkbox" id="homeConnectionPer" value="${cEarly.homeConnectionPer}" name="homeConnectionPer"/> 首页不连通率达到3% </label><span>（十五号文的规定 首页不连通率达到5%即单项否决）</span> </p>
                            <p> <label><input type="checkbox" id="correctContent" value="${cEarly.correctContent}" name="correctContent"/> 网站上出现疑似错别字</label></p>
                            <p> <label><input type="checkbox" id="modifySite" value="${cEarly.modifySite}" name="modifySite"/> 网站疑似被挂码或被篡改</label></p>
                            <p> <label><input type="checkbox" id="notUpdateHome" value="${cEarly.notUpdateHome}" name="notUpdateHome"/> 首页超过10天未更新</label><span>（十五号文规定首页超过两周未更新即单项否决）</span></p>
                            <p> <label><input type="checkbox" id="blankChannel" value="${cEarly.blankChannel}" name="blankChannel"/> 空白栏目超过2个</label><span>（人工深度审核，十五号文的规定空白栏目数量超过5个即单项否决）</span></p>
                            <p> <label><input type="checkbox" id="notUpdateChannel" value="${cEarly.notUpdateChannel}" name="notUpdateChannel"/> 超过6个栏目不更新</label><span>（ 人工深度审核，十五号文的规定栏目不更新的数量超过10个即单项否决）</span></p>
                            <p> <label><input type="checkbox" id="securityResponse" value="${cEarly.securityResponse}" name="securityResponse"/> 互动回应栏目超过3个月未回应</label><span>（人工深度审核， 十五号文的规定栏目互动回应栏目超过3个月不回应即单项否决）</span></p>
                        </div>
                    </div>
                    <div class="receive_y_or_n-two clearfix every_part">
                        <div class="receive_y_or_n-left fl every_title">是否接收预警通知：</div>
                        <div class="receive_y_or_n-right fl">
                        	<input type="hidden" value="${cEarly.isSiteReceive}" id="isSiteReceive">
                            <p> <label><input type="radio" value="1" name="isSiteReceive"/>接收</label></p>
                            <p> <label><input type="radio" value="2" name="isSiteReceive"/>不接收</label></p>
                            <p class="instructions">汇总网站每日的几大指标问题，每日发送</p>
                        </div>
                    </div>
                    <div class="receive_Members  clearfix every_part">
                        <div class="receive_Members_left fl every_title">接收成员：</div>
                        <div class="receive_Members_right fl">
                            <p> <label><input type="checkbox" name="isPrincipalReceive" <c:if test="${cEarly.isPrincipalReceive==1 }">checked</c:if> name="isPrincipalReceive"/> 网站负责人</label><span>（ ${cEarly.principalName}&nbsp;&nbsp;${cEarly.telephone}&nbsp;&nbsp;${cEarly.email }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_indexTB.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isLinkmanReceive" <c:if test="${cEarly.isLinkmanReceive==1 }">checked</c:if> name="isLinkmanReceive"/> 业务联系人</label><span>（ ${cEarly.linkmanName}&nbsp;&nbsp;${cEarly.telephone2}&nbsp;&nbsp;${cEarly.email2}）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_indexTB.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isLinkmanTwo" <c:if test="${cEarly.isLinkmanTwo==1 }">checked</c:if> name="isLinkmanTwo"/> 业务联系人</label><span>（ ${cEarly.linkmanNameTwo}&nbsp;&nbsp;${cEarly.linkmanPhoneTwo}&nbsp;&nbsp;${cEarly.linkmanEmailTwo}）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_indexTB.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isLinkmanThree" <c:if test="${cEarly.isLinkmanThree==1 }">checked</c:if> name="isLinkmanThree"/> 业务联系人</label><span>（ ${cEarly.linkmanNameThree}&nbsp;&nbsp;${cEarly.linkmanPhoneThree}&nbsp;&nbsp;${cEarly.linkmanEmailThree}）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_indexTB.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                        </div>
                    </div>
                    <div class="setting_infor_way clearfix every_part">
                        <div class="setting_infor_way_left fl every_title">设置消息方式：</div>
                        <div class="setting_infor_way_right fl">
                            <p> <label><input type="checkbox" id="isWxReceive" value="${cEarly.isWxReceive}" name="isWxReceive"/>微信消息</label></p>
                            <p> <label><input type="checkbox" id="isEmailReceive" value="${cEarly.isEmailReceive}" name="isEmailReceive"/>邮件消息</label></p>
                            <p> <label><input type="checkbox" id="isTelReceive" value="${cEarly.isTelReceive}" name="isTelReceive"/>短信消息 
                            <input class="Wdate" type="text" id="startHour" onfocus="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'endHour\',{H:-1})}'})"> 
                             <span >至</span>
                        	<input class="Wdate" type="text" id="endHour" onClick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'startHour\',{H:+1})}'})"> 
                            </label></p>
                            <p class="instructions">请扫描二维码关注“开普云监管”，绑定您的登录账号，即可接收微信预警：</p>
                        </div>
                    </div>
                    <div class="erweima_part clearfix every_part">
                        <div class="erweima_part_left fl every_title"></div>
                        <div class="erweima_part_right fl">
                            <img src="<%=path%>/images/info_setting_erweima_img.jpg" alt="二维码"/>
                        </div>
                    </div>
                    <div class="insure_part clearfix every_part">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" onclick="updateInfoTb()">
                            确定
                        </div>
                    </div>
                </div>
            </div>
            <!--消息设置内容部分结束-->
             <%@ include file="/common/footer.jsp"%>	
             <!-- /page-footer -->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>

<input type="hidden" value="${nextChangeLast}" id="nextChangeLast">
<input type ='hidden' value="3" id="guideTopTB">
<script language="javascript" type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/configEarly/configEarlyTB.js"></script>
<script language="javascript" type="text/javascript">
$(function(){
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
