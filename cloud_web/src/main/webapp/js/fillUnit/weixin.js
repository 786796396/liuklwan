var interval="";
$(function(){
	var siteCode=$("#weixin_code_id").val();
	var urlStr=$("#weixin_erweima_url").val();
	//console.log(urlStr);
	if("null"==urlStr.substring(51,urlStr.length)){
		alert(111111111);
		setTimeout(function(){
			getErweiImage();
		},200);
	}else{
		$("#img_id").attr("src",urlStr);
	}
	
	
/*	//点击关闭按钮出发事件
	$("#clous_btn_id").click(function(){
		clousePage(siteCode);
	});*/
	
	//只有扫描并注册成功后，
	interval=window.setInterval("clousePage(\'"+siteCode+"\')",5000);
});

//概览页面点击二维码图片  动态生成临时二维码
function getErweiImage(){
	$("#img_id").removeAttr("src");
	$.ajax({
       type:"post",
       async:false,
       url:webPath+"/fillUnit_clickErWeiImgeUrl.action",
       dataType:"json",
       success : function(data){
    	   console.log(data);
    	   if(data.errorMsg){
    		  // alert(data.errorMsg);
    	   }else{
    	   		var imageUrl=data.erweiUrl;
    	   		console.log(imageUrl);
    	   		$("#img_id").attr("src",data.erweiUrl);
    	   }
       },error:function(data){
    	  // alert(data.errorMsg);
       }
    });
	
}

//点击关闭按钮出发事件
function clousePage(siteCode){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/fillUnit_clousePage.action?siteCode="+siteCode,
        dataType:"json",
        success : function(data){
        	if(data.success){
        		
        		console.log(data);
        		window.clearInterval(interval);
        		$("#img_id").removeAttr("src");
        		window.location.href=webPath+"/fillUnit_gailan.action";
        	}
        },error:function(data){
        	alert(data.errorMsg);
	   }
     });
}