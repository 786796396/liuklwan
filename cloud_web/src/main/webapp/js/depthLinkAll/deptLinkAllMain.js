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

	depthLinkAll();

});

// 导出数据
function depthLinkAllExcel() {
	var sizeId = $("#sizeId").html();
	var siteType = $("#siteTypeIdVal").val(); // 获得网站类别
	var servicePeriodId = $("#servicePeriodIdVal").val();// 监测周期id
	if (sizeId == "") {
		alert("当前没有数据可以导出，请检查");
		return;
	}
	window.location.href = webPath
			+ "/depthLinkAll_depthLinkAllExcel.action?siteType=" + siteType
			+ "&servicePeriodId=" + servicePeriodId;
}

function changeFunction() { // 下拉选择变化时 加载新的数据
	$("#webSiteConnectedTbody").html("");
	depthLinkAll();
}

// 全站链接可用性
function depthLinkAll() {
	var htmlThead = '<tr>';
	htmlThead += ' <th style="width: 30%;" class="th-left first-row">网站名称</th>';
	htmlThead += ' <th style="width: 15%;" class="th-center">全站不可用链接个数</th>';
	htmlThead += '</tr>';
	var url = "/depthLinkAll_getDepthLinkAllMsg.action";
	var jumpUrl = "/linkAll_linkAllDetailIndex.action?siteCode="; // 跳转Url
	getMessage(htmlThead, url, jumpUrl, null);
}

/**
 * 
 * @param htmlThead
 *            拼接的表格 [表头]
 * @param url
 *            网站名称跳转的Url
 * @param jumpUrl
 *            点击数字跳转的url
 * @param jumpUrl2
 *            点击数字跳转的url
 */
function getMessage(htmlThead, url, jumpUrl, jumpUrl2) {
	$("#webSiteConnectedTbody").html(copyInformation(2,null));
	var text = copyInformation(1,"未发现问题");
	$(".free-html").html("");
	$("#tHead").html("");
	$("#webSiteConnectedTbody").html("");
	$("#tHead").append(htmlThead);
	$("#sizeId").html("");
	var siteType = $("#siteTypeIdVal").val(); // 获得网站类别
	var servicePeriodId = $("#servicePeriodIdVal").val();// 监测周期id

	$.ajax({
				type : "POST",
				url : webPath + url,
				data : {
					siteType : siteType,
					servicePeriodId : servicePeriodId
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
						var noServicePeriod = data.noServicePeriod;

						$(".period-box").show();
						$(".total-box").show();
						$("#webSiteConnectedloadingHide").hide();
						$("#webSiteConnectedHide").hide();
						
						if (noServicePeriod == -1) {
							$("#aaaTypeText").html("没有查询到服务周期");
							$("#webSiteConnectedTbody").append(copyInformation(1,"暂未监测"));
						}else{
							if (list != null && list.length > 0) {
								$("#sizeId").html(list.length); // 查询出来的站点数量

								$.each(list,function(index, obj) {
									var url = obj.databaseInfo.url;
									if (url != "") {
										if (!url.match("http")) {
											url = "http://" + url;
										}
									}
										html = getHtml(html, url,
												obj, jumpUrl2,
												jumpUrl,
												servicePeriodId);
								});
								$("#webSiteConnectedTbody").append(doorHtml);
								$("#webSiteConnectedTbody").append(html);
							} else {
								$("#webSiteConnectedTbody").append(text);
							}
						}
					}else if (data.success == 'other') {
						$(".pay").show();
						$(".pay").addClass('free-html');
						$(".free-html").html("");
						// 无合同的情况
						$(".detection-mode-box").hide();
						$(".conditions").hide();

						$("#webSiteConnectedTbody").html("");
						$("#webSiteConnectedloadingHide").hide();
						$("#webSiteConnectedHide").hide();
						$("#webSiteConnectedTbody").hide();
						$("#tHead").hide();
						var pay = "<i></i><span class='font-bold'>提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'>点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href='mailto:jianguan@ucap.com.cn'>jianguan@ucap.com.cn</a>"
						$(".free-html").append(pay);
					} else {
						$("#webSiteConnectedTbody").append(text);
					}
				},
			});
}

function getHtml(html, url, obj, jumpUrl2, jumpUrl, servicePeriodId) {

	html += '<tr>';
	html += '<td class="td-left first-row wz-name w30p"><a href="' + url
			+ '" target="_blank" class="sort-num cor-01a5ec">'
			+ obj.databaseInfo.name + '</a> <br/> <a onClick="fillJumpTb();" href="' + webPath
			+ '/fillUnit_gailan.action?siteCode=' + obj.databaseInfo.siteCode
			+ '" target="_blank" class="cor-01a5ec">'
			+ obj.databaseInfo.siteCode + '</td>';

	html += '<td class="td-center w15p"><a onClick= "jumpLinkTb();" href="' + webPath + jumpUrl
			+ obj.databaseInfo.siteCode + "&servicePeriodId=" + servicePeriodId
			+ '"  target="_blank" >' + obj.size + '</a></td>'; // 查询出来的问题个数
	html += '</tr>';
	return html;

}

function jumpLinkTb(){
	  uri = webPath + "/fillUnit_gailan.action";
	  url = webPath + "/linkAll_linkAllDetailIndex.action";
	  changeCookie(2,uri,null,url);
}
