$(function() {
	// 列表检索
	$("#keyId").keyup(
			function() {
				var searchInfo = $("#keyId").val();
				if (searchInfo == "") {
					$("#webSiteConnectedTbody tr").show();
				} else {
					$("#webSiteConnectedTbody").find(".cor-01a5ec").each(
							function(index, element) {
								if (($(this).html().indexOf(searchInfo) < 0)
										&& ($(this).parent().parent().find(
												".cor-01a5ec").html().indexOf(
												searchInfo) < 0)) {
									$(this).parents("tr").hide();
								} else {
									$(this).parents("tr").show();
								}
							});
				}
			});

	$('.everymode').on('ifChecked', function(event) { // ifCreated 事件应该在插件初始化之前绑定
		changeFunction();
	});
	
	var typeIdJump = $("#typeIdJump").val();
	if(typeIdJump != ""){
		securityBasic();
		
		$("#typeId").val(typeIdJump);
		changeFunction();
		$("#typeIdJump").val("")
		return;
	}
		
	securityBasic(); // 默认加载 基本信息

});

// 导出数据
function securityExcel() {
	var modeVal = $("input[name='mode']:checked").val(); // 人工为1 系统为2
	var typeId = $("#typeId").val();
	var sizeId = $("#sizeId").html();
	var siteType = $("#siteTypeIdVal").val(); // 获得网站类别
	var servicePeriod = $("#servicePeriodIdText").val(); // 监测周期
	var servicePeriodId = $("#servicePeriodIdVal").val();// 监测周期id
	if (typeId == 0) {
		servicePeriod = $("#servicePeriodIdTextBasic").val(); // 监测周期
		servicePeriodId = $("#servicePeriodIdValBasic").val();// 监测周期id
	}
	if (sizeId == "") {
		alert("当前没有数据可以导出，请检查");
		return;
	}
	window.location.href = webPath
			+ "/securityGuarantee_securityExcel.action?siteType=" + siteType
			+ "&servicePeriod=" + servicePeriod + "&typeId=" + typeId
			+ "&servicePeriodId=" + servicePeriodId + "&modeVal=" + modeVal;
}

function changeFunction() { // 下拉选择变化时 加载新的数据
	$("#webSiteConnectedTbody").html("");
	var typeId = $("#typeId").val();
	if (typeId == 0) {
		securityBasic();
	} else if (typeId == 1) {
		securityBlank();
	} else if (typeId == 2) {
		securityResponse();
	} else if (typeId == 3) {
		securityService();
	}

}

function showAndHide(flag) {
	$(".pay").hide();
	if (flag) {
		$(".detection-mode-box").show();
		$(".serviceTwo").show();
		$(".serviceOne").hide();

	} else {
		$(".detection-mode-box").hide();
		$(".serviceOne").show();
		$(".serviceTwo").hide();
		$(".conditions").show();
	}
}

// 基本信息
function securityBasic() {
	showAndHide(true);
	var modeVal = $("input[name='mode']:checked").val(); // 人工为1 系统为2
	$('.every_tip').removeClass('on');
	$("#tab0").addClass('on');
	$("#typeId").val(0);
	var htmlThead = '';
	var url = "/securityGuarantee_getSercurityMsg.action";
	var jumpUrl = "/manageInfo_departmentTB.action?type=1&siteCode="; // 监测栏目个数跳转Url
	var jumpUrl2 = "/securityGuaranteeTb_securityGuaranteeMainTb.action?siteCode="; // 逾期跳转Url
	getMessage(htmlThead, url, 0, jumpUrl, jumpUrl2);
}

// 空白栏目
function securityBlank() {
	showAndHide(false);
	$('.every_tip').removeClass('on');
	$("#tab1").addClass('on');
	$("#typeId").val(1);
	var htmlThead = '<tr>';
	htmlThead += ' <th style="width: 30%;" class="th-left first-row">网站名称</th>';
	htmlThead += ' <th style="width: 15%;" class="th-center">空白栏目个数</th>';
	htmlThead += '</tr>';
	var url = "/securityGuarantee_getSercurityMsg.action";
	var jumpUrl = "/securityGuaranteeTb_securityGuaranteeMainTb.action?siteCode="; // 空白栏目个数跳转Url
	getMessage(htmlThead, url, 1, jumpUrl, null);
}

// 互动回应
function securityResponse() {
	showAndHide(false);
	$('.every_tip').removeClass('on');
	$("#tab2").addClass('on');
	$("#typeId").val(2);
	var htmlThead = '<tr>';
	htmlThead += ' <th style="width: 30%;" class="th-left first-row">网站名称</th>';
	htmlThead += ' <th style="width: 15%;" class="th-center">问题个数</th>';
	htmlThead += '</tr>';
	var url = "/securityGuarantee_getSercurityMsg.action"; //  
	var jumpUrl = "/securityGuaranteeTb_securityGuaranteeMainTb.action?siteCode="; // 互动回应问题个数跳转Url
	getMessage(htmlThead, url, 2, jumpUrl, null);
}
// 服务不实用
function securityService() {
	showAndHide(false);
	$('.every_tip').removeClass('on');
	$("#tab3").addClass('on');
	$("#typeId").val(3);
	var htmlThead = '<tr>';
	htmlThead += ' <th style="width: 30%;" class="th-left first-row">网站名称</th>';
	htmlThead += ' <th style="width: 15%;" class="th-center">问题个数</th>';
	htmlThead += '</tr>';
	var url = "/securityGuarantee_getSercurityMsg.action";
	var jumpUrl = "/securityGuaranteeTb_securityGuaranteeMainTb.action?siteCode="; // 服务不实用问题个数跳转Url
	getMessage(htmlThead, url, 3, jumpUrl, null);
}
/**
 * 
 * @param htmlThead
 *            拼接的表格 [表头]
 * @param url
 *            网站名称跳转的Url
 * @param typeId
 *            保障问题的问题类型[0 基本信息 1 空白栏目 2互动回应差 3服务不实用]
 * @param jumpUrl
 *            点击数字跳转的url
 * @param jumpUrl2
 *            点击数字跳转的url
 */
function getMessage(htmlThead, url, typeId, jumpUrl, jumpUrl2) {
	 $("#webSiteConnectedTbody").html(copyInformation(2,null));
	 var text = copyInformation(1,"没有查询到相应数据");
	var modeVal = $("input[name='mode']:checked").val(); // 人工为1 系统为2

	$(".free-html").html("");
	// 无合同的情况
	/*
	 * $(".detection-mode-box").show(); $(".conditions").show();
	 */
	$("#tHead").html("");
	$("#webSiteConnectedTbody").html("");
	$("#tHead").append(htmlThead);
	$("#sizeId").html("");
	var siteType = $("#siteTypeIdVal").val(); // 获得网站类别
	var servicePeriod = $("#servicePeriodIdText").val(); // 监测周期
	var servicePeriodId = $("#servicePeriodIdVal").val();// 监测周期id
	if(servicePeriodId == ""){
		servicePeriodId = $("#nearServicePeriodId").val();
	}
	if (typeId == 0) {
		servicePeriod = $("#servicePeriodIdTextBasic").val(); // 监测周期
		servicePeriodId = $("#servicePeriodIdValBasic").val();// 监测周期id
		if(servicePeriodId == ""){
			servicePeriodId = $("#nearServicePeriodBasicId").val();
		}
	}

	$.ajax({
				type : "POST",
				url : webPath + url,
				data : {
					siteType : siteType,
					servicePeriod : servicePeriod,
					typeId : typeId,
					servicePeriodId : servicePeriodId,
					modeVal : modeVal
				},
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.success == 'true') {
						$("#webSiteConnectedTbody").html("");
						$("#table-Sum").html("");

						var html = '';
						var doorHtml = '';
						var sumHtml = '';
						var list = data.body;
						var mode = data.modeVal; // 检测方式
						var dateStr = data.dateStr; // 系统检测时间
						var noServicePeriod = data.noServicePeriod;

						$(".period-box").show();
						$(".total-box").show();
						$("#dateStr").html("");
						if (typeId == 0) {
								if (mode == '2') {
									$("#system").attr("checked", "checked");
									$(".period-box").hide();
									$(".total-box").hide();
									$("#dateStr").html("检测时间： " + dateStr);
								} else {
									$("#manual").attr("checked", "checked");
									$(".conditions").show();
									$(".period-box").show();
									$(".total-box").show();
									$("#dateStr").html("");
								}
						}

						if (noServicePeriod == -1) { // 基本信息 没有查询到服务周期
							$("#aaaTypeTextBasic").html("没有查询到服务周期");

							$("#manual").parent().addClass('checked');
						} else if (noServicePeriod == -2) {
							$("#aaaTypeText").html("没有查询到服务周期");
						}

						if (list != null && list.length > 0) {
							$("#sizeId").html(list.length); // 查询出来的站点数量
							// 控制基本信息表头
							if (typeId == 0) {
								htmlThead += '<tr><th style="width: 30%;" class="th-left first-row">网站名称</th>';
								if (mode == '1') {
									// 检测方式 为人工
									htmlThead += '<th style="width:30%" class="th-center">逾期未更新</th>';
									htmlThead += '</tr>';
									$("#tHead").append(htmlThead);
								} else {
									// 2 检测方式 为系统
									htmlThead += '<th style="width:30%" class="th-center">监测栏目/栏目数量</th>';
									htmlThead += '<th style="width:30%" class="th-center">逾期未更新</th>';
									htmlThead += '</tr>';
									$("#tHead").append(htmlThead);
								}
							}

						$.each(list,function(index, obj) {
							if (typeId == 0) {
								var url = obj.databaseInfo.url;
								if (url != "") {
									if (!url.match("http")) {
										url = "http://" + url;
									}
								}
									html = getHtml(html,
											url, obj,
											typeId, mode,
											jumpUrl2,
											jumpUrl,
											servicePeriodId);

							}else {  // 空白，互动，服务
								var uri = obj.url;
								if (uri != "") {
									if (!uri.match("http")) {
										uri = "http://" + uri;
									}
								}
									html = columnHtml(html,
											uri, obj,
											typeId, mode,
											jumpUrl2,
											jumpUrl,
											servicePeriodId);
							}
						});
							$("#webSiteConnectedTbody").append(doorHtml);
							$("#webSiteConnectedTbody").append(html);
						} else {
							$("#webSiteConnectedTbody").append(text);
						}
					} else if (data.success == 'other') {
						$(".pay").show();
						$(".pay").addClass('free-html');
						$(".free-html").html("");
						// 无合同的情况
						$(".detection-mode-box").hide();
						$(".conditions").hide();

						$("#webSiteConnectedTbody").html("");
						$("#content").hide();

						var pay = "<i></i><span class='font-bold'>提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'>点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href='mailto:jianguan@ucap.com.cn'>jianguan@ucap.com.cn</a>"
						$(".free-html").append(pay);
					} else {
						var mode = data.modeVal; // 检测方式
						if (mode == '1') {
							// 检测方式 为人工
							$("#manual").attr("checked", "checked");
						} else {
							// 2 检测方式 为系统
							$("#system").attr("checked", "checked");
						}
						$("#webSiteConnectedTbody").append(text);
					}
				},
			});
}

function getHtml(html, url, obj, typeId, mode, jumpUrl2, jumpUrl,
		servicePeriodId) {
	html += '<tr>';
	html += '<td class="td-left first-row wz-name w30p"><a href="' + url
			+ '" target="_blank" class="sort-num cor-01a5ec">'
			+ obj.databaseInfo.name + '</a> <br/> <a onClick="fillJumpTb();" href="' + webPath
			+ '/fillUnit_gailan.action?siteCode=' + obj.databaseInfo.siteCode
			+ '" target="_blank" class="cor-01a5ec">'
			+ obj.databaseInfo.siteCode + '</td>';
	// 标志码跳转填报单位功能页面
	if (typeId == 0) {
		if (mode == '1') {
			// 逾期未更新数字跳转到填报单位内容保障问题基本信息的逾期未更新
			// homeNotUpdate
			html += '<td class="td-center w30p"><a onClick="security();" href="' + webPath + jumpUrl2
					+ obj.databaseInfo.siteCode + "&mode=" + mode
					+ "&servicePeriodId=" + servicePeriodId + "&typeId=" + typeId
					+ '" target="_blank" >' + obj.size + '</a></td>'; // 逾期栏目个数
		} else {
			html += '<td class="td-center w30p"><a onClick="channel();" href="' + webPath + jumpUrl
					+ obj.databaseInfo.siteCode + '" target="_blank" >'
					+ obj.modeStr + '</a></td>'; // 栏目个数数字跳转对应单位的栏目信息页面
			html += '<td class="td-center w30p"><a onClick="security();" href="' + webPath + jumpUrl2
					+ obj.databaseInfo.siteCode + "&mode=" + mode + "&typeId=" + typeId
					+ '" target="_blank" >' + obj.size + '</a></td>'; // 逾期栏目个数
		}

	}
	html += '</tr>';
	return html;

}

function columnHtml(html, url, obj, typeId, mode, jumpUrl2, jumpUrl,
		servicePeriodId) {
	html += '<tr>';
	html += '<td class="td-left first-row wz-name w30p"><a href="' + url
			+ '" target="_blank" class="sort-num cor-01a5ec">'
			+ obj.name + '</a> <br/> <a onClick="fillJumpTb();" href="' + webPath
			+ '/fillUnit_gailan.action?siteCode=' + obj.siteCode
			+ '" target="_blank" class="cor-01a5ec">'
			+ obj.siteCode + '</td>';
	html += '<td class="td-center w15p"><a onClick="security();" href="' + webPath + jumpUrl
			+ obj.siteCode + "&servicePeriodId="
			+ servicePeriodId + "&typeId=" + typeId + '"  target="_blank" >' + obj.size
			+ '</a></td>'; // 查询出来的栏目或者问题个数
	html += '</tr>';
	return html;

}

function security(){
	var uri = webPath + "/fillUnit_gailan.action";
	var url = webPath + "/securityGuaranteeTb_securityGuaranteeMainTb.action";
	changeCookie(2,uri,null,url);
}

//跳转定位
function channel(){
	var uri = webPath + "/manageInfo_departmentTB.action";
	var url = webPath + "/manageInfo_departmentTB.action";
	changeCookie(2,uri,null,url);
}

