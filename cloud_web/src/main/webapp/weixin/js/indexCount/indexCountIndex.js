
var siteCode="";
var level="";
var menuType="";
var openId="";
$(function(){
	//初始化该页面时，给全局变量赋值
	siteCode=$("#siteCode_id").val();
	level=$("#level_id").val();
	menuType=$("#menuType_id").val();
	openId=$("#openId_id").val();

	//预警信息页面数据初始化
	getIndexCount(siteCode,level,menuType);
	
	$("#indexCountSort_all").click(function(){
		$("#indexCountSort_all").attr("style","display:none");
		getIndexCountSort(siteCode,level,menuType);
	});
	
	$("#indexCountSort_return").click(function(){
		window.location.href=webPath+"/token_checkResultIndex.action?openId="+openId;
	});

});
function getIndexCount(siteCode,level,menuType){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/token_getIndexCount.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType,
        dataType:"json",
        success : function(data){
        	var firstList=data.firstList;
        	var lastList=data.lastList;

        	//先清空
			$("#jiank_index_count_first").html("");
			$("#jiank_index_count_last").html("");
			
			//显示前五名和后五名健康指数排名
			$("#jiank_index_count_first").attr("style","display:block");
			$("#header_first_id").attr("style","display:block");
			
			$("#jiank_index_count_last").attr("style","display:block");
			$("#header_last_id").attr("style","display:block");
			//隐藏全部健康指数排名
			$("#jiank_index_count_all").attr("style","display:none");
			$("#header_all_id").attr("style","display:none");
			
        	var firstStr="";
        	var lastStr="";
        	if(firstList.length>0){
        		firstStr+="<ul>"
        		for(var i=0;i<firstList.length;i++){
        			firstStr+="<li><span class='con-tit ellipsis col-xs-9 col-md-8'>"+firstList[i].siteName+"("+firstList[i].siteCode+")"+"</span><span class='con-status col-xs-3 col-md-4 text-right'>"+firstList[i].totalSum+"</span></li>";
        		}
        		firstStr+="</ul>"
        			
        		
        	}else{
        		firstStr="<div id='conn_table_hide' class='zanwsj'>暂无指数排名数据</div>";
        	}
        	$("#jiank_index_count_first").append(firstStr);
        	
        	if(lastList.length>0){
        		lastStr+="<ul>"
        		for(var j=0;j<lastList.length;j++){
        			lastStr+="<li><span class='con-tit ellipsis col-xs-9 col-md-8'>"+lastList[j].siteName+"("+lastList[j].siteCode+")"+"</span><span class='con-status col-xs-3 col-md-4 text-right'>"+lastList[j].totalSum+"</span></li>";
        		}
        		lastStr+="</ul>"
        	}else{
        		lastStr="<div id='conn_table_hide' class='zanwsj'>暂无指数排名数据</div>";
        	}
        	$("#jiank_index_count_last").append(lastStr);
    	},error:function(data){
    		
    	}
    });
}

//全部健康指数排名
function getIndexCountSort(siteCode,level,menuType){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/token_getIndexCountAll.action?siteCode="+siteCode+"&level="+level+"&menuType="+menuType,
        dataType:"json",
        success : function(data){
        	var allList=data.allList;
			//隐藏前五名和后五名健康指数排名
			$("#jiank_index_count_first").attr("style","display:none");
			$("#header_first_id").attr("style","display:none");
			
			$("#jiank_index_count_last").attr("style","display:none");
			$("#header_last_id").attr("style","display:none");
			
			//显示全部健康指数排名
			$("#jiank_index_count_all").attr("style","display:block");
			$("#header_all_id").attr("style","display:block");
			
        	var allStr="";
        	if(allList){
        		allStr+="<ul>"
        		for(var i=0;i<allList.length;i++){
        			allStr+="<li><span class='con-tit ellipsis col-xs-9 col-md-8'>"+allList[i].siteName+"("+allList[i].siteCode+")"+"</span><span class='con-status col-xs-3 col-md-4 text-right'>"+allList[i].totalSum+"</span></li>";
        		}
        		allStr+="</ul>"
        		$("#jiank_index_count_all").append(allStr);
        	}else{
        		allStr="<div id='conn_table_hide' class='zanwsj'>暂无指数排名数据</div>";
        		$("#jiank_index_count_all").append(allStr);
        	}
			
    	},error:function(data){
    		
    	}
    });
	
	
	
	
}