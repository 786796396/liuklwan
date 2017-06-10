var siteCode;
(function($){
	siteCode =$("#shirUserCode").val();
	queryUrl();
	
})(jQuery);


function queryUrl(){
	var type = $("#yunType").val();
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
					  if(siteCode.length > 6){
						  html += "<div class='apply-btn com-btns' style='display:block;'><a href="+obj.linkUrl+" target='_blank'><i class='publi-ico_2 shoppingcar'></i>";
						  html += "<span>申请试用</span></a></div>";
					  }else{
						  html += "<div class='apply-btn com-btns' style='display:block;'><a href="+obj.linkUrl+" target='_blank'><i class='publi-ico_2 shoppingcar'></i>";
						  html += "<span>申请试用</span></a></div>";
					  }
					  $("#cfgYeUrl").append(html);
					 });
				  }
			  },
	          error:function(data){
//	  			 alert(data.errorMsg);
	  		  }
	  });
}







