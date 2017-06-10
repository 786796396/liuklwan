var ylist = new Array();
var xlist = new Array();
$(function(){
	 $(".tab_box1 table tr:odd td").css("background","#fafbfc");
	 //单选按钮添加添加事件
	 $("input[type='radio']").iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue'
	 });
	 
	$("input[name='days']").on('ifChecked',function(event) {
		var days=$(this).val();
		console.log("======days========="+days);
		//monitorData(days);
		getMonitorList(days)
	});
	//检索
/*	$("#conlist_term").keyup(function(){
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
	});*/
    
    //tab切换  各大块切换；
    $('.every_tip').click(function(){
        var n = $(this).index();
        $('.every_tab').removeClass('on');
        $('.every_tip').removeClass('on');
        $(this).addClass('on');
        $('.every_tab').eq(n).addClass('on');
    });
	
    //折线图
//    setTimeout(function(){
//    	 monitorData();
//    },100);
	
    //列表数据加载
    getMonitorList(-14);
	
});

//列表数据加载
function getMonitorList(days){
	$.ajax({
        async: false,
        type: "POST",
        url: webPath+"/monitorInclude_getMonitorDataList.action?days="+days,
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
    	
    	//清空列表数据
		$("#conlist").html("");
		$("#monitor_total_id").html("");
		
    	if(response.errmsg){//暂无数据
    		//隐藏统计数据
    		$("#monitor_total_id").addClass("hide");
    		//隐藏导出excel
    		$("#out_excel_id").addClass("hide");
    		//隐藏列表数据
    		$("#conlist").addClass("hide");
    		//显示暂无数据
    		$("#conn_table_hide").removeClass("hide");
    		return;
    	}else{
    		var returnList=response.returnList;
    		var totalNum=response.totalNum;
    		$("#monitor_total_id").html("共"+totalNum+"条");
    		if(returnList && returnList.length>0){
    			
        		//显示统计数据
        		$("#monitor_total_id").removeClass("hide");
        		//显示导出excel
        		$("#out_excel_id").removeClass("hide");
        		//显示列表数据
        		$("#conlist").removeClass("hide");
        		//隐藏暂无数据
        		$("#conn_table_hide").addClass("hide");
        		
        		//清空列表数据
        		$("#conlist").html();
        		var dataStr="";
        		var leng = returnList.length;
        		xlist.length = 0;
        		ylist.length = 0;
    			for(var i=0;i<returnList.length;i++){
    				dataStr+="<tr><td class='font_701999' style='width:5%; text-align:center;'>"+(i+1)
    				+"</td><td class=''  style='width:20%;  text-align:left;'>"+returnList[i]["scanDate"]
    				+"</td><td class='td-center' style='width:20%;'>"+returnList[i]["baiduSlWebsite"]
    				+"</td><td class='td-center' style='width:20%;'>"+returnList[i]["baiduSlDomainsite"]
    				+"</td></tr>";
    				xlist.push(returnList[leng-i-1]["scanDate"]);
                	ylist.push(returnList[leng-i-1]["baiduSlWebsite"]);
    			}
    			monitorData();
    			$("#conlist").html(dataStr)
    			
    			//设置排序字段
    			var tableId="conn_table";
    			new TableSorter(tableId,1,2,3);
    			$("#"+tableId+"Sort th").on('click', function(event){
    				
    				if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
    					
    					$("#"+tableId+" .tab_angle").attr("class","tab_angle");
    					$(this).find(".tab_angle").addClass("tab_angle_top");
    					$(this).find(".tab_angle").removeClass("tab_angle_bottom");
    				}else{
    					$("#"+tableId+" .tab_angle").attr("class","tab_angle");
    					$(this).find(".tab_angle").addClass("tab_angle_bottom");
    					$(this).find(".tab_angle").removeClass("tab_angle_top");
    				}
    			  });
    			$(".numOrder").click(function(){
    				 var num=0;
    				  $("#conn_table tr").each(function(index, element) {
    					 if( $(this).css("display") != "none"){
    						 num+=1;
    						 $("#conn_table tr:eq("+index+") td:eq(0)").html(num);
//    						 $(this).parents("tr").find(".font_701999").html(num);
    					 }else if($(this).css("display") == "table-row"){
    						 num+=1;
    						 $("#conn_table tr:eq("+index+") td:eq(0)").html(num);
//    						 $(this).parents("tr").find(".font_701999").html(num);
    					 }

    				});
    				
    			});
    		}
    	}
    }).fail(function (data) {
    	alert(data.errmsg);
    	return;
    }).always(function () {

    });
	
}

//excel导出
function monitorExcel(){
	var siteCode=$("#siteCode").val();
	var days=$("input[name='days']:checked").val();
//	var key=$("#keyInput").val();
	window.location.href=webPath+"/monitorInclude_exportExcel.action?days="+days+"&siteCode="+siteCode;
}

 //折线图加载
function monitorData(){
	//var siteCode=$("#siteCode").val();
	//var days=$("input[name='days']:checked").val();
//    var ylist = new Array();
//	var xlist = new Array();
    var myChart_line = echarts.init(document.getElementById("includeChartDiv"));
/*    $.ajax( {  
        type : "POST",  
        url : webPath+"/baiduBigData_includeChart.action",  
        dataType:"json",
        async : false,
        data:{
        	orgSiteCode:siteCode,
        	dateNum:days
        },
        success : function(data) {
        	if(data.length>0){
        		xlist=data[0].xList;
            	ylist=data[0].yList;
        	}
        }
       });*/
    var min=0;
    var max = 0;
    if(ylist.length>0){
    	var allIndexListSort = ylist.concat(ylist);
    	allIndexListSort.sort(function(a,b){return a-b});
    	min=parseInt(allIndexListSort[0]);
    	max = parseInt(allIndexListSort[allIndexListSort.length-1]);
    }
    if(max ==0 && min==0){
    	max=max+10;
    }
    option = {
		    color:[
               '#36cd4f'
              ],
    	    tooltip : {
    	        trigger: 'axis',
    	         borderColor: '#48b',
    	         backgroundColor: 'rgba(0,0,0,0.5)',
    	          axisPointer: {
		         color: 'rgba(150,150,150,0.3)',
		        width: 'auto',
		        type: 'default'
		      },
		       padding:15,
              textStyle : {
		           fontSize: 12
		           },
              formatter: function (params,ticket,callback) {
  	            var res =params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
  	            for (var i = 0, l = params.length; i < l; i++) {
  	                res += '<br/>站点收录网页数 : ' + params[i].value+'个';
  	            }
  	            setTimeout(function (){
  	                callback(ticket, res);
  	            }, 0)
  	        } 
    	    },
    	    dataZoom : {
    	        show : true,
    	        realtime : true,
    	        height: 20,
    	        start : 60,
    	        end : 100
    	    },
    	    grid:{
    	    	borderColor:'#fff'
             }, 
    	    xAxis : [
    	        {
    	            axisLine:false,
                    axisTick:false,
    	        	splitLine:false,
    	            type : 'category',
    	            boundaryGap : false,
    	            data : xlist
    	        }
    	    ],
    	    yAxis : [
              {   
            	  min:min,
                  max:max,
                
                  name:'',
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
    	            name:'更新数量',
    	            type:'line',
    	            symbol:'emptyCircle',
    	            symbolSize:4,
    	            itemStyle: {
    	            backgroundColor:'rgba(0,0,0,0.5)',
                       normal: {
                         areaStyle: {
                           type: 'default',
                           color:'#e9f9ed'
                              }
                        }},
    	            data: ylist
    	        }
    	    ],
    	    calculable:false
    	};
    myChart_line.setOption(option,true);
}