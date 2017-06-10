$(function() {
	//radio人工，系统监测点击事件
	$('#manual').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		var manual = $("#manual").val();
		$("#blankDetailMonitoringPeriod").show();
		$("#scanCycleId").show();
		$("#detectionTime").hide();
		$("#bihidden").show();
		$("#systemTwo").val("1");//状态改变，1为人工检测
		
		//人工检测
		$("#hight").show();
		$("#low").hide();
		
		$("#updateVal").val("1");
		
		if(testing == 2 && manual == 1){
			// 隐藏列表数据
			$("#table_unUpdate_channel_id").hide();
			// 显示暂无数据
			var val=$('input:radio[name="mode"]:checked').val();
			if(val == 1){
				var dtphtml = $("#dtp").html();
				if(dtphtml != undefined && dtphtml.indexOf("至")>=0){
					if(datePeriod(dtphtml.substring(0,10),dtphtml.substring(11,22))){
						$("#table_response_channel_hide1").html("检测中，暂未发现问题!")
					}else{
						$("#table_response_channel_hide1").html("本次检测未发现逾期不更新栏目!")
					}
				}else{
					$("#table_response_channel_hide1").html("暂时还未开始检测!")
				}
			}else{
				$("#table_response_channel_hide1").html("昨天未发现逾期不更新栏目。")
			}
			$("#table_response_channel_hide1").show();
			//无高级版任务人工周期无
//			$("#scanCycleId").html("<option>暂无周期！</option>");
			$("#selectTwo").html("<dt>暂无周期！</dt>");
			$("#manual").val("2");
		}else{
			basicInfoList();
		}
		
	}); 
	
	$('#system').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		var manual = $("#manual").val();
		$("#blankDetailMonitoringPeriod").hide();
		$("#scanCycleId").hide();
		$("#detectionTime").show();
		$("#systemTwo").val("2");//点击后状态改变，系统检测
		
		$("#hight").hide();
		$("#low").show();
		$("#updateVal").val("1");
		var dt = $(".selectUpdate").children("dt");
		dt.html("逾期未更新");
		
		 if(testing == 2 && manual == 2){
			if(dataSize != 0){
				// 显示列表数据
				$("#table_unUpdate_channel_id").show();
				// 隐藏暂无数据
				$("#table_response_channel_hide1").hide();
				$("#manual").val("1");
			}else{
				// 隐藏列表数据
				$("#table_unUpdate_channel_id").hide();
				// 显示暂无数据
				var val=$('input:radio[name="mode"]:checked').val();
				if(val == 1){
					var dtphtml = $("#dtp").html();
					if(dtphtml != undefined && dtphtml.indexOf("至")>=0){
						if(datePeriod(dtphtml.substring(0,10),dtphtml.substring(11,22))){
							$("#table_response_channel_hide1").html("检测中，暂未发现问题!")
						}else{
							$("#table_response_channel_hide1").html("本次检测未发现逾期不更新栏目!")
						}
					}else{
						$("#table_response_channel_hide1").html("暂时还未开始检测!")
					}
				}else{
					$("#table_response_channel_hide1").html("昨天未发现逾期不更新栏目。")
				}
				$("#table_response_channel_hide1").show();
				$("#manual").val("1");
			}
		}
		 basicInfoList();
	}); 
	//加载监测周期
	essentialinformationMonitoringPeriod(); 
	
	//用来判断选中
	var modeTB = $("#modeTB").val();  //控制跳转后的单选框选中
	var isSenior = $("#isSenior").val();
	if(modeTB != null && modeTB != ''){
		isSenior = modeTB;
	}
	if(isSenior == 1){    //加载数据 判断高级和标准
		$("#blankDetailMonitoringPeriod").show();
		$("#scanCycleId").show();
		$("#systemTwo").val("1");//状态改变，1为人工检测
		$("#manual").prop("checked",true);
		
		$("#hight").show();
		$("#low").hide();
	}else if(isSenior == 2){
		$("#detectionTime").show();
		$("#detectionTimeOne").show();
		$("#systemTwo").val("2");//状态改变，2系统
		$("#system").prop("checked",true);
		
		$("#hight").hide();
		$("#low").show();
	}
	
	var updateVal = $("#updateVal").val();
	basicInfoList();
});
var scanDateSystem = 1;
//服务周期
function essentialinformationMonitoringPeriod(){
	var servicePdIdZZ = $('#typesDate').val();
	var siteCode = $("#siteCode").val();
	$.ajax( {
		type : "POST",
		url : webPath+"/upChannel_essentialinformationMonitoringPeriod.action",
		data:{
			siteCode:siteCode
		},
		dataType:"json",
		async : false,
		success : function(data) {
			$("#isSenior").val(data.isSenior);
			if(data.size == 0){
				$("#selectTwo").html("<dt>暂无周期！</dt>");
			} else{
				var datenow = "";
				$("#ddb").append("<ul></ul>")
				for(var i=0;i<data.scanCycleList.length;i++){
					if(i==0){
							$("#typesDate").val(data.scanCycleList[0]["id"]);
							$("#dtp").html(data.scanCycleList[0]["cycleDate"]);
							$("#ddb ul").append(" <li value="+data.scanCycleList[0]["id"]+">" + data.scanCycleList[0]["cycleDate"] + " </li>");
					}else{
						$("#ddb ul").append(" <li value="+data.scanCycleList[i]["id"]+">" + data.scanCycleList[i]["cycleDate"] + " </li>");
					}
					if(servicePdIdZZ != null && servicePdIdZZ !=''){
						  $('#typesDate').val(servicePdIdZZ);
						 var id = data.scanCycleList[i]["id"];
						 if(id == servicePdIdZZ ){
							 	$("#dtp").html(data.scanCycleList[i]["cycleDate"]);
						 }
					}
				}
			}
		},
		error:function(data){
			if(data.success == 'false'){
//				alert(data.errorMsg);
			}
		}
	});
}
//人工检测，系统监测页面判断
var testing = null;
//用来显示隐藏列表
var dataSize=null;
//列表查询
function basicInfoList() {
	var key = $("#keyInput").val();//模糊查询参数
	var type = $("#types").val();//栏目类别   0全部   1动态、要闻类    2通知公告、政策文件   3人事、规划
//	var servicePdId = $("#scanCycleId option:selected").val();//当前合同下周期
	var servicePdId = $("#typesDate").val();//当前合同下周期
	var siteCode = $("#siteCode").val();
	var systemTwo = $("#systemTwo").val();//用来判断系统检测
	var updateVal = $("#updateVal").val(); //判断  全部0  逾期未更新1 正常更新2
	$.ajax({
		type : "POST", url : webPath + "/upChannel_updateChannelPage.action", dataType : "json", async : false, data : {
			key : key, siteCode : siteCode, type : type, servicePdId : servicePdId, systemTwo : systemTwo,updateVal : updateVal
		}, success : function(data) {
			testing = data.testing;
			dataSize = data.listSize;
			scanDateSystem = data.scanDateSystem;
			$("#scanDateSystem").val(scanDateSystem);
			if(scanDateSystem == 2){
				$("#bihidden").hide();
			}
			var conlist = data.body;
			var list = "";
			// 清空表数据
			$("#tbody_unUpdate_channel_id").html("");
			if (data.listSize != 0) {
				// 显示列表数据
				$("#table_unUpdate_channel_id").show();
				// 隐藏暂无数据
				$("#table_response_channel_hide1").hide();
				$(".dropload-load").show();
				// 显示第几周期
				//					$("#basic_channel_period_id").removeClass("hide");
				// 显示导出excel
				$("#unupdate_excel_out").show();
				var i=0;
				
					if(scanDateSystem == 2){
						if($("#timeId")){
							$("#timeId").remove();
						}
						$("#th").append("<th id='timeId' style='width:150px;style='position:relative;' onmouseover='commonscandateOver()' onmouseout='commonscandateOut()'>最后正常监测时间 </th>");
					}else{
						if($("#timeId")){
							$("#timeId").remove();
						}
					}
				
				$.each(conlist, function(index, obj) {
					var siteUrl = checkUrlHTTTP(obj.url);
					list += "<tr><td class='td_center font_701999'>" + (i+=1) + "</td>";
					if(obj.channel_name.length > 6){
						list += "<td class='td_left'><div title='"+obj.channel_name+"'>"+obj.channel_name.substring(0,6) + "...</div></td>"; 
					}else{
						list += "<td class='td_left'>"+obj.channel_name + "</td>"; 
					}
					
					list += "<td class='td_left'>" + obj.dicChannelId + "</td>";
					list += "<td class='td_left'>" + obj.problemTypeId +"</td>"; 
					list += "<td class='td_left'><a target='_blank' class='wz-name ellipsis-w1' title='" + siteUrl + "' href='" + siteUrl + "'>" + subStr(siteUrl, 20) + "</a></td>";
					if(scanDateSystem != 2){
						list+= "<td class='td_left'>" + obj.scanDate + "</td>";
					}
					list += "<td class='td_left'>" + obj.modify_date + "</td>";
					if(obj.problemTypeId.indexOf("正常")> -1){
						list += "<td class='td_center'><span>" + obj.unUpdateDays + "</span></td>";
					}else{
						list += "<td class='td_center'><span style='color:red'>" + obj.unUpdateDays + "</span></td>";
					}
					
					if (obj.imgUrl == '') {
						list += "<td>无</td>";
					} else {
						if (obj.imgUrl.match("htm")) {
							list += "<td><i class='kuaiz' onclick='getShot(\"" + obj.imgUrl + "\")'></i></td>";
						} else {
// list += "<td><a target='_blank' href='" + obj.imgUrl + "'><img alt='截图'
// src='" + webPath + "/images/jiet.png'/></a></td>";
							list += "<td><a target='_blank' href='"
								+ "jsp/onlineReport/cutImgs.jsp?imgUrl="+obj.imgUrl + "&litImgUrl="+data.litImgUrl+"'><img alt='截图' src='"
								+ webPath
								+ "/images/jiet.png'/></a></td>";
						}
					}
					
					// 连通性弹框：参数siteCode,最后正常监测日期（备用），扫描时间（传null默认昨天），url,type(2:代表获取栏目连通性数据
					// 1：首页连通性数据)
				if(scanDateSystem == 2){
					list += "<td><a href='javascript:void(0)'onclick=showConnectionDialog('"+obj.siteCode+"','"+obj.lastAccessDate+"',"+null+",'"+obj.url+"','"+2+"')"+">"+obj.lastAccessDate+"</a></td>";
				}
					list += "</tr>";
				});
				$("#table_unUpdate_channel_id").siblings(".dropload-load").hide();
				$("#tbody_unUpdate_channel_id").append(list);
				$(".tab_box1 table tr:odd td").css("background","#fafbfc");//渲染表格，隔行花色
			} else {
				// 隐藏列表数据
				$("#table_unUpdate_channel_id").hide();
				// 显示暂无数据
				 var val=$('input:radio[name="mode"]:checked').val();
				if(val == 1){
					
					var dtphtml = $("#dtp").html();
					if(dtphtml != undefined && dtphtml.indexOf("至")>=0){
						if(datePeriod(dtphtml.substring(0,10),dtphtml.substring(11,22))){
							$("#table_response_channel_hide1").html("检测中，暂未发现问题!")
						}else{
							$("#table_response_channel_hide1").html("本次检测未发现逾期不更新栏目!")
						}
					}else{
						$("#table_response_channel_hide1").html("暂时还未开始检测!")
					}
				}else{
					if(updateVal == 0){
						$("#table_response_channel_hide1").html("暂无数据。") //查询全部无数据
					}else if(updateVal == 2){ //正常更新无数据的文案提示
						$("#table_response_channel_hide1").html("暂无正常更新栏目。")
					}else{
						$("#table_response_channel_hide1").html("昨天未发现逾期不更新栏目。")
					}
				}
				$("#table_response_channel_hide1").show();
				$(".dropload-load").hide();
				// 隐藏第几周期
				//					$("#basic_channel_period_id").addClass("hide");
				// 隐藏导出excel
				$("#unupdate_excel_out").hide();
			}

		}, error : function(data) {
		// alert(data.errorMsg);
		}
	});
}
//快照页面
function getShot(imgUrl) {
	window.open(imgUrl);
}
function subStr(str, fontSize) {
	if (str.length > fontSize) {
		return str.substring(0, fontSize) + '...';
	} else {
		return str;
	}
}
//当前时间是否在时间段内
function datePeriod(startDate,endDate){
	var date = new Date().pattern("yyyy-MM-dd")
	if(startDate <= date && date <= endDate){
		return true;
	}else{
		return false;
	}
}
