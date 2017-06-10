var modifyWebsiteInfoId;// 定义一个双击时保存打开表的ID，主键，用于获取website信息
// 服务开通状态
var serviceState = {
    '0': "<div class='status-box bg-gray'>未启动</div>",
    '1': '<div class="status-box bg-green">服务中</div>',
    '2': '<div class="status-box bg-red">服务报告已完成</div>'
};

var table_data_webInfos_title = [];// 部门信息列表表头
table_data_webInfos_title.push({"mDataProp": "dataNumber", "bSortable": false,"sTitle": "序号", "sClass": "center", "bVisible": true, "tabIndex": -9});
table_data_webInfos_title.push({"mDataProp": "siteCode", "bSortable": false,"sTitle": "网站标识码", "sClass": "", "bVisible": true, "tabIndex": -8});
table_data_webInfos_title.push({"mDataProp": "siteName","sWidth":"300px", "bSortable": false,"sTitle": "网站名称", "sClass": "", "bVisible": true, "tabIndex": -7});
table_data_webInfos_title.push({"mDataProp": "zbdw", "bSortable": false,"sTitle": "主办单位", "sClass": "", "bVisible": true, "tabIndex": -6});
table_data_webInfos_title.push({"mDataProp": "channelNum", "bSortable": false,"sTitle": "栏目数量", "sClass": "center", "bVisible": true, "tabIndex": -5});
table_data_webInfos_title.push({"mDataProp": "channelInfo", "bSortable": false,"sTitle": "栏目信息", "sClass": "center", "bVisible": true, "tabIndex": -4});

function sortWebColums(){
	table_data_webInfos_title.sort(function (a, b) {
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
	return table_data_webInfos_title;
}

var table_data_webInfos;
var isComplate = false;
var departmentLevel;
/**
 * 获取本级部门信息列表
 */
function getLevelMesg(obj,level) {
	leftActive(obj);
	
    $(".tab-content > .guanl-con").removeClass("active");
    $("#ddxx").addClass("active");
	departmentLevel = level;
	table_data_webInfos_title = sortWebColums();
	if(isComplate){
		table_data_webInfos.fnClearTable(false);
		table_data_webInfos.fnDraw();
	}else{
		table_data_webInfos = $("#table_data_webInfos").dataTable({
	        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
	        "bDestroy": true,
	        "bAutoWidth": true,
	        "bDeferRender": true,
	        "bJQueryUI": true,
	        "sPaginationType": "full_numbers",
	        "iDisplayLength": 100000,
	        "aaSorting": [],
	        "oTableTools": {
	            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
	            "sRowSelect": "multi"     // 可选中行，single单行。multi多行
	        },
	        "oLanguage": {
	            "sSearch": "<span class='add-on'><img src='"+webPath +"/images/common/search_black.png' alt='search'></span>",
	            "sLengthMenu": '本页显示 <select>' +
	                '<option value="100">100</option>'+
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
	        "sAjaxSource": webPath + "/manageInfo_getLevelMesg.action",
	        "sAjaxDataProp": function (data) {
	        	if(data && data.body){	
	        		for (var i = 0; i < data.body.length; i++) {
	        			data.body[i]["dataNumber"] = i+1;
                        data.body[i]["zbdw"] ="<div class='ellipsis-box ellipsis-w1'>"+data.body[i]["zbdw"]+"</div>";
	        			data.body[i]["siteCode"] = "<a href='"+webPath+"/fillUnit_gailan.action?siteCode="+data.body[i]["siteCode"]+"' target='_blank'>"+data.items[i]["siteCode"]+"</a>"
	        			data.body[i]["channelInfo"] = "<a onclick='showChannelInfo(\""+data.items[i]["siteCode"]+"\",\""+data.items[i]["siteName"]+"\");\'><i class='preview-icon'></i></a>";
                        if(data.items[i]["isOrg"]){
	        				data.body[i]["siteName"] = "<div class='info-box'><i class='star-icon'></i>";
	        			}else{
	        				data.body[i]["siteName"] = "<div class='info-box'>";
	        			}
                        var siteUrl = checkUrlHTTTP(data.items[i]["url"]);
	        			data.body[i]["siteName"] = data.body[i]["siteName"]
						+"<div class='ellipsis-box ellipsis-w1' >"
	                    + "<a href='"+siteUrl+"' target='_blank'>"+data.items[i]["siteName"]+"</a>"
	                    + "</div>"
						+"<div class='chouc-hover-div'></div><div class='info-con'>"
						+"<div><label>单位名称：</label>"+data.items[i]["siteManageUnit"]+"</div>"
	    				+"<div><label>办公地址：</label>"+(data.items[i]["officeAddress"]||"无")+"</div>"
						+"<h3>负责人信息</h3>"
						+"<div><label>姓名：</label>"+data.items[i]["relationName"]+"&nbsp;&nbsp;<label>手机：</label>"+data.items[i]["relationCellphone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+data.items[i]["relationPhone"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+data.items[i]["relationEmail"]+"</div>"
						+"<h3>联系人信息</h3>"
						+"<div><label>姓名：</label>"+data.items[i]["linkman"]+"&nbsp;&nbsp;<label>手机：</label>"+data.items[i]["linkmanCellphone"]+"&nbsp;&nbsp;<label>办公电话：</label>"+data.items[i]["linkmanPhone"]+"&nbsp;&nbsp;<label>电子邮箱：</label>"+data.items[i]["linkmanEmail"]+"</div>"
						+"</div>"
						+"</div></div>"
	        		}
	        	}
	            return data.body;
	        },
	        "bProcessing": false,
	        "fnServerData": fnDataTablesPipeline,
	        "aoColumns": table_data_webInfos_title,
	        "fnCreatedRow":function (id) {
	        	
	        },
	        "fnInitComplete": function () {
	        	isComplate = true;
	        },
	        "fnPreDrawCallback": function(oSettings) {
                $("#table_data_webInfos_wrapper").siblings(".dropload-load").hide();
	        }
	        
	    });
	}
	
}

/**
 * 弹窗显示站点栏目信息
 */
function showChannelInfo(siteCode,siteName){
	$('#edit').modal('show');
	$("#column_undefined").hide();
	$("#principalName").html("");
	$("#principalTelephone").html("");
	$("#principalMobile").html("");
	$("#principalEmail").html("");
	$("#linkmanNameChannle").html("");
	$("#telephone2Chanele").html("");
	$("#mobile2Chanele").html("");
	$("#email2Chanele").html("");
	scrollTop = $(document).scrollTop();
    $(".page-wrap").addClass("page-fixed");
    $(document).scrollTop(0);
    $("#channelList1").html("");
    $("#myModalLabel_show").html("");
    $("#myModalLabel_show").html(siteCode+"-"+siteName);
    var tempLate = "";
    $.ajax({
        async: false,
        type: "GET",
        url: webPath + "/manageInfo_getWebChannelInfos.action?siteCode="+siteCode,
        dataType: "json",
        contentType: "application/text"
    }).done(function (response) {
    	if(!response.items || !response.items.length){
    		$("#column_undefined").show();
    		$("#principalName").html(response.principalName);
    		$("#principalTelephone").html(response.telephone);
    		$("#principalMobile").html(response.mobile);
    		$("#principalEmail").html(response.email);
    		$("#linkmanNameChannle").html(response.linkmanName);
    		$("#telephone2Chanele").html(response.telephone2);
    		$("#mobile2Chanele").html(response.mobile2);
    		$("#email2Chanele").html(response.email2);
    	}else{
    		tempLate = "<table id='table_data_channel_columnTab' cellpadding='0' cellspacing='0' class='table dblclick-tab'><tbody>";
    		tempLate = tempLate + "<tr><th>序号</th><th>名称</th><th>URL</th><th>跳转URL</th><th>类别</th><th>子类</th><th>监测状态</th></tr>";
    		_.each(response.items,function(item,i){
    			tempLate = tempLate + "<tr>";
    			tempLate = tempLate + "<td>"+(i+1)+"</td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w2' title='"+(item.channelName||"无")+"'>"+(item.channelName||"无")+"</div></td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w1' title='"+(item.channelUrl||"无")+"'>"+(item.channelUrl||"无")+"</div></td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w2' title='"+(item.jumpUrl||"无")+"'>"+(item.jumpUrl||"无")+"</div></td>";
    			tempLate = tempLate + "<td>"+(item.dicChannelName||"无")+"</td>";
    			tempLate = tempLate + "<td>"+(item.dicChannelSonIdName||"无")+"</td>";
    			
    			//var linkStatus = item.linkStatus;
    			tempLate = tempLate + "<td><div>";
    			if(item.status || item.status == 1 || item.status == "1"){
    				tempLate = tempLate + "<div class='ellipsis-box ellipsis-w4-c'><span class='status_blue'>正常</span><div>";
    				//tempLate = tempLate + "<span class='status_red'>监测异常</span> <span class='status_normal'>连不通，请检查URL或连通性</span>";
    			}else{
    				tempLate = tempLate + "<div class='ellipsis-box ellipsis-w4-c'><span class='status_red' title='未设置监测该栏目'>未监测</span><div>";
    			}
    			tempLate = tempLate + "</div></td>";
    		});
    		tempLate = tempLate + "</tbody></table>";
    	}
    	$("#channelList1").html(tempLate);    
    }).fail(function (data) {
    	alert("错误！");
    }).always(function () {

    });
}

//订单导出Excel
function orderExportExcel(orderId, orderNum) {
    var aId = "websiteInfoCheckName_" + orderNum;
    var websiteIds = new Array();
    $("input[name=" + aId + "]:checked").each(function () {
        websiteIds.push($(this).attr('value'));
    });

    window.location.href = webPath + "/manageInfo_orderExportExcel.action?orderInfoId=" + orderId + "&orderInfoNum=" + orderNum + "&websiteIds=" + websiteIds;
}


function basicInfo(obj){
	$(".tab-content > .guanl-con").removeClass("active");
	$("#khxx").addClass("active");
	
	//选中状态
	leftActive(obj);
}
/**
 * 左侧菜单选中状态
 * @param obj:当前点击 a 标签
 */
function leftActive(obj) {
	if(obj==undefined||obj==null)
		return;
	
	$("#menuFx li").removeClass("level1-li level1-li-selected")
	$(obj).parent("li").addClass("level1-li level1-li-selected")
}
$(function(){
    nameHover("table_data_webInfos");
	$("#edit").on("hide",function(){
		$(".page-wrap").removeClass("page-fixed");	
	});
});
