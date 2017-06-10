var siteCode="";
var scanDate="";
//滑动分页加载数据
var pageNum = 1;//页数
var pageSize = 6; // 每页展示4个
var pageStart = 0;
var pageEnd = 6;

$(function(){
    $('.every-line .half-part').on('touchstart',function(){
        $('.every-line .half-part').removeClass('active');
        $('.tab-content').removeClass('active');
        $(this).addClass('active');
        var n=$(this).index();
        $('.tab-content').eq(n).addClass('active')
    });
	siteCode=$("#siteCode_id").html();
	scanDate=$("#scanDate_id").html();
    
	//页面数据初始化
	initUpdateHomeData();
});

//组织单位--首页更新-- 数据初始化
function initUpdateHomeData(){
	   //清空列表数据
	   $("#tb_update_home_list_id").html("");
	    // dropload
	    $("#tb_update_home_id").dropload({
	        scrollArea : window,
	        loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/freeToken_getUpdateHomeTbData.action?siteCode="+siteCode
			        +"&scanDate="+scanDate+"&pageNum="+pageNum
			        +"&pageSize="+pageSize,
			        dataType:"json",
			        success : function(data){
				           var resultMap=data;//后台返回数据
				           if(resultMap.errormsg){
				           		alert(resultMap.errormsg);
				           		return;
				           }else{
				        	   //总记录数
			                   	var pageTotal=data.pageTotal;
			                   	//循环判定使用
			                    pageEnd =pageSize * pageNum;
			                    pageStart = pageEnd - pageSize;
			                    pageNum=pageNum+1;
				        	   var dataStr="";
				        	   var dataList=data["titleList"];
				        	   if(dataList && dataList.length>0){
				        		   for(var i=0;i<dataList.length;i++){
				        			   if(dataList[i]["title"]==""){
				        				   dataStr+="<li><i></i><a href='"+dataList[i]["url"]+"' target='_blank'>无标题</a></li>";
				        			   }else{
				        				   if(dataList[i]["title"].length>20){
				        					   dataStr+="<li><i></i><a href='"+dataList[i]["url"]+"' target='_blank'>"+dataList[i]["title"].substring(0,20)+"...</a></li>";
				        				   }else{
				        					   dataStr+="<li><i></i><a href='"+dataList[i]["url"]+"' target='_blank'>"+dataList[i]["title"]+"</a></li>";
				        				   }
				        			   }
				        			   if((i + 1+pageStart) >= pageTotal){
		                                   // 锁定
		                                   me.lock();
		                                   // 无数据
		                                  me.noData();
		                                   break;
		                               }
				        		   }
				        		   // 为了测试，延迟1秒加载
		                           setTimeout(function(){
		                        	   $("#tb_update_home_list_id").append(dataStr);
		                               // 每次数据加载完，必须重置
		                               me.resetload();
		                           },1000);
				        		  
				        	   }else{
				        		   me.lock();
	                              $("#tb_update_home_id").html("");
				        		  dataStr+="<p class='font-30 unkown-p'>本次扫描未发现首页更新内容<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
				        		  $("#tb_update_home_id").append(dataStr);

				        	   }
					        }
				        },error : function(data){
				        	alert("查询数据异常");
				        	me.resetload();
	                        // 锁定
	                        me.lock();
				        }
					});
	        	}
	    });
}
