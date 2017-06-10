var type;
/**
 * tab切换
 * @param tabType
 */
function keyColumnClick(tabType){
	if(tabType == 0){
		$('.every_tip').removeClass('on');
		$("#tab0").addClass('on');
		$("#table-webSort").html("");
		$("#table-Sum").html("");
		$("#keyColumnTbody").html("");
		$("#table-webSort").append(keyColumn);
		var yesterday = $("#yesterdayId").val();
		$("#startDate").val(yesterday);
		$("#endDate").val(yesterday);
		$("#siteTypeText").html("网站类别");
		$("#siteTypeVal").val(0);
		$("#aaaTypeId").show();
		$("#aaaTypeText").html("检测频率");
		$("#aaaTypeVal").val(0);
		type = 3;
		keyColumnTable(type);
	}else if(tabType == 1){
		$('.every_tip').removeClass('on');
		$("#tab1").addClass('on');
		$("#table-webSort").html("");
		$("#table-Sum").html("");
		$("#keyColumnTbody").html("");
		$("#table-webSort").append(businessColumn);
		var yesterday = $("#yesterdayId").val();
		$("#startDate").val(yesterday);
		$("#endDate").val(yesterday);
		$("#siteTypeText").html("网站类别");
		$("#siteTypeVal").val(0);
		$("#aaaTypeId").show();
		$("#aaaTypeText").html("检测频率");
		$("#aaaTypeVal").val(0);
		type = 2;
		keyColumnTable(type);
	}else{
		$('.every_tip').removeClass('on');
		$("#tab2").addClass('on');
		$("#table-webSort").html("");
		$("#table-Sum").html("");
		$("#keyColumnTbody").html("");
		$("#aaaTypeId").hide();
		$("#table-webSort").append(updateColumn);
		var yesterday = $("#yesterdayId").val();
		$("#startDate").val(yesterday);
		$("#endDate").val(yesterday);
		$("#siteTypeText").html("网站类别");
		$("#siteTypeVal").val(0);
		$("#aaaTypeId").hide();
		type = 4;
		keyColumnTable(type);
	}
	/**
	 * 排序样式
	 */
	$("#table-webSort th").on('click', function(event){
		if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
			$("#table-web .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_top");
			$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		}else{
			$("#table-web .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_bottom");
			$(this).find(".tab_angle").removeClass("tab_angle_top");
		}
	  });
}

$(function () {
	
	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
		 if(searchInfo==""){
			 $("#keyColumnTbody tr").show();
		 }else{
			 $("#keyColumnTbody").find(".cor-01a5ec").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().parent().find(".cor-01a5ec").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });

$("#startDate").datepicker({
	inline : true,
	showOn: "both",
    buttonImage: webPath+"/images/date.png",
	buttonImageOnly: true,
	numberOfMonths : 1,// 显示几个月
	showButtonPanel : true,// 是否显示按钮面板
	dateFormat : 'yy-mm-dd',// 日期格式
	clearText : "清除",// 清除日期的按钮名称
	closeText : "关闭",// 关闭选择框的按钮名称
	yearSuffix : '年', // 年的后缀
	showMonthAfterYear : true,// 是否把月放在年的后面
	defaultDate : $("#yesterdayId").val(),// 默认日期
	minDate : GetDateStr(-90),//最小日期
	maxDate : GetDateStr(-1),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		keyColumnTable(type);
	}
}); 

$("#endDate").datepicker({
	inline : true,
	showOn: "both",
    buttonImage: webPath+"/images/date.png",
	buttonImageOnly: true,
	numberOfMonths : 1,// 显示几个月
	showButtonPanel : true,// 是否显示按钮面板
	dateFormat : 'yy-mm-dd',// 日期格式
	clearText : "清除",// 清除日期的按钮名称
	closeText : "关闭",// 关闭选择框的按钮名称
	yearSuffix : '年', // 年的后缀
	showMonthAfterYear : true,// 是否把月放在年的后面
	defaultDate : $("#yesterdayId").val(),// 默认日期
	minDate : GetDateStr(-90),//最小日期
	maxDate : GetDateStr(-1),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		keyColumnTable(type);
	}
});

var yesterday = $("#yesterdayId").val();
$("#startDate").val(yesterday);
$("#endDate").val(yesterday);

var colId = $("#colId").val();
if(colId == "col"){
	keyColumnClick(0);
}else{
	keyColumnClick(colId);
}


});

/**
 * ul
 * @param id
 */
function selectShow(id){
    $("#"+id+"Ul li").click(function(){
        $("#"+id+"Val").val(this.value);
        $("#"+id+"Text").html($(this).html());
        $("#"+id+"Ul").hide();
        keyColumnTable(type);
    });
    
}

var keyColumn = "";   // 关键栏目
var businessColumn = "";   // 业务栏目
var updateColumn = "";   // 栏目更新

keyColumn += "<thead><tr>";
keyColumn += "<th class='w15p first-row' style='width:15%; padding-left:26px;'>网站名称</th>";
keyColumn += "<th class='w15p th-center' style='width:15%;text-align:center;'>关键栏目数量</th>";
keyColumn += "<th id='thIa' class='w10p th-center jiancepl' style='width:10%; text-align: center; position:relative;'>监测频率" +
				"<div id='divIa' style='display:none; position:absolute;width:272px;__height: 38px;padding:5px 10px 5px 10px;font:normal 12px/38px Microsoft Yahei;color: #6a6f7b;background: #fff;border: 1px solid #c9c9c9;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;z-index: 5;'>"+
				"<p style='margin:0;padding:0;line-height:16px;text-align:left;'>重点门户网站为5分钟监测一次；</p>"+
				"<p style='margin:0;padding:0;line-height:16px;text-align:left;'>其他付费网站为15分钟监测一次；</p>"+
				"<p style='margin:0;padding:0;line-height:16px;text-align:left;'>长期连不通或监测异常状态的网站为1天监测一次。</p>"+
				"</div></th>";



keyColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连接总次数</th>";
keyColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连通次数<i class='tab_angle'></i></th>";
keyColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连通率<i class='tab_angle'></i></th>";
keyColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连不通次数<i class='tab_angle'></i></th>";
keyColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>不连通率<i class='tab_angle'></i></th>";
keyColumn += "</tr></thead>";

businessColumn += "<thead><tr>";
businessColumn += "<th class='w15p first-row' style='width:15%; padding-left:26px;'>网站名称</th>";
businessColumn += "<th class='w15p th-center' style='width:15%;text-align:center;'>业务系统数量</th>";
businessColumn += "<th id='thIb' class='w10p th-center jiancepl' style='width:10%; text-align: center; position:relative;'>监测频率" +
					"<div id='divIb' style='display:none; position:absolute;width:272px;__height: 38px;padding:5px 10px 5px 10px;font:normal 12px/38px Microsoft Yahei;color: #6a6f7b;background: #fff;border: 1px solid #c9c9c9;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;z-index: 5;'>"+
					"<p style='margin:0;padding:0;line-height:16px;text-align:left;'>重点门户网站为5分钟监测一次；</p>"+
					"<p style='margin:0;padding:0;line-height:16px;text-align:left;'>其他付费网站为15分钟监测一次；</p>"+
					"<p style='margin:0;padding:0;line-height:16px;text-align:left;'>长期连不通或监测异常状态的网站为1天监测一次。</p>"+
					"</div></th>";
businessColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连接总次数</th>";
businessColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连通次数<i class='tab_angle'></i></th>";
businessColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连通率<i class='tab_angle'></i></th>";
businessColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>连不通次数<i class='tab_angle'></i></th>";
businessColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>不连通率<i class='tab_angle'></i></th>";
businessColumn += "</tr></thead>";

updateColumn += "<thead class='spe-head'><tr>";
updateColumn += "<th class='w15p first-row' style='width:13%; padding-left:26px;'>网站名称</th>";
updateColumn += "<th class='w10p th-center' style='width:10%;text-align:center;'>检测栏目</th>";
updateColumn += "<th class='w10p th-left' style='width:10%;'>主办单位</th>";
updateColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>动态要闻类<i class='tab_angle'></i></th>";
updateColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>人事规划类<i class='tab_angle'></i></th>";
updateColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>通知公告/<br />政策文件类<i class='tab_angle'></i></th>";
updateColumn += "<th class='w10p th-center' style='width:10%;text-align: center;'>所有栏目<br />更新条数<i class='tab_angle'></i></th>";
updateColumn += "</tr></thead>";

function keyColumnTable(type){
	$("#keyColumnTbody").html(copyInformation(2,null));
	var siteType = $("#siteTypeVal").val();
	var aaaType = $("#aaaTypeVal").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
		//关键栏目云弹框显示
		$("#thIa").hover(function (){  
	        $("#divIa").show();  
	    },function (){  
	        $("#divIa").hide();  
	    }); 
	
		//业务系统云弹框显示
		$("#thIb").hover(function (){  
	        $("#divIb").show();  
	    },function (){  
	        $("#divIb").hide();  
	    }); 
	
	if(startDate > endDate){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}else{
   		if(type != 4){
   	 	 $.ajax( {  
   	         type : "POST",  
   	         url : webPath+"/connectionHomeDetail_connectionAllHomeTable.action",  
   	         data : {
   	        	 siteType:siteType,
   	        	 monitoringId:aaaType,
   	        	 startDate:startDate,
   	        	 endDate:endDate,
   	        	 type:type
   	         },  
   	         dataType:"json",
   	         async : false,  
   	         success : function(data) {
   	        	 if(data.success == 'true'){
   	        		 $("#keyColumnTbody").html("");
   	        		 $("#table-Sum").html("");
   	        		 $("#sizeId").html("");
   	        		 var html = '';
   	        		 var sumHtml = '';
   	        		 var list = data.body;
   	        		 if(list.length > 0 && list != null){
   	        			 $("#keyColumnloadingHide").hide();
   	        			 $("#keyColumnHide").hide();
   	        			 
   	        			 $("#sizeId").html(data.size);
   	        			 sumHtml+='<tr >';
   	        			 sumHtml+='<td class="summary-td padl-18 w15p">汇总<i class="summary-icon"></i></td>';
   	        			 sumHtml+='<td class="td-center w15p"><i class="td_num td_num_57a1f2">'+data.columnNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_b233b9">'+data.allConNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_51d466">'+data.allConnectedNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_4e4e4e">'+data.allSuccessThan+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_607d8b">'+data.allNoConnectedNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_ef6333">'+data.allErrorThan+'</i></td>';
   	        			 sumHtml+=' </tr>';
   	        			 
   	        			 $("#table-Sum").append(sumHtml);
   	        			 
   	        			 $.each(list, function(index, obj) {
   	        				 var url = obj.url;
   	        				 if(url != ""){
   	        					 if(!url.match("http")){
   	     	        				 url="http://"+url;
   	     	        			 }
   	        				 }
//   	        				connectionBusinessDetail_connectivity.action?siteCode=4400000001&types=1&columnTB=1
   	        				 html+='<tr>';
   	        				 if(type == 3){  //关键
   	   	        				 html+='<td class="td-left first-row wz-name w15p"><a  href="'+url+'" target="_blank" class="sort-num cor-01a5ec">'+obj.siteName+'</a><br><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&columnTB=guanjian" target="_blank" class="cor-01a5ec">'+obj.siteCode+'</a></td>';
   	        				 }else if(type == 2){ // 业务
   	   	        				 html+='<td class="td-left first-row wz-name w15p"><a  href="'+url+'" target="_blank" class="sort-num cor-01a5ec">'+obj.siteName+'</a><br><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&columnTB=yewu" target="_blank" class="cor-01a5ec">'+obj.siteCode+'</a></td>';
   	        				 }
   	        				 html+='<td class="td-center w15p">'+obj.countSum+'</td>';
   	        				 if(obj.countSum == 0){
   	        					 html+='<td class="td-center w10p">-</td>';
   	   	        				 html+='<td class="td-center sort-num w10p">-</td>';
   	   	        				 html+='<td class="td-center sort-num w10p">-</td>';
   	   	        				 html+='<td class="td-center sort-num w10p"><span class="sort-num">-</span></td>';
   	   	        				 html+='<td class="td-center sort-num w10p">-</td>';
   	   	        				 html+='<td class="td-center sort-num w10p"><span class="sort-num">-</span></td>';
   	        				 }else{
   	        					 html+='<td class="td-center w10p">'+obj.quName+'</td>';
   	   	        				 html+='<td class="td-center sort-num w10p">'+obj.connectionSum+'</td>';
   	   	        				 html+='<td class="td-center sort-num w10p">'+obj.successNum+'</td>';
   	   	        				 html+='<td class="td-center sort-num w10p"><span class="sort-num">'+obj.successThan+'</span>%</td>';
   	   	        				 html+='<td class="td-center sort-num w10p">'+obj.errorNum+'</td>';
   	   	        				 html+='<td class="td-center sort-num w10p"><span class="sort-num">'+obj.errorThan+'</span>%</td>';
   	        				 }
   	        				
   	        				 html+='</tr>';                 
   	        			 });
   	        			 $("#keyColumnTbody").append(html);
   	        		 }else{
   	        			var text = copyInformation(1,"未发现问题");
   	        			$("#keyColumnTbody").append(text);
   	        		 }
   	        	 }
   	         },
   	         error:function(data){
   	 			alert(data.errorMsg);
   	 		 }
   	        });
   	 	 
   	 	 //列表排序
   		 new TableSorter("table-web",4,5,6,7);
   		 
   		}else{
   		 $.ajax( {  
   	         type : "POST",  
   	         url : webPath+"/updateChannelDetail_ProgramUpdate.action",  
   	         data : {
   	        	 siteType:siteType,
   	        	 monitoringId:aaaType,
   	        	 startDate:startDate,
   	        	 endDate:endDate
   	         },  
   	         dataType:"json",
   	         async : false,  
   	         success : function(data) {
   	        	 if(data.success == 'true'){
   	        		 $("#keyColumnTbody").html("");
   	        		 $("#table-Sum").html("");
   	        		 $("#sizeId").html("");
   	        		 var html = '';
   	        		 var sumHtml = '';
   	        		 var list = data.body;
   	        		 if(list.length > 0 && list != null){
   	        			 $("#keyColumnloadingHide").hide();
   	        			 $("#keyColumnHide").hide();
   	        			 
   	        			 $("#sizeId").html(data.size);
   	        			 sumHtml+='<tr >';
   	        			 sumHtml+='<td class="summary-td padl-18 w15p" style="width:13%;">汇总<i class="summary-icon"></i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_b233b9">'+data.chaNum+'</i></td>';
   	        			 sumHtml+='<td class="td-left w10p"></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_51d466">'+data.dcNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_4e4e4e">'+data.plNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_607d8b">'+data.noNum+'</i></td>';
   	        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_ef6333">'+data.cNum+'</i></td>';
   	        			 sumHtml+=' </tr>';
   	        			 $("#table-Sum").append(sumHtml);
   	        			 
   	        			 $.each(list, function(index, obj) {
   	        				 var url = obj.url;
   	        				 if(url != ""){
   	        					 if(!url.match("http")){
   	     	        				 url="http://"+url;
   	     	        			 }
   	        				 }
   	        				
   	        				 html+='<tr>';
   	        				 html+='<td class="td-left first-row wz-name w15p" style="width:13%;"><a  href="'+url+'" target="_blank" class="sort-num cor-01a5ec">'+obj.siteName+'</a><br><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&columnTB=1" target="_blank" class="cor-01a5ec">'+obj.siteCode+'</a></td>';
   	        				 html+='<td class="td-center w10p">'+obj.channelCount+'</td>';
   	        				 html+='<td class="td-left w10p">'+obj.director+'</td>';
   	        				 html+='<td class="td-center sort-num w10p"><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&types=1&columnTB=1" target="_blank" class="sort-num">'+obj.dynamicNum+'</a></td>';
   	        				 html+='<td class="td-center sort-num w10p"><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&types=3&columnTB=1" target="_blank" class="sort-num">'+obj.planningNum+'</a></td>';
   	        				 html+='<td class="td-center sort-num w10p"><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&types=2&columnTB=1" target="_blank" class="sort-num">'+obj.noticeNum+'</a></td>';
   	        				 html+='<td class="td-center sort-num w10p"><a onclick="jumpConnectivity()" href="'+webPath+'/connectionBusinessDetail_connectivity.action?siteCode='+obj.siteCode+'&columnTB=1" target="_blank" class="sort-num">'+obj.size+'</a></td>';
   	        				 html+='</tr>';                 
   	        			 });
   	        			 $("#keyColumnTbody").append(html);
   	        		 }else{
   	        			var text = copyInformation(1,"未发现问题");
   	        			$("#keyColumnTbody").append(text);
   	        		 }
   	        	 }
   	         },
   	         error:function(data){
   	 			alert(data.errorMsg);
   	 		 }
   	        });
   		 
   		 //列表排序
   		 new TableSorter("table-web",3,4,5,6);
   		}
   	}

}
function jumpConnectivity(){
	 uri = webPath + "/fillUnit_gailan.action";
	 url = webPath + "/connectionBusinessDetail_connectivity.action";
	 changeCookie(2,uri,null,url);
}
function keyColumnExcel(){
	var siteType = $("#siteTypeVal").val();
	var aaaType = $("#aaaTypeVal").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(startDate > endDate){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}else{
   		if(type != 4){
   			window.location.href = webPath + "/connectionChannelDetail_columnConnectionTableExcel.action?siteType=" 
				+ siteType + "&startDate=" + startDate + "&endDate=" + endDate + "&monitoringId=" + aaaType
				+ "&type=" + type ;
   		}else{
   			window.location.href = webPath + "/updateChannelDetail_ProgramUpdateExcel.action?siteType=" 
				+ siteType + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type ;
   		}
   	}
}
