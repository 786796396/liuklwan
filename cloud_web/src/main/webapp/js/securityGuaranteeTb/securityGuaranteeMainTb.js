$(function() {
	 
	
	/*var loadMsg = copyInformation(2,null);
	$("webSiteConnectedloadingHide").html("");
	$("webSiteConnectedloadingHide").append(loadMsg);*/
	
	// 列表检索
	$("#keyId").keyup(
			function() {
				var searchInfo = $("#keyId").val();
				if (searchInfo == "") {
					$("#webSiteConnectedTbody tr").show();
				} else {
					$("#webSiteConnectedTbody").find(".searchPage").each(
							function(index, element) {
								if (($(this).html().indexOf(searchInfo) < 0)
										&& ($(this).parent().parent().find(
												".searchPage").html().indexOf(
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

	var typeIdTb = $("#typeIdTb").val(); //跳转选中的模块
	var modeTb = $("#modeTb").val(); //跳转如果是基本信息  高级还是标准版
	var servicePeriodIdTb = $("#servicePeriodIdTb").val(); //跳转的周期
	var spTbStr = $("#spTbStr").val(); 
	if(typeIdTb != ""){
		
		if (modeTb == 2) {
			$("#system").attr("checked", "checked");
			$(".resultTwo").show();
			$(".resultOne").hide();
		} else {
			$("#manual").attr("checked", "checked");
			$(".resultTwo").hide();
			$(".resultOne").show();
		}
		
		if(typeIdTb == "0"){
			$("#isSenior").val(modeTb);
			if(modeTb == "1" && servicePeriodIdTb != "" && spTbStr != ""){
				$("#typeTextBasic").html(spTbStr);
				$("#servicePeriodIdValBasic").val(servicePeriodIdTb);
			}
			securityBasic();
			return;
		}else if(typeIdTb == "1"){
			if(servicePeriodIdTb != "" && spTbStr != ""){
				$("#typeText").html(spTbStr);
				$("#servicePeriodIdVal").val(servicePeriodIdTb);
			}
			securityBlank();
			return;
		}else if(typeIdTb == "2"){
			if(servicePeriodIdTb != "" && spTbStr != ""){
				$("#typeText").html(spTbStr);
				$("#servicePeriodIdVal").val(servicePeriodIdTb);
			}
			securityResponse();
			return;
		}else if(typeIdTb == "3"){
			if(servicePeriodIdTb != "" && spTbStr != ""){
				$("#typeText").html(spTbStr);
				$("#servicePeriodIdVal").val(servicePeriodIdTb);
			}
			securityService();
			return;
		}
	}
	
	
	securityBasic(); // 默认加载 基本信息

});

// 导出数据
function securityExcel() {
	var typeId = $("#typeId").val();
	//var key=$("#keyInput").val();
	var siteCode = $("#siteCode").val();
	var modeVal = $("input[name='mode']:checked").val(); // 人工为1 系统为2
	var isSenior = $("#isSenior").val();
	if(modeVal == undefined){
		modeVal = isSenior;
	}
	var servicePdId = $("#servicePeriodIdVal").val();// 监测周期id
	if(servicePdId == ""){
		servicePdId = $("#typeTextId").val();
	}
	var siteCode = $("#siteCode").val();
	
	var type = $("#channelIdVal").val();
	var updateVal = $("#resultIdVal").val();
	if (typeId == 0) {
		servicePdId = $("#servicePeriodIdValBasic").val();// 监测周期id
		if(servicePdId == ""){
			servicePdId = $("#typeTextBasicId").val();
		}
	}
	if(typeId == 0){
		window.location.href = webPath
		+ "/upChannel_unUpdateChannelExcel.action?type=" + type
		+ "&servicePdId=" + servicePdId  + "&siteCode="
		+ siteCode + "&systemTwo=" + modeVal + "&updateVal=" + updateVal;
	}else if(typeId == 1){
		window.location.href = webPath
				+ "/securityBlankDetail_blankDetailExcel.action?serviceId="+ servicePdId;
	}else if(typeId == 2){
		window.location.href = webPath
		+ "/securityResponse_responseChannelExcel.action?serviceId="
		+ servicePdId;
	}else if(typeId == 3){
		window.location.href = webPath
		+ "/securityServcie_serviceExcel.action?servicePdId=" + servicePdId;
	}
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
		$(".periodTwo").hide();
		$(".periodOne").show();
		$(".detection-mode-box").show();
	} else {
		$(".periodTwo").show();
		$(".periodOne").hide();
		$(".detection-mode-box").hide();
		
	}
}

// 基本信息
function securityBasic() {
	keyId();
	$("#keyId").prop("placeholder","输入栏目名称和url");
	$("#keyId").val("");
	showAndHide(true);
	$('.every_tip').removeClass('on');
	$("#tab0").addClass('on');
	$("#typeId").val(0);
	
	var htmlThead = '<tr>';
	htmlThead += '<th class="th-center" style="width:40px;">序号</th>';
	htmlThead += '<th class="th_left" style="width:;">栏目名称</th>';
	htmlThead += '<th class="th_left" style="width:;">栏目类别</th>';
	htmlThead += '<th class="th_left" style="width:;">问题类型</th>';
	htmlThead += '<th class="th_left">网址</th>';
	
	var url = "/upChannel_updateChannelPage.action";
	getMessage(htmlThead, url, 0);
}

function keyId(){
	$("#keyId").keyup(
			function() {
				var searchInfo = $("#keyId").val();
				if (searchInfo == "") {
					$("#webSiteConnectedTbody tr").show();
				} else {
					$("#webSiteConnectedTbody").find(".searchPage").each(
							function(index, element) {
								if (($(this).html().indexOf(searchInfo) < 0)
										&& ($(this).parent().parent().find(
												".searchPage").html().indexOf(
												searchInfo) < 0)) {
									$(this).parents("tr").hide();
								} else {
									$(this).parents("tr").show();
								}
							});
				}
			});
}
function newKeyId(){
	$("#keyId").keyup(
			function() {
				var searchInfo = $("#keyId").val();
				if (searchInfo == "") {
					$("#webSiteConnectedTbody tr").show();
				} else {
					$("#webSiteConnectedTbody").find(".searchPage").each(
							function(index, element) {
								if (($(this).html().indexOf(searchInfo) < 0)
										&& ($(this).parent().find(
												".searchPage").html().indexOf(
												searchInfo) < 0)) {
									$(this).parents("tr").hide();
								} else {
									$(this).parents("tr").show();
								}
							});
				}
			});

}
// 空白栏目
function securityBlank() {
	newKeyId();
	$("#keyId").prop("placeholder","输入栏目名称");
	$("#keyId").val("");
	//加载对应周期
	showAndHide(false);
	$('.every_tip').removeClass('on');
	$("#tab1").addClass('on');
	$("#typeId").val(1);
	var htmlThead = '<tr>';
	htmlThead += ' <th class="th-center" style="width:40px;">序号</th>';
	htmlThead += ' <th class="channelName th_left" style="">栏目名称</th>';
	htmlThead += ' <th class="th_left" style="">栏目URL</th>';
	htmlThead += ' <th class="th_left">路径</th>';
	htmlThead += ' <th class="th_left">问题描述</th>';
	htmlThead += ' <th class="th_left">问题发现时间</th>';
	htmlThead += ' <th style="">截图</th>';
	htmlThead += '</tr>';
	var url = "/securityBlankDetail_blankDetailPage.action";
	getMessage(htmlThead, url, 1);
}

// 互动回应
function securityResponse() {
	keyId();
	$("#keyId").prop("placeholder","输入栏目名称");
	$("#keyId").val("");
	showAndHide(false);
	$('.every_tip').removeClass('on');
	$("#tab2").addClass('on');
	$("#typeId").val(2);
	var htmlThead = '<tr>';
	htmlThead += ' <th class="th-center" style="width:40px;">序号</th>';
	htmlThead += ' <th class="th_left" style="width:;">栏目类型</th>';
	htmlThead += ' <th class="channelName th_left" style="">栏目名称</th>';
	htmlThead += ' <th class="th_left" style="width:;">问题类型</th>';
	htmlThead += ' <th class="th_left">问题描述</th>';
	htmlThead += ' <th class="th_left">问题发现时间</th>';
	htmlThead += ' <th style="width:;">截图</th>';
	htmlThead += '</tr>';
	var url = "/securityResponse_responseChannelList.action"; //  
	getMessage(htmlThead, url, 2);
}
// 服务不实用
function securityService() {
	keyId();
	$("#keyId").prop("placeholder","输入办事事项");
	$("#keyId").val("");
	showAndHide(false);
	$('.every_tip').removeClass('on');
	$("#tab3").addClass('on');
	$("#typeId").val(3);
	var htmlThead = '<tr>';
	htmlThead += ' <th class="th-center" style="width:40px;">序号</th>';
	htmlThead += ' <th class="th_left" style="width:;">办事事项/地址</th>';
	htmlThead += ' <th class="th_left" style="width:;">问题类型</th>';
	htmlThead += ' <th class="th_left">问题描述</th>';
	htmlThead += ' <th class="th_left" style="width:;">父页面url</th>';
	htmlThead += ' <th class="th_left">问题发现时间</th>';
	htmlThead += ' <th style="width:;">截图</th';
	htmlThead += '</tr>';
	var url = "/securityServcie_serviceProblemPage.action";
	getMessage(htmlThead, url, 3);
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
function getMessage(htmlThead, url, typeId) {
	
	var modeVal = $("input[name='mode']:checked").val(); // 人工为1 系统为2
	var isSenior = $("#isSenior").val();
	if(modeVal == undefined){
		modeVal = isSenior;
	}
	$(".free-html").html("");
	// 无合同的情况
	/*
	 * $(".detection-mode-box").show(); $(".conditions").show();
	 */
	$("#tHead").html("");
	$("#webSiteConnectedTbody").html("");
	$("#webSiteConnectedloadingHide").show();
	/*$("#webSiteConnectedHide").hide();*/
	$("#sizeId").html("");
	/*var siteType = $("#siteTypeIdVal").val();*/ // 获得网站类别
	var servicePeriod = $("#servicePeriodIdText").val(); // 监测周期
	var servicePdId = $("#servicePeriodIdVal").val();// 监测周期id
	if(servicePdId == ""){
		servicePdId = $("#typeTextId").val();
	}
	var siteCode = $("#siteCode").val();
	
	//控制栏目类别和查询结果
	var type = $("#channelIdVal").val();
	var updateVal = $("#resultIdVal").val();
	if (typeId == 0) {
		servicePeriod = $("#servicePeriodIdTextBasic").val(); // 监测周期
		servicePdId = $("#servicePeriodIdValBasic").val();// 监测周期id
		if(servicePdId == ""){
			servicePdId = $("#typeTextBasicId").val();
		}
	}
	
	$(".export-all").attr("style","display:none");
	$.ajax({
				type : "POST",
				url : webPath + url,
				data : {
					siteCode : siteCode,
					servicePeriod : servicePeriod,
					typeId : typeId,
					servicePdId : servicePdId,
					systemTwo : modeVal,
					type : type,
					updateVal : updateVal
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
						var scanDateSystem = data.scanDateSystem; // 检测方式
						var dateStr = $("#yesterdayStr").val() // 系统检测时间
						$(".period-box").show();
						$(".total-box").show();
						$("#dateStr").html("");
						if (typeId == 0) {
							$("#channel").show();
							$("#result").show();
							$(".channelOne").show();
							//控制表头显示
							if(scanDateSystem != 2){
								htmlThead += '<th class="th_left" style="width:100px;" id="bihidden">监测时间</th>';
							}
							htmlThead += '<th class="th_left" style="width:;">最后更新时间</th>';
							htmlThead += '<th class="th-center" style="width:;">未更新天数</th>';
							htmlThead += '<th class="th-center"style="width:;">快照/截图</th>';
							
							if(scanDateSystem == 2){
								if($("#timeId")){
									$("#timeId").remove();
								}
								htmlThead += '<th id="timeId" style="width:;style=position:relative;" onmouseover="commonscandateOver()" onmouseout="commonscandateOut()">最后正常监测时间 '
									+'<div id="lud_detail" style="display: none;">'
									+'<div class="top">'
									+'<p class="line_top">'
									+'<span>*</span>监测服务器最后一次正常连通网站的时间。</p>'
									+'<p class="line-bott">为了更全面的进行监测，建议联系您的网站管理员将开普云监管IP地址加入白名单，<span class="ip_address" onclick="ip_address()">查看IP地址<i class="down"></i></span>'
									+'</p></div><div class="hidden_part"><div class="center"><div class="content">'
									+'<ul class="clearfix" id="whiteListIP"></ul></div></div><div class="bottom clearfix">'
									+'<div class="fl copy_all" id="commonscandate_copyAllId">复制全部</div>'
									+'<div class="fl g_close" id="commonscandate_copyCloseId" onclick="commonscandate_copyCloseId()">关闭</div></div></div></div></th></tr>';
							}else{
								htmlThead += '</tr>';
								if($("#timeId")){
									$("#timeId").remove();
								}
							}
							
								if (scanDateSystem == 2) {
									$("#system").attr("checked", "checked");
									$(".resultTwo").show();
									$(".resultOne").hide();
									//$(".period-box").hide();
									$(".total-box").hide();
									$("#dateStr").html("检测时间： " + dateStr);
									$(".periodOne").hide();
									$(".periodTwo").hide();
									$("#period").hide();
								} else {
									$("#period").show();
									$("#manual").attr("checked", "checked");
									$(".resultTwo").hide();
									$(".resultOne").show();
									$(".conditions").show();
									$(".period-box").show();
									$(".total-box").show();
									$("#dateStr").html("");
								}
						}else{
							$("#channel").hide();
							$("#result").hide();
							$("#period").show();
							$(".resultTwo").hide();
							$(".resultOne").hide();
							$(".channelOne").hide();
							
							$(".conditions").show();
							$("#webSiteConnectedTbody").html("");
							$("#webSiteConnectedloadingHide").show();
						}
						$("#tHead").append(htmlThead);
						
						if((servicePdId == null || servicePdId == "") && modeVal != 2){
							$("#webSiteConnectedloadingHide").hide();
							var hideMsg = copyInformation(1,"暂未监测");
							$("#webSiteConnectedTbody").append(hideMsg);
						}else{
							var listSize = data.listSize;
							if (listSize != null && listSize > 0) {
								$(".export-all").attr("style","display:block");
								$("#sizeId").html(listSize);
								$("#webSiteConnectedloadingHide").hide();
								/*$("#webSiteConnectedHide").hide();*/

								var html = "";
								var index = 0;
								$.each(list, function(index, obj) {
									index++;
									var url = "";
									if(typeId == 0 || typeId == 1 || typeId == 3){
										if(obj.url != "" && obj.url != null){
											url += obj.url;
											if (url != "") {
												if (!url.match("http")) {
													url = "http://" + url;
												}
											}
										}
										
									}else if(typeId == 2){
										if(obj.channelUrl != "" && obj.channelUrl != null){
											url += obj.channelUrl;
											if (url != "") {
												if (!url.match("http")) {
													url = "http://" + url;
												}
											}
										}
										
									}
									
									if (typeId == 0) {
										html += getBasicTbHtml(url, obj, index,
												scanDateSystem,data);
									} else if(typeId == 1){
										html += getBlackTbHtml(url, obj, index,data);
									} else if(typeId == 2){
										html += getResponseTbHtml(url, obj, index,data);
									} else if(typeId == 3){
										html += getServiceTbHtml(url, obj, index,data);
									}
								});
								$("#webSiteConnectedTbody").append(html);
							} else { //无数据的情况
								$("#webSiteConnectedTbody").html("");
								$("#webSiteConnectedloadingHide").hide();
								/*$("#webSiteConnectedHide").show();*/
								/*var hideMsg = "<tr><td colspan='7' class='jiangbei-part' style='padding-bottom: 100px;'> <i class='publi-ico_2 jiangbei'></i> <p>检测中，暂未发现问题!</p></td></tr>";*/
								var hideMsg = copyInformation(1,"未发现问题");
								$("#webSiteConnectedTbody").append(hideMsg);
							}
						}
					} else if (data.success == 'other') {
						$(".pay").show();
						$(".pay").addClass('free-html');
						$(".free-html").html("");
						// 无合同的情况
						$(".detection-mode-box").hide();
						$(".conditions").hide();

						$("#webSiteConnectedTbody").html("");
						$("#webSiteConnectedloadingHide").hide();
						$("#webSiteConnectedHide").hide();

						var pay = "<i></i><span class='font-bold'>提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'>点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href='mailto:jianguan@ucap.com.cn'>jianguan@ucap.com.cn</a>"
						$(".free-html").append(pay);
					} /*else {
						var mode = data.modeVal; // 检测方式
						if (mode == '1') {
							// 检测方式 为人工
							$("#manual").attr("checked", "checked");
						} else {
							// 2 检测方式 为系统
							$("#system").attr("checked", "checked");
						}
						$("#webSiteConnectedTbody").html("");
						$("#webSiteConnectedloadingHide").hide();
						$("#webSiteConnectedHide").show();
					}*/
				},
			});
}

function getBasicTbHtml(url, obj,index,scanDateSystem,data) {
	var list = "";
	list += "<tr><td class='td-center font_701999' style='width:40px;'>" + index + "</td>";
	if(obj.channel_name.length > 6){
		list += "<td class='td_left'><div class='searchPage' title='"+obj.channel_name+"'>"+obj.channel_name.substring(0,6) + "...</div></td>"; 
	}else{
		list += "<td class='td_left searchPage'>"+obj.channel_name + "</td>"; 
	}
	list += "<td class='td_left'>" + obj.dicChannelId + "</td>";
	list += "<td class='td_left'>" + obj.problemTypeId +"</td>"; 
	if(url.length > 20){
		list += "<td class='td_left'><a target='_blank' class='wz-name ellipsis-w1 cor-01a5ec searchPage' title='" + url + "' href='" + url + "'>" + url.substring(0, 20) + "...</a></td>";
	}else{
		list += "<td class='td_left'><a target='_blank' class='wz-name ellipsis-w1 cor-01a5ec searchPage' title='" + url + "' href='" + url + "'>" + url + "</a></td>";
	}
	
	if(scanDateSystem != 2){
		list+= "<td class='td_left'>" + obj.scanDate + "</td>";
	}
	
	list += "<td class='td_left'>" + obj.modify_date + "</td>";
	if(obj.problemTypeId.indexOf("正常")> -1){
		list += "<td class='td-center'><span>" + obj.unUpdateDays + "</span></td>";
	}else{
		list += "<td class='td-center'><span style='color:red'>" + obj.unUpdateDays + "</span></td>";
	}
	if (obj.imgUrl == '') {
		list += "<td class='td-center'>无</td>";
	} else {
		if (obj.imgUrl.match("htm")) {
			list += "<td class='th-center'><i class='jietu-ico publi-ico_2' onclick=\"getShot('"
				+ obj.imgUrl + "');\"></i></td>";
		} else {
			list += "<td class='th-center'><a target='_blank' href='jsp/onlineReport/cutImgs.jsp?imgUrl="+obj.imgUrl + "&litImgUrl="+data.litImgUrl+"'>" +
					"<i class='jietu-ico publi-ico_2' ></i></a></td>";
		}
	}
	
	if(scanDateSystem == 2){
		list += "<td><a href='javascript:void(0)'onclick=showConnectionDialog('"+obj.siteCode+"','"+obj.lastAccessDate+"',"+null+",'"+obj.url+"','"+2+"')"+">"+obj.lastAccessDate+"</a></td>";
	}
	
	list += "</tr>";
	return list;
}

function getBlackTbHtml(url, obj,index,data) {
		var list = "";
		list += "<tr><td class='td-center font_701999' style='width:40px;'>"+ index+ "</td>";
		list += "<td class='td_left channelName searchPage' style='width:;'> "+ obj.channelName+ "</td>";
		list += "<td class='td_left' style='width:;'><a class='url-ellipsis1 cor-01a5ec' href='"+ url+ "' target='_blank' title='"+ url+ "'>"+ (url.substring(0, 25))+ "...</a></td>";
		list += "<td class='td_left' style='width:;'><div class='wz-name' style='width:;' title='"+obj.path+"'> "+ obj.path.substring(0, 10) + "...</div></td>";
		list += "<td class='td_left' style='width:;'>"+ obj.problemDesc+ "</td>";
		list += "<td class='td_left' style='width:;'>"+obj.scanDate+"</td>";
		if (obj.imgUrl.match("htm")) {// 快照
			list += "<td style='width:;'><i class='jietu-ico publi-ico_2' onclick=\"getShot('"
				+ obj.imgUrl + "');\"></i></td></tr>";
		} else {// 截图
			imgUrl = obj.imgUrl;
			list += "<td><a target='_blank' href='"
				+ "jsp/onlineReport/cutImgs.jsp?imgUrl="+obj.imgUrl + "&litImgUrl="+data.litImgUrl+"'><i class='jietu-ico publi-ico_2' ></i></a></td></tr>";
		}
	return list;
}

function getResponseTbHtml(url, obj,index,data) {
	var list = "";
	list += "<tr><td class='td-center font_701999' style='width:40px;'>" + index + "</td>"
			+ "<td class='td_left '><div class='wz-name'>"
			+ obj.dicChannelId + "</div></td>"
			+ "<td class='td_left'><a class='wz-name _channelName searchPage cor-01a5ec' href='"
			+ url + "' target='_blank' title='" +obj.channelName
			+ "'>" + obj.channelName + "</a></td>"
			+ "<td class='td_left'>" + obj.problemTypeId + "</td>"
			+ "<td class='td_left'><div class='wz-name ellipsis-w1' title='"
			+ obj.problemDesc + "' >" + obj.problemDesc
			+ "</div></td>" + "<td class='td_left'>" + obj.scanDate
			+ "</td>";
	if (obj.imgUrl.match("htm")) {
		list += "<td><i class='jietu-ico publi-ico_2' onclick=\"getShot('"
				+ obj.imgUrl + "');\"></i></td></tr>";
	} else {
		list += "<td><a target='_blank' href='"
				+ "jsp/onlineReport/cutImgs.jsp?imgUrl=" + obj.imgUrl
				+ "&litImgUrl=" + data.litImgUrl + "'><i class='jietu-ico publi-ico_2' ></i></a></td></tr>";
	}
	return list;
}

function getServiceTbHtml(url, obj,index,data) {
	var liStr = "";
	var parentUrl = obj.parentUrl;
	if (parentUrl != "" && parentUrl != null && parentUrl!= "null") {
		if (!parentUrl.match("http")) {
			parentUrl = "http://" + url;
		}
	}else{
		parentUrl = "无";
	}
	liStr += "<tr><td class='td-center font_701999' style='width:40px;'>" + index + "</td>";
	if (url != "") {
		liStr += "<td class='td_left'><a class='url-ellipsis serviceItem cor-01a5ec searchPage' href='"
				+ url
				+ "'  target='_blank' title='"
				+ obj.serviceItem + "'>";
	
		if (obj.serviceItem.length > 10) {
			liStr += (obj.serviceItem.substring(0, 10))
					+ "...</a></td>";
		} else if (obj.serviceItem == "") {
			liStr += "无</a></td>";
		} else {
			liStr += obj.serviceItem + "</a></td>";
		}

	} else {
			liStr += "<td class='td_left'><div  class='info-box lineh-normal'><span><a id ='div"
				+ index
				+ "' class='url-ellipsis serviceItem cor-01a5ec searchPage'  onClick = \"jumpOlick('"
				+ index
				+ "');\" onmouseover=\"mouseOver('"
				+ index
				+ "','"
				+ obj.serviceItem
				+ "');\" href='javascript:void(0);'>";
			
		if (obj.serviceItem.length > 10) {
			liStr += (obj.serviceItem.substring(0, 10))
					+ "...</a></span></div></td>";
		} else if (obj.serviceItem == "") {
			liStr += "无</a></span></div></td>";
		} else {
			liStr += obj.serviceItem
			+ "</a></span></div></td>";
		}

	}
	
	if(obj.problemTypeName != ""){
		liStr += "<td class='td_left'><div  title='" + obj.problemTypeName+ "'>" + (obj.problemTypeName.substring(0, 10))+ "</div></td>";
	}
	if(obj.problemDesc != ""){
		liStr += "<td class='td_left'><div title='"+ obj.problemDesc + "'>"+ (obj.problemDesc.substring(0, 10)) + "</div></td>";
	}
	if(parentUrl == "无"){
		liStr += "<td class='td_left'>" + parentUrl+ "</td>";
	}else{
		if(parentUrl.length > 25){
			liStr += "<td class='td_left'><a class='url-ellipsis cor-01a5ec' href='" + parentUrl+ "' target='_blank' title='" + parentUrl + "'>" + parentUrl.substring(0, 25)+ "</a></td>";
		}else{
			liStr += "<td class='td_left'><a class='url-ellipsis cor-01a5ec' href='" + parentUrl+ "' target='_blank' title='" + parentUrl + "'>" + parentUrl+ "</a></td>";
		}
		
	}

	liStr += "<td class='td_left'>" + obj.scanDate+ "</td>";
	
	if (obj.imgUrl.match("htm")) {// 快照
		liStr += "<td><i class='jietu-ico publi-ico_2' onclick=\"getShot('"
				+ obj.imgUrl + "');\"></i></td></tr>";
	} else {// 截图
		liStr += "<td><a target='_blank' href='"
				+ "jsp/onlineReport/cutImgs.jsp?imgUrl=" + obj.imgUrl
				+ "&litImgUrl=" + data.litImgUrl
				+ "'><i class='jietu-ico publi-ico_2'></i></a></td></tr>";
	}
	
	return liStr;
}

function jumpOlick(id){
	$("#div"+id).prop('title', '无地址');  
}

function mouseOver(id,url){
	$("#div"+id).prop('title', url);  
}

//快照页面跳转
function getShot(imgUrl) {
	window.open(imgUrl);
}
