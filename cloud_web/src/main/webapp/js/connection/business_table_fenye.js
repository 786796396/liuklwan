var homeConnectivityTitle = [];//首页连通性表头
homeConnectivityTitle.push({"mDataProp": "dataNumber","sWidth":"50px", "bSortable": false,"sTitle": "序号", "sClass": "center", "bVisible": true, "tabIndex": -7});
homeConnectivityTitle.push({"mDataProp": "systemName","sWidth":"241px", "sTitle": "业务系统名称","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -6});
homeConnectivityTitle.push({"mDataProp": "url","sWidth":"208px", "sTitle": "URL", "bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -5});
homeConnectivityTitle.push({"mDataProp": "scanTime","sWidth":"200px", "sTitle": "时间","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -4});
homeConnectivityTitle.push({"mDataProp": "stateName","sWidth":"95px", "sTitle": "连通状态","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -3});
homeConnectivityTitle.push({"mDataProp": "questionDescribe", "sWidth":"241px","sTitle": "<span class='pull-left'>问题描述</span><div class='http-html'></div>","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -1});
var table_data_homeConnectivity;

$(function(){
	
	$("#datepicker").val($("#time_yes_date").val());
	$("#currentDateker").val(document.getElementById("str").innerText);
	
    $(".ms-icon-wen").hover(function(){
        $(this).siblings(".ms-wen-con").fadeIn();
    },function(){
        $(this).siblings(".ms-wen-con").fadeOut();
    });
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
    
    //统计分析
	businessStatistics();
    //列表
	homeConnectivityList();
    
	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
	$("#business_table_filter").hide();
	$("#business_table_length").hide();
	$("#datepicker").datepicker({//添加日期选择功能
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
// 				businessbar();
			}
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
//		defaultDate : GetDateStr(-1),// 默认日期
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
					+ "/connectionBusinessDetail_queryList.action?code=3");
		}
	}); 
	
	//饼图
	if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="fasle"){
		$("#business_table_pie").attr("style","display:block");
		//$("#business_bar").attr("style","height: 200px;width: 100%;display:none");
		$("#business_bar").addClass("hide");
	}else{
		$("#business_table_pie").attr("style","display:none");
		//$("#business_bar").attr("style","height: 200px;width: 100%;display:block");
		$("#business_bar").removeClass("hide");
// 		businessbar();
	}
	
	
	//折线图
	setTimeout(function(){
		businessLine();
	},100);
	
	/*
	关键字查询
	*/
 	/* $("#keyInput").keyup(
				function() {
					table_data_homeConnectivity.fnReloadAjax(webPath
							+ "/connectionBusinessDetail_queryList.action?code=2");
				});  */
}) 

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
            "sZeroRecords": "暂无业务系统连通性数据！"
        },
        "bInfo": false,
        "bFilter":false,
        "bLengthChange":false,
        "bPaginate": false,
        "bRetrieve": false,
        "bServerSide": false,
        "sAjaxSource": webPath+ "/connectionBusinessDetail_queryList.action?code=3",
        "sAjaxDataProp": function (data) {
        	
        	
        	if(data.body){
        		//隐藏暂无数据
        		$("#business_table_hide2").addClass("hide");
        		//$("#business_table_pie").attr("style","display:none");
        		//显示列表数据
        		$("#business_table").removeClass("hide");
        		//导出excel显示
        		$("#out_excel_id").attr("style","display:block");

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
        		$("#out_excel_id").attr("style","display:block");
        		//日期显示
        		
        		$("#out_excel_id").attr("style","display:none");
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
	var scanDate = $("#currentDateker").val();
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
        	 $("#conlist").html("");
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
	            	  		"<td class='td_left'><div class='url-ellipsis' title='"+siteUrl+"'><a target='_blank' href='"+siteUrl+"'>"+siteUrl+"</div></td>" +
	            	  		"<td>"+conlist[int].successNum+"</td>" +"<td>"+(conlist[int].successProportion)+"%</td>"+
	            	  		"<td>"+conlist[int].errorNum+"</td>"+"<td>"+(conlist[int].errorProportion)+"%</td>"+
	            	  		"<td>"+(conlist[int].successNum+conlist[int].errorNum)+"</td></tr>";
	             }
                $("#business_table").siblings(".dropload-load").hide();
             	$("#conlist").append(list);
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

	function businessLine(){
		 var myChart_line = echarts.init(document.getElementById('business_line'));
            var errorNum = new Array();
            var errorDate = new Array();
            $.ajax( {  
                type : "POST",  
                url : webPath+"/connectionBusinessDetail_getBusinessTrendLine.action",  
                dataType:"json",
                async : false,  
                success : function(data) {
	                 for (var int = 0; int < data.length; int++) {
	                	 errorNum.push(data[int].errorNum);
	                	 errorDate.push(data[int].scanDate);
					}
                }
               });
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