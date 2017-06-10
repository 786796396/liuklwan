

	var siteCode="";
	//滑动分页加载数据
	var pageNum = 1;//页数
	var pageSize = 3; // 每页展示4个
	var pageStart = 0;
	var pageEnd = 3;
	
    $(function(){
        siteCode=$("#siteCode_id").val();
        //页面数据初始化
        initContCorrectTb();
    });
    
    //页面数据初始化
    function initContCorrectTb(){
        //清空列表数据
        $("#contCorrectTb_id").html("");
        // dropload
        $("#contCorrectTb_list_id").dropload({
            scrollArea : window,
            loadDownFn : function(me){
		    	$.ajax({
		       	 	type:"post",
		         	async:false,
		            url: webPath+"/costToken_getContCorrectTbData.action?siteCode="+siteCode
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
		                    
		            	   var returnList=resultMap["returnList"];
		            	   var dataStr="";
		            	   if(returnList && returnList.length>0){
		            		   for ( var i = 0; i < returnList.length; i++) {
		            			   dataStr+="<li class='clearfix'><i class='fl'></i><div class='fl right-part'><div class='clearfix'>"
		            				   +"<h4 class='fl'><a href=\'"+returnList[i]["url"]+"\' target=\'_blank\'>网页URL</a></h4>"
		            				   +"<span class='photo-img fr'> <img src='"+webPath+"/newWeiXin/images/icon-photo.png' alt=''" 
		            				   +"onclick='getShot(\""+returnList[i]["imgUrl"]+"\")'/></span></div>"
		            				   +"<p class='clearfix'><span class='title-txt-f fl'>疑似错误\：</span><span class='fl'>"+returnList[i]["wrongTerm"]+"</span></p>"
		            				   +"<p class='clearfix'><span class='title-txt-f fl'>推荐修改\：</span><span class='fl'>"+returnList[i]["expectTerms"]+"</span></p>"
		            				   +"<p class='clearfix'><span class='title-txt-f fl'>错误级别\：</span><span class='fl except-tit'>"+returnList[i]["correctType"]+"</span></p>"
		            				   +"<p class='clearfix'><span class='title-txt-f fl'>扫描时间\：</span><span class='fl except-tit'>"+returnList[i]["scanDate"]+"</span></p>"
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
	                        	   $("#contCorrectTb_id").append(dataStr);
	                               // 每次数据加载完，必须重置
	                               me.resetload();
	                           },1000);
		            	   }else{
			        		   // 锁定
	                           me.lock();
			        		   $("#contCorrectTb_list_id").html("");
		            		   dataStr+="<p class='font-30 unkown-p'>本次扫描未发现疑似错别字<img src='"+webPath+"/newWeiXin/images/unkown.png' class='unkown-img'>";
		            		   $("#contCorrectTb_list_id").append(dataStr);
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
    
    