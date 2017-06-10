
var openId="";
var fromType="";
$(function(){
	openId=$("#openId_id").val();
	fromType=$("#fromType_id").val();
	//组织单位点击按钮，触发事件
	$("#button_org_id").click(function(){
		//初始化该页面时，给全局变量赋值
		var siteCode=$("#siteCode_org_id").val();
		var password=$("#password_org_id").val();
		
		if(siteCode.length!=6){
    		$("#error_org_id").attr("style","display:block");
    		$("#error_org_id").html("组织单位客户编号必须是6位！");
		}else{
			//点击绑定之后，使其状态变为不可点击
			$("#button_org_id").attr("disabled",true);

			//组织单位保存绑定帐户信息
			saveAccountBind(openId,siteCode,password,fromType);
		}

	});
		
	//填报单位点击按钮触发事件
	$("#button_tb_id").click(function(){
		//初始化该页面时，给全局变量赋值
		var siteCode=$("#siteCode_tb_id").val();
		var password=$("#password_tb_id").val();
		if(siteCode.length!=10){
    		$("#error_tb_id").attr("style","display:block");
    		$("#error_tb_id").html("填报单位客户编号必须是10位！");
		}else {
			//点击绑定之后，使其状态变为不可点击
			$("#button_tb_id").attr("disabled",true);
			//组织单位保存绑定帐户信息
			saveAccountBind(openId,siteCode,password,fromType);
		}

	});
});
	//保存绑定帐户信息
	function saveAccountBind(openId,siteCode,password,fromType){
		 var jsonObj = {};
			jsonObj["openId"] = openId;
			jsonObj["password"] = password;
			jsonObj["siteCode"] = siteCode;
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/newToken_saveAccountBind.action",
	        dataType:"json",
	        data: JSON.stringify(jsonObj),
	        contentType: "application/json",
	        success : function(data){
	        	if(data.errormsg){
	        		if(siteCode.length==6){
	        			$("#button_org_id").attr("disabled",false);
	        		}else{
	        			$("#button_tb_id").attr("disabled",false);
	        		}
	        		
	        		var errormsg=data.errormsg;
	        		if(siteCode.length==6){
		        		//先清空
		        		$("#error_org_id").html("");
		        		$("#error_org_id").attr("style","display:block");
		        		$("#error_org_id").html(errormsg);
	        		}else{
		        		//先清空
		        		$("#error_tb_id").html("");
		        		$("#error_tb_id").attr("style","display:block");
		        		$("#error_tb_id").html(errormsg);
	        		}
	        	}else{
	        		if(fromType=="checkResult"){//如果当前客户未绑定帐户，点击了检测结果，跳转到绑定帐户页面，待帐户绑定成功后，再跳转到检测结果页面
	        			alert(data.success);
	        			window.location.href=webPath+"/newToken_checkResultTBIndex.action?openId="+openId;
	        			return false;
	        		}else if(fromType=="managementCenter"){//如果当前客户未绑定帐户，点击了管理中心，跳转到绑定帐户页面，待帐户绑定成功后，再调回到管理中心页面
	        			alert(data.success);
	        			window.location.href=webPath+"/newToken_managementCenterIndex.action?openId="+openId;
	        			return false;
	        		}else{
	        			alert(data.success);
	        			window.location.href=webPath+"/newToken_removeAccountBindIndex.action?openId="+openId+"&siteCode="+siteCode;
	        			return false;
	        		}
	        	}
	        	
	        }
	     });
	}