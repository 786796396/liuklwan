var siteCode="";
var selectType=4;
var hasFlag=false;

//滑动分页加载数据
var pageNum = 1;//页数
var pageSize = 3; // 每页展示4个
var pageStart = 0;
var pageEnd = 3;

$(function(){
	FastClick.attach(document.body);
	
	siteCode=$("#siteCode_id").val();
    //下拉框
    var showItem = document.querySelector("#select_id");
    var showUl=document.querySelector('.show-ul');
    showItem.addEventListener("touchstart", function(){ 
    	showUl.style.display='block';
    });
	$(".show-ul >li").click(function(e){
		e.stopPropagation();
		$(".show-ul > li").removeClass("active");//清空所有的active
		$(this).addClass("active");//选中的添加active
		$(".show-item").html($(this).html());
		hasFlag=false;
		$(".show-ul").attr("style","display:none");
		selectType=$(this).val();
		if(selectType==1){
			$("#select_id > i").attr("style","width:2.3rem;")
		}else if(selectType==2){
			$("#select_id > i").attr("style","width:.28rem;")
		}else if(selectType==3){
			$("#select_id > i").attr("style","width:1.3rem;")
		}else if(selectType==4){
			$("#select_id > i").attr("style","width:1.7rem;")
		}
		
		//下拉框切换    需要重置分页参数
		//pageNum = 1;//页数
		//pageStart = 0;
		//pageEnd = 3;
		
		//重置滑动区域
       // $("#security_channel_tb_list_id").html("<ul class='green-icon-part' id='security_channel_tb_id'></ul>");
		
		//加载页面数据
		initPageData();
	});
	$(document).click(function(e){
		  e.stopPropagation();
	       var _con = $('.show-ul');
	       var _con2=$("#select_id");
	       if(!_con.is(e.target) && _con.has(e.target).length == 0 &&(!_con2.is(e.target) && _con2.has(e.target).length == 0)){
	    	   showUl.style.display='none';
	       }
		});
	//加载页面数据
	initPageData();
	
});
//页面数据初始化
function initPageData(){
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/costToken_getSecurityChannelTbData.action?siteCode="+siteCode+"&selectType="+selectType
        +"&pageNum="+pageNum+"&pageSize="+pageSize,
        dataType:"json",
        success : function(data){
	           var resultMap=data;//后台返回数据
	           if(resultMap.errormsg){
	           		alert(resultMap.errormsg);
	           		return;
	           }else{
	        	   $("#security_channel_tb_id").html("");
	        	   var dataStr="";
	        	   var returnList=resultMap["returnList"];
	        	   if(returnList && returnList.length>0){
	        		   for ( var i = 0; i < returnList.length; i++) {
	        			   dataStr+="<li class='clearfix'><i class='fl'></i><div class='fl right-part'><div class='clearfix'>"
	        				   +"<h4 class='fl'><a href='"+returnList[i]["url"]+"'>"+returnList[i]["channelName"]+"</a></h4>"
	        				   +"<span class='photo-img fr'> <img src='"+webPath+"/newWeiXin/images/icon-photo.png' alt='' "
	        				   +"onclick='getShot(\""+returnList[i]["imgUrl"]+"\")' /></span></div>"
	        				   +"<p><span class='title-txt-f'> 栏目类别： </span><span>"+returnList[i]["channelTypeName"]+"</span></p>"
	        				   +"<p><span class='title-txt-f'> 监测时间： </span><span>"+returnList[i]["scanDate"]+"</span></p>"
	        				   +"<p><span class='title-txt-f'> 最后更新： </span><span>"+returnList[i]["updateTime"]+"</span></p>"
	        				   +"<p><span class='title-txt-f'> 未更新： </span><span>"+returnList[i]["notUpdateNum"]+"天</span></p></div> </li>";

	        		   }
                      $("#security_channel_tb_id").append(dataStr);

	        	   }else{
	        		   dataStr+="<p class='font-30 unkown-p'>本次扫描未发现更新不及时问题<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
	        		   $("#security_channel_tb_id").append(dataStr);
	        	   }
	           }
	        },error : function(data){
	        	alert("查询数据异常");
	        	return;
	        }
		});
	
	   //清空列表数据
	 /*  $("#security_channel_tb_id").html("");
	    // dropload
	    $("#security_channel_tb_list_id").dropload({
	        scrollArea : window,
	        loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/costToken_getSecurityChannelTbData.action?siteCode="+siteCode+"&selectType="+selectType
			        +"&pageNum="+pageNum+"&pageSize="+pageSize,
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
				        	   var returnList=resultMap["returnList"];
				        	   if(returnList && returnList.length>0){
				        		   for ( var i = 0; i < returnList.length; i++) {
				        			   dataStr+="<li class='clearfix'><i class='fl'></i><div class='fl right-part'><div class='clearfix'>"
				        				   +"<h4 class='fl'>"+returnList[i]["channelName"]+"</h4>"
				        				   +"<span class='photo-img fr'> <img src='"+webPath+"/newWeiXin/images/icon-photo.png' alt='' "
				        				   +"onclick='getShot(\""+returnList[i]["imgUrl"]+"\")' /></span></div>"
				        				   +"<p><span class='title-txt-f'> 栏目类别： </span><span>"+returnList[i]["channelTypeName"]+"</span></p>"
				        				   +"<p><span class='title-txt-f'> 监测时间： </span><span>"+returnList[i]["scanDate"]+"</span></p>"
				        				   +"<p><span class='title-txt-f'> 最后更新： </span><span>"+returnList[i]["updateTime"]+"</span></p>"
				        				   +"<p><span class='title-txt-f'> 未更新： </span><span>"+returnList[i]["notUpdateNum"]+"天</span></p></div> </li>";
				        			   
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
		                        	   $("#security_channel_tb_id").append(dataStr);
		                               // 每次数据加载完，必须重置
		                               me.resetload();
		                           },1000);
				        	   }else{
				        		   // 锁定
		                           me.lock();
				        		   $("#security_channel_tb_list_id").html("");
				        		   dataStr+="<p class='font-30 unkown-p'>本次扫描未发现更新不及时问题<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
				        		   $("#security_channel_tb_list_id").append(dataStr);
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
	    });*/
	}
	

//快照页面
function getShot(imgUrl){
	if(isEmpty(imgUrl)){
		alert("暂时没有快照，无法查看！");
		return;
	}
	window.open(imgUrl);
}
/*
 * 验证是否为空
 */
function isEmpty(str) {
    var isOK = false;
    if (str == undefined || str == 'undefined' || str == "" || str == "null") {
        isOK = true;
    }
    return isOK;
}




