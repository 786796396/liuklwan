//例外省份（广东），针对抽查更改为考评
var site_code_session = $("#site_code_session").val();
var table_title = [];
table_title.push({"mDataProp": "siteCode", "bSortable": false,"sTitle": "网站标识码", "sClass": "", "bVisible": true, "tabIndex": -10});
table_title.push({"mDataProp": "siteName","bSortable": false, "sTitle": "网站名称", "sClass": "tleft", "bVisible": true, "tabIndex": -9});
table_title.push({"mDataProp": "not_connection_num", "sTitle": "	不连通", "sClass": "", "bVisible": true, "tabIndex": -8});
table_title.push({"mDataProp": "not_link_num", "sTitle": "不可用链接", "sClass": "", "bVisible": true, "tabIndex": -7});
table_title.push({"mDataProp": "security_num", "sTitle": "内容保障问题","sClass": "", "bVisible": true, "tabIndex": -6});
table_title.push({"mDataProp": "correct_num","sTitle": "严重错别字","sClass": "", "bVisible": true, "tabIndex": -5});
//table_title.push({"mDataProp": "safety_num","sTitle": "安全问题","sClass": "", "bVisible": true, "tabIndex": -4});
table_title.push({"mDataProp": "update_num","sTitle": "新稿件","sClass": "", "bVisible": true, "tabIndex": -3});
table_title.push({"mDataProp": "health_index","sTitle": "健康指数","sClass": "", "bVisible": true, "tabIndex": -2});
table_title.push({"mDataProp": "state","bSortable": false, "sTitle": "状态","sClass": "", "bVisible": true, "tabIndex": -1});
table_title.push({"mDataProp": "preview","bSortable": false, "sTitle": "报告管理","sClass": "", "bVisible": true, "tabIndex": 0});
var is_firest = true;
/**
 * 初始化套餐
 */
function initComboInfos() {
    $.ajax({
        async: false,
        cache: false,
        type: 'GET',
        dataType: "json",
        url: webPath + "/spotCheckResult_getComboInfos.action",// 请求的action路径
        error: function () {// 请求失败处理函数
            alert('获取套餐类型出错');
        },
        success: function (data) { // 请求成功后处理函数。
            if (data) {
            	var options = "<option value='4'>高级版</option>";
                $("#comboInfos").html(options);
            } else {
                $("#comboInfos").html("");
            }
        }
    });
}

function addSpotWebsite(scheduleId){
	loadingTree(scheduleId);
}
function getBatchSite(scheduleId){
	return false;
}
function getState(state) {
	switch (state) {
	case -1:
		return "取消";
		break;
	case 0:
		return "未启动";
		break;
	case 1:
		if(site_code_session == "440000"){
			return "考评中";
		}else{
			return "抽查中";
		}
		break;
	case 2:
		if(site_code_session == "440000"){
			return "考评完成 ";
		}else{
			return "抽查完成 ";
		}
		break;
	default:
		return false;
	}
}
function startSpotTask(scheduleId) {
    // 加载新建任务的地方或者部委

    $.ajax({
        type: "POST",
        url: webPath + "/spotCheckResult_startSpotTask.action?scheduleId="+scheduleId,
        dataType: "json",
        async: false,
        error: function () {// 请求失败处理函数
            alert('请求失败');
        },
        success: function (data) {
            if(data.body=='success'){
            	alert("一键启动成功");
            	window.location.reload();
                getTables();
            }else{
            	alert("一键启动失败");
            }
        	//alert(data.body);
            
        }
    });
   
}

var spotState = {
    0: "<tr class='qx-chouc'>",
    1: "<tr>",
    2: "<tr>"
};
var spotResultState = {
    0: "<td>已取消抽查</td>",
    1: "<td><a href='#preview' class='view-modal' data-toggle='modal' onclick=\"getPdfUrl('/cloud_web/upload/2015/12/1101080016/1_1101080016_20151215.pdf','测试数据-暂定')\"><img alt='预览' src='" + webPath + "/images/common/preview_active.png'/></a></td>",
    2: "<tr>"
};
/**
 * 获取抽查结果
 */
function getResults(scheduleId) {
    var tempLate = "";
    if (hasInitDatas[scheduleId]) {

    } else {
    	tempLate +=
        	"<table id='spot_check_table" + scheduleId + "' cellpadding='0' cellspacing='0' class='table'><tbody></tbody></table>"
           
    }
    return tempLate;
}
function sortColums(){
	table_title.sort(function (a, b) {
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
	return table_title;
}
function loadTableData(obj,id){
	if(is_firest){
		is_firest = false;
	}else{
		table_title = _.without(table_title,_.first(table_title));
	}
	table_title.push({"mDataProp": ""+id+"", "bSortable": false,"sTitle": "<input type='checkbox' id='checkAll_"+id+"'/>", "sClass": "", "bVisible": true, "tabIndex": -11});
	sortColums();
	obj.dataTable({
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
            "sRowSelect": "multi"     // 可选中行，single单行。multi多行
        },
        "oLanguage": {
            "sSearch": "",
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
        "bFilter": false, // 过滤功能
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bDestroy": true,
        "sServerMethod": "POST",
        "sAjaxSource": webPath + "/spotCheckResult_getCheckResult.action?scheduleId=" + id,
        "sAjaxDataProp": function (data) {
        	maxRecord=data.totalRecords;
        	if(data && data.body){	
        		for (var i = 0; i < data.body.length; i++) {
        			if(data.body[i]["state"]!=-1 && data.body[i]["state"]!="-1"){
        				data.body[i][id] = "<input type='checkbox' _vid='"+id+"' onClick='changeSelectAll(this);' value="+data.body[i]["siteCode"]+" name='checkAll_"+id+
        				 "'  _vstate='"+data.body[i]["state"]+"'/>";
            			data.body[i]["siteName"] = "<td class='tleft'><div class='wz-name wz-name-w3' title='" + data.body[i]["siteName"] + "'>" + data.body[i]["siteName"] + "</div></td>";
            			data.body[i]["preview"] = "<a href='javascript:void(0);' onClick='expReport("+data.body[i]["siteCode"]+","+id+");'>导出报告</a>";
        			}else{
        				data.body[i][id] = "";
            			data.body[i]["siteName"] = "<div class='tleft qx-chouc' ><div class='wz-name wz-name-w3' title='" + data.body[i]["siteName"] + "'>" + data.body[i]["siteName"] + "</div></div>";
            			data.body[i]["siteCode"] = "<div class='qx-chouc'>"+data.body[i]["siteCode"]+"</div>";
            			data.body[i]["not_connection_num"] = "<div class='qx-chouc'>"+data.body[i]["not_connection_num"]+"</div>";
            			data.body[i]["not_link_num"] = "<div class='qx-chouc'>"+data.body[i]["not_link_num"]+"</div>";
            			data.body[i]["security_num"] = "<div class='qx-chouc'>"+data.body[i]["security_num"]+"</div>";
            			data.body[i]["correct_num"] = "<div class='qx-chouc'>"+data.body[i]["correct_num"]+"</div>";
//            			data.body[i]["safety_num"] = "<div class='qx-chouc'>"+data.body[i]["safety_num"]+"</div>";
            			data.body[i]["update_num"] = "<div class='qx-chouc'>"+data.body[i]["update_num"]+"</div>";
            			data.body[i]["health_index"] = "<div class='qx-chouc'>"+data.body[i]["health_index"]+"</div>";
            			data.body[i]["preview"] = "<div class='qx-chouc'>导出报告</div>";
        			}
        			
        			if(data.body[i]["state"]==-1){
        				data.body[i]["state"] = "<div class='qx-chouc'>已取消</div>";
        			}else if(data.body[i]["state"]==0){
        				data.body[i]["state"] = "未启动";
        			}else if(data.body[i]["state"]==1){
        				if(site_code_session == "440000"){
        					data.body[i]["state"] = "考评中";
        				}else{
        					data.body[i]["state"] = "抽查中";
        				}
        			}else if(data.body[i]["state"]==2){
        				if(site_code_session == "440000"){
        					data.body[i]["state"] = "考评完成";
        				}else{
        					data.body[i]["state"] = "抽查完成";
        				}
        			}
        			
        		}
        	}
        	
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": table_title,
        "fnCreatedRow":function (id) {
        	
        },
        "fnInitComplete": function () {
        	
        },
        "fnPreDrawCallback": function(oSettings) {
        	
        	selectAllOrNot(id);
        }
    });
}


/**
 * 获取分页导航数据
 */
function getNavigationData(scheduleId, ths) {
	$("input[id^='checkAll_']").attr("checked", false);
	$(":checkbox[name^='checkAll_']").attr("checked", false);
    var template = "";
    template = getResults(scheduleId);
    if (template) {
        $(ths).parent("div").siblings(".accordion-body").find(".tab_box1").html(template);
    }
    loadTableData($("#spot_check_table"+scheduleId),scheduleId);
}
//绑定checkbox的关联事件
function selectAllOrNot(id){
 	$("#checkAll_"+id).change(function(e){
         if($(this).prop("checked")==true){
         	$("input[name=checkAll_"+id+"]").prop('checked', 'checked');
         }else{
         	$("input[name=checkAll_"+id+"]").removeProp('checked');
         }
     });

     $(":checkbox[name^='checkAll_']").on('click', function(event){
    	 	var cid = $(this).attr("name");
    	 	if( $(":checkbox[name='"+cid+"']").filter(':checked').length ==  $(":checkbox[name='"+cid+"']").length) {
    	 		$("#"+cid).prop('checked', 'checked');
    	 	} else {
    	 		$("#"+cid).removeProp('checked');
    	 	}
    	 	$("#"+cid).iCheck('update');
     });
}

/**
 * 单项选择的时候判断是否要取消全选
 * @param ths
 */
function changeSelectAll(ths){
	var cid = $(ths).attr("name");
 	if( $(":checkbox[name='"+cid+"']").filter(':checked').length ==  $(":checkbox[name='"+cid+"']").length) {
 		$("#"+cid).prop('checked', 'checked');
 	} else {
 		$("#"+cid).removeProp('checked');
 	}
 	$("#"+cid).iCheck('update');
}

/**
 * 导出单条的报告
 */
function expReport(siteCode,scheduleId){
	if(siteCode){
		window.location.href = webPath
        + "/spotCheckResult_expReport.action?siteCode=" + siteCode+"&scheduleId="+scheduleId;
	}else{
		alert("数据错误，网站标识码不存在");
	}
}


/**
 * 批量导出Excel成绩单
 */
function checkExportExcel(checkNumber) {
    var aId = "checkAll_" + checkNumber;
    var spotsiteCodes = new Array();
    $("input[name=" + aId + "]:checked").each(function () {
        spotsiteCodes.push($(this).attr('value'));
    });
    if (spotsiteCodes.length > 0) {
        window.location.href = webPath
            + "/spotCheckResult_checkExportExcel.action?checkNumber=" + checkNumber
            + "&spotsiteCodes=" + spotsiteCodes;
    } else {
        alert("请选择要导出成绩单的条目！");
    }
}
/**
 * 批量导出报告
 */
function checkExportReport(checkNumber) {

    var aId = "checkAll_" + checkNumber;
    var spotsiteCodes = "";
    $("input[name=" + aId + "]:checked").each(function () {
        spotsiteCodes = spotsiteCodes + $(this).attr('value') + ",";
    });
    $("#spotsiteCodes").val(spotsiteCodes);
    $("#checkNumber").val(checkNumber);

    var postData = {"spotsiteCodes": spotsiteCodes, "checkNumber": checkNumber};
    var t = hasFiles(postData, webPath + "/spotCheckResult_hasReport.action?spotsiteCodes=" + spotsiteCodes + "&checkNumber=" + checkNumber);
    if (!t) {
        alert("没有要下载的文件");
        return;
    }
    $("#reportDown").submit();
}
// 预先执行一下判断是否有需要下载的文件
function hasFiles(postData, url) {
    var isHasFiles = false;
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: postData,
        async: false,
        success: function (data) {
            isHasFiles = data;
        },
        error: function () {
        }
    });
    return isHasFiles;
}


/**
 * 取消监测
 */
function cancelCheck() {
    var checkNumber = "";
    if ($("input[type='checkbox']:checked").length > 0) {
        checkNumber = $("input[type='checkbox']:checked:first").attr("name").split("_")[1];
    }
    var spotsiteCodes = new Array();
    var _vstate="";
    $("input[type='checkbox']:checked").each(function () {
        spotsiteCodes.push($(this).attr('value'));
        
        _vstate = _vstate+$(this).attr("_vstate");
    });
  
    if(_vstate.contains("2")){
    	if(site_code_session == "440000"){
    		alert("选中任务中存在已完成的考评任务，不能取消");
		}else{
			alert("选中任务中存在已完成的抽查任务，不能取消");
		}
   		return;
    }
   
    if (checkNumber != "") {
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            dataType: "json",
            url: webPath
                + "/spotCheckResult_cancelCheck.action?checkNumber="
                + checkNumber + "&spotsiteCodes="
                + spotsiteCodes,// 请求的action路径
            error: function () {// 请求失败处理函数
                alert('请求失败');
            },
            success: function (data) { // 请求成功后处理函数。
                $("input[id^='checkAll_']").attr("checked", false);
                window.location.reload();
            }

        });

    } else {
        alert("请选择要取消监测的条目！");
    }
}
// 全选和排序
function sortAndCheckAll(checkNumber) {
    // 全选复选框
    $("#checkAll_" + checkNumber).click(function () {
        $(":checkbox[name='checkAll_" + checkNumber + "']").prop("checked", this.checked);
    });
    $(":checkbox[name='checkAll_" + checkNumber + "']").click(function () {
        var $checkeds = $(":checkbox[name='checkAll_" + checkNumber + "']");
        $("#checkAll_" + checkNumber).prop("checked", $checkeds.length == $checkeds.filter(":checked").length);
    });
    /*// 列表排序
     new TableSorter("spot_check_table" + checkNumber, 3, 4, 5, 6, 7, 8,9);*/
}

/**展示pdf*/
function getPdfUrl(path, name) {
    $("#myModalLabel_show").html('<i class="view"></i>' + name + '报告预览');
    webViewerInitialized(path);
    document.addEventListener('DOMContentLoaded', webViewerLoad, true);
}

function loadingTree() {
    // 加载新建任务的地方或者部委
    $.ajax({
        type: "POST",
        url: webPath + "/spotCheckResult_getLocalAndMinistries.action",
        dataType: "json",
        async: false,
        success: function (data) {
        	$("#ministries_radio_id").attr("disabled", false);
            var list = data.list;
            var ministriesList = "";
            var localList = "";
            if (data.CurrentSiteCode.match("bm0100")) {//部委
            	localList += "<option value=''>——请选择——</option>";
                //将地方置   请选择且不可用
                $("#local_select_id").attr("disabled", false);
                //初始化部委树
                initLocalTree();
            }else if (data.CurrentSiteCode.match("bm") && data.CurrentSiteCode!="bm0100") {//部委
                //将地方置   请选择且不可用
            	/*localList += "<option value=''>——请选择——</option>";*/
            	$("#local_select_id").attr("disabled", true);
                ministriesList += "<option value=''>——请选择——</option>";
                $("#ministries_select_id").attr("disabled", true);
                //初始化部委树
                initLocalTree();
            } else {//地方
            	ministriesList += "<option value=''>——请选择——</option>";
                //部委置为  请选择  且不可用
                $("#ministries_select_id").attr("disabled", true);
                initLocalTree();
            }
            
            //遍历省份集合将省份或者部委添加到下拉框里面
            for (var i = 0; i < list.length; i++) {
                if (list[i].local != null) {
                    localList += "<option value='" + list[i].siteCode + "'>" + list[i].local + "</option>";
                }
                if (list[i].ministries != null) {
                    ministriesList += "<option value='" + list[i].siteCode + "'>" + list[i].ministries + "</option>";
                }
            }
            
            //$("#ministries_radio_id").attr("disabled", true);
            if (data.CurrentSiteCode.match("bm") && data.CurrentSiteCode!="bm0100") {//部委
            	$("#local_select_id").append(ministriesList);
                $("#ministries_select_id").append(localList);
            }else{
            	$("#local_select_id").append(localList);
                $("#ministries_select_id").append(ministriesList);
            }
        }
    });
}
function loadingBatchTask() {
	// 加载任务批次
	$.ajax({
		type : "POST",
		url : webPath + "/spotCheckResult_getBatchTask.action",
		dataType : "json",
		async : false,
		success : function(data) {
			if(data.errorMsg){
				alert(data.errorMsg);
			}else{
				var list = data.list;
				$('#groupNum').html(list[0].groupNum);
				var tem;
				_.each(list, function(item) {
					tem +="<option value="+item.groupNum+">"+item.batchNum+"</option>" ;
				});
				$("#batchNum").html(tem);
			}

		},error:function(data){
			alert(data.errorMsg);
		}
	});
}
//获取历史抽查完成的任务
function getCloseSchedule() {
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/spotCheckResult_getCloseSchedule.action",
        dataType:"json",
        data: null,
        contentType: "application/json",
        success : function(data){
        	//页面返回错误
        	if(data.errormsg){
        		var errormsg=data.errormsg;
        		//隐藏表格内容，显示暂无数据
        		$("#fzhchcrw").html("<tr><td colspan='8'>任务状态暂无检查完成的数据</td></tr>");
        	}else{
        		//所有数据
        		var returnList=data.returnList;
        		//批次数据
        		var batchNumList=data.batchNumList;
 
        		//表数据重置
        		$("#fzhchcrw").html("<tr><td colspan='8'>任务状态暂无检查完成的数据</td></tr>");
        		
        		/**
        		 * 页面处理逻辑
        		 * 		1 首先遍历批次集合表，获取每一个批次的所有组数据，拼装该批次数据，将改组数据封添加到表中
        		 */
        		if(batchNumList){
        			if(batchNumList.length>0){
        				$("#fzhchcrw").html("");
        			}else{
        				$("#spot_choucModalCopy_button").removeClass("btn btn-success green-btn");
        				$("#spot_choucModalCopy_button").addClass("white-btn bg_b1b3b3_sure");
        			}
        			//遍历批次数组
            		for(var i=0;i<batchNumList.length;i++){
            			var listStr="";
            			var listNum=0;
            			for(var x=0;x<returnList.length;x++){
            				if(batchNumList[i]==returnList[x]["batchNum"]){
            					listNum+=1;
            				}
            			}
            			//遍历所有数据的数据
            			for(var j=0;j<returnList.length;j++){
            				if(batchNumList[i]==returnList[j]["batchNum"]){
                				//判断是否为该批次的第一条记录，如果是第一条记录的话，该tr中会有第几批次的td,其他行没有
                				if(listStr==""){
                					listStr+="<tr><td rowspan="+listNum+"><div class='fuzhi-font1'>第"+returnList[j]["batchNum"]+"批</div></td>"+
                					"<td class='f_num'>"+returnList[j]["groupNum"]+"</td>"+
                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]+"</div></td>"+
                					"<td><a href='"+webPath+"/spotCheckResult_websiteList.action?flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>"+
                					"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
                					//增加复制操作
                					listStr+="<td class=\"t-left\"><a class=\"copy\" href=\"javascript:void(0)\" onclick=\"fzhzhd('no"+returnList[j]["scheduleId"]+"','"+returnList[j]["allSiteCode"]+"','"+returnList[j]["notAllSiteCode"]+"')\"><i></i>&nbsp;复制本组任务</a><label class=\"cbox-label\"><input id=\"no"+returnList[j]["scheduleId"]+"\" type=\"checkbox\" />&nbsp;只选择【不合格】站点</label></td>";
                				}else{
                					listStr+="<tr><td class='f_num'>"+returnList[j]["groupNum"]+"</td>"+
                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]+"</div></td>"+
                					"<td><a href='"+webPath+"/spotCheckResult_websiteList.action?flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>"+
                					"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
                					//增加复制操作
                					listStr+="<td class=\"t-left\"><a class=\"copy\" href=\"javascript:void(0)\" onclick=\"fzhzhd('no"+returnList[j]["scheduleId"]+"','"+returnList[j]["allSiteCode"]+"','"+returnList[j]["notAllSiteCode"]+"')\"><i></i>&nbsp;复制本组任务</a><label class=\"cbox-label\"><input id=\"no"+returnList[j]["scheduleId"]+"\" type=\"checkbox\" />&nbsp;只选择【不合格】站点</label></td>";
                				}
            				}
            			}
            			$("#fzhchcrw").append(listStr);
            		}
        		}
        	}
        },error:function(data){
	   }
     });
}
/**
 * 注意：
 * 		只有新建一个批次分组抽查任务时候，该下拉框是多选的，会触发该事件
 * 		如果是点击新增站点进入该页面时，不会触发该事件
 */
$('#batchNum').change(function() {
	var value = $(this).children('option:selected').val();//获取分组值
	
	//将页面隐藏的抽查进度表id清空
	$("#scheduleId").val("");
	
	$('#groupNum').html(value);
	
	
});

function deleteSpotResultBatch(){
	
	var scheduleId=$("#scheduleId").val();
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/spotCheckResult_deleteSpotResultBatch.action?scheduleId="+scheduleId,
        dataType:"json",
        data: null,
        contentType: "application/json",
        success : function(data){
        		console.log("已删除未提交的抽查站点");
        	}
        });
}

function closeTaskDiv(){
	deleteSpotResultBatch();
/*	  $("#main-center").show();
      $("#new_task_div").hide();
      $("#spot_submit_div_id").hide();
      $("#suij").hide();*/
	  //window.location.reload();
	//删除
	var flagId = $("#flag_id").val();
	if(flagId != null && "" != flagId){
		window.history.go(-1);
	}else{
		window.location.reload();//避免选择随机直接关闭，点击添加站点，显示随机选择页
		$("#main-center").show();
	    $("#new_task_div").hide();
	}
}