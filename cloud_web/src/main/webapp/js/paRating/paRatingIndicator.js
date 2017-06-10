
$(function () {

	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/paRating_getIndictor.action?taskId="+$("#taskId").val()+"&appraisalId="+$("#appraisalId").val(),// 请求的action路径
        success: function (data) { // 请求成功后处理函数。
        	if (data.success=="true") {
        		var html = "";
        		$("#taskName").html(data.taskName);
        		$("#appName").html(data.aName);
        		var list = data.list;
        		for(var j=0;j<list.length;j++){
        			var baseData = list[j];
        			html+="<tr><td class=\"text-center\">"+baseData.layer1+"</td>"+
                        "<td class=\"text-center\">"+baseData.layer2+"</td>"+
                        "<td class=\"text-center\">"+baseData.layer3+"</td>"+
                        "<td class=\"text-center\">"+baseData.weight+"</td>"+
                        "<td class=\"text-center\">"+baseData.content+"</td></tr>";
        		}
        		$("#tbodyy").html(html);
        		$("#tables").rowspan(0);
        		$("#tables").rowspan(1);
            } else {
            	alert("请求有误");
            }
        },error: function () {// 请求失败处理函数
        }
    });
});

function closePage(){
	window.close();
}
