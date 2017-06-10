var modifySiteCode;// 定义一个ID用来保存每次打开某一条订单信息的ID，用来提交修改传到后台
/*var modifyWebsiteInfoId;*/
var isSearch = true;
var isChannelUrlLegal=false;
var homeUrlOld;
var homeJumpUrlOld;
var channelUrlVal=0;
var orgToDataInfo = orgToInfo;
//隐藏top引导提示
$("#steps").hide(); 
$("#guideSelection").hide(); 
$("#tb-yd-second-steps").show(); 

var serviceState = {
	    '0': "<div class='status-box bg-gray'>未启动</div>",
	    '1': '<div class="status-box bg-green">服务中</div>',
	    '2': '<div class="status-box bg-red">服务报告已完成</div>'
};
$(function () {
	
	getChannelInfo();
	//负责人是否接受信息
	setIsMobileReceive();
	setIsEmailReceive();
	var top = $('#top').val();
	if(top != 8){
		
	}
	//联系人是否接受信息
	setIsMobileReceive2();
	setIsEmailReceive2();
	
	//modifyWebsiteInfoId = $('#websiteInfoId').val();
	modifySiteCode=$('#siteCode').val();
	
	 homeUrlOld=$('#homeUrl_table').val();
     homeJumpUrlOld=$('#jumpUrl_table').val();
	
	 $(".prependedInput").iePlaceholder();
	 $(".accordion-inner table tr:even td").css("background","#fafbfc");
	
	$('#channelInfo_term').bind('input propertychange', function() {
		table_data_channel_columnTab.fnClearTable();
	});
	
	$("input[type='checkbox'],input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	

	
	//负责人电话
	$("#mobileReceiveCheckBox").on('ifChecked', function(event) {
		$("#mobileReceiveId").val(1);
	});

	$("#mobileReceiveCheckBox").on('ifUnchecked', function(event) {
		$("#mobileReceiveId").val(0);
	});

	
	//负责人邮箱
	$("#emailReceiveCheckBox").on('ifChecked', function(event) {
		$("#emailReceiveId").val(1);
	});

	$("#emailReceiveCheckBox").on('ifUnchecked', function(event) {
		$("#emailReceiveId").val(0);
	});
	
	//联系人电话
	$("#mobileReceiveCheckBox2").on('ifChecked', function(event) {
		$("#mobileReceiveId2").val(1);
	});

	$("#mobileReceiveCheckBox2").on('ifUnchecked', function(event) {
		$("#mobileReceiveId2").val(0);
	}); 
	
	//联系人邮箱
	$("#emailReceiveCheckBox2").on('ifChecked', function(event) {
		$("#emailReceiveId2").val(1);
	});

	$("#emailReceiveCheckBox2").on('ifUnchecked', function(event) {
		$("#emailReceiveId2").val(0);
	}); 
	
	
	
	
	 //移除样式（站点基本信息页面）
 	$("#basicInfo").siblings().removeClass("active");
 	$("#homeUrl_table").removeClass("failure-input");
	$("#jumpUrl_table").removeClass("failure-input");
	$("#basicInfo .error-msg").remove();
	$("#basicInfo .failure").remove();
	$("#basicInfo .succeed").remove();
	$('#checkbox_tr').iCheck('uncheck');
    $('#checkbox_tr2').iCheck('uncheck');
    $("#checkbox_tr").on('ifChecked', function(event){
		 $("#jumpUrl_tr").removeClass("disabled-tr");
		 $('#jumpUrl_table').removeAttr("disabled"); 
		 $('#checkHomeJumpUrlBId').show(); 
		 isHomeUrlLegal=true;
		 isHomeJumpUrlLegal=false;
		 $("#homeUrl_table").siblings("i").remove();
		 $("#homeUrl_table").siblings(".error-msg").remove();
		 $("#homeUrl_table").removeClass("failure-input");	 
		 $("#jumpUrl_table").siblings(".error-msg").remove();
	});
	$("#checkbox_tr").on('ifUnchecked', function(event){
		$("#jumpUrl_tr").addClass("disabled-tr");
		 $('#jumpUrl_table').attr("disabled","disabled"); 
		 $('#checkHomeJumpUrlBId').hide();
		 
		 $("#jumpUrl_table").removeClass("failure-input");
		 $("#jumpUrl_table").siblings("i").remove();
		 $("#jumpUrl_table").siblings(".error-msg").remove();
		 isCheckHomeJumpUrl=false;
		 isCheckHomeUrl=false;
		 isHomeUrlLegal=false;
		 isHomeJumpUrlLegal=true;
		 $('#jumpUrl_table').val('');
	});

    $("#checkbox_tr2").on('ifChecked', function(event){
        $("#jumpUrl_tr2").removeClass("disabled-tr");
        $('#jump_url_id').removeAttr("disabled");


        $("#channel_url_id").siblings("i").remove();
        $("#channel_url_id").siblings(".error-msg").remove();
        $("#channel_url_id").removeClass("failure-input");
        $("#jump_url_id").siblings(".error-msg").remove();
    });
    $("#checkbox_tr2").on('ifUnchecked', function(event){
        $("#jumpUrl_tr2").addClass("disabled-tr");
        $('#jump_url_id').attr("disabled","disabled");


        $("#jump_url_id").removeClass("failure-input");
        $("#jump_url_id").siblings("i").remove();
        $("#jump_url_id").siblings(".error-msg").remove();
        $('#jump_url_id').val('');
    });
    
    
    //添加事件  基本信息首页url发生变化时，将在线检验的值修改0
    $("#homeUrl_table").change(function(){
    	//设置为在线校验未通过，使用再次去点击在线校验
    	$("#home_url_state_id").val("0");
    });
    //添加事件  基本信息跳转url发生变化时，将在线检验的值修改0
    $("#jumpUrl_table").change(function(){
    	//设置为在线校验未通过，使用再次去点击在线校验
    	$("#jump_url_state_id").val("0");
    });
    //添加事件  栏目添加   栏目url发生变化时，将在线检验的值修改0
    $("#channel_url_id").change(function(){
    	//设置为在线校验未通过，使用再次去点击在线校验
    	$("#channel_url_state_id").val("0");
    });
    //添加事件  栏目添加  栏目跳转url发生变化时，将在线检验的值修改0
    $("#jump_url_id").change(function(){
    	//设置为在线校验未通过，使用再次去点击在线校验
    	$("#channel_jump_url_state_id").val("0");
    });
	var jumpUrl = $("#jumpUrl_table").val();
	if(jumpUrl != null && jumpUrl != ''){
		$('#checkbox_tr').iCheck('check');
	}else{
		$('#checkbox_tr').iCheck('uncheck');
	}
	
	var typeId = $("#typeId").val();
	if(typeId != "tp"){
		$("#ulIds li").removeClass("active");
		$("#culId").addClass("active");  
		$("#basicInfo").hide();
		$("#columnInfo").show();
	}
	
	$("#baseId").click(function(){
		$("#basicInfo").show();
		$("#columnInfo").hide();
	});
	
	$("#culId").click(function(){
		$("#basicInfo").hide();
		$("#columnInfo").show();
	});

});


var out;
/**
 * 进度条加载到80%
 * @param testUrl
 */
function progress(testUrl){
	var urlConnTest=testUrl;
	channelUrlVal = channelUrlVal + 4;
    $(urlConnTest).find(".loading-num").siblings("span").html("连通测试中，可能需要几十秒钟，请稍候......");
	$(urlConnTest).find(".column-loading-con").css("width",channelUrlVal+"%");
  	$(urlConnTest).find(".loading-num").html(channelUrlVal+"%");
	if (channelUrlVal <= 80) {
		out=setTimeout("progress('"+urlConnTest+"')", 100);
	}
}
/**
 * 栏目url线校验事件
 */
function onLineCheckChannelUrl(){
	//获取栏目url
	var channelUrl = $("#channel_url_id").val();
	if(isNull(channelUrl)){
		$("#model_channel_loading_box").attr("style","display:none");
		$("#model_channel_loading_box").siblings(".error-msg").remove();
		$("#model_channel_loading_box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	channelUrlVal=0;
	$("#channel_url_conn_test").addClass("dis-btn-07c1f2").removeAttr("onclick");
	
	$("#model_channel_loading_box").removeClass("column-loading-sub");
	$("#model_channel_loading_box").removeClass("column-loading-error");
	$("#model_channel_loading_box").attr("style","display:block");

	
	
	
	//进度条加载
	progress("#model_channel_loading_box");
	//验证栏目url是否合法
	var isLe = onLineCheckUrl(channelUrl,"model_channel_loading_box","loading_channel_url_id");
	
}
/**
 * @param url
 * @returns 返回URL是否合法
 */
function onLineCheckUrl(url,id1,id2){
	var jsonObj = {};
    jsonObj["url"] = url;
	var isLegal = false;
	
	$.ajax({
        async: true,
        type: "POST",
        url: webPath+"/manageInfo_onlineVerifyUrl.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json"
    }).done(function (response) {
    	isLegal = response.result;
    	loadPlanBar(id1,id2, isLegal);

    }).fail(function (data) {
    	loadPlanBar(id1,id2, isLegal);
    	alert("验证错误！");
    }).always(function () {
    	clearTimeout(out);
    });	
	return isLegal;
	
}
/**
 * 
 * @param url1 
 * @param url2
 * @returns
 */
function loadPlanBar(id1,id2, isLegal){
	if(isLegal){//url合法
		isChannelUrlLegal=true;
		$("#"+id1).addClass("column-loading-sub");
    	
		$("#"+id2).html("<span>连通测试成功！</span><span class='loading-num'>100%</span>");
		channelUrlVal = 0;
		$("#"+id1).find(".column-loading-con").css("width","100%");
		$("#"+id1).find(".loading-num").html("100%");
		
		if(id1 == "home_url_loading-box"){
			//add by sunjq 打开按钮事件和颜色
			$("#home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeUrl()");
			if(isLegal){
				//首页url校验且成功
				$("#home_url_state_id").val("1");
			}else{
				$("#home_url_state_id").val("0");
			}

			//enda dd
		}else if(id1 == "jump_url_loading-box"){
			//add by sunjq 打开按钮事件和颜色
			$("#jump_home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeJumpUrl()");
			
			if(isLegal){
				//跳转url校验且成功
				$("#jump_url_state_id").val("1");
			}else{
				$("#jump_url_state_id").val("0");
			}
			//enda dd
		}else if(id1 == "model_channel_loading_box"){
			//add by sunjq 打开按钮事件和颜色     onLineCheckHomeJumpUrl
			$("#channel_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick"," onLineCheckChannelUrl()");
			
			if(isLegal){
				//栏目url校验且成功
				$("#channel_url_state_id").val("1");
			}else{
				$("#channel_url_state_id").val("0");
			}
			
			//enda dd
		}else if(id1 == "channel_jump_loading_box"){
			//add by sunjq 打开按钮事件和颜色
			$("#jump_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckjumpUrl()");
			
			if(isLegal){
				//栏目跳转url校验且成功
				$("#channel_jump_url_state_id").val("1");
			}else{
				$("#channel_jump_url_state_id").val("0");
			}
			//enda dd
		}
	}else{
		$("#"+id1).addClass("column-loading-error");
		
		$("#"+id2).html("<span>开普云监测服务器连不通您填写的 URL!</span><span class='loading-num'>100%</span>");
		channelUrlVal = 0;
		$("#"+id1).find(".column-loading-con").css("width","100%");
		$("#"+id1).find(".loading-num").html("100%");

        if(id1 == "home_url_loading-box"){
            //add by sunjq 打开按钮事件和颜色
            $("#home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeUrl()");
            //enda dd
        }else if(id1 == "jump_url_loading-box"){
            //add by sunjq 打开按钮事件和颜色
            $("#jump_home_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckHomeJumpUrl()");
            //enda dd
        }
        else if(id1 == "model_channel_loading_box"){
            //add by sunjq 打开按钮事件和颜色
            $("#channel_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckChannelUrl()");
            //enda dd
        }
        else if(id1 == "channel_jump_loading_box"){
            //add by sunjq 打开按钮事件和颜色
            $("#jump_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckjumpUrl()");
            //enda dd
        }
	}
}

/**
* 在线校验跳转URL
*/
function onLineCheckjumpUrl(){
	
	channelUrlVal = 0;
    if (!$("#channel_url_conn_test").parents("#jumpUrl_tr2").hasClass("disabled-tr")) {
    	
    	
    	
    	$("#channel_jump_loading_box").siblings(".error-msg").remove();
    	$("#channel_jump_loading_box").removeClass("column-loading-sub");
    	$("#channel_jump_loading_box").removeClass("column-loading-error");
    	$("#channel_jump_loading_box").attr("style","display:block");
    	//获取跳转栏目url
    	var jumpUrl = $("#jump_url_id").val();
    	//获取栏目url
    	var channelUrl = $("#channel_url_id").val();
    	
    	if(!isNull(jumpUrl) && (jumpUrl==channelUrl)){
    		
    		$("#channel_jump_loading_box").attr("style","display:none");
    		$("#channel_jump_loading_box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL和跳转URL不能相同</span>");
    		$("#jump_url_conn_test").removeClass("dis-btn-07c1f2").attr("onclick","onLineCheckjumpUrl()");
    	}else{
    		var testUrl="#channel_jump_loading_box";
    		if(isNull(jumpUrl)){
    			$('#jump_url_conn_test' ).siblings(".column-loading-box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;首页URL不能为空</span>");
    			$("#channel_jump_loading_box").attr("style","display:none");
    		}else{
    			
    			$("#jump_url_conn_test").addClass("dis-btn-07c1f2").removeAttr("onclick");
    			//进度条加载
    			progress("#channel_jump_loading_box");	
    			//验证栏目url是否合法
    			var isLe = onLineCheckUrl(jumpUrl,"channel_jump_loading_box","modal_jump_loading_id");
    		}
    	}
    }
}

var table_data_channel_columnTab_title = [];// 首页连通性表头
table_data_channel_columnTab_title.push({"mDataProp": "iCheck", "bSortable": false,"sWidth":"30px","sTitle": "<input type='checkbox' id='checkChannalAllId'/>", "sClass": "", "bVisible": true, "tabIndex": -9});
table_data_channel_columnTab_title.push({"mDataProp": "dataNumber","sWidth":"35px", "bSortable": false,"sTitle": "序号", "sClass": "", "bVisible": true, "tabIndex": -8});
table_data_channel_columnTab_title.push({"mDataProp": "channelName", "bSortable": false,"sWidth":"150px","sTitle": "名称", "sClass": "", "bVisible": true, "tabIndex": -7});
table_data_channel_columnTab_title.push({"mDataProp": "channelUrl","sWidth":"50px", "bSortable": false,"sTitle": "URL", "sClass": "", "bVisible": true, "tabIndex": -6});
table_data_channel_columnTab_title.push({"mDataProp": "jumpUrl", "sWidth":"50px","bSortable": false,"sTitle": "	跳转URL", "sClass": "", "bVisible": true, "tabIndex": -5});
table_data_channel_columnTab_title.push({"mDataProp": "dicChannelName","sWidth":"80px", "bSortable": false,"sTitle": "类别", "sClass": "", "bVisible": true, "tabIndex": -4});
table_data_channel_columnTab_title.push({"mDataProp": "dicChannelSonIdName","sWidth":"80px","bSortable": false, "sTitle": "子类","sClass": "", "bVisible": true, "tabIndex": -3});
table_data_channel_columnTab_title.push({"mDataProp": "status","bSortable": false,"sWidth":"35px", "sTitle": "监测","sClass": "", "bVisible": true, "tabIndex": -2});
table_data_channel_columnTab_title.push({"mDataProp": "linkStatus","bSortable": false,"sWidth":"70px", "sTitle": "监测状态","sClass": "", "bVisible": true, "tabIndex": -1});
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



//负责人是否邮箱手机接收预警信息
function setIsMobileReceive(){
	
	var isMobileReceive = $("#mobileReceiveId").val();
	
	if(isMobileReceive=='1'){
		$("#mobileReceiveCheckBox").attr("checked",'true');// 全选
	}
	
	
}

//负责人是否邮箱接收预警信息
function setIsEmailReceive(){
	
	var isEmailReceive = $("#emailReceiveId").val();
	
	if(isEmailReceive=='1'){
		$("#emailReceiveCheckBox").attr("checked",'true');// 全选
	}
}

//联系人是否手机接收预警信息
function setIsMobileReceive2(){
	
	var isMobileReceive = $("#mobileReceiveId2").val();
	
	if(isMobileReceive=='1'){
		$("#mobileReceiveCheckBox2").attr("checked",'true');// 全选
	}
	
	
}

//联系人是否邮箱接收预警信息
function setIsEmailReceive2(){
	
	var isEmailReceive = $("#emailReceiveId2").val();
	
	if(isEmailReceive=='1'){
		$("#emailReceiveCheckBox2").attr("checked",'true');// 全选
	}
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
		table_data_channel_columnTab.fnClearTable(false);
		table_data_channel_columnTab.fnReloadAjax(webPath+ "/manageInfo_getChannelInfo.action");
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
        "iDisplayLength": 100,
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
        			//var linkStatus = data.body[i]["linkStatus"];
        			var status = data.body[i]["status"];
        			
        			data.body[i]["iCheck"] = "<input type='checkbox'  value="+data.body[i]["id"]+" name='delId'/>";
        			data.body[i]["dataNumber"] = i+1;
        			data.body[i]["operation"] = "<i class='delete-i' onClick='delChannel("+ data.body[i]["id"] + ");'></i>";
          			data.body[i]["channelName"] ="<div class='ellipsis-box ellipsis-w1' title='"+data.body[i]["channelName"]+"'>"+data.body[i]["channelName"]+"</div>";
        			
          			//data.body[i]["channelUrl"] ="<div class='ellipsis-box ellipsis-w1' title='"+data.body[i]["channelUrl"]+"'><a href='"+data.body[i]["channelUrl"]+"' target='_blank'>"+data.body[i]["channelUrl"]+"</div>";
          			channelUrl = checkUrlHTTTP(channelUrl);
          			jumpurl = checkUrlHTTTP(jumpurl);
        			if(jumpurl == "无"){
        				data.body[i]["channelUrl"] ="<div class='ellipsis-box ellipsis-w1' title='"+channelUrl+"'><a href='"+channelUrl+"' target='_blank'>"+channelUrl+"</a></div>";
            			data.body[i]["jumpUrl"] = "<div class='ellipsis-box ellipsis-w1' title='"+jumpurl+"'>"+jumpurl+"</div>";
        			}else{
        				data.body[i]["channelUrl"] ="<div class='ellipsis-box ellipsis-w1' title='"+channelUrl+"'>"+channelUrl+"</div>";
            			data.body[i]["jumpUrl"] = "<div class='ellipsis-box ellipsis-w1' title='"+jumpurl+"'><a href='"+jumpurl+"' target='_blank'>"+jumpurl+"</a></div>";
        			}
	        		//console.log(linkStatus);	
        			if(status == 1){
        				if(orgToDataInfo == 1){
        					data.body[i]["status"] = "<i class='radio-phone active orgToInfo'  _vid='"+ data.body[i]["id"] + "'></i>";
        				}else{
        					data.body[i]["status"] = "<i class='radio-phone active orgToInfo' onClick='initLi(this);' _vid='"+ data.body[i]["id"] + "'></i>";
        				}
        				/*if(linkStatus == 1)
        					data.body[i]["linkStatus"] = "<span class='status_blue'>正常</span>";
        				else 
        					data.body[i]["linkStatus"] = "<span class='status_red'>监测异常</span> <span class='status_normal'>连不通，请检查URL或连通性</span>";*/
        				data.body[i]["linkStatus"] = "<div class='ellipsis-box ellipsis-w4-c'><span class='status_blue' style='width:40px;' title='正常'>正常</span></div>";
        			}else{
        				if(orgToDataInfo == 1){
        					data.body[i]["status"] = "<i class='radio-phone' _vid='"+ data.body[i]["id"] + "'></i>";
        				}else{
        					data.body[i]["status"] = "<i class='radio-phone' onClick='initLi(this);' _vid='"+ data.body[i]["id"] + "'></i>";
        				}
        				data.body[i]["linkStatus"] = "<div class='ellipsis-box ellipsis-w4-c'><span class='status_red' style='width:40px;' title='未设置监测该栏目，左侧选中即可'>未监测</span></div>";
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
			 $("#table_data_channel_columnTab_filter").show().css("margin", "-105px 0 0 200px").addClass("input-prepend input-prepend-black");
			 $("#table_data_channel_columnTab_filter").find("input").attr("placeholder", "请输入栏目名称、URL");
			 $("#table_data_channel_columnTab_length").hide();
			 $("#table_data_channel_columnTab_info").hide();
			 var table_dataManager_wrapper = $("#table_data_business");
			  table_dataManager_wrapper.find("th").css("cursor", "default");
			  $("#table_data_channel_columnTab_filter input").iePlaceholder();
        },
        "fnPreDrawCallback": function(oSettings) {
        	 
			$("input[type='checkbox']#checkChannalAllId").iCheck({ checkboxClass:
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
				if(data.success){
					alert(data.success);
				}else{
					$(li).removeClass("active");
					alert(data.errorMsg);
				}
				table_data_channel_columnTab.fnClearTable();
			},
			error:function(data){
				$(li).removeClass("active");
				alert(data.errorMsg);
				table_data_channel_columnTab.fnClearTable();
			}
		});
	}

//栏目跳转url不为空
function modalJumpUrlCheckTable(){
	//跳转url
	var modal_channel_url = $("#channel_url_id").val();
	var modal_jump_url = $("#jump_url_id").val();
	
	if(isNull(modal_jump_url)){
		$("#channel_jump_loading_box").siblings(".error-msg").remove();
		$("#channel_jump_loading_box").attr("style","display:none");
		$("#channel_jump_loading_box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	return true;
	
}
//栏目url不为空
function modalChannelUrlTest(){
	//跳转url
	var modal_channel_url = $("#channel_url_id").val();
	
	$("#model_channel_loading_box").siblings(".error-msg").remove();
	if(isNull(modal_channel_url)){
		$("model_channel_loading_box").attr("style","display:none");
		$("#model_channel_loading_box").after("<span style='display: inline;' class='error-msg'>&nbsp;&nbsp;&nbsp;请输入URL</span>");
		return false;
	}
	return true;
	
}



/**
 * 添加栏目--页面初始化
 */
function addColumnModal(){
	

	
	//显示弹出框
	$("#addColumnModal").modal("show");

	//清空数据
	 //栏目名称
	 $("#channel_name_id").val("");
	 //栏目url
	 $("#channel_url_id").val("");
	 //跳转url
	 $("#jump_url_id").val("");
	 
	 //清除错误提示信息
	 $("#channel_url_id").siblings(".error-msg").remove();
	 $("#jump_url_id").siblings(".error-msg").remove();
	 
	 
	 $("#model_channel_loading_box").attr("style","display:none");
	 $("#channel_jump_loading_box").attr("style","display:none");
	
	 // 调用ajax请求获取栏目类型loadDicChannelList下拉框初始化数据
	 var dicChannelList="";	 
	 var dicChannelSonList="";
	 var siteCode="";
	 var siteName="";
	 
	 
	 var siteCode=$("#siteCode").val();
	 
	 $.ajax({
			type : "POST",
			url : webPath + "/manageInfo_initAddChannel.action?siteCode="+siteCode,
			dataType : "json",
			data : {
				// 'scanDate' : scanDate
			},
			async : false,
			success : function(data) {
				//下拉框先清空
				$("#parent_channel_id").html("");
				$("#sun_channel_id").html("");
				
				//获取父类下拉框中所有的选项
				$.each(data.listDicChannel,
						function(index, channelData) {	
					dicChannelList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"	
				});
				
				$("#parent_channel_id").append(dicChannelList);
				
				//获取子类栏目中的现象
				$.each(data.listDicChannelSon,
						function(index, channelData) {		
					dicChannelSonList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"
					
				});
				$("#sun_channel_id").append(dicChannelSonList);
				
				//网站标识码
				siteCode=data.siteCode;
				
				$("#site_code_id").html(siteCode);
				//网站名称
				siteName=data.siteName;
				$("#site_name_id").html(siteName);
				
				//栏目名称 url清空
				$("#channel_name_id").html("");
				$("#channel_url_id").html("");
				$("#jump_url_id").html("");
			},error:function(data){
			}
		});
}

//下拉框添加联动效果
$("#parent_channel_id").on("change", function () {
	var parentId = $(this).find("option:selected").val();
	 var dicChannelList="";	 
	 var listDicChannelList="";
	 $.ajax({
			type : "POST",
			url : webPath + "/manageInfo_initAddChannel.action?parentId="+parentId,
			dataType : "json",
			async : false,
			success : function(data) {
				//先清空子类栏目
				$("#sun_channel_id").html("");
				
				if(data.listDicChannelSon && data.listDicChannelSon.length > 0){
					//获取子类栏目中的现象
					$.each(data.listDicChannelSon,function(index, channelData) {		
						listDicChannelList += "<option value='"+channelData.id+"'>"+channelData.channelName+"</option>"
						
					});
					$("#sun_channel_id").append(listDicChannelList);
				}	
			}
				
		});
 });

/**
 * @param url
 * @returns 返回URL是否合法
 */
/*function onLineCheckUrl2(url){
	var jsonObj = {};
    jsonObj["url"] = url;
	var isLegal = false;
	
	$.ajax({
        async: false,
        type: "POST",
        url: webPath+"/manageInfo_onlineVerifyUrl.action",
        dataType: "json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json"
    }).done(function (response) {
    	isLegal = response.result;
    }).fail(function (data) {
    	
    }).always(function () {
    });	
	return isLegal;
	
}*/


 //添加栏目信息页面---点击确定按钮将栏目信息保存到数据库
 function addChannel(){ 
	 
	 
	 //网站标识码
	 var siteCode = $("#site_code_id").html();
	 //栏目名称
	 var channelName=$("#channel_name_id").val();
	 //栏目url
	 var channelUrl = $("#channel_url_id").val();
	 //跳转url
	 var jumpUrl = $("#jump_url_id").val();
	 
	 var dicChannelId = $("#parent_channel_id").find("option:selected").val();
	 var dicChannelSonId = "";
	 if($("#sun_channel_id").find("option:selected").val()){
		 dicChannelSonId =  $("#sun_channel_id").find("option:selected").val();
	 }
	 var dicChannelText = $("#parent_channel_id").find("option:selected").text();
	 var dicChannelSonText = $("#sun_channel_id").find("option:selected").text();
	 if(isNull(channelName)){
		 alert("栏目名称为空，请输入栏目名称");
		 return;
	 }
	 
	 if(isNull(channelUrl)){
		 alert("栏目URL为空，请输入栏目URL");
		 return;
	 }
	 
	 //如果跳转url不为空，跳转url必须校验且成功，栏目url允许校验失败
	 //如果跳转url为空，首页url必须校验且成功
	 if(!isNull(jumpUrl)){
		 var jumpChannelUrlState=$("#channel_jump_url_state_id").val();
		 if(jumpChannelUrlState=="0"){
			 alert("跳转URL未校验或者校验失败，不能保存！");
			 return;
		 }

		if(jumpUrl==channelUrl){
			 alert("栏目URL与跳转URL不能相同！");
			 return;
		}
	 }else{
		var channelUrlState= $("#channel_url_state_id").val();
		if(channelUrlState=="0"){
			 alert("栏目URL未校验或者校验失败，不能保存！");
			 return;
		}
	 }
	 
	//防止添加栏目按钮点击多次--去除a标签的onclick属性
	$("#addChannel_btn_id").removeAttr('onclick');
	
	 var jsonObj = {};
		jsonObj["channelName"] = channelName;
		jsonObj["channelUrl"] = channelUrl;
		jsonObj["jumpUrl"] = jumpUrl;
		jsonObj["modifySiteCode"] = siteCode;
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
	    	if(response.success){
	    		alert(response.success);
	    		//隐藏弹出框
	    		$("#addColumnModal").modal("hide");
	    		table_data_channel_columnTab.fnClearTable();
	    	}else if(response.errorMsg){
	    		alert(response.errorMsg);
	    		return;
	    	}
	    }).fail(function (data) {
	    	alert(data.errorMsg);
	    	return;
	    }).always(function () {
			//业务逻辑执行成功之后，将
			$("#addChannel_btn_id").attr('onclick',"addChannel()");
	    });
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
    
    $('#checkChannalAllId').iCheck( 'uncheck');
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
    
    $('#checkChannalAllId').iCheck( 'uncheck');
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

    });
	
}

//栏目导出Excel
function channelExportExcel(){

	//获取网站标识码
	var siteCode=$("#siteCode").val();
	if(isNull(siteCode)){
		siteCode="";
	}
	var channelPointIds=new Array();	
	$("input[name=delId]:checked").each(function(){
		channelPointIds.push($(this).attr('value'));
	});
	var term= $("#table_data_channel_columnTab_filter").find("input").val();
   window.location.href=webPath+"/manageInfo_channelExportExcel.action?channelPointIds="+channelPointIds+"&siteCode="+siteCode+"&term="+term;
}





function clearChannelUrlCheck(){
	
	 if($("#channel_url_id").siblings("#checkChannelUrlId").length==0){
		 $("#channel_url_id").siblings("div").remove();
		 var  listStr="<div class='column-loading-box'><div id='loading_id'><span>连通测试中，请稍候......</span><span class='loading-num'>75%</span></div><div class='column-loading'><div class='column-loading-con'></div></div></div>";
		 $(ob).after(listStr);
	 }
}

function clearChannelJumpUrlCheck(){
	 if($("#channel_url_id").siblings("#checkJumpUrlId").length==0){
		 $("#channel_url_id").siblings("div").remove();
		 var  listStr="<div class='column-loading-box'><div id='jump_loading_id'><span>连通测试中，请稍候......</span><span class='loading-num'>75%</span></div><div class='column-loading'><div class='column-loading-con'></div></div></div>";
		 $("#channel_url_id").after(listStr);
	 }
}

