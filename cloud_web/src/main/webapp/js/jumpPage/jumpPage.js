//网站标识码
var siteCode="";
// 主要是用于判断组织单位点击的哪一类统计数据，跳转到填报单位的  menuNum   2--网站连通性   5链接可用性   7内容保障问题   15 更新统计
var menuNum="";
//栏目不更新--类型选择
var channelIds="";
//用于区分来源    1、组织单位首页列表跳转到填报单位    2、13个列表跳转到填报单位
var isHomePage="";
$(function() {
    $("#tj_all_title_id").on("mouseover mouseout",".ms-icon-wen",function(event){
        if(event.type == "mouseover"){
            $(this).siblings(".ms-wen-con").fadeIn();
        }else if(event.type == "mouseout"){
            $(this).siblings(".ms-wen-con").fadeOut();
        }
    });
	siteCode=$("#siteCode_id").val();
	menuNum=$("#menuNum_id").val();
	channelIds=$("#channelIds_id").val();
	isHomePage=$("#isHomePage_id").val();
	if(menuNum==2){//网站连通性
		connJumpPage();
	}else if(menuNum==5){//链接可用性
		linkJumpPage();
	}else if(menuNum==7){//内容保障问题
		securityJumpPage();
	}else if(menuNum==15){//更新统计
		updateTotalJumpPage();
	}
	
});

//更新统计
function updateTotalJumpPage(){
	//网站连通性统计跳转页面
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/jumpPage_updateTotalJumpPage.action?siteCode="+siteCode,
	        dataType:"json",
	        success : function(data){
	        	//首先清空数据
	        	$("#tj_all_body_id").html();
	        	$("#tj_all_title_id").html();
	        	

	        	var titleStr="<ul class='breadcrumb' ><li><a href='#'>更新统计</a></li></ul>";
	        	$("#tj_all_title_id").append(titleStr);
	        	var listStr="";
	        	if(data){
	        		//列表div
	        		listStr+="<div class='row'><h3 class='info_fx_h3 bg-13ba59'>更新统计&nbsp;&nbsp<span>(昨天)</span></h3><div class='t_box4 info_fx_con'><h4 class='font2'></h4>"+
	        			"<div  class='line_chart'><div class='pie-chart-tab'><table cellpadding='0' cellspacing='0' class='table'><tbody>"+
	        			"<tr class='pointer'><th class='text-left'>指标</th><th>统计数</th></tr>"+
	        			"<tr class='pointer'><td class='text-left'>首页更新&nbsp;&nbsp(更新条数)</td><td><a href='"+webPath+"/updateHome_updateHomeIndex.action?siteCode="+siteCode+"'>"+data["updateHomeNum"]+"&nbsp;&nbsp条</a></td></tr>"+
	        			"<tr class='pointer'><td class='text-left'>栏目更新&nbsp;&nbsp(更新条数)</td><td><a href='"+webPath+"/updateChannelDetail_index.action?siteCode="+siteCode+"'>"+data["updateChannelNum"]+"&nbsp;&nbsp条</a></td></tbody></table></div></div></div></div>";
	            	$("#tj_all_body_id").append(listStr);
	            	
	            	getOnclick("#tj_all_body_id");
	        	}
	        },error:function(data){
			   alert(data.errorMsg);
		   }
	     });
}

//内容保障问题统计跳转页面
function securityJumpPage(){
	//网站连通性统计跳转页面
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/jumpPage_securityJumpPage.action?siteCode="+siteCode+"&channelIds="+channelIds+"&isHomePage="+isHomePage,
        dataType:"json",
        success : function(data){
        	
        	//首先清空数据
        	$("#tj_all_body_id").html();
          	//标题赋值
        	$("#tj_all_title_id").html();
        	
        	var titleStr="<ul class='breadcrumb' ><li><a href='#'>内容保障问题</a></li></ul>";
        	$("#tj_all_title_id").append(titleStr);
        	
        	var listStr="";
        	
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
            	if(data){
            		//列表div
            		listStr+="<div class='row'><h3 class='info_fx_h3 bg-13ba59'>内容保障问题&nbsp;&nbsp<span></span></h3><div class='t_box4 info_fx_con'><h4 class='font2'></h4>"+
            			"<div  class='line_chart'><div class='pie-chart-tab'><table cellpadding='0' cellspacing='0' class='table'><tbody>"+
            			"<tr><th class='text-left'>指标</th><th>统计数</th></tr>";
            		if(isHomePage==1){//组织单位首页面列表跳转到填报单位
            			listStr+="<tr class='pointer'><td class='text-left'>首页不更新&nbsp;&nbsp(不更新个数)</td><td><a href='"+webPath+"/homeInfoUnUpdate_index.action?siteCode="+siteCode+"'>"+data["homeUnUpdateNum"]+"&nbsp;&nbsp个</a></td></tr>";
            		}else{//组织13个列表跳转到填报单位
            			listStr+="<tr class='pointer'><td class='text-left'>首页不更新&nbsp;&nbsp(不更新天数)</td><td><a href='"+webPath+"/homeInfoUnUpdate_index.action?siteCode="+siteCode+"'>"+data["homeUnUpdateNum"]+"&nbsp;&nbsp天</a></td></tr>";
            		}
            		listStr+="<tr class='pointer'><td class='text-left'>基本信息&nbsp;&nbsp(基本信息更新情况)</td><td><a href='"+webPath+"/upChannel_upChannelIndex.action?siteCode="+siteCode+"'>"+data["channelUnUpdateNum"]+"&nbsp;&nbsp个</a></td></tr>"+
            			"<tr class='pointer'><td class='text-left'>空白栏目&nbsp;&nbsp(空白栏目个数)</td><td><a href='"+webPath+"/securityBlankDetail_index.action?siteCode="+siteCode+"'>"+data["blankNum"]+"&nbsp;&nbsp个</a></td></tr>"+
            			"<tr class='pointer'><td class='text-left'>互动回应差&nbsp;&nbsp(互动回应差个数)</td><td><a href='"+webPath+"/securityResponse_index.action?siteCode="+siteCode+"'>"+data["responseNum"]+"&nbsp;&nbsp个</a></td></tr>"+
            			"<tr class='pointer'><td class='text-left'>服务不实用&nbsp;&nbsp(服务不实用个数)</td><td><a href='"+webPath+"/securityServcie_index.action?siteCode="+siteCode+"'>"+data["serviceNum"]+"&nbsp;&nbsp个</a></td></tr>" +
            			"</tbody></table></div></div></div></div>";
//            		<tr class='pointer'></tr>
//            		<td class='text-left'>基本信息&nbsp;&nbsp(基本信息问题个数)</td>
//            		<td><a href='"+webPath+"/securityBasicInfo_index.action?siteCode="+siteCode+"'>"+data["basicInfoNum"]+"&nbsp;&nbsp个</a></td>
                	$("#tj_all_body_id").append(listStr);
                	getOnclick("#tj_all_body_id");
            	}
        	}
        },error:function(data){
		   alert(data.errorMsg);
	   }
   });
}
//网站连通性统计跳转页面
function connJumpPage(){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/jumpPage_connJumpPage.action?siteCode="+siteCode,
        dataType:"json",
        success : function(data){
        	
        	//首先清空数据
        	$("#tj_all_body_id").html();
        	$("#tj_all_title_id").html();
        	
        	var titleStr="<ul class='breadcrumb' ><li><a href='#'>网站连通性</a></li>" +
        			"<li class='jc-ms'><div class='ms-msg'><div class='ms-wen-con'><div class='ztm-con'>" +
        			"<p style='margin-top:10px;'>1 考察网站每天首页、栏目页打不开次数；" +
        			"</p><p>2 每15分钟扫描一次，，每次访问时间 >15秒，打不开即为不连通一次；</p>"+
        			"</div><i class='angle1'></i></div><div class='ms-icon-wen'><i class='i-wen'>?</i></div></div></li></ul>";
        	//标题赋值
        	$("#tj_all_title_id").append(titleStr);
        	var listStr="";
        	if(data){
        		//列表div
        		listStr+="<div class='row'><h3 class='info_fx_h3 bg-13ba59'>网站连通性&nbsp;&nbsp<span>(昨天)</span></h3><div class='t_box4 info_fx_con'><h4 class='font2'></h4>"+
        			"<div  class='line_chart'><div class='pie-chart-tab'><table cellpadding='0' cellspacing='0' class='table'><tbody>"+
        			"<tr class='pointer'><th class='text-left'>指标</th><th>统计数</th></tr>"+
        			"<tr class='pointer'><td class='text-left'>首页连通性&nbsp;&nbsp(连不通次数)</td><td><a href='"+webPath+"/connectionHomeDetail_index.action?siteCode="+siteCode+"'>"+data["homeConnNum"]+"&nbsp;&nbsp次</a></td></tr>"+
        			"<tr class='pointer'><td class='text-left'>业务系统连通性&nbsp;&nbsp(连不通次数)</td><td><a href='"+webPath+"/connectionBusinessDetail_index.action?siteCode="+siteCode+"'>"+data["busConnNum"]+"&nbsp;&nbsp次</a></td></tr>"+
        			"<tr class='pointer'><td class='text-left'>关键栏目连通性&nbsp;&nbsp(连不通次数)</td><td><a href='"+webPath+"/connectionChannelDetail_index.action?siteCode="+siteCode+"'>"+data["channelConnNum"]+"&nbsp;&nbsp次</a></td></tbody></table></div></div></div></div>";
        		
            	$("#tj_all_body_id").append(listStr);
            	getOnclick("#tj_all_body_id");
            	
        	}
        },error:function(data){
		   alert(data.errorMsg);
	   }
     });
}
//链接可用性统计跳转页面
function linkJumpPage(){
	$.ajax({
 	  type:"post",
 	  async:false,
      url: webPath+"/jumpPage_linkJumpPage.action?siteCode="+siteCode,
      dataType:"json",
      success : function(data){
    	  var listStr="";
      	//首先清空数据
      	$("#tj_all_body_id").html();
      	//标题赋值
    	$("#tj_all_title_id").html();
    	
    	var titleStr="<ul class='breadcrumb' ><li><a href='#'>链接可用性</a></li>" +
    			"<li class='jc-ms'><div class='ms-msg'><div class='ms-wen-con'><div class='ztm-con'>" +
    			"<p style='margin-top:10px;'>1.考察网站上的链接（包括图片、附件、外部链接等）能否正常访问（打不开或错误）；" +
    			"</p><p>2.统计网站上不能正常访问的链接数量。</p>"+
    			"</div><i class='angle1'></i></div><div class='ms-icon-wen'><i class='i-wen'>?</i></div></div></li></ul>";
    	
    	$("#tj_all_title_id").append(titleStr);
      	
      	if(data){
      		//列表div
      		listStr+="<div class='row'><h3 class='info_fx_h3 bg-13ba59'>链接可用性&nbsp;&nbsp<span>(昨天)</span></h3><div class='t_box4 info_fx_con'><h4 class='font2'></h4>"+
      			"<div  class='line_chart'><div class='pie-chart-tab'><table cellpadding='0' cellspacing='0' class='table'><tbody>"+
      			"<tr class='pointer'><th class='text-left'>指标</th><th>统计数</th></tr>"+
      			"<tr class='pointer'><td class='text-left'>首页链接可用性&nbsp;&nbsp(不可用链接个数)</td><td><a href='"+webPath+"/linkHome_linkHomeIndex.action?siteCode="+siteCode+"'>"+data["linkHomeNum"]+"&nbsp;&nbsp个</a></td></tr>"+
      			"<tr class='pointer'><td class='text-left'>全站链接可用性&nbsp;&nbsp(不可用链接个数)</td><td><a href='"+webPath+"/linkAll_linkAllDetailIndex.action?siteCode="+siteCode+"'>"+data["linkAllNum"]+"&nbsp;&nbsp个</a></td></tbody></table></div></div></div></div>";
          	$("#tj_all_body_id").append(listStr);
          	
          	getOnclick("#tj_all_body_id");

      	}
      },error:function(data){
		   alert(data.errorMsg);
	   }
	 });
}


function getOnclick(tbody){
	var trs=$(tbody).find("tr");
	$.each(trs,function(index,tr){
  		$(tr).on('click',function(){
  			if($(this).find('a').length>0){
  				window.location.href = $(this).find('a').attr('href');
  			}
  		})
	});
}
