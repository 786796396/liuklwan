var siteId = $("#siteCode").val();
$(function(){
	getData();
	spConfigurationTable();
});

//列表查询    
function spConfigurationTable() {
	$.ajax({
		type : "POST",
		url : webPath + "/spChannel_spConfiguration.action",
		data:{siteCode:siteId},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.success=='true') {
				$('#logo').attr("src",data.logo);
				$("#url").html(data.url);
				$("#bottomText").html(data.bottomText);
			} else {
				
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

//获取文章数据
function getData() {
	var user={
		siteCode:siteId,
		id:$("#articleId").val()
	}
	$.ajax({
		type : "POST",
		data:user,
		url : webPath + "/spChannel_getArticle.action",
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.success=='true') {
				$("#title").html(data.title);
				$("#channelName").html(data.channelName);
				$("#content").html(data.content);
				$("#sourceName").html(data.sourceName);
				$("#sourceUrl").html(data.sourceUrl);
				if(data.date.length>19){
					$("#date").html(data.date.substring(0,19));
				}
				
//				$("#buttom").html(data.buttom);
//				$("#logo").attr("src",data.logo);
				
			} else {
			}
		},
		error : function(data) {
		}
	});
}

