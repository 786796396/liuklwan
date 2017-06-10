var type = 2;
var siteCode;
var orgSiteCode;
var siteName;
var liPid;
var pourName = "";
var pourUrl = "";
var orgToInfo;
var reqSiteCode;
var paTargetCount;

//控制头部选中时的样式
$(".indexTop").click(function(){
	$(".indexTop").removeClass("active");
	$(this).addClass("active");
});

//二维码显示隐藏；
$('.wecat-content').click(function(){
 	if($(this).parent('.wecat-part').hasClass('active')&&$(this).parent('.wecat-part').find('.wecat-sh').css('display','block')){
 		//alert(1);
 		$(this).parent('.wecat-part').removeClass('active');
 		/* $(this).parent('.wecat-part').find('.wecat-sh').css('display','none'); */
 		$(this).parent('.wecat-part').find('.wecat-sh').slideUp();
 	}else{
 		//alert(2);
		$(this).parent('.wecat-part').addClass('active');
 		/* $(this).parent('.wecat-part').find('.wecat-sh').css('display','block'); */
 		$(this).parent('.wecat-part').find('.wecat-sh').slideDown();
 	
 	}
});

 $(document).mouseup(function(e){
       var _con = $('.wecat-part');
       if(!_con.is(e.target) && _con.has(e.target).length == 0){
           _con.find('.wecat-sh').slideUp();
           _con.removeClass('active');
       }
	  });

 $(function () {
	
	orgSiteCode =$("#siteCodeId").val();
	siteCode =$("#shirUserCode").val();
	siteName =$("#siteName").val();
	orgToInfo =$("#orgToInfo").val();
	reqSiteCode =$("#reqSiteCode").val();
	paTargetCount =$("#paTargetCount").val();
	topMenuInfoList();
	
	if(liPid != null){
		indMenuInfoList(liPid);
	}
	
	$("#ulId li a, #ulTwId li a, #glUlId li a").click(function (){
		$("#ulId li a, #ulTwId li a, #glUlId li a").each(function (){
			$(this).removeClass("active");
		});
		$.cookie('topTB_cookie', null, {path:'/'});
		$.cookie('topTB_cookie', $(this).children().attr("value"), {path:'/'});
		
		$.cookie('twoMenuTB_cookie', null, {path:'/'});
		$.cookie('twoMenuTB_cookie', $(this).children().attr("value"), {path:'/'});

	});
	if($.cookie("topTB_cookie") == "null"){
		$.cookie('topTB_cookie', $(this).children().attr("value"), {path:'/'});
		$.cookie('twoMenuTB_cookie', $(this).children().attr("value"), {path:'/'});
	}
	
	$("#threeUlId li, #twoUlId li h3").click(function (){
		$("#threeUlId li, #twoUlId li h3").each(function (){
			$(this).removeClass("active");
		});
		$.cookie('twoMenuTB_cookie', null, {path:'/'});
		$.cookie('twoMenuTB_cookie', $(this).children().attr("href"), {path:'/'});
	});
	if($.cookie("twoMenuTB_cookie") == "null"){
		$.cookie('twoMenuTB_cookie', $(this).children().attr("href"), {path:'/'});
	}

	
	$("#logoID, #aID").click(function (){
		$.cookie('typeTB_cookie', "1", {path:'/'});
		window.location.href = webPath + "/fillUnit_gailan.action?siteCode="+siteCode; 
	});
	
	 $("#pourId").hover(function (){  
		 $('#pourId .more-content').slideDown();
     },function (){  
    	 $('#pourId .more-content').slideUp();
     });
});


function topMenuInfoList(){
	
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/menuInfo_topMenuInfoList.action",
        data : { type:type }, 
        dataType:"json",
        async : false,  
        success : function(data) {
       	 $("#ulId").html("");
       	 var topList = data.topList;
       	 var topHtml = '';
       	 var topTwHtml = '';
       	 var topGdHtml = '';
       	 var glHtml = '';
       	 var pid = null;
       	 var num = 0;
       	 if(topList.length > 0 && topList != null){
       		 for(var i = 0; i < topList.length; i++){
       			  num++;
       			  var obj = topList[i];
       			  var Top = topList[0];    // 第一个
       			  var end = topList[topList.length - 1];	// 最后一个
       			  var pour = topList[topList.length - 2];	// 倒数第二个
	        		  pid = obj.id;
	       			  var url = webPath + "/" + obj.GUrl;
	       			  var urlGet = url + "?siteCode=" + siteCode;
	       			  
	       			  var type = $.cookie("typeTB_cookie");  
	       			  
	       			  if(type == "1"){   // 1  默认第一遍加载
	       				var topGul = webPath + "/" + Top.GUrl;
	       					if(topGul == url){
		         	        	$.cookie('topTB_cookie', topGul, {path:'/'});
		         	        	$.cookie('twoMenuTB_cookie', topGul, {path:'/'});
		         	        	$.cookie('typeTB_cookie', "2", {path:'/'});  // 默认选中后改变 （避免2次选中）
		         	        }
	       			  }
	       			  
	       			  
	         	      var guri = $.cookie("topTB_cookie");
	         	      
	         	      var paUri =  webPath + "/paTask_paTaskInfo.action";
	         	     if(num <= 2){
	         	    	 if(paTargetCount != 0 && orgToInfo != 1){
	         	    		if(obj.trialProduct == 2){  // 试用产品  1  是  2  否
		       					 if(guri == url){
		               				  topHtml += '<li class="active"><a href="'+urlGet+'"><span value="'+url+'" onclick="indMenuInfoList(\''+pid+'\')">'+obj.name+'</span></a><em></em></li>';
		               				  liPid = pid;
		       					  }else{
		               				  topHtml += '<li><a href="'+urlGet+'"><span value="'+url+'" onclick="indMenuInfoList(\''+pid+'\')">'+obj.name+'</span></a><em></em></li>';
		           				  }
			       			  }else{
			       				  if(guri == url){
			           				  topHtml += '<li class="active"><a href="'+url+'"><span value="'+url+'">'+obj.name+'</span></a><em></em></li>';
			       				  }else{
			           				  topHtml += '<li><a href="'+url+'"><span value="'+url+'">'+obj.name+'</span></a><em></em></li>';
			       				  }
			       			  }
	         	    	 }else{
	         	    		 if(paUri == url){
	         	    			 
	         	    		 }else{
	         	    			if(obj.trialProduct == 2){  // 试用产品  1  是  2  否
			       					 if(guri == url){
			               				  topHtml += '<li class="active"><a href="'+urlGet+'"><span value="'+url+'" onclick="indMenuInfoList(\''+pid+'\')">'+obj.name+'</span></a><em></em></li>';
			               				  liPid = pid;
			       					  }else{
			               				  topHtml += '<li><a href="'+urlGet+'"><span value="'+url+'" onclick="indMenuInfoList(\''+pid+'\')">'+obj.name+'</span></a><em></em></li>';
			           				  }
				       			  }else{
				       				  if(guri == url){
				           				  topHtml += '<li class="active"><a href="'+url+'"><span value="'+url+'">'+obj.name+'</span></a><em></em></li>';
				       				  }else{
				           				  topHtml += '<li><a href="'+url+'"><span value="'+url+'">'+obj.name+'</span></a><em></em></li>';
				       				  }
				       			  }
	         	    		 }
	         	    	 }
	         	    	 
	         	    	
	         	     }else{
	         	    	 if(obj.trialProduct == 2){  // 试用产品  1  是  2  否
		       				  if(end.name == obj.name){  // 管理中心
		       					  if(guri == url){
		           					  glHtml += '<a href="'+urlGet+'"><span value="'+url+'" class="guanli publi-ico_2" title="'+obj.name+'"></span> <em class=""></em></a>';
		           					  liPid = pid;
		           					  $("#glLiId").addClass("active");
		       					  }else{
		           					  glHtml += '<a href="'+urlGet+'"><span value="'+url+'" class="guanli publi-ico_2" title="'+obj.name+'"></span> <em class=""></em></a>';
		       					  }
		       				  }else if(pour.name == obj.name){  // 更多
		       					  pourName = obj.name;
	               				  pourUrl = url;
	       						  topGdHtml += '<li id="pourId" class="more"><span onclick="pourMenuInfoList(\''+pid+'\')" value="'+pourUrl+'">'+pourName+'</span><i class="publi-ico_2 down-ico"></i></a><em></em></li>';
		       				  }else{
		       					 if(guri == url){
		       						topTwHtml += '<li class="active"><a href="'+urlGet+'"><span value="'+url+'" onclick="indMenuInfoList(\''+pid+'\')">'+obj.name+'</span></a><em></em></li>';
		               				  liPid = pid;
		       					  }else{
		       						topTwHtml += '<li><a href="'+urlGet+'"><span value="'+url+'" onclick="indMenuInfoList(\''+pid+'\')">'+obj.name+'</span></a><em></em></li>';
		           				  }
		       				  }
		       			  }else{
		       				  if(guri == url){
		       					topTwHtml += '<li class="active"><a href="'+url+'"><span value="'+url+'">'+obj.name+'</span></a><em></em></li>';
		       				  }else{
		       					topTwHtml += '<li><a href="'+url+'"><span value="'+url+'">'+obj.name+'</span></a><em></em></li>';
		       				  }
		       			  }
	         	     }
       		 }
       		 
    	        
    	        $("#ulId").append(topHtml);
    	        $("#ulTwId").append(topTwHtml);
    	        $("#ulGdId").append(topGdHtml);
    	        $("#glLiId").append(glHtml);
//    	        $("#ulId li").eq(0).addClass("active");  // 给第一个li选中
    	       
       	 } 
        }
       });
}
 
function indMenuInfoList(pid){
	
	  $.ajax( { 
		  type : "POST",  
	         url : webPath+"/menuInfo_indMenuInfoList.action",   
	         data : { 
	        	 type:type,
	        	 pid:pid
	        	 }, 
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	        	 $("#twoUlId").html("");
	        	 var list = data.body;
	        	 var twoHtml = '';
    			 var guri = $.cookie("twoMenuTB_cookie");
				 
	        	 if(list.length > 0 && list != null){
	        		 $.each(list, function(index, obj) {
	        			 if(obj.trialProduct == 1){  // 试用产品  1  是  2  否
	        				 
	        			 }else{
	        				 var threeLevList = obj.threeLevList;
	        				 if(threeLevList.length > 0 && threeLevList != null){
	        					 var gurl = webPath + "/" + obj.gUrl;
	        					 
	    						 twoHtml += '<li class="multi-line">';
	        					 if(guri == gurl){
	        						 twoHtml += '<h3 class="clearfix active">';   
	        					 }else{
	        						 twoHtml += '<h3 class="clearfix">';   
	        					 }
		        				 twoHtml += ' <i publi-ico_1 fl"><img src="'+obj.imgUrl+'" alt="" /></i><span>'+obj.name+'</span>';
		        				 twoHtml += ' </h3>';
	
		        				 twoHtml += '<ul id="threeUlId" class="second level">';
		        				 
		        				 var sName = siteName.substr(0,11);
		        				  $.each(threeLevList, function(index, obj) {
		        					  var uri = webPath + "/" + obj.GUrl;
		        					  var seUri = webPath + "/servicePeriod_servicePeriod.action?type=2";
 		        					  
		        					  if(orgSiteCode.length > 6){
		        						  if(uri == guri){
			        						  if(obj.name == "本站信息"){
					        					  twoHtml += '<li class="active"><a href="'+uri+'"><span>'+sName+'</span></a></li>';
			        						  }else{
					        					  twoHtml += '<li class="active"><a href="'+uri+'"><span>'+obj.name+'</span></a></li>';
			        						  }
			        					  }else{
			        						  if(obj.name == "本站信息"){
					        					  twoHtml += '<li><a href="'+uri+'"><span>'+sName+'</span></a></li>';
			        						  }else{
					        					  twoHtml += '<li><a href="'+uri+'"><span>'+obj.name+'</span></a></li>';
			        						  }
			        					  }
		        					  }else{
		        						  if(seUri == uri){
		        							  
		        						  }else{
		        							  if(uri == guri){
				        						  if(obj.name == "本站信息"){
						        					  twoHtml += '<li class="active"><a href="'+uri+'"><span>'+sName+'</span></a></li>';
				        						  }else{
						        					  twoHtml += '<li class="active"><a href="'+uri+'"><span>'+obj.name+'</span></a></li>';
				        						  }
				        					  }else{
				        						  if(obj.name == "本站信息"){
						        					  twoHtml += '<li><a href="'+uri+'"><span>'+sName+'</span></a></li>';
				        						  }else{
						        					  twoHtml += '<li><a href="'+uri+'"><span>'+obj.name+'</span></a></li>';
				        						  }
				        					  }
		        						  }
		        					  }
		        				  });
		        				 twoHtml += '</ul>';
		        			  }else{
		        				  var uri = webPath + "/" + obj.gUrl;
	        				  	  var tzUti = webPath + "/notice_indexReceiver.action";
	        				  	  var tempUti = webPath + "/tempReport_tempReportTB.action";
		        				  	 
		        				  if(reqSiteCode.length != 6 && orgToInfo !=  1){
		        						 if(orgSiteCode.length == 10){
				        				  	 twoHtml += '<li class="single-line">';
				        				  	 if(uri == guri){
				        				  		 twoHtml += '<h3 class="clearfix active">';
				        				  	 }else{
				        				  		 twoHtml += '<h3 class="clearfix">';
				        				  	 }
				        				  	
					        				 twoHtml += '<a href="'+uri+'"><i publi-ico_1 fl"><img src="'+obj.imgUrl+'" alt="" /></i><span>'+obj.name+'</span>';
					        				 twoHtml += '<i class="left-icon publi-ico_2 fr"></i> </a></h3>';
			        				  	 }else{
			        				  		 if(tzUti == uri){
			        				  			 
			        				  		 }else{
					        				  	 twoHtml += '<li class="single-line">';
					        				  	 if(uri == guri){
					        				  		 twoHtml += '<h3 class="clearfix active">';
					        				  	 }else{
					        				  		 twoHtml += '<h3 class="clearfix">';
					        				  	 }
					        				  	
						        				 twoHtml += '<a href="'+uri+'"><i publi-ico_1 fl"><img src="'+obj.imgUrl+'" alt="" /></i><span>'+obj.name+'</span>';
						        				 twoHtml += '<i class="left-icon publi-ico_2 fr"></i> </a></h3>';
			        				  		 }
			        				  	 }
		        				  }else{
		        					  if(tempUti == uri){
		        						  
		        					  }else{
		        						  if(orgSiteCode.length == 10){
					        				  	 twoHtml += '<li class="single-line">';
					        				  	 if(uri == guri){
					        				  		 twoHtml += '<h3 class="clearfix active">';
					        				  	 }else{
					        				  		 twoHtml += '<h3 class="clearfix">';
					        				  	 }
					        				  	
						        				 twoHtml += '<a href="'+uri+'"><i publi-ico_1 fl"><img src="'+obj.imgUrl+'" alt="" /></i><span>'+obj.name+'</span>';
						        				 twoHtml += '<i class="left-icon publi-ico_2 fr"></i> </a></h3>';
				        				  	 }else{
				        				  		 if(tzUti == uri){
				        				  			 
				        				  		 }else{
						        				  	 twoHtml += '<li class="single-line">';
						        				  	 if(uri == guri){
						        				  		 twoHtml += '<h3 class="clearfix active">';
						        				  	 }else{
						        				  		 twoHtml += '<h3 class="clearfix">';
						        				  	 }
						        				  	
							        				 twoHtml += '<a href="'+uri+'"><i publi-ico_1 fl"><img src="'+obj.imgUrl+'" alt="" /></i><span>'+obj.name+'</span>';
							        				 twoHtml += '<i class="left-icon publi-ico_2 fr"></i> </a></h3>';
				        				  		 }
				        				  	 }
		        					  }
		        				  }
		        			  } 
		        			  
		        			  twoHtml += '</li>';
	        			 }
	     	         }); 
	        		 
	        		 $("#twoUlId").append(twoHtml);
	        	 }
	         }
	  });
}

function pourMenuInfoList(pid){
	$.ajax( { 
		  type : "POST",  
	         url : webPath+"/menuInfo_indMenuInfoList.action",   
	         data : { 
	        	 type:type,
	        	 pid:pid
	        	 }, 
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	        	 $("#pourId").html("");
	        	 var list = data.body;
	        	 var twoHtml = '';
	        	 var url = $.cookie("twoMenuTB_cookie");
				 
	        	 if(list.length > 0 && list != null){
	        		 
	        		 twoHtml += '<span value="'+url+'">'+pourName+'</span><i class="publi-ico_2 down-ico"></i></a><em></em>';
	        		 twoHtml += '<div class="more-content">';
	        		 $.each(list, function(index, obj) {
	        			 var onlyValue = obj.onlyValue;  // 唯一值
	        			 if(obj.trialProduct == 1){  // 试用产品  1  是  2  否
	        				 if(obj.valueOf == 2){  // 是否携带参数   1 是  2 否
	        					 var uri = obj.gUrl;
	        				  	 twoHtml += '<div class="everyl">';
		        				 twoHtml += '<a href="'+uri+'" target="_blank"><i publi-ico_1"><img src="'+obj.imgUrl+'" alt="" /></i>'+obj.name+'';
			        			 twoHtml += '</a></div>';
	        				 }else{
	        					 var yunProject = "yunProjectTB";  // 云专题
	        					 if(onlyValue == yunProject){
	        						 var uri = obj.gUrl;
		        				  	 twoHtml += '<div class="everyl">';
			        				 twoHtml += '<a onclick="testYun(\''+uri+'\')"><i publi-ico_1"><img src="'+obj.imgUrl+'" alt="" /></i>'+obj.name+'';
				        			 twoHtml += '</a></div>';
	        					 }
	        				 }
	        			 } 
	     	         });
	        		 twoHtml += '</div>';
	        		
	        		 $("#pourId").append(twoHtml);
	        	 }
	         }
	  });
}
/**
 * 云专题
 */
function testYun(url){
//	var url = 'http://192.168.1.249:7777/app-tpl-webapp/special/initSpecial.action';
	 $.ajax( {
			type : "POST",
			url : webPath+"/databaseInfo_queryTestYun.action",
			data:{
				  url:url
			  },
			dataType:"json",
			async : false,
			success : function(data) {
				  $("#testYunUrl").val(data.dataUrl);
				  document.yunformUrl.action=data.url;
				  document.yunformUrl.target = '_blank';//这一句是关键
				  document.yunformUrl.submit();
			  }
		}); 
}