var linkAllTitle = [];// 全站链通可用性
linkAllTitle.push({
	"mDataProp" : "dataNumber", "bSortable" : false, "sWidth" : "40px", "sTitle" : "序号", "sClass" : "center", "bVisible" : true, "tabIndex" : -9
});
linkAllTitle.push({
	"mDataProp" : "scanTime", "sWidth" : "126px", "sTitle" : "监测时刻", "bSortable" : false, "sClass" : "text-left", "bVisible" : true, "tabIndex" : -8
});
// linkAllTitle.push({"mDataProp": "url","sWidth":"130px","sTitle":
// "不可用链接URL","bSortable": false, "sClass": "text-left", "bVisible": true,
// "tabIndex": -7});
linkAllTitle.push({
	"mDataProp" : "scanLeval", "sWidth" : "68px", "sTitle" : "层次", "bSortable" : true, "sClass" : "center", "bVisible" : true, "tabIndex" : -6
});
linkAllTitle.push({
	"mDataProp" : "linkTitle", "sWidth" : "150px", "sTitle" : "链接标题", "bSortable" : false, "sClass" : "text-left", "bVisible" : true, "tabIndex" : -5
});
linkAllTitle.push({
	"mDataProp" : "parentUrl", "sWidth" : "100px", "sTitle" : "父页面URl", "bSortable" : false, "sClass" : "text-left", "bVisible" : true, "tabIndex" : -4
});
linkAllTitle.push({
	"mDataProp" : "parentTitle", "sWidth" : "100px", "sTitle" : "父页面标题", "bSortable" : false, "sClass" : "text-left", "bVisible" : true, "tabIndex" : -4
});
linkAllTitle.push({
	"mDataProp" : "resourceDes", "sWidth" : "100px", "sTitle" : "资源描述", "bSortable" : false, "sClass" : "text-left", "bVisible" : true, "tabIndex" : -3
});
linkAllTitle.push({
	"mDataProp" : "errorDes", "sWidth" : "120px", "sTitle" : "<span class='pull-left'>错误描述</span><div class='http-html'></div>", "bSortable" : false, "sClass" : "text-left", "bVisible" : true, "tabIndex" : -2
});
linkAllTitle.push({
	"mDataProp" : "imgUrl", "sWidth" : "60px", "sTitle" : "定位", "bSortable" : false, "sClass" : "center", "bVisible" : true, "tabIndex" : -1
});

var table_link_all_date;// 全站链接可用性
var periodNum = '';
$(function() {
	var iscostId = $("#iscostId").val();
	linkAllScanCycle();
	if(iscostId == 1){ //  收费
		getTablesData();
	}
	$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
});
/**
 * @param object
 *            渲染表格Jquery对象
 * @param url
 *            获取数据url
 * @param aoColumns
 *            表头信息
 */
var table_link_all_id; //全站链接可用性
function getTablesData() {
	$("#table_link_all_div").show();
	$("#table_link_all").show();
	
	var terms = $("#link_all_table_key").val();
	var servicePdId = $("#servicePeriodIdZZ").val();//周期参数
	
	var stationInsOut = $("#stationInsOut").val();//站内外
	var chainSelectId = $("#chainSelectId").val();//确认，疑似死链
	$.ajax({
		type : "POST",
		url : webPath + "/linkAll_linkAllPage.action",
		dataType : "json",
		async : false,
		data:{
			terms:terms,
			servicePdId:servicePdId,
			stationInsOut:stationInsOut,
			chainSelectId:chainSelectId,
		},
		success : function(data) {
			//$("#linkallCount").html(data.size);
			$("#conutNotAvailable").html("已发现0个不可用链接");
			var conlist = data.body;
			var htmlList = "";
			// 清空表数据
			$("#table_link_all_tbody").html("");
			if(servicePdId == ''){
				$("#table_link_all_hide").html("暂未监测");
			}else{
				$("#table_link_all_hide").html("未发现问题");
			}
			
				if (data.size!=0) {
					//下载查看其他不可用链接
					if(data.size >= 500 ){
						$("#excelMore").show();
					}else{
						$("#excelMore").hide();
					}
					$("#conutNotAvailable").html("已发现<span>"+data.size+"</span>个不可用链接");
					
					// 显示列表数据
					$("#table_link_all").show();
					// 隐藏暂无数据
					$("#table_link_all_hide").hide();
					var confirmSuspected ="";
					$.each(conlist, function(index, obj) {
						htmlList+="<tr>";
						htmlList+='<td style="width:40px; text-align: center;">'+(index+1)+'</td>';//序号
						if(obj.url.length > 20){
							htmlList+='<td style="width:126px; text-align: left;"> <a class="url-ellipsis" href='+obj.url+' target="_blank" title='+obj.url+'>'+obj.url.substring(0,20)+'...</a></td>';//链接Url
						}else{
							htmlList+='<td style="width:126px; text-align: left;"> <a class="url-ellipsis" href='+obj.url+' target="_blank" title='+obj.url+'>'+obj.url+'</a></td>';//链接Url
						}
						
//						htmlList+='<td style="width:68px; text-align: center;">'+obj.scanLeval+'</td>';//层次
		        		//链接标题
		        		if(obj.linkTitle !=null ){
		        			htmlList+= '<td class="td_left" style="width:150px;"><div class="wz-name" title="'+obj.linkTitle+'">' +(obj.linkTitle.substring(0,10))+'</div></td>';
		        		}
		        		//父页面url
		        		htmlList+= "<td class='td_left'><a class='url-ellipsis' href='"+obj.parentUrl+"' target='_blank' title='"+obj.parentUrl+"'>" +(obj.parentUrl.substring(0,40))+"</a></td>";
						//父页面标题
		        		if(obj.parentTitle.length>10){
			        		htmlList+='<td class="td_left" style="width:100px;" title="'+obj.parentTitle+'">'+(obj.parentTitle.substring(0,10))+'..'+'</td>';
		        		}else{
			        		htmlList+='<td class="td_left" style="width:100px;">'+obj.parentTitle.substring(0,10)+'</td>';
		        		}
						//资源类型
//		        		htmlList+='<td class="td_center" style="width:100px;">'+obj.resourceDes+'</td>';
		        		//问题描述
//		        		if(obj.errorDes !=null ){
//		        			htmlList+= '<td style="width:120px;"><div class="wz-name"  style="text-align:center;" title="'+obj.errorDes+'">' +(obj.errorDes.substring(0,20))+'</div><td>'
//		        			htmlList+= '<td style="width:120px;"><div class="wz-name"  style="text-align:center;" title="'+obj.errorCodeBomb+'">' +obj.errorCodeBomb+'</div>'
//		        			+'<div>'+'不可用链接类型：'+obj.resourceDes+'</div><div>'+'问题类型：'+obj.errorDescribeBomb+'</div><div>'+'扫描层次：'+obj.scanLeval+'</div>'+'</td>';
//		        		}
//		        		点击放大镜弹框js拼接	
		        		if(obj.errorCodeBomb == 403 || obj.errorCodeBomb == 404){
		        			confirmSuspected = " 确定不可用链接";
		        		}else{
		        			confirmSuspected = " 疑似不可用链接";
		        		}
		        			htmlList+='<td style="width:120px;text-align:left;">' +obj.errorCodeBomb+'<div class="magnify-content"><div class="info-detail" id ="'+index+'Detail">'//弹框开始
		        			+'<div class="clearfix info-head"><h4 class="fl">'+'详情'+'</h4><i class=" fr close-icon" onmouseover="changIconC()" onmouseout="changIcon()" onclick="detailHidden('+index+')"></i></div>'
		        			+'<div class="info-body"><div class="clearfix"><h4 class="fl">'+'不可用链接类型：'+'</h4> <span class="fl">'+obj.resourceDes+'</span><span class="fl">'+confirmSuspected+'</span></div>'
		        			+'<div class="clearfix"><h4 class="fl">'+'问题类型：'+'</h4><span class="fl">'+obj.errorCodeBomb+'</span></div>'
		        			+'<div class="clearfix"><h4 class="fl">'+'扫描层次：'+'</h4> <span class="fl">'+obj.scanLeval+'</span></div>'
		        			+'<div class="clearfix"><h4 class="fl">'+'监测时刻：'+'</h4> <span class="fl">'+obj.scanTime+'</span></div></div></div>'
		        			+'<div class="magnify-icon"  onclick="detailShow('+index+')"><i></i></div>'
		        			+'</div></td>';
		        			
		        		//定位
		          		 if(obj.imgUrl !=""){
		          			 if(obj.imgUrl.match("htm")){
		          				htmlList+='<td style="width:60px; text-align:center"><i class="kuaiz" onclick="getShot(\'' + obj.imgUrl + '\')"></i></td>';
		          			 }else{
		          				htmlList+='<td style="width:60px; text-align:center"><a target="_blank" href="'+obj.imgUrl+'"></i><img alt="截图" src="' + webPath + '/images/jiet.png"/></a></td>';	 
		          			 }
		          		 }else{
		          			htmlList+='<td style="width:60px;">无</td>';
		          		 }
		          		htmlList+='</tr>';
		          	
						});
					
					$("#table_link_all_tbody").append(htmlList);
					$(".tab_box1 table tr:odd td").css("background", "#fafbfc");
				} else {
					//下载查看其他不可用链接
					$("#excelMore").hide();
					// 隐藏列表数据
					$("#table_link_all").hide();
					// 显示暂无数据
					$("#table_link_all_hide").show();
					if(data.servicePeriodStatus == 1){
						$("#conutNotAvailable").html("已发现0个不可用链接");
						$("#table_link_all_hide").html("未发现问题");
						if(servicePdId == ''){
							$("#table_link_all_hide").html("暂未监测");
						}
					}else if(data.servicePeriodStatus == 2){
						$("#conutNotAvailable").html("未发现不可用链接");
						$("#table_link_all_hide").html("未发现问题");
						if(servicePdId == ''){
							$("#table_link_all_hide").html("暂未监测");
						}
					}else if(data.servicePeriodStatus == 0){
						$("#conutNotAvailable").html("已发现0个不可用链接");
						$("#table_link_all_hide").html("未发现问题");
						if(servicePdId == ''){
							$("#table_link_all_hide").html("暂未监测");
						}
					}
				}
		}
	});
}
//放大镜弹框展示隐藏
function detailShow(id){
	$('.info-detail').hide();
	$("#"+id+"Detail").slideDown();
}
function detailHidden(id){
	$("#"+id+"Detail").slideUp();
}
$(document).mouseup(function(e){
    var _con = $('.magnify-icon').parent().find('.info-detail'); // 设置目标区域
    if(!_con.is(e.target) && _con.has(e.target).length === 0){
        _con.slideUp();
    }
});


//快照页面
function getShot(imgUrl) {
	window.open(imgUrl);
}

//扫描周期
function linkAllScanCycle() {
	var servicePeriodIdZZ = $("#servicePeriodIdZZ").val();
	$.ajax( {
		type : "POST",
		url : webPath+"/linkAll_linkAllScanCycle.action",
		dataType:"json",
		async : false,
		success : function(data) {
			if(data.size == 0){
//				$("#scanCycleId").html("<option>暂无周期！</option>");
				$("#bbbTypeText").html("暂无周期！");
			} else{
				for(var i=0;i<data.scanCycleList.length;i++){
					if(i==0){
						$("#servicePeriodIdZZ").val(data.scanCycleList[0]["id"]);
						$("#bbbTypeText").html(data.scanCycleList[0]["cycleDate"]);
						$("#bbbTypeUl").append(" <li value="+data.scanCycleList[0]["id"]+">" + data.scanCycleList[0]["cycleDate"] + " </li>");
					}else{
						$("#bbbTypeUl").append(" <li value="+data.scanCycleList[i]["id"]+">" + data.scanCycleList[i]["cycleDate"] + " </li>");
					}
					if(servicePeriodIdZZ != null && servicePeriodIdZZ !=''){
						  $('#servicePeriodIdZZ').val(servicePeriodIdZZ);
						 var id = data.scanCycleList[i]["id"];
						 if(id == servicePeriodIdZZ ){
							 	$("#bbbTypeText").html(data.scanCycleList[i]["cycleDate"]);
						 }
					}
				}
				
			}
		},
		error:function(data){
			alert(data.errorMsg);
		}
	});
}
function linkAllScanCycleChange() {
	getTablesData();
}
function chainSelectOption(){
	getTablesData();
}
// excel导出
function linkAllExcle() {
	var servicePdId = $("#servicePeriodIdZZ").val();
	var terms = $("#link_all_table_key").val();
	var stationInsOut = $("#stationInsOut").val();//站内外
	var chainSelectId = $("#chainSelectId").val();//确认，疑似死链
	window.location.href = webPath + "/linkAll_linkAllExcel.action?servicePdId=" + servicePdId + "&terms=" + terms+"&stationInsOut="+stationInsOut+"&chainSelectId="+chainSelectId;
}

// 饼图
function linkAllSum() {
	var comPercent = [];
	var uncomPercent = [];
	var myChart_line = echarts.init(document.getElementById("link_all_pie_id"));
	$.ajax({
		type : "POST", url : webPath + "/linkAll_linkAllInfoPie.action", dataType : "json", async : false, success : function(homeData) {
			if (homeData) {// 本期监测进度存在数据的话，显示饼状图，隐藏暂无数据
				$("#link_all_pie_id").removeClass("hide");
				$("#channel_table_hide2").addClass("hide");
			} else {// 本期检测进度不存在数据，显示暂无数据，隐藏饼状图
				$("#link_all_pie_id").addClass("hide");
				$("#channel_table_hide2").removeClass("hide");
			}
			comPercent.push(homeData.comPercent);
			uncomPercent.push(homeData.uncomPercent);
		}
	});
	option = {
		color : [ '#2DCC70', '#D6DAE3' ], tooltip : {
			trigger : 'item', formatter : "{b}"
		}, calculable : true, series : [ {

			calculable : false,// 取消虚线
			name : '访问来源', type : 'pie', radius : [ '45%', '70%' ], center : [ '50%', '60%' ], data : [ {
				value : comPercent, name : "已完成 \n" + comPercent + "%"
			}, {
				value : uncomPercent, name : "未完成\n" + uncomPercent + "%"
			} ]
		} ]
	};

	myChart_line.setOption(option, true);
}

// 折线图
function linkAllSumLine() {
	var yearAndMouthDate = [];
	var unuseLinkHomeNum = [];
	var myChart_line = echarts.init(document.getElementById("link_all_line_id"));
	$.ajax({
		type : "POST", url : webPath + "/linkAll_linkAllInfoLine.action", dataType : "json", async : false, success : function(homeData) {
			var lineList = homeData.lineList;
			if (lineList) {
				for ( var j = 0; j < lineList.length; j++) {
					// mouthHomeDate.push(lineList[j].mouth+"月");
					yearAndMouthDate.push("第" + lineList[j].perNum + "期");
					unuseLinkHomeNum.push(lineList[j].websiteSum);
				}
			}
		}
	});
	option = {
		color : [ '#4CD2D1' ], dataZoom : {
			show : true, realtime : true, height : 20, start : 100, end : 40
		}, tooltip : {
			trigger : 'axis', borderColor : '#48b', axisPointer : {
				color : 'rgba(150,150,150,0.3)', width : 'auto', type : 'default'
			}, formatter : "{b}<br>全站{a}：\t{c}个", padding : 15
		}, calculable : true, xAxis : [ {
			splitLine : false, axisLine : false, axisTick : false, type : 'category', data : yearAndMouthDate
		} ], grid : {
			borderColor : '#fff'
		}, yAxis : [ {
			name : '不可用链接数  (个)\n', nameTextStyle : {
				color : 'black'
			}, axisLine : {
				lineStyle : {
					color : '#ffffff', width : 1
				}, type : 'value'
			}
		} ], series : [ {
			/*
			 * symbol:'emptyCircle', symbolSize:4,
			 */
			name : '不可用链接数', type : 'line', data : unuseLinkHomeNum

		} ]
	};
	myChart_line.setOption(option, true);
}
