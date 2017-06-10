
var siteCode="";
var checkType=0;

//滑动分页加载数据
var pageNum = 1;//页数
var pageSize = 3; // 每页展示4个
var pageStart = 0;
var pageEnd = 3;

$(function(){
    siteCode=$("#siteCode_id").val();
    checkType=$("#checkType_id").val();
    
    //页面数据初始化
    initConnChannelData();
    
});

function initConnChannelData(){
	   //清空列表数据
	   $("#connChannel_tb_list_id").html("");
	    // dropload
	    $("#connChannel_tb_id").dropload({
	        scrollArea : window,
	        loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/costToken_getConnChannelTbData.action?siteCode="+siteCode
			        +"&pageNum="+pageNum+"&pageSize="+pageSize,
			        dataType:"json",
			        success : function(data){
			           var resultMap=data;//后台返回数据
			           if(resultMap.errormsg){
			           		alert(resultMap.errormsg);
			           }else{
			        	   
			        	   //总记录数
		                   	var pageTotal=data.pageTotal;
		                   	//循环判定使用
		                    pageEnd =pageSize * pageNum;
		                    pageStart = pageEnd - pageSize;
		                    pageNum=pageNum+1;

			        	   var dataStr="";
			        	   var returnList=resultMap["returnList"];
			        	   if(returnList && returnList.length>0){
			        		   for ( var i = 0; i < returnList.length; i++) {
			        			   dataStr+="<li class='clearfix'><i class='fl'></i>"
			        				   +"<div class='fl right-part'><h4><a href='"+returnList[i]["url"]+"'>"+returnList[i]["channelName"]+"</a></h4>"
			        				   +"<p><span>开始时间: </span><span>"+returnList[i]["scanTime"]+"</span></p>"
			        				   +"<p><span>问题类型: </span><span>"+returnList[i]["questionDescribe"]+"</span></p></div></li>";
			        			   
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
	                        	   $("#connChannel_tb_list_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
			        		   
			        	   }else{
			        		   // 锁定
	                           me.lock();
			        		   $("#connChannel_tb_id").html("");
			        		   dataStr+="<p class='font-30 unkown-p'>本次扫描未发现连不通的关键栏目<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
			        		   $("#connChannel_tb_id").append(dataStr);
			        	   }
			           }
			        },error : function(data){
			        	alert("查询数据异常");
	                    // 即使加载出错，也得重置
	                    me.resetload();
	                    // 锁定
	                    me.lock();
			        }
				});
	        }
	    });
}










