//设置全局变量
var siteCode="";
var menuType="";
var level="";
var openId="";

var counter = 1;//页数
var num = 4; // 每页展示4个
var pageStart = 0;
var pageEnd = 4;

$(function(){
	//初始化该页面时，给全局变量赋值
	siteCode=$("#siteCode_id").val();
	openId=$("#openId_id").val();
	level=$("#level_id").val();
	menuType=$("#menuType_id").val();
	
    // dropload
    $("#security_id").dropload({
        scrollArea : window,
        loadDownFn : function(me){
	       	 var jsonObj = {};
	 		jsonObj["siteCode"] = siteCode;
	 		jsonObj["level"] = level;
	 		jsonObj["menuType"] = menuType;
	 		jsonObj["openId"] = openId;
	 		jsonObj["counter"] = counter;
        	$.ajax({
           	 	type:"post",
             	async:false,
                url: webPath+"/token_getsecurity.action",
                dataType:"json",
    	        data: JSON.stringify(jsonObj),
    	        contentType: "application/json",
                success : function(data){
                	if(data.errorMsg){
                        $("#security_id").find(".dropload-load").addClass("hide");
                        $("#conn_table_hide").attr("style","display:block");
                        $("#conn_table_hide").html(data.errorMsg);
                        
                	}else{
                		$("#security_id").find(".dropload-load").removeClass("hide");
                		$("#conn_table_hide").attr("style","display:none");
                    	//页面返回的数据
                    	var resultList=data.resultList;
                    	
                    	//封装每次查询的拼成js，然后添加都列表中
                    	var listStr="";
                    	
                    	var pageTotal=data.pageTotal;
                    	var recordSize=data.recordSize;
                    	
                        pageEnd =num * counter;
                        pageStart = pageEnd - num;
                        counter=counter+1;
                        
                        if(resultList){
    						//隐藏暂无数据
    						$("#conn_table_hide").attr("style","display:none");
    						//显示列表
    						$("#security_table_id").attr("style","display:block");
                    		for(var i =0; i <resultList.length; i++){
                    			var url=resultList[i].url;
                    			if(url.substring(0,4)!="http"){
                    				url="http://"+url;
                    			}
            				 	//头标题
            				 	listStr+="<h3 class='tit-h3-2'><i class='i-point'></i>内容保障问题<span class='pull-right'>"+resultList[i].scanDate+"</span></h3>";
            				 	//封装里面div信息
            				 	if(resultList[i].type==1){//首页不更新
                				 	listStr+="<div class='table-box1'>"+
        	             			"<div class='row'><label>网站名称：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].siteName+"</span></div>"+
        	            			"<div class='row'><label>标 识  码：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].siteCode+"</span></div>"+
        	            			"<div class='row'><label>类型：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>&nbsp;"+resultList[i].typeName+"</span></div>"+
        	            			"<div class='row'><label>首页网址：</label><a class='ellipsis col-xs-8 col-sm-9 col-md-9' target='_blank' href='"+url+"'>"+url+"</a></div>"+
        	            			"<div class='row'><label>监测日期：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].scanDate+"</span></div>"+
        	            			"<div class='row'><label>最后更新：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>&nbsp;"+resultList[i].modifyDate+"</span></div>"+
        	            			
        	            			"</div>";
            				 	}else{//栏目不更新
                				 	listStr+="<div class='table-box1'>"+
        	             			"<div class='row'><label>网站名称：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].siteName+"</span></div>"+
        	            			"<div class='row'><label>标 识  码：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].siteCode+"</span></div>"+
        	            			"<div class='row'><label>类型：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>&nbsp;"+resultList[i].typeName+"</span></div>"+
        	            			"<div class='row'><label>栏目名称：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].name+"</span></div>"+
        	            			"<div class='row'><label>栏目url：</label><a class='ellipsis col-xs-8 col-sm-9 col-md-9' target='_blank' href='"+url+"'>"+url+"</a></div>"+
        	            			"<div class='row'><label>最后更新：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].modifyDate+"</span></div>"+
        	            			"<div class='row'><label>未更新天数：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>&nbsp;"+resultList[i].notUpdateNum+"</span></div>"+
									"</div>";
            				 	}

            	            	
                                if((i + 1+pageStart) >= recordSize){
                                    // 锁定
                                    me.lock();
                                    // 无数据
                                    me.noData();
                                    break;
                                }
                    		}
                            // 为了测试，延迟1秒加载
                            setTimeout(function(){
                                $("#security_table_id").append(listStr);
                                // 每次数据加载完，必须重置
                                me.resetload();
                            },1000);
                        }
                	}
                },error: function(data){
                    //alert('Ajax error!');
                    // 即使加载出错，也得重置
                    me.resetload();
                }
            });
         }
    });
});
