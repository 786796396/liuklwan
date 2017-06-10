
var paTargetCountId = "";
$(function () {
	paTargetCountId = $("#paTargetCountId").val();

	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paProject_paProjectTaskList.action",// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if(paTargetCountId != 0){
        		if (data) {
            		var dataList = data.list;
            		
            		if(dataList!= null && dataList.length>0){
            			var html = "";
            			for ( var i = 0; i < dataList.length; i++) {
    						var dataBase = dataList[i];
    						html += "<tr>" +
    						"<td class=\"text-center w7p\">"+(i+1)+"</td>"+
    						"<td class=\"text-left w25p\">"+dataBase.taskName+"</td>"+
    						"<td class=\"text-left w20p\">"+dataBase.startDate+"至"+dataBase.endDate+"</td>"+
    						"<td class=\"text-left w20p\">"+dataBase.projectName+"</td>"+
    						"<td class=\"text-center\">";
    						if(dataBase.stauts == 1){
    							html +="<span class=\"colo-fb0012\">未启动</span></td>";
    						}else if(dataBase.stauts == 2 || dataBase.stauts == 3){
    							html +="<span class=\"colo-239d60\">进行中</span></td>";
    						}else if(dataBase.stauts == 4){
    							html +="<span>已完成</span></td>";
    						}else{
    							html +="<span></span></td>";
    						}
    						html +="<td class=\"text-left w10p \">";
    						if(dataBase.stauts != 1){
    							html +="<a href="+webPath+ "/paProject_paProjectDetail.action?taskId="+dataBase.id+"&projectId="+dataBase.projectId+"&startDate="+dataBase.startDate+"&endDate="+dataBase.endDate+"&projectName="+dataBase.projectName+"&stauts="+dataBase.stauts+"&taskName="+dataBase.taskName+"  class=\"colo-01a5ec\">查看详情</a></td>";
    						}else{
    							html +="查看详情</td>";
    						}
                        
            			}
            			$("#paTable").html(html);
            			
            			
//            			$(".details").bind("click",function(){
//            				
//            			});
            		}else{
            			var text = bodyMation("无绩效考评情况");
           			 	$("#paTable").append(text);
            		}
                } else {
                	var text = bodyMation("无绩效考评情况");
       			 	$("#paTable").append(text);
                }
        	}else{
        		 var text = bodyMation("网站绩效考评，是开普云平台提供的一项基于部门自评的上级考评服务，<br/>实现全流程管理，可大大提高网站考评准确性和规范性。<br/><br/>如需使用此服务，请联系我们的服务团队4000-976-005");
    			 $("#paTable").append(text);
        	}
        	
        },error: function () {// 请求失败处理函数
        }
    });
});
function bodyMation (name){ 
	var html = '';
	html += ' <tr>';
	html += ' <td colspan="7" class="jiangbei-part" style="padding-bottom: 100px; background:#fff;">';
	html += ' <i class="publi-ico_2 jiangbei"></i><p style="font:normal 16px/28px Microsoft Yahei;color: #6e6e6e;">'+name+'</p>';
	html += ' </td></tr>';
	return html;
}	