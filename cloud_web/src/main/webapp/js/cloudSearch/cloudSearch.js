var show =0;
var siteCodeId;
$(function () {
	
	siteCodeId =$("#shirUserCode").val();
	queryCfgOtherProducts();
	
});


function closeTops(){
	//隐藏一键检测和云搜索的div弹框 
	 $("#tanSlidedowns").slideUp();
	 $('.more-project-icon').css('background','url('+webPath+'/images/common/yun_others.png) no-repeat -97px -1px');
	 show = 0;
}
function openTops(){
	$("#tanSlidedowns").removeAttr("style"); 
	//打开一键检测和云搜索的div弹框 
	$('.more-project-icon').css('background','url('+webPath+'/images/common/yun_others.png) no-repeat -66px -1px');	 
	$("#tanSlidedowns").slideDown();
	show = 1;
}
//鼠标移入更多按钮效果
function changMoreIconC(){
	$('.more-project-icon').css('background','url('+webPath+'/images/common/yun_others.png) no-repeat -66px -1px');
	
}
//鼠标移出更多按钮效果
function changMoreIcon(){
	if(show == 1){
		$('.more-project-icon').css('background','url('+webPath+'/images/common/yun_others.png) no-repeat -66px -1px');
	}else{
		$('.more-project-icon').css('background','url('+webPath+'/images/common/yun_others.png) no-repeat -97px -1px');
	}
}

//鼠标划过关闭按钮效果
function changIconC(){
	$('.close-icon').css({'background':'url('+webPath+'/images/common/yun_others.png) no-repeat -24px -90px','transition':' all .5s ease-in'})
}
//鼠标划过关闭按钮效果
function changIcon(){
	$('.close-icon').css({'background':'url('+webPath+'/images/common/yun_others.png) no-repeat -5px -90px','transition':' all .5s ease-in'})
}
//点击其他区域更多云服务收起
$(document).mouseup(function(e){
    var _con = $('.item-last').parent().find('.yun-others-list') // 设置目标区域
    if(!_con.is(e.target) && _con.has(e.target).length === 0){
        _con.slideUp();
        $('.more-project-icon').css('background','url('+webPath+'/images/common/yun_others.png) no-repeat -97px -1px');
        show = 0;
    }
});

function queryCloudSearchHide(){
	  $.ajax( {
			type : "POST",
			url : webPath+"/databaseInfo_queryCloudSearchHide.action",
			dataType:"json",
			async : false,
			success : function(data) {
				if(data.type==1){
					$("#cloudSearch").show();
				}else{
					$("#cloudSearch").hide();
				}
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
				  $("#dataUrl").val(data.dataUrl);
				  document.searchform.action=data.url;
				  document.searchform.target = '_blank';//这一句是关键
				  document.searchform.submit();
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
			      $("#dataUrl").val(data.dataUrl);
			      document.searchform.action=data.url;
			  	  document.searchform.target = '_blank';//这一句是关键
				  document.searchform.submit();
			  }
		  });
  }
	
  // 动态获取一键监测
  function queryCfgOtherProducts(){
	  $.ajax( {
		  type : "POST",
		  url : webPath+"/cfgOtherProducts_queryCfgOtherProducts.action",
		  dataType:"json",
		  async : false,
		  success : function(data) {
			  $("#cfgId").html("");
			  $("#cfgHideId").html("");
			  var cfgHtml = "";
			  if(data.success == 'true'){
				  $.each(data.body, function(index, obj) {
					  if(obj.specialShow == 1){
						  var html = "";
						  if(obj.isData == 1){
							  if(siteCodeId.length > 6){
								  html += "<div class='pull-right' onclick=\"queryDatabaseInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\">";
								  html += "<a href='javascript:void(0);'  class='top-item' >";
								  html += "<img style='width: 30px;height: 30px' src='"+obj.icon+"' /><span>"+obj.title+"</span>";
								  html += "</a></div>";	
							  }else{
								  html += "<div class='pull-right' onclick=\"queryDatabaseOrgInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\">";
								  html += "<a href='javascript:void(0);'  class='top-item' >";
								  html += "<img style='width: 30px;height: 30px' src='"+obj.icon+"' /><span>"+obj.title+"</span>";
								  html += "</a></div>";	
							  }
						  }else if(obj.isData == 2){
							  html += "<div class='pull-right'>";
							  html += "<a href='"+obj.linkUrl+"' target='_blank' class='top-item' >";
							  html += "<img style='width: 30px;height: 30px' src='"+obj.icon+"' /><span>"+obj.title+"</span>";
							  html += "</a></div>";	
						  }
						  $("#cfgId").prepend(html);
					  }
					if(obj.isData == 1){
							  if(siteCodeId.length > 6){
								  cfgHtml += "<div class='span33 fl' onclick=\"queryDatabaseInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\">";
								  cfgHtml += "<a href='javascript:void(0);' ><i class='list-icon fl'><img style='width: 42px;height: 42px' src='"+obj.icon+"' /></i>";
								  cfgHtml += "<div class='text-descr fl'><h5>"+obj.title+"</h5><p>"+obj.description+"</p></div>";
							      cfgHtml += "</a></div>";
							  }else{
								  cfgHtml += "<div class='span33 fl' onclick=\"queryDatabaseOrgInfo('"+obj.title+"','"+obj.linkUrl+"','"+obj.type+"');\">";
								  cfgHtml += "<a href='javascript:void(0);' ><i class='list-icon fl'><img style='width: 42px;height: 42px' src='"+obj.icon+"' /></i>";
								  cfgHtml += "<div class='text-descr fl'><h5>"+obj.title+"</h5><p>"+obj.description+"</p></div>";
							      cfgHtml += "</a></div>";
							  }
						  }else if(obj.isData == 2){
							  cfgHtml += "<div class='span33 fl'>";
							  cfgHtml += "<a href='"+obj.linkUrl+"' target='_blank'><i class='list-icon fl'><img style='width: 42px;height: 42px' src='"+obj.icon+"' /></i>";
							  cfgHtml += "<div class='text-descr fl'><h5>"+obj.title+"</h5><p>"+obj.description+"</p></div>";
						      cfgHtml += "</a></div>";
						  }
					 });
				  $("#cfgHideId").append(cfgHtml);
				  }
			  },
	          error:function(data){
//	  			 alert(data.errorMsg);
	  		  }
	  });
  }
	  
//云搜索介绍超链接
function cloudSearchIntroduce(){
	window.open("http://so.kaipuyun.cn:8080/sac/pdf/platformIntroduce.pdf");
}