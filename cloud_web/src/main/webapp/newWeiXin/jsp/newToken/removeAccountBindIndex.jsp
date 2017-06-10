<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>帐号绑定</title>
	 <%@ include file="/newWeiXin/jsp/common.jsp"%>
  </head>
<body style="background:#fbfbfb;">
<div class="green-bg">您已成功绑定帐户！</div>
<div class="login-sub tab-content">
    <div class="tab-pane active">
        <div class="table-box1">
            <table cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td class="td-tit">绑定帐号：</td><td><span>${initMap.siteCode}</span></td></tr>
                    <tr><td class="td-tit">帐户类型：</td><td><span>${initMap.custType}</span></td></tr>
                    <tr><td class="td-tit">单位名称：</td><td><span>${initMap.custName}</span></td></tr>
                    <tr><td class="td-tit">网站总数：</td><td><span>${initMap.siteNum}个</span></td></tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="/newWeiXin/jsp/footer.jsp"%>
</body>


</html>
