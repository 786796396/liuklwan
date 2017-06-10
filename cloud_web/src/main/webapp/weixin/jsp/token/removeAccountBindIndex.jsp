<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>账号绑定</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>
<body style="background:#fbfbfb;">
<div class="green-bg">您已成功绑定账户！</div>
<div class="login-sub tab-content">
    <div class="tab-pane active">
        <div class="table-box1">
        	<input type="hidden" id="siteCode_id" value="${resultMap.siteCode}"/>
        	<input type="hidden" id="openId_id" value="${resultMap.openId}"/>
            <table cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td class="td-tit">绑定账号：</td><td><span>${resultMap.siteCode}</span></td></tr>
                    <tr><td class="td-tit">账户类型：</td><td><span>${resultMap.custType}</span></td></tr>
                    <tr><td class="td-tit">单位名称：</td><td><span>${resultMap.custName}</span></td></tr>
                    <tr><td class="td-tit">网站总数：</td><td><span>${resultMap.siteNum}个</span></td></tr>
                </tbody>
            </table>
        </div>
    </div><!--**********************/tab-pane************************-->
</div>
<div class="red-btn-box">
	<input class="red-btn" type="button"  id="btn_removeBind_id" value="解除绑定"/>
</div>
<%@ include file="/weixin/jsp/footer.jsp"%>
</body>
<script language="javascript" type="text/javascript">
$(function(){
	var siteCode=$("#siteCode_id").val();
	var openId=$("#openId_id").val();
	//点击解除绑定按钮触发事件
	$("#btn_removeBind_id").click(function() {
		$("#btn_removeBind_id").attr("disabled",true);
		removeBindAccount(siteCode,openId);
	});

});
//解除绑定账户
function removeBindAccount(siteCode,openId){
 	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/token_removeBindAccount.action?siteCode="+siteCode+"&openId="+openId,
        dataType:"json",
        success : function(data){
        	if(data.errormsg){
        		$("#btn_removeBind_id").attr("disabled",false);
        		var errormsg=data.errormsg;
        		alert(errormsg);
        	}else{
				alert(data.success);
				//解除绑定账户后跳转到绑定账户信息页面
				window.location.href=webPath+"/token_accountBindIndex.action?openId="+openId;
        	}
        },error:function(){
		   alert("解除绑定账户失败！");
	   }
     });
}
</script>

</html>
