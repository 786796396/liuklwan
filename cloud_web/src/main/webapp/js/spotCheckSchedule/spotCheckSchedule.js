 $(function (){
    //抽查首页面--表格数据初始化
    getTables();
    
    //抽查首页面--搜索框添加点击搜索事件
    $("#search_btn_id").click(function(){
    	getTables();
    });
    
    //抽查首页面--时间段下拉框，添加绑定事件
    $("#select_id").change(function(){
    	
    	getTables();
    });
 });
 
 var spotStatus={"0":"未启动","1":"检查中","2":"检查完成"};
 //抽查数据
 function getTables(){
		var key = $("#input_key_word_id").val();
		var datePd = $("#select_id").val();
//		$("#spot_check_no_data_id").attr("style","display:none");
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/spotCheckSchedule_getScheduleList.action",
	        data:{
	        	key:key,
	        	datePd:datePd,
	        },
	        dataType : "json",
	        success : function(data){
	        	//表数据清空
	    		//抽查总数
	    		var spotSum=data.spotSum;
	    	    //已抽查次数
	    		var spotNum=data.spotNum;
	    		
	    		 //剩余可抽查次数
	    		var remainNum=data.remainNum;
	            $("#spotSum").html(spotSum);
	            $("#spotNum").html(spotNum);
	            $("#remainNum").html(remainNum);
	            if(null!=data.curredContractCode){
	            	 $("#curredContractCode").html("当前服务合同号 : "+data.curredContractCode);
	            }
	           
	            if(spotSum==0){
	            	$("#new_task").removeClass("btn-3ac42d");
	            	$("#new_task").addClass("btn-b1b2b3");
	            }
	        	//页面返回错误
	        	if(data.errormsg){
	        		var errormsg=data.errormsg;
	        		//隐藏表格内容，显示暂无数据
	        		$("#spot_check_schedule_table_id").attr("style","display:none");
	        		$("#spot_check_no_data_id").attr("style","display:block");
	        		//表数据清空
	        	}else{
	        		//显示表格内容，隐藏暂无数据
	        		$("#spot_check_schedule_table_id").attr("style","display:block");
	        		$("#spot_check_no_data_id").attr("style","display:none");
	        		
	        		//所有数据
	        		var returnList=data.returnList;
	        		//批次数据
	        		var batchNumList=data.batchNumList;
	        		//表数据清空
	        		$("#spot_check_schedule_tbody_id").html("");
	        		
	        		/**
	        		 * 页面处理逻辑
	        		 * 		1 首先遍历批次集合表，获取每一个批次的所有组数据，拼装该批次数据，将改组数据封添加到表中
	        		 */
	        		
//	        		if(returnList.length > 0 || batchNumList > 0){
	        			
	        		if(batchNumList){
	            		//遍历批次数组
	        			var str ="启动抽查";
	        			if(site_code_session == "440000"){
	        				str = "启动考评";
	        			}
	        			for(var i=0;i<batchNumList.length;i++){
	        				var listStr="";
	            			var listNum=0;
	            			for(var x=0;x<returnList.length;x++){
	            				if(batchNumList[i]==returnList[x]["batchNum"]){
	            					listNum+=1;
	            				}
	            			}
	            			//遍历所有数据的数据
	            			for(var j=0;j<returnList.length;j++){
	            				if(batchNumList[i]==returnList[j]["batchNum"]){
	                				//判断是否为该批次的第一条记录，如果是第一条记录的话，该tr中会有第几批次的td,其他行没有
	                				if(listStr==""){
	                					listStr+="<tr><td rowspan="+listNum+"><div class='fuzhi-font1'>第"
	                					
	                					+returnList[j]["batchNum"]+"批</div><br/><div>"
	                					+returnList[j]["contractCode"]+"</div></td>"+
	                					"<td class='f_num'>"+returnList[j]["groupNum"]+"</td>"+
	                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
	                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]
	                					+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]
	                					+"</div></td>"+
	                					"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
	                					//检查完成--不显示添加站点按钮  检查中显示添加站点按钮
//	                					listStr+="<td>";
//	                					if(returnList[j]["state"]==0){
////	                						<i ></i>&nbsp;
//	                						//添加站点
//	                						listStr+="<a href='#' onclick='openByBatchGroup("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>增加站点</a><br/>";
//	                						//启动考评
//	                						listStr+="<a href='#' onclick='openCheckResult("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+")'>"+str+"</a><br/>";
//	                						//删除
//	                						listStr+="<a href='#' onclick='deleteSpotCheckSchedule("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>删除</a>";
//	                					}else{
//	                						listStr+="<div >增加站点</div>";
//	                						listStr+="<div  >"+str+"</div>";
//	                						listStr+="<div  >删除</div>";
//	                					}
//	            						
//	            						listStr+="</td>";
	            						
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=2&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["successReportWordNum"]+"</a></td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=3&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["checkReportResultNum"]+"</a></td>";
//	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=4&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["IsReadNoticeNum"]+"</a></td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"&siteCode="+returnList[j]["siteCode"]+"'>详情</a></td>";                					
	                					listStr+="</tr>";
	                					
	                					
	                				}else{
	                					listStr+="<tr><td class='f_num'>"+returnList[j]["groupNum"]+"</td>"+
	                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
	                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]+"</div></td>"+
	                					"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
	                					
	                					//检查完成--不显示添加站点按钮  检查中显示添加站点按钮
//	                					listStr+="<td>";
//	                					if(returnList[j]["state"]==0){
////	                						<i ></i>&nbsp;
//	                						//添加站点
//	                						listStr+="<a href='#' onclick='openByBatchGroup("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>增加站点</a><br/>";
//	                						//启动考评
//	                						listStr+="<a href='#' onclick='openCheckResult("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+")'>"+str+"</a><br/>";
//	                						//删除
//	                						listStr+="<a href='#' onclick='openByBatchGroup("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>删除</a>";
//	                					}else{
//	                						listStr+="<div >增加站点</div>";
//	                						listStr+="<div  >"+str+"</div>";
//	                						listStr+="<div  >删除</div>";
//	                					}
//	            						listStr+="</td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=2&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["successReportWordNum"]+"</a></td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=3&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["checkReportResultNum"]+"</a></td>";
//	                					listStr+="<td ><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=4&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["IsReadNoticeNum"]+"</a></td>";
	                					listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"&siteCode="+returnList[j]["siteCode"]+"'>详情</a></td>";
	                					listStr+="</tr>";
	                				}
	            				}
	            			}
	            			$("#spot_check_schedule_tbody_id").append(listStr);
	            		    //增加站点添加绑定事件
	            		    //getSpotOnclick();
	            		} 
	            		
	        		}
//	        		}else{
//	        			listStr+="<tr><td colspan='9'>暂无结果数据</td></tr>";
//	        			$("#spot_check_schedule_tbody_id").append(listStr);
//	        		}
	        	}
	        },error:function(data){
        		$("#spot_check_no_data_id").attr("style","display:none");
		   }
	     });
	}