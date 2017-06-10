var accuracyOfContentTitle = [];//内容正确性表头
accuracyOfContentTitle.push({"mDataProp": "dataNumber", "sWidth":"40px","bSortable": false,"sTitle": "序号", "sClass": "text-left", "bVisible": true, "tabIndex": -6});
accuracyOfContentTitle.push({"mDataProp": "errorType", "sWidth":"200px","bSortable": false,"sTitle": "错误类型", "sClass": "text-left", "bVisible": true, "tabIndex": -5});
accuracyOfContentTitle.push({"mDataProp": "wrongTerm","sWidth":"200px", "sTitle": "疑似错误","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -4});
accuracyOfContentTitle.push({"mDataProp": "expectTerms","sWidth":"200px", "sTitle": "推荐修改","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -3});
accuracyOfContentTitle.push({"mDataProp": "url","sWidth":"200px", "sTitle": "网页URL","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -2});
accuracyOfContentTitle.push({"mDataProp": "imgUrl", "sWidth":"60px","sTitle": "定位","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -1});
var table_accuracy_content;
$(function () {
	$("#datepicker_start").val(GetDateStr(-1));
	$("#datepicker_end").val(GetDateStr(-1));
	$("#scanDate_id_start").val(GetDateStr(-1));
	$("#scanDate_id_end").val(GetDateStr(-1));
	table_accuracy_content = $("#table_accuracy_content").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 100,
        "aaSorting": [[ 2, "desc"]],
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
        "bFilter" : false,
        "bSort": false,
        "bPaginate": true,
        "bRetrieve": true,
        "bLengthChange": false,
        "bServerSide": true,
        "sAjaxSource": webPath+ "/correctContent_correctContentPage.action",
        "sAjaxDataProp": function (data) {
        	
        	if(data.body.length>0){
        		$("#table_accuracy_content_hide").removeClass("hide");
        		$("#table_accuracy_content").removeClass("hide");
        		
        		//$("#table_accuracy_content_hide1").addClass("hide");
	        	for (var i = 0; i < data.body.length; i++) {
	    			data.body[i]["questionDesc"] = "<td class='td_left'><div class='wz-name wz-name-w1' title='"+data.body[i]["questionDesc"]+"'>" +data.body[i]["questionDesc"]+"</div></td>";
	    			data.body[i]["questionDesc"] = "<td class='td_left'><div class='wz-name wz-name-w1' title='"+data.body[i]["questionDesc"]+"'>" +data.body[i]["questionDesc"]+"</div></td>";
	    			
	        		var url=data.body[i]["url"];
	        		if(!url.match("http")){
	        			url="http://"+url;
	        		}
	    			
	    			data.body[i]["url"] = "<td class='td_left'><a class='wz-name wz-name-w1' href='"+url+"' target='_blank' title='"+url+"'>" +url+"</a></td>";
	    			
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
	            	//data.body[i]["imgUrl"]="<td><a target='_blank' href='"+data.body[i]["imgUrl"]+"'><i class='dingw'></i></a></td>";

	    		}
        	}
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": accuracyOfContentTitle,
        "fnInitComplete": function () {
            $("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $("#table_accuracy_content_filter").hide();
            $("#table_accuracy_content_length").hide();
            $("#table_accuracy_content_info").hide();
            var table_dataManager_wrapper = $("#table_data");
            table_dataManager_wrapper.find("th").css("cursor", "default");
        },
        "fnPreDrawCallback": function(oSettings) {
            $("#table_accuracy_content").siblings(".dropload-load").hide();
        }
    });
});

//快照跳转到指定页面
function getShot(imgUrl) {
	window.open(imgUrl);
}


