var homeConnectivityTitle = [];//14-栏目信息更新
homeConnectivityTitle.push({
	"mDataProp" : "dataNumber", "sWidth" : "56px", "bSortable" : false, "sTitle" : "序号", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -8
});
homeConnectivityTitle.push({
	"mDataProp" : "lastTime", "sWidth" : "126px", "sTitle" : "更新时间", "bSortable" : true, "sClass" : "center", "bVisible" : true, "tabIndex" : -7
});
homeConnectivityTitle.push({
	"mDataProp" : "title", "sWidth" : "206px", "bSortable" : false, "sTitle" : "标题", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -6
});
homeConnectivityTitle.push({
	"mDataProp" : "channelName", "sWidth" : "156px", "bSortable" : false, "sTitle" : "栏目名称", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -5
});
homeConnectivityTitle.push({
	"mDataProp" : "dicChannelName", "sWidth" : "156px", "bSortable" : false, "sTitle" : "栏目类别", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -4
});
homeConnectivityTitle.push({
	"mDataProp" : "url", "sWidth" : "216px", "bSortable" : false, "sTitle" : "稿件URL", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -3
});
homeConnectivityTitle.push({
	"mDataProp" : "imgUrl", "sWidth" : "71px", "bSortable" : false, "sTitle" : "快照", "bSortable" : false, "sClass" : "center", "bVisible" : true, "tabIndex" : -2
});
var table_up_channel_detail;
$(function() {
	table_up_channel_detail = $("#table_up_channel_detail").dataTable({
		"sDom" : '<"top"T<"clear">>frt<"toolbar">lip', "bDestroy" : true, "bAutoWidth" : false, "bDeferRender" : true, "bJQueryUI" : true, "sPaginationType" : "full_numbers", "iDisplayLength" : 100, "aaSorting" : [ [ "1", "desc" ] ], "oTableTools" : {
			"sSwfPath" : "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf", "sRowSelect" : "multi" //可选中行，single单行。multi多行    
		}, "oLanguage" : {
			"sSearch" : "查询:", "sLengthMenu" : '本页显示 <select>' + '<option value="50">50</option>' + '<option value="100">100</option>' + '<option value="200">200</option>' + '<option value="500">500</option>' + '<option value="1000">1000</option>' + '</select> ', "oPaginate" : {
				"sFirst" : "首页", "sLast" : "尾页", "sNext" : "下一页", "sPrevious" : "上一页"
			}, "sInfo" : "共 _TOTAL_ 条记录 (当前 _START_ 至 _END_)", "sLoadingRecords" : "数据正在加载...", "sInfoFiltered" : "", "sInfoEmpty" : "共 0 条记录", "sZeroRecords" : "没有要显示的数据！"
		}, "bInfo" : true, "bPaginate" : true, "bRetrieve" : true, "bServerSide" : true, "sAjaxSource" : webPath + "/updateChannelDetail_updateChannelPage.action", "sAjaxDataProp" : function(data) {

			if (data.body) {
				$("#table_data_channelUpdate_hide").removeClass("hide");
				$("#table_data_channelUpdate_hide1").removeClass("hide");
				$("#channel_table_id").addClass("hide");
				//$(".dropload-load").show();
				$("#table_data_channelUpdate_hide").html("共 <span>" + data.total + "</span> 条");
				$("#table_up_channel_detail").removeClass("hide");

				for ( var i = 0; i < data.body.length; i++) {

					data.body[i]["title"] = "<td class='td_left'><div class='wz-name' title='" + data.body[i]["title"] + "'>" + (data.body[i]["title"].substring(0, 20)) + "</div></td>";

					var url = data.body[i]["url"];
					if (!url.match("http")) {
						url = "http://" + url;
					}

					data.body[i]["url"] = "<td class='td_left'><a class='url-ellipsis' href='" + url + "' target='_blank' title='" + url + "'>" + (url.substring(0, 40)) + "</a></td>";

					// 快照处理 start=============
					var imgUrl = data.body[i]["imgUrl"] + "";
					if (imgUrl == '') {
						data.body[i]["imgUrl"] = "<td>无</td>";
					} else {
						if (imgUrl.match("htm")) {
							data.body[i]["imgUrl"] = "<td><i class='kuaiz' onclick='getShot(\"" + imgUrl + "\")'></i></td>";
						} else {
							data.body[i]["imgUrl"] = "<td><a target='_blank' href='" + imgUrl + "'><img alt='截图' src='" + webPath + "/images/jiet.png'/></a></td>";
						}
					}
					// 快照处理 end=============
					//data.body[i]["imgUrl"] ="<td><i class='kuaiz' onclick='getShot(\""+data.body[i]["imgUrl"]+"\")'></i></td>";

				}
				return data.body;

			} else {
				$("#table_up_channel_detail").addClass("hide");
			}
		}, "bProcessing" : false, "fnServerData" : fnDataTablesPipeline, "aoColumns" : homeConnectivityTitle, "fnInitComplete" : function() {
			$("#table_data").parent().parent().removeAttr("style");
			$("#table_data_filter").css("margin-right", "75%");
			$("#table_up_channel_detail_filter").hide();
			$("#table_up_channel_detail_length").hide();
			$("#table_up_channel_detail_info").hide();
			var table_dataManager_wrapper = $("#table_data");
			table_dataManager_wrapper.find("th").css("cursor", "default");
		}, "fnPreDrawCallback" : function(oSettings) {
			$("#table_up_channel_detail").siblings(".dropload-load").hide();
		}
	});
});
//快照跳转到指定页面
function getShot(imgUrl) {
	window.open(imgUrl);
}
