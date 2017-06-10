var DEFAULT_URL;
var periodNum="";//期数
var menuType = "";
var comboType="";//套餐类型
$(function(){ 
	$(".tab_box1 table tr:odd td").css("background","#fafbfc");
	$("#siteList_tbody_id_term").iePlaceholder();
	/*============================
	@author:弹窗
	@time:2015-10-15
	============================*/
	var scrollTop=0;
	$('#preview').on('show.bs.modal', function () {
	  scrollTop=$(document).scrollTop();
	  $(".page-wrap").addClass("page-fixed");
	  $(document).scrollTop(0);
	});
	$('#preview').on('hidden.bs.modal', function () {	
	  $(".page-wrap").removeClass("page-fixed");
	  $(document).scrollTop(scrollTop);
	});
	// cbox样式
   $("input[type='checkbox']").iCheck(
    {
       checkboxClass: 'icheckbox_square-blue',
       radioClass: 'iradio_square-blue'
    });
    //周期控件初始化
	initPeriod(menuType);
	
	
	
	//检索
	$("#siteList_tbody_id_term").keyup(function(){
		var searchInfo=$("#siteList_tbody_id_term").val();
		if(searchInfo==""){
			$("#report_table tr").show();
		}else{
			$("#report_table").find(".key").each(function(index, element) {
				if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				}else{
					$(this).parents("tr").show();
				}
			});
		}
	});
	
	//列表查询 默认进去普通套餐页面
	initMessage(periodNum,menuType,"");
	
	//点击跳转到普通套餐页面  并控制周期控件的亮暗(默认每次进来最后一个控件高亮)
	$("#to_pt_combo").click(function(){
		var servicePeriodNum =$("#ptCurrentPeriodNum").attr("value");
		periodNum = servicePeriodNum;
		//清空
		$("#ptCombo_id li").removeClass("active");
		//将时间控件的最后一个设置为高亮
		$("#ptCombo_id li").last().addClass("active");
		//var per=$("#ptCombo_id li").last().find("span").html();
		
		periodNum = servicePeriodNum;
		comboType=3;
		$("#siteList_tbody_id_term").val("");
		initMessage(periodNum,menuType,comboType);
	});
	//点击跳转到全面套餐页面   并控制周期控件的亮暗(默认每次进来最后一个控件高亮)
	$("#to_all_combo").click(function(){
		var servicePeriodNum =$("#allCurrentPeriodNum").attr("value");
		//清空
		$("#allCombo_id li").removeClass("active");
		//将时间控件的最后一个设置为高亮
		$("#allCombo_id li").last().addClass("active");
		//var per=$("#allCombo_id li").last().find("span").html();
		
		periodNum = servicePeriodNum;
		comboType=4;
		$("#siteList_tbody_id_term").val("");
		initMessage(periodNum,menuType,comboType);
	});
	
	
	
	//普通套餐---周期控件单击事件
	$("#ptCombo_id li").click(function(){
		var active =  $(this).hasClass("active");
        if (active==false) {
        	$("#ptCombo_id li").removeClass("active");
        	$(this).addClass("active");
        }
		
		var servicePeriodNum = $(this).find("span").html();
		var parentLi=$(this).parent().filter(".active");
		parentLi.removeClass("active");

		periodNum = servicePeriodNum;
		comboType=3;
		$("#siteList_tbody_id_term").val("");
		initMessage(periodNum,menuType,comboType);
	});
	
	//全面套餐---周期控件单击事件
	$("#allCombo_id li").click(function(){
		var active =  $(this).hasClass("active");
        if (active==false) {
        	$("#allCombo_id li").removeClass("active");
        	$(this).addClass("active");
        }
		
		var servicePeriodNum = $(this).find("span").html();
		periodNum = servicePeriodNum;
		comboType=4;
		initMessage(periodNum,menuType,comboType);
	});
	$("#report_table").on("click",".view-modal",function(){
		$("#flexsliderMore3").flexslider({
			slideshowSpeed: 50000, // 自动播放速度毫秒
			animation: "slide",
			controlNav: false, //Boolean:  usage是否显示控制菜单//什么是控制菜单？
			itemWidth: 133,
			itemMargin: 1,
			minItems: 2,
			maxItems: 8
		   // pausePlay: true
		});
	});
});

function selectAllOrNot(){
	//全选复选框
	 $("#select_all").on('ifChecked ifUnchecked', function(event) {
	        if (event.type == 'ifChecked') {
	             $(":checkbox[name='table_check']").iCheck('check');
	        } else {
	             $(":checkbox[name='table_check']").iCheck('uncheck');
	        }
	    });
	     $(":checkbox[name='table_check']").on('ifChanged', function(event){
	        if( $(":checkbox[name='table_check']").filter(':checked').length ==  $(":checkbox[name='table_check']").length) {
	            $("#select_all").prop('checked', 'checked');
	        } else {
	            $("#select_all").removeProp('checked');
	        }
	       $("#select_all").iCheck('update');
	    });
}
	

//初始化周期列表
function initPeriod(menuType){
	$.ajax({ 
		   type : "POST",  
		   url : webPath+"/reportManageLog_initPeriod.action",  
		   dataType:"json",
		   data:"menuType="+menuType,
		   async : false,
		   success : function(data) {
		   			var returnList=data.serviceList;
		   			if(returnList!=null){
		   				for(var i=0;i<returnList.length;i++){
		   					//套餐标准版和高级版做如下处理   ptCombo_id  allCombo_id
		   					if(returnList[i].dicComboName==3){//标准套餐
		   						var timeToolListPT=returnList[i].timeToolList;
		   						var listStrPT="";
		   						for(var j=0;j<timeToolListPT.length;j++){
		   							if(j == timeToolListPT.length -1){
		   								$("#ptCurrentPeriodNum").attr("value",timeToolListPT[j].periodNum);
		   								periodNum=timeToolListPT[j].periodNum;
		       							listStrPT +="<li class='active'><div class='font-size1'>第"+timeToolListPT[j].periodNum+"期</div><span  style='display:none'>"+timeToolListPT[j].periodNum+"</span>"+timeToolListPT[j].startTime+"</li>"
		       						}else{
			       						listStrPT +="<li><div class='font-size1'>第"+timeToolListPT[j].periodNum+"期</div><span  style='display:none'>"+timeToolListPT[j].periodNum+"</span>"+timeToolListPT[j].startTime+"</li>";
			       					}
		   						}
								$("#ptCombo_id").append(listStrPT);
								
		   					}
		   					if(returnList[i].dicComboName==4){//全面（高级）套餐
		   						var timeToolListQM=returnList[i].timeToolList;
		   						var listStrQM="";
		   						for(var x=0;x<timeToolListQM.length;x++){
		   							if(x == timeToolListQM.length -1){
		       							$("#allCurrentPeriodNum").attr("value",timeToolListQM[x].periodNum);
		       							listStrQM +="<li class='active'><div class='font-size1'>第"+timeToolListQM[x].periodNum+"期</div><span  style='display:none'>"+timeToolListQM[x].periodNum+"</span>"+timeToolListQM[x].startTime+"</li>"
		       						}else{
		       							listStrQM +="<li><div class='font-size1'>第"+timeToolListQM[x].periodNum+"期</div><span  style='display:none'>"+timeToolListQM[x].periodNum+"</span>"+timeToolListQM[x].startTime+"</li>";
		       						}		   						
		   						}
								$("#allCombo_id").append(listStrQM);
		   					}
		   				}
		   				/*============================
		   				@author:轮播
		   				@time:2015-10-09
		   				============================*/
		   					$("#flexsliderMore1").flexslider({
		   						slideshow: false,
		   						animation: "slide",
		   						controlNav: false, //Boolean:  usage是否显示控制菜单//什么是控制菜单？
		   				        itemWidth: 133,
		   				        itemMargin: 1,
		   						minItems: 2,
		   				        maxItems: 7

		   				       // pausePlay: true
		   					});
		   				$(".tabbable-new li").click(function(){
		   					$("#flexsliderMore2").flexslider({
		   						slideshow: false,
		   						animation: "slide",
		   						controlNav: false, //Boolean:  usage是否显示控制菜单//什么是控制菜单？
		   						itemWidth: 133,
		   						itemMargin: 1,
		   						minItems: 2,
		   						maxItems: 7
		   					   // pausePlay: true
		   					});
		   					if($("#allCombo_id li").length<6){
		   						$("#flexsliderMore2 .flex-direction-nav").hide();
		   					}
		   				});
		   			} 
		       }
			});
}
	//全局变量  发送状态
	var sendState = {
		"0":"<div class='font-fb0012'>失败</div>",
		"1":"<div>成功</div>"
	};
//初始化基本信息
function initMessage(periodNum,menuType,comboType){
	if(periodNum==""){
		periodNum=$("#ptCurrentPeriodNum").attr("value");
	}
	
	if(periodNum==null){
		periodNum="";
	}
	$.ajax({
		   type : "POST",
		   url : webPath+"/reportManageLog_getMessageList.action?periodNum="+periodNum+"&menuType="+menuType+"&comboType="+comboType,  
		   dataType:"json",
		   async : true,
		   success : function(data) {
			//清空上次查询的列表数据
			$("#siteList_tbody_id").html("");
		  	var reportList=data;
		  	var listStr="";
		  	var listAll="";
		  	if(reportList.length>0){
		  		$("#report_table").show();//显示table数据
		  		$("#table_no_data_contral").attr("style","display:none");//隐藏暂无数据
		  		
		  		$("#key_hidden_id").attr("style","display:block");//显示关键字查询
		  		$("#show_all_select_div").attr("style","display:block");//显示所有下拉框
		  		
		  		$("#batchDownExcel").attr("style","display:none");//邮件督办按钮显示
		  		
		  		
		  		$("#batchDownReport").attr("style","display:block");//批量导出报告按钮显示
		  		$("#sendMail_id").attr("style","display:block");//导出excel文件按钮显示
		  		var allNo=0;
		  		var ptNo=0;
		  		for(var i=0;i<reportList.length;i++){
		  			var path = reportList[i].pdfUrl;
		  			if(reportList[i].dicComboName==4){//全面（高级）套餐
		  				allNo=allNo+1;
		  				listAll += "<tr><td class='td_left'><input type='checkbox' name='table_check' value='"+reportList[i].siteCode +"'/></td>" +
                        "<td class='td_left'>"+allNo+"</td>"+
                        "<td class='td_left font_701999'>"+ reportList[i].siteCode + "</td>" +
                        /*"<td class='td_left'><div class='wz-name key' title='"+reportList[i].siteName+"'>"+(reportList[i].siteName.substring(0,10))+"</div></td>" +*/
                        "<td class='td_left' title='"+reportList[i].remark+"'>" + (reportList[i].remark.substring(0,10)) +"</td>" +
                        "<td>" + reportList[i].sendTime +"</td>"+
                        "<td>" + sendState[reportList[i].sendState] +"</td>";
                        if(path == ""){
                        	listAll += "<td><a data-toggle='modal' class='view-modal' href='javascript:void(0)'>" 
                      	  			+ "<img alt='预览' src='" + webPath + "/images/common/preview.png'/>";
                        } else {
                        	listAll += "<td><a data-toggle='modal' class='view-modal' href='#preview' onclick=\"getPdfUrl('"+webPath+path+"','"+reportList[i].siteName+"')\">"
                      	  		  + "<img alt='预览' src='" + webPath + "/images/common/preview_active.png'/>"; 
                        }
                        listAll += "</a></td></tr>";
                        
		  			}else if(reportList[i].dicComboName==3){//普通套餐（标准套餐）
		  				ptNo=ptNo+1;
			  			listStr += "<tr><td class='td_left'><input type='checkbox' name='table_check' value='"+reportList[i].siteCode +"'/></td>" +
                        "<td class='td_left'>"+ptNo+"</td>"+
                        "<td class='td_left font_701999'>"+ reportList[i].siteCode + "</td>" +
                        "<td class='td_left'><div class='wz-name key' title='"+reportList[i].siteName+"'>"+(reportList[i].siteName.substring(0,10))+"</div></td>" +
                        /*"<td class='td_left' title='"+reportList[i].remark+"'>" + (reportList[i].remark.substring(0,10)) +"</td>" +*/
                        "<td>" + reportList[i].sendTime +"</td>"+
                        "<td>" + sendState[reportList[i].sendState] +"</td>";
                        if(path == ""){
                      	  listStr += "<td><a data-toggle='modal' class='view-modal' href='javascript:void(0)'>" 
                      	  			+ "<img alt='预览' src='" + webPath + "/images/common/preview.png'/>";
                        } else {
                      	  listStr += "<td><a data-toggle='modal' class='view-modal' href='#preview' onclick=\"getPdfUrl('"+webPath+path+"','"+reportList[i].siteName+"')\">"
                      	  		  + "<img alt='预览' src='" + webPath + "/images/common/preview_active.png'/>"; 
                        }
                        listStr += "</a></td></tr>";
		  			}
		  		}
		  		
		  		if(comboType==3 || comboType==""){//默认显示标准表套（普通套餐）
		  			$("#siteList_tbody_id").append(listStr);
		  		}else if(comboType==4){
			  		$("#siteList_tbody_id").append(listAll);
		  		}
		        //重新加载checkbox样式
			    $("input[type='checkbox']").iCheck({
				   checkboxClass : 'icheckbox_square-blue',
				   radioClass : 'iradio_square-blue'
			       });
		  		selectAllOrNot();
		  		
		  	}else{
		  		$("#report_table").attr("style","display:none");//隐藏table
		  		$("#table_no_data_contral").attr("style","display:block");//显示暂无数据
		  		
		  		$("#key_hidden_id").attr("style","display:none");//隐藏关键字查询
		  		$("#show_all_select_div").attr("style","display:none");//隐藏显示所有下拉框
		  		
		  		$("#batchDownExcel").attr("style","display:none");//邮件督办按钮隐藏
		  		$("#batchDownReport").attr("style","display:none");//批量导出报告按钮隐藏
		  		$("#sendMail_id").attr("style","display:none");//导出excel文件按钮隐藏

		  	}
		  
		   }
	});
}

/**展示pdf*/
function getPdfUrl(path,name){
	if(path.length<=10){
		path = webPath+"/pdf/demo.pdf";
	}
	$("#myModalLabel11").html('<i class="view"></i>'+name+'报告预览');
	webViewerInitialized(path);
	document.addEventListener('DOMContentLoaded', webViewerLoad, true);
}

//邮件督办
function emailSuperviseFn(){
	var parameters = "";
  $('input[name="table_check"]:checked').each(function(){
   	parameters+=$(this).val()+",";
  });
  
  if(parameters){
  	$.ajax( {
		   type : "POST",
		   url : webPath+"/reportManageLog_sendEmail.action?parameters="+parameters+"&periodNum="+periodNum,  
		   dataType:"json",
		   async : true,
		   success : function(data) {
			   if(data){
				   alert("邮件发送成功");
			   }else{
				   alert("邮件发送失败");
			   }
		   },error:function(){
			   alert("邮件发送失败");
		   }
	});
  }else{
	  alert("请选择要发送的数据");
  }
}
//批量导出Excel
function batchDownExcelFn(){
	var parameters = "";
  $('input[name="table_check"]:checked').each(function(){
   parameters = parameters + $(this).val() + ",";
  });
  if(parameters){
	  	$("#reportSitecodeEx").val(parameters);
		$("#servicePeriodNumEx").val(periodNum);
		$("#excelDown").submit();
  }else{
	  alert("请选择要下载的对象");
  }
  	
}
//批量下载报告
function batchDownReportFn(){
	var parameters = "";
  $('input[name="table_check"]:checked').each(function(){
   		parameters = parameters + $(this).val() + ",";
  });
  if(parameters){
	  $("#reportSitecode").val(parameters);
	  $("#reportPeriodNum").val(periodNum);
	  var postData = {"reportSitecode":parameters,"reportPeriodNum":periodNum};
	  var t = hasFiles(postData,webPath+ "/reportManageLog_hasReport.action?reportSitecode="+parameters+"&reportPeriodNum="+periodNum);
	  if(!t){
		  alert("没有要下载的文件");
		  return;
	  }
	  $("#reportDown").submit();
  }else{
	  alert("请选择要下载的对象");
  }
}

//预先执行一下判断是否有需要下载的文件
function hasFiles(postData,url){
	var isHasFiles = false;
	$.ajax( {
		   type : "POST",
		   url : url,  
		   dataType:"json",
		   data : postData,
		   async : false,
		   success : function(data) {
			   isHasFiles = data;
		   },
		   error:function(){
			   
		   }
	});
	return isHasFiles;
}


