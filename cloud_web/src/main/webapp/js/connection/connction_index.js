(function($){
	
	var yesDate = $("#time_yes_date").val();
	$("#datepicker").val($("#time_yes_date").val());
	$("#currentDateker").val(document.getElementById("str").innerText);
	
//检索
$("#conlist_term").keyup(function(){
         var searchInfo=$("#conlist_term").val();
		 if(searchInfo==""){
			 $("#conn_table tr").show();
		 }else{
			 $("#conn_table").find(".key").each(function(index, element) {
				 if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
});

//列表
homeList();
//占比
homeSumList(yesDate);

$("#datepicker").datepicker({
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
	//defaultDate : $("input[name='a']").val(),// 默认日期
	minDate : $("#time_tool_min_date").attr("value"),//最小日期
	maxDate : GetDateStr(-1),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		//占比
		homeSumList(dateText);
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
		homeList(dateText);
	}
}); 


})(jQuery);

//占比
function homeSumList(time){
	if(time==null){
		time = "";
	}
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionHomeDetail_queryHomelist.action",  
         data : "date=" + time,  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 var tfootList="";
        	 $("#successNum").html("");
        	 $("#errorNum").html("");
        	 $("#successProportion").html("");
        	 $("#errorProportion").html("");
        	 $("#tj_count").html("");
        	 
        	 tfootList+="<input type='hidden' id='successProportion_bar' value='"+data.successProportion+"' />" +

       		"<input type='hidden' id='errorProportion_bar' value='"+data.errorProportion+"' />"+
       
       		"<input type='hidden' id='yesterdayStrEng_id' value='"+data.yesterdayStrEng+"' />";
         	 $("#tfoot_id").append(tfootList);
        	 
        	   if(data.errorProportion==null){
		           	 $("#successNum").append(data.successNum+"次");
		           	 $("#successProportion").append((data.successProportion)+"%");
		           	
		           	 $("#errorNum").append(data.errorNum+"次");
		           	 $("#errorProportion").append(0+"%");
		           	 $("#tj_count").append((parseInt(data.errorNum)+parseInt(data.successNum))+"次");
		       }else if(data.successProportion==null){
		             $("#successNum").append(data.successNum+"次");
		           	 $("#successProportion").append(0+"%");
		           	 $("#errorNum").append(data.errorNum+"次");
		           	 $("#errorProportion").append((data.errorProportion)+"%");
		           	 $("#tj_count").append((parseInt(data.errorNum)+parseInt(data.successNum))+"次");
		        }else{
		           	 $("#successNum").append(data.successNum+"次");
		           	 $("#successProportion").append((data.successProportion)+"%");
		           	 $("#errorNum").append(data.errorNum+"次");
		           	 $("#errorProportion").append((data.errorProportion)+"%");
		     
		           	 $("#tj_count").append((parseInt(data.errorNum)+parseInt(data.successNum))+"次");
		        }
        	   
        	   homebar(data.successProportion,data.errorProportion);
         }
        });
}   

//列表
function homeList(time){
	if(time==null){
		time = "";
	}
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionHomeDetail_queryHomelistOverview.action",  
         data : "date=" + time,  
         dataType:"json",
         async : false,  
         success : function(data) {
        	//折线图
        	 setTimeout(function(){
        	 	homeLine(data.list);
        	 },100);

        	 var conlist = data.connDetList;
        	 var list = "";
        	 $("#tfoot_id").html("");
        	 $("#conlist").html("");
        	 
        	 if(data.siteBeginServiceDate!=""){
        		 $("#time_tool_min_date").attr("value",data.siteBeginServiceDate);
        	 }
        
        	 if(conlist.length >0){
        		 $("#conn_table").removeClass("hide");
        		 $("#conn_table_hide").addClass("hide");
                 $(".dropload-load").show();
         		$("#out_excel_id").html("<i></i>导出列表");
        		$("#out_excel_id").addClass("page-btn1");
        		$("#out_excel_id").attr("onclick","homeExcle()");
                 for (var int = 0; int < conlist.length; int++) {
               	  list +="<tr><td class='td_left font_701999'>"+(int+1)+"</td>" +
               	  		"<td class='td_left'>"+conlist[int].scanTime+"</td>" +
               	  		"<td>"+conlist[int].stateName+"</td>" +
               	  		"<td class='td_left'><div class='wz-name'>" +
               	  		"<span class='error-num key'>"+conlist[int].questionCode+"</span>&nbsp;&nbsp;"
               	  		+conlist[int].questionDescribe+"</div></td></tr>";
                 	}
                 $("#conn_table").siblings(".dropload-load").hide();
                 $("#conlist").append(list);
                 new TableSorter("conn_table",1);
        	 }
		    
		       //日期
		      //$("#yesterdayStr_1").text(data.yesterdayStr);
//              $("#datepicker").val(data.yesterdayStr);
//              $("#currentDateker").val(data.dateStr);
		      $("#dateStr").text(data.dateStr);
         }
        });
}


function homebar(successProportion,errorProportion){
	//饼图

	var myChart_bar = echarts.init(document.getElementById('home_bar'));
	option = {
			color:['#2DCC70','#D6DAE3'],
			tooltip : {
				trigger: 'item',
				formatter: "{b}"
			},
			calculable : true,
			series : [{
				calculable : false,//取消虚线
				name:'访问来源',
				type:'pie',
				radius : ['40%','60%'],
				center: ['48%', '60%'],
				data:[
				      {value:parseInt(successProportion), name:'成功连通'+(successProportion)+'%'},
				      {value:parseInt(errorProportion), name:'连通超时'+(errorProportion)+'%'}
				      ]
			}]
	};
	
	myChart_bar.setOption(option,true);
	}
	   
function homeLine(list){
	//折线图
	var myChart_line = echarts.init(document.getElementById('home_line'));
	var errorNum = new Array();
	var errorDate = new Array();
//	$.ajax( {  
//		type : "POST",  
//		url : webPath+"/connectionHomeDetail_getHomeTrendLine.action",  
//		// data : "content=" + content,  
//		dataType:"json",
//		async : false,
//		success : function(data) {
			for (var int = 0; int < list.length; int++) {
				errorNum.push(list[int].errorNum);
				errorDate.push(list[int].scanDate);
				
			}
//		}
//	});
	option = {
			color:['#BAA7E3'],
			tooltip : {
				trigger: 'axis',
				axisPointer: {
                   color: 'rgba(150,150,150,0.3)',
                   width: 'auto',
                   type: 'default'
                 } ,
     			formatter: function (params,ticket,callback) {
                    var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
                    for (var i = 0, l = params.length; i < l; i++) {
                        res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'次';
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
    	    grid:{
    	    	x:60,
    	    	y:65,
	  	    	borderColor:'#fff'
	  	    },
			xAxis : [
			         {
			        	 type : 'category',
			        	 axisLine:false,
			        	 splitLine:false,
			        	 axisTick:false,
			        	 data : errorDate
			         }
			         ],
		    yAxis : [
                  {
                	  name:'连接超时次数  (次)\n',
                	  nameTextStyle:{color:'#999999'},
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
                            	symbol:'emptyCircle',
    		    	            symbolSize:4,
                            	name:'超时次数',
                            	type:'line',
                            	data:errorNum
                            }
                   ],
             calculable:false
	};
	myChart_line.setOption(option,true);
}
//excel导出
function homeExcle(){
	var scanDate = $("#currentDateker").val();
	window.location.href=webPath+"/connectionHomeDetail_homeExcel.action?date="+scanDate;
}
   