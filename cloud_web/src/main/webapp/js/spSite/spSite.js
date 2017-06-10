var displays = $("#displayModle").val();
$(function() {
	//隐藏top引导提示
//	$("#steps").hide(); 
	$("#guideSelection").hide();
	$(".evlapp").hide();
	$("#pageHover").mouseout(function(){
		$(".evlapp").hide();
	});
	$("#pageHover").mouseover(function(){
		$(".evlapp").show();
	});
	$("input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	 $('.img-box').mouseover(function(){
	        $(this).find('.opa-bg').show();
	        $(this).find('.fangd_icon').show();
	    });

	    $('.img-box').mouseout(function(){
	        $(this).find('.opa-bg').hide();
	        $(this).find('.fangd_icon').hide();
	    });
	
	$("#formUpload").ajaxForm({
		url : "spSite_uplodeSpSite.action",
		type : "post", // 请求方式
		dataType : "json", // 响应的数据类型
		async : false, // 异步
		success : function(data) {
			
			if(data.error==1){
				alert(data.message);
			}else{
				/*$("#logo").attr('src', data.logo);
				$("#splogo").value(data.url);*/
				$("#AddLogo_area").html("<img  src='"+data.splogo+"' id='splogo' style='height:60px' />"
						+"<input type='hidden' id='logo' name='logo' value='"+data.url+"'>");
				alert(data.success);			
			}
		}
	});

	$("#logoName").bind("change", function() {//上传图片
		var logo=$("#logoName").val();		
		if(logo != null && logo != ""){
			var hz = logo.substring(logo.lastIndexOf(".")+1,logo.length);
			if(hz != "png" && hz!="jpg"){
				alert("只能上传jpg和png文件");
				return false;
			}
			
		}
		
		$("#formUpload").submit();
	});
	
	$("#copyUrl").zclip({//复制url
		path: webPath+"/js/ZeroClipboard.swf",
		copy: function(){
			console.info($("#urlUrl").text());
		return $("#urlUrl").text();
		}
	});

	
	$('.AddLogo_btn').on('click', function(e) {		//上传时绑定input	   
		$("#logoName").closest('input[type=file]').trigger('click');
	});
	
	
	$('.save-btn').on('click', function(e) {	//保存数据		   
		var flag = $("#flag").val();//$("#flag21").parent().prop("class").indexOf("checkedd");
		var plays="";
		var arr1=['1','2','3'];
		var arr2=['1','2','5','6'];
		var isShow =$("#isShow").val();
		if(isShow==2){
			var arr1=['1','3'];
			var arr2=['1','5','6'];
		}

		
		//防止保存连续点击多次disabled="disabled"
		$(".save-btn").attr("disabled",true); 		 
		 var jsonObj = {};
		 jsonObj["flag"] = flag;
		 jsonObj["id"] = $("#idid").val();
		 	if(flag == 1){
				var bottomText=$("#bottomText").val();
				var logo=$("#logo").val();
		 		for(var i=0;i<arr1.length;i++){
		 			var f =$("#flag"+flag+arr1[i]).parent().prop("class").indexOf("checked");
		 			if(f>0){
		 				plays+=arr1[i]+",";
		 			}
		 		}
		 		jsonObj["bottomText"] = bottomText;
			 	jsonObj["logo"] = logo;
			}else{
				for(var i=0;i<arr2.length;i++){
		 			var f =$("#flag"+flag+arr2[i]).parent().prop("class").indexOf("checked");
		 			if(f>0){
		 				plays+=arr2[i]+",";
		 			}
		 		}
				
			}
		 	jsonObj["plays"] = plays;
		
				$.ajax({
				 	 type:"post",
				  	 async:false,
				     url: webPath+"/spSite_updateSpSite.action",
				     dataType: "json",
				     data: JSON.stringify(jsonObj),
				     contentType: "application/json",
				     success:function(data){
				     	if(data.errorMsg){
				     		alert(data.errorMsg);
				     	}else{
							alert(data.succees);
				     	}
				     	//代码逻辑处理往完成之后，将其设置为保存可点击
				        $(".save-btn").attr("disabled",false);				     	
				     },error:function(data){
						   alert(data.errorMsg);
						 //代码逻辑处理往完成之后，将其设置为保存可点击
						   $(".save-btn").attr("disabled",false);
					   }
			  });

	});
	
 
});
//预览
function previewImg(){
		var fg = $("#flag").val();
		var t1 = $("#type11").val();
		var t2 = $("#type22").val();
		if(fg==1){
			if(t1==1){
				//window.open(webPath+"/index.jsp");
				window.open($("#uuUrl").val());
			}else{
				window.open(webPath+"/spSite_previewImg.action?flag=1");
			}
		}else{
			if(t2==1){
				window.open($("#uUrl").val());
			}else{
				window.open(webPath+"/spSite_previewImg.action?flag=2");
			}
			
		}
		
}
function previewImg1(){
	var size=$("#type11").val();
		if(size!=0){
			window.location.href=webPath+"/spSite_getSpSite.action?flag=1";
		}else{
			window.open(webPath+"/spSite_previewImg.action?flag=1");
		}
		
}
function previewImg2(){
		var size=$("#type22").val();
		if(size!=0){
			window.location.href=webPath+"/spSite_getSpSite.action?flag=2";
		}else{
			window.open(webPath+"/spSite_previewImg.action?flag=2");
		}
		
}