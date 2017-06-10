
$(function () {

	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRating_paRatingList.action?taskId="+$("#taskId").val()+"&paTargetTaskId="+$("#paTargetTaskId").val()+"&addType="+1,// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		var html = "";
        		$("#siteName").html(data.ratingName);
        		$("#companyName").html(data.companyName);
        		$("#phone").html(data.phone);
        		$("#overallSituation").html(data.overallSituation);
        		$("#problemsSuggestions").html(data.problemsSuggestions);
        		$("#other").html(data.other); 
        		$("#plan").html(data.plan);
        		var list = data.list;
        		var pathName = window.location.pathname.substring(1);
    			var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        		for(var j=0;j<list.length;j++){
        			var baseData = list[j];
        			html+="<tr><td class=\"level\"><p>"+baseData.quota1+"</p></td>"+
        				  "<td class=\"level\"><p>"+baseData.quota2+"</p></td>"+
        				  "<td class=\"level\"><p att="+baseData.id+">"+baseData.quota3+"</p></td>"+
		        		  "<td><p att="+baseData.id+">"+baseData.appraisalContent+"</p></td>";
	        			 if(baseData.ratingScore<0){
		        			  html+="<td><p att="+baseData.id+">"+''+"</p></td>"+
		        			  "<td style=\"word-break:break-all;\"><p att="+baseData.id+">"+baseData.ratingExplain+"</p></td>"+
			        		  "<td style=\"word-break:break-all;\">"+baseData.channelUrl+"</td>";
		        		  }else{
		        			  html+="<td><p att="+baseData.id+">"+baseData.ratingScore+"</p></td>"+
		        			  "<td style=\"word-break:break-all;\"><p att="+baseData.id+">"+baseData.ratingExplain+"</p></td>"+
			        		  "<td style=\"word-break:break-all;\">"+baseData.channelUrl+"</td>";
		        		  }
		        		  if(baseData.imgUrl != null && baseData.imgUrl !=''){
		        			  html+="<td class=\"text-center accessory\"><div><a target=\"_blank\" href=\"jsp/onlineReport/cutImgs.jsp?imgUrl="+baseData.imgUrl+"&litImgUrl="+data.paRatingUrl+"\">截图</a></div>";
		        		  }else{
		        			  html+="<td class=\"text-center accessory\">";
		        		  }
		        		  
		        		  var path = baseData.path;//附件路径
	            	      var names = baseData.aliasName;//附件别名
	            	      if(path!= null && path.length>0){
	            	    		pathArr = path.split("|");
	            	    		namesArr = names.split("|");
	            	    		for(var i=0;i<pathArr.length;i++){
	            	    			if(pathArr[i] != null && pathArr[i] != ""){
	            	    				html +="<div><a href='" + "/" + webName +pathArr[i] + "' target='_blank'>"+namesArr[i]+"</a></div>";
	            	    			}
	            	    		}
	            	    	}
	            	      html+="</td></tr>";
        		}
        		$("#tbodyy").html(html);
        		$("#tables").rowspan(0);
        		$("#tables").rowspan(1);
        		$("#tables").rowspan(2);
        		$("#tables").rowspan(3);
        		$("#tables").rowspan(4);
            } else {
            }
        },error: function () {// 请求失败处理函数
        }
    });
});


function closePage(){
	window.close();
}