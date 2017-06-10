//设置全局变量
var siteCode="";
var counter = 1;//页数
var num = 4; // 每页展示4个
var pageStart = 0;
var pageEnd = 4;

$(function(){
	//初始化该页面时，给全局变量赋值
	siteCode=$("#siteCode_id").val();

    // dropload
    $("#early_detail_id").dropload({
        scrollArea : window,
        loadDownFn : function(me){
        	$.ajax({
           	 	type:"post",
             	async:true,
                url: webPath+"/token_getEarlyDetail.action?siteCode="+siteCode+"&counter="+counter,
                dataType:"json",
                success : function(data){
                	if(data.errorMsg){
                       $("#early_detail_id").find(".dropload-load").addClass("hide");
                        $("#conn_table_hide").attr("style","display:block");
                        $("#conn_table_hide").html(data.errorMsg);
                	}else{
                		// $("#early_detail_id").find(".dropload-load").removeClass("hide");
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
    						$("#early_detail_list_id").attr("style","display:block");
                    		for(var i =0; i <resultList.length; i++){
                    			var url=resultList[i].url;
                    			if(url.substring(0,4)!="http"){
                    				url="http://"+url;
                    			}
            				 	//头标题
            				 	listStr+="<h3 class='tit-h3-2'><i class='i-point'></i>预警提醒<span class='pull-right'>"+resultList[i].scanDate+"</span></h3>";
            				 	//封装里面div信息
            				 	listStr+="<div class='table-box1'>"+
            	             			"<div class='row'><label>网站名称：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].siteName+"</span></div>"+
            	            			"<div class='row'><label>标 识  码：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].siteCode+"</span></div>"+
            	            			"<div class='row'><label>问题类型：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].dicChannelId+"</span></div>";
            				 	if(resultList[i]["type"]==6){
            				 		listStr+="<div class='row'><label>疑似错误：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].wrongTerm+"</span></div>"+
        	            			"<div class='row'><label>推荐修改：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].expectTerms+"</span></div>";
            				 	}else{
            				 		
            				 		listStr+="<div class='row'><label>错误描述：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].queDescribe+"</span></div>"
            				 	}
            	            	listStr+="<div class='row'><label>发现日期：</label><span class='ellipsis col-xs-8 col-sm-9 col-md-9'>"+resultList[i].scanTime+"</span></div>"+
            	            			"<div class='row'><label>问题页面：</label><a class='ellipsis col-xs-8 col-sm-9 col-md-9' target='_blank' href='"+url+"'>"+url+"</a></div>"+
    									"</div>";
            	            	
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
                                $("#early_detail_list_id").append(listStr);
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
