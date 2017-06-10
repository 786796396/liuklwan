
var siteCode="";
$(function(){
	siteCode=$("#siteCode_id").val();
	
	//初始化页面数据
	initConnHomeTbData();
	
});


function initConnHomeTbData(){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/freeToken_getConnHomeTbData.action?siteCode="+siteCode,
        dataType:"json",
        success : function(data){
           var resultMap=data;//后台返回数据
           if(resultMap.errormsg){
           		alert(resultMap.errormsg);
           }else{
        	   var dataStr="";
        	   var pageTotal=data.pageTotal;
        	   $("#pageTotal_id").html(pageTotal+"次");
        	   var questionDes=data.questionDes;
        	   var scanTime=data.scanTime;
        	   if(scanTime && questionDes && questionDes!="" && scanTime!=""){
        		   $("#conn_home_tb_id").show();
    			   dataStr+="<div class='block-style details-info'><p class='clearfix'>"
    				   +"<span class='fl'>发现连不通时间\：</span><span class='fr'>"+scanTime+"</span></p>"
    				   +"<p class='clearfix'> <span class='fl'>问题描述\：</span><span class='fr'>"+questionDes+"</span></p></div>";
        		   $("#conn_home_list_tb_id").append(dataStr);
        	   }else{
        		   $("#conn_home_tb_id").hide();
/*        		   dataStr+="<p class='font-30 unkown-p'>暂无首页不连通数据<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
        		   $("#conn_home_tb_id").append(dataStr);*/
        	   }
           }

        },error : function(data){
        	alert("查询数据异常");
        	return;
        }
	});
}