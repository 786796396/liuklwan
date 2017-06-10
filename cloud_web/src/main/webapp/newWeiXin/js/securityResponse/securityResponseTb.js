	var siteCode="";
	var checkType=0;
	var servicePeroidId="";
	
	//滑动分页加载数据
	var pageNum = 1;//页数
	var pageSize = 2; // 每页展示4个
	var pageStart = 0;
	var pageEnd = 2;
	
    $(function(){
        siteCode=$("#siteCode_id").val();
        checkType=$("#checkType_id").val();
        servicePeroidId=$("#servicePeroidId_id").val();
        
        //页面数据初始化
        initSecurityResponseData();
    });
    
    function initSecurityResponseData(){
        //清空列表数据
        $("#security_response_tb_id").html("");
        // dropload
        $("#security_response_tb_list_id").dropload({
            scrollArea : window,
            loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/advanceToken_getsecurityResponseTbData.action?siteCode="+siteCode
			        			+"&servicePeroidId="+servicePeroidId
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
			        			   dataStr+="<li class='clearfix'><i class='fl'></i><div class='fl right-part'><div class='clearfix'>"
			        				   +"<h4 class='fl'>"+returnList[i]["channelName"]+"</h4>"
			        				   +"<span class='blank-img fr'><img src='"+webPath+"/newWeiXin/images/blank-img.png' alt='' onclick='getShot(\""+returnList[i]["imgUrl"]+"\",\""+data.litImgUrl+"\")'/></span></div>"
			        				   +"<p class='clearfix'><span class='title-txt-f fl'>发现时间\：</span><span class='fl'>"+returnList[i]["scanDate"]+"</span></p>"
			        				   +"<p class='clearfix'><span class='title-txt-f fl'>类型\：</span><span class='fl except-tit'>"+returnList[i]["problemType"]+"</span></p>"
			        				   +"<p class='clearfix'><span class='title-txt-f fl'>描述\：</span><span class='fl except-tit'>"+returnList[i]["questionDescribe"]+"</span></p></div></li>";

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
	                        	   $("#security_response_tb_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
			        		 
			        	   }else{

			        		   // 锁定
	                           me.lock();
			        		   $("#security_response_tb_list_id").html("");
			        		   dataStr+="<p class='font-30 unkown-p'>本检测周期中，各站点不存在互动回应差的问题<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
			        		   $("#security_response_tb_list_id").append(dataStr);
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
    
    //快照页面
    function getShot(imgUrl,litImgUrl){
    	if(isEmpty(imgUrl)){
    		alert("暂时没有快照，无法查看！");
    		return;
    	}
    	if(imgUrl.match("htm")){//快照
    		window.open(imgUrl);
    	}else{
    		window.open("jsp/onlineReport/cutImgs.jsp?imgUrl="+imgUrl + "&litImgUrl="+litImgUrl);
    	}
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
    
    