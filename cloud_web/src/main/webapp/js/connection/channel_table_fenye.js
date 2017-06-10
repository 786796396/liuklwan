var homeConnectivityTitle = [];//关键栏目连通性表头
homeConnectivityTitle.push({"mDataProp": "dataNumber", "bSortable": false,"sWidth":"50px","sTitle": "序号", "sClass": "center", "bVisible": true, "tabIndex": -7});
homeConnectivityTitle.push({"mDataProp": "systemName", "sTitle": "关键栏目名称","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -6});
homeConnectivityTitle.push({"mDataProp": "url", "sWidth":"200px","sTitle": "URL", "bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -5});
homeConnectivityTitle.push({"mDataProp": "scanTime","sWidth":"200px", "sTitle": "时间","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -4});
homeConnectivityTitle.push({"mDataProp": "stateName","sWidth":"95px", "sTitle": "连通状态","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -3});
homeConnectivityTitle.push({"mDataProp": "questionDescribe", "sTitle": "<span class='pull-left'>问题描述</span><div class='http-html'></div>","bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -1});
var table_data_channelConnectivity;

$(function(){
	
	$("#datepicker").val($("#time_yes_date").val());
	$("#currentDateker").val(document.getElementById("str").innerText);
	
    $(".ms-icon-wen").hover(function(){
        $(this).siblings(".ms-wen-con").fadeIn();
    },function(){
        $(this).siblings(".ms-wen-con").fadeOut();
    });
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
    
	//统计分析
	channelStatistics();
	//列表
	channelConnectivityList();
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
		minDate : $("#channel_time_tool_mindate").attr("value"),//最小日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
		
			//统计分析
			channelStatistics(dateText);
		//饼图
/* 		if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="false"){//没有统计数据
			//显示暂无数据
			$("#business_table_pie").attr("style","display:block");
			//隐藏环饼状图
			$("#channel_bar").attr("style","height: 200px;width: 100%;display:none");
		}else{//存在统计数局
			//隐藏暂无数据
			$("#business_table_pie").attr("style","display:none");
			//显示环饼状图
			$("#channel_bar").attr("style","height: 200px;width: 100%;display:block");
			setTimeout(function(){
				channelbar();
			},100);
		} */
		
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
			//列表
			channelList();
		}
	}); 
	//饼图
/* 	if($("#successProportion_bar").val()=="false" ||  $("#errorProportion_bar").val()=="false"){
		//显示暂无数据
		$("#business_table_pie").attr("style","display:block");
		//隐藏环饼状图
		$("#channel_bar").attr("style","height: 200px;width: 100%;display:none");
	}else{
		//隐藏暂无数据
		$("#business_table_pie").attr("style","display:none");
		//显示环饼状图
		$("#channel_bar").attr("style","height: 200px;width: 100%;display:block");
		setTimeout(function(){
			channelbar();
		},100);
	} */
	
	//折线图
	setTimeout(function(){
			channelLine();
	},100);
   /*
	关键字查询
	*/
	$("#keyInput").keyup(
		function() {
			table_data_channelConnectivity.fnReloadAjax(webPath+"/connectionBusinessDetail_queryList.action?code=2");
		});

});

//excel导出
function channelExcel(){
	var scanDate = $("#currentDateker").val();
	window.location.href=webPath+"/connectionChannelDetail_channelExcel.action?code=2&date="+scanDate;
}
//连动分页查询
function channelList(){
	table_data_channelConnectivity.fnClearTable();
	table_data_channelConnectivity.fnReloadAjax(webPath+"/connectionBusinessDetail_queryList.action?code=2");
}
//连动统计分析
function channelStatistics(time){
	if(time==null){
			time = "";
		}
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionChannelDetail_getChannelStatistics.action",   
         data : "date=" + time,  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 var conlist = data.connChannelList;
        	 var html = '';
        	 var sumHtml = '';
        	 $("#conlist").html("");
        	 $("#conSum").html("");
            
        	 if(conlist.length > 0 && conlist != null){
        		 $("#channel_table_hide").removeClass("hide"); 
        		  $.each(conlist, function(index, obj) {
     	        	 var url = obj.url;
     				 if(url != ""){
     					 if(!url.match("http")){
     	     				 url="http://"+url;
     	     			 }
     				 }
     	        	 html += '<tr>';
     	        	 html += '<td class="th_left">'+obj.name+'</td>';
     	        	 html += '<td class="td_left"><div class="url-ellipsis" title="'+url+'"><a target="_blank" href="'+url+'">'+url+'</div></td>';
     	        	 html += '<td >'+obj.successNum+'</td>';
     	        	 html += '<td >'+obj.successThan+'%</td>';
     	        	 html += '<td >'+obj.errorNum+'</td>';
     	        	 html += '<td >'+obj.errorThan+'%</td>';
     	        	 html += '<td >'+obj.connectionSum+'</td>';
     	        	 html += '</tr>';
     	        	 
     	         });
        		  
        		 sumHtml += '<td colspan="2" class="t-td-font1">汇&nbsp;&nbsp;&nbsp;&nbsp;总</td>"+"<td>'+data.successCountNum+'</td>';
     	         sumHtml += '<td >'+data.successProportion+'%</td>';
     	         sumHtml += '<td class="bg-e65e5e">'+data.errorCountNum+'</td>';
     	         sumHtml += '<td class="bg-e65e5e">'+data.errorProportion+'%</td>';
     	         sumHtml += '<td >'+data.connectionCountNum+'</td>';
     	         
     	        $("#conlist").append(html);
     	        $("#conSum").append(sumHtml);
        	 }else{
        		 $("#channel_table_hide1").show();
        	 }
        	 
	         $("#datepicker").val(data.date);
	         $("#dateStr").text(data.dateStr);
         }
        });
}
function channelLine(){ 
			var myChart_line = echarts.init(document.getElementById('channel_line'));
            var errorNum = new Array();
            var errorDate = new Array();
            $.ajax( {  
                type : "POST",  
                url : webPath+"/connectionChannelDetail_getChannelLine.action",  
                // data : "content=" + content,  
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
        "sAjaxSource": webPath+ "/connectionBusinessDetail_queryList.action?code=2",
        "sAjaxDataProp": function (data) {
        	//控制导出excel的显示和隐藏
        	if(data.body.length >0){
        		$("#channel_table").removeClass("hide");
                $("#channel_table_hide2").addClass("hide");
                $(".dropload-load").show();
        		$("#out_excel_id").attr("style","display:block");
        	}else{
        		$("#out_excel_id").attr("style","display:none");
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
