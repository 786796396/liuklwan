var siteCodes = "";
var types = "";
$(function () {
	siteCodes = $("#siteCodes").val();
	types = $("#types").val();
	//获取周期数据
	getServicePeriod();
	$("#search_btn_id").click(function(){
		getServicePeriod();
    });
	$("#select_id").change(function(){
		getServicePeriod();
    });
});

function getServicePeriod(){
	var jsonObj={};
	jsonObj["key"]=$("#input_key_word_id").val();
	if(jsonObj["key"] =="请输入周期任务号"){
		jsonObj["key"]="";
	}
	jsonObj["datePd"]=$("#select_id").val();
	jsonObj["siteCode"]=siteCodes;
	jsonObj["type"]=types;
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/servicePeriod_getServicePeriod.action",
        data: JSON.stringify(jsonObj),
        contentType: "application/json",
        success:function(data){
        	var str = "";
        	if(data.NoContract == 'no'){
        		$("#service_period_tbody_id").hide();
        		$(".pay").addClass('free-html');
				$(".free-html").html("");
        		var pay = "<i></i><span class='font-bold'>提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'>点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href='mailto:jianguan@ucap.com.cn'>jianguan@ucap.com.cn</a>";
        		$(".pay").append(pay);
        	}else if(data.NoContract == 'yes'){
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		$(".pay").hide();
        		var servicePeriodList = data.servicePeriodList;
        		var stLists = data.stList;//批次
            	
            	var num = servicePeriodList.length;
            	var stListNum = (stLists+1);//批次+1
            	$("#service_period_tbody_id").html(str);
            	if(servicePeriodList.length>0){
            		var contractInfoId = 0;//合同ID
            		var groupNum = 0;//组次
            		for(var j=0;j<num;j++){
            			if(contractInfoId != servicePeriodList[j]["contractInfoId"]){//合同ID不同，批次-1
            				stListNum = (stListNum-1);
            				contractInfoId = servicePeriodList[j]["contractInfoId"];
            				groupNum = servicePeriodList[j]["rowspanNum"];
            				str+="<tr><td rowspan="+groupNum+"><div class='fuzhi-font1'>第"+stListNum+"批</div></td>"+//nums
    						"<td class='f_num'>"+groupNum+"</td>"+
    						"<td>"+servicePeriodList[j]["servicePeriodNum"]+"</td>"+
    						"<td><div class='fuzhi-font2'>起："+servicePeriodList[j]["startDate"]+"</div><div class='fuzhi-font2'>止："+servicePeriodList[j]["endDate"]+"</div></td>"+
    						"<td>"+ServicePeriodStatus[servicePeriodList[j]["status"]]+"</td>"+
    						"<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["webCount"]+"</a></td>"+
            				"<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=2&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["successReportWordNum"]+"</a></td>";
            				var site_code_session=$("#site_code_session").val();
            				if(site_code_session.length==6){
            					str+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=3&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["checkReportResultNum"]+"</a></td>";
            					str+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=4&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["IsReadNoticeNum"]+"</a></td>";
            				}
            				str+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>详情</a></td>"+
            				"</tr>";
            			}else{
            				str+="<tr><td class='f_num'>"+(groupNum-1)+"</td>";
            				groupNum = (groupNum-1);
        					str += "<td>"+servicePeriodList[j]["servicePeriodNum"]+"</td>"+
        					"<td><div class='fuzhi-font2'>起："+servicePeriodList[j]["startDate"]+"</div><div class='fuzhi-font2'>止："+servicePeriodList[j]["endDate"]+"</div></td>"+
        					"<td>"+ServicePeriodStatus[servicePeriodList[j]["status"]]+"</td>"+
        					"<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["webCount"]+"</a></td>"+
        					"<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=2&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["successReportWordNum"]+"</a></td>";
            				var site_code_session=$("#site_code_session").val();
            				if(site_code_session.length==6){
            					str+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=3&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["checkReportResultNum"]+"</a></td>";
            					str+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=4&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>"+servicePeriodList[j]["IsReadNoticeNum"]+"</a></td>";
            				}
            				str+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=1&servicePeriodId="+servicePeriodList[j]["id"]+"&batchNum="+stListNum+"&groupNum="+groupNum+"&webCount="+servicePeriodList[j]["webCount"]+"'>详情</a></td>"+
            				"</tr>";
            			}
            		}
            		$("#service_period_tbody_id").append(str);
            	}else{
            		$("#service_period_tbody_id").append("<tr><td colspan='10'>暂无结果数据</td></tr>");
            	}
        	}
        	}
        },error:function(data){
        	alert(data.errorMsg);
        }
    });
}
function spotJump(){
	var site_code_session=$("#site_code_session").val();
	var url = "";
	if(site_code_session.length > 6){
		url = webPath + "/servicePeriod_servicePeriod.action?type=2";
		changeCookie(2,null,null,url);
	}else{
		url = webPath + "/servicePeriod_servicePeriod.action?type=1";
		changeCookie(1,null,null,url);
	}
	
}
var ServicePeriodStatus={"0":"未开始服务","1":"服务中","2":"已完成服务"};
