<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>帐号绑定</title>
	 <%@ include file="/newWeiXin/jsp/common.jsp"%>
  </head>
<body style="background:#fbfbfb;">
<nav>
    <ul class="nav nav-pills nav-justified">
      <li class="active"><a data-toggle="tab" href="#zuz">组织单位(主管)</a></li>
      <li><a data-toggle="tab" href="#tianb">填报单位(主办)</a></li>
    </ul>
</nav>
<div class="account-manage tab-content mar-bom1">
	<input type="hidden" id="openId_id"  value="${initMap.openId}">
	<input type="hidden" id="fromType_id"  value="${initMap.fromType}">
    <div class="tab-pane active" id="zuz">
    	<div class="input-box">
    		<input type="text" placeholder="请输入组织单位帐号" id="siteCode_org_id" />
        </div>
        <div class="input-box">
        	<input type="password" placeholder="请输入密码" id="password_org_id" />
        </div>
        <div class="error-info" id="error_org_id">错误提示</div>
        <div class="btn-box">
        	<button type="button" class="btn btn-success" id="button_org_id">绑  定</button>
        </div>
    </div><!--**********************/zuz************************-->
    <div class="tab-pane" id="tianb">
    	<div class="input-box">
    		<input type="text" placeholder="请输入填报单位帐号" id="siteCode_tb_id" />
        </div>
        <div class="input-box">
        	<input type="password" placeholder="请输入密码" id="password_tb_id" />
        </div>
        <div class="error-info" id="error_tb_id" >错误信息</div>
        <div class="btn-box">
        	<button type="button" class="btn btn-success" id="button_tb_id" >绑  定</button>
        </div>
    </div><!--**********************/tianb************************-->
</div>
<%@ include file="/newWeiXin/jsp/footer.jsp"%>
<script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/accountBind/accountBindIndex.js"></script>

</body>
</html>
