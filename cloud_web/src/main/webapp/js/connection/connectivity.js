var table_data_homeConnectivity;
var homeConnectivityTitle = [];//关键栏目连通性表头
homeConnectivityTitle.push({"mDataProp": "dataNumber", "bSortable": false,"sWidth":"50px","sTitle": "序号", "sClass": "center", "bVisible": true, "tabIndex": -7});
homeConnectivityTitle.push({"mDataProp": "systemName", "sTitle": "关键栏目名称","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -6});
homeConnectivityTitle.push({"mDataProp": "url", "sWidth":"200px","sTitle": "URL", "bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -5});
homeConnectivityTitle.push({"mDataProp": "scanTime","sWidth":"200px", "sTitle": "时间","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -4});
homeConnectivityTitle.push({"mDataProp": "stateName","sWidth":"95px", "sTitle": "连通状态","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -3});
homeConnectivityTitle.push({"mDataProp": "questionDescribe", "sTitle": "<span class='pull-left'>问题描述</span><div class='http-html'></div>","bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -1});
var table_data_channelConnectivity;

var homeConnectivityTitleUpdate = [];//14-栏目信息更新
homeConnectivityTitleUpdate.push({
	"mDataProp" : "dataNumber", "sWidth" : "56px", "bSortable" : false, "sTitle" : "序号", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -8
});
homeConnectivityTitleUpdate.push({
	"mDataProp" : "lastTime", "sWidth" : "126px", "sTitle" : "更新时间", "bSortable" : true, "sClass" : "center", "bVisible" : true, "tabIndex" : -7
});
homeConnectivityTitleUpdate.push({
	"mDataProp" : "title", "sWidth" : "206px", "bSortable" : false, "sTitle" : "标题", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -6
});
homeConnectivityTitleUpdate.push({
	"mDataProp" : "channelName", "sWidth" : "156px", "bSortable" : false, "sTitle" : "栏目名称", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -5
});
homeConnectivityTitleUpdate.push({
	"mDataProp" : "dicChannelName", "sWidth" : "156px", "bSortable" : false, "sTitle" : "栏目类别", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -4
});
homeConnectivityTitleUpdate.push({
	"mDataProp" : "url", "sWidth" : "216px", "bSortable" : false, "sTitle" : "稿件URL", "sClass" : "text-left", "bVisible" : true, "tabIndex" : -3
});
homeConnectivityTitleUpdate.push({
	"mDataProp" : "imgUrl", "sWidth" : "71px", "bSortable" : false, "sTitle" : "快照", "bSortable" : false, "sClass" : "center", "bVisible" : true, "tabIndex" : -2
});
var table_up_channel_detail;
var typesId = $("#typesId").val();
var columnTB = $("#columnTB").val();
var col="0";
(function($){
	
	col=$("#col_id").val();
	//页面进来应该展示那个模块的数据   col  0-关键栏目连通性  1-业务系统连通性  2栏目更新情况
	changeTabInit();
	 $('.every_tip').click(function(){
	        var tabid = $(this).index();
	        $('.every_tip').removeClass('on');
	        $(this).addClass('on');
			$("#tabId").val(tabid);
			
			$('.every_tabs').removeClass('on');
			$('.every_tabs').eq(tabid).addClass('on');
			changeTab(tabid);
			if(tabid == 2 ){
				$(".msg").show();
			}else{
				$(".msg").hide();
			}
	  });
	
	 


//业务系统连同性 插件初始化
$("#datepickerBusiness").val($("#time_yes_dateBusiness").val());
$("#currentDatekerBusiness").val(document.getElementById("strBusiness").innerText);

$("#datepickerBusiness").datepicker({//添加日期选择功能
	inline: true,
    showOn: "both",
    buttonImage: webPath+"/images/date.png",
    buttonImageOnly: true,
	numberOfMonths:1,//显示几个月  
	showButtonPanel:true,//是否显示按钮面板  
	dateFormat: 'yy-mm-dd',//日期格式  
	clearText:"清除",//清除日期的按钮名称  
	closeText:"关闭",//关闭选择框的按钮名称  
	yearSuffix: '年', //年的后缀  
	showMonthAfterYear:true,//是否把月放在年的后面  
	defaultDate:GetDateStr(-1),//默认日期
	minDate : $("#bus_time_date_begin").attr("value"),//最小日期
	maxDate:GetDateStr(-1),//最大日期  
	monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
	dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	dayNamesMin: ['日','一','二','三','四','五','六'],
	onSelect : function(dateText, inst){		

		//统计分析
		businessStatistics(dateText);
		//饼图
		if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="false"){
			$("#business_table_pie").attr("style","display:block");
			//$("#business_bar").attr("style","height: 200px;width: 100%;display:none");
			$("#business_bar").addClass("hide");
		}else{
			$("#business_table_pie").attr("style","display:none");
			//$("#business_bar").attr("style","height: 200px;width: 100%;display:block");
			$("#business_bar").removeClass("hide");
//				businessbar();
		}
	}
});
//默认选中当前时间
$("#currentDatekerBusiness").datepicker({
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
//	defaultDate : GetDateStr(-1),// 默认日期
	minDate : GetDateStr(-30),//最小日期
	maxDate : GetDateStr(),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		
		table_data_homeConnectivity.fnClearTable();   
		table_data_homeConnectivity.fnReloadAjax(webPath
				+ "/connectionBusinessDetail_getBusinessTrendLineLAndList.action?code=3");
	}
}); 
//业务系统插件初始化 end

//关键栏目连通性 插件初始化
$("#datepickerChannel").val(GetDateStr(0));
//$("#datepickerChannel").val($("#time_yes_dateChannel").val());
$("#currentDateker").val(document.getElementById("strChannel").innerText);

$("#datepickerChannel").datepicker({//添加日期选择功能
	inline: true,
    showOn: "both",
    buttonImage: webPath+"/images/date.png",
    buttonImageOnly: true,
	numberOfMonths:1,//显示几个月  
	showButtonPanel:true,//是否显示按钮面板  
	dateFormat: 'yy-mm-dd',//日期格式  
	clearText:"清除",//清除日期的按钮名称  
	closeText:"关闭",//关闭选择框的按钮名称  
	yearSuffix: '年', //年的后缀  
	showMonthAfterYear:true,//是否把月放在年的后面  
	defaultDate:GetDateStr(0),//默认日期
	minDate : $("#channel_time_tool_mindate").attr("value"),//最小日期
	maxDate:GetDateStr(0),//最大日期  
	monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
	dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	dayNamesMin: ['日','一','二','三','四','五','六'],
	onSelect : function(dateText, inst){

		//统计分析
		channelStatistics(dateText);
	}
}); 

//默认选中当前时间
$("#currentDateker").datepicker({
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
//	defaultDate : GetDateStr(-1),// 默认日期
	minDate : GetDateStr(-30),//最小日期
	maxDate : GetDateStr(),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		//列表
		channelList();
	}
}); 

//业务通连同性------START

$(".ms-icon-wen").hover(function(){
    $(this).siblings(".ms-wen-con").fadeIn();
},function(){
    $(this).siblings(".ms-wen-con").fadeOut();
});
$(".tab_box1 table tr:odd td").css("background","#fafbfc");

$("#business_table_filter").hide();
$("#business_table_length").hide();



//饼图
if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="fasle"){
	$("#business_table_pie").attr("style","display:block");
	//$("#business_bar").attr("style","height: 200px;width: 100%;display:none");
	$("#business_bar").addClass("hide");
}else{
	$("#business_table_pie").attr("style","display:none");
	//$("#business_bar").attr("style","height: 200px;width: 100%;display:block");
	$("#business_bar").removeClass("hide");
//		businessbar();
}

/*
关键字查询
*/
$("#keyInputChannel").keyup(
	function() {
		table_data_channelConnectivity.fnReloadAjax(webPath+"/connectionBusinessDetail_queryList.action?code=2");
	});

var uri = "";
var url = "";
if(columnTB == 1){
	
	 $('#tab0').removeClass('on');
	 $('#tab2').addClass('on');
	 $('#tabs0').removeClass('on');
	 $('#tabs2').addClass('on');
	 crux();
}else if(columnTB == 'guanjian'){
	channelStatistics();

	channelLine();
//	channelConnectivityList();
	
}else if(columnTB == 'yewu'){
	
	 $('#tab0').removeClass('on');
	 $('#tab1').addClass('on');
	 $('#tabs0').removeClass('on');
	 $('#tabs1').addClass('on');
	business();

}else{
channelStatistics();

channelLine();
////列表
//channelConnectivityList();
}

})(jQuery);

//页面跳转
function openHref(){
	var uri = webPath + "/manageInfo_departmentTB.action";
	changeCookie(2,uri,null,uri);
	window.location.href = webPath + "/manageInfo_departmentTB.action";
}
function changeTab(tabid){
	if(tabid == 0){
		channelStatistics();
		////列表
		channelConnectivityList();
	}else if(tabid == 1){
		business();
	}else if(tabid == 2){
		crux();
	}
}

//业务系统连通性---    START
function business(){
	homeConnectivityList();
	businessStatistics();
}


//  业务系统连通性---    START
function homeConnectivityList() {
	table_data_homeConnectivity = $("#business_table").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 100,
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
        },
        "oLanguage": {
            "sSearch": "查询:",
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
            "sZeroRecords": "未发现问题"
        },
        "bInfo": false,
        "bFilter":false,
        "bLengthChange":false,
        "bPaginate": false,
        "bRetrieve": false,
        "bServerSide": false,
        "sAjaxSource": webPath+ "/connectionBusinessDetail_getBusinessTrendLineLAndList.action?code=3",
        "sAjaxDataProp": function (data) {
        	setTimeout(function(){
        		businessLine(data.list);
        	},100);
        	if(data.body){
        		//隐藏暂无数据
        		$("#business_table_hide2").addClass("hide");
        		//$("#business_table_pie").attr("style","display:none");
        		//显示列表数据
        		$("#business_table").removeClass("hide");
        		//导出excel显示
        		$("#out_excel_id_Business").attr("style","display:block");

        		for (var i = 0; i < data.body.length; i++) {
        			var siteUrl = checkUrlHTTTP(data.body[i]["url"]);
        			data.body[i]["url"] = "<td class='td_left'><a class='wz-name' href='"+siteUrl+"' target='_blank' title='"+siteUrl+"'>" +siteUrl+"</a></td>";
        		}
        		
        	}else{
        		//隐藏暂无数据
        		$("#business_table_hide2").addClass("hide");
        		//$("#business_table_pie").attr("style","display:none");
        		//显示列表数据
        		$("#business_table").removeClass("hide");
        		//导出excel显示
        		$("#out_excel_id_Business").attr("style","display:block");
        		//日期显示
        		
        		$("#out_excel_id_Business").attr("style","display:none");
        	}
        	
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": homeConnectivityTitle,
        "fnInitComplete": function () {
            $("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $("#business_table_length").hide();
            $("#business_table_info").hide();
            $("#business_table_filter").hide();
            var table_dataManager_wrapper = $("#table_data");
            table_dataManager_wrapper.find("th").css("cursor", "default");
        },
        "fnPreDrawCallback": function(oSettings) {
            httpHtmlF();
        }
    });
}


//excel导出
function businessExcel(){
	var scanDate = $("#currentDatekerBusiness").val();
	window.location.href=webPath+"/connectionBusinessDetail_businessExcel.action?code=3&date="+scanDate;
}

function businessStatistics(time){

	if(time==null){
		time = "";
	}
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionBusinessDetail_getBusinessStatistics.action",   
         data : "date=" + time,  
         dataType:"json",
         async : false,  
         success : function(data) {
         	 var conlist=[];
        	 var conlist = data.connBusiList;
        	 var list = "";
        	 var consum="";
        	 var listHidden="";
        	 
        	 //统计数据列表清空
        	 $("#conlistBusiness").html("");
        	 //汇总数据
        	 $("#conSum").html("");
        	 //隐藏字段
        	 $("#hidden_id").html("");
        	 
        	 if($("#bus_time_date_begin").attr("value")!=""){
        	 	$("#bus_time_date_begin").attr("value",data.siteBeginServiceDate);
        	 }
        	 
         	 if(conlist){
         	 	//显示业务统计数据表格
         	    $("#tbody_tongji_contral").removeClass("hide");
        	 	for (var int = 0; int < conlist.length; int++) {
        	 	var siteUrl = checkUrlHTTTP(conlist[int].url);
	            	  list +="<tr><td class='td_left'>"+conlist[int].name+"</td>" +
	            	  		"<td class='td_left'><div class='url-ellipsis' title='"+siteUrl+"'><a target='_blank' href='"+siteUrl+"'>"+subStr(siteUrl,29)+"</div></td>" +
	            	  		"<td>"+conlist[int].successNum+"</td>" +"<td>"+(conlist[int].successProportion)+"%</td>"+
	            	  		"<td>"+conlist[int].errorNum+"</td>"+"<td>"+(conlist[int].errorProportion)+"%</td>"+
	            	  		"<td>"+(conlist[int].successNum+conlist[int].errorNum)+"</td></tr>";
	             }
                $("#business_table").siblings(".dropload-load").hide();
             	$("#conlistBusiness").append(list);
        	 }else{ 
        		//隐藏业务统计数据表格
				$("#tbody_tongji_contral").addClass("hide");
        	 }
        	 
            listHidden+="<input type='hidden' id='successProportion_bar' value='"+data.successProportion+"' />" +
              		"<input type='hidden' id='errorProportion_bar' value='"+data.errorProportion+"' />";
        	        	
        	$("#hidden_id").append(listHidden);
              if(conlist.length>0){
	              $("#business_table_hide").removeClass("hide");
	              $("#business_table_hide1").addClass("hide");
	              consum+="<td colspan='2' class='t-td-font1'>汇&nbsp;&nbsp;&nbsp;&nbsp;总</td>"+"<td>"+(parseInt(data.successNum))+"</td>";
	              if(data.successProportion!=null){
	              consum+="<td>"+(data.successProportion)+"%</td>";
	              }else{
	               consum+="<td>"+0+"%</td>";
	              }
	              consum+="<td class='bg-e65e5e'>"+(parseInt(data.errorNum))+"</td>";
	              if(data.errorProportion!=null){
	              consum+="<td class='bg-e65e5e'>"+(data.errorProportion)+"%</td>";
	              }else{
	              consum+="<td>"+0+"%</td>";
	              }
	              consum+="<td>"+(parseInt(data.successNum)+parseInt(data.errorNum))+"</td>";
	              }else{
	               $("#business_table_hide").addClass("hide");
	              $("#business_table_hide1").removeClass("hide");
	              }
	              $("#conSum").append(consum);
	              //$("#datepicker").val(data.date);
	              //$("#yesterdayStr_2").text(data.yesterdayStr);
	              //$("#yesterdayStr_1").text(data.date);
	              $("#dateStr").text(data.dateStr);
	              //$("#dateText").val(data.date);
              
              
         	}
        });
}
function businessbar(){
  		//饼图
		//成功占比
		var successProportion = $("#successProportion_bar").val();
		//不连通占比
		var errorProportion = $("#errorProportion_bar").val();
		if(successProportion!=null && errorProportion!=null){
			
		}

        var myChart_bar = echarts.init(document.getElementById('business_bar'));
            option = {
                    color:
                    ['#d5d9e2','#2dcc70']
                    ,
            	    tooltip : {
            	        trigger: 'item',
            	        formatter: "{b}"
            	    },
            	    calculable : true,
            	    series : [
            	        {
            	        	calculable : false,//取消虚线
            	            name:'访问来源',
            	            type:'pie',
            	            radius : ['40%','60%'],
            	            center: ['50%', '60%'],
            	            data:[
            	                {value:errorProportion, name:'连通超时\n'+errorProportion+'%'},
            	                {value:successProportion, name:'连通成功\n'+successProportion+'%'}
            	            ]
            	        }
            	    ]
            	};
         myChart_bar.setOption(option,true);
}

	function businessLine(list){
		 var myChart_line = echarts.init(document.getElementById('business_line'));
            var errorNum = new Array();
            var errorDate = new Array();
	        for (var int = 0; int < list.length; int++) {
	            errorNum.push(list[int].errorNum);
	            errorDate.push(list[int].scanDate);
			}
            option = {
            		color:['#F8BB48'],
            	    tooltip : {
            	        trigger: 'axis',
            	        axisPointer: {
					        color: 'rgba(150,150,150,0.3)',
					        width: 'auto',
					        type: 'default'
				      	},
				       formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'个';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              }
            	    },
            	    grid:{
            	    	x:40,
				    	borderColor:'#fff'
				    },
            	    dataZoom : {
            	        show : true,
            	        realtime : true,
            	        height: 20,
            	        start : 100,
            	        end : 50
            	    },
            	    xAxis : [
            	        {
            	            axisLine:false,
                            axisTick:false,
		    	        	splitLine:false,
            	            type : 'category',
            	            boundaryGap : false,
            	            data : errorDate
            	        }
            	    ],
            	    yAxis : [
	                  {
	                      name:'连通超时次数\n',
	                      nameTextStyle:{color:'black'},
	                      axisLine:{
	                          lineStyle: {
	                              color: '#ffffff',
	                              width:1
	                          }
	                      },
	                      type : 'value'
	                  }
	                  ],
            	    series : [
            	        {
            	            name:'超时次数',
            	            symbol:'rectangle',
		    	            symbolSize:4,
            	            type:'line',
            	            data:errorNum
            	        }
            	    ],
            	    calculable:false
            	};
          myChart_line.setOption(option,true);
	}
	//业务系统连同性 end
	
function crux(){
	
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
    $("input[type='checkbox'],input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	updateChannelBar();
	/*============================
	@author:flc
	@time:2015-10-08
	============================*/
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("li").click(function(){
			dt.html($(this).html());_hide();
			var params="5,6,7,8,9,10,11,12,13,14,15,16";
			if($(this).val()==1){
				params="5";
			}else if($(this).val()==2){
				params="6,7";
			}else if($(this).val()==3){
				params="8,9,10,11,12,13,14,15,16";
			}
			$("#types").val(params);
			
			//列表数据
			selectUpdateChannel();
			//柱状图加载
   			updateChannelBar();
			
		});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
	
	
	
   $("#table_up_channel_detail_filter").hide();
   $("#table_up_channel_detail_length").hide();
  	conditionalChoice();
	if(typesId == "tp"){
		$("#typeName").html("全部");
	}else{
		if(typesId == 1){
			$("#typeName").html('<span class="font-704fc1">动态、要闻类</span><span class="font-ff0000">（要求两周内更新）</span>');
		}else if(typesId == 2){
			$("#typeName").html('<span class="font-704fc1">通知公告、政策文件类</span><span class="font-ff0000">（要求半年内更新）</span>');
		}else if(typesId == 3){
			$("#typeName").html('<span class="font-704fc1">人事、规划计划类</span><span class="font-ff0000">（要求一年内更新）</span>');
		}
	}
	
	columnUpdateList();
	
}
//	关键栏目连通性 start-------------------------------------------------------------------------------------------
	//excel导出
	function channelExcel(){
		var scanDate = $("#datepickerChannel").val();
		window.location.href=webPath+"/connectionChannelDetail_channelExcel.action?code=2&date="+scanDate;
	}
	//连动分页查询
	function channelList(){
		table_data_channelConnectivity.fnClearTable();
		table_data_channelConnectivity.fnReloadAjax(webPath+"/connectionBusinessDetail_getChannelStatistics.action?code=2");
	}
	//连动统计分析
	function channelStatistics(time){
		$("#conlistChannel").html(copyInformation(2,null));
		var realTimeType="1";//0：实时   1：非实时
		if($("#datepickerChannel").val() == GetDateStr(0)){
			realTimeType="0";//实时
		}
		var dateTime=$("#datepickerChannel").val();
		 $.ajax( {  
	         type : "POST",  
	         url : webPath+"/connectionChannelDetail_getChannelStatistics.action",   
	         data : {
	        	 date : dateTime,
	        	 realTimeType : realTimeType
	         },  
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	        	 $("#dateStr").val(data.dateStr);
	        	 //实时的时候执行
	        	 if(realTimeType=="0"){
	        		 $("#chartFailNum").val(data.chartFailNum);
	        	 }
	        	
	        	 var conlist = data.connChannelList;
	        	 var html = '';
	        	 $("#conlistChannel").html("");
	        	 var titleStr='title="平台会在第二天展示连接总次数和占比"';
	        	 if(conlist.length > 0 && conlist != null){
	        		 $("#channel_table_hide").removeClass("hide"); 
	        		  $.each(conlist, function(index, obj) {
	        			 var successNum=obj.successNum;
	 	        		 var successThan=obj.successThan+"%";
	 	        		 var errorNum=obj.errorNum;
	 	        		 var errorThan=obj.errorThan+"%";
	 	        		 var connectionSum=obj.connectionSum;
	     	        	 var url = obj.url;
	     				 if(url != ''){
	     					 if(!url.match("http")){
	     	     				 url='http://'+url;
	     	     			 }
	     				 }
	     			if(realTimeType=="0"){
	     				  successNum="-";
	 	        		  successThan="-";
	 	        		  errorThan="-";
	 	        		  connectionSum="-";
	     			}
	     				 html += '<tr>';
	     	        	 html += '<td class="th_left" title="'+obj.name+'">'+subStr(obj.name,6)+'</td>';
	     	        	 html += '<td class="td_left"><div class="url-ellipsis" title="'+url+'"><a target="_blank" href="'+url+'">'+subStr(url,29)+'</div></td>';
	     	        	 html += '<td '+titleStr+'>'+successNum+'</td>';
	     	        	 html += '<td '+titleStr+'>'+successThan+'</td>';
	     	        	 if(errorNum==0){
	     	        		html += '<td >'+errorNum+'</td>';
	     	        	 }else{
	     	        		html += '<td title="点击查看详情" onclick="channelStatisticsInfo(\''+obj.encodeurl+'\',\''+url+'\',\''+index+'\')" style="color:red;cursor:pointer">'+errorNum+'</td>';
	     	        	 }
	     	        	 
	     	        	 html += '<td '+titleStr+'>'+errorThan+'</td>';
	     	        	 html += '<td '+titleStr+'>'+connectionSum+'</td>';
	     	        	 html += '</tr>';
	     	        	 
	     	         });
	        		  var successCountNum=data.successCountNum;
	        		  var successProportion=data.successProportion+"%";
	        		  var errorCountNum=data.errorCountNum;
	        		  var errorProportion=data.errorProportion+"%";
	        		  var connectionCountNum=data.connectionCountNum;
	        		  if(realTimeType=="0"){
	        			  successCountNum="-";
	        			  successProportion="-";
	        			  errorProportion="-";
	        			  connectionCountNum="-";
	     			} 
	        		 html += '<td colspan="2" class="t-td-font1" >汇&nbsp;&nbsp;&nbsp;&nbsp;总</td>';
	        		 html += '<td '+titleStr+'>'+successCountNum+'</td>';
	        		 html += '<td '+titleStr+'>'+successProportion+'</td>';
	        		 html += '<td class="bg-e65e5e">'+errorCountNum+'</td>';
	        		 html += '<td class="bg-e65e5e" '+titleStr+'>'+errorProportion+'</td>';
	        		 html += '<td '+titleStr+'>'+connectionCountNum+'</td>';
	     	         
	     	        $("#conlistChannel").append(html);
	        	 }else{
	        		 $("#channel_table_hide1").show();
	        	 }
	        	 
//		         $("#datepickerChannel").val(data.date);
//		         $("#dateStr").text(data.dateStr);
	         }
	        });
	}
	function channelLine(){ 
		var dateStr=$("#dateStr").val();
    	var chartFailNum=$("#chartFailNum").val();
    	var myChart_line = echarts.init(document.getElementById('channel_line'));
            var errorNum = new Array();
            var errorDate = new Array();
            $.ajax( {  
                type : "POST",  
                url : webPath+"/connectionChannelDetail_getChannelLine.action",  
                data:{
                	dateStr:dateStr,
                	chartFailNum:chartFailNum
                },  
                dataType:"json",
                async : false,  
                success : function(data) {
	                 for (var i = 0; i < data.length; i++) {
	                	 errorNum.push(data[i].errorNum);
	                	 errorDate.push(data[i].scanDate);
					}
                }
               });
	            option = {
	            	    tooltip : {
	            	        trigger: 'axis',
	            	         axisPointer: {
					         color: 'rgba(150,150,150,0.3)',
					        width: 'auto',
					        type: 'default'
					      },
					       formatter: function (params,ticket,callback) {
			                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
			                  for (var i = 0, l = params.length; i < l; i++) {
			                      res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'个';
			                  }
			                  setTimeout(function (){
			                      // 仅为了模拟异步回调
			                      callback(ticket, res);
			                  }, 0);
			              }
	            	    },
	            	    dataZoom : {
	            	        show : true,
	            	        realtime : true,
	            	        height: 20,
	            	        start : 100,
	            	        end : 50
	            	    },
	            	    xAxis : [
	            	        {
	            	        	 axisLine:false,
	                            axisTick:false,
			    	        	splitLine:false,
	            	            type : 'category',
	            	            boundaryGap : false,
	            	            data : errorDate
	            	        }
	            	    ],
	            	   grid:{
	            	   		x:40,
		  	   				borderColor:'#fff'
		  	    		}, 
	            	    yAxis : [
		                  {
		                      name:'连通超时次数\n',
		                      nameTextStyle:{color:'black'},
		                      axisLine:{
		                          lineStyle: {
		                              color: '#ffffff',
		                              width:1
		                          }
		                      },
		                      type : 'value'
		                  }
		                  ],
	            	    series : [
	            	        {
	            	            name:'超时次数',
	            	            type:'line',
	            	            symbol:'rectangle',
			    	            symbolSize:4,
	            	            data:errorNum
	            	        }
	            	    ],
	            	    calculable:false
	            	};
	          myChart_line.setOption(option,true);
	}
	function channelConnectivityList() {
		table_data_channelConnectivity = $("#channel_table").dataTable({
	        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',
	        "bDestroy": true,
	        "bAutoWidth": true,
	        "bDeferRender": true,
	        "bJQueryUI": true,
	        "sPaginationType": "full_numbers",
	        "iDisplayLength": 100,
	        "oTableTools": {
	            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
	            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
	        },
	        "oLanguage": {
	            "sSearch": "查询:",
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
	            "sZeroRecords": "暂无关键栏目连通性数据！"
	        },
	        "bInfo": false,
	        "bFilter":false,
	        "bLengthChange":false,
	        "bPaginate": false,
	        "bRetrieve": false,
	        "bServerSide": false,
	        "sAjaxSource": webPath+ "/connectionChannelDetail_getChannelLineAndList.action?code=2",
	        "sAjaxDataProp": function (data) {
	        	setTimeout(function(){
	        		channelLine(data.list);//关键栏目连通性
	        	},100);
//	        	channelLine(data.list);
	        
	        	//控制导出excel的显示和隐藏
	        	if(data.body.length >0){
	        		$("#channel_table").removeClass("hide");
	                $("#channel_table_hide2").addClass("hide");
	                $(".dropload-load").show();
	        		$("#out_excel_idChannel").attr("style","display:block");
	        	}else{
	        		$("#out_excel_idChannel").attr("style","display:none");
	        	}
	        	
	        	//列表url加title
	    		for (var i = 0; i < data.body.length; i++) {
	    			if(data.body[i]["url"].match("http")){
	    				data.body[i]["url"] = "<td class='td_left'><a class='wz-name wz-name-w2' href='"+data.body[i]["url"]+"' target='_blank' title='"+data.body[i]["url"]+"'>" +data.body[i]["url"]+"</a></td>";
	    			}else{
	    				data.body[i]["url"] = "<td class='td_left'><a class='wz-name wz-name-w2' href='http://"+data.body[i]["url"]+"' target='_blank' title='http://"+data.body[i]["url"]+"'>http://" +data.body[i]["url"]+"</a></td>";
	    			}
	    			
	    			data.body[i]["questionDescribe"] = "<td class='td_left'><div class='wz-name wz-name-w2' title='"+data.body[i]["questionDescribe"]+"'>"+data.body[i]["questionDescribe"]+"</div></td>";
	    		}
	        	
	            return data.body;
	            
	        },
	        "bProcessing": false,
	        "fnServerData": fnDataTablesPipeline,
	        "aoColumns": homeConnectivityTitle,
	        "fnInitComplete": function () {
	            $("#table_data").parent().parent().removeAttr("style");
	            $("#table_data_filter").css("margin-right", "75%");
	            $("#channel_table_length").hide();
	            $("#channel_table_info").hide();
	            $("#channel_table_filter").hide();
	            var table_dataManager_wrapper = $("#table_data");
	            table_dataManager_wrapper.find("th").css("cursor", "default");
	        },
	        "fnPreDrawCallback": function(oSettings) {
	            httpHtmlF();
	        }
	    });
	}

	
	
	
	
// start  栏目更新情况
function columnUpdateList(){
	table_up_channel_detail = $("#table_up_channel_detail").dataTable({
		"sDom" : '<"top"T<"clear">>frt<"toolbar">lip', "bDestroy" : true, "bAutoWidth" : false, "bDeferRender" : true, "bJQueryUI" : true, "sPaginationType" : "full_numbers", "iDisplayLength" : 100, "aaSorting" : [ [ "1", "desc" ] ], "oTableTools" : {
			"sSwfPath" : "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf", "sRowSelect" : "multi" //可选中行，single单行。multi多行    
		}, "oLanguage" : {
			"sSearch" : "查询:", "sLengthMenu" : '本页显示 <select>' + '<option value="50">50</option>' + '<option value="100">100</option>' + '<option value="200">200</option>' + '<option value="500">500</option>' + '<option value="1000">1000</option>' + '</select> ', "oPaginate" : {
				"sFirst" : "首页", "sLast" : "尾页", "sNext" : "下一页", "sPrevious" : "上一页"
			}, "sInfo" : "共 _TOTAL_ 条记录 (当前 _START_ 至 _END_)", "sLoadingRecords" : "数据正在加载...", "sInfoFiltered" : "", "sInfoEmpty" : "共 0 条记录", "sZeroRecords" : "未发现问题"
		}, "bInfo" : true, "bPaginate" : true, "bRetrieve" : true, "bServerSide" : true, "sAjaxSource" : webPath + "/updateChannelDetail_updateChannelPage.action", "sAjaxDataProp" : function(data) {

			if (data.body) {
				$("#table_data_channelUpdate_hide").removeClass("hide");
				$("#table_data_channelUpdate_hide1").removeClass("hide");
				$("#channel_table_id").addClass("hide");
				//$(".dropload-load").show();
				$("#table_data_channelUpdate_hide").html("共 <span>" + data.total + "</span> 条");
				$("#table_up_channel_detail").removeClass("hide");

				for ( var i = 0; i < data.body.length; i++) {

					data.body[i]["title"] = "<td class='td_left'><div class='wz-name' title='" + data.body[i]["title"] + "'>" + (data.body[i]["title"].substring(0, 20)) + "</div></td>";

					var url = data.body[i]["url"];
					if (!url.match("http")) {
						url = "http://" + url;
					}

					data.body[i]["url"] = "<td class='td_left'><a class='url-ellipsis' href='" + url + "' target='_blank' title='" + url + "'>" + (url.substring(0, 40)) + "</a></td>";

					// 快照处理 start=============
					var imgUrl = data.body[i]["imgUrl"] + "";
					if (imgUrl == '') {
						data.body[i]["imgUrl"] = "<td>无</td>";
					} else {
						if (imgUrl.match("htm")) {
							data.body[i]["imgUrl"] = "<td><i class='kuaiz' onclick='getShot(\"" + imgUrl + "\")'></i></td>";
						} else {
							data.body[i]["imgUrl"] = "<td><a target='_blank' href='" + imgUrl + "'><img alt='截图' src='" + webPath + "/images/jiet.png'/></a></td>";
						}
					}
					// 快照处理 end=============
					//data.body[i]["imgUrl"] ="<td><i class='kuaiz' onclick='getShot(\""+data.body[i]["imgUrl"]+"\")'></i></td>";

				}
				return data.body;

			} else {
				$("#table_up_channel_detail").addClass("hide");
			}
		}, "bProcessing" : false, "fnServerData" : fnDataTablesPipeline, "aoColumns" : homeConnectivityTitleUpdate, "fnInitComplete" : function() {
			$("#table_data").parent().parent().removeAttr("style");
			$("#table_data_filter").css("margin-right", "75%");
			$("#table_up_channel_detail_filter").hide();
			$("#table_up_channel_detail_length").hide();
			$("#table_up_channel_detail_info").hide();
			var table_dataManager_wrapper = $("#table_data");
			table_dataManager_wrapper.find("th").css("cursor", "default");
		}, "fnPreDrawCallback" : function(oSettings) {
			$("#table_up_channel_detail").siblings(".dropload-load").hide();
		}
	});
}

function getShot(imgUrl) {
	window.open(imgUrl);
}
//类型选择下拉框处理
function selectUpdateChannel(params){
	table_up_channel_detail.fnReloadAjax(webPath+"/updateChannelDetail_updateChannelPage.action");
	
}

//栏目跟新页面excel导出
function  updateChannelExcel(){
	
	//jquery获取复选框值    
	 var types=$("#types").val();  
	 var days=""; 
	 $("input[name='days']:checked").each(function(index,element){  
	  	days=$(this).val();  
	  });
	  var key=$("#keyInputColumn").val();

	window.location.href=webPath+"/updateChannelDetail_updateChannelExcel.action?type="+types+"&days="+days+"&key="+key;
}
//栏目更新分页查询
 function  conditionalChoice(){
	$("input[name='days']").on('ifChecked',function(event) {
		table_up_channel_detail.fnReloadAjax(webPath+ "/updateChannelDetail_updateChannelPage.action"); 
		//柱状图加载
   		updateChannelBar();
	});
 
	$("#keyInputColumn").keyup(
			function() {
//				table_up_channel_detail.fnClearTable(); 
				table_up_channel_detail.fnReloadAjax(webPath
						+ "/updateChannelDetail_updateChannelPage.action");
			}); 
	}
	
function updateChannelBar(){
			var updateNumDate=[];
			var channelNameDate=[];
			var types = "";
			var tyId = $("#typesId").val();
			if(tyId == "tp"){
				types = $("#types").val();
			}else{
				$("#typesId").attr("value", "tp");
				if(tyId == 1){
					types="5";
				}else if(tyId == 2){
					types="6,7";
				}else if(tyId == 3){
					types="8,9,10,11,12,13,14,15,16";
				}
			}
			var days=$("input[name='days']:checked").val();
			var myBarChart = echarts.init(document.getElementById("update_channel_bar_id"));
		    $.ajax( {  
			        type : "POST",  
			        url : webPath+"/updateChannelDetail_updateChannelBar.action?type="+types+"&days="+days,
			        dataType:"json",
			        async : false,
			        success : function(homeData) {
			             for (var j = 0; j < homeData.length; j++) {
			            	 var updateNum=homeData[j].updateNum;
			            	 var channelName=homeData[j].channelName;
			            	 updateNumDate.push(updateNum);
			            	 channelNameDate.push(channelName);
						}
			        }
		       });
			option = {
			
				tooltip : {
			    	 trigger: 'axis',
		             borderColor: '#48b',
		             axisPointer: {
			             color: 'rgba(150,150,150,0.3)',
			             width: 'auto',
			             type: 'default'
	              	},
	             	formatter:"{b}<br/>{a}：{c}个",
                 		padding:15,
                 		textStyle : {
		           			fontSize: 12,
		          		 }
		   			 },
				    xAxis : [
				        {
				            axisLine:false,
                            axisTick:false,
				            type : 'category',
				            splitLine:false,
				            data : channelNameDate,
				            axisLabel: {
				            rotate: 30
				            }
				        },
				      ],
		    	    yAxis : [
	                  {
	                      name:'更新栏目数\n',
	                      nameTextStyle:{color:'black'},
	                      axisLine:{
	                          lineStyle: {
	                              color: '#ffffff',
	                              width:1
	                          }
	                      },
	                      type : 'value'
	                  }
	                  ],
		    	    grid:{
		    	    	borderColor:'#fff',
         /*                  x:35,
                          y:5,*/
                          y2:65,
                     }, 
				    series : [
				        {
				            name:'更新栏目数',
				            type:'bar',
				            barWidth: 40, 
				            data:updateNumDate,
				            stack: 'a',
				            itemStyle: {
                                  normal: {
                                   color: function(params) {
                                var colorList = [
			                          '#F75352','#46CDE1','#62D975','#F6D64F','#3BA6DC',
			                           '#6FCA63','#D30D13','#BE32D9','#46E0DE','#B9ACD8',
			                           '#62D975','#F7D550','#C8A5DB'
			                        ];
			                        return colorList[params.dataIndex]
                                },
		                           label: {
				                        show: true,
				                        position: 'insideTop',
				                        formatter: '{c}',
				                        textStyle:{
				                        fontSize:18
				                        }
		                            } 
                                },
                                  emphasis : {
                                   color: function(params) {
                                var colorList = [
			                          '#F75352','#46CDE1','#62D975','#F6D64F','#3BA6DC',
			                           '#6FCA63','#D30D13','#BE32D9','#46E0DE','#B9ACD8',
			                           '#62D975','#F7D550','#C8A5DB'
			                        ];
			                        return colorList[params.dataIndex]
                                },
		                           label: {
				                        show: true,
				                        position: 'insideTop',
				                        formatter: '{c}',
				                        textStyle:{
				                        fontSize:18
				                        }
		                            } 
                                },
                               }
				        }
				    ]
				};
		 myBarChart.setOption(option,true);
 	}                   
function subStr(str,fontSize){
    if(str.length > fontSize){
    	return str.substring(0,fontSize)+'...';
    }else{
    	return str;
    }
}
function changeTabInit(){
	if(col==1){
		$('.every_tip').removeClass('on');
		$("#tab1").addClass('on');
		$("#tabId").val(col);
		$('.every_tabs').removeClass('on');
		$('.every_tabs').eq(col).addClass('on');
		changeTab(col);
		if(col == 2 ){
			$(".msg").show();
		}else{
			$(".msg").hide();
		}
	}else if(col==2){
		$('.every_tip').removeClass('on');
		$("#tab2").addClass('on');
		$("#tabId").val(col);
		$('.every_tabs').removeClass('on');
		$('.every_tabs').eq(col).addClass('on');
		changeTab(col);
		if(col == 2 ){
			$(".msg").show();
		}else{
			$(".msg").hide();
		}
	}
}