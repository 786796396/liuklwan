
	var siteCode="";
	var checkType=0;
	var servicePeroidId="";
	

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
        servicePeroidId=$("#servicePeroidId_id").val();
        
        //页面数据初始化
        initSecurityFatalData();
    });
    
    function initSecurityFatalData(){
        //清空列表数据
        $("#security_fatal_error_id").html("");
        // dropload
        $("#security_fatal_error_list_id").dropload({
            scrollArea : window,
            loadDownFn : function(me){
				$.ajax({
			   	 	type:"post",
			     	async:false,
			        url: webPath+"/advanceToken_getSecurityFatalErrorOrgData.action?siteCode="+siteCode
			        			+"&servicePeroidId="+servicePeroidId+"&checkType="+checkType
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
			        			   dataStr+="<li onclick='goToSecurityFatalErrorTb(\""+returnList[i]["siteCode"]+"\","+returnList[i]["fatalErrorNum"]+",\""+returnList[i]["siteName"]+"\")'>"
			        				   +"<p class='clearfix top'><span class='fl colo-a0 font-20'>"+returnList[i]["siteCode"]+"</span>"
			        				   +"<span class='fr colo-6e font-22'>"+returnList[i]["fatalErrorNum"]+"个</span></p>"
			        				   +"<p class='bottom colo-000 font-26'>"+returnList[i]["siteName"]+"</p></li>";
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
	                        	   $("#security_fatal_error_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
			        		   
			        	   }else{
			        		   // 锁定
	                           me.lock();
			        		   $("#security_fatal_error_list_id").html("");
			        		   dataStr+="<p class='font-30 unkown-p'>本检测周期中，各站点没有严重问题<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
			        		   $("#security_fatal_error_list_id").append(dataStr);
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
    
    //跳转到填报单位
    function goToSecurityFatalErrorTb(code,fatalErrorNum,name){
		window.location.href=webPath+"/advanceToken_securityFatalErrorTbIndex.action?siteCode="+code
		+"&fatalErrorNum="+fatalErrorNum+"&siteName="+name+"&servicePeroidId="+servicePeroidId;
    }


