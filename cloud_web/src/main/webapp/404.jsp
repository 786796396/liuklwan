<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>404 报错页面</title>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/heade.jsp"%>
	</head>

	<body>
		<div class="navbar navbar-rel">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="brand" href="<%=path%>/users_login.action" target="_blank"><img alt="logo" src="<%=path%>/images/common/logo.png" /></a>
					<div class="nav-collapse">
						<div class="pull-left navbar-logotit">
                            政府网站云监管平台
						</div>
						<p class="navbar-text pull-right">
							服务热线：400-876-5486
						</p>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<div class="container_404">
			<div class="img_404">
				<img alt="404" src="<%=path%>/images/common/404.jpg" />
			</div>
			<div class="tit_404">
				您访问的页面无法找到
			</div>
			<div class="info_404">
				如您是在地址栏输入网址的，请确认其拼写正确。 注意：大多数网址是区分大小写的。
			</div>
			<a class="a_404" href="<%=path%>">返回首页</a>
		</div>
		<!-- /container -->
		<footer class="fixed_footer">
			<p>
				© Copyright <span class="footer-year">2015</span>. Ucap Info All Rights Reserved
			</p>
		</footer>
	</body>
</html>