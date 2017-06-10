var tName="";
$(function () {

	getPaName();

    /*tab切换*/
    $('.change-tab').click(function(){
        $('.change-tab').removeClass('on');
        $(this).addClass('on');
        tName = $(this).find("span").attr("tName");
        //var n=$(this).index();
        $('.change_box').show();
        //$('.change_box').eq(0).show();
        var select = $("#selectt").val();
    	var siteOrName = $("#siteOrName").val();
        getTagetList($(this).attr("att"),select,siteOrName);
        $("#appraisalId").val($(this).attr("att"));
    });
    
    $("#selectt").change(function(){
    	var select = $("#selectt").val();
    	var siteOrName = $("#siteOrName").val();
    	var appraisalId = $("#appraisalId").val();
    	getTagetList(appraisalId,select,siteOrName);
    });
    $("#siteOrName").keyup(function(){
    	var select = $("#selectt").val();
    	var siteOrName = $("#siteOrName").val();
    	var appraisalId = $("#appraisalId").val();
    	getTagetList(appraisalId,select,siteOrName);
    });

});
function goback(){
	 //window.location.href=history.go(-1);	
	window.location.href= webPath + "/paProject_paProjectList.action";
}
function lookIndor(){
	window.open(webPath+"/paRating_paRatingIndictor.action?taskId="+$("#taskId").val()+"&appraisalId="+$("#appraisalId").val(), "_blank");
}
function getPaName(){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paAppraisal_getPaName.action?projectId="+$("#projectId").val(),// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var dataList = data.list;
        		if(dataList!= null && dataList.length>0){
        			var html = "";
        			for ( var i = 0; i < dataList.length; i++) {
						var dataBase = dataList[i];
						if(i==0){
							html +="<div att="+dataBase.id+" class=\"province fl change-tab on\">"
							getTagetList(dataBase.id,null,null);
							tName=dataBase.name;
							$("#appraisalId").val(dataBase.id);
						}else{
							html +="<div att="+dataBase.id+" class=\"province fl change-tab\">"
						}
						if(dataBase.name.length>9){
							html +="<span title='"+dataBase.name+"' tName='"+dataBase.name+"'>"+dataBase.name.substring(0,9)+"..</span><i class=\"left\"></i></div>";
						}else{
							html +="<span tName='"+dataBase.name+"'>"+dataBase.name+"</span><i class=\"left\"></i></div>";
						}
        			}
        			$("#getName").html(html);
        		}
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
}

function getTagetList(appraisalId,appraisalState,siteOrName){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paTarget_getPaTargetList.action?taskId="+$("#taskId").val()+"&appraisalId="+appraisalId+"&appraisalState="+appraisalState+"&siteOrName="+siteOrName,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var dataList = data.list;
        		if(dataList!= null && dataList.length>0){
        			var html = "";
        			for ( var i = 0; i < dataList.length; i++) {
						var dataBase = dataList[i];
						//if(dataBase.isOpen ==1){
						html +="<tr><td class=\"text-center w4p\"><input type=\"checkbox\" paTargetTaskId="+dataBase.paTargetTaskId+" isOpen="+dataBase.isOpen+" ratingState="+dataBase.ratingState+"  name=\"fen\"/></td>";
						//}else{
						//	html +="<tr><td class=\"text-center w4p\"><input type=\"checkbox\" disabled=\"true\" /></td>";
						//}
						html +="<td class=\"text-center w5p\">"+(i+1)+"</td>"+
                         "<td class=\"text-left w10p\">"+dataBase.siteCode+"</td>"+
                         "<td class=\"text-left w15p\">"+dataBase.siteName+"</td><td class=\"text-center w10p\">";
						
						 if(dataBase.ratingState ==1 ){
							 html+="<span>已提交</span>";
						 }else if(dataBase.ratingState ==3){
							 html+="<span class=\"colo-239d60\">自评中</span>";
						 }else if(dataBase.ratingState ==2){
							 html+="<span class=\"colo-fb0012\">未自评</span>";
						 }
						 
//                         html+="</td><td class=\"text-center w10p\">";
//                         
//                         if(dataBase.appraisalState ==1){
//                        	 html+="<span>考评完成</span>";
//                         }else if(dataBase.appraisalState ==2){
//                        	 html+="<span class=\"colo-239d60\">考评中</span>";
//                         }else if(dataBase.appraisalState ==3){
//                        	 html+="<span class=\"colo-fb0012\">未考评</span>";
//                         }
                         
                         html+="</td><td class=\"text-center w15p operate\">";
                         if(dataBase.ratingState ==1){
                        		 html+="<a target=\"_blank\" href="+webPath+ "/paRating_paRatingDetail.action?taskId="+$("#taskId").val()+"&paTargetTaskId="+dataBase.paTargetTaskId+"&siteName="+dataBase.siteName+" class=\"colo-01a5ec\">查看自评</a>";
                        		 html+="<a href="+webPath+ "/paProject_downRatingWord.action?paTargetTaskId="+dataBase.paTargetTaskId+" style=\"color: #01a5ec\">自评报告</a>";
                         }else{
                        	 html+="<span>查看自评</span><span>自评报告</span>";
                         }
                         if(dataBase.isOpen ==1){
                         html+="<a href="+webPath+ "/paProject_downWord.action?paTargetTaskId="+dataBase.paTargetTaskId+" style=\"color: #01a5ec\" >考评报告</a></td></tr>";
                         }else{
                        	 html+="<span>考评报告</span></td></tr>";
                         }
        			}
        			$("#targets").html(html);
        			$("#infos").html(dataList.length);
        			
        		}else{
        			$("#targets").html("");
        			$("#infos").html(0);
        		}
            } else {
            }
        	$("input").iCheck({
        		checkboxClass : 'icheckbox_square-blue',
        		radioClass : 'iradio_square-blue'
        	});
        	checkZongCheckBox();
        	checkCheckBox();
        },error: function () {// 请求失败处理函数
        }
    });
}


//退回自评报告
function backRatingWord(){
	var arr=new Array();	
	var arrZong=new Array();
	  $("input[name='fen']:checked").each(function(){
	    	if($(this).attr('ratingState') == 1){
	    		arr.push($(this).attr('paTargetTaskId'));
	    	}else{
	    	arrZong.push($(this).attr('paTargetTaskId'));
	    	}
		});
	  if(arr.length == 0 && arrZong.length==0){
		  alert("请至少选择一个站点进行操作");
		  return;
	  }
	  if(arrZong.length > 0){
	      alert("您选择的站点中有未提交自评报告的,请检查");
	      return;
	   }
	  var ids="";
	    for(var i=0;i<arr.length;i++){
				ids+=arr[i]+",";
		}
	  $.ajax({
		  url:'paProject_backWord.action',
		  data:{
			  'ids':ids
		  },
		  success:function(rsp){
			  if(rsp.success == 'true'){
				  alert(rsp.msg);
				  window.location.reload();
			  }else{
				  alert(rsp.msg);
			  }
		  }
	  });
	    
}

// 批量下载
function downWord(){
	var arr=new Array();	
	var arrZong=new Array();
	    $("input[name='fen']:checked").each(function(){
	    	if($(this).attr('isOpen') == 1){
	    		arr.push($(this).attr('paTargetTaskId'));
	    	}
	    	arrZong.push($(this).attr('paTargetTaskId'));
		});
	    if(arrZong.length==0){
	        alert("请选择需要下载的数据！");
	        return;
	    }
	    if(arrZong.length>arr.length && arr.length>0){
	    	alert("本次仅下载已完成考评打分的网站考评报告。");
	    }else if(arr.length==0){
	    	alert("请选择已完成考评打分的网站考评报告。");
	    	return;
	    }
	    var ids="";
	    for(var i=0;i<arr.length;i++){
				ids+=arr[i]+",";
		}
	    window.location.href = "paProject_downWordOrZip.action?ids="+ids+"&tName="+tName;
    
}

//批量下载自评报告
function downRatingWord(){
	var arr=new Array();	
	var arrZong=new Array();
	    $("input[name='fen']:checked").each(function(){
	    	if($(this).attr('ratingState') == 1){
	    		arr.push($(this).attr('paTargetTaskId'));
	    	}
	    	arrZong.push($(this).attr('paTargetTaskId'));
		});
	    if(arrZong.length==0){
	        alert("请选择需要下载的数据！");
	        return;
	    }
	    if(arrZong.length>arr.length && arr.length>0){
	    	alert("本次仅下载已完成自评的网站自评报告。");
	    }else if(arr.length==0){
	    	alert("请选择已完成自评的网站自评报告。");
	    	return;
	    }
	    var ids="";
	    for(var i=0;i<arr.length;i++){
				ids+=arr[i]+",";
		}
	    window.location.href = "paProject_downWordRatingOrZip.action?ids="+ids+"&tName="+tName;
    
}

//  icheck checkbox 的联动性
function checkZongCheckBox(){
	
	$('#zong').on('ifClicked', function(event){
		var s=0;
		$("input[name='zong']:checked").each(function(){
			s=1;
		});
		if(s==1){
			$("input[name='fen']").iCheck('uncheck');
		}else{
			$("input[name='fen']").iCheck('check');
		}
	});

}
function checkCheckBox(){
	var arr1=new Array();	
	$("input[name='fen']").each(function(){
		arr1.push($(this).attr('paTargetTaskId'));
	});
	$("input[name='fen']").on('ifChecked', function(event){
	    var arr2=new Array();	
	    $("input[name='fen']:checked").each(function(){
	    	arr2.push($(this).attr('paTargetTaskId'));
		    if(arr1.length==arr2.length){
		    	$("#zong").iCheck('check');
		    }
		});
	});
	$("input[name='fen']").on('ifUnchecked', function(event){
		$("#zong").iCheck('uncheck');
	});
}