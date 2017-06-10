var accuracyOfContentTitle = [];//内容正确性表头
accuracyOfContentTitle.push({"mDataProp": "dataNumber", "bWidth":"40px","bSortable": false,"sTitle": "序号", "sClass": "text-left", "bVisible": true, "tabIndex": -7});
accuracyOfContentTitle.push({"mDataProp": "errorType", "bWidth":"200px","bSortable": false,"sTitle": "错误类型", "sClass": "text-left", "bVisible": true, "tabIndex": -6});
accuracyOfContentTitle.push({"mDataProp": "questionDesc", "sTitle": "问题描述","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -5});
accuracyOfContentTitle.push({"mDataProp": "url","bWidth":"280px", "sTitle": "网页URL","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -4});
accuracyOfContentTitle.push({"mDataProp": "imgUrl", "bWidth":"55px","sTitle": "截图","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -3});
var table_accuracy_content;
$(function () {
	table_accuracy_content = $("#table_accuracy_content").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 10,
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
            "sZeroRecords": "没有要显示的数据！"
        },
        "bInfo": true,
        "bFilter" : false,
        "bSort": false,
        "bPaginate": true,
        "bRetrieve": true,
        "bLengthChange": false,
        "bServerSide": true,
        "sAjaxSource": webPath+ "/accuracyOfContent_accuracyContentPage.action",
        "sAjaxDataProp": function (data) {
        	if(data.body.length>0){
        		$("#table_accuracy_content_hide").removeClass("hide");
        		$("#table_accuracy_content").removeClass("hide");
        		$("#table_accuracy_content_hide1").addClass("hide");
        	for (var i = 0; i < data.body.length; i++) {
    			data.body[i]["questionDesc"] = "<td class='td_left'><div class='wz-name' title='"+data.body[i]["questionDesc"]+"'>" +data.body[i]["questionDesc"].substring(0,30)+"</div></td>";
    			data.body[i]["url"] = "<td class='td_left'><div class='wz-name' title='"+data.body[i]["url"]+"'>" +data.body[i]["url"].substring(0,30)+"</div></td>";
    			if(data.body[i]["imgUrl"].match("http:")){
    				data.body[i]["imgUrl"] = "<a target='_blank' href='"+data.body[i]["imgUrl"]+"'><i class='kuaiz'></a></i>";
    			}else{
    				data.body[i]["imgUrl"] ="<a target='_blank' href='"+webPath+data.body[i]["imgUrl"]+"'><img alt='截图' src='"+webPath+"/images/jiet.png'></a></img>";
    			}
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
            
        }
    });
});

