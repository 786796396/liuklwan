var homeConnectivityTitle = [];
homeConnectivityTitle.push({"mDataProp": "earlyCheck", "bSortable": false,"sTitle": "<input type='checkbox' id='select_all'>", "sClass": "center", "bVisible": true, "tabIndex": -7});
homeConnectivityTitle.push({"mDataProp": "dataNumber", "bSortable": false,"sTitle": "序号", "sClass": "center", "bVisible": true, "tabIndex": -7});
homeConnectivityTitle.push({"mDataProp": "siteCode", "sTitle": "网站标识码","bSortable": false, "sClass": "center font_701999", "bVisible": true, "tabIndex": -6});
homeConnectivityTitle.push({"mDataProp": "siteName", "sTitle": "网站名称","bSortable": false, "sClass": "", "bVisible": true, "tabIndex": -6});
homeConnectivityTitle.push({"mDataProp": "newEarlyNum", "sTitle": "新预警数/总数","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -4});
homeConnectivityTitle.push({"mDataProp": "read", "sTitle": "","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -3});
var table_data_homeConnectivity;
$(function () {
	var earlyType=$("#earlyType").val();
	table_data_homeConnectivity = $("#early_table").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 10,
        "aaSorting": [],
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
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "sAjaxSource": webPath+ "/early_queryList.action?siteCode=" + siteCodeParam+"&earlyType="+earlyType,
        "sAjaxDataProp": function (data) {
            return data.body;
            
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": homeConnectivityTitle,
        "fnDrawCallback": function () {
        	$("#early_table_wrapper input[type='checkbox']").iCheck({
        		checkboxClass: 'icheckbox_square-blue',
        		radioClass: 'iradio_square-blue'
        	});
        },
        "fnInitComplete": function () {
            $("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $("#early_table_length").hide();
            $("#early_table_info").hide();
            $("#early_table_filter").hide();
            var table_dataManager_wrapper = $("#table_data");
            table_dataManager_wrapper.find("th").css("cursor", "default");
        	/*============================
        	@author:modal
        	@time:2015-10-09
        	============================*/
        	$("#early_table").on("click",".view-modal",function(){
        		var dataHtml= "<tr><th style='width:50px;'>序号</th><th class='th_left' style='width:100px;''>问题类型</th>" +
        				"<th class='th_left'>问题描述</th><th style='width:160px;'>发现时间</th></tr>";
    			$("#yujModalTab").html(dataHtml);
        		//总页数
           	 	var pageTotal;
           	 	//当前页数
           	 	var pageNo;
	           	//总记录数
	           	var recordSize;
        		var id=$(this).attr("id");
        		 $.ajax({  
                     type : "POST",  
                     url : webPath+"/early_getEarlyDetail.action",  
                     data : {
                    	 siteCode:id,
                    	 size:10,
                    	 earlyType:earlyType
                     },  
                     dataType:"json",
                     async : false,  
                     success : function(data) {
                    	 var body = data.body;
                    	 var siteName = data.siteName || "";
    		         	 $("#myModalLabel1").html(siteName + "预警详细");
                    	 for (var int = 0; int < body.length; int++) {
                    		 var str=body[int];
                    		 $("#yujModalTab").append("<tr><td>"+str.dataNumber+"</td>" +
                    		 		"<td class='td_left font_701999'>"+str.earlyTypeName+"</td>" +
                    		 		"<td class='td_left'>"+str.queDescribe+"</td>" +
                    		 		"<td>"+str.scanTime+"</td>" +
                    		 				"</tr>");
						}
                    	 //总页数
                    	 pageTotal = data.pageTotal;
                    	 //当前页数
                    	 pageNo = data.PageNo;
                    	 //总记录数
                    	 recordSize = data.recordSize;
                    	 $("#yujModalTab").append("<input type='hidden' id='pageNo' value='"+pageNo+"'>" +
                     	 		"<input type='hidden' id='pageTotal' value='"+pageTotal+"'>" +
                     	 		"<input type='hidden' id='early_id' value='"+id+"'>" +
                     	 		"<input type='hidden' id='early_sum' value='"+data.earlySum+"'>")

                     }
                    });
        			if(pageTotal>pageNo){
        				$("#tabLoading").show();
        			}
        		$('#viewModal').modal('show');
        	});
        	$("#select_all").on('ifChecked ifUnchecked', function(event) {
    	        if (event.type == 'ifChecked') {
    	             $(":checkbox[name='table_check']").iCheck('check');
    	        } else {
    	             $(":checkbox[name='table_check']").iCheck('uncheck');
    	        }
    	    });
        },
        "fnPreDrawCallback": function(oSettings) {
            
        }
    });
	
});
//<!-- add by sunjq -->
/**
 * 邮件督办
 */
function emailSupervision(){
	var parameters = "";
	  $('input[name="table_check"]:checked').each(function(){
	   	parameters+=$(this).val()+",";
	  });
	  
	  if(parameters){
	  	$.ajax( {
			   type : "POST",
			   url : webPath+"/early_sendEmail.action?parameters="+parameters+"&siteCode="+siteCodeParam,  
			   dataType:"json",
			   async : true,
			   success : function(data) {
				   if(data){
					   alert("邮件发送成功");
				   }else{
					   alert("邮件发送失败");
				   }
			   },error:function(){
				   alert("邮件发送失败");
			   }
		});
	  }else{
		  alert("请选择要发送的数据");
	  }
}
/**
 * 批量导出Excel
 */
function downExcel(){
	var parameters = "";
	  $('input[name="table_check"]:checked').each(function(){
	   	parameters+=$(this).val()+",";
	  });
	 var earlyType= $("#earlyType").val();
     window.location.href = webPath+"/early_downExcel.action?parameters="+parameters+"&siteCode="+siteCodeParam+"&earlyType="+earlyType;
}

