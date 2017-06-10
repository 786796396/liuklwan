
var siteCode="";
var checkType="";
//滑动分页加载数据
var pageNum = 1;//页数
var pageSize = 3; // 每页展示4个
var pageStart = 0;
var pageEnd = 3;

var firstFlag=true;


$(function(){
    $('.every-line .half-part').on('touchstart',function(){
        $('.every-line .half-part').removeClass('active');
        $('.tab-content').removeClass('active');
        $(this).addClass('active');
        var n=$(this).index();
        $('.tab-content').eq(n).addClass('active')
        changeTab(n);
    });
    
    siteCode=$("#siteCode_id").val();
    checkType=$("#checkType_id").val();
    
    //页面数据初始化
    initConnHomeOrgData();
    
});

function changeTab(type){
	//分页参数重置
	pageNum = 1;//页数
	pageSize = 3; // 每页展示4个
	pageStart = 0;
	pageEnd = 4;
	//清空数据
	$("#conn_home_per_list_org_id").html("");
	$("#conn_home_list_org_id").html("");
	
	if(type==1){//一周首页不连通率超过3%
    	$("#conn_home_list_org_id").hide();
    	$("#conn_home_per_list_org_id").show();
    	
		$("#conn_home_per_list_org_id").append("<div id='conn_home_per_org_id'></div>");
		getConnHomePerOrgData();
	}else{
    	$("#conn_home_list_org_id").show();
    	$("#conn_home_per_list_org_id").hide();
		
    	
    	$("#conn_home_list_org_id").append("<div id='conn_home_org_id'></div>");
    	 initConnHomeOrgData();
	}
}
//前7日  不连通率超过3%
function getConnHomePerOrgData(){
	   //清空列表数据
	   $("#conn_home_per_org_id").html("");
	    // dropload
	    $("#conn_home_per_list_org_id").dropload({
	        scrollArea : window,
	        loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/freeToken_connHomePerOrgData.action?siteCode="+siteCode+"&checkType="+checkType
			        +"&pageNum="+pageNum+"&pageSize="+pageSize,
			        dataType:"json",
			        success : function(data){
			           var resultMap=data;//后台返回数据
			           if(resultMap.errormsg){
			           		alert(resultMap.errormsg);
			           }else{
		                   	//循环判定使用
		                    pageEnd =pageSize * pageNum;
		                    pageStart = pageEnd - pageSize;
		                    pageNum=pageNum+1;
			        	   //总记录数
		                   	var pageTotal=data.pageTotal;
			        	   
			        	   var dataStr="";
			        	   var returnList=resultMap["connPer7List"];
			        	   if(returnList && returnList.length>0){
			        		   for ( var i = 0; i < returnList.length; i++) {
			        			   dataStr+="<div class='block-style' onclick='goToConnHomeTb(\""+returnList[i]["siteCode"]+"\")'>"
			        				   +"<div class='fl nums-part'><span class='green-num'>"+(i+1+pageStart)+"</span></div>"
			        				   +"<div class='fl describs'>"
			        				   +"<div class='top-part'><p class='font-18 colo-a0'>"+returnList[i]["siteCode"]+"</p>"
			        				   +"<p class='font-26 colo-000 margt-17'>"+returnList[i]["siteName"]+"</p></div>"
			        				 /*  +"<div class='bottom-part'><p class='font-20 colo-6e'>发现连不通时间\:"+returnList[i]["scanTime"]+"</p>"*/
			        				   +" <p class='font-20 colo-6e'>7日连不通率（截至昨日）\:"+returnList[i]["linkerrprop7"]+"</p></div></div></div>";
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
	                        	   $("#conn_home_per_org_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
			        	   }else{
			        		   // 锁定
	                           me.lock();
	                           // 无数据
	                           $("#conn_home_per_list_org_id").html("");
			        		   dataStr+="<p class='font-30 unkown-p'>没有发现该类问题<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
			        		   $("#conn_home_per_list_org_id").append(dataStr);

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


//今日首页不连通---统计
function initConnHomeOrgData(){
	   //清空列表数据
	   $("#conn_home_org_id").html("");
	    // dropload
	    $("#conn_home_list_org_id").dropload({
	        scrollArea : window,
	        loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/freeToken_connHomeOrgData.action?siteCode="+siteCode+"&checkType="+checkType
			        +"&pageNum="+pageNum+"&pageSize="+pageSize,
			        dataType:"json",
			        success : function(data){
			           var resultMap=data;//后台返回数据
			           if(resultMap.errormsg){
			           		alert(resultMap.errormsg);
			           }else{
		                   	//循环判定使用
		                    pageEnd =pageSize * pageNum;
		                    pageStart = pageEnd - pageSize;
		                    pageNum=pageNum+1;
			        	   //总记录数
		                   	var pageTotal=data.pageTotal;
			        	   
			        	   var dataStr="";
			        	   var returnList=resultMap["returnList"];
			        	   if(returnList && returnList.length>0){
			        		   for ( var i = 0; i < returnList.length; i++) {
			        			   dataStr+="<div class='block-style-first' onclick='goToConnHomeTb("+returnList[i]["siteCode"]+")'>"
			        				   +"<div class='fl nums-part'><span class='green-num'>"+(i+1+pageStart)+"</span></div>"
			        				   +"<div class='fl describs'>"
			        				   +"<div class='top-part'><p class='font-18 colo-a0'>"+returnList[i]["siteCode"]+"</p>"
			        				   +"<p class='font-26 colo-000 margt-17'>"+returnList[i]["siteName"]+"</p></div>"
			        				   +"<div class='bottom-part'><p class='font-20 colo-6e'>最后连不通时间\:"+returnList[i]["scanTime"]+"</p>"
			        				   +" <p class='font-20 colo-6e'>7日连不通率（截至昨日）\:"+returnList[i]["linkerrprop7"]+"</p></div></div></div>";
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
	                        	   $("#conn_home_org_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
			        	   }else{
			        		   // 锁定
	                           me.lock();
	                           // 无数据
	                           $("#conn_home_list_org_id").html("");
			        		   dataStr+="<p class='font-30 unkown-p'>今日暂时未发现首页连不通问题<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
			        		   $("#conn_home_list_org_id").append(dataStr);

			        	   }
			           }
			
			        },error : function(data){
			        	alert("查询数据异常");
	                    // 即使加载出错，也得重置
	                    me.resetload();
			        }
				});
	        }
	    });
}

function goToConnHomeTb(code){
	window.location.href=webPath+"/freeToken_connHomeTbIndex.action?siteCode="+code;
}

















