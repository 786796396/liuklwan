$(function() {
	if(iscost==1){
		securityQuestionPage() ;
	}
	
	
});

// 列表查询
function securityQuestionPage() {

//	var serviceId = $("#service_peroid_id").val();
	$.ajax({
		type : "POST",
		url : webPath
				+ "/securityQuestion_securityQuestionPage.action",
		dataType : "json",
		async : false,
		success : function(data) {
			var conlist = new Array();
			conlist = data.returnList;

			var list = "";
			// 清空数据
			$("#tbody_security_question_id").html("");

			if (conlist.length>0) {
				// 空控制当前查询周期的显示和隐藏

				$("#security_question_period_id").attr("style",
						"display:block");
				// 表格数据的显示和隐藏
				$("#table_security_question_id").show();
				// 导出excel显示和隐藏
				$("#security_question_excel")
						.attr("style", "display:block");
				// 隐藏暂无数据
				$("#table_security_question_hide").attr("style",
						"display:none");
				if (conlist.length > 0) {
					$("#security_question_total_id").html(
							"共" + data.total + "个安全问题");
					for ( var i = 0; i < conlist.length; i++) {
						list += "<tr><td class='td_left font_701999' style='width:50px;'>"
								+ (i + 1)
								+ "</td>"
								+ "<td class='td_left channelName' style='width:150px;'>"
								+ conlist[i].scanDate
								+ "</td>"
								+ "<td class='td_left ' style='width:100px;'>"
								+ conlist[i].indicatorName
								+ "</td>"
								+ "<td class='td_left' style='width:320px;'>"
								+ conlist[i].problemDesc
								+ "</td>"
								+ "<td class='td_left' style='width:150px;'>"
								+ conlist[i].webVersion
								+ "</td>";
							//附件
							if(conlist[i].path==""){
								list += "<td>无</td>";
							}else{
								list += "<td><a target='_blank' href='"+data.litImgUrl+conlist[i].path+"'><img alt='下载附件' src='"
								+ webPath
								+ "/images/down_load8.png'/></a></td>";
							}
							
							imgUrl = conlist[i].imgUrl;
							if(imgUrl==""){
								list += "<td>无</td>";
							}else{
								list += "<td><a target='_blank' href='"
									+ "jsp/onlineReport/cutImgs.jsp?imgUrl="+conlist[i].imgUrl + "&litImgUrl="+data.litImgUrl+"'><img alt='截图' src='"
									+ webPath
									+ "/images/jiet.png'/></a></td></tr>";
							}
							
					
					}
					$("#table_security_question_id").siblings(
							".dropload-load").hide();
					$("#tbody_security_question_id").html(list);
				}
			} else {
				// 空控制当前查询周期的显示和隐藏
				$("#security_question_period_id").attr("style",
						"display:none");
				// 表格数据的显示和隐藏
				$("#table_security_question_id").hide();
				// 导出excel显示和隐藏
				$("#security_question_excel").attr("style", "display:none");
				// 显示暂无数据
				$("#table_security_question_hide").attr("style",
						"display:block");
			}
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

//excel导出
function questionDetailExcel() {
	// 服务周期id
//	var serviceId = $("#service_peroid_id").val();
//	var key = $("#prependedInput").val();
	window.location.href = webPath
			+ "/securityQuestion_questionDetailExcel.action" ;
//					"?serviceId="
//			+ serviceId + "&key=" + key;
}