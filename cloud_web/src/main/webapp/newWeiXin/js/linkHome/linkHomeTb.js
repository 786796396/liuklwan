var siteCode="";

$(function(){
    siteCode=$("#siteCode_id").val();
    
    //初始化页面数据
    initLinkHomeTbDate();
    
});

function initLinkHomeTbDate(){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/freeToken_getLinkHomeTbData.action?siteCode="+siteCode,
        dataType:"json",
        success : function(data){
           var resultMap=data;//后台返回数据
           if(resultMap.errormsg){
           		alert(resultMap.errormsg);
           }else{
        	   //清空数据
        	   $("#confirm_link_id").html("");
        	   $("#not_confirm_link_id").html("");
        	   $("#link_list_id").html("");
        	   
        	   var returnStr="";
        	   var confirmStr="";
        	   var notConfirmStr="";
        	   
        	   var returnList=resultMap["returnList"];//列表数据
        	   var confrimList=resultMap["confrimList"];//确认死链
        	   var notConfirmList=resultMap["notConfirmList"];//疑似死链

        	   //确认死链
        	   if(confrimList && confrimList.length>0){
        		  confirmStr+="确定不可用的链接";
        		  var conLen=parseInt(confrimList.length/2);
        		  for(var j=0;j<conLen;j++){
        			  
        			  confirmStr+="<div class='two-state clearfix'><div class='fl'>"
        				  +"<span class='numbs'>"+confrimList[j*2]["totalNum"]+"</span>"
        				  +"<p class='state-descr'>"+confrimList[j*2]["questionDescribe"]+"</p></div>";
        			  if(confrimList.length>1){
            			  confirmStr+="<div class='fr'><span class='numbs'>"+confrimList[j*2+1]["totalNum"]+"</span>"
        			  	  +"<p class='state-descr'>"+confrimList[j*2+1]["questionDescribe"]+"</p></div></div>";
        			  }
        		  }
        		  var confirmFlag=isTwo(confrimList.length);//判断奇数还是偶数个
        		  if(confirmFlag==1){
        			  confirmStr+="<div class='two-state clearfix'><div class='fl'>"
        				  +"<span class='numbs'>"+confrimList[confrimList.length-1]["totalNum"]+"</span>"
        				  +"<p class='state-descr'>"+confrimList[confrimList.length-1]["questionDescribe"]+"</p></div></div>";
        		  }
        		  $("#confirm_link_id").append(confirmStr);
        	   }else{
        		   $("#confirm_link_id").hide();
        	   }
        	   
        	   //疑似死链
	    	   if(notConfirmList && notConfirmList.length>0){
	    		   notConfirmStr+="疑似不可用的链接";
	    		   var len=parseInt(notConfirmList.length/2);
	     		  for(var j=0;j<len;j++){
	     			  
	     			 notConfirmStr+="<div class='two-state clearfix'><div class='fl'>"
	     				  +"<span class='numbs'>"+notConfirmList[j*2]["totalNum"]+"</span>"
	     				  +"<p class='state-descr'>"+notConfirmList[j*2]["questionDescribe"]+"</p></div>";
	     			  
	     			notConfirmStr+="<div class='fr'><span class='numbs'>"+notConfirmList[j*2+1]["totalNum"]+"</span>"
	     			  	  +"<p class='state-descr'>"+notConfirmList[j*2+1]["questionDescribe"]+"</p></div></div>";
	     		  }
	     		  var notConfirmFlag=isTwo(notConfirmList.length);//判断奇数还是偶数个
	     		  if(notConfirmFlag==1){
	     			 notConfirmStr+="<div class='two-state clearfix'><div class='fl'>"
	     				  +"<span class='numbs'>"+notConfirmList[notConfirmList.length-1]["totalNum"]+"</span>"
	     				  +"<p class='state-descr'>"+notConfirmList[notConfirmList.length-1]["questionDescribe"]+"</p></div></div>";
	     		  }
	     		  $("#not_confirm_link_id").append(notConfirmStr);
	     	   }else{
	     		   $("#not_confirm_link_id").hide();
	     	   }
        	   
	    	   //列表数据
	    	   if(returnList && returnList.length>0){
	    		   returnStr+="<ul class='green-icon-part'>";
	    		   for ( var i = 0; i < returnList.length; i++) {
	    			   returnStr+="<li class='clearfix'><i class='fl'></i><div class='fl right-part'>"
	    				   +"<h4><a href=\'"+returnList[i]["url"]+"\' target=\'_blank\'>"+returnList[i]["linkTitle"]+"</a></h4>"
	    				   +"<p><span>开始时间: </span><span>"+returnList[i]["scanTime"]+"</span></p>"
	    				   +"<p><span>问题类型: </span><span>"+returnList[i]["questionDescribe"]+"</span></p></div></li>";
	    		   }
	    		   
	    		   returnStr+="</ul>";
	    		   
	    		   $("#link_list_id").append(returnStr);
	    		   
	    	   }else{
	    		   returnStr+="<p class='font-30 unkown-p'>本次扫描未发现首页不可用链接<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
	    		   $("#link_list_id").append(returnStr);
	    	   }
           }
        },error : function(data){
        	alert(data.errormsg);
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



