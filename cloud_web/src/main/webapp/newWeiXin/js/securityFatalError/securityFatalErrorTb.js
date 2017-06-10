	var siteCode="";
	var checkType=0;
	var servicePeroidId="";
    $(function(){
        siteCode=$("#siteCode_id").val();
        checkType=$("#checkType_id").val();
        servicePeroidId=$("#servicePeroidId_id").val();
        
        //页面数据初始化
        initSecurityFatalErrorTbData();
    });
    
    function initSecurityFatalErrorTbData(){
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/advanceToken_getSecurityFatalErrorTbData.action?siteCode="+siteCode
	        			+"&servicePeroidId="+servicePeroidId,
	        dataType:"json",
	        success : function(data){
	           var resultMap=data;//后台返回数据
	           if(resultMap.errormsg){
	           		alert(resultMap.errormsg);
	           }else{
	        	   //清空页面数据
	        	   $("#fatal_error_tb_id1").html("");
	        	   $("#fatal_error_tb_id2").html("");
	        	   $("#fatal_error_tb_id3").html("");
	        	   
	        	   $("#fatal_error_num_id1").html("");
	        	   $("#fatal_error_num_id2").html("");
	        	   $("#fatal_error_num_id3").html("");
	        	   
	        	   //虚假或伪造内容
	        	   var dataStr1=""; 
	        	   var returnList1=resultMap["returnList1"];
	        	   if(returnList1 && returnList1.length>0){
	        		   $("#fatal_error_num_id1").html(returnList1.length);
	        		   dataStr1+="<ul class='green-icon-part'>";
	        		   for ( var i = 0; i < returnList1.length; i++) {
	        			   dataStr1+="<li class='clearfix'><i class='fl special-point'></i><div class='fl right-part'>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题名称：</span><span class='fl'>"+returnList1[i]["name"]+"</span>"
	        				   +"<span class='photo-img fr'><img src='"+webPath+"/newWeiXin/images/icon-photo.png' alt='' onclick='getShot(\""+returnList1[i]["imgUrl"]+"\",\""+data.litImgUrl+"\")'/></span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>发现时间\：</span><span class='fl'>"+returnList1[i]["scanDate"]+"</span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题描述\：</span><span class='fl except-tit'>"+returnList1[i]["questionDescribe"]+"</span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题页面\：</span><a href='"+returnList1[i]["url"]+"'><span class='fl except-tit'>"+returnList1[i]["url"]+"</span></a></p></div></li>";
	        				   ;
	        		   }
	        		   dataStr1+="</ul>";
	        		   $("#fatal_error_tb_id1").append(dataStr1);
	        	   }else{
	        		   $("#fatal_error_num_id1").html("0");
	        		   
	        		   dataStr1+="<ul class='green-icon-part'><li class='clearfix'> <i class='fl'></i>"
                    	+"<div class='fl right-part last-item'><h4>本次检测未发现虚假或伪造内容 </h4></div></li></ul>";
	        		   $("#fatal_error_tb_id1").append(dataStr1);
	        	   }
	        	   
	        	   
	        	   // 反动、暴力或色情内容
	        	   var dataStr2="";
	        	   var returnList2=resultMap["returnList2"];
	        	   if(returnList2 && returnList2.length>0){
	        		   $("#fatal_error_num_id2").html(returnList2.length);
	        		   dataStr2+="<ul class='green-icon-part'>";
	        		   for ( var i = 0; i < returnList2.length; i++) {
	        			   dataStr2+="<li class='clearfix'><i class='fl special-point'></i><div class='fl right-part'>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题名称：</span><span class='fl'>"+returnList2[i]["name"]+"</span>"
	        				   +"<span class='photo-img fr'><img src='"+webPath+"/newWeiXin/images/icon-photo.png' alt='' onclick='getShot(\""+returnList2[i]["imgUrl"]+"\",\""+data.litImgUrl+"\")'/></span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>发现时间\：</span><span class='fl'>"+returnList2[i]["scanDate"]+"</span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题描述\：</span><span class='fl except-tit'>"+returnList2[i]["questionDescribe"]+"</span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题页面\：</span><a href='"+returnList2[i]["url"]+"'><span class='fl except-tit'>"+returnList2[i]["url"]+"</span></a></p></div></li>";
	        				   ;
	        		   }
	        		   dataStr2+="</ul>";
	        		   $("#fatal_error_tb_id2").append(dataStr2);
	        	   }else{
	        		   $("#fatal_error_num_id2").html("0");
	        		   
	        		   dataStr2+="<ul class='green-icon-part'><li class='clearfix'> <i class='fl'></i>"
                    	+"<div class='fl right-part last-item'><h4>本次检测未发现反动、暴力或色情内容 </h4></div></li></ul>";
	        		   $("#fatal_error_tb_id2").append(dataStr2);
	        	   }
	        	   //其他
	        	   var dataStr3="";
	        	   var returnList3=resultMap["returnList3"];
	        	   if(returnList3 && returnList3.length>0){
	        		   $("#fatal_error_num_id3").html(returnList3.length);
	        		   dataStr3+="<ul class='green-icon-part'>";
	        		   for ( var i = 0; i < returnList3.length; i++) {
	        			   dataStr3+="<li class='clearfix'><i class='fl special-point'></i><div class='fl right-part'>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题名称：</span><span class='fl'>"+returnList3[i]["name"]+"</span>"
	        				   +"<span class='photo-img fr'><img src='"+webPath+"/newWeiXin/images/icon-photo.png' alt='' onclick='getShot(\""+returnList3[i]["imgUrl"]+"\",\""+data.litImgUrl+"\")'/></span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>发现时间\：</span><span class='fl'>"+returnList3[i]["scanDate"]+"</span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题描述\：</span><span class='fl except-tit'>"+returnList3[i]["questionDescribe"]+"</span></p>"
	        				   +"<p class='clearfix'><span class='title-txt-f fl'>问题页面\：</span><a href='"+returnList3[i]["url"]+"'><span class='fl except-tit'>"+returnList3[i]["url"]+"</span></a></p></div></li>";
	        				   ;
	        		   }
	        		   dataStr3+="</ul>";
	        		   $("#fatal_error_tb_id3").append(dataStr3);
	        	   }else{
	        		   $("#fatal_error_num_id3").html("0");
	        		   
	        		   dataStr3+="<ul class='green-icon-part'><li class='clearfix'> <i class='fl'></i>"
                    	+"<div class='fl right-part last-item'><h4>本次检测未发现其他严重问题 </h4></div></li></ul>";
	        		   $("#fatal_error_tb_id3").append(dataStr3);
	        	   }
	           }
	        },error : function(data){
	        	alert("查询数据异常");
	        	return;
	        }
		});
    	
    }
    
    //快照页面
    function getShot(imgUrl,litImgUrl){
    	if(isEmpty(imgUrl)){
    		alert("暂时没有快照，无法查看！");
    		return;
    	}
    	if(imgUrl.match("htm")){//快照
    		window.open(imgUrl);
    	}else{
    		window.open("jsp/onlineReport/cutImgs.jsp?imgUrl="+imgUrl + "&litImgUrl="+litImgUrl);
    	}
    }
    /*
     * 验证是否为空
     */
    function isEmpty(str) {
        var isOK = false;
        if (str == undefined || str == 'undefined' || str == "" || str == "null") {
            isOK = true;
        }
        return isOK;
    }
    
    