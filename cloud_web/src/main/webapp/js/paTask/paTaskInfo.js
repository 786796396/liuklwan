 var MOBILE_REG = /^(0|86|17951)?(13[0-9]|1[56][012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
 var TELEPHONE_REG = /\d{3}-\d{8}|\d{4}-\d{7}/; //固定电话正则验证

 var paTargetCountId = "";
 var orgToInfoId = "";
//验证是否手机号 mobile或者null,空
function isMobile(obj) {
    var controlObj = $.trim(obj);
    if (MOBILE_REG.test(obj)) {
        return true;
    }else if(TELEPHONE_REG.test(obj)){
    	return true;
    }else {
        return false;
    }
}
 var taskId=-1;
 var targetTaskId=-1;
 var appraisalId=-1;
//修改出错的input的外观
function changeCss(id, Validatemsg) {
     $("#" + id).tips({
         side: 1,
         msg: Validatemsg,
         bg: 'red',
         time: 1
     });
     $("#" + id).focus();
     //$("#" + id).css("border", "1px solid red");
}

//var phone = $.trim($("#phone").val());
//if(email.length == 0){
//   changeCss("phone", "电话、邮箱两者必填其一"); 
//   return false;
//}
$(function () {
	paTargetCountId = $("#paTargetCountId").val();
	orgToInfoId = $("#orgToInfoId").val();
	getOldRating();
	init();
});

//获取往期自评
function getOldRating(){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paTask_getTaskInfo.action?siteCode="+$("#siteCode").val(),// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
          if(paTargetCountId != 0 && orgToInfoId != 1){
	        if(data){
	        	var dataList = data.list;
	    		if(dataList!= null && dataList.length>0){
	    			var html = "";
	    			for ( var i = 0; i < dataList.length; i++) {
						var dataBase = dataList[i];
							html +="<tr><td class=\"text-center w7p\">"+(i+1)+"</td>"+
	                       "<td class=\"text-left w25p\">"+dataBase.taskName+"</td>"+
	                       "<td class=\"text-left w20p\">"+dataBase.startDate+"至"+dataBase.endDate+"</td>"+
	                       "<td class=\"text-center\">";
							if(dataBase.ratingState == 1){  //状态（1：已提交，2：未自评，3：填报中）
									html +="<span>已提交</span></td><td class=\"text-center w25p past_operate\">"+
									"<span class=\"look_p\" taskId="+dataBase.id+" targetTaskId="+dataBase.targetTaskId+" appraisalId="+dataBase.appraisalId+">查看自评</span>&nbsp;&nbsp;"+
									"<span class=\"rating_p\"  ratingState="+dataBase.ratingState+"  taskId="+dataBase.id+"  targetTaskId="+dataBase.targetTaskId+" >自评报告</span>&nbsp;&nbsp;";
							}else if(dataBase.ratingState ==2){
								html +="<span class=\"colo-fb0012\">未自评</span></td><td class=\"text-center w25p past_operate\">"+
									"<span class=\"begin_p\"  taskId="+dataBase.id+" targetTaskId="+dataBase.targetTaskId+" appraisalId="+dataBase.appraisalId+" siteName='"+dataBase.taskName+"' startAndEnd="+dataBase.startDate+"|"+dataBase.endDate+">开始自评</span>&nbsp;&nbsp;"+
									"<span class=\"look_kk bg_pink\"  targetTaskId="+dataBase.targetTaskId+" >自评报告</span>&nbsp;&nbsp;";;
							}else if(dataBase.ratingState ==3){
								 var myDate = new Date();
						         var nowNiane = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
						         var nowYuee  = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
						         var nowDaye  = myDate.getDate();        //获取当前日(1-31)
						         // 自评的开始时间  结束时间  对比当前时间；
						         if(nowYuee<10){
						        	 nowYuee = "0"+nowYuee;
						         }
						         if(nowDaye<10){
						        	 nowDaye = "0"+nowDaye;
						         }
						         var nowDatee = nowNiane+"-"+nowYuee+"-"+nowDaye;
						         var mate = daysBetween(nowDatee,dataBase.endDate);// 负数  为  左小
						         if(mate>0){
						        	 html +="<span class=\"colo-239d60\">逾期</span></td><td class=\"text-center w25p past_operate\">"+
						        	 "<span class=\"look_pp\">修改自评</span>&nbsp;&nbsp;"+
						        	 "<span class=\"rating_p\" taskId="+dataBase.id+"   ratingState="+dataBase.ratingState+"  targetTaskId="+dataBase.targetTaskId+" >自评报告</span>&nbsp;&nbsp;";;
						         }else{
						        	 html +="<span class=\"colo-239d60\">填报中</span></td><td class=\"text-center w25p past_operate\">"+
						        	 "<span class=\"modify_p\" taskId="+dataBase.id+" taskName='"+dataBase.taskName+"' targetTaskId="+dataBase.targetTaskId+" appraisalId="+dataBase.appraisalId+">修改自评</span>&nbsp;&nbsp;"+
						        	 "<span class=\"rating_p\" taskId="+dataBase.id+"   ratingState="+dataBase.ratingState+"  targetTaskId="+dataBase.targetTaskId+" >自评报告</span>&nbsp;&nbsp;";;
						        	 
						         }
							}
							
							if(dataBase.isOpen == 1){
								html+="<a style='text-decoration:none;' href="+webPath+ "/paProject_downWord.action?paTargetTaskId="+dataBase.targetTaskId+"><span class=\"look_k\">考评报告</span></a></td></tr>";
							}else{
								html+="<span class=\"look_kk bg_pink\">考评报告</span></td></tr>";
							}
							
							
	    			}
	    			$("#ttbody").html(html);
	    		}else{
	    			var text = bodysMation("无绩效考评情况");
	   			 	$("#ttbody").append(text);
	    		}
	        } else {
	        	var text = bodysMation("无绩效考评情况");
   			 	$("#ttbody").append(text);
	        }
          }else{
        	  var text = bodysMation("网站绩效考评，是开普云平台提供的一项基于部门自评的上级考评服务，<br/>实现全流程管理，可大大提高网站考评准确性和规范性。<br/><br/>您的上级主管单位暂时没有开通此项服务");
 			 $("#ttbody").append(text);
          }
        },error: function () {// 请求失败处理函数
        }
    });
}
// 开始自评
function subRating(){
	var ratingName  = $.trim($("#ratingName").val());
	if(ratingName.length == 0){
	   changeCss("ratingName", "此处为必填项"); 
	   return false;
	}
	var siteName  = $.trim($("#siteName").val());
	if(siteName.length == 0){
		   changeCss("siteName", "此处为必填项"); 
		   return false;
	}
	var ratingPhone  = $.trim($("#ratingPhone").val());
	if(!isMobile(ratingPhone)){
		changeCss("ratingPhone", "请输入有效的手机号或者固定电话号码");
		return false;
	}
	
	
	var user = {
			companyName:siteName,
			ratingName:ratingName,
			ratingPhone:ratingPhone,
			siteCode:$("#siteCode").val(),
			taskId:taskId,
		    targetTaskId:targetTaskId,
		    appraisalId:appraisalId
        };
	$.ajax({
        async: false,
        cache: false,
        data:user,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRating_addRating.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        if(data){
        	if(data.success == "true"){
        		$.ajax({
        	        async: true,
        	        cache: false,
        	        type: 'POST',
        	        dataType: "json",
        	        url: webPath + "/paTask_createWord.action?ratingId="+data.ratingId,// 请求的action路径
        	        success: function (data) { // 请求成功后处理函数。
        			    if(data){
        		        	if(data.success == 'true'){
        		        	}
        			    } else {
        			    }
        	        }
        	    });
        		window.location.href=webPath+"/paRatingDetail_paRatingDetailInfo.action?taskId="+taskId+"&targetTaskId="+targetTaskId+"&taskName="+$("#taskName").html();
        	}
        } else {
        }
        },error: function () {// 请求失败处理函数
        }
    });
}
//获取指标说明
function getIndictor(){
	//window.location.href=webPath+"/paRating_paRatingIndictor.action?taskId="+taskId+"&appraisalId="+appraisalId;
	window.open(webPath+"/paRating_paRatingIndictor.action?taskId="+taskId+"&appraisalId="+appraisalId, "_blank");
}
//+---------------------------------------------------  
//| 求两个时间的天数差 日期格式为 YYYY-MM-dd   
//+---------------------------------------------------  
function daysBetween(DateOne,DateTwo)  
{   
  var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
  var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
  var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  

  var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
  var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
  var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  

  var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
  return cha;  
}  
// init
function init(){
    $('.past_operate .begin_p').click(function(){
         
         taskId=$(this).attr("taskId");
         var taskName = $(this).attr("siteName");
         $("#taskName").html(taskName);
         var startAndEnd = $(this).attr("startAndEnd");
         var arrs = startAndEnd.split('|');
         var startDate =arrs[0];
         var endDate =arrs[1];
         var arrs1=startDate.split('-');
         var arrs2=endDate.split('-');
         var nian1 = arrs1[0];
         var yue1 = arrs1[1];
         var ri1 = arrs1[2];
         $("#nian1").html(nian1);
         $("#yue1").html(yue1);
         $("#ri1").html(ri1);
         var nian2 = arrs2[0];
         var yue2 = arrs2[1];
         var ri2 = arrs2[2];
         $("#nian2").html(nian2);
         $("#yue2").html(yue2);
         $("#ri2").html(ri2);
         $("#nian3").html(nian2);
         $("#yue3").html(yue2);
         $("#ri3").html(ri2);
         
         var myDate = new Date();
         var nowNian = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
         var nowYue  = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
         var nowDay  = myDate.getDate();        //获取当前日(1-31)
         // 自评的开始时间  结束时间  对比当前时间；
         if(nowYue<10){
        	 nowYue = "0"+nowYue;
         }
         if(nowDay<10){
        	 nowDay = "0"+nowDay;
         }
         var nowDate = nowNian+"-"+nowYue+"-"+nowDay;
         var mat = daysBetween(nowDate,startDate);// 负数  为  左小
         var ma = daysBetween(nowDate,endDate);	  // 
         if(mat<0 || ma>0){
        	 $("#startT").css('background','grey');
        	 $("#startT").removeAttr("onclick");
         }else{
        	 $("#startT").css('background','#2dcc70');
        	 $("#startT").attr("onclick","subRating();");
         }
         
         
        // $(this).attr({'data-toggle':'modal','data-target':'#report_sm'});
         $('#report_sm').modal({backdrop: 'static', keyboard: false});
         targetTaskId=$(this).attr("targetTaskId");
         appraisalId=$(this).attr("appraisalId");
     });
    $('.past_operate .modify_p').click(function(){
       // $(this).attr({'data-toggle':'modal','data-target':'#report_sm'});
        taskId=$(this).attr("taskId");
        targetTaskId=$(this).attr("targetTaskId");
        var tName = $(this).attr("taskName");
        //appraisalId=$(this).attr("appraisalId");
        window.location.href=webPath+"/paRatingDetail_paRatingDetailInfo.action?taskId="+taskId+"&targetTaskId="+targetTaskId+"&taskName="+tName;
    });
    $('.past_operate .look_p').click(function(){
        // $(this).attr({'data-toggle':'modal','data-target':'#report_sm'});
         taskId=$(this).attr("taskId");
         targetTaskId=$(this).attr("targetTaskId");
         //appraisalId=$(this).attr("appraisalId");
         
         //window.location.href=webPath+"/paRating_paRatingDetail.action?taskId="+taskId+"&paTargetTaskId="+targetTaskId;
         window.open(webPath+"/paRating_paRatingDetail.action?taskId="+taskId+"&paTargetTaskId="+targetTaskId, "_blank");
     });
    $('.past_operate .rating_p').click(function(){
        targetTaskId=$(this).attr("targetTaskId");
        var ratingStatee=$(this).attr("ratingState");
        if(ratingStatee == "1"){//已经提交
        	window.location.href=webPath+"/paProject_downRatingWord.action?paTargetTaskId="+targetTaskId;
        	return false;
        }
        var taskIdd=$(this).attr("taskId");
		$.ajax({
	        async: true,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        url: webPath + "/paRating_getRatingByOtherId.action?taskId="+taskIdd+"&targetTaskId="+targetTaskId,// 请求的action路径
	        success: function (data) { // 请求成功后处理函数。
			    if(data){
		        	if(data.success == 'true'){
		        		$.ajax({
		        	        async: true,
		        	        cache: false,
		        	        type: 'POST',
		        	        dataType: "json",
		        	        url: webPath + "/paTask_createWord.action?ratingId="+data.paRatingId,// 请求的action路径
		        	        success: function (data) { // 请求成功后处理函数。
		        			    if(data){
		        		        	if(data.success == 'true'){
		        		        		 window.location.href=webPath+"/paProject_downRatingWord.action?paTargetTaskId="+targetTaskId;
		        		        	}
		        			    } else {
		        			    }
		        	        }
		        	    });
		        	}
			    } else {
			    }
	        }
	    });
       
     });
}

function bodysMation (name){ 
	var html = '';
	html += ' <tr>';
	html += ' <td colspan="7" class="jiangbei-part" style="padding-bottom: 100px; background:#fff;">';
	html += ' <i class="publi-ico_2 jiangbei"></i><p style="font:normal 16px/28px Microsoft Yahei;color: #6e6e6e;">'+name+'</p>';
	html += ' </td></tr>';
	return html;
}		