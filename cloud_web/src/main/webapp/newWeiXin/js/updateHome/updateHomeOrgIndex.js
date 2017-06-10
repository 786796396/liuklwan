

var siteCode="";
var checkType="";
var scanDate="";

//滑动分页加载数据
var pageNum = 1;//页数
var pageSize = 3; // 每页展示4个
var pageStart = 0;
var pageEnd = 3;
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
	scanDate=$("#scanDate_id").html();
	
	//页面数据初始化
	initUpdateHomeData();
    
    
});

//组织单位--首页更新-- 数据初始化
function initUpdateHomeData(){
   //清空列表数据
   $("#updateHome_list_id").html("");
    // dropload
    $("#updateHome_id").dropload({
        scrollArea : window,
        loadDownFn : function(me){
			$.ajax({
		   	 	type:"post",
		     	async:false,
		        url: webPath+"/freeToken_getUpdateHomeData.action?siteCode="+siteCode+"&pageNum="+pageNum+"&pageSize="+pageSize
		        			+"&checkType="+checkType+"&scanDate="+scanDate,
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
			        	   var dataList=data["list"];
			        	   if(dataList && dataList.length>0){
			        		   for(var i=0;i<dataList.length;i++){
			        			   dataStr+="<li onclick='goToUpdateHomeTb(\""+dataList[i]["siteCode"]+"\","+dataList[i]["homeNum"]+",\""+dataList[i]["siteName"]+"\")'><p class='clearfix top'><span class='fl colo-a0 font-20'>"+dataList[i]["siteCode"]+"</span>"
			        			   +"<span class='fr colo-6e font-22'>"+dataList[i]["homeNum"]+"条</span></p>"
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
	                        	   $("#updateHome_list_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
			        		   
			        	   }else{
			        		   // 锁定
	                           me.lock();
			        		   $("#updateHome_id").html("");
			        		   dataStr+="<p class='font-30 unkown-p'>昨日各监测站点首页未更新内容<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
			        		   $("#updateHome_id").append(dataStr);
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
function goToUpdateHomeTb(code,homeNum,name){
	var siteName=name;
	console.log("  code:"+code+"  homeNum:"+homeNum+" siteName:"+siteName);
	window.location.href=webPath+"/freeToken_updateHomeTbIndex.action?siteCode="+code
	+"&updateHome="+homeNum+"&scanDate="+scanDate+"&siteName="+siteName;
}