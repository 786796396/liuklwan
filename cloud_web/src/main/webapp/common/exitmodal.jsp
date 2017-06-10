<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Modal -->
<div id="exitModal" class="page-modal modal fade hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=" width:500px; margin-left:-250px;">
  <div class="modal-header modal-header2">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close2"></i></button>
    <h3 id="myModalLabel">提示</h3>
  </div>
  <div class="modal-body" style="max-height:none;">
    <p class="exit-p">是否要退出开普云政府网站云监管平台？</p>
  </div>
    <div class="modal-footer">
      <a href="javascript:logOut()" class="btn btn-success green-btn">确  定</a>
      <button id="closeExitModal" data-dismiss="modal" class="btn white-btn">取  消</button>
    </div>
</div>
<script type="text/javascript">
	$("#logOut").click(function(){
		$('#exitModal').modal('show');
		sessionStorage.removeItem("key");
		sessionStorage.removeItem("isFirstJoin");
		
		//退出时清除cookie里的值
		//组织
		$.cookie('type_cookie', null, {path:'/'});   
		$.cookie('top_cookie', null, {path:'/'});   // 一级菜单  头部
		$.cookie('twoMenu_cookie', null, {path:'/'});  // 二级菜单  
		//填报
		$.cookie('typeTB_cookie', null, {path:'/'});
		$.cookie('topTB_cookie', null, {path:'/'});  // 一级菜单  头部
		$.cookie('twoMenuTB_cookie', null, {path:'/'}); // 二级菜单  
	});
	$("#closeExitModal").click(function(){
		$('#exitModal').modal('hide');
	});
	//判断是从收费登录页面登录还是免费登录页面登录
	function logOut(){
		sessionStorage.removeItem("key");
		var logOut = '${sessionScope.loginout}';
		
		if(logOut=='loginout'){
			
			window.location.href = 'users_logOut.action';
			
		}else if(logOut=='loginsout'){
			
			window.location.href = 'users_logsOut.action';
			
		}
		sessionStorage.removeItem("isFirstJoin");
	}
</script>