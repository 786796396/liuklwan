var ch = 0;
var type = 1;
$(function () {
   $("input[name='checkboxId']").iCheck({
    	checkboxClass : 'icheckbox_square-blue',
    	radioClass : 'iradio_square-blue'
    });	
   //复选框  
 	$("[name='checkboxId']").on('ifChanged', function(event){
 		var checkType = $(this).prop("checked");
 		if(checkType){
 			ch = 5;
 		}else{
 			ch = "";
 		}
 		webSiteConnectedTable();
 	});
 	
	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
		 if(searchInfo==""){
			 $("#webSiteConnectedTbody tr").show();
		 }else{
			 $("#webSiteConnectedTbody").find(".cor-01a5ec").each(function(index, element) {
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
		webSiteConnectedTable();
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
		webSiteConnectedTable();
	}
});

var yesterday = $("#yesterdayId").val();
$("#startDate").val(yesterday);
$("#endDate").val(yesterday);

webSiteConnectedTable();

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
        webSiteConnectedTable();
    });
}

function webSiteConnectedChange(){
	webSiteConnectedTable();
}

function webSiteConnectedTable(){
	 $("#webSiteConnectedTbody").html(copyInformation(2,null));
	var siteType = $("#siteTypeVal").val();
	var monitoringId = $("#aaaTypeVal").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(monitoringId == 6 || monitoringId == 0){
		//合成事件 hover()  
        $("#thId").hover(function (){  
            $("#divId").show();  
        },function (){  
            $("#divId").hide();  
        });  
		
	}else{
	     $("#thId").hover(function (){  
	    	 $("#divId").hide();
	     }); 
	}
	
	if(startDate > endDate){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}else{
   	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionHomeDetail_connectionAllHomeTable.action",  
         data : {
        	 siteType:siteType,
        	 monitoringId:monitoringId,
        	 percentage:ch,
        	 startDate:startDate,
        	 endDate:endDate,
        	 type:type
         },  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 if(data.success == 'true'){
        		 $("#webSiteConnectedTbody").html("");
        		 $("#table-Sum").html("");
        		 $("#sizeId").html("");
        		 var html = '';
        		 var sumHtml = '';
        		 var list = data.body;
        		 if(list.length > 0 && list != null){
        			 
        			 $("#sizeId").html(data.size);
        			 sumHtml+='<tr >';
        			 sumHtml+='<td class="summary-td padl-18 w15p">汇总<i class="summary-icon"></i></td>';
        			 sumHtml+='<td class="td-center w20p"><i class="td_num td_num_57a1f2">'+data.homeUrlNum+'</i></td>';
        			 sumHtml+='<td class="td-center w10p"></td>';
        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_b233b9">'+data.allConNum+'</i></td>';
        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_51d466">'+data.allConnectedNum+'</i></td>';
        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_4e4e4e">'+data.allSuccessThan+'</i></td>';
        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_607d8b">'+data.allNoConnectedNum+'</i></td>';
        			 sumHtml+='<td class="td-center w10p"><i class="td_num td_num_ef6333">'+data.allErrorThan+'</i></td>';
        			 sumHtml+=' </tr>';
        			 
        			 $("#table-Sum").append(sumHtml);
        			 
        			 var ur = webPath + "/fillUnit_gailan.action";
        			 var uriThree = webPath + "/connectionHomeDetail_index.action";
        			 
        			 $.each(list, function(index, obj) {
        				 var url = obj.url;
        				 if(url != ""){
        					 if(!url.match("http")){
     	        				 url="http://"+url;
     	        			 }
        				 }
        				
        				 var uri = url.substr(0,30);
        				 html+='<tr>';
        				 html+='<td class="td-left first-row wz-name w15p"><a onClick="changeCookie(\''+2+'\',\''+ur+'\',\''+null+'\',\''+uriThree+'\');" href="'+webPath+'/connectionHomeDetail_index.action?siteCode='+obj.siteCode+'" target="_blank" class="cor-01a5ec">'+obj.siteName+'<br>'+obj.siteCode+'</a></td>';
        				 html+='<td class="td-center w20p" title="'+url+'"><a href="'+url+'" target="_blank" class="sort-num cor-01a5ec url">'+uri+'</a></td>';
        				 html+='<td class="td-center w10p">'+obj.quName+'</td>';
        				 html+='<td class="td-center sort-num w10p">'+obj.connectionSum+'</td>';
        				 html+='<td class="td-center sort-num w10p">'+obj.successNum+'</td>';
        				 html+='<td class="td-center sort-num w10p"><span class="sort-num">'+obj.successThan+'</span>%</td>';
        				 html+='<td class="td-center sort-num w10p"><a onClick="changeCookie(\''+2+'\',\''+ur+'\',\''+null+'\',\''+uriThree+'\');" href="'+webPath+'/connectionHomeDetail_index.action?siteCode='+obj.siteCode+'" target="_blank" class="sort-num">'+obj.errorNum+'</a></td>';
        				 html+='<td class="td-center sort-num w10p"><span class="sort-num">'+obj.errorThan+'</span>%</td>';
        				 html+='</tr>';                 
        			 });
        			 $("#webSiteConnectedTbody").append(html);
        		 }else{
        			 var text = copyInformation(1,"未发现问题");
	    			 $("#webSiteConnectedTbody").append(text);
        		 }
        	 }
         },
         error:function(data){
 			alert(data.errorMsg);
 		 }
        });
   //列表排序
	 new TableSorter("table-web",3,4,5,6,7);
   	}

}

function webSiteConnectedExcel(){
	var siteType = $("#siteTypeVal").val();
	var monitoringId = $("#aaaTypeVal").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(startDate > endDate){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}else{
   		window.location.href = webPath + "/connectionHomeDetail_connectionAllHomeTableExcel.action?siteType=" 
   								+ siteType + "&startDate=" + startDate + "&endDate=" + endDate + "&monitoringId=" + monitoringId
   								+ "&percentage=" + ch + "&type=" + type   ;
   	}
}
