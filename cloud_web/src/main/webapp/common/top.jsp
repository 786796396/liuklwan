<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
        body{ margin: 0;}
        .guangg_bg{ width: 100%; height: 60px; background: #380454; margin: 0 auto;}
        .guangg_content{ position:relative; width:996px; height: 60px; <%-- background: url("<%=path%>/images/ad_pic.png") no-repeat center center; --%> margin:0 auto; cursor: pointer;}
         .guangg_close-iocn{ position: absolute;top: 4px; right: 4px; width: 20px; height: 20px; background: url("<%=path%>/images/close-icon1.png") no-repeat center center;}
  </style>
<title></title>
</head>
<body class="bg_f5f5f5">
 	<!--头部       satrt  -->
	<div class="header">
		<input type ='hidden' value="${sessionScope.shiroUser.siteCode}" id="shirUserCode">	
		<input type ='hidden' value="${sessionScope.shiroUser.currentCount}" id="currentCount">	
		<input type ='hidden' value="${sessionScope.shiroUser.nextCount}" id="nextCount">	
		<input type ='hidden' value="${sessionScope.shiroUser.exceptionCount}" id="exceptionCount">	
		<input type ='hidden' value="${sessionScope.shiroUser.closeCount}" id="closeCount">	
		<input type ='hidden' value="${sessionScope.shiroUser.otherCount}" id="otherCount">	

		<div class="guangg_bg" id="advertDiv" style="display: none">
			<div class="guangg_content">
				<script type="text/javascript" src="<%=path%>/js/advert.js?v=1"
					encodead="ad_1_1_1" id="_ad_"></script>
				<i class="guangg_close-iocn"
					onclick="hideAdvert();event.cancelBubble=true"></i>
			</div>
		</div>

		<div class="header-content clearfix">
			<div class="left-part fl">
				<div class="logo fl" id="logoID"></div>
				<%-- <i class="publi-ico_2 fgx-b fl"></i> <a id="aID" class="fl">
					<img src="<%=path%>/images/common/yjg-ziyang.png" alt="" />
				</a> --%>
			</div>
			
			<!-- top部分  satrt -->
			<div class="center-part fl" id="center-part">
				<ul class="nav-part fl o" id="ulId">
				</ul>
				<ul class="nav-part fl t" id="ulTwId">
				</ul>
				<ul class="nav-part fl" id="ulGdId">
				</ul>
			</div>
			<!-- top部分  end -->
			
			<div class="right-part fr">
				<ul id="glUlId" class="clearfix">
					<li id="logOut" class="indexTop "><span class="tuichu publi-ico_2" title="退出"></span> <em class=""></em>
					</li>
					<li class="publi-ico_2 fgx-s indexTop"></li>
					<li class="indexTop"><a target="_blank"
						href="https://boxpro.cn/boxpro/p/4kLdCq_NZ"><span
							class="bzsc publi-ico_2" title="使用手册"></span> </a><em class="active"></em>
					</li>
 					<li class="publi-ico_2 fgx-s indexTop"></li>
					<li id="glLiId" class="indexTop"></li>
					<li class="publi-ico_2 fgx-s indexTop"></li>
					<!-- <li class="yujing-part indexTop"><span class="yujing publi-ico_2"></span>
						<em class=""></em> <i class="yujing-tip">99+</i></li> 
					<li class="publi-ico_2 fgx-s"></li>-->
				</ul>
			</div>
			<div class="wecat-part fr" style="display:  block">
				<div class="wecat-content" id="weChatId">
					<span class="publi-ico_2 ewm-ico-new"> </span> 
					<i class="publi-ico_2 down-ico-2"></i>
				</div>

				<div class="wecat-sh" id="weChatDetailId" style="display:  none">
					<div class="sh-top clearfix">
						<div class="fl left-box">
							<img src="<%=path%>/images/ewm-133.png" alt="" />
						</div>
						<div class="wecat-list-box">
							<!-- <ul>
								<li class=""><i class="ckwz publi-ico_2"></i> <span>查看网站状况</span>
								</li>
								<li class="fgx-l"></li>
								<li class=""><i class="jsyj publi-ico_2"></i> <span>接收预警消息</span>
								</li>
							</ul> -->
							<ul>
								<li><img src="<%=path%>/images/circles-ico1.png" alt="" /><span>扫码关注</span></li>
								<li><img src="<%=path%>/images/circles-ico2.png" alt="" /><span>输入开普云登录账户和密码</span></li>
								<li><img src="<%=path%>/images/circles-ico3.png" alt="" /><span>查看检测数据,接收预警信息</span></li>
								<div class="left-line"></div>
							</ul>
							<div class="close-img"><img src="<%=path%>/images/close_shape.png" alt="" /></div>
							
						</div>
					</div>
					<div class="sh-bottom">
						<h4>微信公众号：开普云</h4>
						<span>快快绑定微信吧！更多功能陆续开放中...</span>
					</div>
					<i class="mty-ico publi-ico_2"></i>
				</div>
			</div>
		</div>
	</div>

	<!--头部       end  -->

	<div class="base-info">
		<div class="bi-content clearfix">
			<i class="colo-f publi-ico_2"></i>
			<h4 class="fl">${sessionScope.shiroUser.siteCode }- ${sessionScope.shiroUser.userName }</h4>
			<div class="sub-success" style="display: none" id="submitSuccess">提交成功</div>
			<div class="fr">
				<ul>
					<li class="fr">数据更新时间：${sessionScope.shiroUser.updateDate }</li>
					<!--<li class="fgx-s publi-ico_2 fr"></li>
					<li class="fr jies"><a href="javascript:void(0)" class="colo-38ba98" onclick="testYun()"> <i 
 							class="publi-ico_2 zbjs"></i> 试用云专题 
					</a></li> 
					 <li class="fr jies"><a href="" class="colo-38ba98"> <i
							class="publi-ico_2 zbjs"></i> 指标解释 
					</a></li> -->
				</ul>
			</div>
		</div>
	</div>
<form id="yunformUrl" name="yunformUrl" method="post">
<input name="data" type="hidden" value="" id="testYunUrl"/>
</form>
</body>
<%@ include file="/common/exitmodal.jsp"%>
<script type="text/javascript" src="<%=path%>/js/common/header.js?<%= autoVersoin %>"></script>

</html>	