var siteCode="";
var servicePeroidId="";
$(function(){
    siteCode=$("#siteCode_id").val();
    servicePeroidId=$("#servicePeroidId_id").val();
    //初始化页面数据
    initLinkAllTbDate();
    
});

function initLinkAllTbDate(){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/advanceToken_getLinkAllTbData.action?siteCode="+siteCode
        +"&servicePeroidId="+servicePeroidId,
        dataType:"json",
        success : function(data){
           var resultMap=data;//后台返回数据
           if(resultMap.errormsg){
           		alert(resultMap.errormsg);
           }else{
        	   //清空数据
        	   $("#link_all_tb_confirm_id").html("");
        	   $("#link_all_tb_not_confirm_id").html("");
        	   $("#link_all_tb_no_data_id").html("");
        	   var confirmStr="";
        	   var notConfirmStr="";
        	   var noDataStr="";
        	   
        	   var confrimList=resultMap["confrimList"];//确认死链
        	   var notConfirmList=resultMap["notConfirmList"];//疑似死链
        	   
        	   $("#link_all_tb_confirm_id").show();
        	   $("#link_all_tb_not_confirm_id").show();
        	   $("#link_all_tb_no_data_id").hide();
        	   
        	   //确认死链
        	   if(confrimList && confrimList.length>0){
        		   confirmStr+="确定不可用的链接<div>";
        		  var conLen=parseInt(confrimList.length/2);
        		  for(var j=0;j<conLen;j++){
        			  confirmStr+="<div class='two-state clearfix'><div class='fl'>"
        				  +"<span class='numbs'>"+confrimList[j*2]["errorNum"]+"</span>"
        				  +"<p class='state-descr'>"+confrimList[j*2]["questionDescribe"]+"</p></div>";
        			  if(confrimList.length>1){
        				  confirmStr+="<div class='fr'><span class='numbs'>"+confrimList[j*2+1]["errorNum"]+"</span>"
        			  	  +"<p class='state-descr'>"+confrimList[j*2+1]["questionDescribe"]+"</p></div></div>";
        			  }
        		  }
        		  var confirmFlag=isTwo(confrimList.length);//判断奇数还是偶数个
        		  if(confirmFlag==1){
        			  confirmStr+="<div class='two-state clearfix'><div class='fl'>"
        				  +"<span class='numbs'>"+confrimList[confrimList.length-1]["errorNum"]+"</span>"
        				  +"<p class='state-descr'>"+confrimList[confrimList.length-1]["questionDescribe"]+"</p></div></div>";
        		  }
        		  confirmStr+="</div>";
        			  
        		  $("#link_all_tb_confirm_id").append(confirmStr);
        	   }else{
        		   $("#link_all_tb_confirm_id").hide();
        	   }
        	  
        	   //疑似死链
        	   if(notConfirmList && notConfirmList.length>0){
        		   notConfirmStr+="疑似不可用的链接<div>";
        		  var conLen=parseInt(notConfirmList.length/2);
        		  for(var j=0;j<conLen;j++){
        			  notConfirmStr+="<div class='two-state clearfix'><div class='fl'>"
        				  +"<span class='numbs'>"+notConfirmList[j*2]["errorNum"]+"</span>"
        				  +"<p class='state-descr'>"+notConfirmList[j*2]["questionDescribe"]+"</p></div>";
        			  if(notConfirmList.length>1){
        				  notConfirmStr+="<div class='fr'><span class='numbs'>"+notConfirmList[j*2+1]["errorNum"]+"</span>"
        			  	  +"<p class='state-descr'>"+notConfirmList[j*2+1]["questionDescribe"]+"</p></div></div>";
        			  }
        		  }
        		  var confirmFlag=isTwo(notConfirmList.length);//判断奇数还是偶数个
        		  if(confirmFlag==1){
        			  notConfirmStr+="<div class='two-state clearfix'><div class='fl'>"
        				  +"<span class='numbs'>"+notConfirmList[notConfirmList.length-1]["errorNum"]+"</span>"
        				  +"<p class='state-descr'>"+notConfirmList[notConfirmList.length-1]["questionDescribe"]+"</p></div></div>";
        		  }
        		  notConfirmStr+="</div>";
        			  
        		  $("#link_all_tb_not_confirm_id").append(notConfirmStr);
        	   }else{
        		   $("#link_all_tb_not_confirm_id").hide();
        	   }
        	   
        	   if((notConfirmList && notConfirmList.length>0) || (confrimList && confrimList.length>0)){
        		   
        	   }else{
        		   $("#link_all_tb_no_data_id").show();
        		   noDataStr+="<p class='font-30 unkown-p'>您的监测站点中没有不可用的链接<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
        		   $("#link_all_tb_no_data_id").append(noDataStr);
        	   }
           }
        },error : function(data){
        	alert("查询数据不存在");
        	return;
        }
	});
	
}

//判断基数还是偶数
function isTwo(num){
	var num2=num%2;
	if(num2==0){//偶数
		return 0;
	}else{
		return 1;
	}
}



