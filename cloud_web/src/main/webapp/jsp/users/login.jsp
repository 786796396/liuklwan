<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>开普云监管-用户登录-政府网站监测监管服务</title>
		<meta name="keywords" content="政府网站普查,政府网站建设,政府网站监测,政府网站检测,政府网站监管,政府网站监督,网站错别字,网站打不开,网站连通性,国办网站普查,国办网站抽查,政府网站常态化监测,开普互联,全国政府网站普查,国办15号文,电子政务,政府网站整改,政府网站关停,政府网站集约化建设,政府网站纠错,网上办事">
		<meta name="description" content="开普云监管是全国各级政府网站有效的自我监测、站群监管工具。组织单位和填报单位可分别登录使用，建议绑定微信收取预警消息。">
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/heade.jsp"%>
        <link rel="stylesheet" type="text/css" href="<%= path%>/css/new-login.css" />
        <script>
        var _hmt = _hmt || [];
        (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?b90f7fa393b6faa8537070e6b16e53d9";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
        })();
        </script>
	</head>

<body class="bg_fff">
<!-- *******************nav开始****************** -->

<nav class="navbar navbar-default navbar-fixed-top  headerLogin">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=path%>/users_login.action"><img src="<%=path%>/images/logo.png" class="img-responsive"></a>
        </div>
        <div class="navbar-nav navbar-right loginButn">
            <a target="_blank" href="http://www.boxpro.cn/boxpro/p/Eyo-GJyFx" class="btn btn-success btn-sm shenqing" type="button">申 请</a>
            
            <div class="yincang visible-lg visible-md visible-sm">
                <p class="navbar-img pull-right">
                    <a class="btn_pop"><img src="<%=path%>/images/erwei.jpg" width="35" height="35"></a>
                </p>
                <p class="navbar-text pull-right">4000-976-005</p>
                <div class="erwei"><img src="<%=path%>/images/erwei_large.jpg" width="187" height="203"></div>
            </div>
        </div>
    </div>
</nav>

<!-- *******************nav结束****************** -->
<div class="login_container">
    <ul class="nav nav-tabs" id="myTab">
      <li class="active"><a data-toggle="tab" href="#home" onclick="formSubmitOrg()" aria-expanded="true">组织单位<span>(主管)</span></a> </li>
      <li class=""><a class=" text-right" data-toggle="tab" href="#tianb" onclick="formSubmit()" aria-expanded="false">填报单位<span>(主办)</span></a></li>
    </ul>
      <div class="tab-content" id="myTabContent">
        </div>
        
    <div class="line-tip"><i>提示</i></div>
    <div class="dltsnr text-center">
      如果您还没有开普云监管账户，请点此申请开通。
    </div>
    <!--<div class="dltsnr">输入单位在网站普查时所用标识码和校验码，即可登录；
    如无，您可申请开普云监测免费体验账户或付费账户。</div>-->
    <div class="shenqing">
      <a id="loginHref" target="_blank" href="http://www.boxpro.cn/boxpro/p/Eyo-GJyFx" class="btn btn-success btn-sm shenqing" type="button">申 请</a>
    </div>
</div> <!-- /container -->
<div class="login_bg"></div>
<!-- *******************footer开始****************** -->
<div class="new-footer">
    <div class="footnav center-block row">
        <ul class="nav nav-pills">
            <li><a href="http://121.41.102.236:8080/ce/index.shtml">概览</a></li><li>|</li> 
            <li><a href="http://121.41.102.236:8080/ce/texing/fuwu.shtml">特性</a></li><li>|</li>
            <li><a href="http://121.41.102.236:8080/ce/banben/version.shtml">版本</a></li><li>|</li>
            <li class="dropup">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    帮助 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="http://121.41.102.236:8080/ce/zhxg/help.shtml">账户相关</a></li>
                    <li><a href="http://121.41.102.236:8080/ce/jcgn/help.shtml">监测功能</a></li>
                    <li><a href="http://121.41.102.236:8080/ce/wxb/help.shtml">微信版</a></li>
                    <li><a style="border:0px none;" href="http://121.41.102.236:8080/ce/mfjcb/help.shtml">免费检测版</a></li>
                </ul>
            </li>
        </ul>
        <div class="footText">客服热线 4000-976-005</div>
    </div>
    <div class="copyright text-center">
        <div class="container">
            © Copyright 2016. Ucap Info  All Rights Reserve  <span class="fsize12">开普互联版权所有  京ICP备14035494号</span>
        </div>
    </div>
</div>
<!-- *******************footer结束****************** -->
<script type="text/javascript" src="<%= path %>/js/login.js"></script>

<script type="text/javascript">
$(function(){
	if($(window).height()>700){	
		$(".login_footer").css("position","fixed");
	}
	//鼠标滑过弹出层
	function popUp(popBtn,popContent){
		$(popBtn).hover(
			function(){	$(popContent).fadeIn(200);	},
			function(){	$(popContent).fadeOut(200);	}
		)
	}
	popUp('.btn_pop','.erwei');
	
	
	
});
/**
 * 点击更换验证码
 */
function changeCaptcha() {
	$("#captchaImage").attr("src", "<%= path%>/kaptcha.jpg?"+new Date().getTime());
}
</script>

</body>
</html>
