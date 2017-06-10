var siteCode="";
var checkType=0;

//滑动分页加载数据
var pageNum = 1;//页数
var pageSize = 3; // 每页展示4个
var pageStart = 0;
var pageEnd =3;


$(function(){
    $('.every-line .half-part').on('touchstart',function(){
        $('.every-line .half-part').removeClass('active');
        $('.tab-content').removeClass('active');
        $(this).addClass('active');
        var n=$(this).index();
        $('.tab-content').eq(n).addClass('active')
    });
    
	siteCode=$("#siteCode_id").val();
	checkType=$("#checkType_id").val();
	
	//页面数据初始化
	initUpdateChannelData();
});

//组织单位--栏目更新-- 数据初始化
function initUpdateChannelData(){
	    //清空列表数据
	    $("#update_channel_org_id").html("");
	    // dropload
	    $("#update_channel_org_list_id").dropload({
	        scrollArea : window,
	        loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/costToken_getUpdateChannelOrgData.action?siteCode="+siteCode
			        +"&checkType="+checkType+"&pageNum="+pageNum
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
				        	   var dataList=data["list"];
				        	   if(dataList && dataList.length>0){
				        		   for(var i=0;i<dataList.length;i++){
				        			   dataStr+="<li onclick='goToUpdateChannelTb(\""+dataList[i]["siteCode"]+"\","+dataList[i]["updateNum"]+",\""+dataList[i]["siteName"]+"\")'>"
				        			   +"<p class='clearfix top'><span class='fl colo-a0 font-20'>"+dataList[i]["siteCode"]+"</span>"
				        			   +"<span class='fr colo-6e font-22'>"+dataList[i]["updateNum"]+"条</span></p>"
				        			   +"<p class='bottom colo-000 font-26'>"+dataList[i]["siteName"]+"</p></li>";
				        			   
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
		                        	   $("#update_channel_org_id").append(dataStr);
		                               // 每次数据加载完，必须重置
		                               me.resetload();
		                           },1000);
				        		   
				        	   }else{
				        		   // 锁定
		                           me.lock();
				        		   $("#update_channel_org_list_id").html("");
				        		   dataStr+="<p class='font-30 unkown-p'>昨日各监测站点栏目未更新内容<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
				        		   $("#update_channel_org_list_id").append(dataStr);
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

//点击某行跳转到填报单位
function goToUpdateChannelTb(code,homeNum,name){
	var siteName=name;
	console.log("  code:"+code+"  homeNum:"+homeNum+" siteName:"+siteName);
	window.location.href=webPath+"/costToken_updateChannelTbIndex.action?siteCode="+code
	+"&updateChannel="+homeNum+"&siteName="+siteName;
}