<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
        <meta charset="utf-8">
        <title>关注并绑定微信账号</title>
        <%@ include file="/common/meta.jsp"%>
        <%@ include file="/common/heade.jsp"%>
        <script language="javascript" type="text/javascript" src="<%= path %>/js/fillUnit/weixin.js"></script> 
    </head>
    <body>
    	<input id="weixin_code_id" type="hidden" value="${siteCode}">
    	<input id="weixin_erweima_url" type="hidden" value="${urlStr}"> 
        <img class="fuzzy-bg" alt="模糊背景" src="<%= path %>/images/fuzzy2-bg.jpg" />
        <div class="binding-logo">
            <a class="navbar-brand" href="#"><img src="<%= path %>/images/logo.png" class="img-responsive"></a>
        </div>
        <div class="binding-container">
            <!-- <div class="erwei-img"><img id="img_id" alt="关注并绑定微信账号"/></div>
            <div class="binding-font1">请扫描二维码绑定账号</div>
            <div class="binding-font2">（首次使用监管系统须通过微信进行账号绑定）</div>
            <ul class="binding-url">
                <li>
                    <i class="weix"></i>
                    <p class="mar2">手机登录微信</p>
                </li>
                <li>
                    <i class="sao"></i>
                    <p>从微信发现，进入“扫一扫”<br/>扫描二维码关注微信并绑定您的账号</p>
                </li>
                <li>
                    <i class="binding-sub"></i>
                    <p>绑定成功<br/>最新监测动态，尽在“掌”握</p>
                </li>
            </ul> -->
            <div class="erweima">
            	<img id="img_id" alt="关注并绑定微信账号"/>
            </div>
            <div class="focus_on">
            	扫码关注官方微信公众号：<span>开普云监管</span>
            </div>
            <div class="gby erwei_hide-right">
            	<p>微信接收网站异常预警</p>
            	<div>
                    <i class="blue"></i>
                    <span>网站不连通 </span>
                </div>
                <div>
                    <i class="yellow"></i>
                    <span>严重错别字</span>
                </div>
                <div>
                    <i class="green"></i>
                    <span>首页超过10天未更新</span>
                </div>
            </div>
            <div style="position:absolute; bottom:0; left:0; height:32px; width:100%;">
            	<p style="padding-left:42px; font-size:12px; color:#b7b7b7; line-height:32px; height:32px; border-top:1px solid #e5e5e5;">一个微信号仅能绑定一个开普云监管账号</p>
            </div>
        </div>
        <div class="binding-footer">© Copyright 2016   开普互联版权所有</div>
    </body>
</html>
