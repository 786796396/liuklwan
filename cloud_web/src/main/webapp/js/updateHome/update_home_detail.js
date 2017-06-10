var homeConnectivityTitle = [];//首页更新
homeConnectivityTitle.push({"mDataProp": "dataNumber", "sWidth":"56px","bSortable": false,"sTitle": "序号", "sClass": "text-left", "bVisible": true, "tabIndex": -7});
homeConnectivityTitle.push({"mDataProp": "updateTime", "bSortable": true,"sWidth":"216px","sTitle": "更新时间", "sClass": "text-left", "bVisible": true, "tabIndex": -6});
homeConnectivityTitle.push({"mDataProp": "title", "bSortable": false,"sWidth":"300px","sTitle": "标题", "sClass": "text-left", "bVisible": true, "tabIndex": -5});
homeConnectivityTitle.push({"mDataProp": "url", "bSortable": false,"sWidth":"300px","sTitle": "网页URL", "sClass": "text-left", "bVisible": true, "tabIndex": -4});
var table_update_home;
$(function () {
	table_update_home = $("#table_update_home").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
        "bDestroy": true,
        "bAutoWidth": false,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 100,
        "aaSorting": [[1,]],
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
        },
        "oLanguage": {
            "sSearch": "查询:",
            "sLengthMenu": '本页显示 <select>' +
                '<option value="50">50</option>' +
                '<option value="100">100</option>'+
                '<option value="200">200</option>' +
                '<option value="500">500</option>'+
                '<option value="1000">1000</option>'+
                '</select> ',
            "oPaginate": {
                "sFirst": "首页",
                "sLast": "尾页",
                "sNext": "下一页",
                "sPrevious": "上一页"
            },
            "sInfo": "共 _TOTAL_ 条记录 (当前 _START_ 至 _END_)",
            "sLoadingRecords": "数据正在加载...",
            "sInfoFiltered": "",
            "sInfoEmpty": "共 0 条记录",
            "sZeroRecords": "未发现问题"
        },
        "bInfo": true,
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "sAjaxSource": webPath+ "/updateHome_updateHomePage.action",
        "sAjaxDataProp": function (data) {
        	if(data.exists>0){
        		$(data.hide).removeClass("hide");
        		$(data.hide).find("span").html(data.exists);
        		$(data.hide+"1").removeClass("hide");
        		$(data.hide+"2").removeClass("hide");
        	}
        	for (var i = 0; i < data.body.length; i++) {
        		 data.body[i]["title"] = "<td class='td_left'><div class='wz-name wz-name-w4' title='"+data.body[i]["title"]+"'>" +data.body[i]["title"]+"</div></td>";
        		 
         		var url=data.body[i]["url"];
        		if(!url.match("http")){
        			url="http://"+url;
        		}
        		 
        		 data.body[i]["url"] = "<td class='td_left'><a class='url-ellipsis' style='width:190px;' href='"+url+"' target='_blank' title='"+url+"'>" +url+"</a></td>";
        	}
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": homeConnectivityTitle,
        "fnInitComplete": function () {
            $("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $("#table_update_home_filter").hide();
            $("#table_update_home_length").hide();
            $("#table_update_home_info").hide();
            var table_dataManager_wrapper = $("#table_data");
            table_dataManager_wrapper.find("th").css("cursor", "default");
        },
        "fnPreDrawCallback": function(oSettings) {
            $("#table_update_home").siblings(".dropload-load").hide();
        }
    });
});

