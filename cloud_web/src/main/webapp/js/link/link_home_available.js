$(function(){
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
	//长期不可用链接检索
	$("#searchInfo").keyup(function(){
	 var searchInfo=$("#searchInfo").val();
			 if(searchInfo==""){
				 $("#link_table tr").show();
			 }else{
				 $("#link_table").find(".linkTitle").each(function(index, element) {
					 if($(this).html().indexOf(searchInfo)>=0){
						$(this).parents("tr").show();
					 }else{
						 $(this).parents("tr").hide();
					 }
				});
			 }
	});

	//首页链接可用性检索
	$("#keyInput").keyup(function(){
	 var searchInfo=$("#keyInput").val();
			 if(searchInfo==""){
				 $("#linkHome_table tr").show();
			 }else{
				 $("#linkHome_table").find(".codeDesc").each(function(index, element) {
					 if($(this).html().indexOf(searchInfo)>=0){
						$(this).parents("tr").show();
					 }else{
						 $(this).parents("tr").hide();
					 }
				});
			 }
	});

	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
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
		minDate : $("#siteBeginServiceDate_id").val(),//最小日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六'],
		onSelect : function(dateText, inst){
			//首页链接可用性检测结果
			linkHome(dateText);
			$("#datepickerVal").val(dateText);
		}
	});
	//首页链接可用性趋势分析折线图
	linkHomeTrendLine();
	//首页链接可用性检测结果
  	linkHome();
}) 

	//导出首页链接可用性Excel
	function linkHomeAvailableExcel(){
		var scanDate=$("#dateText").val();
		var key=$("#keyInput").val();
		var homeUnavailableSelectId = $("#chainSelectId").val();
		window.location.href=webPath+"/linkHome_linkHomeAvaliableExcel.action?scanDate="+scanDate+"&key="+key+"&homeUnavailableSelectId="+homeUnavailableSelectId;
	}
	//快照拼装url
	function getShot(imgUrl){
		window.open(imgUrl);
		//window.location.href=imgUrl;
	}
function homeUnavailableOption(){
	linkHome($("#datepickerVal").val());
}
	function linkHome(time){
		var homeUnavailableSelectId = $("#chainSelectId").val();
		if(time==null){
			time = "";
		}
		 $.ajax( {  
	         type : "POST",  
	         url : webPath+"/linkHome_linkHomeAvaliable.action",  
	         data:{
	 			date:time,
	 			homeUnavailableSelectId:homeUnavailableSelectId,
	 		 },
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	        	 var conlist = data.linkHome_List;
	        	 var list = "";
	        	 var excelList="";
	        	 
	        	 $("#linkList").html("");
	        	 
	        	 $("#home_excel_out").html("");
	        	 
	        	 if(conlist.length>0){
	        	   $("#linkHome_table").removeClass("hide");
	        	   $("#linkHome_table_hide").addClass("hide");
                    $(".red-tip").removeClass("hide");
                     $(".dropload-load").show();
	        	 	$("#home_excel_out").html("<i></i>导出列表");
	        		$("#home_excel_out").addClass("page-btn1");
	        		$("#home_excel_out").attr("style","display:block");
	        		$("#home_excel_out").attr("onclick","linkHomeAvailableExcel()");
	        		
	        		for (var i = 0; i < conlist.length; i++) {
	        			
	        			var url=conlist[i].NoAvailableurl;
	        			if(!url.match("http")){
	        				url="http://"+url;
	        			}
	        			var parentUrl=conlist[i].parentUrl;
	        			if(parentUrl!=""){
	        				if(!parentUrl.match("http")){
		        				parentUrl="http://"+parentUrl;
		        			}
	        			}
	        			
	        			list +="<tr><td class='td_left font_701999'>"+(i+1)+"</td>" +
	            	  		"<td class='td_left'>"+conlist[i].scanTime+"</td>" +
	            	  		"<td class='td_left'><a class='url-ellipsis' href='"+(url)+"' target='_blank' title='"+(url)+"'>"+url+"</a></td>" +
	            	  		"<td class='td_left'><div  title='"+conlist[i].linkTitle+"'>"+conlist[i].linkTitle+"</div></td>" +
	            	  		"<td class='td_left'><a class='url-ellipsis' href='"+(parentUrl)+"' target='_blank' title='"+(parentUrl)+"'>"+parentUrl+"</a></td>" +
	            	  		"<td class='td_left'>"+conlist[i].linkNotavailableDay+"</td>" +
	            	  		"<td class='td_left'>"+conlist[i].resourceDes+"("+conlist[i].scope+")"+"</td>"+
	            	  		"<td class='td_left'><div class='wz-name wz-name-w2 codeDesc' title='"+conlist[i].questionDescribe+"'>"+
	            	  		"<span >"+conlist[i].questionDescribe+"</div></td>";
	            	  		//"<td>"+shotStr+"</td></tr>";
	        			
	        			// 快照处理 start=============
						var imgUrl = conlist[i].imgUrl + "";
						if (imgUrl == '') {
							list +="<td>无</td>";
						} else {
							if (imgUrl.match("htm")) {
								list += "<td><i class='kuaiz' onclick='getShot(\"" + imgUrl + "\")'></i></td>";
							} else {
								list +="<td><a target='_blank' href='" + imgUrl + "'><img alt='截图' src='" + webPath + "/images/jiet.png'/></a></td>";
							}
						}
						// 快照处理 end=============
						list += "</tr>";
	            	}
                     $("#linkHome_table").siblings(".dropload-load").hide();
	            	$("#linkList").append(list);
                     $("#linkList tr:odd td").css("background","#fafbfc");
	        	 }else{
	        	      $("#linkHome_table").addClass("hide");
	        	      $("#linkHome_table_hide").removeClass("hide");
                     $(".red-tip").addClass("hide");
                     $(".dropload-load").hide();
	        	 	 $("#home_excel_out").attr("style","display:none");
	        	 }
	             //日期
                 $("#datepicker").val(data.yesterdayStr);
	         	//$("#yesterdayStr_1").text(data.yesterdayStr);
	         	
	        	$("#dateText").val(data.date);
	        	var a = $("#dateText").val();
	
	       }
	     });
	}

	function linkHomeTrendLine(){
        var myChart_line = echarts.init(document.getElementById('link_home_line_id'));
        var errorNum = new Array();
        var errorDate = new Array();
        $.ajax( {  
            type : "POST",  
            url : webPath+"/linkHome_linkHomeTrendLine.action",  
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
	       color:['#BAAEE0'],
	  		dataZoom : {
	  	        show : true,
	  	        realtime : true,
	  	        height: 20,
	  	        start : 100,
	  	        end : 60
	  	    },
		    tooltip : {
		        trigger: 'axis',
			    borderColor: '#48b',
	          axisPointer: {
	          color: 'rgba(150,150,150,0.3)',
	          width: 'auto',
	          type: 'default'
	          },
	          formatter: function (params,ticket,callback) {
	                var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
	                for (var i = 0, l = params.length; i < l; i++) {
	                    res += '<br/>首页' + params[i].seriesName + '：' + params[i].value+'个';
	                }
	                setTimeout(function (){
	                    // 仅为了模拟异步回调
	                    callback(ticket, res);
	                }, 0);
	            },
	          padding:15
		    },
		    calculable : true,
		    xAxis : [
		        {
		        	splitLine:false,
		        	axisLine:false,  
		        	 axisTick:false,
		            type : 'category',
		            data : errorDate
		        }
		    ],
		    grid:{
		    	borderColor:'#fff',
		    	x:50,
		    	x2:50,
		    	y:50
		    },
		    yAxis : [
		        {
		        	name:'不可用链接数  (个)\n',
		        	nameTextStyle:{color:'black'},
		            axisLine:{
		                lineStyle: {
		                    color: '#ffffff',
		                    width:1
		                },
		            type : 'value'
		            }
		        }
		    ],
		    series : [
		        {
		        	symbol:'emptyCircle',
		        	symbolSize:4,
		            name:'不可用链接数',
		            type:'line',
		            data:errorNum
		           
		        }
		    ]
		};
  		myChart_line.setOption(option);
	}