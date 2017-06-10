
//全局变量  发送状态
var checkStatus = {
	"true":"1",
	"false":"0"
};
$(function(){
	var siteCode=$("#siteCode_id").val();
	var openId=$("#openId_id").val();
	if(siteCode.length==6){
		//管理中心页面数据初始化
		getManagementCenter(siteCode,openId);
	}else{
		//管理中心页面--填报单位
		getManagementCenterTB(siteCode,openId);
	}	
	//点击解除绑定按钮触发事件--组织单位
	$("#remove_bind_btn").click(function() {
		$("#remove_bind_btn").attr("disabled",true);
		removeBindAccount(siteCode,openId);
	});
	
	//点击解除绑定按钮触发事件--填报单位
	$("#tb_remove_bind_btn").click(function() {
		removeBindAccount(siteCode,openId);
	});
	//接收设置页面--站群预警通知
	$("#cb2").click(function() {
		var cb2_statusValue=$("#cb2").is(":checked");
		var status=checkStatus[cb2_statusValue];
		changeStatusValue(openId,status,"");
	});
	//接收设置页面--本级门户预警通知
	$("#cb4").click(function() {
		var cb4_statusValue=$("#cb4").is(":checked");
		var status=checkStatus[cb4_statusValue];
		changeStatusValue(openId,"",status);
	});
	//接收设置页面--本级门户预警通知
	$("#cb6").click(function() {
		var cb6_statusValue=$("#cb6").is(":checked");
		var status=checkStatus[cb6_statusValue];
		changeStatusValue(openId,status,"");
	});

});

//填报单位管理中心页面
function getManagementCenterTB(siteCode,openId){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/newToken_getManagementCenterTB.action?siteCode="+siteCode
        +"&openId="+openId,
        dataType:"json",
        success : function(data){
           var returnMap=data;//后台返回数据
           if(returnMap.errormsg){
           		alert(returnMap.errormsg);
           }else{
        	   
           	   /**管理信息页面---基本信息信息*/
	           var baseStr="";
	           //先清空列表数据
	           $("#tbody_base_info").html("");
	           baseStr+="<tr><td class='td-tit'>标识码：</td><td><span>"+returnMap.siteCode+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>帐户类型：</td><td><span>"+returnMap.accountType+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>网站名称：</td><td><span>"+returnMap.siteName+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>主管单位：</td><td><span>"+returnMap.companyName+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>首页url：</td><td><span>"+returnMap.url+"</span></td></tr>";
	         
	           baseStr+="<tr><td class='td-tit'>解除绑定：</td><td><button type='button' class='btn btn-default' id='tb_remove_bind_btn'>解除</button></td></tr>";
	           $("#tbody_base_info").append(baseStr);
	        	
	        	/**接受设置页面数据*/
            	
	        	//先将组织单位的隐藏
	           	errorHide(1);
	        	
	        	//显示填报单位的
	           	errorShow(2);
	        	
	        	//先清空---填报单位接收报告和预警报告
	        	$("#tb_title_id").html("");
	        	$("#tb_title_id").append("<i class='i-point bg-3ca8ef'></i>"+returnMap.companyName);
	        	
	        	
	        	var earlyStatusTB=returnMap.earlyStatusTB;//预警
	        	
	        	//预警
	        	if(earlyStatusTB==0){//不发送（未选中）
	        		$("#cb6").attr("checked",false);
	        	}else if(earlyStatusTB==1){//发送（选中）
	        		$("#cb6").attr("checked","checked");
	        	}
	           }
        },error : function(data){
        	alert(data.errormsg);
        }
     });

}

//组织单位--管理中心页面
function getManagementCenter(siteCode,openId){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/newToken_getManagementCenter.action?siteCode="+siteCode
        +"&openId="+openId,
        dataType:"json",
        success : function(data){
           var returnMap=data;//后台返回数据
           if(returnMap.errormsg){
	           	//接收设置页面隐藏数据
	           	//组织单位隐藏
        	   	errorHide();
           		alert(returnMap.errormsg);
           }else{
           	   /**管理信息页面---基本信息信息*/
	           var baseStr="";
	           //先清空列表数据
	           $("#tbody_base_info").html("");
	           baseStr+="<tr><td class='td-tit'>绑定账号：</td><td><span>"+returnMap.siteCode+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>帐户类型：</td><td><span>"+returnMap.accountType+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>单位名称：</td><td><span>"+returnMap.companyName+"</span></td></tr>"+
	           			"<tr><td class='td-tit'>网站总数：</td><td><span>"+returnMap.siteSum+"个</span></td></tr>"+
	                    "<tr><td class='td-tit'>解除绑定：</td><td><button type='button' class='btn btn-default' id='remove_bind_btn'>解除</button></td></tr>"
	        	
	        	$("#tbody_base_info").append(baseStr);
	        	
	        	/**接受设置页面数据*/
	        	var localhostEarlyStatus=returnMap.localhostEarlyStatus;//本级门户预警
	        	var siteListEarlyStatus=returnMap.siteListEarlyStatus;//站群预警
	        	
	        	//组织单位显示
	        	errorShow(1);
	        	
	        	//隐藏填报单位
	        	errorHide(2);
	        	
	        	//先清空---站群标题
	        	$("#siteList_title_id").text("");
	        	$("#siteList_title_id").append("<i class='i-point bg-3ca8ef'></i>"+returnMap.companyName);
	        	
	        	//站群预警
	        	if(siteListEarlyStatus==0){//不发送（未选中）
	        		$("#cb2").attr("checked",false);
	        	}else if(siteListEarlyStatus==1){//发送（选中）
	        		$("#cb2").attr("checked","checked");
	        	}
	        	//本级门户预警
	        	if(localhostEarlyStatus==0){//不发送（未选中）
	        		$("#cb4").attr("checked",false);
	        	}else if(localhostEarlyStatus==1){//发送（选中）
	        		$("#cb4").attr("checked","checked");
	        	}
	           }
        },error : function(data){
        	//接收设置页面隐藏数据
        	errorHide();
        	alert("获取管理中心页面的数据异常！");
        }
     });
}
//改变预警通知和报告通知接收/不接受
function changeStatusValue(openId,cb2_statusValue,cb4_statusValue){
	
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/newToken_changeStatusValue.action?openId="+openId+
	        "&cb2_statusValue="+cb2_statusValue+
	        "&cb4_statusValue="+cb4_statusValue,
        dataType:"json",
        success : function(data){
        	if(data.errormsg){
        		var errormsg=data.errormsg;
        		alert(errormsg);
        	}else{
        	}
        },error:function(){
		   alert("状态修改失败！");
	   }
    });
}
//解除绑定帐户
function removeBindAccount(siteCode,openId){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/newToken_removeBindAccount.action?openId="+openId,
        dataType:"json",
        success : function(data){
        	if(data.errormsg){
        		$("#remove_bind_btn").attr("disabled",false);
        		var errormsg=data.errormsg;
        		alert(errormsg);
        	}else{
				alert(data.success);
				//解除绑定帐户后跳转到绑定帐户信息页面
				window.location.href=webPath+"/newToken_accountBindIndex.action?openId="+openId
				+"&fromType=managementCenter";
        	}
        },error:function(){
		   alert("解除绑定帐户失败！");
	   }
     });
}
//谁关注了我模块 --数据初始化
function focusInit(siteCode,openId){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/token_focusInit.action?siteCode="+siteCode+"&openId="+openId,
        dataType:"json",
        success : function(data){
        	var returnList=data;
        	var focusStr="";
        	if(returnList.length>0){
        		focusStr+="<ul>"
        		for(var i=0;i<returnList.length;i++){
        			focusStr+="<li><span class='con-tit ellipsis col-xs-8 col-sm-8 col-md-8'>"+returnList[i].nickname+"</span></li>";
        		}
        		focusStr+="</ul>"
        		$("#focus_title_id").attr("style","display:block");//展示标题
        		$("#focus_id").append(focusStr);
        	}else{
        		$("#focus_title_id").attr("style","display:none");//隐藏标题
        	}
        
        }
     });
}
/**
 * type 1-隐藏组织单位  2 隐藏填报单位  3-全部隐藏
 */
function errorHide(type){
	//组织单位隐藏
	if(type==1 || type==3){
		$("#siteList_title_id").attr("style","display:none");
		$("#container_org_siteList_id").attr("style","display:none");
		$("#local_title_id").attr("style","display:none");
		$("#container_org_local_id").attr("style","display:none");
		
	}
	if(type==2 || type==3){
		//填报单位隐藏
		$("#tb_title_id").attr("style","display:none");
		$("#container_tb_id").attr("style","display:none");
	}
}
/**
 * type 1-显示组织单位  2 显示填报单位  3-全部显示
 */
function errorShow(type){
	//组织单位显示
	if(type==1 || type==3){
		$("#siteList_title_id").attr("style","display:block");
		$("#container_org_siteList_id").attr("style","display:block");
		$("#local_title_id").attr("style","display:block");
		$("#container_org_local_id").attr("style","display:block");
		
	}
	if(type==2 || type==3){
		//填报单位显示
		$("#tb_title_id").attr("style","display:block");
		$("#container_tb_id").attr("style","display:block");
	}
}