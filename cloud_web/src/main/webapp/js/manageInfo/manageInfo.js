var modifySiteCode;// 定义一个ID用来保存每次打开某一条订单信息的ID，用来提交修改传到后台
var modifyOrderInfoId;// 订单ID用来保存每次打开不同订单的不同网站，用来提交修改传到后台
var table_data_channel_columnTab;
var maxRecord=0;
var isChannelUrlLegal=false;
var isSearch = true;
$(function () {
	
	
	$("input[type='checkbox'].one-cbox").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});

	
	$(".prependedInput").iePlaceholder();
	
	
	$(".pagination li").click(function () {
		$(this).siblings("li").removeClass("active");
		$(this).addClass("active");
	});
    $(".accordion-inner table tr:even td").css("background", "#fafbfc");
    $('.accordion-toggle').on('click', function () {
        if ($(this).attr("class") == "accordion-toggle") {
            $(".accordion-toggle").attr("class", "accordion-toggle collapsed");
        } else {
            $(".accordion-toggle").attr("class", "accordion-toggle collapsed");
            $(this).attr("class", "accordion-toggle");
        }
    });
   
     
   
    

    /*
	 * ============================ @author:1、弹窗内部样式 2、第二层弹框控制 @time:2015-10-15
	 * ============================
	 */
     $(".columninfo-body table tr:even td").css("background", "#fafbfc");
     $('#columnModal').on('show', function () {
        $(".modal-backdrop").css("z-index", "1051");
        $(this).css("z-index", "1052");
    });
     $('#columnModal').on('hidden', function () {
        $(".modal-backdrop").css("z-index", "1040");
        $(this).css("z-index", "1049");
    });

    var scrollTop = 0;
$('#edit').on('hidden.bs.modal', function () {
    $(".page-wrap").removeClass("page-fixed");
    $(document).scrollTop(scrollTop);
});
});




var table_data_channel_columnTab_title = [];// 首页连通性表头
table_data_channel_columnTab_title.push({"mDataProp": "iCheck", "sWidth":"40px","bSortable": false,"sTitle": "<input type='checkbox' id='checkChannalAllId'/>", "sClass": "", "bVisible": true, "tabIndex": -9});
table_data_channel_columnTab_title.push({"mDataProp": "dataNumber","sWidth":"40px", "bSortable": false,"sTitle": "", "sClass": "", "bVisible": true, "tabIndex": -8});
table_data_channel_columnTab_title.push({"mDataProp": "channelName", "bSortable": false,"sTitle": "栏目名称", "sClass": "", "bVisible": true, "tabIndex": -7});
table_data_channel_columnTab_title.push({"mDataProp": "dicChannelName","sWidth":"160px", "bSortable": false,"sTitle": "类别", "sClass": "", "bVisible": true, "tabIndex": -6});
table_data_channel_columnTab_title.push({"mDataProp": "dicChannelSonIdName","sWidth":"160px","bSortable": false, "sTitle": "子类","sClass": "", "bVisible": true, "tabIndex": -5});
table_data_channel_columnTab_title.push({"mDataProp": "channelUrl","sWidth":"100px", "bSortable": false,"sTitle": "栏目URL", "sClass": "", "bVisible": true, "tabIndex": -4});
table_data_channel_columnTab_title.push({"mDataProp": "jumpUrl", "sWidth":"100px","bSortable": false,"sTitle": "	跳转URL", "sClass": "", "bVisible": true, "tabIndex": -3});
table_data_channel_columnTab_title.push({"mDataProp": "operation","sWidth":"55px","bSortable": false, "sTitle": "操作","sClass": "", "bVisible": true, "tabIndex": -1});

function sortColums(){
	table_data_channel_columnTab_title.sort(function (a, b) {
        var ia = parseInt(a["tabIndex"]),
            ib = parseInt(b["tabIndex"]);
        if (isNaN(ia)) {
            ia = 65535;
        }
        if (isNaN(ib)) {
            ib = 65535;
        }
        return ia - ib;
    });
	return table_data_channel_columnTab_title;
}

/**
 * 获取栏目信息
 */
function getChannelInfo(){
	table_data_channel_columnTab_title = sortColums();	
	if(isSearch){
		isSearch = false;
		loadTableChannelData();
	}else{
		//table_data_channel_columnTab.fnClearTable();
		//loadTableChannelData();
		table_data_channel_columnTab.fnClearTable(false);
		table_data_channel_columnTab.fnReloadAjax(webPath+ "/manageInfo_getChannelInfo.action");
		//table_data_channel_columnTab.fnReloadAjax(webPath+ "/connectionHomeTable_getTabels.action");
	}
	
	$('#checkChannalAllId').iCheck('uncheck'); 

	$( "#checkChannalAllId").on('ifChecked' , function (event){
		$('input[name=delId]').iCheck('check'); 
	}); 
	
	$( "#checkChannalAllId").on('ifUnchecked' , function (event){
		$('input[name=delId]').iCheck('uncheck'); 
	}); 
	
	 $( "#addTab").css( "display" ,"block" );
	
	
}

/**
加载栏目数据
 */
function loadTableChannelData(){
	table_data_channel_columnTab = $("#table_data_channel_columnTab").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 50,
        "aaSorting": [],
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     // 可选中行，single单行。multi多行
        },
        "oLanguage": {
            "sSearch": "<span class='add-on'><img src='"+webPath +"/images/common/search_black.png' alt='search'></span>",
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
        "bInfo": false,  
        "bLengthChange": false,
        "bFilter": true, // 过滤功能
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bDestroy": true,
        "sServerMethod": "POST",
        "sAjaxSource": webPath+ "/manageInfo_getChannelInfo.action",
        "sAjaxDataProp": function (data) {
        	maxRecord=data.totalRecords;
        	if(maxRecord>0){	
        		for (var i = 0; i < data.body.length; i++) {
        			var jumpurl=data.body[i]["jumpUrl"];
        			var channelUrl = data.body[i]["channelUrl"];
        			
        			data.body[i]["iCheck"] = "<input type='checkbox'  value="+data.body[i]["id"]+" name='delId'/>";
        			data.body[i]["dataNumber"] = i+1;
        			//data.body[i]["operation"] = "<i class='delete-i' onClick='delChannel("+ data.body[i]["id"] + ");'></i>";
          			data.body[i]["channelName"] ="<div class='ellipsis-box ellipsis-w1' title='"+data.body[i]["channelName"]+"'>"+data.body[i]["channelName"]+"</div>";
        			
          			/*data.body[i]["channelUrl"] ="<div class='ellipsis-box ellipsis-w1' title='"+data.body[i]["channelUrl"]+"'><a href='"+data.body[i]["channelUrl"]+"' target='_blank'>"+data.body[i]["channelUrl"]+"</div>";
        			if(data.body[i]["jumpUrl"]=="无"){
        				data.body[i]["jumpUrl"] = "<div class='ellipsis-box ellipsis-w1' title='"+jumpurl+"'>"+jumpurl+"</div>";
        			}else{
        				data.body[i]["jumpUrl"] = "<div class='ellipsis-box ellipsis-w1' title='"+jumpurl+"'><a href='"+jumpurl+"' target='_blank'>"+jumpurl+"</a></div>";
        			}*/
        			
        			if(jumpurl == "无"){
        				data.body[i]["channelUrl"] ="<div class='ellipsis-box ellipsis-w1' title='"+channelUrl+"'><a href='"+channelUrl+"' target='_blank'>"+channelUrl+"</a></div>";
            			data.body[i]["jumpUrl"] = "<div class='ellipsis-box ellipsis-w1' title='"+jumpurl+"'>"+jumpurl+"</div>";
        			}else{
        				data.body[i]["channelUrl"] ="<div class='ellipsis-box ellipsis-w1' title='"+channelUrl+"'>"+channelUrl+"</div>";
            			data.body[i]["jumpUrl"] = "<div class='ellipsis-box ellipsis-w1' title='"+jumpurl+"'><a href='"+jumpurl+"' target='_blank'>"+jumpurl+"</a></div>";
        			}
        			
        			var statusCode =data.body[i]["status"];
        			if(statusCode==1){
        				data.body[i]["operation"] = "<i class='radio-phone active' onClick='initLi(this);' _vid='"+ data.body[i]["id"] + "'></i>";
        			}else{
        				data.body[i]["operation"] = "<i class='radio-phone' onClick='initLi(this);' _vid='"+ data.body[i]["id"] + "'></i>";
        			}
        			
        		}
        	}
        	
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": table_data_channel_columnTab_title,
        "fnCreatedRow":function (id) {
        	$(id).find("td input[name='delId']").iCheck({
  	 			checkboxClass: 'icheckbox_square-blue',
  	 			radioClass: 'iradio_square-blue'
  	 		});
        },
        "fnInitComplete": function () {
          
			 $("#table_data_channel_columnTab").parent().parent().removeAttr("style");
			 $("#table_data_channel_columnTab_filter").show().css("margin", "-75px 0 0 200px").addClass("input-prepend input-prepend-black");
			 $("#table_data_channel_columnTab_filter").find("input").attr("placeholder", "请输入网站名称");
			 $("#table_data_channel_columnTab_length").hide();
			 $("#table_data_channel_columnTab_info").hide();
			 var table_dataManager_wrapper = $("#table_data_business");
			  table_dataManager_wrapper.find("th").css("cursor", "default");
			  $("#table_data_channel_columnTab_filter input").iePlaceholder();
        },
        "fnPreDrawCallback": function(oSettings) {
        	 
			$("input[type='checkbox']").iCheck({ checkboxClass:
			'icheckbox_square-blue', radioClass: 'iradio_square-blue' });
			
			$( "#checkChannalAllId").on('ifChecked' , function (event){
				$('input[name=delId]').iCheck('check'); 
			}); 
			
			$( "#checkChannalAllId").on('ifUnchecked' , function (event){
				$('input[name=delId]').iCheck('uncheck'); 
			});
            $("#table_data_channel_columnTab_wrapper").siblings(".dropload-load").hide();
        }
        
    });
}


/*
 * ============================ @author:1、弹窗内部tab编辑 @time:2015-10-15
 * ============================
 */
 $("#addTab").click(function () {
	 
	 $("#addTab").css("display","none"); 
	 // 调用ajax请求获取栏目类型loadDicChannelList
	 
	 var dicChannelList;	 
	 var dicChannelSonList;	 
	 $
		.ajax({
			type : "POST",
			url : webPath + "/manageInfo_loadDicChannelList.action",
			dataType : "json",
			data : {
				// 'scanDate' : scanDate
			},
			async : false,
			success : function(data) {
				dicChannelList = data;
				$.each(data.listDicChannel,
						function(index, channelData) {	
					dicChannelList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"	
				});
				
				$.each(data.listDicChannelSon,
						function(index, channelData) {		
					dicChannelSonList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"
					
				});
			}
				
		});
	 
	 
	 	var addIndex = maxRecord +1;
        var trHtml='<td><input name="delId" type="checkbox"></td>'+
        	'<td>'+addIndex+'</td><td>'+
        '<input type="text" class="basic-input channelName" style="width:89px;">'+
        	'</td><td id="channelUrlTdId">'+
        	'<input type="text"  class="basic-input channelUrl" style="width:111px" onfocus="clearChannelUrlCheck(this)">'+
        	'<b class="online-check" onclick="onLineCheckChannelUrl(this);" id="checkChannelUrlId">在线校验</b>'+
        	'<div class="info-tip5">为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，可联系客服。</div>'+
        	'</td><td id="jumUrltdId">'+
        /*
		 * '<input type="text" class="basic-input jumpUrl"
		 * style="width:121px"> <b class="online-check"
		 * onclick="onLineCheckjumpUrl(this);">在线校验</b>'+
		 */
        	'</td><td><div class="tab-selectpicker-box selectBox">'+
        	'<select class="selectpicker dicChannelId">'+dicChannelList+'</select>'+
        	'</div></td><td><div class="tab-selectpicker-box dicChannelListSonDiv">'+
        	'<select class="selectpicker dicChannelSonId" style="width:100px;">'+dicChannelSonList+'</select>'+
        	'</div></td><td class="text-center" style="width:80px"><i class="editor-i selected"></i><i id="newDelele" class="delete-i selected"></i></td>';
	$("#table_data_channel_columnTab > tbody").append('<tr class="editor-tr add-html">'+trHtml+"</tr>");
	$(".add-html input[type='checkbox']").iCheck({
		checkboxClass: 'icheckbox_square-blue',
		radioClass: 'iradio_square-blue'
	});
	
	
	$(".selectBox").on("change", "select", function () {
		
		var parentId = $(this).find("option:selected").val();
		var dicChannelSonList;
		
		var dicChannelSonSelect =$(this).parents("td").next("td").find(".dicChannelListSonDiv");
		 $
			.ajax({
				type : "POST",
				url : webPath + "/manageInfo_loadDicChannelList.action",
				dataType : "json",
				data : {
					'parentId' : parentId
				},
				async : false,
				success : function(data) {
					dicChannelList = data;			
					dicChannelSonSelect.empty();	
					if(data.listDicChannelSon && data.listDicChannelSon.length > 0){
						$.each(data.listDicChannelSon,
								function(index, channelData) {		
							dicChannelSonList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"
							
						});
						
						dicChannelSonSelect.append('<select class="selectpicker dicChannelSonId" >'+dicChannelSonList+'</select>');
					}	
				}
					
			});
		 
		
     });

 });
 
 $("#channelList").on("click", "#newDelele", function () {
     $(this).parents("tr").remove();
     $( "#addTab").css( "display" ,"block" ); 
 });

 
 $("#channelList").on("click", ".editor-i", function () { 
	 var channelName = $(this).parents("tr").find(".channelName").val();
	 var channelUrl = $(this).parents("tr").find(".channelUrl").val();
	 var jumpUrl = $(this).parents("tr").find(".jumpUrl").val();
	 var dicChannelId = $(this).parents("tr").find(".dicChannelId").find("option:selected").val();
	 var dicChannelSonId = $(this).parents("tr").find(".dicChannelSonId").find("option:selected").val();
	 var dicChannelText = $(this).parents("tr").find(".dicChannelId").find("option:selected").text();
	 var dicChannelSonText = $(this).parents("tr").find(".dicChannelSonId").find("option:selected").text();
	 if(channelName==''){
		 alert("栏目名称为空，请输入栏目名称");
		 return;
	 }
	 
	 if(channelUrl==''){
		 alert("栏目Url为空，请输入栏目url");
		 return;
	 }
	 
	 if(!isChannelUrlLegal){
		 alert("url或跳转url验证失败，请输入正确的url或跳转url");
		 return;
	 }
	 
	 var channelId;
	 var tbody= $(this).parents("tbody");
	 var tr= $(this).parents("tr");
	 var jsonObj = {};
		jsonObj["channelName"] = channelName;
		jsonObj["channelUrl"] = channelUrl;
		jsonObj["jumpUrl"] = jumpUrl;
		jsonObj["modifySiteCode"] = modifySiteCode;
		jsonObj["modifyWebsiteInfoId"] = modifyWebsiteInfoId;
		jsonObj["dicChannelId"] = dicChannelId;
		jsonObj["dicChannelSonId"] = dicChannelSonId;	
		$.ajax({
	        async: false,
	        type: "POST",
	        url: webPath+"/manageInfo_addChannel.action",
	        dataType: "json",
	        data: JSON.stringify(jsonObj),
	        contentType: "application/json"
	    }).done(function (response) {
	    	if(response.succeed){
	    		channelId = response.id;
	    		alert(response.succeed);
	    		table_data_channel_columnTab.fnClearTable();
	    		 $( "#addTab").css( "display" ,"block" );
	    		
	    		/* if(jumpUrl==undefined||jumpUrl==''){
	        		 jumpUrl="无";
	        	 }
	        	 
	        	 maxRecord+=1;
	        	 var trHtml = "<tr role='row' class='odd'>" +
	        	 		"<td><div class='icheckbox_square-blue' style='position: relative;'>" +
	        	 		"<input name='delId' type='checkbox' style='position: absolute; opacity: 0;'>" +
	        	 		"<ins class='iCheck-helper' style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;'></ins>" +
	        	 		"</div></td>" +
	        	 		"<td>"+maxRecord+"</td>" +
	        	 		"<td><div class='ellipsis-box ellipsis-w2'>"+channelName +"</div></td>" +
	        	 		"<td><div class='ellipsis-box ellipsis-w1'>"+ channelUrl + "</div></td>" +
	        	 		"<td><div class='ellipsis-box ellipsis-w1'>"+ jumpUrl + "</div></td>" +
	        	 		"<td>"+dicChannelText+"</td><td>"+dicChannelSonText+"</td>" +
	        	 		"<td><i onclick='delChannel("+ channelId+ ");' class='delete-i'></i></td>" +
	        	 		"</tr>";
	        	 tbody.append(trHtml);
	        	 tr.remove();
	    		 $("#addTab").css("display","block"); */
	    		 
	    	}else if(response.error){
	    		alert(response.error);
	    		return;
	    	}
	    }).fail(function (data) {
	    	alert("错误！");
	    	return;
	    }).always(function () {

	    });
	 
 });
 
 /**
  * 滑动条操作栏目状态
 * @param li
 */
function initLi(li){
		var vid = $(li).attr("_vid");
		var isActive = false;
		if($(li).hasClass("active")){
			$(li).removeClass("active");
		}else{
			$(li).addClass("active");
			isActive = true;
		}
		$.ajax({
			type : "POST",
			url : webPath + "/manageInfo_operateChannelInfo.action?id="+vid+"&isActive="+isActive,
			dataType : "json",
			async : false,
			success : function(data) {
				
			},
			errot:function(){
				alert("操作失败");
				return;
			}
		});
		table_data_channel_columnTab.fnClearTable();
		table_data_channel_columnTab.fnClearTable(false);
		table_data_channel_columnTab.fnDraw();
	}


/**
 * 删除栏目
 */
function delChannel(id){
	var ids = [];
	if(!id){
		return;
	}
	
	if(!confirm("确定要删除记录吗?")){
       return false
 	}
	ids.push(id);
	
	batchDeleteChannel(ids);
}

/**
 * 批量删除栏目信息
 */
function batchDeleteChannel(ids){
	if (_.isEmpty(ids)) {
        return;
    }
	var jsonObj = {};
	jsonObj["ids"] = ids;
	$.ajax({
        async: false,
        type: "POST",
        url: webPath+"/manageInfo_delChannel.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json"
    }).done(function (response) {
    	if(response.succeed){
    		alert(response.succeed);
    		table_data_channel_columnTab.fnClearTable();
    	}else if(response.error){
    		alert(response.error);
    	}
    }).fail(function (data) {
    	alert("错误！");
    }).always(function () {

    });
	
}

/**
 * 批量删除栏目
 * @returns {Boolean}
 */
function delChannelList(){
    var channelPointIds=new Array();	
    $("input[name=delId]:checked").each(function(){
		channelPointIds.push($(this).attr('value'));
	});

    if(channelPointIds.length==0){
        alert("请选择栏目！");
        return
    }

    if(!confirm("确定要删除记录吗?")){
        return false
    }
 
    batchDeleteChannel(channelPointIds);
}


/**
 * 取消监测栏目
 * @returns {Boolean}
 */
function cancelCheck(){
    var channelPointIds=new Array();	
    $("input[name=delId]:checked").each(function(){
		channelPointIds.push($(this).attr('value'));
	});

    if(channelPointIds.length==0){
        alert("请选择要取消监测的栏目！");
        return
    }

    if(!confirm("确定要取消监测栏目吗?")){
        return false
    }
 
    batchCancleChannel(channelPointIds);
}

/**
 * 批量取消监测栏目信息
 */
function batchCancleChannel(ids){
	if (_.isEmpty(ids)) {
        return;
    }
	var jsonObj = {};
	jsonObj["ids"] = ids;
	$.ajax({
        async: false,
        type: "POST",
        url: webPath+"/manageInfo_cancelChannel.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json"
    }).done(function (response) {
    	if(response.succeed){
    		alert(response.succeed);
    		table_data_channel_columnTab.fnClearTable();
    	}else if(response.error){
    		alert(response.error);
    	}
    }).fail(function (data) {
    	alert("错误！");
    }).always(function () {
    	$("#checkChannalAllId").iCheck('uncheck');
    });
	
}

/**
 * 恢复监测（重点监测）
 * @returns {Boolean}
 */
function reCheckChannel(){
    var channelPointIds=new Array();	
    $("input[name=delId]:checked").each(function(){
		channelPointIds.push($(this).attr('value'));
	});

    if(channelPointIds.length==0){
        alert("请选择要重点监测的栏目！");
        return
    }

    if(!confirm("确定要重点监测选择的栏目吗?")){
        return false
    }
 
    batchReCheckChannel(channelPointIds);
}

/**
 * 批量恢复监测（重点监测）
 */
function batchReCheckChannel(ids){
	if (_.isEmpty(ids)) {
        return;
    }
	var jsonObj = {};
	jsonObj["ids"] = ids;
	$.ajax({
        async: false,
        type: "POST",
        url: webPath+"/manageInfo_reCheckChannel.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json"
    }).done(function (response) {
    	if(response.succeed){
    		alert(response.succeed);
    		table_data_channel_columnTab.fnClearTable();
    	}else if(response.error){
    		alert(response.error);
    	}
    }).fail(function (data) {
    	alert("错误！");
    }).always(function () {
    	$("#checkChannalAllId").iCheck('uncheck');
    });
	
}

//栏目导出Excel
function channelExportExcel(){

	var channelPointIds=new Array();	
	$("input[name=delId]:checked").each(function(){
		channelPointIds.push($(this).attr('value'));
	});
   window.location.href=webPath+"/manageInfo_channelExportExcel.action?websiteInfoId="+modifyWebsiteInfoId+"&channelPointIds="+channelPointIds;
}





/**
* 在线校验栏目URL
*/
function onLineCheckChannelUrl(ob){
	var channelUrl = $(ob).siblings(".channelUrl").val();
	$('#checkChannelUrlId').text('');
	$('#checkChannelUrlId').append("<img src='"+webPath+"/images/common/jy-loading.gif' alt='loading' >");
	var isLe = onLineCheckUrl(channelUrl);
	// var isLe =false;
	//$('#checkChannelUrlId').append("<img src='<%=path%>/images/common/jy-loading.gif' alt='loading' >");
	if(isLe){
		isChannelUrlLegal=true;
      $('#checkChannelUrlId').after('<i class="succeed"></i>');
  	   $(ob).remove();
	}else{
		$('#checkChannelUrlId').after('<i class="failure"></i><div class="failure-error">您的校验失败，请输入正确URL!</div>');	
		if($('#jumUrltdId').children("#jumpUrlInputId").length==0){	
			 $('#jumUrltdId').append('<input id="jumpUrlInputId" type="text" class="basic-input jumpUrl" style="width:111px" onfocus="clearChannelJumpUrlCheck(this)"> '
					+' <b class="online-check" onclick="onLineCheckjumpUrl(this);" id="checkJumpUrlId">在线校验</b>');
		}
		$(ob).remove();
		
	}
	
}
/**
* 在线校验跳转URL
*/
function onLineCheckjumpUrl(ob){
	var jumpUrl = $(ob).siblings(".jumpUrl").val();
	$('#checkJumpUrlId').text('');
	$('#checkJumpUrlId').append("<img src='"+webPath+"/images/common/jy-loading.gif' alt='loading' >");
	var isLe = onLineCheckUrl(jumpUrl);	
	// var isLe =false;
	if(isLe){
		isChannelUrlLegal=true;
      $('#checkJumpUrlId').after('<i class="succeed"></i>');
      $(ob).remove();
	}else{
		 $('#checkJumpUrlId').after('<i class="failure"></i><div class="failure-error">您的校验失败，请输入正确URL!</div>');
		 $(ob).remove();
	}
	
}


function clearChannelUrlCheck(ob){
	
	 if($(ob).siblings("#checkChannelUrlId").length==0){
		 $(ob).siblings("i").remove();
		 $(ob).siblings("div").remove();
		 $(ob).after('<b id="checkChannelUrlId" onclick="onLineCheckChannelUrl(this);" class="online-check">在线校验</b>');
	 }
}

function clearChannelJumpUrlCheck(ob){
	
	 if($(ob).siblings("#checkJumpUrlId").length==0){
		 $(ob).siblings("i").remove();
		 $(ob).siblings("div").remove();
		 $(ob).after('<b id="checkJumpUrlId" onclick="onLineCheckjumpUrl(this);" class="online-check">在线校验</b>');
		
	 }
}

/**
 * 获取服务信息
 */
function getServiceInfo(){
	$.ajax({
	     async: true,
	     type: "GET",
	     url: webPath+ "/manageInfo_getServiceInfo.action?modifyWebsiteInfoId="+ modifyWebsiteInfoId,
	     dataType: "json",
	     contentType: "application/text"
	 }).done(function (response) {
	  if(response){
	     $("#remark").html(response.remark || "");
	     $("#beginDate").html(response.beginDate || "");
	     $("#endDate").html(response.endDate || "");
	 }
	}).fail(function (data) {
	  alert("错误！");
	}).always(function () {

	});
}


 
 
 
 