var siteCode;
(function($){
	siteCode =$("#shirUserCode").val();
	
	queryOpenState();
	
	queryUrl();
	
})(jQuery);
function queryOpenState(){
	var type = 2;
	 $.ajax( {
		  type : "POST",
		  url : webPath+"/databaseInfo_getYunState.action",
		  data:{
			  yunType:type,
			},
		  dataType:"json",
		  async : false,
		  success : function(data) {
			  $("#openType").val(data.openState);
		  },
	      error:function(data){
//	  		alert(data.errorMsg);
	  	  }
	  });
}

function queryUrl(){
	var type = $("#yunType").val();
	var openType = $("#openType").val();
	 $.ajax( {
		  type : "POST",
		  url : webPath+"/cfgOtherProducts_queryUrl.action",
		  data:{
			  type:type,
			},
		  dataType:"json",
		  async : false,
		  success : function(data) {
			  var list = data.body;
			  if(list.length > 0){
				  $.each(list, function(index, obj) {
					  var html ='';
					  if(openType == 0){
						  if(siteCode.length > 6){
							  html += "<div class='apply-btn com-btns' style='display:block;' onclick=\"queryDatabaseInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\"><i class='publi-ico_2 shoppingcar'></i>";
							  html += "<span>申请试用</span></div>";
						  }else{
							  html += "<div class='apply-btn com-btns' style='display:block;' onclick=\"queryDatabaseOrgInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\"><i class='publi-ico_2 shoppingcar'></i>";
							  html += "<span>申请试用</span></div>";
						  }
					  }/*else if(openType == 2){
						 if(siteCode.length > 6){
							  html += "<div class='apply-btn com-btns' style='display:block;'><i class='publi-ico_2 shoppingcar'></i>";
							  html += "<span>申请中</span></div>";
						  }else{
							  html += "<div class='apply-btn com-btns' style='display:block;'><i class='publi-ico_2 shoppingcar'></i>";
							  html += "<span>申请中</span></div>";
						  }
					  }else if(openType == 3){
						  if(siteCode.length > 6){
							  html += "<div class='login-use com-btns' style='display:block;' onclick=\"queryDatabaseInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\"><i class='publi-ico_2 touxiang'></i>";
							  html += "<span>登录使用</span></div>";
						  }else{
							  html += "<div class='login-use com-btns' style='display:block;' onclick=\"queryDatabaseOrgInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\"><i class='publi-ico_2 touxiang'></i>";
							  html += "<span>登录使用</span></div>";
						  }
					  }*/
//					  
					  $("#cfgYeUrl").append(html);
					 });
				  }
			  },
	          error:function(data){
//	  			 alert(data.errorMsg);
	  		  }
	  });
}


function queryDatabaseInfo(typeName,url,type){
	  $.ajax( {
			type : "POST",
			url : webPath+"/databaseInfo_queryDatabaseInfo.action",
			data:{
				  typeName:typeName,
				  url:url,
				  type:type
			  },
			dataType:"json",
			async : false,
			success : function(data) {
				  $("#dataSearchUrl").val(data.dataUrl);
				  document.searchformUrl.action=data.url;
				  document.searchformUrl.target = '_blank';//这一句是关键
				  document.searchformUrl.submit();
			  }
		}); 
	  
}

function queryDatabaseOrgInfo(typeName,url,type){
	  $.ajax( {
		  type : "POST",
		  url : webPath+"/databaseInfo_queryDatabaseOrgInfo.action",
		  data:{
			  typeName:typeName,
			  url:url,
			  type:type
		  },
		  dataType:"json",
		  async : false,
		  success : function(data) {
			      $("#dataSearchUrl").val(data.dataUrl);
			      document.searchformUrl.action=data.url;
			  	  document.searchformUrl.target = '_blank';//这一句是关键
				  document.searchformUrl.submit();
			  }
		  });
}





