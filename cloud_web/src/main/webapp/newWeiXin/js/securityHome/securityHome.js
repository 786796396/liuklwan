var siteCode="";
var checkType="";

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
    
    
    //页面数据初始化
    initSecurityHomeOrgData();
    
    
});

function initSecurityHomeOrgData(){
   //清空列表数据
   $("#securityHomeDays_list_id").html("");
    // dropload
    $("#securityHomeDays_id").dropload({
        scrollArea : window,
        loadDownFn : function(me){
			$.ajax({
		   	 	type:"post",
		     	async:false,
		        url:webPath+"/freeToken_getsecurityHomeOrgData.action?siteCode="+siteCode
		        +"&checkType="+checkType+"&pageNum="+pageNum
		        +"&pageSize="+pageSize,
		        dataType:"json",
		        success : function(data){
		            var resultMap=data;//后台返回数据
		            
		            if(resultMap.errrorMsg){
		            		alert(resultMap.errrorMsg);
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
		         			   dataStr+="<li onclick='goToSecurityHomeTB(\""+returnList[i]["siteCode"]+"\",\""+returnList[i]["siteName"]+"\")'><p class='clearfix top'>" 
		         			   		+"<span class='fl colo-a0 font-20'>"+ returnList[i]["siteCode"]+"</span>"
		         			   		+"<span class='fr colo-6e font-22'>"+returnList[i]["notUpdateDays"]+"天</span>"
		         			   		+"<p class='bottom colo-000 font-26'>"+returnList[i]["siteName"]+"</p>"
		         			   		+"<div class='clearfix last-time'>"
		         			   		+"<span class='wx-top-line'></span>"
		         			   		+"<span class='fl'>最后更新 :"+returnList[i]["updateTime"]+"</span>"
		         			   		+"<span class='fr'>最近扫描:"+returnList[i]["scanDate"]+"</span>"
		         			   		+"</div></li>";
	        				   
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
                        	   $("#securityHomeDays_list_id").append(dataStr);
                               // 每次数据加载完，必须重置
                               me.resetload();
                           },1000);
		         	
		         	   }else{
		         		    // 锁定
                           me.lock();
                           $("#securityHomeDays_id").html(""); 
		         		   dataStr+="<p class='font-30 unkown-p'>截至昨日，各站点首页更新及时<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
		         		   $("#securityHomeDays_id").append(dataStr); 
		         	   }
		            }
		 		},error : function(data){
                    // 每次数据加载完，必须重置
                    me.resetload();
         		    // 锁定
                    me.lock();
		        	alert("查询数据异常");
		        	return;
		        }
			});
        }
    });
}

//首页更新不及时点击事件
function goToSecurityHomeTB(code,siteName){
	console.log(siteName);
	window.location.href=webPath+"/freeToken_securityHomeTbIndex.action?siteCode="+code+"&siteName="+siteName;
	
}